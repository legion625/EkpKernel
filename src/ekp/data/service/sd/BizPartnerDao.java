package ekp.data.service.sd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.event.Level;

import ekp.sd.BizPartner;
import legion.data.service.AbstractMySqlDao;
import legion.data.service.AbstractMySqlDao.ColType;
import legion.util.LogUtil;

public class BizPartnerDao extends AbstractMySqlDao {

	protected BizPartnerDao(String source) {
		super(source);
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------BizPartner-----------------------------------
	private final static String TB_BIZ_PARTNER = "sd_biz_partner";
	private final static String COL_BP_BPSN = "bpsn";
	private final static String COL_BP_NAME = "name";
	private final static String COL_BP_BAN = "ban";

	boolean saveBizPartner(BizPartner _bp) {
		DbColumn<BizPartner>[] cols = new DbColumn[] { //
				DbColumn.of(COL_BP_BPSN, ColType.STRING, BizPartner::getBpsn, 45), //
				DbColumn.of(COL_BP_NAME, ColType.STRING, BizPartner::getName, 45), //
				DbColumn.of(COL_BP_BAN, ColType.STRING, BizPartner::getBan, 45), //
		};
		return saveObject(TB_BIZ_PARTNER, cols, _bp);
	}

	boolean deleteBizPartner(String _uid) {
		return deleteObject(TB_BIZ_PARTNER, _uid);
	}

	private BizPartner parseBizPartner(ResultSet _rs) {
		try {
			BizPartner bp = BizPartner.getInstance(parseUid(_rs), parseObjectCreateTime(_rs),
					parseObjectUpdateTime(_rs));
			/* pack attributes */
			bp.setBpsn(_rs.getString(COL_BP_BPSN));
			bp.setName(_rs.getString(COL_BP_NAME));
			bp.setBan(_rs.getString(COL_BP_BAN));
			return bp;
		} catch (SQLException e) {
			LogUtil.log(log, e, Level.ERROR);
			return null;
		}
	}

	BizPartner loadBizPartner(String _uid) {
		return loadObject(TB_BIZ_PARTNER, _uid, this::parseBizPartner);
	}

	BizPartner loadBizPartnerByBpsn(String _bpsn) {
		return loadObject(TB_BIZ_PARTNER, COL_BP_BPSN, _bpsn, this::parseBizPartner);
	}

	List<BizPartner> loadBizPartnerList() {
		return loadObjectList(TB_BIZ_PARTNER, this::parseBizPartner);
	}
}
