package ekp.invt;

import java.util.List;
import java.util.Map;

import ekp.data.service.invt.query.InvtOrderItemQueryParam;
import ekp.data.service.invt.query.InvtOrderQueryParam;
import ekp.data.service.invt.query.MaterialMasterQueryParam;
import ekp.data.service.invt.query.MbsbStmtQueryParam;
import ekp.invt.dto.InvtOrderCreateObj;
import ekp.invt.dto.InvtOrderItemCreateObj;
import ekp.invt.dto.MaterialBinStockBatchCreateObj;
import ekp.invt.dto.MaterialBinStockCreateObj;
import ekp.invt.dto.MaterialInstCreateObj;
import ekp.invt.dto.MaterialInstSrcConjCreateObj;
import ekp.invt.dto.MaterialMasterCreateObj;
import ekp.invt.dto.MbsbStmtCreateObj;
import ekp.invt.dto.WrhsBinCreateObj;
import ekp.invt.dto.WrhsLocCreateObj;
import ekp.invt.type.MaterialInstAcqChannel;
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
	public WrhsBin loadWrhsBin(String _wlUid, String _id); 
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
	
	public boolean invtOrderToApv(String _uid);
	public boolean invtOrderRevertToApv(String _uid);
	public boolean invtOrderApprove(String _uid, long _apvTime);
	public boolean invtOrderRevertApprove(String _uid);
	
	// -------------------------------------------------------------------------------
	// ---------------------------------InvtOrderItem---------------------------------
	public InvtOrderItem createInvtOrderItem(InvtOrderItemCreateObj _dto);
	public boolean deleteInvtOrderItem(String _uid);
	public InvtOrderItem loadInvtOrderItem(String _uid);
	public List<InvtOrderItem> loadInvtOrderItemList(String _ioUid);
	public List<InvtOrderItem> loadInvtOrderItemListByMm(String _mmUid);
	public QueryOperation<InvtOrderItemQueryParam, InvtOrderItem> searchInvtOrderItem(
			QueryOperation<InvtOrderItemQueryParam, InvtOrderItem> _param,
			Map<InvtOrderItemQueryParam, QueryValue[]> _existsDetailMap);
	public boolean invtOrderItemMbsbStmtCreated(String _uid);
	public boolean invtOrderItemRevertMbsbStmtCreated(String _uid);
	
	// -------------------------------------------------------------------------------
	// --------------------------------MaterialMaster---------------------------------	
	public MaterialMaster createMaterialMaster(MaterialMasterCreateObj _dto);
	public boolean deleteMaterialMaster(String _uid);
	public MaterialMaster loadMaterialMaster(String _uid);
	public MaterialMaster loadMaterialMasterByMano(String _mano);
	public QueryOperation<MaterialMasterQueryParam, MaterialMaster> searchMaterialMaster(
			QueryOperation<MaterialMasterQueryParam, MaterialMaster> _param);
	
	// -------------------------------------------------------------------------------
	// ---------------------------------MaterialInst----------------------------------
	public MaterialInst createMaterialInst(MaterialInstCreateObj _dto);
	public boolean deleteMaterialInst(String _uid);
	public MaterialInst loadMaterialInst(String _uid);
	public MaterialInst loadMaterialInstByMisn(String _misn);
//	public MaterialInst loadMaterialInstByMiacSrcNo(String _miacSrcNo);
	public List<MaterialInst> loadMaterialInstList(String _mmUid,MaterialInstAcqChannel _miac, String _miacSrcNo);
	public boolean materialInstToAssignSrcMi(String _uid);
	public boolean materialInstRevertToAssignSrcMi(String _uid);
	public boolean materialInstFinishAssignedSrcMi(String _uid);
	public boolean materialInstRevertFinishAssignedSrcMi(String _uid);
	public boolean materialInstNotAssignSrcMi(String _uid);
	public boolean materialInstRevertNotAssignSrcMi(String _uid);
	
	// -------------------------------------------------------------------------------
	// ------------------------------MaterialInstSrcConj------------------------------
	public MaterialInstSrcConj createMaterialInstSrcConj(MaterialInstSrcConjCreateObj _dto);
	public boolean deleteMaterialInstSrcConj(String _uid);
	public MaterialInstSrcConj loadMaterialInstSrcConj(String _uid);
	public List<MaterialInstSrcConj> loadMaterialInstSrcConjList(String _miUid);
	public List<MaterialInstSrcConj> loadMaterialInstSrcConjListBySrcMi(String _srcMiUid);
	
	// -------------------------------------------------------------------------------
	// -------------------------------MaterialBinStock--------------------------------
	public MaterialBinStock createMaterialBinStock(MaterialBinStockCreateObj _dto);
	public boolean deleteMaterialBinStock(String _uid);
	public MaterialBinStock loadMaterialBinStock(String _uid);
	public MaterialBinStock loadMaterialBinStock(String _mmUid, String _wrhsBinUid);
	public List<MaterialBinStock> loadMaterialBinStockList(String _mmUid);
	public List<MaterialBinStock> loadMaterialBinStockListByWrhsBin(String _wbUid);

	// -------------------------------------------------------------------------------
	// -----------------------------MaterialBinStockBatch-----------------------------
	public MaterialBinStockBatch createMaterialBinStockBatch(MaterialBinStockBatchCreateObj _dto);
	public boolean deleteMaterialBinStockBatch(String _uid);
	public MaterialBinStockBatch loadMaterialBinStockBatch(String _uid);
	public MaterialBinStockBatch loadMaterialBinStockBatch(String _mbsUid, String _miUid);
	public List<MaterialBinStockBatch> loadMaterialBinStockBatchList(String _mbsUid);
	public List<MaterialBinStockBatch> loadMaterialBinStockBatchListByMi(String _miUid);
	
	// -------------------------------------------------------------------------------
	// -----------------------------------MbsbStmt------------------------------------
	public MbsbStmt createMbsbStmt(MbsbStmtCreateObj _dto);
	public boolean deleteMbsbStmt(String _uid);
	public MbsbStmt loadMbsbStmt(String _uid);
	public List<MbsbStmt> loadMbsbStmtList(String _mbsbUid);
	public List<MbsbStmt> loadMbsbStmtListByIoi(String _ioiUid);
	public QueryOperation<MbsbStmtQueryParam, MbsbStmt> searchMbsbStmt(
			QueryOperation<MbsbStmtQueryParam, MbsbStmt> _param);
	
	public boolean mbsbStmtPost(String _uid);
	public boolean mbsbStmtRevertPost(String _uid);
	
}