package ekp.mbom;

import ekp.data.MbomDataService;
import ekp.mbom.dto.PartCreateObj;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class Part extends ObjectModel {

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String pin; // biz key
	private String name;

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private Part() {
	}

	static Part newInstance() {
		Part p = new Part();
		p.configNewInstance();
		return p;
	}

	public static Part getInstance(String _uid, long _objectCreateTime, long _objectUpdateTime) {
		Part p = new Part();
		p.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
		return p;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
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
		return DataServiceFactory.getInstance().getService(MbomDataService.class).savePart(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).deletePart(getUid());
	}

	// -------------------------------------------------------------------------------
	// -------------------------------------Part--------------------------------------
	static Part create(PartCreateObj _dto) {
		Part p = newInstance();
		p.setPin(_dto.getPin());
		p.setName(_dto.getName());
		return p.save() ? p : null;
	}

}
