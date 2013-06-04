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

	this.name = "mediaTracker";
	
	// ---------------------------------------------------------------
	// mediaTracker
	// ---------------------------------------------------------------

	this.testFunctions = function (testRun)
	{
		valueOf(testRun, function() {
			Omniture.startMediaTracker();
		}).shouldNotThrowException();

		finish(testRun);
	}

	this.testProperties = function (testRun)
	{
		var mediaTracker = Omniture.startMediaTracker();

		valueOf(testRun, mediaTracker.trackVars).shouldBe("None");
		valueOf(testRun, mediaTracker.trackEvents).shouldBe("None");
		valueOf(testRun, mediaTracker.channel).shouldBeUndefined();
		valueOf(testRun, mediaTracker.completeCloseOffsetThreshold).shouldBeNumber();
		valueOf(testRun, mediaTracker.contextDataMapping).shouldBeUndefined();

		valueOf(testRun, mediaTracker.trackSeconds).shouldBeNumber();
		valueOf(testRun, mediaTracker.trackMilestones).shouldBeUndefined();
		valueOf(testRun, mediaTracker.segmentByMilestones).shouldBeBoolean();
		valueOf(testRun, mediaTracker.trackOffsetMilestones).shouldBeUndefined();
		valueOf(testRun, mediaTracker.segmentByOffsetMilestones).shouldBeBoolean();

		valueOf(testRun, mediaTracker.adTrackSeconds).shouldBeNumber();
		valueOf(testRun, mediaTracker.adTrackMilestones).shouldBeUndefined();
		valueOf(testRun, mediaTracker.adSegmentByMilestones).shouldBeBoolean();
		valueOf(testRun, mediaTracker.adTrackOffsetMilestones).shouldBeUndefined();
		valueOf(testRun, mediaTracker.adSegmentByOffsetMilestones).shouldBeBoolean();

		finish(testRun);
	};

	// Test that all of the Properties are defined
	this.testPropertiesSetWithStartMediaTracker = function (testRun)
	{
		var trackVars = "trackVars",
			trackEvents = "trackEvents",
			channel = "channel",
			completeCloseOffsetThreshold = 2,
			contextDataMapping = {hello: "there"},

			trackSeconds = 21,
			trackMilestones = "trackMilestones",
			segmentByMilestones = true,
			trackOffsetMilestones = "trackOffsetMilestones",
			segmentByOffsetMilestones = true,

			adTrackSeconds = 19,
			adTrackMilestones = "adTrackMilestones",
			adSegmentByMilestones = true,
			adTrackOffsetMilestones = "adTrackOffsetMilestones",
			adSegmentByOffsetMilestones = true;

		var mediaTracker = Omniture.startMediaTracker({
		    trackVars: trackVars,
		    trackEvents: trackEvents,
		    channel: channel,
		    completeCloseOffsetThreshold: completeCloseOffsetThreshold,
		    contextDataMapping: contextDataMapping,

		    trackSeconds: trackSeconds,
		    trackMilestones: trackMilestones,
		    segmentByMilestones: segmentByMilestones,
		    trackOffsetMilestones: trackOffsetMilestones,
		    segmentByOffsetMilestones: segmentByOffsetMilestones,

		    adTrackSeconds: adTrackSeconds,
		    adTrackMilestones: adTrackMilestones,
		    adSegmentByMilestones: adSegmentByMilestones,
		    adTrackOffsetMilestones: adTrackOffsetMilestones,
		    adSegmentByOffsetMilestones: adSegmentByOffsetMilestones
		});

		valueOf(testRun, mediaTracker.trackVars).shouldBe(trackVars);
		valueOf(testRun, mediaTracker.trackEvents).shouldBe(trackEvents);
		valueOf(testRun, mediaTracker.channel).shouldBe(channel);
		valueOf(testRun, mediaTracker.completeCloseOffsetThreshold).shouldBe(completeCloseOffsetThreshold);
		valueOf(testRun, mediaTracker.contextDataMapping.hello).shouldBe(contextDataMapping.hello);

		valueOf(testRun, mediaTracker.trackSeconds).shouldBe(trackSeconds);
		valueOf(testRun, mediaTracker.trackMilestones).shouldBe(trackMilestones);
		valueOf(testRun, mediaTracker.segmentByMilestones).shouldBe(segmentByMilestones);
		valueOf(testRun, mediaTracker.trackOffsetMilestones).shouldBe(trackOffsetMilestones);
		valueOf(testRun, mediaTracker.segmentByOffsetMilestones).shouldBe(segmentByOffsetMilestones);

		valueOf(testRun, mediaTracker.adTrackSeconds).shouldBe(adTrackSeconds);
		valueOf(testRun, mediaTracker.adTrackMilestones).shouldBe(adTrackMilestones);
		valueOf(testRun, mediaTracker.adSegmentByMilestones).shouldBe(adSegmentByMilestones);
		valueOf(testRun, mediaTracker.adTrackOffsetMilestones).shouldBe(adTrackOffsetMilestones);
		valueOf(testRun, mediaTracker.adSegmentByOffsetMilestones).shouldBe(adSegmentByOffsetMilestones);

		finish(testRun);
	};

	this.testPropertiesSetWithStartMediaTrackerAndOverriddenWithProperties = function (testRun)
	{
		var _trackVars = "_trackVars",
			_trackEvents = "_trackEvents",
			_channel = "_channel",
			_completeCloseOffsetThreshold = 12,
			_contextDataMapping = {hello: "_there"},

			_trackSeconds = 121,
			_trackMilestones = "_trackMilestones",
			_segmentByMilestones = false,
			_trackOffsetMilestones = "_trackOffsetMilestones",
			_segmentByOffsetMilestones = false,

			_adTrackSeconds = 119,
			_adTrackMilestones = "_adTrackMilestones",
			_adSegmentByMilestones = false,
			_adTrackOffsetMilestones = "_adTrackOffsetMilestones",
			_adSegmentByOffsetMilestones = false;

		var trackVars = "trackVars",
			trackEvents = "trackEvents",
			channel = "channel",
			completeCloseOffsetThreshold = 2,
			contextDataMapping = {hello: "there"},

			trackSeconds = 21,
			trackMilestones = "trackMilestones",
			segmentByMilestones = true,
			trackOffsetMilestones = "trackOffsetMilestones",
			segmentByOffsetMilestones = true,

			adTrackSeconds = 19,
			adTrackMilestones = "adTrackMilestones",
			adSegmentByMilestones = true,
			adTrackOffsetMilestones = "adTrackOffsetMilestones",
			adSegmentByOffsetMilestones = true;

		var mediaTracker = Omniture.startMediaTracker({
		    trackVars: _trackVars,
		    trackEvents: _trackEvents,
		    channel: _channel,
		    completeCloseOffsetThreshold: _completeCloseOffsetThreshold,
		    contextDataMapping: _contextDataMapping,

		    trackSeconds: _trackSeconds,
		    trackMilestones: _trackMilestones,
		    segmentByMilestones: _segmentByMilestones,
		    trackOffsetMilestones: _trackOffsetMilestones,
		    segmentByOffsetMilestones: _segmentByOffsetMilestones,

		    adTrackSeconds: _adTrackSeconds,
		    adTrackMilestones: _adTrackMilestones,
		    adSegmentByMilestones: _adSegmentByMilestones,
		    adTrackOffsetMilestones: _adTrackOffsetMilestones,
		    adSegmentByOffsetMilestones: _adSegmentByOffsetMilestones
		});

		mediaTracker.trackVars = trackVars;
		mediaTracker.trackEvents = trackEvents;
		mediaTracker.channel = channel;
		mediaTracker.completeCloseOffsetThreshold = completeCloseOffsetThreshold;
		mediaTracker.contextDataMapping = contextDataMapping;

		mediaTracker.trackSeconds = trackSeconds;
		mediaTracker.trackMilestones = trackMilestones;
		mediaTracker.segmentByMilestones = segmentByMilestones;
		mediaTracker.trackOffsetMilestones = trackOffsetMilestones;
		mediaTracker.segmentByOffsetMilestones = segmentByOffsetMilestones;

		mediaTracker.adTrackSeconds = adTrackSeconds;
		mediaTracker.adTrackMilestones = adTrackMilestones;
		mediaTracker.adSegmentByMilestones = adSegmentByMilestones;
		mediaTracker.adTrackOffsetMilestones = adTrackOffsetMilestones;
		mediaTracker.adSegmentByOffsetMilestones = adSegmentByOffsetMilestones;

		valueOf(testRun, mediaTracker.trackVars).shouldBe(trackVars);
		valueOf(testRun, mediaTracker.trackEvents).shouldBe(trackEvents);
		valueOf(testRun, mediaTracker.channel).shouldBe(channel);
		valueOf(testRun, mediaTracker.completeCloseOffsetThreshold).shouldBe(completeCloseOffsetThreshold);
		valueOf(testRun, mediaTracker.contextDataMapping.hello).shouldBe(contextDataMapping.hello);

		valueOf(testRun, mediaTracker.trackSeconds).shouldBe(trackSeconds);
		valueOf(testRun, mediaTracker.trackMilestones).shouldBe(trackMilestones);
		valueOf(testRun, mediaTracker.segmentByMilestones).shouldBe(segmentByMilestones);
		valueOf(testRun, mediaTracker.trackOffsetMilestones).shouldBe(trackOffsetMilestones);
		valueOf(testRun, mediaTracker.segmentByOffsetMilestones).shouldBe(segmentByOffsetMilestones);

		valueOf(testRun, mediaTracker.adTrackSeconds).shouldBe(adTrackSeconds);
		valueOf(testRun, mediaTracker.adTrackMilestones).shouldBe(adTrackMilestones);
		valueOf(testRun, mediaTracker.adSegmentByMilestones).shouldBe(adSegmentByMilestones);
		valueOf(testRun, mediaTracker.adTrackOffsetMilestones).shouldBe(adTrackOffsetMilestones);
		valueOf(testRun, mediaTracker.adSegmentByOffsetMilestones).shouldBe(adSegmentByOffsetMilestones);

		finish(testRun);
	};

	// Populate the array of tests based on the 'hammer' convention
	this.tests = require('hammer').populateTests(this);
};
