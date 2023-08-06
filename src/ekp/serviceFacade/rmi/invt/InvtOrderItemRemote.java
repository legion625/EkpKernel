package ekp.serviceFacade.rmi.invt;

import ekp.invt.type.InvtOrderType;
import legion.serviceFacade.rmi.ObjectModelRemote;

public class InvtOrderItemRemote extends ObjectModelRemote{

	protected InvtOrderItemRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}
	
	private String ioUid; // invt order uid
//	private String mbsUid; // BaterialBinStock uid (biz key) 指定「料項+儲位」
	private String mmUid;
	private String miUid;
	private String wrhsBinUid;

	private InvtOrderType ioType;
	private double orderQty; // 記錄異動的數量
	private double orderValue; // 記錄異動的金額
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
	public String getMiUid() {
		return miUid;
	}
	void setMiUid(String miUid) {
		this.miUid = miUid;
	}
	public String getWrhsBinUid() {
		return wrhsBinUid;
	}
	void setWrhsBinUid(String wrhsBinUid) {
		this.wrhsBinUid = wrhsBinUid;
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
