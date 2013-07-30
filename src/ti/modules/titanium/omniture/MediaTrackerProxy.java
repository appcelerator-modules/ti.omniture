package ti.modules.titanium.omniture;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollPropertyChange;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.KrollProxyListener;
import org.appcelerator.kroll.KrollRuntime;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;
import org.appcelerator.titanium.util.TiConvert;

import com.adobe.adms.mediameasurement.ADMS_MediaMeasurement;

@Kroll.proxy
public class MediaTrackerProxy extends KrollProxy implements KrollProxyListener
{
	private static final String LCAT = "OmnitureModule";
	private static final boolean DBG = TiConfig.DEBUG;
	
	public MediaTrackerProxy() {
		this(null);
	}
	
	public MediaTrackerProxy(KrollDict params) {
		super();
		setModelListener(this);
		handleCreationDict(params);
	}
	
	@Override
	public void processProperties(KrollDict dict) {
		// Setting Omniture `vars` using reflection to avoid calling each setter
		// when properties are passed in on proxy creation
		for (String key : dict.keySet()) {
			String setter = Utils.setterNameFromPropertyName(key);
			try {
				Method method = this.getClass().getMethod(setter, Object.class);
				method.invoke(this, dict.get(key));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * KrollProxyListener methods
	 */
	
	@Override
	public void propertyChanged(String key, Object oldValue, Object newValue, KrollProxy proxy) {
		
	}

	@Override
	public void propertiesChanged(List<KrollPropertyChange> changes, KrollProxy proxy) {
		
	}

	@Override
	public void listenerAdded(String type, int count, KrollProxy proxy) {

	}

	@Override
	public void listenerRemoved(String type, int count, KrollProxy proxy) {
		
	}
	
	/*
	 * Tracking Methods
	 */
	
	@Kroll.method
	public void open(Object hm) {
		KrollDict args = Utils.krollDictFromHashMap(hm);
		
		Utils.checkRequired("mediaName", args);
		Utils.checkRequired("mediaLength", args);
		Utils.checkRequired("mediaPlayerName", args);
		
		String mediaName = args.getString("mediaName");
		Double mediaLength = args.getDouble("mediaLength");
		String mediaPlayerName = args.getString("mediaPlayerName");
		
		ADMS_MediaMeasurement.sharedInstance().open(mediaName, mediaLength, mediaPlayerName);
	}
	
	@Kroll.method
	public void play(Object hm) {
		KrollDict args = Utils.krollDictFromHashMap(hm);
		
		Utils.checkRequired("mediaName", args);
		Utils.checkRequired("mediaOffset", args);
		
		String mediaName = args.getString("mediaName");
		Double mediaOffset = args.getDouble("mediaOffset");
		
		ADMS_MediaMeasurement.sharedInstance().play(mediaName, mediaOffset);
	}
	
	@Kroll.method
	public void stop(Object hm) {
		KrollDict args = Utils.krollDictFromHashMap(hm);
		
		Utils.checkRequired("mediaName", args);
		Utils.checkRequired("mediaOffset", args);
		
		String mediaName = args.getString("mediaName");
		Double mediaOffset = args.getDouble("mediaOffset");
		
		ADMS_MediaMeasurement.sharedInstance().stop(mediaName, mediaOffset);
	}
	
	@Kroll.method
	public void close(Object hm) {
		KrollDict args = Utils.krollDictFromHashMap(hm);
		
		Utils.checkRequired("mediaName", args);

		String mediaName = args.getString("mediaName");
		
		ADMS_MediaMeasurement.sharedInstance().close(mediaName);
	}
	
	/*
	 * Properties
	 */
	
	// trackVars
	@Kroll.setProperty @Kroll.method
	public void setTrackVars(Object arg) {
		ADMS_MediaMeasurement.sharedInstance().trackVars = TiConvert.toString(arg);
	}

	@Kroll.getProperty @Kroll.method
	public Object getTrackVars() {
		String toReturn = ADMS_MediaMeasurement.sharedInstance().trackVars;
		// In the iOS module getTrackVars returns the string "None" if no
		// trackVars exist, doing the same thing here to maintain parity
		return Utils.stringOrNone(toReturn);
	}
	
	// trackEvents
	@Kroll.setProperty @Kroll.method
	public void setTrackEvents(Object arg) {
		ADMS_MediaMeasurement.sharedInstance().trackEvents = TiConvert.toString(arg);
	}

	@Kroll.getProperty @Kroll.method
	public Object getTrackEvents() {
		String toReturn = ADMS_MediaMeasurement.sharedInstance().trackEvents;
		// In the iOS module getTrackVars returns the string "None" if no
		// trackVars exist, doing the same thing here to maintain parity
		return Utils.stringOrNone(toReturn);
	}
	
	// channel
	@Kroll.setProperty @Kroll.method
	public void setChannel(Object arg) {
		ADMS_MediaMeasurement.sharedInstance().channel = TiConvert.toString(arg);
	}

	@Kroll.getProperty @Kroll.method
	public Object getChannel() {
		String toReturn = ADMS_MediaMeasurement.sharedInstance().channel;
		return Utils.stringOrUndefined(toReturn);
	}
	
	// completeCloseOffsetThreshold
	@Kroll.setProperty @Kroll.method
	public void setCompleteCloseOffsetThreshold(Object arg) {
		ADMS_MediaMeasurement.sharedInstance().completeCloseOffsetThreshold = TiConvert.toInt(arg);
	}

	@Kroll.getProperty @Kroll.method
	public int getCompleteCloseOffsetThreshold() {
		return ADMS_MediaMeasurement.sharedInstance().completeCloseOffsetThreshold;
	}
	
	// contextDataMapping
	@Kroll.setProperty @Kroll.method
	public void setContextDataMapping(Object arg) {
		if (!(arg instanceof HashMap)) {
			throw new IllegalArgumentException("`contextDataMapping` must be a <Dictionary> of key-value pairs");
		}
		ADMS_MediaMeasurement.sharedInstance().contextDataMapping = new Hashtable<String, Object>((HashMap) arg);
	}

	@Kroll.getProperty @Kroll.method
	public Object getContextDataMapping() {
		Hashtable<String, Object> contextDataMap = ADMS_MediaMeasurement.sharedInstance().contextDataMapping;
		if (contextDataMap == null || contextDataMap.size() == 0) {
			return KrollRuntime.UNDEFINED;
		}
		return new HashMap<String, Object>(contextDataMap);
	}
	
	
	// trackSeconds
	@Kroll.setProperty @Kroll.method
	public void setTrackSeconds(Object arg) {
		ADMS_MediaMeasurement.sharedInstance().trackSeconds = TiConvert.toInt(arg);
	}

	@Kroll.getProperty @Kroll.method
	public int getTrackSeconds() {
		return ADMS_MediaMeasurement.sharedInstance().trackSeconds;
	}
	
	// trackMilestones
	@Kroll.setProperty @Kroll.method
	public void setTrackMilestones(Object arg) {
		ADMS_MediaMeasurement.sharedInstance().trackMilestones = TiConvert.toString(arg);
	}

	@Kroll.getProperty @Kroll.method
	public Object getTrackMilestones() {
		String toReturn = ADMS_MediaMeasurement.sharedInstance().trackMilestones;
		return Utils.stringOrUndefined(toReturn);
	}
	
	// segmentByMilestones
	@Kroll.setProperty @Kroll.method
	public void setSegmentByMilestones(Object arg) {
		ADMS_MediaMeasurement.sharedInstance().segmentByMilestones = TiConvert.toBoolean(arg);
	}

	@Kroll.getProperty @Kroll.method
	public Boolean getSegmentByMilestones() {
		return ADMS_MediaMeasurement.sharedInstance().segmentByMilestones;
	}
	
	// trackOffsetMilestones
	@Kroll.setProperty @Kroll.method
	public void setTrackOffsetMilestones(Object arg) {
		ADMS_MediaMeasurement.sharedInstance().trackOffsetMilestones = TiConvert.toString(arg);
	}

	@Kroll.getProperty @Kroll.method
	public Object getTrackOffsetMilestones() {
		String toReturn = ADMS_MediaMeasurement.sharedInstance().trackOffsetMilestones;
		return Utils.stringOrUndefined(toReturn);
	}
	
	// segmentByOffsetMilestones
	@Kroll.setProperty @Kroll.method
	public void setSegmentByOffsetMilestones(Object arg) {
		ADMS_MediaMeasurement.sharedInstance().segmentByOffsetMilestones = TiConvert.toBoolean(arg);
	}

	@Kroll.getProperty @Kroll.method
	public Boolean getSegmentByOffsetMilestones() {
		return ADMS_MediaMeasurement.sharedInstance().segmentByOffsetMilestones;
	}
}
