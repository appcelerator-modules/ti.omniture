/**
 * Appcelerator Titanium Mobile Modules
 * Copyright (c) 2010-2015 by Appcelerator, Inc. All Rights Reserved.
 * Proprietary and Confidential - This source code is not for redistribution
 */

#import "TiOmnitureModule.h"
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
    [super dealloc];
}

#pragma mark - Properties

MAKE_SYSTEM_PROP(PRIVACY_STATUS_OPT_IN, ADBMobilePrivacyStatusOptIn);
MAKE_SYSTEM_PROP(PRIVACY_STATUS_OPT_OUT, ADBMobilePrivacyStatusOptOut);
MAKE_SYSTEM_PROP(PRIVACY_STATUS_UNKNOWN, ADBMobilePrivacyStatusUnknown);

MAKE_SYSTEM_STR(TARGET_PARAM_ORDER_ID, ADBTargetParameterOrderId);
MAKE_SYSTEM_STR(TARGET_PARAM_ORDER_TOTAL, ADBTargetParameterOrderTotal);
MAKE_SYSTEM_STR(TARGET_PARAM_PRODUCT_PURCHASE_ID, ADBTargetParameterProductPurchasedId);
MAKE_SYSTEM_STR(TARGET_PARAM_CATEGORY_ID, ADBTargetParameterCategoryId);
MAKE_SYSTEM_STR(TARGET_PARAM_MBOX_3RD_PARTY_ID, ADBTargetParameterMbox3rdPartyId);
MAKE_SYSTEM_STR(TARGET_PARAM_MBOX_PAGE_VALUE, ADBTargetParameterMboxPageValue);
MAKE_SYSTEM_STR(TARGET_PARAM_MBOX_PC, ADBTargetParameterMboxPc);
MAKE_SYSTEM_STR(TARGET_PARAM_MBOX_SESSION_ID, ADBTargetParameterMboxSessionId);
MAKE_SYSTEM_STR(TARGET_PARAM_MBOX_HOST, ADBTargetParameterMboxHost);

MAKE_SYSTEM_STR(version, [ADBMobile version]);
MAKE_SYSTEM_PROP(lifetimeValue, [[ADBMobile lifetimeValue] intValue]);
MAKE_SYSTEM_NUMBER(trackingQueueSize, [NSNumber numberWithUnsignedLong:[ADBMobile trackingGetQueueSize]]);

MAKE_INT_GETTER_SETTER(privacyStatus, setPrivacyStatus, ADBMobile.privacyStatus);
MAKE_STR_GETTER_SETTER(userIdentifier, setUserIdentifier, ADBMobile.userIdentifier);
MAKE_BOOL_GETTER_SETTER(debugLogging, setDebugLogging, ADBMobile.debugLogging);

#pragma mark - Public Methods

-(void) keepLifecycleSessionAlive:(id)unused
{
    [ADBMobile keepLifecycleSessionAlive];
}

-(void) collectLifecycleData:(id)data
{
    ENSURE_SINGLE_ARG_OR_NIL(data, NSDictionary);
    
    if (data) {
        [ADBMobile collectLifecycleDataWithAdditionalData:data];
    } else {
        [ADBMobile collectLifecycleData];
    }
}

// TODO: This function must be called before applicationDidFinishLaunching
// has completed and before any other interactions with the Adobe Mobile
// library have happened. I suspect that this is not possible from JS,
// so exposing this function is probably not helpful. We'll need to discuss
// what if anything can be done to provide this feature to Titanium developers.
-(void) overrideConfigPath:(id)newConfigPath
{
    ENSURE_SINGLE_ARG(newConfigPath, NSString);
    [ADBMobile overrideConfigPath:newConfigPath];
}

-(void) trackState:(NSArray*)params
{
    ENSURE_TYPE(params, NSArray);
    ENSURE_TYPE([params objectAtIndex:0], NSString);
    NSString *state = [params objectAtIndex:0];
    
    NSDictionary *data = nil;
    if (params.count > 1) {
        ENSURE_TYPE([params objectAtIndex:1], NSDictionary);
        data = [params objectAtIndex:1];
    }
    
    [ADBMobile trackState:state data:data];
}

