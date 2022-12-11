package ekp.serviceFacade.rmi.mbom;

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
import ekp.mbom.dto.ParsProcCreateObj;
import ekp.mbom.dto.PartAcqRoutingStepCreateObj;
import ekp.mbom.dto.PartAcquisitionCreateObj;
import ekp.mbom.dto.PartCfgCreateObj;
import ekp.mbom.dto.PartCreateObj;
import ekp.mbom.dto.PpartSkewer;
import ekp.mbom.dto.ProdCreateObj;
import ekp.mbom.dto.ProdCtlCreateObj;
import ekp.mbom.dto.ProdModCreateObj;

public class MbomFO {

	// -------------------------------------------------------------------------------
	// -------------------------------------Part--------------------------------------
	public static PartRemote parsePartRemote(Part _obj) {
		PartRemote remote = new PartRemote(_obj.getUid(), _obj.getObjectCreateTime(), _obj.getObjectUpdateTime());
		remote.setPin(_obj.getPin());
		remote.setName(_obj.getName());
		remote.setUnit(_obj.getUnit());
		return remote;
	}

	public static PartCreateObj parsePartCreateObj(PartCreateObjRemote _remote) {
		PartCreateObj dto = new PartCreateObj();
		dto.setPin(_remote.getPin());
		dto.setName(_remote.getName());
		dto.setUnit(_remote.getUnit());
		return dto;
	}

	// -------------------------------------------------------------------------------
	// --------------------------------PartAcquisition--------------------------------
	public static PartAcquisitionRemote parsePartAcquisitionRemote(PartAcquisition _obj) {
		PartAcquisitionRemote remote = new PartAcquisitionRemote(_obj.getUid(), _obj.getObjectCreateTime(),
				_obj.getObjectUpdateTime());
		remote.setPartUid(_obj.getPartUid());
		remote.setPartPin(_obj.getPartPin());
		remote.setStatus(_obj.getStatus());
		remote.setId(_obj.getId());
		remote.setName(_obj.getName());
		remote.setType(_obj.getType());
		remote.setPublishTime(_obj.getPublishTime());
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
		remote.setSeq(_obj.getSeq());
		remote.setName(_obj.getName());
		remote.setDesp(_obj.getDesp());
		return remote;
	}

