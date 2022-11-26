package ekp.serviceFacade.rmi.mbom;

import legion.serviceFacade.rmi.ObjectModelRemote;

public class PartAcqRoutingStepRemote extends ObjectModelRemote {

	protected PartAcqRoutingStepRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------attribute-----------------------------------
	private String partAcqUid; // ref data key

	private String seq; // routing step seq
	private String name;
	private String desp;

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getPartAcqUid() {
		return partAcqUid;
	}

	void setPartAcqUid(String partAcqUid) {
		this.partAcqUid = partAcqUid;
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

}
