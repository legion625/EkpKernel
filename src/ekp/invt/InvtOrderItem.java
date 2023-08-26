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
	
	private String mmUid;
	private InvtOrderType ioType;
	private double orderQty; // 記錄異動的數量
	private double orderValue; // 記錄異動的金額
	
	//
	private boolean miAssigned;
	private String miUid;
	//
	private boolean wrhsBinAssigned;
	private String wrhsBinUid;
	

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
	
	public String getMmUid() {
		return mmUid;
	}

	public void setMmUid(String mmUid) {
		this.mmUid = mmUid;
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
	
	public boolean isMiAssigned() {
		return miAssigned;
	}

	public void setMiAssigned(boolean miAssigned) {
		this.miAssigned = miAssigned;
	}
	
	public String getMiUid() {
		return miUid;
	}

	public void setMiUid(String miUid) {
		this.miUid = miUid;
	}

	public boolean isWrhsBinAssigned() {
		return wrhsBinAssigned;
	}

	public void setWrhsBinAssigned(boolean wrhsBinAssigned) {
		this.wrhsBinAssigned = wrhsBinAssigned;
	}

	public String getWrhsBinUid() {
		return wrhsBinUid;
	}

	public void setWrhsBinUid(String wrhsBinUid) {
		this.wrhsBinUid = wrhsBinUid;
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
		ioi.setMmUid(_dto.getMmUid());
		ioi.setIoType(_dto.getIoType());
		ioi.setOrderQty(_dto.getOrderQty());
		ioi.setOrderValue(_dto.getOrderValue());
		//
		ioi.setMiAssigned(false);
		ioi.setMiUid(""); // 未指定
		//
		ioi.setWrhsBinAssigned(false);
		ioi.setWrhsBinUid(""); // 未指定
		return ioi.save()?ioi: null;
	}
	
	boolean assignMi(String _miUid) {
		setMiAssigned(true);
		setMiUid(_miUid);
		return save();
	}
	boolean revertAssignMi() {
		setMiAssigned(false);
		setMiUid("");
		return save();
	}
	boolean assignWrhsBin(String _wrhsBinUid) {
		setWrhsBinAssigned(true);
		setWrhsBinUid(_wrhsBinUid);
		return save();
	}
	boolean revertAssignWrhsBin() {
		setWrhsBinAssigned(false);
		setWrhsBinUid("");
		return save();
	}
	
	// TODO method
}
