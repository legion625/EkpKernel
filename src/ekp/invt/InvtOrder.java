package ekp.invt;

import ekp.common.SerialNoGenerator;
import ekp.data.InvtDataService;
import ekp.invt.dto.InvtOrderCreateObj;
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
	private String applierId;
	private String applierName;
	private long apvTime; // approval time
	private String remark; //

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private InvtOrder() {
	}

	static InvtOrder newInstance() {
		InvtOrder io = new InvtOrder();
		io.configNewInstance();
		return io;
	}

	public static InvtOrder getInstance(String _uid, long _objectCreateTime, long _objectUpdateTime) {
		InvtOrder io = new InvtOrder();
		io.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
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

	public long getApvTime() {
		return apvTime;
	}

	public void setApvTime(long apvTime) {
		this.apvTime = apvTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
		io.setApvTime(_dto.getApvTime());
		io.setRemark(_dto.getRemark());
		return io.save() ? io : null;
	}
}
