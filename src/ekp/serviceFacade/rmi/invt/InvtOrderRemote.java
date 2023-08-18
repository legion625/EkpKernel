package ekp.serviceFacade.rmi.invt;

import ekp.invt.type.InvtOrderStatus;
import legion.serviceFacade.rmi.ObjectModelRemote;

public class InvtOrderRemote extends ObjectModelRemote {

	protected InvtOrderRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	private String iosn; // invt order serial number
	private InvtOrderStatus status;
	private String applierId;
	private String applierName;
	private long applyTime; // apply time;
	private String remark; //
	private long apvTime; // approval time

	public String getIosn() {
		return iosn;
	}

	void setIosn(String iosn) {
		this.iosn = iosn;
	}

	public InvtOrderStatus getStatus() {
		return status;
	}

	void setStatus(InvtOrderStatus status) {
		this.status = status;
	}

	public String getApplierId() {
		return applierId;
	}

	void setApplierId(String applierId) {
		this.applierId = applierId;
	}

	public String getApplierName() {
		return applierName;
	}

	void setApplierName(String applierName) {
		this.applierName = applierName;
	}

	public long getApplyTime() {
		return applyTime;
	}

	void setApplyTime(long applyTime) {
		this.applyTime = applyTime;
	}

	public String getRemark() {
		return remark;
	}

	void setRemark(String remark) {
		this.remark = remark;
	}

	public long getApvTime() {
		return apvTime;
	}

	void setApvTime(long apvTime) {
		this.apvTime = apvTime;
	}

}
