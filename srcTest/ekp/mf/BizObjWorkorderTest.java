package ekp.mf;

import java.time.LocalDateTime;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.MfDataService;
import ekp.mf.type.WorkorderStatus;
import legion.DataServiceFactory;
import legion.util.DateUtil;

public class BizObjWorkorderTest extends AbstractEkpInitTest {
	private static MfDataService dataService = DataServiceFactory.getInstance().getService(MfDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {

		long l1 = DateUtil.toLong(LocalDateTime.now().plusDays(1));
		long l2 = DateUtil.toLong(LocalDateTime.now().plusDays(2));
		
		target1 = new Target("woNo1", WorkorderStatus.TO_START, "partUid1", "partPin1", "partMmMano1", l1, l1, l1);
		target2 = new Target("woNo2", WorkorderStatus.FINISH_WORK, "partUid2", "partPin2", "partMmMano2", l2, l2, l2);
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateWorkorder();
		testUpdateWorkorder();
		testDeleteWorkorder();
	}

	@Test
	@Ignore
	public void testCreateWorkorder() throws Throwable {
		/* create */
		Workorder obj = Workorder.newInstance();
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadWorkorder(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateWorkorder() throws Throwable {
		Workorder obj = dataService.loadWorkorder(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadWorkorder(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteWorkorder() {
		assert dataService.loadWorkorder(targetUid).delete();
	}

	// -------------------------------------------------------------------------------
	public class Target {
		private String woNo;
		private WorkorderStatus status;
		// 欲製造的part
		private String partUid;
		private String partPin;
		private String partMmMano;

		private long startWorkTime;
		private long finishWorkTime;
		private long overTime;

		private Target(String woNo, WorkorderStatus status, String partUid, String partPin, String partMmMano,
				long startWorkTime, long finishWorkTime, long overTime) {
			this.woNo = woNo;
			this.status = status;
			this.partUid = partUid;
			this.partPin = partPin;
			this.partMmMano = partMmMano;
			this.startWorkTime = startWorkTime;
			this.finishWorkTime = finishWorkTime;
			this.overTime = overTime;
		}

		public String getWoNo() {
			return woNo;
		}

		public WorkorderStatus getStatus() {
			return status;
		}

		public String getPartUid() {
			return partUid;
		}

		public String getPartPin() {
			return partPin;
		}

		public String getPartMmMano() {
			return partMmMano;
		}

		public long getStartWorkTime() {
			return startWorkTime;
		}

		public long getFinishWorkTime() {
			return finishWorkTime;
		}

		public long getOverTime() {
			return overTime;
		}

	}

}
