package ekp.serviceFacade.rmi.sd;

import java.io.Serializable;

public class BizPartnerCreateObjRemote implements Serializable{
	private String name;
	private String ban;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}
}
