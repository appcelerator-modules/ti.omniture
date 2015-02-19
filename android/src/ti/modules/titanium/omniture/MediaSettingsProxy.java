package ti.modules.titanium.omniture;

import java.util.HashMap;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.util.TiConvert;

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
	
	@Kroll.getProperty
	public boolean getSegmentByMilestones() {
		return mSettings.segmentByMilestones;
	}
	
	@Kroll.setProperty
	public void setSegmentByMilestones(boolean newValue) {
		mSettings.segmentByMilestones = newValue;
	}
	
	@Kroll.getProperty
	public boolean getSegmentByOffsetMilestones() {
		return mSettings.segmentByOffsetMilestones;
	}
	
	@Kroll.setProperty
	public void setSegmentByOffsetMilestones(boolean newValue) {
		mSettings.segmentByOffsetMilestones = newValue;
	}
	
	@Kroll.getProperty
	public double getLength() {
		return mSettings.length;
	}
	
	@Kroll.setProperty
	public void setLength(double newValue) {
		mSettings.length = newValue;
	}
	
	@Kroll.getProperty
	public String getChannel() {
		return mSettings.channel;
	}
	
	@Kroll.setProperty
	public void setChannel(String newValue) {
		mSettings.channel = newValue;
	}
	
	@Kroll.getProperty
	public String getName() {
		return mSettings.name;
	}
	
	@Kroll.setProperty
	public void setName(String newValue) {
		mSettings.name = newValue;
	}
	
	@Kroll.getProperty
	public String getPlayerName() {
		return mSettings.playerName;
	}
	
	@Kroll.setProperty
	public void setPlayerName(String newValue) {
		mSettings.playerName = newValue;
	}
	
	@Kroll.getProperty
	public String getPlayerID() {
		return mSettings.playerID;
	}
	
	@Kroll.setProperty
	public void setPlayerID(String newValue) {
		mSettings.playerID = newValue;
	}
	
	@Kroll.getProperty
	public String getMilestones() {
		return mSettings.milestones;
	}
	
	@Kroll.setProperty
	public void setMilestones(String newValue) {
		mSettings.milestones = newValue;
	}
	
	@Kroll.getProperty
	public String getOffsetMilestones() {
		return mSettings.offsetMilestones;
	}
	
	@Kroll.setProperty
	public void setOffsetMilestones(String newValue) {
		mSettings.offsetMilestones = newValue;
	}
	
	@Kroll.getProperty
	public int getTrackSeconds() {
		return mSettings.trackSeconds;
	}
	
	@Kroll.setProperty
	public void setTrackSeconds(int newValue) {
		mSettings.trackSeconds = newValue;
	}
	
	@Kroll.getProperty
	public int getCompleteCloseOffsetThreshold() {
		return mSettings.completeCloseOffsetThreshold;
	}
	
	@Kroll.setProperty
	public void setCompleteCloseOffsetThreshold(int newValue) {
		mSettings.completeCloseOffsetThreshold = newValue;
	}
	
	@Kroll.getProperty
	public boolean getIsMediaAd() {
		return mSettings.isMediaAd;
	}
	
	@Kroll.setProperty
	public void setIsMediaAd(boolean newValue) {
		mSettings.isMediaAd = newValue;
	}
	
	@Kroll.getProperty
	public double getParentPodPosition() {
		return mSettings.parentPodPosition;
	}
	
	@Kroll.setProperty
	public void setParentPodPosition(double newValue) {
		mSettings.parentPodPosition = newValue;
	}
	
	@Kroll.getProperty(name="CPM")
	public String getCPM() {
		return mSettings.CPM;
	}
	
	@Kroll.setProperty(name="CPM")
	public void setCPM(String newValue) {
		mSettings.CPM = newValue;
	}
	
	@Kroll.getProperty
	public String getParentName() {
		return mSettings.parentName;
	}
	
	@Kroll.setProperty
	public void setParentName(String newValue) {
		mSettings.parentName = newValue;
	}
	
	@Kroll.getProperty
	public String getParentPod() {
		return mSettings.parentPod;
	}
	
	@Kroll.setProperty
	public void setParentPod(String newValue) {
		mSettings.parentPod = newValue;
	}
	
	void addProps(@SuppressWarnings("rawtypes") HashMap props) {
		Object value;
		
		value = props.get("segmentByMilestones");
		if (value != null) {
			setSegmentByMilestones(TiConvert.toBoolean(value));
		}
		
		value = props.get("segmentByOffsetMilestones");
		if (value != null) {
			setSegmentByOffsetMilestones(TiConvert.toBoolean(value));
		}
		
		value = props.get("channel");
		if (value != null) {
			setChannel(TiConvert.toString(value));
		}
		
		value = props.get("playerID");
		if (value != null) {
			setPlayerID(TiConvert.toString(value));
		}
		
		value = props.get("milestones");
		if (value != null) {
			setMilestones(TiConvert.toString(value));
		}
		
		value = props.get("offsetMilestones");
		if (value != null) {
			setOffsetMilestones(TiConvert.toString(value));
		}
		
		value = props.get("trackSeconds");
		if (value != null) {
			setTrackSeconds(TiConvert.toInt(value));
		}
		
		value = props.get("completeCloseOffsetThreshold");
		if (value != null) {
			setCompleteCloseOffsetThreshold(TiConvert.toInt(value));
		}

		value = props.get("isMediaAd");
		if (value != null) {
			setIsMediaAd(TiConvert.toBoolean(value));
		}
		
		value = props.get("parentPodPosition");
		if (value != null) {
			setParentPodPosition(TiConvert.toDouble(value));
		}
		
		value = props.get("CPM");
		if (value != null) {
			setCPM(TiConvert.toString(value));
		}
		
		value = props.get("parentName");
		if (value != null) {
			setParentName(TiConvert.toString(value));
		}
		
		value = props.get("parentPod");
		if (value != null) {
			setParentPod(TiConvert.toString(value));
		}
		
	}
}
