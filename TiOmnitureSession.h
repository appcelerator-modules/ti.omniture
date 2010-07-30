/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2010 by Appcelerator, Inc. All Rights Reserved.
 */

#import "TiProxy.h"
#import "OMAppMeasurement.h"

@interface TiOmnitureSession : TiProxy {
@private
	OMAppMeasurement *session;
}

@property(nonatomic,readonly) NSString *CUSTOM_LINK;
@property(nonatomic,readonly) NSString *FILE_DOWNLOAD;
@property(nonatomic,readonly) NSString *EXIT_LINK;

-(OMAppMeasurement*)session;

@end
