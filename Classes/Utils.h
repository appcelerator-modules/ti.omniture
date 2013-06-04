/**
 * Appcelerator Titanium Mobile Modules
 * Copyright (c) 2010-2013 by Appcelerator, Inc. All Rights Reserved.
 * Proprietary and Confidential - This source code is not for redistribution
 */

// MAKE_OBJ_GETTER_SETTER(name, setName, type, className)
#define MAKE_OBJ_GETTER_SETTER(name, setName, type, className) \
-(id)name \
{ \
    return [[className sharedInstance] name]; \
} \
-(void)setName:(id)args \
{ \
    ENSURE_SINGLE_ARG(args, type); \
    [[className sharedInstance] setName:args]; \
} \

// MAKE_INT_GETTER_SETTER(name, setName, className)
#define MAKE_INT_GETTER_SETTER(name, setName, className) \
MAKE_SYSTEM_PROP(name, [[className sharedInstance] name]); \
-(void)setName:(id)args \
{ \
    ENSURE_SINGLE_ARG(args, NSNumber); \
    [[className sharedInstance] setName:[TiUtils intValue:args]]; \
} \

// MAKE_BOOL_GETTER_SETTER(name, setName, className)
#define MAKE_BOOL_GETTER_SETTER(name, setName, className) \
MAKE_SYSTEM_NUMBER(name, NUMBOOL([[className sharedInstance] name])); \
-(void)setName:(id)args \
{ \
    ENSURE_SINGLE_ARG(args, NSNumber); \
    [[className sharedInstance] setName:[TiUtils boolValue:args]]; \
} \

// MAKE_DBL_GETTER_SETTER(name, setName, className)
#define MAKE_DBL_GETTER_SETTER(name, setName, className) \
MAKE_SYSTEM_PROP_DBL(name, [[className sharedInstance] name]); \
-(void)setName:(id)args \
{ \
    ENSURE_SINGLE_ARG(args, NSNumber); \
    [[className sharedInstance] setName:[TiUtils doubleValue:args]]; \
} \

// MAKE_STR_GETTER_SETTER(name, setName, className)
#define MAKE_STR_GETTER_SETTER(name, setName, className) MAKE_OBJ_GETTER_SETTER(name, setName, NSString, className)