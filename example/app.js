// This is a test harness for your module
// You should do something interesting in this harness 
// to test out the module and to provide instructions 
// to users on how to use it by example.


// open a single window
var window = Ti.UI.createWindow({
  backgroundColor:'white'
});
window.open();

Ti.Omniture = require('ti.omniture');
var session = Ti.Omniture.createSession({
	account:"your_account_here",
	trackingServer:"your_tracking_server_here"
});

session.track({pageName:"iphone_test"});


