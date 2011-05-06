/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2010 by Appcelerator, Inc. All Rights Reserved.
 */

#import "TiOmnitureSession.h"
#import "TiUtils.h"
#import "TiApp.h"

@implementation TiOmnitureSession

-(void)_destroy
{
	RELEASE_TO_NIL(session);
	[super _destroy];
}

-(OMAppMeasurement*)session
{
	if (session == nil)
	{
		session = [[OMAppMeasurement alloc] init];
	}
	return session;
}

MAKE_SYSTEM_STR(CUSTOM_LINK,@"o");
MAKE_SYSTEM_STR(FILE_DOWNLOAD,@"d");
MAKE_SYSTEM_STR(EXIT_LINK,@"e");

-(void)configureFromDictionary:(NSDictionary*)properties
{
	OMAppMeasurement *s = [self session];
	s.debugTracking = YES;
	s.account = [properties objectForKey:@"account"];
	s.currencyCode = [TiUtils stringValue:@"currencyCode" properties:properties def:@"USD"];
	s.trackingServer = [properties objectForKey:@"trackingServer"];
	
	// Add support for the trackingServerSecure parameter. Omniture documentation says that
	// this parameter is required if you specify trackingServer, but so far it seems to
	// work even if it is not provided.
	// Note that we need to check if it is provided since the session object will throw an
	// exception if we try to set it to nil
	id trackingServerSecure = [properties objectForKey:@"trackingServerSecure"];
	if (trackingServerSecure != nil)
		s.trackingServerSecure = trackingServerSecure;
	
	s.userAgent = [[TiApp app] userAgent];
	s.pageURL = @"";
	s.pageName = @"";
	s.ssl = YES;
	
	NSMutableArray *keys = [NSMutableArray arrayWithObjects:@"dc",
							@"linkTrackEvents",
							@"linkTrackVars",
							@"userAgent",
							@"visitorNamespace",
							@"visitorID",
							@"linkLeaveQueryString",
							@"ssl",
							@"pageName",
							@"pageURL",
							@"referrer",
							@"purchaseID",
							@"transactionID",
							@"channel",
							@"server",
							@"pageType",
							@"dynamicVariablePrefix",
							@"variableProvider",
							@"campaign",
							@"state",
							@"zip",
							@"events",
							@"products",
							// Additional properties provided by Fred
							@"pe",
							@"pev1",
							@"pev2",
							nil];
	for (int c=0;c<50;c++)
	{
		[keys addObject:[NSString stringWithFormat:@"prop%d",c+1]];
		[keys addObject:[NSString stringWithFormat:@"eVar%d",c+1]];
		if (c < 5)
		{
			[keys addObject:[NSString stringWithFormat:@"hier%d",c+1]];
		}
	}
	
	id nullClass = [NSNull class];
	for (id key in keys)
	{
		id value = [properties objectForKey:key];
		if (value!=nil)
		{
			if ([value isKindOfClass:nullClass])
			{
				[s setValue:nil forKey:key];
			}
			else
			{
				[s setValue:value forKey:key];
			}
		}
	}
}

-(void)_initWithProperties:(NSDictionary*)properties
{
	[super _initWithProperties:properties];
	[self configureFromDictionary:properties];
}

-(void)track:(id)args
{
	ENSURE_SINGLE_ARG_OR_NIL(args,NSDictionary);
	ENSURE_UI_THREAD_1_ARG(args);
	
	// this must be called on UI thread but docs say it goes to background
	if (args!=nil)
	{
		[[self session] track:args];
	}
	else
	{
		[[self session] track];
	}
}

// We can support two different forms of calling this method.
//
// The first form provides for the specification of a dictionary of KV pairs. This helps those that
// want to maintain a dictionary of KV pairs and then pass it straight in to this method.
//
//   session.trackLink({url:<url> type:<type> name:<name> overrides:{events:<event>,pageName:<page name>}});
//
// The second form is simply a variable list of arguments of KV pairs
//
//   session.trackLink({url:<url> type:<type> name:<name> events:<events> pageName:<page name>"});
//
-(void)trackLink:(id)args
{
	ENSURE_SINGLE_ARG(args,NSDictionary);
	ENSURE_UI_THREAD_1_ARG(args);	
	
	NSString *linkURL = [args objectForKey:@"url"];
	NSString *linkType = [args objectForKey:@"type"];
	NSString *linkName = [args objectForKey:@"name"];
	
	// Retrieve the optional overrides parameter and see which form was used to call this method
	NSDictionary *overrides = [args objectForKey:@"overrides"];
	ENSURE_TYPE_OR_NIL(overrides,NSDictionary);
	if (overrides!=nil)
	{
		[[self session] trackLink:linkURL linkType:linkType linkName:linkName variableOverrides:overrides];
	}
	else 
	{
		// To support the second form of calling this method we can simply pass the args parameter
		// to the trackLink method. The url, type, and name parameters (which are part of the args
		// dictionary) are ignored by the Omniture method, but it does pick up any additional KV pairs.
		[[self session] trackLink:linkURL linkType:linkType linkName:linkName variableOverrides:args];
	}
}

-(void)clearVars:(id)args
{
	ENSURE_UI_THREAD_1_ARG(args);
	[[self session] clearVars];
}


@end
