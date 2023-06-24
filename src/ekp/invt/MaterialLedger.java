package ekp.invt;

import ekp.data.InvtDataService;
import ekp.invt.dto.MaterialMasterCreateObj;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class MaterialLedger extends ObjectModel {

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String mmUid; // material master的uid
	private String mano; // (redundant attribute)

	private String binUid; // biz key

	//
	private double stockQty;
	private double stockValue;

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private MaterialLedger(String mmUid) {
		this.mmUid = mmUid;
	}

	static MaterialLedger newInstance(String _mmUid) {
		MaterialLedger ml = new MaterialLedger(_mmUid);
		ml.configNewInstance();
		return ml;
	}

	public static MaterialLedger getInstance(String _uid, String _mmUid, long _objectCreateTime,
			long _objectUpdateTime) {
		MaterialLedger ml = new MaterialLedger(_mmUid);
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

	public String getBinUid() {
		return binUid;
	}

	public void setBinUid(String binUid) {
		this.binUid = binUid;
	}

	public double getStockQty() {
		return stockQty;
	}

	public void setStockQty(double stockQty) {
		this.stockQty = stockQty;
	}

	public double getStockValue() {
		return stockValue;
	}

	public void setStockValue(double stockValue) {
		this.stockValue = stockValue;
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		return DataServiceFactory.getInstance().getService(InvtDataService.class).saveMaterialLedger(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(InvtDataService.class).deleteMaterialLedger(getUid());
	}
	
	// -------------------------------------------------------------------------------
	// --------------------------------MaterialLedger---------------------------------
	static MaterialLedger create(MaterialLedgerCreateObj _dto) {
		// TODO not implemented yet...
		return null;
	}

}
