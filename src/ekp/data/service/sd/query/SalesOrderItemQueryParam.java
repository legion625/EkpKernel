package ekp.data.service.sd.query;

import legion.util.query.QueryParam;

public enum SalesOrderItemQueryParam implements QueryParam {
	/* SalesOrderItem:this */
	SO_UID("SO_UID", "銷售單"), //
	MM_MID("MM_MID", "料件基本檔"), //
	MM_MANO("MM_MANO", "料號"), //
	MM_NAME("MM_NAME", "品名"), //
	MM_SPEC("MM_SPEC", "規格"), //
	ALL_DELIVERED("ALL_DELIVERED", "已全數交貨"), //
	FINISH_DELIVERED_DATE("FINISH_DELIVERED_DATE", "完成交貨日期"), //
	;

	private String id, desp;

	private SalesOrderItemQueryParam(String id, String desp) {
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
