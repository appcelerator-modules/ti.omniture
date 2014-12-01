/**
 * Appcelerator Titanium Mobile Modules
 * Copyright (c) 2010-2013 by Appcelerator, Inc. All Rights Reserved.
 * Proprietary and Confidential - This source code is not for redistribution
 */

#import "TiModule.h"
#import "TiOmnitureSession.h"
#import "TiOmnitureMediaTracker.h"

@interface TiOmnitureModule : TiModule 
{
@private
    TiOmnitureSession *session;
    TiOmnitureMediaTracker *mediaTracker;
}

@end
