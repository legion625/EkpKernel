package ekp.serviceFacade.rmi.invt;

import ekp.invt.type.InvtOrderType;
import ekp.invt.type.IoiTargetType;
import legion.serviceFacade.rmi.ObjectModelRemote;

public class InvtOrderItemRemote extends ObjectModelRemote {

	protected InvtOrderItemRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	private String ioUid; // invt order uid
	private String mmUid;

	private InvtOrderType ioType;
	private IoiTargetType targetType;
	private String targetUid;
	private String targetBizKey;

	private double orderQty; // 記錄異動的數量
	private double orderValue; // 記錄異動的金額
	private boolean mbsbStmtCreated;

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

	public IoiTargetType getTargetType() {
		return targetType;
	}

	void setTargetType(IoiTargetType targetType) {
		this.targetType = targetType;
	}

	public String getTargetUid() {
		return targetUid;
	}

	void setTargetUid(String targetUid) {
		this.targetUid = targetUid;
	}

	public String getTargetBizKey() {
		return targetBizKey;
	}

	void setTargetBizKey(String targetBizKey) {
		this.targetBizKey = targetBizKey;
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

	public boolean isMbsbStmtCreated() {
		return mbsbStmtCreated;
	}

	void setMbsbStmtCreated(boolean mbsbStmtCreated) {
		this.mbsbStmtCreated = mbsbStmtCreated;
	}

}
