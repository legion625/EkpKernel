package ekp.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.AbstractEkpInitTest;
import ekp.TestLogMark;
import ekp.data.service.invt.query.InvtOrderQueryParam;
import ekp.data.service.mf.query.WorkorderQueryParam;
import ekp.invt.InvtOrder;
import ekp.mf.Workorder;
import legion.DataServiceFactory;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public class MfDataServiceTest extends AbstractEkpInitTest{
	private static MfDataService dataService = DataServiceFactory.getInstance().getService(MfDataService.class);
	private Logger log = LoggerFactory.getLogger(TestLogMark.class);
	
	@Test
	public void testSearchWorkorder() {
		QueryOperation<WorkorderQueryParam, Workorder> param = new QueryOperation<>();
		Map<WorkorderQueryParam, QueryValue[]> existsDetailMap = new HashMap<>();
		
		param = dataService.searchWorkorder(param, existsDetailMap);
		log.debug("param.getTotal(): {}", param.getTotal());
		
		List<Workorder> woList = param.getQueryResult();
		log.debug("woList.size(): {}", woList.size());
	}
}
