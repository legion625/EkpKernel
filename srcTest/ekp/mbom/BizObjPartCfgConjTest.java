package ekp.mbom;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.MbomDataService;
import legion.DataServiceFactory;

public class BizObjPartCfgConjTest extends AbstractEkpInitTest {
	private static MbomDataService dataService = DataServiceFactory.getInstance().getService(MbomDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		target1 = new Target("partCfgUid1", "partAcqUid1");
		target2 = new Target("partCfgUid2", "partAcqUid2");
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreatePartCfgConj();
		testUpdatePartCfgConj();
		testDeletePartCfgConj();
	}

	@Test
	@Ignore
	public void testCreatePartCfgConj() throws Throwable {
		/* create */
		PartCfgConj obj = PartCfgConj.newInstance(target1.partCfgUid);
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadPartCfgConj(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdatePartCfgConj() throws Throwable {
		PartCfgConj obj = dataService.loadPartCfgConj(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadPartCfgConj(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeletePartCfgConj() {
		assert dataService.loadPartCfgConj(targetUid).delete();
	}

	// -------------------------------------------------------------------------------
	public class Target {
		private String partCfgUid;

		private String partAcqUid;

		private Target(String partCfgUid, String partAcqUid) {
			super();
			this.partCfgUid = partCfgUid;
			this.partAcqUid = partAcqUid;
		}

		public String getPartCfgUid() {
			return partCfgUid;
		}

		public String getPartAcqUid() {
			return partAcqUid;
		}

	}
}
