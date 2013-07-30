# Ti.Omniture.MediaTracker

## Description

An _omniture_ module object which represents an Omniture media tracker.

On iOS _Ti.Omniture.MediaTracker_ automatically tracks [Ti.Media.VideoPlayer][] events, such as start, stop, and pause.

This prevents you from needing to manually track these events and call the `open`, `play`, `stop`, and `close` methods directly.

On Android there is not automatic tracking, so open, play, stop, and close methods must be called manually. See the examples below and in the example app.

Properties can be set on the _Ti.Omniture.MediaTracker_ object in two ways:

1. Set properties by passing them as a dictionary of key/value pairs to the `startMediaTracker` method.
2. Set properties by setting them on the _Ti.Omniture.MediaTracker_ object returned by the `startMediaTracker` method.

## Omniture Resources

* [Video Metrics][VideoMetrics]
* [Measuring Video iOS][MeasuringVideoIOS]
* [Measuring Video Android][MeasuringVideoAndroid]
* [Configure Milestones][ConfigureMilestones]

## Methods

## <void\> open(props)

Tracking method to be called when a video is opened

* props (object): Key/value dictionary of properties.
	* mediaName (string): Name of the media (required).
	* mediaLength (number): Length of media in seconds (required).
	* mediaPlayerName (string): Name of the media player (required).

* returns: void.

__Android only__

#### Example

	mediaTracker.open({
       mediaName: mediaName,
       mediaLength: secondsFromMs(e.duration), // `e.duration` is in milliseconds, but `mediaLength` is in seconds
       mediaPlayerName: "Ti.Media.VideoPlayer"
    });
    
## <void\> play(props)

Tracking method to be called when a video is played

* props (object): Key/value dictionary of properties.
	* mediaName (string): Name of the media being played (required).
	* mediaOffset (number): The current offset of media in seconds (required).

* returns: void.

__Android only__

#### Example

	 mediaTracker.play({
         mediaName: mediaName,
         // VideoPlayer times are in milliseconds, but Omniture.mediaTracker times are in seconds
         mediaOffset: secondsFromMs($.videoPlayer.currentPlaybackTime)
     });

## <void\> stop(props)

Tracking method to be called when a video is stoped

* props (object): Key/value dictionary of properties.
	* mediaName (string): Name of the media (required).
	* mediaOffset (number): The current offset of media in seconds (required).

* returns: void.

__Android only__

#### Example

	 mediaTracker.stop({
         mediaName: mediaName,
         // VideoPlayer times are in milliseconds, but Omniture.mediaTracker times are in seconds
         mediaOffset: secondsFromMs($.videoPlayer.currentPlaybackTime)
     });
 
## <void\> close(props)

Tracking method to be called when a video is closed/finished

* props (object): Key/value dictionary of properties.
	* mediaName (string): Name of the media (required).

* returns: void.

__Android only__

#### Example

	 mediaTracker.close({
         mediaName: mediaName
     });    
     
    

## Properties

### trackVars : string

Comma-delimited list of variables.

### trackEvents : string

Comma-delimited list of events.

### channel : string

Channel.

### completeCloseOffsetThreshold : number

Number of seconds before the end of the media that it should be considered complete.

__Default:__ 1

### contextDataMapping : object

Defines variable mapping to eVars and events.

#### Example

	mediaTracker.contextDataMapping = {
        "a.media.name": "eVar2,prop2",
        "a.media.segment": "eVar3",
        "a.contentType": "eVar1",
        "a.media.timePlayed": "event3",
        "a.media.view": "event1",
        "a.media.segmentView": "event2",
        "a.media.complete": "event7"
    };

### trackSeconds : number

Interval for sending tracking data.

__Default:__ 0

### trackMilestones : string

Comma-delimited list of intervals (as a percentage) for sending tracking data.

#### Example

	mediaTracker.trackMilestones = "25,50,75";

### segmentByMilestones : boolean

Automatically generates segment info based on the `trackMilestones` property.

__Default:__ false

### trackOffsetMilestones : string

Comma-delimited list of intervals (in seconds) for sending tracking data.

#### Example

	mediaTracker.trackOffsetMilestones = "15,30,45,60,75,90";

### segmentByOffsetMilestones : boolean

Automatically generates segment info based on the `trackOffsetMilestones` property.

__Default:__ false

### adTrackSeconds : number

Interval for sending ad tracking data.

__Default:__ 0

__iOS only__

### adTrackMilestones : string

Comma-delimited list of intervals (as a percentage) for sending ad tracking data.

__iOS only__

### adSegmentByMilestones : boolean

Automatically generates segment info based on the `adTrackMilestones` property.

__Default:__ false

__iOS only__

### adTrackOffsetMilestones : string

Comma-delimited list of intervals (in seconds) for sending ad tracking data.

__iOS only__

### adSegmentByOffsetMilestones : boolean

Automatically generates segment info based on the `adTrackOffsetMilestones` property.

__Default:__ false

__iOS only__

[Ti.Media.VideoPlayer]: http://docs.appcelerator.com/titanium/latest/#!/api/Titanium.Media.VideoPlayer
[VideoMetrics]: http://microsite.omniture.com/t2/help/en_US/sc/appmeasurement/video/index.html#Video_Metrics
[MeasuringVideoIOS]: http://microsite.omniture.com/t2/help/en_US/sc/appmeasurement/video/index.html#Apple_iOS
[MeasuringVideoAndroid]: http://microsite.omniture.com/t2/help/en_US/sc/appmeasurement/android/index.html#AppMeasurement_3x_for_Android
[ConfigureMilestones]: http://microsite.omniture.com/t2/help/en_US/sc/appmeasurement/video/index.html#Configure_Milestones