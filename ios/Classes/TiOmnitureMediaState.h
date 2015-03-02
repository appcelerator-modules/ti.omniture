/**
 * Appcelerator Titanium Mobile Modules
 * Copyright (c) 2010-2015 by Appcelerator, Inc. All Rights Reserved.
 * Proprietary and Confidential - This source code is not for redistribution
 */

#import "TiProxy.h"
#import "ADBMobile.h"

@interface TiOmnitureMediaState : TiProxy

@property(retain,nonatomic,readonly) ADBMediaState *adbMediaState;

-(TiOmnitureMediaState*)initWithMediaState:(ADBMediaState*)mediaState;

@end
