package ekp.data;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.AbstractEkpInitTest;
import ekp.TestLogMark;
import ekp.data.service.invt.query.InvtOrderItemQueryParam;
import ekp.data.service.invt.query.InvtOrderQueryParam;
import ekp.invt.InvtOrder;
import ekp.invt.InvtOrderItem;
import ekp.invt.type.InvtOrderType;
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

import static ekp.data.service.invt.query.InvtOrderQueryParam.*;
import static ekp.data.service.invt.query.InvtOrderItemQueryParam.*;
import static ekp.data.service.invt.query.MbsbStmtQueryParam.*;

public class InvtDataServiceTest extends AbstractEkpInitTest{
	private static InvtDataService dataService = DataServiceFactory.getInstance().getService(InvtDataService.class);
	private Logger log = LoggerFactory.getLogger(TestLogMark.class);
	
	@Test
	public void testSearchInvtOrder() {
		QueryOperation<InvtOrderQueryParam, InvtOrder> param = new QueryOperation<>();
		//
		param.appendCondition(value(APPLIER_NAME, equal,"Jason"));
		
		//
		param.appendCondition(value(B_OF_IOI$, equal, true));
		Map<InvtOrderQueryParam, QueryValue[]> existsDetailMap = new HashMap<>();
		existsDetailMap.put(B_OF_IOI$, new QueryValue[] {
				value(IO_TYPE_IDX, equal, InvtOrderType.I1.getIdx()),
		});
		
		param = dataService.searchInvtOrder(param, existsDetailMap);
		log.debug("param.getTotal(): {}", param.getTotal());
		List<InvtOrder> ioList = param.getQueryResult();
		log.debug("ioList.size(): {}", ioList.size());
		for(InvtOrder io: ioList) {
			log.debug("{}\t{}\t{}\t{}\t{}", io.getUid(),io.getApplierId(),io.getApplierName(), DateFormatUtil.transToTime(new Date(io.getApvTime())) , io.getRemark());
		}
	}
	
	@Test
//	@Ignore
	public void testSearchInvtOrderItem() {
		QueryOperation<InvtOrderItemQueryParam, InvtOrderItem> param = new QueryOperation<>();
		//
		param.appendCondition(value(IO_TYPE_IDX, equal, InvtOrderType.I1.getIdx()));
		//
		param.appendCondition(value(IO_APPLIER_NAME, equal, "Jason"));
		param.appendCondition(value(IO_REMARK, like, "%sample%"));
		//
		param.appendCondition(value(MBS_MANO, equal, "MANO1"));
		//
		param.appendCondition(value(MBS_MM_NAME, equal, "測試料號1"));
		
		//
		param.appendCondition(value(B_OF_MBSBS$, equal, true));
		Map<InvtOrderItemQueryParam, QueryValue[]> existsDetailMap = new HashMap<>();
		existsDetailMap.put(B_OF_MBSBS$, new QueryValue[] {
				value(MBSB_FLOW_TYPE_IDX, equal, MbsbFlowType.IN.getIdx()),
				value(POSTING_STATUS_IDX, equal, PostingStatus.POSTED.getIdx()),
//				POSTING_TIME("POSTING_TIME","Posting Time"), //
		});
		
		
		param = dataService.searchInvtOrderItem(param, existsDetailMap);
		log.debug("param.getTotal(): {}", param.getTotal());
		List<InvtOrderItem> ioiList = param.getQueryResult();
		log.debug("ioiList.size(): {}", ioiList.size());
		for(InvtOrderItem ioi: ioiList) {
			log.debug("{}\t{}\t{}\t{}\t{}\t{}", ioi.getUid(),ioi.getIoUid(),ioi.getMbsUid(),  ioi.getIoType(), ioi.getOrderQty(), ioi.getOrderValue());
		}
		

	}
}
