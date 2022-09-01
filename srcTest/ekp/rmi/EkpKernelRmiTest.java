package ekp.rmi;

import java.rmi.RemoteException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.serviceFacade.rmi.EkpKernelServiceRemote;
import legion.datasource.manager.DSManager;

public class EkpKernelRmiTest extends AbstractEkpKernelRmiInitTest {
	private static Logger log = LoggerFactory.getLogger(EkpKernelRmiTest.class);
	
	private static EkpKernelServiceRemote serviceRemote;
	
	@BeforeClass
	public static void beforeClass() {
		serviceRemote = (EkpKernelServiceRemote) DSManager.getInstance().getConn("ekp_kernel_rmi");
		Assert.assertNotNull(serviceRemote);
	}
	
	@Test
	public void test() throws RemoteException {
		log.debug("serviceRemote.testCallBack(): {}", serviceRemote.testCallBack());
		System.out.println("123");
	}
}
