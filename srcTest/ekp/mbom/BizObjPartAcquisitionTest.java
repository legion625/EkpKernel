package ekp.mbom;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.MbomDataService;
import ekp.mbom.type.PartAcquisitionType;
import legion.DataServiceFactory;

public class BizObjPartAcquisitionTest extends AbstractEkpInitTest{
	private static MbomDataService dataService = DataServiceFactory.getInstance().getService(MbomDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		target1 = new Target("partUid1", "partPin1", "id1", "name1", PartAcquisitionType.PURCHASING);
		target2 = new Target("partUid2", "partPin2", "id2", "name2", PartAcquisitionType.OUTSOURCING);
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreatePartAcquisition();
		testUpdatePartAcquisition();
		testDeletePartAcquisition();
	}

	@Test
	@Ignore
	public void testCreatePartAcquisition() throws Throwable {
		/* create */
		PartAcquisition obj = PartAcquisition.newInstance(target1.partUid, target1.partPin);
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadPartAcquisition(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdatePartAcquisition() throws Throwable {
		PartAcquisition obj = dataService.loadPartAcquisition(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadPartAcquisition(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeletePartAcquisition() {
		assert dataService.loadPartAcquisition(targetUid).delete();
	}

	// -------------------------------------------------------------------------------
	public class Target {

		private String partUid; // ref data key
		private String partPin; // ref biz key

		private String id; // biz key
		private String name;
		private PartAcquisitionType type;
		private Target(String partUid, String partPin, String id, String name, PartAcquisitionType type) {
			super();
			this.partUid = partUid;
			this.partPin = partPin;
			this.id = id;
			this.name = name;
			this.type = type;
		}
		public String getPartUid() {
			return partUid;
		}
		public String getPartPin() {
			return partPin;
		}
		public String getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		public PartAcquisitionType getType() {
			return type;
		}


	}
}
