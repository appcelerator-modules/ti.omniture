package ti.modules.titanium.omniture;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiContext;
import org.appcelerator.titanium.util.Log;
import org.appcelerator.titanium.util.TiConfig;

import com.omniture.android.*;

@Kroll.module(name="Omniture", id="ti.omniture")
public class OmnitureModule extends KrollModule {
	private static final String LCAT = "OmnitureModule";
	private static final boolean DBG = TiConfig.DEBUG;
	private AppMeasurement session;
		
	@Kroll.method
	public void track(KrollDict params) {
		if (session != null) {
			Iterator i = params.entrySet().iterator();
			HashMap<String, String> trackData = new HashMap<String, String>();
			
			while (i.hasNext()) {
				Map.Entry pairs = (Map.Entry)i.next();
				trackData.put(pairs.getKey().toString(), pairs.getValue().toString());
			}
			
			Log.d(LCAT, "Calling track on current session...");
			session.track(trackData);
		} else {
			Log.d(LCAT, "Call of track was unsucessful...");
			Log.d(LCAT, "You do not have a current Omniture session...");
		}
	}
	
	@Kroll.method
	public void createSession(KrollDict params) {
		Log.d(LCAT, "Creating an Omniture session...");

		try {
			session = new AppMeasurement(getTiContext().getTiApp());
			session.account = params.get("account").toString();
			session.trackingServer = params.get("trackingServer").toString();
			session.debugTracking = (Boolean) params.get("debug");
		} catch (Exception e) {
			session = null;
			Log.d(LCAT, "Omniture session was NOT created...");
			Log.d(LCAT, "Check the supplied account, tracking server and debug options...");
		}
	}
	
	public OmnitureModule(TiContext tiContext) {
		super(tiContext);
	}	
}