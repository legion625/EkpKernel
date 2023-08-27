package ekp.mf.type;

import legion.type.IdxEnum;

public enum WorkorderStatus implements IdxEnum {
	UNDEFINED(0, "未定義"), INIT(1, "初始"), TO_START(2, "未開工"), WORKING(5, "加工中"), FINISH_WORK(7, "完工"), OVER(9, "銷令"), //
	;

	private int idx;
	private String name;

	private WorkorderStatus(int idx, String name) {
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
	public static WorkorderStatus get(int _idx) {
		for (WorkorderStatus s : values())
			if (s.idx == _idx)
				return s;
		return UNDEFINED;
	}
}
