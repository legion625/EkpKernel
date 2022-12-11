package ekp.mbom;

import java.time.LocalDate;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.MbomDataService;
import ekp.mbom.type.PartCfgStatus;
import legion.DataServiceFactory;
import legion.util.DateUtil;

public class BizObjPartCfgTest extends AbstractEkpInitTest {
	private static MbomDataService dataService = DataServiceFactory.getInstance().getService(MbomDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		long time1 = DateUtil.toLong(LocalDate.now().plusDays(1));
		long time2 = DateUtil.toLong(LocalDate.now().plusDays(2));
		target1 = new Target("rootPartUid1", "rootPartPin1", PartCfgStatus.EDITING, "id1", "name1", "desp1", time1);
		target2 = new Target("rootPartUid2", "rootPartPin2", PartCfgStatus.PUBLISHED, "id2", "name2", "desp2", time2);
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreatePartCfg();
		testUpdatePartCfg();
		testDeletePartCfg();
	}

	@Test
	@Ignore
	public void testCreatePartCfg() throws Throwable {
		/* create */
		PartCfg obj = PartCfg.newInstance(target1.rootPartUid,target1.rootPartPin);
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadPartCfg(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdatePartCfg() throws Throwable {
		PartCfg obj = dataService.loadPartCfg(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadPartCfg(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeletePartCfg() {
		assert dataService.loadPartCfg(targetUid).delete();
	}

	// -------------------------------------------------------------------------------
	public class Target {
		private String rootPartUid; // ref data key
		private String rootPartPin; // ref biz key

		private PartCfgStatus status;

		private String id; // biz key
		private String name;
		private String desp;
		
		private long publishTime;

		private Target(String rootPartUid, String rootPartPin, PartCfgStatus status, String id, String name,
				String desp, long publishTime) {
			super();
			this.rootPartUid = rootPartUid;
			this.rootPartPin = rootPartPin;
			this.status = status;
			this.id = id;
			this.name = name;
			this.desp = desp;
			this.publishTime = publishTime;
		}

		public String getRootPartUid() {
			return rootPartUid;
		}

		public String getRootPartPin() {
			return rootPartPin;
		}

		public PartCfgStatus getStatus() {
			return status;
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getDesp() {
			return desp;
		}

		public long getPublishTime() {
			return publishTime;
		}
		
		

	}
}
