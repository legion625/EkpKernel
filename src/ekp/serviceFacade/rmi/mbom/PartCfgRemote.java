package ekp.serviceFacade.rmi.mbom;

import ekp.mbom.type.PartCfgStatus;
import legion.serviceFacade.rmi.ObjectModelRemote;

public class PartCfgRemote extends ObjectModelRemote {
	protected PartCfgRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	private String rootPartUid; // ref data key
	private String rootPartPin; // ref biz key

	private PartCfgStatus status;

	private String id; // biz key
	private String name;
	private String desp;

	private long publishTime;
	
	public String getRootPartUid() {
		return rootPartUid;
	}

	void setRootPartUid(String rootPartUid) {
		this.rootPartUid = rootPartUid;
	}

	public String getRootPartPin() {
		return rootPartPin;
	}

	void setRootPartPin(String rootPartPin) {
		this.rootPartPin = rootPartPin;
	}

	public PartCfgStatus getStatus() {
		return status;
	}

	void setStatus(PartCfgStatus status) {
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

	public String getDesp() {
		return desp;
	}

	void setDesp(String desp) {
		this.desp = desp;
	}

	public long getPublishTime() {
		return publishTime;
	}

	void setPublishTime(long publishTime) {
		this.publishTime = publishTime;
	}

}
