/**
 * Appcelerator Titanium Mobile Modules
 * Copyright (c) 2010-2013 by Appcelerator, Inc. All Rights Reserved.
 * Proprietary and Confidential - This source code is not for redistribution
 */

#import "TiOmnitureMediaTracker.h"
#import "TiUtils.h"
#import "Utils.h"
#import "ADMS_MediaMeasurement.h"

@implementation TiOmnitureMediaTracker

-(id)init
{
    // Using automatic tracking of MPMoviePlayer
    [[ADMS_MediaMeasurement sharedInstance] setAutoTrackingOptions:ADMS_MediaAutoTrackOptionsMPMoviePlayer];
    return [super init];
}

#pragma mark - Properties

MAKE_STR_GETTER_SETTER(trackVars,   setTrackVars,   ADMS_MediaMeasurement);
MAKE_STR_GETTER_SETTER(trackEvents, setTrackEvents, ADMS_MediaMeasurement);
MAKE_STR_GETTER_SETTER(channel,     setChannel,     ADMS_MediaMeasurement);
MAKE_DBL_GETTER_SETTER(completeCloseOffsetThreshold, setCompleteCloseOffsetThreshold,       ADMS_MediaMeasurement);
MAKE_OBJ_GETTER_SETTER(contextDataMapping,           setContextDataMapping, NSDictionary,   ADMS_MediaMeasurement);

MAKE_INT_GETTER_SETTER(trackSeconds,                setTrackSeconds,                        ADMS_MediaMeasurement);
MAKE_STR_GETTER_SETTER(trackMilestones,             setTrackMilestones,                     ADMS_MediaMeasurement);
MAKE_BOOL_GETTER_SETTER(segmentByMilestones,        setSegmentByMilestones,                 ADMS_MediaMeasurement);
MAKE_STR_GETTER_SETTER(trackOffsetMilestones,       setTrackOffsetMilestones,               ADMS_MediaMeasurement);
MAKE_BOOL_GETTER_SETTER(segmentByOffsetMilestones,  setSegmentByOffsetMilestones,           ADMS_MediaMeasurement);

MAKE_INT_GETTER_SETTER(adTrackSeconds,              setAdTrackSeconds,              ADMS_MediaMeasurement);
MAKE_STR_GETTER_SETTER(adTrackMilestones,           setAdTrackMilestones,           ADMS_MediaMeasurement);
MAKE_BOOL_GETTER_SETTER(adSegmentByMilestones,      setAdSegmentByMilestones,       ADMS_MediaMeasurement);
MAKE_STR_GETTER_SETTER(adTrackOffsetMilestones,     setAdTrackOffsetMilestones,     ADMS_MediaMeasurement);
MAKE_BOOL_GETTER_SETTER(adSegmentByOffsetMilestones, setAdSegmentByOffsetMilestones, ADMS_MediaMeasurement);
    
@end
