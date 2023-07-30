package ekp.data.service.invt.query;

import ekp.mbom.type.PartUnit;
import legion.util.query.QueryParam;

public enum MaterialMasterQueryParam implements QueryParam {
	/* MaterialMaster */
	MANO("MANO", "Mano"), //
	NAME("NAME","Name"), //
	SPECIFICATION("SPECIFICATION","Specification"), //
	STD_UNIT_ID("STD_UNIT_ID", "Std. Unit"), //
	
	;

	private String id, desp;
	private MaterialMasterQueryParam(String id, String desp) {
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
