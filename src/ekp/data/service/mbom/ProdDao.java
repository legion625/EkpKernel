package ekp.data.service.mbom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.event.Level;

import ekp.mbom.Part;
import ekp.mbom.PartAcquisition;
import ekp.mbom.Prod;
import ekp.mbom.ProdCtl;
import ekp.mbom.ProdCtlPartCfgConj;
import ekp.mbom.ProdMod;
import ekp.mbom.ProdModItem;
import ekp.mbom.type.PartAcquisitionType;
import legion.data.service.AbstractMySqlDao;
import legion.data.service.AbstractMySqlDao.ColType;
import legion.data.service.AbstractMySqlDao.DbColumn;
import legion.util.LogUtil;

public class ProdDao extends AbstractMySqlDao {

	protected ProdDao(String source) {
		super(source);
	}

	// -------------------------------------------------------------------------------
	// -------------------------------------Prod--------------------------------------
	private final static String TB_MBOM_PROD = "mbom_prod";
	private final static String COL_P_ID = "id";
	private final static String COL_P_NAME = "name";

	boolean saveProd(Prod _p) {
		DbColumn<Prod>[] cols = new DbColumn[] { //
				DbColumn.of(COL_P_ID, ColType.STRING, Prod::getId), //
				DbColumn.of(COL_P_NAME, ColType.STRING, Prod::getName), //
		};
		return saveObject(TB_MBOM_PROD, cols, _p);
	}

	boolean deleteProd(String _uid) {
		return deleteObject(TB_MBOM_PROD, _uid);
	}

