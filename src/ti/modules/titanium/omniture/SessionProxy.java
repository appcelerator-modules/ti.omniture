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
			session.account = params.getString("account");
			session.trackingServer = params.getString("trackingServer");
			if (params.containsKey("trackingServerSecure")) {
				session.trackingServerSecure = params.getString("trackingServerSecure");
			}
			session.debugTracking = params.optBoolean("debug", false);
		} catch (Exception e) {
			session = null;
			e.printStackTrace();
			Log.d(LCAT, "Omniture session was NOT created...");
			Log.d(LCAT, "Check the supplied account, tracking server and debug options...");
		}
	}

	@Kroll.method
	public void track(HashMap raw) {
		try {
			if (session != null) {
				KrollDict params = new KrollDict(raw);
				Iterator i = params.entrySet().iterator();
				HashMap<String, String> trackData = new HashMap<String, String>();

				while (i.hasNext()) {
					Map.Entry pairs = (Map.Entry) i.next();
					Object key = pairs.getKey();
					Object value = pairs.getValue();
					if (key != null && value != null) {
						trackData.put(key.toString(), value.toString());
					}
				}

				Log.d(LCAT, "Calling track on current session...");
				session.track(trackData);
			} else {
				Log.d(LCAT, "Call of track was unsucessful...");
				Log.d(LCAT, "You do not have a current Omniture session...");
			}
		} catch (Exception e) {
			Log.d(LCAT, "track failed...");
			e.printStackTrace();
		}
	}

	@Kroll.method
	public void trackLink(HashMap raw) {
		try {
			if (session != null) {
				KrollDict params = new KrollDict(raw);
				String linkURL = params.getString("url");
				String linkType = params.getString("type");
				String linkName = params.getString("name");

				KrollDict overrides = params.containsKey("overrides") ? params.getKrollDict("overrides") : params;

				Iterator i = overrides.entrySet().iterator();
				HashMap<String, String> trackData = new HashMap<String, String>();

				while (i.hasNext()) {
					Map.Entry pairs = (Map.Entry) i.next();
					Object key = pairs.getKey();
					Object value = pairs.getValue();
					if (key != null && value != null) {
						trackData.put(key.toString(), value.toString());
					}
				}

				Log.d(LCAT, "Calling trackLink on current session...");
				session.trackLink(linkURL, linkType, linkName, trackData);
			} else {
				Log.d(LCAT, "Call of trackLink was unsucessful...");
				Log.d(LCAT, "You do not have a current Omniture session...");
			}
		} catch (Exception e) {
			Log.d(LCAT, "trackLink failed...");
			e.printStackTrace();
		}
	}

}