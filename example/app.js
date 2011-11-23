var window = Ti.UI.createWindow({
    backgroundColor:'white'
});
window.open();

var Omniture = require('ti.omniture');
var session = Omniture.createSession({
    account: 'your_account_here',
    trackingServer: 'your_tracking_server_here'
});

var button = Ti.UI.createButton({
    title: 'Track',
    width: 200, height: 50,top: 4
});
window.add(button);

button.addEventListener('click', function() {
    // Track an event
    session.track({ pageName: 'iphone_test', events: 'event5' });
    // Track a link
    session.trackLink({ url: 'your_url_here', type: session.CUSTOM_LINK, name: 'Test' });
    // Track a link with overrides using a dictionary
    session.trackLink({ url: 'your_url_here', type: session.CUSTOM_LINK, name: 'Test', overrides:{ events: 'event6', pageName: 'Texas' } });
    // Track a link with overrides as arguments
    session.trackLink({ url: 'your_url_here', type: session.CUSTOM_LINK, name: 'Test', events: 'event7,event8', pageName: 'California' });
});

var mediaPlayer = Ti.Media.createVideoPlayer({
    height: 320, width: 240,
    url: '/test.mov',
    movieControlMode: Ti.Media.VIDEO_CONTROL
});
window.add(mediaPlayer);

var mediaTracker = Omniture.createMediaTracker({
    session: session,
    player: mediaPlayer,
    playerName: 'your_player_name_here',
    mediaName: 'name_of_video_here'
});
// Change media name as you change media in the player
//mediaTracker.mediaName = 'name_of_video_here';