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
import ekp.mbom.Part;
import ekp.mbom.type.PartUnit;
import legion.DataServiceFactory;
import legion.util.DateUtil;

public class BizObjMaterialBinStockBatchTest extends AbstractEkpInitTest {
	private static InvtDataService dataService = DataServiceFactory.getInstance().getService(InvtDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		double d1 = 199.999d, d2 = 299.999d;
		long l1 = DateUtil.toLong(LocalDateTime.now().plusDays(1)),
				l2 = DateUtil.toLong(LocalDateTime.now().plusDays(2));
		target1 = new Target("mbsUid1", "miUid1", d1, d1, l1);
		target2 = new Target("mbsUid2", "miUid2", d2, d2, l2);
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateMaterialBinStockBatch();
		testUpdateMaterialBinStockBatch();
		testDeleteMaterialBinStockBatch();
	}

	@Test
	@Ignore
	public void testCreateMaterialBinStockBatch() throws Throwable {
		/* create */
		MaterialBinStockBatch obj = MaterialBinStockBatch.newInstance(target1.mbsUid, target1.miUid);
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadMaterialBinStockBatch(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateMaterialBinStockBatch() throws Throwable {
		MaterialBinStockBatch obj = dataService.loadMaterialBinStockBatch(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadMaterialBinStockBatch(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteMaterialBinStockBatch() {
		assert dataService.loadMaterialBinStockBatch(targetUid).delete();
	}

	public class Target {
		private String mbsUid; // 主要ref的MaterialBinStock
		private String miUid; // material inst uid

		/**/
		private double stockQty; // 當前的存量餘額
		private double stockValue; // 當前的帳值餘額
		private long stockTime; // 存入時間（每一個batch只會有一個時間）

		private Target(String mbsUid, String miUid, double stockQty, double stockValue, long stockTime) {
			this.mbsUid = mbsUid;
			this.miUid = miUid;
			this.stockQty = stockQty;
			this.stockValue = stockValue;
			this.stockTime = stockTime;
		}

		public String getMbsUid() {
			return mbsUid;
		}

		public String getMiUid() {
			return miUid;
		}

		public double getStockQty() {
			return stockQty;
		}

		public double getStockValue() {
			return stockValue;
		}

		public long getStockTime() {
			return stockTime;
		}

	}
}
