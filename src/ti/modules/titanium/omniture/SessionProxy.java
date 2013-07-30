/**
 * Appcelerator Titanium Mobile Modules
 * Copyright (c) 2010-2013 by Appcelerator, Inc. All Rights Reserved.
 * Proprietary and Confidential - This source code is not for redistribution
 */

package ti.modules.titanium.omniture;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.KrollProxyListener;
import org.appcelerator.kroll.KrollPropertyChange;
import org.appcelerator.kroll.KrollRuntime;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.kroll.common.Log;
import android.app.Activity;

import com.adobe.adms.measurement.ADMS_Measurement;

// propertyAccessors must be set here for all of the variables 
// so `propertyChanged` will be called when they are changed
@Kroll.proxy(propertyAccessors = {
	//  list1-list3;                  
	"list1", "list2", "list3",
	//  hier1-heir5;
	"hier1", "hier2", "hier3", "hier4", "hier5",
	//  prop1-prop75;
	"prop1", "prop2", "prop3", "prop4", "prop5", "prop6", "prop7", "prop8", "prop9", "prop10", "prop11", "prop12", "prop13", "prop14", "prop15", 
	"prop16", "prop17", "prop18", "prop19", "prop20", "prop21", "prop22", "prop23", "prop24", "prop25", "prop26", "prop27", "prop28", "prop29", 
	"prop30", "prop31", "prop32", "prop33", "prop34", "prop35", "prop36", "prop37", "prop38", "prop39", "prop40", "prop41", "prop42", "prop43", 
	"prop44", "prop45", "prop46", "prop47", "prop48", "prop49", "prop50", "prop51", "prop52", "prop53", "prop54", "prop55", "prop56", "prop57", 
	"prop58", "prop59", "prop60", "prop61", "prop62", "prop63", "prop64", "prop65", "prop66", "prop67", "prop68", "prop69", "prop70", "prop71", 
	"prop72", "prop73", "prop74", "prop75",
	//  eVar1-eVar75;
	"eVar1", "eVar2", "eVar3", "eVar4", "eVar5", "eVar6", "eVar7", "eVar8", "eVar9", "eVar10", "eVar11", "eVar12", "eVar13", "eVar14", "eVar15", 
	"eVar16", "eVar17", "eVar18", "eVar19", "eVar20", "eVar21", "eVar22", "eVar23", "eVar24", "eVar25", "eVar26", "eVar27", "eVar28", "eVar29", 
	"eVar30", "eVar31", "eVar32", "eVar33", "eVar34", "eVar35", "eVar36", "eVar37", "eVar38", "eVar39", "eVar40", "eVar41", "eVar42", "eVar43", 
	"eVar44", "eVar45", "eVar46", "eVar47", "eVar48", "eVar49", "eVar50", "eVar51", "eVar52", "eVar53", "eVar54", "eVar55", "eVar56", "eVar57", 
	"eVar58", "eVar59", "eVar60", "eVar61", "eVar62", "eVar63", "eVar64", "eVar65", "eVar66", "eVar67", "eVar68", "eVar69", "eVar70", "eVar71", 
	"eVar72", "eVar73", "eVar74", "eVar75"
})
public class SessionProxy extends KrollProxy implements KrollProxyListener 
{
	private static final String LCAT = "OmnitureModule";
	
	private static ArrayList<String> numberedKeysInProxy = new ArrayList<String>();
	
	public SessionProxy() {
		this(null);
	}
	
	public SessionProxy(KrollDict params) {
		super();
		// To listen for property changes on a proxy we must
		// - implement the `KrollProxyListener` methods
		// - call `setModelListener`
		// - list the properties that we want to listen for in @Kroll.proxy `propertyAccessors`
		setModelListener(this);
		// Apply passed in properties to proxy 
		handleCreationDict(params);
		
		String reportSuiteIDs = null;
		String trackingServer = null;
		if (params != null) {
			reportSuiteIDs = params.getString("reportSuiteIDs");
			trackingServer = params.getString("trackingServer");
		}

		if (reportSuiteIDs == null || trackingServer == null) {
			Log.e(LCAT,	"`reportSuiteIDs` and `trackingServer` are required when calling startSession");
		}

		// sharedInstance needs an activity passed to it at least once
		ADMS_Measurement.sharedInstance(TiApplication.getAppRootOrCurrentActivity());
	}
	
	/*
	 * KrollProxyListener methods
	 */
	
	@Override
	public void listenerAdded(String type, int count, KrollProxy proxy) {

	}
	
	@Override
	public void listenerRemoved(String type, int count, KrollProxy proxy) {
		
	}
	
