package ekp.serviceFacade.rmi.mbom;

import legion.serviceFacade.rmi.ObjectModelRemote;

public class PartAcqRoutingStepRemote extends ObjectModelRemote {

	protected PartAcqRoutingStepRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------attribute-----------------------------------
	private String partAcqUid; // ref data key

	private String id; // routing step id
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

	public String getId() {
		return id;
	}

	void setId(String id) {
		this.id = id;
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
