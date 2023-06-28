package ekp.serviceFacade.rmi.invt;

import ekp.invt.type.InvtOrderType;
import legion.serviceFacade.rmi.ObjectModelRemote;

public class InvtOrderItemRemote extends ObjectModelRemote{

	protected InvtOrderItemRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}
	
	private String ioUid; // invt order uid
	private String mbsUid; // BaterialBinStock uid (biz key) 指定「料項+儲位」

	private InvtOrderType ioType;
	private double orderQty; // 記錄異動的數量
	private double orderValue; // 記錄異動的金額
	public String getIoUid() {
		return ioUid;
	}
	void setIoUid(String ioUid) {
		this.ioUid = ioUid;
	}
	public String getMbsUid() {
		return mbsUid;
	}
	void setMbsUid(String mbsUid) {
		this.mbsUid = mbsUid;
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
	
	
	

}
