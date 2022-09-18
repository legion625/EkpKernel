package ekp.mbom;

import legion.ObjectModel;

public class ParsPart extends ObjectModel {
	private String parsUid; // ref data key

	// assign part
	private boolean assignPart;
	private String partUid; // ref data key
	private String partPin; // ref biz key
	private double partReqQty; // required quantity (allow decimal in certain conditions)

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private ParsPart(String parsUid) {
		this.parsUid = parsUid;
	}

}
