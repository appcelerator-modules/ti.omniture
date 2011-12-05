# Ti.Omniture.Session

## Description

An _omniture_ module object which represents a tracking session.

## Functions

### Ti.Omniture.Session.track({...})

Tracks an event. Takes one argument, a dictionary which specifies the tracking information.

### Ti.Omniture.Session.trackLink({...})

Tracks the specified link.  Takes one argument, a dictionary with the properties:

* url[string]: The link URL
* type[string]: The link type
* name[string]: A user-defined name for the link

You can optionally override tracking properties for this function call by specifying additional
arguments in one of two ways:

* overrides[dictionary]: A dictionary with the override properties

Example:

<pre>session.trackLink({url:_URL_,type:session.CUSTOM_LINK,name:_NAME_,overrides:{ prop1:_VALUE_ }});</pre>

or by appending the properties to the trackLink properties

Example:

<pre>session.trackLink({url:_URL_,type:session.CUSTOM_LINK,name:_NAME_,prop1:_VALUE_,prop2:_VALUE_ });</pre>

### Ti.Omniture.Session.clearVars()

Clears the session's variables.

## Properties

Note that these properties can only be set at the time of session creation,
and cannot be modified afterwards.

### Ti.Omniture.Session.prop[1-50]

A property variable set on the session.

### Ti.Omniture.Session.CUSTOM_LINK

A type constant for a custom link

### Ti.Omniture.Session.FILE_DOWNLOAD

A type constant for a file download

### Ti.Omniture.Session.EXIT_LINK

A type constant for an exit link
