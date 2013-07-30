# Ti.Omniture.Session

## Description

An _omniture_ module object which represents a tracking session.

Properties and variables can be set on the _Ti.Omniture.Session_ object in two ways:

1. Set properties and variables by passing them as a dictionary of key/value pairs to the `startSession` method.
2. Set properties and variables by setting them on the _Ti.Omniture.Session_ object returned by the `startSession` method.

## Omniture Resources

* [Omniture Method Documentation][Methods]

## Methods

### <void\> setAutoTrackingOptions(options)

Sets auto track options for the session.

* options (array): Array of _AUTO_TRACK_OPTIONS_ (set with constants _AUTO_TRACK_OPTIONS_LIFECYCLE_, _AUTO_TRACK_OPTIONS_NAVIGATION_, or _AUTO_TRACK_OPTIONS_NONE_).

__Default:__ AUTO_TRACK_OPTIONS_LIFECYCLE

__iOS only__

#### Example

	//only LifeCycle auto tracking enabled (default)
	session.setAutoTrackingOptions([ session.AUTO_TRACK_OPTIONS_LIFECYCLE ]); 
	
	//LifeCycle and Navigation tracking enabled
	session.setAutoTrackingOptions([ 
		session.AUTO_TRACK_OPTIONS_LIFECYCLE, 
		session.AUTO_TRACK_OPTIONS_NAVIGATION 
	]); 
	
	//only Navigation auto tracking enabled
	session.setAutoTrackingOptions([ session.AUTO_TRACK_OPTIONS_NAVIGATION ]);
	
	//fully disable auto tracking
	session.setAutoTrackingOptions([ session.AUTO_TRACK_OPTIONS_NONE ]);
	
### <void\> setOnline()

Brings measurement functionality online.

#### Example

	session.setOnline();

### <void\> setOffline()

Takes measurement functionality offline.

#### Example

	session.setOffline();

### <void\> trackAppState(props)

Tracks an application state change. 

Increments overall page view count. AppState will be tracked as `pageName` by SiteCatalyst.

* props (object): Key/value dictionary of properties.
	* appState (string): application state description (required).
	* contextData (object): additional data to send with this state change.

#### Example

	session.trackAppState({
	    appState: "state1",
	    contextData: {
	        key: "value"
	    }
	});

### <void\> trackEvents(props)

Tracks an application event. Does not increment overall page view count.

* props (object): Key/value dictionary of properties.
	* eventNames (string): comma-delimited list of events (required).
	* contextData (object): additional data to send with this event.
	
#### Example

	session.trackEvents({
	    eventNames: "event1, event2",
	    contextData: {
	        key: "value"
	    }
	});

### <void\> track(props)

Sends a tracking call with preset options. Increments overall page view count.

* props (object): Key/value dictionary of properties.
	* variables (object): additional variables to send with this event.
	* contextData (object): additional data to send with this event.
			
#### Example

	session.track({
        contextData: {
            key: "value"
        },
        variables: {
            prop1: "P ONE"
        }
    });

### <void\> trackLink(props)

Sends a tracking call with preset options. Does not increment overall page view count.

* props (object): Key/value dictionary of properties.
	* linkURL (string): The link URL (required).
	* linkType (string): The link type (required) (set with constants _LINK_TYPE_CUSTOM_, _LINK_TYPE_FILE_DOWNLOAD_, or _LINK_TYPE_EXIT_).
	* linkName (string): name of link.
	* contextData (object): additional data to send with this event.
	* variables (object): additional variables to send with this event.
	
#### Example

	session.trackLink({
        linkURL: "http://example.com",
        linkType: session.LINK_TYPE_FILE_DOWNLOAD,
        linkName: "example_link",
        contextData: {
            contextKey: "contextValue"
        },
        variables: {
            eVar3: "E THREE"
        }
    });

### <void\> trackLight(props)

Sends a light tracking call with preset options. Light tracking must be configured with Adobe ClientCare.

* props (object): Key/value dictionary of properties.
	* profileID (string): light profile ID (required)
	* storeForSeconds (number): number of seconds to hold hi.t while waiting for additional hits.
	* incrementBy (number): number of times to increment metric count associated with light profile ID.
	* contextData (object): additional data to send with this call.
	* variables (object): additional variables to send with this call.

### <void\> clearVars()

