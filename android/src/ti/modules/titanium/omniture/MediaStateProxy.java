package ti.modules.titanium.omniture;

import java.util.Date;

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
	
	@Kroll.getProperty
	public boolean getAd() {
		return mState.ad;
	}
	
	@Kroll.setProperty
	public void setAd(boolean newValue) {
		mState.ad = newValue;
	}

	@Kroll.getProperty
	public boolean getClicked() {
		return mState.clicked;
	}
	
	@Kroll.setProperty
	public void setClicked(boolean newValue) {
		mState.clicked = newValue;
	}

	@Kroll.getProperty
	public boolean getComplete() {
		return mState.complete;
	}
	
	@Kroll.setProperty
	public void setComplete(boolean newValue) {
		mState.complete = newValue;
	}

	@Kroll.getProperty
	public boolean getEventFirstTime() {
		return mState.eventFirstTime;
	}
	
	@Kroll.setProperty
	public void setEventFirstTime(boolean newValue) {
		mState.eventFirstTime = newValue;
	}

	@Kroll.getProperty
	public double getLength() {
		return mState.length;
	}
	
	@Kroll.setProperty
	public void setLength(double newValue) {
		mState.length = newValue;
	}

	@Kroll.getProperty
	public double getOffset() {
		return mState.offset;
	}
	
	@Kroll.setProperty
	public void setOffset(double newValue) {
		mState.offset = newValue;
	}

	@Kroll.getProperty
	public double getPercent() {
		return mState.percent;
	}
	
	@Kroll.setProperty
	public void setPercent(double newValue) {
		mState.percent = newValue;
	}

	@Kroll.getProperty
	public double getSegmentLength() {
		return mState.segmentLength;
	}
	
	@Kroll.setProperty
	public void setSegmentLength(double newValue) {
		mState.segmentLength = newValue;
	}

	@Kroll.getProperty
	public double getTimePlayed() {
		return mState.timePlayed;
	}
	
	@Kroll.setProperty
	public void setTimePlayed(double newValue) {
		mState.timePlayed = newValue;
	}

	@Kroll.getProperty
	public double getTimePlayedSinceTrack() {
		// Here to provide parity with iOS. MediaState has a
		// getTimePlayedSinceTrack method but it's not public.
		return 0;
	}
	
	@Kroll.setProperty
	public void setTimePlayedSinceTrack(double newValue) {
		// Here to provide parity with iOS. MediaState has a
		// setTimePlayedSinceTrack method but it's not public.
	}

	@Kroll.getProperty
	public double getTimestamp() {
		// Here to provide parity with iOS. MediaState has a
		// getTimeStamp method but it's not public.
		return 0;
	}
	
	@Kroll.setProperty
	public void setTimestamp(double newValue) {
		// Here to provide parity with iOS. MediaState has a
		// setTimeStamp method but it's not public.
	}

	@Kroll.getProperty
	public Date getOpenTime() {
		return mState.openTime;
	}
	
	@Kroll.setProperty
	public void setOpenTime(Date newValue) {
		mState.openTime = newValue;
	}

	@Kroll.getProperty
	public String getName() {
		return mState.name;
	}
	
	@Kroll.setProperty
	public void setName(String newValue) {
		mState.name = newValue;
	}

	@Kroll.getProperty
	public String getPlayerName() {
		return mState.playerName;
	}
	
	@Kroll.setProperty
	public void setPlayerName(String newValue) {
		mState.playerName = newValue;
	}

	@Kroll.getProperty
	public String getMediaEvent() {
		return mState.mediaEvent;
	}
	
	@Kroll.setProperty
	public void setMediaEvent(String newValue) {
		mState.mediaEvent = newValue;
	}

	@Kroll.getProperty
	public String getSegment() {
		return mState.segment;
	}
	
	@Kroll.setProperty
	public void setSegment(String newValue) {
		mState.segment = newValue;
	}

	@Kroll.getProperty
	public int getMilestone() {
		return mState.milestone;
	}
	
	@Kroll.setProperty
	public void setMilestone(int newValue) {
		mState.milestone = newValue;
	}

	@Kroll.getProperty
	public int getOffsetMilestone() {
		return mState.offsetMilestone;
	}
	
	@Kroll.setProperty
	public void setOffsetMilestone(int newValue) {
		mState.offsetMilestone = newValue;
	}

	@Kroll.getProperty
	public int getSegmentNum() {
		return mState.segmentNum;
	}
	
	@Kroll.setProperty
	public void setSegmentNum(int newValue) {
		mState.segmentNum = newValue;
	}

	@Kroll.getProperty
	public double getEventType() {
		// Here to provide parity with iOS. MediaState has a
		// getEventType method but it's not public.
		return 0;
	}
	
	@Kroll.setProperty
	public void setEventType(double newValue) {
		// Here to provide parity with iOS. MediaState has a
		// setEventType method but it's not public.
	}

}
