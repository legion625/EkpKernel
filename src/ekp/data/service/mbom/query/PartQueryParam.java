package ekp.data.service.mbom.query;

import legion.util.query.QueryParam;

public enum PartQueryParam implements QueryParam {
	/* part */
	PIN("pin", "Pin"), //
	NAME("name", "Name"), // 
	UNIT_ID("unit_id", "Unit"), //
	;

	private String id, desp;

	private PartQueryParam(String id, String desp) {
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
