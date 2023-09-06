package ekp.invt.dto;

import ekp.invt.type.InvtOrderType;
import ekp.invt.type.IoiTargetType;

public class InvtOrderItemCreateObj {
	/* biz key */
	private String ioUid; // invt order uid

	private String mmUid;
	private InvtOrderType ioType;
	private IoiTargetType targetType;
	private String targetUid;
	private String targetBizKey;
	private double orderQty; // 記錄異動的數量
	private double orderValue; // 記錄異動的金額

	public String getIoUid() {
		return ioUid;
	}

	public void setIoUid(String ioUid) {
		this.ioUid = ioUid;
	}

	public String getMmUid() {
		return mmUid;
	}

	public void setMmUid(String mmUid) {
		this.mmUid = mmUid;
	}

	public InvtOrderType getIoType() {
		return ioType;
	}

	public void setIoType(InvtOrderType ioType) {
		this.ioType = ioType;
	}

	public IoiTargetType getTargetType() {
		return targetType;
	}

	public void setTargetType(IoiTargetType targetType) {
		this.targetType = targetType;
	}

	public String getTargetUid() {
		return targetUid;
	}

	public void setTargetUid(String targetUid) {
		this.targetUid = targetUid;
	}

	public String getTargetBizKey() {
		return targetBizKey;
	}

	public void setTargetBizKey(String targetBizKey) {
		this.targetBizKey = targetBizKey;
	}

	public double getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(double orderQty) {
		this.orderQty = orderQty;
	}

	public double getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(double orderValue) {
		this.orderValue = orderValue;
	}

}
