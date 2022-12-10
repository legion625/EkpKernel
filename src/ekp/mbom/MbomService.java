package ekp.mbom;

import java.util.List;
import java.util.Map;

import ekp.data.service.mbom.query.PartCfgQueryParam;
import ekp.data.service.mbom.query.PartQueryParam;
import ekp.data.service.mbom.query.PpartSkewerQueryParam;
import ekp.mbom.dto.ParsProcCreateObj;
import ekp.mbom.dto.PartAcqRoutingStepCreateObj;
import ekp.mbom.dto.PartAcquisitionCreateObj;
import ekp.mbom.dto.PartCfgCreateObj;
import ekp.mbom.dto.PartCreateObj;
import ekp.mbom.dto.PpartSkewer;
import ekp.mbom.dto.ProdCreateObj;
import ekp.mbom.dto.ProdCtlCreateObj;
import ekp.mbom.dto.ProdModCreateObj;
import ekp.serviceFacade.rmi.mbom.PartCfgConjRemote;
import legion.BusinessService;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public interface MbomService extends BusinessService {

	// -------------------------------------------------------------------------------
	// -------------------------------------Part--------------------------------------
	public Part createPart(PartCreateObj _dto);

	public boolean deletePart(String _uid);

	public Part loadPart(String _uid);

	public Part loadPartByPin(String _pin);
	
	public QueryOperation<PartQueryParam, Part> searchPart(QueryOperation<PartQueryParam, Part> _param);

	// -------------------------------------------------------------------------------
	// --------------------------------PartAcquisition--------------------------------
	public PartAcquisition createPartAcquisition(PartAcquisitionCreateObj _dto);

	public boolean deletePartAcquisition(String _uid);

	public PartAcquisition loadPartAcquisition(String _uid);

	public PartAcquisition loadPartAcquisition(String _partPin, String _id);

	public List<PartAcquisition> loadPartAcquisitionList(String _partUid);

	// -------------------------------------------------------------------------------
	// ------------------------------PartAcqRoutingStep-------------------------------
	public PartAcqRoutingStep createPartAcqRoutingStep(PartAcqRoutingStepCreateObj _dto);

	public boolean deletePartAcqRoutingStep(String _uid);

	public PartAcqRoutingStep loadPartAcqRoutingStep(String _uid);

	public PartAcqRoutingStep loadPartAcqRoutingStep(String _partAcqUid, String _seq);

	public List<PartAcqRoutingStep> loadPartAcqRoutingStepList(String _partAcqUid);

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsProc------------------------------------
	public ParsProc createParsProc(ParsProcCreateObj _dto);

	public boolean deleteParsProc(String _uid);

	public ParsProc loadParsProc(String _uid);

	public List<ParsProc> loadParsProcList(String _parsUid);

	public List<ParsProc> loadParsProcListByProc(String _procUid);

	public boolean parsProcAssignProc(String _uid, String _procUid, String _procId);

	public boolean parsProcRevertAssignProc(String _uid);

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsPart------------------------------------
	public ParsPart createParsPart(String _parsUid);

	public boolean deleteParsPart(String _uid);

	public ParsPart loadParsPart(String _uid);

	public ParsPart loadParsPart(String _parsUid, String _partuid);

	public List<ParsPart> loadParsPartList(String _parsUid);

	public List<ParsPart> loadParsPartListByPart(String _partUid);

	public boolean parsPartAssignPart(String _uid, String _partUid, String _partPin, double _partReqQty);

	public boolean parsPartRevertAssignPart(String _uid);

	// -------------------------------------------------------------------------------
	// ----------------------------------PpartSkewer----------------------------------
	public PpartSkewer loadPpartSkewer(String _uid);

	public QueryOperation<PpartSkewerQueryParam, PpartSkewer> searchPpartSkewer(
			QueryOperation<PpartSkewerQueryParam, PpartSkewer> _p,
			Map<PpartSkewerQueryParam, QueryValue[]> _existsQvMap);
	
	// -------------------------------------------------------------------------------
	// ------------------------------------PartCfg------------------------------------
	public PartCfg createPartCfg(PartCfgCreateObj _dto);

	public boolean deletePartCfg(String _uid);

	public PartCfg loadPartCfg(String _uid);

	public PartCfg loadPartCfgById(String _id);

	public List<PartCfg> loadPartCfgList(String _rootPartUid);
	
	public QueryOperation<PartCfgQueryParam, PartCfg> searchPartCfg(QueryOperation<PartCfgQueryParam, PartCfg> _param);

	public boolean partCfgStartEditing(String _uid);

	public boolean partCfgRevertStartEditing(String _uid);

	public boolean partCfgPublish(String _uid);

	public boolean partCfgRevertPublish(String _uid);

	// -------------------------------------------------------------------------------
	// ----------------------------------PartCfgConj----------------------------------
	public PartCfgConj createPartCfgConj(String _partCfgUid, String _partAcqUid);

	public boolean deletePartCfgConj(String _uid);

	public PartCfgConj loadPartCfgConj(String _uid);

	public PartCfgConj loadPartCfgConj(String _partCfgUid, String _partAcqUid);

	public List<PartCfgConj> loadPartCfgConjList(String _partCfgUid);
	
	public List<PartCfgConj> loadPartCfgConjListByPartAcq(String _partAcqUid);
	
	// -------------------------------------------------------------------------------
	// -------------------------------------Prod--------------------------------------
	public Prod createProd(ProdCreateObj _dto);

	public boolean deleteProd(String _uid);

	public Prod loadProd(String _uid);

	public Prod loadProdById(String _id);

	public List<Prod> loadProdList();

	// -------------------------------------------------------------------------------
	// ------------------------------------ProdCtl------------------------------------
	public ProdCtl createProdCtl(ProdCtlCreateObj _dto);

	public boolean deleteProdCtl(String _uid);

	public ProdCtl loadProdCtl(String _uid);

	public ProdCtl loadProdCtlById(String _id);

	public List<ProdCtl> loadProdCtlList(String _parentUid);

	public List<ProdCtl> loadProdCtlListLv1(String _prodUid);

	public boolean prodCtlAssignParent(String _uid, String _parentUid, String _parentId);

	public boolean prodCtlUnassignParent(String _uid);

	public boolean prodCtlAssignProd(String _uid, String _prodUid);

	public boolean prodCtlUnassignProd(String _uid);

	// -------------------------------------------------------------------------------
	// ------------------------------ProdCtlPartCfgConj-------------------------------
	public ProdCtlPartCfgConj createProdCtlPartCfgConj(String _prodCtlUid, String _partCfgUid);

	public boolean deleteProdCtlPartCfgConj(String _uid);

	public ProdCtlPartCfgConj loadProdCtlPartCfgConj(String _uid);

	public ProdCtlPartCfgConj loadProdCtlPartCfgConj(String _prodCtlUid, String _partCfgUid);

	public List<ProdCtlPartCfgConj> loadProdCtlPartCfgConjList1(String _prodCtlUid);

	public List<ProdCtlPartCfgConj> loadProdCtlPartCfgConjList2(String _partCfgUid);

	// -------------------------------------------------------------------------------
	// ------------------------------------ProdMod------------------------------------
	public ProdMod createProdMod(ProdModCreateObj _dto);

	public boolean deleteProdMod(String _uid);

	public ProdMod loadProdMod(String _uid);
	
	public ProdMod loadProdModById(String _id);

	public List<ProdMod> loadProdModList(String _prodUid);

	// -------------------------------------------------------------------------------
	// ----------------------------------ProdModItem----------------------------------
	public ProdModItem createProdModItem(String prodModUid, String prodCtlUid);

	public boolean deleteProdModItem(String _uid);

	public ProdModItem loadProdModItem(String _uid);
	public ProdModItem loadProdModItem(String _prodModUid, String _prodCtlUid);
	public ProdModItem loadProdModItem(String _prodModUid, String _prodCtlUid, String _partCfgUid);

	public List<ProdModItem> loadProdModItemList(String _prodModUid);

	public boolean prodModItemAssignPartCfg(String _uid, String _partCfgUid);

	public boolean prodModItemUnassignPartCfg(String _uid);

}
