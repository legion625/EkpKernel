package ekp.data.service.invt;

import java.util.List;
import java.util.Map;

import ekp.data.InvtDataService;
import ekp.invt.InvtOrder;
import ekp.invt.InvtOrderItem;
import ekp.invt.MaterialBinStock;
import ekp.invt.MaterialBinStockBatch;
import ekp.invt.MaterialInst;
import ekp.invt.MaterialMaster;
import ekp.invt.MbsbStmt;
import ekp.invt.WrhsBin;
import ekp.invt.WrhsLoc;

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
	public List<MaterialInst> loadMaterialInstList(String _mmUid) {
		return materialDao.loadMaterialInstList(_mmUid);
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
	public List<MaterialBinStock> loadMaterialBinStockList(String _mmUid) {
		return materialDao.loadMaterialBinStockList(_mmUid);
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

}
