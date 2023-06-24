package ekp.invt;

import ekp.invt.type.InvtOrderType;
import legion.ObjectModel;

public class InvtOrderItem extends ObjectModel{
	
	// -------------------------------------------------------------------------------
		// ----------------------------------Attributes-----------------------------------
	private String ioUid; // invt order uid
	private String iosn; // ref biz key
	
	private String mlUid; // material ledger uid (biz key)
	private String mano; // (redundant)
	private String binUid; // (redundant)
	private WrhsLoc wl; // (redundant attribute)
	private String binId; // (redundant attribute)
	
	private InvtOrderType ioType;
	private double orderQty; // 記錄異動的數量
	private double orderValue; // 記錄異動的金額
	
	
	
}
