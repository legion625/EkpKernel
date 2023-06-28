package ekp.serviceFacade.rmi.invt;

import legion.serviceFacade.rmi.ObjectModelRemote;

public class WrhsBinRemote extends ObjectModelRemote {
	protected WrhsBinRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	private String wlUid;
	private String id;
	private String name;

	public String getWlUid() {
		return wlUid;
	}

	void setWlUid(String wlUid) {
		this.wlUid = wlUid;
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

}
