package ekp.data.service.mbom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.swing.SwingConstants;

import org.slf4j.Logger;
import org.slf4j.event.Level;

import ekp.data.service.mbom.query.PartCfgQueryParam;
import ekp.data.service.mbom.query.PpartSkewerQueryParam;
import ekp.mbom.Part;
import ekp.mbom.PartCfg;
import ekp.mbom.PartCfgConj;
import ekp.mbom.type.PartCfgStatus;
import legion.Fsm;
import legion.data.service.AbstractMySqlDao;
import legion.data.service.AbstractMySqlDao.ColType;
import legion.data.service.AbstractMySqlDao.DbColumn;
import legion.util.LogUtil;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.ConjunctiveOp;
import legion.util.query.QueryOperation.QueryValue;

class PartCfgDao extends AbstractMySqlDao {

	protected PartCfgDao(String source) {
		super(source);
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------PartCfg------------------------------------
	private final static String TB_MBOM_PART_CFG = "mbom_part_cfg";
	private final static String COL_PC_ROOT_PART_UID = "root_part_uid";
	private final static String COL_PC_ROOT_PART_PIN = "root_part_pin";
	private final static String COL_PC_STATUS_IDX = "status_idx";
	private final static String COL_PC_ID = "id";
	private final static String COL_PC_NAME = "name";
	private final static String COL_PC_DESP = "desp";
	private final static String COL_PC_PUBLISH_TIME = "publish_time";
	

	boolean savePartCfg(PartCfg _pc) {
		DbColumn<PartCfg>[] cols = new DbColumn[] { //
				DbColumn.of(COL_PC_ROOT_PART_UID, ColType.STRING, PartCfg::getRootPartUid, 45), //
				DbColumn.of(COL_PC_ROOT_PART_PIN, ColType.STRING, PartCfg::getRootPartPin, 45), //
				DbColumn.of(COL_PC_STATUS_IDX, ColType.INT, PartCfg::getStatusIdx), //
				DbColumn.of(COL_PC_ID, ColType.STRING, PartCfg::getId, 45), //
				DbColumn.of(COL_PC_NAME, ColType.STRING, PartCfg::getName, 45), //
				DbColumn.of(COL_PC_DESP, ColType.STRING, PartCfg::getDesp, 200), //
				DbColumn.of(COL_PC_PUBLISH_TIME, ColType.LONG, PartCfg::getPublishTime), //
		};
		return saveObject(TB_MBOM_PART_CFG, cols, _pc);
	}

	boolean deletePartCfg(String _uid) {
		return deleteObject(TB_MBOM_PART_CFG, _uid);
	}

	private PartCfg parsePartCfg(ResultSet _rs) {
		PartCfg pc = null;
		try {
			String rootPartUid = _rs.getString(COL_PC_ROOT_PART_UID);
			String rootPartPin = _rs.getString(COL_PC_ROOT_PART_PIN);
			PartCfgStatus status = PartCfgStatus.get(_rs.getInt(COL_PC_STATUS_IDX));
			pc = PartCfg.getInstance(parseUid(_rs), rootPartUid, rootPartPin, status, parseObjectCreateTime(_rs),
					parseObjectUpdateTime(_rs));
			/* pack attributes */
			pc.setId(_rs.getString(COL_PC_ID));
			pc.setName(_rs.getString(COL_PC_NAME));
			pc.setDesp(_rs.getString(COL_PC_DESP));
			pc.setPublishTime(_rs.getLong(COL_PC_PUBLISH_TIME));
			return pc;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}

	PartCfg loadPartCfg(String _uid) {
		return loadObject(TB_MBOM_PART_CFG, _uid, this::parsePartCfg);
	}

	PartCfg loadPartCfgById(String _id) {
		return loadObject(TB_MBOM_PART_CFG, COL_PC_ID, _id, this::parsePartCfg);
	}

	List<PartCfg> loadPartCfgList(String _rootPartUid) {
		return loadObjectList(TB_MBOM_PART_CFG, COL_PC_ROOT_PART_UID, _rootPartUid, this::parsePartCfg);
	}
	
	private static String parsePartCfgQueryParamMapping(PartCfgQueryParam _p) {
		String col = null;
		switch (_p) {
		case ROOT_PART_UID:
			col = COL_PC_ROOT_PART_UID;
			break;
		case ROOT_PART_PIN:
			col = COL_PC_ROOT_PART_PIN;
			break;
		case STATUS_IDX:
			col = COL_PC_STATUS_IDX;
			break;
		case ID:
			col = COL_PC_ID;
			break;
		case NAME:
			col = COL_PC_NAME;
			break;
		case DESP:
			col = COL_PC_DESP;
			break;
		case PUBLISH_TIME:
			col = COL_PC_PUBLISH_TIME;
			break;
		default:
			return null;
		}
		return col;
	}
	
	QueryOperation<PartCfgQueryParam, PartCfg> searchPartCfg(QueryOperation<PartCfgQueryParam, PartCfg> _param) {
		return searchObject(TB_MBOM_PART_CFG, _param, PartCfgDao::parsePartCfgQueryParamMapping, this::parsePartCfg);
	}
	
	static String packPartCfgField(PpartSkewerQueryParam _p, String _tbPartAcq) {
		switch (_p) {
		case PC_ROOT_PART_UID:
			return packConjQueryField(COL_PC_ROOT_PART_UID, _tbPartAcq, TB_MBOM_PART_CFG, TB_MBOM_PART_CFG_CONJ,
					COL_PCC_PART_ACQ_UID, COL_PCC_PART_CFG_UID);
		case PC_ROOT_PART_PIN:
			return packConjQueryField(COL_PC_ROOT_PART_PIN, _tbPartAcq, TB_MBOM_PART_CFG, TB_MBOM_PART_CFG_CONJ,
					COL_PCC_PART_ACQ_UID, COL_PCC_PART_CFG_UID);
		case B_OF_PC_ROOT_PART:
			return packConjQueryField(COL_PC_ROOT_PART_UID, _tbPartAcq, TB_MBOM_PART_CFG, TB_MBOM_PART_CFG_CONJ,
					COL_PCC_PART_ACQ_UID, COL_PCC_PART_CFG_UID);
		default:
			return null;
		}
	}
	
	/** PpartSkewerQueryParam.B_OF_PC$_PA_EXISTS */
	// FIXME
	static String packPartCfgFieldPartAcqExists(PpartSkewerQueryParam _p,
			Map<PpartSkewerQueryParam, QueryValue[]> _inSelectQueryValueMap, String _tbAliasPa, String _colPaUid) {
		return packExistsField(TB_MBOM_PART_CFG, PartCfgDao::parsePartCfgQueryParamMapping, _inSelectQueryValueMap,
				ConjunctiveOp.and, _p,
//				COL_PCC_PART_ACQ_UID + " = " + _tbAliasPa + "." + _colPaUid
				packPartCfgConjFieldPartAcqExists(TB_MBOM_PART_CFG, COL_UID, _tbAliasPa, _colPaUid)
				);
	}

	/** PpartSkewerQueryParam.B_OF_PC$_PARENT_PART_EXISTS */
	static String packPartCfgFieldParentPartExists(PpartSkewerQueryParam _p,
			Map<PpartSkewerQueryParam, QueryValue[]> _inSelectQueryValueMap, String _tbAliasPart, String _colPartUid) {
		return packExistsField(TB_MBOM_PART_CFG, PartCfgDao::parsePartCfgQueryParamMapping, _inSelectQueryValueMap,
				ConjunctiveOp.and, _p,
				packPartCfgConjExistsField4PcIdsInParentPa(TB_MBOM_PART_CFG, COL_UID, _tbAliasPart, _colPartUid));
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------PartCfgConj----------------------------------
	private final static String TB_MBOM_PART_CFG_CONJ = "mbom_part_cfg_conj";
	private final static String COL_PCC_PART_CFG_UID = "part_cfg_uid";
	private final static String COL_PCC_PART_ACQ_UID = "part_acq_uid";

	boolean savePartCfgConj(PartCfgConj _pcc) {
		DbColumn<PartCfgConj>[] cols = new DbColumn[] { //
				DbColumn.of(COL_PCC_PART_CFG_UID, ColType.STRING, PartCfgConj::getPartCfgUid, 45), //
				DbColumn.of(COL_PCC_PART_ACQ_UID, ColType.STRING, PartCfgConj::getPartAcqUid, 45), //
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

	PartCfgConj loadPartCfgConj(String _partCfgUid, String _partAcqUid) {
		Map<String, String> map = new HashMap<>();
		map.put(COL_PCC_PART_CFG_UID, _partCfgUid);
		map.put(COL_PCC_PART_ACQ_UID, _partAcqUid);
		return loadObject(TB_MBOM_PART_CFG_CONJ, map, this::parsePartCfgConj);
	}

	List<PartCfgConj> loadPartCfgConjList(String _partCfgUid) {
		return loadObjectList(TB_MBOM_PART_CFG_CONJ, COL_PCC_PART_CFG_UID, _partCfgUid, this::parsePartCfgConj);
	}
	
	List<PartCfgConj> loadPartCfgConjListByPartAcq(String _partAcqUid){
		return loadObjectList(TB_MBOM_PART_CFG_CONJ, COL_PCC_PART_ACQ_UID, _partAcqUid, this::parsePartCfgConj);
	}
	
	/** PpartSkewerQueryParam.B_OF_PC$_PA_EXISTS */
	private static String packPartCfgConjFieldPartAcqExists(String _tbPc, String _colPcUid,
			String _tbAliasPa, String _colPaUid) {
		return packExistsField(_tbPc, _colPcUid, TB_MBOM_PART_CFG_CONJ, COL_PCC_PART_CFG_UID,
				COL_PCC_PART_ACQ_UID + " = " + _tbAliasPa + "." + _colPaUid
//				PartDao.packParsExistsField4PcIdsInParentPa(TB_MBOM_PART_CFG_CONJ, COL_PCC_PART_ACQ_UID, _tbAliasPart,
//						_colPartUid)
				);
	} 
	
	/** PpartSkewerQueryParam.B_OF_PC$_PARENT_PART_EXISTS */
	private static String packPartCfgConjExistsField4PcIdsInParentPa(String _tbPc, String _colPcUid,
			String _tbAliasPart, String _colPartUid) {
		return packExistsField(_tbPc, _colPcUid, TB_MBOM_PART_CFG_CONJ, COL_PCC_PART_CFG_UID,
				PartDao.packParsExistsField4PcIdsInParentPa(TB_MBOM_PART_CFG_CONJ, COL_PCC_PART_ACQ_UID, _tbAliasPart,
						_colPartUid));
	}

}

