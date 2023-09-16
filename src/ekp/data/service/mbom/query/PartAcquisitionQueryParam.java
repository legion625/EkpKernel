package ekp.data.service.mbom.query;

import legion.util.query.QueryParam;

public enum PartAcquisitionQueryParam implements QueryParam {
	/* PartAcq */
	MM_ASSIGNED("mm_assigned", "已指定料件基本檔"), //
	MM_UID("mm_uid", "料件基本檔"), //
	MM_MANO("mm_mano", "料號"), //
	;

	private String id, desp;

	private PartAcquisitionQueryParam(String id, String desp) {
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
