package ekp.data;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.AbstractEkpInitTest;
import ekp.TestLogMark;
import ekp.data.service.invt.query.InvtOrderItemQueryParam;
import ekp.data.service.invt.query.InvtOrderQueryParam;
import ekp.data.service.invt.query.MbsbStmtQueryParam;
import ekp.invt.InvtOrder;
import ekp.invt.InvtOrderItem;
import ekp.invt.MbsbStmt;
import ekp.invt.type.InvtOrderType;
import ekp.invt.type.MaterialInstAcqChannel;
import ekp.invt.type.MbsbFlowType;
import ekp.invt.type.PostingStatus;
import legion.DataServiceFactory;
import legion.util.BeanUtil;
import legion.util.DateFormatUtil;
import legion.util.DateUtil;
import legion.util.query.QueryOperation;

import static legion.util.query.QueryOperation.*;
import static legion.util.query.QueryOperation.CompareOp.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import static ekp.data.service.invt.query.InvtOrderQueryParam.*;
//import static ekp.data.service.invt.query.InvtOrderItemQueryParam.*;
//import static ekp.data.service.invt.query.MbsbStmtQueryParam.*;

public class InvtDataServiceTest extends AbstractEkpInitTest {
	private static InvtDataService dataService = DataServiceFactory.getInstance().getService(InvtDataService.class);
	private Logger log = LoggerFactory.getLogger(TestLogMark.class);

	@Test
	public void testSearchInvtOrder() {
		QueryOperation<InvtOrderQueryParam, InvtOrder> param = new QueryOperation<>();
		//
		param.appendCondition(value(InvtOrderQueryParam.APPLIER_NAME, equal, "Jason"));

		//
		param.appendCondition(value(InvtOrderQueryParam.B_of_IOI$, equal, true));
		Map<InvtOrderQueryParam, QueryValue[]> existsDetailMap = new HashMap<>();
		existsDetailMap.put(InvtOrderQueryParam.B_of_IOI$,
				new QueryValue[] { value(InvtOrderItemQueryParam.IO_TYPE_IDX, equal, InvtOrderType.I1.getIdx()), });
		//
		param.appendCondition(value(InvtOrderQueryParam.B_of_IOI_of_MBSBS$, equal, true));
		existsDetailMap.put(InvtOrderQueryParam.B_of_IOI_of_MBSBS$,
				new QueryValue[] { value(MbsbStmtQueryParam.MBSB_FLOW_TYPE_IDX, equal, MbsbFlowType.IN.getIdx()), });

		param = dataService.searchInvtOrder(param, existsDetailMap);
		log.debug("param.getTotal(): {}", param.getTotal());
		List<InvtOrder> ioList = param.getQueryResult();
		log.debug("ioList.size(): {}", ioList.size());
		for (InvtOrder io : ioList) {
			log.debug("{}\t{}\t{}\t{}\t{}", io.getUid(), io.getApplierId(), io.getApplierName(),
					DateFormatUtil.transToTime(new Date(io.getApvTime())), io.getRemark());
		}
	}

	@Test
	@Ignore
	public void testSearchInvtOrderItem() {
		QueryOperation<InvtOrderItemQueryParam, InvtOrderItem> param = new QueryOperation<>();
		//
		param.appendCondition(value(InvtOrderItemQueryParam.IO_TYPE_IDX, equal, InvtOrderType.I1.getIdx()));
		//
		param.appendCondition(value(InvtOrderItemQueryParam.IO_APPLIER_NAME, equal, "Jason"));
		param.appendCondition(value(InvtOrderItemQueryParam.IO_REMARK, like, "%sample%"));
		//
		param.appendCondition(value(InvtOrderItemQueryParam.MBS_MANO, equal, "MANO1"));
		//
		param.appendCondition(value(InvtOrderItemQueryParam.MBS_MM_NAME, equal, "測試料號1"));

		//
		param.appendCondition(value(InvtOrderItemQueryParam.B_of_MBSBS$, equal, true));
		Map<InvtOrderItemQueryParam, QueryValue[]> existsDetailMap = new HashMap<>();
		existsDetailMap.put(InvtOrderItemQueryParam.B_of_MBSBS$,
				new QueryValue[] { value(MbsbStmtQueryParam.MBSB_FLOW_TYPE_IDX, equal, MbsbFlowType.IN.getIdx()),
						value(MbsbStmtQueryParam.POSTING_STATUS_IDX, equal, PostingStatus.POSTED.getIdx()),
//				POSTING_TIME("POSTING_TIME","Posting Time"), //
						//
						value(MbsbStmtQueryParam.MBS_MANO, equal, "MANO1"), //
						//
						value(MbsbStmtQueryParam.MISN, equal, "MI23000002"), //
						value(MbsbStmtQueryParam.MIAC_IDX, equal, MaterialInstAcqChannel.PURCHASING.getIdx()), });

		param = dataService.searchInvtOrderItem(param, existsDetailMap);
		log.debug("param.getTotal(): {}", param.getTotal());
		List<InvtOrderItem> ioiList = param.getQueryResult();
		log.debug("ioiList.size(): {}", ioiList.size());
		for (InvtOrderItem ioi : ioiList) {
			log.debug("{}\t{}\t{}\t{}\t{}\t{}", ioi.getUid(), ioi.getIoUid(), ioi.getMbsUid(), ioi.getIoType(),
					ioi.getOrderQty(), ioi.getOrderValue());
		}

	}

	@Test
	@Ignore
	public void testSearchMbsbStmt() {
		QueryOperation<MbsbStmtQueryParam, MbsbStmt> param = new QueryOperation<>();
		//
		param.appendCondition(value(MbsbStmtQueryParam.MBSB_FLOW_TYPE_IDX, equal, MbsbFlowType.IN.getIdx()));
		param.appendCondition(value(MbsbStmtQueryParam.POSTING_STATUS_IDX, equal, PostingStatus.POSTED.getIdx()));
		//
		//
		param.appendCondition(value(MbsbStmtQueryParam.MBS_MANO, equal, "MANO1"));
		//
		param.appendCondition(value(MbsbStmtQueryParam.MISN, equal, "MI23000002"));
		param.appendCondition(value(MbsbStmtQueryParam.MIAC_IDX, equal, MaterialInstAcqChannel.PURCHASING.getIdx()));

		param = dataService.searchMbsbStmt(param);
		log.debug("param.getTotal(): {}", param.getTotal());
		List<MbsbStmt> mbsbsList = param.getQueryResult();
		log.debug("mbsbsList.size(): {}", mbsbsList.size());
		for (MbsbStmt mbsbs : mbsbsList) {
			log.debug("{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}", mbsbs.getUid(), mbsbs.getMbsbUid(), mbsbs.getIoiUid(),
					mbsbs.getMbsbFlowType(), mbsbs.getStmtQty(), mbsbs.getStmtValue(), mbsbs.getPostingStatus(),
					mbsbs.getPostingTime());
		}

	}
}
