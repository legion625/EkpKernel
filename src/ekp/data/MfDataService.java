package ekp.data;

import java.util.List;
import java.util.Map;

import ekp.data.service.mf.query.WorkorderQueryParam;
import ekp.mf.Workorder;
import ekp.mf.WorkorderMaterial;
import legion.IntegrationService;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public interface MfDataService extends IntegrationService {

	// -------------------------------------------------------------------------------
	// -----------------------------------Workorder-----------------------------------
	public boolean saveWorkorder(Workorder _wo);
	public boolean deleteWorkorder(String _uid);
	public Workorder loadWorkorder(String _uid);
	public QueryOperation<WorkorderQueryParam, Workorder> searchWorkorder(QueryOperation<WorkorderQueryParam, Workorder> _param,
			Map<WorkorderQueryParam, QueryValue[] > _existsDetailMap);
	
	// -------------------------------------------------------------------------------
	// -------------------------------WorkorderMaterial-------------------------------
	public boolean saveWorkorderMaterial(WorkorderMaterial _wom);
	public boolean deleteWorkorderMaterial(String _uid);
	public WorkorderMaterial loadWorkorderMaterial(String _uid);
	public List<WorkorderMaterial> loadWorkorderMaterialList(String _woUid);
	
}
