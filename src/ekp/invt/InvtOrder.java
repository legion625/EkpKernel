package ekp.invt;

import ekp.common.SerialNoGenerator;
import ekp.data.InvtDataService;
import ekp.invt.dto.InvtOrderCreateObj;
import ekp.invt.fsm.InvtOrderFsm;
import ekp.invt.type.InvtOrderStatus;
import ekp.util.EkpKernelDateUtil;
import legion.DataServiceFactory;
import legion.ObjectModel;
import legion.data.ObjectSeqDataService;
import legion.data.SystemDataService;
import legion.system.SystemService;
import legion.util.DataFO;
import legion.util.DateUtil;

public class InvtOrder extends ObjectModel {

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String iosn; // invt order serial number
	private InvtOrderFsm fsm;
	private String applierId;
	private String applierName;
	private long applyTime; // apply time;
	private String remark; //

	private long apvTime; // approval time

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private InvtOrder() {
	}

	static InvtOrder newInstance() {
		InvtOrder io = new InvtOrder();
		io.configNewInstance();
		io.fsm = new InvtOrderFsm(io.getUid(), InvtOrderStatus.INIT);
		return io;
	}

	public static InvtOrder getInstance(String _uid, InvtOrderStatus _status, long _objectCreateTime,
			long _objectUpdateTime) {
		InvtOrder io = new InvtOrder();
		io.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
		io.fsm = new InvtOrderFsm(io.getUid(), _status);
		return io;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getIosn() {
		return iosn;
	}

	public void setIosn(String iosn) {
		this.iosn = iosn;
	}

	public InvtOrderStatus getStatus() {
		return fsm.getStatus();
	}

	public void setStatus(InvtOrderStatus status) {
		fsm.setStatus(status);
	}

	public String getApplierId() {
		return applierId;
	}

	public void setApplierId(String applierId) {
		this.applierId = applierId;
	}

	public String getApplierName() {
		return applierName;
	}

	public void setApplierName(String applierName) {
		this.applierName = applierName;
	}

	public long getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(long applyTime) {
		this.applyTime = applyTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getApvTime() {
		return apvTime;
	}

	public void setApvTime(long apvTime) {
		this.apvTime = apvTime;
	}

	// -------------------------------------------------------------------------------
	public int getStatusIdx() {
		return (getStatus() == null ? InvtOrderStatus.UNDEFINED : getStatus()).getIdx();
	}
	
	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		if (DataFO.isEmptyString(getIosn()))
			setIosn(SerialNoGenerator.generateIOSN());
		return DataServiceFactory.getInstance().getService(InvtDataService.class).saveInvtOrder(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(InvtDataService.class).deleteInvtOrder(getUid());
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------InvtOrder-----------------------------------
	static InvtOrder create(InvtOrderCreateObj _dto) {
		InvtOrder io = newInstance();
		io.setIosn(""); // not generated yet...
		io.setApplierId(_dto.getApplierId());
		io.setApplierName(_dto.getApplierName());
		io.setApplyTime(_dto.getApplyTime());
		io.setRemark(_dto.getRemark());
		
		// status-> to be controlled in app...
		io.setApvTime(0); // not approved yet...
		
		return io.save() ? io : null;
	}
	
	boolean toApv() {
		if (!fsm.gotoStatusToApv())
			return false;
		return save();
	}

	boolean revertToApv() {
		if (!fsm.backtoStatusInit())
			return false;
		return save();
	}

	boolean approve(long _apvTime) {
		if (!fsm.gotoStatusApproved())
			return false;
		setApvTime(_apvTime);
		return save();
	}

	boolean revertApprove() {
		if (!fsm.backtoStatusToApv())
			return false;
		setApvTime(0);
		return save();
	}
}
