package ekp.data.service.mf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.event.Level;

import ekp.data.service.mf.query.WorkorderQueryParam;
import ekp.mf.Workorder;
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
	private final static String COL_WO_PART_MM_MANO = "part_mm_mano";
	private final static String COL_WO_START_WORK_TIME = "start_work_time";
	private final static String COL_WO_FINISH_WORK_TIME = "finish_work_time";
	private final static String COL_WO_OVER_TIME = "over_time";
	
	boolean saveWorkorder(Workorder _wo) {
		DbColumn<Workorder>[] cols = new DbColumn[] {
				DbColumn.of(COL_WO_WO_NO, ColType.STRING, Workorder::getWoNo, 45), //
				DbColumn.of(COL_WO_STATUS_IDX, ColType.INT, Workorder::getStatusIdx), //
				DbColumn.of(COL_WO_PART_UID, ColType.STRING, Workorder::getPartUid, 45), //
				DbColumn.of(COL_WO_PART_PIN, ColType.STRING, Workorder::getPartPin, 45), //
				DbColumn.of(COL_WO_PART_MM_MANO, ColType.STRING, Workorder::getPartMmMano, 45), //
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
			wo.setPartMmMano(_rs.getString(COL_WO_PART_MM_MANO));
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
		case PART_MM_MANO:
			return COL_WO_PART_MM_MANO;
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

}
