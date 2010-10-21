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

### ti.omniture.Session.clearVars()

Clears the session's variables.

## Properties

Note that these properties can only be set at the time of session creation,
and not modified afterwards.

### ti.omniture.Session.prop[1-50]

A property variable set on the session.

