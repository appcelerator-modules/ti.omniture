/**
 * Appcelerator Titanium Mobile Modules
 * Copyright (c) 2010-2013 by Appcelerator, Inc. All Rights Reserved.
 * Proprietary and Confidential - This source code is not for redistribution
 */

#import "TiOmnitureModule.h"
#import "ADMS_Measurement.h"
#import "Utils.h"

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

-(void)dealloc
{
    RELEASE_TO_NIL(session);
    RELEASE_TO_NIL(mediaTracker);
    [super dealloc];
}

#pragma mark - Properties

MAKE_SYSTEM_STR(version, [[ADMS_Measurement sharedInstance] version]);
MAKE_BOOL_GETTER_SETTER(debugLogging,  setDebugLogging,   ADMS_Measurement);

#pragma mark - Public Methods

-(id)startSession:(id)args
{
    return [self sessionForClass:[TiOmnitureSession class] withArgs:args sessionHolder:&session];
}

-(id)startMediaTracker:(id)args
{
    return [self sessionForClass:[TiOmnitureMediaTracker class] withArgs:args sessionHolder:&mediaTracker];
}

#pragma mark - Utils

-(id)sessionForClass:(Class)class withArgs:(id)args sessionHolder:(id*)sessionHolder
{
    // The proxy must be treated like a singleton because
    // there are some properties that are stored on the proxy itself and
    // they will not show up on the returned proxy if start is called a 2nd time
    // If the start method is called a 2nd time, the properties passed in
    // must be passed onto the proxy incase they have changed
    if (!*sessionHolder) {
        id tmp = nil;
        ENSURE_ARG_OR_NIL_AT_INDEX(tmp, args, 0, NSDictionary);
        *sessionHolder = [[[class alloc] _initWithPageContext:[self pageContext] args:args] retain];
    } else {
        ENSURE_SINGLE_ARG_OR_NIL(args, NSDictionary);
        [*sessionHolder setValuesForKeysWithDictionary:args];
    }
    return *sessionHolder;
}

@end
