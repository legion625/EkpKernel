package ekp.mbom;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.data.MbomDataService;
import ekp.mbom.dto.ParsProcCreateObj;
import ekp.mbom.dto.PartAcqRoutingStepCreateObj;
import ekp.mbom.dto.PartAcquisitionCreateObj;
import ekp.mbom.dto.PartCreateObj;
import legion.DataServiceFactory;

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
	public PartAcquisition loadPartAcquisitionById(String _id) {
		return dataService.loadPartAcquisitionById(_id);
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
	public boolean parsePartRevertAssignPart(String _uid) {
		return loadParsPart(_uid).revertAssignPart();
	}

}