-(void) trackAction:(NSArray*)params
{
    ENSURE_TYPE(params, NSArray);
    ENSURE_TYPE([params objectAtIndex:0], NSString);
    NSString *action = [params objectAtIndex:0];
    
    NSDictionary *data = nil;
    if (params.count > 1) {
        ENSURE_TYPE([params objectAtIndex:1], NSDictionary);
        data = [params objectAtIndex:1];
    }
    
    [ADBMobile trackAction:action data:data];
}

-(void) trackActionFromBackground:(NSArray*)params
{
    ENSURE_TYPE(params, NSArray);
    ENSURE_TYPE([params objectAtIndex:0], NSString);
    NSString *action = [params objectAtIndex:0];
    
    NSDictionary *data = nil;
    if (params.count > 1) {
        ENSURE_TYPE([params objectAtIndex:1], NSDictionary);
        data = [params objectAtIndex:1];
    }
    
    [ADBMobile trackActionFromBackground:action data:data];
}

-(void) trackLocation:(NSArray*)params
{
    ENSURE_TYPE(params, NSArray);
    ENSURE_TYPE([params objectAtIndex:0], NSNumber);
    ENSURE_TYPE([params objectAtIndex:1], NSNumber);
    CLLocation *location = [[[CLLocation alloc] initWithLatitude:[[params objectAtIndex:0] doubleValue] longitude:[[params objectAtIndex:1] doubleValue]] autorelease];
    
    NSDictionary *data = nil;
    if (params.count > 2) {
        ENSURE_TYPE([params objectAtIndex:2], NSDictionary);
        data = [params objectAtIndex:2];
    }
    
    [ADBMobile trackLocation:location data:data];
}

// TODO: We need to figure out how to get a beacon object if we want to expose
// these two.
//+ (void) trackBeacon:(CLBeacon *)beacon data:(NSDictionary *)data;
//+ (void) trackingClearCurrentBeacon;

-(void) trackLifetimeValueIncrease:(NSArray*)params
{
    ENSURE_TYPE(params, NSArray);
    ENSURE_TYPE([params objectAtIndex:0], NSString);
    NSNumber *amount = [params objectAtIndex:0];
    
    NSDictionary *data = nil;
    if (params.count > 1) {
        ENSURE_TYPE([params objectAtIndex:1], NSDictionary);
        data = [params objectAtIndex:1];
    }
    
    [ADBMobile trackLifetimeValueIncrease:[NSDecimalNumber decimalNumberWithDecimal:[amount decimalValue]] data:data];
}

-(void) trackTimedActionStart:(NSArray*)params
{
    ENSURE_TYPE(params, NSArray);
    ENSURE_TYPE([params objectAtIndex:0], NSString);
    NSString *action = [params objectAtIndex:0];
    
    NSDictionary *data = nil;
    if (params.count > 1) {
        ENSURE_TYPE([params objectAtIndex:1], NSDictionary);
        data = [params objectAtIndex:1];
    }
    
    [ADBMobile trackTimedActionStart:action data:data];
}

-(void) trackTimedActionUpdate:(NSArray*)params
{
    ENSURE_TYPE(params, NSArray);
    ENSURE_TYPE([params objectAtIndex:0], NSString);
    NSString *action = [params objectAtIndex:0];
    
    NSDictionary *data = nil;
    if (params.count > 1) {
        ENSURE_TYPE([params objectAtIndex:1], NSDictionary);
        data = [params objectAtIndex:1];
    }
    
    [ADBMobile trackTimedActionUpdate:action data:data];
}

