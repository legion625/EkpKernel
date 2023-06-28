package ekp.serviceFacade.rmi.invt;

import ekp.mbom.type.PartUnit;
import legion.serviceFacade.rmi.ObjectModelRemote;

public class MaterialMasterRemote extends ObjectModelRemote{

	protected MaterialMasterRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}
	
	private String mano;
	private String name;
	private String specification;

	private PartUnit stdUnit;

	// 所有MaterialBinStockBatch的庫存量和金額(這是redundant屬性，必須確保一致)
	private double sumStockQty;
	private double sumStockValue;
	public String getMano() {
		return mano;
	}
	void setMano(String mano) {
		this.mano = mano;
	}
	public String getName() {
		return name;
	}
	void setName(String name) {
		this.name = name;
	}
	public String getSpecification() {
		return specification;
	}
	void setSpecification(String specification) {
		this.specification = specification;
	}
	public PartUnit getStdUnit() {
		return stdUnit;
	}
	void setStdUnit(PartUnit stdUnit) {
		this.stdUnit = stdUnit;
	}
	public double getSumStockQty() {
		return sumStockQty;
	}
	void setSumStockQty(double sumStockQty) {
		this.sumStockQty = sumStockQty;
	}
	public double getSumStockValue() {
		return sumStockValue;
	}
	void setSumStockValue(double sumStockValue) {
		this.sumStockValue = sumStockValue;
	}
	
	

}
