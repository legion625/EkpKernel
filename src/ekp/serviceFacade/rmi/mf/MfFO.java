package ekp.serviceFacade.rmi.mf;

import ekp.mf.Workorder;
import ekp.mf.WorkorderMaterial;
import ekp.mf.dto.WorkorderCreateObj;
import ekp.mf.dto.WorkorderMaterialCreateObj;

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
	
	// -------------------------------------------------------------------------------
	// -------------------------------WorkorderMaterial-------------------------------
	public static WorkorderMaterialRemote parseWorkorderMaterialRemote(WorkorderMaterial _obj) {
		WorkorderMaterialRemote remote = new WorkorderMaterialRemote(_obj.getUid(), _obj.getObjectCreateTime(), _obj.getObjectUpdateTime());
		remote.setWoUid(_obj.getWoUid());
		remote.setWoNo(_obj.getWoNo());
		remote.setMmUid(_obj.getMmUid());
		remote.setMmMano(_obj.getMmMano());
		remote.setMmName(_obj.getMmName());
		remote.setQty0(_obj.getQty0());
		remote.setQty1(_obj.getQty1());
		return remote;
	}
	
	public static WorkorderMaterialCreateObj parseWorkorderMaterialCreateObj(WorkorderMaterialCreateObjRemote _remote) {
		WorkorderMaterialCreateObj dto = new WorkorderMaterialCreateObj();
		dto.setWoUid(_remote.getWoUid());
		dto.setWoNo(_remote.getWoNo());
		dto.setMmUid(_remote.getMmUid());
		dto.setMmMano(_remote.getMmMano());
		dto.setMmName(_remote.getMmName());
		return dto;
	}
}
