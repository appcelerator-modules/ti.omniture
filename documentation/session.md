# ti.omniture.Session

## Description

An _omniture_ module object which represents a tracking session.

## Reference

## Functions

### ti.omniture.Session.track({...})

Tracks an event.  Takes one argument, a dictionary which specifies the tracking information.

### ti.omniture.Session.trackLink({...})

Tracks the specified link.  Takes one argument, a dictionary with the properties:

url[string]: The link URL  
type[string]: The link type  
name[string]: A user-defined name for the link

You can optionally override tracking properties for this function call by specifying additional
arguments in one of two ways:

overrides[dictionary]: A dictionary with the override properties

Example:

session.trackLink({url:_URL_,type:session.CUSTOM_LINK,name:_NAME_,overrides:{ prop1:_VALUE_ }});

or by appending the properties to the trackLink properties

Example:

session.trackLink({url:_URL_,type:session.CUSTOM_LINK,name:_NAME_,prop1:_VALUE_,prop2:_VALUE_ });

### ti.omniture.Session.clearVars()

Clears the session's variables.

## Properties

Note that these properties can only be set at the time of session creation,
and not modified afterwards.

### ti.omniture.Session.prop[1-50]

A property variable set on the session.

### session.CUSTOM_LINK

A type constant for a custom link

### session.FILE_DOWNLOAD

A type constant for a file download

### session.EXIT_LINK

A type constant for an exit link
