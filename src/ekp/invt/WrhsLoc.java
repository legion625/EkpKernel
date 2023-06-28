package ekp.invt;

import ekp.data.InvtDataService;
import ekp.invt.dto.WrhsLocCreateObj;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class WrhsLoc extends ObjectModel {
//	UNDEFINED(0, "未定義"), //
//	WL100(101,"院區1-庫房1"), //
//	WL101(102,"院區1-庫房2"), //
//	WL102(110,"院區1-工廠1延伸庫"), //
//	WL200(200,"院區2-總庫"), //
//	WL900(900,"出貨倉庫"), //

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String id;
	private String name;

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private WrhsLoc() {
	}

	static WrhsLoc newInstance() {
		WrhsLoc wl = new WrhsLoc();
		wl.configNewInstance();
		return wl;
	}

	public static WrhsLoc getInstance(String _uid, long _objectCreateTime, long _objectUpdateTime) {
		WrhsLoc wl = new WrhsLoc();
		wl.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
		return wl;
	}

	
	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
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
		return DataServiceFactory.getInstance().getService(InvtDataService.class).saveWrhsLoc(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(InvtDataService.class).deleteWrhsLoc(getUid());
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsLoc------------------------------------
	static WrhsLoc create(WrhsLocCreateObj _dto) {
		WrhsLoc wl = newInstance();
		wl.setId(_dto.getId());
		wl.setName(_dto.getName());
		return wl.save() ? wl : null;
	}
	
	
}
