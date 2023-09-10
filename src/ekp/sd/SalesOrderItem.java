package ekp.sd;

import ekp.data.SdDataService;
import ekp.sd.dto.SalesOrderItemCreateObj;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class SalesOrderItem extends ObjectModel {

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String soUid;
	private String mmUid;
	private String mmMano;
	private String mmName;
	private String mmSpec;
	private double qty;
	private double value;

	private boolean allDelivered; // 是否已交貨
	private long finishDeliveredDate;

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private SalesOrderItem(String soUid) {
		this.soUid = soUid;
	}

	static SalesOrderItem newInstance(String _soUid) {
		SalesOrderItem soi = new SalesOrderItem(_soUid);
		soi.configNewInstance();
		return soi;
	}

	public static SalesOrderItem getInstance(String _uid, String _soUid, long _objectCreateTime,
			long _objectUpdateTime) {
		SalesOrderItem so = new SalesOrderItem(_soUid);
		so.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
		return so;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getSoUid() {
		return soUid;
	}

	public void setSoUid(String soUid) {
		this.soUid = soUid;
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

	public String getMmSpec() {
		return mmSpec;
	}

	public void setMmSpec(String mmSpec) {
		this.mmSpec = mmSpec;
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

	public boolean isAllDelivered() {
		return allDelivered;
	}

	public void setAllDelivered(boolean allDelivered) {
		this.allDelivered = allDelivered;
	}

	public long getFinishDeliveredDate() {
		return finishDeliveredDate;
	}

	public void setFinishDeliveredDate(long finishDeliveredDate) {
		this.finishDeliveredDate = finishDeliveredDate;
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		return DataServiceFactory.getInstance().getService(SdDataService.class).saveSalesOrderItem(this);
	}



	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(SdDataService.class).deleteSalesOrderItem(getUid());
	}

	// -------------------------------------------------------------------------------
	// --------------------------------SalesOrderItem---------------------------------
	static SalesOrderItem create(String _soUid, SalesOrderItemCreateObj _dto) {
		SalesOrderItem soi = newInstance(_soUid);
		soi.setMmUid(_dto.getMmUid());
		soi.setMmMano(_dto.getMmMano());
		soi.setMmName(_dto.getMmName());
		soi.setMmSpec(_dto.getMmSpec());
		soi.setQty(_dto.getQty());
		soi.setValue(_dto.getValue());

		// not deliver yet...
		soi.setAllDelivered(false);
		soi.setFinishDeliveredDate(0);

		return soi.save()?soi:null;
	}

	boolean finishDeliver(long _finishDeliveredDate) {
		if (isAllDelivered()) {
			log.error("deliver status [{}] error!!", getUid());
			return false;
		}
		setAllDelivered(true);
		setFinishDeliveredDate(_finishDeliveredDate);
		return save();
	}

	boolean revertFinishDeliver() {
		setAllDelivered(false);
		setFinishDeliveredDate(0);
		return save();
	}

}
