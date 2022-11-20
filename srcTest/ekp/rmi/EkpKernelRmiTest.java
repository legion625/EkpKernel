package ekp.rmi;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.cj.Query;

import ekp.data.service.mbom.query.PartQueryParam;
import ekp.serviceFacade.rmi.EkpKernelServiceRemote;
import ekp.serviceFacade.rmi.mbom.PartCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.PartRemote;
import legion.datasource.manager.DSManager;
import legion.util.query.QueryOperation;

public class EkpKernelRmiTest extends AbstractEkpKernelRmiInitTest {
	private static Logger log = LoggerFactory.getLogger(EkpKernelRmiTest.class);
	
	private static EkpKernelServiceRemote serviceRemote;
	
	@BeforeClass
	public static void beforeClass() {
		serviceRemote = (EkpKernelServiceRemote) DSManager.getInstance().getConn("ekp_kernel_rmi");
		Assert.assertNotNull(serviceRemote);
	}
	
	// -------------------------------------------------------------------------------
	@Test
	public void test() throws RemoteException {
		log.debug("serviceRemote.testCallBack(): {}", serviceRemote.testCallBack());
		System.out.println("123");
	}
	
	// -------------------------------------------------------------------------------
	// -------------------------------------Mbom--------------------------------------
	@Test
	public void testCreatePart() throws RemoteException {
		PartCreateObjRemote partCo = new PartCreateObjRemote();
		partCo.setPin("A1");
		partCo.setName("快樂機器人");

		PartRemote partRemote = serviceRemote.createPart(partCo);
		assertNotNull(partRemote);
		log.debug("{}\t{}\t{}", partRemote.getUid(), partRemote.getPin(), partRemote.getName());
	}
	
	@Test
	public void testLoadPart() throws RemoteException {
		PartRemote part1 = serviceRemote.loadPart("2022!2!8!1");
		PartRemote part2 = serviceRemote.loadPartByPin("A1");
		log.debug("{}\t{}", part1.getUid(), part2.getUid());
		assertTrue(part1.equals(part2));
		
	}
	
	@Test
	public void testSearchPart() throws RemoteException {
		QueryOperation<PartQueryParam, PartRemote> paramRemote = new QueryOperation<>();
		paramRemote.setLimit(5);
		paramRemote = serviceRemote.searchPart(paramRemote);
		log.debug("paramRemote.getTotal(): {}", paramRemote.getTotal());
		log.debug("limit: {}\t{}", paramRemote.getLimit()[0], paramRemote.getLimit()[1]);
		log.debug("paramRemote.getQueryResult().size(): {}", paramRemote.getQueryResult().size());
	}

}
