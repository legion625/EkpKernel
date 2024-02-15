package ekp.mbom.type;

import legion.type.IdxEnum;

public enum PartAcquisitionType implements IdxEnum {
	UNDEFINED(0, "未定義"), //
	PU(1, "採購"), OS(2, "委外"), SP(3, "自製"), //
	;

	private int idx;
	private String name;

	private PartAcquisitionType(int idx, String name) {
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
	public static PartAcquisitionType get(int _idx) {
		for (PartAcquisitionType t : values())
			if (t.idx == _idx)
				return t;
		return UNDEFINED;
	}

}
