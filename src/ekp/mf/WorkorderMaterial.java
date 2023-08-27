package ekp.mf;

import ekp.data.MfDataService;
import ekp.mf.dto.WorkorderMaterialCreateObj;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class WorkorderMaterial extends ObjectModel {
	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String woUid;
	private String woNo;

	// 料件基本檔
	private String mmUid;
	private String mmMano;
	private String mmName;
	private double qty0; // 待領用量
	private double qty1; // 已領用量

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private WorkorderMaterial(String woUid) {
		this.woUid = woUid;
	}

	static WorkorderMaterial newInstance(String woUid) {
		WorkorderMaterial wom = new WorkorderMaterial(woUid);
		wom.configNewInstance();
		return wom;
	}

	public static WorkorderMaterial getInstance(String _uid, String _woUid, long _objectCreateTime,
			long _objectUpdateTime) {
		WorkorderMaterial wom = new WorkorderMaterial(_woUid);
		wom.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
		return wom;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getWoUid() {
		return woUid;
	}

	public void setWoUid(String woUid) {
		this.woUid = woUid;
	}

	public String getWoNo() {
		return woNo;
	}

	public void setWoNo(String woNo) {
		this.woNo = woNo;
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

	public double getQty0() {
		return qty0;
	}

	public void setQty0(double qty0) {
		this.qty0 = qty0;
	}

	public double getQty1() {
		return qty1;
	}

	public void setQty1(double qty1) {
		this.qty1 = qty1;
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		return DataServiceFactory.getInstance().getService(MfDataService.class).saveWorkorderMaterial(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(MfDataService.class).deleteWorkorderMaterial(getUid());
	}
	
	// -------------------------------------------------------------------------------
	// -------------------------------WorkorderMaterial-------------------------------
	static WorkorderMaterial createWorkorderMaterial(WorkorderMaterialCreateObj _dto) {
		WorkorderMaterial wom = newInstance(_dto.getWoUid());
		wom.setWoUid(_dto.getWoUid());
		wom.setWoNo(_dto.getWoNo());
		wom.setMmUid(_dto.getMmUid());
		wom.setMmMano(_dto.getMmMano());
		wom.setMmName(_dto.getMmName());
		wom.setQty0(0);
		wom.setQty1(0);
		return wom.save()?wom:null;
	}
	
	boolean addQty0(double _addQty) {
		setQty0(getQty0() + _addQty);
		return save();
	}
	
	boolean qty0to1(double _qty) {
		setQty0(getQty0() - _qty);
		setQty1(getQty1() + _qty);
		return save();
	}
	

}
