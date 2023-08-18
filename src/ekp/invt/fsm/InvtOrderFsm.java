package ekp.invt.fsm;

import ekp.invt.type.InvtOrderStatus;
import legion.Fsm;

import static ekp.invt.type.InvtOrderStatus.*;

public class InvtOrderFsm extends Fsm<InvtOrderStatus>{

	public InvtOrderFsm(String uid, InvtOrderStatus status) {
		super(uid, status);
	}
	
	// -------------------------------------------------------------------------------
	public boolean backtoStatusInit() {
		return transfer(TO_APV, INIT);
	}
	
	public boolean gotoStatusToApv() {
		return transfer(INIT, TO_APV);
	}
	
	public boolean backtoStatusToApv() {
		return transfer(APPROVED, TO_APV);
	}
	
	public boolean gotoStatusApproved() {
		return transfer(TO_APV, APPROVED);
	}
}
