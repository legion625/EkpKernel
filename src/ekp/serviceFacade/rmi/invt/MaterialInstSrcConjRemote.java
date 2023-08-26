package ekp.serviceFacade.rmi.invt;

import legion.serviceFacade.rmi.ObjectModelRemote;

public class MaterialInstSrcConjRemote extends ObjectModelRemote {
	protected MaterialInstSrcConjRemote(String uid, long objectCreateTime, long objectUpdateTime) {
		super(uid, objectCreateTime, objectUpdateTime);
	}

	private String miUid;
	private String srcMiUid;
	private double srcMiQty;
	private double srcMiValue;

	public String getMiUid() {
		return miUid;
	}

	void setMiUid(String miUid) {
		this.miUid = miUid;
	}

	public String getSrcMiUid() {
		return srcMiUid;
	}

	void setSrcMiUid(String srcMiUid) {
		this.srcMiUid = srcMiUid;
	}

	public double getSrcMiQty() {
		return srcMiQty;
	}

	void setSrcMiQty(double srcMiQty) {
		this.srcMiQty = srcMiQty;
	}

	public double getSrcMiValue() {
		return srcMiValue;
	}

	void setSrcMiValue(double srcMiValue) {
		this.srcMiValue = srcMiValue;
	}

}
