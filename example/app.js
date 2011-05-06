// This is a test harness for your module
// You should do something interesting in this harness 
// to test out the module and to provide instructions 
// to users on how to use it by example.


// open a single window
var window = Ti.UI.createWindow({
  backgroundColor:'white'
});
window.open();

Ti.Omniture = require('ti.Omniture');
var session = Ti.Omniture.createSession({
	account:'your_account_here',
    trackingServer:'your_tracking_server_here'
});

var button = Ti.UI.createButton({
	title:"Track",
	width:200,
	height:50,
    top:4
});
window.add(button);

button.addEventListener('click',function()
{
    // Track an event
    session.track({pageName:"iphone_test",events:"event5"});
    // Track a link
    session.trackLink({url:"your_url_here",type:session.CUSTOM_LINK,name:"Test"});
    // Track a link with overrides using a dictionary
    session.trackLink({url:"your_url_here",type:session.CUSTOM_LINK,name:"Test",overrides:{events:"event6",pageName:"Texas"}});
    // Track a link with overrides as arguments
    session.trackLink({url:"your_url_here",type:session.CUSTOM_LINK,name:"Test",events:"event7,event8",pageName:"California"});
});

var mediaPlayer = Ti.Media.createVideoPlayer({
    height: 320,
    width: 240,
    url: "/test.mov",
    movieControlMode: Titanium.Media.VIDEO_CONTROL
});
window.add(mediaPlayer);

var mediaTracker = Ti.Omniture.createMediaTracker({
    session:session,
    player:mediaPlayer
});

mediaTracker.PlayerName = 'your_player_name_here';