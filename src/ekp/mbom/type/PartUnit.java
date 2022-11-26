package ekp.mbom.type;

import legion.type.IdxEnum;

public enum PartUnit implements IdxEnum {
	UNDEFINED(0, "未定義"), //
	EA(10, "EA"), //
	GRAM(21, "Gram"), //
	MM(31, "MM"), //

	;

	private int idx;
	private String name;

	private PartUnit(int idx, String name) {
		this.idx = idx;
		this.name = name;
	}

	@Override
	public int getIdx() {
		return idx;
	}

	@Override
	public String getName() {
		return name;
	}

	// -------------------------------------------------------------------------------
	public static PartUnit get(int _idx) {
		for (PartUnit t : values())
			if (t.idx == _idx)
				return t;
		return UNDEFINED;
	}
}
