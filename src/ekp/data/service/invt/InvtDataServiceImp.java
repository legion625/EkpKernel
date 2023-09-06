package ekp.data.service.invt;

import java.util.List;
import java.util.Map;

import ekp.data.InvtDataService;
import ekp.data.service.invt.query.InvtOrderItemQueryParam;
import ekp.data.service.invt.query.InvtOrderQueryParam;
import ekp.data.service.invt.query.MaterialMasterQueryParam;
import ekp.data.service.invt.query.MbsbStmtQueryParam;
import ekp.invt.InvtOrder;
import ekp.invt.InvtOrderItem;
import ekp.invt.MaterialBinStock;
import ekp.invt.MaterialBinStockBatch;
import ekp.invt.MaterialInst;
import ekp.invt.MaterialInstSrcConj;
import ekp.invt.MaterialMaster;
import ekp.invt.MbsbStmt;
import ekp.invt.WrhsBin;
import ekp.invt.WrhsLoc;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public class InvtDataServiceImp implements InvtDataService {

	private String source;

	// dao
	private WrhsDao wrhsDao;
	private MaterialDao materialDao;

	@Override
	public void register(Map<String, String> _params) {
		if (_params == null || _params.isEmpty())
			return;

		source = _params.get("source");

		// dao
		wrhsDao = new WrhsDao(source);
		materialDao = new MaterialDao(source);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	// -------------------------------------------------------------------------------
	// --------------------------------MaterialMaster---------------------------------
	@Override
	public boolean saveMaterialMaster(MaterialMaster _mm) {
		return materialDao.saveMaterialMaster(_mm);
	}

	@Override
	public boolean deleteMaterialMaster(String _uid) {
		return materialDao.deleteMaterialMaster(_uid);
	}

	@Override
	public MaterialMaster loadMaterialMaster(String _uid) {
		return materialDao.loadMaterialMaster(_uid);
	}

	@Override
	public MaterialMaster loadMaterialMasterByMano(String _mano) {
		return materialDao.loadMaterialMasterByMano(_mano);
	}
	
	@Override
	public QueryOperation<MaterialMasterQueryParam, MaterialMaster> searchMaterialMaster(
			QueryOperation<MaterialMasterQueryParam, MaterialMaster> _param){
		return materialDao.searchMaterialMaster(_param);
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsLoc------------------------------------
	@Override
	public boolean saveWrhsLoc(WrhsLoc _wl) {
		return wrhsDao.saveWrhsLoc(_wl);
	}

	@Override
	public boolean deleteWrhsLoc(String _uid) {
		return wrhsDao.deleteWrhsLoc(_uid);
	}

	@Override
	public WrhsLoc loadWrhsLoc(String _uid) {
		return wrhsDao.loadWrhsLoc(_uid);
	}

	@Override
	public WrhsLoc loadWrhsLocById(String _id) {
		return wrhsDao.loadWrhsLocById(_id);
	}

	@Override
	public List<WrhsLoc> loadWrhsLocList() {
		return wrhsDao.loadWrhsLocList();
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsBin------------------------------------
	@Override
	public boolean saveWrhsBin(WrhsBin _wb) {
		return wrhsDao.saveWrhsBin(_wb);
	}

	@Override
	public boolean deleteWrhsBin(String _uid) {
		return wrhsDao.deleteWrhsBin(_uid);
	}

	@Override
	public WrhsBin loadWrhsBin(String _uid) {
		return wrhsDao.loadWrhsBin(_uid);
	}
	
	@Override
	public WrhsBin loadWrhsBin(String _wlUid, String _id) {
		return wrhsDao.loadWrhsBin(_wlUid, _id);
	}

	@Override
	public List<WrhsBin> loadWrhsBinList(String _wlUid) {
		return wrhsDao.loadWrhsBinList(_wlUid);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------InvtOrder-----------------------------------
	@Override
	public boolean saveInvtOrder(InvtOrder _io) {
		return wrhsDao.saveInvtOrder(_io);
	}

	@Override
	public boolean deleteInvtOrder(String _uid) {
		return wrhsDao.deleteInvtOrder(_uid);
	}

	@Override
	public InvtOrder loadInvtOrder(String _uid) {
		return wrhsDao.loadInvtOrder(_uid);
	}

	@Override
	public InvtOrder loadInvtOrderByIosn(String _iosn) {
		return wrhsDao.loadInvtOrderByIosn(_iosn);
	}
	
	@Override
	public QueryOperation<InvtOrderQueryParam, InvtOrder> searchInvtOrder(QueryOperation<InvtOrderQueryParam, InvtOrder> _param
			,Map<InvtOrderQueryParam, QueryValue[]> _existsDetailMap){
		return wrhsDao.searchInvtOrder(_param, _existsDetailMap);
	}
	
	// -------------------------------------------------------------------------------
	// ---------------------------------InvtOrderItem---------------------------------
	@Override
	public boolean saveInvtOrderItem(InvtOrderItem _ioi) {
		return wrhsDao.saveInvtOrderItem(_ioi);
	}

	@Override
	public boolean deleteInvtOrderItem(String _uid) {
		return wrhsDao.deleteInvtOrderItem(_uid);
	}

	@Override
	public InvtOrderItem loadInvtOrderItem(String _uid) {
		return wrhsDao.loadInvtOrderItem(_uid);
	}

	@Override
	public List<InvtOrderItem> loadInvtOrderItemList(String _ioUid) {
		return wrhsDao.loadInvtOrderItemList(_ioUid);
	}
	@Override
	public List<InvtOrderItem> loadInvtOrderItemListByMm(String _mmUid){
		return wrhsDao.loadInvtOrderItemListByMm(_mmUid);
	}
	
	@Override
	public QueryOperation<InvtOrderItemQueryParam, InvtOrderItem> searchInvtOrderItem(QueryOperation<InvtOrderItemQueryParam, InvtOrderItem> _param
			, Map<InvtOrderItemQueryParam, QueryValue[]> _existsDetailMap){
		return wrhsDao.searchInvtOrderItem(_param,  _existsDetailMap);
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------MaterialInst----------------------------------
	@Override
	public boolean saveMaterialInst(MaterialInst _mi) {
		return materialDao.saveMaterialInst(_mi);
	}

	@Override
	public boolean deleteMaterialInst(String _uid) {
		return materialDao.deleteMaterialInst(_uid);
	}

	@Override
	public MaterialInst loadMaterialInst(String _uid) {
		return materialDao.loadMaterialInst(_uid);
	}

	@Override
	public MaterialInst loadMaterialInstByMisn(String _misn) {
		return materialDao.loadMaterialInstByMisn(_misn);
	}
	
	@Override
	public MaterialInst loadMaterialInstByMiacSrcNo(String _miacSrcNo) {
		return materialDao.loadMaterialInstByMiacSrcNo(_miacSrcNo);
	}

	@Override
	public List<MaterialInst> loadMaterialInstList(String _mmUid) {
		return materialDao.loadMaterialInstList(_mmUid);
	}
	
	// -------------------------------------------------------------------------------
	// ------------------------------MaterialInstSrcConj------------------------------
	@Override
	public boolean saveMaterialInstSrcConj(MaterialInstSrcConj _c) {
		return materialDao.saveMaterialInstSrcConj(_c);
	}
	@Override
	public boolean deleteMaterialInstSrcConj(String _uid) {
		return materialDao.deleteMaterialInstSrcConj(_uid);
	}
	@Override
	public MaterialInstSrcConj loadMaterialInstSrcConj(String _uid) {
		return materialDao.loadMaterialInstSrcConj(_uid);
	}
	@Override
	public List<MaterialInstSrcConj> loadMaterialInstSrcConjList(String _miUid){
		return materialDao.loadMaterialInstSrcConjList(_miUid);
	}
	@Override
	public List<MaterialInstSrcConj> loadMaterialInstSrcConjListBySrcMi(String _srcMiUid){
		return materialDao.loadMaterialInstSrcConjListBySrcMi(_srcMiUid);
	}

	// -------------------------------------------------------------------------------
	// -------------------------------MaterialBinStock--------------------------------
	@Override
	public boolean saveMaterialBinStock(MaterialBinStock _mbs) {
		return materialDao.saveMaterialBinStock(_mbs);
	}

	@Override
	public boolean deleteMaterialBinStock(String _uid) {
		return materialDao.deleteMaterialBinStock(_uid);
	}

	@Override
	public MaterialBinStock loadMaterialBinStock(String _uid) {
		return materialDao.loadMaterialBinStock(_uid);
	}
	
	@Override
	public MaterialBinStock loadMaterialBinStock(String _mmUid, String _wrhsBinUid) {
		return materialDao.loadMaterialBinStock(_mmUid, _wrhsBinUid);
	}

	@Override
	public List<MaterialBinStock> loadMaterialBinStockList(String _mmUid) {
		return materialDao.loadMaterialBinStockList(_mmUid);
	}
	
	@Override
	public List<MaterialBinStock> loadMaterialBinStockListByWrhsBin(String _wbUid){
		return materialDao.loadMaterialBinStockListByWrhsBin(_wbUid);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------MaterialBinStockBatch-----------------------------
	@Override
	public boolean saveMaterialBinStockBatch(MaterialBinStockBatch _mbsb) {
		return materialDao.saveMaterialBinStockBatch(_mbsb);
	}

	@Override
	public boolean deleteMaterialBinStockBatch(String _uid) {
		return materialDao.deleteMaterialBinStockBatch(_uid);
	}

	@Override
	public MaterialBinStockBatch loadMaterialBinStockBatch(String _uid) {
		return materialDao.loadMaterialBinStockBatch(_uid);
	}
	
	@Override
	public MaterialBinStockBatch loadMaterialBinStockBatch(String _mbsUid, String _miUid) {
		return materialDao.loadMaterialBinStockBatch(_mbsUid, _miUid);
	}

	@Override
	public List<MaterialBinStockBatch> loadMaterialBinStockBatchList(String _mbsUid) {
		return materialDao.loadMaterialBinStockBatchList(_mbsUid);
	}

	@Override
	public List<MaterialBinStockBatch> loadMaterialBinStockBatchListByMi(String _miUid) {
		return materialDao.loadMaterialBinStockBatchListByMi(_miUid);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------MbsbStmt------------------------------------
	@Override
	public boolean saveMbsbStmt(MbsbStmt _mbsbs) {
		return materialDao.saveMbsbStmt(_mbsbs);
	}

	@Override
	public boolean deleteMbsbStmt(String _uid) {
		return materialDao.deleteMbsbStmt(_uid);
	}

	@Override
	public MbsbStmt loadMbsbStmt(String _uid) {
		return materialDao.loadMbsbStmt(_uid);
	}

	@Override
	public List<MbsbStmt> loadMbsbStmtList(String _mbsbUid) {
		return materialDao.loadMbsbStmtList(_mbsbUid);
	}

	@Override
	public List<MbsbStmt> loadMbsbStmtListByIoi(String _ioiUid) {
		return materialDao.loadMbsbStmtListByIoi(_ioiUid);
	}
	
	@Override
	public QueryOperation<MbsbStmtQueryParam, MbsbStmt> searchMbsbStmt(
			QueryOperation<MbsbStmtQueryParam, MbsbStmt> _param){
		return materialDao.searchMbsbStmt(_param);
	}

}
