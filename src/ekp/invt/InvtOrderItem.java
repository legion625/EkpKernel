package ekp.invt;

import ekp.data.InvtDataService;
import ekp.invt.dto.InvtOrderItemCreateObj;
import ekp.invt.type.InvtOrderType;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class InvtOrderItem extends ObjectModel {

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	/* biz key */
	private String ioUid; // invt order uid
	private String mbsUid; // BaterialBinStock uid (biz key) 指定「料項+儲位」

	private InvtOrderType ioType;
	private double orderQty; // 記錄異動的數量
	private double orderValue; // 記錄異動的金額

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private InvtOrderItem(String ioUid) {
		this.ioUid = ioUid;
	}

	static InvtOrderItem newInstance(String _ioUid) {
		InvtOrderItem ioi = new InvtOrderItem(_ioUid);
		ioi.configNewInstance();
		return ioi;
	}

	public static InvtOrderItem getInstance(String _uid, String _ioUid, long _objectCreateTime,
			long _objectUpdateTime) {
		InvtOrderItem ioi = new InvtOrderItem(_ioUid);
		ioi.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
		return ioi;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getIoUid() {
		return ioUid;
	}

	public void setIoUid(String ioUid) {
		this.ioUid = ioUid;
	}

	public String getMbsUid() {
		return mbsUid;
	}

	public void setMbsUid(String mbsUid) {
		this.mbsUid = mbsUid;
	}

	public InvtOrderType getIoType() {
		return ioType;
	}

	public void setIoType(InvtOrderType ioType) {
		this.ioType = ioType;
	}

	public double getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(double orderQty) {
		this.orderQty = orderQty;
	}

	public double getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(double orderValue) {
		this.orderValue = orderValue;
	}
	
	// -------------------------------------------------------------------------------
	public int getIoTypeIdx() {
		return (getIoType()==null?InvtOrderType.UNDEFINED:getIoType()).getIdx();
	}
	
	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		return DataServiceFactory.getInstance().getService(InvtDataService.class).saveInvtOrderItem(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(InvtDataService.class).deleteInvtOrderItem(getUid());
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------InvtOrderItem---------------------------------
	static InvtOrderItem create(InvtOrderItemCreateObj _dto) {
		InvtOrderItem ioi = newInstance(_dto.getIoUid());
		ioi.setMbsUid(_dto.getMbsUid());
		ioi.setIoType(_dto.getIoType());
		ioi.setOrderQty(_dto.getOrderQty());
		ioi.setOrderValue(_dto.getOrderValue());
		return ioi.save()?ioi: null;
	}
	
	// TODO method
}
