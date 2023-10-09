package ekp.mf;

import ekp.common.SerialNoGenerator;
import ekp.data.MfDataService;
import ekp.data.PuDataService;
import ekp.mf.dto.WorkorderCreateObj;
import ekp.mf.fsm.WorkorderFsm;
import ekp.mf.type.WorkorderStatus;
import legion.DataServiceFactory;
import legion.ObjectModel;
import legion.util.DataFO;

public class Workorder extends ObjectModel{
	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String woNo;
	private WorkorderFsm fsm;
	// 欲製造的part
	private String partUid;
	private String partPin;
	private String partAcqUid;
	private String partAcqId;
	private String partAcqMmMano;
	
	private String partCfgUid;
	private String partCfgId;
	
	private double rqQty; // 需求數量
	
	private long startWorkTime;
	private long finishWorkTime;
	private long overTime;
	
	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private Workorder() {}
	static Workorder newInstance() {
		Workorder wo = new Workorder();
		wo.configNewInstance();
		wo.fsm = new WorkorderFsm(wo.getUid(), WorkorderStatus.INIT);
		return wo;
	}
	public static Workorder getInstance(String _uid,WorkorderStatus _status, long _objectCreateTime, long _objectUpdateTime ) {
		Workorder wo = new Workorder();
		wo.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
		wo.fsm = new WorkorderFsm(wo.getUid(), _status);
		return wo;
	}
	
	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getWoNo() {
		return woNo;
	}
	public void setWoNo(String woNo) {
		this.woNo = woNo;
	}
	public WorkorderStatus getStatus() {
		return fsm.getStatus();
	}
	public void setStatus(WorkorderStatus status) {
		fsm.setStatus(status);
	}
	public String getPartUid() {
		return partUid;
	}
	public void setPartUid(String partUid) {
		this.partUid = partUid;
	}
	public String getPartPin() {
		return partPin;
	}
	public void setPartPin(String partPin) {
		this.partPin = partPin;
	}
	
	public String getPartAcqUid() {
		return partAcqUid;
	}
	public void setPartAcqUid(String partAcqUid) {
		this.partAcqUid = partAcqUid;
	}
	public String getPartAcqId() {
		return partAcqId;
	}
	public void setPartAcqId(String partAcqId) {
		this.partAcqId = partAcqId;
	}
	
	public String getPartAcqMmMano() {
		return partAcqMmMano;
	}
	public void setPartAcqMmMano(String partAcqMmMano) {
		this.partAcqMmMano = partAcqMmMano;
	}
	
	public String getPartCfgUid() {
		return partCfgUid;
	}
	public void setPartCfgUid(String partCfgUid) {
		this.partCfgUid = partCfgUid;
	}
	public String getPartCfgId() {
		return partCfgId;
	}
	public void setPartCfgId(String partCfgId) {
		this.partCfgId = partCfgId;
	}
	public double getRqQty() {
		return rqQty;
	}
	public void setRqQty(double rqQty) {
		this.rqQty = rqQty;
	}
	public long getStartWorkTime() {
		return startWorkTime;
	}
	public void setStartWorkTime(long startWorkTime) {
		this.startWorkTime = startWorkTime;
	}
	public long getFinishWorkTime() {
		return finishWorkTime;
	}
	public void setFinishWorkTime(long finishWorkTime) {
		this.finishWorkTime = finishWorkTime;
	}
	public long getOverTime() {
		return overTime;
	}
	public void setOverTime(long overTime) {
		this.overTime = overTime;
	}

	// -------------------------------------------------------------------------------
	public int getStatusIdx() {
		return (getStatus() == null ? WorkorderStatus.UNDEFINED : getStatus()).getIdx();
	}

	// -------------------------------------------------------------------------------
	@Override
	protected boolean save() {
		if(DataFO.isEmptyString(getWoNo()))
			setWoNo(SerialNoGenerator.generateWoNo());
		return DataServiceFactory.getInstance().getService(MfDataService.class).saveWorkorder(this);
	}
	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(MfDataService.class).deleteWorkorder(getUid());
	}
	
	// -------------------------------------------------------------------------------
	// -----------------------------------Workorder-----------------------------------
	static Workorder create(WorkorderCreateObj _dto) {
		Workorder wo = newInstance();
		wo.setWoNo(""); // not generated yet...
		// status-> to be controlled in app...
		wo.setPartUid(_dto.getPartUid());
		wo.setPartPin(_dto.getPartPin());
		wo.setPartAcqUid(_dto.getPartAcqUid());
		wo.setPartAcqId(_dto.getPartAcqId());
		wo.setPartAcqMmMano(_dto.getPartAcqMmMano());
		wo.setPartCfgUid(_dto.getPartCfgUid());
		wo.setPartCfgId(_dto.getPartCfgId());
		wo.setRqQty(_dto.getRqQty());
		wo.setStartWorkTime(0); // not yet...
		wo.setFinishWorkTime(0); // not yet...
		wo.setOverTime(0); // not yet
		return wo.save() ? wo : null;
	}
	
	boolean toStart() {
		if(!fsm.gotoStatusToStart())
			return false;
		return save();
	}
	boolean revertToStart() {
		if(!fsm.backtoStatusInit())
			return false;
		return save();
	}
	boolean startWork(long _startWorkTime) {
		if(!fsm.gotoStatusWorking())
			return false;
		setStartWorkTime(_startWorkTime);
		return save();
	}
	boolean revertStartWork() {
		if(!fsm.backtoStatusToStart())
			return false;
		setStartWorkTime(0);
		return save();
	}
	boolean finishWork(long _finishWorkTime) {
		if(!fsm.gotoStatusFinishWork())
			return false;
		setFinishWorkTime(System.currentTimeMillis());
		return save();
	}
	boolean revertFinishWork() {
		if(!fsm.backtoStatusWorking())
			return false;
		setFinishWorkTime(0);
		return save();
	}
	
	boolean over(long _overTime) {
		if(!fsm.gotoStatusOver())
			return false;
		setOverTime(_overTime);
		return save();
	}
	boolean revertOver() {
		if(!fsm.backtoStatusFinishWork())
			return false;
		setOverTime(0);
		return save();
	}
	
	
	
	
}
