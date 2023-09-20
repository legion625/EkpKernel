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
	private boolean partAcqCfgAssigned;
	private String partCfgUid; // PartCfg識別碼，此model對應的prodCtl所選用的構型
	private String partAcqUid; // PartAcq識別碼，此model對應的prodCtl所選用的獲取方式

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

	public boolean isPartAcqCfgAssigned() {
		return partAcqCfgAssigned;
	}

	public void setPartAcqCfgAssigned(boolean partAcqCfgAssigned) {
		this.partAcqCfgAssigned = partAcqCfgAssigned;
	}

	public String getPartCfgUid() {
		return partCfgUid;
	}

	public void setPartCfgUid(String partCfgUid) {
		this.partCfgUid = partCfgUid;
	}
	
	public String getPartAcqUid() {
		return partAcqUid;
	}

	public void setPartAcqUid(String partAcqUid) {
		this.partAcqUid = partAcqUid;
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
		pmi.setPartAcqCfgAssigned(false); // not assigned yet
		pmi.setPartCfgUid(""); // not assigned yet
		pmi.setPartAcqUid(""); // not assigned yet
		return pmi.save() ? pmi : null;
	}

	public boolean assignPartAcqCfg(String _partCfgUid, String _partAcqUid) {
		setPartAcqCfgAssigned(true);
		setPartCfgUid(_partCfgUid);
		setPartAcqUid(_partAcqUid);
		return save();
	}

	public boolean unassignPartAcqCfg() {
		setPartAcqCfgAssigned(false);
		setPartCfgUid("");
		setPartAcqUid("");
		return save();
	}
}
