package ekp.invt;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.InvtDataService;
import ekp.data.MbomDataService;
import ekp.invt.BizObjWrhsLocTest.Target;
import ekp.invt.type.InvtOrderType;
import ekp.mbom.Part;
import ekp.mbom.type.PartUnit;
import legion.DataServiceFactory;

public class BizObjInvtOrderItemTest extends AbstractEkpInitTest {
	private static InvtDataService dataService = DataServiceFactory.getInstance().getService(InvtDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		target1 = new Target("ioUid1", "mmUid1", InvtOrderType.I1, 199.999d, 199.999d,false, "miUid1",false, "wrhsBinUid1");
		target2 = new Target("ioUid2", "mmUid2" , InvtOrderType.I2, 299.999d, 299.999d, true, "miUid2", true, "wrhsBinUid2");
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateInvtOrderItem();
		testUpdateInvtOrderItem();
		testDeleteInvtOrderItem();
	}

	@Test
	@Ignore
	public void testCreateInvtOrderItem() throws Throwable {
		/* create */
		InvtOrderItem obj = InvtOrderItem.newInstance(target1.ioUid);
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadInvtOrderItem(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateInvtOrderItem() throws Throwable {
		InvtOrderItem obj = dataService.loadInvtOrderItem(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadInvtOrderItem(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteInvtOrderItem() {
		assert dataService.loadInvtOrderItem(targetUid).delete();
	}

	public class Target {
		private String ioUid; // invt order uid
		private String mmUid;

		private InvtOrderType ioType;
		private double orderQty; // 記錄異動的數量
		private double orderValue; // 記錄異動的金額
		private boolean miAssigned;
		private String miUid;
		private boolean wrhsBinAssigned;
		private String wrhsBinUid;

		private Target(String ioUid, String mmUid, InvtOrderType ioType, double orderQty, double orderValue,
				boolean miAssigned, String miUid, boolean wrhsBinAssigned, String wrhsBinUid) {
			this.ioUid = ioUid;
			this.mmUid = mmUid;
			this.ioType = ioType;
			this.orderQty = orderQty;
			this.orderValue = orderValue;
			this.miAssigned = miAssigned;
			this.miUid = miUid;
			this.wrhsBinAssigned = wrhsBinAssigned;
			this.wrhsBinUid = wrhsBinUid;
		}

		public String getIoUid() {
			return ioUid;
		}

		public String getMmUid() {
			return mmUid;
		}

		public InvtOrderType getIoType() {
			return ioType;
		}

		public double getOrderQty() {
			return orderQty;
		}

		public double getOrderValue() {
			return orderValue;
		}

		public boolean isMiAssigned() {
			return miAssigned;
		}

		public String getMiUid() {
			return miUid;
		}

		public boolean isWrhsBinAssigned() {
			return wrhsBinAssigned;
		}

		public String getWrhsBinUid() {
			return wrhsBinUid;
		}

	}
}
