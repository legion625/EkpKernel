package ekp.data.service.sd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.event.Level;

import ekp.data.service.sd.query.SalesOrderItemQueryParam;
import ekp.data.service.sd.query.SalesOrderQueryParam;
import ekp.sd.SalesOrder;
import ekp.sd.SalesOrderItem;
import legion.data.service.AbstractMySqlDao;
import legion.data.service.AbstractMySqlDao.ColType;
import legion.util.LogUtil;
import legion.util.query.QueryOperation;
import legion.util.query.QueryOperation.QueryValue;

public class SoDao extends AbstractMySqlDao {
	protected SoDao(String source) {
		super(source);
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------SalesOrder-----------------------------------
	private final static String TB_SALES_ORDER = "sd_sales_order";
	private final static String COL_SO_SOSN = "sosn";
	private final static String COL_SO_TITLE = "title";
	private final static String COL_SO_CUSTOMER_UID = "customer_uid";
	private final static String COL_SO_CUSTOMER_NAME = "customer_name";
	private final static String COL_SO_CUSTOMER_BAN = "customer_ban";
	private final static String COL_SO_SALER_ID = "saler_id";
	private final static String COL_SO_SALER_NAME = "saler_name";
	private final static String COL_SO_SALE_DATE = "sale_date";

	boolean saveSalesOrder(SalesOrder _so) {
		DbColumn<SalesOrder>[] cols = new DbColumn[] {
				DbColumn.of(COL_SO_SOSN, ColType.STRING, SalesOrder::getSosn, 45), //
				DbColumn.of(COL_SO_TITLE, ColType.STRING, SalesOrder::getTitle, 45), //
				DbColumn.of(COL_SO_CUSTOMER_UID, ColType.STRING, SalesOrder::getCustomerUid, 45), //
				DbColumn.of(COL_SO_CUSTOMER_NAME, ColType.STRING, SalesOrder::getCustomerName, 45), //
				DbColumn.of(COL_SO_CUSTOMER_BAN, ColType.STRING, SalesOrder::getCustomerBan, 45), //
				DbColumn.of(COL_SO_SALER_ID, ColType.STRING, SalesOrder::getSalerId, 45), //
				DbColumn.of(COL_SO_SALER_NAME, ColType.STRING, SalesOrder::getSalerName, 45), //
				DbColumn.of(COL_SO_SALE_DATE, ColType.LONG, SalesOrder::getSaleDate), //
		};
		return saveObject(TB_SALES_ORDER, cols, _so);
	}

	boolean deleteSalesOrder(String _uid) {
		return deleteObject(TB_SALES_ORDER, _uid);
	}

	private SalesOrder parseSalesOrder(ResultSet _rs) {
		try {
			SalesOrder so = SalesOrder.getInstance(parseUid(_rs), parseObjectCreateTime(_rs),
					parseObjectUpdateTime(_rs));
			/* pack attributes */
			so.setSosn(_rs.getString(COL_SO_SOSN));
			so.setTitle(_rs.getString(COL_SO_TITLE));
			so.setCustomerUid(_rs.getString(COL_SO_CUSTOMER_UID));
			so.setCustomerName(_rs.getString(COL_SO_CUSTOMER_NAME));
			so.setCustomerBan(_rs.getString(COL_SO_CUSTOMER_BAN));
			so.setSalerId(_rs.getString(COL_SO_SALER_ID));
			so.setSalerName(_rs.getString(COL_SO_SALER_NAME));
			so.setSaleDate(_rs.getLong(COL_SO_SALE_DATE));
			return so;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}

	SalesOrder loadSalesOrder(String _uid) {
		return loadObject(TB_SALES_ORDER, _uid, this::parseSalesOrder);
	}

	SalesOrder loadSalesOrderBySosn(String _sosn) {
		return loadObject(TB_SALES_ORDER, COL_SO_SOSN, _sosn, this::parseSalesOrder);
	}

	private static String parseSalesOrderQueryparamMapping(SalesOrderQueryParam _param,
			Map<SalesOrderQueryParam, QueryValue[]> _existsDetailMap) {
		switch (_param) {
		/* SalesOrder:this */
		case SOSN:
			return COL_SO_SOSN;
		case TITLE:
			return COL_SO_TITLE;
		case CUSTOMER_UID:
			return COL_SO_CUSTOMER_UID;
		case CUSTOMER_NAME:
			return COL_SO_CUSTOMER_NAME;
		case CUSTOMER_BAN:
			return COL_SO_CUSTOMER_BAN;
		case SALER_ID:
			return COL_SO_SALER_ID;
		case SALER_NAME:
			return COL_SO_SALER_NAME;
		case SALE_DATE:
			return COL_SO_SALE_DATE;
			/* SalesOrderItem:detail */
		case B_OF_SOI$:
			return packSalesOrderItemField(TB_SALES_ORDER, COL_UID, _existsDetailMap, _param);
		default:
			log.warn("_param error. {}", _param);
			return null;
		}
	}

	QueryOperation<SalesOrderQueryParam, SalesOrder> searchSalesOrder(
			QueryOperation<SalesOrderQueryParam, SalesOrder> _param, Map<SalesOrderQueryParam, QueryValue[]>  _existsDetailMap){
		Function<SalesOrderQueryParam, String> queryParamMappingParser = p -> parseSalesOrderQueryparamMapping(p,
				_existsDetailMap);
		return searchObject(TB_SALES_ORDER, _param, queryParamMappingParser, this::parseSalesOrder);
	}
	
	// -------------------------------------------------------------------------------
	// --------------------------------SalesOrderItem---------------------------------
	private final static String TB_SALES_ORDER_ITEM = "sd_sales_order_item";
	private final static String COL_SOI_SO_UID = "so_uid";
	private final static String COL_SOI_MM_UID = "mm_uid";
	private final static String COL_SOI_MM_MANO = "mm_mano";
	private final static String COL_SOI_MM_NAME = "mm_name";
	private final static String COL_SOI_MM_SPEC = "mm_spec";
	private final static String COL_SOI_QTY = "qty";
	private final static String COL_SOI_VALUE = "value";
	private final static String COL_SOI_ALL_DELIVERED = "all_delivered";
	private final static String COL_SOI_FINISH_DELIVERED_DATE = "finish_delivered_date";
	
	boolean saveSalesOrderItem(SalesOrderItem _soi) {
		DbColumn<SalesOrderItem>[] cols = new DbColumn[] {
				DbColumn.of(COL_SOI_SO_UID,ColType.STRING, SalesOrderItem::getSoUid, 45 ), //
				DbColumn.of(COL_SOI_MM_UID,ColType.STRING, SalesOrderItem::getMmUid, 45 ), //
				DbColumn.of(COL_SOI_MM_MANO,ColType.STRING, SalesOrderItem::getMmMano, 45 ), //
				DbColumn.of(COL_SOI_MM_NAME,ColType.STRING, SalesOrderItem::getMmName, 45 ), //
				DbColumn.of(COL_SOI_MM_SPEC,ColType.STRING, SalesOrderItem::getMmSpec, 200 ), //
				DbColumn.of(COL_SOI_QTY,ColType.DOUBLE, SalesOrderItem::getQty), //
				DbColumn.of(COL_SOI_VALUE,ColType.DOUBLE, SalesOrderItem::getValue), //
				DbColumn.of(COL_SOI_ALL_DELIVERED,ColType.BOOLEAN, SalesOrderItem::isAllDelivered), //
				DbColumn.of(COL_SOI_FINISH_DELIVERED_DATE,ColType.LONG, SalesOrderItem::getFinishDeliveredDate), //
		};
		return saveObject(TB_SALES_ORDER_ITEM, cols, _soi);
	}
	boolean deleteSalesOrderItem(String _uid) {
		return deleteObject(TB_SALES_ORDER_ITEM, _uid);
	}
	
	private SalesOrderItem parseSalesOrderItem(ResultSet _rs) {
		try {
			String soUid = _rs.getString(COL_SOI_SO_UID);
			SalesOrderItem soi = SalesOrderItem.getInstance(parseUid(_rs), soUid, parseObjectCreateTime(_rs), parseObjectUpdateTime(_rs));
			/* pack attributes */
			soi.setMmUid(_rs.getString(COL_SOI_MM_UID));
			soi.setMmMano(_rs.getString(COL_SOI_MM_MANO));
			soi.setMmName(_rs.getString(COL_SOI_MM_NAME));
			soi.setMmSpec(_rs.getString(COL_SOI_MM_SPEC));
			soi.setQty(_rs.getDouble(COL_SOI_QTY));
			soi.setValue(_rs.getDouble(COL_SOI_VALUE));
			soi.setAllDelivered(_rs.getBoolean(COL_SOI_ALL_DELIVERED));
			soi.setFinishDeliveredDate(_rs.getLong(COL_SOI_FINISH_DELIVERED_DATE));
			return soi;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}
	SalesOrderItem loadSalesOrderItem(String _uid) {
		return loadObject(TB_SALES_ORDER_ITEM, _uid, this::parseSalesOrderItem);
	}
	List<SalesOrderItem> loadSalesOrderItemList(String _soUid){
		return loadObjectList(TB_SALES_ORDER_ITEM, COL_SOI_SO_UID, _soUid, this::parseSalesOrderItem);
	}
	List<SalesOrderItem> loadSalesOrderItemListMyMm(String _mmUid){
		return loadObjectList(TB_SALES_ORDER_ITEM, COL_SOI_MM_UID, _mmUid, this::parseSalesOrderItem);
	}

	private static String parseSalesOrderItemQueryparamMapping(SalesOrderItemQueryParam _param) {
		switch (_param) {
		/* SalesOrderItem:detail */
		case SO_UID:
			return COL_SOI_SO_UID;
		case MM_MID:
			return COL_SOI_MM_UID;
		case MM_MANO:
			return COL_SOI_MM_MANO;
		case MM_NAME:
			return COL_SOI_MM_NAME;
		case MM_SPEC:
			return COL_SOI_MM_SPEC;
		case ALL_DELIVERED:
			return COL_SOI_ALL_DELIVERED;
		case FINISH_DELIVERED_DATE:
			return COL_SOI_FINISH_DELIVERED_DATE;
		default:
			log.warn("_param error. {}", _param);
			return null;
		}
	}

	QueryOperation<SalesOrderItemQueryParam, SalesOrderItem> searchSalesOrderItem(
			QueryOperation<SalesOrderItemQueryParam, SalesOrderItem> _param) {
		return searchObject(TB_SALES_ORDER_ITEM, _param, SoDao::parseSalesOrderItemQueryparamMapping,
				this::parseSalesOrderItem);
	}
	
	private static String packSalesOrderItemField(String _tbSo, String _colSoUid,
			Map<SalesOrderQueryParam, QueryValue[]> _existsDetailMap, SalesOrderQueryParam _param) {
		return packExistsField(_tbSo, _colSoUid, TB_SALES_ORDER_ITEM, COL_SOI_SO_UID,
				SoDao::parseSalesOrderItemQueryparamMapping, _existsDetailMap, _param);
	}
}
