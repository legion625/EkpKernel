package ekp.invt.type;

import legion.type.IdxEnum;

public enum MaterialInstSrcStatus implements IdxEnum{
	UNDEFINED(0, "未定義"), //
	INIT(1, "初始"), //
	ASSIGNING(5,"指定中"), //
	NONE(8,"無"), //
	FINISH_ASSIGNED(9,"完成指定"), //
	;
	
	private int idx;
	private String name;
	
	private MaterialInstSrcStatus(int idx, String name) {
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
	public static MaterialInstSrcStatus get(int _idx) {
		for(MaterialInstSrcStatus s: values())
			if(s.idx == _idx)
				return s;
		return UNDEFINED;
	}
	
	
}
