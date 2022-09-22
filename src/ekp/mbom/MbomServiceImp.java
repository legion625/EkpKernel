package ekp.mbom;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.data.MbomDataService;
import ekp.mbom.dto.PartCreateObj;
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
	@Override
	public Part createPart(PartCreateObj _dto) {
		return Part.create(_dto);
	}

	@Override
	public boolean deletePart(String _uid) {
		return loadPart(_uid).delete();
	}

	@Override
	public Part loadPart(String _uid) {
		return dataService.loadPart(_uid);
	}

	@Override
	public Part loadPartByPin(String _pin) {
		return dataService.loadPartByPin(_pin);
	}

}
