package ekp.invt;

import ekp.data.InvtDataService;
import ekp.invt.dto.MaterialBinStockCreateObj;
import ekp.invt.dto.MaterialMasterCreateObj;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class MaterialBinStock extends ObjectModel {

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String mmUid; // material master的uid
	private String mano; // (redundant attribute)

	private String wrhsBinUid; // biz key (對應的wrhsBin)

	// 所有MaterialBinStockBatch的庫存量和金額(這是redundant屬性，必須確保一致)
	private double sumStockQty;
	private double sumStockValue;

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private MaterialBinStock(String mmUid) {
		this.mmUid = mmUid;
	}

	static MaterialBinStock newInstance(String _mmUid) {
		MaterialBinStock mbs = new MaterialBinStock(_mmUid);
		mbs.configNewInstance();
		return mbs;
	}

	public static MaterialBinStock getInstance(String _uid, String _mmUid, long _objectCreateTime,
			long _objectUpdateTime) {
		MaterialBinStock ml = new MaterialBinStock(_mmUid);
		ml.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
		return ml;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getMmUid() {
		return mmUid;
	}

	public void setMmUid(String mmUid) {
		this.mmUid = mmUid;
	}

	public String getMano() {
		return mano;
	}

	public void setMano(String mano) {
		this.mano = mano;
	}

	public String getWrhsBinUid() {
		return wrhsBinUid;
	}

	public void setWrhsBinUid(String wrhsBinUid) {
		this.wrhsBinUid = wrhsBinUid;
	}

	public double getSumStockQty() {
		return sumStockQty;
	}

	public void setSumStockQty(double sumStockQty) {
		this.sumStockQty = sumStockQty;
	}

	public double getSumStockValue() {
		return sumStockValue;
	}

	public void setSumStockValue(double sumStockValue) {
		this.sumStockValue = sumStockValue;
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		return DataServiceFactory.getInstance().getService(InvtDataService.class).saveMaterialBinStock(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(InvtDataService.class).deleteMaterialBinStock(getUid());
	}
	
	// -------------------------------------------------------------------------------
	// -------------------------------MaterialBinStock--------------------------------
	static MaterialBinStock create(MaterialBinStockCreateObj _dto) {
		MaterialBinStock mbs = newInstance(_dto.getMmUid());
		mbs.setMano(_dto.getMano());
		mbs.setWrhsBinUid(_dto.getWrhsBinUid());
		mbs.setSumStockQty(0d); // init 0
		mbs.setSumStockValue(0d); // init 0
		return mbs.save() ? mbs : null;
	}
	
	boolean add(double _addingQty, double _addingValue) {
		setSumStockQty(getSumStockQty() + _addingQty);
		setSumStockValue(getSumStockValue() + _addingValue);
		return save();
	}

	boolean subtract(double _subtractingQty, double _subtractingValue) {
		setSumStockQty(getSumStockQty() - _subtractingQty);
		setSumStockValue(getSumStockValue() - _subtractingValue);
		return save();
	}
	
	// TODO method

}
