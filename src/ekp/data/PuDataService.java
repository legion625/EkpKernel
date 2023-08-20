package ekp.data;

import java.util.List;
import java.util.Map;

import ekp.data.service.pu.query.PurchQueryParam;
import ekp.pu.Purch;
import ekp.pu.PurchItem;
import legion.IntegrationService;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public interface PuDataService extends IntegrationService {

	// -------------------------------------------------------------------------------
	// -------------------------------------Purch-------------------------------------
	public boolean savePurch(Purch _p);

	public boolean deletePurch(String _uid);

	public Purch loadPurch(String _uid);
	
	public Purch loadPurchByPuNo(String _puNo);

	public QueryOperation<PurchQueryParam, Purch> searchPurch(QueryOperation<PurchQueryParam, Purch> _param,
			Map<PurchQueryParam, QueryValue[]> _existsDetailMap);

	// -------------------------------------------------------------------------------
	// -----------------------------------PurchItem-----------------------------------
	public boolean savePurchItem(PurchItem _pi);

	public boolean deletePurchItem(String _uid);

	public PurchItem loadPurchItem(String _uid);

	public List<PurchItem> loadPurchItemList(String _purchUid);
	
	public List<PurchItem> loadPurchItemListByMm(String _mmUid);
}
