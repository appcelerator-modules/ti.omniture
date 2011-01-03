Titanium.Omniture = require('ti.omniture')
Ti.Omniture = Titanium.Omniture; // shorthand

var win = Ti.UI.createWindow({ backgroundColor:'#fff' });
var label = Ti.UI.createLabel({ text:'SEE LOG...' });

win.add(label);
win.open();

// manual object to hold our 
// createSession and track methods
var appTracker = {
	createSession: function() {
		Ti.Omniture.createSession({
			account: 'accountname', 
			trackingServer: 'trackingserver',
			debug:true
		});
	},
	track: function(opts) {
		Ti.Omniture.track(opts);
	}
};

// -- create an Omniture session
// account, trackingServer and debug
appTracker.createSession();

// -- standard page view track 
// send to collection server with track 
// config vars (props,evars,etc)
appTracker.track({
	pageName: 'Test Page Name', 
	prop3: 'Test Prop 3', 
	eVar36: 'Test eVar 36'
});