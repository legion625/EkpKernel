package ekp.mbom;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.MbomDataService;
import legion.DataServiceFactory;

public class BizObjProdTest extends AbstractEkpInitTest {
	private static MbomDataService dataService = DataServiceFactory.getInstance().getService(MbomDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		target1 = new Target("id1", "name1");
		target2 = new Target("id2", "name2");
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateProd();
		testUpdateProd();
		testDeleteProd();
	}

	@Test
	@Ignore
	public void testCreateProd() throws Throwable {
		/* create */
		Prod obj = Prod.newInstance();
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadProd(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateProd() throws Throwable {
		Prod obj = dataService.loadProd(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadProd(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteProd() {
		assert dataService.loadProd(targetUid).delete();
	}

	// -------------------------------------------------------------------------------
	public class Target {
		private String id; // 型號 biz key
		private String name; // 名稱

		private Target(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

	}
}
