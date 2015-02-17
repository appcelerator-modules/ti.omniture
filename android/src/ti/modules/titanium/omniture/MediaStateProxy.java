package ti.modules.titanium.omniture;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;

import com.adobe.mobile.MediaState;

@Kroll.proxy
public class MediaStateProxy
	extends KrollProxy
{
	private MediaState mState;
	
	public MediaStateProxy(MediaState state) {
		super();
		mState = state;
	}
	
	public MediaState getState() {
		return mState;
	}
}
