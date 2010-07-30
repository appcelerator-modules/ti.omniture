//
//  OMAppMeasurement.h
//  OMAppMeasurement
//
//  Copyright 1996-2010. Adobe, Inc. All Rights Reserved

#import <UIKit/UIKit.h>

#pragma mark OMAppMeasurement interface

@class OMAppMeasurementModuleMedia;
@protocol OMAppMeasurementDelegate;

@interface OMAppMeasurement : NSObject {

@private
	BOOL _ssl;
	BOOL _linkLeaveQueryString;
	BOOL _debugTracking;
	BOOL _usePlugins;
	NSString * _version;
	NSMutableArray * _requiredVarList;
	NSMutableArray * _accountVarList;
	NSMutableArray * _accountConfigList;
	NSMutableDictionary * _accountVars;
	id<OMAppMeasurementDelegate> _delegate;
	OMAppMeasurementModuleMedia * _Media;
}

#pragma mark OMAppMeasurement public methods

+ (OMAppMeasurement *)getInstance;
- (id)init;
- (id)initWithDelegate:(id<OMAppMeasurementDelegate>)delegate;
- (void)track;
- (void)track:(NSDictionary *)variableOverrides;
- (void)trackLink:(NSString *)linkURL linkType:(NSString *)linkType linkName:(NSString *)linkName;
- (void)trackLink:(NSString *)linkURL linkType:(NSString *)linkType linkName:(NSString *)linkName variableOverrides:(NSDictionary *)variableOverrides;
- (void)clearVars;

// reserved for internal use only
- (BOOL)isSet:(NSString *)var;
- (NSString *)escape:(NSString *)unescapedString;

#pragma mark OMAppMeasurement properties

