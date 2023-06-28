package ekp.data.service.invt.query;

import legion.util.query.QueryParam;

public enum InvtOrderQueryParam implements QueryParam{
	/* InvtOrder:this */
	IOSN("IOSN", "IOSN"), //
	APPLIER_ID("APPLIER_ID", "Applier ID"), //
	APPLIER_NAME("APPLIER_NAME", "Applier Name"), //
	APV_TIME("APV_TIME","Approval Time"), //
	REMARK("REMARK","Remark"), //
	/* InvtOrderItem:detail */
	B_of_IOI$("B_OF_IOI$", "B_OF_IOI$"), //
	/* InvtOrderItem->MbsbStmt:detail */
	B_of_IOI_of_MBSBS$("B_OF_IOI_MBSBS$","B_OF_IOI_MBSBS$"), //
	;

	private String id, desp;

	private InvtOrderQueryParam(String id, String desp) {
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
