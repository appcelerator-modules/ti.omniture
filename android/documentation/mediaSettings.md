# Ti.Omniture.MediaSettings

## Description

An object that represents Omniture media settings.

Properties can be set on the _Ti.Omniture.MediaSettings_ object in two ways:

1. Set properties by passing them as a dictionary of key/value pairs to the
`createMediaSettings` method.
2. Set properties by setting them on the _Ti.Omniture.MediaSettings_ object
returned by the `createMediaSettings` method.

## Properties

### segmentByMilestones : Boolean

Indicates if segment info should be automatically generated for milestones
generated or not. Defaults to false.

### segmentByOffsetMilestones : Boolean

Indicates if segment info should be automatically generated for offset
milestones or not, the default is false.

### length : Number

The length of the media item in seconds.

### channel : String

The name or ID of the channel.

### name : String

The name or ID of the media item.

### playerName : String

The name of the media player.

### playerID : String

The ID of the media player.

### milestones : String

A comma-delimited list of intervals (as a percentage) for sending tracking data.

### offsetMilestones : String

A comma-delimited list of intervals (in seconds) for sending tracking data.

### trackSeconds : Number

The interval at which tracking data should be sent, the default is 0.

### completeCloseOffsetThreshold : Number

The number of second prior to the end of the media that it should be
considered complete, the default is 1.

### isMediaAd : Boolean

Indicates if the media item is an ad or not.

### parentPodPosition : Number

The position within the pod where the ad is played.

### CPM : String

The CMP or encrypted CPM (prefixed with a "~") for the media item.

### parentName : String

The name or ID of the media item that the ad is embedded in.

### parentPod : String

The position in the primary content the ad was played.
