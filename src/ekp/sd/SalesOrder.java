package ekp.sd;

import ekp.common.SerialNoGenerator;
import ekp.data.SdDataService;
import ekp.sd.dto.SalesOrderCreateObj;
import legion.DataServiceFactory;
import legion.ObjectModel;
import legion.util.DataFO;

public class SalesOrder extends ObjectModel {

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String sosn;
	private String title;
	private String customerUid;
	private String customerName;
	private String customerBan;

	private String salerId;
	private String salerName;
	private long saleDate;

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private SalesOrder() {
	}

	static SalesOrder newInstance() {
		SalesOrder so = new SalesOrder();
		so.configNewInstance();
		return so;
	}

	public static SalesOrder getInstance(String _uid, long _objectCreateTime, long _objectUpdateTime) {
		SalesOrder so = new SalesOrder();
		so.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
		return so;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getSosn() {
		return sosn;
	}

	public void setSosn(String sosn) {
		this.sosn = sosn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getCustomerUid() {
		return customerUid;
	}

	public void setCustomerUid(String customerUid) {
		this.customerUid = customerUid;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerBan() {
		return customerBan;
	}

	public void setCustomerBan(String customerBan) {
		this.customerBan = customerBan;
	}

	public String getSalerId() {
		return salerId;
	}

	public void setSalerId(String salerId) {
		this.salerId = salerId;
	}

	public String getSalerName() {
		return salerName;
	}

	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}

	public long getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(long saleDate) {
		this.saleDate = saleDate;
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		if (DataFO.isEmptyString(getSosn()))
			setSosn(SerialNoGenerator.generateSOSN());
		return DataServiceFactory.getInstance().getService(SdDataService.class).saveSalesOrder(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(SdDataService.class).deleteSalesOrder(getUid());
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------SalesOrder-----------------------------------
	static SalesOrder create(SalesOrderCreateObj _dto) {
		SalesOrder so = newInstance();
		so.setSosn(""); // 未給定
		so.setTitle(_dto.getTitle());
		so.setCustomerName(_dto.getCustomerName());
		so.setCustomerBan(_dto.getCustomerBan());
		so.setSalerId(_dto.getSalerId());
		so.setSalerName(_dto.getSalerName());
		so.setSaleDate(_dto.getSaleDate());
		return so.save() ? so : null;
	}

}
