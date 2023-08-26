package ekp.invt.fsm;

import ekp.invt.type.MaterialInstSrcStatus;
import legion.Fsm;

import static ekp.invt.type.MaterialInstSrcStatus.*;

public class MaterialInstSrcFsm extends Fsm<MaterialInstSrcStatus> {

	public MaterialInstSrcFsm(String uid, MaterialInstSrcStatus status) {
		super(uid, status);
	}
	
	// -------------------------------------------------------------------------------
	public boolean backtoStatusInit() {
		return transfer(new MaterialInstSrcStatus[] {ASSIGNING, NONE} , INIT);
	}
	
	public boolean gotoStatusAssigning() {
		return transfer(INIT, ASSIGNING);
	}
	
	public boolean backtoStatusAssigning() {
		return transfer(FINISH_ASSIGNED, ASSIGNING);
	}
	
	public boolean gotoStatusNone() {
		return transfer(INIT, NONE);
	}
	
	public boolean gotoStatusAssigned() {
		return transfer(ASSIGNING, FINISH_ASSIGNED);
	}
	
	

}
