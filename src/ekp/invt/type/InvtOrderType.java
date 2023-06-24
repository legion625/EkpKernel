package ekp.invt.type;

import legion.type.IdxEnum;

public enum InvtOrderType implements IdxEnum {
	UNDEFINED(0, "未定義", ""), //
	I1(11, "採購入庫", ""), //
	I2(12, "工廠入庫", ""), //
	I6(16, "移動入庫", "同一倉庫內不同儲位間的移動入庫。"), //
	I7(17, "調撥入庫", "不同倉庫間的調撥入庫。"), //
	I8(18, "調整增量", "記錄因庫存調整或庫存差異的增量。"), //
	I9(19, "成品返庫", "可以包含客戶退貨、商品保固維修等。"), //

	O1(21, "退料出庫", ""), //
	O2(22, "料件領用", ""), //
	O6(26, "移動出庫", "同一倉庫內不同儲位間的移動出庫。"), //
	O7(27, "調撥出庫", "不同倉庫間的調撥出庫。"), //
	O8(28, "調整減量", "記錄因庫存調整或庫存差異的減量，例如損壞品、遺失等。"), //
	O9(29, "成品出庫", ""), //
	;

	private int idx;
	private String name;
	private String desp;

	private InvtOrderType(int idx, String name, String desp) {
		this.idx = idx;
		this.name = name;
		this.desp = desp;
	}

	public int getIdx() {
		return idx;
	}

	public String getName() {
		return name;
	}

	public String getDesp() {
		return desp;
	}

}
