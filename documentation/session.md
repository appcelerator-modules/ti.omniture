# Ti.Omniture.Session

## Description

An _omniture_ module object which represents a tracking session.

Properties and Variables can be set on the _Ti.Omniture.Session_ object in two ways:

1. Set Properties and Variables by passing them as a dictionary of key value pairs to the `startSession` method.
2. Set Properties and Variables by setting them on the _Ti.Omniture.Session_ object returned by the `startSession` method

## Omniture Resources

* [Omniture Method Documentation][Methods]

## Methods

### void Ti.Omniture.Session.setAutoTrackingOptions(options)

Set auto track options for the session.

* options[array]: Array of AUTO_TRACK_OPTIONS (set with constants _Ti.Omniture.Session.AUTO_TRACK_OPTIONS_LIFECYCLE_, _Ti.Omniture.Session.AUTO_TRACK_OPTIONS_NAVIGATION_, or _Ti.Omniture.Session.AUTO_TRACK_OPTIONS_NONE_)

__Default:__ Ti.Omniture.Session.AUTO_TRACK_OPTIONS_LIFECYCLE

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
	
### void Ti.Omniture.Session.setOnline()

Brings measurement functionality online

#### Example

	session.setOnline();

### void Ti.Omniture.Session.setOffline()

Take measurement functionality offline

#### Example

	session.setOffline();

### void Ti.Omniture.Session.trackAppState(props)

Tracks an application state change. 

Increments overall page view count. AppState will be tracked as pageName by SiteCatalyst.

* props[object]: Key value dictionary of properties
	* appState[string]: appState app state description (required)
	* contextData[object]: additional data to send with this state change

#### Example

	session.trackAppState({
	    appState: "state1",
	    contextData: {
	        key: "value"
	    }
	});

### void Ti.Omniture.Session.trackEvents(props)

Tracks an application event. Does not increment overall page view count.

* props[object]: Key value dictionary of properties
	* eventNames[string]: comma delimited list of events (required)
	* contextData[object]: additional data to send with this event
	
#### Example

	session.trackEvents({
	    eventNames: "event1, event2",
	    contextData: {
	        key: "value"
	    }
	});

### void Ti.Omniture.Session.track(props)

Sends a tracking call with preset options. Increments overall page view count.

* props[object]: Key value dictionary of properties
	* variables[object]: additional variables to send with this event
	* contextData[object]: additional data to send with this event
			
#### Example

	session.track({
        contextData: {
            key: "value"
        },
        variables: {
            prop1: "P ONE"
        }
    });

### void Ti.Omniture.Session.trackLink(props)

Sends a tracking call with preset options. Does not increment overall page view count.

* props[object]: Key value dictionary of properties
	* linkURL[string]: The link URL (required)
	* linkType[string]: The link type (required) (set with constants _Ti.Omniture.Session.LINK_TYPE_CUSTOM_, _Ti.Omniture.Session.LINK_TYPE_FILE_DOWNLOAD_, or _Ti.Omniture.Session.LINK_TYPE_EXIT_)
	* linkName[string]: name of link
	* contextData[object]: additional data to send with this event
	* variables[object]: additional variables to send with this event
	
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

### void Ti.Omniture.Session.trackLight(props)

Sends a light tracking call with preset options. Light tracking must be configured with Adobe ClientCare.

* props[object]: Key value dictionary of properties
	* profileID[string]: light profile id (required)
	* storeForSeconds[number]: number of seconds to hold hit while waiting for additional hits
	* incrementBy[number]: number of times to increment metric count associated with light profile id
	* contextData[object]: additional data to send with this call
	* variables[object]: additional variables to send with this call

### Ti.Omniture.Session.clearVars()

Clears all persistent track variables.

#### Example

	session.clearVars();

### number Ti.Omniture.Session.trackingQueueSize()

Get number of hits currently in the queue

#### Example

	session.trackingQueueSize();

### void Ti.Omniture.Session.clearTrackingQueue()

Clear all hits currently in the queue waiting to be sent. This will delete all pending hits, they will not be sent or saved.

#### Example

	session.clearTrackingQueue();

## Constants

### Ti.Omniture.Session.LINK_TYPE_CUSTOM

A link type constant for a custom link