Clears all persistent track variables.

#### Example

	session.clearVars();

### <number\> trackingQueueSize()

Gets the number of hits currently in the queue.

#### Example

	session.trackingQueueSize();

### <void\> clearTrackingQueue()

Clears all hits currently in the queue waiting to be sent. This will delete all pending hits. They will not be sent or saved.

#### Example

	session.clearTrackingQueue();
	
### <void\> startActivity()

Lifecycle tracking method to be called when the app's window is opened

* returns: void.

__Android only__

#### Example

	session.startActivity();

### <void\> stopActivity()

Lifecycle tracking method to be called when the app's window is closed

* returns: void.

__Android only__

#### Example

	session.stopActivity();
	

## Constants

### LINK_TYPE_CUSTOM : string

A link type constant for a custom link.

### LINK_TYPE_FILE_DOWNLOAD : string

A link type constant for a file download.

### LINK_TYPE_EXIT : string

A link type constant for an exit link.

### AUTO_TRACK_OPTIONS_NONE : number

Disables all auto tracking. 

__iOS only__

### AUTO_TRACK_OPTIONS_LIFECYCLE : number

Tracks application lifecycle. 

__iOS only__

### AUTO_TRACK_OPTIONS_NAVIGATION : number

Tracks navigation events. 

__iOS only__


## Variables

### list1 - list3 : string

Gets and sets the value of a specific list variable.

#### Example

	session.list1 = "L ONE";

### hier1 - heir5 : string

Gets and sets the value of a specific hierarchy var.

#### Example

	session.hier2 = "H TWO";

### prop1 - prop75 : string

Gets and sets the value of a specific prop.

#### Example

	session.prop3 = "P THREE";

### eVar1 - eVar75 : string

Gets and sets the value of a specific eVar.

#### Example

	session.eVar4 = "E FOUR";


## Properties

### reportSuiteIDs : string

Comma-delimited list of report suite IDs.

### trackingServer : string

Tracking server to send data to.

### visitorID : string

Custom visitor ID.

__Default:__ App-specific UUID will be used if not set.

### charSet : string

Character encoding.

__Default:__ "UTF-8"

### currencyCode : string

Currency code.

__Default:__ "USD"

### ssl : boolean

Transfer hits by SSL connection.

__Default:__ false

__Android:__ (writeonly)

### purchaseID : string

ID to attach to a purchase.

### transactionID : string

ID to attach to a transaction.

### appState : string

State of application at time of hit. Same as `PageName` in SiteCatalyst.

### channel : string

Custom channel variable.

### appSection : string

Application section. Same as `Server` in SiteCatalyst.

### campaign : string

Related campaign for hit(s).

### products : string

Comma-delimited list of products.

### events : string

Comma-delimited list of events.

### geoState : string

Geographical state/province variable.

##### Example

	session.geoState = "CA";

### geoZip : string

Geographical zip/postal code variable.

#### Example

	session.geoZip = "94043";

### persistentContextData : object

Persistent context data to include with every hit.

#### Example

	session.persistentContextData = {
        "custom key": "value"
    };

### lifecycleSessionTimeout : number

Sets the total amount of time to ignore between sessions.

### linkTrackVars : string

Comma-delimited list of variables to send with a `trackLink` or `trackEvents` call. 

All set variables and context data will be filtered by `linkTrackVars`.  If `linkTrackVars` is not set, all set variables will be sent.

### linkTrackEvents : string

Comma-delimited list of events to send with a `trackLink` or `trackEvents` call.

The events list will be filtered by `linkTrackEvents`.  If `linkTrackEvents` is not set, all set events will be sent.

### lightTrackVar : string

Comma-delimited list of variables to send with a trackLight call.

All set variables and context data will be filtered by `lightTrackVars`.  If `lightTrackVars` is not set, all set variables will be sent.

### offlineTrackingEnabled : boolean

Enables or disables offline tracking.

You need to use a timestamped report suite to use this functionality.

__Default:__ false

__Android:__ (writeonly)

### offlineHitLimit : number

Maximum number of hits to cache when in offline mode.

Values over 5000 may result in performance issues.  When this limit is reached oldest hits will be discarded when new hits come in.

__Default:__ 1000


[Methods]: http://microsite.omniture.com/t2/help/en_US/sc/appmeasurement/ios/index.html#Methods
