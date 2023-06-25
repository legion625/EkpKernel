package ekp.invt;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.data.InvtDataService;
import ekp.invt.dto.InvtOrderCreateObj;
import ekp.invt.dto.InvtOrderItemCreateObj;
import ekp.invt.dto.MaterialBinStockBatchCreateObj;
import ekp.invt.dto.MaterialBinStockCreateObj;
import ekp.invt.dto.MaterialInstCreateObj;
import ekp.invt.dto.MaterialMasterCreateObj;
import ekp.invt.dto.MbsbStmtCreateObj;
import ekp.invt.dto.WrhsBinCreateObj;
import ekp.invt.dto.WrhsLocCreateObj;
import ekp.invt.type.MbsbFlowType;
import legion.DataServiceFactory;
import legion.util.TimeTraveler;

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
	// TODO search

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
	// TODO search

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
	public List<MaterialBinStock> loadMaterialBinStockList(String _mmUid) {
		return dataService.loadMaterialBinStockList(_mmUid);
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
