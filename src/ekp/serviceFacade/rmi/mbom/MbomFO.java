package ekp.serviceFacade.rmi.mbom;

import ekp.mbom.ParsPart;
import ekp.mbom.ParsProc;
import ekp.mbom.Part;
import ekp.mbom.PartAcqRoutingStep;
import ekp.mbom.PartAcquisition;
import ekp.mbom.PartCfg;
import ekp.mbom.PartCfgConj;
import ekp.mbom.dto.ParsProcCreateObj;
import ekp.mbom.dto.PartAcqRoutingStepCreateObj;
import ekp.mbom.dto.PartAcquisitionCreateObj;
import ekp.mbom.dto.PartCfgCreateObj;
import ekp.mbom.dto.PartCreateObj;

public class MbomFO {

	// -------------------------------------------------------------------------------
	// -------------------------------------Part--------------------------------------
	public static PartRemote parsePartRemote(Part _obj) {
		PartRemote remote = new PartRemote(_obj.getUid(), _obj.getObjectCreateTime(), _obj.getObjectUpdateTime());
		remote.setPin(_obj.getPin());
		remote.setName(_obj.getName());
		return remote;
	}

	public static PartCreateObj parsePartCreateObj(PartCreateObjRemote _remote) {
		PartCreateObj dto = new PartCreateObj();
		dto.setPin(_remote.getPin());
		dto.setName(_remote.getName());
		return dto;
	}

	// -------------------------------------------------------------------------------
	// --------------------------------PartAcquisition--------------------------------
	public static PartAcquisitionRemote parsePartAcquisitionRemote(PartAcquisition _obj) {
		PartAcquisitionRemote remote = new PartAcquisitionRemote(_obj.getUid(), _obj.getObjectCreateTime(),
				_obj.getObjectUpdateTime());
		remote.setPartUid(_obj.getPartUid());
		remote.setPartPin(_obj.getPartPin());
		remote.setId(_obj.getId());
		remote.setName(_obj.getName());
		remote.setType(_obj.getType());
		return remote;
	}

	public static PartAcquisitionCreateObj parsePartAcquisitionCreateObj(PartAcquisitionCreateObjRemote _remote) {
		PartAcquisitionCreateObj dto = new PartAcquisitionCreateObj();
		dto.setPartUid(_remote.getPartUid());
		dto.setPartPin(_remote.getPartPin());
		dto.setId(_remote.getId());
		dto.setName(_remote.getName());
		dto.setType(_remote.getType());
		return dto;
	}

	// -------------------------------------------------------------------------------
	// ------------------------------PartAcqRoutingStep-------------------------------
	public static PartAcqRoutingStepRemote parsePartAcqRoutingStepRemote(PartAcqRoutingStep _obj) {
		PartAcqRoutingStepRemote remote = new PartAcqRoutingStepRemote(_obj.getUid(), _obj.getObjectCreateTime(),
				_obj.getObjectUpdateTime());
		remote.setPartAcqUid(_obj.getPartAcqUid());
		remote.setId(_obj.getId());
		remote.setName(_obj.getName());
		remote.setDesp(_obj.getDesp());
		return remote;
	}

	public static PartAcqRoutingStepCreateObj parsePartAcqRoutingStepCreateObj(
			PartAcqRoutingStepCreateObjRemote _remote) {
		PartAcqRoutingStepCreateObj dto = new PartAcqRoutingStepCreateObj();
		dto.setPartAcqUid(_remote.getPartAcqUid());
		dto.setId(_remote.getId());
		dto.setName(_remote.getName());
		dto.setDesp(_remote.getDesp());
		return dto;
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsProc------------------------------------
	public static ParsProcRemote parseParsProcRemote(ParsProc _obj) {
		ParsProcRemote remote = new ParsProcRemote(_obj.getUid(), _obj.getObjectCreateTime(),
				_obj.getObjectUpdateTime());
		remote.setParsUid(_obj.getParsUid());
		remote.setSeq(_obj.getSeq());
		remote.setName(_obj.getName());
		remote.setDesp(_obj.getDesp());
		remote.setAssignProc(_obj.isAssignProc());
		remote.setProcUid(_obj.getProcUid());
		remote.setProcId(_obj.getProcId());
		return remote;
	}

	public static ParsProcCreateObj parseParsProcCreateObj(ParsProcCreateObjRemote _remote) {
		ParsProcCreateObj dto = new ParsProcCreateObj();
		dto.setParsUid(_remote.getParsUid());
		dto.setSeq(_remote.getSeq());
		dto.setName(_remote.getName());
		dto.setDesp(_remote.getDesp());
		return dto;
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsPart------------------------------------
	public static ParsPartRemote parseParsPartRemote(ParsPart _obj) {
		ParsPartRemote remote = new ParsPartRemote(_obj.getUid(), _obj.getObjectCreateTime(),
				_obj.getObjectUpdateTime());
		remote.setParsUid(_obj.getParsUid());
		remote.setAssignPart(_obj.isAssignPart());
		remote.setPartUid(_obj.getPartUid());
		remote.setPartPin(_obj.getPartPin());
		remote.setPartReqQty(_obj.getPartReqQty());
		return remote;
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------PartCfg------------------------------------
	public static PartCfgRemote parsePartCfgRemote(PartCfg _obj) {
		PartCfgRemote remote = new PartCfgRemote(_obj.getUid(), _obj.getObjectCreateTime(),
				_obj.getObjectUpdateTime());
		remote.setRootPartUid(_obj.getRootPartUid());
		remote.setRootPartPin(_obj.getRootPartPin());
		remote.setStatus(_obj.getStatus());
		remote.setId(_obj.getId());
		remote.setName(_obj.getName());
		remote.setDesp(_obj.getDesp());
		return remote;
	}
	
	public static PartCfgCreateObj parsePartCfgCreateObj(PartCfgCreateObjRemote _remote) {
		PartCfgCreateObj dto = new 	PartCfgCreateObj();
		dto.setRootPartUid(_remote.getRootPartUid());
		dto.setRootPartPin(_remote.getRootPartPin());
		dto.setId(_remote.getId());
		dto.setName(_remote.getName());
		dto.setDesp(_remote.getDesp());
		return dto;
	}
	
	
	// TODO
	
	// -------------------------------------------------------------------------------
	// ----------------------------------PartCfgConj----------------------------------
	public static PartCfgConjRemote parsePartCfgConjRemote(PartCfgConj _obj) {
		PartCfgConjRemote remote = new PartCfgConjRemote(_obj.getUid(), _obj.getObjectCreateTime(),
				_obj.getObjectUpdateTime());
		remote.setPartCfgUid(_obj.getPartCfgUid());
		remote.setPartAcqUid(_obj.getPartAcqUid());
		return remote;
	}
	
	
}
