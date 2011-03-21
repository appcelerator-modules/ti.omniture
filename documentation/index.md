# omniture Module

## Description

Provides access to the Omniture tracking and analytics service.

## Accessing the omniture Module

To access this module from JavaScript, you would do the following (recommended):

	Titanium.Omniture = require('ti.omniture');
	Ti.Omniture = Titanium.Omniture; // shorthand
	
The omniture variable is a reference to the Module object.	

## Reference

## Functions

### Ti.Omniture.createSession({...})

Sets internal AppMeasurement object to a new session, with:
 	
- account*[string]*
- trackingServer*[string]*
- debug*[boolean]*

If any of these values are not set, session creation will fail; as will track calls.

### Ti.Omniture.track({...})

Sends JavaScript object literal (key-value), containing props, evars, etc. Values will be converted to *[string]* and an Omniture session track call will be made.

### Ti.Omniture.trackLink({...})

This has not yet been implemented.

## Module Installation and Use

- Put the module zip file into the root folder of your Titanium application.
- Set the `<module>` element in tiapp.xml, such as this:
    <modules>
	    <module version="1.0">ti.omniture</module>
	</modules>
- See example

## Author

Fred Spencer <fspencer@appcelerator.com>, Appcelerator Inc.

## License

&copy; 2011 Appcelerator Inc.