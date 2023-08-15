package ekp.pu.type;

import legion.type.IdxEnum;

/**
 * 購案覆約狀態
 * @author Min-Hua Chao
 *
 */
public enum PurchPerfStatus implements IdxEnum{
	UNDEFINED(0, "未定義"), //
	INIT(1, "初始"), //
	TO_PERF(2, "待履約"), //
	// 目前都是「一次履約」的假設。未來若要擴充，則可考慮調整「待覆約」的定義，或新增一「PERFING（履約中）」狀態。
	PERFED(9, "已履約"), //
	;

	private int idx;
	private String name;
	
	private PurchPerfStatus(int idx, String name) {
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
	public static PurchPerfStatus get(int _idx) {
		for(PurchPerfStatus s: values())
			if(s.idx ==_idx)
				return s;
		return UNDEFINED;
	}
}
