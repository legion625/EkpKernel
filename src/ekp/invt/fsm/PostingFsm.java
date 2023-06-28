package ekp.invt.fsm;

import ekp.invt.type.PostingStatus;
import legion.Fsm;

import static ekp.invt.type.PostingStatus.*;

public class PostingFsm extends Fsm<PostingStatus>{

	public PostingFsm(String uid, PostingStatus status) {
		super(uid, status);
	}
	
	// -------------------------------------------------------------------------------
	public boolean backtoStatusInit() {
		return transfer(TO_POST, INIT);
	}
	
	public boolean gotoStatusToPost() {
		return transfer(INIT, TO_POST);
	}
	
	public boolean backtoStatusToPost() {
		return transfer(POSTED, TO_POST);
	}

	public boolean gotoStatusPosted() {
		return transfer(TO_POST, POSTED);
	}
	
}
