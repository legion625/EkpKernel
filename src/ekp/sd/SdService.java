package ekp.sd;

import java.util.List;
import java.util.Map;

import ekp.data.service.sd.query.SalesOrderItemQueryParam;
import ekp.data.service.sd.query.SalesOrderQueryParam;
import ekp.sd.dto.BizPartnerCreateObj;
import ekp.sd.dto.SalesOrderCreateObj;
import ekp.sd.dto.SalesOrderItemCreateObj;
import legion.BusinessService;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public interface SdService extends BusinessService{

	// -------------------------------------------------------------------------------
	// ----------------------------------BizPartner-----------------------------------
	public BizPartner createBizPartner(BizPartnerCreateObj _dto);
	public boolean deleteBizPartner(String _uid);
	public BizPartner loadBizPartner(String _uid);
	public BizPartner loadBizPartnerByBpsn(String _bpsn);
	public List<BizPartner> loadBizPartnerList();
	
	// -------------------------------------------------------------------------------
	// ----------------------------------SalesOrder-----------------------------------
	public SalesOrder createSalesOrder(SalesOrderCreateObj _dto);
	public boolean deleteSalesOrder(String _uid);
	public SalesOrder loadSalesOrder(String _uid);
	public SalesOrder loadSalesOrderBySosn(String _sosn);
	public QueryOperation<SalesOrderQueryParam, SalesOrder> searchSalesOrder(
			QueryOperation<SalesOrderQueryParam, SalesOrder> _param, Map<SalesOrderQueryParam, QueryValue[]> _existsDetailMap);
	
	// -------------------------------------------------------------------------------
	// --------------------------------SalesOrderItem---------------------------------
	public SalesOrderItem createSalesOrderItem(String _soUid, SalesOrderItemCreateObj _dto);
	public boolean deleteSalesOrderItem(String _uid);
	public SalesOrderItem loadSalesOrderItem(String _uid);
	public List<SalesOrderItem> loadSalesOrderItemList(String _soUid);
	public List<SalesOrderItem> loadSalesOrderItemListMyMm(String _mmUid);
	public QueryOperation<SalesOrderItemQueryParam, SalesOrderItem> searchSalesOrderItem(
			QueryOperation<SalesOrderItemQueryParam, SalesOrderItem> _param);
	boolean soiFinishDeliver(String _uid, long _finishDeliveredDate);
	boolean soiRevertFinishDeliver(String _uid);
	
}
