package ekp.mbom;

import ekp.data.MbomDataService;
import ekp.mbom.dto.ParsProcCreateObj;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class ParsProc extends ObjectModel {
	private String parsUid; // ref data key

	//
	private String seq; //
	private String name;
	private String desp;

	// proc
	private boolean assignProc;
	private String procUid; // ref data key
	private String procId; // ref biz key

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private ParsProc(String parsUid) {
		this.parsUid = parsUid;
	}

	static ParsProc newInstance(String parsUid) {
		ParsProc parsProc = new ParsProc(parsUid);
		parsProc.configNewInstance();
		return parsProc;
	}

	public static ParsProc getInstance(String uid, String parsUid, long objectCreateTime,
			long objectUpdateTime) {
		ParsProc parsProc = new ParsProc(parsUid);
		parsProc.configGetInstance(uid, objectCreateTime, objectUpdateTime);
		return parsProc;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getParsUid() {
		return parsUid;
	}

	public void setParsUid(String parsUid) {
		this.parsUid = parsUid;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
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

	public boolean isAssignProc() {
		return assignProc;
	}

	public void setAssignProc(boolean assignProc) {
		this.assignProc = assignProc;
	}

	public String getProcUid() {
		return procUid;
	}

	public void setProcUid(String procUid) {
		this.procUid = procUid;
	}

	public String getProcId() {
		return procId;
	}

	public void setProcId(String procId) {
		this.procId = procId;
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).saveParsProc(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).deleteParsProc(getUid());
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsProc------------------------------------
	static ParsProc create(ParsProcCreateObj _dto) {
		ParsProc parsProc = newInstance(_dto.getParsUid());
		parsProc.setSeq(_dto.getSeq());
		parsProc.setName(_dto.getName());
		parsProc.setDesp(_dto.getDesp());

		// proc not assigned yet
		parsProc.setAssignProc(false);
		parsProc.setProcUid("");
		parsProc.setProcId("");

		return parsProc.save() ? parsProc : null;
	}

	boolean assignProc(String _procUid, String _procId) {
		setAssignProc(true);
		setProcUid(_procUid);
		setProcId(_procId);
		return save();
	}

	boolean revertAssignProc() {
		setAssignProc(false);
		setProcUid("");
		setProcId("");
		return save();
	}

}
