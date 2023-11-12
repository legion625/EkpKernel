package ekp.pu;

import java.time.LocalDateTime;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.PuDataService;
import ekp.pu.fsm.PurchPerfFsm;
import ekp.pu.type.PurchPerfStatus;
import legion.DataServiceFactory;
import legion.util.DateUtil;

public class BizObjPurchTest extends AbstractEkpInitTest{
	private static PuDataService dataService = DataServiceFactory.getInstance().getService(PuDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		long l1 = DateUtil.toLong(LocalDateTime.now().plusDays(1));
		long l2 = DateUtil.toLong(LocalDateTime.now().plusDays(2));
		target1 = new Target("puNo1", "title1","supplierUid1", "supplierName1", "supplierBan1", PurchPerfStatus.TO_PERF, l1);
		target2 = new Target("puNo2", "title2","supplierUid2", "supplierName2", "supplierBan2", PurchPerfStatus.PERFED, l2);
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreatePurch();
		testUpdatePurch();
		testDeletePurch();
	}

	@Test
	@Ignore
	public void testCreatePurch() throws Throwable {
		/* create */
		Purch obj = Purch.newInstance();
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadPurch(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdatePurch() throws Throwable {
		Purch obj = dataService.loadPurch(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadPurch(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeletePurch() {
		assert dataService.loadPurch(targetUid).delete();
	}

	public class Target {
		private String puNo; // 購案案號
		private String title; // 名稱
		private String supplierUid;
		private String supplierName;
		private String supplierBan; // 供應商統編（臺灣）

		private PurchPerfStatus perfStatus;

		private long perfTime; // 履約時間

		private Target(String puNo, String title, String supplierUid, String supplierName, String supplierBan, PurchPerfStatus perfStatus,
				long perfTime) {
			this.puNo = puNo;
			this.title = title;
			this.supplierUid = supplierUid;
			this.supplierName = supplierName;
			this.supplierBan = supplierBan;
			this.perfStatus = perfStatus;
			this.perfTime = perfTime;
		}

		public String getPuNo() {
			return puNo;
		}

		public String getTitle() {
			return title;
		}
		
		public String getSupplierUid() {
			return supplierUid;
		}

		public String getSupplierName() {
			return supplierName;
		}

		public String getSupplierBan() {
			return supplierBan;
		}

		public PurchPerfStatus getPerfStatus() {
			return perfStatus;
		}

		public long getPerfTime() {
			return perfTime;
		}

	}
}
