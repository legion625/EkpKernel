package ekp.mf;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.MbomDataService;
import ekp.data.MfDataService;
import ekp.mbom.Prod;
import ekp.mbom.BizObjProdTest.Target;
import legion.DataServiceFactory;

public class BizObjWorkorderMaterialTest extends AbstractEkpInitTest {
	private static MfDataService dataService = DataServiceFactory.getInstance().getService(MfDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		double d1 = 199.999d;
		double d2 = 299.999d;
		target1 = new Target("woUid1", "woNo1", "mmUid1", "mmMano1", "mmName1", d1, d1);
		target2 = new Target("woUid2", "woNo2", "mmUid2", "mmMano2", "mmName2", d2, d2);
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateWorkorderMaterial();
		testUpdateWorkorderMaterial();
		testDeleteWorkorderMaterial();
	}

	@Test
	@Ignore
	public void testCreateWorkorderMaterial() throws Throwable {
		/* create */
		WorkorderMaterial obj = WorkorderMaterial.newInstance(target1.woUid);
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadWorkorderMaterial(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateWorkorderMaterial() throws Throwable {
		WorkorderMaterial obj = dataService.loadWorkorderMaterial(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadWorkorderMaterial(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteWorkorderMaterial() {
		assert dataService.loadWorkorderMaterial(targetUid).delete();
	}

	// -------------------------------------------------------------------------------
	public class Target {
		private String woUid;
		private String woNo;

		// 料件基本檔
		private String mmUid;
		private String mmMano;
		private String mmName;
		private double qty0; // 待領用量
		private double qty1; // 已領用量

		private Target(String woUid, String woNo, String mmUid, String mmMano, String mmName, double qty0,
				double qty1) {
			this.woUid = woUid;
			this.woNo = woNo;
			this.mmUid = mmUid;
			this.mmMano = mmMano;
			this.mmName = mmName;
			this.qty0 = qty0;
			this.qty1 = qty1;
		}

		public String getWoUid() {
			return woUid;
		}

		public String getWoNo() {
			return woNo;
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

		public double getQty0() {
			return qty0;
		}

		public double getQty1() {
			return qty1;
		}

	}
}
