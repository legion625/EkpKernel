package ekp.util;

import java.util.Calendar;

import legion.util.DateUtil;

public class EkpKernelDateUtil extends DateUtil {
	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
}
