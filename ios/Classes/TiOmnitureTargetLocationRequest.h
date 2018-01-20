/**
 * Appcelerator Titanium Mobile Modules
 * Copyright (c) 2010-present by Appcelerator, Inc. All Rights Reserved.
 * Proprietary and Confidential - This source code is not for redistribution
 */

#import "ADBMobile.h"
#import "TiProxy.h"

@interface TiOmnitureTargetLocationRequest : TiProxy

@property (retain, nonatomic, readonly) ADBTargetLocationRequest *adbTargetLocationRequest;

- (TiOmnitureTargetLocationRequest *)initWithTargetLocationRequest:(ADBTargetLocationRequest *)targetLocationRequest;

@end
