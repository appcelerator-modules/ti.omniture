package ti.modules.titanium.omniture;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.util.Log;
import org.appcelerator.titanium.util.TiConfig;
import org.appcelerator.titanium.TiApplication;

import com.omniture.android.*;

@Kroll.module(name = "Omniture", id = "ti.omniture")
public class OmnitureModule extends KrollModule {
	private static final String LCAT = "OmnitureModule";
	private static final boolean DBG = TiConfig.DEBUG;

	public OmnitureModule() {
		super();
	}
}