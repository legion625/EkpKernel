package ekp.data.service.invt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.event.Level;

import ekp.data.service.invt.query.InvtOrderItemQueryParam;
import ekp.data.service.invt.query.InvtOrderQueryParam;
import ekp.data.service.invt.query.MaterialMasterQueryParam;
import ekp.data.service.invt.query.MbsbStmtQueryParam;
import ekp.invt.MaterialBinStock;
import ekp.invt.MaterialBinStockBatch;
import ekp.invt.MaterialInst;
import ekp.invt.MaterialInstSrcConj;
import ekp.invt.MaterialMaster;
import ekp.invt.MbsbStmt;
import ekp.invt.type.MaterialInstAcqChannel;
import ekp.invt.type.MaterialInstSrcStatus;
import ekp.invt.type.MbsbFlowType;
import ekp.invt.type.PostingStatus;
import ekp.mbom.Part;
import ekp.mbom.type.PartUnit;
import legion.data.service.AbstractMySqlDao;
import legion.data.service.AbstractMySqlDao.ColType;
import legion.data.service.AbstractMySqlDao.DbColumn;
import legion.util.DataFO;
import legion.util.LogUtil;
import legion.util.query.QueryOperation;
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
	
	private static String parseMaterialMasterQueryParamMapping(MaterialMasterQueryParam _param) {
		switch (_param) {
		/* MaterialMaster:this */
		case MANO:
			return COL_MM_MANO;
		case NAME:
			return COL_MM_NAME;
		case SPECIFICATION:
			return COL_MM_SPECIFICATION;
		case STD_UNIT_ID:
			return COL_MM_STD_UNIT_ID;
		default:
			log.warn("_param error. {}", _param);
			return null;
		}
	}

	QueryOperation<MaterialMasterQueryParam, MaterialMaster> searchMaterialMaster(
			QueryOperation<MaterialMasterQueryParam, MaterialMaster> _param){
		return searchObject(TB_MATERIAL_MASTER, _param,MaterialDao::parseMaterialMasterQueryParamMapping, this::parseMaterialMaster);
	}
	
	
	static String packMaterialMasterQueryField(InvtOrderItemQueryParam _param, String _tbIoi,
			String _colIoiMmUid) {
		String targetMasterField;
		switch (_param) {
		case MM_MANO:
			targetMasterField = COL_MM_MANO;
			break;
		case MM_NAME:
			targetMasterField = COL_MM_NAME;
			break;
		default:
			log.warn("not supported. {}", _param);
			return null;
		}
		return packMasterQueryField(targetMasterField, TB_MATERIAL_MASTER, COL_UID, _tbIoi, _colIoiMmUid);
	}
	
	// -------------------------------------------------------------------------------
	// ---------------------------------MaterialInst----------------------------------
	private final static String TB_MATERIAL_INST = "invt_mat_inst";
	private final static String COL_MI_MM_UID = "mm_uid";
	private final static String COL_MI_MISN = "misn";
	private final static String COL_MI_MIAC_IDX = "miac_idx";
	private final static String COL_MI_MIAC_SRC_NO = "miac_src_no";
	private final static String COL_MI_QTY = "qty";
	private final static String COL_MI_VALUE = "value";
	private final static String COL_MI_EFF_DATE = "eff_date";
	private final static String COL_MI_EXP_DATE = "exp_date";
	private final static String COL_MI_SRC_STATUS_IDX = "src_status_idx";
	
	
	boolean saveMaterialInst(MaterialInst _mi) {
		DbColumn<MaterialInst>[] cols = new DbColumn[] { //
				DbColumn.of(COL_MI_MM_UID, ColType.STRING, MaterialInst::getMmUid, 45), //
				DbColumn.of(COL_MI_MISN, ColType.STRING, MaterialInst::getMisn, 45), //
				DbColumn.of(COL_MI_MIAC_IDX, ColType.INT, MaterialInst::getMiacIdx), //
				DbColumn.of(COL_MI_MIAC_SRC_NO, ColType.STRING, MaterialInst::getMiacSrcNo, 45), //
				DbColumn.of(COL_MI_QTY, ColType.DOUBLE, MaterialInst::getQty), //
				DbColumn.of(COL_MI_VALUE, ColType.DOUBLE, MaterialInst::getValue), //
				DbColumn.of(COL_MI_EFF_DATE, ColType.LONG, MaterialInst::getEffDate), //
				DbColumn.of(COL_MI_EXP_DATE, ColType.LONG, MaterialInst::getExpDate), //
				DbColumn.of(COL_MI_SRC_STATUS_IDX, ColType.INT, MaterialInst::getSrcStatusIdx), //
		};
		return saveObject(TB_MATERIAL_INST, cols, _mi);
	}
	
	boolean deleteMaterialInst(String _uid) {
		return deleteObject(TB_MATERIAL_INST, _uid);
	}
	private MaterialInst parseMaterialInst(ResultSet _rs) {
		try {
			String mmUid = _rs.getString(COL_MI_MM_UID);
			MaterialInstSrcStatus srcStatus =MaterialInstSrcStatus.get(_rs.getInt(COL_MI_SRC_STATUS_IDX));
			MaterialInst mi = MaterialInst.getInstance(parseUid(_rs), mmUid,srcStatus, parseObjectCreateTime(_rs),
					parseObjectUpdateTime(_rs));
			/* pack attributes */
			mi.setMisn(_rs.getString(COL_MI_MISN));
			mi.setMiac(MaterialInstAcqChannel.get(_rs.getInt(COL_MI_MIAC_IDX)));
			mi.setMiacSrcNo(_rs.getString(COL_MI_MIAC_SRC_NO));
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
//	MaterialInst loadMaterialInstByMiacSrcNo(String _miacSrcNo) {
//		return loadObject(TB_MATERIAL_INST, COL_MI_MIAC_SRC_NO, _miacSrcNo, this::parseMaterialInst);
//	}
	List<MaterialInst> loadMaterialInstList(String _mmUid,MaterialInstAcqChannel _miac, String _miacSrcNo){
		Map<String, String> colValueMap = new HashMap<>();
		if(!DataFO.isEmptyString(_mmUid))
			colValueMap.put(COL_MI_MM_UID, _mmUid);
		if(_miac!=null && MaterialInstAcqChannel.UNDEFINED!=_miac)
			colValueMap.put(COL_MI_MIAC_IDX, _miac.getIdx()+"");
		if(!DataFO.isEmptyString(_miacSrcNo))
			colValueMap.put(COL_MI_MIAC_SRC_NO, _miacSrcNo);
		return loadObjectList(TB_MATERIAL_INST, colValueMap, this::parseMaterialInst);
	}
	
	private static String packMaterialInstField(MbsbStmtQueryParam _param, String _tbMbsb, String _colMbsbMiUid) {
		String targetMasterField;
		switch (_param) {
		/* MaterialBinStockBatch:master */
		// -> MaterialInst
		case MISN:
			targetMasterField = COL_MI_MISN;
			break;
		case MIAC_IDX:
			targetMasterField = COL_MI_MIAC_IDX;
			break;
		case MIAC_SRC_NO:
			targetMasterField = COL_MI_MIAC_SRC_NO;
			break;
		case MI_SRC_STATUS_IDX:
			targetMasterField = COL_MI_SRC_STATUS_IDX;
			break;
		default:
			log.debug("not supported. {}", _param);
			return null;
		}
		return packMasterQueryField(targetMasterField, TB_MATERIAL_INST, COL_UID, _tbMbsb, _colMbsbMiUid);
	}

	// -------------------------------------------------------------------------------
	// ------------------------------MaterialInstSrcConj------------------------------
	private final static String TB_MATERIAL_INST_SRC_CONJ = "invt_mat_inst_src_conj";
	private final static String COL_MISC_MI_UID = "mi_uid";
	private final static String COL_MISC_SRC_MI_UID = "src_mi_uid";
	private final static String COL_MISC_SRC_MI_QTY = "src_mi_qty";
	private final static String COL_MISC_SRC_MI_VALUE = "src_mi_value";
	
	boolean saveMaterialInstSrcConj(MaterialInstSrcConj _c) {
		DbColumn<MaterialInstSrcConj>[] cols = new DbColumn[] {
				DbColumn.of(COL_MISC_MI_UID, ColType.STRING, MaterialInstSrcConj::getMiUid, 45), //
				DbColumn.of(COL_MISC_SRC_MI_UID, ColType.STRING, MaterialInstSrcConj::getSrcMiUid, 45), //
				DbColumn.of(COL_MISC_SRC_MI_QTY, ColType.DOUBLE, MaterialInstSrcConj::getSrcMiQty), //
				DbColumn.of(COL_MISC_SRC_MI_VALUE, ColType.DOUBLE, MaterialInstSrcConj::getSrcMiValue), //
		};
		return saveObject(TB_MATERIAL_INST_SRC_CONJ, cols, _c);
		
	}
	boolean deleteMaterialInstSrcConj(String _uid) {
		return deleteObject(TB_MATERIAL_INST_SRC_CONJ, _uid);
	}
	
	private MaterialInstSrcConj parseMaterialInstSrcConj(ResultSet _rs) {
		try {
			MaterialInstSrcConj c = MaterialInstSrcConj.getInstance(parseUid(_rs), parseObjectCreateTime(_rs),
					parseObjectUpdateTime(_rs));
			/* pack attributes */
			c.setMiUid(_rs.getString(COL_MISC_MI_UID));
			c.setSrcMiUid(_rs.getString(COL_MISC_SRC_MI_UID));
			c.setSrcMiQty(_rs.getDouble(COL_MISC_SRC_MI_QTY));
			c.setSrcMiValue(_rs.getDouble(COL_MISC_SRC_MI_VALUE));
			return c;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}
	
	MaterialInstSrcConj loadMaterialInstSrcConj(String _uid) {
		return loadObject(TB_MATERIAL_INST_SRC_CONJ, _uid, this::parseMaterialInstSrcConj);
	}
	List<MaterialInstSrcConj> loadMaterialInstSrcConjList(String _miUid){
		return loadObjectList(TB_MATERIAL_INST_SRC_CONJ, COL_MISC_MI_UID, _miUid, this::parseMaterialInstSrcConj);
	}
	List<MaterialInstSrcConj> loadMaterialInstSrcConjListBySrcMi(String _srcMiUid){
		return loadObjectList(TB_MATERIAL_INST_SRC_CONJ, COL_MISC_SRC_MI_UID, _srcMiUid, this::parseMaterialInstSrcConj);
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
	
	MaterialBinStock loadMaterialBinStock(String _mmUid, String _wrhsBinUid) {
		Map<String, String> colValueMap = new HashMap<>();
		colValueMap.put(COL_MBS_MM_UID, _mmUid);
		colValueMap.put(COL_MBS_WRHS_BIN_UID, _wrhsBinUid);
		return loadObject(TB_MATERIAL_BIN_STOCK, colValueMap, this::parseMaterialBinStock);
	}
	
	
	List<MaterialBinStock> loadMaterialBinStockList(String _mmUid){
		return loadObjectList(TB_MATERIAL_BIN_STOCK,COL_MBS_MM_UID, _mmUid, this::parseMaterialBinStock);
	}
	
	List<MaterialBinStock> loadMaterialBinStockListByWrhsBin(String _wbUid){
		return loadObjectList(TB_MATERIAL_BIN_STOCK,COL_MBS_WRHS_BIN_UID, _wbUid, this::parseMaterialBinStock);
	}
	
//	static String packMaterialBinStockQueryField(InvtOrderItemQueryParam _param, String _tbIoi, String _colIoiMbsUid) {
//		String targetMasterField;
//		switch (_param) {
//		/* MaterialBinStock:master */
//		case MBS_MM_UID:
//			targetMasterField = COL_MBS_MM_UID;
//			break;
//		case MBS_MANO:
//			targetMasterField = COL_MBS_MANO;
//			break;
//		case MBS_WB_UID:
//			targetMasterField = COL_MBS_WRHS_BIN_UID;
//			break;
//			// -> MaterialMaster
//		case MBS_MM_NAME:
//			targetMasterField = packMaterialMasterQueryField(_param, TB_MATERIAL_BIN_STOCK, COL_MBS_MM_UID);
//			break;
//		default:
//			log.debug("not supported. {}", _param);
//			return null;
//		}
//		return packMasterQueryField(targetMasterField, TB_MATERIAL_BIN_STOCK, COL_UID, _tbIoi, _colIoiMbsUid);
//	}
	
	private static String packMaterialBinStockField(MbsbStmtQueryParam _param, String _tbMbsb, String _colMbsbMbsUid) {
		String targetMasterField;
		switch (_param) {
		/* MaterialBinStockBatch:master */
		// -> MaterialBinStock
		case MBS_MANO:
			targetMasterField = COL_MBS_MANO;
			break;
		default:
			log.debug("not supported. {}", _param);
			return null;
		}
		return packMasterQueryField(targetMasterField, TB_MATERIAL_BIN_STOCK, COL_UID, _tbMbsb, _colMbsbMbsUid);
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
	
	private static String packMaterialBinStockBatchField(MbsbStmtQueryParam _param, String _tbMbsbs,
			String _colMbsbsMbsbUid) {
		String targetMasterField;
		switch (_param) {
		/* MaterialBinStockBatch:master */
		// -> MaterialBinStock
		case MBS_MANO:
			targetMasterField = packMaterialBinStockField(_param, TB_MAT_BIN_STOCK_BATCH, COL_MBSB_MBS_UID);
			break;
		// -> MaterialInst
		case MISN:
		case MIAC_IDX:
		case MIAC_SRC_NO:
		case MI_SRC_STATUS_IDX:
			targetMasterField = packMaterialInstField(_param, TB_MAT_BIN_STOCK_BATCH, COL_MBSB_MI_UID);
			break;
		default:
			log.debug("not supported. {}", _param);
			return null;
		}

		return packMasterQueryField(targetMasterField, TB_MAT_BIN_STOCK_BATCH, COL_UID, _tbMbsbs, _colMbsbsMbsbUid);
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
		/* MaterialBinStockBatch:master */
		// -> MaterialBinStock
		case MBS_MANO:
			// -> MaterialInst
		case MISN:
		case MIAC_IDX:
		case MIAC_SRC_NO:
		case MI_SRC_STATUS_IDX:
			return MaterialDao.packMaterialBinStockBatchField(_param, TB_MBSB_STMT, COL_MBSBS_MBSB_UID);
		default:
			log.warn("_param error. {}", _param);
			return null;
		}
	}

	QueryOperation<MbsbStmtQueryParam, MbsbStmt> searchMbsbStmt(QueryOperation<MbsbStmtQueryParam, MbsbStmt> _param) {
		return searchObject(TB_MBSB_STMT, _param, MaterialDao::parseMbsbStmtQueryParamMapping, this::parseMbsbStmt);
	}
	
	static String packMbsbStmtField(String _tbIoi, String _colIoiUid,
			Map<InvtOrderItemQueryParam, QueryValue[]> _existsDetailMap, InvtOrderItemQueryParam _param) {
		return packExistsField(_tbIoi, _colIoiUid, TB_MBSB_STMT, COL_MBSBS_IOI_UID,
				MaterialDao::parseMbsbStmtQueryParamMapping, _existsDetailMap, _param);
	}
	
	static String packMbsbStmtField(String _tbIoi, String _colIoiUid,Map<InvtOrderQueryParam, QueryValue[]> _existsDetailMap,InvtOrderQueryParam _param) {
		return packExistsField(_tbIoi, _colIoiUid, TB_MBSB_STMT, COL_MBSBS_IOI_UID,MaterialDao::parseMbsbStmtQueryParamMapping, _existsDetailMap, _param);
	}
}
