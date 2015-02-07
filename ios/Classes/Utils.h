/**
 * Appcelerator Titanium Mobile Modules
 * Copyright (c) 2010-2015 by Appcelerator, Inc. All Rights Reserved.
 * Proprietary and Confidential - This source code is not for redistribution
 */

#import "TiUtils.h"

#define MAKE_STR_GETTER_SETTER(name,setName,map) \
-(NSString*)name\
{\
return (NSString*)map;\
}\
-(void)setName:(id)newValue \
{\
ENSURE_SINGLE_ARG(newValue, NSString);\
map = (NSString*)newValue;\
}\

#define MAKE_BOOL_GETTER_SETTER(name,setName,map) \
-(NSNumber*)name\
{\
return NUMBOOL(map);\
}\
-(void)setName:(id)newValue \
{\
ENSURE_SINGLE_ARG(newValue, NSNumber);\
map = [TiUtils boolValue:newValue];\
}\

#define MAKE_DBL_GETTER_SETTER(name,setName,map) \
-(NSNumber*)name\
{\
return [NSNumber numberWithDouble:map];\
}\
-(void)setName:(id)newValue \
{\
ENSURE_SINGLE_ARG(newValue, NSNumber);\
map = [TiUtils doubleValue:newValue];\
}\

#define MAKE_INT_GETTER_SETTER(name,setName,map) \
-(NSNumber*)name\
{\
return [NSNumber numberWithInt:map];\
}\
-(void)setName:(id)newValue \
{\
ENSURE_SINGLE_ARG(newValue, NSNumber);\
map = [TiUtils intValue:newValue];\
}\

#define MAKE_UINT_GETTER_SETTER(name,setName,map) \
-(NSNumber*)name\
{\
return [NSNumber numberWithUnsignedInt:map];\
}\
-(void)setName:(id)newValue \
{\
ENSURE_SINGLE_ARG(newValue, NSNumber);\
map = [TiUtils intValue:newValue];\
}\

#define MAKE_DATE_GETTER_SETTER(name,setName,map) \
-(NSDate*)name\
{\
return (NSDate*)map;\
}\
-(void)setName:(id)newValue \
{\
ENSURE_SINGLE_ARG(newValue, NSDate);\
map = newValue;\
}\

