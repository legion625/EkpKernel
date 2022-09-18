package ekp.mbom;

import java.util.List;

import ekp.mbom.dto.ParsProcCreateObj;
import ekp.mbom.dto.PartAcqRoutingStepCreateObj;
import ekp.mbom.dto.PartAcquisitionCreateObj;
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

	public List<PartAcquisition> loadPartAcquisitionList(String  _partUid);
	
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
	
}
