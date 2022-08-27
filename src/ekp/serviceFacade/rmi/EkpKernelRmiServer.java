package ekp.serviceFacade.rmi;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Constructor;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import java.util.Hashtable;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.InitApplication;
import ekp.SystemInfo;
import legion.datasource.source.RmiSslClientSocketFactory;
import legion.datasource.source.RmiSslServerSocketFactory;
import legion.util.BeanUtil;
import legion.util.DataFO;

public class EkpKernelRmiServer {

	private static Logger log = LoggerFactory.getLogger(EkpKernelRmiServer.class);

	private String[] services;
	private String[] serviceImps;
	private Remote[] serviceObjects;
	private int[] serviceImpPorts;

	private Registry registry;

//	private String keyStoreConfName = "";
	private int port;
	private boolean ssl = true;

	// -------------------------------------------------------------------------------
	private static final EkpKernelRmiServer INSTANCE = new EkpKernelRmiServer();

	private EkpKernelRmiServer() {
		// 啟始系統設定
		InitApplication.getInstance().init(SystemInfo.getInstance(), false);
		ResourceBundle resource = ResourceBundle.getBundle("EkpKernelRmiServer");
		services = resource.getString("service").split(",");
		serviceImps = resource.getString("serviceImp").split(",");
		if (!resource.containsKey("serviceImpPort"))
			serviceImpPorts = new int[serviceImps.length];
		else {
			String[] ports = resource.getString("serviceImpPort").split(",");
			serviceImpPorts = new int[ports.length];
			for (int i = 0; i < ports.length; i++)
				serviceImpPorts[i] = Integer.parseInt(ports[i]);
		}
		// keyStoreConfName = resource.getString("keyStoreConfName");
		String portStr = resource.getString("port");
		if (!DataFO.isEmptyString(portStr))
			port = Integer.parseInt(portStr);

		String sslStr = resource.getString("sslGate");
		if (!DataFO.isEmptyString(sslStr) && sslStr.equalsIgnoreCase("true"))
			ssl = true;
		else
			ssl = false;
	}

	public static EkpKernelRmiServer getInstance() {
		return INSTANCE;
	}

	// -------------------------------------------------------------------------------
	protected int getPort() {
		return port;
	}

	// -------------------------------------------------------------------------------
	public void startRmi() throws RemoteException, Exception {
		log.info("");
		log.info("====================================");
		log.info("Server:Start Ekp Kernel Rmi Server");
		log.info("Rmi server ssl: {}", ssl);
		log.info("Rmi server port: {}", port);
		if (registry != null) {
			log.info("Server: Start Ekp Kernel Rmi Server fail, [registry has existed]");
			return;
		}

		//
		if ((services == null || services.length == 0) || (serviceImps == null || serviceImps.length == 0)
				|| (services.length != serviceImps.length)) {
			log.error("Server:Start Rmi Server Fail, [services configuration error]");
			throw new Exception("Server:Start Rmi Server Fail, [services configuration error]");
		}

//		Hashtable sslproperties = null;
//		if(!DataFO.isEmptyString(keyStoreConfName)) {
//			sslproperties = new Hashtable();
//			sslproperties.put(RmiSSL.KEYSTORE_CONF_DS_NAME, keyStoreConfName);
//		}

		/* 建立register... */
		if (ssl)
			registry = LocateRegistry.createRegistry(port, new RmiSslClientSocketFactory(),
					new RmiSslServerSocketFactory());
		else
			registry = LocateRegistry.createRegistry(port);

		serviceObjects = new Remote[services.length];
		for (int i = 0; i < services.length; i++) {
			Class<?> targetClass = BeanUtil.serviceClass(serviceImps[i]);
			Constructor<?> targetConstructor = targetClass.getConstructor(int.class);
			Object targetObj = targetConstructor.newInstance(serviceImpPorts[i]);
			registry.rebind(services[i], (Remote) targetObj);
			serviceObjects[i] = (Remote) targetObj;
			log.info("Server: register service[{},{},{}]", services[i], serviceImpPorts[i], serviceImps[i]);
		}

		log.info("************************************");
		log.info("*** Server: register service list ***");
		String[] services = registry.list();
		if (services != null && services.length > 0)
			for (int i = 0, count = services.length; i < count; i++)
				log.info("{} - {}[{}]", i, services[i], serviceImpPorts[i]);

		log.info("************************************");
		log.info("Ekp kernel rmi server[{}], is ready", port);
	}

	public void listService() throws AccessException, RemoteException {
		if (registry == null)
			return;
		String[] services = registry.list();
		if (services != null && services.length > 0)
			for (int i = 0, count = services.length; i < count; i++)
				log.info("{} - {}", i, services[i]);
	}

	public void stopRmi() throws RemoteException, NotBoundException {
		log.info("Server: stop rmi server");
		if (registry == null)
			return;
		String[] services = registry.list();
		if (services != null && services.length > 0)
			for (int i = 0, count = services.length; i < count; i++) {
				registry.unbind(services[i]);
				UnicastRemoteObject.unexportObject(serviceObjects[i], true);
			}
		UnicastRemoteObject.unexportObject(registry, true);
	}
	
	public static void main(String _args[]) {
		try {
			// 列出系統資訊
			Map<String, String> sps = ManagementFactory.getRuntimeMXBean().getSystemProperties();
			List<String[]> systemProperties = new ArrayList<>();
			log.info("*** System Properties ***");
			if (sps != null)
				for (Map.Entry<String, String> entry : sps.entrySet())
					log.info("{} - {}", entry.getKey(), entry.getValue());

			/* */
			log.info("*** JVM Input Arguments");
			List<String> inArgs = ManagementFactory.getRuntimeMXBean().getInputArguments();
			if (inArgs != null)
				for (String entry : inArgs)
					log.info("[{}]", entry);

			// TODO
			
		}catch (Exception e) {
			System.err.println("Something wrong happened on the remote end");
			e.printStackTrace();
			System.exit(-1); // can't just return, rmi threads may not exit
		}
		System.out.println("The rmi server is ready.");
	}

}
