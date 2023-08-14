package ekp.data.service.pu;

import java.util.List;
import java.util.Map;

import ekp.data.PuDataService;
import ekp.data.service.pu.query.PurchQueryParam;
import ekp.pu.Purch;
import ekp.pu.PurchItem;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public class PuDataServiceImp implements PuDataService {
	private String source;

	// dao
	private PuDao puDao;

	@Override
	public void register(Map<String, String> _params) {
		if (_params == null || _params.isEmpty())
			return;

		source = _params.get("source");

		// dao
		puDao = new PuDao(source);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	// -------------------------------------------------------------------------------
	// -------------------------------------Purch-------------------------------------
	@Override
	public boolean savePurch(Purch _p) {
		return puDao.savePurch(_p);
	}

	@Override
	public boolean deletePurch(String _uid) {
		return puDao.deletePurch(_uid);
	}

	@Override
	public Purch loadPurch(String _uid) {
		return puDao.loadPurch(_uid);
	}
	
	@Override
	public Purch loadPurchByPuNo(String _puNo) {
		return puDao.loadPurchByPuNo(_puNo);
	}

	@Override
	public QueryOperation<PurchQueryParam, Purch> searchPurch(QueryOperation<PurchQueryParam, Purch> _param,
			Map<PurchQueryParam, QueryValue[]> _existsDetailMap) {
		return puDao.searchPurch(_param, _existsDetailMap);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------PurchItem-----------------------------------
	@Override
	public boolean savePurchItem(PurchItem _pi) {
		return puDao.savePurchItem(_pi);
	}
	@Override
	public boolean deletePurchItem(String _uid){
		return puDao.deletePurchItem(_uid);
	}
	@Override
	public PurchItem loadPurchItem(String _uid){
		return puDao.loadPurchItem(_uid);
	}
	@Override
	public List<PurchItem> loadPurchItemList(String _purchUid){
		return puDao.loadPurchItemList(_purchUid);
	}
	@Override
	public List<PurchItem> loadPurchItemListByMm(String _mmUid){
		return puDao.loadPurchItemListByMm(_mmUid);
	}

}
