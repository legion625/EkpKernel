package ekp.serviceFacade.rmi.mbom;

import legion.serviceFacade.rmi.ObjectModelRemote;

public class ProdCtlRemote extends ObjectModelRemote {

	protected ProdCtlRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	private String id; // 型號 biz key
	private int lv; // 1:系統;2:次系統;3:模組 預設先展到第3階
	private String name; // 名稱
	private boolean req; // 是否為必要的

	private String parentUid;
	private String parentId;

	//
	private String prodUid;

	public String getId() {
		return id;
	}

	void setId(String id) {
		this.id = id;
	}

	public int getLv() {
		return lv;
	}

	void setLv(int lv) {
		this.lv = lv;
	}

	public String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	public boolean isReq() {
		return req;
	}

	void setReq(boolean req) {
		this.req = req;
	}

	public String getParentUid() {
		return parentUid;
	}

	void setParentUid(String parentUid) {
		this.parentUid = parentUid;
	}

	public String getParentId() {
		return parentId;
	}

	void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getProdUid() {
		return prodUid;
	}

	void setProdUid(String prodUid) {
		this.prodUid = prodUid;
	}

}
