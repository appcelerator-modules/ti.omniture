//
//  TiOmnitureMediaState.m
//  omniture
//
//  Created by Zsombor Papp on 2/2/15.
//
//

#import "TiOmnitureMediaState.h"

@implementation TiOmnitureMediaState

@synthesize adbMediaState = _adbMediaState;

-(TiOmnitureMediaState*)initWithMediaState:(ADBMediaState*)mediaState
{
    self = [super init];
    if (self) {
        _adbMediaState = [mediaState retain];
    }
    return self;
}

@end
