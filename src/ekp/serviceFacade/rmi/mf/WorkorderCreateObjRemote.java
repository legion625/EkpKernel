package ekp.serviceFacade.rmi.mf;

import java.io.Serializable;

import ekp.mf.fsm.WorkorderFsm;
import ekp.mf.type.WorkorderStatus;
import legion.serviceFacade.rmi.ObjectModelRemote;

public class WorkorderCreateObjRemote implements Serializable {
	private String partUid;
	private String partPin;
	private String partMmMano;
	private String partAcqUid;
	private String partAcqId;
	private double rqQty; // 需求數量

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

	public String getPartAcqUid() {
		return partAcqUid;
	}

	public void setPartAcqUid(String partAcqUid) {
		this.partAcqUid = partAcqUid;
	}

	public String getPartAcqId() {
		return partAcqId;
	}

	public void setPartAcqId(String partAcqId) {
		this.partAcqId = partAcqId;
	}

	public double getRqQty() {
		return rqQty;
	}

	public void setRqQty(double rqQty) {
		this.rqQty = rqQty;
	}

}
