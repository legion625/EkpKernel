package ekp.data.service.invt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.event.Level;

import ekp.data.service.invt.query.InvtOrderItemQueryParam;
import ekp.data.service.invt.query.MbsbStmtQueryParam;
import ekp.invt.MaterialBinStock;
import ekp.invt.MaterialBinStockBatch;
import ekp.invt.MaterialInst;
import ekp.invt.MaterialMaster;
import ekp.invt.MbsbStmt;
import ekp.invt.type.MaterialInstAcqChannel;
import ekp.invt.type.MbsbFlowType;
import ekp.invt.type.PostingStatus;
import ekp.mbom.Part;
import ekp.mbom.type.PartUnit;
import legion.data.service.AbstractMySqlDao;
import legion.data.service.AbstractMySqlDao.ColType;
import legion.data.service.AbstractMySqlDao.DbColumn;
import legion.util.LogUtil;
import legion.util.query.QueryOperation.QueryValue;

public class MaterialDao extends AbstractMySqlDao {

	protected MaterialDao(String source) {
		super(source);
	}

	// -------------------------------------------------------------------------------
	// --------------------------------MaterialMaster---------------------------------
	private final static String TB_MATERIAL_MASTER = "invt_mat_mstr";
	private final static String COL_MM_MANO = "mano";
	private final static String COL_MM_NAME = "name";
	private final static String COL_MM_SPECIFICATION = "specification";
	private final static String COL_MM_STD_UNIT_ID = "std_unit_id";
	private final static String COL_MM_SUM_STOCK_QTY = "sum_stock_qty";
	private final static String COL_MM_SUM_STOCK_VALUE = "sum_stock_value";
	
	boolean saveMaterialMaster(MaterialMaster _mm) {
		DbColumn<MaterialMaster>[] cols = new DbColumn[] {
				DbColumn.of(COL_MM_MANO, ColType.STRING, MaterialMaster::getMano, 45), //
				DbColumn.of(COL_MM_NAME, ColType.STRING, MaterialMaster::getName, 45), //
				DbColumn.of(COL_MM_SPECIFICATION, ColType.STRING, MaterialMaster::getSpecification, 200), //
				DbColumn.of(COL_MM_STD_UNIT_ID, ColType.STRING, MaterialMaster::getStdUnitId, 10), //
				DbColumn.of(COL_MM_SUM_STOCK_QTY, ColType.DOUBLE, MaterialMaster::getSumStockQty), //
				DbColumn.of(COL_MM_SUM_STOCK_VALUE, ColType.DOUBLE, MaterialMaster::getSumStockValue), //
		};
		return saveObject(TB_MATERIAL_MASTER, cols, _mm);
	}

	boolean deleteMaterialMaster(String _uid) {
		return deleteObject(TB_MATERIAL_MASTER, _uid);
	}

	private MaterialMaster parseMaterialMaster(ResultSet _rs) {
		MaterialMaster mm =   null;
		try {
			mm = MaterialMaster.getInstance(parseUid(_rs), parseObjectCreateTime(_rs), parseObjectUpdateTime(_rs));
			/* pack attributes */
			mm.setMano(_rs.getString(COL_MM_MANO));
			mm.setName(_rs.getString(COL_MM_NAME));
			mm.setSpecification(_rs.getString(COL_MM_SPECIFICATION));
			mm.setStdUnit(PartUnit.get(_rs.getString(COL_MM_STD_UNIT_ID)));
			mm.setSumStockQty(_rs.getDouble(COL_MM_SUM_STOCK_QTY));
			mm.setSumStockValue(_rs.getDouble(COL_MM_SUM_STOCK_VALUE));
			return mm;
		}catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
		
	}
	
	MaterialMaster loadMaterialMaster(String _uid) {
		return loadObject(TB_MATERIAL_MASTER, _uid, this::parseMaterialMaster);
	}

	MaterialMaster loadMaterialMasterByMano(String _mano) {
		return loadObject(TB_MATERIAL_MASTER, COL_MM_MANO, _mano, this::parseMaterialMaster);
	}