	private Prod parseProd(ResultSet _rs) {
		Prod p = null;
		try {
			p = Prod.getInstance(parseUid(_rs), parseObjectCreateTime(_rs), parseObjectUpdateTime(_rs));
			/* pack attributes */
			p.setId(_rs.getString(COL_P_ID));
			p.setName(_rs.getString(COL_P_NAME));
			return p;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}

	Prod loadProd(String _uid) {
		return loadObject(TB_MBOM_PROD, _uid, this::parseProd);
	}

	Prod loadProdById(String _id) {
		return loadObject(TB_MBOM_PROD, COL_P_ID, _id, this::parseProd);
	}

	List<Prod> loadProdList() {
		return loadObjectList(TB_MBOM_PROD, this::parseProd);
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------ProdCtl------------------------------------
	private final static String TB_MBOM_PROD_CTL = "mbom_prod_ctl";
	private final static String COL_PC_ID = "id";
	private final static String COL_PC_LV = "lv";
	private final static String COL_PC_NAME = "name";
	private final static String COL_PC_REQ = "req";
	private final static String COL_PC_PARENT_UID = "parent_uid";
	private final static String COL_PC_PARENT_ID = "parent_id";
	private final static String COL_PC_PROD_UID = "prod_uid";

	boolean saveProdCtl(ProdCtl _pc) {
		DbColumn<ProdCtl>[] cols = new DbColumn[] { //
				DbColumn.of(COL_PC_ID, ColType.STRING, ProdCtl::getId), //
				DbColumn.of(COL_PC_LV, ColType.INT, ProdCtl::getLv), //
				DbColumn.of(COL_PC_NAME, ColType.STRING, ProdCtl::getName), //
				DbColumn.of(COL_PC_REQ, ColType.BOOLEAN, ProdCtl::isReq), //
				DbColumn.of(COL_PC_PARENT_UID, ColType.STRING, ProdCtl::getParentUid), //
				DbColumn.of(COL_PC_PARENT_ID, ColType.STRING, ProdCtl::getParentId), //
				DbColumn.of(COL_PC_PROD_UID, ColType.STRING, ProdCtl::getProdUid), //
		};
		return saveObject(TB_MBOM_PROD_CTL, cols, _pc);
	}

	boolean deleteProdCtl(String _uid) {
		return deleteObject(TB_MBOM_PROD_CTL, _uid);
	}

	private ProdCtl parseProdCtl(ResultSet _rs) {
		ProdCtl pc = null;
		try {
			pc = ProdCtl.getInstance(parseUid(_rs), parseObjectCreateTime(_rs), parseObjectUpdateTime(_rs));
			/* pack attributes */
			pc.setId(_rs.getString(COL_PC_ID));
			pc.setLv(_rs.getInt(COL_PC_LV));
			pc.setName(_rs.getString(COL_PC_NAME));
			pc.setReq(_rs.getBoolean(COL_PC_REQ));
			pc.setParentUid(_rs.getString(COL_PC_PARENT_UID));
			pc.setParentId(_rs.getString(COL_PC_PARENT_ID));
			pc.setProdUid(_rs.getString(COL_PC_PROD_UID));
			return pc;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}

	ProdCtl loadProdCtl(String _uid) {
		return loadObject(TB_MBOM_PROD_CTL, _uid, this::parseProdCtl);
	}
	
	ProdCtl loadProdCtlById(String _id) {
		return loadObject(TB_MBOM_PROD_CTL,COL_PC_ID,  _id, this::parseProdCtl);
	}

	List<ProdCtl> loadProdCtlList(String _parentUid) {
		return loadObjectList(TB_MBOM_PROD_CTL, COL_PC_PARENT_UID, _parentUid, this::parseProdCtl);
	}

	List<ProdCtl> loadProdCtlListLv1(String _prodUid) {
		// TODO not implemented yet...
		return null;
	}

	// -------------------------------------------------------------------------------
	// ------------------------------ProdCtlPartCfgConj-------------------------------
	private final static String TB_MBOM_PROD_CTL_PART_CFG_CONJ = "mbom_prod_ctl_part_cfg_conj";
	private final static String COL_PCPCC_PROD_CTL_UID = "prod_ctl_uid";
	private final static String COL_PCPCC_PART_CFG_UID = "part_cfg_uid";

	boolean saveProdCtlPartCfgConj(ProdCtlPartCfgConj _pcpcc) {
		DbColumn<ProdCtlPartCfgConj>[] cols = new DbColumn[] { //
				DbColumn.of(COL_PCPCC_PROD_CTL_UID, ColType.STRING, ProdCtlPartCfgConj::getProdCtlUid), //
				DbColumn.of(COL_PCPCC_PART_CFG_UID, ColType.STRING, ProdCtlPartCfgConj::getPartCfgUid), //
		};
		return saveObject(TB_MBOM_PROD_CTL_PART_CFG_CONJ, cols, _pcpcc);
	}

	boolean deleteProdCtlPartCfgConj(String _uid) {
		return deleteObject(TB_MBOM_PROD_CTL_PART_CFG_CONJ, _uid);
	}

	private ProdCtlPartCfgConj parseProdCtlPartCfgConj(ResultSet _rs) {
		ProdCtlPartCfgConj pcpcc = null;
		try {
			String prodCtlUid = _rs.getString(COL_PCPCC_PROD_CTL_UID);
			String partCfgUid = _rs.getString(COL_PCPCC_PART_CFG_UID);
			pcpcc = ProdCtlPartCfgConj.getInstance(parseUid(_rs), prodCtlUid, partCfgUid, parseObjectCreateTime(_rs),
					parseObjectUpdateTime(_rs));
			/* pack attributes */
			// none
			return pcpcc;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}

	ProdCtlPartCfgConj loadProdCtlPartCfgConj(String _uid) {
		return loadObject(TB_MBOM_PROD_CTL_PART_CFG_CONJ, _uid, this::parseProdCtlPartCfgConj);
	}
	
	ProdCtlPartCfgConj loadProdCtlPartCfgConj(String _prodCtlUid, String _partCfgUid) {
		Map<String, String> map = new HashMap<>();
		map.put(COL_PCPCC_PROD_CTL_UID,_prodCtlUid);
		map.put(COL_PCPCC_PART_CFG_UID,_partCfgUid);
		return loadObject(TB_MBOM_PROD_CTL_PART_CFG_CONJ, map, this::parseProdCtlPartCfgConj);
	}

	List<ProdCtlPartCfgConj> loadProdCtlPartCfgConjList1(String _prodCtlUid) {
		return loadObjectList(TB_MBOM_PROD_CTL_PART_CFG_CONJ, COL_PCPCC_PROD_CTL_UID, _prodCtlUid,
				this::parseProdCtlPartCfgConj);
	}

	List<ProdCtlPartCfgConj> loadProdCtlPartCfgConjList2(String _partCfgUid) {
		return loadObjectList(TB_MBOM_PROD_CTL_PART_CFG_CONJ, COL_PCPCC_PART_CFG_UID, _partCfgUid,
				this::parseProdCtlPartCfgConj);
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------ProdMod------------------------------------
	private final static String TB_MBOM_PROD_MOD = "mbom_prod_mod";
	private final static String COL_PM_PROD_UID = "prod_uid";
	private final static String COL_PM_ID = "id";
	private final static String COL_PM_NAME = "name";
	private final static String COL_PM_DESP = "desp";

	boolean saveProdMod(ProdMod _pm) {
		DbColumn<ProdMod>[] cols = new DbColumn[] { DbColumn.of(COL_PM_PROD_UID, ColType.STRING, ProdMod::getProdUid), //
				DbColumn.of(COL_PM_ID, ColType.STRING, ProdMod::getId), //
				DbColumn.of(COL_PM_NAME, ColType.STRING, ProdMod::getName), //
				DbColumn.of(COL_PM_DESP, ColType.STRING, ProdMod::getDesp), //
		};
		return saveObject(TB_MBOM_PROD_MOD, cols, _pm);
	}

	boolean deleteProdMod(String _uid) {
		return deleteObject(TB_MBOM_PROD_MOD, _uid);
	}

	private ProdMod parseProdMod(ResultSet _rs) {
		ProdMod pm = null;
		try {
			String prodUid = _rs.getString(COL_PM_PROD_UID);
			pm = ProdMod.getInstance(parseUid(_rs), prodUid, parseObjectCreateTime(_rs), parseObjectUpdateTime(_rs));
			/* pack attributes */
			pm.setId(_rs.getString(COL_PM_ID));
			pm.setName(_rs.getString(COL_PM_NAME));
			pm.setDesp(_rs.getString(COL_PM_DESP));
			return pm;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}

	ProdMod loadProdMod(String _uid) {
		return loadObject(TB_MBOM_PROD_MOD, _uid, this::parseProdMod);
	}

	List<ProdMod> loadProdModList(String _prodUid) {
		return loadObjectList(TB_MBOM_PROD_MOD, COL_PM_PROD_UID, _prodUid, this::parseProdMod);
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------ProdModItem----------------------------------
	private final static String TB_MBOM_PROD_MOD_ITEM = "mbom_prod_mod_item";
	private final static String COL_PMI_PROD_MOD_UID = "prod_mod_uid";
	private final static String COL_PMI_PROD_CTL_UID = "prod_ctl_uid";
	private final static String COL_PMI_PART_CFG_ASSIGNED = "part_cfg_assigned";
	private final static String COL_PMI_PART_CFG_UID = "part_cfg_uid";

	boolean saveProdModItem(ProdModItem _pmi) {
		DbColumn<ProdModItem>[] cols = new DbColumn[] {
				DbColumn.of(COL_PMI_PROD_MOD_UID, ColType.STRING, ProdModItem::getProdModUid), //
				DbColumn.of(COL_PMI_PROD_CTL_UID, ColType.STRING, ProdModItem::getProdCtlUid), //
				DbColumn.of(COL_PMI_PART_CFG_ASSIGNED, ColType.BOOLEAN, ProdModItem::isPartCfgAssigned), //
				DbColumn.of(COL_PMI_PART_CFG_UID, ColType.STRING, ProdModItem::getPartCfgUid), //
		};
		return saveObject(TB_MBOM_PROD_MOD_ITEM, cols, _pmi);
	}

	boolean deleteProdModItem(String _uid) {
		return deleteObject(TB_MBOM_PROD_MOD_ITEM, _uid);
	}

	private ProdModItem parseProdModItem(ResultSet _rs) {
		ProdModItem pmi = null;
		try {
			String prodModUid = _rs.getString(COL_PMI_PROD_MOD_UID);
			String prodCtlUid = _rs.getString(COL_PMI_PROD_CTL_UID);
			pmi = ProdModItem.getInstance(parseUid(_rs), prodModUid, prodCtlUid, parseObjectCreateTime(_rs),
					parseObjectUpdateTime(_rs));
			/* pack attributes */
			pmi.setPartCfgAssigned(_rs.getBoolean(COL_PMI_PART_CFG_ASSIGNED));
			pmi.setPartCfgUid(_rs.getString(COL_PMI_PART_CFG_UID));
			return pmi;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}

	ProdModItem loadProdModItem(String _uid) {
		return loadObject(TB_MBOM_PROD_MOD_ITEM, _uid, this::parseProdModItem);
	}

	List<ProdModItem> loadProdModItemList(String _prodModUid) {
		return loadObjectList(TB_MBOM_PROD_MOD_ITEM, COL_PMI_PROD_MOD_UID, _prodModUid, this::parseProdModItem);
	}

}
