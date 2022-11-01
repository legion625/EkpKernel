package ekp.mbom;

import ekp.data.MbomDataService;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class ProdModItem extends ObjectModel {
	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String prodModUid; // 產品model識別碼 biz key
	private String prodCtlUid; // prodCtl識別碼 biz key
	//
	private boolean partCfgAssigned;
	private String partCfgUid; // PartCfg識別碼，此model對應的prodCtl所選用的構型

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private ProdModItem(String prodModUid, String prodCtlUid) {
		this.prodModUid = prodModUid;
		this.prodCtlUid = prodCtlUid;
	}

	static ProdModItem newInstance(String prodModUid, String prodCtlUid) {
		ProdModItem pmi = new ProdModItem(prodModUid, prodCtlUid);
		pmi.configNewInstance();
		return pmi;
	}

	public static ProdModItem getInstance(String uid, String prodModUid, String prodCtlUid, long objectCreateTime,
			long objectUpdateTime) {
		ProdModItem pmi = new ProdModItem(prodModUid, prodCtlUid);
		pmi.configGetInstance(uid, objectCreateTime, objectUpdateTime);
		return pmi;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getProdModUid() {
		return prodModUid;
	}

	public void setProdModUid(String prodModUid) {
		this.prodModUid = prodModUid;
	}

	public String getProdCtlUid() {
		return prodCtlUid;
	}

	public void setProdCtlUid(String prodCtlUid) {
		this.prodCtlUid = prodCtlUid;
	}

	public boolean isPartCfgAssigned() {
		return partCfgAssigned;
	}

	public void setPartCfgAssigned(boolean partCfgAssigned) {
		this.partCfgAssigned = partCfgAssigned;
	}

	public String getPartCfgUid() {
		return partCfgUid;
	}

	public void setPartCfgUid(String partCfgUid) {
		this.partCfgUid = partCfgUid;
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).saveProdModItem(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).deleteProdModItem(getUid());
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ProdModItem----------------------------------
	public static ProdModItem create(String prodModUid, String prodCtlUid) {
		ProdModItem pmi = newInstance(prodModUid, prodCtlUid);
		pmi.setPartCfgAssigned(false); // not assigned yet
		pmi.setPartCfgUid(""); // not assigned yet
		return pmi.save() ? pmi : null;
	}

	public boolean assignPartCfg(String _partCfgUid) {
		setPartCfgAssigned(true);
		setPartCfgUid(_partCfgUid);
		return save();
	}

	public boolean unassignPartCfg() {
		setPartCfgAssigned(false);
		setPartCfgUid("");
		return save();
	}
}
