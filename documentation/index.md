# Ti.Omniture Module

## Description

Provides access to the Omniture tracking and analytics service.

## Accessing the Ti.Omniture Module

To access this module from JavaScript, you would do the following:

	var Omniture = require('ti.omniture');

## Functions

### Ti.Omniture.createSession({...})

Creates and returns a [Ti.Omniture.Session][] object.

Takes one argument, a dictionary which specifies the Omniture session properties.

* account[string]: Your account identifier
* trackingServer[string]: Your tracking server
* trackingServerSecure[string]: Your secure tracking server

## Usage

See example

## Author

Fred Spencer

## Module History

View the [change log](changelog.html) for this module.

## Feedback and Support

Please direct all questions, feedback, and concerns to [info@appcelerator.com](mailto:info@appcelerator.com?subject=Android%20Omniture%20Module).

## License

Copyright(c) 2010-2011 by Appcelerator, Inc. All Rights Reserved. Please see the LICENSE file included in the distribution for further details.

[Ti.Omniture.Session]: session.html
[Ti.Omniture.MediaTracker]: mediaTracker.html