@property(readwrite, copy) NSString * account;
@property(readwrite, copy) NSString * linkURL;
@property(readwrite, copy) NSString * linkName;
@property(readwrite, copy) NSString * linkType;
@property(readwrite, copy) NSString * linkTrackVars;
@property(readwrite, copy) NSString * linkTrackEvents;
@property(readwrite, copy) NSString * dc;
@property(readwrite, copy) NSString * trackingServer;
@property(readwrite, copy) NSString * trackingServerSecure;
@property(readwrite, copy) NSString * userAgent;
@property(readwrite, copy) NSString * dynamicVariablePrefix;
@property(readwrite, copy) NSString * visitorID;
@property(readwrite, copy) NSString * charSet;
@property(readwrite, copy) NSString * visitorNamespace;
@property(readwrite, copy) NSString * pageName;
@property(readwrite, copy) NSString * pageURL;
@property(readwrite, copy) NSString * referrer;
@property(readwrite, copy) NSString * currencyCode;
@property(readwrite, copy) NSString * purchaseID;
@property(readwrite, copy) NSString * channel;
@property(readwrite, copy) NSString * server;
@property(readwrite, copy) NSString * pageType;
@property(readwrite, copy) NSString * transactionID;
@property(readwrite, copy) NSString * campaign;
@property(readwrite, copy) NSString * state;
@property(readwrite, copy) NSString * zip;
@property(readwrite, copy) NSString * events;
@property(readwrite, copy) NSString * products;
@property(readwrite, copy) NSString * list1;
@property(readwrite, copy) NSString * list2;
@property(readwrite, copy) NSString * list3;
@property(readwrite, copy) NSString * hier1;
@property(readwrite, copy) NSString * hier2;
@property(readwrite, copy) NSString * hier3;
@property(readwrite, copy) NSString * hier4;
@property(readwrite, copy) NSString * hier5;
@property(readwrite, copy) NSString * prop1;
@property(readwrite, copy) NSString * prop2;
@property(readwrite, copy) NSString * prop3;
@property(readwrite, copy) NSString * prop4;
@property(readwrite, copy) NSString * prop5;
@property(readwrite, copy) NSString * prop6;
@property(readwrite, copy) NSString * prop7;
@property(readwrite, copy) NSString * prop8;
@property(readwrite, copy) NSString * prop9;
@property(readwrite, copy) NSString * prop10;
@property(readwrite, copy) NSString * prop11;
@property(readwrite, copy) NSString * prop12;
@property(readwrite, copy) NSString * prop13;
@property(readwrite, copy) NSString * prop14;
@property(readwrite, copy) NSString * prop15;
@property(readwrite, copy) NSString * prop16;
@property(readwrite, copy) NSString * prop17;
@property(readwrite, copy) NSString * prop18;
@property(readwrite, copy) NSString * prop19;
@property(readwrite, copy) NSString * prop20;
@property(readwrite, copy) NSString * prop21;
@property(readwrite, copy) NSString * prop22;
@property(readwrite, copy) NSString * prop23;
@property(readwrite, copy) NSString * prop24;
@property(readwrite, copy) NSString * prop25;
@property(readwrite, copy) NSString * prop26;
@property(readwrite, copy) NSString * prop27;
@property(readwrite, copy) NSString * prop28;
@property(readwrite, copy) NSString * prop29;
@property(readwrite, copy) NSString * prop30;
@property(readwrite, copy) NSString * prop31;
@property(readwrite, copy) NSString * prop32;
@property(readwrite, copy) NSString * prop33;
@property(readwrite, copy) NSString * prop34;
@property(readwrite, copy) NSString * prop35;
@property(readwrite, copy) NSString * prop36;
@property(readwrite, copy) NSString * prop37;
@property(readwrite, copy) NSString * prop38;
@property(readwrite, copy) NSString * prop39;
@property(readwrite, copy) NSString * prop40;
@property(readwrite, copy) NSString * prop41;
@property(readwrite, copy) NSString * prop42;
@property(readwrite, copy) NSString * prop43;
@property(readwrite, copy) NSString * prop44;
@property(readwrite, copy) NSString * prop45;
@property(readwrite, copy) NSString * prop46;
@property(readwrite, copy) NSString * prop47;
@property(readwrite, copy) NSString * prop48;
@property(readwrite, copy) NSString * prop49;
@property(readwrite, copy) NSString * prop50;
@property(readwrite, copy) NSString * eVar1;
@property(readwrite, copy) NSString * eVar2;
@property(readwrite, copy) NSString * eVar3;
@property(readwrite, copy) NSString * eVar4;
@property(readwrite, copy) NSString * eVar5;
@property(readwrite, copy) NSString * eVar6;
@property(readwrite, copy) NSString * eVar7;
@property(readwrite, copy) NSString * eVar8;
@property(readwrite, copy) NSString * eVar9;
@property(readwrite, copy) NSString * eVar10;
@property(readwrite, copy) NSString * eVar11;
@property(readwrite, copy) NSString * eVar12;
@property(readwrite, copy) NSString * eVar13;
@property(readwrite, copy) NSString * eVar14;
@property(readwrite, copy) NSString * eVar15;
@property(readwrite, copy) NSString * eVar16;
@property(readwrite, copy) NSString * eVar17;
@property(readwrite, copy) NSString * eVar18;
@property(readwrite, copy) NSString * eVar19;
@property(readwrite, copy) NSString * eVar20;
@property(readwrite, copy) NSString * eVar21;
@property(readwrite, copy) NSString * eVar22;
@property(readwrite, copy) NSString * eVar23;
@property(readwrite, copy) NSString * eVar24;
@property(readwrite, copy) NSString * eVar25;
@property(readwrite, copy) NSString * eVar26;
@property(readwrite, copy) NSString * eVar27;
@property(readwrite, copy) NSString * eVar28;
@property(readwrite, copy) NSString * eVar29;
@property(readwrite, copy) NSString * eVar30;
@property(readwrite, copy) NSString * eVar31;
@property(readwrite, copy) NSString * eVar32;
@property(readwrite, copy) NSString * eVar33;
@property(readwrite, copy) NSString * eVar34;
@property(readwrite, copy) NSString * eVar35;
@property(readwrite, copy) NSString * eVar36;
@property(readwrite, copy) NSString * eVar37;
@property(readwrite, copy) NSString * eVar38;
@property(readwrite, copy) NSString * eVar39;
@property(readwrite, copy) NSString * eVar40;
@property(readwrite, copy) NSString * eVar41;
@property(readwrite, copy) NSString * eVar42;
@property(readwrite, copy) NSString * eVar43;
@property(readwrite, copy) NSString * eVar44;
@property(readwrite, copy) NSString * eVar45;
@property(readwrite, copy) NSString * eVar46;
@property(readwrite, copy) NSString * eVar47;
@property(readwrite, copy) NSString * eVar48;
@property(readwrite, copy) NSString * eVar49;
@property(readwrite, copy) NSString * eVar50;
@property(readwrite) BOOL ssl;
@property(readwrite) BOOL linkLeaveQueryString;
@property(readwrite) BOOL debugTracking;
@property(readwrite) BOOL usePlugins;
@property(readwrite, assign) id<OMAppMeasurementDelegate> delegate;
@property(readwrite, retain) OMAppMeasurementModuleMedia * Media;

