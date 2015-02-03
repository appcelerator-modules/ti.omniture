//
//  TiOmnitureMediaState.h
//  omniture
//
//  Created by Zsombor Papp on 2/2/15.
//
//

#import "TiProxy.h"
#import "ADBMobile.h"

@interface TiOmnitureMediaState : TiProxy

@property(retain,nonatomic,readonly) ADBMediaState *adbMediaState;

-(TiOmnitureMediaState*)initWithMediaState:(ADBMediaState*)mediaState;

@end
