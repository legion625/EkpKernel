package ekp.sd;

import java.time.LocalDateTime;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.PuDataService;
import ekp.data.SdDataService;
import ekp.pu.Purch;
import ekp.pu.BizObjPurchTest.Target;
import ekp.pu.type.PurchPerfStatus;
import legion.DataServiceFactory;
import legion.util.DateUtil;

public class BizObjSalesOrderItemTest extends AbstractEkpInitTest {
	private static SdDataService dataService = DataServiceFactory.getInstance().getService(SdDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		double d1 = 199.999d;
		double d2 = 199.999d;
		long l1 = DateUtil.toLong(LocalDateTime.now().plusDays(1));
		long l2 = DateUtil.toLong(LocalDateTime.now().plusDays(2));
		target1 = new Target("soUid1", "mmUid1", "mmMano1", "mmName1", "mmSpec1", d1, d1, false, l1);
		target2 = new Target("soUid2", "mmUid2", "mmMano2", "mmName2", "mmSpec2", d2, d2, true, l2);
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateSalesOrderItem();
		testUpdateSalesOrderItem();
		testDeleteSalesOrderItem();
	}

	@Test
	@Ignore
	public void testCreateSalesOrderItem() throws Throwable {
		/* create */
		SalesOrderItem obj = SalesOrderItem.newInstance(target1.soUid);
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadSalesOrderItem(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateSalesOrderItem() throws Throwable {
		SalesOrderItem obj = dataService.loadSalesOrderItem(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadSalesOrderItem(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteSalesOrderItem() {
		assert dataService.loadSalesOrderItem(targetUid).delete();
	}

	public class Target {
		private String soUid;
		private String mmUid;
		private String mmMano;
		private String mmName;
		private String mmSpec;
		private double qty;
		private double value;

		private boolean allDelivered; // 是否已交貨
		private long finishDeliveredDate;

		private Target(String soUid, String mmUid, String mmMano, String mmName, String mmSpec, double qty,
				double value, boolean allDelivered, long finishDeliveredDate) {
			this.soUid = soUid;
			this.mmUid = mmUid;
			this.mmMano = mmMano;
			this.mmName = mmName;
			this.mmSpec = mmSpec;
			this.qty = qty;
			this.value = value;
			this.allDelivered = allDelivered;
			this.finishDeliveredDate = finishDeliveredDate;
		}

		public String getSoUid() {
			return soUid;
		}

		public String getMmUid() {
			return mmUid;
		}

		public String getMmMano() {
			return mmMano;
		}

		public String getMmName() {
			return mmName;
		}

		public String getMmSpec() {
			return mmSpec;
		}

		public double getQty() {
			return qty;
		}

		public double getValue() {
			return value;
		}

		public boolean isAllDelivered() {
			return allDelivered;
		}

		public long getFinishDeliveredDate() {
			return finishDeliveredDate;
		}

	}
}
