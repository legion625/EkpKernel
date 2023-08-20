package ekp.invt;

import java.time.LocalDateTime;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.InvtDataService;
import ekp.invt.type.InvtOrderStatus;
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
		target1 = new Target("iosn1",InvtOrderStatus.TO_APV, "applierId1", "applierName1", l1, "remark1",l1);
		target2 = new Target("iosn2",InvtOrderStatus.APPROVED, "applierId2", "applierName2", l2, "remark2",l2);
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
		private InvtOrderStatus status;
		private String applierId;
		private String applierName;
		private long applyTime;
		private String remark; //
		private long apvTime; // approval time

		private Target(String iosn, InvtOrderStatus status, String applierId, String applierName, long applyTime,
				String remark, long apvTime) {
			this.iosn = iosn;
			this.status = status;
			this.applierId = applierId;
			this.applierName = applierName;
			this.applyTime = applyTime;
			this.remark = remark;
			this.apvTime = apvTime;
		}

		public String getIosn() {
			return iosn;
		}

		public InvtOrderStatus getStatus() {
			return status;
		}

		public String getApplierId() {
			return applierId;
		}

		public String getApplierName() {
			return applierName;
		}

		public long getApplyTime() {
			return applyTime;
		}

		public String getRemark() {
			return remark;
		}

		public long getApvTime() {
			return apvTime;
		}

	}
}
