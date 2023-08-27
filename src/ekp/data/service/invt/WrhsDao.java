package ekp.data.service.invt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.event.Level;

import ekp.data.service.invt.query.InvtOrderItemQueryParam;
import ekp.data.service.invt.query.InvtOrderQueryParam;
import ekp.invt.InvtOrder;
import ekp.invt.InvtOrderItem;
import ekp.invt.WrhsBin;
import ekp.invt.WrhsLoc;
import ekp.invt.type.InvtOrderStatus;
import ekp.invt.type.InvtOrderType;
import ekp.mbom.Part;
import legion.data.service.AbstractMySqlDao;
import legion.data.service.AbstractMySqlDao.ColType;
import legion.data.service.AbstractMySqlDao.DbColumn;
import legion.util.LogUtil;
import legion.util.query.QueryOperation;
import legion.util.query.QueryParam;
import legion.util.query.QueryOperation.QueryValue;

public class WrhsDao extends AbstractMySqlDao {

	protected WrhsDao(String source) {
		super(source);
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsLoc------------------------------------
	private final static String TB_WRHS_LOC = "invt_wrhs_loc";
	private final static String COL_WL_ID = "id";
	private final static String COL_WL_NAME = "name";

	boolean saveWrhsLoc(WrhsLoc _wl) {
		DbColumn<WrhsLoc>[] cols = new DbColumn[] { //
				DbColumn.of(COL_WL_ID, ColType.STRING, WrhsLoc::getId, 45), //
				DbColumn.of(COL_WL_NAME, ColType.STRING, WrhsLoc::getName, 45), //
		};
		return saveObject(TB_WRHS_LOC, cols, _wl);
	}

	boolean deleteWrhsLoc(String _uid) {
		return deleteObject(TB_WRHS_LOC, _uid);
	}

	private WrhsLoc parseWrhsLoc(ResultSet _rs) {
		try {
			WrhsLoc wl = WrhsLoc.getInstance(parseUid(_rs), parseObjectCreateTime(_rs), parseObjectUpdateTime(_rs));
			/* pack attributes */
			wl.setId(_rs.getString(COL_WL_ID));
			wl.setName(_rs.getString(COL_WL_NAME));
			return wl;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}

	WrhsLoc loadWrhsLoc(String _uid) {
		return loadObject(TB_WRHS_LOC, _uid, this::parseWrhsLoc);
	}

	WrhsLoc loadWrhsLocById(String _id) {
		return loadObject(TB_WRHS_LOC, COL_WL_ID, _id, this::parseWrhsLoc);
	}

	List<WrhsLoc> loadWrhsLocList() {
		return loadObjectList(TB_WRHS_LOC, this::parseWrhsLoc);
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------WrhsBin------------------------------------
	private final static String TB_WRHS_BIN = "invt_wrhs_bin";
	private final static String COL_WB_WL_UID = "wl_uid";
	private final static String COL_WB_ID = "id";
	private final static String COL_WB_NAME = "name";
	
	boolean saveWrhsBin(WrhsBin _wb) {
		DbColumn<WrhsBin>[] cols = new DbColumn[] { //
				DbColumn.of(COL_WB_WL_UID, ColType.STRING, WrhsBin::getWlUid, 45), //
				DbColumn.of(COL_WB_ID, ColType.STRING, WrhsBin::getId, 45), //
				DbColumn.of(COL_WB_NAME, ColType.STRING, WrhsBin::getName, 45), //
		};
		return saveObject(TB_WRHS_BIN, cols, _wb);
	}

	boolean deleteWrhsBin(String _uid) {
		return deleteObject(TB_WRHS_BIN, _uid);
	}

	private WrhsBin parseWrhsBin(ResultSet _rs) {
		try {
			String wlUid = _rs.getString(COL_WB_WL_UID);
			WrhsBin wb = WrhsBin.getInstance(parseUid(_rs),wlUid, parseObjectCreateTime(_rs), parseObjectUpdateTime(_rs));
			/* pack attributes */
			wb.setId(_rs.getString(COL_WL_ID));
			wb.setName(_rs.getString(COL_WL_NAME));
			return wb;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}
	
	WrhsBin loadWrhsBin(String _uid) {
		return loadObject(TB_WRHS_BIN, _uid, this::parseWrhsBin);
	}

	WrhsBin loadWrhsBin(String _wlUid, String _id) {
		Map<String, String> colValueMap = new HashMap<>();
		colValueMap.put(COL_WB_WL_UID, _wlUid);
		colValueMap.put(COL_WB_ID, _id);
		return loadObject(TB_WRHS_BIN, colValueMap, this::parseWrhsBin);
	}
	
	List<WrhsBin> loadWrhsBinList(String _wlUid){
		return loadObjectList(TB_WRHS_BIN,COL_WB_WL_UID, _wlUid, this::parseWrhsBin);
	}
	
	// -------------------------------------------------------------------------------
	// -----------------------------------InvtOrder-----------------------------------
	private final static String TB_INVT_ORDER = "invt_invt_order";
	private final static String COL_IO_IOSN = "iosn";
	private final static String COL_IO_STATUS_IDX = "status_idx";
	private final static String COL_IO_APPLIER_ID = "applier_id";
	private final static String COL_IO_APPLIER_NAME = "applier_name";
	private final static String COL_IO_APPLY_TIME = "apply_time";
	private final static String COL_IO_REMARK = "remark";
	private final static String COL_IO_APV_TIME = "apv_time";
	
	boolean saveInvtOrder(InvtOrder _io) {
		DbColumn<InvtOrder>[] cols = new DbColumn[] { // 
				DbColumn.of(COL_IO_IOSN, ColType.STRING, InvtOrder::getIosn , 45), //
				DbColumn.of(COL_IO_STATUS_IDX, ColType.INT, InvtOrder::getStatusIdx), //
				DbColumn.of(COL_IO_APPLIER_ID, ColType.STRING, InvtOrder::getApplierId , 45), //
				DbColumn.of(COL_IO_APPLIER_NAME, ColType.STRING, InvtOrder::getApplierName , 45), //
				DbColumn.of(COL_IO_APPLY_TIME, ColType.LONG, InvtOrder::getApplyTime), //
				DbColumn.of(COL_IO_REMARK, ColType.STRING, InvtOrder::getRemark , 200), //
				DbColumn.of(COL_IO_APV_TIME, ColType.LONG, InvtOrder::getApvTime), //
		};
		return saveObject(TB_INVT_ORDER, cols, _io);
	}
	boolean deleteInvtOrder(String _uid) {
		return deleteObject(TB_INVT_ORDER, _uid);
	}

	private InvtOrder parseInvtOrder(ResultSet _rs) {
		try {
			InvtOrderStatus status = InvtOrderStatus.get(_rs.getInt(COL_IO_STATUS_IDX));
			InvtOrder io = InvtOrder.getInstance(parseUid(_rs),status, parseObjectCreateTime(_rs), parseObjectUpdateTime(_rs));
			/* pack attributes */
			io.setIosn(_rs.getString(COL_IO_IOSN));
			io.setApplierId(_rs.getString(COL_IO_APPLIER_ID));
			io.setApplierName(_rs.getString(COL_IO_APPLIER_NAME));
			io.setApplyTime(_rs.getLong(COL_IO_APPLY_TIME));
			io.setRemark(_rs.getString(COL_IO_REMARK));
			io.setApvTime(_rs.getLong(COL_IO_APV_TIME));
			return io;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}
	
	InvtOrder loadInvtOrder(String _uid) {
		return loadObject(TB_INVT_ORDER, _uid, this::parseInvtOrder);
	}
	InvtOrder loadInvtOrderByIosn(String _iosn) {
		return loadObject(TB_INVT_ORDER, COL_IO_IOSN, _iosn, this::parseInvtOrder);
	}
	
	private String parseInvtOrderQueryParamMapping(InvtOrderQueryParam _param, Map<InvtOrderQueryParam, QueryValue[]> _map) {
		switch (_param) {
		/* InvtOrder:this */
		case IOSN:
			return COL_IO_IOSN;
		case STATUS_IDX:
			return COL_IO_STATUS_IDX;
		case APPLIER_ID:
			return COL_IO_APPLIER_ID;
		case APPLIER_NAME:
			return COL_IO_APPLIER_NAME;
		case APPLY_TIME:
			return COL_IO_APPLY_TIME;
		case REMARK:
			return COL_IO_REMARK;
		case APV_TIME:
			return COL_IO_APV_TIME;
		/* InvtOrderItem:detail */
		case B_of_IOI$:
			return packInvtOrderItemField(TB_INVT_ORDER, COL_UID, _map, _param);
			/* InvtOrderItem->MbsbStmt:detail */
		case B_of_IOI_of_MBSBS$:
			return packInvtOrderItemField(TB_INVT_ORDER, COL_UID, _map, _param); // TODO

		default:
			log.warn("_param error. {}", _param);
			return null;
		}
	}
	
	QueryOperation<InvtOrderQueryParam, InvtOrder> searchInvtOrder(QueryOperation<InvtOrderQueryParam, InvtOrder> _param
			, Map<InvtOrderQueryParam, QueryValue[]> _existsDetailMap){
		Function<InvtOrderQueryParam, String> _queryParamMappingParser = p -> parseInvtOrderQueryParamMapping(p,
				_existsDetailMap); 
		return searchObject(TB_INVT_ORDER, _param, _queryParamMappingParser, this::parseInvtOrder);
	}
	
	private String packInvtOrderQueryField(InvtOrderItemQueryParam _param, String _tbIoi, String _colIoiIoUid) {
		String targetMasterField;
		switch (_param) {
		/* InvtOrder:master */
		case IOSN:
			targetMasterField = COL_IO_IOSN;
			break;
		case IO_STATUS_IDX:
			targetMasterField = COL_IO_STATUS_IDX;
			break;
		case IO_APPLIER_ID:
			targetMasterField = COL_IO_APPLIER_ID;
			break;
		case IO_APPLIER_NAME:
			targetMasterField = COL_IO_APPLIER_NAME;
			break;
		case IO_APPLY_TIME:
			targetMasterField = COL_IO_APPLY_TIME;
			break;
		case IO_REMARK:
			targetMasterField = COL_IO_REMARK;
			break;
		case IO_APV_TIME:
			targetMasterField = COL_IO_APV_TIME;
			break;
		default:
			log.debug("not supported. {}", _param);
			return null;
		}
		return packMasterQueryField(targetMasterField, TB_INVT_ORDER, COL_UID, _tbIoi, _colIoiIoUid);
	}
	
//	
	
	// -------------------------------------------------------------------------------
	// ---------------------------------InvtOrderItem---------------------------------
	private final static String TB_INVT_ORDER_ITEM = "invt_invt_order_item";
	private final static String COL_IOI_IO_UID = "io_uid";
	private final static String COL_IOI_MM_UID = "mm_uid";
	private final static String COL_IOI_IO_TYPE_IDX = "io_type_idx";
	private final static String COL_IOI_ORDER_QTY = "order_qty";
	private final static String COL_IOI_ORDER_VALUE = "order_value";
	private final static String COL_IOI_MI_ASSIGNED = "mi_assigned";
	private final static String COL_IOI_MI_UID = "mi_uid";
	private final static String COL_IOI_WRHS_BIN_ASSIGNED = "wrhs_bin_assigned";
	private final static String COL_IOI_WRHS_BIN_UID = "wrhs_bin_uid";
	
	
	boolean saveInvtOrderItem(InvtOrderItem _ioi) {
		DbColumn<InvtOrderItem>[] cols = new DbColumn[] { //
				DbColumn.of(COL_IOI_IO_UID, ColType.STRING, InvtOrderItem::getIoUid, 45), //
				DbColumn.of(COL_IOI_MM_UID, ColType.STRING, InvtOrderItem::getMmUid, 45), //
				DbColumn.of(COL_IOI_IO_TYPE_IDX, ColType.INT, InvtOrderItem::getIoTypeIdx), //
				DbColumn.of(COL_IOI_ORDER_QTY, ColType.DOUBLE, InvtOrderItem::getOrderQty), //
				DbColumn.of(COL_IOI_ORDER_VALUE, ColType.DOUBLE, InvtOrderItem::getOrderValue), //
				DbColumn.of(COL_IOI_MI_ASSIGNED, ColType.BOOLEAN, InvtOrderItem::isMiAssigned), //
				DbColumn.of(COL_IOI_MI_UID, ColType.STRING, InvtOrderItem::getMiUid, 45), //
				DbColumn.of(COL_IOI_WRHS_BIN_ASSIGNED, ColType.BOOLEAN, InvtOrderItem::isWrhsBinAssigned), //
				DbColumn.of(COL_IOI_WRHS_BIN_UID, ColType.STRING, InvtOrderItem::getWrhsBinUid, 45), //
		};
		return saveObject(TB_INVT_ORDER_ITEM, cols, _ioi);
	}
	boolean deleteInvtOrderItem(String _uid) {
		return deleteObject(TB_INVT_ORDER_ITEM, _uid);
	}

	private InvtOrderItem parseInvtOrderItem(ResultSet _rs) {
		try {
			String ioUid = _rs.getString(COL_IOI_IO_UID);
			InvtOrderItem ioi = InvtOrderItem.getInstance(parseUid(_rs), ioUid, parseObjectCreateTime(_rs),
					parseObjectUpdateTime(_rs));
			/* pack attributes */
			ioi.setMmUid(_rs.getString(COL_IOI_MM_UID));
			ioi.setIoType(InvtOrderType.get(_rs.getInt(COL_IOI_IO_TYPE_IDX)));
			ioi.setOrderQty(_rs.getDouble(COL_IOI_ORDER_QTY));
			ioi.setOrderValue(_rs.getDouble(COL_IOI_ORDER_VALUE));
			ioi.setMiAssigned(_rs.getBoolean(COL_IOI_MI_ASSIGNED));
			ioi.setMiUid(_rs.getString(COL_IOI_MI_UID));
			ioi.setWrhsBinAssigned(_rs.getBoolean(COL_IOI_WRHS_BIN_ASSIGNED));
			ioi.setWrhsBinUid(_rs.getString(COL_IOI_WRHS_BIN_UID));
			return ioi;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}

	InvtOrderItem loadInvtOrderItem(String _uid) {
		return loadObject(TB_INVT_ORDER_ITEM, _uid, this::parseInvtOrderItem);
	}
	
	List<InvtOrderItem> loadInvtOrderItemList(String _ioUid){
		return loadObjectList(TB_INVT_ORDER_ITEM,COL_IOI_IO_UID, _ioUid, this::parseInvtOrderItem);
	}
	
	List<InvtOrderItem> loadInvtOrderItemListByMm(String _mmUid){
		return loadObjectList(TB_INVT_ORDER_ITEM,COL_IOI_MM_UID, _mmUid, this::parseInvtOrderItem);
	}
	List<InvtOrderItem> loadInvtOrderItemListByMi(String _miUid){
		return loadObjectList(TB_INVT_ORDER_ITEM,COL_IOI_MI_UID, _miUid, this::parseInvtOrderItem);
	}
	List<InvtOrderItem> loadInvtOrderItemListByWb(String _wrhsBinUid){
		return loadObjectList(TB_INVT_ORDER_ITEM,COL_IOI_WRHS_BIN_UID, _wrhsBinUid, this::parseInvtOrderItem);
	}
	
	private String parseInvtOrderItemQueryParamMapping(InvtOrderItemQueryParam _param
			, Map<InvtOrderItemQueryParam, QueryValue[]> _existsDetailMap) {
		switch (_param) {
		/* InvtOrderItem:this */
		case IO_UID:
			return COL_IOI_IO_UID;
		case MM_UID:
			return COL_IOI_MM_UID;
		case IO_TYPE_IDX:
			return COL_IOI_IO_TYPE_IDX;
		case MI_UID:
			return COL_IOI_MI_UID;
		case WRHS_BIN_UID:
			return COL_IOI_WRHS_BIN_UID;
		/* InvtOrder:master */
		case IOSN:
		case IO_STATUS_IDX:
		case IO_APPLIER_ID:
		case IO_APPLIER_NAME:
		case IO_APPLY_TIME:
		case IO_REMARK:
		case IO_APV_TIME:
			return packInvtOrderQueryField(_param, TB_INVT_ORDER_ITEM, COL_IOI_IO_UID);
		/* MaterialMaster */
		case MM_MANO:
		case MM_NAME:
			return MaterialDao.packMaterialMasterQueryField(_param, TB_INVT_ORDER_ITEM, COL_IOI_MM_UID);
			
		/* MbsbStmt:Detail */
		case B_of_MBSBS$:
			return MaterialDao.packMbsbStmtField(TB_INVT_ORDER_ITEM,COL_UID, _existsDetailMap, _param);
		default:
			log.warn("_param error. {}", _param);
			return null;
		}
	}
	
	QueryOperation<InvtOrderItemQueryParam, InvtOrderItem> searchInvtOrderItem(
			QueryOperation<InvtOrderItemQueryParam, InvtOrderItem> _param,
			Map<InvtOrderItemQueryParam, QueryValue[]> _existsDetailMap) {
		Function<InvtOrderItemQueryParam, String> queryParamMappingParser = p -> parseInvtOrderItemQueryParamMapping(p,
				_existsDetailMap);
		return searchObject(TB_INVT_ORDER_ITEM, _param, queryParamMappingParser, this::parseInvtOrderItem);
	}

	private String packInvtOrderItemField(String _tbIo, String _colIoUid,
			Map<InvtOrderQueryParam, QueryValue[]> _existsDetailMap, InvtOrderQueryParam _param) {
		Function<InvtOrderItemQueryParam, String> queryParamMappingParser = p -> parseInvtOrderItemQueryParamMapping(p,
				null);
		
//		String targetDetailField;
		switch (_param) {
		case B_of_IOI$:
			return packExistsField(_tbIo, _colIoUid, TB_INVT_ORDER_ITEM, COL_IOI_IO_UID, queryParamMappingParser,
					_existsDetailMap, _param);
		case B_of_IOI_of_MBSBS$:
			return packExistsField(_tbIo, _colIoUid, TB_INVT_ORDER_ITEM, COL_IOI_IO_UID, MaterialDao.packMbsbStmtField(TB_INVT_ORDER_ITEM, COL_UID, _existsDetailMap, _param));
// FIXME
//			XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		default:
			log.warn("_param error. {}", _param);
			return null;
		}
		
		
		
//		B_OF_IOI_MBSBS$
	}
}
