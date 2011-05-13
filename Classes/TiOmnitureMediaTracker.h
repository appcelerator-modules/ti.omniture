/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2010 by Appcelerator, Inc. All Rights Reserved.
 */

#import "TiProxy.h"
#import "OMAppMeasurement.h"

@interface TiOmnitureMediaTracker : TiProxy {
@private
	double duration;
	OMAppMeasurement *measurement;
	NSTimer *timer;
	double offset;
	NSString *mediaName;
	BOOL openSent;
	BOOL closeSent;
}

@end
