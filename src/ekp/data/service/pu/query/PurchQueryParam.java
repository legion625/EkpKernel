package ekp.data.service.pu.query;

import legion.util.query.QueryParam;

public enum PurchQueryParam implements QueryParam{
	/* Purch:this */
	PU_NO("PU_NO", "購案案號"), //
	TITLE("TITLE", "名稱"), //
	SUPPLIER_NAME("SUPPLIER_NAME", "供應商名稱"), //
	SUPPLIER_BAN("SUPPLIER_BAN","供應商統編"), //
	PERF_STATUS_IDX("PERF_STATUS_IDX", "履約狀態"), //
	PERF_TIME("PERF_TIME", "履約時間"), //
	/* PurchItem:detail */
	B_OF_PI$("B_OF_PI$", "B_OF_PI$"), //
	;

	private String id, desp;

	private PurchQueryParam(String id, String desp) {
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
