package ekp.data;

import java.util.List;

import ekp.invt.MaterialInst;
import ekp.invt.MaterialMaster;
import ekp.invt.WrhsBin;
import ekp.invt.WrhsLoc;
import legion.IntegrationService;
import legion.util.query.QueryOperation;

public interface InvtDataService extends IntegrationService {
	
	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsLoc------------------------------------
	public boolean saveWrhsLoc(WrhsLoc _wl);
	public boolean deleteWrhsLoc(String _uid);
	public WrhsLoc loadWrhsLoc(String _uid);
	public WrhsLoc loadWrhsLocById(String _id);
	public List<WrhsLoc> loadWrhsLocList();
	
	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsBin------------------------------------
	public boolean saveWrhsBin(WrhsBin _wb);
	public boolean deleteWrhsBin(String _uid);
	public WrhsBin loadWrhsBin(String _uid);
	public List<WrhsBin> loadWrhsBinList(String _wlUid);
	
	// -------------------------------------------------------------------------------
	// --------------------------------MaterialMaster---------------------------------
	public boolean saveMaterialMaster(MaterialMaster _mm);
	public boolean deleteMaterialMaster(String _uid);
	public MaterialMaster loadMaterialMaster(String _uid);
	public MaterialMaster loadMaterialMasterByMano(String _mano);
	
	// -------------------------------------------------------------------------------
	// ---------------------------------MaterialInst----------------------------------
	public boolean saveMaterialInst(MaterialInst _mi);
	public boolean deleteMaterialInst(String _uid);
	public MaterialInst loadMaterialInst(String _uid);
	public MaterialInst loadMaterialInstByMisn(String _misn);
	public List<MaterialInst> loadMaterialInstList(String _mmUid);
	
	
	

}
