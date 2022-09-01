package legion.data;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ekp.AbstractEkpInitTest;
import legion.DataServiceFactory;
import legion.system.SysAttr;
import legion.util.BeanUtil;

public class TestSystemDataService extends AbstractEkpInitTest{
	
	private static SystemDataService dataService; 
	
	@BeforeClass
	public static void beforeClass() {
		dataService = DataServiceFactory.getInstance().getService(SystemDataService.class);
		log.debug("dataService: {}", dataService);
	}
	
	@Test
	public void test() {
	log.debug("test");	
	List<SysAttr> list =  dataService.loadSysAttrList();
	log.debug("list.size(): {}", list.size());
	for(SysAttr sa: list) {
		log.debug("{}\t{}\t{}\t{}", sa.getUid(),sa.getType().getDesp() ,sa.getKey(), sa.getValue());
	}
	}

}
