package ekp.serviceFacade.rmi;

import java.awt.ActiveEvent;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import ekp.InitApplication;
import ekp.SystemInfo;
import legion.datasource.source.RmiSslClientSocketFactory;
import legion.datasource.source.RmiSslServerSocketFactory;
import legion.util.BeanUtil;
import legion.util.DataFO;
import legion.util.LogUtil;

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

			/* */
			if(SystemTray.isSupported()) {
				log.info("SystemTray is supported.");
				SystemTray systemTray = SystemTray.getSystemTray();
				TrayIcon trayIcon = new TrayIcon(ServerStatus.init.getTrayIcon(), SystemInfo.getInstance().getName()+" "+SystemInfo.getInstance().getVersion());
				trayIcon.setImageAutoSize(true);
				systemTray.add(trayIcon);
				
				Cursor cursorHand = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				
				JPopupMenu popup = new JPopupMenu("選單");
				JMenuItem miStatus = new JMenuItem(SystemInfo.getInstance().getName()+" "+SystemInfo.getInstance().getVersion());
				miStatus.setIcon(ServerStatus.init.getStatusIcon());
				popup.add(miStatus);
				popup.addSeparator();
				
				JMenuItem miStart = new JMenuItem("啟動");
				miStart.setActionCommand(ServerCommand.start.getId());
				miStart.setIcon(ServerCommand.start.getIcon());
				miStart.setCursor(cursorHand);
				popup.add(miStart);
				
				JMenuItem miStop = new JMenuItem("停止");
				miStop.setActionCommand(ServerCommand.stop.getId());
				miStop.setIcon(ServerCommand.stop.getIcon());
				miStop.setCursor(cursorHand);
				popup.add(miStop);
				
				JMenuItem miClose = new JMenuItem("關閉");
				miClose.setActionCommand(ServerCommand.close.getId());
				miClose.setIcon(ServerCommand.close.getIcon());
				miClose.setCursor(cursorHand);
				popup.add(miClose);
				
				popup.addSeparator();
				
				JMenuItem miMinus = new JMenuItem("隱藏");
				miMinus.setActionCommand(ServerCommand.minus.getId());
				miMinus.setIcon(ServerCommand.minus.getIcon());
				miMinus.setCursor(cursorHand);
				popup.add(miMinus);
				
				ActionListener actionListener = event -> {
					try {
						// start
						if (ServerCommand.start.getId().equalsIgnoreCase(event.getActionCommand())) {
							getInstance().startRmi();
							getInstance().listService();
							miStatus.setIcon(ServerStatus.running.getStatusIcon());
							trayIcon.setImage(ServerStatus.running.getTrayIcon());
							
							miStart.setEnabled(false);
							miStop.setEnabled(true);
						}
						// stop
						else if(ServerCommand.stop.getId().equalsIgnoreCase(event.getActionCommand())) {
							getInstance().stopRmi();
							miStatus.setIcon(ServerStatus.stop.getStatusIcon());
							trayIcon.setImage(ServerStatus.stop.getTrayIcon());

							miStart.setEnabled(true);
							miStop.setEnabled(false);
						}
						// minus
						else if (ServerCommand.minus.getId().equalsIgnoreCase(event.getActionCommand())) {
							popup.setVisible(false);
						}
						// close
						else if (ServerCommand.close.getId().equalsIgnoreCase(event.getActionCommand())) {
							getInstance().stopRmi();
							miStatus.setIcon(ServerStatus.warning.getStatusIcon());
							trayIcon.setImage(ServerStatus.warning.getTrayIcon());
							
							miStart.setEnabled(false);
							miStop.setEnabled(false);
							System.exit(0);
						}

					} catch (Exception e) {
						LogUtil.log(e, Level.ERROR);
						miStatus.setIcon(ServerStatus.warning.getStatusIcon());
						trayIcon.setImage(ServerStatus.warning.getTrayIcon());
						miStart.setEnabled(false);
						miStop.setEnabled(false);
					}
				};
				
				miStatus.addActionListener(actionListener);
				miStart.addActionListener(actionListener);
				miStop.addActionListener(actionListener);
				miMinus.addActionListener(actionListener);
				miClose.addActionListener(actionListener);
				trayIcon.addActionListener(actionListener);
				
				trayIcon.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (!popup.isVisible())
							popup.show(e.getComponent(), e.getX() + 20, e.getY() - 120);
						else
							popup.setVisible(false);
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
					}
					
					@Override
					public void mouseReleased(MouseEvent e) {
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
					}
				});
				
				// 觸發啟動系統事件
				ActionEvent event = new ActionEvent(trayIcon, ActionEvent.ACTION_FIRST, ServerCommand.start.getId());
				actionListener.actionPerformed(event);
			}else {
				// 直接啟用
				getInstance().startRmi();
				getInstance().listService();
			}
			
		}catch (Exception e) {
			System.err.println("Something wrong happened on the remote end");
			e.printStackTrace();
			System.exit(-1); // can't just return, rmi threads may not exit
		}
		System.out.println("The rmi server is ready.");
	}
	
	
	// -------------------------------------------------------------------------------
	// ----------------------------------inner_class----------------------------------
	enum ServerStatus {
		init("init", "tray_s.png", "question.png"), //
		running("running", "tray_s_running.png", "status_running.png"), //
		stop("stop", "tray_s_stop.png", "status_stop.png"), //
		warning("warning", "tray_s_warning.png", "status_warning.png"), //
		;

		private String id, trayIconName, statusIconName;
		private Image trayIcon;
		private ImageIcon statusIcon;

		private ServerStatus(String id, String trayIconName, String statusIconName) {
			this.id = id;
			this.trayIconName = trayIconName;
			this.statusIconName = statusIconName;
			trayIcon = Toolkit.getDefaultToolkit().getImage(EkpKernelRmiServer.class.getResource(trayIconName));
			statusIcon = new ImageIcon(EkpKernelRmiServer.class.getResource(statusIconName));
		}

		// ---------------------------------------------------------------------------
		public String getId() {
			return id;
		}

		public String getTrayIconName() {
			return trayIconName;
		}

		public String getStatusIconName() {
			return statusIconName;
		}

		public Image getTrayIcon() {
			return trayIcon;
		}

		public ImageIcon getStatusIcon() {
			return statusIcon;
		}

		// ---------------------------------------------------------------------------
		public static ServerStatus get(String _id) {
			for (ServerStatus item : ServerStatus.values())
				if (_id.equals(item.getId()))
					return item;
			return null;
		}

	}
	
	enum ServerCommand{
		start("start","start.png"), //
		stop("stop","stop.png"), //
		minus("minus","minus.png"), //
		close("close","power.png"), //
		;
		
		private String id, iconName;
		private ImageIcon icon;
		
		private ServerCommand(String id, String iconName) {
			this.id = id;
			this.iconName = iconName;
			icon = new ImageIcon(EkpKernelRmiServer.class.getResource(iconName));
		}
		
		// ---------------------------------------------------------------------------
		public String getId() {
			return id;
		}

		public String getIconName() {
			return iconName;
		}

		public ImageIcon getIcon() {
			return icon;
		}
		
		// ---------------------------------------------------------------------------
		public static ServerCommand get(String _id) {
			for (ServerCommand item : ServerCommand.values())
				if (_id.equals(item.getId()))
					return item;
			return null;
		}
		
	}

}
