package ekp.mbom;

import ekp.data.MbomDataService;
import ekp.mbom.dto.ProdModCreateObj;
import legion.DataServiceFactory;
import legion.ObjectModel;

public class ProdMod extends ObjectModel {
	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String prodUid; // 對應的產品項 biz key
	//
	private String id; // 識別碼 biz key
	private String name;
	private String desp;

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private ProdMod(String prodUid) {
		this.prodUid = prodUid;
	}

	static ProdMod newInstance(String prodUid) {
		ProdMod pm = new ProdMod(prodUid);
		pm.configNewInstance();
		return pm;
	}

	public static ProdMod getInstance(String uid, String prodUid, long objectCreateTime, long objectUpdateTime) {
		ProdMod pm = new ProdMod(prodUid);
		pm.configGetInstance(uid, objectCreateTime, objectUpdateTime);
		return pm;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getProdUid() {
		return prodUid;
	}

	public void setProdUid(String prodUid) {
		this.prodUid = prodUid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).saveProdMod(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(MbomDataService.class).deleteProdMod(this.getUid());
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------ProdMod------------------------------------
	public static ProdMod create(ProdModCreateObj _dto) {
		ProdMod pm = newInstance(_dto.getProdUid());
		pm.setId(_dto.getId());
		pm.setName(_dto.getName());
		pm.setDesp(_dto.getDesp());
		return pm.save() ? pm : null;
	}
}
