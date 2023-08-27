package ekp.serviceFacade.rmi.mf;

import ekp.mf.Workorder;
import ekp.mf.dto.WorkorderCreateObj;

public class MfFO {

	// -------------------------------------------------------------------------------
	// -----------------------------------Workorder-----------------------------------
	public static WorkorderRemote parseWorkorderRemote(Workorder _obj) {
		WorkorderRemote remote = new WorkorderRemote(_obj.getUid(), _obj.getObjectCreateTime(), _obj.getObjectUpdateTime());
		remote.setWoNo(_obj.getWoNo());
		remote.setStatus(_obj.getStatus());
		remote.setPartUid(_obj.getPartUid());
		remote.setPartPin(_obj.getPartPin());
		remote.setPartMmMano(_obj.getPartMmMano());
		remote.setStartWorkTime(_obj.getStartWorkTime());
		remote.setFinishWorkTime(_obj.getFinishWorkTime());
		remote.setOverTime(_obj.getOverTime());
		return remote;
	}

	public static WorkorderCreateObj parseWorkorderCreateObj(WorkorderCreateObjRemote _remote) {
		WorkorderCreateObj dto = new WorkorderCreateObj();
		dto.setPartUid(_remote.getPartUid());
		dto.setPartPin(_remote.getPartPin());
		dto.setPartMmMano(_remote.getPartMmMano());
		return dto;
	}
}
