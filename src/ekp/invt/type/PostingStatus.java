package ekp.invt.type;

import legion.type.IdxEnum;

public enum PostingStatus implements IdxEnum{
	UNDEFINED(0, "未定義"), //
	INIT(1, "初始"), //
	TO_POST(2, "待登帳"), //
	POSTED(9,"已登帳"), //
	;
	
	private int idx;
	private String name;
	
	private PostingStatus(int idx, String name) {
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
	public static PostingStatus get(int _idx) {
		for(PostingStatus s: values())
			if(s.idx == _idx)
				return s;
		return UNDEFINED;
	}

}