@end

#pragma mark OMAppMeasurementDelegate protocol

@protocol OMAppMeasurementDelegate<NSObject>
@optional

// Called before the track data is sent to Omniture collection servers
- (void)appMeasurementDoPlugins:(OMAppMeasurement *)s;

@end

#pragma mark Modules

#pragma mark OMAppMeasurementModuleMedia interface

@protocol OMAppMeasurementModuleMediaDelegate;

@interface OMAppMeasurementModuleMedia : NSObject {
	
@private
	OMAppMeasurement * _s;
	NSMutableDictionary * _list;
	NSString * _playerName;
	NSString * _trackVars;
	NSString * _trackEvents;
	int _trackSeconds;
	NSString * _trackMilestones;
	BOOL _trackAtCuePoints;
	NSString * _cuePoints;
	id<OMAppMeasurementModuleMediaDelegate> _delegate;
}

#pragma mark OMAppMeasurementModuleMedia public methods

- (id)init:(OMAppMeasurement *)s;
- (void)open:(NSString *)name length:(double)length playerName:(NSString *)playerName cuePoints:(NSString *)cuePoints playerID:(NSString *)playerID;
- (void)open:(NSString *)name length:(double)length playerName:(NSString *)playerName cuePoints:(NSString *)cuePoints;
- (void)open:(NSString *)name length:(double)length playerName:(NSString *)playerName;
- (void)close:(NSString *)name;
- (void)play:(NSString *)name offset:(double)offset;
- (void)stop:(NSString *)name offset:(double)offset;
- (void)track:(NSString *)name;
- (void)cuePoint:(NSString *)name offset:(double)offset cuePointName:(NSString *)cuePointName;
- (void)cuePoint:(NSString *)name offset:(double)offset;

#pragma mark OMAppMeasurementModuleMedia properties

@property(readwrite, copy) NSString * playerName;
@property(readwrite, copy) NSString * trackVars;
@property(readwrite, copy) NSString * trackEvents;
@property(readwrite) int trackSeconds;
@property(readwrite, copy) NSString * trackMilestones;
@property(readwrite) BOOL trackAtCuePoints;
@property(readwrite, copy) NSString * cuePoints;
@property(readwrite, assign) id<OMAppMeasurementModuleMediaDelegate> delegate;

@end

#pragma mark OMAppMeasurementModuleMediaState (see OMAppMeasurementModuleMediaDelegate protocol)

@interface OMAppMeasurementModuleMediaState : NSObject {

@private
	NSString * _name;
	double _length;
	NSString * _playerName;
	NSString * _mediaEvent;
	BOOL _eventFirstTime;
	NSDate * _openTime;
	double _offset;
	NSString * _offsetName;
	double _percent;
	double _timePlayed;
	double _milestone;
}

#pragma mark OMAppMeasurementModuleMediaState properties

@property(readwrite, copy) NSString * name;
@property(readwrite) double length;
@property(readwrite, copy) NSString * playerName;
@property(readwrite, copy) NSString * mediaEvent;
@property(readwrite) BOOL eventFirstTime;
@property(readwrite, copy) NSDate * openTime;
@property(readwrite) double offset;
@property(readwrite, copy) NSString * offsetName;
@property(readwrite) double percent;
@property(readwrite) double timePlayed;
@property(readwrite) double milestone;

@end

#pragma mark OMAppMeasurementModuleMediaDelegate protocol

@protocol OMAppMeasurementModuleMediaDelegate<NSObject>
@optional

// Media Monitor
- (void)appMeasurementModuleMediaMonitor:(OMAppMeasurement *)s media:(OMAppMeasurementModuleMediaState *)media;

@end
