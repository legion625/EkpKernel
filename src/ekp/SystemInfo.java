package ekp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import legion.ISystemInfo;
import legion.LegionContext;
import legion.util.DataFO;
import legion.util.LogUtil;

public class SystemInfo implements ISystemInfo {
	private static Logger log = LoggerFactory.getLogger(SystemInfo.class);
	private String[] classAnalyseClasspath;
	// private AspectManager aspectManager;

	private Map<String, String> attrs = new HashMap<>();
	private String systemId = "";
	private String systemName = "";
	private String hostIp;

	// -------------------------------------------------------------------------------
	private static final SystemInfo INSTANCE = new SystemInfo();

	private SystemInfo() {
		LegionContext.getInstance().registerSystemInfo(this);
		try {
			hostIp = InetAddress.getLocalHost().getHostAddress();
		} catch (Throwable e) {
			LogUtil.log(e, Level.ERROR);
		}

		init("/system-conf.xml");
	}

	public static SystemInfo getInstance() {
		return INSTANCE;
	}

	// -------------------------------------------------------------------------------
	@Override
	public String getAttribute(String _key) {
		return attrs.get(_key);
	}

	@Override
	public Map<String, String> getAttributes() {
		return new HashMap<>(attrs);
	}

	@Override
	public String[] getClassAnalyseClasspath() {
		return classAnalyseClasspath;
	}

	@Override
	public String getName() {
		if (!DataFO.isEmptyString(systemName))
			return systemName;
		systemName = getAttribute("system.name");
		if (DataFO.isEmptyString(systemName))
			systemName = getAttribute("APP_NAME");
		if (DataFO.isEmptyString(systemName))
			systemName = hostIp;
		return systemName;
	}

	@Override
	public String getId() {
		if (!DataFO.isEmptyString(systemId))
			return systemId;

		systemId = getAttribute("system.id");
		if (DataFO.isEmptyString(systemId))
			systemId = getAttribute("APP_NAME");
		if (DataFO.isEmptyString(systemId))
			systemId = hostIp;
		return systemId;
	}

	@Override
	public String getHostIp() {
		return hostIp;
	}

	@Override
	public String getVersion() {
		return getAttribute("Version");
	}

	@Override
	public void putAttribute(String _key, String _attr) {
		attrs.put(_key, _attr);
	}

	@Override
	public void setClassAnalyseClasspath(String[] _classAnalyseClasspath) {
		classAnalyseClasspath = _classAnalyseClasspath;
	}

	// -------------------------------------------------------------------------------
	public void init(String _classResource) {
		try {
			URL configURL = getClass().getResource(_classResource);
			log.debug("configURL: {}", configURL);
			SAXBuilder saxBuilder = new SAXBuilder();
			Document doc = saxBuilder.build(configURL);
			Element rootApp = doc.getRootElement();
			putAttribute("APP_NAME", rootApp.getAttributeValue("name"));
			putAttribute("Version", rootApp.getAttributeValue("version"));
			List<Element> contextParams = rootApp.getChildren("context-param");
			if (contextParams != null && !contextParams.isEmpty())
				for (Element param : contextParams)
					putAttribute(param.getChildText("param-name"), param.getChildText("param-value"));
		} catch (JDOMException | IOException e) {
			log.error(e.getMessage(), e);
		}
	}

}
