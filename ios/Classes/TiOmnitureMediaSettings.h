/**
 * Appcelerator Titanium Mobile Modules
 * Copyright (c) 2010-2015 by Appcelerator, Inc. All Rights Reserved.
 * Proprietary and Confidential - This source code is not for redistribution
 */

#import "TiProxy.h"
#import "ADBMobile.h"

@interface TiOmnitureMediaSettings : TiProxy

@property(retain,nonatomic,readonly) ADBMediaSettings *adbMediaSettings;

-(TiOmnitureMediaSettings*)initWithMediaSettings:(ADBMediaSettings*)mediaSettings;

@end
