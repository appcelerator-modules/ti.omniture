/*
 * Appcelerator Titanium Mobile
 * Copyright (c) 2011-2013 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */

module.exports = new function ()
{
    var finish;
    var valueOf;
    var Omniture;
    
    this.init = function (testUtils)
    {
        finish = testUtils.finish;
        valueOf = testUtils.valueOf;
        Omniture = require('ti.omniture');
    };

    this.name = "omniture";
    
    // ---------------------------------------------------------------
    // omniture
    // ---------------------------------------------------------------

    // Test that module is loaded
    this.testModule = function (testRun)
    {
        // Verify that the module is defined
        valueOf(testRun, Omniture).shouldBeObject();
        finish(testRun);
    };
    
    // Test that all of the Functions are defined
    this.testFunctions = function (testRun)
    {
        valueOf(testRun, Omniture.startSession).shouldBeFunction();
        valueOf(testRun, Omniture.startMediaTracker).shouldBeFunction();

        finish(testRun);
    };

    // Test that all of the Properties are defined
    this.testProperties = function (testRun)
    {
        valueOf(testRun, Omniture.version).shouldBeString();
        // Property Getters
        valueOf(testRun, Omniture.getVersion).shouldBeFunction();
        // Property Setters
        valueOf(testRun, Omniture.setDebugLogging).shouldBeFunction();

        finish(testRun);
    };

    // calling session or mediaTracker multiple times with dif properties should be set
    this.testStartSessionCalledMultipleTimes = function (testRun)
    {
        var visitorID = "visitorID",
            eVar1 = "E ONE";

        var session = Omniture.startSession();

        valueOf(testRun, session.visitorID).shouldBeString();
        valueOf(testRun, session.visitorID).shouldNotBe(visitorID);
        valueOf(testRun, session.eVar1).shouldBeUndefined();

        var session2 = Omniture.startSession({
            visitorID: visitorID,
            eVar1: eVar1
        });

        valueOf(testRun, session.visitorID).shouldBe(visitorID);
        valueOf(testRun, session.eVar1).shouldBe(eVar1);
        
        finish(testRun);
    };

    // Populate the array of tests based on the 'hammer' convention
    this.tests = require('hammer').populateTests(this);
};
