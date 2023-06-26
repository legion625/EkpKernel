package ekp.invt;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import ekp.invt.dto.InvtOrderCreateObj;
import ekp.invt.dto.InvtOrderItemCreateObj;
import ekp.invt.dto.MaterialBinStockBatchCreateObj;
import ekp.invt.dto.MaterialBinStockCreateObj;
import ekp.invt.dto.MaterialInstCreateObj;
import ekp.invt.dto.MaterialMasterCreateObj;
import ekp.invt.dto.MbsbStmtCreateObj;
import ekp.invt.dto.WrhsBinCreateObj;
import ekp.invt.dto.WrhsLocCreateObj;
import ekp.invt.type.InvtOrderType;
import ekp.invt.type.MaterialInstAcqChannel;
import ekp.invt.type.MbsbFlowType;
import ekp.mbom.type.PartUnit;
import legion.BusinessServiceFactory;
import legion.util.DateUtil;

public class InvtServiceTest extends AbstractEkpInitTest {

	private static InvtService service;

	@BeforeClass
	public static void beforeClass() {
		service = BusinessServiceFactory.getInstance().getService(InvtService.class);
		log.debug("service: {}", service);
	}

	@Test
	@Ignore
	public void prepare() {
		log.debug("test InvtServiceTest");
		
		/* WrhsLoc */
		WrhsLocCreateObj wlCreateObj = new WrhsLocCreateObj();
		wlCreateObj.setId("W001");
		wlCreateObj.setName("倉庫1");
		WrhsLoc wl = service.createWrhsLoc(wlCreateObj);
		
		/* WrhsBin */
		WrhsBinCreateObj wbCreateObj = new WrhsBinCreateObj();
		wbCreateObj.setWlUid(wl.getUid());
		wbCreateObj.setId("B001");
		wbCreateObj.setName("儲位1");
		WrhsBin wb = service.createWrhsBin(wbCreateObj);
		
		
		/* mm */
		MaterialMasterCreateObj mmCreateObj = new MaterialMasterCreateObj();
		mmCreateObj.setMano("MANO1");
		mmCreateObj.setName("測試料號1");
		mmCreateObj.setSpecification("測試規格1");
		mmCreateObj.setStdUnit(PartUnit.EAC);
		MaterialMaster mm = service.createMaterialMaster(mmCreateObj);
		
		/* mi */
		MaterialInstCreateObj miCreateObj = new MaterialInstCreateObj();
		miCreateObj.setMmUid(mm.getUid());
		miCreateObj.setMiac(MaterialInstAcqChannel.PURCHASING);
		miCreateObj.setQty(123.45d);
		miCreateObj.setValue(1851.75d);
		miCreateObj.setEffDate(DateUtil.toLong(LocalDateTime.now()));
		miCreateObj.setExpDate(DateUtil.toLong(LocalDateTime.now().plusYears(2)));
		MaterialInst mi = service.createMaterialInst(miCreateObj);
		
		/* mbs */
		MaterialBinStockCreateObj mbsCreateObj = new MaterialBinStockCreateObj();
		mbsCreateObj.setMmUid(mm.getUid());
		mbsCreateObj.setMano(mm.getMano());
		mbsCreateObj.setWrhsBinUid(wb.getUid());
		MaterialBinStock mbs = service.createMaterialBinStock(mbsCreateObj);
		
		/* mbsb */
		MaterialBinStockBatchCreateObj mbsbCreateObj = new MaterialBinStockBatchCreateObj();
		mbsbCreateObj.setMbsUid(mbs.getUid());
		mbsbCreateObj.setMiUid(mi.getUid());
		MaterialBinStockBatch mbsb = service.createMaterialBinStockBatch(mbsbCreateObj);
		
		
		/* InvtOrder */
		InvtOrderCreateObj ioCreateObj = new InvtOrderCreateObj();
		ioCreateObj.setApplierId("USER1");
		ioCreateObj.setApplierName("Jason");
		ioCreateObj.setApvTime(System.currentTimeMillis());
		ioCreateObj.setRemark("This is a sample inventory order.");
		InvtOrder io = service.createInvtOrder(ioCreateObj);
		
		/* InvtOrderItem */
		InvtOrderItemCreateObj ioiCreateObj = new InvtOrderItemCreateObj();
		ioiCreateObj.setIoUid(io.getUid());
		ioiCreateObj.setMbsUid(mbs.getUid());
		ioiCreateObj.setIoType(InvtOrderType.I1);
		ioiCreateObj.setOrderQty(mi.getQty());
		ioiCreateObj.setOrderValue(mi.getValue());
		InvtOrderItem ioi = service.createInvtOrderItem(ioiCreateObj);
		
		/* mbsbStmt */
		MbsbStmtCreateObj mbsbsCreateObj = new MbsbStmtCreateObj();
		mbsbsCreateObj.setMbsbUid(mbsb.getUid());
		mbsbsCreateObj.setIoiUid(ioi.getUid());
		mbsbsCreateObj.setMbsbFlowType(MbsbFlowType.IN);
		mbsbsCreateObj.setStmtQty(mi.getQty());
		mbsbsCreateObj.setStmtValue(mi.getValue());
		MbsbStmt mbsbs = service.createMbsbStmt(mbsbsCreateObj);
	}

	@Test
	@Ignore
	public void testMbsbStmtPost() {
		String mbsbStmtUid = "2023!98!a7!9";
		Assert.assertTrue(service.mbsbStmtPost(mbsbStmtUid));
//		Assert.assertTrue(service.mbsbStmtRevertPost(mbsbStmtUid));
	}
}
