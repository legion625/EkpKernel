package ekp.mbom;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.data.MbomDataService;
import legion.DataServiceFactory;

public class MbomServiceImp implements MbomService {
	private Logger log = LoggerFactory.getLogger(MbomServiceImp.class);
	
	private static MbomDataService dataService;
	
	@Override
	public void register(Map<String, String> _params) {
		dataService = DataServiceFactory.getInstance().getService(MbomDataService.class);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	// -------------------------------------------------------------------------------
	// -------------------------------------Part--------------------------------------

	
}
