package ekp.data.service.invt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.event.Level;

import ekp.invt.WrhsBin;
import ekp.invt.WrhsLoc;
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
}
