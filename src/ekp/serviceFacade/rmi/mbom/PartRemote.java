package ekp.serviceFacade.rmi.mbom;

import legion.serviceFacade.rmi.ObjectModelRemote;

public class PartRemote extends ObjectModelRemote {

	protected PartRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------attribute-----------------------------------
	private String pin;
	private String name;

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

}
