package ekp.serviceFacade.rmi.sd;

import legion.serviceFacade.rmi.ObjectModelRemote;

public class SalesOrderItemRemote extends ObjectModelRemote {

	protected SalesOrderItemRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	private String soUid;
	private String mmUid;
	private String mmMano;
	private String mmName;
	private String mmSpec;
	private double qty;
	private double value;

	private boolean allDelivered; // 是否已交貨
	private long finishDeliveredDate;

	public String getSoUid() {
		return soUid;
	}

	void setSoUid(String soUid) {
		this.soUid = soUid;
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

	public String getMmName() {
		return mmName;
	}

	void setMmName(String mmName) {
		this.mmName = mmName;
	}

	public String getMmSpec() {
		return mmSpec;
	}

	void setMmSpec(String mmSpec) {
		this.mmSpec = mmSpec;
	}

	public double getQty() {
		return qty;
	}

	void setQty(double qty) {
		this.qty = qty;
	}

	public double getValue() {
		return value;
	}

	void setValue(double value) {
		this.value = value;
	}

	public boolean isAllDelivered() {
		return allDelivered;
	}

	void setAllDelivered(boolean allDelivered) {
		this.allDelivered = allDelivered;
	}

	public long getFinishDeliveredDate() {
		return finishDeliveredDate;
	}

	void setFinishDeliveredDate(long finishDeliveredDate) {
		this.finishDeliveredDate = finishDeliveredDate;
	}

}
