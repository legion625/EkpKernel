package ekp.pu.fsm;

import ekp.pu.type.PurchPerfStatus;
import legion.Fsm;

import static ekp.pu.type.PurchPerfStatus.*;

public class PurchPerfFsm  extends Fsm<PurchPerfStatus>{

	public PurchPerfFsm(String uid, PurchPerfStatus status) {
		super(uid, status);
	}
	
	// -------------------------------------------------------------------------------
	public boolean backtoStatusInit() {
		return transfer(TO_PERF, INIT);
	}
	
	public boolean gotoStatusToPerf() {
		return transfer(INIT, TO_PERF);
	}

	public boolean backtoStatusToPerf() {
		return transfer(PERFED, TO_PERF);
	}
	
	public boolean gotoStatusPerfed() {
		return transfer(TO_PERF, PERFED);
	}
	
}
