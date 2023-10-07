package ekp.serviceFacade.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.DebugLogMark;
import ekp.data.service.invt.query.InvtOrderItemQueryParam;
import ekp.data.service.invt.query.InvtOrderQueryParam;
import ekp.data.service.invt.query.MaterialMasterQueryParam;
import ekp.data.service.invt.query.MbsbStmtQueryParam;
import ekp.data.service.mbom.query.PartAcquisitionQueryParam;
import ekp.data.service.mbom.query.PartCfgQueryParam;
import ekp.data.service.mbom.query.PartQueryParam;
import ekp.data.service.mbom.query.PpartSkewerQueryParam;
import ekp.data.service.mf.query.WorkorderQueryParam;
import ekp.data.service.pu.query.PurchQueryParam;
import ekp.data.service.sd.query.SalesOrderItemQueryParam;
import ekp.data.service.sd.query.SalesOrderQueryParam;
import ekp.invt.InvtOrder;
import ekp.invt.InvtOrderItem;
import ekp.invt.InvtService;
import ekp.invt.MaterialBinStock;
import ekp.invt.MaterialBinStockBatch;
import ekp.invt.MaterialInst;
import ekp.invt.MaterialInstSrcConj;
import ekp.invt.MaterialMaster;
import ekp.invt.MbsbStmt;
import ekp.invt.WrhsBin;
import ekp.invt.WrhsLoc;
import ekp.mbom.MbomService;
import ekp.mbom.ParsPart;
import ekp.mbom.ParsProc;
import ekp.mbom.Part;
import ekp.mbom.PartAcqRoutingStep;
import ekp.mbom.PartAcquisition;
import ekp.mbom.PartCfg;
import ekp.mbom.PartCfgConj;
import ekp.mbom.Prod;
import ekp.mbom.ProdCtl;
import ekp.mbom.ProdCtlPartCfgConj;
import ekp.mbom.ProdMod;
import ekp.mbom.ProdModItem;
import ekp.mbom.dto.PpartSkewer;
import ekp.mbom.type.PartAcquisitionType;
import ekp.mbom.type.PartUnit;
import ekp.mf.MfService;
import ekp.mf.Workorder;
import ekp.mf.WorkorderMaterial;
import ekp.pu.PuService;
import ekp.pu.Purch;
import ekp.pu.PurchItem;
import ekp.sd.SalesOrder;
import ekp.sd.SalesOrderItem;
import ekp.sd.SdService;
import ekp.serviceFacade.rmi.invt.InvtFO;
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
import ekp.serviceFacade.rmi.invt.MaterialInstSrcConjCreateObjRemote;
import ekp.serviceFacade.rmi.invt.MaterialInstSrcConjRemote;
import ekp.serviceFacade.rmi.invt.MaterialMasterCreateObjRemote;
import ekp.serviceFacade.rmi.invt.MaterialMasterRemote;
import ekp.serviceFacade.rmi.invt.MbsbStmtCreateObjRemote;
import ekp.serviceFacade.rmi.invt.MbsbStmtRemote;
import ekp.serviceFacade.rmi.invt.WrhsBinCreateObjRemote;
import ekp.serviceFacade.rmi.invt.WrhsBinRemote;
import ekp.serviceFacade.rmi.invt.WrhsLocCreateObjRemote;
import ekp.serviceFacade.rmi.invt.WrhsLocRemote;
import ekp.serviceFacade.rmi.mbom.MbomFO;
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
import ekp.serviceFacade.rmi.mf.MfFO;
import ekp.serviceFacade.rmi.mf.WorkorderCreateObjRemote;
import ekp.serviceFacade.rmi.mf.WorkorderMaterialCreateObjRemote;
import ekp.serviceFacade.rmi.mf.WorkorderMaterialRemote;
import ekp.serviceFacade.rmi.mf.WorkorderRemote;
import ekp.serviceFacade.rmi.pu.PuFO;
import ekp.serviceFacade.rmi.pu.PurchCreateObjRemote;
import ekp.serviceFacade.rmi.pu.PurchItemCreateObjRemote;
import ekp.serviceFacade.rmi.pu.PurchItemRemote;
import ekp.serviceFacade.rmi.pu.PurchRemote;
import ekp.serviceFacade.rmi.sd.SalesOrderCreateObjRemote;
import ekp.serviceFacade.rmi.sd.SalesOrderItemCreateObjRemote;
import ekp.serviceFacade.rmi.sd.SalesOrderItemRemote;
import ekp.serviceFacade.rmi.sd.SalesOrderRemote;
import ekp.serviceFacade.rmi.sd.SdFO;
import legion.BusinessServiceFactory;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public class EkpKernelServiceRemoteImp extends UnicastRemoteObject implements EkpKernelServiceRemote {

	private static Logger log = LoggerFactory.getLogger(EkpKernelServiceRemoteImp.class);

	// -------------------------------------------------------------------------------
	private InvtService invtService = BusinessServiceFactory.getInstance().getService(InvtService.class);
	private MbomService mbomService = BusinessServiceFactory.getInstance().getService(MbomService.class);
	private MfService mfService = BusinessServiceFactory.getInstance().getService(MfService.class);
	private PuService puService =  BusinessServiceFactory.getInstance().getService(PuService.class);
	private SdService sdService =  BusinessServiceFactory.getInstance().getService(SdService.class);

	// -------------------------------------------------------------------------------
	public EkpKernelServiceRemoteImp(int port) throws Exception {
		super(port);
		// TODO Auto-generated constructor stub
	}

	// -------------------------------------------------------------------------------
	@Override
	public boolean testCallBack() throws RemoteException {
		return true;
	}

	// -------------------------------------------------------------------------------
	// -------------------------------------INVT--------------------------------------

	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsLoc------------------------------------
	@Override
	public WrhsLocRemote createWrhsLoc(WrhsLocCreateObjRemote _dto) throws RemoteException {
		return InvtFO.parseWrhsLocRemote(invtService.createWrhsLoc(InvtFO.parseWrhsLocCreateObj(_dto)));
	}

	@Override
	public boolean deleteWrhsLoc(String _uid) throws RemoteException {
		return invtService.deleteWrhsLoc(_uid);
	}

	@Override
	public WrhsLocRemote loadWrhsLoc(String _uid) throws RemoteException {
		WrhsLoc obj = invtService.loadWrhsLoc(_uid);
		return obj == null ? null : InvtFO.parseWrhsLocRemote(obj);
	}

	@Override
	public WrhsLocRemote loadWrhsLocById(String _id) throws RemoteException {
		WrhsLoc obj = invtService.loadWrhsLocById(_id);
		return obj == null ? null : InvtFO.parseWrhsLocRemote(obj);
	}

	@Override
	public List<WrhsLocRemote> loadWrhsLocList() throws RemoteException {
		List<WrhsLoc> list = invtService.loadWrhsLocList();
		List<WrhsLocRemote> remoteList = list.stream().map(InvtFO::parseWrhsLocRemote).collect(Collectors.toList());
		return remoteList;
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsBin------------------------------------
	@Override
	public WrhsBinRemote createWrhsBin(WrhsBinCreateObjRemote _dto) throws RemoteException {
		return InvtFO.parseWrhsBinRemote(invtService.createWrhsBin(InvtFO.parseWrhsBinCreateObj(_dto)));
	}

	@Override
	public boolean deleteWrhsBin(String _uid) throws RemoteException {
		return invtService.deleteWrhsBin(_uid);
	}

	@Override
	public WrhsBinRemote loadWrhsBin(String _uid) throws RemoteException {
		WrhsBin obj = invtService.loadWrhsBin(_uid);
		return obj == null ? null : InvtFO.parseWrhsBinRemote(obj);
	}

	@Override
	public WrhsBinRemote loadWrhsBin(String _wlUid, String _id) throws RemoteException{
		WrhsBin obj = invtService.loadWrhsBin(_wlUid,_id );
		return obj == null ? null : InvtFO.parseWrhsBinRemote(obj);
	}

	@Override
	public List<WrhsBinRemote> loadWrhsBinList(String _wlUid) throws RemoteException {
		List<WrhsBin> list = invtService.loadWrhsBinList(_wlUid);
		List<WrhsBinRemote> remoteList = list.stream().map(InvtFO::parseWrhsBinRemote).collect(Collectors.toList());
		return remoteList;
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------InvtOrder-----------------------------------
	@Override
	public InvtOrderRemote createInvtOrder(InvtOrderCreateObjRemote _dto) throws RemoteException {
		return InvtFO.parseInvtOrderRemote(invtService.createInvtOrder(InvtFO.parseInvtOrderCreateObj(_dto)));
	}

	@Override
	public boolean deleteInvtOrder(String _uid) throws RemoteException {
		return invtService.deleteInvtOrder(_uid);
	}

	@Override
	public InvtOrderRemote loadInvtOrder(String _uid) throws RemoteException {
		InvtOrder obj = invtService.loadInvtOrder(_uid);
		return obj == null ? null : InvtFO.parseInvtOrderRemote(obj);
	}

	@Override
	public InvtOrderRemote loadInvtOrderByIosn(String _iosn) throws RemoteException {
		InvtOrder obj = invtService.loadInvtOrderByIosn(_iosn);
		return obj == null ? null : InvtFO.parseInvtOrderRemote(obj);
	}

	@Override
	public QueryOperation<InvtOrderQueryParam, InvtOrderRemote> searchInvtOrder(
			QueryOperation<InvtOrderQueryParam, InvtOrderRemote> _param,
			Map<InvtOrderQueryParam, QueryValue[]> _existsDetailMap) throws RemoteException {
		QueryOperation<InvtOrderQueryParam, InvtOrder> param = (QueryOperation<InvtOrderQueryParam, InvtOrder>) _param
				.copy();
		param = invtService.searchInvtOrder(param, _existsDetailMap);
		_param.setQueryResult(
				param.getQueryResult().stream().map(InvtFO::parseInvtOrderRemote).collect(Collectors.toList()));
		_param.setTotal(param.getTotal());
		return _param;
	}
	@Override
	public boolean invtOrderToApv(String _uid) throws RemoteException{
		return invtService.invtOrderToApv(_uid);
	}
	@Override
	public boolean invtOrderRevertToApv(String _uid) throws RemoteException{
		return invtService.invtOrderRevertToApv(_uid);
	}
	@Override
	public boolean invtOrderApprove(String _uid, long _apvTime) throws RemoteException{
		return invtService.invtOrderApprove(_uid, _apvTime);
	}
	@Override
	public boolean invtOrderRevertApprove(String _uid) throws RemoteException{
		return invtService.invtOrderRevertApprove(_uid);
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------InvtOrderItem---------------------------------
	@Override
	public InvtOrderItemRemote createInvtOrderItem(InvtOrderItemCreateObjRemote _dto) throws RemoteException{
		return InvtFO.parseInvtOrderItemRemote(invtService.createInvtOrderItem(InvtFO.parseInvtOrderItemCreateObj(_dto)));
	}

	@Override
	public boolean deleteInvtOrderItem(String _uid) throws RemoteException{
		return invtService.deleteInvtOrderItem(_uid);
	}

	@Override
	public InvtOrderItemRemote loadInvtOrderItem(String _uid) throws RemoteException{
		InvtOrderItem obj = invtService.loadInvtOrderItem(_uid);
		return obj == null ? null : InvtFO.parseInvtOrderItemRemote(obj);
	}

	@Override
	public List<InvtOrderItemRemote> loadInvtOrderItemList(String _ioUid) throws RemoteException{
		List<InvtOrderItem> list = invtService.loadInvtOrderItemList(_ioUid);
		List<InvtOrderItemRemote> remoteList = list.stream().map(InvtFO::parseInvtOrderItemRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public List<InvtOrderItemRemote> loadInvtOrderItemListByMm(String _mmUid) throws RemoteException {
		List<InvtOrderItem> list = invtService.loadInvtOrderItemListByMm(_mmUid);
		List<InvtOrderItemRemote> remoteList = list.stream().map(InvtFO::parseInvtOrderItemRemote)
				.collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public QueryOperation<InvtOrderItemQueryParam, InvtOrderItemRemote> searchInvtOrderItem(
			QueryOperation<InvtOrderItemQueryParam, InvtOrderItemRemote> _param,
			Map<InvtOrderItemQueryParam, QueryValue[]> _existsDetailMap) throws RemoteException{
		QueryOperation<InvtOrderItemQueryParam, InvtOrderItem> param = (QueryOperation<InvtOrderItemQueryParam, InvtOrderItem>) _param
				.copy();
		param = invtService.searchInvtOrderItem(param, _existsDetailMap);
		_param.setQueryResult(
				param.getQueryResult().stream().map(InvtFO::parseInvtOrderItemRemote).collect(Collectors.toList()));
		_param.setTotal(param.getTotal());
		return _param;
	}
	@Override
	public boolean invtOrderItemMbsbStmtCreated(String _uid)throws RemoteException{
		return invtService.invtOrderItemMbsbStmtCreated(_uid);
	}
	@Override
	public boolean invtOrderItemRevertMbsbStmtCreated(String _uid)throws RemoteException{
		return invtService.invtOrderItemRevertMbsbStmtCreated(_uid);
	}


	// -------------------------------------------------------------------------------
	// --------------------------------MaterialMaster---------------------------------
	@Override
	public MaterialMasterRemote createMaterialMaster(MaterialMasterCreateObjRemote _dto) throws RemoteException{
		return InvtFO.parseMaterialMasterRemote(invtService.createMaterialMaster(InvtFO.parseMaterialMasterCreateObj(_dto)));
	}

	@Override
	public boolean deleteMaterialMaster(String _uid) throws RemoteException{
		return invtService.deleteMaterialMaster(_uid);
	}

	@Override
	public MaterialMasterRemote loadMaterialMaster(String _uid) throws RemoteException{
		MaterialMaster obj = invtService.loadMaterialMaster(_uid);
		return obj == null ? null : InvtFO.parseMaterialMasterRemote(obj);
	}

	@Override
	public MaterialMasterRemote loadMaterialMasterByMano(String _mano) throws RemoteException{
		MaterialMaster obj = invtService.loadMaterialMasterByMano(_mano);
		return obj == null ? null : InvtFO.parseMaterialMasterRemote(obj);
	}

	@Override
	public QueryOperation<MaterialMasterQueryParam, MaterialMasterRemote> searchMaterialMaster(
			QueryOperation<MaterialMasterQueryParam, MaterialMasterRemote> _param) throws RemoteException {
		QueryOperation<MaterialMasterQueryParam, MaterialMaster> param = (QueryOperation<MaterialMasterQueryParam, MaterialMaster>) _param
				.copy();
		param = invtService.searchMaterialMaster(param);
		_param.setQueryResult(
				param.getQueryResult().stream().map(InvtFO::parseMaterialMasterRemote).collect(Collectors.toList()));
		_param.setTotal(param.getTotal());
		return _param;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------MaterialInst----------------------------------
	@Override
	public MaterialInstRemote createMaterialInst(MaterialInstCreateObjRemote _dto) throws RemoteException{
		return InvtFO.parseMaterialInstRemote(invtService.createMaterialInst(InvtFO.parseMaterialInstCreateObj(_dto)));
	}

	@Override
	public boolean deleteMaterialInst(String _uid) throws RemoteException{
		return invtService.deleteMaterialInst(_uid);
	}

	@Override
	public MaterialInstRemote loadMaterialInst(String _uid) throws RemoteException{
		MaterialInst obj = invtService.loadMaterialInst(_uid);
		return obj == null ? null : InvtFO.parseMaterialInstRemote(obj);
	}

	@Override
	public MaterialInstRemote loadMaterialInstByMisn(String _misn) throws RemoteException{
		MaterialInst obj = invtService.loadMaterialInstByMisn(_misn);
		return obj == null ? null : InvtFO.parseMaterialInstRemote(obj);
	}

	@Override
	public MaterialInstRemote loadMaterialInstByMiacSrcNo(String _miacSrcNo) throws RemoteException{
		MaterialInst obj = invtService.loadMaterialInstByMiacSrcNo(_miacSrcNo);
		return obj == null ? null : InvtFO.parseMaterialInstRemote(obj);
	}

	@Override
	public List<MaterialInstRemote> loadMaterialInstList(String _mmUid) throws RemoteException{
		List<MaterialInst> list = invtService.loadMaterialInstList(_mmUid);
		List<MaterialInstRemote> remoteList = list.stream().map(InvtFO::parseMaterialInstRemote).collect(Collectors.toList());
		return remoteList;
	}
	@Override
	public boolean materialInstToAssignSrcMi(String _uid)throws RemoteException{
		return invtService.materialInstToAssignSrcMi(_uid);
	}

	@Override
	public boolean materialInstRevertToAssignSrcMi(String _uid) throws RemoteException {
		return invtService.materialInstRevertToAssignSrcMi(_uid);
	}

	@Override
	public boolean materialInstFinishAssignedSrcMi(String _uid) throws RemoteException {
		return invtService.materialInstFinishAssignedSrcMi(_uid);
	}

	@Override
	public boolean materialInstRevertFinishAssignedSrcMi(String _uid) throws RemoteException {
		return invtService.materialInstRevertFinishAssignedSrcMi(_uid);
	}

	@Override
	public boolean materialInstNotAssignSrcMi(String _uid) throws RemoteException {
		return invtService.materialInstNotAssignSrcMi(_uid);
	}

	@Override
	public boolean materialInstRevertNotAssignSrcMi(String _uid) throws RemoteException {
		return invtService.materialInstRevertNotAssignSrcMi(_uid);
	}

	// -------------------------------------------------------------------------------
	// ------------------------------MaterialInstSrcConj------------------------------
	@Override
	public MaterialInstSrcConjRemote createMaterialInstSrcConj(MaterialInstSrcConjCreateObjRemote _dto)throws RemoteException{
		if (_dto == null)
			return null;
		MaterialInstSrcConj obj = invtService.createMaterialInstSrcConj(InvtFO.parseMaterialInstSrcConjCreateObj(_dto));
		return obj == null ? null : InvtFO.parseMaterialInstSrcConjRemote(obj);
	}
	@Override
	public boolean deleteMaterialInstSrcConj(String _uid)throws RemoteException{
		return invtService.deleteMaterialInstSrcConj(_uid);
	}
	@Override
	public MaterialInstSrcConjRemote loadMaterialInstSrcConj(String _uid)throws RemoteException{
		MaterialInstSrcConj obj = invtService.loadMaterialInstSrcConj(_uid);
		return obj == null ? null : InvtFO.parseMaterialInstSrcConjRemote(obj);
	}
	@Override
	public List<MaterialInstSrcConjRemote> loadMaterialInstSrcConjList(String _miUid)throws RemoteException{
		List<MaterialInstSrcConj> list = invtService.loadMaterialInstSrcConjList(_miUid);
		List<MaterialInstSrcConjRemote> remoteList = list.stream().map(InvtFO::parseMaterialInstSrcConjRemote).collect(Collectors.toList());
		return remoteList;
	}
	@Override
	public List<MaterialInstSrcConjRemote> loadMaterialInstSrcConjListBySrcMi(String _srcMiUid)throws RemoteException{
		List<MaterialInstSrcConj> list = invtService.loadMaterialInstSrcConjListBySrcMi(_srcMiUid);
		List<MaterialInstSrcConjRemote> remoteList = list.stream().map(InvtFO::parseMaterialInstSrcConjRemote).collect(Collectors.toList());
		return remoteList;
	}

	// -------------------------------------------------------------------------------
	// -------------------------------MaterialBinStock--------------------------------
	@Override
	public MaterialBinStockRemote createMaterialBinStock(MaterialBinStockCreateObjRemote _dto) throws RemoteException{
		return InvtFO.parseMaterialBinStockRemote(invtService.createMaterialBinStock(InvtFO.parseMaterialBinStockCreateObj(_dto)));
	}

	@Override
	public boolean deleteMaterialBinStock(String _uid) throws RemoteException{
		return invtService.deleteMaterialBinStock(_uid);
	}

	@Override
	public MaterialBinStockRemote loadMaterialBinStock(String _uid) throws RemoteException{
		MaterialBinStock obj = invtService.loadMaterialBinStock(_uid);
		return obj == null ? null : InvtFO.parseMaterialBinStockRemote(obj);
	}

	@Override
	public MaterialBinStockRemote loadMaterialBinStock(String _mmUid, String _wrhsBinUid) throws RemoteException{
		MaterialBinStock obj = invtService.loadMaterialBinStock(_mmUid, _wrhsBinUid);
		return obj == null ? null : InvtFO.parseMaterialBinStockRemote(obj);
	}

	@Override
	public List<MaterialBinStockRemote> loadMaterialBinStockList(String _mmUid) throws RemoteException{
		List<MaterialBinStock> list = invtService.loadMaterialBinStockList(_mmUid);
		List<MaterialBinStockRemote> remoteList = list.stream().map(InvtFO::parseMaterialBinStockRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public List<MaterialBinStockRemote> loadMaterialBinStockListByWrhsBin(String _wbUid) throws RemoteException{
		List<MaterialBinStock> list = invtService.loadMaterialBinStockListByWrhsBin(_wbUid);
		List<MaterialBinStockRemote> remoteList = list.stream().map(InvtFO::parseMaterialBinStockRemote).collect(Collectors.toList());
		return remoteList;
	}

	// -------------------------------------------------------------------------------
	// -----------------------------MaterialBinStockBatch-----------------------------
	@Override
	public MaterialBinStockBatchRemote createMaterialBinStockBatch(MaterialBinStockBatchCreateObjRemote _dto)
			throws RemoteException{
		return InvtFO.parseMaterialBinStockBatchRemote(invtService.createMaterialBinStockBatch(InvtFO.parseMaterialBinStockBatchCreateObj(_dto)));
	}

	@Override
	public boolean deleteMaterialBinStockBatch(String _uid) throws RemoteException{
		return invtService.deleteMaterialBinStockBatch(_uid);
	}

	@Override
	public MaterialBinStockBatchRemote loadMaterialBinStockBatch(String _uid) throws RemoteException{
		MaterialBinStockBatch obj = invtService.loadMaterialBinStockBatch(_uid);
		return obj == null ? null : InvtFO.parseMaterialBinStockBatchRemote(obj);
	}

	@Override
	public MaterialBinStockBatchRemote loadMaterialBinStockBatch(String _mbsUid, String _miUid) throws RemoteException{
		MaterialBinStockBatch obj = invtService.loadMaterialBinStockBatch(_mbsUid, _miUid);
		return obj == null ? null : InvtFO.parseMaterialBinStockBatchRemote(obj);
	}

	@Override
	public List<MaterialBinStockBatchRemote> loadMaterialBinStockBatchList(String _mbsUid) throws RemoteException{
		List<MaterialBinStockBatch> list = invtService.loadMaterialBinStockBatchList(_mbsUid);
		List<MaterialBinStockBatchRemote> remoteList = list.stream().map(InvtFO::parseMaterialBinStockBatchRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public List<MaterialBinStockBatchRemote> loadMaterialBinStockBatchListByMi(String _miUid) throws RemoteException{
		List<MaterialBinStockBatch> list = invtService.loadMaterialBinStockBatchListByMi(_miUid);
		List<MaterialBinStockBatchRemote> remoteList = list.stream().map(InvtFO::parseMaterialBinStockBatchRemote).collect(Collectors.toList());
		return remoteList;
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------MbsbStmt------------------------------------
	@Override
	public MbsbStmtRemote createMbsbStmt(MbsbStmtCreateObjRemote _dto) throws RemoteException{
		return InvtFO.parseMbsbStmtRemote(invtService.createMbsbStmt(InvtFO.parseMbsbStmtCreateObj(_dto)));
	}

	@Override
	public boolean deleteMbsbStmt(String _uid) throws RemoteException{
		return invtService.deleteMbsbStmt(_uid);
	}

	@Override
	public MbsbStmtRemote loadMbsbStmt(String _uid) throws RemoteException{
		MbsbStmt obj = invtService.loadMbsbStmt(_uid);
		return obj == null ? null : InvtFO.parseMbsbStmtRemote(obj);
	}

	@Override
	public List<MbsbStmtRemote> loadMbsbStmtList(String _mbsbUid) throws RemoteException{
		List<MbsbStmt> list = invtService.loadMbsbStmtList(_mbsbUid);
		List<MbsbStmtRemote> remoteList = list.stream().map(InvtFO::parseMbsbStmtRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public List<MbsbStmtRemote> loadMbsbStmtListByIoi(String _ioiUid) throws RemoteException{
		List<MbsbStmt> list = invtService.loadMbsbStmtListByIoi(_ioiUid);
		List<MbsbStmtRemote> remoteList = list.stream().map(InvtFO::parseMbsbStmtRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public QueryOperation<MbsbStmtQueryParam, MbsbStmtRemote> searchMbsbStmt(
			QueryOperation<MbsbStmtQueryParam, MbsbStmtRemote> _param) throws RemoteException{
		QueryOperation<MbsbStmtQueryParam, MbsbStmt> param = (QueryOperation<MbsbStmtQueryParam, MbsbStmt>) _param
				.copy();
		param = invtService.searchMbsbStmt(param);
		_param.setQueryResult(
				param.getQueryResult().stream().map(InvtFO::parseMbsbStmtRemote).collect(Collectors.toList()));
		_param.setTotal(param.getTotal());
		return _param;
	}

	@Override
	public boolean mbsbStmtPost(String _uid) throws RemoteException{
		return invtService.mbsbStmtPost(_uid);
	}

	@Override
	public boolean mbsbStmtRevertPost(String _uid) throws RemoteException{
		return invtService.mbsbStmtRevertPost(_uid);
	}

	// -------------------------------------MBOM--------------------------------------
	// -------------------------------------------------------------------------------
	// -------------------------------------Part--------------------------------------
	@Override
	public PartRemote createPart(PartCreateObjRemote _dto) throws RemoteException {
		return MbomFO.parsePartRemote(mbomService.createPart(MbomFO.parsePartCreateObj(_dto)));
	}

	@Override
	public boolean deletePart(String _uid) throws RemoteException {
		return mbomService.deletePart(_uid);
	}

	@Override
	public PartRemote loadPart(String _uid) throws RemoteException {
		Part obj = mbomService.loadPart(_uid);
		return obj == null ? null : MbomFO.parsePartRemote(obj);
	}

	@Override
	public PartRemote loadPartByPin(String _pin) throws RemoteException {
		Part obj = mbomService.loadPartByPin(_pin);
		return obj == null ? null : MbomFO.parsePartRemote(obj);
	}

	@Override
	public QueryOperation<PartQueryParam, PartRemote> searchPart(QueryOperation<PartQueryParam, PartRemote> _param)
			throws RemoteException {
		QueryOperation<PartQueryParam, Part> param = (QueryOperation<PartQueryParam, Part>) _param.copy();
		param = mbomService.searchPart(param);
		_param.setQueryResult(
				param.getQueryResult().stream().map(MbomFO::parsePartRemote).collect(Collectors.toList()));
		_param.setTotal(param.getTotal());
		return _param;
	}

	@Override
	public boolean partUpdate(String _uid, String _pin, String _name, PartUnit _unit) throws RemoteException {
		return mbomService.partUpdate(_uid, _pin, _name, _unit);
	}

	// -------------------------------------------------------------------------------
	// --------------------------------PartAcquisition--------------------------------
	@Override
	public PartAcquisitionRemote createPartAcquisition(PartAcquisitionCreateObjRemote _dto) throws RemoteException {
		return MbomFO.parsePartAcquisitionRemote(
				mbomService.createPartAcquisition(MbomFO.parsePartAcquisitionCreateObj(_dto)));
	}

	@Override
	public boolean deletePartAcquisition(String _uid) throws RemoteException {
		return mbomService.deletePartAcquisition(_uid);
	}

	@Override
	public PartAcquisitionRemote loadPartAcquisition(String _uid) throws RemoteException {
		PartAcquisition obj = mbomService.loadPartAcquisition(_uid);
		return obj == null ? null : MbomFO.parsePartAcquisitionRemote(obj);
	}

	@Override
	public PartAcquisitionRemote loadPartAcquisition(String _partPin, String _id) throws RemoteException {
		PartAcquisition obj = mbomService.loadPartAcquisition(_partPin, _id);
		return obj == null ? null : MbomFO.parsePartAcquisitionRemote(obj);
	}

	@Override
	public List<PartAcquisitionRemote> loadPartAcquisitionList(String _partUid) throws RemoteException {
		List<PartAcquisition> list = mbomService.loadPartAcquisitionList(_partUid);
		List<PartAcquisitionRemote> remoteList = list.stream().map(MbomFO::parsePartAcquisitionRemote)
				.collect(Collectors.toList());
		return remoteList;
	}
	
	@Override
	public QueryOperation<PartAcquisitionQueryParam, PartAcquisitionRemote> searchPartAcquisition(
			QueryOperation<PartAcquisitionQueryParam, PartAcquisitionRemote> _param) throws RemoteException{
		QueryOperation<PartAcquisitionQueryParam, PartAcquisition> param = (QueryOperation<PartAcquisitionQueryParam, PartAcquisition>) _param.copy();
		param = mbomService.searchPartAcquisition(param);
		_param.setQueryResult(
				param.getQueryResult().stream().map(MbomFO::parsePartAcquisitionRemote).collect(Collectors.toList()));
		_param.setTotal(param.getTotal());
		return _param;
	}

	@Override
	public boolean partAcqStartEditing(String _uid) throws RemoteException {
		return mbomService.partAcqStartEditing(_uid);
	}

	@Override
	public boolean partAcqRevertStartEditing(String _uid) throws RemoteException {
		return mbomService.partAcqRevertStartEditing(_uid);
	}
	
	@Override
	public boolean partAcqAssignMm(String _uid, String _mmUid, String _mmMano) throws RemoteException{
		return mbomService.partAcqAssignMm(_uid, _mmUid, _mmMano);
	}
	@Override
	public boolean partAcqRevertAssignMm(String _uid) throws RemoteException{
		return mbomService.partAcqRevertAssignMm(_uid);
	}
	
	@Override
	public boolean partAcqPublish(String _uid, long _publishTime) throws RemoteException {
		return mbomService.partAcqPublish(_uid, _publishTime);
	}

	@Override
	public boolean partAcqRevertPublish(String _uid) throws RemoteException {
		return mbomService.partAcqRevertPublish(_uid);
	}

	@Override
	public boolean partAcqUpdateInfo(String _uid, String _id, String _name, PartAcquisitionType _type) {
		return mbomService.partAcqUpdateInfo(_uid, _id, _name, _type);
	}

	@Override
	public boolean partAcqUpdateRefUnitCost(String _uid, double _refUnitCost) throws RemoteException {
		return mbomService.partAcqUpdateRefUnitCost(_uid, _refUnitCost);
	}

	// -------------------------------------------------------------------------------
	// ------------------------------PartAcqRoutingStep-------------------------------
	@Override
	public PartAcqRoutingStepRemote createPartAcqRoutingStep(PartAcqRoutingStepCreateObjRemote _dto)
			throws RemoteException {
		return MbomFO.parsePartAcqRoutingStepRemote(
				mbomService.createPartAcqRoutingStep(MbomFO.parsePartAcqRoutingStepCreateObj(_dto)));
	}

	@Override
	public boolean deletePartAcqRoutingStep(String _uid) throws RemoteException {
		return mbomService.deletePartAcqRoutingStep(_uid);
	}

	@Override
	public PartAcqRoutingStepRemote loadPartAcqRoutingStep(String _uid) throws RemoteException {
		PartAcqRoutingStep obj = mbomService.loadPartAcqRoutingStep(_uid);
		return obj == null ? null : MbomFO.parsePartAcqRoutingStepRemote(obj);
	}

	@Override
	public PartAcqRoutingStepRemote loadPartAcqRoutingStep(String _partAcqUid, String _seq) throws RemoteException {
		PartAcqRoutingStep obj = mbomService.loadPartAcqRoutingStep(_partAcqUid, _seq);
		return obj == null ? null : MbomFO.parsePartAcqRoutingStepRemote(obj);
	}

	@Override
	public List<PartAcqRoutingStepRemote> loadPartAcqRoutingStepList(String _partAcqUid) throws RemoteException {
		List<PartAcqRoutingStep> list = mbomService.loadPartAcqRoutingStepList(_partAcqUid);
		List<PartAcqRoutingStepRemote> remoteList = list.stream().map(MbomFO::parsePartAcqRoutingStepRemote)
				.collect(Collectors.toList());
		return remoteList;
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsProc------------------------------------
	@Override
	public ParsProcRemote createParsProc(ParsProcCreateObjRemote _dto) throws RemoteException {
		return MbomFO.parseParsProcRemote(mbomService.createParsProc(MbomFO.parseParsProcCreateObj(_dto)));
	}

	@Override
	public boolean deleteParsProc(String _uid) throws RemoteException {
		return mbomService.deleteParsProc(_uid);
	}

	@Override
	public ParsProcRemote loadParsProc(String _uid) throws RemoteException {
		ParsProc obj = mbomService.loadParsProc(_uid);
		return obj == null ? null : MbomFO.parseParsProcRemote(obj);
	}

	@Override
	public List<ParsProcRemote> loadParsProcList(String _parsUid) throws RemoteException {
		List<ParsProc> list = mbomService.loadParsProcList(_parsUid);
		List<ParsProcRemote> remoteList = list.stream().map(MbomFO::parseParsProcRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public List<ParsProcRemote> loadParsProcListByProc(String _procUid) throws RemoteException {
		List<ParsProc> list = mbomService.loadParsProcListByProc(_procUid);
		List<ParsProcRemote> remoteList = list.stream().map(MbomFO::parseParsProcRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public boolean parsProcAssignProc(String _uid, String _procUid, String _procId) throws RemoteException {
		return mbomService.parsProcAssignProc(_uid, _procUid, _procId);
	}

	@Override
	public boolean parsProcRevertAssignProc(String _uid) throws RemoteException {
		return mbomService.parsProcRevertAssignProc(_uid);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsPart------------------------------------
	@Override
	public ParsPartRemote createParsPart(String _parsUid) throws RemoteException {
		return MbomFO.parseParsPartRemote(mbomService.createParsPart(_parsUid));
	}

	@Override
	public boolean deleteParsPart(String _uid) throws RemoteException {
		return mbomService.deleteParsPart(_uid);
	}

	@Override
	public ParsPartRemote loadParsPart(String _uid) throws RemoteException {
		ParsPart obj = mbomService.loadParsPart(_uid);
		return obj == null ? null : MbomFO.parseParsPartRemote(obj);
	}

	@Override
	public ParsPartRemote loadParsPart(String _parsUid, String _partuid) throws RemoteException {
		ParsPart obj = mbomService.loadParsPart(_parsUid, _partuid);
		return obj == null ? null : MbomFO.parseParsPartRemote(obj);
	}

	@Override
	public List<ParsPartRemote> loadParsPartList(String _parsUid) throws RemoteException {
		List<ParsPart> list = mbomService.loadParsPartList(_parsUid);
		List<ParsPartRemote> remoteList = list.stream().map(MbomFO::parseParsPartRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public List<ParsPartRemote> loadParsPartListByPart(String _partUid) throws RemoteException {
		List<ParsPart> list = mbomService.loadParsPartListByPart(_partUid);
		List<ParsPartRemote> remoteList = list.stream().map(MbomFO::parseParsPartRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public boolean parsPartAssignPart(String _uid, String _partUid, String _partPin, double _partReqQty)
			throws RemoteException {
		return mbomService.parsPartAssignPart(_uid, _partUid, _partPin, _partReqQty);
	}

	@Override
	public boolean parsPartRevertAssignPart(String _uid) throws RemoteException {
		return mbomService.parsPartRevertAssignPart(_uid);
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------PpartSkewer----------------------------------
	@Override
	public PpartSkewerRemote loadPpartSkewer(String _uid) throws RemoteException {
		PpartSkewer obj = mbomService.loadPpartSkewer(_uid);
		return obj == null ? null : MbomFO.parsePpartSkewerRemote(obj);
	}

	@Override
	public QueryOperation<PpartSkewerQueryParam, PpartSkewerRemote> searchPpartSkewer(
			QueryOperation<PpartSkewerQueryParam, PpartSkewerRemote> _param,
			Map<PpartSkewerQueryParam, QueryValue[]> _existsQvMap) throws RemoteException {
		QueryOperation<PpartSkewerQueryParam, PpartSkewer> param = (QueryOperation<PpartSkewerQueryParam, PpartSkewer>) _param
				.copy();
		param = mbomService.searchPpartSkewer(param, _existsQvMap);
		log.debug("param.getTotal(): {}", param.getTotal());
		log.debug("limit: {}\t{}", param.getLimit()[0], param.getLimit()[1]);
		log.debug("param.getQueryResult().size(): {}", param.getQueryResult().size());
		_param.setQueryResult(
				param.getQueryResult().stream().map(MbomFO::parsePpartSkewerRemote).collect(Collectors.toList()));
		_param.setTotal(param.getTotal());
		return _param;
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------PartCfg------------------------------------
	@Override
	public PartCfgRemote createPartCfg(PartCfgCreateObjRemote _dto) throws RemoteException {
		return MbomFO.parsePartCfgRemote(mbomService.createPartCfg(MbomFO.parsePartCfgCreateObj(_dto)));
	}

	@Override
	public boolean deletePartCfg(String _uid) throws RemoteException {
		return mbomService.deletePartCfg(_uid);
	}

	@Override
	public PartCfgRemote loadPartCfg(String _uid) throws RemoteException {
		PartCfg obj = mbomService.loadPartCfg(_uid);
		return obj == null ? null : MbomFO.parsePartCfgRemote(obj);
	}

	@Override
	public PartCfgRemote loadPartCfgById(String _id) throws RemoteException {
		PartCfg obj = mbomService.loadPartCfgById(_id);
		return obj == null ? null : MbomFO.parsePartCfgRemote(obj);
	}

	@Override
	public List<PartCfgRemote> loadPartCfgList(String _rootPartUid) throws RemoteException {
		List<PartCfg> list = mbomService.loadPartCfgList(_rootPartUid);
		List<PartCfgRemote> remoteList = list.stream().map(MbomFO::parsePartCfgRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public QueryOperation<PartCfgQueryParam, PartCfgRemote> searchPartCfg(
			QueryOperation<PartCfgQueryParam, PartCfgRemote> _param) throws RemoteException {
		QueryOperation<PartCfgQueryParam, PartCfg> param = (QueryOperation<PartCfgQueryParam, PartCfg>) _param.copy();
		param = mbomService.searchPartCfg(param);
		_param.setQueryResult(
				param.getQueryResult().stream().map(MbomFO::parsePartCfgRemote).collect(Collectors.toList()));
		_param.setTotal(param.getTotal());
		return _param;
	}

	@Override
	public boolean partCfgStartEditing(String _uid) throws RemoteException {
		return mbomService.partCfgStartEditing(_uid);
	}

	@Override
	public boolean partCfgRevertStartEditing(String _uid) throws RemoteException {
		return mbomService.partCfgRevertStartEditing(_uid);
	}

	@Override
	public boolean partCfgPublish(String _uid, long _publishTime) throws RemoteException {
		return mbomService.partCfgPublish(_uid, _publishTime);
	}

	@Override
	public boolean partCfgRevertPublish(String _uid) throws RemoteException {
		return mbomService.partCfgRevertPublish(_uid);
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------PartCfgConj----------------------------------
	@Override
	public PartCfgConjRemote createPartCfgConj(String _partCfgUid, String _partAcqUid) throws RemoteException {
		return MbomFO.parsePartCfgConjRemote(mbomService.createPartCfgConj(_partCfgUid, _partAcqUid));
	}

	@Override
	public boolean deletePartCfgConj(String _uid) throws RemoteException {
		return mbomService.deletePartCfgConj(_uid);
	}

	@Override
	public PartCfgConjRemote loadPartCfgConj(String _uid) throws RemoteException {
		PartCfgConj obj = mbomService.loadPartCfgConj(_uid);
		return obj == null ? null : MbomFO.parsePartCfgConjRemote(obj);
	}

	@Override
	public PartCfgConjRemote loadPartCfgConj(String _partCfgUid, String _partAcqUid) throws RemoteException {
		PartCfgConj obj = mbomService.loadPartCfgConj(_partCfgUid, _partAcqUid);
		return obj == null ? null : MbomFO.parsePartCfgConjRemote(obj);
	}

	@Override
	public List<PartCfgConjRemote> loadPartCfgConjList(String _partCfgUid) throws RemoteException {
		List<PartCfgConj> list = mbomService.loadPartCfgConjList(_partCfgUid);
		List<PartCfgConjRemote> remoteList = list.stream().map(MbomFO::parsePartCfgConjRemote)
				.collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public List<PartCfgConjRemote> loadPartCfgConjListByPartAcq(String _partAcqUid) throws RemoteException {
		List<PartCfgConj> list = mbomService.loadPartCfgConjListByPartAcq(_partAcqUid);
		List<PartCfgConjRemote> remoteList = list.stream().map(MbomFO::parsePartCfgConjRemote)
				.collect(Collectors.toList());
		return remoteList;
	}

	// -------------------------------------------------------------------------------
	// -------------------------------------Prod--------------------------------------
	@Override
	public ProdRemote createProd(ProdCreateObjRemote _dto) throws RemoteException {
		return MbomFO.parseProdRemote(mbomService.createProd(MbomFO.parseProdCreateObj(_dto)));
	}

	@Override
	public boolean deleteProd(String _uid) throws RemoteException {
		return mbomService.deleteProd(_uid);
	}

	@Override
	public ProdRemote loadProd(String _uid) throws RemoteException {
		Prod obj = mbomService.loadProd(_uid);
		return obj == null ? null : MbomFO.parseProdRemote(obj);
	}

	@Override
	public ProdRemote loadProdById(String _id) throws RemoteException {
		Prod obj = mbomService.loadProdById(_id);
		return obj == null ? null : MbomFO.parseProdRemote(obj);
	}

	@Override
	public List<ProdRemote> loadProdList() throws RemoteException {
		List<Prod> list = mbomService.loadProdList();
		List<ProdRemote> remoteList = list.stream().map(MbomFO::parseProdRemote).collect(Collectors.toList());
		return remoteList;
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------ProdCtl------------------------------------
	@Override
	public ProdCtlRemote createProdCtl(ProdCtlCreateObjRemote _dto) throws RemoteException {
		return MbomFO.parseProdCtlRemote(mbomService.createProdCtl(MbomFO.parseProdCtlCreateObj(_dto)));
	}

	@Override
	public boolean deleteProdCtl(String _uid) throws RemoteException {
		return mbomService.deleteProdCtl(_uid);
	}

	@Override
	public ProdCtlRemote loadProdCtl(String _uid) throws RemoteException {
		ProdCtl obj = mbomService.loadProdCtl(_uid);
		return obj == null ? null : MbomFO.parseProdCtlRemote(obj);
	}

	@Override
	public List<ProdCtlRemote> loadProdCtlList(String _parentUid) throws RemoteException {
		List<ProdCtl> list = mbomService.loadProdCtlList(_parentUid);
		List<ProdCtlRemote> remoteList = list.stream().map(MbomFO::parseProdCtlRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public List<ProdCtlRemote> loadProdCtlListLv1(String _prodUid) throws RemoteException {
		List<ProdCtl> list = mbomService.loadProdCtlListLv1(_prodUid);
		List<ProdCtlRemote> remoteList = list.stream().map(MbomFO::parseProdCtlRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public boolean prodCtlAssignParent(String _uid, String _parentUid) throws RemoteException {
		return mbomService.prodCtlAssignParent(_uid, _parentUid);
	}

	@Override
	public boolean prodCtlUnassignParent(String _uid) throws RemoteException {
		return mbomService.prodCtlUnassignParent(_uid);
	}

	@Override
	public boolean prodCtlAssignProd(String _uid, String _prodUid) throws RemoteException {
		return mbomService.prodCtlAssignProd(_uid, _prodUid);
	}

	@Override
	public boolean prodCtlUnassignProd(String _uid) throws RemoteException {
		return mbomService.prodCtlUnassignProd(_uid);
	}

	// -------------------------------------------------------------------------------
	// ------------------------------ProdCtlPartCfgConj-------------------------------
	@Override
	public ProdCtlPartCfgConjRemote createProdCtlPartCfgConj(String _prodCtlUid, String _partCfgUid, String _partAcqUid)
			throws RemoteException {
		return MbomFO.parseProdCtlPartCfgConjRemote(mbomService.createProdCtlPartCfgConj(_prodCtlUid, _partCfgUid, _partAcqUid));
	}

	@Override
	public boolean deleteProdCtlPartCfgConj(String _uid) throws RemoteException {
		return mbomService.deleteProdCtlPartCfgConj(_uid);
	}

	@Override
	public ProdCtlPartCfgConjRemote loadProdCtlPartCfgConj(String _uid) throws RemoteException {
		ProdCtlPartCfgConj obj = mbomService.loadProdCtlPartCfgConj(_uid);
		return obj == null ? null : MbomFO.parseProdCtlPartCfgConjRemote(obj);
	}

	@Override
	public ProdCtlPartCfgConjRemote loadProdCtlPartCfgConj(String _prodCtlUid, String _partCfgUid, String _partAcqUid)
			throws RemoteException {
		ProdCtlPartCfgConj obj = mbomService.loadProdCtlPartCfgConj(_prodCtlUid, _partCfgUid, _partAcqUid);
		return obj == null ? null : MbomFO.parseProdCtlPartCfgConjRemote(obj);
	}

	@Override
	public List<ProdCtlPartCfgConjRemote> loadProdCtlPartCfgConjList1(String _prodCtlUid) throws RemoteException {
		List<ProdCtlPartCfgConj> list = mbomService.loadProdCtlPartCfgConjList1(_prodCtlUid);
		List<ProdCtlPartCfgConjRemote> remoteList = list.stream().map(MbomFO::parseProdCtlPartCfgConjRemote)
				.collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public List<ProdCtlPartCfgConjRemote> loadProdCtlPartCfgConjList2(String _partCfgUid) throws RemoteException {
		List<ProdCtlPartCfgConj> list = mbomService.loadProdCtlPartCfgConjList2(_partCfgUid);
		List<ProdCtlPartCfgConjRemote> remoteList = list.stream().map(MbomFO::parseProdCtlPartCfgConjRemote)
				.collect(Collectors.toList());
		return remoteList;
	}
	
	@Override
	public List<ProdCtlPartCfgConjRemote> loadProdCtlPartCfgConjList3(String _partAcqUid) throws RemoteException{
		List<ProdCtlPartCfgConj> list = mbomService.loadProdCtlPartCfgConjList3(_partAcqUid);
		List<ProdCtlPartCfgConjRemote> remoteList = list.stream().map(MbomFO::parseProdCtlPartCfgConjRemote)
				.collect(Collectors.toList());
		return remoteList;
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------ProdMod------------------------------------
	@Override
	public ProdModRemote createProdMod(ProdModCreateObjRemote _dto) throws RemoteException {
		return MbomFO.parseProdModRemote(mbomService.createProdMod(MbomFO.parseProdModCreateObj(_dto)));
	}

	@Override
	public boolean deleteProdMod(String _uid) throws RemoteException {
		return mbomService.deleteProdMod(_uid);
	}

	@Override
	public ProdModRemote loadProdMod(String _uid) throws RemoteException {
		ProdMod obj = mbomService.loadProdMod(_uid);
		return obj == null ? null : MbomFO.parseProdModRemote(obj);
	}

	@Override
	public ProdModRemote loadProdModById(String _id) throws RemoteException {
		ProdMod obj = mbomService.loadProdModById(_id);
		return obj == null ? null : MbomFO.parseProdModRemote(obj);
	}

	@Override
	public List<ProdModRemote> loadProdModList(String _prodUid) throws RemoteException {
		List<ProdMod> list = mbomService.loadProdModList(_prodUid);
		List<ProdModRemote> remoteList = list.stream().map(MbomFO::parseProdModRemote).collect(Collectors.toList());
		return remoteList;
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ProdModItem----------------------------------
	@Override
	public ProdModItemRemote createProdModItem(String prodModUid, String prodCtlUid) throws RemoteException {
		return MbomFO.parseProdModItemRemote(mbomService.createProdModItem(prodModUid, prodCtlUid));
	}

	@Override
	public boolean deleteProdModItem(String _uid) throws RemoteException {
		return mbomService.deleteProdModItem(_uid);
	}

	@Override
	public ProdModItemRemote loadProdModItem(String _uid) throws RemoteException {
		ProdModItem obj = mbomService.loadProdModItem(_uid);
		return obj == null ? null : MbomFO.parseProdModItemRemote(obj);
	}

	@Override
	public ProdModItemRemote loadProdModItem(String _prodModUid, String _prodCtlUid) throws RemoteException {
		ProdModItem obj = mbomService.loadProdModItem(_prodModUid, _prodCtlUid);
		return obj == null ? null : MbomFO.parseProdModItemRemote(obj);
	}

	@Override
	public ProdModItemRemote loadProdModItem(String _prodModUid, String _prodCtlUid, String _partCfgUid, String _partAcqUid)
			throws RemoteException {
		ProdModItem obj = mbomService.loadProdModItem(_prodModUid, _prodCtlUid, _partCfgUid, _partAcqUid);
		return obj == null ? null : MbomFO.parseProdModItemRemote(obj);
	}

	@Override
	public List<ProdModItemRemote> loadProdModItemList(String _prodModUid) throws RemoteException {
		List<ProdModItem> list = mbomService.loadProdModItemList(_prodModUid);
		List<ProdModItemRemote> remoteList = list.stream().map(MbomFO::parseProdModItemRemote)
				.collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public boolean prodModItemAssignPartAcqCfg(String _uid, String _partCfgUid, String _partAcqUid) throws RemoteException {
		return mbomService.prodModItemAssignPartAcqCfg(_uid, _partCfgUid, _partAcqUid);
	}

	@Override
	public boolean prodModItemUnassignPartAcqCfg(String _uid) throws RemoteException {
		return mbomService.prodModItemUnassignPartAcqCfg(_uid);
	}

	// -------------------------------------------------------------------------------
	// --------------------------------------MF---------------------------------------
	// -------------------------------------------------------------------------------
	// -----------------------------------Workorder-----------------------------------
	@Override
	public WorkorderRemote createWorkorder(WorkorderCreateObjRemote _dto) throws RemoteException {
		if (_dto == null)
			return null;
		Workorder obj = mfService.createWorkorder(MfFO.parseWorkorderCreateObj(_dto));
		return obj == null ? null : MfFO.parseWorkorderRemote(obj);
	}

	@Override
	public boolean deleteWorkorder(String _uid) throws RemoteException{
		return mfService.deleteWorkorder(_uid);
	}

	@Override
	public WorkorderRemote loadWorkorder(String _uid) throws RemoteException{
		Workorder obj = mfService.loadWorkorder(_uid);
		return obj == null ? null : MfFO.parseWorkorderRemote(obj);
	}

	@Override
	public QueryOperation<WorkorderQueryParam, WorkorderRemote> searchWorkorder(
			QueryOperation<WorkorderQueryParam, WorkorderRemote> _param,
			Map<WorkorderQueryParam, QueryValue[]> _existsDetailMap) throws RemoteException{
		QueryOperation<WorkorderQueryParam, Workorder> param =(QueryOperation<WorkorderQueryParam, Workorder>) _param.copy();
		param = mfService.searchWorkorder(param, _existsDetailMap);
		_param.setQueryResult(param.getQueryResult().stream().map(MfFO::parseWorkorderRemote).collect(Collectors.toList()));
		_param.setTotal(param.getTotal());
		return _param;
	}

	@Override
	public boolean woToStart(String _uid) throws RemoteException{
		return mfService.woToStart(_uid);
	}

	@Override
	public boolean woRevertToStart(String _uid) throws RemoteException{
		return mfService.woRevertToStart(_uid);
	}

	@Override
	public boolean woStartWork(String _uid, long _startWorkTime) throws RemoteException{
		return mfService.woStartWork(_uid, _startWorkTime);
	}

	@Override
	public boolean woRevertStartWork(String _uid) throws RemoteException{
		return mfService.woRevertStartWork(_uid);
	}

	@Override
	public boolean woFinishWork(String _uid, long _finishWorkTime) throws RemoteException{
		return mfService.woFinishWork(_uid, _finishWorkTime);
	}

	@Override
	public boolean woRevertFinishWork(String _uid) throws RemoteException{
		return mfService.woRevertFinishWork(_uid);
	}

	@Override
	public boolean woOver(String _uid, long _overTime) throws RemoteException{
		return mfService.woOver(_uid, _overTime);
	}

	@Override
	public boolean woRevertOver(String _uid) throws RemoteException{
		return mfService.woRevertOver(_uid);
	}

	// -------------------------------------------------------------------------------
	// -------------------------------WorkorderMaterial-------------------------------
	@Override
	public WorkorderMaterialRemote createWorkorderMaterial(WorkorderMaterialCreateObjRemote _dto)throws RemoteException{
		if (_dto == null)
			return null;
		WorkorderMaterial obj = mfService.createWorkorderMaterial(MfFO.parseWorkorderMaterialCreateObj(_dto));
		return obj == null ? null : MfFO.parseWorkorderMaterialRemote(obj);
	}
	@Override
	public boolean deleteWorkorderMaterial(String _uid)throws RemoteException{
		return mfService.deleteWorkorderMaterial(_uid);
	}
	@Override
	public WorkorderMaterialRemote loadWorkorderMaterial(String _uid)throws RemoteException{
		WorkorderMaterial obj = mfService.loadWorkorderMaterial(_uid);
		return obj == null ? null : MfFO.parseWorkorderMaterialRemote(obj);
	}
	@Override
	public List<WorkorderMaterialRemote> loadWorkorderMaterialList(String _woUid)throws RemoteException{
		List<WorkorderMaterial> list = mfService.loadWorkorderMaterialList(_woUid);
		List<WorkorderMaterialRemote> remoteList = list.stream().map(MfFO::parseWorkorderMaterialRemote).collect(Collectors.toList());
		return remoteList;
	}
	@Override
	public boolean womAddQty0(String _uid, double _addQty)throws RemoteException{
		return mfService.womAddQty0(_uid, _addQty);
	}
	@Override
	public boolean womQty0to1(String _uid, double _qty)throws RemoteException{
		return mfService.womQty0to1(_uid, _qty);
	}

	// -------------------------------------------------------------------------------
	// --------------------------------------PU---------------------------------------
	// -------------------------------------------------------------------------------
	// -------------------------------------Purch-------------------------------------
	@Override
	public PurchRemote createPurch(PurchCreateObjRemote _dto) throws RemoteException {
		if (_dto == null)
			return null;
		Purch obj = puService.createPurch(PuFO.parsePurchCreateObj(_dto));
		return obj == null ? null : PuFO.parsePurchRemote(obj);
	}

	@Override
	public boolean deletePurch(String _uid) throws RemoteException {
		return puService.deletePurch(_uid);
	}

	@Override
	public PurchRemote loadPurch(String _uid) throws RemoteException {
		Purch obj = puService.loadPurch(_uid);
		return obj == null ? null : PuFO.parsePurchRemote(obj);
	}

	@Override
	public PurchRemote loadPurchByPuNo(String _puNo) throws RemoteException {
		Purch obj = puService.loadPurchByPuNo(_puNo);
		return obj == null ? null : PuFO.parsePurchRemote(obj);
	}

	@Override
	public QueryOperation<PurchQueryParam, PurchRemote> searchPurch(QueryOperation<PurchQueryParam, PurchRemote> _param,
			Map<PurchQueryParam, QueryValue[]> _existsDetailMap) throws RemoteException {
		QueryOperation<PurchQueryParam, Purch> param = (QueryOperation<PurchQueryParam, Purch>) _param.copy();
		param = puService.searchPurch(param, _existsDetailMap);
		_param.setQueryResult(param.getQueryResult().stream().map(PuFO::parsePurchRemote).collect(Collectors.toList()));
		_param.setTotal(param.getTotal());
		return _param;
	}
	@Override
	public boolean purchToPerf(String _uid) throws RemoteException{
		return puService.purchToPerf(_uid);
	}
	@Override
	public boolean purchRevertToPerf(String _uid) throws RemoteException{
		return puService.purchRevertToPerf(_uid);
	}

	@Override
	public boolean purchPerf(String _uid, long _perfTime) throws RemoteException {
		return puService.purchPerf(_uid, _perfTime);
	}

	@Override
	public boolean purchRevertPerf(String _uid) throws RemoteException {
		return puService.purchRevertPerf(_uid);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------PurchItem-----------------------------------
	@Override
	public PurchItemRemote createPurchItem(PurchItemCreateObjRemote _dto) throws RemoteException {
		if (_dto == null)
			return null;
		PurchItem obj = puService.createPurchItem(PuFO.parsePurchItemCreateObj(_dto));
		return obj == null ? null : PuFO.parsePurchItemRemote(obj);
	}

	@Override
	public boolean deletePurchItem(String _uid) throws RemoteException {
		return puService.deletePurchItem(_uid);
	}

	@Override
	public PurchItemRemote loadPurchItem(String _uid) throws RemoteException {
		PurchItem obj = puService.loadPurchItem(_uid);
		return obj == null ? null : PuFO.parsePurchItemRemote(obj);
	}

	@Override
	public List<PurchItemRemote> loadPurchItemList(String _purchUid) throws RemoteException {
		List<PurchItem> list = puService.loadPurchItemList(_purchUid);
		List<PurchItemRemote> remoteList = list.stream().map(PuFO::parsePurchItemRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public List<PurchItemRemote> loadPurchItemListByMm(String _mmUid) throws RemoteException {
		List<PurchItem> list = puService.loadPurchItemListByMm(_mmUid);
		List<PurchItemRemote> remoteList = list.stream().map(PuFO::parsePurchItemRemote).collect(Collectors.toList());
		return remoteList;
	}

	// -------------------------------------------------------------------------------
	// --------------------------------------SD---------------------------------------
	// XXX
	// -------------------------------------------------------------------------------
	// ----------------------------------SalesOrder-----------------------------------
	@Override
	public SalesOrderRemote createSalesOrder(SalesOrderCreateObjRemote _dto) throws RemoteException {
		if (_dto == null)
			return null;
		SalesOrder obj = sdService.createSalesOrder(SdFO.parseSalesOrderCreateObj(_dto));
		return obj == null ? null : SdFO.parseSalesOrderRemote(obj);
	}
	@Override
	public boolean deleteSalesOrder(String _uid) throws RemoteException{
		return sdService.deleteSalesOrder(_uid);
	}

	@Override
	public SalesOrderRemote loadSalesOrder(String _uid) throws RemoteException{
		SalesOrder obj = sdService.loadSalesOrder(_uid);
		return obj == null ? null : SdFO.parseSalesOrderRemote(obj);
	}

	@Override
	public SalesOrderRemote loadSalesOrderBySosn(String _sosn) throws RemoteException{
		SalesOrder obj = sdService.loadSalesOrderBySosn(_sosn);
		return obj == null ? null : SdFO.parseSalesOrderRemote(obj);
	}

	@Override
	public QueryOperation<SalesOrderQueryParam, SalesOrderRemote> searchSalesOrder(
			QueryOperation<SalesOrderQueryParam, SalesOrderRemote> _param,
			Map<SalesOrderQueryParam, QueryValue[]> _existsDetailMap) throws RemoteException {
		QueryOperation<SalesOrderQueryParam, SalesOrder> param = (QueryOperation<SalesOrderQueryParam, SalesOrder>) _param
				.copy();
		param = sdService.searchSalesOrder(param, _existsDetailMap);
		_param.setQueryResult(
				param.getQueryResult().stream().map(SdFO::parseSalesOrderRemote).collect(Collectors.toList()));
		_param.setTotal(param.getTotal());
		return _param;
	}

	// -------------------------------------------------------------------------------
	// --------------------------------SalesOrderItem---------------------------------
	@Override
	public SalesOrderItemRemote createSalesOrderItem(String _soUid, SalesOrderItemCreateObjRemote _dto)
			throws RemoteException {
		if (_dto == null)
			return null;
		SalesOrderItem obj = sdService.createSalesOrderItem(_soUid, SdFO.parseSalesOrderItemCreateObj(_dto));
		return obj == null ? null : SdFO.parseSalesOrderItemRemote(obj);
	}

	@Override
	public boolean deleteSalesOrderItem(String _uid) throws RemoteException{
		return sdService.deleteSalesOrderItem(_uid);
	}

	@Override
	public SalesOrderItemRemote loadSalesOrderItem(String _uid) throws RemoteException{
		SalesOrderItem obj = sdService.loadSalesOrderItem(_uid);
		return obj == null ? null : SdFO.parseSalesOrderItemRemote(obj);
	}

	@Override
	public List<SalesOrderItemRemote> loadSalesOrderItemList(String _soUid) throws RemoteException{
		List<SalesOrderItem> list = sdService.loadSalesOrderItemList(_soUid);
		List<SalesOrderItemRemote> remoteList = list.stream().map(SdFO::parseSalesOrderItemRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public List<SalesOrderItemRemote> loadSalesOrderItemListMyMm(String _mmUid) throws RemoteException{
		List<SalesOrderItem> list = sdService.loadSalesOrderItemListMyMm(_mmUid);
		List<SalesOrderItemRemote> remoteList = list.stream().map(SdFO::parseSalesOrderItemRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public QueryOperation<SalesOrderItemQueryParam, SalesOrderItemRemote> searchSalesOrderItem(
			QueryOperation<SalesOrderItemQueryParam, SalesOrderItemRemote> _param) throws RemoteException{
		QueryOperation<SalesOrderItemQueryParam, SalesOrderItem> param = (QueryOperation<SalesOrderItemQueryParam, SalesOrderItem>) _param
				.copy();
		param = sdService.searchSalesOrderItem(param);
		_param.setQueryResult(
				param.getQueryResult().stream().map(SdFO::parseSalesOrderItemRemote).collect(Collectors.toList()));
		_param.setTotal(param.getTotal());
		return _param;
	}

	@Override
	public boolean soiFinishDeliver(String _uid, long _finishDeliveredDate) throws RemoteException{
		return sdService.soiFinishDeliver(_uid, _finishDeliveredDate);
	}

	@Override
	public boolean soiRevertFinishDeliver(String _uid) throws RemoteException{
		return sdService.soiRevertFinishDeliver(_uid);
	}
	
}
