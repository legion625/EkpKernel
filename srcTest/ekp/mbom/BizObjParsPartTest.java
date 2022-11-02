package ekp.mbom;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.MbomDataService;
import legion.DataServiceFactory;

public class BizObjParsPartTest extends AbstractEkpInitTest {
	private static MbomDataService dataService = DataServiceFactory.getInstance().getService(MbomDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		target1 = new Target("parsUid1", false, "partUid1", "partPin1", 123.456);
		target2 = new Target("parsUid2", true, "partUid2", "partPin2", 223.456);
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateParsPart();
		testUpdateParsPart();
		testDeleteParsPart();
	}

	@Test
	@Ignore
	public void testCreateParsPart() throws Throwable {
		/* create */
		ParsPart obj = ParsPart.newInstance(target1.parsUid);
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadParsPart(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateParsPart() throws Throwable {
		ParsPart obj = dataService.loadParsPart(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadParsPart(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteParsPart() {
		assert dataService.loadParsPart(targetUid).delete();
	}

	// -------------------------------------------------------------------------------
	public class Target {
		private String parsUid; // ref data key

		// assign part
		private boolean assignPart;
		private String partUid; // ref data key
		private String partPin; // ref biz key
		private double partReqQty; // required quantity (allow decimal in certain conditions)

		private Target(String parsUid, boolean assignPart, String partUid, String partPin, double partReqQty) {
			super();
			this.parsUid = parsUid;
			this.assignPart = assignPart;
			this.partUid = partUid;
			this.partPin = partPin;
			this.partReqQty = partReqQty;
		}

		public String getParsUid() {
			return parsUid;
		}

		public boolean isAssignPart() {
			return assignPart;
		}

		public String getPartUid() {
			return partUid;
		}

		public String getPartPin() {
			return partPin;
		}

		public double getPartReqQty() {
			return partReqQty;
		}

	}
}
