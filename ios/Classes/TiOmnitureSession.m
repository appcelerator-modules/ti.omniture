/**
 * Appcelerator Titanium Mobile Modules
 * Copyright (c) 2010-2013 by Appcelerator, Inc. All Rights Reserved.
 * Proprietary and Confidential - This source code is not for redistribution
 */

#import "TiOmnitureSession.h"
#import "TiUtils.h"
#import "Utils.h"
#import "ADMS_Measurement.h"

@implementation TiOmnitureSession

-(id)init
{
    numberedKeysInProxy = [[NSMutableArray array] retain];
    
    self.modelDelegate = self; // watch for properties being set
    return [super init];
}

-(void)dealloc
{
    RELEASE_TO_NIL(numberedKeysInProxy);
    [super dealloc];
}

-(void)_initWithProperties:(NSDictionary *)properties
{
    [super _initWithProperties:properties];
    
    id reportSuiteIDs = [properties objectForKey:@"reportSuiteIDs"];
    id trackingServer = [properties objectForKey:@"trackingServer"];
    
    if (!reportSuiteIDs || !trackingServer) {
        NSLog(@"[ERROR] reportSuiteIDs and trackingServer are required when calling startSession");
    }
}

#pragma mark - Module Delegate

-(void)propertyChanged:(NSString*)key oldValue:(id)oldValue newValue:(id)newValue proxy:(TiProxy*)proxy
{
    if ([oldValue isEqual:newValue]) {
        // Value didn't really change
        return;
    }
    
    // Special handling for numbered properties
    //    list1-list3;         
    //    hier1-heir5;         
    //    prop1-prop75;
    //    eVar1-eVar75;
    // These numbered props will be set on the session and stored on the proxy
    if ([key hasPrefix:@"eVar"]) {
        [self setNumberedPropertyValue:newValue
                                forKey:key withPrefix:@"eVar"
                           setSelector:@selector(setEvar:toValue:)
                           getSelector:@selector(getEvar:)];
        // saving set numbered properties in array for fast removal from proxy
        [numberedKeysInProxy addObject:key];
        return;
    }
    if ([key hasPrefix:@"prop"]) {
        [self setNumberedPropertyValue:newValue
                                forKey:key withPrefix:@"prop"
                           setSelector:@selector(setProp:toValue:)
                           getSelector:@selector(getProp:)];
        [numberedKeysInProxy addObject:key];
        return;
    }
    if ([key hasPrefix:@"hier"]) {
        [self setNumberedPropertyValue:newValue
                                forKey:key withPrefix:@"hier"
                           setSelector:@selector(setHier:toValue:)
                           getSelector:@selector(getHier:)];
        [numberedKeysInProxy addObject:key];
        return;
    }
    if ([key hasPrefix:@"list"]) {
        [self setNumberedPropertyValue:newValue
                                forKey:key withPrefix:@"list"
                           setSelector:@selector(setListVar:toValue:)
                           getSelector:@selector(getListVar:)];
        [numberedKeysInProxy addObject:key];
        return;
    }
}

#pragma mark - Utils

-(void)setNumberedPropertyValue:(id)newValue forKey:(NSString *)key withPrefix:(NSString *)prefix setSelector:(SEL)setSelector getSelector:(SEL)getSelector
{
    NSString *numberAsString = [key substringFromIndex:[prefix length]];
    NSNumber *num = [NSNumber numberWithInt: [TiUtils intValue:numberAsString]];
    
    ADMS_Measurement *session = [ADMS_Measurement sharedInstance];
    
    [session performSelector:setSelector
                  withObject:[num unsignedIntegerValue]
                  withObject:newValue];
    
    // Reading value back from session and setting it on the proxy
    // If an invalid variable number is retried from the session it will return nil
    // In the case that an invalid variable number is set on the session, it needs to be reflected on the proxy
    // else it will look like the value is set on the session when it is really not.
    id value = [session performSelector:getSelector
                             withObject:[num unsignedIntegerValue]];
    if (!value) {
        [self deleteKey:key]; // deleting key so the value will be undefined, the same as before it was set
        return;
    }
    [self replaceValue:value forKey:key notification:NO];
}

-(void)removeNumberedPropertiesFromProxy
{
    for (NSString *key in numberedKeysInProxy) {
        [self deleteKey:key];
    }
    [numberedKeysInProxy removeAllObjects];
}

