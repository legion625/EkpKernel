package ekp.serviceFacade.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.DebugLogMark;
import ekp.data.service.mbom.query.PartQueryParam;
import ekp.data.service.mbom.query.PpartSkewerQueryParam;
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
import legion.BusinessServiceFactory;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public class EkpKernelServiceRemoteImp extends UnicastRemoteObject implements EkpKernelServiceRemote {

	private static Logger log = LoggerFactory.getLogger(EkpKernelServiceRemoteImp.class);

	// -------------------------------------------------------------------------------
	private MbomService mbomService = BusinessServiceFactory.getInstance().getService(MbomService.class);

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
			throws RemoteException{
		QueryOperation<PartQueryParam, Part> param = (QueryOperation<PartQueryParam, Part>) _param.copy();
		param = mbomService.searchPart(param);
		log.debug("param.getTotal(): {}", param.getTotal());
		log.debug("limit: {}\t{}", param.getLimit()[0], param.getLimit()[1]);
		log.debug("param.getQueryResult().size(): {}", param.getQueryResult().size());
		_param.setQueryResult(
				param.getQueryResult().stream().map(MbomFO::parsePartRemote).collect(Collectors.toList()));
		_param.setTotal(param.getTotal());
		return _param;
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
	public boolean partCfgStartEditing(String _uid) throws RemoteException {
		return mbomService.partCfgStartEditing(_uid);
	}

	@Override
	public boolean partCfgRevertStartEditing(String _uid) throws RemoteException {
		return mbomService.partCfgRevertStartEditing(_uid);
	}

	@Override
	public boolean partCfgPublish(String _uid) throws RemoteException {
		return mbomService.partCfgPublish(_uid);
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
	public List<PartCfgConjRemote> loadPartCfgConjListByPartAcq(String _partAcqUid) throws RemoteException{
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
	public ProdCtlRemote loadProdCtlById(String _id) throws RemoteException {
		ProdCtl obj = mbomService.loadProdCtlById(_id);
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
	public boolean prodCtlAssignParent(String _uid, String _parentUid, String _parentId) throws RemoteException {
		return mbomService.prodCtlAssignParent(_uid, _parentUid, _parentId);
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
	public ProdCtlPartCfgConjRemote createProdCtlPartCfgConj(String _prodCtlUid, String _partCfgUid)
			throws RemoteException {
		return MbomFO.parseProdCtlPartCfgConjRemote(mbomService.createProdCtlPartCfgConj(_prodCtlUid, _partCfgUid));
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
	public ProdCtlPartCfgConjRemote loadProdCtlPartCfgConj(String _prodCtlUid, String _partCfgUid)
			throws RemoteException {
		ProdCtlPartCfgConj obj = mbomService.loadProdCtlPartCfgConj(_prodCtlUid, _partCfgUid);
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
	public ProdModItemRemote loadProdModItem(String _prodModUid, String _prodCtlUid, String _partCfgUid)
			throws RemoteException {
		ProdModItem obj = mbomService.loadProdModItem(_prodModUid, _prodCtlUid, _partCfgUid);
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
	public boolean prodModItemAssignPartCfg(String _uid, String _partCfgUid) throws RemoteException {
		return mbomService.prodModItemAssignPartCfg(_uid, _partCfgUid);
	}

	@Override
	public boolean prodModItemUnassignPartCfg(String _uid) throws RemoteException {
		return mbomService.prodModItemUnassignPartCfg(_uid);
	}
}
