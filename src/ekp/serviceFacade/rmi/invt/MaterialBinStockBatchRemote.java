package ekp.serviceFacade.rmi.invt;

import legion.serviceFacade.rmi.ObjectModelRemote;

public class MaterialBinStockBatchRemote extends ObjectModelRemote{

	protected MaterialBinStockBatchRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	/* bizKey */
	private String mbsUid; // 主要ref的MaterialBinStock
	private String miUid; // material inst uid

	/**/
	private double stockQty; // 當前的存量餘額
	private double stockValue; // 當前的帳值餘額
	public String getMbsUid() {
		return mbsUid;
	}
	void setMbsUid(String mbsUid) {
		this.mbsUid = mbsUid;
	}
	public String getMiUid() {
		return miUid;
	}
	void setMiUid(String miUid) {
		this.miUid = miUid;
	}
	public double getStockQty() {
		return stockQty;
	}
	void setStockQty(double stockQty) {
		this.stockQty = stockQty;
	}
	public double getStockValue() {
		return stockValue;
	}
	void setStockValue(double stockValue) {
		this.stockValue = stockValue;
	}
	
	
}
