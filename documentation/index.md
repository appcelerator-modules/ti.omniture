# omniture Module

## Description

Provides access to the omniture tracking and analytics service.

## Accessing the omniture Module

To access this module from JavaScript, you would do the following:

	var omniture = require("ti.omniture");

The omniture variable is a reference to the Module object.	

## Reference

## Functions

### ti.omniture.createSession({...})

Creates and returns a [ti.omniture.Session][] object.

Takes one argument, a dictionary which specifies the Omniture session properties.

### ti.omniture.createMediaTracker({...})

Creates and returns a [ti.omniture.MediaTracker][] object.

Takes one argument, a dictionary with the properties:

session[ti.omniture.Session]: Your session object  
player[Ti.Media.VideoPlayer]: Your video player control  
playerName[string]: The name of the media player used to view the video  
mediaName[string]: The name of the video

## Usage

See example

## Author

Jeff Haynie <jhaynie@appcelerator.com>, Appcelerator Inc.

## License

TODO: Enter your license/legal information here.

[ti.omniture.Session]: session.html
[ti.omniture.MediaTracker]: mediaTracker.html
