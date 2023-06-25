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
import ekp.invt.type.MbsbFlowType;
import ekp.mbom.Part;
import ekp.mbom.type.PartUnit;
import legion.DataServiceFactory;

public class BizObjMbsbStmtTest extends AbstractEkpInitTest {
	private static InvtDataService dataService = DataServiceFactory.getInstance().getService(InvtDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		double d1 = 199.999d, d2 = 299.999d;
		target1 = new Target("mbsbUid1", "ioiUid1", MbsbFlowType.IN, d1, d1);
		target2 = new Target("mbsbUid2", "ioiUid2", MbsbFlowType.OUT, d2, d2);
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateMbsbStmt();
		testUpdateMbsbStmt();
		testDeleteMbsbStmt();
	}

	@Test
	@Ignore
	public void testCreateMbsbStmt() throws Throwable {
		/* create */
		MbsbStmt obj = MbsbStmt.newInstance(target1.mbsbUid, target1.ioiUid);
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadMbsbStmt(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateMbsbStmt() throws Throwable {
		MbsbStmt obj = dataService.loadMbsbStmt(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadMbsbStmt(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteMbsbStmt() {
		assert dataService.loadMbsbStmt(targetUid).delete();
	}

	public class Target {
		/* Conj的兩個對象：MaterialBinStockBatch和InvtOrderItem */
		private String mbsbUid; //
		private String ioiUid; //

		/* 這個Conj紀錄完整的流向、數量、金額。 */
		private MbsbFlowType mbsbFlowType;
		private double stmtQty; // 記錄異動的數量
		private double stmtValue; // 記錄異動的金額

		private Target(String mbsbUid, String ioiUid, MbsbFlowType mbsbFlowType, double stmtQty, double stmtValue) {
			this.mbsbUid = mbsbUid;
			this.ioiUid = ioiUid;
			this.mbsbFlowType = mbsbFlowType;
			this.stmtQty = stmtQty;
			this.stmtValue = stmtValue;
		}

		public String getMbsbUid() {
			return mbsbUid;
		}

		public String getIoiUid() {
			return ioiUid;
		}

		public MbsbFlowType getMbsbFlowType() {
			return mbsbFlowType;
		}

		public double getStmtQty() {
			return stmtQty;
		}

		public double getStmtValue() {
			return stmtValue;
		}

	}
}
