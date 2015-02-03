//
//  TiOmnitureTargetLocationRequest.h
//  omniture
//
//  Created by Zsombor Papp on 2/2/15.
//
//

#import "TiProxy.h"
#import "ADBMobile.h"

@interface TiOmnitureTargetLocationRequest : TiProxy

@property(retain,nonatomic,readonly) ADBTargetLocationRequest *adbTargetLocationRequest;

-(TiOmnitureTargetLocationRequest*)initWithTargetLocationRequest:(ADBTargetLocationRequest*)targetLocationRequest;

@end
