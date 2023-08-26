package ekp.invt;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.InvtDataService;
import ekp.data.MbomDataService;
import ekp.mbom.Part;
import ekp.mbom.BizObjPartTest.Target;
import ekp.mbom.type.PartUnit;
import legion.DataServiceFactory;

public class BizObjMaterialInstSrcConjTest extends AbstractEkpInitTest{
	private static InvtDataService dataService = DataServiceFactory.getInstance().getService(InvtDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		double d1 = 199.999d;
		double d2 = 299.999d;
		target1 = new Target("miUid1", "srcMiUid1", d1, d1);
		target2 = new Target("miUid2", "srcMiUid2", d2, d2);
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateMaterialInstSrcConj();
		testUpdateMaterialInstSrcConj();
		testDeleteMaterialInstSrcConj();
	}

	@Test
	@Ignore
	public void testCreateMaterialInstSrcConj() throws Throwable {
		/* create */
		MaterialInstSrcConj obj = MaterialInstSrcConj.newInstance();
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadMaterialInstSrcConj(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateMaterialInstSrcConj() throws Throwable {
		MaterialInstSrcConj obj = dataService.loadMaterialInstSrcConj(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadMaterialInstSrcConj(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteMaterialInstSrcConj() {
		assert dataService.loadMaterialInstSrcConj(targetUid).delete();
	}

	// -------------------------------------------------------------------------------
	public class Target {
		private String miUid;
		private String srcMiUid;
		private double srcMiQty;
		private double srcMiValue;

		private Target(String miUid, String srcMiUid, double srcMiQty, double srcMiValue) {
			this.miUid = miUid;
			this.srcMiUid = srcMiUid;
			this.srcMiQty = srcMiQty;
			this.srcMiValue = srcMiValue;
		}

		public String getMiUid() {
			return miUid;
		}

		public String getSrcMiUid() {
			return srcMiUid;
		}

		public double getSrcMiQty() {
			return srcMiQty;
		}

		public double getSrcMiValue() {
			return srcMiValue;
		}

	}
}
