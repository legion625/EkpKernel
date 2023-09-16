package ekp.serviceFacade.rmi.mbom;

import ekp.mbom.type.PartAcqStatus;
import ekp.mbom.type.PartAcquisitionType;
import legion.serviceFacade.rmi.ObjectModelRemote;

public class PartAcquisitionRemote extends ObjectModelRemote {

	protected PartAcquisitionRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------attribute-----------------------------------
	private String partUid; // ref data key
	private String partPin; // ref biz key

	private PartAcqStatus status;

	private String id; // biz key
	private String name;
	private PartAcquisitionType type;

	// mm
	private boolean mmAssigned;
	private String mmUid;
	private String mmMano;

	private long publishTime;

	private double refUnitCost;

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getPartUid() {
		return partUid;
	}

	void setPartUid(String partUid) {
		this.partUid = partUid;
	}

	public String getPartPin() {
		return partPin;
	}

	void setPartPin(String partPin) {
		this.partPin = partPin;
	}

	public PartAcqStatus getStatus() {
		return status;
	}

	void setStatus(PartAcqStatus status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	public PartAcquisitionType getType() {
		return type;
	}

	void setType(PartAcquisitionType type) {
		this.type = type;
	}

	public boolean isMmAssigned() {
		return mmAssigned;
	}

	void setMmAssigned(boolean mmAssigned) {
		this.mmAssigned = mmAssigned;
	}

	public String getMmUid() {
		return mmUid;
	}

	void setMmUid(String mmUid) {
		this.mmUid = mmUid;
	}

	public String getMmMano() {
		return mmMano;
	}

	void setMmMano(String mmMano) {
		this.mmMano = mmMano;
	}

	public long getPublishTime() {
		return publishTime;
	}

	void setPublishTime(long publishTime) {
		this.publishTime = publishTime;
	}

	public double getRefUnitCost() {
		return refUnitCost;
	}

	void setRefUnitCost(double refUnitCost) {
		this.refUnitCost = refUnitCost;
	}

}
