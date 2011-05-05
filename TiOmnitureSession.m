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
	//s.trackingServerSecure = [properties objectForKey:@"trackingServerSecure"];
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

-(void)trackLink:(id)args
{
	ENSURE_SINGLE_ARG(args,NSDictionary);
	ENSURE_UI_THREAD_1_ARG(args);	
	
	NSString *linkURL = [args objectForKey:@"url"];
	NSString *linkType = [args objectForKey:@"type"];
	NSString *linkName = [args objectForKey:@"name"];
	
	[[self session] trackLink:linkURL linkType:linkType linkName:linkName];
}

-(void)clearVars:(id)args
{
	ENSURE_UI_THREAD_1_ARG(args);
	[[self session] clearVars];
}


@end
