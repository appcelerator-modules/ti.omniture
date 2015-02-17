package ti.modules.titanium.omniture;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;

import com.adobe.mobile.MediaSettings;

@Kroll.proxy
public class MediaSettingsProxy
	extends KrollProxy
{
	private MediaSettings mSettings;
	
	public MediaSettingsProxy(MediaSettings settings) {
		super();
		mSettings = settings;
	}
	
	public MediaSettings getSettings() {
		return mSettings;
	}
}
