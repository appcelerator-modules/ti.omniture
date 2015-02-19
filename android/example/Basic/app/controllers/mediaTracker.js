function Log(text) {
	Ti.API.info(text);
}

var Omniture = require('ti.omniture'); // Did not change
var mediaName = 'movie.mp4';

$.videoPlayer.addEventListener('durationavailable', function(e) {
	/*
	 
		mediaTracker.open is now Omniture.mediaOpen; parameters are
		represented by MediaSettings objects:
		 
			mediaTracker.open({
				mediaName: mediaName,
				mediaLength: e.duration / 1000,
				mediaPlayerName: "Ti.Media.VideoPlayer"
	
			});
	*/
	var mediaSettings = Omniture.createMediaSettings({
		name: mediaName,
		milestones: '25,50,75',
		segmentByMilestones: true,
		playerName: 'Ti.Media.VideoPlayer',
		length: e.duration / 1000 // Convert from msec to sec
	});

	Log('mediaSettings.milestones: ' + mediaSettings.milestones);
	Log('mediaSettings.segmentByMilestones: ' + mediaSettings.segmentByMilestones);
	Log('mediaSettings.length: ' + mediaSettings.length);

	/*
		mediaTracker.open() is now Omniture.mediaOpen().
	 */
	Omniture.mediaOpen(mediaSettings);	 
});
	
$.videoPlayer.addEventListener('complete',function() {
	/*
		mediaTracker.close() is now Omniture.mediaClose():
		
			mediaTracker.close({
				mediaName: mediaName
			});
	*/  
	Omniture.mediaClose(mediaName);
});
	
$.videoPlayer.addEventListener('playbackState',function(e) {
	switch (e.playbackState) {
		case Ti.Media.VIDEO_PLAYBACK_STATE_PLAYING:
		/*
			mediaTracker.play() is now Omniture.mediaPlay():
			
				mediaTracker.play({
					mediaName: mediaName,
					mediaOffset: $.videoPlayer.currentPlaybackTime / 1000
				});
		*/
			Omniture.mediaPlay(
				mediaName,
				$.videoPlayer.currentPlaybackTime / 1000 // offset in secs
			);
			break;
		case Ti.Media.VIDEO_PLAYBACK_STATE_SEEKING_BACKWARD:
		case Ti.Media.VIDEO_PLAYBACK_STATE_SEEKING_FORWARD:
		case Ti.Media.VIDEO_PLAYBACK_STATE_STOPPED:
		case Ti.Media.VIDEO_PLAYBACK_STATE_PAUSED:
		/*
			mediaTracker.stop() is now Omniture.mediaStop();
			 
				mediaTracker.stop({
					mediaName: mediaName,
					mediaOffset: $.videoPlayer.currentPlaybackTime / 1000
				});
		 */
			Omniture.mediaStop(
				mediaName,
				$.videoPlayer.currentPlaybackTime / 1000 // offset in secs
			);
			break;		   
	}
});
	
function onClickClose() {
	$.win.close();
}

