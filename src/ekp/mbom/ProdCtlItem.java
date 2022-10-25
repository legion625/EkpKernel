package ekp.mbom;

import legion.ObjectModel;

public class ProdCtlItem extends ObjectModel {
	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	//
	private String prodCtlUid; // 標的產品型錄prodCtl biz key
	private String partCfgUid; // 此產品型錄對應對產品構型PartCfg biz key
	// 
	private boolean active; // 是否啟用
}
