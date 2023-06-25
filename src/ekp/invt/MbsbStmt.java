package ekp.invt;

import ekp.data.InvtDataService;
import ekp.invt.dto.MbsbStmtCreateObj;
import ekp.invt.type.MbsbFlowType;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class MbsbStmt extends ObjectModel {

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	/* Conj的兩個對象：MaterialBinStockBatch和InvtOrderItem */
	private String mbsbUid; //
	private String ioiUid; //

	/* 這個Conj紀錄完整的流向、數量、金額。 */
	private MbsbFlowType mbsbFlowType;
	private double stmtQty; // 記錄異動的數量
	private double stmtValue; // 記錄異動的金額

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private MbsbStmt(String mbsbUid, String ioiUid) {
		this.mbsbUid = mbsbUid;
		this.ioiUid = ioiUid;
	}

	static MbsbStmt newInstance(String _mbsbUid, String _ioiUid) {
		MbsbStmt mbsbStmt = new MbsbStmt(_mbsbUid, _ioiUid);
		mbsbStmt.configNewInstance();
		return mbsbStmt;
	}

	public static MbsbStmt getInstance(String _uid, String _mbsbUid, String _ioiUid, long _objectCreateTime,
			long _objectUpdateTime) {
		MbsbStmt mbsbStmt = new MbsbStmt(_mbsbUid, _ioiUid);
		mbsbStmt.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
		return mbsbStmt;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getMbsbUid() {
		return mbsbUid;
	}

	public void setMbsbUid(String mbsbUid) {
		this.mbsbUid = mbsbUid;
	}

	public String getIoiUid() {
		return ioiUid;
	}

	public void setIoiUid(String ioiUid) {
		this.ioiUid = ioiUid;
	}

	public MbsbFlowType getMbsbFlowType() {
		return mbsbFlowType;
	}

	public void setMbsbFlowType(MbsbFlowType mbsbFlowType) {
		this.mbsbFlowType = mbsbFlowType;
	}

	public double getStmtQty() {
		return stmtQty;
	}

	public void setStmtQty(double stmtQty) {
		this.stmtQty = stmtQty;
	}

	public double getStmtValue() {
		return stmtValue;
	}

	public void setStmtValue(double stmtValue) {
		this.stmtValue = stmtValue;
	}

	// -------------------------------------------------------------------------------
	public int getMbsbFlowTypeIdx() {
		return (getMbsbFlowType() == null ? MbsbFlowType.UNDEFINED : getMbsbFlowType()).getIdx();
	}
	
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		return DataServiceFactory.getInstance().getService(InvtDataService.class).saveMbsbStmt(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(InvtDataService.class).deleteMbsbStmt(getUid());
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------MbsbStmt------------------------------------
	static MbsbStmt create(MbsbStmtCreateObj _dto) {
		MbsbStmt mbsbStmt = newInstance(_dto.getMbsbUid(), _dto.getIoiUid());
		mbsbStmt.setMbsbFlowType(_dto.getMbsbFlowType());
		mbsbStmt.setStmtQty(_dto.getStmtQty());
		mbsbStmt.setStmtValue(_dto.getStmtValue());
		return mbsbStmt.save()?mbsbStmt: null;
	}
	
	// TODO method
}
