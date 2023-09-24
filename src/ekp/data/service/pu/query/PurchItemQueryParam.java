package ekp.data.service.pu.query;

import ekp.mbom.type.PartUnit;
import legion.util.query.QueryParam;

public enum PurchItemQueryParam implements QueryParam{
	/* PurchItem:this */
	MM_UID("MM_UID","Material Master"), //
	MM_MANO("MM_MANO", "料號"), //
	MM_NAME("MM_NAME", "品名"), //
	MM_SPECIFICATION("MM_SPECIFICATION", "規格"), //
	REF_PA("REF_PA","有參考獲取方式"), //
	REF_PA_UID("REF_PA_UID","參考獲取方式"), //
	REF_PA_TYPE_IDX("REF_PA_TYPE_IDX","參考獲取方式類型"), //
	REMARK("REMARK","備註"), //
	;

	private String id, desp;

	private PurchItemQueryParam(String id, String desp) {
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
