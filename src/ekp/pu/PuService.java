package ekp.pu;

import java.util.List;
import java.util.Map;

import ekp.data.service.pu.query.PurchQueryParam;
import ekp.pu.dto.PurchCreateObj;
import ekp.pu.dto.PurchItemCreateObj;
import legion.BusinessService;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public interface PuService extends BusinessService{
	
	// -------------------------------------------------------------------------------
	// -------------------------------------Purch-------------------------------------
	public Purch createPurch(PurchCreateObj _dto);
	public boolean deletePurch(String _uid);
	public Purch loadPurch(String _uid);
	public Purch loadPurchByPuNo(String _puNo);
	public QueryOperation<PurchQueryParam, Purch> searchPurch(QueryOperation<PurchQueryParam, Purch> _param,
			Map<PurchQueryParam, QueryValue[]> _existsDetailMap);
	
	public boolean purchToPerf(String _uid);
	public boolean purchRevertToPerf(String _uid);
	
	public boolean purchPerf(String _uid, long _perfTime);
	public boolean purchRevertPerf(String _uid);
	
	
	// -------------------------------------------------------------------------------
	// -----------------------------------PurchItem-----------------------------------
	public PurchItem createPurchItem(PurchItemCreateObj _dto);
	public boolean deletePurchItem(String _uid);
	public PurchItem loadPurchItem(String _uid);
	public List<PurchItem> loadPurchItemList(String _purchUid);
	public List<PurchItem> loadPurchItemListByMm(String _mmUid);
	
	

}
