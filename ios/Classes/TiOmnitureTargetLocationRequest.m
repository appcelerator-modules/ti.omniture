//
//  TiOmnitureTargetLocationRequest.m
//  omniture
//
//  Created by Zsombor Papp on 2/2/15.
//
//

#import "TiOmnitureTargetLocationRequest.h"

@implementation TiOmnitureTargetLocationRequest

@synthesize adbTargetLocationRequest = _adbTargetLocationRequest;

-(TiOmnitureTargetLocationRequest*)initWithTargetLocationRequest:(ADBTargetLocationRequest*)targetLocationRequest
{
    self = [super init];
    if (self) {
        _adbTargetLocationRequest = [targetLocationRequest retain];
    }
    return self;
}

@end
