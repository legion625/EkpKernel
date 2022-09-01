package ekp.rmi;


import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.SystemInfo;

public class AbstractEkpKernelRmiInitTest {
	protected static Logger log = LoggerFactory.getLogger(AbstractEkpKernelRmiInitTest.class);
	
	static {
		SystemInfo sys = SystemInfo.getInstance();
		sys.init("/ekp/rmi/system-conf-rmi.xml");
		InitApplicationRmi.getInstance().init(sys, true);
	}
	
	@BeforeClass
	public static void setupBeforeClass() {
		// 啟始系統設定
		SystemInfo sys = SystemInfo.getInstance();
		sys.init("/ekp/rmi/system-conf-rmi.xml");
		InitApplicationRmi.getInstance().init(sys, false);
		assertNotNull("log null",log);
	}
	@AfterClass
	public static void teardownAfterClass() {
		log.debug("teardownAfterClass...");
	}
}