	private static String packMaterialMasterQueryField(InvtOrderItemQueryParam _param, String _tbMbs,
			String _colMbsMmUid) {
		String targetMasterField;
		switch (_param) {
		/* MaterialBinStock:master */
		// -> MaterialMaster
		case MBS_MM_NAME:
			targetMasterField = COL_MM_NAME;
			break;
		default:
			log.debug("not supported. {}", _param);
			return null;
		}
		return packMasterQueryField(targetMasterField, TB_MATERIAL_MASTER, COL_UID, _tbMbs, _colMbsMmUid);
	}

	// -------------------------------------------------------------------------------
	// ---------------------------------MaterialInst----------------------------------
	private final static String TB_MATERIAL_INST = "invt_mat_inst";
	private final static String COL_MI_MM_UID = "mm_uid";
	private final static String COL_MI_MISN = "misn";
	private final static String COL_MI_MIAC_IDX = "miac_idx";
	private final static String COL_MI_QTY = "qty";
	private final static String COL_MI_VALUE = "value";
	private final static String COL_MI_EFF_DATE = "eff_date";
	private final static String COL_MI_EXP_DATE = "exp_date";
	
	boolean saveMaterialInst(MaterialInst _mi) {
		DbColumn<MaterialInst>[] cols = new DbColumn[] { //
				DbColumn.of(COL_MI_MM_UID, ColType.STRING, MaterialInst::getMmUid, 45), //
				DbColumn.of(COL_MI_MISN, ColType.STRING, MaterialInst::getMisn, 45), //
				DbColumn.of(COL_MI_MIAC_IDX, ColType.INT, MaterialInst::getMiacIdx), //
				DbColumn.of(COL_MI_QTY, ColType.DOUBLE, MaterialInst::getQty), //
				DbColumn.of(COL_MI_VALUE, ColType.DOUBLE, MaterialInst::getValue), //
				DbColumn.of(COL_MI_EFF_DATE, ColType.LONG, MaterialInst::getEffDate), //
				DbColumn.of(COL_MI_EXP_DATE, ColType.LONG, MaterialInst::getExpDate), //
		};
		return saveObject(TB_MATERIAL_INST, cols, _mi);
	}
	
