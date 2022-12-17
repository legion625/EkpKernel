package ekp.data.service.mbom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import ekp.mbom.Part;
import ekp.mbom.PartAcqRoutingStep;
import ekp.TestLogMark;
import ekp.data.service.mbom.query.PartQueryParam;
import ekp.data.service.mbom.query.PpartSkewerQueryParam;
import ekp.mbom.ParsPart;
import ekp.mbom.ParsProc;
import ekp.mbom.PartAcquisition;
import ekp.mbom.PartCfg;
import ekp.mbom.dto.PpartSkewer;
import ekp.mbom.type.PartAcqStatus;
import ekp.mbom.type.PartAcquisitionType;
import ekp.mbom.type.PartUnit;
import legion.data.AbstractDao;
import legion.data.service.AbstractMySqlDao;
import legion.data.service.AbstractMySqlDao.ColType;
import legion.data.service.AbstractMySqlDao.DbColumn;
import legion.data.skewer.TableColPack;
import legion.data.skewer.TableRel;
import legion.util.LogUtil;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public class PartDao extends AbstractMySqlDao {

	PartDao(String source) {
		super(source);
	}

	// -------------------------------------------------------------------------------
	// -------------------------------------Part--------------------------------------
	private final static String TB_MBOM_PART = "mbom_part";
	private final static String COL_P_PIN = "pin";
	private final static String COL_P_NAME = "name";
	private final static String COL_P_UNIT_IDX = "unit_idx";

	boolean savePart(Part _p) {
		DbColumn<Part>[] cols = new DbColumn[] { //
				DbColumn.of(COL_P_PIN, ColType.STRING, Part::getPin, 45), //
				DbColumn.of(COL_P_NAME, ColType.STRING, Part::getName, 45), //
				DbColumn.of(COL_P_UNIT_IDX, ColType.INT, Part::getUnitIdx, 45), //
		};
		return saveObject(TB_MBOM_PART, cols, _p);
	}

	boolean deletePart(String _uid) {
		return deleteObject(TB_MBOM_PART, _uid);
	}

	private Part parsePart(ResultSet _rs) {
		Part p = null;
		try {
			p = Part.getInstance(parseUid(_rs), parseObjectCreateTime(_rs), parseObjectUpdateTime(_rs));
			/* pack attributes */
			p.setPin(_rs.getString(COL_P_PIN));
			p.setName(_rs.getString(COL_P_NAME));
			p.setUnit(PartUnit.get(_rs.getInt(COL_P_UNIT_IDX)));
			return p;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}

	Part loadPart(String _uid) {
		return loadObject(TB_MBOM_PART, _uid, this::parsePart);
	}

	Part loadPartByPin(String _pin) {
		return loadObject(TB_MBOM_PART, COL_P_PIN, _pin, this::parsePart);
	}

	private String parsePartQueryParamMapping(PartQueryParam _p) {
		switch (_p) {
		/* part */
		case PIN:
			return COL_P_PIN;
		case NAME:
			return COL_P_NAME;
		case UNIT_IDX:
			return COL_P_UNIT_IDX;
		default:
			log.error("PartQueryParam error.");
			return null;
		}
	}

	QueryOperation<PartQueryParam, Part> searchPart(QueryOperation<PartQueryParam, Part> _param) {
		return searchObject(TB_MBOM_PART, _param, this::parsePartQueryParamMapping, this::parsePart);
	}
	
	// -------------------------------------------------------------------------------
	// --------------------------------PartAcquisition--------------------------------
	private final static String TB_MBOM_PART_ACQUISITION = "mbom_part_acq";
	private final static String COL_PA_PART_UID = "part_uid";
	private final static String COL_PA_PART_PIN = "part_pin";
	private final static String COL_PA_STATUS_IDX = "status_idx";
	private final static String COL_PA_ID = "id";
	private final static String COL_PA_NAME = "name";
	private final static String COL_PA_TYPE_IDX = "type_idx";
	private final static String COL_PA_PUBLISH_TIME = "publish_time";
	private final static String COL_PA_REF_UNIT_COST = "ref_unit_cost";
	

	boolean savePartAcquisition(PartAcquisition _pa) {
		DbColumn<PartAcquisition>[] cols = new DbColumn[] { //
				DbColumn.of(COL_PA_PART_UID, ColType.STRING, PartAcquisition::getPartUid, 45), //
				DbColumn.of(COL_PA_PART_PIN, ColType.STRING, PartAcquisition::getPartPin, 45), //
				DbColumn.of(COL_PA_STATUS_IDX, ColType.INT, PartAcquisition::getStatusIdx), //
				DbColumn.of(COL_PA_ID, ColType.STRING, PartAcquisition::getId, 45), //
				DbColumn.of(COL_PA_NAME, ColType.STRING, PartAcquisition::getName, 45), //
				DbColumn.of(COL_PA_TYPE_IDX, ColType.INT, PartAcquisition::getTypeIdx), //
				DbColumn.of(COL_PA_PUBLISH_TIME, ColType.LONG, PartAcquisition::getPublishTime), //
				DbColumn.of(COL_PA_REF_UNIT_COST, ColType.DOUBLE, PartAcquisition::getRefUnitCost), //
				
		};
		return saveObject(TB_MBOM_PART_ACQUISITION, cols, _pa);
	}

	boolean deletePartAcquisition(String _uid) {
		return deleteObject(TB_MBOM_PART_ACQUISITION, _uid);
	}

	private PartAcquisition parsePartAcquisition(ResultSet _rs) {
		PartAcquisition pa = null;
		try {
			String partUid = _rs.getString(COL_PA_PART_UID);
			String partPin = _rs.getString(COL_PA_PART_PIN);
			PartAcqStatus status = PartAcqStatus.get(_rs.getInt(COL_PA_STATUS_IDX));
			pa = PartAcquisition.getInstance(parseUid(_rs), partUid, partPin, status, parseObjectCreateTime(_rs),
					parseObjectUpdateTime(_rs));
			/* pack attributes */
			pa.setId(_rs.getString(COL_PA_ID));
			pa.setName(_rs.getString(COL_PA_NAME));
			pa.setType(PartAcquisitionType.get(_rs.getInt(COL_PA_TYPE_IDX)));
			pa.setPublishTime(_rs.getLong(COL_PA_PUBLISH_TIME));
			pa.setRefUnitCost(_rs.getDouble(COL_PA_REF_UNIT_COST));
			return pa;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}

	PartAcquisition loadPartAcquisition(String _uid) {
		return loadObject(TB_MBOM_PART_ACQUISITION, _uid, this::parsePartAcquisition);
	}

	PartAcquisition loadPartAcquisition(String _partPin, String _id) {
		Map<String, String> map = new HashMap<>();
		map.put(COL_PA_PART_PIN, _partPin);
		map.put(COL_PA_ID, _id);
		return loadObject(TB_MBOM_PART_ACQUISITION, map, this::parsePartAcquisition);
	}

	List<PartAcquisition> loadPartAcquisitionList(String _partUid) {
		return loadObjectList(TB_MBOM_PART_ACQUISITION, COL_PA_PART_UID, _partUid, this::parsePartAcquisition);
	}

	// -------------------------------------------------------------------------------
	// ------------------------------PartAcqRoutingStep-------------------------------
	private final static String TB_MBOM_PART_ACQ_ROUTING_STEP = "mbom_part_acq_r_s";
	private final static String COL_PARS_PART_ACQ_UID = "part_acq_uid";
	private final static String COL_PARS_SEQ = "seq";
	private final static String COL_PARS_NAME = "name";
	private final static String COL_PARS_DESP = "desp";

	boolean savePartAcqRoutingStep(PartAcqRoutingStep _pars) {
		DbColumn<PartAcqRoutingStep>[] cols = new DbColumn[] { //
				DbColumn.of(COL_PARS_PART_ACQ_UID, ColType.STRING, PartAcqRoutingStep::getPartAcqUid, 45), //
				DbColumn.of(COL_PARS_SEQ, ColType.STRING, PartAcqRoutingStep::getSeq, 45), //
				DbColumn.of(COL_PARS_NAME, ColType.STRING, PartAcqRoutingStep::getName, 45), //
				DbColumn.of(COL_PARS_DESP, ColType.STRING, PartAcqRoutingStep::getDesp, 200), //
		};
		return saveObject(TB_MBOM_PART_ACQ_ROUTING_STEP, cols, _pars);
	}

	boolean deletePartAcqRoutingStep(String _uid) {
		return deleteObject(TB_MBOM_PART_ACQ_ROUTING_STEP, _uid);
	}

	private PartAcqRoutingStep parsePartAcqRoutingStep(ResultSet _rs) {
		PartAcqRoutingStep pars = null;
		try {
			String partAcqUid = _rs.getString(COL_PARS_PART_ACQ_UID);
			pars = PartAcqRoutingStep.getInstance(parseUid(_rs), partAcqUid, parseObjectCreateTime(_rs),
					parseObjectUpdateTime(_rs));
			/* pack attributes */
			pars.setSeq(_rs.getString(COL_PARS_SEQ));
			pars.setName(_rs.getString(COL_PARS_NAME));
			pars.setDesp(_rs.getString(COL_PARS_DESP));
			return pars;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}

	PartAcqRoutingStep loadPartAcqRoutingStep(String _uid) {
		return loadObject(TB_MBOM_PART_ACQ_ROUTING_STEP, _uid, this::parsePartAcqRoutingStep);
	}

	PartAcqRoutingStep loadPartAcqRoutingStep(String _partAcqUid, String _seq) {
		Map<String, String> keyValueMap = new HashMap<>();
		keyValueMap.put(COL_PARS_PART_ACQ_UID, _partAcqUid);
		keyValueMap.put(COL_PARS_SEQ, _seq);
		return loadObject(TB_MBOM_PART_ACQ_ROUTING_STEP, keyValueMap, this::parsePartAcqRoutingStep);
	}

	List<PartAcqRoutingStep> loadPartAcqRoutingStepList(String _partAcqUid) {
		return loadObjectList(TB_MBOM_PART_ACQ_ROUTING_STEP, COL_PARS_PART_ACQ_UID, _partAcqUid,
				this::parsePartAcqRoutingStep);
	}
	
	/** PpartSkewerQueryParam.B_OF_PC$_PARENT_PART_EXISTS */
	static String packParsExistsField4PcIdsInParentPa(String _tbPcc, String _colPccPartAcqUid
			, String _tbAliasPart, String _colPartUid) {
		return packExistsField(_tbPcc, _colPccPartAcqUid, TB_MBOM_PART_ACQ_ROUTING_STEP, COL_PARS_PART_ACQ_UID,
				packPpartExistsField4PcIdsInParentPa(TB_MBOM_PART_ACQ_ROUTING_STEP, COL_UID, _tbAliasPart,
						_colPartUid));
	}
	

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsProc------------------------------------
	private final static String TB_MBOM_PARS_PROC = "mbom_pars_proc";
	private final static String COL_PARS_PROC_PARS_UID = "pars_uid";
	private final static String COL_PARS_PROC_SEQ = "seq";
	private final static String COL_PARS_PROC_NAME = "name";
	private final static String COL_PARS_PROC_DESP = "desp";
	private final static String COL_PARS_PROC_ASSIGN_PROC = "assign_proc";
	private final static String COL_PARS_PROC_PROC_UID = "proc_uid";
	private final static String COL_PARS_PROC_PROC_ID = "proc_id";

	boolean saveParsProc(ParsProc _parsProc) {
		DbColumn<ParsProc>[] cols = new DbColumn[] { //
				DbColumn.of(COL_PARS_PROC_PARS_UID, ColType.STRING, ParsProc::getParsUid, 45), //
				DbColumn.of(COL_PARS_PROC_SEQ, ColType.STRING, ParsProc::getSeq, 45), //
				DbColumn.of(COL_PARS_PROC_NAME, ColType.STRING, ParsProc::getName, 45), //
				DbColumn.of(COL_PARS_PROC_DESP, ColType.STRING, ParsProc::getDesp, 200), //
				DbColumn.of(COL_PARS_PROC_ASSIGN_PROC, ColType.BOOLEAN, ParsProc::isAssignProc), //
				DbColumn.of(COL_PARS_PROC_PROC_UID, ColType.STRING, ParsProc::getProcUid, 45), //
				DbColumn.of(COL_PARS_PROC_PROC_ID, ColType.STRING, ParsProc::getProcId, 45), //
		};
		return saveObject(TB_MBOM_PARS_PROC, cols, _parsProc);
	}

	boolean deleteParsProc(String _uid) {
		return deleteObject(TB_MBOM_PARS_PROC, _uid);
	}

	private ParsProc parseParsProc(ResultSet _rs) {
		ParsProc parsProc = null;
		try {
			String parsUid = _rs.getString(COL_PARS_PROC_PARS_UID);
			parsProc = ParsProc.getInstance(parseUid(_rs), parsUid, parseObjectCreateTime(_rs),
					parseObjectUpdateTime(_rs));
			/* pack attributes */
			parsProc.setSeq(_rs.getString(COL_PARS_PROC_SEQ));
			parsProc.setName(_rs.getString(COL_PARS_PROC_NAME));
			parsProc.setDesp(_rs.getString(COL_PARS_PROC_DESP));
			parsProc.setAssignProc(_rs.getBoolean(COL_PARS_PROC_ASSIGN_PROC));
			parsProc.setProcUid(_rs.getString(COL_PARS_PROC_PROC_UID));
			parsProc.setProcId(_rs.getString(COL_PARS_PROC_PROC_ID));
			return parsProc;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}

	ParsProc loadParsProc(String _uid) {
		return loadObject(TB_MBOM_PARS_PROC, _uid, this::parseParsProc);
	}

	List<ParsProc> loadParsProcList(String _parsUid) {
		return loadObjectList(TB_MBOM_PARS_PROC, COL_PARS_PROC_PARS_UID, _parsUid, this::parseParsProc);
	}

	List<ParsProc> loadParsProcListByProc(String _processUid) {
		return loadObjectList(TB_MBOM_PARS_PROC, COL_PARS_PROC_PROC_UID, _processUid, this::parseParsProc);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsPart------------------------------------
	private final static String TB_MBOM_PARS_PART = "mbom_pars_part";
	private final static String COL_PARS_PART_PARS_UID = "pars_uid";
	private final static String COL_PARS_PART_ASSIGN_PART = "assign_part";
	private final static String COL_PARS_PART_PART_UID = "part_uid";
	private final static String COL_PARS_PART_PART_PIN = "part_pin";
	private final static String COL_PARS_PART_PART_REQ_QTY = "part_req_qty";

	boolean saveParsPart(ParsPart _parsPart) {
		DbColumn<ParsPart>[] cols = new DbColumn[] {
				DbColumn.of(COL_PARS_PART_PARS_UID, ColType.STRING, ParsPart::getParsUid, 45), //
				DbColumn.of(COL_PARS_PART_ASSIGN_PART, ColType.BOOLEAN, ParsPart::isAssignPart), //
				DbColumn.of(COL_PARS_PART_PART_UID, ColType.STRING, ParsPart::getPartUid, 45), //
				DbColumn.of(COL_PARS_PART_PART_PIN, ColType.STRING, ParsPart::getPartPin, 45), //
				DbColumn.of(COL_PARS_PART_PART_REQ_QTY, ColType.DOUBLE, ParsPart::getPartReqQty), //
		};
		return saveObject(TB_MBOM_PARS_PART, cols, _parsPart);
	}

	boolean deleteParsPart(String _uid) {
		return deleteObject(TB_MBOM_PARS_PART, _uid);
	}

	private ParsPart parseParsPart(ResultSet _rs) {
		ParsPart parsPart = null;
		try {
			String parsUid = _rs.getString(COL_PARS_PART_PARS_UID);
			parsPart = ParsPart.getInstance(parseUid(_rs), parsUid, parseObjectCreateTime(_rs),
					parseObjectUpdateTime(_rs));
			/* pack attributes */
			parsPart.setAssignPart(_rs.getBoolean(COL_PARS_PART_ASSIGN_PART));
			parsPart.setPartUid(_rs.getString(COL_PARS_PART_PART_UID));
			parsPart.setPartPin(_rs.getString(COL_PARS_PART_PART_PIN));
			parsPart.setPartReqQty(_rs.getDouble(COL_PARS_PART_PART_REQ_QTY));
			return parsPart;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}

	ParsPart loadParsPart(String _uid) {
		return loadObject(TB_MBOM_PARS_PART, _uid, this::parseParsPart);
	}

	ParsPart loadParsPart(String _parsUid, String _partuid) {
		Map<String, String> map = new HashMap<>();
		map.put(COL_PARS_PART_PARS_UID, _parsUid);
		map.put(COL_PARS_PART_PART_UID, _partuid);
		return loadObject(TB_MBOM_PARS_PART, map, this::parseParsPart);
	}

	List<ParsPart> loadParsPartList(String _parsUid) {
		return loadObjectList(TB_MBOM_PARS_PART, COL_PARS_PART_PARS_UID, _parsUid, this::parseParsPart);
	}

	List<ParsPart> loadParsPartListByPart(String _partUid) {
		return loadObjectList(TB_MBOM_PARS_PART, COL_PARS_PART_PART_UID, _partUid, this::parseParsPart);
	}
	
	/** PpartSkewerQueryParam.B_OF_PC$_PARENT_PART_EXISTS */
	static String packPpartExistsField4PcIdsInParentPa(String _tbPars, String _colParsUid, String _tbAliasPart,
			String _colPartUid) {
		String ppartSubConditionSql = COL_PARS_PART_PART_UID + " = " + _tbAliasPart + "." + _colPartUid;
		return packExistsField(_tbPars, _colParsUid, TB_MBOM_PARS_PART, COL_PARS_PART_PARS_UID, ppartSubConditionSql);
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------PpartSkewer----------------------------------
	/* TableColPack */
	private final static TableColPack PPART_SKEWER_P = TableColPack.of("p", TB_MBOM_PART,
			new String[] { COL_UID, COL_P_PIN, COL_P_NAME });
	private final static TableColPack PPART_SKEWER_PA = TableColPack.of("pa", TB_MBOM_PART_ACQUISITION,
			new String[] { COL_UID, COL_PA_ID, COL_PA_NAME });
	private final static TableColPack PPART_SKEWER_PARS = TableColPack.of("pars", TB_MBOM_PART_ACQ_ROUTING_STEP,
			new String[] { COL_PARS_SEQ, COL_PARS_NAME, COL_PARS_DESP });
	private final static TableColPack PPART_SKEWER_PPART = TableColPack.of(TB_MBOM_PARS_PART);
	private final static TableColPack PPART_SKEWER_PPART_P = TableColPack.of("ppart_p", TB_MBOM_PART,
			new String[] { COL_P_NAME });
	private final static TableColPack[] PPART_SKEWER_TCPs = new TableColPack[] { PPART_SKEWER_P, PPART_SKEWER_PA,
			PPART_SKEWER_PARS, PPART_SKEWER_PPART, PPART_SKEWER_PPART_P };
	/* TableRel */
	private final TableRel[] ppartSkewerTrs = new TableRel[] {
			TableRel.of(PPART_SKEWER_P, PPART_SKEWER_PA, COL_UID, COL_PA_PART_UID), //
			TableRel.of(PPART_SKEWER_PA, PPART_SKEWER_PARS, COL_UID, COL_PARS_PART_ACQ_UID), //
			TableRel.of(PPART_SKEWER_PARS, PPART_SKEWER_PPART, COL_UID, COL_PARS_PART_PARS_UID), //
			TableRel.of(PPART_SKEWER_PPART, PPART_SKEWER_PPART_P, COL_PARS_PART_PART_UID, COL_UID), //
	};

	private PpartSkewer parsePpartSkewer(ResultSet _rs) {
		PpartSkewer s = null;
		try {
			s = new PpartSkewer();

			/* p */
			s.setpUid(_rs.getString(PPART_SKEWER_P.getNewCol(COL_UID)));
			s.setpPin(_rs.getString(PPART_SKEWER_P.getNewCol(COL_P_PIN)));
			s.setpName(_rs.getString(PPART_SKEWER_P.getNewCol(COL_P_NAME)));

			/* pa */
			s.setPaUid(_rs.getString(PPART_SKEWER_PA.getNewCol(COL_UID)));
			s.setPaId(_rs.getString(PPART_SKEWER_PA.getNewCol(COL_PA_ID)));
			s.setPaName(_rs.getString(PPART_SKEWER_PA.getNewCol(COL_PA_NAME)));

			/* pars */
			s.setParsSeq(_rs.getString(PPART_SKEWER_PARS.getNewCol(COL_PARS_SEQ)));
			s.setParsName(_rs.getString(PPART_SKEWER_PARS.getNewCol(COL_PARS_NAME)));
			s.setParsDesp(_rs.getString(PPART_SKEWER_PARS.getNewCol(COL_PARS_DESP)));

			/* ppart */
			s.setUid(parseUid(_rs));
			s.setObjectCreateTime(parseObjectCreateTime(_rs));
			s.setObjectUpdateTime(parseObjectUpdateTime(_rs));
			s.setParsUid(_rs.getString(COL_PARS_PART_PARS_UID)); // ref data key
			s.setAssignPart(_rs.getBoolean(COL_PARS_PART_ASSIGN_PART));
			s.setPartUid(_rs.getString(COL_PARS_PART_PART_UID));
			s.setPartPin(_rs.getString(COL_PARS_PART_PART_PIN));
			s.setPartReqQty(_rs.getDouble(COL_PARS_PART_PART_REQ_QTY));

			/* ppart-p */
			s.setPartName(_rs.getString(PPART_SKEWER_PPART_P.getNewCol(COL_P_NAME)));

			return s;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}

	PpartSkewer loadPpartSkewer(String _uid) {
		log.debug("loadPpartSkewer");
		log.error("loadPpartSkewer");
		return loadSkewer(PPART_SKEWER_TCPs, ppartSkewerTrs, PPART_SKEWER_PPART.getSqlCol(COL_UID), _uid,
				this::parsePpartSkewer);
	}

	private String parsePpartSkewerQueryParamMapping(PpartSkewerQueryParam _p,
			Map<PpartSkewerQueryParam, QueryValue[]> _inSelectQueryValueMap) {
		switch (_p) {
		/* p */
		case P_UID:
			return PPART_SKEWER_P.getSqlCol(COL_UID);
		case P_PIN:
			return PPART_SKEWER_P.getSqlCol(COL_P_PIN);
		case P_NAME:
			return PPART_SKEWER_P.getSqlCol(COL_P_NAME);
		/* pa */
		case PA_UID:
			return PPART_SKEWER_PA.getSqlCol(COL_UID);
		case PA_ID:
			return PPART_SKEWER_PA.getSqlCol(COL_PA_ID);
		case PA_NAME:
			return PPART_SKEWER_PA.getSqlCol(COL_PA_NAME);
		/* pars */
		case PARS_SEQ:
			return PPART_SKEWER_PARS.getSqlCol(COL_PARS_SEQ);
		case PARS_NAME:
			return PPART_SKEWER_PARS.getSqlCol(COL_PARS_NAME);
		case PARS_DESP:
			return PPART_SKEWER_PARS.getSqlCol(COL_PARS_DESP);
		/* ppart */
		case PARS_UID:
			return PPART_SKEWER_PPART.getSqlCol(COL_PARS_PART_PARS_UID);
		case ASSIGN_PART:
			return PPART_SKEWER_PPART.getSqlCol(COL_PARS_PART_ASSIGN_PART);
		case PART_UID:
			return PPART_SKEWER_PPART.getSqlCol(COL_PARS_PART_PART_UID);
		case PART_PIN:
			return PPART_SKEWER_PPART.getSqlCol(COL_PARS_PART_PART_PIN);
		/* ppart-p */
		case PART_NAME:
			return PPART_SKEWER_PPART_P.getSqlCol(COL_P_NAME);

		/* pc */
		case PC_ROOT_PART_UID:
		case PC_ROOT_PART_PIN:
//		case PC_IDs:
			return PartCfgDao.packPartCfgField(_p, PPART_SKEWER_PA.getAlias());
		case B_OF_PC$_PA_EXISTS:
			return PartCfgDao.packPartCfgFieldPartAcqExists(_p, _inSelectQueryValueMap, PPART_SKEWER_PA.getAlias(),
					COL_UID);
		case B_OF_PC$_PARENT_PART_EXISTS:
			return PartCfgDao.packPartCfgFieldParentPartExists(_p, _inSelectQueryValueMap,
					PPART_SKEWER_P.getAlias(), COL_UID);
		case B_OF_PC_ROOT_PART:
			String field = PartCfgDao.packPartCfgField(_p, PPART_SKEWER_PA.getAlias());
			String fstr = "(" + field + " = " + PPART_SKEWER_P.getSqlCol(COL_UID) + ")";
			return fstr;

		default:
			return null;
		}
	}

	QueryOperation<PpartSkewerQueryParam, PpartSkewer> searchPpartSkewer(
			QueryOperation<PpartSkewerQueryParam, PpartSkewer> _p,
			Map<PpartSkewerQueryParam, QueryValue[]> _existsQvMap) {
		Function<PpartSkewerQueryParam, String> queryParamMappingParser = p -> parsePpartSkewerQueryParamMapping(p,
				_existsQvMap);
		return searchSkewer(PPART_SKEWER_TCPs, ppartSkewerTrs, _p, queryParamMappingParser, this::parsePpartSkewer);
	}

}
