package ekp.data.service.mbom.query;

import legion.util.query.QueryParam;

public enum PartCfgQueryParam implements QueryParam{
	/* PartCfg */
	ROOT_PART_UID("root_part_uid", "Root part uid"), //
	ROOT_PART_PIN("root_part_pin", "Root part pin"), //
	STATUS_IDX("status_idx", "Status"), //
	ID("id", "Id"), // 
	NAME("name", "Name"), //
	DESP("desp", "Description"), // 
	
	;

	private String id, desp;

	private PartCfgQueryParam(String id, String desp) {
		this.id = id;
		this.desp = desp;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getDesp() {
		return desp;
	}

}
