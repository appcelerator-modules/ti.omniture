package ti.modules.titanium.omniture;

import java.util.HashMap;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollRuntime;

public class Utils {
	
	public static void checkRequired(String key, KrollDict args) {
		if (!args.containsKey(key)) {
			throw new IllegalArgumentException("`"+key+"` required");
		}
	}
	
	public static KrollDict krollDictFromHashMap(Object hm) {
		if (!(hm instanceof HashMap)) {
			throw new IllegalArgumentException("argument must be a <Dictionary> of key-value pairs");
		}
		
		return new KrollDict((HashMap) hm);
	}
	
	public static String setterNameFromPropertyName(String prop) {
		return "set" + prop.substring(0, 1).toUpperCase() + prop.substring(1);
	}
	
	public static Object stringOrUndefined(String str) {
		if (str == null || str.length() == 0) {
			return KrollRuntime.UNDEFINED;
		}
		return str;
	}
	
	public static String stringOrNone(String str) {
		if (str == null || str.length() == 0) {
			return "None";
		}
		return str;
	}
}
