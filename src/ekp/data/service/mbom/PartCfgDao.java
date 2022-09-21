package ekp.data.service.mbom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.event.Level;

import ekp.mbom.Part;
import ekp.mbom.PartCfg;
import ekp.mbom.PartCfgConj;
import legion.data.service.AbstractMySqlDao;
import legion.data.service.AbstractMySqlDao.ColType;
import legion.data.service.AbstractMySqlDao.DbColumn;
import legion.util.LogUtil;

class PartCfgDao extends AbstractMySqlDao {

	protected PartCfgDao(String source) {
		super(source);
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------PartCfg------------------------------------
	boolean savePartCfg(PartCfg _pc) {
		// TODO
		return false;
	}

	boolean deletePartCfg(String _uid) {
		// TODO
		return false;
	}

	PartCfg loadPartCfg(String _uid) {
		// TODO
		return null;
	}

	List<PartCfg> loadPartCfgList(String _rootPartUid) {
		// TODO
		return null;
	}
	// TODO

	// -------------------------------------------------------------------------------
	// ----------------------------------PartCfgConj----------------------------------
	private final static String TB_MBOM_PART_CFG_CONJ = "mbom_part_cfg_conj";
	private final static String COL_PCC_PART_CFG_UID = "part_cfg_uid";
	private final static String COL_PCC_PART_ACQ_UID = "part_acq_uid";

	boolean savePartCfgConj(PartCfgConj _pcc) {
		DbColumn<PartCfgConj>[] cols = new DbColumn[] { //
				DbColumn.of(COL_PCC_PART_CFG_UID, ColType.STRING, PartCfgConj::getPartCfgUid), //
				DbColumn.of(COL_PCC_PART_ACQ_UID, ColType.STRING, PartCfgConj::getPartAcqUid), //
		};
		return saveObject(TB_MBOM_PART_CFG_CONJ, cols, _pcc);
	}

	boolean deletePartCfgConj(String _uid) {
		return deleteObject(TB_MBOM_PART_CFG_CONJ, _uid);
	}

	private PartCfgConj parsePartCfgConj(ResultSet _rs) {
		PartCfgConj pcc = null;
		try {
			String partCfgUid = _rs.getString(COL_PCC_PART_CFG_UID);
			pcc = PartCfgConj.getInstance(parseUid(_rs), partCfgUid, parseObjectCreateTime(_rs),
					parseObjectUpdateTime(_rs));
			/* pack attributes */
			pcc.setPartAcqUid(_rs.getString(COL_PCC_PART_ACQ_UID));
			return pcc;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}

	PartCfgConj loadPartCfgConj(String _uid) {
		return loadObject(TB_MBOM_PART_CFG_CONJ, _uid, this::parsePartCfgConj);
	}

	List<PartCfgConj> loadPartCfgConjList(String _partCfgUid) {
		return loadObjectList(TB_MBOM_PART_CFG_CONJ, COL_PCC_PART_CFG_UID, _partCfgUid, this::parsePartCfgConj);
	}
}
