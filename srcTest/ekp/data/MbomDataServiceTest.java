package ekp.data;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.AbstractEkpInitTest;
import ekp.TestLogMark;
import ekp.data.service.mbom.query.PartCfgQueryParam;
import ekp.data.service.mbom.query.PartQueryParam;
import ekp.data.service.mbom.query.PpartSkewerQueryParam;
import ekp.mbom.Part;
import ekp.mbom.dto.PpartSkewer;
import legion.DataServiceFactory;
import legion.util.BeanUtil;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.CompareBoolean;
import legion.util.query.QueryOperation.CompareOp;
import legion.util.query.QueryOperation.QueryValue;

import static legion.util.query.QueryOperation.CompareOp.*;
import static legion.util.query.QueryOperation.CompareBoolean.*;

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
	
	// -------------------------------------------------------------------------------
	// ----------------------------------PpartSkewer----------------------------------
	@Test
	@Ignore
	public void testLoadPpartSkewer() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String uid = "2022!48!7!3";
		PpartSkewer s = dataService.loadPpartSkewer(uid);
		log.debug("s: {}", s);
		log.debug("describe: {}", PropertyUtils.describe(s));
	}
	
	@Test
	public void testSearchPpartSkewer() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		QueryOperation<PpartSkewerQueryParam, PpartSkewer> p = new QueryOperation<>();
		Map<PpartSkewerQueryParam, QueryValue[]> existsQvMap = new HashMap<>();
		//
		p.appendCondition(QueryOperation.value(PpartSkewerQueryParam.B_OF_PC$_PA_EXISTS, equal, true));
		existsQvMap.put(PpartSkewerQueryParam.B_OF_PC$_PA_EXISTS,
				new QueryValue[] { QueryOperation.value(PartCfgQueryParam.ID, equal, "MAU") });
		p.appendCondition(QueryOperation.value(PpartSkewerQueryParam.B_OF_PC_ROOT_PART, equal, false)); // 排除root
		p.appendCondition(QueryOperation.value(PpartSkewerQueryParam.B_OF_PC$_PARENT_PART_EXISTS, equal, false)); // 沒上階(孤兒)
		existsQvMap.put(PpartSkewerQueryParam.B_OF_PC$_PARENT_PART_EXISTS,
				new QueryValue[] { QueryOperation.value(PartCfgQueryParam.ID, equal, "MAU") });
		
		//
		p  =	dataService.searchPpartSkewer(p, existsQvMap);
		List<PpartSkewer> list = p.getQueryResult();
		log.debug("list.size(): {}", list.size());
		for(PpartSkewer s:  list) {
			log.debug("PropertyUtils.describe(s): {}", PropertyUtils.describe(s));
		}
		
	}
}
