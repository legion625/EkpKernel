package ekp.data.service.sd.query;

import legion.util.query.QueryParam;

public enum SalesOrderQueryParam implements QueryParam{
	/* SalesOrder:this */
	SOSN("SOSN","序號"), //
	TITLE("TITLE", "名稱"), //
	CUSTOMER_NAME("CUSTOMER_NAME", "客戶名稱"), //
	CUSTOMER_BAN("CUSTOMER_BAN","客戶統編"), //
	SALER_ID("SALER_ID", "銷售人ID"), //
	SALER_NAME("SALER_NAME", "銷售人姓名"), //
	SALE_DATE("SALE_DATE", "銷售日期"), //
	/* SalesOrderItem:detail */
	B_OF_SOI$("B_OF_SOI$", "B_OF_SOI$"), //
	;
	
	
	private String id, desp;

	private SalesOrderQueryParam(String id, String desp) {
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
