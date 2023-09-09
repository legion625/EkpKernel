package ekp.serviceFacade.rmi.sd;

import legion.serviceFacade.rmi.ObjectModelRemote;

public class SalesOrderRemote extends ObjectModelRemote {
	protected SalesOrderRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	private String sosn;
	private String title;
	private String customerName;
	private String customerBan;

	private String salerId;
	private String salerName;
	private long saleDate;

	public String getSosn() {
		return sosn;
	}

	void setSosn(String sosn) {
		this.sosn = sosn;
	}

	public String getTitle() {
		return title;
	}

	void setTitle(String title) {
		this.title = title;
	}

	public String getCustomerName() {
		return customerName;
	}

	void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerBan() {
		return customerBan;
	}

	void setCustomerBan(String customerBan) {
		this.customerBan = customerBan;
	}

	public String getSalerId() {
		return salerId;
	}

	void setSalerId(String salerId) {
		this.salerId = salerId;
	}

	public String getSalerName() {
		return salerName;
	}

	void setSalerName(String salerName) {
		this.salerName = salerName;
	}

	public long getSaleDate() {
		return saleDate;
	}

	void setSaleDate(long saleDate) {
		this.saleDate = saleDate;
	}

}
