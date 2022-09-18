package ekp.serviceFacade.rmi.mbom;

import legion.serviceFacade.rmi.ObjectModelRemote;

public class ParsProcRemote extends ObjectModelRemote {
	protected ParsProcRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------attribute-----------------------------------
	private String parsUid; // ref data key

	//
	private String seq; //
	private String name;
	private String desp;

	// process
	private boolean assignProc;
	private String procUid; // ref data key
	private String procId; // ref biz key

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getParsUid() {
		return parsUid;
	}

	void setParsUid(String parsUid) {
		this.parsUid = parsUid;
	}

	public String getSeq() {
		return seq;
	}

	void setSeq(String seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	public String getDesp() {
		return desp;
	}

	void setDesp(String desp) {
		this.desp = desp;
	}

	public boolean isAssignProc() {
		return assignProc;
	}

	void setAssignProc(boolean assignProc) {
		this.assignProc = assignProc;
	}

	public String getProcUid() {
		return procUid;
	}

	void setProcUid(String procUid) {
		this.procUid = procUid;
	}

	public String getProcId() {
		return procId;
	}

	void setProcId(String procId) {
		this.procId = procId;
	}

}
