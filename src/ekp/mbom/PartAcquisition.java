package ekp.mbom;

import ekp.data.MbomDataService;
import ekp.mbom.dto.PartAcquisitionCreateObj;
import ekp.mbom.type.PartAcquisitionType;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class PartAcquisition extends ObjectModel {

	private String partUid; // ref data key
	private String partPin; // ref biz key

	private String id; // biz key
	private String name;
	private PartAcquisitionType type;

	// -------------------------------------------------------------------------------
	private PartAcquisition(String partUid, String partPin) {
		this.partUid = partUid;
		this.partPin = partPin;
	}

	protected static PartAcquisition newInstance(String partUid, String partPin) {
		PartAcquisition pa = new PartAcquisition(partUid, partPin);
		pa.configNewInstance();
		return pa;
	}

	public static PartAcquisition getInstance(String uid, String partUid, String partPin, long objectCreateTime,
			long objectUpdateTime) {
		PartAcquisition pa = new PartAcquisition(partUid, partPin);
		pa.configGetInstance(uid, objectCreateTime, objectUpdateTime);
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
	// --------------------------------PartAcquisition--------------------------------
	static PartAcquisition create(PartAcquisitionCreateObj _dto) {
		PartAcquisition pa = newInstance(_dto.getPartUid(), _dto.getPartPin());
		pa.setId(_dto.getId());
		pa.setName(_dto.getName());
		pa.setType(_dto.getType());
		if (pa.save())
			return pa;
		return null;
	}

}
