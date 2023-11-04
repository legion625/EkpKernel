package ekp.mbom.type;

import legion.type.IdEnum;

/**
 * 參考經濟部國際貿易局的計量單位列表
 * @author Min-Hua Chao
 *
 */
public enum PartUnit implements IdEnum {
	UNDEFINED("UNDEFINED","未定義", "Undefined"), //
	CMK("CMK","平方公分","Squre Centimeter"), //
	EAC("EAC","每個", "Each"), //
	GRM("GRM","公克", "Gram"), //
	KGM("KGM","公斤", "Kilogram"), //
	MMT("MMT","公釐", "Millimeter"), //
	SHE("SHE","張, 片","Sheet"), //
	SPL("SPL","捲，軸","Spool"), //
	;

	private String id;
	private String chtName;
	private String engName;

	private PartUnit(String id, String chtName, String engName) {
		this.id = id;
		this.chtName = chtName;
		this.engName = engName;
	}

	public String getId() {
		return id;
	}

	public String getChtName() {
		return chtName;
	}

	public String getEngName() {
		return engName;
	}
	

	// -------------------------------------------------------------------------------
	@Override
	public String getName() {
		return getChtName();
	}

	
	// -------------------------------------------------------------------------------
	public static PartUnit get(String _id) {
		for (PartUnit t : values())
			if (t.id.equalsIgnoreCase(_id))
				return t;
		return UNDEFINED;
	}

	
	
}
