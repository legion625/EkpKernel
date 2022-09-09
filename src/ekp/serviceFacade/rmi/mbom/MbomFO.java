package ekp.serviceFacade.rmi.mbom;

import ekp.mbom.Part;
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
}
