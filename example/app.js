var window = Ti.UI.createWindow({
    backgroundColor:'white'
});
window.open();

var Omniture = require('ti.omniture');
var session = Omniture.createSession({
    account:'your_account_here',
    trackingServer:'your_tracking_server_here',
    debug:true
});

var button = Ti.UI.createButton({
    title:'Track',
    width:200, height:50, top:4
});
window.add(button);

button.addEventListener('click', function () {
    // Track an event
    Omniture.track({ pageName:'android_test', events:'event6' });
});