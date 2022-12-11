package ekp.data.service.mbom;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.data.MbomDataService;
import ekp.data.service.mbom.query.PartCfgQueryParam;
import ekp.data.service.mbom.query.PartQueryParam;
import ekp.data.service.mbom.query.PpartSkewerQueryParam;
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
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public class MbomDataServiceImp implements MbomDataService {
	private Logger log = LoggerFactory.getLogger(MbomDataServiceImp.class);

	private String source;

	// dao
	private PartDao partDao;
	private PartCfgDao partCfgDao;
	private ProdDao prodDao;

	@Override
	public void register(Map<String, String> _params) {
		if (_params == null || _params.isEmpty())
			return;

		source = _params.get("source");

		// dao
		partDao = new PartDao(source);
		partCfgDao = new PartCfgDao(source);
		prodDao = new ProdDao(source);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	// -------------------------------------------------------------------------------
	// -------------------------------------Part--------------------------------------
	@Override
	public boolean savePart(Part _p) {
		return partDao.savePart(_p);
	}

	@Override
	public boolean deletePart(String _uid) {
		return partDao.deletePart(_uid);
	}

	@Override
	public Part loadPart(String _uid) {
		return partDao.loadPart(_uid);
	}

	@Override
	public Part loadPartByPin(String _pin) {
		return partDao.loadPartByPin(_pin);
	}
	
	@Override
	public QueryOperation<PartQueryParam, Part> searchPart(QueryOperation<PartQueryParam, Part> _param){
		return partDao.searchPart(_param);
	}

	// -------------------------------------------------------------------------------
	// --------------------------------PartAcquisition--------------------------------
	@Override
	public boolean savePartAcquisition(PartAcquisition _pa) {
		return partDao.savePartAcquisition(_pa);
	}

	@Override
	public boolean deletePartAcquisition(String _uid) {
		return partDao.deletePartAcquisition(_uid);
	}

	@Override
	public PartAcquisition loadPartAcquisition(String _uid) {
		return partDao.loadPartAcquisition(_uid);
	}

	@Override
	public PartAcquisition loadPartAcquisition(String _partPin, String _id) {
		return partDao.loadPartAcquisition(_partPin, _id);
	}

	@Override
	public List<PartAcquisition> loadPartAcquisitionList(String _partUid) {
		return partDao.loadPartAcquisitionList(_partUid);
	}

	// -------------------------------------------------------------------------------
	// ------------------------------PartAcqRoutingStep-------------------------------
	@Override
	public boolean savePartAcqRoutingStep(PartAcqRoutingStep _pars) {
		return partDao.savePartAcqRoutingStep(_pars);
	}

	@Override
	public boolean deletePartAcqRoutingStep(String _uid) {
		return partDao.deletePartAcqRoutingStep(_uid);
	}

	@Override
	public PartAcqRoutingStep loadPartAcqRoutingStep(String _uid) {
		return partDao.loadPartAcqRoutingStep(_uid);
	}

	@Override
	public PartAcqRoutingStep loadPartAcqRoutingStep(String _partAcqUid, String _seq) {
		return partDao.loadPartAcqRoutingStep(_partAcqUid, _seq);
	}

	@Override
	public List<PartAcqRoutingStep> loadPartAcqRoutingStepList(String _partAcqUid) {
		return partDao.loadPartAcqRoutingStepList(_partAcqUid);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsProc------------------------------------
	@Override
	public boolean saveParsProc(ParsProc _parsProc) {
		return partDao.saveParsProc(_parsProc);
	}

	@Override
	public boolean deleteParsProc(String _uid) {
		return partDao.deleteParsProc(_uid);
	}

	@Override
	public ParsProc loadParsProc(String _uid) {
		return partDao.loadParsProc(_uid);
	}

	@Override
	public List<ParsProc> loadParsProcList(String _parsUid) {
		return partDao.loadParsProcList(_parsUid);
	}

	@Override
	public List<ParsProc> loadParsProcListByProc(String _procUid) {
		return partDao.loadParsProcListByProc(_procUid);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsPart------------------------------------
	@Override
	public boolean saveParsPart(ParsPart _parsPart) {
		return partDao.saveParsPart(_parsPart);
	}

	@Override
	public boolean deleteParsPart(String _uid) {
		return partDao.deleteParsPart(_uid);
	}

	@Override
	public ParsPart loadParsPart(String _uid) {
		return partDao.loadParsPart(_uid);
	}

	@Override
	public ParsPart loadParsPart(String _parsUid, String _partuid) {
		return partDao.loadParsPart(_parsUid, _partuid);
	}

	@Override
	public List<ParsPart> loadParsPartList(String _parsUid) {
		return partDao.loadParsPartList(_parsUid);
	}

	@Override
	public List<ParsPart> loadParsPartListByPart(String _partUid) {
		return partDao.loadParsPartListByPart(_partUid);
	}
	
	// -------------------------------------------------------------------------------
	// ----------------------------------PpartSkewer----------------------------------
	@Override
	public PpartSkewer loadPpartSkewer(String _uid) {
		return partDao.loadPpartSkewer(_uid);
	}
	
	@Override
	public QueryOperation<PpartSkewerQueryParam, PpartSkewer> searchPpartSkewer(
			QueryOperation<PpartSkewerQueryParam, PpartSkewer> _p, Map<PpartSkewerQueryParam, QueryValue[]> _existsQvMap){
		return partDao.searchPpartSkewer(_p, _existsQvMap);
	}
	
	// -------------------------------------------------------------------------------
	// ------------------------------------PartCfg------------------------------------
	@Override
	public boolean savePartCfg(PartCfg _pc) {
		return partCfgDao.savePartCfg(_pc);
	}

	@Override
	public boolean deletePartCfg(String _uid) {
		return partCfgDao.deletePartCfg(_uid);
	}

	@Override
	public PartCfg loadPartCfg(String _uid) {
		return partCfgDao.loadPartCfg(_uid);
	}

	@Override
	public PartCfg loadPartCfgById(String _id) {
		return partCfgDao.loadPartCfgById(_id);
	}

	@Override
	public List<PartCfg> loadPartCfgList(String _rootPartUid) {
		return partCfgDao.loadPartCfgList(_rootPartUid);
	}
	
	@Override
	public QueryOperation<PartCfgQueryParam, PartCfg> searchPartCfg(QueryOperation<PartCfgQueryParam, PartCfg> _param){
		return partCfgDao.searchPartCfg(_param);
	}
	
	// -------------------------------------------------------------------------------
	// ----------------------------------PartCfgConj----------------------------------
	@Override
	public boolean savePartCfgConj(PartCfgConj _pcc) {
		return partCfgDao.savePartCfgConj(_pcc);
	}

	@Override
	public boolean deletePartCfgConj(String _uid) {
		return partCfgDao.deletePartCfgConj(_uid);
	}

	@Override
	public PartCfgConj loadPartCfgConj(String _uid) {
		return partCfgDao.loadPartCfgConj(_uid);
	}

	@Override
	public PartCfgConj loadPartCfgConj(String _partCfgUid, String _partAcqUid) {
		return partCfgDao.loadPartCfgConj(_partCfgUid, _partAcqUid);
	}

	@Override
	public List<PartCfgConj> loadPartCfgConjList(String _partCfgUid) {
		return partCfgDao.loadPartCfgConjList(_partCfgUid);
	}
	
	@Override
	public List<PartCfgConj> loadPartCfgConjListByPartAcq(String _partAcqUid){
		return partCfgDao.loadPartCfgConjListByPartAcq(_partAcqUid);
	}

	// -------------------------------------------------------------------------------
	// -------------------------------------Prod--------------------------------------
	@Override
	public boolean saveProd(Prod _p) {
		return prodDao.saveProd(_p);
	}

	@Override
	public boolean deleteProd(String _uid) {
		return prodDao.deleteProd(_uid);
	}

	@Override
	public Prod loadProd(String _uid) {
		return prodDao.loadProd(_uid);
	}

	@Override
	public Prod loadProdById(String _id) {
		return prodDao.loadProdById(_id);
	}

	@Override
	public List<Prod> loadProdList() {
		return prodDao.loadProdList();
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------ProdCtl------------------------------------
	@Override
	public boolean saveProdCtl(ProdCtl _pc) {
		return prodDao.saveProdCtl(_pc);
	}

	@Override
	public boolean deleteProdCtl(String _uid) {
		return prodDao.deleteProdCtl(_uid);
	}

	@Override
	public ProdCtl loadProdCtl(String _uid) {
		return prodDao.loadProdCtl(_uid);
	}

	@Override
	public ProdCtl loadProdCtlById(String _id) {
		return prodDao.loadProdCtlById(_id);
	}

	@Override
	public List<ProdCtl> loadProdCtlList(String _parentUid) {
		return prodDao.loadProdCtlList(_parentUid);
	}

	@Override
	public List<ProdCtl> loadProdCtlListLv1(String _prodUid) {
		return prodDao.loadProdCtlListLv1(_prodUid);
	}

	// -------------------------------------------------------------------------------
	// ------------------------------ProdCtlPartCfgConj-------------------------------
	@Override
	public boolean saveProdCtlPartCfgConj(ProdCtlPartCfgConj _pcpcc) {
		return prodDao.saveProdCtlPartCfgConj(_pcpcc);
	}

	@Override
	public boolean deleteProdCtlPartCfgConj(String _uid) {
		return prodDao.deleteProdCtlPartCfgConj(_uid);
	}

	@Override
	public ProdCtlPartCfgConj loadProdCtlPartCfgConj(String _uid) {
		return prodDao.loadProdCtlPartCfgConj(_uid);
	}

	@Override
	public ProdCtlPartCfgConj loadProdCtlPartCfgConj(String _prodCtlUid, String _partCfgUid) {
		return prodDao.loadProdCtlPartCfgConj(_prodCtlUid, _partCfgUid);
	}

	@Override
	public List<ProdCtlPartCfgConj> loadProdCtlPartCfgConjList1(String _prodCtlUid) {
		return prodDao.loadProdCtlPartCfgConjList1(_prodCtlUid);
	}

	@Override
	public List<ProdCtlPartCfgConj> loadProdCtlPartCfgConjList2(String _partCfgUid) {
		return prodDao.loadProdCtlPartCfgConjList2(_partCfgUid);
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------ProdMod------------------------------------
	@Override
	public boolean saveProdMod(ProdMod _pm) {
		return prodDao.saveProdMod(_pm);
	}

	@Override
	public boolean deleteProdMod(String _uid) {
		return prodDao.deleteProdMod(_uid);
	}

	@Override
	public ProdMod loadProdMod(String _uid) {
		return prodDao.loadProdMod(_uid);
	}
	@Override
	public ProdMod loadProdModById(String _id) {
		return prodDao.loadProdModById(_id);
	}

	@Override
	public List<ProdMod> loadProdModList(String _prodUid) {
		return prodDao.loadProdModList(_prodUid);
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ProdModItem----------------------------------
	@Override
	public boolean saveProdModItem(ProdModItem _pmi) {
		return prodDao.saveProdModItem(_pmi);
	}

	@Override
	public boolean deleteProdModItem(String _uid) {
		return prodDao.deleteProdModItem(_uid);
	}

	@Override
	public ProdModItem loadProdModItem(String _uid) {
		return prodDao.loadProdModItem(_uid);
	}

	@Override
	public ProdModItem loadProdModItem(String _prodModUid, String _prodCtlUid) {
		return prodDao.loadProdModItem(_prodModUid, _prodCtlUid);
	}

	@Override
	public ProdModItem loadProdModItem(String _prodModUid, String _prodCtlUid, String _partCfgUid) {
		return prodDao.loadProdModItem(_prodModUid, _prodCtlUid, _partCfgUid);
	}

	@Override
	public List<ProdModItem> loadProdModItemList(String _prodModUid) {
		return prodDao.loadProdModItemList(_prodModUid);
	}

}
