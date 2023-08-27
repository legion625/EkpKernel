package ekp.mf;

import java.util.List;
import java.util.Map;

import ekp.data.service.mf.query.WorkorderQueryParam;
import ekp.mf.dto.WorkorderCreateObj;
import ekp.mf.dto.WorkorderMaterialCreateObj;
import legion.BusinessService;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public interface MfService extends BusinessService{
	
	// -------------------------------------------------------------------------------
	// -----------------------------------Workorder-----------------------------------
	public Workorder createWorkorder(WorkorderCreateObj _dto);
	public boolean deleteWorkorder(String _uid);
	public Workorder loadWorkorder(String _uid);
	public QueryOperation<WorkorderQueryParam, Workorder> searchWorkorder(QueryOperation<WorkorderQueryParam, Workorder> _param,
			Map<WorkorderQueryParam, QueryValue[] > _existsDetailMap);
	
	public boolean woToStart(String _uid);
	public boolean woRevertToStart(String _uid);
	public boolean woStartWork(String _uid, long _startWorkTime);
	public boolean woRevertStartWork(String _uid);
	public boolean woFinishWork(String _uid, long _finishWorkTime);
	public boolean woRevertFinishWork(String _uid);
	public boolean woOver(String _uid, long _overTime);
	public boolean woRevertOver(String _uid);
	
	// -------------------------------------------------------------------------------
	// -------------------------------WorkorderMaterial-------------------------------
	public WorkorderMaterial createWorkorderMaterial(WorkorderMaterialCreateObj _dto);
	public boolean deleteWorkorderMaterial(String _uid);
	public WorkorderMaterial loadWorkorderMaterial(String _uid);
	public List<WorkorderMaterial> loadWorkorderMaterialList(String _woUid);
	public boolean womAddQty0(String _uid, double _addQty);
	public boolean womQty0to1(String _uid, double _qty);
	
	

}
