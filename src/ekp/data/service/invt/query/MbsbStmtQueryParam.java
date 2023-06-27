package ekp.data.service.invt.query;

import legion.util.query.QueryParam;

public enum MbsbStmtQueryParam implements QueryParam{
	/* MbsbStmt:this */
	MBSB_FLOW_TYPE_IDX("MBSB_FLOW_TYPE_IDX","Flow Type"), //
	POSTING_STATUS_IDX("POSTING_STATUS_IDX","Posting Status"), //
	POSTING_TIME("POSTING_TIME","Posting Time"), //
	
	/* MaterialBinStockBatch:master */
	// -> MaterialBinStock
	MBS_MANO("MBS_MANO", "Mano"), //
	
	// -> MaterialInst
	MISN("MISN","MISN"), //
	MIAC_IDX("MIAC_IDX","Mat. Inst. Acq. Channel"), //
	
	;

	private String id, desp;

	private MbsbStmtQueryParam(String id, String desp) {
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
