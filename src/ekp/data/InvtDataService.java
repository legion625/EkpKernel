package ekp.data;

import java.util.List;
import java.util.Map;

import ekp.data.service.invt.query.InvtOrderItemQueryParam;
import ekp.data.service.invt.query.InvtOrderQueryParam;
import ekp.invt.InvtOrder;
import ekp.invt.InvtOrderItem;
import ekp.invt.MaterialBinStock;
import ekp.invt.MaterialBinStockBatch;
import ekp.invt.MaterialInst;
import ekp.invt.MaterialMaster;
import ekp.invt.MbsbStmt;
import ekp.invt.WrhsBin;
import ekp.invt.WrhsLoc;
import legion.IntegrationService;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public interface InvtDataService extends IntegrationService {
	
	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsLoc------------------------------------
	public boolean saveWrhsLoc(WrhsLoc _wl);
	public boolean deleteWrhsLoc(String _uid);
	public WrhsLoc loadWrhsLoc(String _uid);
	public WrhsLoc loadWrhsLocById(String _id);
	public List<WrhsLoc> loadWrhsLocList();
	
	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsBin------------------------------------
	public boolean saveWrhsBin(WrhsBin _wb);
	public boolean deleteWrhsBin(String _uid);
	public WrhsBin loadWrhsBin(String _uid);
	public List<WrhsBin> loadWrhsBinList(String _wlUid);
	
	// -------------------------------------------------------------------------------
	// -----------------------------------InvtOrder-----------------------------------
	public boolean saveInvtOrder(InvtOrder _io);
	public boolean deleteInvtOrder(String _uid);
	public InvtOrder loadInvtOrder(String _uid);
	public InvtOrder loadInvtOrderByIosn(String _iosn);
	public QueryOperation<InvtOrderQueryParam, InvtOrder> searchInvtOrder(QueryOperation<InvtOrderQueryParam, InvtOrder> _param
			,Map<InvtOrderQueryParam, QueryValue[]> _existsDetailMap);
	
	// -------------------------------------------------------------------------------
	// ---------------------------------InvtOrderItem---------------------------------
	public boolean saveInvtOrderItem(InvtOrderItem _ioi);
	public boolean deleteInvtOrderItem(String _uid);
	public InvtOrderItem loadInvtOrderItem(String _uid);
	public List<InvtOrderItem> loadInvtOrderItemList(String _ioUid);
	public QueryOperation<InvtOrderItemQueryParam, InvtOrderItem> searchInvtOrderItem(QueryOperation<InvtOrderItemQueryParam, InvtOrderItem> _param
			, Map<InvtOrderItemQueryParam, QueryValue[]> _existsDetailMap);
	
	// -------------------------------------------------------------------------------
	// --------------------------------MaterialMaster---------------------------------
	public boolean saveMaterialMaster(MaterialMaster _mm);
	public boolean deleteMaterialMaster(String _uid);
	public MaterialMaster loadMaterialMaster(String _uid);
	public MaterialMaster loadMaterialMasterByMano(String _mano);
	
	// -------------------------------------------------------------------------------
	// ---------------------------------MaterialInst----------------------------------
	public boolean saveMaterialInst(MaterialInst _mi);
	public boolean deleteMaterialInst(String _uid);
	public MaterialInst loadMaterialInst(String _uid);
	public MaterialInst loadMaterialInstByMisn(String _misn);
	public List<MaterialInst> loadMaterialInstList(String _mmUid);
	
	// -------------------------------------------------------------------------------
	// -------------------------------MaterialBinStock--------------------------------
	public boolean saveMaterialBinStock(MaterialBinStock _mbs);
	public boolean deleteMaterialBinStock(String _uid);
	public MaterialBinStock loadMaterialBinStock(String _uid);
	public List<MaterialBinStock> loadMaterialBinStockList(String _mmUid);
	
	// -------------------------------------------------------------------------------
	// -----------------------------MaterialBinStockBatch-----------------------------
	public boolean saveMaterialBinStockBatch(MaterialBinStockBatch _mbsb);
	public boolean deleteMaterialBinStockBatch(String _uid);
	public MaterialBinStockBatch loadMaterialBinStockBatch(String _uid);
	public MaterialBinStockBatch loadMaterialBinStockBatch(String _mbsUid, String _miUid);
	public List<MaterialBinStockBatch> loadMaterialBinStockBatchList(String _mbsUid);
	public List<MaterialBinStockBatch> loadMaterialBinStockBatchListByMi(String _miUid);
	
	// -------------------------------------------------------------------------------
	// -----------------------------------MbsbStmt------------------------------------
	public boolean saveMbsbStmt(MbsbStmt _mbsbs);
	public boolean deleteMbsbStmt(String _uid);
	public MbsbStmt loadMbsbStmt(String _uid);
	public List<MbsbStmt> loadMbsbStmtList(String _mbsbUid);
	public List<MbsbStmt> loadMbsbStmtListByIoi(String _ioiUid);
}
