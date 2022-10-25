package ekp.mbom;

import legion.ObjectModel;

public class ProdModItem extends ObjectModel {
	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String prodModUid; // 產品model識別碼 biz key
	//
	private String prodCtlUid; // prodCtl識別碼 biz key
	private String prodCtlItemUid; // 此model選用的組件構型
	private String partCfgUid; // (redundant: 套用prodCtlItemUid所指向的partCfgUid，PartCfg可長出其下階的完整結構。)

}
