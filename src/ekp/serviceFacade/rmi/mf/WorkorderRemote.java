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

	private String partAcqUid;
	private String partAcqId;
	private String partAcqMmMano;

	private String partCfgUid;
	private String partCfgId;

	private double rqQty; // 需求數量

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

	public String getPartAcqUid() {
		return partAcqUid;
	}

	void setPartAcqUid(String partAcqUid) {
		this.partAcqUid = partAcqUid;
	}

	public String getPartAcqId() {
		return partAcqId;
	}

	void setPartAcqId(String partAcqId) {
		this.partAcqId = partAcqId;
	}

	public String getPartAcqMmMano() {
		return partAcqMmMano;
	}

	void setPartAcqMmMano(String partAcqMmMano) {
		this.partAcqMmMano = partAcqMmMano;
	}

	public String getPartCfgUid() {
		return partCfgUid;
	}

	void setPartCfgUid(String partCfgUid) {
		this.partCfgUid = partCfgUid;
	}

	public String getPartCfgId() {
		return partCfgId;
	}

	void setPartCfgId(String partCfgId) {
		this.partCfgId = partCfgId;
	}

	public double getRqQty() {
		return rqQty;
	}

	void setRqQty(double rqQty) {
		this.rqQty = rqQty;
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
