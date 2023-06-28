package ekp.invt;

import java.time.LocalDateTime;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.InvtDataService;
import legion.DataServiceFactory;
import legion.util.DateUtil;

public class BizObjInvtOrderTest extends AbstractEkpInitTest {
	private static InvtDataService dataService = DataServiceFactory.getInstance().getService(InvtDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		long l1 = DateUtil.toLong(LocalDateTime.now().plusDays(1));
		long l2 = DateUtil.toLong(LocalDateTime.now().plusDays(2));
		target1 = new Target("iosn1", "applierId1", "applierName1", l1, "remark1");
		target2 = new Target("iosn2", "applierId2", "applierName2", l2, "remark2");
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateInvtOrder();
		testUpdateInvtOrder();
		testDeleteInvtOrder();
	}

	@Test
	@Ignore
	public void testCreateInvtOrder() throws Throwable {
		/* create */
		InvtOrder obj = InvtOrder.newInstance();
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadInvtOrder(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateInvtOrder() throws Throwable {
		InvtOrder obj = dataService.loadInvtOrder(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadInvtOrder(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteInvtOrder() {
		assert dataService.loadInvtOrder(targetUid).delete();
	}

	public class Target {
		private String iosn; // invt order serial number
		private String applierId;
		private String applierName;
		private long apvTime; // approval time
		private String remark; //

		private Target(String iosn, String applierId, String applierName, long apvTime, String remark) {
			this.iosn = iosn;
			this.applierId = applierId;
			this.applierName = applierName;
			this.apvTime = apvTime;
			this.remark = remark;
		}

		public String getIosn() {
			return iosn;
		}

		public String getApplierId() {
			return applierId;
		}

		public String getApplierName() {
			return applierName;
		}

		public long getApvTime() {
			return apvTime;
		}

		public String getRemark() {
			return remark;
		}

	}
}
