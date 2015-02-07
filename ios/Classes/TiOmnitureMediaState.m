/**
 * Appcelerator Titanium Mobile Modules
 * Copyright (c) 2010-2015 by Appcelerator, Inc. All Rights Reserved.
 * Proprietary and Confidential - This source code is not for redistribution
 */

#import "TiOmnitureMediaState.h"
#import "Utils.h"

@implementation TiOmnitureMediaState

@synthesize adbMediaState = _adbMediaState;

-(TiOmnitureMediaState*)initWithMediaState:(ADBMediaState*)mediaState
{
    self = [super init];
    if (self) {
        _adbMediaState = [mediaState retain];
    }
    return self;
}

MAKE_BOOL_GETTER_SETTER(ad, setAd, self.adbMediaState.ad);
MAKE_BOOL_GETTER_SETTER(clicked, setClicked, self.adbMediaState.clicked);
MAKE_BOOL_GETTER_SETTER(complete, setComplete, self.adbMediaState.complete);
MAKE_BOOL_GETTER_SETTER(eventFirstTime, setEventFirstTime, self.adbMediaState.eventFirstTime);

MAKE_DBL_GETTER_SETTER(length, setLength, self.adbMediaState.length);
MAKE_DBL_GETTER_SETTER(offset, setOffset, self.adbMediaState.offset);
MAKE_DBL_GETTER_SETTER(percent, setPercent, self.adbMediaState.percent);
MAKE_DBL_GETTER_SETTER(segmentLength, setSegmentLength, self.adbMediaState.segmentLength);
MAKE_DBL_GETTER_SETTER(timePlayed, setTimePlayed, self.adbMediaState.timePlayed);
MAKE_DBL_GETTER_SETTER(timePlayedSinceTrack, setTimePlayedSinceTrack, self.adbMediaState.timePlayedSinceTrack);
MAKE_DBL_GETTER_SETTER(timestamp, setTimestapm, self.adbMediaState.timestamp);

MAKE_DATE_GETTER_SETTER(openTime, setOpenTime, self.adbMediaState.openTime);

MAKE_STR_GETTER_SETTER(name, setName, self.adbMediaState.name);
MAKE_STR_GETTER_SETTER(playerName, setPlayerName, self.adbMediaState.playerName);
MAKE_STR_GETTER_SETTER(mediaEvent, setMediaEvent, self.adbMediaState.mediaEvent);
MAKE_STR_GETTER_SETTER(segment, setSegment, self.adbMediaState.segment);

MAKE_UINT_GETTER_SETTER(milestone, setMilestone, self.adbMediaState.milestone);
MAKE_UINT_GETTER_SETTER(offsetMilestone, setOffsetMilestone, self.adbMediaState.offsetMilestone);
MAKE_UINT_GETTER_SETTER(segmentNum, setSegmentNum, self.adbMediaState.segmentNum);
MAKE_UINT_GETTER_SETTER(eventType, setEventType, self.adbMediaState.eventType);

@end
