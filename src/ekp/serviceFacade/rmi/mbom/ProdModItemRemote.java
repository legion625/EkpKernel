package ekp.serviceFacade.rmi.mbom;

import legion.serviceFacade.rmi.ObjectModelRemote;

public class ProdModItemRemote extends ObjectModelRemote {

	protected ProdModItemRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	private String prodModUid; // 產品model識別碼 biz key
	private String prodCtlUid; // prodCtl識別碼 biz key
	//
	private boolean partCfgAssigned;
	private String partCfgUid; // PartCfg識別碼，此model對應的prodCtl所選用的構型

	public String getProdModUid() {
		return prodModUid;
	}

	void setProdModUid(String prodModUid) {
		this.prodModUid = prodModUid;
	}

	public String getProdCtlUid() {
		return prodCtlUid;
	}

	void setProdCtlUid(String prodCtlUid) {
		this.prodCtlUid = prodCtlUid;
	}

	public boolean isPartCfgAssigned() {
		return partCfgAssigned;
	}

	void setPartCfgAssigned(boolean partCfgAssigned) {
		this.partCfgAssigned = partCfgAssigned;
	}

	public String getPartCfgUid() {
		return partCfgUid;
	}

	void setPartCfgUid(String partCfgUid) {
		this.partCfgUid = partCfgUid;
	}

}
