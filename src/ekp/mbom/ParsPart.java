package ekp.mbom;

import ekp.data.MbomDataService;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class ParsPart extends ObjectModel {
	private String parsUid; // ref data key

	// assign part
	private boolean assignPart;
	private String partUid; // ref data key
	private String partPin; // ref biz key
	private double partReqQty; // required quantity (allow decimal in certain conditions)

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private ParsPart(String parsUid) {
		this.parsUid = parsUid;
	}

	static ParsPart newInstance(String parsUid) {
		ParsPart parsPart = new ParsPart(parsUid);
		parsPart.configNewInstance();
		return parsPart;
	}

	public static ParsPart getInstance(String uid, String parsUid, long objectCreateTime, long objectUpdateTime) {
		ParsPart parsPart = new ParsPart(parsUid);
		parsPart.configGetInstance(uid, objectCreateTime, objectUpdateTime);
		return parsPart;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getParsUid() {
		return parsUid;
	}

	public void setParsUid(String parsUid) {
		this.parsUid = parsUid;
	}

	public boolean isAssignPart() {
		return assignPart;
	}

	public void setAssignPart(boolean assignPart) {
		this.assignPart = assignPart;
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

	public double getPartReqQty() {
		return partReqQty;
	}

	public void setPartReqQty(double partReqQty) {
		this.partReqQty = partReqQty;
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).saveParsPart(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).deleteParsPart(getUid());
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsPart------------------------------------
	static ParsPart create(String _parsUid) {
		ParsPart parsPart = newInstance(_parsUid);
		// part not assinged yet
		parsPart.setAssignPart(false);
		parsPart.setPartUid("");
		parsPart.setPartPin("");
		parsPart.setPartReqQty(0);
		return parsPart.save() ? parsPart : null;
	}

	boolean assignPart(String _partUid, String _partPin, double _partReqQty) {
		setAssignPart(true);
		setPartUid(_partUid);
		setPartPin(_partPin);
		setPartReqQty(_partReqQty);
		return save();
	}

	boolean revertAssignPart() {
		setAssignPart(false);
		setPartUid("");
		setPartPin("");
		setPartReqQty(0);
		return save();
	}

}
