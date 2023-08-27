package ekp.data.service.mf.query;

import legion.util.query.QueryParam;

public enum WorkorderQueryParam implements QueryParam{
	/* Workorder:this */
	WO_NO("WO_NO","工令編號"), //
	STATUS_IDX("STATUS_IDX","狀態"), //
	PART_UID("PART_UID","零件"), //
	PART_PIN("PART_PIN","零件編號"), //
	PART_MM_MANO("PART_MM_MANO","料號"), //
	START_WORK_TIME("START_WORK_TIME","開工時間"), //
	FINISH_WORK_TIME("FINISH_WORK_TIME","完工時間"), //
	OVER_TIME("OVER_TIME","銷令時間"), //
	;

	private String id, desp;

	private WorkorderQueryParam(String id, String desp) {
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
