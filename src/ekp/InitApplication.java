package ekp;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import legion.BusinessServiceFactory;
import legion.DataServiceFactory;
import legion.LegionContext;
import legion.datasource.manager.DSManager;
import legion.datasource.source.RmiSslClientSocketFactory;
import legion.datasource.source.RmiSslServerSocketFactory;
import legion.util.DataFO;
import legion.util.LogUtil;

public class InitApplication {

	private static Logger log = LoggerFactory.getLogger(InitApplication.class);
	
	private static InitApplication instance = new InitApplication();

	private InitApplication() {

	}

	public static InitApplication getInstance() {
		return instance;
	}
	
	// -------------------------------------------------------------------------------
	// -------------------------------------init--------------------------------------
	private boolean init = false;
	
	public void init(SystemInfo _sys, boolean _reInit) {
		if (init && !_reInit)
			return;
		log.debug("Application init...");
		initLog(_sys);
		registerServerRmiSSL(_sys);
		registerClientRmiSSL(_sys);
		initDatasource(_sys);
		initIntegrationServiceModule(_sys);
		initBusinessServiceModule(_sys);
		log.debug("Application init completed...");
		init = true;
	}
	
	// -------------------------------------------------------------------------------
	// ------------------------------------initLog------------------------------------
	public static void initLog(SystemInfo _sys) {
		log.info("initLog...");
		// 初始Logback環境
		initLogback(_sys);
	}
	
	protected static void initLogback(SystemInfo _sys) {
		String logfile = _sys.getAttribute("logback-init-file");
		System.out.println("logfile: " +logfile);
		if (DataFO.isEmptyString(logfile)) {
			log.info("initLogback fail: logfile [{}]", logfile);
			return;
		}
		
		URL configURL = InitApplication.class.getResource(logfile);
		log.info("logback-init-file: {}", configURL);
		LogbackConfigure.initLogback(configURL);
	}
	
	
	// -------------------------------------------------------------------------------
	private static class LogbackConfigure{

		static void initLogback(URL _logfile) {
			// assume slf4j is bound to logback in the current environment
			LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(lc);
			lc.reset();
			try {
				configurator.doConfigure(_logfile);
			} catch (JoranException e) {
				log.error(e.getMessage(), e);
				log.error("initLogback fail... {}", e.getMessage());
			}

		}

	}
	
	// -------------------------------------------------------------------------------
	// ------------------------------------rmiSsl-------------------------------------
	private void registerServerRmiSSL(SystemInfo _sys) {
		log.debug("registerServerRmiSSL...");
		// 註冊SSL keyStore
		InputStream serverKeyStream = null;
		try {
			if (DataFO.isEmptyString(_sys.getAttribute("serverKeyStore"))) {
				log.warn("No RegisterServerRmiSSL");
				return;
			}
			// 設定底層SSL連結
			serverKeyStream = InitApplication.class.getResourceAsStream(_sys.getAttribute("serverKeyStore"));
			byte[] serverKeyByte = new byte[serverKeyStream.available()];
			serverKeyStream.read(serverKeyByte);
			serverKeyStream.close();
			RmiSslServerSocketFactory.RmiSslServerSocketFactoryProperties.setInfo(serverKeyByte,
					_sys.getAttribute("serverKeyStorePass"), _sys.getAttribute("serverKeyStoreType"));
			RmiSslServerSocketFactory.RmiSslServerSocketFactoryProperties.setup(true);
		} catch (Exception e) {
			LogUtil.log(e, Level.ERROR);
		} finally {
			try {
				if (serverKeyStream != null)
					serverKeyStream.close();
			} catch (IOException e) {
				LogUtil.log(e, Level.ERROR);
			}
		}
	}

	private void registerClientRmiSSL(SystemInfo _sys) {
		log.debug("registerClientRmiSSL...");
		// 註冊SSL keystore
		InputStream clientKeyStream = null;
		try {
			if (DataFO.isEmptyString(_sys.getAttribute("clientKeyStore"))) {
				log.warn("No register clientRmiSSL...");
				return;
			}

			// 底層SSL連結設定
			clientKeyStream = InitApplication.class.getResourceAsStream(_sys.getAttribute("clientKeyStore"));
			byte[] clientKeyByte = new byte[clientKeyStream.available()];
			clientKeyStream.read(clientKeyByte);
			clientKeyStream.close();

			RmiSslClientSocketFactory.RmiSslClientSocketFactoryProperties.setInfo(clientKeyByte,
					_sys.getAttribute("clientKeyStorePass"), _sys.getAttribute("clientKeyStoreType"));
			RmiSslClientSocketFactory.RmiSslClientSocketFactoryProperties.setup(true);
		} catch (IOException e) {
			LogUtil.log(e, Level.ERROR);
		} finally {
			try {
				if (clientKeyStream != null)
					clientKeyStream.close();
			} catch (IOException e) {
				LogUtil.log(e, Level.ERROR);
			}
		}
	}
	
	// -------------------------------------------------------------------------------
	// ----------------------------------datasource-----------------------------------
	private void initDatasource(SystemInfo _sys) {
		log.debug("initDatasource...");
		InputStream inStream = null;
		try {
			inStream = InitApplication.class.getResourceAsStream(_sys.getAttribute("datasource-file"));
			DSManager.getInstance().registerDatasourceXml(inStream, false);
		} catch (Throwable e) {
			LogUtil.log(e, Level.ERROR);
		} finally {
			if (inStream != null)
				try {
					inStream.close();
				} catch (IOException e) {
					LogUtil.log(e, Level.ERROR);
				}
		}

	}

	// -------------------------------------------------------------------------------
	// ---------------------------------serviceModule---------------------------------
	protected void initIntegrationServiceModule(SystemInfo _sys) {
		log.debug("initIntegrationServiceModule...");
		InputStream inStream = null;
		try {
			inStream = InitApplication.class.getResourceAsStream(_sys.getAttribute("Service-Module-Integration-file"));
			// DataService
			log.debug("_sys.getAttribute(\"Service-Module-Integration-file\") {}",
					_sys.getAttribute("Service-Module-Integration-file"));
			log.debug("inStream: {}", inStream);
			if (!DataServiceFactory.getInstance().registerService(inStream))
				log.error(
						"initIntegrationServiceModule fail ... DataServiceFactory.getInstance().registerService return false.");
		} catch (Throwable e) {
			LogUtil.log(e, Level.ERROR);
		} finally {
			if (inStream != null)
				try {
					inStream.close();
				} catch (IOException e) {
					LogUtil.log(e, Level.ERROR);
				}
		}
	}

	protected void initBusinessServiceModule(SystemInfo _sys) {
		log.debug("initBusinessServiceModule...");
		InputStream inStream = null;
		try {
			inStream = InitApplication.class.getResourceAsStream(_sys.getAttribute("Service-Module-Business-file"));
			// BusinessService
			if (!BusinessServiceFactory.getInstance().registerService(inStream))
				log.error(
						"initBusinessServiceModule fail ... BusinessServiceFactory.getInstance().registerService return false.");
			log.debug("*.Legionmodule Version [{}] ...", LegionContext.getInstance().getVersion());
			LegionContext.getInstance().getSystemInfo().putAttribute("legionmodule.version",
					LegionContext.getInstance().getVersion());
		} catch (Throwable e) {
			LogUtil.log(e, Level.ERROR);
		} finally {
			if (inStream != null)
				try {
					inStream.close();
				} catch (IOException e) {
					LogUtil.log(e, Level.ERROR);
				}
		}
	}
	
}
