package ekp.invt;

import ekp.data.InvtDataService;
import ekp.invt.dto.MaterialBinStockBatchCreateObj;
import legion.DataServiceFactory;
import legion.ObjectModel;

/**
 * 以mbsUid和mbsbsn作為共同biz key. <br>
 * {@link #mbsUid}主要ref的MaterialBinStock，指向儲位。 <br>
 * {@link #miUid}
 * 料件的批次以MaterialInst表達，在料件「產生」的時候給予，最常見的是採購入庫(I1)或產製入庫(I2)。<br>
 * 
 * 當同一個料件有多批次購入放在相同儲位時，每一批次購入均會產生對應不同的mi，此時會有多筆相同mbsUid但不同miUid的資料；<br>
 * 當同一批採購入庫同時存放在不同儲位時，可能會有多個MaterialBinStock對應到相同的mi，此時會有多筆不同mbsUid但相同miUid資料。<br>
 * 
 * 
 * @author Min-Hua Chao
 *
 */
public class MaterialBinStockBatch extends ObjectModel {

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	/* bizKey */
	private String mbsUid; // 主要ref的MaterialBinStock
	private String miUid; // material inst uid

	/**/
	private double stockQty; // 當前的存量餘額
	private double stockValue; // 當前的帳值餘額

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private MaterialBinStockBatch(String mbsUid, String miUid) {
		this.mbsUid = mbsUid;
		this.miUid = miUid;
	}

	static MaterialBinStockBatch newInstance(String _mbsUid, String _miUid) {
		MaterialBinStockBatch mbsb = new MaterialBinStockBatch(_mbsUid, _miUid);
		mbsb.configNewInstance();
		return mbsb;
	}

	public static MaterialBinStockBatch getInstance(String _uid, String _mbsUid, String _miUid, long _objectCreateTime,
			long _objectUpdateTime) {
		MaterialBinStockBatch mbsb = new MaterialBinStockBatch(_mbsUid, _miUid);
		mbsb.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
		return mbsb;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
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
	
	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		return DataServiceFactory.getInstance().getService(InvtDataService.class).saveMaterialBinStockBatch(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(InvtDataService.class).deleteMaterialBinStockBatch(getUid());
	}
	
	// -------------------------------------------------------------------------------
	// -----------------------------MaterialBinStockBatch-----------------------------
	static MaterialBinStockBatch create(MaterialBinStockBatchCreateObj _dto) {
		MaterialBinStockBatch mbsb = newInstance(_dto.getMbsUid(), _dto.getMiUid());
		mbsb.setStockQty(0d); // init 0
		mbsb.setStockValue(0d); // init 0
		return mbsb.save() ? mbsb : null;
	}
	
	boolean add(double _addingQty, double _addingValue) {
		setStockQty(getStockQty() + _addingQty);
		setStockValue(getStockValue() + _addingValue);
		return save();
	}

	boolean subtract(double _subtractingQty, double _subtractingValue) {
		setStockQty(getStockQty() - _subtractingQty);
		setStockValue(getStockValue() - _subtractingValue);
		return save();
	}
	
	// TODO method



}