### Ti.Omniture.Session.LINK_TYPE_FILE_DOWNLOAD

A link type constant for a file download

### Ti.Omniture.Session.LINK_TYPE_EXIT

A link type constant for an exit link

### Ti.Omniture.Session.AUTO_TRACK_OPTIONS_NONE

Disable all auto tracking

### Ti.Omniture.Session.AUTO_TRACK_OPTIONS_LIFECYCLE

Track application lifecycle

### Ti.Omniture.Session.AUTO_TRACK_OPTIONS_NAVIGATION

Track navigation events


## Variables

### Ti.Omniture.Session.list1-list3[string]

Gets and sets the value of a specific list variable

#### Example

	session.list1 = "L ONE";

### Ti.Omniture.Session.hier1-heir5[string]

Gets and sets the value of a specific hierarchy var

#### Example

	session.hier2 = "H TWO";

### Ti.Omniture.Session.prop1-prop75[string]

Gets and sets the value of a specific prop

#### Example

	session.prop3 = "P THREE";

### Ti.Omniture.Session.eVar1-eVar75[string]

Gets and sets the value of a specific eVar

#### Example

	session.eVar4 = "E FOUR";


## Properties

### Ti.Omniture.Session.reportSuiteIDs[string]

Comma-delimited list of Report Suite IDs

### Ti.Omniture.Session.trackingServer[string]

Tracking Server to send data to

### Ti.Omniture.Session.visitorID [string]

Custom visitor ID

__Default:__ App-Specific UUID will be used if not set.

### Ti.Omniture.Session.charSet[string]

Character Encoding

__Default:__ "UTF-8"

### Ti.Omniture.Session.currencyCode[string]

Currency Code 

__Default:__ "USD"

### Ti.Omniture.Session.ssl[boolean]

Transfer hits by SSL connection 

__Default:__ false

### Ti.Omniture.Session.purchaseID[string]

ID to attach to a purchase

### Ti.Omniture.Session.transactionID[string]

ID to attach to a transaction

### Ti.Omniture.Session.appState[string]

State of application at time of hit. Same as PageName in SiteCatalyst.

### Ti.Omniture.Session.channel[string]

Custom channel variable

### Ti.Omniture.Session.appSection[string]

Application Section. Same as Server in SiteCatalyst.

### Ti.Omniture.Session.campaign[string]

Related campaign for hit(s).

### Ti.Omniture.Session.products[string]

Comma-delimited list of products.

### Ti.Omniture.Session.events[string]

Comma-delimited list of events.

### Ti.Omniture.Session.geoState[string]

Geographical State/Province variable.

##### Example

	session.geoState = "CA";

### Ti.Omniture.Session.geoZip[string]

Geographical Zip/Postal Code variable.

#### Example

	session.geoZip = "94043";

### Ti.Omniture.Session.persistentContextData[object]

Persistent context data to include with every hit.

#### Example

	session.persistentContextData = {
        "custom key": "value"
    };

### Ti.Omniture.Session.lifecycleSessionTimeout[number]

Set the total amount of time to ignore between sessions.

### Ti.Omniture.Session.linkTrackVars[string]

Comma-delimited list of variables to send with a trackLink or trackEvent call. 

All set variables and context data will be filtered by linkTrackVars.  If linkTrackVars is not set all set variables will be sent.

### Ti.Omniture.Session.linkTrackEvents[string]

Comma-delimited list of events to send with a trackLink or trackEvent call

The events list will be filtered by linkTrackEvents.  If linkTrackEvents is not set, all set events will be sent.

### Ti.Omniture.Session.lightTrackVar[string]

Comma-delimited list of variables to send with a trackLight call

All set variables and context data will be filtered by lightTrackVars.  If lightTrackVars is not set all set variables will be sent.

### Ti.Omniture.Session.offlineTrackingEnabled[boolean]

Enable/Disable offline tracking.

You will need to be using a timestamped report suite to use this functionality.

__Default:__ false

### Ti.Omniture.Session.offlineHitLimit[number]

Maximum number of hits to cache when in offline mode.

Values over 5000 may result in performance issues.  When this limit is reached oldest hits will be discarded when new hits come in.

__Default:__ 1000


[Methods]: http://microsite.omniture.com/t2/help/en_US/sc/appmeasurement/ios/index.html#Methods
