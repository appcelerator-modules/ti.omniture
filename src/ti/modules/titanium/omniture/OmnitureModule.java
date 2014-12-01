/**
 * Appcelerator Titanium Mobile Modules
 * Copyright (c) 2010-2013 by Appcelerator, Inc. All Rights Reserved.
 * Proprietary and Confidential - This source code is not for redistribution
 */

package ti.modules.titanium.omniture;

import java.util.HashMap;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.util.TiConvert;

import com.adobe.adms.measurement.ADMS_Measurement;

@Kroll.module(name = "Omniture", id = "ti.omniture")
public class OmnitureModule extends KrollModule {
	private static final String LCAT = "OmnitureModule";
	
	public OmnitureModule() {
		super();
		Log.d(LCAT, "OmnitureModule Loaded");
	}
	
	@Kroll.getProperty
	@Kroll.method
	public String getVersion() {
		return ADMS_Measurement.sharedInstance().getVersion();
	}
	
	@Kroll.setProperty
	@Kroll.method
	public void setDebugLogging(Object arg) {
		ADMS_Measurement.sharedInstance().setDebugLogging(TiConvert.toBoolean(arg));
	}
	
	@Kroll.method
	public SessionProxy startSession(@Kroll.argument(optional=true) HashMap hm) {
		if (hm == null) {
			return new SessionProxy();
		}
		return new SessionProxy(Utils.krollDictFromHashMap(hm));
	}
	
	@Kroll.method
	public MediaTrackerProxy startMediaTracker(@Kroll.argument(optional=true) HashMap hm) {
		if (hm == null) {
			return new MediaTrackerProxy();
		}
		return new MediaTrackerProxy(Utils.krollDictFromHashMap(hm));
	}
}