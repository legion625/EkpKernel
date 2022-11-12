package ekp.mbom;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.MbomDataService;
import legion.DataServiceFactory;

public class BizObjParsProcTest extends AbstractEkpInitTest {
	private static MbomDataService dataService = DataServiceFactory.getInstance().getService(MbomDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		target1 = new Target("parsUid1", "seq1", "name1", "desp1", false, "procUid1", "procId1");
		target2 = new Target("parsUid2", "seq2", "name2", "desp2", true, "procUid2", "procId2");
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateParsProc();
		testUpdateParsProc();
		testDeleteParsProc();
	}

	@Test
	@Ignore
	public void testCreateParsProc() throws Throwable {
		/* create */
		ParsProc obj = ParsProc.newInstance(target1.parsUid);
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadParsProc(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateParsProc() throws Throwable {
		ParsProc obj = dataService.loadParsProc(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadParsProc(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteParsProc() {
		assert dataService.loadParsProc(targetUid).delete();
	}

	// -------------------------------------------------------------------------------
	public class Target {
		private String parsUid; // ref data key

		//
		private String seq; //
		private String name;
		private String desp;

		// proc
		private boolean assignProc;
		private String procUid; // ref data key
		private String procId; // ref biz key

		private Target(String parsUid, String seq, String name, String desp, boolean assignProc, String procUid,
				String procId) {
			super();
			this.parsUid = parsUid;
			this.seq = seq;
			this.name = name;
			this.desp = desp;
			this.assignProc = assignProc;
			this.procUid = procUid;
			this.procId = procId;
		}

		public String getParsUid() {
			return parsUid;
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

		public boolean isAssignProc() {
			return assignProc;
		}

		public String getProcUid() {
			return procUid;
		}

		public String getProcId() {
			return procId;
		}

	}
}
