/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2010 by Appcelerator, Inc. All Rights Reserved.
 */

#import "TiOmnitureModule.h"
#import "TiBase.h"
#import "TiHost.h"
#import "TiUtils.h"
#import "TiOmnitureSession.h"
#import "TiOmnitureMediaTracker.h"

@implementation TiOmnitureModule

#pragma mark Internal

// this is generated for your module, please do not change it
-(id)moduleGUID
{
	return @"5f64860a-2d93-491b-8ba7-abcda6ffca0e";
}

// this is generated for your module, please do not change it
-(NSString*)moduleId
{
	return @"ti.omniture";
}

-(id)createSession:(id)args
{
	TiOmnitureSession *session = [[[TiOmnitureSession alloc] _initWithPageContext:[self pageContext] args:args] autorelease];
	return session;
}

-(id)createMediaTracker:(id)args
{
	TiOmnitureMediaTracker *session = [[[TiOmnitureMediaTracker alloc] _initWithPageContext:[self pageContext] args:args] autorelease];
	return session;
}

@end
