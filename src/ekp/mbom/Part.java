package ekp.mbom;

import ekp.data.MbomDataService;
import ekp.mbom.dto.PartCreateObj;
import ekp.mbom.type.PartUnit;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class Part extends ObjectModel {

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String pin; // biz key
	private String name;
	private PartUnit unit;
	
	// mm
	private boolean mmAssigned;
	private String mmUid;
	private String mmMano;
	

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

	public PartUnit getUnit() {
		return unit;
	}

	public void setUnit(PartUnit unit) {
		this.unit = unit;
	}
	
	public boolean isMmAssigned() {
		return mmAssigned;
	}

	public void setMmAssigned(boolean mmAssigned) {
		this.mmAssigned = mmAssigned;
	}

	public String getMmUid() {
		return mmUid;
	}

	public void setMmUid(String mmUid) {
		this.mmUid = mmUid;
	}

	public String getMmMano() {
		return mmMano;
	}

	public void setMmMano(String mmMano) {
		this.mmMano = mmMano;
	}

	// -------------------------------------------------------------------------------
	public String getUnitId() {
		return (getUnit() == null ? PartUnit.UNDEFINED : getUnit()).getId();
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
		p.setUnit(_dto.getUnit());
		return p.save() ? p : null;
	}
	
	boolean update(String _pin, String _name, PartUnit _unit) {
		setPin(_pin);
		setName(_name);
		setUnit(_unit);
		return save();
	} 

	boolean assignMm(String _mmUid, String _mmMano) {
		setMmAssigned(true);
		setMmUid(_mmUid);
		setMmMano(_mmMano);
		return save();
	}

	boolean revertAssignMm() {
		setMmAssigned(false);
		setMmUid("");
		setMmMano("");
		return save();
	}

}
