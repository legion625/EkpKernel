package ekp.serviceFacade.rmi.mbom;

import java.io.Serializable;

public class PartCreateObjRemote implements Serializable {
	private String pin;
	private String name;

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
}