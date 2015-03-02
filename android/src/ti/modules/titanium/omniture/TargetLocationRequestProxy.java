package ti.modules.titanium.omniture;

import java.util.HashMap;

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
	
	@Kroll.getProperty
	public String getName() {
		return mRequest.name;
	}
	
	@Kroll.setProperty
	public void setName(String name) {
		mRequest.name = name;
	}
	
	@Kroll.getProperty
	public String getDefaultContent() {
		return mRequest.defaultContent;
	}
	
	@Kroll.setProperty
	public void setDefaultContent(String defaultContent) {
		mRequest.defaultContent = defaultContent;
	}
	
	@Kroll.getProperty
	public HashMap<String, Object> getParameters() {
		return mRequest.parameters;
	}
	
	@Kroll.setProperty
	public void setParameters(
			@SuppressWarnings("rawtypes") HashMap parameters
	) {
		HashMap<String, Object> nativeParams = new HashMap<String, Object>();
		for (Object key: parameters.keySet()) {
			if (key instanceof String) {
				nativeParams.put((String)key, parameters.get(key));
			}
		}
		mRequest.parameters = nativeParams;
	}
	
}
