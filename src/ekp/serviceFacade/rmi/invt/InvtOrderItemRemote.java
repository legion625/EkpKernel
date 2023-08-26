package ekp.serviceFacade.rmi.invt;

import ekp.invt.type.InvtOrderType;
import legion.serviceFacade.rmi.ObjectModelRemote;

public class InvtOrderItemRemote extends ObjectModelRemote {

	protected InvtOrderItemRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	private String ioUid; // invt order uid
	private String mmUid;

	private InvtOrderType ioType;
	private double orderQty; // 記錄異動的數量
	private double orderValue; // 記錄異動的金額
	private boolean miAssigned;
	private String miUid;
	private boolean wrhsBinAssigned;
	private String wrhsBinUid;

	public String getIoUid() {
		return ioUid;
	}

	void setIoUid(String ioUid) {
		this.ioUid = ioUid;
	}

	public String getMmUid() {
		return mmUid;
	}

	void setMmUid(String mmUid) {
		this.mmUid = mmUid;
	}

	public InvtOrderType getIoType() {
		return ioType;
	}

	void setIoType(InvtOrderType ioType) {
		this.ioType = ioType;
	}

	public double getOrderQty() {
		return orderQty;
	}

	void setOrderQty(double orderQty) {
		this.orderQty = orderQty;
	}

	public double getOrderValue() {
		return orderValue;
	}

	void setOrderValue(double orderValue) {
		this.orderValue = orderValue;
	}

	public boolean isMiAssigned() {
		return miAssigned;
	}

	public void setMiAssigned(boolean miAssigned) {
		this.miAssigned = miAssigned;
	}

	public String getMiUid() {
		return miUid;
	}

	void setMiUid(String miUid) {
		this.miUid = miUid;
	}

	public boolean isWrhsBinAssigned() {
		return wrhsBinAssigned;
	}

	public void setWrhsBinAssigned(boolean wrhsBinAssigned) {
		this.wrhsBinAssigned = wrhsBinAssigned;
	}

	public String getWrhsBinUid() {
		return wrhsBinUid;
	}

	void setWrhsBinUid(String wrhsBinUid) {
		this.wrhsBinUid = wrhsBinUid;
	}

}
