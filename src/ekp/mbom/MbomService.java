package ekp.mbom;

import java.util.List;

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
}
