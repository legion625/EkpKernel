package ekp.data.service.mbom;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.data.MbomDataService;
import ekp.mbom.Part;

public class MbomDataServiceImp implements MbomDataService {
	private Logger log = LoggerFactory.getLogger(MbomDataServiceImp.class);

	private String source;

	// dao
	private PartDao partDao;

	@Override
	public void register(Map<String, String> _params) {
		if (_params == null || _params.isEmpty())
			return;

		source = _params.get("source");

		// dao
		partDao = new PartDao(source);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	// -------------------------------------------------------------------------------
	// -------------------------------------Part--------------------------------------
	@Override
	public boolean savePart(Part _p) {
		return partDao.savePart(_p);
	}

	@Override
	public boolean deletePart(String _uid) {
		return partDao.deletePart(_uid);
	}

	@Override
	public Part loadPart(String _uid) {
		return partDao.loadPart(_uid);
	}

	@Override
	public Part loadPartByPin(String _pin) {
		return partDao.loadPartByPin(_pin);
	}

}
