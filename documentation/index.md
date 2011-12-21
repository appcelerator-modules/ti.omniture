# Ti.Omniture Module

## Description

Provides access to the Omniture tracking and analytics service.

## Getting Started

View the [Configuring Apps to Use Modules](https://wiki.appcelerator.org/display/guides/Configuring+Apps+to+Use+Modules) page for instructions on getting
started with using this module in your application.

## Accessing the Ti.Omniture Module

To access this module from JavaScript, you would do the following:

	var Omniture = require('ti.omniture');

## Functions

### Ti.Omniture.createSession({...})

Creates and returns a [Ti.Omniture.Session][] object.

Takes one argument, a dictionary which specifies the Omniture session properties.

* account[string]: Your account identifier
* trackingServer[string]: Your tracking server

### Ti.Omniture.createMediaTracker({...})

Creates and returns a [Ti.Omniture.MediaTracker][] object.

Takes one argument, a dictionary with the properties:

* session[ti.omniture.Session]: Your session object
* player[Ti.Media.VideoPlayer]: Your video player control
* playerName[string]: The name of the media player used to view the video
* mediaName[string]: The name of the video

## Usage

See example

## Author

Jeff Haynie

## Module History

View the [change log](changelog.html) for this module.

## Feedback and Support

Please direct all questions, feedback, and concerns to [info@appcelerator.com](mailto:info@appcelerator.com?subject=iOS%20Omniture%20Module).

## License

Copyright(c) 2010-2011 by Appcelerator, Inc. All Rights Reserved. Please see the LICENSE file included in the distribution for further details.

[Ti.Omniture.Session]: session.html
[Ti.Omniture.MediaTracker]: mediaTracker.html
