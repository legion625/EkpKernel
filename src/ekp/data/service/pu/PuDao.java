package ekp.data.service.pu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.event.Level;

import ekp.data.service.pu.query.PurchItemQueryParam;
import ekp.data.service.pu.query.PurchQueryParam;
import ekp.invt.MaterialMaster;
import ekp.mbom.type.PartAcquisitionType;
import ekp.mbom.type.PartUnit;
import ekp.pu.Purch;
import ekp.pu.PurchItem;
import ekp.pu.type.PurchPerfStatus;
import legion.data.service.AbstractMySqlDao;
import legion.data.service.AbstractMySqlDao.ColType;
import legion.util.LogUtil;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public class PuDao extends AbstractMySqlDao{

	protected PuDao(String source) {
		super(source);
	}
	
	// -------------------------------------------------------------------------------
	// -------------------------------------Purch-------------------------------------
	private final static String TB_PURCH = "pu_purch";
	private final static String COL_P_PU_NO = "pu_no";
	private final static String COL_P_TITLE = "title";
	private final static String COL_P_SUPPLIER_UID = "supplier_uid";
	private final static String COL_P_SUPPLIER_NAME = "supplier_name";
	private final static String COL_P_SUPPLIER_BAN = "supplier_ban";
	private final static String COL_P_PERF_STATUS_IDX = "perf_status_idx";
	private final static String COL_P_PERF_TIME = "perf_time";
	
	boolean savePurch(Purch _p) {
		DbColumn<Purch>[] cols = new DbColumn[] {
				DbColumn.of(COL_P_PU_NO, ColType.STRING, Purch::getPuNo , 45), //
				DbColumn.of(COL_P_TITLE, ColType.STRING, Purch::getTitle , 45), //
				DbColumn.of(COL_P_SUPPLIER_UID, ColType.STRING, Purch::getSupplierUid , 45), //
				DbColumn.of(COL_P_SUPPLIER_NAME, ColType.STRING, Purch::getSupplierName , 45), //
				DbColumn.of(COL_P_SUPPLIER_BAN, ColType.STRING, Purch::getSupplierBan , 45), //
				DbColumn.of(COL_P_PERF_STATUS_IDX, ColType.INT, Purch::getPerfStatusIdx), //
				DbColumn.of(COL_P_PERF_TIME, ColType.LONG, Purch::getPerfTime), //
		};
		return saveObject(TB_PURCH, cols, _p);
	}
	boolean deletePurch(String _uid) {
		return deleteObject(TB_PURCH, _uid);
	}

	private Purch parsePurch(ResultSet _rs) {
		try {
			PurchPerfStatus perfStatus = PurchPerfStatus.get(_rs.getInt(COL_P_PERF_STATUS_IDX));
			Purch p = Purch.getInstance(parseUid(_rs), perfStatus, parseObjectCreateTime(_rs),
					parseObjectUpdateTime(_rs));
			/* pack attributes */
			p.setPuNo(_rs.getString(COL_P_PU_NO));
			p.setTitle(_rs.getString(COL_P_TITLE));
			p.setSupplierUid(_rs.getString(COL_P_SUPPLIER_UID));
			p.setSupplierName(_rs.getString(COL_P_SUPPLIER_NAME));
			p.setSupplierBan(_rs.getString(COL_P_SUPPLIER_BAN));
			p.setPerfTime(_rs.getLong(COL_P_PERF_TIME));
			return p;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}
	
	Purch loadPurch(String _uid) {
		return loadObject(TB_PURCH, _uid, this::parsePurch);
	}

	Purch loadPurchByPuNo(String _puNo) {
		return loadObject(TB_PURCH, COL_P_PU_NO, _puNo, this::parsePurch);
	}
	
	private static String parsePurchQueryparamMapping(PurchQueryParam _param,
			Map<PurchQueryParam, QueryValue[]> _existsDetailMap) {
		switch (_param) {
		/* Purch:this */
		case PU_NO:
			return COL_P_PU_NO;
		case TITLE:
			return COL_P_TITLE;
		case SUPPLIER_UID:
			return COL_P_SUPPLIER_UID;
		case SUPPLIER_NAME:
			return COL_P_SUPPLIER_NAME;
		case SUPPLIER_BAN:
			return COL_P_SUPPLIER_BAN;
		case PERF_STATUS_IDX:
			return COL_P_PERF_STATUS_IDX;
		case PERF_TIME:
			return COL_P_PERF_TIME;
		/* PurchItem:detail */
		case B_OF_PI$:
			return packPurchItemField(TB_PURCH, COL_UID, _existsDetailMap, _param);

		default:
			log.warn("_param error. {}", _param);
			return null;
		}
	}
	
	QueryOperation<PurchQueryParam, Purch> searchPurch(QueryOperation<PurchQueryParam, Purch> _param
			, Map<PurchQueryParam, QueryValue[]> _existsDetailMap){
		Function<PurchQueryParam, String> queryParamMappingParser = p->parsePurchQueryparamMapping(p, _existsDetailMap);
		return searchObject(TB_PURCH, _param, queryParamMappingParser, this::parsePurch);
	}
	
	// -------------------------------------------------------------------------------
	// -----------------------------------PurchItem-----------------------------------
	private final static String TB_PURCH_ITEM = "pu_purch_item";
	private final static String COL_PI_PURCH_UID = "purch_uid";
	private final static String COL_PI_MM_UID = "mm_uid";
	private final static String COL_PI_MM_MANO = "mm_mano";
	private final static String COL_PI_MM_NAME = "mm_name";
	private final static String COL_PI_MM_SPECIFICATION = "mm_specification";
	private final static String COL_PI_MM_STD_UNIT_ID = "mm_std_unit_id";
	private final static String COL_PI_REF_PA = "ref_pa";
	private final static String COL_PI_REF_PA_UID = "ref_pa_uid";
	private final static String COL_PI_REF_PA_TYPE_IDX = "ref_pa_type_idx";
	private final static String COL_PI_QTY = "qty";
	private final static String COL_PI_VALUE = "value";
	private final static String COL_PI_REMARK = "remark";
	
	boolean savePurchItem(PurchItem _pi) {
		DbColumn<PurchItem>[] cols = new DbColumn[] { // 
				DbColumn.of(COL_PI_PURCH_UID, ColType.STRING, PurchItem:: getPurchUid, 45 ), //
				DbColumn.of(COL_PI_MM_UID, ColType.STRING, PurchItem:: getMmUid, 45 ), //
				DbColumn.of(COL_PI_MM_MANO, ColType.STRING, PurchItem:: getMmMano, 45 ), //
				DbColumn.of(COL_PI_MM_NAME, ColType.STRING, PurchItem:: getMmName, 45 ), //
				DbColumn.of(COL_PI_MM_SPECIFICATION, ColType.STRING, PurchItem::getMmSpecification, 45 ), //
				DbColumn.of(COL_PI_MM_STD_UNIT_ID, ColType.STRING, PurchItem:: getMmStdUnitId, 10 ), //
				DbColumn.of(COL_PI_REF_PA, ColType.BOOLEAN, PurchItem::isRefPa), //
				DbColumn.of(COL_PI_REF_PA_UID, ColType.STRING, PurchItem::getRefPaUid), //
				DbColumn.of(COL_PI_REF_PA_TYPE_IDX, ColType.INT, PurchItem::getRefPaTypeIdx), //
				DbColumn.of(COL_PI_QTY, ColType.DOUBLE, PurchItem:: getQty), //
				DbColumn.of(COL_PI_VALUE, ColType.DOUBLE, PurchItem:: getValue ), //
				DbColumn.of(COL_PI_REMARK, ColType.STRING, PurchItem:: getRemark, 200 ), //
		};
		return saveObject(TB_PURCH_ITEM, cols, _pi);
	}
	boolean deletePurchItem(String _uid) {
		return deleteObject(TB_PURCH_ITEM, _uid);
	}
	
	private PurchItem parsePurchItem(ResultSet _rs) {
		try {
			String purchUid = _rs.getString(COL_PI_PURCH_UID);
			PurchItem pi = PurchItem.getInstance(parseUid(_rs), purchUid, parseObjectCreateTime(_rs),
					parseObjectUpdateTime(_rs));
			/* pack attributes */
			pi.setMmUid(_rs.getString(COL_PI_MM_UID));
			pi.setMmMano(_rs.getString(COL_PI_MM_MANO));
			pi.setMmName(_rs.getString(COL_PI_MM_NAME));
			pi.setMmSpecification(_rs.getString(COL_PI_MM_SPECIFICATION));
			pi.setMmStdUnit(PartUnit.get(_rs.getString(COL_PI_MM_STD_UNIT_ID)));
			pi.setRefPa(_rs.getBoolean(COL_PI_REF_PA));
			pi.setRefPaUid(_rs.getString(COL_PI_REF_PA_UID));
			pi.setRefPaType(PartAcquisitionType.get(_rs.getInt(COL_PI_REF_PA_TYPE_IDX)));
			pi.setQty(_rs.getDouble(COL_PI_QTY));
			pi.setValue(_rs.getDouble(COL_PI_VALUE));
			pi.setRemark(_rs.getString(COL_PI_REMARK));
			return pi;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}

	PurchItem loadPurchItem(String _uid) {
		return loadObject(TB_PURCH_ITEM, _uid, this::parsePurchItem);
	}

	List<PurchItem> loadPurchItemList(String _purchUid) {
		return loadObjectList(TB_PURCH_ITEM, COL_PI_PURCH_UID, _purchUid, this::parsePurchItem);
	}
	
	List<PurchItem> loadPurchItemListByMm(String _mmUid){
		return loadObjectList(TB_PURCH_ITEM, COL_PI_MM_UID, _mmUid, this::parsePurchItem);
	}
	
	private static String parsePurchItemQueryParamMapping(PurchItemQueryParam _param) {
		switch (_param) {
		case MM_UID:
			return COL_PI_MM_UID;
		case MM_MANO:
			return COL_PI_MM_MANO;
		case MM_NAME:
			return COL_PI_MM_NAME;
		case MM_SPECIFICATION:
			return COL_PI_MM_SPECIFICATION;
		case REF_PA:
			return COL_PI_REF_PA;
		case REF_PA_UID:
			return COL_PI_REF_PA_UID;
		case REF_PA_TYPE_IDX:
			return COL_PI_REF_PA_TYPE_IDX;
		case REMARK:
			return COL_PI_REMARK;
		default:
			log.warn("_param error. {}", _param);
			return null;
		}
	}
	
	private static String packPurchItemField(String _tbPurch, String _colPurchUid,
			Map<PurchQueryParam, QueryValue[]> _existsDetailMap, PurchQueryParam _param) {
		return packExistsField(_tbPurch, _colPurchUid, TB_PURCH_ITEM, COL_PI_PURCH_UID,
				PuDao::parsePurchItemQueryParamMapping, _existsDetailMap, _param);
	}

}
