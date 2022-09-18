package ekp.data;

import java.util.List;

import ekp.mbom.Part;
import ekp.mbom.PartAcqRoutingStep;
import ekp.mbom.ParsProc;
import ekp.mbom.PartAcquisition;
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
	
	

}
