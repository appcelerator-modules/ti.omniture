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
	account:'genclone34-24',
    trackingServer:'appceleratorsbx.sbx1.2o7.net'
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
    //session.trackLink({
    //    url:'www.appcelerator.com',
    //    type:'unknown',
    //    name:'test'
    //});
    session.track({pageName:"iphone_test"});
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

mediaTracker.PlayerName = "Test";