	public static PartAcqRoutingStepCreateObj parsePartAcqRoutingStepCreateObj(
			PartAcqRoutingStepCreateObjRemote _remote) {
		PartAcqRoutingStepCreateObj dto = new PartAcqRoutingStepCreateObj();
		dto.setPartAcqUid(_remote.getPartAcqUid());
		dto.setSeq(_remote.getSeq());
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
	// ----------------------------------PpartSkewer----------------------------------
	public static PpartSkewerRemote parsePpartSkewerRemote(PpartSkewer _skewer) {
		PpartSkewerRemote remote = new PpartSkewerRemote();
		/* p */
		remote.setpUid(_skewer.getpUid());
		remote.setpPin(_skewer.getpPin());
		remote.setpName(_skewer.getpName());

		/* pa */
		remote.setPaUid(_skewer.getPaUid());
		remote.setPaId(_skewer.getPaId());
		remote.setPaName(_skewer.getPaName());

		/* pars */
		remote.setParsSeq(_skewer.getParsSeq());
		remote.setParsName(_skewer.getParsName());
		remote.setParsDesp(_skewer.getParsDesp());

		/* ppart */
		remote.setUid(_skewer.getUid());
		remote.setObjectCreateTime(_skewer.getObjectCreateTime());
		remote.setObjectUpdateTime(_skewer.getObjectUpdateTime());
		remote.setParsUid(_skewer.getParsUid());

		// assign part
		remote.setAssignPart(_skewer.isAssignPart());
		remote.setPartUid(_skewer.getPartUid());
		remote.setPartPin(_skewer.getPartPin());
		remote.setPartReqQty(_skewer.getPartReqQty());

		/* ppart-p */
		remote.setPartName(_skewer.getPartName());

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
		remote.setPublishTime(_obj.getPublishTime());
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
	
	// -------------------------------------------------------------------------------
	// ----------------------------------PartCfgConj----------------------------------
	public static PartCfgConjRemote parsePartCfgConjRemote(PartCfgConj _obj) {
		PartCfgConjRemote remote = new PartCfgConjRemote(_obj.getUid(), _obj.getObjectCreateTime(),
				_obj.getObjectUpdateTime());
		remote.setPartCfgUid(_obj.getPartCfgUid());
		remote.setPartAcqUid(_obj.getPartAcqUid());
		return remote;
	}
	
	// -------------------------------------------------------------------------------
	// -------------------------------------Prod--------------------------------------
	public static ProdRemote parseProdRemote(Prod _obj) {
		ProdRemote remote = new ProdRemote(_obj.getUid(), _obj.getObjectCreateTime(), _obj.getObjectUpdateTime());
		remote.setId(_obj.getId());
		remote.setName(_obj.getName());
		return remote;
	}
	
	public static ProdCreateObj parseProdCreateObj(ProdCreateObjRemote _remote) {
		ProdCreateObj dto = new ProdCreateObj();
		dto.setId(_remote.getId());
		dto.setName(_remote.getName());
		return dto;
	}
	
	// -------------------------------------------------------------------------------
	// ------------------------------------ProdCtl------------------------------------
	public static ProdCtlRemote parseProdCtlRemote(ProdCtl _obj) {
		ProdCtlRemote remote = new ProdCtlRemote(_obj.getUid(), _obj.getObjectCreateTime(), _obj.getObjectUpdateTime());
		remote.setId(_obj.getId());
		remote.setLv(_obj.getLv());
		remote.setName(_obj.getName());
		remote.setReq(_obj.isReq());
		remote.setParentUid(_obj.getParentUid());
		remote.setParentId(_obj.getParentId());
		remote.setProdUid(_obj.getProdUid());
		return remote;
	}

	public static ProdCtlCreateObj parseProdCtlCreateObj(ProdCtlCreateObjRemote _remote) {
		ProdCtlCreateObj dto = new ProdCtlCreateObj();
		dto.setId(_remote.getId());
		dto.setLv(_remote.getLv());
		dto.setName(_remote.getName());
		dto.setReq(_remote.isReq());
		return dto;
	}
	
	// -------------------------------------------------------------------------------
	// ------------------------------ProdCtlPartCfgConj-------------------------------
	public static ProdCtlPartCfgConjRemote parseProdCtlPartCfgConjRemote(ProdCtlPartCfgConj _obj) {
		ProdCtlPartCfgConjRemote remote = new ProdCtlPartCfgConjRemote(_obj.getUid(), _obj.getObjectCreateTime(), _obj.getObjectUpdateTime());
		remote.setProdCtlUid(_obj.getProdCtlUid());
		remote.setPartCfgUid(_obj.getPartCfgUid());
		return remote;
	}
	
	// -------------------------------------------------------------------------------
	// ------------------------------------ProdMod------------------------------------
	public static ProdModRemote parseProdModRemote(ProdMod _obj) {
		ProdModRemote remote = new ProdModRemote(_obj.getUid(), _obj.getObjectCreateTime(), _obj.getObjectUpdateTime());
		remote.setProdUid(_obj.getProdUid());
		remote.setId(_obj.getId());
		remote.setName(_obj.getName());
		remote.setDesp(_obj.getDesp());
		return remote;
	}
	
	public static ProdModCreateObj parseProdModCreateObj(ProdModCreateObjRemote _remote) {
		ProdModCreateObj dto = new ProdModCreateObj();
		dto.setProdUid(_remote.getProdUid());
		dto.setId(_remote.getId());
		dto.setName(_remote.getName());
		dto.setDesp(_remote.getDesp());
		return dto;
	}
	
	// -------------------------------------------------------------------------------
	// ----------------------------------ProdModItem----------------------------------
	public static ProdModItemRemote parseProdModItemRemote(ProdModItem _obj) {
		ProdModItemRemote remote = new ProdModItemRemote(_obj.getUid(), _obj.getObjectCreateTime(),
				_obj.getObjectUpdateTime());
		remote.setProdModUid(_obj.getProdModUid());
		remote.setProdCtlUid(_obj.getProdCtlUid());
		remote.setPartCfgAssigned(_obj.isPartCfgAssigned());
		remote.setPartCfgUid(_obj.getPartCfgUid());
		return remote;
	}
}
