package ekp.mbom;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.MbomDataService;
import legion.DataServiceFactory;

public class BizObjProdCtlTest extends AbstractEkpInitTest {
	private static MbomDataService dataService = DataServiceFactory.getInstance().getService(MbomDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		target1 = new Target("id1", 1, "name1", false, "parentUid1", "parentId1", "prodUid1");
		target2 = new Target("id2", 2, "name2", true, "parentUid2", "parentId2", "prodUid2");
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateProdCtl();
		testUpdateProdCtl();
		testDeleteProdCtl();
	}

	@Test
	@Ignore
	public void testCreateProdCtl() throws Throwable {
		/* create */
		ProdCtl obj = ProdCtl.newInstance();
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadProdCtl(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateProdCtl() throws Throwable {
		ProdCtl obj = dataService.loadProdCtl(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadProdCtl(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteProdCtl() {
		assert dataService.loadProdCtl(targetUid).delete();
	}

	// -------------------------------------------------------------------------------
	public class Target {
		private String id; // 型號 biz key
		private int lv; // 1:系統;2:次系統;3:模組 預設先展到第3階
		private String name; // 名稱
		private boolean req; // 是否為必要的

		private String parentUid;
		private String parentId;

		//
		private String prodUid;

		private Target(String id, int lv, String name, boolean req, String parentUid, String parentId, String prodUid) {
			this.id = id;
			this.lv = lv;
			this.name = name;
			this.req = req;
			this.parentUid = parentUid;
			this.parentId = parentId;
			this.prodUid = prodUid;
		}

		public String getId() {
			return id;
		}

		public int getLv() {
			return lv;
		}

		public String getName() {
			return name;
		}

		public boolean isReq() {
			return req;
		}

		public String getParentUid() {
			return parentUid;
		}

		public String getParentId() {
			return parentId;
		}

		public String getProdUid() {
			return prodUid;
		}

	}
}