#pragma mark - Constants

MAKE_SYSTEM_STR(LINK_TYPE_CUSTOM, @"o");
MAKE_SYSTEM_STR(LINK_TYPE_FILE_DOWNLOAD, @"d");
MAKE_SYSTEM_STR(LINK_TYPE_EXIT, @"e");

MAKE_SYSTEM_PROP(AUTO_TRACK_OPTIONS_NONE, ADMS_AutoTrackOptionsNone);
MAKE_SYSTEM_PROP(AUTO_TRACK_OPTIONS_LIFECYCLE, ADMS_AutoTrackOptionsLifecycle);
MAKE_SYSTEM_PROP(AUTO_TRACK_OPTIONS_NAVIGATION, ADMS_AutoTrackOptionsNavigation);

#pragma mark - Configuration Methods

-(void)setAutoTrackingOptions:(id)args
{
    if (![args isKindOfClass:[NSArray class]]) {
        [[ADMS_Measurement sharedInstance] setAutoTrackingOptions:ADMS_AutoTrackOptionsNone];
        NSLog(@"[DEBUG] AutoTracking disabled");
        return;
    }
    
    // Must handle passing multiple options
    // eg. [measurement setAutoTrackingOptions:ADMS_AutoTrackOptionsLifecycle | ADMS_AutoTrackOptionsNavigation];
    int options = 0;
    for (id mode in args) {
        ENSURE_TYPE(mode, NSNumber);
        options |= [mode intValue];
    }
    
    [[ADMS_Measurement sharedInstance] setAutoTrackingOptions:options];
}

-(void)setOnline:(id)args
{
    [[ADMS_Measurement sharedInstance] setOnline];
}
-(void)setOffline:(id)args
{
    [[ADMS_Measurement sharedInstance] setOffline];
}

#pragma mark - Tracking Methods
#pragma mark Simple Tracking Methods

-(void)trackAppState:(id)args
{
    ENSURE_SINGLE_ARG(args,NSDictionary);
    id appState = [args objectForKey:@"appState"];
    id contextData = [args objectForKey:@"contextData"];
    
    ENSURE_STRING(appState);
    ENSURE_TYPE_OR_NIL(contextData, NSDictionary);
    
    [[ADMS_Measurement sharedInstance] trackAppState:appState withContextData:contextData];
}

-(void)trackEvents:(id)args
{
    ENSURE_SINGLE_ARG(args,NSDictionary);
    id eventNames = [args objectForKey:@"eventNames"];
    id contextData = [args objectForKey:@"contextData"];
    
    ENSURE_STRING(eventNames);
    ENSURE_TYPE_OR_NIL(contextData, NSDictionary);
    
    [[ADMS_Measurement sharedInstance] trackEvents:eventNames withContextData:contextData];
}

#pragma mark Advanced Tracking Methods

-(void)track:(id)args
{
    ENSURE_SINGLE_ARG(args,NSDictionary);
    id contextData = [args objectForKey:@"contextData"];
    id variables = [args objectForKey:@"variables"];
    
    ENSURE_TYPE_OR_NIL(contextData, NSDictionary);
    ENSURE_TYPE_OR_NIL(variables, NSDictionary);
    
    [[ADMS_Measurement sharedInstance] trackWithContextData:contextData variables:variables];
}

-(void)trackLink:(id)args
{
    ENSURE_SINGLE_ARG(args,NSDictionary);
    id linkURL = [args objectForKey:@"linkURL"];
    id linkType = [args objectForKey:@"linkType"];
    id linkName = [args objectForKey:@"linkName"];
    id contextData = [args objectForKey:@"contextData"];
    id variables = [args objectForKey:@"variables"];
    
    ENSURE_STRING(linkURL);
    ENSURE_STRING(linkType);
    ENSURE_TYPE_OR_NIL(linkName, NSString);
    ENSURE_TYPE_OR_NIL(contextData, NSDictionary);
    ENSURE_TYPE_OR_NIL(variables, NSDictionary);
    
    [[ADMS_Measurement sharedInstance] trackLinkURL:linkURL
                                       withLinkType:linkType
                                           linkName:linkName
                                        contextData:contextData
                                          variables:variables];
}

