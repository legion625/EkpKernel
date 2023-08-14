package ekp.serviceFacade.rmi.pu;

import ekp.pu.type.PurchPerfStatus;
import legion.serviceFacade.rmi.ObjectModelRemote;

public class PurchRemote extends ObjectModelRemote {

	protected PurchRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String puNo; // 購案案號
	private String title; // 名稱
	private String supplierName;
	private String supplierBan; // 供應商統編（臺灣）
	private PurchPerfStatus perfStatus; // 履約狀態
	private long perfTime; // 履約時間

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getPuNo() {
		return puNo;
	}

	void setPuNo(String puNo) {
		this.puNo = puNo;
	}

	public String getTitle() {
		return title;
	}

	void setTitle(String title) {
		this.title = title;
	}

	public String getSupplierName() {
		return supplierName;
	}

	void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierBan() {
		return supplierBan;
	}

	void setSupplierBan(String supplierBan) {
		this.supplierBan = supplierBan;
	}

	public PurchPerfStatus getPerfStatus() {
		return perfStatus;
	}

	void setPerfStatus(PurchPerfStatus perfStatus) {
		this.perfStatus = perfStatus;
	}

	public long getPerfTime() {
		return perfTime;
	}

	void setPerfTime(long perfTime) {
		this.perfTime = perfTime;
	}

}
