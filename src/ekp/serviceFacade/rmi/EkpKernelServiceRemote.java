package ekp.serviceFacade.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import ekp.data.service.invt.query.InvtOrderItemQueryParam;
import ekp.data.service.invt.query.InvtOrderQueryParam;
import ekp.data.service.invt.query.MbsbStmtQueryParam;
import ekp.data.service.mbom.query.PartCfgQueryParam;
import ekp.data.service.mbom.query.PartQueryParam;
import ekp.data.service.mbom.query.PpartSkewerQueryParam;
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
import ekp.mbom.dto.PpartSkewer;
import ekp.mbom.type.PartAcquisitionType;
import ekp.mbom.type.PartUnit;
import ekp.serviceFacade.rmi.invt.InvtOrderCreateObjRemote;
import ekp.serviceFacade.rmi.invt.InvtOrderItemCreateObjRemote;
import ekp.serviceFacade.rmi.invt.InvtOrderItemRemote;
import ekp.serviceFacade.rmi.invt.InvtOrderRemote;
import ekp.serviceFacade.rmi.invt.MaterialBinStockBatchCreateObjRemote;
import ekp.serviceFacade.rmi.invt.MaterialBinStockBatchRemote;
import ekp.serviceFacade.rmi.invt.MaterialBinStockCreateObjRemote;
import ekp.serviceFacade.rmi.invt.MaterialBinStockRemote;
import ekp.serviceFacade.rmi.invt.MaterialInstCreateObjRemote;
import ekp.serviceFacade.rmi.invt.MaterialInstRemote;
import ekp.serviceFacade.rmi.invt.MaterialMasterCreateObjRemote;
import ekp.serviceFacade.rmi.invt.MaterialMasterRemote;
import ekp.serviceFacade.rmi.invt.MbsbStmtCreateObjRemote;
import ekp.serviceFacade.rmi.invt.MbsbStmtRemote;
import ekp.serviceFacade.rmi.invt.WrhsBinCreateObjRemote;
import ekp.serviceFacade.rmi.invt.WrhsBinRemote;
import ekp.serviceFacade.rmi.invt.WrhsLocCreateObjRemote;
import ekp.serviceFacade.rmi.invt.WrhsLocRemote;
import ekp.serviceFacade.rmi.mbom.ParsPartRemote;
import ekp.serviceFacade.rmi.mbom.ParsProcCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.ParsProcRemote;
import ekp.serviceFacade.rmi.mbom.PartAcqRoutingStepCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.PartAcqRoutingStepRemote;
import ekp.serviceFacade.rmi.mbom.PartAcquisitionCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.PartAcquisitionRemote;
import ekp.serviceFacade.rmi.mbom.PartCfgConjRemote;
import ekp.serviceFacade.rmi.mbom.PartCfgCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.PartCfgRemote;
import ekp.serviceFacade.rmi.mbom.PartCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.PartRemote;
import ekp.serviceFacade.rmi.mbom.PpartSkewerRemote;
import ekp.serviceFacade.rmi.mbom.ProdCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.ProdCtlCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.ProdCtlPartCfgConjRemote;
import ekp.serviceFacade.rmi.mbom.ProdCtlRemote;
import ekp.serviceFacade.rmi.mbom.ProdModCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.ProdModItemRemote;
import ekp.serviceFacade.rmi.mbom.ProdModRemote;
import ekp.serviceFacade.rmi.mbom.ProdRemote;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public interface EkpKernelServiceRemote extends Remote {
	public boolean testCallBack() throws RemoteException;

	// -------------------------------------------------------------------------------
	// -------------------------------------INVT--------------------------------------

	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsLoc------------------------------------
	public WrhsLocRemote createWrhsLoc(WrhsLocCreateObjRemote _dto) throws RemoteException;

	public boolean deleteWrhsLoc(String _uid) throws RemoteException;

	public WrhsLocRemote loadWrhsLoc(String _uid) throws RemoteException;

	public WrhsLocRemote loadWrhsLocById(String _id) throws RemoteException;

	public List<WrhsLocRemote> loadWrhsLocList() throws RemoteException;

	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsBin------------------------------------
	public WrhsBinRemote createWrhsBin(WrhsBinCreateObjRemote _dto) throws RemoteException;

	public boolean deleteWrhsBin(String _uid) throws RemoteException;

	public WrhsBinRemote loadWrhsBin(String _uid) throws RemoteException;

	public List<WrhsBinRemote> loadWrhsBinList(String _wlUid) throws RemoteException;

	// -------------------------------------------------------------------------------
	// -----------------------------------InvtOrder-----------------------------------
	public InvtOrderRemote createInvtOrder(InvtOrderCreateObjRemote _dto) throws RemoteException;

	public boolean deleteInvtOrder(String _uid) throws RemoteException;

	public InvtOrderRemote loadInvtOrder(String _uid) throws RemoteException;

	public InvtOrderRemote loadInvtOrderByIosn(String _iosn) throws RemoteException;

	public QueryOperation<InvtOrderQueryParam, InvtOrderRemote> searchInvtOrder(
			QueryOperation<InvtOrderQueryParam, InvtOrderRemote> _param,
			Map<InvtOrderQueryParam, QueryValue[]> _existsDetailMap) throws RemoteException;

	// -------------------------------------------------------------------------------
	// ---------------------------------InvtOrderItem---------------------------------
	public InvtOrderItemRemote createInvtOrderItem(InvtOrderItemCreateObjRemote _dto) throws RemoteException;

	public boolean deleteInvtOrderItem(String _uid) throws RemoteException;

	public InvtOrderItemRemote loadInvtOrderItem(String _uid) throws RemoteException;

	public List<InvtOrderItemRemote> loadInvtOrderItemList(String _ioUid) throws RemoteException;

	public QueryOperation<InvtOrderItemQueryParam, InvtOrderItemRemote> searchInvtOrderItem(
			QueryOperation<InvtOrderItemQueryParam, InvtOrderItemRemote> _param,
			Map<InvtOrderItemQueryParam, QueryValue[]> _existsDetailMap) throws RemoteException;

	// -------------------------------------------------------------------------------
	// --------------------------------MaterialMaster---------------------------------
	public MaterialMasterRemote createMaterialMaster(MaterialMasterCreateObjRemote _dto) throws RemoteException;

	public boolean deleteMaterialMaster(String _uid) throws RemoteException;

	public MaterialMasterRemote loadMaterialMaster(String _uid) throws RemoteException;

	public MaterialMasterRemote loadMaterialMasterByMano(String _mano) throws RemoteException;

	// -------------------------------------------------------------------------------
	// ---------------------------------MaterialInst----------------------------------
	public MaterialInstRemote createMaterialInst(MaterialInstCreateObjRemote _dto) throws RemoteException;

	public boolean deleteMaterialInst(String _uid) throws RemoteException;

	public MaterialInstRemote loadMaterialInst(String _uid) throws RemoteException;

	public MaterialInstRemote loadMaterialInstByMisn(String _misn) throws RemoteException;

	public List<MaterialInstRemote> loadMaterialInstList(String _mmUid) throws RemoteException;

	// -------------------------------------------------------------------------------
	// -------------------------------MaterialBinStock--------------------------------
	public MaterialBinStockRemote createMaterialBinStock(MaterialBinStockCreateObjRemote _dto) throws RemoteException;

	public boolean deleteMaterialBinStock(String _uid) throws RemoteException;

	public MaterialBinStockRemote loadMaterialBinStock(String _uid) throws RemoteException;

	public List<MaterialBinStockRemote> loadMaterialBinStockList(String _mmUid) throws RemoteException;

	// -------------------------------------------------------------------------------
	// -----------------------------MaterialBinStockBatch-----------------------------
	public MaterialBinStockBatchRemote createMaterialBinStockBatch(MaterialBinStockBatchCreateObjRemote _dto)
			throws RemoteException;

	public boolean deleteMaterialBinStockBatch(String _uid) throws RemoteException;

	public MaterialBinStockBatchRemote loadMaterialBinStockBatch(String _uid) throws RemoteException;

	public List<MaterialBinStockBatchRemote> loadMaterialBinStockBatchList(String _mbsUid) throws RemoteException;

	public List<MaterialBinStockBatchRemote> loadMaterialBinStockBatchListByMi(String _miUid) throws RemoteException;

	// -------------------------------------------------------------------------------
	// -----------------------------------MbsbStmt------------------------------------
	public MbsbStmtRemote createMbsbStmt(MbsbStmtCreateObjRemote _dto) throws RemoteException;

	public boolean deleteMbsbStmt(String _uid) throws RemoteException;

	public MbsbStmtRemote loadMbsbStmt(String _uid) throws RemoteException;

	public List<MbsbStmtRemote> loadMbsbStmtList(String _mbsbUid) throws RemoteException;

	public List<MbsbStmtRemote> loadMbsbStmtListByIoi(String _ioiUid) throws RemoteException;

	public QueryOperation<MbsbStmtQueryParam, MbsbStmtRemote> searchMbsbStmt(
			QueryOperation<MbsbStmtQueryParam, MbsbStmtRemote> _param) throws RemoteException;

	public boolean mbsbStmtPost(String _uid) throws RemoteException;

	public boolean mbsbStmtRevertPost(String _uid) throws RemoteException;
	
	// -------------------------------------------------------------------------------
	// -------------------------------------MBOM--------------------------------------
	
	// -------------------------------------------------------------------------------
	// -------------------------------------Part--------------------------------------
	public PartRemote createPart(PartCreateObjRemote _dto) throws RemoteException;

	public boolean deletePart(String _uid) throws RemoteException;

	public PartRemote loadPart(String _uid) throws RemoteException;

	public PartRemote loadPartByPin(String _pin) throws RemoteException;
	
	public QueryOperation<PartQueryParam, PartRemote> searchPart(QueryOperation<PartQueryParam, PartRemote> _param)
			throws RemoteException;

	public boolean partUpdate(String _uid, String _pin, String _name, PartUnit _unit) throws RemoteException;
	
	// -------------------------------------------------------------------------------
	// --------------------------------PartAcquisition--------------------------------
	public PartAcquisitionRemote createPartAcquisition(PartAcquisitionCreateObjRemote _dto) throws RemoteException;

	public boolean deletePartAcquisition(String _uid) throws RemoteException;

	public PartAcquisitionRemote loadPartAcquisition(String _uid) throws RemoteException;

	public PartAcquisitionRemote loadPartAcquisition(String _partPin, String _id) throws RemoteException;

	public List<PartAcquisitionRemote> loadPartAcquisitionList(String _partUid) throws RemoteException;

	public boolean partAcqStartEditing(String _uid) throws RemoteException;

	public boolean partAcqRevertStartEditing(String _uid) throws RemoteException;

	public boolean partAcqPublish(String _uid, long _publishTime) throws RemoteException;

	public boolean partAcqRevertPublish(String _uid) throws RemoteException;
	
	public boolean partAcqUpdateInfo(String _uid, String _id, String _name, PartAcquisitionType _type)
			throws RemoteException;

	public boolean partAcqUpdateRefUnitCost(String _uid, double _refUnitCost) throws RemoteException;

	// -------------------------------------------------------------------------------
	// ------------------------------PartAcqRoutingStep-------------------------------
	public PartAcqRoutingStepRemote createPartAcqRoutingStep(PartAcqRoutingStepCreateObjRemote _dto)
			throws RemoteException;

	public boolean deletePartAcqRoutingStep(String _uid) throws RemoteException;

	public PartAcqRoutingStepRemote loadPartAcqRoutingStep(String _uid) throws RemoteException;

	public PartAcqRoutingStepRemote loadPartAcqRoutingStep(String _partAcqUid, String _seq) throws RemoteException;

	public List<PartAcqRoutingStepRemote> loadPartAcqRoutingStepList(String _partAcqUid) throws RemoteException;

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsProc------------------------------------
	public ParsProcRemote createParsProc(ParsProcCreateObjRemote _dto) throws RemoteException;

	public boolean deleteParsProc(String _uid) throws RemoteException;

	public ParsProcRemote loadParsProc(String _uid) throws RemoteException;

	public List<ParsProcRemote> loadParsProcList(String _parsUid) throws RemoteException;

	public List<ParsProcRemote> loadParsProcListByProc(String _procUid) throws RemoteException;

	public boolean parsProcAssignProc(String _uid, String _procUid, String _procId) throws RemoteException;

	public boolean parsProcRevertAssignProc(String _uid) throws RemoteException;

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsPart------------------------------------
	public ParsPartRemote createParsPart(String _parsUid) throws RemoteException;

	public boolean deleteParsPart(String _uid) throws RemoteException;

	public ParsPartRemote loadParsPart(String _uid) throws RemoteException;

	public ParsPartRemote loadParsPart(String _parsUid, String _partuid) throws RemoteException;

	public List<ParsPartRemote> loadParsPartList(String _parsUid) throws RemoteException;

	public List<ParsPartRemote> loadParsPartListByPart(String _partUid) throws RemoteException;

	public boolean parsPartAssignPart(String _uid, String _partUid, String _partPin, double _partReqQty)
			throws RemoteException;

	public boolean parsPartRevertAssignPart(String _uid) throws RemoteException;
	
	// -------------------------------------------------------------------------------
	// ----------------------------------PpartSkewer----------------------------------
	public PpartSkewerRemote loadPpartSkewer(String _uid) throws RemoteException;

	public QueryOperation<PpartSkewerQueryParam, PpartSkewerRemote> searchPpartSkewer(
			QueryOperation<PpartSkewerQueryParam, PpartSkewerRemote> _param,
			Map<PpartSkewerQueryParam, QueryValue[]> _existsQvMap) throws RemoteException;

	// -------------------------------------------------------------------------------
	// ------------------------------------PartCfg------------------------------------
	public PartCfgRemote createPartCfg(PartCfgCreateObjRemote _dto) throws RemoteException;

	public boolean deletePartCfg(String _uid) throws RemoteException;

	public PartCfgRemote loadPartCfg(String _uid) throws RemoteException;

	public PartCfgRemote loadPartCfgById(String _id) throws RemoteException;

	public List<PartCfgRemote> loadPartCfgList(String _rootPartUid) throws RemoteException;
	
	public QueryOperation<PartCfgQueryParam, PartCfgRemote> searchPartCfg(
			QueryOperation<PartCfgQueryParam, PartCfgRemote> _param) throws RemoteException;

	public boolean partCfgStartEditing(String _uid) throws RemoteException;

	public boolean partCfgRevertStartEditing(String _uid) throws RemoteException;

	public boolean partCfgPublish(String _uid, long _publishTime) throws RemoteException;

	public boolean partCfgRevertPublish(String _uid) throws RemoteException;

	// -------------------------------------------------------------------------------
	// ----------------------------------PartCfgConj----------------------------------
	public PartCfgConjRemote createPartCfgConj(String _partCfgUid, String _partAcqUid) throws RemoteException;

	public boolean deletePartCfgConj(String _uid) throws RemoteException;

	public PartCfgConjRemote loadPartCfgConj(String _uid) throws RemoteException;

	public PartCfgConjRemote loadPartCfgConj(String _partCfgUid, String _partAcqUid) throws RemoteException;

	public List<PartCfgConjRemote> loadPartCfgConjList(String _partCfgUid) throws RemoteException;
	
	public List<PartCfgConjRemote> loadPartCfgConjListByPartAcq(String _partAcqUid) throws RemoteException;

	// -------------------------------------------------------------------------------
	// -------------------------------------Prod--------------------------------------
	public ProdRemote createProd(ProdCreateObjRemote _dto) throws RemoteException;

	public boolean deleteProd(String _uid) throws RemoteException;

	public ProdRemote loadProd(String _uid) throws RemoteException;

	public ProdRemote loadProdById(String _id) throws RemoteException;

	public List<ProdRemote> loadProdList() throws RemoteException;

	// -------------------------------------------------------------------------------
	// ------------------------------------ProdCtl------------------------------------
	public ProdCtlRemote createProdCtl(ProdCtlCreateObjRemote _dto) throws RemoteException;

	public boolean deleteProdCtl(String _uid) throws RemoteException;

	public ProdCtlRemote loadProdCtl(String _uid) throws RemoteException;

	public ProdCtlRemote loadProdCtlById(String _id) throws RemoteException;

	public List<ProdCtlRemote> loadProdCtlList(String _parentUid) throws RemoteException;

	public List<ProdCtlRemote> loadProdCtlListLv1(String _prodUid) throws RemoteException;

	public boolean prodCtlAssignParent(String _uid, String _parentUid, String _parentId) throws RemoteException;

	public boolean prodCtlUnassignParent(String _uid) throws RemoteException;

	public boolean prodCtlAssignProd(String _uid, String _prodUid) throws RemoteException;

	public boolean prodCtlUnassignProd(String _uid) throws RemoteException;

	// -------------------------------------------------------------------------------
	// ------------------------------ProdCtlPartCfgConj-------------------------------
	public ProdCtlPartCfgConjRemote createProdCtlPartCfgConj(String _prodCtlUid, String _partCfgUid)
			throws RemoteException;

	public boolean deleteProdCtlPartCfgConj(String _uid) throws RemoteException;

	public ProdCtlPartCfgConjRemote loadProdCtlPartCfgConj(String _uid) throws RemoteException;

	public ProdCtlPartCfgConjRemote loadProdCtlPartCfgConj(String _prodCtlUid, String _partCfgUid)
			throws RemoteException;

	public List<ProdCtlPartCfgConjRemote> loadProdCtlPartCfgConjList1(String _prodCtlUid) throws RemoteException;

	public List<ProdCtlPartCfgConjRemote> loadProdCtlPartCfgConjList2(String _partCfgUid) throws RemoteException;

	// -------------------------------------------------------------------------------
	// ------------------------------------ProdMod------------------------------------
	public ProdModRemote createProdMod(ProdModCreateObjRemote _dto) throws RemoteException;

	public boolean deleteProdMod(String _uid) throws RemoteException;

	public ProdModRemote loadProdMod(String _uid) throws RemoteException;
	
	public ProdModRemote loadProdModById(String _id) throws RemoteException;

	public List<ProdModRemote> loadProdModList(String _prodUid) throws RemoteException;

	// -------------------------------------------------------------------------------
	// ----------------------------------ProdModItem----------------------------------
	public ProdModItemRemote createProdModItem(String prodModUid, String prodCtlUid) throws RemoteException;

	public boolean deleteProdModItem(String _uid) throws RemoteException;

	public ProdModItemRemote loadProdModItem(String _uid) throws RemoteException;
	public ProdModItemRemote loadProdModItem(String _prodModUid, String _prodCtlUid) throws RemoteException;
	public ProdModItemRemote loadProdModItem(String _prodModUid, String _prodCtlUid, String _partCfgUid) throws RemoteException;

	public List<ProdModItemRemote> loadProdModItemList(String _prodModUid) throws RemoteException;

	public boolean prodModItemAssignPartCfg(String _uid, String _partCfgUid) throws RemoteException;

	public boolean prodModItemUnassignPartCfg(String _uid) throws RemoteException;

}