	boolean deleteMaterialInst(String _uid) {
		return deleteObject(TB_MATERIAL_INST, _uid);
	}
	private MaterialInst parseMaterialInst(ResultSet _rs) {
		try {
			String mmUid = _rs.getString(COL_MI_MM_UID);
			MaterialInst mi = MaterialInst.getInstance(parseUid(_rs), mmUid, parseObjectCreateTime(_rs),
					parseObjectUpdateTime(_rs));
			/* pack attributes */
			mi.setMisn(_rs.getString(COL_MI_MISN));
			mi.setMiac(MaterialInstAcqChannel.get(_rs.getInt(COL_MI_MIAC_IDX)));
			mi.setQty(_rs.getDouble(COL_MI_QTY));
			mi.setValue(_rs.getDouble(COL_MI_VALUE));
			mi.setEffDate(_rs.getLong(COL_MI_EFF_DATE));
			mi.setExpDate(_rs.getLong(COL_MI_EXP_DATE));
			return mi;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}
	
	MaterialInst loadMaterialInst(String _uid) {
		return loadObject(TB_MATERIAL_INST, _uid, this::parseMaterialInst);
	}
	MaterialInst loadMaterialInstByMisn(String _misn) {
		return loadObject(TB_MATERIAL_INST, COL_MI_MISN, _misn, this::parseMaterialInst);
	}
	List<MaterialInst> loadMaterialInstList(String _mmUid){
		return loadObjectList(TB_MATERIAL_INST, COL_MI_MM_UID, _mmUid, this::parseMaterialInst);
	}
	
	// -------------------------------------------------------------------------------
	// -------------------------------MaterialBinStock--------------------------------
	private final static String TB_MATERIAL_BIN_STOCK = "invt_mat_bin_stock";
	private final static String COL_MBS_MM_UID = "mm_uid";
	private final static String COL_MBS_MANO = "mano";
	private final static String COL_MBS_WRHS_BIN_UID = "wrhs_bin_uid";
	private final static String COL_MBS_SUM_STOCK_QTY = "sum_stock_qty";
	private final static String COL_MBS_SUM_STOCK_VALUE = "sum_stock_value";
	
	boolean saveMaterialBinStock(MaterialBinStock _mbs) {
		DbColumn<MaterialBinStock>[] cols = new DbColumn[] { // 
				DbColumn.of(COL_MBS_MM_UID, ColType.STRING, MaterialBinStock::getMmUid, 45), //
				DbColumn.of(COL_MBS_MANO, ColType.STRING, MaterialBinStock::getMano, 45), //
				DbColumn.of(COL_MBS_WRHS_BIN_UID, ColType.STRING, MaterialBinStock::getWrhsBinUid, 45), //
				DbColumn.of(COL_MBS_SUM_STOCK_QTY, ColType.DOUBLE, MaterialBinStock::getSumStockQty), //
				DbColumn.of(COL_MBS_SUM_STOCK_VALUE, ColType.DOUBLE, MaterialBinStock::getSumStockValue), //
		};
		return saveObject(TB_MATERIAL_BIN_STOCK, cols, _mbs);
	}
	boolean deleteMaterialBinStock(String _uid) {
		return deleteObject(TB_MATERIAL_BIN_STOCK, _uid);
	}
	
	private MaterialBinStock parseMaterialBinStock(ResultSet _rs) {
		try {
			String mmUid = _rs.getString(COL_MBS_MM_UID);
			MaterialBinStock mbs = MaterialBinStock.getInstance(parseUid(_rs), mmUid, parseObjectCreateTime(_rs),
					parseObjectUpdateTime(_rs));
			/* pack attributes */
			mbs.setMano(_rs.getString(COL_MBS_MANO));
			mbs.setWrhsBinUid(_rs.getString(COL_MBS_WRHS_BIN_UID));
			mbs.setSumStockQty(_rs.getDouble(COL_MBS_SUM_STOCK_QTY));
			mbs.setSumStockValue(_rs.getDouble(COL_MBS_SUM_STOCK_VALUE));
			return mbs;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}
	
	MaterialBinStock loadMaterialBinStock(String _uid) {
		return loadObject(TB_MATERIAL_BIN_STOCK, _uid, this::parseMaterialBinStock);
	}
	List<MaterialBinStock> loadMaterialBinStockList(String _mmUid){
		return loadObjectList(TB_MATERIAL_BIN_STOCK,COL_MBS_MM_UID, _mmUid, this::parseMaterialBinStock);
	}
	
	static String packMaterialBinStockQueryField(InvtOrderItemQueryParam _param, String _tbIoi, String _colIoiMbsUid) {
		String targetMasterField;
		switch (_param) {
		/* MaterialBinStock:master */
		case MBS_MM_UID:
			targetMasterField = COL_MBS_MM_UID;
			break;
		case MBS_MANO:
			targetMasterField = COL_MBS_MANO;
			break;
		case MBS_WB_UID:
			targetMasterField = COL_MBS_WRHS_BIN_UID;
			break;
			// -> MaterialMaster
		case MBS_MM_NAME:
			targetMasterField = packMaterialMasterQueryField(_param, TB_MATERIAL_BIN_STOCK, COL_MBS_MM_UID);
			break;
		default:
			log.debug("not supported. {}", _param);
			return null;
		}
		return packMasterQueryField(targetMasterField, TB_MATERIAL_BIN_STOCK, COL_UID, _tbIoi, _colIoiMbsUid);
	}
	
	// -------------------------------------------------------------------------------
	// -----------------------------MaterialBinStockBatch-----------------------------
	private final static String TB_MAT_BIN_STOCK_BATCH = "invt_mat_bin_stock_batch";
	private final static String COL_MBSB_MBS_UID = "mbs_uid";
	private final static String COL_MBSB_MI_UID = "mi_uid";
	private final static String COL_MBSB_STOCK_QTY = "stock_qty";
	private final static String COL_MBSB_STOCK_VALUE = "stock_value";

	boolean saveMaterialBinStockBatch(MaterialBinStockBatch _mbsb) {
		DbColumn<MaterialBinStockBatch>[] cols = new DbColumn[] { //
				DbColumn.of(COL_MBSB_MBS_UID, ColType.STRING, MaterialBinStockBatch::getMbsUid, 45), //
				DbColumn.of(COL_MBSB_MI_UID, ColType.STRING, MaterialBinStockBatch::getMiUid, 45), //
				DbColumn.of(COL_MBSB_STOCK_QTY, ColType.DOUBLE, MaterialBinStockBatch::getStockQty), //
				DbColumn.of(COL_MBSB_STOCK_VALUE, ColType.DOUBLE, MaterialBinStockBatch::getStockValue), //
		};
		return saveObject(TB_MAT_BIN_STOCK_BATCH, cols, _mbsb);
	}

	boolean deleteMaterialBinStockBatch(String _uid) {
		return deleteObject(TB_MAT_BIN_STOCK_BATCH, _uid);
	}

	private MaterialBinStockBatch parseMaterialBinStockBatch(ResultSet _rs) {
		try {
			
			String mbsUid = _rs.getString(COL_MBSB_MBS_UID);
			String miUid = _rs.getString(COL_MBSB_MI_UID);
			MaterialBinStockBatch mbsb = MaterialBinStockBatch.getInstance(parseUid(_rs), mbsUid, miUid, parseObjectCreateTime(_rs), parseObjectUpdateTime(_rs));
			/* pack attributes */
			mbsb.setStockQty(_rs.getDouble(COL_MBSB_STOCK_QTY));
			mbsb.setStockValue(_rs.getDouble(COL_MBSB_STOCK_VALUE));
			return mbsb;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}
	
	MaterialBinStockBatch loadMaterialBinStockBatch(String _uid) {
		return loadObject(TB_MAT_BIN_STOCK_BATCH, _uid, this::parseMaterialBinStockBatch);
	}
	
	MaterialBinStockBatch loadMaterialBinStockBatch(String _mbsUid, String _miUid) {
		Map<String, String> colValueMap = new HashMap<>();
		colValueMap.put(COL_MBSB_MBS_UID, _mbsUid);
		colValueMap.put(COL_MBSB_MI_UID, _miUid);
		return loadObject(TB_MAT_BIN_STOCK_BATCH, colValueMap, this::parseMaterialBinStockBatch);
	}

	List<MaterialBinStockBatch> loadMaterialBinStockBatchList(String _mbsUid){
		return loadObjectList(TB_MAT_BIN_STOCK_BATCH,COL_MBSB_MBS_UID,  _mbsUid, this::parseMaterialBinStockBatch);
	}

	List<MaterialBinStockBatch> loadMaterialBinStockBatchListByMi(String _miUid){
		return loadObjectList(TB_MAT_BIN_STOCK_BATCH,COL_MBSB_MI_UID,  _miUid, this::parseMaterialBinStockBatch);
	}
	
	// -------------------------------------------------------------------------------
	// -----------------------------------MbsbStmt------------------------------------
	private final static String TB_MBSB_STMT = "invt_mbsb_stmt";
	private final static String COL_MBSBS_MBSB_UID = "mbsb_uid";
	private final static String COL_MBSBS_IOI_UID = "ioi_uid";
	private final static String COL_MBSBS_MBSB_FLOW_TYPE_IDX = "mbsb_flow_type_idx";
	private final static String COL_MBSBS_STMT_QTY = "stmt_qty";
	private final static String COL_MBSBS_STMT_VALUE = "stmt_value";
	private final static String COL_MBSBS_POSTING_STATUS_IDX = "posting_status_idx";
	private final static String COL_MBSBS_POSTING_TIME = "posting_time";
	
	boolean saveMbsbStmt(MbsbStmt _mbsbs) {
		DbColumn<MbsbStmt>[] cols = new DbColumn[] { //
				DbColumn.of(COL_MBSBS_MBSB_UID, ColType.STRING, MbsbStmt::getMbsbUid, 45), //
				DbColumn.of(COL_MBSBS_IOI_UID, ColType.STRING, MbsbStmt::getIoiUid, 45), //
				DbColumn.of(COL_MBSBS_MBSB_FLOW_TYPE_IDX, ColType.INT, MbsbStmt::getMbsbFlowTypeIdx), //
				DbColumn.of(COL_MBSBS_STMT_QTY, ColType.DOUBLE, MbsbStmt::getStmtQty), //
				DbColumn.of(COL_MBSBS_STMT_VALUE, ColType.DOUBLE, MbsbStmt::getStmtValue), //
				DbColumn.of(COL_MBSBS_POSTING_STATUS_IDX, ColType.INT, MbsbStmt::getPostingStatusIdx), //
				DbColumn.of(COL_MBSBS_POSTING_TIME, ColType.LONG, MbsbStmt::getPostingTime), //
		};
		return saveObject(TB_MBSB_STMT, cols, _mbsbs);
	}

	boolean deleteMbsbStmt(String _uid) {
		return deleteObject(TB_MBSB_STMT, _uid);
	}
	
	private MbsbStmt parseMbsbStmt(ResultSet _rs) {
		try {
			String mbsbUid = _rs.getString(COL_MBSBS_MBSB_UID);
			String ioiUid = _rs.getString(COL_MBSBS_IOI_UID);
			PostingStatus postingStatus = PostingStatus.get(_rs.getInt(COL_MBSBS_POSTING_STATUS_IDX));
			MbsbStmt mbsbs = MbsbStmt.getInstance(parseUid(_rs), mbsbUid, ioiUid, postingStatus,parseObjectCreateTime(_rs), parseObjectUpdateTime(_rs));
			/* pack attributes */
			mbsbs.setMbsbFlowType(MbsbFlowType.get(_rs.getInt(COL_MBSBS_MBSB_FLOW_TYPE_IDX)));
			mbsbs.setStmtQty(_rs.getDouble(COL_MBSBS_STMT_QTY));
			mbsbs.setStmtValue(_rs.getDouble(COL_MBSBS_STMT_VALUE));
			mbsbs.setPostingTime(_rs.getLong(COL_MBSBS_POSTING_TIME));
			return mbsbs;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}
	
	MbsbStmt loadMbsbStmt(String _uid) {
		return loadObject(TB_MBSB_STMT, _uid, this::parseMbsbStmt);
	}
	List<MbsbStmt> loadMbsbStmtList(String _mbsbUid){
		return loadObjectList(TB_MBSB_STMT, COL_MBSBS_MBSB_UID, _mbsbUid, this::parseMbsbStmt);
	}
	List<MbsbStmt> loadMbsbStmtListByIoi(String _ioiUid){
		return loadObjectList(TB_MBSB_STMT, COL_MBSBS_IOI_UID, _ioiUid, this::parseMbsbStmt);
	}

	private static String parseMbsbStmtQueryParamMapping(MbsbStmtQueryParam _param) {
		switch (_param) {
		/* MbsbStmt:this */
		case MBSB_FLOW_TYPE_IDX:
			return COL_MBSBS_MBSB_FLOW_TYPE_IDX;
		case POSTING_STATUS_IDX:
			return COL_MBSBS_POSTING_STATUS_IDX;
		case POSTING_TIME:
			return COL_MBSBS_POSTING_TIME;
		default:
			log.warn("_param error. {}", _param);
			return null;
		}
	}
	
	static String packMbsbStmtField(String _tbIoi, String _colIoiUid,
			Map<InvtOrderItemQueryParam, QueryValue[]> _existsDetailMap, InvtOrderItemQueryParam _param) {
		return packExistsField(_tbIoi, _colIoiUid, TB_MBSB_STMT, COL_MBSBS_IOI_UID,
				MaterialDao::parseMbsbStmtQueryParamMapping, _existsDetailMap, _param);
	}
	
}
