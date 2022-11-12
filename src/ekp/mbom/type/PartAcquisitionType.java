package ekp.mbom.type;

import legion.type.IdxEnum;

public enum PartAcquisitionType implements IdxEnum {
	UNDEFINED(0, "未定義"), //
	PURCHASING(1, "採購"), OUTSOURCING(2, "委外"), SELF_PRODUCING(3, "自製"), //
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
