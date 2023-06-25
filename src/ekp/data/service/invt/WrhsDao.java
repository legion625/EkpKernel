package ekp.data.service.invt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.event.Level;

import ekp.invt.InvtOrder;
import ekp.invt.InvtOrderItem;
import ekp.invt.WrhsBin;
import ekp.invt.WrhsLoc;
import ekp.invt.type.InvtOrderType;
import ekp.mbom.Part;
import legion.data.service.AbstractMySqlDao;
import legion.data.service.AbstractMySqlDao.ColType;
import legion.data.service.AbstractMySqlDao.DbColumn;
import legion.util.LogUtil;

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

	List<WrhsBin> loadWrhsBinList(String _wlUid){
		return loadObjectList(TB_WRHS_BIN,COL_WB_WL_UID, _wlUid, this::parseWrhsBin);
	}
	
	// -------------------------------------------------------------------------------
	// -----------------------------------InvtOrder-----------------------------------
	private final static String TB_INVT_ORDER = "invt_invt_order";
	private final static String COL_IO_IOSN = "iosn";
	private final static String COL_IO_APPLIER_ID = "applier_id";
	private final static String COL_IO_APPLIER_NAME = "applier_name";
	private final static String COL_IO_APV_TIME = "apv_time";
	private final static String COL_IO_REMARK = "remark";
	
	boolean saveInvtOrder(InvtOrder _io) {
		DbColumn<InvtOrder>[] cols = new DbColumn[] { // 
				DbColumn.of(COL_IO_IOSN, ColType.STRING, InvtOrder::getIosn , 45), //
				DbColumn.of(COL_IO_APPLIER_ID, ColType.STRING, InvtOrder::getApplierId , 45), //
				DbColumn.of(COL_IO_APPLIER_NAME, ColType.STRING, InvtOrder::getApplierName , 45), //
				DbColumn.of(COL_IO_APV_TIME, ColType.LONG, InvtOrder::getApvTime), //
				DbColumn.of(COL_IO_REMARK, ColType.STRING, InvtOrder::getRemark , 200), //
		};
		return saveObject(TB_INVT_ORDER, cols, _io);
	}
	boolean deleteInvtOrder(String _uid) {
		return deleteObject(TB_INVT_ORDER, _uid);
	}

	private InvtOrder parseInvtOrder(ResultSet _rs) {
		try {
			InvtOrder io = InvtOrder.getInstance(parseUid(_rs), parseObjectCreateTime(_rs), parseObjectUpdateTime(_rs));
			/* pack attributes */
			io.setIosn(_rs.getString(COL_IO_IOSN));
			io.setApplierId(_rs.getString(COL_IO_APPLIER_ID));
			io.setApplierName(_rs.getString(COL_IO_APPLIER_NAME));
			io.setApvTime(_rs.getLong(COL_IO_APV_TIME));
			io.setRemark(_rs.getString(COL_IO_REMARK));
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
	
	// -------------------------------------------------------------------------------
	// ---------------------------------InvtOrderItem---------------------------------
	private final static String TB_INVT_ORDER_ITEM = "invt_invt_order_item";
	private final static String COL_IOI_IO_UID = "io_uid";
	private final static String COL_IOI_MBS_UID = "mbs_uid";
	private final static String COL_IOI_IO_TYPE_IDX = "io_type_idx";
	private final static String COL_IOI_ORDER_QTY = "order_qty";
	private final static String COL_IOI_ORDER_VALUE = "order_value";
	
	
	boolean saveInvtOrderItem(InvtOrderItem _ioi) {
		DbColumn<InvtOrderItem>[] cols = new DbColumn[] { //
				DbColumn.of(COL_IOI_IO_UID, ColType.STRING, InvtOrderItem::getIoUid, 45), //
				DbColumn.of(COL_IOI_MBS_UID, ColType.STRING, InvtOrderItem::getMbsUid, 45), //
				DbColumn.of(COL_IOI_IO_TYPE_IDX, ColType.INT, InvtOrderItem::getIoTypeIdx), //
				DbColumn.of(COL_IOI_ORDER_QTY, ColType.DOUBLE, InvtOrderItem::getOrderQty), //
				DbColumn.of(COL_IOI_ORDER_VALUE, ColType.DOUBLE, InvtOrderItem::getOrderValue), //
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
			ioi.setMbsUid(_rs.getString(COL_IOI_MBS_UID));
			ioi.setIoType(InvtOrderType.get(_rs.getInt(COL_IOI_IO_TYPE_IDX)));
			ioi.setOrderQty(_rs.getDouble(COL_IOI_ORDER_QTY));
			ioi.setOrderValue(_rs.getDouble(COL_IOI_ORDER_VALUE));
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
}
