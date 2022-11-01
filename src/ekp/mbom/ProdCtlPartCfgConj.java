package ekp.mbom;

import ekp.data.MbomDataService;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class ProdCtlPartCfgConj extends ObjectModel {
	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String prodCtlUid; // 標的產品型錄prodCtl biz key
	private String partCfgUid; // 此產品型錄對應對產品構型PartCfg biz key

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private ProdCtlPartCfgConj(String prodCtlUid, String partCfgUid) {
		this.prodCtlUid = prodCtlUid;
		this.partCfgUid = partCfgUid;
	}

	static ProdCtlPartCfgConj newInstance(String prodCtlUid, String partCfgUid) {
		ProdCtlPartCfgConj pcpcc = new ProdCtlPartCfgConj(prodCtlUid, partCfgUid);
		pcpcc.configNewInstance();
		return pcpcc;
	}

	public static ProdCtlPartCfgConj getInstance(String uid, String prodCtlUid, String partCfgUid,
			long objectCreateTime, long objectUpdateTime) {
		ProdCtlPartCfgConj pcpcc = new ProdCtlPartCfgConj(prodCtlUid, partCfgUid);
		pcpcc.configGetInstance(uid, objectCreateTime, objectUpdateTime);
		return pcpcc;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getProdCtlUid() {
		return prodCtlUid;
	}

	public void setProdCtlUid(String prodCtlUid) {
		this.prodCtlUid = prodCtlUid;
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
		return DataServiceFactory.getInstance().getService(MbomDataService.class).saveProdCtlPartCfgConj(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).deleteProdCtlPartCfgConj(getUid());
	}

	// -------------------------------------------------------------------------------
	// ------------------------------ProdCtlPartCfgConj-------------------------------
	public static ProdCtlPartCfgConj create(String _prodCtlUid, String _partCfgUid) {
		ProdCtlPartCfgConj pcpcc = newInstance(_prodCtlUid, _partCfgUid);
		return pcpcc.save() ? pcpcc : null;
	}

}
