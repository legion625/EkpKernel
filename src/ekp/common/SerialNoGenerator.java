package ekp.common;

import ekp.util.EkpKernelDateUtil;
import legion.DataServiceFactory;
import legion.data.ObjectSeqDataService;
import legion.util.DataFO;

public  class SerialNoGenerator {

	public synchronized static String generateIOSN() {
		ObjectSeqDataService objSeqDataService = DataServiceFactory.getInstance().getService(ObjectSeqDataService.class);
		String yearStr =String.valueOf(EkpKernelDateUtil.getCurrentYear()).substring(2);  
		String idenfigyStr = "IOSN" + yearStr;
		String numStr = objSeqDataService.getSimpleSeq(idenfigyStr);
		String iosn = "IO"+DataFO.fillString(yearStr+"", 2, '0')+DataFO.fillString(numStr+"", 4, '0');
		return iosn;
	}
	
	public synchronized static String generateMISN() {
		ObjectSeqDataService objSeqDataService = DataServiceFactory.getInstance().getService(ObjectSeqDataService.class);
		String yearStr =String.valueOf(EkpKernelDateUtil.getCurrentYear()).substring(2);
		String idenfigyStr = "MISN" + yearStr;
		String numStr = objSeqDataService.getSimpleSeq(idenfigyStr);
		String misn = "MI"+DataFO.fillString(yearStr+"", 2, '0')+DataFO.fillString(numStr+"", 6, '0');
		return misn;
	}

	public synchronized static String generatePuNo() {
		ObjectSeqDataService objSeqDataService = DataServiceFactory.getInstance()
				.getService(ObjectSeqDataService.class);
		String yearStr = String.valueOf(EkpKernelDateUtil.getCurrentYear()).substring(2);
		String idenfigyStr = "PuNo" + yearStr;
		String numStr = objSeqDataService.getSimpleSeq(idenfigyStr);
		String puNo = "PU" + DataFO.fillString(yearStr + "", 2, '0') + DataFO.fillString(numStr + "", 6, '0');
		return puNo;
	}

	public synchronized static String generateWoNo() {
		ObjectSeqDataService objSeqDataService = DataServiceFactory.getInstance()
				.getService(ObjectSeqDataService.class);
		String yearStr = String.valueOf(EkpKernelDateUtil.getCurrentYear()).substring(2);
		String idenfigyStr = "WoNo" + yearStr;
		String numStr = objSeqDataService.getSimpleSeq(idenfigyStr);
		String woNo = "WO" + DataFO.fillString(yearStr + "", 2, '0') + DataFO.fillString(numStr + "", 6, '0');
		return woNo;
	}

	public synchronized static String generateSOSN() {
		ObjectSeqDataService objSeqDataService = DataServiceFactory.getInstance()
				.getService(ObjectSeqDataService.class);
		String yearStr = String.valueOf(EkpKernelDateUtil.getCurrentYear()).substring(2);
		String idenfigyStr = "SOSN" + yearStr;
		String numStr = objSeqDataService.getSimpleSeq(idenfigyStr);
		String sosn = "SO" + DataFO.fillString(yearStr + "", 2, '0') + DataFO.fillString(numStr + "", 6, '0');
		return sosn;
	}
	
	public synchronized static String generateBPSN() {
		ObjectSeqDataService objSeqDataService = DataServiceFactory.getInstance()
				.getService(ObjectSeqDataService.class);
		String yearStr = String.valueOf(EkpKernelDateUtil.getCurrentYear()).substring(2);
		String idenfigyStr = "BPSN" + yearStr;
		String numStr = objSeqDataService.getSimpleSeq(idenfigyStr);
		String bpsn = "BP" + DataFO.fillString(yearStr + "", 2, '0') + DataFO.fillString(numStr + "", 2, '0');
		return bpsn;
	}
}
