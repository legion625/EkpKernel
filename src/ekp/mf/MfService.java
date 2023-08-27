package ekp.mf;

import java.util.Map;

import ekp.data.service.mf.query.WorkorderQueryParam;
import ekp.mf.dto.WorkorderCreateObj;
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
	
	
	
	

}
