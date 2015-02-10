function Log(text) {
    $.textLog.value = text + "\n" + $.textLog.value;
    Ti.API.info(text);
}

var Omniture = require("ti.omniture"); // Did not change

Log('Omniture.debugLogging: ' + Omniture.debugLogging);
Omniture.debugLogging = true; // Did not change
Log('Omniture.debugLogging: ' + Omniture.debugLogging);

var mediaSettings = Omniture.createMediaSettings({
	name: 'media1',
	length: 1289,
	milestones: '25,50,75',
	segmentByMilestones: true
});

Log('mediaSettings.milestones: ' + mediaSettings.milestones);
Log('mediaSettings.segmentByMilestones: ' + mediaSettings.segmentByMilestones);

var rows = [
    {
        title: "Omniture SDK version",
        onClick: function(){
            Log("Version: " + Omniture.version); // Did not change
        }
    },
    {
        title: "trackState",
        onClick: function(){
            // session.trackAppState({
                // appState: "state1",
                // contextData: {
                    // key: "value"
                // }
            // });
            Omniture.trackState('state1', { key: 'value' });
            Log("sent trackState");
        }
    },
    {
        title: "trackAction",
        onClick: function(){
            // session.trackEvents({
                // eventNames: "event1, event2",
                // contextData: {
                    // key: "value"
                // }
            // });
            // There is no 'trackEvents' any more...
            Omniture.trackAction('action1', { key: 'value' });
            Log("sent trackAction");
        }
    },
    // {
        // title: "track",
        // onClick: function(){
            // session.track({
                // contextData: {
                    // key: "value"
                // },
                // variables: {
                    // prop1: "P ONE"
                // }
            // });
            // Log("sent track");
        // }
    // },
    // {
        // title: "trackLink",
        // onClick: function(){
            // session.trackLink({
                // linkURL: "http://example.com",
                // linkType: session.LINK_TYPE_FILE_DOWNLOAD,
                // linkName: "example_link",
                // contextData: {
                    // contextKey: "contextValue"
                // },
                // variables: {
                    // eVar3: "E THREE"
                // }
            // });
            // Log("sent trackLink");
        // }
    // },
    // There are no variables, properties, heirs, and lists...
	// {
        // title: "set Vars",
        // onClick: function(){
            // session.eVar1 = "E ONE";
            // session.prop1 = "P ONE";
            // Log("vars set");
        // }
    // },
    // {
        // title: "clearVars",
        // onClick: function(){
            // session.clearVars();
            // Log("vars cleared");
        // }
    // },
    // {
        // title: "set Properties",
        // onClick: function(){
            // session.geoState = "CA";
            // session.geoZip = "94043";
            // session.persistentContextData = {
                // "custom key": "value"
            // };
            // Log("properties set");
        // }
    // },
    {
        title: "Media Tracker",
        onClick: function(){
            // The mediaTracker variable is not used in this example.
            // It is here to show that the MediaTracker object is returned by startMediaTracker 
            var mediaTracker = Omniture.startMediaTracker({
                trackMilestones:"25,50,75"
            });
            // Open the media tracker window
            // mediaTracker is needed in the mediaTracker contoller so it is passed in here
            var args = {mediaTracker: mediaTracker};
            Alloy.createController("mediaTracker", args).getView().open();
        }
    }
];


// There is no 'session' any more. Related configuration is done
// via the ADBMobileConfig.json file.
// var session = Omniture.startSession({
    // reportSuiteIDs: "my_rsid",
    // trackingServer: "<< YOUR TRACKING SERVER ADDRESS HERE >>",
    // ssl: false
// });

function onRowClick(e) {  
    rows[e.index].onClick && rows[e.index].onClick();
}

$.tableView.data = rows;
$.win.open();

