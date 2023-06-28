package ekp.serviceFacade.rmi.invt;

import legion.serviceFacade.rmi.ObjectModelRemote;

public class InvtOrderRemote extends ObjectModelRemote{

	protected InvtOrderRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}
	
	private String iosn; // invt order serial number
	private String applierId;
	private String applierName;
	private long apvTime; // approval time
	private String remark; //
	public String getIosn() {
		return iosn;
	}
	void setIosn(String iosn) {
		this.iosn = iosn;
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
	public long getApvTime() {
		return apvTime;
	}
	void setApvTime(long apvTime) {
		this.apvTime = apvTime;
	}
	public String getRemark() {
		return remark;
	}
	void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
