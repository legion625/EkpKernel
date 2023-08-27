package ekp.serviceFacade.rmi.mf;

import legion.ObjectModel;
import legion.serviceFacade.rmi.ObjectModelRemote;

public class WorkorderMaterialRemote extends ObjectModelRemote {

	protected WorkorderMaterialRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	private String woUid;
	private String woNo;

	// 料件基本檔
	private String mmUid;
	private String mmMano;
	private String mmName;
	private double qty0; // 待領用量
	private double qty1; // 已領用量

	public String getWoUid() {
		return woUid;
	}

	void setWoUid(String woUid) {
		this.woUid = woUid;
	}

	public String getWoNo() {
		return woNo;
	}

	void setWoNo(String woNo) {
		this.woNo = woNo;
	}

	public String getMmUid() {
		return mmUid;
	}

	void setMmUid(String mmUid) {
		this.mmUid = mmUid;
	}

	public String getMmMano() {
		return mmMano;
	}

	void setMmMano(String mmMano) {
		this.mmMano = mmMano;
	}

	public String getMmName() {
		return mmName;
	}

	void setMmName(String mmName) {
		this.mmName = mmName;
	}

	public double getQty0() {
		return qty0;
	}

	void setQty0(double qty0) {
		this.qty0 = qty0;
	}

	public double getQty1() {
		return qty1;
	}

	void setQty1(double qty1) {
		this.qty1 = qty1;
	}

}
