package ekp.mbom;

import ekp.data.MbomDataService;
import ekp.mbom.dto.PartAcqRoutingStepCreateObj;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class PartAcqRoutingStep extends ObjectModel {
	private String partAcqUid; // ref data key

	private String id; // routing step id
	private String name;
	private String desp;

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private PartAcqRoutingStep(String partAcqUid) {
		this.partAcqUid = partAcqUid;
	}

	static PartAcqRoutingStep newInstance(String partAcqUid) {
		PartAcqRoutingStep pars = new PartAcqRoutingStep(partAcqUid);
		pars.configNewInstance();
		return pars;
	}

	public static PartAcqRoutingStep getInstance(String uid, String partAcqUid, long objectCreateTime,
			long objectUpdateTime) {
		PartAcqRoutingStep pars = new PartAcqRoutingStep(partAcqUid);
		pars.configGetInstance(uid, objectCreateTime, objectUpdateTime);
		return pars;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getPartAcqUid() {
		return partAcqUid;
	}

	public void setPartAcqUid(String partAcqUid) {
		this.partAcqUid = partAcqUid;
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
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).savePartAcqRoutingStep(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).deletePartAcqRoutingStep(getUid());
	}
	
	// -------------------------------------------------------------------------------
	// ------------------------------PartAcqRoutingStep-------------------------------
	static PartAcqRoutingStep create(PartAcqRoutingStepCreateObj _dto) {
		PartAcqRoutingStep pars = newInstance(_dto.getPartAcqUid());
		pars.setId(_dto.getId());
		pars.setName(_dto.getName());
		pars.setDesp(_dto.getDesp());
		return pars.save() ? pars : null;
	}
	

}
