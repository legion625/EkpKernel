package ekp.data;

import java.util.List;

import ekp.mbom.Part;
import ekp.mbom.PartAcqRoutingStep;
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
import legion.IntegrationService;

public interface MbomDataService extends IntegrationService {

	// -------------------------------------------------------------------------------
	// -------------------------------------Part--------------------------------------
	public boolean savePart(Part _p);

	public boolean deletePart(String _uid);

	public Part loadPart(String _uid);

	public Part loadPartByPin(String _pin);

	// -------------------------------------------------------------------------------
	// --------------------------------PartAcquisition--------------------------------
	public boolean savePartAcquisition(PartAcquisition _pa);

	public boolean deletePartAcquisition(String _uid);

	public PartAcquisition loadPartAcquisition(String _uid);

	public PartAcquisition loadPartAcquisitionById(String _id);

	public List<PartAcquisition> loadPartAcquisitionList(String _partUid);

	// -------------------------------------------------------------------------------
	// ------------------------------PartAcqRoutingStep-------------------------------
	public boolean savePartAcqRoutingStep(PartAcqRoutingStep _pars);

	public boolean deletePartAcqRoutingStep(String _uid);

	public PartAcqRoutingStep loadPartAcqRoutingStep(String _uid);

	public PartAcqRoutingStep loadPartAcqRoutingStep(String _partAcqUid, String _id);

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

	public List<ParsPart> loadParsPartList(String _parsUid);

	public List<ParsPart> loadParsPartListByPart(String _partUid);

	// -------------------------------------------------------------------------------
	// ------------------------------------PartCfg------------------------------------
	public boolean savePartCfg(PartCfg _pc);

	public boolean deletePartCfg(String _uid);

	public PartCfg loadPartCfg(String _uid);

	public List<PartCfg> loadPartCfgList(String _rootPartUid);

	// -------------------------------------------------------------------------------
	// ----------------------------------PartCfgConj----------------------------------
	public boolean savePartCfgConj(PartCfgConj _pcc);

	public boolean deletePartCfgConj(String _uid);

	public PartCfgConj loadPartCfgConj(String _uid);

	public List<PartCfgConj> loadPartCfgConjList(String _partCfgUid);

	// -------------------------------------------------------------------------------
	// -------------------------------------Prod--------------------------------------
	public boolean saveProd(Prod _p);
	public boolean deleteProd(String _uid);
	public Prod loadProd(String _uid);
	public List<Prod> loadProdList();

	// -------------------------------------------------------------------------------
	// ------------------------------------ProdCtl------------------------------------
	public boolean saveProdCtl(ProdCtl _pc);
	public boolean deleteProdCtl(String _uid);
	public ProdCtl loadProdCtl(String _uid);
	public List<ProdCtl> loadProdCtlList(String _parentUid);
	public List<ProdCtl> loadProdCtlListLv1(String _prodUid);
	

	// -------------------------------------------------------------------------------
	// ------------------------------ProdCtlPartCfgConj-------------------------------
	public boolean saveProdCtlPartCfgConj(ProdCtlPartCfgConj _pcpcc);
	public boolean deleteProdCtlPartCfgConj(String _uid);
	public ProdCtlPartCfgConj loadProdCtlPartCfgConj(String _uid);
	public List<ProdCtlPartCfgConj> loadProdCtlPartCfgConjList1(String _prodCtlUid);
	public List<ProdCtlPartCfgConj> loadProdCtlPartCfgConjList2(String _partCfgUid);
	
	// -------------------------------------------------------------------------------
	// ------------------------------------ProdMod------------------------------------
	public boolean saveProdMod(ProdMod _pm);
	public boolean deleteProdMod(String _uid);
	public ProdMod loadProdMod(String _uid);
	public List<ProdMod> loadProdModList(String _prodUid);
	
	// -------------------------------------------------------------------------------
	// ----------------------------------ProdModItem----------------------------------
	public boolean saveProdModItem(ProdModItem _pmi);
	public boolean deleteProdModItem(String _uid);
	public ProdModItem loadProdModItem(String _uid);
	public List<ProdModItem> loadProdModItemList(String _prodModUid);
	
	
	
	

}
