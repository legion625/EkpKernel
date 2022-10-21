package ekp.mbom;

import java.util.List;

import ekp.mbom.dto.ParsProcCreateObj;
import ekp.mbom.dto.PartAcqRoutingStepCreateObj;
import ekp.mbom.dto.PartAcquisitionCreateObj;
import ekp.mbom.dto.PartCfgCreateObj;
import ekp.mbom.dto.PartCreateObj;
import legion.BusinessService;

public interface MbomService extends BusinessService {

	// -------------------------------------------------------------------------------
	// -------------------------------------Part--------------------------------------
	public Part createPart(PartCreateObj _dto);

	public boolean deletePart(String _uid);

	public Part loadPart(String _uid);

	public Part loadPartByPin(String _pin);

	// -------------------------------------------------------------------------------
	// --------------------------------PartAcquisition--------------------------------
	public PartAcquisition createPartAcquisition(PartAcquisitionCreateObj _dto);

	public boolean deletePartAcquisition(String _uid);

	public PartAcquisition loadPartAcquisition(String _uid);

	public PartAcquisition loadPartAcquisitionById(String _id);

	public List<PartAcquisition> loadPartAcquisitionList(String _partUid);

	// -------------------------------------------------------------------------------
	// ------------------------------PartAcqRoutingStep-------------------------------
	public PartAcqRoutingStep createPartAcqRoutingStep(PartAcqRoutingStepCreateObj _dto);

	public boolean deletePartAcqRoutingStep(String _uid);

	public PartAcqRoutingStep loadPartAcqRoutingStep(String _uid);

	public PartAcqRoutingStep loadPartAcqRoutingStep(String _partAcqUid, String _id);

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

	public List<ParsPart> loadParsPartList(String _parsUid);

	public List<ParsPart> loadParsPartListByPart(String _partUid);

	public boolean parsPartAssignPart(String _uid, String _partUid, String _partPin, double _partReqQty);

	public boolean parsePartRevertAssignPart(String _uid);

	// -------------------------------------------------------------------------------
	// ------------------------------------PartCfg------------------------------------
	public PartCfg createPartCfg(PartCfgCreateObj _dto);
	public boolean deletePartCfg(String _uid);
	public PartCfg loadPartCfg(String _uid);
	public List<PartCfg> loadPartCfgList(String _rootPartUid);
	public boolean partCfgStartEditing(String _uid);
	public boolean partCfgRevertStartEditing(String _uid);
	public boolean partCfgPublish(String _uid);
	public boolean partCfgRevertPublish(String _uid);
	
	// -------------------------------------------------------------------------------
	// ----------------------------------PartCfgConj----------------------------------
	public PartCfgConj createPartCfgConj(String _partCfgUid, String _partAcqUid);
	public boolean deletePartCfgConj(String _uid);
	public PartCfgConj loadPartCfgConj(String _uid);
	public List<PartCfgConj> loadPartCfgConjList(String _partCfgUid);

}
