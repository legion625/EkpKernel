package ekp.serviceFacade.rmi.pu;

import ekp.mbom.type.PartAcquisitionType;
import ekp.mbom.type.PartUnit;
import legion.serviceFacade.rmi.ObjectModelRemote;

public class PurchItemRemote extends ObjectModelRemote {

	protected PurchItemRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	/* purchUid+mmUid作為biz key */
	private String purchUid;
	private String mmUid;

	/* 快照了物料基本檔當下的各欄位 */
	private String mmMano; // 物料基本檔料號
	private String mmName; // 品名
	private String mmSpecification;
	private PartUnit mmStdUnit;
	/* 快照了當下主要參考的PartAcq */
	private boolean refPa;
	private String refPaUid;
	private PartAcquisitionType refPaType;
	// 依物料基本檔輸入採購的數量和總價
	private double qty;
	private double value;
	//
	private String remark; // 備註（補充說明）

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getPurchUid() {
		return purchUid;
	}

	void setPurchUid(String purchUid) {
		this.purchUid = purchUid;
	}

	public String getMmUid() {
		return mmUid;
	}

	void setMmUid(String mmUid) {
		this.mmUid = mmUid;
	}

	public String getMmMano() {
		return mmMano;
	}

	void setMmMano(String mmMano) {
		this.mmMano = mmMano;
	}

	public String getMmName() {
		return mmName;
	}

	void setMmName(String mmName) {
		this.mmName = mmName;
	}

	public String getMmSpecification() {
		return mmSpecification;
	}

	void setMmSpecification(String mmSpecification) {
		this.mmSpecification = mmSpecification;
	}

	public PartUnit getMmStdUnit() {
		return mmStdUnit;
	}

	void setMmStdUnit(PartUnit mmStdUnit) {
		this.mmStdUnit = mmStdUnit;
	}

	public boolean isRefPa() {
		return refPa;
	}

	void setRefPa(boolean refPa) {
		this.refPa = refPa;
	}

	public String getRefPaUid() {
		return refPaUid;
	}

	void setRefPaUid(String refPaUid) {
		this.refPaUid = refPaUid;
	}

	public PartAcquisitionType getRefPaType() {
		return refPaType;
	}

	void setRefPaType(PartAcquisitionType refPaType) {
		this.refPaType = refPaType;
	}

	public double getQty() {
		return qty;
	}

	void setQty(double qty) {
		this.qty = qty;
	}

	public double getValue() {
		return value;
	}

	void setValue(double value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	void setRemark(String remark) {
		this.remark = remark;
	}

}
