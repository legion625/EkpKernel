package ekp.common;

import org.junit.Test;

import ekp.AbstractEkpInitTest;

public class SerialNoGenratorTest extends AbstractEkpInitTest {

	@Test
	public void testGenerateIOSN() {
		String iosn = SerialNoGenerator.generateIOSN();
		log.debug("iosn: {}", iosn);
	}

}
