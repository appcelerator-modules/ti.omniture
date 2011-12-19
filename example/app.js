var window = Ti.UI.createWindow({
    backgroundColor: 'white'
});
window.open();

var Omniture = require('ti.omniture');
var session = Omniture.createSession({
    account: '<< YOUR ACCOUNT HERE >>',
    trackingServer: '<< YOUR TRACKING SERVER HERE >>',
    trackingServerSecure: '<< YOUR SECURE TRACKING SERVER HERE >>',
    debug: true
});

var button = Ti.UI.createButton({
    title: 'Track',
    width: 200, height: 50, top: 4
});
window.add(button);

button.addEventListener('click', function () {
    // Track an event
    session.track({ pageName: 'android_test', events: 'event5' });
    // Track a link
    session.trackLink({ url: 'your_url_here', type: session.CUSTOM_LINK, name: 'Test' });
    // Track a link with overrides using a dictionary
    session.trackLink({ url: 'your_url_here', type: session.CUSTOM_LINK, name: 'Test', overrides: { events: 'event6', pageName: 'Texas' } });
    // Track a link with overrides as arguments
    session.trackLink({ url: 'your_url_here', type: session.CUSTOM_LINK, name: 'Test', events: 'event7,event8', pageName: 'California' });
});