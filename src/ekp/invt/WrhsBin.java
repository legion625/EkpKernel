package ekp.invt;

import ekp.data.InvtDataService;
import ekp.invt.dto.WrhsBinCreateObj;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class WrhsBin extends ObjectModel {

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	// 以wl和id作為共同biz key
	private String wlUid;
	//
	private String id;
	private String name;

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private WrhsBin(String wlUid) {
		this.wlUid = wlUid;
	}

	static WrhsBin newInstance(String _wlUid) {
		WrhsBin wb = new WrhsBin(_wlUid);
		wb.configNewInstance();
		return wb;
	}

	public static WrhsBin getInstance(String _uid, String _wlUid, long _objectCreateTime, long _objectUpdateTime) {
		WrhsBin wb = new WrhsBin(_wlUid);
		wb.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
		return wb;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getWlUid() {
		return wlUid;
	}

	public void setWlUid(String wlUid) {
		this.wlUid = wlUid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		return DataServiceFactory.getInstance().getService(InvtDataService.class).saveWrhsBin(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(InvtDataService.class).deleteWrhsBin(getUid());
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsBin------------------------------------
	static WrhsBin create(WrhsBinCreateObj _dto) {
		WrhsBin wb = newInstance(_dto.getWlUid());
		wb.setId(_dto.getId());
		wb.setName(_dto.getName());
		return wb.save() ? wb : null;
	}
}
