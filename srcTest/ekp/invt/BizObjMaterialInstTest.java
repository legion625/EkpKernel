package ekp.invt;

import java.time.LocalDateTime;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.TestUtil;
import ekp.data.InvtDataService;
import ekp.data.MbomDataService;
import ekp.invt.BizObjWrhsLocTest.Target;
import ekp.invt.type.MaterialInstAcqChannel;
import ekp.invt.type.MaterialInstSrcStatus;
import ekp.mbom.Part;
import ekp.mbom.type.PartUnit;
import legion.DataServiceFactory;
import legion.util.DateUtil;

public class BizObjMaterialInstTest extends AbstractEkpInitTest {
	private static InvtDataService dataService = DataServiceFactory.getInstance().getService(InvtDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		double d1 = 199.999d;
		double d2 = 299.999d;
		long l1 = DateUtil.toLong(LocalDateTime.now().plusDays(1));
		long l2 = DateUtil.toLong(LocalDateTime.now().plusDays(2));
		
		target1 = new Target("mmUid1", "misn1", MaterialInstAcqChannel.PURCHASING,"miacSrcNo1", d1, d1, l1, l1
				, MaterialInstSrcStatus.ASSIGNING);
		target2 = new Target("mmUid2", "misn2", MaterialInstAcqChannel.OUTSOURCING,"miacSrcNo2", d2, d2, l2, l2
				, MaterialInstSrcStatus.FINISH_ASSIGNED);
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateMaterialInst();
		testUpdateMaterialInst();
		testDeleteMaterialInst();
	}

	@Test
	@Ignore
	public void testCreateMaterialInst() throws Throwable {
		/* create */
		MaterialInst obj = MaterialInst.newInstance(target1.mmUid);
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadMaterialInst(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateMaterialInst() throws Throwable {
		MaterialInst obj = dataService.loadMaterialInst(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadMaterialInst(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteMaterialInst() {
		assert dataService.loadMaterialInst(targetUid).delete();
	}

	public class Target {
		private String mmUid;

		private String misn; // material instance serial number
		private MaterialInstAcqChannel miac;
		private String miacSrcNo;
		private double qty; // 數量
		private double value; // 帳值
		private long effDate; // 生效日期
		private long expDate; // 失效日期
		private MaterialInstSrcStatus srcStatus;

		private Target(String mmUid, String misn, MaterialInstAcqChannel miac,String miacSrcNo, double qty, double value, long effDate,
				long expDate, MaterialInstSrcStatus srcStatus) {
			this.mmUid = mmUid;
			this.misn = misn;
			this.miac = miac;
			this.miacSrcNo = miacSrcNo;
			this.qty = qty;
			this.value = value;
			this.effDate = effDate;
			this.expDate = expDate;
			this.srcStatus = srcStatus;
		}

		public String getMmUid() {
			return mmUid;
		}

		public String getMisn() {
			return misn;
		}

		public MaterialInstAcqChannel getMiac() {
			return miac;
		}

		public String getMiacSrcNo() {
			return miacSrcNo;
		}

		public double getQty() {
			return qty;
		}

		public double getValue() {
			return value;
		}

		public long getEffDate() {
			return effDate;
		}

		public long getExpDate() {
			return expDate;
		}

		public MaterialInstSrcStatus getSrcStatus() {
			return srcStatus;
		}

	}
}