-(void) trackTimedActionEnd:(NSArray*)params
{
    ENSURE_TYPE(params, NSArray);
    ENSURE_TYPE([params objectAtIndex:0], NSString);
    NSString *action = [params objectAtIndex:0];
    
    KrollCallback *callback = nil;
    if (params.count > 1) {
        ENSURE_TYPE([params objectAtIndex:1], KrollCallback);
        callback = [params objectAtIndex:1];
    }
    
    [ADBMobile trackTimedActionEnd:action
                             logic:^BOOL(NSTimeInterval inAppDuration, NSTimeInterval totalDuration, NSMutableDictionary *data) {
                                 if (callback == nil) {
                                     return YES;
                                 }
                                 
                                 NSMutableDictionary *callbackParams = [NSMutableDictionary dictionary];
                                 [callbackParams setObject:[NSNumber numberWithDouble:inAppDuration]
                                                    forKey:@"inAppDuration"];
                                 [callbackParams setObject:[NSNumber numberWithDouble:totalDuration]
                                                    forKey:@"totalDuration"];
                                 [callbackParams setObject:data
                                                    forKey:@"data"];
                                 
                                 // TODO: The native code could add new values to 'data';
                                 // we are missing out on this functionality for now.
                                 
                                 id callbackRetVal = [callback call:[NSArray arrayWithObject:callbackParams]
                                                         thisObject:nil];
                                 
                                 if (callbackRetVal != nil) {
                                     return [callbackRetVal boolValue];
                                 } else {
                                     return YES;
                                 }
                             }];
}

-(NSNumber*) trackTimedActionExists:(id)action
{
    ENSURE_SINGLE_ARG(action, NSString);
    return NUMBOOL([ADBMobile trackingTimedActionExists:action]);
}

-(NSString*) trackingIdentifier
{
    // Note: This looks like a simple property, but the header file
    // comment says "This method can cause a blocking network call and should
    // not be used from a UI thread." I didn't think we could shift this
    // responsibility to the user of the module, so I created an async function,
    // retrieveTrackingId, see below.
    return [ADBMobile trackingIdentifier];
}

-(void) retrieveTrackingId:(id)callback
{
    ENSURE_SINGLE_ARG(callback, KrollCallback);
    
    if (callback) {
        dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_BACKGROUND,0), ^{
            [callback call:[NSArray arrayWithObject:[ADBMobile trackingIdentifier]] thisObject:nil];
        });
    }
}

-(NSString*) visitorMarketingCloudId
{
    // Note: This looks like a simple property, but the header file
    // comment says "This method can cause a blocking network call and should
    // not be used from a UI thread." I didn't think we could shift this
    // responsibility to the user of the module, so I created an async function,
    // retrieveVisitorMarketingCloudID, see below.
    return [ADBMobile visitorMarketingCloudID];
}

-(void) retrieveVisitorMarketingCloudID:(id)callback
{
    ENSURE_SINGLE_ARG(callback, KrollCallback);
    
    if (callback) {
        dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_BACKGROUND,0), ^{
            [callback call:[NSArray arrayWithObject:[ADBMobile visitorMarketingCloudID]] thisObject:nil];
        });
    }
}

-(void) trackingSendQueuedHits:(id)unused
{
    [ADBMobile trackingSendQueuedHits];
}

-(void) trackingClearQueue:(id)unused
{
    [ADBMobile trackingClearQueue];
}


#pragma mark - Media Analytics

- (TiOmnitureMediaSettings*) createMediaSettings:(id)params
{
    ENSURE_SINGLE_ARG(params, NSDictionary);
    
    NSString *name = [params objectForKey:@"name"];
    NSNumber *length = [params objectForKey:@"length"];
    NSString *playerName = [params objectForKey:@"playerName"];
    NSString *playerId = [params objectForKey:@"playerId"];
    
    ADBMediaSettings *mediaSettings = [ADBMobile mediaCreateSettingsWithName:name
                                                                      length:[length doubleValue]
                                                                  playerName:playerName
                                                                    playerID:playerId];

    if (mediaSettings) {
        TiOmnitureMediaSettings *proxy = [[[TiOmnitureMediaSettings alloc] initWithMediaSettings:mediaSettings] autorelease];
        [proxy setValuesForKeysWithDictionary:params];
        return proxy;
    }
    return nil;
}

