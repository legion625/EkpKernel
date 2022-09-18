package ekp.serviceFacade.rmi.mbom;

import ekp.mbom.Part;
import ekp.mbom.PartAcquisition;
import ekp.mbom.dto.PartAcquisitionCreateObj;
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
}