-(void)trackLight:(id)args
{
    ENSURE_SINGLE_ARG(args,NSDictionary);
    id profileID = [args objectForKey:@"profileID"];
    id storeForSeconds = [args objectForKey:@"storeForSeconds"];
    id incrementBy = [args objectForKey:@"incrementBy"];
    id contextData = [args objectForKey:@"contextData"];
    id variables = [args objectForKey:@"variables"];
    
    ENSURE_STRING(profileID);
    ENSURE_TYPE_OR_NIL(storeForSeconds, NSNumber);
    ENSURE_TYPE_OR_NIL(incrementBy, NSNumber);
    ENSURE_TYPE_OR_NIL(contextData, NSDictionary);
    ENSURE_TYPE_OR_NIL(variables, NSDictionary);
    
    [[ADMS_Measurement sharedInstance] trackLightWithProfileID:profileID
                                               storeForSeconds:storeForSeconds
                                                   incrementBy:incrementBy
                                                   contextData:contextData
                                                     variables:variables];
}

-(void)clearVars:(id)args
{
    // Removing numbered props from the proxy
    // Numbered properties are stored on the proxy and must be removed when they are cleared from the session
    [self removeNumberedPropertiesFromProxy];
    [[ADMS_Measurement sharedInstance] clearVars];
}

-(NSNumber *)trackingQueueSize:(id)args
{
    return [NSNumber numberWithUnsignedInt: [[ADMS_Measurement sharedInstance] trackingQueueSize]];
}

-(void)clearTrackingQueue:(id)args
{
    [[ADMS_Measurement sharedInstance] clearTrackingQueue];
}

#pragma mark - Properties

MAKE_STR_GETTER_SETTER(reportSuiteIDs, setReportSuiteIDs, ADMS_Measurement);
MAKE_STR_GETTER_SETTER(trackingServer, setTrackingServer, ADMS_Measurement);

MAKE_STR_GETTER_SETTER(visitorID,      setVisitorID,     ADMS_Measurement);
MAKE_STR_GETTER_SETTER(charSet,        setCharSet,       ADMS_Measurement);
MAKE_STR_GETTER_SETTER(currencyCode,   setCurrencyCode,  ADMS_Measurement);
MAKE_BOOL_GETTER_SETTER(ssl,           setSsl,           ADMS_Measurement);

MAKE_STR_GETTER_SETTER(purchaseID,     setPurchaseID,    ADMS_Measurement);
MAKE_STR_GETTER_SETTER(transactionID,  setTransactionID, ADMS_Measurement);

MAKE_STR_GETTER_SETTER(appState,       setAppState,      ADMS_Measurement);
MAKE_STR_GETTER_SETTER(channel,        setChannel,       ADMS_Measurement);
MAKE_STR_GETTER_SETTER(appSection,     setAppSection,    ADMS_Measurement);
MAKE_STR_GETTER_SETTER(campaign,       setCampaign,      ADMS_Measurement);
MAKE_STR_GETTER_SETTER(products,       setProducts,      ADMS_Measurement);
MAKE_STR_GETTER_SETTER(events,         setEvents,        ADMS_Measurement);

MAKE_STR_GETTER_SETTER(geoState,       setGeoState,      ADMS_Measurement);
MAKE_STR_GETTER_SETTER(geoZip,         setGeoZip,        ADMS_Measurement);

MAKE_OBJ_GETTER_SETTER(persistentContextData,   setPersistentContextData,   NSDictionary, ADMS_Measurement);
MAKE_DBL_GETTER_SETTER(lifecycleSessionTimeout, setLifecycleSessionTimeout,               ADMS_Measurement);

MAKE_STR_GETTER_SETTER(linkTrackVars,           setLinkTrackVars,          ADMS_Measurement);
MAKE_STR_GETTER_SETTER(linkTrackEvents,         setLinkTrackEvents,        ADMS_Measurement);
MAKE_STR_GETTER_SETTER(lightTrackVars,          setLightTrackVars,         ADMS_Measurement);
MAKE_BOOL_GETTER_SETTER(offlineTrackingEnabled, setOfflineTrackingEnabled, ADMS_Measurement);
MAKE_INT_GETTER_SETTER(offlineHitLimit,         setOfflineHitLimit,        ADMS_Measurement);

@end
