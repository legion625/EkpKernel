package ekp.serviceFacade.rmi.invt;

import ekp.invt.InvtOrder;
import ekp.invt.InvtOrderItem;
import ekp.invt.MaterialBinStock;
import ekp.invt.MaterialBinStockBatch;
import ekp.invt.MaterialInst;
import ekp.invt.MaterialMaster;
import ekp.invt.MbsbStmt;
import ekp.invt.WrhsBin;
import ekp.invt.WrhsLoc;
import ekp.invt.dto.InvtOrderCreateObj;
import ekp.invt.dto.InvtOrderItemCreateObj;
import ekp.invt.dto.MaterialBinStockBatchCreateObj;
import ekp.invt.dto.MaterialBinStockCreateObj;
import ekp.invt.dto.MaterialInstCreateObj;
import ekp.invt.dto.MaterialMasterCreateObj;
import ekp.invt.dto.MbsbStmtCreateObj;
import ekp.invt.dto.WrhsBinCreateObj;
import ekp.invt.dto.WrhsLocCreateObj;

public class InvtFO {

	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsLoc------------------------------------
	public static WrhsLocRemote parseWrhsLocRemote(WrhsLoc _obj) {
		WrhsLocRemote remote = new WrhsLocRemote(_obj.getUid(), _obj.getObjectCreateTime(), _obj.getObjectUpdateTime());
		remote.setId(_obj.getId());
		remote.setName(_obj.getName());
		return remote;
	}

