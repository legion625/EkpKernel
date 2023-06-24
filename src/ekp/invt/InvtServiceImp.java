package ekp.invt;

import java.util.Map;

import ekp.data.InvtDataService;
import legion.DataServiceFactory;

public class InvtServiceImp implements InvtService {
	private static InvtDataService dataService;

	@Override
	public void register(Map<String, String> _params) {
		dataService = DataServiceFactory.getInstance().getService(InvtDataService.class);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	// -------------------------------------------------------------------------------

}
