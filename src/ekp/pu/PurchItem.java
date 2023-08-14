package ekp.pu;

import ekp.data.PuDataService;
import ekp.mbom.type.PartUnit;
import ekp.pu.dto.PurchItemCreateObj;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class PurchItem extends ObjectModel {
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
	// 依物料基本檔輸入採購的數量和總價
	private double qty;
	private double value;
	//
	private String remark; // 備註（補充說明）

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private PurchItem(String purchUid) {
		this.purchUid = purchUid;
	}

	static PurchItem newInstance(String _purchUid) {
		PurchItem pi = new PurchItem(_purchUid);
		pi.configNewInstance();
		return pi;
	}

	public static PurchItem getInstance(String _uid, String _purchUid, long _objectCreateTime, long _objectUpdateTime) {
		PurchItem pi = new PurchItem(_purchUid);
		pi.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
		return pi;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getPurchUid() {
		return purchUid;
	}

	public void setPurchUid(String purchUid) {
		this.purchUid = purchUid;
	}

	public String getMmUid() {
		return mmUid;
	}

	public void setMmUid(String mmUid) {
		this.mmUid = mmUid;
	}

	public String getMmMano() {
		return mmMano;
	}

	public void setMmMano(String mmMano) {
		this.mmMano = mmMano;
	}

	public String getMmName() {
		return mmName;
	}

	public void setMmName(String mmName) {
		this.mmName = mmName;
	}

	public String getMmSpecification() {
		return mmSpecification;
	}

	public void setMmSpecification(String mmSpecification) {
		this.mmSpecification = mmSpecification;
	}

	public PartUnit getMmStdUnit() {
		return mmStdUnit;
	}

	public void setMmStdUnit(PartUnit mmStdUnit) {
		this.mmStdUnit = mmStdUnit;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	// -------------------------------------------------------------------------------
	public String getMmStdUnitId() {
		return (getMmStdUnit()==null?PartUnit.UNDEFINED:getMmStdUnit()).getId();
	}
	
	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		return DataServiceFactory.getInstance().getService(PuDataService.class).savePurchItem(this);
	}
	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(PuDataService.class).deletePurchItem(getUid());
	}
	
	// -------------------------------------------------------------------------------
	// -----------------------------------PurchItem-----------------------------------
	static PurchItem create(PurchItemCreateObj _dto) {
		PurchItem pi = newInstance(_dto.getPurchUid());
		pi.setMmUid(_dto.getMmUid());
		pi.setMmMano(_dto.getMmMano());
		pi.setMmName(_dto.getMmName());
		pi.setMmSpecification(_dto.getMmSpecification());
		pi.setMmStdUnit(_dto.getMmStdUnit());
		pi.setQty(_dto.getQty());
		pi.setValue(_dto.getValue());
		pi.setRemark(_dto.getRemark());
		return pi.save()?pi: null;
	}

}
