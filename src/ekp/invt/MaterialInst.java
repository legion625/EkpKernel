package ekp.invt;

import ekp.common.SerialNoGenerator;
import ekp.data.InvtDataService;
import ekp.invt.dto.MaterialInstCreateObj;
import ekp.invt.type.MaterialInstAcqChannel;
import legion.DataServiceFactory;
import legion.ObjectModel;
import legion.util.DataFO;

public class MaterialInst extends ObjectModel {
	private String mmUid;

	private String misn; // material instance serial number
	private MaterialInstAcqChannel miac;
	private double qty; // 數量
	private double value; // 帳值
	private long effDate; // 生效日期
	private long expDate; // 失效日期

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private MaterialInst(String mmUid) {
		this.mmUid = mmUid;
	}

	static MaterialInst newInstance(String _mmUid) {
		MaterialInst mi = new MaterialInst(_mmUid);
		mi.configNewInstance();
		return mi;
	}

	public static MaterialInst getInstance(String _uid, String _mmUid, long _objectCreateTime, long _objectUpdateTime) {
		MaterialInst mi = new MaterialInst(_mmUid);
		mi.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
		return mi;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getMmUid() {
		return mmUid;
	}

	public void setMmUid(String mmUid) {
		this.mmUid = mmUid;
	}

	public String getMisn() {
		return misn;
	}

	public void setMisn(String misn) {
		this.misn = misn;
	}

	public MaterialInstAcqChannel getMiac() {
		return miac;
	}

	public void setMiac(MaterialInstAcqChannel miac) {
		this.miac = miac;
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

	public long getEffDate() {
		return effDate;
	}

	public void setEffDate(long effDate) {
		this.effDate = effDate;
	}

	public long getExpDate() {
		return expDate;
	}

	public void setExpDate(long expDate) {
		this.expDate = expDate;
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		if (DataFO.isEmptyString(getMisn()))
			setMisn(SerialNoGenerator.generateMISN());
		return DataServiceFactory.getInstance().getService(InvtDataService.class).saveMaterialInst(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(InvtDataService.class).deleteMaterialInst(getUid());
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------MaterialInst----------------------------------
	static MaterialInst create(MaterialInstCreateObj _dto) {
		MaterialInst mi = newInstance(_dto.getMmUid());
		mi.setMisn(""); // not generated yet
		mi.setMiac(_dto.getMiac());
		mi.setQty(_dto.getQty());
		mi.setValue(_dto.getValue());
		mi.setEffDate(_dto.getEffDate());
		mi.setExpDate(_dto.getExpDate());
		return mi.save() ? mi : null;
	}
	
	// TODO method
}
