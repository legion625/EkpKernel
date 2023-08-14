package ekp.pu;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.data.PuDataService;
import ekp.data.service.pu.query.PurchQueryParam;
import ekp.pu.dto.PurchCreateObj;
import ekp.pu.dto.PurchItemCreateObj;
import legion.DataServiceFactory;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public class PuServiceImp implements PuService{
	private Logger log = LoggerFactory.getLogger(PuServiceImp.class);

	private static PuDataService dataService;


	@Override
	public void register(Map<String, String> _params) {
		dataService = DataServiceFactory.getInstance().getService(PuDataService.class);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	// -------------------------------------------------------------------------------
	// -------------------------------------Purch-------------------------------------
	@Override
	public Purch createPurch(PurchCreateObj _dto) {
		return Purch.create(_dto);
	}
	@Override
	public boolean deletePurch(String _uid) {
		return loadPurch(_uid).delete();
	}
	@Override
	public Purch loadPurch(String _uid) {
		return dataService.loadPurch(_uid);
	}
	@Override
	public Purch loadPurchByPuNo(String _puNo) {
		return dataService.loadPurchByPuNo(_puNo);
	}
	@Override
	public QueryOperation<PurchQueryParam, Purch> searchPurch(QueryOperation<PurchQueryParam, Purch> _param,
			Map<PurchQueryParam, QueryValue[]> _existsDetailMap){
		return dataService.searchPurch(_param, _existsDetailMap);
	}

	@Override
	public boolean purchPerf(String _uid, long _perfTime) {
		return loadPurch(_uid).perf(_perfTime);

	}
	@Override
	public boolean purchRevertPerf(String _uid) {
		return loadPurch(_uid).revertPerf();
	}


	// -------------------------------------------------------------------------------
	// -----------------------------------PurchItem-----------------------------------
	@Override
	public PurchItem createPurchItem(PurchItemCreateObj _dto) {
		return PurchItem.create(_dto);
	}

	@Override
	public boolean deletePurchItem(String _uid) {
		return loadPurchItem(_uid).delete();
	}

	@Override
	public PurchItem loadPurchItem(String _uid) {
		return dataService.loadPurchItem(_uid);
	}
	@Override
	public List<PurchItem> loadPurchItemList(String _purchUid){
		return dataService.loadPurchItemList(_purchUid);
	}
	@Override
	public List<PurchItem> loadPurchItemListByMm(String _mmUid){
		return dataService.loadPurchItemListByMm(_mmUid);
	}

}
