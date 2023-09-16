package ekp.data.service.mf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.event.Level;

import ekp.data.service.mf.query.WorkorderQueryParam;
import ekp.mf.Workorder;
import ekp.mf.WorkorderMaterial;
import ekp.mf.type.WorkorderStatus;
import ekp.pu.Purch;
import ekp.pu.type.PurchPerfStatus;
import legion.data.service.AbstractMySqlDao;
import legion.data.service.AbstractMySqlDao.ColType;
import legion.util.LogUtil;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public class WoDao extends AbstractMySqlDao{

	protected WoDao(String source) {
		super(source);
	}
	
	// -------------------------------------------------------------------------------
	// -----------------------------------Workorder-----------------------------------
	private final static String TB_WO = "mf_wo";
	private final static String COL_WO_WO_NO = "wo_no";
	private final static String COL_WO_STATUS_IDX = "status_idx";
	private final static String COL_WO_PART_UID = "part_uid";
	private final static String COL_WO_PART_PIN = "part_pin";
	
	private final static String COL_WO_PART_ACQ_UID = "part_acq_uid";
	private final static String COL_WO_PART_ACQ_ID= "part_acq_id";
	private final static String COL_WO_PART_ACQ_MM_MANO = "part_acq_mm_mano";
	
	private final static String COL_WO_PART_CFG_UID = "part_cfg_uid";
	private final static String COL_WO_PART_CFG_ID = "part_cfg_id";
	
	private final static String COL_WO_RQ_QTY = "rq_qty";
	private final static String COL_WO_START_WORK_TIME = "start_work_time";
	private final static String COL_WO_FINISH_WORK_TIME = "finish_work_time";
	private final static String COL_WO_OVER_TIME = "over_time";
	
	boolean saveWorkorder(Workorder _wo) {
		DbColumn<Workorder>[] cols = new DbColumn[] {
				DbColumn.of(COL_WO_WO_NO, ColType.STRING, Workorder::getWoNo, 45), //
				DbColumn.of(COL_WO_STATUS_IDX, ColType.INT, Workorder::getStatusIdx), //
				DbColumn.of(COL_WO_PART_UID, ColType.STRING, Workorder::getPartUid, 45), //
				DbColumn.of(COL_WO_PART_PIN, ColType.STRING, Workorder::getPartPin, 45), //
				
				DbColumn.of(COL_WO_PART_ACQ_UID, ColType.STRING, Workorder::getPartAcqUid, 45), //
				DbColumn.of(COL_WO_PART_ACQ_ID, ColType.STRING, Workorder::getPartAcqId, 45), //
				DbColumn.of(COL_WO_PART_ACQ_MM_MANO, ColType.STRING, Workorder::getPartAcqMmMano, 45), //
				DbColumn.of(COL_WO_PART_CFG_UID, ColType.STRING, Workorder::getPartCfgUid, 45), //
				DbColumn.of(COL_WO_PART_CFG_ID, ColType.STRING, Workorder::getPartCfgId, 45), //
				
				DbColumn.of(COL_WO_RQ_QTY, ColType.DOUBLE, Workorder::getRqQty), //
				DbColumn.of(COL_WO_START_WORK_TIME, ColType.LONG, Workorder::getStartWorkTime), //
				DbColumn.of(COL_WO_FINISH_WORK_TIME, ColType.LONG, Workorder::getFinishWorkTime), //
				DbColumn.of(COL_WO_OVER_TIME, ColType.LONG, Workorder::getOverTime), //
		};
		return saveObject(TB_WO, cols, _wo);
	}
	boolean deleteWorkorder(String _uid) {
		return deleteObject(TB_WO, _uid);
	}
	
	private Workorder parseWorkorder(ResultSet _rs) {
		try {
			WorkorderStatus status = WorkorderStatus.get(_rs.getInt(COL_WO_STATUS_IDX));
			Workorder wo = Workorder.getInstance(parseUid(_rs), status, parseObjectCreateTime(_rs),
					parseObjectUpdateTime(_rs));
			/* pack attributes */
			wo.setWoNo(_rs.getString(COL_WO_WO_NO));
			wo.setPartUid(_rs.getString(COL_WO_PART_UID));
			wo.setPartPin(_rs.getString(COL_WO_PART_PIN));
			wo.setPartAcqUid(_rs.getString(COL_WO_PART_ACQ_UID));
			wo.setPartAcqId(_rs.getString(COL_WO_PART_ACQ_ID));
			wo.setPartAcqMmMano(_rs.getString(COL_WO_PART_ACQ_MM_MANO));
			wo.setPartCfgUid(_rs.getString(COL_WO_PART_CFG_UID));
			wo.setPartCfgId(_rs.getString(COL_WO_PART_CFG_ID));
			wo.setRqQty(_rs.getDouble(COL_WO_RQ_QTY));
			wo.setStartWorkTime(_rs.getLong(COL_WO_START_WORK_TIME));
			wo.setFinishWorkTime(_rs.getLong(COL_WO_FINISH_WORK_TIME));
			wo.setOverTime(_rs.getLong(COL_WO_OVER_TIME));
			return wo;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}
	
	Workorder loadWorkorder(String _uid) {
		return loadObject(TB_WO, _uid, this::parseWorkorder);
	}
	
	private static String  parseWorkorderQueryparamMapping(WorkorderQueryParam _param, 
			Map<WorkorderQueryParam, QueryValue[]> _existsDetailMap) {
		switch (_param) {
		/* Workorder:this */
		case WO_NO:
			return COL_WO_WO_NO;
		case PART_UID:
			return COL_WO_PART_UID;
		case PART_PIN:
			return COL_WO_PART_PIN;
		case PART_ACQ_UID:
			return COL_WO_PART_ACQ_UID;
		case PART_ACQ_ID:
			return COL_WO_PART_ACQ_ID;
		case PART_ACQ_MM_MANO:
			return COL_WO_PART_ACQ_MM_MANO;
		case PART_CFG_UID:
			return COL_WO_PART_CFG_UID;
		case PART_CFG_ID:
			return COL_WO_PART_CFG_ID;
		case RQ_QTY:
			return COL_WO_RQ_QTY;
		case START_WORK_TIME:
			return COL_WO_START_WORK_TIME;
		case FINISH_WORK_TIME:
			return COL_WO_FINISH_WORK_TIME;
		case OVER_TIME:
			return COL_WO_OVER_TIME;
		default:
			log.warn("_param error. {}", _param);
			return null;
		}
	}
	
	QueryOperation<WorkorderQueryParam, Workorder> searchWorkorder(
			QueryOperation<WorkorderQueryParam, Workorder> _param,
			Map<WorkorderQueryParam, QueryValue[]> _existsDetailMap) {
		Function<WorkorderQueryParam, String> queryParamMappingParser = p -> parseWorkorderQueryparamMapping(p,
				_existsDetailMap);
		return searchObject(TB_WO, _param, queryParamMappingParser, this::parseWorkorder);
	}
	
	// -------------------------------------------------------------------------------
	// -------------------------------WorkorderMaterial-------------------------------
	private final static String TB_WOM = "mf_wom";
	private final static String COL_WOM_WO_UID = "wo_uid";
	private final static String COL_WOM_WO_NO = "wo_no";
	private final static String COL_WOM_MM_UID = "mm_uid";
	private final static String COL_WOM_MM_MANO = "mm_mano";
	private final static String COL_WOM_MM_NAME = "mm_name";
	private final static String COL_WOM_QTY0 = "qty0";
	private final static String COL_WOM_QTY1 = "qty1";
	
	boolean saveWorkorderMaterial(WorkorderMaterial _wom) {
		DbColumn<WorkorderMaterial>[] cols = new DbColumn[] {
				DbColumn.of(COL_WOM_WO_UID, ColType.STRING, WorkorderMaterial::getWoUid, 45), //
				DbColumn.of(COL_WOM_WO_NO, ColType.STRING, WorkorderMaterial::getWoNo, 45), //
				DbColumn.of(COL_WOM_MM_UID, ColType.STRING, WorkorderMaterial::getMmUid, 45), //
				DbColumn.of(COL_WOM_MM_MANO, ColType.STRING, WorkorderMaterial::getMmMano, 45), //
				DbColumn.of(COL_WOM_MM_NAME, ColType.STRING, WorkorderMaterial::getMmName, 45), //
				DbColumn.of(COL_WOM_QTY0, ColType.DOUBLE, WorkorderMaterial::getQty0), //
				DbColumn.of(COL_WOM_QTY1, ColType.DOUBLE, WorkorderMaterial::getQty1), //
		};
		return saveObject(TB_WOM, cols, _wom);
	}
	boolean deleteWorkorderMaterial(String _uid) {
		return deleteObject(TB_WOM, _uid);
	}
	private WorkorderMaterial parseWorkorderMaterial(ResultSet _rs) {
		try {
			String woUid = _rs.getString(COL_WOM_WO_UID);
			WorkorderMaterial wom = WorkorderMaterial.getInstance(parseUid(_rs), woUid, parseObjectCreateTime(_rs), parseObjectUpdateTime(_rs));
			/* pack attributes */
			wom.setWoNo(_rs.getString(COL_WOM_WO_NO));
			wom.setMmUid(_rs.getString(COL_WOM_MM_UID));
			wom.setMmMano(_rs.getString(COL_WOM_MM_MANO));
			wom.setMmName(_rs.getString(COL_WOM_MM_NAME));
			wom.setQty0(_rs.getDouble(COL_WOM_QTY0));
			wom.setQty1(_rs.getDouble(COL_WOM_QTY1));
			return wom;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}
	
	WorkorderMaterial loadWorkorderMaterial(String _uid) {
		return loadObject(TB_WOM, _uid, this::parseWorkorderMaterial);
	}
	List<WorkorderMaterial> loadWorkorderMaterialList(String _woUid){
		return loadObjectList(TB_WOM, COL_WOM_WO_UID, _woUid, this::parseWorkorderMaterial);
	}
}
