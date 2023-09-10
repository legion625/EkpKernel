package ekp.invt.type;

import legion.type.IdxEnum;

public enum IoiTargetType implements IdxEnum {
	UNDEFINED(0, "未定義"), //
	PURCH_ITEM(11, "採購項目"), // bizKey為PuNo
	WO(12, "工令"), // bizKey為WoNo
	//
	WOM(22, "工令料項"), // bizKey為WoNo
	SOI(29,"銷售訂單"), // bizKey為sosn
	;

	private int idx;
	private String name;

	private IoiTargetType(int idx, String name) {
		this.idx = idx;
		this.name = name;
	}

	public int getIdx() {
		return idx;
	}

	public String getName() {
		return name;
	}

	// -------------------------------------------------------------------------------
	public static IoiTargetType get(int _idx) {
		for (IoiTargetType t : values())
			if (t.idx == _idx)
				return t;
		return UNDEFINED;
	}

}
