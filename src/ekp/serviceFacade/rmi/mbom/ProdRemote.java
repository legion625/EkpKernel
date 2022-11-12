package ekp.serviceFacade.rmi.mbom;

import legion.serviceFacade.rmi.ObjectModelRemote;

public class ProdRemote extends ObjectModelRemote {

	protected ProdRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	private String id; // 型號 biz key
	private String name; // 名稱

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
