package ekp.data.service.sd;

import java.util.List;
import java.util.Map;

import ekp.data.SdDataService;
import ekp.data.service.sd.query.SalesOrderItemQueryParam;
import ekp.data.service.sd.query.SalesOrderQueryParam;
import ekp.sd.SalesOrder;
import ekp.sd.SalesOrderItem;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public class SdDataServiceImp implements SdDataService {

	private String source;

	// dao
	private SoDao soDao;

	@Override
	public void register(Map<String, String> _params) {
		if (_params == null || _params.isEmpty())
			return;

		source = _params.get("source");

		// dao
		soDao = new SoDao(source);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------SalesOrder-----------------------------------
	@Override
	public boolean saveSalesOrder(SalesOrder _so) {
		return soDao.saveSalesOrder(_so);
	}

	@Override
	public boolean deleteSalesOrder(String _uid) {
		return soDao.deleteSalesOrder(_uid);
	}

	@Override
	public SalesOrder loadSalesOrder(String _uid) {
		return soDao.loadSalesOrder(_uid);
	}

	@Override
	public SalesOrder loadSalesOrderBySosn(String _sosn) {
		return soDao.loadSalesOrderBySosn(_sosn);
	}

	@Override
	public QueryOperation<SalesOrderQueryParam, SalesOrder> searchSalesOrder(
			QueryOperation<SalesOrderQueryParam, SalesOrder> _param,
			Map<SalesOrderQueryParam, QueryValue[]> _existsDetailMap) {
		return soDao.searchSalesOrder(_param, _existsDetailMap);
	}

	// -------------------------------------------------------------------------------
	// --------------------------------SalesOrderItem---------------------------------
	@Override
	public boolean saveSalesOrderItem(SalesOrderItem _soi) {
		return soDao.saveSalesOrderItem(_soi);
	}

	@Override
	public boolean deleteSalesOrderItem(String _uid) {
		return soDao.deleteSalesOrderItem(_uid);
	}

	@Override
	public SalesOrderItem loadSalesOrderItem(String _uid) {
		return soDao.loadSalesOrderItem(_uid);
	}

	@Override
	public List<SalesOrderItem> loadSalesOrderItemList(String _soUid) {
		return soDao.loadSalesOrderItemList(_soUid);
	}

	@Override
	public List<SalesOrderItem> loadSalesOrderItemListMyMm(String _mmUid) {
		return soDao.loadSalesOrderItemListMyMm(_mmUid);
	}

	@Override
	public QueryOperation<SalesOrderItemQueryParam, SalesOrderItem> searchSalesOrderItem(
			QueryOperation<SalesOrderItemQueryParam, SalesOrderItem> _param) {
		return soDao.searchSalesOrderItem(_param);
	}

}
