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
import ekp.mbom.Part;
import ekp.mbom.type.PartUnit;
import legion.DataServiceFactory;

public class BizObjMaterialBinStockTest extends AbstractEkpInitTest {
	private static InvtDataService dataService = DataServiceFactory.getInstance().getService(InvtDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		
		double d1 = 199.999d, d2 = 299.999d;
		target1 = new Target("mmUid1", "mano1", "wrhsBinUid1", d1, d1);
		target2 = new Target("mmUid2", "mano2", "wrhsBinUid2", d2, d2);
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateMaterialBinStock();
		testUpdateMaterialBinStock();
		testDeleteMaterialBinStock();
	}

	@Test
	@Ignore
	public void testCreateMaterialBinStock() throws Throwable {
		/* create */
		MaterialBinStock obj = MaterialBinStock.newInstance(target1.mmUid);
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadMaterialBinStock(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateMaterialBinStock() throws Throwable {
		MaterialBinStock obj = dataService.loadMaterialBinStock(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadMaterialBinStock(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteMaterialBinStock() {
		assert dataService.loadMaterialBinStock(targetUid).delete();
	}

	public class Target {
		private String mmUid; // material master的uid
		private String mano; // (redundant attribute)

		private String wrhsBinUid; // biz key (對應的wrhsBin)

		// 所有MaterialBinStockBatch的庫存量和金額(這是redundant屬性，必須確保一致)
		private double sumStockQty;
		private double sumStockValue;

		private Target(String mmUid, String mano, String wrhsBinUid, double sumStockQty, double sumStockValue) {
			this.mmUid = mmUid;
			this.mano = mano;
			this.wrhsBinUid = wrhsBinUid;
			this.sumStockQty = sumStockQty;
			this.sumStockValue = sumStockValue;
		}

		public String getMmUid() {
			return mmUid;
		}

		public String getMano() {
			return mano;
		}

		public String getWrhsBinUid() {
			return wrhsBinUid;
		}

		public double getSumStockQty() {
			return sumStockQty;
		}

		public double getSumStockValue() {
			return sumStockValue;
		}

	}
}
