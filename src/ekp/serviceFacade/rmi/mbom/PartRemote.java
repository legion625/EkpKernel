package ekp.serviceFacade.rmi.mbom;

import ekp.mbom.type.PartUnit;
import legion.serviceFacade.rmi.ObjectModelRemote;

public class PartRemote extends ObjectModelRemote {

	protected PartRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------attribute-----------------------------------
	private String pin;
	private String name;
	private PartUnit unit;
	// mm
	private boolean mmAssigned;
	private String mmUid;
	private String mmMano;

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getPin() {
		return pin;
	}

	void setPin(String pin) {
		this.pin = pin;
	}

	public String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	public PartUnit getUnit() {
		return unit;
	}

	void setUnit(PartUnit unit) {
		this.unit = unit;
	}

	public boolean isMmAssigned() {
		return mmAssigned;
	}

	void setMmAssigned(boolean mmAssigned) {
		this.mmAssigned = mmAssigned;
	}

	public String getMmUid() {
		return mmUid;
	}

	void setMmUid(String mmUid) {
		this.mmUid = mmUid;
	}

	public String getMmMano() {
		return mmMano;
	}

	void setMmMano(String mmMano) {
		this.mmMano = mmMano;
	}

}