- (TiOmnitureMediaSettings*) createAdMediaSettings:(id)params
{
    ENSURE_SINGLE_ARG(params, NSDictionary);
    
    NSString *name = [params objectForKey:@"name"];
    NSNumber *length = [params objectForKey:@"length"];
    NSString *playerName = [params objectForKey:@"playerName"];
    NSString *parentName = [params objectForKey:@"parentName"];
    NSString *parentPod = [params objectForKey:@"parentPod"];
    NSNumber *parentPodPosition = [params objectForKey:@"parentPodPosition"];
    NSString *cpm = [params objectForKey:@"cpm"];
    
    ADBMediaSettings *mediaSettings = [ADBMobile mediaAdCreateSettingsWithName:name
                                                          length:[length doubleValue]
                                                      playerName:playerName
                                                      parentName:parentName
                                                       parentPod:parentPod
                                               parentPodPosition:[parentPodPosition doubleValue]
                                                             CPM:cpm];
    if (mediaSettings) {
        return [[[TiOmnitureMediaSettings alloc] initWithMediaSettings:mediaSettings] autorelease];
    }
    return nil;
}

-(void) mediaOpen:(NSArray*)params
{
    TiOmnitureMediaSettings *mediaSettingsProxy = [params objectAtIndex:0];
    ENSURE_TYPE(mediaSettingsProxy, TiOmnitureMediaSettings);
    
    KrollCallback *callback = nil;
    
    if (params.count > 1) {
        callback = [params objectAtIndex:1];
    }
    
    [ADBMobile mediaOpenWithSettings:mediaSettingsProxy.adbMediaSettings
                            callback:^(ADBMediaState *mediaState) {
                                if (callback) {
                                    TiOmnitureMediaState *mediaStateProxy = [[[TiOmnitureMediaState alloc] initWithMediaState:mediaState] autorelease];
                                    [callback call:[NSArray arrayWithObject:mediaStateProxy] thisObject:nil];
                                }
                        }];
}

-(void) mediaClose:(id)name
{
    ENSURE_SINGLE_ARG(name, NSString);
    [ADBMobile mediaClose:name];
}

-(void) mediaPlay:(NSArray*)params
{
    NSString *name = [params objectAtIndex:0];
    ENSURE_TYPE(name, NSString);
    
    NSNumber* offset = [params objectAtIndex:1];
    ENSURE_TYPE(offset, NSNumber);
    
    [ADBMobile mediaPlay:name offset:[offset doubleValue]];
}

-(void) mediaComplete:(NSArray*)params
{
    NSString *name = [params objectAtIndex:0];
    ENSURE_TYPE(name, NSString);
    
    NSNumber* offset = [params objectAtIndex:1];
    ENSURE_TYPE(offset, NSNumber);
    
    [ADBMobile mediaComplete:name offset:[offset doubleValue]];
}

-(void) mediaStop:(NSArray*)params
{
    NSString *name = [params objectAtIndex:0];
    ENSURE_TYPE(name, NSString);
    
    NSNumber* offset = [params objectAtIndex:1];
    ENSURE_TYPE(offset, NSNumber);
    
    [ADBMobile mediaStop:name offset:[offset doubleValue]];
}

-(void) mediaClick:(NSArray*)params
{
    NSString *name = [params objectAtIndex:0];
    ENSURE_TYPE(name, NSString);
    
    NSNumber* offset = [params objectAtIndex:1];
    ENSURE_TYPE(offset, NSNumber);
    
    [ADBMobile mediaClick:name offset:[offset doubleValue]];
}

-(void) mediaTrack:(NSArray*)params
{
    NSString *name = [params objectAtIndex:0];
    ENSURE_TYPE(name, NSString);
    
    NSDictionary* data = nil;
    
    if (params.count > 1) {
        data = [params objectAtIndex:1];
        ENSURE_TYPE(data, NSDictionary);
    }
    
    [ADBMobile mediaTrack:name data:data];
}

-(void) targetLoadRequest:(NSArray*)params
{
    TiOmnitureTargetLocationRequest *requestProxy = [params objectAtIndex:0];
    ENSURE_TYPE(requestProxy, TiOmnitureTargetLocationRequest);
    
    KrollCallback *callback = nil;
    
    if (params.count > 1) {
        callback = [params objectAtIndex:1];
        ENSURE_TYPE(callback, KrollCallback);
    }
    
    [ADBMobile targetLoadRequest:requestProxy.adbTargetLocationRequest
                        callback:^(NSString *content) {
                            if (callback) {
                                [callback call:[NSArray arrayWithObject:content]
                                    thisObject:nil];
                            }
                        }
     ];
}

