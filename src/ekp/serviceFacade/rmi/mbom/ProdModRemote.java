package ekp.serviceFacade.rmi.mbom;

import legion.serviceFacade.rmi.ObjectModelRemote;

public class ProdModRemote extends ObjectModelRemote {

	protected ProdModRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	private String prodUid; // 對應的產品項 biz key
	//
	private String id; // 識別碼 biz key
	private String name;
	private String desp;

	public String getProdUid() {
		return prodUid;
	}

	void setProdUid(String prodUid) {
		this.prodUid = prodUid;
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
