package ekp.mf;

import java.util.List;
import java.util.Map;

import ekp.data.MfDataService;
import ekp.data.service.mf.query.WorkorderQueryParam;
import ekp.mf.dto.WorkorderCreateObj;
import ekp.mf.dto.WorkorderMaterialCreateObj;
import legion.DataServiceFactory;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public class MfServiceImp implements MfService {

	private static MfDataService dataService;

	@Override
	public void register(Map<String, String> _params) {
		dataService = DataServiceFactory.getInstance().getService(MfDataService.class);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	// -------------------------------------------------------------------------------
	// -----------------------------------Workorder-----------------------------------
	@Override
	public Workorder createWorkorder(WorkorderCreateObj _dto) {
		return Workorder.create(_dto);
	}
	@Override
	public boolean deleteWorkorder(String _uid) {
		return loadWorkorder(_uid).delete();
	}
	@Override
	public Workorder loadWorkorder(String _uid) {
		return dataService.loadWorkorder(_uid);
	}
	@Override
	public QueryOperation<WorkorderQueryParam, Workorder> searchWorkorder(QueryOperation<WorkorderQueryParam, Workorder> _param,
			Map<WorkorderQueryParam, QueryValue[] > _existsDetailMap){
		return dataService.searchWorkorder(_param, _existsDetailMap);
	}
	@Override
	public boolean woToStart(String _uid) {
		return loadWorkorder(_uid).toStart();
	}
	@Override
	public boolean woRevertToStart(String _uid) {
		return loadWorkorder(_uid).revertToStart();
	}
	@Override
	public boolean woStartWork(String _uid, long _startWorkTime) {
		return loadWorkorder(_uid).startWork(_startWorkTime);
	}
	@Override
	public boolean woRevertStartWork(String _uid) {
		return loadWorkorder(_uid).revertStartWork();
	}
	@Override
	public boolean woFinishWork(String _uid, long _finishWorkTime) {
		return loadWorkorder(_uid).finishWork(_finishWorkTime);
	}
	@Override
	public boolean woRevertFinishWork(String _uid) {
		return loadWorkorder(_uid).revertFinishWork();
	}
	@Override
	public boolean woOver(String _uid, long _overTime) {
		return loadWorkorder(_uid).over(_overTime);
	}
	@Override
	public boolean woRevertOver(String _uid) {
		return loadWorkorder(_uid).revertOver();
	}
	
	// -------------------------------------------------------------------------------
	// -------------------------------WorkorderMaterial-------------------------------
	public WorkorderMaterial createWorkorderMaterial(WorkorderMaterialCreateObj _dto) {
		return WorkorderMaterial.createWorkorderMaterial(_dto);
	}
	public boolean deleteWorkorderMaterial(String _uid) {
		return loadWorkorderMaterial(_uid).delete();
	}
	public WorkorderMaterial loadWorkorderMaterial(String _uid) {
		return dataService.loadWorkorderMaterial(_uid);
	}
	public List<WorkorderMaterial> loadWorkorderMaterialList(String _woUid){
		return dataService.loadWorkorderMaterialList(_woUid);
	}
	public boolean womAddQty0(String _uid, double _addQty) {
		return loadWorkorderMaterial(_uid).addQty0(_addQty);
	}
	public boolean womQty0to1(String _uid, double _qty) {
		return loadWorkorderMaterial(_uid).qty0to1(_qty);
	}

}
