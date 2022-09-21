package ekp.serviceFacade.rmi.mbom;

import legion.serviceFacade.rmi.ObjectModelRemote;

public class ParsPartRemote extends ObjectModelRemote {

	protected ParsPartRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------attribute-----------------------------------
	private String parsUid; // ref data key

	// assign part
	private boolean assignPart;
	private String partUid; // ref data key
	private String partPin; // ref biz key
	private double partReqQty; // required quantity (allow decimal in certain conditions)

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getParsUid() {
		return parsUid;
	}

	void setParsUid(String parsUid) {
		this.parsUid = parsUid;
	}

	public boolean isAssignPart() {
		return assignPart;
	}

	void setAssignPart(boolean assignPart) {
		this.assignPart = assignPart;
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

	public double getPartReqQty() {
		return partReqQty;
	}

	void setPartReqQty(double partReqQty) {
		this.partReqQty = partReqQty;
	}

}
