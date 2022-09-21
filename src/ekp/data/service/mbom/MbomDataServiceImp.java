package ekp.data.service.mbom;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.data.MbomDataService;
import ekp.mbom.ParsPart;
import ekp.mbom.ParsProc;
import ekp.mbom.Part;
import ekp.mbom.PartAcqRoutingStep;
import ekp.mbom.PartAcquisition;

public class MbomDataServiceImp implements MbomDataService {
	private Logger log = LoggerFactory.getLogger(MbomDataServiceImp.class);

	private String source;

	// dao
	private PartDao partDao;

	@Override
	public void register(Map<String, String> _params) {
		if (_params == null || _params.isEmpty())
			return;

		source = _params.get("source");

		// dao
		partDao = new PartDao(source);
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
	public PartAcquisition loadPartAcquisitionById(String _id) {
		return partDao.loadPartAcquisitionById(_id);
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
	public PartAcqRoutingStep loadPartAcqRoutingStep(String _partAcqUid, String _id) {
		return partDao.loadPartAcqRoutingStep(_partAcqUid, _id);
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
	public List<ParsPart> loadParsPartList(String _parsUid) {
		return partDao.loadParsPartList(_parsUid);
	}

	@Override
	public List<ParsPart> loadParsPartListByPart(String _partUid) {
		return partDao.loadParsPartListByPart(_partUid);
	}

}
