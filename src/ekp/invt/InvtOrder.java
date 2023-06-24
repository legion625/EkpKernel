package ekp.invt;

import legion.ObjectModel;

public class InvtOrder extends ObjectModel {

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String iosn; // invt order serial number
	private String applierId;
	private String applierName;
	private long apvTime; // approval time
	private String remark; //

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private InvtOrder() {
	}
}
