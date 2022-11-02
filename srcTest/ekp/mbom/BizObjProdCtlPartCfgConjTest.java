package ekp.mbom;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.MbomDataService;
import legion.DataServiceFactory;

public class BizObjProdCtlPartCfgConjTest extends AbstractEkpInitTest {
	private static MbomDataService dataService = DataServiceFactory.getInstance().getService(MbomDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		target1 = new Target("prodCtlUid1", "partCfgUid1");
		target2 = new Target("prodCtlUid2", "partCfgUid2");
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateProdCtlPartCfgConj();
		testUpdateProdCtlPartCfgConj();
		testDeleteProdCtlPartCfgConj();
	}

	@Test
	@Ignore
	public void testCreateProdCtlPartCfgConj() throws Throwable {
		/* create */
		ProdCtlPartCfgConj obj = ProdCtlPartCfgConj.newInstance(target1.prodCtlUid, target1.partCfgUid);
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadProdCtlPartCfgConj(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateProdCtlPartCfgConj() throws Throwable {
		ProdCtlPartCfgConj obj = dataService.loadProdCtlPartCfgConj(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadProdCtlPartCfgConj(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteProdCtlPartCfgConj() {
		assert dataService.loadProdCtlPartCfgConj(targetUid).delete();
	}

	// -------------------------------------------------------------------------------
	public class Target {
		private String prodCtlUid; // 標的產品型錄prodCtl biz key
		private String partCfgUid; // 此產品型錄對應對產品構型PartCfg biz key

		private Target(String prodCtlUid, String partCfgUid) {
			this.prodCtlUid = prodCtlUid;
			this.partCfgUid = partCfgUid;
		}

		public String getProdCtlUid() {
			return prodCtlUid;
		}

		public String getPartCfgUid() {
			return partCfgUid;
		}

	}
}
