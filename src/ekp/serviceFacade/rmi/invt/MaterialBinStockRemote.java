package ekp.serviceFacade.rmi.invt;

import legion.serviceFacade.rmi.ObjectModelRemote;

public class MaterialBinStockRemote extends ObjectModelRemote {
	protected MaterialBinStockRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	private String mmUid; // material master的uid
	private String mano; // (redundant attribute)

	private String wrhsBinUid; // biz key (對應的wrhsBin)

	// 所有MaterialBinStockBatch的庫存量和金額(這是redundant屬性，必須確保一致)
	private double sumStockQty;
	private double sumStockValue;

	public String getMmUid() {
		return mmUid;
	}

	void setMmUid(String mmUid) {
		this.mmUid = mmUid;
	}

	public String getMano() {
		return mano;
	}

	void setMano(String mano) {
		this.mano = mano;
	}

	public String getWrhsBinUid() {
		return wrhsBinUid;
	}

	void setWrhsBinUid(String wrhsBinUid) {
		this.wrhsBinUid = wrhsBinUid;
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
