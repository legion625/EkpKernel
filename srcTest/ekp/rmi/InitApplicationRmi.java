package ekp.rmi;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ekp.SystemInfo;
import legion.datasource.manager.DSManager;
import legion.datasource.source.RmiSslClientSocketFactory;
import legion.util.DataFO;
import legion.util.LogUtil;

public class InitApplicationRmi {

	private static Logger log = LoggerFactory.getLogger(InitApplicationRmi.class);

	private static InitApplicationRmi instance = new InitApplicationRmi();

	private InitApplicationRmi() {
	}

	public static InitApplicationRmi getInstance() {
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
		registerClientRmiSSL(_sys);
		initDatasource(_sys);
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
		if (DataFO.isEmptyString(logfile)) {
			log.info("initLogback fail: logfile [{}]", logfile);
			return;
		}
		
		URL configURL = InitApplicationRmi.class.getResource(logfile);
		log.info("logback-init-file: {}", configURL);
		LogbackConfigure.initLogback(configURL);
	}
	
	// -------------------------------------------------------------------------------
	private static class LogbackConfigure {
		static void initLogback(URL _logFile) {
			LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
			try {
				JoranConfigurator jc = new JoranConfigurator();
				jc.setContext(lc);
				lc.reset();
				jc.doConfigure(_logFile);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				log.error("initLogback fail... {}", e.getMessage());
			}
		}
	}
	
	// -------------------------------------------------------------------------------
	// ------------------------------------rmiSsl-------------------------------------
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
			clientKeyStream = InitApplicationRmi.class.getResourceAsStream(_sys.getAttribute("clientKeyStore"));
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
			inStream = InitApplicationRmi.class.getResourceAsStream(_sys.getAttribute("datasource-file"));
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
}
