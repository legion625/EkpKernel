package ekp.invt.type;

import legion.type.IdxEnum;

public enum InvtOrderStatus implements IdxEnum{
	UNDEFINED(0, "未定義"), //
	INIT(1, "初始"), //
	TO_APV(2, "待登帳"), //
	APPROVED(9, "已登帳"), //
	;
	
	private int idx;
	private String name;
	
	private InvtOrderStatus(int idx, String name) {
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
	public static InvtOrderStatus get(int _idx) {
		for (InvtOrderStatus s : values())
			if (s.idx == _idx)
				return s;
		return UNDEFINED;
	}
}
