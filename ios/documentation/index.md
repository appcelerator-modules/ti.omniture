# Ti.Omniture Module

## Description

Provides access to the Omniture tracking and analytics service.

## Dependencies

This iOS module requires iOS 6.0 or later.
This Android module Requires Android 2.3.3 or later.

## Omniture Resources

* [Native Documentation for iOS][iOSNativeDocumentation]
* [Native Documentation for Android][AndroidNativeDocumentation]
* [Native Migration Guide 4.x for iOS][iOSMigrationGuide]
* [Native Migration Guide 4.x for Android][AndroidMigrationGuide]
* Test Mobile Applications using [Bloodhound][Bloodhound]

## Getting Started

View the [Using Titanium Modules](http://docs.appcelerator.com/titanium/latest/#!/guide/Using_Titanium_Modules) document for instructions on getting
started with using this module in your application.

## Accessing the Module

Use `require` to access this module from JavaScript:

	var Omniture = require("ti.omniture");

The `Omniture` variable is a reference to the Module object.

## Breaking Changes

If you are upgrading from an earlier version of this module (prior to version
3.0.0) you should be aware that API of the module changed substantially to
match the changes in the underlying native library.

### Sessions and Basic Functions

The Ti.Omniture.Session class is no longer used. Instead of creating a Session
object using the `startSession` function, basic parameters such as tracking
server address and suite IDs are specified in a configuration file called
ADBMobileConfig.json. Download a pre-configured instance of this file from the
Adobe Mobile Services web site.

The functions of the Session class have been replaced with functions of
the module object itself.

The name of many functions has changed to match the native library. Also,
the way parameters are provided has changed to allow for a more concise
coding style.

For example, this:

		session.trackAppState({
					appState: "state1",
					contextData: {
							key: "value"
					}
		});

will become this:

		Omniture.trackState('state1', { key: 'value' });

### Media Tracker

The Ti.Omniture.MediaTracker class is no longer used. Instead of creating a
MediaTracker object using the `startMediaTracker` function, use the
`createMediaSettings` function to create a [Ti.Omniture.MediaSettings][] object
and pass it to the `mediaOpen` function.

For example, this:

		var mediaTracker = Omniture.startMediaTracker({
			trackMilestones: "25,50,75",
			contextDataMapping: {
				"a.media.name": "eVar2,prop2",
				"a.media.segment": "eVar3",
				"a.contentType": "eVar1",
				"a.media.timePlayed": "event3",
				"a.media.view": "event1",
				"a.media.segmentView": "event2",
				"a.media.complete": "event7"
			}
		});

becomes this:

		var mediaSettings = Omniture.createMediaSettings({
			name: 'media1',
			milestones: '25,50,75',
			...
		});
		Omniture.mediaOpen(mediaSettings);
		...
		Omniture.mediaClose('media1');

## Methods

### <void\> keepLifecycleSessionAlive()

Sets the preference of lifecycle session keep alive. Calling
keepLifecycleSessionAlive will prevent your app from launching a new session
the next time it is resumed from background. Only use this if your app
registers for notifications in the background.

### <void\> collectLifecycleDataWithAdditionalData(data)

Begins the collection of lifecycle data. This should be the first method
called upon app launch.

* data (object): Key/value dictionary containing the context data to be added
to the lifecycle hit.

### <void\> keepLifecycleSessionAlive()

Sets the preference of lifecycle session keep alive.
Calling keepLifecycleSessionAlive will prevent your app from launching a new
session the next time it is resumed from background. Only use this if your app
registers for notifications in the background

### <void\> collectLifecycleData([contextData])

Begins the collection of lifecycle data. This should be the first method called
upon app launch.

* data (Object): A dictionary containing the context data to be added to the
lifecycle hit. Optional.

### <void\> trackState(state [, data])

Tracks a state with context data. This method increments page views.

* state (String): The state value to be tracked.
* data (Object): Key/value dictionary containing the context data to be tracked.

### <void\> trackAction(action [, data])n

Tracks an action with context data. This method does not increment page views.

* action (String): The action value to be tracked.
* data (Object): Key/value dictionary containing the context data to be tracked.

### <void\> trackActionFromBackground(action [, data])

Tracks an action with context data. This method does not increment page views.
This method is intended to be called while your app is in the background (it
will not cause lifecycle data to send if the session timeout has been exceeded).

* action (String): The action value to be tracked.
* data (Object): Key/value dictionary containing the context data to be tracked.

### <void\>trackLocation(latitude, longitude[, data])

Tracks a location with context data. This method does not increment page views.

* latitude (Number): Latitude of the location.
* longitude (Number): Longitude of the location.
* data (Object): Key/value dictionary containing the context data to be tracked.

### <void\> trackLifetimeValueIncrease(amount[, data])

Tracks an increase in a user's lifetime value.
This method does not increment page views.

* amount (Number): a positive number detailing the amount to increase lifetime value by.
* data (Object): Key/value dictionary containing the context data to be tracked.

### <void\> trackTimedActionStart(action[, data])

Tracks the start of a timed event.
This method does not send a tracking hit
If an action with the same name already exists it will be deleted and a new one will replace it.

* action (String): denotes the action name to track.
* data (Object): Key/value dictionary containing the context data to be tracked.

### <void\> trackTimedActionUpdate(action[, data])

Tracks the start of a timed event. This method does not send a tracking hit.
When the timed event is updated the contents of the parameter data will 
overwrite existing context data keys and append new ones.

* action (String): denotes the action name to track.
* data (Object): Key/value dictionary containing the context data to be tracked.

### <void\> trackTimedActionEnd(action[, callback])

Tracks the end of a timed event

* action (String): denotes the action name to track.
* callback (Function): Optional callback function to be executed when
this timed event ends. The function can cancel the sending of the hit by
returning false. The callback function will receive as parameter a single
object with the following properties:
	* inAppDuration (Number)
	* totalDuration (Number)
	* data (Object)

### <Boolean\> trackTimedActionExists(action)

Returns whether or not a timed action is in progress.

* action (String): denotes the action name to track.

### <void\> retrieveVisitorMarketingCloudID(callback)

Retrieves the Marketing Cloud Identifier from the Visitor ID Service. Querying
this propert can cause a blocking network call, therefore this is an async
function: the result will be delivered to the provided callback function.

* callback (Function): A callback function that will receive the Marketing 
Cloud Identifier, a String, as its single parameter.

### <void\> retrieveTrackingId(callback)

Retrieves the analytics tracking identifier. Querying this propert can cause a
blocking network call, therefore this is an async function: the result will
be delivered to the provided callback function.

* callback (Function): A callback function that will receive the tracking ID,
a String, as its single parameter.

### <void\> trackingSendQueuedHits()

Force library to send all queued hits regardless of current batch options.

### <void\> trackingClearQueue()

Clears any hits out of the tracking queue and removes them from the database.

### <Ti.Omniture.MediaSettings\> createMediaSettings(props)

Creates a [Ti.Omniture.MediaSettings][] object with the specified properties.

* props (Object): See [Ti.Omniture.MediaSettings][] properties.

### <Ti.Omniture.MediaSettings\> createMediaAdSettings(props)

Creates a [Ti.Omniture.MediaSettings][] object with the specified properties.

* props (Object): See [Ti.Omniture.MediaSettings][] properties.

The difference between `createMediaSettings` and this function is that this
function sets isMediaAd to true by default.

### <void\> mediaOpen(mediaSettings[, callback])

Opens a media item for tracking.

* mediaSettings (Ti.Omniture.MediaSettings)
* callback (Function): Optional function to be called every second. 
The function will receive as parameter a single [Ti.Omniture.MediaState][]
object.

### <void\> mediaClose(name)

Closes a media item.

* name (String): Name of the media item.

### <void\> mediaPlay(name, offset)

Begins tracking a media item.

* name (String): Name of media item.
* offset (Number): The point that the media items is being played from (in seconds).

### <void\> mediaComplete:(name, offset)

Artificially completes a media item.

* name (String): Name of media item.
* offset (Number): The point that the media items is when `mediaComplete` is called (in seconds).

### <void\> mediaStop:(name, offset)

Notifies the media module that the media item has been paused or stopped.

* name (String): Name of media item.
* offset (Number): The point that the media items is when the media item was stopped (in seconds).

### <void\> mediaClick:(name, offset)

Notifies the media module that the media item has been clicked.

* name (String): Name of media item.
* offset (Number): The point that the media items is when the media item was clicked (in seconds).

### <void\> mediaTrack:(name[, contextData])

Sends a track event with the current media state

* name (String): Name of media item.
* contextData (Object): Context data to track with this media action.

### <void\> targetLoadRequest(request, callback)

Processes a Target service request.

* request ([Ti.Omniture.TargetLocationRequest][]): The target request created with the `createTargetLocationRequest` or `createTargetOrderConfirmRequest` function.
* callback (Function): The function to call with a response string parameter
upon completion of the service request.

### <TiOmnitureTargetLocationRequest\> createTargetLocationRequest(params)

Creates a [Ti.Omniture.TargetLocationRequest][] object.

* params (Object): A dictionary with the following parameters:
    * name (String)
    * defaultContent (String)
    * parameters (Object): a dictionary of key-value pairs that will be added to the request

### <TiOmnitureTargetLocationRequest\> createTargetOrderConfirmRequest(params)

Creates a [Ti.Omniture.TargetLocationRequest][] object.

* params (Object): A dictionary with the following parameters:
    * name (String)
    * orderId (String)
    * orderTotal (String)
    * productPurchaseId (String)
    * parameters (Object): a dictionary of key-value pairs that will be added to the request.


### <void\> targetClearCookies()

Clears target cookies from shared cookie storage.

### <void\> setAudienceIds(dpid, dpuuid)

Sets the DPID and DPUUID.

### <void\> audienceSignalWithData(data[, callback])

Processes an Audience Manager service request.

* data (Object)
* callback (Function): Function to call with a response dictionary parameter
upon completion of the service request.

### <void\> audienceReset()

Resets audience manager UUID and purges current visitor profile.

### <void\> visitorSyncIdentifiers(identifiers)

Synchronizes the provided identifiers to the visitor id service

* identifiers (Object): A dictionary containing identifiers, with the keys
being the id types and the values being the correlating identifiers

## Properties

### version : String

The version of the Omniture library.

### debugLogging : Boolean

Enables or disables debug logging. 

### lifetimeValue : Number

The user's current lifetime value.

### trackingQueueSize : Number

The number of hits currently in the tracking queue.

### privacyStatus : Number

The privacy status.

### audienceVisitorProfile : Object

The visitor's profile.

### audienceDpid: String

A string containing the DPID value.

### audienceDpuuid: String

A string containing the DPUUID value.

### PRIVACY_STATUS_OPT_IN : Number

One of the possible values of the `privacyStatus` property.

### PRIVACY_STATUS_OPT_OUT : Number

One of the possible values of the `privacyStatus` property.

### PRIVACY_STATUS_OPT_UNKNOWN : Number

One of the possible values of the `privacyStatus` property.

### TARGET_PARAM_ORDER_ID : String

Constant string that can be used as keys to add common target parameters.

### TARGET_PARAM_ORDER_TOTAL : String

Constant string that can be used as keys to add common target parameters.

### TARGET_PARAM_PRODUCT_PURCHASE_ID : String

Constant string that can be used as keys to add common target parameters.

### TARGET_PARAM_CATEGORY_ID : String

Constant string that can be used as keys to add common target parameters.

### TARGET_PARAM_MBOX_3RD_PARTY_ID : String

Constant string that can be used as keys to add common target parameters.

### TARGET_PARAM_MBOX_PAGE_VALUE : String

Constant string that can be used as keys to add common target parameters.

### TARGET_PARAM_MBOX_PC : String

Constant string that can be used as keys to add common target parameters.

### TARGET_PARAM_MBOX_SESSION_ID : String

Constant string that can be used as keys to add common target parameters.

### TARGET_PARAM_MBOX_HOST : String

Constant string that can be used as keys to add common target parameters.

## Usage

See example.

## Author

Original: Jeff Haynie, Fred Spencer, & Jon Alter

Rewrite based on 4.x: Zsombor Papp

## Module History

View the [change log](changelog.html) for this module.

## Feedback and Support

Please direct all questions, feedback, and concerns to [info@appcelerator.com](mailto:info@appcelerator.com?subject=iOS%20Omniture%20Module).

## License

Copyright(c) 2010-2015 by Appcelerator, Inc. All Rights Reserved. Please see the LICENSE file included in the distribution for further details.

[iOSNativeDocumentation]: https://marketing.adobe.com/resources/help/en_US/mobile/ios/
[AndroidNativeDocumentation]: https://marketing.adobe.com/resources/help/en_US/mobile/android/
[iOSMigrationGuide]: https://marketing.adobe.com/resources/help/en_US/mobile/ios/migration_v3.html
[AndroidMigrationGuide]: https://marketing.adobe.com/resources/help/en_US/mobile/android/migration_v3.html
[Bloodhound]: https://marketing.adobe.com/resources/help/en_US/mobile/ios/bloodhound.html
[Ti.Omniture.MediaSettings]: mediaSettings.html
[Ti.Omniture.MediaState]: mediaState.html
[Ti.Omniture.TargetLocationRequest]: targetLocationRequest.html
