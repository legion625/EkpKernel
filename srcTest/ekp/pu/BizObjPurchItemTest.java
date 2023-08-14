package ekp.pu;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.PuDataService;
import ekp.mbom.type.PartUnit;
import legion.DataServiceFactory;

public class BizObjPurchItemTest extends AbstractEkpInitTest {
	private static PuDataService dataService = DataServiceFactory.getInstance().getService(PuDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		double d1 = 199.9999d;
		double d2 = 299.9999d;
		target1 = new Target("purchUid1", "mmUid1", "mmMano1", "mmName1", "mmSpecification1", PartUnit.CMK, d1, d1,
				"remark1");
		target2 = new Target("purchUid2", "mmUid2", "mmMano2", "mmName2", "mmSpecification2", PartUnit.CMK, d2, d2,
				"remark2");
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreatePurchItem();
		testUpdatePurchItem();
		testDeletePurchItem();
	}

	@Test
	@Ignore
	public void testCreatePurchItem() throws Throwable {
		/* create */
		PurchItem obj = PurchItem.newInstance(target1.purchUid);
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadPurchItem(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdatePurchItem() throws Throwable {
		PurchItem obj = dataService.loadPurchItem(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadPurchItem(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeletePurchItem() {
		assert dataService.loadPurchItem(targetUid).delete();
	}

	public class Target {
		/* purchUid+mmUid作為biz key */
		private String purchUid;
		private String mmUid;

		/* 快照了物料基本檔當下的各欄位 */
		private String mmMano; // 物料基本檔料號
		private String mmName; // 品名
		private String mmSpecification;
		private PartUnit mmStdUnit;
		// 依物料基本檔輸入採購的數量和總價
		private double qty;
		private double value;
		//
		private String remark; // 備註（補充說明）

		private Target(String purchUid, String mmUid, String mmMano, String mmName, String mmSpecification,
				PartUnit mmStdUnit, double qty, double value, String remark) {
			this.purchUid = purchUid;
			this.mmUid = mmUid;
			this.mmMano = mmMano;
			this.mmName = mmName;
			this.mmSpecification = mmSpecification;
			this.mmStdUnit = mmStdUnit;
			this.qty = qty;
			this.value = value;
			this.remark = remark;
		}

		public String getPurchUid() {
			return purchUid;
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

		public String getMmSpecification() {
			return mmSpecification;
		}

		public PartUnit getMmStdUnit() {
			return mmStdUnit;
		}

		public double getQty() {
			return qty;
		}

		public double getValue() {
			return value;
		}

		public String getRemark() {
			return remark;
		}

	}
}
