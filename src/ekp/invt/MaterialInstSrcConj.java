package ekp.invt;

import ekp.data.InvtDataService;
import ekp.invt.dto.MaterialInstSrcConjCreateObj;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class MaterialInstSrcConj extends ObjectModel {

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String miUid;
	private String srcMiUid;
	private double srcMiQty;
	private double srcMiValue;

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private MaterialInstSrcConj() {
	}

	static MaterialInstSrcConj newInstance() {
		MaterialInstSrcConj c = new MaterialInstSrcConj();
		c.configNewInstance();
		return c;
	}

	public static MaterialInstSrcConj getInstance(String _uid, long _objectCreateTime, long _objectUpdateTime) {
		MaterialInstSrcConj c = new MaterialInstSrcConj();
		c.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
		return c;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getMiUid() {
		return miUid;
	}

	public void setMiUid(String miUid) {
		this.miUid = miUid;
	}

	public String getSrcMiUid() {
		return srcMiUid;
	}

	public void setSrcMiUid(String srcMiUid) {
		this.srcMiUid = srcMiUid;
	}

	public double getSrcMiQty() {
		return srcMiQty;
	}

	public void setSrcMiQty(double srcMiQty) {
		this.srcMiQty = srcMiQty;
	}

	public double getSrcMiValue() {
		return srcMiValue;
	}

	public void setSrcMiValue(double srcMiValue) {
		this.srcMiValue = srcMiValue;
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		return DataServiceFactory.getInstance().getService(InvtDataService.class).saveMaterialInstSrcConj(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(InvtDataService.class).deleteMaterialInstSrcConj(getUid());
	}
	
	// -------------------------------------------------------------------------------
	// ------------------------------MaterialInstSrcConj------------------------------
	static MaterialInstSrcConj create(MaterialInstSrcConjCreateObj _dto) {
		MaterialInstSrcConj c = newInstance();
		c.setMiUid(_dto.getMiUid());
		c.setSrcMiUid(_dto.getSrcMiUid());
		c.setSrcMiQty(_dto.getSrcMiQty());
		c.setSrcMiValue(_dto.getSrcMiValue());
		return c.save() ? c : null;
	}

}
