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

public class BizObjMaterialMasterTest extends AbstractEkpInitTest {
	private static InvtDataService dataService = DataServiceFactory.getInstance().getService(InvtDataService.class);

	private String targetUid;

	private Target target1, target2;

	@Before
	public void initMethod() {
		target1 = new Target("mano1", "name1", "specification1", PartUnit.EAC, 199.999d, 199.999d);
		target2 = new Target("mano2", "name2", "specification2", PartUnit.GRM, 299.999d, 299.999d);
	}

	@Test
	public void testCRUD() throws Throwable {
		testCreateMaterialMaster();
		testUpdateMaterialMaster();
		testDeleteMaterialMaster();
	}

	@Test
	@Ignore
	public void testCreateMaterialMaster() throws Throwable {
		/* create */
		MaterialMaster obj = MaterialMaster.newInstance();
		PropertyUtils.copyProperties(obj, target1);
		assert obj.save();
		targetUid = obj.getUid();
		/* load */
		obj = dataService.loadMaterialMaster(targetUid);
		TestUtil.assertObjEqual(target1, obj);
	}

	@Test
	@Ignore
	public void testUpdateMaterialMaster() throws Throwable {
		MaterialMaster obj = dataService.loadMaterialMaster(targetUid);
		PropertyUtils.copyProperties(obj, target2);
		assert obj.save();
		/* load */
		obj = dataService.loadMaterialMaster(targetUid);
		TestUtil.assertObjEqual(target2, obj);
	}

	@Test
	@Ignore
	public void testDeleteMaterialMaster() {
		assert dataService.loadMaterialMaster(targetUid).delete();
	}

	public class Target {
		private String mano;
		private String name;
		private String specification;

		private PartUnit stdUnit;

		// 所有MaterialBinStockBatch的庫存量和金額(這是redundant屬性，必須確保一致)
		private double sumStockQty;
		private double sumStockValue;

		private Target(String mano, String name, String specification, PartUnit stdUnit, double sumStockQty,
				double sumStockValue) {
			this.mano = mano;
			this.name = name;
			this.specification = specification;
			this.stdUnit = stdUnit;
			this.sumStockQty = sumStockQty;
			this.sumStockValue = sumStockValue;
		}

		public String getMano() {
			return mano;
		}

		public String getName() {
			return name;
		}

		public String getSpecification() {
			return specification;
		}

		public PartUnit getStdUnit() {
			return stdUnit;
		}

		public double getSumStockQty() {
			return sumStockQty;
		}

		public double getSumStockValue() {
			return sumStockValue;
		}

	}
}
