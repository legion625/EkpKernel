package ekp.invt;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.InvtDataService;
import ekp.data.MbomDataService;
import ekp.invt.BizObjWrhsLocTest.Target;
import ekp.mbom.Part;
import ekp.mbom.type.PartUnit;
import legion.DataServiceFactory;

public class BizObjWrhsBinTest extends AbstractEkpInitTest {
	private static InvtDataService dataService = DataServiceFactory.getInstance().getService(InvtDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		target1 = new Target("wlUid1", "id1", "name1");
		target2 = new Target("wlUid2", "id2", "name2");
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateWrhsBin();
		testUpdateWrhsBin();
		testDeleteWrhsBin();
	}

	@Test
	@Ignore
	public void testCreateWrhsBin() throws Throwable {
		/* create */
		WrhsBin obj = WrhsBin.newInstance(target1.wlUid);
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadWrhsBin(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateWrhsBin() throws Throwable {
		WrhsBin obj = dataService.loadWrhsBin(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadWrhsBin(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteWrhsBin() {
		assert dataService.loadWrhsBin(targetUid).delete();
	}

	public class Target {
		// 以wl和id作為共同biz key
		private String wlUid;
		//
		private String id;
		private String name;

		private Target(String wlUid, String id, String name) {
			this.wlUid = wlUid;
			this.id = id;
			this.name = name;
		}

		public String getWlUid() {
			return wlUid;
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

	}
}
