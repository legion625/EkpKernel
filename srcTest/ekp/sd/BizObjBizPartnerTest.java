package ekp.sd;

import java.time.LocalDateTime;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.SdDataService;
import legion.DataServiceFactory;
import legion.util.DateUtil;

public class BizObjBizPartnerTest extends AbstractEkpInitTest{
	private static SdDataService dataService = DataServiceFactory.getInstance().getService(SdDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		target1 = new Target("bpsn1", "name1", "ban1");
		target2 = new Target("bpsn2", "name2", "ban2");
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateBizPartner();
		testUpdateBizPartner();
		testDeleteBizPartner();
	}

	@Test
	@Ignore
	public void testCreateBizPartner() throws Throwable {
		/* create */
		BizPartner obj = BizPartner.newInstance();
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadBizPartner(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateBizPartner() throws Throwable {
		BizPartner obj = dataService.loadBizPartner(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadBizPartner(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteBizPartner() {
		assert dataService.loadBizPartner(targetUid).delete();
	}

	public class Target {
		private String bpsn;
		private String name;
		private String ban;

		private Target(String bpsn, String name, String ban) {
			this.bpsn = bpsn;
			this.name = name;
			this.ban = ban;
		}

		public String getBpsn() {
			return bpsn;
		}

		public String getName() {
			return name;
		}

		public String getBan() {
			return ban;
		}

	}
}
