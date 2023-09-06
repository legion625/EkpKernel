package ekp.invt;

import ekp.data.InvtDataService;
import ekp.invt.dto.InvtOrderItemCreateObj;
import ekp.invt.type.InvtOrderType;
import ekp.invt.type.IoiTargetType;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class InvtOrderItem extends ObjectModel {

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	/* biz key */
	private String ioUid; // invt order uid
	
	private String mmUid;
	private InvtOrderType ioType;
	
	private IoiTargetType targetType;
	private String targetUid;
	private String targetBizKey;
	
	private double orderQty; // 記錄異動的數量
	private double orderValue; // 記錄異動的金額
	
	//
	private boolean mbsbStmtCreated;
	

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
	
	public IoiTargetType getTargetType() {
		return targetType;
	}

	public void setTargetType(IoiTargetType targetType) {
		this.targetType = targetType;
	}

	public String getTargetUid() {
		return targetUid;
	}

	public void setTargetUid(String targetUid) {
		this.targetUid = targetUid;
	}

	public String getTargetBizKey() {
		return targetBizKey;
	}

	public void setTargetBizKey(String targetBizKey) {
		this.targetBizKey = targetBizKey;
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
	
	public boolean isMbsbStmtCreated() {
		return mbsbStmtCreated;
	}

	public void setMbsbStmtCreated(boolean mbsbStmtCreated) {
		this.mbsbStmtCreated = mbsbStmtCreated;
	}

	// -------------------------------------------------------------------------------
	public int getIoTypeIdx() {
		return (getIoType()==null?InvtOrderType.UNDEFINED:getIoType()).getIdx();
	}
	
	public int getTargetTypeIdx() {
		return (getTargetType()==null?IoiTargetType.UNDEFINED:getTargetType()).getIdx();
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
		ioi.setTargetType(_dto.getTargetType());
		ioi.setTargetUid(_dto.getTargetUid());
		ioi.setTargetBizKey(_dto.getTargetBizKey());
		ioi.setOrderQty(_dto.getOrderQty());
		ioi.setOrderValue(_dto.getOrderValue());
		// 
		ioi.setMbsbStmtCreated(false); // 未指定
		return ioi.save()?ioi: null;
	}
	
	boolean mbsbStmtCreated() {
		setMbsbStmtCreated(true);
		return save();
	}

	boolean revertMbsbStmtCreated() {
		setMbsbStmtCreated(false);
		return save();
	}
}
