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

	this.name = "session";
	
	// ---------------------------------------------------------------
	// session
	// ---------------------------------------------------------------

	this.testStartSession = function (testRun)
	{
		var session;
		
		valueOf(testRun, function() {
			session = Omniture.startSession();
		}).shouldNotThrowException();
		valueOf(testRun, session).shouldBeObject();

		finish(testRun);
	};

		// Test that all of the Properties are defined
	this.testConstants = function (testRun)
	{
		var session = Omniture.startSession();

		valueOf(testRun, session.LINK_TYPE_CUSTOM).shouldBeString();
		valueOf(testRun, session.LINK_TYPE_FILE_DOWNLOAD).shouldBeString();
		valueOf(testRun, session.LINK_TYPE_EXIT).shouldBeString();

		valueOf(testRun, session.AUTO_TRACK_OPTIONS_NONE).shouldBeNumber();
		valueOf(testRun, session.AUTO_TRACK_OPTIONS_LIFECYCLE).shouldBeNumber();
		valueOf(testRun, session.AUTO_TRACK_OPTIONS_NAVIGATION).shouldBeNumber();

		finish(testRun);
	};	

	// Test that all of the Functions are defined
	this.testFunctions = function (testRun)
	{
		var session = Omniture.startSession();

		valueOf(testRun, session.setAutoTrackingOptions).shouldBeFunction();
		valueOf(testRun, session.setOnLine).shouldBeFunction();
		valueOf(testRun, session.setOffline).shouldBeFunction();

		valueOf(testRun, session.trackAppState).shouldBeFunction();
		valueOf(testRun, session.trackEvents).shouldBeFunction();
		valueOf(testRun, session.track).shouldBeFunction();
		valueOf(testRun, session.trackLink).shouldBeFunction();
		valueOf(testRun, session.trackLight).shouldBeFunction();

		valueOf(testRun, session.getEvar).shouldBeFunction();
		valueOf(testRun, session.getProp).shouldBeFunction();
		valueOf(testRun, session.getHier).shouldBeFunction();
		valueOf(testRun, session.getListVar).shouldBeFunction();

		valueOf(testRun, session.setEvar).shouldBeFunction();
		valueOf(testRun, session.setProp).shouldBeFunction();
		valueOf(testRun, session.setHier).shouldBeFunction();
		valueOf(testRun, session.setListVar).shouldBeFunction();

		valueOf(testRun, session.clearVars).shouldBeFunction();
		valueOf(testRun, session.trackingQueueSize).shouldBeFunction();
		valueOf(testRun, session.clearTrackingQueue).shouldBeFunction();

		valueOf(testRun, session.trackingQueueSize()).shouldBeNumber();

		finish(testRun);
	};

	this.testFuntionCalls = function (testRun)
	{
		var session = Omniture.startSession();

		valueOf(testRun, function() {
			session.clearVars();
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.trackingQueueSize();
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.clearTrackingQueue();
		}).shouldNotThrowException();

		finish(testRun);
	};

	this.testAutoTrackingOptionsFuntionCalls = function (testRun)
	{
		var session = Omniture.startSession();

		valueOf(testRun, function() {
			session.setAutoTrackingOptions();
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.setAutoTrackingOptions([
				session.AUTO_TRACK_OPTIONS_LIFECYCLE,
				session.AUTO_TRACK_OPTIONS_NAVIGATION
			]);
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.setAutoTrackingOptions([
				session.AUTO_TRACK_OPTIONS_NONE
			]);
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.setAutoTrackingOptions(["STRING"]);
		}).shouldThrowException();

		finish(testRun);
	};

	this.testOnlineOfflineFuntionCalls = function (testRun)
	{
		var session = Omniture.startSession();

		valueOf(testRun, function() {
			session.setOffline();
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.setOnline();
		}).shouldNotThrowException();

		finish(testRun);
	};

	this.testTrackAppStateFuntionCalls = function (testRun)
	{
		var session = Omniture.startSession();

		valueOf(testRun, function() {
			session.trackAppState();
		}).shouldThrowException();
		valueOf(testRun, function() {
			session.trackAppState({
				contextData: {}
			});
		}).shouldThrowException();
		valueOf(testRun, function() {
			session.trackAppState({
				appState: "state"
			});
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.trackAppState({
				appState: "state",
				contextData: {}
			});
		}).shouldNotThrowException();

		finish(testRun);
	};

	this.testTrackEventsFuntionCalls = function (testRun)
	{
		var session = Omniture.startSession();

		valueOf(testRun, function() {
			session.trackEvents();
		}).shouldThrowException();
		valueOf(testRun, function() {
			session.trackEvents({
				contextData: {}
			});
		}).shouldThrowException();
		valueOf(testRun, function() {
			session.trackEvents({
				eventNames: "event"
			});
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.trackEvents({
				eventNames: "event",
				contextData: {}
			});
		}).shouldNotThrowException();

		finish(testRun);
	};

	this.testTrackFuntionCalls = function (testRun)
	{
		var session = Omniture.startSession();

		valueOf(testRun, function() {
			session.track();
		}).shouldThrowException();
		valueOf(testRun, function() {
			session.track({});
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.track({
				contextData: {}
			});
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.track({
				variables: {}
			});
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.track({
				contextData: {},
				variables: {}
			});
		}).shouldNotThrowException();

		finish(testRun);
	};

	this.testTrackLinkFuntionCalls = function (testRun)
	{
		var session = Omniture.startSession();

		valueOf(testRun, function() {
			session.trackLink();
		}).shouldThrowException();
		valueOf(testRun, function() {
			session.trackLink({});
		}).shouldThrowException();
		valueOf(testRun, function() {
			session.trackLink({
				linkURL: "http://example.com"
			});
		}).shouldThrowException();
		valueOf(testRun, function() {
			session.trackLink({
				linkURL: "http://example.com",
				linkType: 12
			});
		}).shouldThrowException();
		valueOf(testRun, function() {
			session.trackLink({
				linkType: session.LINK_TYPE_EXIT
			});
		}).shouldThrowException();
		valueOf(testRun, function() {
			session.trackLink({
				linkURL: "http://example.com",
				linkType: session.LINK_TYPE_EXIT
			});
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.trackLink({
				linkURL: "http://example.com",
				linkType: session.LINK_TYPE_CUSTOM
			});
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.trackLink({
				linkURL: "http://example.com",
				linkType: session.LINK_TYPE_FILE_DOWNLOAD
			});
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.trackLink({
				linkURL: "http://example.com",
				linkType: session.LINK_TYPE_FILE_DOWNLOAD,
				linkName: 21
			});
		}).shouldThrowException();
		valueOf(testRun, function() {
			session.trackLink({
				linkURL: "http://example.com",
				linkType: session.LINK_TYPE_FILE_DOWNLOAD,
				linkName: "name"
			});
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.trackLink({
				linkURL: "http://example.com",
				linkType: session.LINK_TYPE_FILE_DOWNLOAD,
				contextData: "STRING"
			});
		}).shouldThrowException();
		valueOf(testRun, function() {
			session.trackLink({
				linkURL: "http://example.com",
				linkType: session.LINK_TYPE_FILE_DOWNLOAD,
				contextData: {}
			});
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.trackLink({
				linkURL: "http://example.com",
				linkType: session.LINK_TYPE_FILE_DOWNLOAD,
				variables: 19
			});
		}).shouldThrowException();
		valueOf(testRun, function() {
			session.trackLink({
				linkURL: "http://example.com",
				linkType: session.LINK_TYPE_FILE_DOWNLOAD,
				variables: {}
			});
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.trackLink({
				linkURL: "http://example.com",
				linkType: session.LINK_TYPE_CUSTOM,
				linkName: "Name",
				contextData: {},
				variables: {}
			});
		}).shouldNotThrowException();

		finish(testRun);
	};

	this.testTrackLightFuntionCalls = function (testRun)
	{
		var session = Omniture.startSession();

		valueOf(testRun, function() {
			session.trackLight();
		}).shouldThrowException();
		valueOf(testRun, function() {
			session.trackLight({});
		}).shouldThrowException();
		valueOf(testRun, function() {
			session.trackLight({
				profileID: "id"
			});
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.trackLight({
				profileID: "id",
				storeForSeconds: 12
			});
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.trackLight({
				profileID: "id",
				incrementBy: 43
			});
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.trackLight({
				profileID: "id",
				storeForSeconds: "STRING"
			});
		}).shouldThrowException();
		valueOf(testRun, function() {
			session.trackLight({
				profileID: "id",
				incrementBy: "STRING"
			});
		}).shouldThrowException();
		valueOf(testRun, function() {
			session.trackLight({
				profileID: "id",
				contextData: "STRING"
			});
		}).shouldThrowException();
		valueOf(testRun, function() {
			session.trackLight({
				profileID: "id",
				contextData: {}
			});
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.trackLight({
				profileID: "id",
				variables: 19
			});
		}).shouldThrowException();
		valueOf(testRun, function() {
			session.trackLight({
				profileID: "id",
				variables: {}
			});
		}).shouldNotThrowException();
		valueOf(testRun, function() {
			session.trackLight({
				profileID: "id",
				storeForSeconds: 33,
				incrementBy: 11,
				contextData: {},
				variables: {}
			});
		}).shouldNotThrowException();

		finish(testRun);
	};

	this.testFunctionReturnTypes = function (testRun)
	{
		var session = Omniture.startSession();

		valueOf(testRun, session.trackingQueueSize()).shouldBeNumber();

		finish(testRun);
	};

	// Test that all of the Functions are defined
	this.testProperties = function (testRun)
	{
		var session = Omniture.startSession();

		valueOf(testRun, session.reportSuiteIDs).shouldBeUndefined();
		valueOf(testRun, session.trackingServer).shouldBeUndefined();

		valueOf(testRun, session.visitorID).shouldBeString();
		valueOf(testRun, session.charSet).shouldBeUndefined();
		valueOf(testRun, session.currencyCode).shouldBeUndefined();
		valueOf(testRun, session.ssl).shouldBeBoolean();

		valueOf(testRun, session.purchaseID).shouldBeUndefined();
		valueOf(testRun, session.transactionID).shouldBeUndefined();

		valueOf(testRun, session.appState).shouldBeUndefined();
		valueOf(testRun, session.channel).shouldBeUndefined();
		valueOf(testRun, session.appSection).shouldBeUndefined();
		valueOf(testRun, session.campaign).shouldBeUndefined();
		valueOf(testRun, session.products).shouldBeUndefined();
		valueOf(testRun, session.events).shouldBeUndefined();

		valueOf(testRun, session.geoState).shouldBeUndefined();
		valueOf(testRun, session.geoZip).shouldBeUndefined();

		valueOf(testRun, session.persistentContextData).shouldBeObject();
		valueOf(testRun, session.lifecycleSessionTimeout).shouldBeNumber();

		valueOf(testRun, session.linkTrackVars).shouldBeUndefined();
		valueOf(testRun, session.linkTrackEvents).shouldBeUndefined();
		valueOf(testRun, session.lightTrackVars).shouldBeUndefined();
		valueOf(testRun, session.offlineTrackingEnabled).shouldBeBoolean();
		valueOf(testRun, session.offlineHitLimit).shouldBeNumber();

		finish(testRun);
	};

	this.testPropertiesSetWithStartSession = function (testRun)
	{
		var reportSuiteIDs = "ids",
			trackingServer = "trackingServer",
			visitorID = "visitorID",
			charSet = "charSet",
			currencyCode = "currencyCode",
			ssl = true,
			purchaseID = "purchaseID",
			transactionID = "transactionID",
			appState = "appState",
			channel = "channel",
			appSection = "appSection",
			campaign = "campaign",
			products = "products",
			events = "events",
			geoState = "geoState",
			geoZip = "geoZip",
			persistentContextData = {hello:"there"},
			lifecycleSessionTimeout = 21,
			linkTrackVars = "linkTrackVars",
			linkTrackEvents = "linkTrackEvents",
			lightTrackVars = "lightTrackVars",
			offlineTrackingEnabled = true,
			offlineHitLimit = 43;

		var session = Omniture.startSession({
			reportSuiteIDs: reportSuiteIDs,
			trackingServer: trackingServer,
			visitorID: visitorID,
			charSet: charSet,
			currencyCode: currencyCode,
			ssl: ssl,
			purchaseID: purchaseID,
			transactionID: transactionID,
			appState: appState,
			channel: channel,
			appSection: appSection,
			campaign: campaign,
			products: products,
			events: events,
			geoState: geoState,
			geoZip: geoZip,
			persistentContextData: persistentContextData,
			lifecycleSessionTimeout: lifecycleSessionTimeout,
			linkTrackVars: linkTrackVars,
			linkTrackEvents: linkTrackEvents,
			lightTrackVars: lightTrackVars,
			offlineTrackingEnabled: offlineTrackingEnabled,
			offlineHitLimit: offlineHitLimit
		});

		valueOf(testRun, session.reportSuiteIDs).shouldBe(reportSuiteIDs);
		valueOf(testRun, session.trackingServer).shouldBe(trackingServer);

		valueOf(testRun, session.visitorID).shouldBe(visitorID);
		valueOf(testRun, session.charSet).shouldBe(charSet);
		valueOf(testRun, session.currencyCode).shouldBe(currencyCode);
		valueOf(testRun, session.ssl).shouldBe(ssl);

		valueOf(testRun, session.purchaseID).shouldBe(purchaseID);
		valueOf(testRun, session.transactionID).shouldBe(transactionID);

		valueOf(testRun, session.appState).shouldBe(appState);
		valueOf(testRun, session.channel).shouldBe(channel);
		valueOf(testRun, session.appSection).shouldBe(appSection);
		valueOf(testRun, session.campaign).shouldBe(campaign);
		valueOf(testRun, session.products).shouldBe(products);
		valueOf(testRun, session.events).shouldBe(events);

		valueOf(testRun, session.geoState).shouldBe(geoState);
		valueOf(testRun, session.geoZip).shouldBe(geoZip);

		valueOf(testRun, session.persistentContextData.hello).shouldBe(persistentContextData.hello);
		valueOf(testRun, session.lifecycleSessionTimeout).shouldBe(lifecycleSessionTimeout);

		valueOf(testRun, session.linkTrackVars).shouldBe(linkTrackVars);
		valueOf(testRun, session.linkTrackEvents).shouldBe(linkTrackEvents);
		valueOf(testRun, session.lightTrackVars).shouldBe(lightTrackVars);
		valueOf(testRun, session.offlineTrackingEnabled).shouldBe(offlineTrackingEnabled);
		valueOf(testRun, session.offlineHitLimit).shouldBe(offlineHitLimit);

		finish(testRun);
	};

	this.testPropertiesSetWithPropertiesOnSession = function (testRun)
	{
		var reportSuiteIDs = "ids",
			trackingServer = "trackingServer",
			visitorID = "visitorID",
			charSet = "charSet",
			currencyCode = "currencyCode",
			ssl = true,
			purchaseID = "purchaseID",
			transactionID = "transactionID",
			appState = "appState",
			channel = "channel",
			appSection = "appSection",
			campaign = "campaign",
			products = "products",
			events = "events",
			geoState = "geoState",
			geoZip = "geoZip",
			persistentContextData = {hello:"there"},
			lifecycleSessionTimeout = 21,
			linkTrackVars = "linkTrackVars",
			linkTrackEvents = "linkTrackEvents",
			lightTrackVars = "lightTrackVars",
			offlineTrackingEnabled = true,
			offlineHitLimit = 43;

		var session = Omniture.startSession();

		session.reportSuiteIDs = reportSuiteIDs;
		session.trackingServer = trackingServer;
		session.visitorID = visitorID;
		session.charSet = charSet;
		session.currencyCode = currencyCode;
		session.ssl = ssl;
		session.purchaseID = purchaseID;
		session.transactionID = transactionID;
		session.appState = appState;
		session.channel = channel;
		session.appSection = appSection;
		session.campaign = campaign;
		session.products = products;
		session.events = events;
		session.geoState = geoState;
		session.geoZip = geoZip;
		session.persistentContextData = persistentContextData;
		session.lifecycleSessionTimeout = lifecycleSessionTimeout;
		session.linkTrackVars = linkTrackVars;
		session.linkTrackEvents = linkTrackEvents;
		session.lightTrackVars = lightTrackVars;
		session.offlineTrackingEnabled = offlineTrackingEnabled;
		session.offlineHitLimit = offlineHitLimit;

		valueOf(testRun, session.reportSuiteIDs).shouldBe(reportSuiteIDs);
		valueOf(testRun, session.trackingServer).shouldBe(trackingServer);

		valueOf(testRun, session.visitorID).shouldBe(visitorID);
		valueOf(testRun, session.charSet).shouldBe(charSet);
		valueOf(testRun, session.currencyCode).shouldBe(currencyCode);
		valueOf(testRun, session.ssl).shouldBe(ssl);

		valueOf(testRun, session.purchaseID).shouldBe(purchaseID);
		valueOf(testRun, session.transactionID).shouldBe(transactionID);

		valueOf(testRun, session.appState).shouldBe(appState);
		valueOf(testRun, session.channel).shouldBe(channel);
		valueOf(testRun, session.appSection).shouldBe(appSection);
		valueOf(testRun, session.campaign).shouldBe(campaign);
		valueOf(testRun, session.products).shouldBe(products);
		valueOf(testRun, session.events).shouldBe(events);

		valueOf(testRun, session.geoState).shouldBe(geoState);
		valueOf(testRun, session.geoZip).shouldBe(geoZip);

		valueOf(testRun, session.persistentContextData.hello).shouldBe(persistentContextData.hello);
		valueOf(testRun, session.lifecycleSessionTimeout).shouldBe(lifecycleSessionTimeout);

		valueOf(testRun, session.linkTrackVars).shouldBe(linkTrackVars);
		valueOf(testRun, session.linkTrackEvents).shouldBe(linkTrackEvents);
		valueOf(testRun, session.lightTrackVars).shouldBe(lightTrackVars);
		valueOf(testRun, session.offlineTrackingEnabled).shouldBe(offlineTrackingEnabled);
		valueOf(testRun, session.offlineHitLimit).shouldBe(offlineHitLimit);

		finish(testRun);
	};

	this.testPropertiesSetWithStartSessionThenOverriddenByPropertiesOnSession = function (testRun)
	{
		var _reportSuiteIDs = "_ids",
			_trackingServer = "_trackingServer",
			_visitorID = "_visitorID",
			_charSet = "_charSet",
			_currencyCode = "_currencyCode",
			_ssl = false,
			_purchaseID = "_purchaseID",
			_transactionID = "_transactionID",
			_appState = "_appState",
			_channel = "_channel",
			_appSection = "_appSection",
			_campaign = "-campaign",
			_products = "-products",
			_events = "-events",
			_geoState = "_geoState",
			_geoZip = "_geoZip",
			_persistentContextData = {hello:"_there"},
			_lifecycleSessionTimeout = 211,
			_linkTrackVars = "_linkTrackVars",
			_linkTrackEvents = "_linkTrackEvents",
			_lightTrackVars = "_lightTrackVars",
			_offlineTrackingEnabled = false,
			_offlineHitLimit = 431;

		var reportSuiteIDs = "ids",
			trackingServer = "trackingServer",
			visitorID = "visitorID",
			charSet = "charSet",
			currencyCode = "currencyCode",
			ssl = true,
			purchaseID = "purchaseID",
			transactionID = "transactionID",
			appState = "appState",
			channel = "channel",
			appSection = "appSection",
			campaign = "campaign",
			products = "products",
			events = "events",
			geoState = "geoState",
			geoZip = "geoZip",
			persistentContextData = {hello:"there"},
			lifecycleSessionTimeout = 21,
			linkTrackVars = "linkTrackVars",
			linkTrackEvents = "linkTrackEvents",
			lightTrackVars = "lightTrackVars",
			offlineTrackingEnabled = true,
			offlineHitLimit = 43;

		var session = Omniture.startSession({
			reportSuiteIDs: _reportSuiteIDs,
			trackingServer: _trackingServer,
			visitorID: _visitorID,
			charSet: _charSet,
			currencyCode: _currencyCode,
			ssl: _ssl,
			purchaseID: _purchaseID,
			transactionID: _transactionID,
			appState: _appState,
			channel: _channel,
			appSection: _appSection,
			campaign: _campaign,
			products: _products,
			events: _events,
			geoState: _geoState,
			geoZip: _geoZip,
			persistentContextData: _persistentContextData,
			lifecycleSessionTimeout: _lifecycleSessionTimeout,
			linkTrackVars: _linkTrackVars,
			linkTrackEvents: _linkTrackEvents,
			lightTrackVars: _lightTrackVars,
			offlineTrackingEnabled: _offlineTrackingEnabled,
			offlineHitLimit: _offlineHitLimit
		});

		session.reportSuiteIDs = reportSuiteIDs;
		session.trackingServer = trackingServer;
		session.visitorID = visitorID;
		session.charSet = charSet;
		session.currencyCode = currencyCode;
		session.ssl = ssl;
		session.purchaseID = purchaseID;
		session.transactionID = transactionID;
		session.appState = appState;
		session.channel = channel;
		session.appSection = appSection;
		session.campaign = campaign;
		session.products = products;
		session.events = events;
		session.geoState = geoState;
		session.geoZip = geoZip;
		session.persistentContextData = persistentContextData;
		session.lifecycleSessionTimeout = lifecycleSessionTimeout;
		session.linkTrackVars = linkTrackVars;
		session.linkTrackEvents = linkTrackEvents;
		session.lightTrackVars = lightTrackVars;
		session.offlineTrackingEnabled = offlineTrackingEnabled;
		session.offlineHitLimit = offlineHitLimit;

		valueOf(testRun, session.reportSuiteIDs).shouldBe(reportSuiteIDs);
		valueOf(testRun, session.trackingServer).shouldBe(trackingServer);

		valueOf(testRun, session.visitorID).shouldBe(visitorID);
		valueOf(testRun, session.charSet).shouldBe(charSet);
		valueOf(testRun, session.currencyCode).shouldBe(currencyCode);
		valueOf(testRun, session.ssl).shouldBe(ssl);

		valueOf(testRun, session.purchaseID).shouldBe(purchaseID);
		valueOf(testRun, session.transactionID).shouldBe(transactionID);

		valueOf(testRun, session.appState).shouldBe(appState);
		valueOf(testRun, session.channel).shouldBe(channel);
		valueOf(testRun, session.appSection).shouldBe(appSection);
		valueOf(testRun, session.campaign).shouldBe(campaign);
		valueOf(testRun, session.products).shouldBe(products);
		valueOf(testRun, session.events).shouldBe(events);

		valueOf(testRun, session.geoState).shouldBe(geoState);
		valueOf(testRun, session.geoZip).shouldBe(geoZip);

		valueOf(testRun, session.persistentContextData.hello).shouldBe(persistentContextData.hello);
		valueOf(testRun, session.lifecycleSessionTimeout).shouldBe(lifecycleSessionTimeout);

		valueOf(testRun, session.linkTrackVars).shouldBe(linkTrackVars);
		valueOf(testRun, session.linkTrackEvents).shouldBe(linkTrackEvents);
		valueOf(testRun, session.lightTrackVars).shouldBe(lightTrackVars);
		valueOf(testRun, session.offlineTrackingEnabled).shouldBe(offlineTrackingEnabled);
		valueOf(testRun, session.offlineHitLimit).shouldBe(offlineHitLimit);

		finish(testRun);
	};

	this.testNumberedEvarsUndefined = function (testRun)
	{
		var session = Omniture.startSession();
		session.clearVars();

		valueOf(testRun, session.eVar0).shouldBeUndefined();
		valueOf(testRun, session.eVar1).shouldBeUndefined();
		valueOf(testRun, session.eVar35).shouldBeUndefined();
		valueOf(testRun, session.eVar75).shouldBeUndefined();
		valueOf(testRun, session.eVar76).shouldBeUndefined();

		finish(testRun);
	};

	this.testNumberedPropsUndefined = function (testRun)
	{
		var session = Omniture.startSession();
		session.clearVars();

		valueOf(testRun, session.prop0).shouldBeUndefined();
		valueOf(testRun, session.prop1).shouldBeUndefined();
		valueOf(testRun, session.prop35).shouldBeUndefined();
		valueOf(testRun, session.prop75).shouldBeUndefined();
		valueOf(testRun, session.prop76).shouldBeUndefined();

		finish(testRun);
	};

	this.testNumberedHiersUndefined = function (testRun)
	{
		var session = Omniture.startSession();
		session.clearVars();

		valueOf(testRun, session.hier0).shouldBeUndefined();
		valueOf(testRun, session.hier1).shouldBeUndefined();
		valueOf(testRun, session.hier3).shouldBeUndefined();
		valueOf(testRun, session.hier5).shouldBeUndefined();
		valueOf(testRun, session.hier6).shouldBeUndefined();

		finish(testRun);
	};

	this.testNumberedListsUndefined = function (testRun)
	{
		var session = Omniture.startSession();
		session.clearVars();

		valueOf(testRun, session.list0).shouldBeUndefined();
		valueOf(testRun, session.list1).shouldBeUndefined();
		valueOf(testRun, session.list2).shouldBeUndefined();
		valueOf(testRun, session.list3).shouldBeUndefined();
		valueOf(testRun, session.list4).shouldBeUndefined();

		finish(testRun);
	};

	this.testNumberedPropertiesSet = function (testRun)
	{
		var eVar1 = "E ONE",
			eVar3 = "E THREE",
			prop1 = "P ONE",
			prop3 = "P THREE",
			hier1 = "H ONE",
			hier3 = "H THREE",
			list1 = "L ONE",
			list3 = "L THREE";

		var session = Omniture.startSession({
			eVar1: eVar1,
			eVar3: eVar3,
			prop1: prop1,
			prop3: prop3,
			hier1: hier1,
			hier3: hier3,
			list1: list1,
			list3: list3
		});

		valueOf(testRun, session.eVar1).shouldBe(eVar1);
		valueOf(testRun, session.eVar3).shouldBe(eVar3);

		valueOf(testRun, session.prop1).shouldBe(prop1);
		valueOf(testRun, session.prop3).shouldBe(prop3);

		valueOf(testRun, session.hier1).shouldBe(hier1);
		valueOf(testRun, session.hier3).shouldBe(hier3);

		valueOf(testRun, session.list1).shouldBe(list1);
		valueOf(testRun, session.list3).shouldBe(list3);

		finish(testRun);
	};

	this.testNumberedPropertiesBadSet = function (testRun)
	{
		var eVar0 = "E ONE",
			eVar99 = "E THREE",
			prop0 = "P ONE",
			prop99 = "P THREE",
			hier0 = "H ONE",
			hier99 = "H THREE",
			list0 = "L ONE",
			list99 = "L THREE";

		var session = Omniture.startSession({
			eVar0: eVar0,
			eVar99: eVar99,
			prop0: prop0,
			prop99: prop99,
			hier0: hier0,
			hier99: hier99,
			list0: list0,
			list99: list99
		});

		valueOf(testRun, session.eVar0).shouldBeUndefined();
		valueOf(testRun, session.eVar99).shouldBeUndefined();

		valueOf(testRun, session.prop0).shouldBeUndefined();
		valueOf(testRun, session.prop99).shouldBeUndefined();

		valueOf(testRun, session.hier0).shouldBeUndefined();
		valueOf(testRun, session.hier99).shouldBeUndefined();

		valueOf(testRun, session.list0).shouldBeUndefined();
		valueOf(testRun, session.list99).shouldBeUndefined();

		finish(testRun);
	};

	this.testNumberedPropertiesClear = function (testRun)
	{
		var eVar1 = "E ONE",
			eVar3 = "E THREE",
			prop1 = "P ONE",
			prop3 = "P THREE",
			hier1 = "H ONE",
			hier3 = "H THREE",
			list1 = "L ONE",
			list3 = "L THREE";

		var session = Omniture.startSession({
			eVar1: eVar1,
			eVar3: eVar3,
			prop1: prop1,
			prop3: prop3,
			hier1: hier1,
			hier3: hier3,
			list1: list1,
			list3: list3
		});

		valueOf(testRun, function() {
			session.clearVars();
		}).shouldNotThrowException();

		valueOf(testRun, session.eVar1).shouldBeUndefined();
		valueOf(testRun, session.eVar3).shouldBeUndefined();

		valueOf(testRun, session.prop1).shouldBeUndefined();
		valueOf(testRun, session.prop3).shouldBeUndefined();

		valueOf(testRun, session.hier1).shouldBeUndefined();
		valueOf(testRun, session.hier3).shouldBeUndefined();

		valueOf(testRun, session.list1).shouldBeUndefined();
		valueOf(testRun, session.list3).shouldBeUndefined();

		finish(testRun);
	};

	// Populate the array of tests based on the 'hammer' convention
	this.tests = require('hammer').populateTests(this);
};
