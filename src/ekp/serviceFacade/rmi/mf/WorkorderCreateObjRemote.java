package ekp.serviceFacade.rmi.mf;

import java.io.Serializable;

import ekp.mf.fsm.WorkorderFsm;
import ekp.mf.type.WorkorderStatus;
import legion.serviceFacade.rmi.ObjectModelRemote;

public class WorkorderCreateObjRemote  implements Serializable{
	private String partUid;
	private String partPin;
	private String partMmMano;

	public String getPartUid() {
		return partUid;
	}

	public void setPartUid(String partUid) {
		this.partUid = partUid;
	}

	public String getPartPin() {
		return partPin;
	}

	public void setPartPin(String partPin) {
		this.partPin = partPin;
	}

	public String getPartMmMano() {
		return partMmMano;
	}

	public void setPartMmMano(String partMmMano) {
		this.partMmMano = partMmMano;
	}

}
