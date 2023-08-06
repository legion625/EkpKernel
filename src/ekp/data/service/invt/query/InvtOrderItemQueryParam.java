package ekp.data.service.invt.query;

import legion.util.query.QueryParam;

public enum InvtOrderItemQueryParam implements QueryParam {
	/* InvtOrderItem:this */
	IO_UID("IO_UID", "Invt Order"), //
//	MBS_UID("MBS_UID", "Material Bin Stock"), //
	MM_UID("MM_UID", "Material Master"), //
	MI_UID("MI_UID", "Material Instance"), //
	WRHS_BIN_UID("WRHS_BINUID", "Warehouse Bin"), //
	IO_TYPE_IDX("IO_TYPE_IDX", "Invt Order Type"), //
	
	/* InvtOrder:master */
	IOSN("IOSN", "IOSN"), //
	IO_APPLIER_ID("IO_APPLIER_ID", "Applier ID"), //
	IO_APPLIER_NAME("IO_APPLIER_NAME", "Applier Name"), //
	IO_APV_TIME("IO_APV_TIME","Approval Time"), //
	IO_REMARK("IO_REMARK","Remark"), //
	
//	/* MaterialBinStock:master */
//	MBS_MM_UID("MBS_MM_UID","Material Master"), //
//	MBS_MANO("MBS_MANO","Mano"), //
//	MBS_WB_UID("MBS_WB_UID","Warehouse Bin"), //
//	// -> MaterialMaster
//	MBS_MM_NAME("MBS_MM_NAME","Material Master Name"), //
	
	/* MaterialMaster */
	MM_MANO("MM_MANO", "Mano"), //
	MM_NAME("MM_NAME","Material Master Name"), // 
	
	/* MbsbStmt:Detail */
	B_of_MBSBS$("B_OF_MBSBS$", "B_OF_MBSBS$"), //
	
	;

	private String id, desp;

	private InvtOrderItemQueryParam(String id, String desp) {
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
