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

public class BizObjWrhsLocTest extends AbstractEkpInitTest {
	private static InvtDataService dataService = DataServiceFactory.getInstance().getService(InvtDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		target1 = new Target("id1", "name1");
		target2 = new Target("id2", "name2");
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateWrhsLoc();
		testUpdateWrhsLoc();
		testDeleteWrhsLoc();
	}

	@Test
	@Ignore
	public void testCreateWrhsLoc() throws Throwable {
		/* create */
		WrhsLoc obj = WrhsLoc.newInstance();
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadWrhsLoc(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateWrhsLoc() throws Throwable {
		WrhsLoc obj = dataService.loadWrhsLoc(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadWrhsLoc(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteWrhsLoc() {
		assert dataService.loadWrhsLoc(targetUid).delete();
	}

	public class Target {
		private String id;
		private String name;

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
