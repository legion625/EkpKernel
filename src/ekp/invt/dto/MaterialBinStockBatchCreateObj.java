package ekp.invt.dto;

public class MaterialBinStockBatchCreateObj {
	/* bizKey */
	private String mbsUid; // 主要ref的MaterialBinStock
	private String miUid; // material inst uid

	/**/
	private double stockQty; // 當前的存量餘額
	private double stockValue; // 當前的帳值餘額
	private long stockTime; // 存入時間（每一個batch只會有一個時間）

	public String getMbsUid() {
		return mbsUid;
	}

	public void setMbsUid(String mbsUid) {
		this.mbsUid = mbsUid;
	}

	public String getMiUid() {
		return miUid;
	}

	public void setMiUid(String miUid) {
		this.miUid = miUid;
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

	public long getStockTime() {
		return stockTime;
	}

	public void setStockTime(long stockTime) {
		this.stockTime = stockTime;
	}

}
