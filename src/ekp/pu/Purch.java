package ekp.pu;

import ekp.common.SerialNoGenerator;
import ekp.data.PuDataService;
import ekp.pu.dto.PurchCreateObj;
import ekp.pu.fsm.PurchPerfFsm;
import ekp.pu.type.PurchPerfStatus;
import legion.DataServiceFactory;
import legion.ObjectModel;
import legion.util.DataFO;

public class Purch extends ObjectModel {
	// -------------------------------------------------------------------------------
	// ----------------------------------Attributes-----------------------------------
	private String puNo; // 購案案號
	private String title; // 名稱
	private String supplierUid;// 供應商
	private String supplierName;
	private String supplierBan; // 供應商統編（臺灣）
	
	private PurchPerfFsm perfFsm; // 履約狀態
	private long perfTime; // 履約時間

	// -------------------------------------------------------------------------------
	// ----------------------------------constructor----------------------------------
	private Purch() {
	}

	static Purch newInstance() {
		Purch p = new Purch();
		p.configNewInstance();
		p.perfFsm = new PurchPerfFsm(p.getUid(), PurchPerfStatus.INIT);
		return p;
	}

	public static Purch getInstance(String _uid,PurchPerfStatus _perfStatus, long _objectCreateTime, long _objectUpdateTime) {
		Purch p = new Purch();
		p.configGetInstance(_uid, _objectCreateTime, _objectUpdateTime);
		p.perfFsm = new PurchPerfFsm(p.getUid(), _perfStatus);
		return p;
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------getter&setter---------------------------------
	public String getPuNo() {
		return puNo;
	}

	public void setPuNo(String puNo) {
		this.puNo = puNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSupplierUid() {
		return supplierUid;
	}

	public void setSupplierUid(String supplierUid) {
		this.supplierUid = supplierUid;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierBan() {
		return supplierBan;
	}

	public void setSupplierBan(String supplierBan) {
		this.supplierBan = supplierBan;
	}
	
	public PurchPerfStatus getPerfStatus() {
		return perfFsm.getStatus();
	}
	public void setPerfStatus(PurchPerfStatus perfStatus) {
		perfFsm.setStatus(perfStatus);
	}
	
	public long getPerfTime() {
		return perfTime;
	}

	public void setPerfTime(long perfTime) {
		this.perfTime = perfTime;
	}

	// -------------------------------------------------------------------------------
	public int getPerfStatusIdx() {
		return (getPerfStatus() == null ? PurchPerfStatus.UNDEFINED : getPerfStatus()).getIdx();
	}
	
	// -------------------------------------------------------------------------------
	// ----------------------------------ObjectModel----------------------------------
	@Override
	protected boolean save() {
		if(DataFO.isEmptyString(getPuNo()))
			setPuNo(SerialNoGenerator.generatePuNo());
		return DataServiceFactory.getInstance().getService(PuDataService.class).savePurch(this);
	}

	@Override
	protected boolean delete() {
		return DataServiceFactory.getInstance().getService(PuDataService.class).deletePurch(getUid());
	}
	
	// -------------------------------------------------------------------------------
	// -------------------------------------Purch-------------------------------------
	static Purch create(PurchCreateObj _dto) {
		Purch p = newInstance();
		p.setPuNo(""); // not generated yet...
		p.setTitle(_dto.getTitle());
		p.setSupplierUid(_dto.getSupplierUid());
		p.setSupplierName(_dto.getSupplierName());
		p.setSupplierBan(_dto.getSupplierBan());
		
		// perfStatus-> to be controlled in app...
		p.setPerfTime(0); // not perfed yet...
		
		return p.save() ? p : null;
	}

	boolean toPerf() {
		if (!perfFsm.gotoStatusToPerf())
			return false;
		return save();
	}

	boolean revertToPerf() {
		if (!perfFsm.backtoStatusInit())
			return false;
		return save();
	}
	
	boolean perf(long _perfTime) {
		if (!perfFsm.gotoStatusPerfed())
			return false;
		setPerfTime(_perfTime);
		return save();
	}

	boolean revertPerf() {
		if (!perfFsm.backtoStatusToPerf())
			return false;
		setPerfTime(0);
		return save();
	}
}
