package ekp.data.service.invt;

import java.util.Map;

import ekp.data.InvtDataService;

public class InvtDataServiceImp implements InvtDataService {

	private String source;

	@Override
	public void register(Map<String, String> _params) {
		if (_params == null || _params.isEmpty())
			return;

		source = _params.get("source");

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	// -------------------------------------------------------------------------------

}
