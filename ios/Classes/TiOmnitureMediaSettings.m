/**
 * Appcelerator Titanium Mobile Modules
 * Copyright (c) 2010-2015 by Appcelerator, Inc. All Rights Reserved.
 * Proprietary and Confidential - This source code is not for redistribution
 */

#import "TiOmnitureMediaSettings.h"

@implementation TiOmnitureMediaSettings

@synthesize adbMediaSettings = _adbMediaSettings;

-(TiOmnitureMediaSettings*)initWithMediaSettings:(ADBMediaSettings*)mediaSettings
{
    self = [super init];
    if (self) {
        _adbMediaSettings = [mediaSettings retain];
    }
    return self;
}

@end