-(TiOmnitureTargetLocationRequest*) createTargetLocationRequest:(id)params
{
    ENSURE_SINGLE_ARG(params, NSDictionary);
    
    NSString *name = [params objectForKey:@"name"];
    NSString *defaultContent = [params objectForKey:@"defaultContent"];
    NSDictionary *parameters = [params objectForKey:@"parameters"];
    
    ENSURE_TYPE(name, NSString);
    ENSURE_TYPE(defaultContent, NSString);
    ENSURE_TYPE_OR_NIL(parameters, NSDictionary);
    
    return [[[TiOmnitureTargetLocationRequest alloc]
                initWithTargetLocationRequest:[ADBMobile targetCreateRequestWithName:name
                                                                      defaultContent:defaultContent
                                                                          parameters:parameters]]
            autorelease];
}

-(TiOmnitureTargetLocationRequest*) createTargetOrderConfirmRequest:(id)params
{
    ENSURE_SINGLE_ARG(params, NSDictionary);
    
    NSString *name = [params objectForKey:@"name"];
    NSString *orderId = [params objectForKey:@"orderId"];
    NSString *orderTotal = [params objectForKey:@"orderTotal"];
    NSString *productPurchasedId = [params objectForKey:@"productPurchasedId"];
    NSDictionary *parameters = [params objectForKey:@"parameters"];
    
    ENSURE_TYPE(name, NSString);
    ENSURE_TYPE(orderId, NSString);
    ENSURE_TYPE_OR_NIL(orderTotal, NSString);
    ENSURE_TYPE_OR_NIL(productPurchasedId, NSString);
    ENSURE_TYPE_OR_NIL(parameters, NSDictionary);
    
    return [[[TiOmnitureTargetLocationRequest alloc]
             initWithTargetLocationRequest:[ADBMobile targetCreateOrderConfirmRequestWithName:name
                                                                                      orderId:orderId
                                                                                   orderTotal:orderTotal
                                                                           productPurchasedId:productPurchasedId
                                                                                   parameters:parameters]]
            autorelease];
}

-(void) targetClearCookies:(id)unused
{
    [ADBMobile targetClearCookies];
}

-(NSDictionary*) audienceVisitorProfile
{
    // TODO: Can't find any details about what's in the dictonary; we may
    // need to do some conversions.
    return [ADBMobile audienceVisitorProfile];
}

-(NSString *) audienceDpid
{
    return [ADBMobile audienceDpid];
}

-(NSString *) audienceDpuuid
{
    return [ADBMobile audienceDpuuid];
}

-(void)setAudienceIds:(NSArray*)params
{
    NSString *dpid = [params objectAtIndex:0];
    NSString *dpuuid = [params objectAtIndex:1];
    
    ENSURE_TYPE(dpid, NSString);
    ENSURE_TYPE(dpuuid, NSString);
    
    [ADBMobile audienceSetDpid:dpid dpuuid:dpuuid];
}

-(void) audienceSignalWithData:(NSArray*)params
{
    NSDictionary* data = nil;
    if (params.count > 0) {
        data = [params objectAtIndex:0];
    }
    ENSURE_TYPE(data, NSDictionary);
    
    KrollCallback *callback = nil;
    if (params.count > 1) {
        callback = [params objectAtIndex:1];
    }
    
    [ADBMobile audienceSignalWithData:data callback:^(NSDictionary *response) {
        if (callback) {
            [callback call:[NSArray arrayWithObject:response] thisObject:nil];
        }
    }];
}

-(void) audienceReset:(id)unused
{
    [ADBMobile audienceReset];
}

-(void) visitorSyncIdentifiers:(id)identifiers
{
    ENSURE_SINGLE_ARG(identifiers, NSDictionary);
    [ADBMobile visitorSyncIdentifiers:identifiers];
}

@end
