package ekp.data.service.mbom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.event.Level;

import ekp.mbom.Part;
import ekp.mbom.PartAcqRoutingStep;
import ekp.mbom.ParsPart;
import ekp.mbom.ParsProc;
import ekp.mbom.PartAcquisition;
import ekp.mbom.type.PartAcquisitionType;
import legion.data.service.AbstractMySqlDao;
import legion.util.LogUtil;

public class PartDao extends AbstractMySqlDao {

	PartDao(String source) {
		super(source);
	}

	// -------------------------------------------------------------------------------
	// -------------------------------------Part--------------------------------------
	private final static String TB_MBOM_PART = "mbom_part";
	private final static String COL_P_PIN = "pin";
	private final static String COL_P_NAME = "name";

	boolean savePart(Part _p) {
		DbColumn<Part>[] cols = new DbColumn[] { //
				DbColumn.of(COL_P_PIN, ColType.STRING, Part::getPin), //
				DbColumn.of(COL_P_NAME, ColType.STRING, Part::getName), //
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

	// -------------------------------------------------------------------------------
	// --------------------------------PartAcquisition--------------------------------
	private final static String TB_MBOM_PART_ACQUISITION = "mbom_part_acq";
	private final static String COL_PA_PART_UID = "part_uid";
	private final static String COL_PA_PART_PIN = "part_pin";
	private final static String COL_PA_ID = "id";
	private final static String COL_PA_NAME = "name";
	private final static String COL_PA_TYPE_IDX = "type_idx";

	boolean savePartAcquisition(PartAcquisition _pa) {
		DbColumn<PartAcquisition>[] cols = new DbColumn[] {
				DbColumn.of(COL_PA_PART_UID, ColType.STRING, PartAcquisition::getPartUid), //
				DbColumn.of(COL_PA_PART_PIN, ColType.STRING, PartAcquisition::getPartPin), //
				DbColumn.of(COL_PA_ID, ColType.STRING, PartAcquisition::getId), //
				DbColumn.of(COL_PA_NAME, ColType.STRING, PartAcquisition::getName), //
				DbColumn.of(COL_PA_TYPE_IDX, ColType.INT, PartAcquisition::getTypeIdx), //
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
			pa = PartAcquisition.getInstance(parseUid(_rs), partUid, partPin, parseObjectCreateTime(_rs),
					parseObjectUpdateTime(_rs));
			/* pack attributes */
			pa.setId(_rs.getString(COL_PA_ID));
			pa.setName(_rs.getString(COL_PA_NAME));
			pa.setType(PartAcquisitionType.get(_rs.getInt(COL_PA_TYPE_IDX)));
			return pa;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}

	PartAcquisition loadPartAcquisition(String _uid) {
		return loadObject(TB_MBOM_PART_ACQUISITION, _uid, this::parsePartAcquisition);
	}

	PartAcquisition loadPartAcquisitionById(String _id) {
		return loadObject(TB_MBOM_PART_ACQUISITION, COL_PA_ID, _id, this::parsePartAcquisition);
	}

	List<PartAcquisition> loadPartAcquisitionList(String _partUid) {
		return loadObjectList(TB_MBOM_PART_ACQUISITION, COL_PA_PART_UID, _partUid, this::parsePartAcquisition);
	}

	// -------------------------------------------------------------------------------
	// ------------------------------PartAcqRoutingStep-------------------------------
	private final static String TB_MBOM_PART_ACQ_ROUTING_STEP = "mbom_part_acq_r_s";
	private final static String COL_PARS_PART_ACQ_UID = "part_acq_uid";
	private final static String COL_PARS_ID = "id";
	private final static String COL_PARS_NAME = "name";
	private final static String COL_PARS_DESP = "desp";

	boolean savePartAcqRoutingStep(PartAcqRoutingStep _pars) {
		DbColumn<PartAcqRoutingStep>[] cols = new DbColumn[] {
				DbColumn.of(COL_PARS_PART_ACQ_UID, ColType.STRING, PartAcqRoutingStep::getPartAcqUid), //
				DbColumn.of(COL_PARS_ID, ColType.STRING, PartAcqRoutingStep::getId), //
				DbColumn.of(COL_PARS_NAME, ColType.STRING, PartAcqRoutingStep::getName), //
				DbColumn.of(COL_PARS_DESP, ColType.STRING, PartAcqRoutingStep::getDesp), //
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
			pars.setId(_rs.getString(COL_PARS_ID));
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

	PartAcqRoutingStep loadPartAcqRoutingStep(String _partAcqUid, String _id) {
		Map<String, String> keyValueMap = new HashMap<>();
		keyValueMap.put(COL_PARS_PART_ACQ_UID, _partAcqUid);
		keyValueMap.put(COL_PARS_ID, _id);
		return loadObject(TB_MBOM_PART_ACQ_ROUTING_STEP, keyValueMap, this::parsePartAcqRoutingStep);
	}

	List<PartAcqRoutingStep> loadPartAcqRoutingStepList(String _partAcqUid) {
		return loadObjectList(TB_MBOM_PART_ACQ_ROUTING_STEP, COL_PARS_PART_ACQ_UID, _partAcqUid,
				this::parsePartAcqRoutingStep);
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
		DbColumn<ParsProc>[] cols = new DbColumn[] {
				DbColumn.of(COL_PARS_PROC_PARS_UID, ColType.STRING, ParsProc::getParsUid), //
				DbColumn.of(COL_PARS_PROC_SEQ, ColType.STRING, ParsProc::getSeq), //
				DbColumn.of(COL_PARS_PROC_NAME, ColType.STRING, ParsProc::getName), //
				DbColumn.of(COL_PARS_PROC_DESP, ColType.STRING, ParsProc::getDesp), //
				DbColumn.of(COL_PARS_PROC_ASSIGN_PROC, ColType.BOOLEAN, ParsProc::isAssignProc), //
				DbColumn.of(COL_PARS_PROC_PROC_UID, ColType.STRING, ParsProc::getProcUid), //
				DbColumn.of(COL_PARS_PROC_PROC_ID, ColType.STRING, ParsProc::getProcId), //
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
				DbColumn.of(COL_PARS_PART_PARS_UID, ColType.STRING, ParsPart::getParsUid), //
				DbColumn.of(COL_PARS_PART_ASSIGN_PART, ColType.BOOLEAN, ParsPart::isAssignPart), //
				DbColumn.of(COL_PARS_PART_PART_UID, ColType.STRING, ParsPart::getPartUid), //
				DbColumn.of(COL_PARS_PART_PART_PIN, ColType.STRING, ParsPart::getPartPin), //
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

	List<ParsPart> loadParsPartList(String _parsUid) {
		return loadObjectList(TB_MBOM_PARS_PART, COL_PARS_PART_PARS_UID, _parsUid, this::parseParsPart);
	}

	List<ParsPart> loadParsPartListByPart(String _partUid) {
		return loadObjectList(TB_MBOM_PARS_PART, COL_PARS_PART_PART_UID, _partUid, this::parseParsPart);
	}

}
