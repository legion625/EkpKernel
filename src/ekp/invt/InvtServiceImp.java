package ekp.invt;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.data.InvtDataService;
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
import ekp.invt.type.MbsbFlowType;
import legion.DataServiceFactory;
import legion.util.TimeTraveler;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public class InvtServiceImp implements InvtService {

	private Logger log = LoggerFactory.getLogger(InvtServiceImp.class);

	private static InvtDataService dataService;

	@Override
	public void register(Map<String, String> _params) {
		dataService = DataServiceFactory.getInstance().getService(InvtDataService.class);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsLoc------------------------------------
	@Override
	public WrhsLoc createWrhsLoc(WrhsLocCreateObj _dto) {
		return WrhsLoc.create(_dto);
	}

	@Override
	public boolean deleteWrhsLoc(String _uid) {
		return loadWrhsLoc(_uid).delete();
	}

	@Override
	public WrhsLoc loadWrhsLoc(String _uid) {
		return dataService.loadWrhsLoc(_uid);
	}

	@Override
	public WrhsLoc loadWrhsLocById(String _id) {
		return dataService.loadWrhsLocById(_id);
	}

	@Override
	public List<WrhsLoc> loadWrhsLocList() {
		return dataService.loadWrhsLocList();
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsBin------------------------------------
	@Override
	public WrhsBin createWrhsBin(WrhsBinCreateObj _dto) {
		return WrhsBin.create(_dto);
	}

	@Override
	public boolean deleteWrhsBin(String _uid) {
		return loadWrhsBin(_uid).delete();
	}

	@Override
	public WrhsBin loadWrhsBin(String _uid) {
		return dataService.loadWrhsBin(_uid);
	}
	
	@Override
	public WrhsBin loadWrhsBin(String _wlUid, String _id) {
		return dataService.loadWrhsBin(_wlUid, _id);
	}
	
	@Override
	public List<WrhsBin> loadWrhsBinList(String _wlUid) {
		return dataService.loadWrhsBinList(_wlUid);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------InvtOrder-----------------------------------
	@Override
	public InvtOrder createInvtOrder(InvtOrderCreateObj _dto) {
		return InvtOrder.create(_dto);
	}

	@Override
	public boolean deleteInvtOrder(String _uid) {
		return loadInvtOrder(_uid).delete();
	}

	@Override
	public InvtOrder loadInvtOrder(String _uid) {
		return dataService.loadInvtOrder(_uid);
	}

	@Override
	public InvtOrder loadInvtOrderByIosn(String _iosn) {
		return dataService.loadInvtOrderByIosn(_iosn);
	}
	
	@Override
	public QueryOperation<InvtOrderQueryParam, InvtOrder> searchInvtOrder(
			QueryOperation<InvtOrderQueryParam, InvtOrder> _param,
			Map<InvtOrderQueryParam, QueryValue[]> _existsDetailMap) {
		return dataService.searchInvtOrder(_param, _existsDetailMap);
	}
	@Override
	public boolean invtOrderToApv(String _uid) {
		return loadInvtOrder(_uid).toApv();
	}

	@Override
	public boolean invtOrderRevertToApv(String _uid) {
		return loadInvtOrder(_uid).revertToApv();
	}

	@Override
	public boolean invtOrderApprove(String _uid, long _apvTime) {
		return loadInvtOrder(_uid).approve(_apvTime);
	}

	@Override
	public boolean invtOrderRevertApprove(String _uid) {
		return loadInvtOrder(_uid).revertApprove();
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------InvtOrderItem---------------------------------
	@Override
	public InvtOrderItem createInvtOrderItem(InvtOrderItemCreateObj _dto) {
		return InvtOrderItem.create(_dto);
	}

	@Override
	public boolean deleteInvtOrderItem(String _uid) {
		return loadInvtOrderItem(_uid).delete();
	}

	@Override
	public InvtOrderItem loadInvtOrderItem(String _uid) {
		return dataService.loadInvtOrderItem(_uid);
	}

	@Override
	public List<InvtOrderItem> loadInvtOrderItemList(String _ioUid) {
		return dataService.loadInvtOrderItemList(_ioUid);
	}
	@Override
	public List<InvtOrderItem> loadInvtOrderItemListByMm(String _mmUid){
		return dataService.loadInvtOrderItemListByMm(_mmUid);
	}
	
	@Override
	public QueryOperation<InvtOrderItemQueryParam, InvtOrderItem> searchInvtOrderItem(QueryOperation<InvtOrderItemQueryParam, InvtOrderItem> _param, Map<InvtOrderItemQueryParam, QueryValue[]> _existsDetailMap){
		return dataService.searchInvtOrderItem(_param,  _existsDetailMap);
	}
	@Override
	public boolean invtOrderItemMbsbStmtCreated(String _uid) {
		return loadInvtOrderItem(_uid).mbsbStmtCreated();
	}
	@Override
	public boolean invtOrderItemRevertMbsbStmtCreated(String _uid) {
		return loadInvtOrderItem(_uid).revertMbsbStmtCreated();
	}

	// -------------------------------------------------------------------------------
	// --------------------------------MaterialMaster---------------------------------
	@Override
	public MaterialMaster createMaterialMaster(MaterialMasterCreateObj _dto) {
		return MaterialMaster.create(_dto);
	}

	@Override
	public boolean deleteMaterialMaster(String _uid) {
		return loadMaterialMaster(_uid).delete();
	}

	@Override
	public MaterialMaster loadMaterialMaster(String _uid) {
		return dataService.loadMaterialMaster(_uid);
	}

	@Override
	public MaterialMaster loadMaterialMasterByMano(String _mano) {
		return dataService.loadMaterialMasterByMano(_mano);
	}
	@Override
	public QueryOperation<MaterialMasterQueryParam, MaterialMaster> searchMaterialMaster(
			QueryOperation<MaterialMasterQueryParam, MaterialMaster> _param){
		return dataService.searchMaterialMaster(_param);
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------MaterialInst----------------------------------
	@Override
	public MaterialInst createMaterialInst(MaterialInstCreateObj _dto) {
		return MaterialInst.create(_dto);
	}

	@Override
	public boolean deleteMaterialInst(String _uid) {
		return loadMaterialInst(_uid).delete();
	}

	@Override
	public MaterialInst loadMaterialInst(String _uid) {
		return dataService.loadMaterialInst(_uid);
	}

	@Override
	public MaterialInst loadMaterialInstByMisn(String _misn) {
		return dataService.loadMaterialInstByMisn(_misn);
	}

	@Override
	public List<MaterialInst> loadMaterialInstList(String _mmUid) {
		return dataService.loadMaterialInstList(_mmUid);
	}
	@Override
	public boolean materialInstToAssignSrcMi(String _uid) {
		return loadMaterialInst(_uid).toAssignSrcMi();
	}
	public boolean materialInstRevertToAssignSrcMi(String _uid){
		return loadMaterialInst(_uid).revertToAssignSrcMi();
	}
	public boolean materialInstFinishAssignedSrcMi(String _uid){
		return loadMaterialInst(_uid).finishAssignedSrcMi();
	}
	public boolean materialInstRevertFinishAssignedSrcMi(String _uid){
		return loadMaterialInst(_uid).revertFinishAssingedSrcMi();
	}
	public boolean materialInstNotAssignSrcMi(String _uid){
		return loadMaterialInst(_uid).notAssignSrcMi();
	}
	public boolean materialInstRevertNotAssignSrcMi(String _uid){
		return loadMaterialInst(_uid).revertNotAssignSrcSMi();
	}
	
	// -------------------------------------------------------------------------------
	// ------------------------------MaterialInstSrcConj------------------------------
	public MaterialInstSrcConj createMaterialInstSrcConj(MaterialInstSrcConjCreateObj _dto) {
		return MaterialInstSrcConj.create(_dto);
	}
	public boolean deleteMaterialInstSrcConj(String _uid) {
		return loadMaterialInstSrcConj(_uid).delete();
	}
	public MaterialInstSrcConj loadMaterialInstSrcConj(String _uid) {
		return dataService.loadMaterialInstSrcConj(_uid);
	}
	public List<MaterialInstSrcConj> loadMaterialInstSrcConjList(String _miUid){
		return dataService.loadMaterialInstSrcConjList(_miUid);
	}
	public List<MaterialInstSrcConj> loadMaterialInstSrcConjListBySrcMi(String _srcMiUid){
		return dataService.loadMaterialInstSrcConjListBySrcMi(_srcMiUid);
	}

	// -------------------------------------------------------------------------------
	// -------------------------------MaterialBinStock--------------------------------
	@Override
	public MaterialBinStock createMaterialBinStock(MaterialBinStockCreateObj _dto) {
		return MaterialBinStock.create(_dto);
	}

	@Override
	public boolean deleteMaterialBinStock(String _uid) {
		return loadMaterialBinStock(_uid).delete();
	}

	@Override
	public MaterialBinStock loadMaterialBinStock(String _uid) {
		return dataService.loadMaterialBinStock(_uid);
	}
	
	@Override
	public MaterialBinStock loadMaterialBinStock(String _mmUid, String _wrhsBinUid) {
		return dataService.loadMaterialBinStock(_mmUid, _wrhsBinUid);
	}

	@Override
	public List<MaterialBinStock> loadMaterialBinStockList(String _mmUid) {
		return dataService.loadMaterialBinStockList(_mmUid);
	}
	
	@Override
	public List<MaterialBinStock> loadMaterialBinStockListByWrhsBin(String _wbUid){
		return dataService.loadMaterialBinStockListByWrhsBin(_wbUid);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------MaterialBinStockBatch-----------------------------
	@Override
	public MaterialBinStockBatch createMaterialBinStockBatch(MaterialBinStockBatchCreateObj _dto) {
		return MaterialBinStockBatch.create(_dto);
	}

	@Override
	public boolean deleteMaterialBinStockBatch(String _uid) {
		return loadMaterialBinStockBatch(_uid).delete();
	}

	@Override
	public MaterialBinStockBatch loadMaterialBinStockBatch(String _uid) {
		return dataService.loadMaterialBinStockBatch(_uid);
	}
	
	@Override
	public MaterialBinStockBatch loadMaterialBinStockBatch(String _mbsUid, String _miUid) {
		return dataService.loadMaterialBinStockBatch(_mbsUid, _miUid);
	}

	@Override
	public List<MaterialBinStockBatch> loadMaterialBinStockBatchList(String _mbsUid) {
		return dataService.loadMaterialBinStockBatchList(_mbsUid);
	}

	@Override
	public List<MaterialBinStockBatch> loadMaterialBinStockBatchListByMi(String _miUid) {
		return dataService.loadMaterialBinStockBatchListByMi(_miUid);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------MbsbStmt------------------------------------
	@Override
	public MbsbStmt createMbsbStmt(MbsbStmtCreateObj _dto) {
		return MbsbStmt.create(_dto);
	}

	@Override
	public boolean deleteMbsbStmt(String _uid) {
		return loadMbsbStmt(_uid).delete();
	}

	@Override
	public MbsbStmt loadMbsbStmt(String _uid) {
		return dataService.loadMbsbStmt(_uid);
	}

	@Override
	public List<MbsbStmt> loadMbsbStmtList(String _mbsbUid) {
		return dataService.loadMbsbStmtList(_mbsbUid);
	}

	@Override
	public List<MbsbStmt> loadMbsbStmtListByIoi(String _ioiUid) {
		return dataService.loadMbsbStmtListByIoi(_ioiUid);
	}
	
	@Override
	public QueryOperation<MbsbStmtQueryParam, MbsbStmt> searchMbsbStmt(
			QueryOperation<MbsbStmtQueryParam, MbsbStmt> _param){
		return dataService.searchMbsbStmt(_param);
	}
	
	@Override
	public  boolean mbsbStmtPost(String _uid) {
		synchronized (this) {
			MbsbStmt mbsbs = loadMbsbStmt(_uid);
			if (mbsbs == null) {
				log.debug("mbsbs null.");
				return false;
			}
			MaterialBinStockBatch mbsb = loadMaterialBinStockBatch(mbsbs.getMbsbUid());
			if (mbsb == null) {
				log.error("mbsb null.");
				return false;
			}
			MaterialBinStock mbs = loadMaterialBinStock(mbsb.getMbsUid());
			if (mbs == null) {
				log.error("mbs null.");
				return false;
			}
			MaterialMaster mm = loadMaterialMaster(mbs.getMmUid());
			if (mm == null) {
				log.error("mm null.");
				return false;
			}

			/**/
			TimeTraveler tt = new TimeTraveler();

			/**/
			if (!mbsbs.post(System.currentTimeMillis())) {
				log.error("mbsbs.post return false.");
				tt.travel();
				return false;
			}
			tt.addSite("revert mbsbs.post", () -> mbsbs.revertPost());

			/**/
			if (MbsbFlowType.IN == mbsbs.getMbsbFlowType()) {
				double addingQty = mbsbs.getStmtQty();
				double addingValue = mbsbs.getStmtValue();

				// mbsb
				if (!mbsb.add(addingQty, addingValue)) {
					log.error("mbsb.add return false.");
					tt.travel();
					return false;
				}
				tt.addSite("revert mbsb.add", () -> mbsb.subtract(addingQty, addingValue));

				// mbs
				if (!mbs.add(addingQty, addingValue)) {
					log.error("mbs.add return false.");
					tt.travel();
					return false;
				}
				tt.addSite("revert mbs.add", () -> mbs.subtract(addingQty, addingValue));

				// mm
				if (!mm.add(addingQty, addingValue)) {
					log.error("mm.add return false.");
					tt.travel();
					return false;
				}
				tt.addSite("revert mm.add", () -> mm.subtract(addingQty, addingValue));
			} else if (MbsbFlowType.OUT == mbsbs.getMbsbFlowType()) {
				double subtractingQty = mbsbs.getStmtQty();
				double subtractingValue = mbsbs.getStmtValue();

				// mbsb
				if (!mbsb.subtract(subtractingQty, subtractingValue)) {
					log.error("mbsb.subtract return false.");
					tt.travel();
					return false;
				}
				tt.addSite("revert mbsb.subtract", () -> mbsb.add(subtractingQty, subtractingValue));

				// mbs
				if (!mbs.subtract(subtractingQty, subtractingValue)) {
					log.error("mbs.subtract return false.");
					tt.travel();
					return false;
				}
				tt.addSite("revert mbs.subtract", () -> mbs.add(subtractingQty, subtractingValue));

				// mm
				if (!mm.subtract(subtractingQty, subtractingValue)) {
					log.error("mm.subtract return false.");
					tt.travel();
					return false;
				}
				tt.addSite("revert mm.subtract", () -> mm.add(subtractingQty, subtractingValue));

			} else {
				log.error("mbsbs[{}].getMbsbFlowType()[{}] error.", mbsbs.getUid(), mbsbs.getMbsbFlowType());
				return false;
			}

//			//
//			if (_tt != null)
//				_tt.copySitesFrom(tt);

			return true;
		}
	}

	@Override
	public boolean mbsbStmtRevertPost(String _uid) {
		synchronized (this) {
			MbsbStmt mbsbs = loadMbsbStmt(_uid);
			if (mbsbs == null) {
				log.debug("mbsbs null.");
				return false;
			}
			MaterialBinStockBatch mbsb = loadMaterialBinStockBatch(mbsbs.getMbsbUid());
			if (mbsb == null) {
				log.error("mbsb null.");
				return false;
			}
			MaterialBinStock mbs = loadMaterialBinStock(mbsb.getMbsUid());
			if (mbs == null) {
				log.error("mbs null.");
				return false;
			}
			MaterialMaster mm = loadMaterialMaster(mbs.getMmUid());
			if (mm == null) {
				log.error("mm null.");
				return false;
			}

			/**/
//			TimeTraveler tt = new TimeTraveler();
			boolean result = true;

			/**/
			if (!mbsbs.revertPost()) {
				log.error("revert mbsbs.post return false.");
//				tt.travel();
//				return false;
				result = false;
			}
//			tt.addSite("revert mbsbs.post", () -> mbsbs.revertPost());

			/**/
			if (MbsbFlowType.IN == mbsbs.getMbsbFlowType()) {
				
				double addingQty = mbsbs.getStmtQty();
				double addingValue = mbsbs.getStmtValue();

				// mbsb
				if (!mbsb.subtract(addingQty, addingValue)) {
					log.error("revert mbsb.add return false.");
//					tt.travel();
					result = false;
				}

				// mbs
				if (!mbs.subtract(addingQty, addingValue)) {
					log.error("revert mbs.add return false.");
//					tt.travel();
//					return false;
					result = false;
				}
//				tt.addSite("revert mbs.add", () -> mbs.subtract(addingQty, addingValue));

				// mm
				if (!mm.subtract(addingQty, addingValue)) {
					log.error("revert mm.add return false.");
//					tt.travel();
//					return false;
					result = false;
				}
//				tt.addSite("revert mm.add", () -> mm.subtract(addingQty, addingValue));
			} else if (MbsbFlowType.OUT == mbsbs.getMbsbFlowType()) {
				double subtractingQty = mbsbs.getStmtQty();
				double subtractingValue = mbsbs.getStmtValue();

				// mbsb
				if (!mbsb.add(subtractingQty, subtractingValue)) {
					log.error("revert mbsb.subtract return false.");
//					tt.travel();
//					return false;
					result = false;
				}
//				tt.addSite("revert mbsb.subtract", () -> mbsb.add(subtractingQty, subtractingValue));

				// mbs
				if (!mbs.add(subtractingQty, subtractingValue)) {
					log.error("revert mbs.subtract return false.");
//					tt.travel();
//					return false;
					result = false;
				}
//				tt.addSite("revert mbs.subtract", () -> mbs.add(subtractingQty, subtractingValue));

				// mm
				if (!mm.add(subtractingQty, subtractingValue)) {
					log.error("revert mm.subtract return false.");
//					tt.travel();
//					return false;
					result = false;
				}
//				tt.addSite("revert mm.subtract", () -> mm.add(subtractingQty, subtractingValue));

			} else {
				log.error("mbsbs[{}].getMbsbFlowType()[{}] error.", mbsbs.getUid(), mbsbs.getMbsbFlowType());
//				return false;
				result = false;
			}

//			//
//			if (_tt != null)
//				_tt.copySitesFrom(tt);

			return result;
		}
	}
	
	
	
//	public boolean ioiProgress(InvtOrderItem _ioi, MaterialInst _mi) {
//		TimeTraveler tt = new TimeTraveler();
//
//		/* 1. Find MBSB. If not, Create MBSB. */
//		MaterialBinStockBatch mbsb = dataService.loadMaterialBinStockBatch(_ioi.getMbsUid(), _mi.getUid());
//		if (mbsb == null) {
//			MaterialBinStockBatchCreateObj mbsbCreateObj = new MaterialBinStockBatchCreateObj();
//			mbsbCreateObj.setMbsUid(_ioi.getMbsUid());
//			mbsbCreateObj.setMiUid(_mi.getUid());
//			mbsbCreateObj.setStockQty(_mi.getQty());
//			mbsbCreateObj.setStockValue(_mi.getValue());
//			mbsbCreateObj.setStockTime(System.currentTimeMillis());
//			mbsb = createMaterialBinStockBatch(mbsbCreateObj);
//		}
//		if (mbsb == null) {
//			log.error("mbsb null.");
//			tt.travel();
//			return false;
//		}
//
//		/* 2. create mbsbStmt */
//		MbsbStmtCreateObj mbsbsCreateObj = new MbsbStmtCreateObj();
//		mbsbsCreateObj.setMbsbUid(mbsbUid);
//		mbsbsCreateObj.setIoiUid(ioiUid);
//		mbsbsCreateObj.setMbsbFlowType(mbsbFlowType);
//
////		/* Conj的兩個對象：MaterialBinStockBatch和InvtOrderItem */
////		private String mbsbUid; //
////		private String ioiUid; //
////
////		/* 這個Conj紀錄完整的流向、數量、金額。 */
////		private MbsbFlowType mbsbFlowType;
////		private double stmtQty; // 記錄異動的數量
////		private double stmtValue; // 記錄異動的金額
//		MbsbStmt mbsbs = MbsbStmt.create(_dto);
//	}

}
