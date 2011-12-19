package ti.modules.titanium.omniture;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.TiContext;
import org.appcelerator.titanium.util.Log;
import org.appcelerator.titanium.util.TiConfig;

import com.omniture.android.AppMeasurement;

@Kroll.proxy(creatableInModule = OmnitureModule.class)
public class SessionProxy extends KrollProxy {
	private static final String LCAT = "OmnitureModule";
	private static final boolean DBG = TiConfig.DEBUG;

	private AppMeasurement session;

	public SessionProxy(TiContext context) {
		super(context);
	}

	@Kroll.constant
	public static final String CUSTOM_LINK = "o";

	@Kroll.constant
	public static final String FILE_DOWNLOAD = "d";

	@Kroll.constant
	public static final String EXIT_LINK = "e";

	@Override
	public void handleCreationDict(KrollDict params) {
		super.handleCreationDict(params);

		Log.d(LCAT, "Creating an Omniture session...");

		try {
			session = new AppMeasurement(TiApplication.getInstance());
			session.account = params.get("account").toString();
			session.trackingServer = params.get("trackingServer").toString();
			if (params.containsKey("trackingServerSecure")) {
				session.trackingServerSecure = params.get("trackingServerSecure").toString();
			}
			session.debugTracking = (Boolean) params.get("debug");
		} catch (Exception e) {
			session = null;
			e.printStackTrace();
			Log.d(LCAT, "Omniture session was NOT created...");
			Log.d(LCAT, "Check the supplied account, tracking server and debug options...");
		}
	}

	@Kroll.method
	public void track(HashMap params) {
		if (session != null) {
			Iterator i = params.entrySet().iterator();
			HashMap<String, String> trackData = new HashMap<String, String>();

			while (i.hasNext()) {
				Map.Entry pairs = (Map.Entry) i.next();
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
	public void trackLink(HashMap params) {
		if (session != null) {
			String linkURL = params.get("url").toString();
			String linkType = params.get("type").toString();
			String linkName = params.get("name").toString();

			HashMap overrides = params.containsKey("overrides") ? (HashMap) params.get("overrides") : params;

			Iterator i = overrides.entrySet().iterator();
			HashMap<String, String> trackData = new HashMap<String, String>();

			while (i.hasNext()) {
				Map.Entry pairs = (Map.Entry) i.next();
				trackData.put(pairs.getKey().toString(), pairs.getValue().toString());
			}

			Log.d(LCAT, "Calling trackLink on current session...");
			session.trackLink(linkURL, linkType, linkName, trackData);
		} else {
			Log.d(LCAT, "Call of trackLink was unsucessful...");
			Log.d(LCAT, "You do not have a current Omniture session...");
		}
	}

}