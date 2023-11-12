package ekp.sd;

import java.time.LocalDateTime;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.SdDataService;
import legion.DataServiceFactory;
import legion.util.DateUtil;

public class BizObjSalesOrderTest extends AbstractEkpInitTest {

	private static SdDataService dataService = DataServiceFactory.getInstance().getService(SdDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		long l1 = DateUtil.toLong(LocalDateTime.now().plusDays(1));
		long l2 = DateUtil.toLong(LocalDateTime.now().plusDays(2));
		target1 = new Target("sosn1", "title1","customerUid1", "customerName1", "customerBan1", "salerId1", "salerName1", l1);
		target2 = new Target("sosn2", "title2","customerUid2", "customerName2", "customerBan2", "salerId2", "salerName2", l2);
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateSalesOrder();
		testUpdateSalesOrder();
		testDeleteSalesOrder();
	}

	@Test
	@Ignore
	public void testCreateSalesOrder() throws Throwable {
		/* create */
		SalesOrder obj = SalesOrder.newInstance();
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadSalesOrder(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateSalesOrder() throws Throwable {
		SalesOrder obj = dataService.loadSalesOrder(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadSalesOrder(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteSalesOrder() {
		assert dataService.loadSalesOrder(targetUid).delete();
	}

	public class Target {
		private String sosn;
		private String title;
		private String customerUid;
		private String customerName;
		private String customerBan;

		private String salerId;
		private String salerName;
		private long saleDate;

		private Target(String sosn, String title,String customerUid, String customerName, String customerBan, String salerId,
				String salerName, long saleDate) {
			this.sosn = sosn;
			this.title = title;
			this.customerUid = customerUid;
			this.customerName = customerName;
			this.customerBan = customerBan;
			this.salerId = salerId;
			this.salerName = salerName;
			this.saleDate = saleDate;
		}

		public String getSosn() {
			return sosn;
		}

		public String getTitle() {
			return title;
		}

		public String getCustomerUid() {
			return customerUid;
		}

		public String getCustomerName() {
			return customerName;
		}

		public String getCustomerBan() {
			return customerBan;
		}

		public String getSalerId() {
			return salerId;
		}

		public String getSalerName() {
			return salerName;
		}

		public long getSaleDate() {
			return saleDate;
		}

	}
}
