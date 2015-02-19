# Ti.Omniture.MediaState

## Description

An object that represents Omniture media state. There is no API to create
these objects directly. The callback provided to the `mediaOpen`
function will receive these state objects as parameter.

## Properties

### ad : Boolean

Indicates if the media item is an ad or not.

### clicked : Boolean

Indicates if the media item has been clicked or not.

### complete : Boolean

Indicates if media play is complete or not.

### eventFirstTime : Boolean

Indicates if this was the first time that this media event was called for this video.

### length : Number

The length of the media item in seconds.

### offset : Number

The current point in the media item in seconds.

### percent : Number

The current point in the media item as a percentage.

### segmentLength : Number

The length of the current segment.

### timePlayed : Number

The total time played so far in seconds.

### timePlayedSinceTrack : Number

The amount of time played since the last track event occurred in seconds.

iOS only.

### timestamp : Number

The number of seconds since 1970 when this media state was created.

iOS only.

### openTime : Date

The date and time of when the media item was opened.

### name : String

The name or ID of the media item.

### playerName : String

The name or ID of the media player.

### mediaEvent : String

The name of the most recent media event.

### segment : String

The name of the current segment.

### milestone : Number

The most recent milestone.

### offsetMilestone : Number

The most recent offset milestone.

### segmentNum : Number

The current segment.

### eventType : Number

The current event type.

iOS only.
