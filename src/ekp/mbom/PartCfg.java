package ekp.mbom;

import ekp.data.MbomDataService;
import ekp.mbom.dto.PartCfgCreateObj;
import ekp.mbom.type.PartCfgStatus;
import legion.DataServiceFactory;
import legion.Fsm;
import legion.ObjectModel;

import static ekp.mbom.type.PartCfgStatus.*;

public class PartCfg extends ObjectModel {

	// -------------------------------------------------------------------------------
	private String rootPartUid; // ref data key
	private String rootPartPin; // ref biz key

	private Fsm<PartCfgStatus> fsm;

	private String id; // biz key
	private String name;
	private String desp;

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private PartCfg(String rootPartUid, String rootPartPin) {
		this.rootPartUid = rootPartUid;
		this.rootPartPin = rootPartPin;
	}

	static PartCfg newInstance(String rootPartUid, String rootPartPin) {
		PartCfg pc = new PartCfg(rootPartUid, rootPartPin);
		pc.configNewInstance();
		pc.fsm = new Fsm<>(pc.getUid(), PartCfgStatus.INIT);
		return pc;
	}

	public static PartCfg getInstance(String uid, String rootPartUid, String rootPartPin, PartCfgStatus status,
			long objectCreateTime, long objectUpdateTime) {
		PartCfg pc = new PartCfg(rootPartUid, rootPartPin);
		pc.configGetInstance(uid, objectCreateTime, objectUpdateTime);
		pc.fsm = new Fsm<>(pc.getUid(), status);
		return pc;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getRootPartUid() {
		return rootPartUid;
	}

	public void setRootPartUid(String rootPartUid) {
		this.rootPartUid = rootPartUid;
	}

	public String getRootPartPin() {
		return rootPartPin;
	}

	public void setRootPartPin(String rootPartPin) {
		this.rootPartPin = rootPartPin;
	}

	public PartCfgStatus getStatus() {
		return fsm.getStatus();
	}

	public void setStatus(PartCfgStatus status) {
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

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	// -------------------------------------------------------------------------------
	public int getStatusIdx() {
		return (getStatus() == null ? PartCfgStatus.UNDEFINED : getStatus()).getIdx();
	}
	
	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).savePartCfg(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).deletePartCfg(this.getUid());
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
	// ------------------------------------PartCfg------------------------------------
	static PartCfg create(PartCfgCreateObj _dto) {
		PartCfg pc = newInstance(_dto.getRootPartUid(), _dto.getRootPartPin());
		// status init
		pc.setId(_dto.getId());
		pc.setName(_dto.getName());
		pc.setDesp(_dto.getDesp());
		return pc.save() ? pc : null;
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

	boolean publish() {
		if (!gotoStatusPublished())
			return false;
		return save();
	}

	boolean revertPublish() {
		if (!backtoStatusEditing())
			return false;
		return save();
	}

}
