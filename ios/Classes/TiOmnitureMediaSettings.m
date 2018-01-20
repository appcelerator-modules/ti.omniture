/**
 * Appcelerator Titanium Mobile Modules
 * Copyright (c) 2010-present by Appcelerator, Inc. All Rights Reserved.
 * Proprietary and Confidential - This source code is not for redistribution
 */

#import "TiOmnitureMediaSettings.h"
#import "Utils.h"

@implementation TiOmnitureMediaSettings

@synthesize adbMediaSettings = _adbMediaSettings;

- (TiOmnitureMediaSettings *)initWithMediaSettings:(ADBMediaSettings *)mediaSettings
{
  self = [super init];
  if (self) {
    _adbMediaSettings = mediaSettings;
  }
  return self;
}

MAKE_BOOL_GETTER_SETTER(segmentByMilestones, setSegmentByMilestones, self.adbMediaSettings.segmentByMilestones);
MAKE_BOOL_GETTER_SETTER(segmentByOffsetMilestones, setSegmentByOffsetMilestones, self.adbMediaSettings.segmentByOffsetMilestones);
MAKE_DBL_GETTER_SETTER(length, setLength, self.adbMediaSettings.length);
MAKE_STR_GETTER_SETTER(channel, setChannel, self.adbMediaSettings.channel);
MAKE_STR_GETTER_SETTER(name, setName, self.adbMediaSettings.name);

MAKE_STR_GETTER_SETTER(playerName, setPlayerName, self.adbMediaSettings.playerName);
MAKE_STR_GETTER_SETTER(playerID, setPlayerID, self.adbMediaSettings.playerID);
MAKE_STR_GETTER_SETTER(milestones, setMilestones, self.adbMediaSettings.milestones);
MAKE_STR_GETTER_SETTER(offsetMilestones, setOffsetMilestones, self.adbMediaSettings.offsetMilestones);
MAKE_ULONG_GETTER_SETTER(trackSeconds, setTrackSeconds, self.adbMediaSettings.trackSeconds);
MAKE_ULONG_GETTER_SETTER(completeCloseOffsetThreshold, setCompleteCloseOffsetThreshold, self.adbMediaSettings.completeCloseOffsetThreshold);

// Media Ad settings
MAKE_BOOL_GETTER_SETTER(isMediaAd, setIsMediaAd, self.adbMediaSettings.isMediaAd);
MAKE_DBL_GETTER_SETTER(parentPodPosition, setParentPodPosition, self.adbMediaSettings.parentPodPosition);
MAKE_STR_GETTER_SETTER(CPM, setCPM, self.adbMediaSettings.CPM);
MAKE_STR_GETTER_SETTER(parentName, setParentName, self.adbMediaSettings.parentName);
MAKE_STR_GETTER_SETTER(parentPod, setParentPod, self.adbMediaSettings.parentPod);

@end
