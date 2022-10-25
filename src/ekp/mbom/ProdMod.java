package ekp.mbom;

import legion.ObjectModel;

public class ProdMod extends ObjectModel {
	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String prodUid; // 對應的產品項 biz key
	//
	private String id; // 識別碼 biz key
	private String name;
	private String desp;

	// -------------------------------------------------------------------------------

	@Override
	protected boolean save() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}
}
