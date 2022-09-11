package ekp.data.service.mbom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.event.Level;

import ekp.mbom.Part;
import ekp.mbom.PartAcquisition;
import ekp.mbom.type.PartAcquisitionType;
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

	// -------------------------------------------------------------------------------
	// --------------------------------PartAcquisition--------------------------------
	private final static String TB_MBOM_PART_ACQUISITION = "mbom_part_acq";
	private final static String COL_PA_PART_UID = "part_uid";
	private final static String COL_PA_PART_PIN = "part_pin";
	private final static String COL_PA_ID = "id";
	private final static String COL_PA_NAME = "name";
	private final static String COL_PA_TYPE_IDX = "type_idx";
	
	boolean savePartAcquisition(PartAcquisition _pa) {
		DbColumn<PartAcquisition>[] cols = new DbColumn[] {
				DbColumn.of(COL_PA_PART_UID, ColType.STRING, PartAcquisition::getPartUid), //
				DbColumn.of(COL_PA_PART_PIN, ColType.STRING, PartAcquisition::getPartPin), //
				DbColumn.of(COL_PA_ID, ColType.STRING, PartAcquisition::getId), //
				DbColumn.of(COL_PA_NAME, ColType.STRING, PartAcquisition::getName), //
				DbColumn.of(COL_PA_TYPE_IDX, ColType.INT, PartAcquisition::getTypeIdx), //
		};
		return saveObject(TB_MBOM_PART_ACQUISITION, cols, _pa);
	}

	boolean deletePartAcquisition(String _uid) {
		return deleteObject(TB_MBOM_PART_ACQUISITION, _uid);
	}

	private PartAcquisition parsePartAcquisition(ResultSet _rs) {
		PartAcquisition pa = null;
		try {
			String partUid = _rs.getString(COL_PA_PART_UID);
			String partPin = _rs.getString(COL_PA_PART_PIN);
			pa = PartAcquisition.getInstance(parseUid(_rs), partUid, partPin, parseObjectCreateTime(_rs), parseObjectUpdateTime(_rs));
			/* pack attributes */
			pa.setId(_rs.getString(COL_PA_ID));
			pa.setName(_rs.getString(COL_PA_NAME));
			pa.setType(PartAcquisitionType.get(_rs.getInt(COL_PA_TYPE_IDX)));
			return pa;
		}catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}
	
	PartAcquisition loadPartAcquisition(String _uid) {
		return loadObject(TB_MBOM_PART_ACQUISITION, _uid, this::parsePartAcquisition);
	}

	PartAcquisition loadPartAcquisitionById(String _id) {
		return loadObject(TB_MBOM_PART_ACQUISITION, COL_PA_ID, _id, this::parsePartAcquisition);
	}
	
	List<PartAcquisition> loadPartAcquisitionList(String _partUid){
		return loadObjectList(TB_MBOM_PART_ACQUISITION, COL_PA_PART_UID, _partUid, this::parsePartAcquisition);
	}
	
}

