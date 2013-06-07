# Ti.Omniture Module

## Description

Provides access to the Omniture tracking and analytics service.

## Dependencies

This module requires iOS 4.3 or later.

## Omniture Resources

* [AppMeasurement 3.x for iOS][AppMeasurement]
* Test Mobile Applications using [Bloodhound][Bloodhound]

## Getting Started

View the [Using Titanium Modules](http://docs.appcelerator.com/titanium/latest/#!/guide/Using_Titanium_Modules) document for instructions on getting
started with using this module in your application.

## Accessing the Module

Use `require` to access this module from JavaScript:

	var Omniture = require("ti.omniture");

The `Omniture` variable is a reference to the Module object.

## Breaking Changes

If you are upgrading from an earlier version of this module (prior to version 2.0.0) you should be
aware of the following breaking changes to the API:

* Session properties have changed. See the [iOS Version 2.x to 3.x Migration Guide](http://microsite.omniture.com/t2/help/en_US/sc/appmeasurement/ios/index.html#iOS_Version_2x_to_3x_Migration_Guide) for a list of SDK property changes that are reflected in the module.
* `createSession` and `createMediaTracker` have been replaced with `startSession` and `startMediaTracker` respectively.
* `startMediaTracker` does not require `session` or `player` to be passed to it.
* `playerName` and 'mediaName' are no longer properties of `MediaTracker`. They will be set automatically, `playerName` with "MPMoviePlayer" and `mediaName` with the name of the media file.
* The argument structure and names have changed for the `track` and `trackLink` methods.

## Methods

### <[Ti.Omniture.Session][]\> startSession(props)

Configures and returns a [Ti.Omniture.Session][] object. There is only one session. Calling `startSession` multiple times will return the same session and will not create multiple sessions.

Takes one argument, a dictionary that specifies the [Ti.Omniture.Session][] properties. Look at [Ti.Omniture.Session][] for a list of properties. The following properties are required:

* props (object): Key/value dictionary of [Ti.Omniture.Session][] properties.
	* reportSuiteIDs (string): Comma-delimited list of report suite IDs (required).
	* trackingServer (string): Tracking server to send data to (required).

* returns: [Ti.Omniture.Session][] object.

#### Example

	var session = Omniture.startSession({
        reportSuiteIDs: "<<YOUR SUITE IDS HERE>>", // Required
        trackingServer: "<<YOUR TRACKING SERVER HERE>>", // Required
        ssl: true, // ssl is false by default
        visitorID: "some_visitor_id",
        eVar1: "E VAR ONE"
    });

### <[Ti.Omniture.MediaTracker][]\> startMediaTracker(props)

Configures and returns a [Ti.Omniture.MediaTracker][] object. There is only one media tracker session. Calling `startMediaTracker` multiple times will return the same media tracker session and will not create multiple sessions.

Takes one argument, a dictionary that specifies the [Ti.Omniture.MediaTracker][] properties. Look at [Ti.Omniture.MediaTracker][] for a list of properties. 

* props (object): Key/value dictionary of [Ti.Omniture.MediaTracker][] properties.

* returns: [Ti.Omniture.MediaTracker][] object.

#### Example

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

## Properties

### version : string

The version of the Omniture library.

### debugLogging : boolean

Enables or disables debug logging. 

## Usage

See example.

## Author

Jeff Haynie & Jon Alter

## Module History

View the [change log](changelog.html) for this module.

## Feedback and Support

Please direct all questions, feedback, and concerns to [info@appcelerator.com](mailto:info@appcelerator.com?subject=iOS%20Omniture%20Module).

## License

Copyright(c) 2010-2013 by Appcelerator, Inc. All Rights Reserved. Please see the LICENSE file included in the distribution for further details.

[Ti.Omniture.Session]: session.html
[Ti.Omniture.MediaTracker]: mediaTracker.html
[AppMeasurement]: http://microsite.omniture.com/t2/help/en_US/sc/appmeasurement/ios/index.html
[Bloodhound]: http://microsite.omniture.com/t2/help/en_US/sc/appmeasurement/ios/index.html#Using_Bloodhound_to_Test_Mobile_Applications

