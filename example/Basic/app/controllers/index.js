
var rows = [
    {
        title: "Omniture SDK version",
        onClick: function(){
            Log("Version: " + Omniture.version);
        }
    },
    {
        title: "trackAppState",
        onClick: function(){
            session.trackAppState({
                appState: "state1",
                contextData: {
                    key: "value"
                }
            });
            Log("sent trackAppState");
        }
    },
    {
        title: "trackEvents",
        onClick: function(){
            session.trackEvents({
                eventNames: "event1, event2",
                contextData: {
                    key: "value"
                }
            });
            Log("sent trackEvents");
        }
    },
    {
        title: "track",
        onClick: function(){
            session.track({
                contextData: {
                    key: "value"
                },
                variables: {
                    prop1: "P ONE"
                }
            });
            Log("sent track");
        }
    },
    {
        title: "trackLink",
        onClick: function(){
            session.trackLink({
                linkURL: "http://example.com",
                linkType: session.LINK_TYPE_FILE_DOWNLOAD,
                linkName: "example_link",
                contextData: {
                    contextKey: "contextValue"
                },
                variables: {
                    eVar3: "E THREE"
                }
            });
            Log("sent trackLink");
        }
    },
    {
        title: "set Vars",
        onClick: function(){
            session.eVar1 = "E ONE";
            session.prop1 = "P ONE";
            Log("vars set");
        }
    },
    {
        title: "clearVars",
        onClick: function(){
            session.clearVars();
            Log("vars cleared");
        }
    },
    {
        title: "set Properties",
        onClick: function(){
            session.geoState = "CA";
            session.geoZip = "94043";
            session.persistentContextData = {
                "custom key": "value"
            };
            Log("properties set");
        }
    },
    {
        title: "Media Tracker",
        onClick: function(){
            // The mediaTracker variable is not used in this example.
            // It is here to show that the MediaTracker object is returned by startMediaTracker 
            var mediaTracker = Omniture.startMediaTracker({
                trackMilestones:"25,50,75",
                contextDataMapping: {
                    "a.media.name": "eVar2,prop2",
                    "a.media.segment": "eVar3",
                    "a.contentType": "eVar1", //note that this is not in the .media namespace
                    "a.media.timePlayed": "event3",
                    "a.media.view": "event1",
                    "a.media.segmentView": "event2",
                    "a.media.complete": "event7"
                }
            });
            // Open the media tracker window
            Alloy.createController("mediaTracker").getView().open();
        }
    }
];

var Omniture = require("ti.omniture");
Omniture.debugLogging = true;

// Session properties can be set by passing them to startSession
// properties can also be set on the session object that is returned by startSession
var session = Omniture.startSession({
    reportSuiteIDs: "my_rsid",
    trackingServer: "<< YOUR TRACKING SERVER ADDRESS HERE >>",
    ssl: false
});
session.setAutoTrackingOptions([session.AUTO_TRACK_OPTIONS_LIFECYCLE]); // AUTO_TRACK_OPTIONS_LIFECYCLE is default

function Log(text) {
    $.textLog.value = text + "\n" + $.textLog.value;
    Ti.API.info(text);
}

function onRowClick(e) {  
    e.source.onClick && e.source.onClick();
}

$.tableView.data = rows;
$.win.open();

