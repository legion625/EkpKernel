package ekp.data;

import java.util.List;
import java.util.Map;

import ekp.data.service.sd.query.SalesOrderItemQueryParam;
import ekp.data.service.sd.query.SalesOrderQueryParam;
import ekp.sd.SalesOrder;
import ekp.sd.SalesOrderItem;
import legion.IntegrationService;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public interface SdDataService extends IntegrationService{

	// -------------------------------------------------------------------------------
	// ----------------------------------SalesOrder-----------------------------------
	public boolean saveSalesOrder(SalesOrder _so);

	public boolean deleteSalesOrder(String _uid);

	public SalesOrder loadSalesOrder(String _uid);

	public SalesOrder loadSalesOrderBySosn(String _sosn);

	public QueryOperation<SalesOrderQueryParam, SalesOrder> searchSalesOrder(
			QueryOperation<SalesOrderQueryParam, SalesOrder> _param, Map<SalesOrderQueryParam, QueryValue[]> _existsDetailMap);

	// -------------------------------------------------------------------------------
	// --------------------------------SalesOrderItem---------------------------------
	public boolean saveSalesOrderItem(SalesOrderItem _soi);
	public boolean deleteSalesOrderItem(String _uid);
	public SalesOrderItem loadSalesOrderItem(String _uid);
	public List<SalesOrderItem> loadSalesOrderItemList(String _soUid);
	public List<SalesOrderItem> loadSalesOrderItemListMyMm(String _mmUid);
	public QueryOperation<SalesOrderItemQueryParam, SalesOrderItem> searchSalesOrderItem(
			QueryOperation<SalesOrderItemQueryParam, SalesOrderItem> _param);
	
}
