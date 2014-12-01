
var args = arguments[0] || {};
var mediaTracker = args.mediaTracker;
var mediaName = "movie.mp4";

// Automatic Video Measurement is only supported on iOS
// On Android, manual tracking is required
if (OS_ANDROID) {
    $.videoPlayer.addEventListener('durationavailable', function(e) {
        mediaTracker.open({
           mediaName: mediaName,
           // VideoPlayer times are in milliseconds, but Omniture.mediaTracker times are in seconds
           mediaLength: secondsFromMs(e.duration),
           mediaPlayerName: "Ti.Media.VideoPlayer"
        });
    });
    
    $.videoPlayer.addEventListener('complete',function() {
        mediaTracker.close({
            mediaName: mediaName
        });
    });
    
    $.videoPlayer.addEventListener('playbackState',function(e) {
        switch (e.playbackState) {
            case Ti.Media.VIDEO_PLAYBACK_STATE_PLAYING:
                mediaTracker.play({
                    mediaName: mediaName,
                    // VideoPlayer times are in milliseconds, but Omniture.mediaTracker times are in seconds
                    mediaOffset: secondsFromMs($.videoPlayer.currentPlaybackTime)
                });
                break;
            // If the `playbackState` event listener is not being triggerd by seek events
            // the likely cause is https://jira.appcelerator.org/browse/TIMOB-13913 
            case Ti.Media.VIDEO_PLAYBACK_STATE_SEEKING_BACKWARD:
            case Ti.Media.VIDEO_PLAYBACK_STATE_SEEKING_FORWARD:
            case Ti.Media.VIDEO_PLAYBACK_STATE_STOPPED:
            case Ti.Media.VIDEO_PLAYBACK_STATE_PAUSED:
                mediaTracker.stop({
                    mediaName: mediaName,
                    // VideoPlayer times are in milliseconds, but Omniture.mediaTracker times are in seconds
                    mediaOffset: secondsFromMs($.videoPlayer.currentPlaybackTime)
                });
                break;           
        }
    });
    
    // Helper function for convertion milliseconds to seconds
    function secondsFromMs(ms) {
        return Math.round(ms / 1000);
    }
}

function onClickClose() {
	$.win.close();
}

