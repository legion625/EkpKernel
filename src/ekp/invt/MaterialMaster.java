package ekp.invt;

import ekp.data.InvtDataService;
import ekp.invt.dto.MaterialMasterCreateObj;
import ekp.mbom.Part;
import ekp.mbom.type.PartUnit;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class MaterialMaster extends ObjectModel {

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String mano;
	private String name;
	private String specification;

	private PartUnit stdUnit;

	// 所有ledger的庫存量和金額(這是redundant屬性，必須確保一致)
	private double sumStockQty;
	private double sumStockValue;

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private MaterialMaster() {
	}

	static MaterialMaster newInstance() {
		MaterialMaster mm = new MaterialMaster();
		mm.configNewInstance();
		return mm;
	}

	public static MaterialMaster getInstance(String _uid, long _objectCreateTime, long _objectUpdateTime) {
		MaterialMaster mm = new MaterialMaster();
		mm.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
		return mm;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getMano() {
		return mano;
	}

	public void setMano(String mano) {
		this.mano = mano;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public PartUnit getStdUnit() {
		return stdUnit;
	}

	public void setStdUnit(PartUnit stdUnit) {
		this.stdUnit = stdUnit;
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
	public String getStdUnitId() {
		return (getStdUnit() == null ? PartUnit.UNDEFINED : getStdUnit()).getId();
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		return DataServiceFactory.getInstance().getService(InvtDataService.class).saveMaterialMaster(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(InvtDataService.class).deleteMaterialMaster(getUid());
	}

	// -------------------------------------------------------------------------------
	// --------------------------------MaterialMaster---------------------------------
	static MaterialMaster create(MaterialMasterCreateObj _dto) {
		// TODO not implemented yet...
		return null;
	}
}
