package ekp.mbom;

import ekp.data.MbomDataService;
import ekp.mbom.dto.ProdCreateObj;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class Prod extends ObjectModel {
	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String id; // 型號 biz key
	private String name; // 名稱

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private Prod() {
	}

	static Prod newInstance() {
		Prod p = new Prod();
		p.configNewInstance();
		return p;
	}

	public static Prod getInstance(String _uid, long _objectCreateTime, long _objectUpdateTime) {
		Prod p = new Prod();
		p.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
		return p;
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
		return DataServiceFactory.getInstance().getService(MbomDataService.class).saveProd(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).deleteProd(getUid());
	}

	// -------------------------------------------------------------------------------
	// -------------------------------------Prod--------------------------------------
	public static Prod create(ProdCreateObj _dto) {
		Prod p = newInstance();
		p.setId(_dto.getId());
		p.setName(_dto.getName());
		return p.save() ? p : null;
	}

}
