package ekp.invt.type;

import legion.type.IdxEnum;

public enum MbsbFlowType implements IdxEnum {
	UNDEFINED(0, "未定義"), //
	IN(1, "流入"), //
	OUT(2, "流出"), //

	;

	private int idx;
	private String name;

	private MbsbFlowType(int idx, String name) {
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
	public static MbsbFlowType get(int _idx) {
		for (MbsbFlowType t : values())
			if (t.idx == _idx)
				return t;
		return UNDEFINED;
	}

}