	@Override
	public void propertiesChanged(List<KrollPropertyChange> changes, KrollProxy proxy) {
		
	}
	
	@Override
	public void processProperties(KrollDict dict) {
		// Setting Omniture `vars` using reflection to avoid calling each setter
		// when properties are passed in on proxy creation
		for (String key : dict.keySet()) {
			Object val = dict.get(key);
			if (!setNumberedProperty(key, null, val, this)) {
				String setter = Utils.setterNameFromPropertyName(key);
				try {
					Method method = this.getClass().getMethod(setter, Object.class);
					method.invoke(this, val);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void propertyChanged(String key, Object oldValue, Object newValue, KrollProxy proxy) {
		setNumberedProperty(key, oldValue, newValue, proxy);
	}
	
	/*
	 * Private Method(s)
	 */
	
	private Boolean setNumberedProperty(String key, Object oldValue, Object newValue, KrollProxy proxy) {
		// If the 'modelListener' property has been set for this proxy then this method is called
		// whenever a proxy property value is updated. Note that this method is called whenever the
		// setter is called, so it will get called even if the value of the property has not changed.
		if ((oldValue == newValue) || ((oldValue != null) && oldValue.equals(newValue))) {
    		return false;
    	}
        
		// Special handling for numbered properties
		//    list1-list3;         
		//    hier1-heir5;         
		//    prop1-prop75;
		//    eVar1-eVar75;
		// These numbered props will be set on the session
		if (key.startsWith("list")) {
			String prefix = "list";
			int num = TiConvert.toInt(key.substring(prefix.length()));
			ADMS_Measurement.sharedInstance().setListVar(num, TiConvert.toString(newValue));
			proxy.setProperty(key, ADMS_Measurement.sharedInstance().getListVar(num));
			// Saving keys so they can be easily removed later when clearing vars
			numberedKeysInProxy.add(key);
			return true;
		}
		if (key.startsWith("hier")) {
			String prefix = "hier";
			int num = TiConvert.toInt(key.substring(prefix.length()));
			ADMS_Measurement.sharedInstance().setHier(num, TiConvert.toString(newValue));
			
			proxy.setProperty(key, ADMS_Measurement.sharedInstance().getHier(num));
			numberedKeysInProxy.add(key);
			return true;
		}
		if (key.startsWith("prop")) {
			String prefix = "prop";
			int num = TiConvert.toInt(key.substring(prefix.length()));
			ADMS_Measurement.sharedInstance().setProp(num, TiConvert.toString(newValue));
			
			proxy.setProperty(key, ADMS_Measurement.sharedInstance().getProp(num));
			numberedKeysInProxy.add(key);
			return true;
		}
		if (key.startsWith("eVar")) {
			String prefix = "eVar";
			int num = TiConvert.toInt(key.substring(prefix.length()));
			ADMS_Measurement.sharedInstance().setEvar(num, TiConvert.toString(newValue));
			
			proxy.setProperty(key, ADMS_Measurement.sharedInstance().getEvar(num));
			numberedKeysInProxy.add(key);
			return true;
		}
		// Not a key that we care about
		return false;
	}	
	
	/*
	 * Constants
	 */
	
	@Kroll.constant
	public static final String LINK_TYPE_CUSTOM = "o";

	@Kroll.constant
	public static final String LINK_TYPE_FILE_DOWNLOAD = "d";

	@Kroll.constant
	public static final String LINK_TYPE_EXIT = "e";

	/*
	 * Configuration Methods
	 */
	
	@Kroll.method
	public void setOnline() {
		ADMS_Measurement.sharedInstance().setOnline();
	}

	@Kroll.method
	public void setOffline() {
		ADMS_Measurement.sharedInstance().setOffline();
	}
	
	/*
	 * Tracking Methods
	 * - Simple Tracking Methods
	 */
	
	@Kroll.method
	public void trackAppState(Object hm) {
		KrollDict args = Utils.krollDictFromHashMap(hm);
		
		Utils.checkRequired("appState", args);

		String appState = args.getString("appState");
		KrollDict cData = args.getKrollDict("contextData");

		Hashtable<String, Object> contextData = (cData == null) ? new Hashtable<String, Object>() : new Hashtable<String, Object>(cData);

		ADMS_Measurement measurement = ADMS_Measurement.sharedInstance();
		measurement.trackAppState(appState, contextData);
	}

	@Kroll.method
	public void trackEvents(Object hm) {
		KrollDict args = Utils.krollDictFromHashMap(hm);
		
		Utils.checkRequired("eventNames", args);

		String eventNames = args.getString("eventNames");
		KrollDict cData = args.getKrollDict("contextData");

		Hashtable<String, Object> contextData = (cData == null) ? new Hashtable<String, Object>() : new Hashtable<String, Object>(cData);

		ADMS_Measurement measurement = ADMS_Measurement.sharedInstance();
		measurement.trackEvents(eventNames, contextData);
	}

	/*
	 * - Advanced Tracking Methods
	 */

	@Kroll.method
	public void track(Object hm) {
		KrollDict args = Utils.krollDictFromHashMap(hm);

		KrollDict cData = args.getKrollDict("contextData");
		KrollDict vars = args.getKrollDict("variables");

		Hashtable<String, Object> contextData = (cData == null) ? new Hashtable<String, Object>() : new Hashtable<String, Object>(cData);
		Hashtable<String, Object> variables = (vars == null) ? new Hashtable<String, Object>() : new Hashtable<String, Object>(vars);

		ADMS_Measurement measurement = ADMS_Measurement.sharedInstance();
		measurement.track(contextData, variables);
	}

	@Kroll.method
	public void trackLink(Object hm) {
		KrollDict args = Utils.krollDictFromHashMap(hm);
		
		Utils.checkRequired("linkURL", args);
		Utils.checkRequired("linkType", args);

		String linkURL = args.getString("linkURL");
		String linkType = args.getString("linkType");
		String linkName = args.getString("linkName");
		KrollDict cData = args.getKrollDict("contextData");
		KrollDict vars = args.getKrollDict("variables");

		Hashtable<String, Object> contextData = (cData == null) ? new Hashtable<String, Object>() : new Hashtable<String, Object>(cData);
		Hashtable<String, Object> variables = (vars == null) ? new Hashtable<String, Object>() : new Hashtable<String, Object>(vars);

		ADMS_Measurement measurement = ADMS_Measurement.sharedInstance();
		measurement.trackLink(linkURL, linkType, linkName, contextData, variables);
	}

	@Kroll.method
	public void trackLight(Object hm) {
		KrollDict args = Utils.krollDictFromHashMap(hm);
		
		Utils.checkRequired("profileID", args);

		String profileID = args.getString("profileID");
		// Passing 0 seems to produce the same results as passing nil instead of numbers in the iOS module
		int storeForSeconds = args.containsKey("storeForSeconds") ? args.getInt("storeForSeconds") : 0;
		int incrementBy = args.containsKey("incrementBy") ? args.getInt("incrementBy") : 0;
		KrollDict cData = args.getKrollDict("contextData");
		KrollDict vars = args.getKrollDict("variables");

		Hashtable<String, Object> contextData = (cData == null) ? new Hashtable<String, Object>() : new Hashtable<String, Object>(cData);
		Hashtable<String, Object> variables = (vars == null) ? new Hashtable<String, Object>() : new Hashtable<String, Object>(vars);

		ADMS_Measurement measurement = ADMS_Measurement.sharedInstance();
		measurement.trackLight(profileID, storeForSeconds, incrementBy, contextData, variables);
	}

	@Kroll.method
	public void clearVars() {
		ADMS_Measurement.sharedInstance().clearVars();
		for (String s : numberedKeysInProxy) {
			setProperty(s, KrollRuntime.UNDEFINED);
		}
		
		numberedKeysInProxy.clear();
	}

	@Kroll.method
	public int trackingQueueSize() {
		return ADMS_Measurement.sharedInstance().getTrackingQueueSize();
	}

	@Kroll.method
	public void clearTrackingQueue() {
		ADMS_Measurement.sharedInstance().clearTrackingQueue();
	}
	
	@Kroll.method
	public void startActivity() {
		Activity activity = TiApplication.getAppRootOrCurrentActivity();
		ADMS_Measurement.sharedInstance(activity).startActivity(activity);
	}
	
	@Kroll.method
	public void stopActivity() {
		ADMS_Measurement.sharedInstance().stopActivity();
	}
	
	/*
	 * Properties
	 */

	// reportSuiteIDs
	@Kroll.setProperty @Kroll.method
	public void setReportSuiteIDs(Object arg) {
		ADMS_Measurement.sharedInstance().setReportSuiteIDs(TiConvert.toString(arg));
	}

	@Kroll.getProperty @Kroll.method
	public Object getReportSuiteIDs() {
		String toReturn = ADMS_Measurement.sharedInstance().getReportSuiteIDs();
		return Utils.stringOrUndefined(toReturn);
	}

	// trackingServer
	@Kroll.setProperty @Kroll.method
	public void setTrackingServer(Object arg) {
		ADMS_Measurement.sharedInstance().setTrackingServer(TiConvert.toString(arg));
	}

	@Kroll.getProperty @Kroll.method
	public Object getTrackingServer() {
		String toReturn = ADMS_Measurement.sharedInstance().getTrackingServer();
		return Utils.stringOrUndefined(toReturn);
	}

	// visitorID
	@Kroll.setProperty @Kroll.method
	public void setVisitorID(Object arg) {
		ADMS_Measurement.sharedInstance().setVisitorID(TiConvert.toString(arg));
	}

	@Kroll.getProperty @Kroll.method
	public Object getVisitorID() {
		String toReturn = ADMS_Measurement.sharedInstance().getVisitorID();
		return Utils.stringOrUndefined(toReturn);
	}

	// charSet
	@Kroll.setProperty @Kroll.method
	public void setCharSet(Object arg) {
		ADMS_Measurement.sharedInstance().setCharSet(TiConvert.toString(arg));
	}

	@Kroll.getProperty @Kroll.method
	public Object getCharSet() {
		String toReturn = ADMS_Measurement.sharedInstance().getCharSet(); 
		return Utils.stringOrUndefined(toReturn);
	}

	// currencyCode
	@Kroll.setProperty @Kroll.method
	public void setCurrencyCode(Object arg) {
		ADMS_Measurement.sharedInstance().setCurrencyCode(TiConvert.toString(arg));
	}

	@Kroll.getProperty @Kroll.method
	public Object getCurrencyCode() {
		String toReturn = ADMS_Measurement.sharedInstance().getCurrencyCode();
		return Utils.stringOrUndefined(toReturn);
	}

	// ssl
	@Kroll.setProperty @Kroll.method
	public void setSsl(Object arg) {
		ADMS_Measurement.sharedInstance().setSSL(TiConvert.toBoolean(arg));
	}

	// purchaseID
	@Kroll.setProperty @Kroll.method
	public void setPurchaseID(Object arg) {
		ADMS_Measurement.sharedInstance().setPurchaseID(TiConvert.toString(arg));
	}

	@Kroll.getProperty @Kroll.method
	public Object getPurchaseID() {
		String toReturn = ADMS_Measurement.sharedInstance().getPurchaseID();
		return Utils.stringOrUndefined(toReturn);
	}

	// transactionID
	@Kroll.setProperty @Kroll.method
	public void setTransactionID(Object arg) {
		ADMS_Measurement.sharedInstance().setTransactionID(TiConvert.toString(arg));
	}

	@Kroll.getProperty @Kroll.method
	public Object getTransactionID() {
		String toReturn = ADMS_Measurement.sharedInstance().getTransactionID();
		return Utils.stringOrUndefined(toReturn);
	}

	// appState
	@Kroll.setProperty @Kroll.method
	public void setAppState(Object arg) {
		ADMS_Measurement.sharedInstance().setAppState(TiConvert.toString(arg));
	}

	@Kroll.getProperty @Kroll.method
	public Object getAppState() {
		String toReturn = ADMS_Measurement.sharedInstance().getAppState();
		return Utils.stringOrUndefined(toReturn);
	}

	// channel
	@Kroll.setProperty @Kroll.method
	public void setChannel(Object arg) {
		ADMS_Measurement.sharedInstance().setChannel(TiConvert.toString(arg));
	}

	@Kroll.getProperty @Kroll.method
	public Object getChannel() {
		String toReturn = ADMS_Measurement.sharedInstance().getChannel();
		return Utils.stringOrUndefined(toReturn);
	}

	// appSection
	@Kroll.setProperty @Kroll.method
	public void setAppSection(Object arg) {
		ADMS_Measurement.sharedInstance().setAppSection(TiConvert.toString(arg));
	}

	@Kroll.getProperty @Kroll.method
	public Object getAppSection() {
		String toReturn = ADMS_Measurement.sharedInstance().getAppSection();
		return Utils.stringOrUndefined(toReturn);
	}

	// campaign
	@Kroll.setProperty @Kroll.method
	public void setCampaign(Object arg) {
		ADMS_Measurement.sharedInstance().setCampaign(TiConvert.toString(arg));
	}

	@Kroll.getProperty @Kroll.method
	public Object getCampaign() {
		String toReturn = ADMS_Measurement.sharedInstance().getCampaign();
		return Utils.stringOrUndefined(toReturn);
	}

	// products
	@Kroll.setProperty @Kroll.method
	public void setProducts(Object arg) {
		ADMS_Measurement.sharedInstance().setProducts(TiConvert.toString(arg));
	}

	@Kroll.getProperty @Kroll.method
	public Object getProducts() {
		String toReturn = ADMS_Measurement.sharedInstance().getProducts();
		return Utils.stringOrUndefined(toReturn);
	}

	// events
	@Kroll.setProperty @Kroll.method
	public void setEvents(Object arg) {
		ADMS_Measurement.sharedInstance().setEvents(TiConvert.toString(arg));
	}

	@Kroll.getProperty @Kroll.method
	public Object getEvents() {
		String toReturn = ADMS_Measurement.sharedInstance().getEvents();
		return Utils.stringOrUndefined(toReturn);
	}

	// geoState
	@Kroll.setProperty @Kroll.method
	public void setGeoState(Object arg) {
		ADMS_Measurement.sharedInstance().setGeoState(TiConvert.toString(arg));
	}

	@Kroll.getProperty @Kroll.method
	public Object getGeoState() {
		String toReturn = ADMS_Measurement.sharedInstance().getGeoState();
		return Utils.stringOrUndefined(toReturn);
	}

	// geoZip
	@Kroll.setProperty @Kroll.method
	public void setGeoZip(Object arg) {
		ADMS_Measurement.sharedInstance().setGeoZip(TiConvert.toString(arg));
	}

	@Kroll.getProperty @Kroll.method
	public Object getGeoZip() {
		String toReturn = ADMS_Measurement.sharedInstance().getGeoZip();
		return Utils.stringOrUndefined(toReturn);
	}

	// persistentContextData
	@Kroll.setProperty @Kroll.method
	public void setPersistentContextData(Object arg) {
		if (!(arg instanceof HashMap)) {
			throw new IllegalArgumentException("`persistentContextData` must be a <Dictionary> of key-value pairs");
		}
		ADMS_Measurement.sharedInstance().setPersistentContextData(new Hashtable<String, Object>((HashMap) arg));
	}

	@Kroll.getProperty @Kroll.method
	public HashMap getPersistentContextData() {
		return new HashMap<String, Object>(ADMS_Measurement.sharedInstance().getPersistentContextData());
	}

	// lifecycleSessionTimeout
	@Kroll.setProperty @Kroll.method
	public void setLifecycleSessionTimeout(Object arg) {
		ADMS_Measurement.sharedInstance().setLifecycleSessionTimeout(TiConvert.toInt(arg));
	}

	@Kroll.getProperty @Kroll.method
	public int getLifecycleSessionTimeout() {
		return ADMS_Measurement.sharedInstance().getLifecycleSessionTimeout();
	}

	// linkTrackVars
	@Kroll.setProperty @Kroll.method
	public void setLinkTrackVars(Object arg) {
		ADMS_Measurement.sharedInstance().setLinkTrackVars(TiConvert.toString(arg));
	}

	@Kroll.getProperty @Kroll.method
	public Object getLinkTrackVars() {
		String toReturn = ADMS_Measurement.sharedInstance().getLinkTrackVars();
		return Utils.stringOrUndefined(toReturn);
	}

	// linkTrackEvents
	@Kroll.setProperty @Kroll.method
	public void setLinkTrackEvents(Object arg) {
		ADMS_Measurement.sharedInstance().setLinkTrackEvents(TiConvert.toString(arg));
	}

	@Kroll.getProperty @Kroll.method
	public Object getLinkTrackEvents() {
		String toReturn = ADMS_Measurement.sharedInstance().getLinkTrackEvents();
		return Utils.stringOrUndefined(toReturn);
	}

	// lightTrackVars
	@Kroll.setProperty @Kroll.method
	public void setLightTrackVars(Object arg) {
		ADMS_Measurement.sharedInstance().setLightTrackVars(TiConvert.toString(arg));
	}

	@Kroll.getProperty @Kroll.method
	public Object getLightTrackVars() {
		String toReturn = ADMS_Measurement.sharedInstance().getLightTrackVars();
		return Utils.stringOrUndefined(toReturn);
	}

	// offlineTrackingEnabled
	@Kroll.setProperty @Kroll.method
	public void setOfflineTrackingEnabled(Object arg) {
		ADMS_Measurement.sharedInstance().setOfflineTrackingEnabled(TiConvert.toBoolean(arg));
	}

	// offlineHitLimit
	@Kroll.setProperty @Kroll.method
	public void setOfflineHitLimit(Object arg) {
		ADMS_Measurement.sharedInstance().setOfflineHitLimit(TiConvert.toInt(arg));
	}

	@Kroll.getProperty @Kroll.method
	public int getOfflineHitLimit() {
		return ADMS_Measurement.sharedInstance().getOfflineHitLimit();
	}
}