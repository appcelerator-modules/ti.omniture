package ti.modules.titanium.omniture;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;

import com.adobe.mobile.TargetLocationRequest;

@Kroll.proxy
public class TargetLocationRequestProxy
		extends KrollProxy
{
	private TargetLocationRequest mRequest;
	
	public TargetLocationRequestProxy(TargetLocationRequest request) {
		super();
		mRequest = request;
	}
	
	public TargetLocationRequest getRequest() {
		return mRequest;
	}
}
