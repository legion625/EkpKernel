package ekp.mbom;

import java.time.LocalDate;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.MbomDataService;
import ekp.mbom.type.PartAcqStatus;
import ekp.mbom.type.PartAcquisitionType;
import legion.DataServiceFactory;
import legion.util.DateUtil;

public class BizObjPartAcquisitionTest extends AbstractEkpInitTest{
	private static MbomDataService dataService = DataServiceFactory.getInstance().getService(MbomDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		long time1 = DateUtil.toLong(LocalDate.now().plusDays(1));
		long time2 = DateUtil.toLong(LocalDate.now().plusDays(2));
		target1 = new Target("partUid1", "partPin1",PartAcqStatus.EDITING, "id1", "name1", PartAcquisitionType.PURCHASING,  false, "mmUid1", "mmMano1", time1, 199.99d);
		target2 = new Target("partUid2", "partPin2",PartAcqStatus.PUBLISHED, "id2", "name2", PartAcquisitionType.OUTSOURCING, true, "mmUid2", "mmMano2", time2,299.99d);
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
		
		private PartAcqStatus status;

		private String id; // biz key
		private String name;
		private PartAcquisitionType type;
		
		// mm
		private boolean mmAssigned;
		private String mmUid;
		private String mmMano;
		
		private long publishTime;
		
		private double refUnitCost;
		
		private Target(String partUid, String partPin,PartAcqStatus status,
				String id, String name, PartAcquisitionType type,boolean mmAssigned, String mmUid, String mmMano, long publishTime, double refUnitCost) {
			this.partUid = partUid;
			this.partPin = partPin;
			this.status = status;
			this.id = id;
			this.name = name;
			this.type = type;
			this.mmAssigned = mmAssigned;
			this.mmUid = mmUid;
			this.mmMano = mmMano;
			this.publishTime = publishTime;
			this.refUnitCost = refUnitCost;
		}
		public String getPartUid() {
			return partUid;
		}
		public String getPartPin() {
			return partPin;
		}
		
		public PartAcqStatus getStatus() {
			return status;
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

		public boolean isMmAssigned() {
			return mmAssigned;
		}

		public String getMmUid() {
			return mmUid;
		}

		public String getMmMano() {
			return mmMano;
		}
		
		public long getPublishTime() {
			return publishTime;
		}

		public double getRefUnitCost() {
			return refUnitCost;
		}
		
		
		

	}
}
