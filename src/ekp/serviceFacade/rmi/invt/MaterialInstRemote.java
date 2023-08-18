package ekp.serviceFacade.rmi.invt;

import ekp.invt.type.MaterialInstAcqChannel;
import legion.serviceFacade.rmi.ObjectModelRemote;

public class MaterialInstRemote extends ObjectModelRemote{

	protected MaterialInstRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}
	
	private String mmUid;

	private String misn; // material instance serial number
	private MaterialInstAcqChannel miac;
	private String miacSrcNo;
	private double qty; // 數量
	private double value; // 帳值
	private long effDate; // 生效日期
	private long expDate; // 失效日期
	public String getMmUid() {
		return mmUid;
	}
	void setMmUid(String mmUid) {
		this.mmUid = mmUid;
	}
	public String getMisn() {
		return misn;
	}
	void setMisn(String misn) {
		this.misn = misn;
	}
	public MaterialInstAcqChannel getMiac() {
		return miac;
	}
	void setMiac(MaterialInstAcqChannel miac) {
		this.miac = miac;
	}
	
	public String getMiacSrcNo() {
		return miacSrcNo;
	}
	void setMiacSrcNo(String miacSrcNo) {
		this.miacSrcNo = miacSrcNo;
	}
	public double getQty() {
		return qty;
	}
	void setQty(double qty) {
		this.qty = qty;
	}
	public double getValue() {
		return value;
	}
	void setValue(double value) {
		this.value = value;
	}
	public long getEffDate() {
		return effDate;
	}
	void setEffDate(long effDate) {
		this.effDate = effDate;
	}
	public long getExpDate() {
		return expDate;
	}
	void setExpDate(long expDate) {
		this.expDate = expDate;
	}
	
	

}
