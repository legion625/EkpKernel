package ekp.data.service.mf;

import java.util.List;
import java.util.Map;

import ekp.data.MfDataService;
import ekp.data.service.mf.query.WorkorderQueryParam;
import ekp.mf.Workorder;
import ekp.mf.WorkorderMaterial;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public class MfDataServiceImp implements MfDataService {
	private String source;
	
	// dao
	private WoDao woDao;

	@Override
	public void register(Map<String, String> _params) {
		if (_params == null || _params.isEmpty())
			return;

		source = _params.get("source");

		// dao
		woDao = new WoDao(source);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	// -------------------------------------------------------------------------------
	// -----------------------------------Workorder-----------------------------------
	@Override
	public boolean saveWorkorder(Workorder _wo) {
		return woDao.saveWorkorder(_wo);
	}
	@Override
	public boolean deleteWorkorder(String _uid) {
		return woDao.deleteWorkorder(_uid);
	}
	@Override
	public Workorder loadWorkorder(String _uid) {
		return woDao.loadWorkorder(_uid);
	}
	@Override
	public QueryOperation<WorkorderQueryParam, Workorder> searchWorkorder(QueryOperation<WorkorderQueryParam, Workorder> _param,
			Map<WorkorderQueryParam, QueryValue[] > _existsDetailMap){
		return woDao.searchWorkorder(_param, _existsDetailMap);
	}
	
	// -------------------------------------------------------------------------------
	// -------------------------------WorkorderMaterial-------------------------------
	@Override
	public boolean saveWorkorderMaterial(WorkorderMaterial _wom) {
		return woDao.saveWorkorderMaterial(_wom);
	}

	@Override
	public boolean deleteWorkorderMaterial(String _uid) {
		return woDao.deleteWorkorderMaterial(_uid);
	}

	@Override
	public WorkorderMaterial loadWorkorderMaterial(String _uid) {
		return woDao.loadWorkorderMaterial(_uid);
	}

	@Override
	public List<WorkorderMaterial> loadWorkorderMaterialList(String _woUid) {
		return woDao.loadWorkorderMaterialList(_woUid);
	}

}
