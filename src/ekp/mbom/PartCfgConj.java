package ekp.mbom;

import ekp.data.MbomDataService;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class PartCfgConj extends ObjectModel {
	private String partCfgUid;
	
	private String partAcqUid;

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private PartCfgConj(String partCfgUid) {
		this.partCfgUid = partCfgUid;
	}

	static PartCfgConj newInstance(String partCfgUid) {
		PartCfgConj pcc = new PartCfgConj(partCfgUid);
		pcc.configNewInstance();
		return pcc;
	}

	public static PartCfgConj getInstance(String uid,String partCfgUid, long objectCreateTime, long objectUpdateTime) {
		PartCfgConj pcc = new PartCfgConj(partCfgUid);
		pcc.configGetInstance(uid, objectCreateTime, objectUpdateTime);
		return pcc;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
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
		return DataServiceFactory.getInstance().getService(MbomDataService.class).savePartCfgConj(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).deletePartCfgConj(getUid());
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------PartCfgConj----------------------------------
	static PartCfgConj create(String _partCfgUid, String _partAcqUid) {
		PartCfgConj pcc = newInstance(_partCfgUid);
		pcc.setPartAcqUid(_partAcqUid);
		return pcc.save() ? pcc : null;
	}
}
