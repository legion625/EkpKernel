package ekp.data;

import java.util.List;

import ekp.mbom.Part;
import ekp.mbom.PartAcqRoutingStep;
import ekp.data.service.mbom.query.PartQueryParam;
import ekp.mbom.ParsPart;
import ekp.mbom.ParsProc;
import ekp.mbom.PartAcquisition;
import ekp.mbom.PartCfg;
import ekp.mbom.PartCfgConj;
import ekp.mbom.Prod;
import ekp.mbom.ProdCtl;
import ekp.mbom.ProdCtlPartCfgConj;
import ekp.mbom.ProdMod;
import ekp.mbom.ProdModItem;
import ekp.mbom.dto.PpartSkewer;
import legion.IntegrationService;
import legion.util.query.QueryOperation;

public interface MbomDataService extends IntegrationService {

	// -------------------------------------------------------------------------------
	// -------------------------------------Part--------------------------------------
	public boolean savePart(Part _p);

	public boolean deletePart(String _uid);

	public Part loadPart(String _uid);

	public Part loadPartByPin(String _pin);
	
	public QueryOperation<PartQueryParam, Part> searchPart(QueryOperation<PartQueryParam, Part> _param);

	// -------------------------------------------------------------------------------
	// --------------------------------PartAcquisition--------------------------------
	public boolean savePartAcquisition(PartAcquisition _pa);

	public boolean deletePartAcquisition(String _uid);

	public PartAcquisition loadPartAcquisition(String _uid);

	public PartAcquisition loadPartAcquisition(String _partPin, String _id);

	public List<PartAcquisition> loadPartAcquisitionList(String _partUid);

	// -------------------------------------------------------------------------------
	// ------------------------------PartAcqRoutingStep-------------------------------
	public boolean savePartAcqRoutingStep(PartAcqRoutingStep _pars);

	public boolean deletePartAcqRoutingStep(String _uid);

	public PartAcqRoutingStep loadPartAcqRoutingStep(String _uid);

	public PartAcqRoutingStep loadPartAcqRoutingStep(String _partAcqUid, String _seq);

	public List<PartAcqRoutingStep> loadPartAcqRoutingStepList(String _partAcqUid);

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsProc------------------------------------
	public boolean saveParsProc(ParsProc _parsProcess);

	public boolean deleteParsProc(String _uid);

	public ParsProc loadParsProc(String _uid);

	public List<ParsProc> loadParsProcList(String _parsUid);

	public List<ParsProc> loadParsProcListByProc(String _procUid);

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsPart------------------------------------
	public boolean saveParsPart(ParsPart _parsPart);

	public boolean deleteParsPart(String _uid);

	public ParsPart loadParsPart(String _uid);
	
	public ParsPart loadParsPart(String _parsUid, String _partuid);

	public List<ParsPart> loadParsPartList(String _parsUid);

	public List<ParsPart> loadParsPartListByPart(String _partUid);
	
	// -------------------------------------------------------------------------------
	// ----------------------------------PpartSkewer----------------------------------
	public PpartSkewer loadPpartSkewer(String _uid);
	
	// -------------------------------------------------------------------------------
	// ------------------------------------PartCfg------------------------------------
	public boolean savePartCfg(PartCfg _pc);

	public boolean deletePartCfg(String _uid);

	public PartCfg loadPartCfg(String _uid);
	
	public PartCfg loadPartCfgById(String _id);

	public List<PartCfg> loadPartCfgList(String _rootPartUid);

	// -------------------------------------------------------------------------------
	// ----------------------------------PartCfgConj----------------------------------
	public boolean savePartCfgConj(PartCfgConj _pcc);

	public boolean deletePartCfgConj(String _uid);

	public PartCfgConj loadPartCfgConj(String _uid);
	
	public PartCfgConj loadPartCfgConj(String _partCfgUid, String _partAcqUid);

	public List<PartCfgConj> loadPartCfgConjList(String _partCfgUid);
	
	public List<PartCfgConj> loadPartCfgConjListByPartAcq(String _partAcqUid);

	// -------------------------------------------------------------------------------
	// -------------------------------------Prod--------------------------------------
	public boolean saveProd(Prod _p);
	public boolean deleteProd(String _uid);
	public Prod loadProd(String _uid);
	public Prod loadProdById(String _id);
	public List<Prod> loadProdList();

	// -------------------------------------------------------------------------------
	// ------------------------------------ProdCtl------------------------------------
	public boolean saveProdCtl(ProdCtl _pc);
	public boolean deleteProdCtl(String _uid);
	public ProdCtl loadProdCtl(String _uid);
	public ProdCtl loadProdCtlById(String _id);
	public List<ProdCtl> loadProdCtlList(String _parentUid);
	public List<ProdCtl> loadProdCtlListLv1(String _prodUid);
	

	// -------------------------------------------------------------------------------
	// ------------------------------ProdCtlPartCfgConj-------------------------------
	public boolean saveProdCtlPartCfgConj(ProdCtlPartCfgConj _pcpcc);
	public boolean deleteProdCtlPartCfgConj(String _uid);
	public ProdCtlPartCfgConj loadProdCtlPartCfgConj(String _uid);
	public ProdCtlPartCfgConj loadProdCtlPartCfgConj(String _prodCtlUid, String _partCfgUid);
	public List<ProdCtlPartCfgConj> loadProdCtlPartCfgConjList1(String _prodCtlUid);
	public List<ProdCtlPartCfgConj> loadProdCtlPartCfgConjList2(String _partCfgUid);
	
	// -------------------------------------------------------------------------------
	// ------------------------------------ProdMod------------------------------------
	public boolean saveProdMod(ProdMod _pm);
	public boolean deleteProdMod(String _uid);
	public ProdMod loadProdMod(String _uid);
	public ProdMod loadProdModById(String _id);
	public List<ProdMod> loadProdModList(String _prodUid);
	
	// -------------------------------------------------------------------------------
	// ----------------------------------ProdModItem----------------------------------
	public boolean saveProdModItem(ProdModItem _pmi);
	public boolean deleteProdModItem(String _uid);
	public ProdModItem loadProdModItem(String _uid);
	public ProdModItem loadProdModItem(String _prodModUid, String _prodCtlUid);
	public ProdModItem loadProdModItem(String _prodModUid, String _prodCtlUid, String _partCfgUid);
	public List<ProdModItem> loadProdModItemList(String _prodModUid);
	
	
	
	

}
