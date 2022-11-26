package ekp.mbom;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.MbomDataService;
import legion.DataServiceFactory;

public class BizObjPartAcqRoutingStepTest extends AbstractEkpInitTest {
	private static MbomDataService dataService = DataServiceFactory.getInstance().getService(MbomDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		target1 = new Target("partAcqUid1", "seq1", "name1", "desp1");
		target2 = new Target("partAcqUid2", "seq2", "name2", "desp2");
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreatePartAcqRoutingStep();
		testUpdatePartAcqRoutingStep();
		testDeletePartAcqRoutingStep();
	}

	@Test
	@Ignore
	public void testCreatePartAcqRoutingStep() throws Throwable {
		/* create */
		PartAcqRoutingStep obj = PartAcqRoutingStep.newInstance(target1.partAcqUid);
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadPartAcqRoutingStep(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdatePartAcqRoutingStep() throws Throwable {
		PartAcqRoutingStep obj = dataService.loadPartAcqRoutingStep(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadPartAcqRoutingStep(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeletePartAcqRoutingStep() {
		assert dataService.loadPartAcqRoutingStep(targetUid).delete();
	}

	// -------------------------------------------------------------------------------
	public class Target {
		private String partAcqUid; // ref data key

		private String seq; // routing step seq
		private String name;
		private String desp;

		private Target(String partAcqUid, String seq, String name, String desp) {
			super();
			this.partAcqUid = partAcqUid;
			this.seq = seq;
			this.name = name;
			this.desp = desp;
		}

		public String getPartAcqUid() {
			return partAcqUid;
		}

		public String getSeq() {
			return seq;
		}

		public String getName() {
			return name;
		}

		public String getDesp() {
			return desp;
		}

	}
}
