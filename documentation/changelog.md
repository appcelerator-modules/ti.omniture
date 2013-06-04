# Change Log
<pre>
v2.0.0  Updated Omniture SDK to v3.1.6 (No longer uses UDID) [MOD-1373]
		* See the [iOS Version 2.x to 3.x Migration Guide](http://microsite.omniture.com/t2/help/en_US/sc/appmeasurement/ios/index.html#iOS_Version_2x_to_3x_Migration_Guide) for a list of SDK changes that are reflected in the module
		* `createSession` and `createMediaTracker` changed to `startSession` and `startMediaTracker` respectively
		* `startMediaTracker` does not require `session` or `player` to be passed to it
		* `playerName` and 'mediaName' are no longer properties of `MediaTracker`. They will be set automatically, `playerName` with "MPMoviePlayer" and `mediaName` with the name of the media file 

v1.0    Initial Release
