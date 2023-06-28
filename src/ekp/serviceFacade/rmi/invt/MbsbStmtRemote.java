package ekp.serviceFacade.rmi.invt;

import ekp.invt.type.MbsbFlowType;
import ekp.invt.type.PostingStatus;
import legion.serviceFacade.rmi.ObjectModelRemote;

public class MbsbStmtRemote extends ObjectModelRemote {

	protected MbsbStmtRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	/* Conj的兩個對象：MaterialBinStockBatch和InvtOrderItem */
	private String mbsbUid; //
	private String ioiUid; //

	/* 這個Conj紀錄完整的流向、數量、金額。 */
	private MbsbFlowType mbsbFlowType;
	private double stmtQty; // 記錄異動的數量
	private double stmtValue; // 記錄異動的金額

	private PostingStatus postingStatus; // 登帳狀態
	private long postingTime; // 登帳時間

	public String getMbsbUid() {
		return mbsbUid;
	}

	void setMbsbUid(String mbsbUid) {
		this.mbsbUid = mbsbUid;
	}

	public String getIoiUid() {
		return ioiUid;
	}

	void setIoiUid(String ioiUid) {
		this.ioiUid = ioiUid;
	}

	public MbsbFlowType getMbsbFlowType() {
		return mbsbFlowType;
	}

	void setMbsbFlowType(MbsbFlowType mbsbFlowType) {
		this.mbsbFlowType = mbsbFlowType;
	}

	public double getStmtQty() {
		return stmtQty;
	}

	void setStmtQty(double stmtQty) {
		this.stmtQty = stmtQty;
	}

	public double getStmtValue() {
		return stmtValue;
	}

	void setStmtValue(double stmtValue) {
		this.stmtValue = stmtValue;
	}

	public PostingStatus getPostingStatus() {
		return postingStatus;
	}

	void setPostingStatus(PostingStatus postingStatus) {
		this.postingStatus = postingStatus;
	}

	public long getPostingTime() {
		return postingTime;
	}

	void setPostingTime(long postingTime) {
		this.postingTime = postingTime;
	}

}
