package ekp.sd;

import java.util.List;
import java.util.Map;

import ekp.data.SdDataService;
import ekp.data.service.sd.query.SalesOrderItemQueryParam;
import ekp.data.service.sd.query.SalesOrderQueryParam;
import ekp.sd.dto.BizPartnerCreateObj;
import ekp.sd.dto.SalesOrderCreateObj;
import ekp.sd.dto.SalesOrderItemCreateObj;
import legion.DataServiceFactory;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public class SdServiceImp implements SdService {

	private static SdDataService dataService;

	@Override
	public void register(Map<String, String> _params) {
		dataService = DataServiceFactory.getInstance().getService(SdDataService.class);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	// -------------------------------------------------------------------------------
	// ----------------------------------BizPartner-----------------------------------
	@Override
	public BizPartner createBizPartner(BizPartnerCreateObj _dto) {
		return BizPartner.create(_dto);
	}
	@Override
	public boolean deleteBizPartner(String _uid) {
		return loadBizPartner(_uid).delete();
	}
	@Override
	public BizPartner loadBizPartner(String _uid) {
		return dataService.loadBizPartner(_uid);
	}
	@Override
	public BizPartner loadBizPartnerByBpsn(String _bpsn) {
		return dataService.loadBizPartnerByBpsn(_bpsn);
	}
	@Override
	public List<BizPartner> loadBizPartnerList() {
		return dataService.loadBizPartnerList();
	}

	@Override
	public boolean bpToggleSupplier(String _uid, boolean _supplier) {
		return loadBizPartner(_uid).toggleSupplier(_supplier);
	}

	@Override
	public boolean bpToggleCustomer(String _uid, boolean _customer) {
		return loadBizPartner(_uid).toggleCustomer(_customer);
	}
	
	// -------------------------------------------------------------------------------
	// ----------------------------------SalesOrder-----------------------------------
	@Override
	public SalesOrder createSalesOrder(SalesOrderCreateObj _dto) {
		return SalesOrder.create(_dto);
	}

	@Override
	public boolean deleteSalesOrder(String _uid) {
		return loadSalesOrder(_uid).delete();
	}

	@Override
	public SalesOrder loadSalesOrder(String _uid) {
		return dataService.loadSalesOrder(_uid);
	}

	@Override
	public SalesOrder loadSalesOrderBySosn(String _sosn) {
		return dataService.loadSalesOrderBySosn(_sosn);
	}

	@Override
	public QueryOperation<SalesOrderQueryParam, SalesOrder> searchSalesOrder(
			QueryOperation<SalesOrderQueryParam, SalesOrder> _param,
			Map<SalesOrderQueryParam, QueryValue[]> _existsDetailMap) {
		return dataService.searchSalesOrder(_param, _existsDetailMap);
	}

	// -------------------------------------------------------------------------------
	// --------------------------------SalesOrderItem---------------------------------
	@Override
	public SalesOrderItem createSalesOrderItem(String _soUid, SalesOrderItemCreateObj _dto) {
		return SalesOrderItem.create(_soUid, _dto);
	}

	@Override
	public boolean deleteSalesOrderItem(String _uid) {
		return loadSalesOrderItem(_uid).delete();
	}

	@Override
	public SalesOrderItem loadSalesOrderItem(String _uid) {
		return dataService.loadSalesOrderItem(_uid);
	}

	@Override
	public List<SalesOrderItem> loadSalesOrderItemList(String _soUid) {
		return dataService.loadSalesOrderItemList(_soUid);
	}

	@Override
	public List<SalesOrderItem> loadSalesOrderItemListMyMm(String _mmUid) {
		return dataService.loadSalesOrderItemListMyMm(_mmUid);
	}

	@Override
	public QueryOperation<SalesOrderItemQueryParam, SalesOrderItem> searchSalesOrderItem(
			QueryOperation<SalesOrderItemQueryParam, SalesOrderItem> _param) {
		return dataService.searchSalesOrderItem(_param);
	}

	@Override
	public boolean soiFinishDeliver(String _uid, long _finishDeliveredDate) {
		return loadSalesOrderItem(_uid).finishDeliver(_finishDeliveredDate);
	}

	@Override
	public boolean soiRevertFinishDeliver(String _uid) {
		return loadSalesOrderItem(_uid).revertFinishDeliver();
	}

}
