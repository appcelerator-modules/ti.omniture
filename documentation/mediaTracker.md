# Ti.Omniture.MediaTracker

## Description

An _omniture_ module object which represents an Omniture Media Tracker.

_Ti.Omniture.MediaTracker_ automatically tracks [Ti.Media.VideoPlayer][] events such as start, stop, and pause.

This prevents you from needing to manually track these events and call the open, play, stop, and close methods directly.

Properties can be set on the _Ti.Omniture.MediaTracker_ object in two ways:

1. Set Properties by passing them as a dictionary of key value pairs to the `startMediaTracker` method.
2. Set Properties by setting them on the _Ti.Omniture.MediaTracker_ object returned by the `startMediaTracker` method

## Omniture Resources

* [Video Metrics][VideoMetrics]
* [Measuring Video][MeasuringVideo]
* [Configure Milestones][ConfigureMilestones]

## Properties

### Ti.Omniture.MediaTracker.trackVars[string]

Comma-delimited list of variables

### Ti.Omniture.MediaTracker.trackEvents[string]

Comma-delimited list of events

### Ti.Omniture.MediaTracker.channel[string]

Channel

### Ti.Omniture.MediaTracker.completeCloseOffsetThreshold[number]

Number of second before the end of the media that it should be considered complete

__Default:__ 1

### Ti.Omniture.MediaTracker.contextDataMapping[object]

Defines variable mapping to eVars and Events

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

### Ti.Omniture.MediaTracker.trackSeconds[number]

Interval for sending tracking data

__Default:__ 0

### Ti.Omniture.MediaTracker.trackMilestones[string]

Comma-delimited list of intervals (as a percentage) for sending tracking data

#### Example

	mediaTracker.trackMilestones = "25,50,75";

### Ti.Omniture.MediaTracker.segmentByMilestones[boolean]

Automatically generates segment info based on trackMilestones

__Default:__ false

### Ti.Omniture.MediaTracker.trackOffsetMilestones[string]

Comma-delimited list of intervals (in seconds) for sending tracking data

#### Example

	mediaTracker.trackOffsetMilestones = "15,30,45,60,75,90";

### Ti.Omniture.MediaTracker.segmentByOffsetMilestones[boolean]

Automatically generates segment info based on trackOffsetMilestones

__Default:__ false

### Ti.Omniture.MediaTracker.adTrackSeconds[number]

Interval for sending ad tracking data

__Default:__ 0

### Ti.Omniture.MediaTracker.adTrackMilestones[string]

Comma-delimited list of intervals (as a percentage) for sending ad tracking data

### Ti.Omniture.MediaTracker.adSegmentByMilestones[boolean]

Automatically generates segment info based on adTrackMilestones

__Default:__ false

### Ti.Omniture.MediaTracker.adTrackOffsetMilestones[string]

Comma-delimited list of intervals (in seconds) for sending ad tracking data

### Ti.Omniture.MediaTracker.adSegmentByOffsetMilestones[boolean]

Automatically generates segment info based on adTrackOffsetMilestones

__Default:__ false

[Ti.Media.VideoPlayer]: http://docs.appcelerator.com/titanium/latest/#!/api/Titanium.Media.VideoPlayer
[VideoMetrics]: http://microsite.omniture.com/t2/help/en_US/sc/appmeasurement/video/index.html#Video_Metrics
[MeasuringVideo]: http://microsite.omniture.com/t2/help/en_US/sc/appmeasurement/video/index.html#Apple_iOS
[ConfigureMilestones]: http://microsite.omniture.com/t2/help/en_US/sc/appmeasurement/video/index.html#Configure_Milestones