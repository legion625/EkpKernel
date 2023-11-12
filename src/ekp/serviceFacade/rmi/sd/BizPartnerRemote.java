package ekp.serviceFacade.rmi.sd;

import legion.serviceFacade.rmi.ObjectModelRemote;

public class BizPartnerRemote extends ObjectModelRemote {

	protected BizPartnerRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	private String bpsn;
	private String name;
	private String ban;

	public String getBpsn() {
		return bpsn;
	}

	void setBpsn(String bpsn) {
		this.bpsn = bpsn;
	}

	public String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	public String getBan() {
		return ban;
	}

	void setBan(String ban) {
		this.ban = ban;
	}

}
