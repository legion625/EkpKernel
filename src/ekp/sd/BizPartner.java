package ekp.sd;

import ekp.common.SerialNoGenerator;
import ekp.data.SdDataService;
import ekp.sd.dto.BizPartnerCreateObj;
import legion.DataServiceFactory;
import legion.ObjectModel;
import legion.util.DataFO;

public class BizPartner extends ObjectModel {

	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String bpsn;
	private String name;
	private String ban;

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private BizPartner() {
	}

	static BizPartner newInstance() {
		BizPartner bp = new BizPartner();
		bp.configNewInstance();
		return bp;
	}

	public static BizPartner getInstance(String _uid, long _objectCreateTime, long _objectUpdateTime) {
		BizPartner bp = new BizPartner();
		bp.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
		return bp;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getBpsn() {
		return bpsn;
	}

	public void setBpsn(String bpsn) {
		this.bpsn = bpsn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		if(DataFO.isEmptyString(getBpsn()))
			setBpsn(SerialNoGenerator.generateBPSN());
		return DataServiceFactory.getInstance().getService(SdDataService.class).saveBizPartner(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(SdDataService.class).deleteBizPartner(getUid());
	}
	
	// -------------------------------------------------------------------------------
	// ----------------------------------BizPartner-----------------------------------
	static BizPartner create(BizPartnerCreateObj _dto) {
		BizPartner bp = newInstance();
		bp.setBpsn(""); // 未指定
		bp.setName(_dto.getName());
		bp.setBan(_dto.getBan());
		return bp.save() ? bp : null;
	}

}
