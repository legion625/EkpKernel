package ekp.data.service.mbom;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.event.Level;

import ekp.mbom.Part;
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
		DbColumn<Part>[] cols = new DbColumn[] {
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

}