	public static WrhsLocCreateObj parseWrhsLocCreateObj(WrhsLocCreateObjRemote _remote) {
		WrhsLocCreateObj dto = new WrhsLocCreateObj();
		dto.setId(_remote.getId());
		dto.setName(_remote.getName());
		return dto;
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsBin------------------------------------
	public static WrhsBinRemote parseWrhsBinRemote(WrhsBin _obj) {
		WrhsBinRemote remote = new WrhsBinRemote(_obj.getUid(), _obj.getObjectCreateTime(), _obj.getObjectUpdateTime());
		remote.setWlUid(_obj.getWlUid());
		remote.setId(_obj.getId());
		remote.setName(_obj.getName());
		return remote;
	}

	public static WrhsBinCreateObj parseWrhsBinCreateObj(WrhsBinCreateObjRemote _remote) {
		WrhsBinCreateObj dto = new WrhsBinCreateObj();
		dto.setWlUid(_remote.getWlUid());
		dto.setId(_remote.getId());
		dto.setName(_remote.getName());
		return dto;
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------InvtOrder-----------------------------------
	public static InvtOrderRemote parseInvtOrderRemote(InvtOrder _obj) {
		InvtOrderRemote remote = new InvtOrderRemote(_obj.getUid(), _obj.getObjectCreateTime(),
				_obj.getObjectUpdateTime());
		remote.setIosn(_obj.getIosn());
		remote.setApplierId(_obj.getApplierId());
		remote.setApplierName(_obj.getApplierName());
		remote.setApvTime(_obj.getApvTime());
		remote.setRemark(_obj.getRemark());
		return remote;
	}

	public static InvtOrderCreateObj parseInvtOrderCreateObj(InvtOrderCreateObjRemote _remote) {
		InvtOrderCreateObj dto = new InvtOrderCreateObj();
		dto.setApplierId(_remote.getApplierId());
		dto.setApplierName(_remote.getApplierName());
		dto.setApvTime(_remote.getApvTime());
		dto.setRemark(_remote.getRemark());
		return dto;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------InvtOrderItem---------------------------------
	public static InvtOrderItemRemote parseInvtOrderItemRemote(InvtOrderItem _obj) {
		InvtOrderItemRemote remote = new InvtOrderItemRemote(_obj.getUid(), _obj.getObjectCreateTime(),
				_obj.getObjectUpdateTime());
		remote.setIoUid(_obj.getIoUid());
		remote.setMmUid(_obj.getMmUid());
		remote.setMiUid(_obj.getMiUid());
		remote.setWrhsBinUid(_obj.getWrhsBinUid());
		remote.setIoType(_obj.getIoType());
		remote.setOrderQty(_obj.getOrderQty());
		remote.setOrderValue(_obj.getOrderValue());
		return remote;
	}

	public static InvtOrderItemCreateObj parseInvtOrderItemCreateObj(InvtOrderItemCreateObjRemote _remote) {
		InvtOrderItemCreateObj dto = new InvtOrderItemCreateObj();
		dto.setIoUid(_remote.getIoUid());
		dto.setMmUid(_remote.getMmUid());
		dto.setMiUid(_remote.getMiUid());
		dto.setWrhsBinUid(_remote.getWrhsBinUid());
		dto.setIoType(_remote.getIoType());
		dto.setOrderQty(_remote.getOrderQty());
		dto.setOrderValue(_remote.getOrderValue());
		return dto;
	}

	// -------------------------------------------------------------------------------
	// --------------------------------MaterialMaster---------------------------------
	public static MaterialMasterRemote parseMaterialMasterRemote(MaterialMaster _obj) {
		MaterialMasterRemote remote = new MaterialMasterRemote(_obj.getUid(), _obj.getObjectCreateTime(),
				_obj.getObjectUpdateTime());
		remote.setMano(_obj.getMano());
		remote.setName(_obj.getName());
		remote.setSpecification(_obj.getSpecification());
		remote.setStdUnit(_obj.getStdUnit());
		remote.setSumStockQty(_obj.getSumStockQty());
		remote.setSumStockValue(_obj.getSumStockValue());
		return remote;
	}

	public static MaterialMasterCreateObj parseMaterialMasterCreateObj(MaterialMasterCreateObjRemote _remote) {
		MaterialMasterCreateObj dto = new MaterialMasterCreateObj();
		dto.setMano(_remote.getMano());
		dto.setName(_remote.getName());
		dto.setSpecification(_remote.getSpecification());
		dto.setStdUnit(_remote.getStdUnit());
		return dto;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------MaterialInst----------------------------------
	public static MaterialInstRemote parseMaterialInstRemote(MaterialInst _obj) {
		MaterialInstRemote remote = new MaterialInstRemote(_obj.getUid(), _obj.getObjectCreateTime(),
				_obj.getObjectUpdateTime());
		remote.setMmUid(_obj.getMmUid());
		remote.setMisn(_obj.getMisn());
		remote.setMiac(_obj.getMiac());
		remote.setQty(_obj.getQty());
		remote.setValue(_obj.getValue());
		remote.setEffDate(_obj.getEffDate());
		remote.setExpDate(_obj.getExpDate());
		return remote;
	}

	public static MaterialInstCreateObj parseMaterialInstCreateObj(MaterialInstCreateObjRemote _remote) {
		MaterialInstCreateObj dto = new MaterialInstCreateObj();
		dto.setMmUid(_remote.getMmUid());
		dto.setMiac(_remote.getMiac());
		dto.setQty(_remote.getQty());
		dto.setValue(_remote.getValue());
		dto.setEffDate(_remote.getEffDate());
		dto.setExpDate(_remote.getExpDate());
		return dto;
	}

	// -------------------------------------------------------------------------------
	// -------------------------------MaterialBinStock--------------------------------
	public static MaterialBinStockRemote parseMaterialBinStockRemote(MaterialBinStock _obj) {
		MaterialBinStockRemote remote = new MaterialBinStockRemote(_obj.getUid(), _obj.getObjectCreateTime(),
				_obj.getObjectUpdateTime());
		remote.setMmUid(_obj.getMmUid());
		remote.setMano(_obj.getMano());
		remote.setWrhsBinUid(_obj.getWrhsBinUid());
		remote.setSumStockQty(_obj.getSumStockQty());
		remote.setSumStockValue(_obj.getSumStockValue());
		return remote;
	}

	public static MaterialBinStockCreateObj parseMaterialBinStockCreateObj(MaterialBinStockCreateObjRemote _remote) {
		MaterialBinStockCreateObj dto = new MaterialBinStockCreateObj();
		dto.setMmUid(_remote.getMmUid());
		dto.setMano(_remote.getMano());
		dto.setWrhsBinUid(_remote.getWrhsBinUid());
		return dto;
	}

	// -------------------------------------------------------------------------------
	// -----------------------------MaterialBinStockBatch-----------------------------
	public static MaterialBinStockBatchRemote parseMaterialBinStockBatchRemote(MaterialBinStockBatch _obj) {
		MaterialBinStockBatchRemote remote = new MaterialBinStockBatchRemote(_obj.getUid(), _obj.getObjectCreateTime(),
				_obj.getObjectUpdateTime());
		remote.setMbsUid(_obj.getMbsUid());
		remote.setMiUid(_obj.getMiUid());
		remote.setStockQty(_obj.getStockQty());
		remote.setStockValue(_obj.getStockValue());
		return remote;
	}

	public static MaterialBinStockBatchCreateObj parseMaterialBinStockBatchCreateObj(
			MaterialBinStockBatchCreateObjRemote _remote) {
		MaterialBinStockBatchCreateObj dto = new MaterialBinStockBatchCreateObj();
		dto.setMbsUid(_remote.getMbsUid());
		dto.setMiUid(_remote.getMiUid());
		return dto;
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------MbsbStmt------------------------------------
	public static MbsbStmtRemote parseMbsbStmtRemote(MbsbStmt _obj) {
		MbsbStmtRemote remote = new MbsbStmtRemote(_obj.getUid(), _obj.getObjectCreateTime(),
				_obj.getObjectUpdateTime());
		remote.setMbsbUid(_obj.getMbsbUid());
		remote.setIoiUid(_obj.getIoiUid());
		remote.setMbsbFlowType(_obj.getMbsbFlowType());
		remote.setStmtQty(_obj.getStmtQty());
		remote.setStmtValue(_obj.getStmtValue());
		remote.setPostingStatus(_obj.getPostingStatus());
		remote.setPostingTime(_obj.getPostingTime());
		return remote;
	}

	public static MbsbStmtCreateObj parseMbsbStmtCreateObj(MbsbStmtCreateObjRemote _remote) {
		MbsbStmtCreateObj dto = new MbsbStmtCreateObj();
		dto.setMbsbUid(_remote.getMbsbUid());
		dto.setIoiUid(_remote.getIoiUid());
		dto.setMbsbFlowType(_remote.getMbsbFlowType());
		dto.setStmtQty(_remote.getStmtQty());
		dto.setStmtValue(_remote.getStmtValue());
		return dto;
	}
}
