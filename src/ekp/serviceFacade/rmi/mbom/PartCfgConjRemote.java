package ekp.serviceFacade.rmi.mbom;

import legion.serviceFacade.rmi.ObjectModelRemote;

public class PartCfgConjRemote extends ObjectModelRemote {

	protected PartCfgConjRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------attribute-----------------------------------
	private String partCfgUid;

	private String partAcqUid;

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getPartCfgUid() {
		return partCfgUid;
	}

	void setPartCfgUid(String partCfgUid) {
		this.partCfgUid = partCfgUid;
	}

	public String getPartAcqUid() {
		return partAcqUid;
	}

	void setPartAcqUid(String partAcqUid) {
		this.partAcqUid = partAcqUid;
	}

}
