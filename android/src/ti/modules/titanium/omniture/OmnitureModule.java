package ti.modules.titanium.omniture;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollFunction;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiApplication;
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

		Config.setContext(TiApplication.getInstance());
		Log.d(LCAT, "OmnitureModule Loaded");
	}

	private HashMap<String, Object> convert(
			@SuppressWarnings("rawtypes") HashMap data
	) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		for (Object key: data.keySet()) {
			if (key instanceof String) {
				result.put((String)key, data.get(key));
			}
		}
		return result;
	}
	
	@Kroll.method
	public void keepLifecycleSessionAlive() {
		// iOS only?
	}

	@Kroll.method
	public void collectLifecycleData(
			@SuppressWarnings("rawtypes") HashMap contextData
	) {
		if (contextData == null) {
			Config.collectLifecycleData(getActivity());
		} else {
			Config.collectLifecycleData(getActivity(), convert(contextData));
		}
	}

	@Kroll.method
	public void trackState(
			String state,
			@SuppressWarnings("rawtypes")
			@Kroll.argument(optional=true) HashMap contextData
	) {
		Analytics.trackState(state, convert(contextData));
	}

	@Kroll.method
	public void trackAction(
			String state,
			@SuppressWarnings("rawtypes")
			@Kroll.argument(optional=true) HashMap contextData
	) {
		Analytics.trackAction(state, convert(contextData));
	}
	
	@Kroll.method
	public void trackActionFromBackground(
			String state,
			@SuppressWarnings("rawtypes")
			@Kroll.argument(optional=true) HashMap contextData
	) {
		// NOTE: Not a type; the Android version of the native library doesn't
		// have a 'trackActionFromBackground' method. This is here to provide
		// parity with iOS.
		Analytics.trackAction(state, convert(contextData));
	}

	@Kroll.method
	public void trackLocation(
					float latitude,
					float longitude,
					@SuppressWarnings("rawtypes")
					@Kroll.argument(optional=true) HashMap contextData
	) {
		Location loc = new Location("ti.omniture");
		loc.setLatitude(latitude);
		loc.setLongitude(longitude);
		Analytics.trackLocation(loc, convert(contextData));
	}

	@Kroll.method
	public void trackLifetimeValueIncrease(
				float amount,
				@SuppressWarnings("rawtypes")
				@Kroll.argument(optional=true) HashMap contextData
	) {
		Analytics.trackLifetimeValueIncrease(
				BigDecimal.valueOf(amount),
				convert(contextData));
	}

	@Kroll.method
	public void trackTimedActionStart(
					String action,
					@SuppressWarnings("rawtypes")
					@Kroll.argument(optional=true) HashMap contextData
	) {
		Analytics.trackTimedActionStart(action, convert(contextData));
	}

	@Kroll.method
	public void trackTimedActionUpdate(
			String action,
			@SuppressWarnings("rawtypes")
			@Kroll.argument(optional=true) HashMap contextData
	) {
		Analytics.trackTimedActionUpdate(action, convert(contextData));
	}

	@Kroll.method
	public void trackTimedActionEnd(
			String action,
			@Kroll.argument(optional=true) final KrollFunction callback
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
						
						Object callbackResult = callback.call(
													getKrollObject(),
													callbackParams);
						if (callbackResult == null) {
							return false;
						}
						return TiConvert.toBoolean(callbackResult);
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
		Object[] callbackParams = new Object[1];
		callbackParams[0] = Visitor.getMarketingCloudId();
		callback.callAsync(getKrollObject(), callbackParams);
    }

	@Kroll.method
	public void retrieveTrackingId(KrollFunction callback) {
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
			@SuppressWarnings("rawtypes") HashMap props
	) {
		MediaSettings settings =
				Media.settingsWith(
						(String)props.get("name"),
						TiConvert.toDouble(props.get("length")),
						(String)props.get("playerName"),
						(String)props.get("playerID"));
		
		MediaSettingsProxy proxy = new MediaSettingsProxy(settings);

		proxy.addProps(props);
		
		return proxy;
	}

	@Kroll.method
	public MediaSettingsProxy createMediaAdSettings(
			@SuppressWarnings("rawtypes") HashMap props
	) {
		MediaSettings settings =
				Media.adSettingsWith(
						(String)props.get("name"),
						TiConvert.toDouble(props.get("length")),
						(String)props.get("playerName"),
						(String)props.get("parentName"),
						(String)props.get("parentPod"),
						TiConvert.toDouble(props.get("parentPodPosition")),
						(String)props.get("CPM")
						);
		
		MediaSettingsProxy proxy = new MediaSettingsProxy(settings);
		
		proxy.addProps(props);

		return proxy;
	}

	@Kroll.method
	public void mediaOpen(
				MediaSettingsProxy mediaSettingsProxy, 
				@Kroll.argument(optional=true) final KrollFunction callback
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
	public void mediaTrack(
			String name, 
			@SuppressWarnings("rawtypes")
			@Kroll.argument(optional=true) HashMap contextData
	) {
		Media.track(name, convert(contextData));
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
								@SuppressWarnings("rawtypes") HashMap props
	) {
		TargetLocationRequestProxy proxy =
				new TargetLocationRequestProxy(
					Target.createRequest(
						(String)props.get("name"), 
						(String)props.get("defaultContent"), 
						(HashMap<String, Object>)props.get("parameters"))
				);
		return proxy;
	}

	@Kroll.method
	public TargetLocationRequestProxy createTargetOrderConfirmRequest(
								@SuppressWarnings("rawtypes") HashMap props
	) {
		TargetLocationRequestProxy proxy =
				new TargetLocationRequestProxy(
					Target.createOrderConfirmRequest(
						(String)props.get("name"), 
						(String)props.get("orderId"), 
						(String)props.get("orderTotal"), 
						(String)props.get("productPurchasedId"), 
						(HashMap<String, Object>)props.get("parameters"))
				);
	
		Object value;
		
		value = props.get("defaultContent");
		if (value != null) {
			proxy.setDefaultContent(TiConvert.toString(value));
		}
		return proxy;
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
			@SuppressWarnings("rawtypes") HashMap data,
			@Kroll.argument(optional=true) final KrollFunction callback
	) {
		AudienceManager.signalWithData(
				convert(data),
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
	public void visitorSyncIdentifiers(
			@SuppressWarnings("rawtypes") HashMap identifiers
	) {
		HashMap<String, String> nativeIds = new HashMap<String, String>();
		
		for(Object key: identifiers.keySet()) {
			Object value = identifiers.get(key);
			if (key instanceof String && value instanceof String) {
				nativeIds.put((String)key, (String)value);
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