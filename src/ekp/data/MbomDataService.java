package ekp.data;

import java.util.List;

import ekp.mbom.Part;
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

}
