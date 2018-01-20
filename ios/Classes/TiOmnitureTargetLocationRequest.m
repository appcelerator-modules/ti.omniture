/**
 * Appcelerator Titanium Mobile Modules
 * Copyright (c) 2010-present by Appcelerator, Inc. All Rights Reserved.
 * Proprietary and Confidential - This source code is not for redistribution
 */

#import "TiOmnitureTargetLocationRequest.h"
#import "Utils.h"

@implementation TiOmnitureTargetLocationRequest

@synthesize adbTargetLocationRequest = _adbTargetLocationRequest;

- (TiOmnitureTargetLocationRequest *)initWithTargetLocationRequest:(ADBTargetLocationRequest *)targetLocationRequest
{
  self = [super init];
  if (self) {
    _adbTargetLocationRequest = targetLocationRequest;
  }
  return self;
}

MAKE_STR_GETTER_SETTER(name, setName, self.adbTargetLocationRequest.name);
MAKE_STR_GETTER_SETTER(defaultContent, setDefaultContent, self.adbTargetLocationRequest.defaultContent);

- (NSDictionary *)parameters
{
  return self.adbTargetLocationRequest.parameters;
}

- (void)setParameters:(NSDictionary *)params
{
  self.adbTargetLocationRequest.parameters = [NSMutableDictionary dictionaryWithDictionary:params];
}

@end
