package ekp.invt;

import java.util.List;
import java.util.Map;

import ekp.data.service.invt.query.InvtOrderItemQueryParam;
import ekp.data.service.invt.query.InvtOrderQueryParam;
import ekp.invt.dto.InvtOrderCreateObj;
import ekp.invt.dto.InvtOrderItemCreateObj;
import ekp.invt.dto.MaterialBinStockBatchCreateObj;
import ekp.invt.dto.MaterialBinStockCreateObj;
import ekp.invt.dto.MaterialInstCreateObj;
import ekp.invt.dto.MaterialMasterCreateObj;
import ekp.invt.dto.MbsbStmtCreateObj;
import ekp.invt.dto.WrhsBinCreateObj;
import ekp.invt.dto.WrhsLocCreateObj;
import legion.BusinessService;
import legion.util.TimeTraveler;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public interface InvtService extends BusinessService {

	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsLoc------------------------------------
	public WrhsLoc createWrhsLoc(WrhsLocCreateObj _dto);
	public boolean deleteWrhsLoc(String _uid);
	public WrhsLoc loadWrhsLoc(String _uid);
	public WrhsLoc loadWrhsLocById(String _id);
	public List<WrhsLoc> loadWrhsLocList(); 
	
	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsBin------------------------------------
	public WrhsBin createWrhsBin(WrhsBinCreateObj _dto);
	public boolean deleteWrhsBin(String _uid);
	public WrhsBin loadWrhsBin(String _uid);
	public List<WrhsBin> loadWrhsBinList(String _wlUid); 
	
	// -------------------------------------------------------------------------------
	// -----------------------------------InvtOrder-----------------------------------
	public InvtOrder createInvtOrder(InvtOrderCreateObj _dto);
	public boolean deleteInvtOrder(String _uid);
	public InvtOrder loadInvtOrder(String _uid);
	public InvtOrder loadInvtOrderByIosn(String _iosn);

	public QueryOperation<InvtOrderQueryParam, InvtOrder> searchInvtOrder(
			QueryOperation<InvtOrderQueryParam, InvtOrder> _param,
			Map<InvtOrderQueryParam, QueryValue[]> _existsDetailMap);
	
	// -------------------------------------------------------------------------------
	// ---------------------------------InvtOrderItem---------------------------------
	public InvtOrderItem createInvtOrderItem(InvtOrderItemCreateObj _dto);
	public boolean deleteInvtOrderItem(String _uid);
	public InvtOrderItem loadInvtOrderItem(String _uid);
	public List<InvtOrderItem> loadInvtOrderItemList(String _ioUid);

	public QueryOperation<InvtOrderItemQueryParam, InvtOrderItem> searchInvtOrderItem(
			QueryOperation<InvtOrderItemQueryParam, InvtOrderItem> _param,
			Map<InvtOrderItemQueryParam, QueryValue[]> _existsDetailMap);
	
	// -------------------------------------------------------------------------------
	// --------------------------------MaterialMaster---------------------------------	
	public MaterialMaster createMaterialMaster(MaterialMasterCreateObj _dto);
	public boolean deleteMaterialMaster(String _uid);
	public MaterialMaster loadMaterialMaster(String _uid);
	public MaterialMaster loadMaterialMasterByMano(String _mano);
	
	// -------------------------------------------------------------------------------
	// ---------------------------------MaterialInst----------------------------------
	public MaterialInst createMaterialInst(MaterialInstCreateObj _dto);
	public boolean deleteMaterialInst(String _uid);
	public MaterialInst loadMaterialInst(String _uid);
	public MaterialInst loadMaterialInstByMisn(String _misn);
	public List<MaterialInst> loadMaterialInstList(String _mmUid);
	
	// -------------------------------------------------------------------------------
	// -------------------------------MaterialBinStock--------------------------------
	public MaterialBinStock createMaterialBinStock(MaterialBinStockCreateObj _dto);
	public boolean deleteMaterialBinStock(String _uid);
	public MaterialBinStock loadMaterialBinStock(String _uid);
	public List<MaterialBinStock> loadMaterialBinStockList(String _mmUid);

	// -------------------------------------------------------------------------------
	// -----------------------------MaterialBinStockBatch-----------------------------
	public MaterialBinStockBatch createMaterialBinStockBatch(MaterialBinStockBatchCreateObj _dto);
	public boolean deleteMaterialBinStockBatch(String _uid);
	public MaterialBinStockBatch loadMaterialBinStockBatch(String _uid);
	public List<MaterialBinStockBatch> loadMaterialBinStockBatchList(String _mbsUid);
	public List<MaterialBinStockBatch> loadMaterialBinStockBatchListByMi(String _miUid);
	
	// -------------------------------------------------------------------------------
	// -----------------------------------MbsbStmt------------------------------------
	public MbsbStmt createMbsbStmt(MbsbStmtCreateObj _dto);
	public boolean deleteMbsbStmt(String _uid);
	public MbsbStmt loadMbsbStmt(String _uid);
	public List<MbsbStmt> loadMbsbStmtList(String _mbsbUid);
	public List<MbsbStmt> loadMbsbStmtListByIoi(String _ioiUid);
	
	public boolean mbsbStmtPost(String _uid);
	public boolean mbsbStmtRevertPost(String _uid);
	
	
	
	
	

}