package ekp.data;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.AbstractEkpInitTest;
import ekp.TestLogMark;
import ekp.data.service.mbom.query.PartQueryParam;
import ekp.mbom.Part;
import legion.DataServiceFactory;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.CompareOp;

public class MbomDataServiceTest extends AbstractEkpInitTest {
	private static MbomDataService dataService = DataServiceFactory.getInstance().getService(MbomDataService.class);

	private Logger log = LoggerFactory.getLogger(TestLogMark.class);
	
	
	@Test
	public void testSearchPart() {
		QueryOperation<PartQueryParam, Part> p = new QueryOperation<>();
		p.appendCondition(QueryOperation.value(PartQueryParam.PIN, CompareOp.like, "%F%"));
		p.appendCondition(QueryOperation.value(PartQueryParam.NAME, CompareOp.like, "%冷凍%"));
		p = dataService.searchPart(p);
		
		
		List<Part> resultList = p.getQueryResult();
		log.debug("resultList.size(): {}", resultList.size());
		for (Part part : resultList) {
			log.debug("{}\t{}", part.getPin(), part.getName());
		}
	}
}
