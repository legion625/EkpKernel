package ekp.invt.type;

import legion.type.IdxEnum;

public enum IoiTargetType implements IdxEnum {
	UNDEFINED(0, "未定義"), //
	PURCH_ITEM(11, "採購項目"), //
	WO(12, "工令"), //

	WOM(22, "工令料項"), //
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
