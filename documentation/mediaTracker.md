# Ti.Omniture.MediaTracker

## Description

An _omniture_ module object which represents an Omniture media tracker.

_Ti.Omniture.MediaTracker_ automatically tracks [Ti.Media.VideoPlayer][] events, such as start, stop, and pause.

This prevents you from needing to manually track these events and call the `open`, `play`, `stop`, and `close` methods directly.

Properties can be set on the _Ti.Omniture.MediaTracker_ object in two ways:

1. Set properties by passing them as a dictionary of key/value pairs to the `startMediaTracker` method.
2. Set properties by setting them on the _Ti.Omniture.MediaTracker_ object returned by the `startMediaTracker` method.

## Omniture Resources

* [Video Metrics][VideoMetrics]
* [Measuring Video][MeasuringVideo]
* [Configure Milestones][ConfigureMilestones]

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

### adTrackMilestones : string

Comma-delimited list of intervals (as a percentage) for sending ad tracking data.

### adSegmentByMilestones : boolean

Automatically generates segment info based on the `adTrackMilestones` property.

__Default:__ false

### adTrackOffsetMilestones : string

Comma-delimited list of intervals (in seconds) for sending ad tracking data.

### adSegmentByOffsetMilestones : boolean

Automatically generates segment info based on the `adTrackOffsetMilestones` property.

__Default:__ false

[Ti.Media.VideoPlayer]: http://docs.appcelerator.com/titanium/latest/#!/api/Titanium.Media.VideoPlayer
[VideoMetrics]: http://microsite.omniture.com/t2/help/en_US/sc/appmeasurement/video/index.html#Video_Metrics
[MeasuringVideo]: http://microsite.omniture.com/t2/help/en_US/sc/appmeasurement/video/index.html#Apple_iOS
[ConfigureMilestones]: http://microsite.omniture.com/t2/help/en_US/sc/appmeasurement/video/index.html#Configure_Milestones