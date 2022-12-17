package ekp.mbom;

import ekp.data.MbomDataService;
import ekp.mbom.dto.PartAcquisitionCreateObj;
import ekp.mbom.type.PartAcqStatus;
import ekp.mbom.type.PartAcquisitionType;
import ekp.mbom.type.PartCfgStatus;
import legion.DataServiceFactory;
import legion.Fsm;
import legion.ObjectModel;

import static ekp.mbom.type.PartAcqStatus.*;

public class PartAcquisition extends ObjectModel {

	private String partUid; // ref data key
	private String partPin; // ref biz key

	private Fsm<PartAcqStatus> fsm;

	private String id; // biz key
	private String name;
	private PartAcquisitionType type;

	private long publishTime;
	
	private double refUnitCost;

	// -------------------------------------------------------------------------------
	private PartAcquisition(String partUid, String partPin) {
		this.partUid = partUid;
		this.partPin = partPin;
	}

	static PartAcquisition newInstance(String partUid, String partPin) {
		PartAcquisition pa = new PartAcquisition(partUid, partPin);
		pa.configNewInstance();
		pa.fsm = new Fsm<>(pa.getUid(), PartAcqStatus.INIT);
		return pa;
	}

	public static PartAcquisition getInstance(String uid, String partUid, String partPin, PartAcqStatus status,
			long objectCreateTime, long objectUpdateTime) {
		PartAcquisition pa = new PartAcquisition(partUid, partPin);
		pa.configGetInstance(uid, objectCreateTime, objectUpdateTime);
		pa.fsm = new Fsm<>(pa.getUid(), status);
		return pa;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
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

	public PartAcqStatus getStatus() {
		return fsm.getStatus();
	}

	public void setStatus(PartAcqStatus status) {
		fsm.setStatus(status);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PartAcquisitionType getType() {
		return type;
	}

	public void setType(PartAcquisitionType type) {
		this.type = type;
	}

	public long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(long publishTime) {
		this.publishTime = publishTime;
	}

	public double getRefUnitCost() {
		return refUnitCost;
	}

	public void setRefUnitCost(double refUnitCost) {
		this.refUnitCost = refUnitCost;
	}

	// -------------------------------------------------------------------------------
	public int getTypeIdx() {
		return (getType() == null ? PartAcquisitionType.UNDEFINED : getType()).getIdx();
	}

	public int getStatusIdx() {
		return (getStatus() == null ? PartAcqStatus.UNDEFINED : getStatus()).getIdx();
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).savePartAcquisition(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).deletePartAcquisition(getUid());
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------status-------------------------------------
	private boolean backtoStatusInit() {
		return fsm.transfer(EDITING, INIT);
	}

	private boolean gotoStatusEditing() {
		return fsm.transfer(INIT, EDITING);
	}

	private boolean backtoStatusEditing() {
		return fsm.transfer(PUBLISHED, EDITING);
	}

	private boolean gotoStatusPublished() {
		return fsm.transfer(EDITING, PUBLISHED);
	}

	// -------------------------------------------------------------------------------
	// --------------------------------PartAcquisition--------------------------------
	static PartAcquisition create(PartAcquisitionCreateObj _dto) {
		PartAcquisition pa = newInstance(_dto.getPartUid(), _dto.getPartPin());
		// status init
		pa.setId(_dto.getId());
		pa.setName(_dto.getName());
		pa.setType(_dto.getType());
		pa.setPublishTime(0); // not publish yet
		pa.setRefUnitCost(0.0); // not set yet

		return pa.save() ? pa : null;
	}

	boolean startEditing() {
		if (!gotoStatusEditing())
			return false;
		return save();
	}

	boolean revertStartEditing() {
		if (!backtoStatusInit())
			return false;
		return save();
	}

	boolean publish(long _publishTime) {
		if (!gotoStatusPublished())
			return false;
		setPublishTime(_publishTime);
		return save();
	}

	boolean revertPublish() {
		if (!backtoStatusEditing())
			return false;
		setPublishTime(0);
		return save();
	}
	
	boolean updateRefUnitCost(double _refUnitCost) {
		this.refUnitCost = _refUnitCost;
		return save();
	}

}
