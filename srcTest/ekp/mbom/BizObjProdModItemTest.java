package ekp.mbom;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.MbomDataService;
import legion.DataServiceFactory;

public class BizObjProdModItemTest extends AbstractEkpInitTest {
	private static MbomDataService dataService = DataServiceFactory.getInstance().getService(MbomDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		target1 = new Target("prodModUid1", "prodCtlUid1", false, "partCfgUid1","partAcqUid1");
		target2 = new Target("prodModUid2", "prodCtlUid2", true, "partCfgUid2","partAcqUid2");
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateProdModItem();
		testUpdateProdModItem();
		testDeleteProdModItem();
	}

	@Test
	@Ignore
	public void testCreateProdModItem() throws Throwable {
		/* create */
		ProdModItem obj = ProdModItem.newInstance(target1.prodModUid, target1.prodCtlUid);
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadProdModItem(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateProdModItem() throws Throwable {
		ProdModItem obj = dataService.loadProdModItem(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadProdModItem(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteProdModItem() {
		assert dataService.loadProdModItem(targetUid).delete();
	}

	// -------------------------------------------------------------------------------
	public class Target {
		private String prodModUid; // 產品model識別碼 biz key
		private String prodCtlUid; // prodCtl識別碼 biz key
		//
		private boolean partAcqCfgAssigned;
		private String partCfgUid; // PartCfg識別碼，此model對應的prodCtl所選用的構型
		private String partAcqUid; // PartAcq識別碼，此model對應的prodCtl所選用的獲取方式

		private Target(String prodModUid, String prodCtlUid, boolean partAcqCfgAssigned, String partCfgUid,
				String partAcqUid) {
			this.prodModUid = prodModUid;
			this.prodCtlUid = prodCtlUid;
			this.partAcqCfgAssigned = partAcqCfgAssigned;
			this.partCfgUid = partCfgUid;
			this.partAcqUid = partAcqUid;
		}

		public String getProdModUid() {
			return prodModUid;
		}

		public String getProdCtlUid() {
			return prodCtlUid;
		}

		public boolean isPartAcqCfgAssigned() {
			return partAcqCfgAssigned;
		}

		public String getPartCfgUid() {
			return partCfgUid;
		}

		public String getPartAcqUid() {
			return partAcqUid;
		}

	}
}
