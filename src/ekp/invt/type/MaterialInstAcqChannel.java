package ekp.invt.type;

import legion.type.IdxEnum;

public enum MaterialInstAcqChannel implements IdxEnum {
	UNDEFINED(0, "未定義"), //
	PURCHASING(1, "採購"), OUTSOURCING(2, "委外"), SELF_PRODUCING(3, "自製"), //
	
	;

	private int idx;
	private String name;

	private MaterialInstAcqChannel(int idx, String name) {
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
	public static MaterialInstAcqChannel get(int _idx) {
		for (MaterialInstAcqChannel miac : values())
			if (miac.idx == _idx)
				return miac;
		return UNDEFINED;
	}
}
