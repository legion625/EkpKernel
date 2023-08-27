package ekp.mf.fsm;

import ekp.mf.type.WorkorderStatus;
import legion.Fsm;

import static ekp.mf.type.WorkorderStatus.*;

public class WorkorderFsm extends Fsm<WorkorderStatus> {

	public WorkorderFsm(String uid, WorkorderStatus status) {
		super(uid, status);
	}

	// -------------------------------------------------------------------------------
	public boolean backtoStatusInit() {
		return transfer(TO_START, INIT);
	}

	public boolean gotoStatusToStart() {
		return transfer(INIT, TO_START);
	}

	public boolean backtoStatusToStart() {
		return transfer(WORKING, TO_START);
	}

	public boolean gotoStatusWorking() {
		return transfer(TO_START, WORKING);
	}

	public boolean backtoStatusWorking() {
		return transfer(FINISH_WORK, WORKING);
	}

	public boolean gotoStatusFinishWork() {
		return transfer(WORKING, FINISH_WORK);
	}

	public boolean backtoStatusFinishWork() {
		return transfer(OVER, FINISH_WORK);
	}

	public boolean gotoStatusOver() {
		return transfer(FINISH_WORK, OVER);
	}
}
