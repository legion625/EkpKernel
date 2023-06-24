package ekp.data.service.invt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.event.Level;

import ekp.invt.MaterialInst;
import ekp.invt.MaterialMaster;
import ekp.invt.type.MaterialInstAcqChannel;
import ekp.mbom.Part;
import ekp.mbom.type.PartUnit;
import legion.data.service.AbstractMySqlDao;
import legion.data.service.AbstractMySqlDao.ColType;
import legion.data.service.AbstractMySqlDao.DbColumn;
import legion.util.LogUtil;

public class MaterialDao extends AbstractMySqlDao {

	protected MaterialDao(String source) {
		super(source);
	}

	// -------------------------------------------------------------------------------
	// --------------------------------MaterialMaster---------------------------------
	private final static String TB_MATERIAL_MASTER = "invt_material_master";
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
	
	// -------------------------------------------------------------------------------
	// ---------------------------------MaterialInst----------------------------------
	private final static String TB_MATERIAL_INST = "invt_material_inst";
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

}
