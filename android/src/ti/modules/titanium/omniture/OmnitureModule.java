package ti.modules.titanium.omniture;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollFunction;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.util.TiConvert;

import android.location.Location;

import com.adobe.mobile.Analytics;
import com.adobe.mobile.AudienceManager;
import com.adobe.mobile.AudienceManager.AudienceManagerCallback;
import com.adobe.mobile.Config;
import com.adobe.mobile.Media;
import com.adobe.mobile.Media.MediaCallback;
import com.adobe.mobile.MediaSettings;
import com.adobe.mobile.MediaState;
import com.adobe.mobile.MobilePrivacyStatus;
import com.adobe.mobile.Target;
import com.adobe.mobile.Target.TargetCallback;
import com.adobe.mobile.TargetLocationRequest;
import com.adobe.mobile.Visitor;

@Kroll.module(name = "Omniture", id = "ti.omniture")
public class OmnitureModule
	extends KrollModule
{
	private static final String LCAT = "OmnitureModule";
	public OmnitureModule() {
		super();

		Config.setContext(getActivity().getApplicationContext());
		Log.d(LCAT, "OmnitureModule Loaded");
	}

	@Kroll.method
	public void keepLifecycleSessionAlive() {
		// iOS only?
	}

	@Kroll.method
	public void collectLifecycleData(HashMap<String, Object> contextData) {
		if (contextData == null) {
			Config.collectLifecycleData(getActivity());
		} else {
			Config.collectLifecycleData(getActivity(), contextData);
		}
	}

	@Kroll.method
	public void trackState(String state, HashMap<String, Object> contextData) {
		Analytics.trackState(state, contextData);
	}

	@Kroll.method
	public void trackAction(String state, HashMap<String, Object> contextData) {
		Analytics.trackAction(state, contextData);
	}
	
	@Kroll.method
	public void trackActionFromBackground(
			String state,
			HashMap<String, Object> contextData
	) {
		// NOTE: Not a type; the Android version of the native library doesn't
		// have a 'trackActionFromBackground' method. This is here to provide
		// parity with iOS.
		Analytics.trackAction(state, contextData);
	}

	@Kroll.method
	public void trackLocation(
					float latitude,
					float longitude,
					HashMap<String, Object> contextData
	) {
		Location loc = new Location("ti.omniture");
		loc.setLatitude(latitude);
		loc.setLongitude(longitude);
		Analytics.trackLocation(loc, contextData);
	}

	@Kroll.method
	public void trackLifetimeValueIncrease(
				float amount,
				HashMap<String, Object> contextData) {
		Analytics.trackLifetimeValueIncrease(BigDecimal.valueOf(amount), contextData);
	}

	@Kroll.method
	public void trackTimedActionStart(
					String action,
					HashMap<String, Object> contextData
	) {
		Analytics.trackTimedActionStart(action, contextData);
	}

	@Kroll.method
	public void trackTimedActionUpdate(
			String action,
			HashMap<String, Object> contextData
	) {
		Analytics.trackTimedActionUpdate(action, contextData);
	}

	@Kroll.method
	public void trackTimedActionEnd(
			String action,
			final KrollFunction callback
	) {
		Analytics.trackTimedActionEnd(
				action,
				new Analytics.TimedActionBlock<Boolean>()
				{
					@Override
					public Boolean call(
							long inAppDuration,
							long totalDuration,
							Map<String, Object> data
					) {
						if (callback == null) {
							return false;
						}
                        // TODO: The native code could add new values to 'data';
                        // we are missing out on this functionality for now.
						KrollDict callbackParams = new KrollDict();
						callbackParams.put("inAppDuration", inAppDuration);
						callbackParams.put("totalDuration", totalDuration);
						callbackParams.put("data", data);
						return TiConvert.toBoolean(
								callback.call(
										getKrollObject(),
										callbackParams));
					}
				}
		);
	}

	@Kroll.method
	public boolean trackTimedActionExists(String action) {
		return Analytics.trackingTimedActionExists(action);
	}

	@Kroll.method
	public void retrieveVisitorMarketingCloudID(KrollFunction callback) {
		if (callback == null) {
			return;
		}

		Object[] callbackParams = new Object[1];
		callbackParams[0] = Visitor.getMarketingCloudId();
		callback.callAsync(getKrollObject(), callbackParams);
    }

	@Kroll.method
	public void retrieveTrackingId(KrollFunction callback) {
		if (callback == null) {
			return;
		}

		Object[] callbackParams = new Object[1];
		callbackParams[0] = Analytics.getTrackingIdentifier();
		callback.callAsync(getKrollObject(), callbackParams);
    }

	@Kroll.method
	public void trackingSendQueuedHits() {
		Analytics.sendQueuedHits();
	}

	@Kroll.method
	public void trackingClearQueue() {
		Analytics.clearQueue();
	}
	
	@Kroll.method
	public MediaSettingsProxy createMediaSettings(
			HashMap<String, Object> args
	) {
		MediaSettings settings =
				Media.settingsWith(
						(String)args.get("name"),
						TiConvert.toDouble(args.get("length")),
						(String)args.get("playerName"),
						(String)args.get("playerID"));
		
		MediaSettingsProxy proxy = new MediaSettingsProxy(settings);
		// TODO: Will need to copy other parameters from args to proxy.
		return proxy;
	}

	@Kroll.method
	public MediaSettingsProxy createMediaAdSettings(
			HashMap<String, Object> args
	) {
		MediaSettings settings =
				Media.adSettingsWith(
						(String)args.get("name"),
						TiConvert.toDouble(args.get("length")),
						(String)args.get("playerName"),
						(String)args.get("parentName"),
						(String)args.get("parentPod"),
						TiConvert.toDouble(args.get("parentPod")),
						(String)args.get("CPM")
						);
		
		MediaSettingsProxy proxy = new MediaSettingsProxy(settings);
		// TODO: Will need to copy other parameters from args to proxy.
		return proxy;
	}

	@Kroll.method
	public void mediaOpen(
				MediaSettingsProxy mediaSettingsProxy, 
				final KrollFunction callback
	) {
		Media.open(mediaSettingsProxy.getSettings(), new MediaCallback<MediaState>() {
			@Override
			public void call(MediaState item) {
				if (callback == null) {
					return;
				}
				Object[] callbackParams = new Object[1];
				callbackParams[0] = new MediaStateProxy((MediaState)item);
				callback.call(getKrollObject(), callbackParams);
			} 
		});
	}

	@Kroll.method
	public void mediaClose(String name) {
	    Media.close(name);
    }

	@Kroll.method
	public void mediaPlay(String name, double offset) {
		Media.play(name, offset);
	}

	@Kroll.method
	public void mediaComplete(String name, double offset) {
		Media.complete(name, offset);
    }
	
	@Kroll.method
	public void mediaStop(String name, double offset) {
		Media.stop(name, offset);
    }
	
	@Kroll.method
	public void mediaClick(String name, double offset) {
		Media.click(name, offset);
    }

	@Kroll.method
	public void mediaTrack(String name, HashMap<String, Object> contextData) {
		Media.track(name, contextData);
    }
	
	@Kroll.method
	public void targetLoadRequest(
			TargetLocationRequestProxy requestProxy, 
			final KrollFunction callback
	) {
		Target.loadRequest(
				requestProxy.getRequest(),
				new TargetCallback<String>() {
			
				@Override
				public void call(String response) {
					if (callback == null) {
						return;
					}
					Object[] callbackParams = new Object[1];
					callbackParams[0] = response;
					callback.call(getKrollObject(), callbackParams);
				}
		});
	}

	@Kroll.method
	public TargetLocationRequestProxy createTargetLocationRequest(
											HashMap<String, Object> params
	) {
		return new TargetLocationRequestProxy(
				Target.createRequest(
						(String)params.get("name"), 
						(String)params.get("defaultContent"), 
						(HashMap<String, Object>)params.get("parameters"))
			);
	}

	@Kroll.method
	public TargetLocationRequestProxy createTargetOrderConfirmRequest(
											HashMap<String, Object> params
	) {
		return new TargetLocationRequestProxy(
				Target.createOrderConfirmRequest(
						(String)params.get("name"), 
						(String)params.get("orderId"), 
						(String)params.get("orderTotal"), 
						(String)params.get("productPurchasedId"), 
						(HashMap<String, Object>)params.get("parameters"))
			);
	}

	@Kroll.method
	public void targetClearCookies() {
		Target.clearCookies();
	}

	@Kroll.method
	public void setAudienceIds(String dpid, String dpuuid) {
		AudienceManager.setDpidAndDpuuid(dpid, dpuuid);
	}

	@Kroll.method
	public void audienceSignalWithData(
			HashMap<String, Object> data,
			final KrollFunction callback
	) {
		AudienceManager.signalWithData(
				data,
				new AudienceManagerCallback<Map<String,Object>>() {
					@Override
					public void call(Map<String, Object> response) {
						if (callback == null) {
							return;
						}
					}
				}
		);
	}

	@Kroll.method
	public void audienceReset() {
		AudienceManager.reset();
	}

	@Kroll.method
	public void visitorSyncIdentifiers(HashMap<String, Object>identifiers) {
		HashMap<String, String> nativeIds = new HashMap<String, String>();
		
		for(String key: identifiers.keySet()) {
			Object value = identifiers.get(key);
			if (value instanceof String) {
				nativeIds.put(key, (String)value);
			}
		}
		Visitor.syncIdentifiers(nativeIds);
	}

	@Kroll.getProperty
	public String getVersion() {
		return Config.getVersion();
	}
	
	@Kroll.getProperty
	public boolean getDebugLogging() {
		return Config.getDebugLogging();
	}
	
	@Kroll.setProperty
	public void setDebugLogging(boolean newValue) {
		Config.setDebugLogging(newValue);
	}
	
	@Kroll.getProperty
	public double getLifetimeValue() {
		return Config.getLifetimeValue().doubleValue();
	}

	@Kroll.getProperty
	public double getTrackingQueueSize() {
		return Analytics.getQueueSize();
	}
	
	@Kroll.getProperty
	public int getPrivacyStatus() {
		return Config.getPrivacyStatus().ordinal();
	}

	@Kroll.getProperty
	public HashMap<String, Object> getVisitorProfile() {
		return AudienceManager.getVisitorProfile();
	}

	@Kroll.getProperty
	public String getAudienceDpid() {
		return AudienceManager.getDpid();
	}

	@Kroll.getProperty
	public String getAudienceDpuuid() {
		return AudienceManager.getDpuuid();
	}

	@Kroll.constant
	public static final int PRIVACY_STATUS_OPT_IN =
							MobilePrivacyStatus
									.MOBILE_PRIVACY_STATUS_OPT_IN
									.ordinal();
	@Kroll.constant
	public static final int PRIVACY_STATUS_OPT_OUT =
							MobilePrivacyStatus
									.MOBILE_PRIVACY_STATUS_OPT_OUT
									.ordinal();
	@Kroll.constant
	public static final int PRIVACY_STATUS_UNKNOWN =
							MobilePrivacyStatus
									.MOBILE_PRIVACY_STATUS_UNKNOWN
									.ordinal();

	@Kroll.constant
	public static final String TARGET_PARAM_ORDER_ID =
							TargetLocationRequest.TARGET_PARAMETER_ORDER_ID;
	@Kroll.constant
	public static final String TARGET_PARAM_ORDER_TOTAL =
							TargetLocationRequest.TARGET_PARAMETER_ORDER_TOTAL;
	@Kroll.constant
	public static final String TARGET_PARAM_PRODUCT_PURCHASE_ID =
							TargetLocationRequest
										.TARGET_PARAMETER_PRODUCT_PURCHASE_ID;
	@Kroll.constant
	public static final String TARGET_PARAM_CATEGORY_ID =
							TargetLocationRequest
										.TARGET_PARAMETER_CATEGORY_ID;
	@Kroll.constant
	public static final String TARGET_PARAM_MBOX_3RD_PARTY_ID =
							TargetLocationRequest
										.TARGET_PARAMETER_MBOX_3RDPARTY_ID;
	@Kroll.constant
	public static final String TARGET_PARAM_MBOX_PAGE_VALUE =
							TargetLocationRequest
										.TARGET_PARAMETER_MBOX_PAGE_VALUE;
	@Kroll.constant
	public static final String TARGET_PARAM_MBOX_PC =
							TargetLocationRequest
										.TARGET_PARAMETER_MBOX_PC;
	@Kroll.constant
	public static final String TARGET_PARAM_MBOX_SESSION_ID =
							TargetLocationRequest
										.TARGET_PARAMETER_MBOX_SESSION_ID;
	@Kroll.constant
	public static final String TARGET_PARAM_MBOX_HOST =
							TargetLocationRequest.TARGET_PARAMETER_MBOX_HOST;
}