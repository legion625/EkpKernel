package ekp.data;

import ekp.mbom.Part;
import legion.IntegrationService;

public interface MbomDataService extends IntegrationService {

	// -------------------------------------------------------------------------------
	// -------------------------------------Part--------------------------------------
	public boolean savePart(Part _p);

	public boolean deletePart(String _uid);

	public Part loadPart(String _uid);

	public Part loadPartByPin(String _pin);

}
