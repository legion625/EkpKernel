package ekp.mbom.dto;

import legion.ObjectSkewer;

public class PpartSkewer extends ObjectSkewer {
	/* p */
	private String pUid;
	private String pPin;
	private String pName;

	/* pa */
	private String paUid;
	private String paId;
	private String paName;

	/* pars */
	private String parsSeq;
	private String parsName;
	private String parsDesp;

	/* ppart */
	private String uid;
	private long objectCreateTime;
	private long objectUpdateTime;
	private String parsUid; // ref data key

	// assign part
	private boolean assignPart;
	private String partUid; // ref data key
	private String partPin; // ref biz key
	private double partReqQty; // required quantity (allow decimal in certain conditions)

	/* ppart-p */
	private String partName;

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getpUid() {
		return pUid;
	}

	public void setpUid(String pUid) {
		this.pUid = pUid;
	}

	public String getpPin() {
		return pPin;
	}

	public void setpPin(String pPin) {
		this.pPin = pPin;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getPaUid() {
		return paUid;
	}

	public void setPaUid(String paUid) {
		this.paUid = paUid;
	}

	public String getPaId() {
		return paId;
	}

	public void setPaId(String paId) {
		this.paId = paId;
	}

	public String getPaName() {
		return paName;
	}

	public void setPaName(String paName) {
		this.paName = paName;
	}

	public String getParsSeq() {
		return parsSeq;
	}

	public void setParsSeq(String parsSeq) {
		this.parsSeq = parsSeq;
	}

	public String getParsName() {
		return parsName;
	}

	public void setParsName(String parsName) {
		this.parsName = parsName;
	}

	public String getParsDesp() {
		return parsDesp;
	}

	public void setParsDesp(String parsDesp) {
		this.parsDesp = parsDesp;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public long getObjectCreateTime() {
		return objectCreateTime;
	}

	public void setObjectCreateTime(long objectCreateTime) {
		this.objectCreateTime = objectCreateTime;
	}

	public long getObjectUpdateTime() {
		return objectUpdateTime;
	}

	public void setObjectUpdateTime(long objectUpdateTime) {
		this.objectUpdateTime = objectUpdateTime;
	}

	public String getParsUid() {
		return parsUid;
	}

	public void setParsUid(String parsUid) {
		this.parsUid = parsUid;
	}

	public boolean isAssignPart() {
		return assignPart;
	}

	public void setAssignPart(boolean assignPart) {
		this.assignPart = assignPart;
	}

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

	public double getPartReqQty() {
		return partReqQty;
	}

	public void setPartReqQty(double partReqQty) {
		this.partReqQty = partReqQty;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

}
