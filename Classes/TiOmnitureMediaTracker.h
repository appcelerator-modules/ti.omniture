/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2010 by Appcelerator, Inc. All Rights Reserved.
 */

#import "TiProxy.h"
#import "AppMeasurement.h"

@interface TiOmnitureMediaTracker : TiProxy {
@private
	double duration;
	AppMeasurement *measurement;
	NSTimer *timer;
	double offset;
	NSString *mediaName;
	BOOL openSent;
	BOOL closeSent;
}

@end
