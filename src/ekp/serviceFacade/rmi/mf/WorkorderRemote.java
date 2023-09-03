package ekp.serviceFacade.rmi.mf;

import ekp.mf.type.WorkorderStatus;
import legion.serviceFacade.rmi.ObjectModelRemote;

public class WorkorderRemote extends ObjectModelRemote {
	protected WorkorderRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	private String woNo;
	private WorkorderStatus status;
	// 欲製造的part
	private String partUid;
	private String partPin;
	private String partMmMano;

	private long startWorkTime;
	private long finishWorkTime;
	private long overTime;

	public String getWoNo() {
		return woNo;
	}

	void setWoNo(String woNo) {
		this.woNo = woNo;
	}

	public WorkorderStatus getStatus() {
		return status;
	}

	void setStatus(WorkorderStatus status) {
		this.status = status;
	}

	public String getPartUid() {
		return partUid;
	}

	void setPartUid(String partUid) {
		this.partUid = partUid;
	}

	public String getPartPin() {
		return partPin;
	}

	void setPartPin(String partPin) {
		this.partPin = partPin;
	}

	public String getPartMmMano() {
		return partMmMano;
	}

	void setPartMmMano(String partMmMano) {
		this.partMmMano = partMmMano;
	}

	public long getStartWorkTime() {
		return startWorkTime;
	}

	void setStartWorkTime(long startWorkTime) {
		this.startWorkTime = startWorkTime;
	}

	public long getFinishWorkTime() {
		return finishWorkTime;
	}

	void setFinishWorkTime(long finishWorkTime) {
		this.finishWorkTime = finishWorkTime;
	}

	public long getOverTime() {
		return overTime;
	}

	void setOverTime(long overTime) {
		this.overTime = overTime;
	}
}