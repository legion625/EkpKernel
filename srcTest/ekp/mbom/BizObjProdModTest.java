package ekp.mbom;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.MbomDataService;
import legion.DataServiceFactory;

public class BizObjProdModTest extends AbstractEkpInitTest {
	private static MbomDataService dataService = DataServiceFactory.getInstance().getService(MbomDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		target1 = new Target("prodUid1", "id1", "name1", "desp1");
		target2 = new Target("prodUid2", "id2", "name2", "desp2");
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateProdMod();
		testUpdateProdMod();
		testDeleteProdMod();
	}

	@Test
	@Ignore
	public void testCreateProdMod() throws Throwable {
		/* create */
		ProdMod obj = ProdMod.newInstance(target1.prodUid);
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadProdMod(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateProdMod() throws Throwable {
		ProdMod obj = dataService.loadProdMod(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadProdMod(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteProdMod() {
		assert dataService.loadProdMod(targetUid).delete();
	}

	// -------------------------------------------------------------------------------
	public class Target {
		private String prodUid; // 對應的產品項 biz key
		//
		private String id; // 識別碼 biz key
		private String name;
		private String desp;

		private Target(String prodUid, String id, String name, String desp) {
			this.prodUid = prodUid;
			this.id = id;
			this.name = name;
			this.desp = desp;
		}

		public String getProdUid() {
			return prodUid;
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getDesp() {
			return desp;
		}

	}
}
