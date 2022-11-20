package ekp.mbom;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.data.MbomDataService;
import ekp.data.service.mbom.query.PartQueryParam;
import ekp.mbom.dto.ParsProcCreateObj;
import ekp.mbom.dto.PartAcqRoutingStepCreateObj;
import ekp.mbom.dto.PartAcquisitionCreateObj;
import ekp.mbom.dto.PartCfgCreateObj;
import ekp.mbom.dto.PartCreateObj;
import ekp.mbom.dto.ProdCreateObj;
import ekp.mbom.dto.ProdCtlCreateObj;
import ekp.mbom.dto.ProdModCreateObj;
import legion.DataServiceFactory;
import legion.util.query.QueryOperation;

public class MbomServiceImp implements MbomService {
	private Logger log = LoggerFactory.getLogger(MbomServiceImp.class);

	private static MbomDataService dataService;

	@Override
	public void register(Map<String, String> _params) {
		dataService = DataServiceFactory.getInstance().getService(MbomDataService.class);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	// -------------------------------------------------------------------------------
	// -------------------------------------Part--------------------------------------
	@Override
	public Part createPart(PartCreateObj _dto) {
		return Part.create(_dto);
	}

	@Override
	public boolean deletePart(String _uid) {
		return loadPart(_uid).delete();
	}

	@Override
	public Part loadPart(String _uid) {
		return dataService.loadPart(_uid);
	}

	@Override
	public Part loadPartByPin(String _pin) {
		return dataService.loadPartByPin(_pin);
	}

	@Override
	public QueryOperation<PartQueryParam, Part> searchPart(QueryOperation<PartQueryParam, Part> _param){
		return dataService.searchPart(_param);
	}
	
	// -------------------------------------------------------------------------------
	// --------------------------------PartAcquisition--------------------------------
	@Override
	public PartAcquisition createPartAcquisition(PartAcquisitionCreateObj _dto) {
		return PartAcquisition.create(_dto);
	}

	@Override
	public boolean deletePartAcquisition(String _uid) {
		return loadPartAcquisition(_uid).delete();
	}

	@Override
	public PartAcquisition loadPartAcquisition(String _uid) {
		return dataService.loadPartAcquisition(_uid);
	}

	@Override
	public PartAcquisition loadPartAcquisition(String _partPin, String _id) {
		return dataService.loadPartAcquisition(_partPin, _id);
	}

	@Override
	public List<PartAcquisition> loadPartAcquisitionList(String _partUid) {
		return dataService.loadPartAcquisitionList(_partUid);
	}

	// -------------------------------------------------------------------------------
	// ------------------------------PartAcqRoutingStep-------------------------------
	@Override
	public PartAcqRoutingStep createPartAcqRoutingStep(PartAcqRoutingStepCreateObj _dto) {
		return PartAcqRoutingStep.create(_dto);
	}

	@Override
	public boolean deletePartAcqRoutingStep(String _uid) {
		return loadPartAcqRoutingStep(_uid).delete();
	}

	@Override
	public PartAcqRoutingStep loadPartAcqRoutingStep(String _uid) {
		return dataService.loadPartAcqRoutingStep(_uid);
	}

	@Override
	public PartAcqRoutingStep loadPartAcqRoutingStep(String _partAcqUid, String _id) {
		return dataService.loadPartAcqRoutingStep(_partAcqUid, _id);
	}

	@Override
	public List<PartAcqRoutingStep> loadPartAcqRoutingStepList(String _partAcqUid) {
		return dataService.loadPartAcqRoutingStepList(_partAcqUid);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsProc------------------------------------
	@Override
	public ParsProc createParsProc(ParsProcCreateObj _dto) {
		return ParsProc.create(_dto);
	}

	@Override
	public boolean deleteParsProc(String _uid) {
		return loadParsProc(_uid).delete();
	}

	@Override
	public ParsProc loadParsProc(String _uid) {
		return dataService.loadParsProc(_uid);
	}

	@Override
	public List<ParsProc> loadParsProcList(String _parsUid) {
		return dataService.loadParsProcList(_parsUid);
	}

	@Override
	public List<ParsProc> loadParsProcListByProc(String _processUid) {
		return dataService.loadParsProcListByProc(_processUid);
	}

	@Override
	public boolean parsProcAssignProc(String _uid, String _processUid, String _processId) {
		return loadParsProc(_uid).assignProc(_processUid, _processId);
	}

	@Override
	public boolean parsProcRevertAssignProc(String _uid) {
		return loadParsProc(_uid).revertAssignProc();
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsPart------------------------------------
	@Override
	public ParsPart createParsPart(String _parsUid) {
		return ParsPart.create(_parsUid);
	}

	@Override
	public boolean deleteParsPart(String _uid) {
		return loadParsPart(_uid).delete();
	}

	@Override
	public ParsPart loadParsPart(String _uid) {
		return dataService.loadParsPart(_uid);
	}

	@Override
	public ParsPart loadParsPart(String _parsUid, String _partuid) {
		return dataService.loadParsPart(_parsUid, _partuid);
	}

	@Override
	public List<ParsPart> loadParsPartList(String _parsUid) {
		return dataService.loadParsPartList(_parsUid);
	}

	@Override
	public List<ParsPart> loadParsPartListByPart(String _partUid) {
		return dataService.loadParsPartListByPart(_partUid);
	}

	@Override
	public boolean parsPartAssignPart(String _uid, String _partUid, String _partPin, double _partReqQty) {
		return loadParsPart(_uid).assignPart(_partUid, _partPin, _partReqQty);
	}

	@Override
	public boolean parsPartRevertAssignPart(String _uid) {
		return loadParsPart(_uid).revertAssignPart();
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------PartCfg------------------------------------
	@Override
	public PartCfg createPartCfg(PartCfgCreateObj _dto) {
		return PartCfg.create(_dto);
	}

	@Override
	public boolean deletePartCfg(String _uid) {
		return loadPartCfg(_uid).delete();
	}

	@Override
	public PartCfg loadPartCfg(String _uid) {
		return dataService.loadPartCfg(_uid);
	}

	@Override
	public PartCfg loadPartCfgById(String _id) {
		return dataService.loadPartCfgById(_id);
	}

	@Override
	public List<PartCfg> loadPartCfgList(String _rootPartUid) {
		return dataService.loadPartCfgList(_rootPartUid);
	}

	@Override
	public boolean partCfgStartEditing(String _uid) {
		return loadPartCfg(_uid).startEditing();
	}

	@Override
	public boolean partCfgRevertStartEditing(String _uid) {
		return loadPartCfg(_uid).revertStartEditing();
	}

	@Override
	public boolean partCfgPublish(String _uid) {
		return loadPartCfg(_uid).publish();
	}

	@Override
	public boolean partCfgRevertPublish(String _uid) {
		return loadPartCfg(_uid).revertPublish();
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------PartCfgConj----------------------------------
	@Override
	public PartCfgConj createPartCfgConj(String _partCfgUid, String _partAcqUid) {
		return PartCfgConj.create(_partCfgUid, _partAcqUid);
	}

	@Override
	public boolean deletePartCfgConj(String _uid) {
		return loadPartCfgConj(_uid).delete();
	}

	@Override
	public PartCfgConj loadPartCfgConj(String _uid) {
		return dataService.loadPartCfgConj(_uid);
	}

	@Override
	public PartCfgConj loadPartCfgConj(String _partCfgUid, String _partAcqUid) {
		return dataService.loadPartCfgConj(_partCfgUid, _partAcqUid);
	}

	@Override
	public List<PartCfgConj> loadPartCfgConjList(String _partCfgUid) {
		return dataService.loadPartCfgConjList(_partCfgUid);
	}

	// -------------------------------------------------------------------------------
	// -------------------------------------Prod--------------------------------------
	@Override
	public Prod createProd(ProdCreateObj _dto) {
		return Prod.create(_dto);
	}

	@Override
	public boolean deleteProd(String _uid) {
		return loadProd(_uid).delete();
	}

	@Override
	public Prod loadProd(String _uid) {
		return dataService.loadProd(_uid);
	}

	@Override
	public Prod loadProdById(String _id) {
		return dataService.loadProdById(_id);
	}

	@Override
	public List<Prod> loadProdList() {
		return dataService.loadProdList();
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------ProdCtl------------------------------------
	@Override
	public ProdCtl createProdCtl(ProdCtlCreateObj _dto) {
		return ProdCtl.create(_dto);
	}

	@Override
	public boolean deleteProdCtl(String _uid) {
		return loadProdCtl(_uid).delete();
	}

	@Override
	public ProdCtl loadProdCtl(String _uid) {
		return dataService.loadProdCtl(_uid);
	}

	@Override
	public ProdCtl loadProdCtlById(String _id) {
		return dataService.loadProdCtlById(_id);
	}

	@Override
	public List<ProdCtl> loadProdCtlList(String _parentUid) {
		return dataService.loadProdCtlList(_parentUid);
	}

	@Override
	public List<ProdCtl> loadProdCtlListLv1(String _prodUid) {
		return dataService.loadProdCtlListLv1(_prodUid);
	}

	@Override
	public boolean prodCtlAssignParent(String _uid, String _parentUid, String _parentId) {
		return loadProdCtl(_uid).assignParent(_parentUid, _parentId);
	}

	@Override
	public boolean prodCtlUnassignParent(String _uid) {
		return loadProdCtl(_uid).unassignParent();
	}

	@Override
	public boolean prodCtlAssignProd(String _uid, String _prodUid) {
		return loadProdCtl(_uid).assignProd(_prodUid);
	}

	@Override
	public boolean prodCtlUnassignProd(String _uid) {
		return loadProdCtl(_uid).unassignProd();
	}

	// -------------------------------------------------------------------------------
	// ------------------------------ProdCtlPartCfgConj-------------------------------
	@Override
	public ProdCtlPartCfgConj createProdCtlPartCfgConj(String _prodCtlUid, String _partCfgUid) {
		return ProdCtlPartCfgConj.create(_prodCtlUid, _partCfgUid);
	}

	@Override
	public boolean deleteProdCtlPartCfgConj(String _uid) {
		return loadProdCtlPartCfgConj(_uid).delete();
	}

	@Override
	public ProdCtlPartCfgConj loadProdCtlPartCfgConj(String _uid) {
		return dataService.loadProdCtlPartCfgConj(_uid);
	}

	@Override
	public ProdCtlPartCfgConj loadProdCtlPartCfgConj(String _prodCtlUid, String _partCfgUid) {
		return dataService.loadProdCtlPartCfgConj(_prodCtlUid, _partCfgUid);
	}

	@Override
	public List<ProdCtlPartCfgConj> loadProdCtlPartCfgConjList1(String _prodCtlUid) {
		return dataService.loadProdCtlPartCfgConjList1(_prodCtlUid);
	}

	@Override
	public List<ProdCtlPartCfgConj> loadProdCtlPartCfgConjList2(String _partCfgUid) {
		return dataService.loadProdCtlPartCfgConjList2(_partCfgUid);
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------ProdMod------------------------------------
	@Override
	public ProdMod createProdMod(ProdModCreateObj _dto) {
		return ProdMod.create(_dto);
	}

	@Override
	public boolean deleteProdMod(String _uid) {
		return loadProdMod(_uid).delete();
	}

	@Override
	public ProdMod loadProdMod(String _uid) {
		return dataService.loadProdMod(_uid);
	}

	@Override
	public ProdMod loadProdModById(String _id) {
		return dataService.loadProdModById(_id);
	}

	@Override
	public List<ProdMod> loadProdModList(String _prodUid) {
		return dataService.loadProdModList(_prodUid);
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ProdModItem----------------------------------
	@Override
	public ProdModItem createProdModItem(String prodModUid, String prodCtlUid) {
		return ProdModItem.create(prodModUid, prodCtlUid);
	}

	@Override
	public boolean deleteProdModItem(String _uid) {
		return loadProdModItem(_uid).delete();
	}

	@Override
	public ProdModItem loadProdModItem(String _uid) {
		return dataService.loadProdModItem(_uid);
	}

	@Override
	public ProdModItem loadProdModItem(String _prodModUid, String _prodCtlUid) {
		return dataService.loadProdModItem(_prodModUid, _prodCtlUid);
	}

	@Override
	public ProdModItem loadProdModItem(String _prodModUid, String _prodCtlUid, String _partCfgUid) {
		return dataService.loadProdModItem(_prodModUid, _prodCtlUid, _partCfgUid);
	}

	@Override
	public List<ProdModItem> loadProdModItemList(String _prodModUid) {
		return dataService.loadProdModItemList(_prodModUid);
	}

	@Override
	public boolean prodModItemAssignPartCfg(String _uid, String _partCfgUid) {
		return loadProdModItem(_uid).assignPartCfg(_partCfgUid);
	}

	@Override
	public boolean prodModItemUnassignPartCfg(String _uid) {
		return loadProdModItem(_uid).unassignPartCfg();
	}

}
