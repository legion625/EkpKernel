package ekp.data;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.AbstractEkpInitTest;
import ekp.TestLogMark;
import ekp.data.service.invt.query.InvtOrderItemQueryParam;
import ekp.invt.InvtOrderItem;
import ekp.invt.type.InvtOrderType;
import legion.DataServiceFactory;
import legion.util.BeanUtil;
import legion.util.query.QueryOperation;

import static legion.util.query.QueryOperation.*;
import static legion.util.query.QueryOperation.CompareOp.*;

import java.util.List;

import static ekp.data.service.invt.query.InvtOrderItemQueryParam.*;

public class InvtDataServiceTest extends AbstractEkpInitTest{
	private static InvtDataService dataService = DataServiceFactory.getInstance().getService(InvtDataService.class);
	private Logger log = LoggerFactory.getLogger(TestLogMark.class);
	
	@Test
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
		
		
		param = dataService.searchInvtOrderItem(param);
		log.debug("param.getTotal(): {}", param.getTotal());
		List<InvtOrderItem> ioiList = param.getQueryResult();
		log.debug("ioiList.size()(): {}", ioiList.size());
		for(InvtOrderItem ioi: ioiList) {
			log.debug("{}\t{}\t{}\t{}\t{}\t{}", ioi.getUid(),ioi.getIoUid(),ioi.getMbsUid(),  ioi.getIoType(), ioi.getOrderQty(), ioi.getOrderValue());
		}
		

	}
}
