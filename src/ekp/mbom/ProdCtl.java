package ekp.mbom;

import legion.ObjectModel;

public class ProdCtl extends ObjectModel{
	
	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	//
	private String prodUid; //  biz key
	//
	private String id; // 型號  biz key
	private int lv; // 1:系統;2:次系統;3:模組 預設先展到第3階
	private String name; // 名稱
	private boolean req; // 是否為必要的
	
	private String parentUid;
	private String parentId;
	
	
	
}
