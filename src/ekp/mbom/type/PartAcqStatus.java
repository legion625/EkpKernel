package ekp.mbom.type;

import legion.type.IdxEnum;

public enum PartAcqStatus implements IdxEnum {
	UNDEFINED(0, "未定義"), INIT(1, "初始"), EDITING(2, "編輯中"), PUBLISHED(9, "已發佈");

	private int idx;
	private String name;

	private PartAcqStatus(int idx, String name) {
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
	public static PartAcqStatus get(int _idx) {
		for (PartAcqStatus s : values())
			if (s.idx == _idx)
				return s;
		return UNDEFINED;
	}
}