/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2010 by Appcelerator, Inc. All Rights Reserved.
 */

#import "TiProxy.h"
#import "AppMeasurement.h"

@interface TiOmnitureSession : TiProxy {
@private
	AppMeasurement *session;
}

@property(nonatomic,readonly) NSString *CUSTOM_LINK;
@property(nonatomic,readonly) NSString *FILE_DOWNLOAD;
@property(nonatomic,readonly) NSString *EXIT_LINK;

-(AppMeasurement*)session;

@end
