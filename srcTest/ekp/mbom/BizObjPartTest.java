package ekp.mbom;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.MbomDataService;
import ekp.mbom.type.PartUnit;
import legion.DataServiceFactory;

public class BizObjPartTest extends AbstractEkpInitTest{
	private static MbomDataService dataService = DataServiceFactory.getInstance().getService(MbomDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		target1 = new Target("pin1", "name1", PartUnit.EA);
		target2 = new Target("pin2", "name2", PartUnit.GRAM);
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreatePart();
		testUpdatePart();
		testDeletePart();
	}

	@Test
	@Ignore
	public void testCreatePart() throws Throwable {
		/* create */
		Part obj = Part.newInstance();
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadPart(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdatePart() throws Throwable {
		Part obj = dataService.loadPart(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadPart(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeletePart() {
		assert dataService.loadPart(targetUid).delete();
	}

	// -------------------------------------------------------------------------------
	public class Target {

		private String pin;
		private String name;
		private PartUnit unit;

		private Target(String pin, String name, PartUnit unit) {
			this.pin = pin;
			this.name = name;
			this.unit = unit;
		}

		public String getPin() {
			return pin;
		}

		public String getName() {
			return name;
		}

		public PartUnit getUnit() {
			return unit;
		}

	}
}
