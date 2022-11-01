package ekp.mbom;

import ekp.data.MbomDataService;
import ekp.mbom.dto.ProdCtlCreateObj;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class ProdCtl extends ObjectModel {

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String id; // 型號 biz key
	private int lv; // 1:系統;2:次系統;3:模組 預設先展到第3階
	private String name; // 名稱
	private boolean req; // 是否為必要的

	private String parentUid;
	private String parentId;

	//
	private String prodUid;

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private ProdCtl() {
	}

	static ProdCtl newInstance() {
		ProdCtl pc = new ProdCtl();
		pc.configNewInstance();
		return pc;
	}

	public static ProdCtl getInstance(String uid, long objectCreateTime, long objectUpdateTime) {
		ProdCtl pc = new ProdCtl();
		pc.configGetInstance(uid, objectCreateTime, objectUpdateTime);
		return pc;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isReq() {
		return req;
	}

	public void setReq(boolean req) {
		this.req = req;
	}

	public String getParentUid() {
		return parentUid;
	}

	public void setParentUid(String parentUid) {
		this.parentUid = parentUid;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getProdUid() {
		return prodUid;
	}

	public void setProdUid(String prodUid) {
		this.prodUid = prodUid;
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).saveProdCtl(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).deleteProdCtl(getUid());
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------ProdCtl------------------------------------
	public static ProdCtl create(ProdCtlCreateObj _dto) {
		ProdCtl pc = newInstance();
		pc.setId(_dto.getId());
		pc.setLv(_dto.getLv());
		pc.setName(_dto.getName());
		pc.setReq(_dto.isReq());
		pc.setParentUid(""); // not assigned yet
		pc.setParentId(""); // not assigned yet
		pc.setProdUid(""); // not assigned yet
		return pc.save() ? pc : null;
	}

	public boolean assignParent(String _parentUid, String _parentId) {
		setParentUid(_parentUid);
		setParentId(_parentId);
		return save();
	}

	public boolean unassignParent() {
		setParentUid("");
		setParentId("");
		return save();
	}

	public boolean assignProd(String _prodUid) {
		setProdUid(_prodUid);
		return save();
	}

	public boolean unassignProd() {
		setProdUid("");
		return save();
	}

}
