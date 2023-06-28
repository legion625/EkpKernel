package ekp.common;

import ekp.util.EkpKernelDateUtil;
import legion.DataServiceFactory;
import legion.data.ObjectSeqDataService;
import legion.util.DataFO;

public class SerialNoGenerator {

	public static String generateIOSN() {
		ObjectSeqDataService objSeqDataService = DataServiceFactory.getInstance().getService(ObjectSeqDataService.class);
		String yearStr =String.valueOf(EkpKernelDateUtil.getCurrentYear()).substring(2);  
		String idenfigyStr = "IOSN" + yearStr;
		String numStr = objSeqDataService.getSimpleSeq(idenfigyStr);
		String iosn = "IO"+DataFO.fillString(yearStr+"", 2, '0')+DataFO.fillString(numStr+"", 4, '0');
		return iosn;
	}
	
	public static String generateMISN() {
		ObjectSeqDataService objSeqDataService = DataServiceFactory.getInstance().getService(ObjectSeqDataService.class);
		String yearStr =String.valueOf(EkpKernelDateUtil.getCurrentYear()).substring(2);
		String idenfigyStr = "MISN" + yearStr;
		String numStr = objSeqDataService.getSimpleSeq(idenfigyStr);
		String misn = "MI"+DataFO.fillString(yearStr+"", 2, '0')+DataFO.fillString(numStr+"", 6, '0');
		return misn;
	}
}
