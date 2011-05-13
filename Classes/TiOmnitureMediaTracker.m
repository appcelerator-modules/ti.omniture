/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2010 by Appcelerator, Inc. All Rights Reserved.
 */

#import "TiOmnitureMediaTracker.h"
#import "TiUtils.h"
#import "TiMediaVideoPlayerProxy.h"
#import "TiOmnitureSession.h"

@interface TiMediaVideoPlayerProxy(Private)
-(MPMoviePlayerController*)player;
@end


@implementation TiOmnitureMediaTracker

-(void)sendCloseEvent
{
	ENSURE_UI_THREAD_0_ARGS
	NSLog(@"[DEBUG] sending media close event: %@",mediaName);
	[measurement.Media close:mediaName];
	[measurement.Media track:mediaName];
	openSent = NO;
	closeSent = YES;
}

-(void)_destroy
{
	[[NSNotificationCenter defaultCenter] removeObserver:self];
	if (closeSent==NO)
	{
		[self performSelectorOnMainThread:@selector(sendCloseEvent) withObject:nil waitUntilDone:YES];
	}
	RELEASE_TO_NIL(measurement);
	if (timer!=nil && [timer isValid])
	{
		[timer invalidate];
	}
	RELEASE_TO_NIL(timer);
	RELEASE_TO_NIL(mediaName);
	[super _destroy];
}

-(void)sendOpenEvent
{
	ENSURE_UI_THREAD_0_ARGS
	
	if (openSent) return;

	NSString *playerName = [self valueForUndefinedKey:@"playerName"];
	NSString *playerId = [self valueForUndefinedKey:@"playerID"];
	NSString *cuePoints = [self valueForUndefinedKey:@"cuePoints"];
	if (cuePoints!=nil) {
		[measurement.Media setTrackAtCuePoints:YES];
	}
	
	NSLog(@"[DEBUG] sending media open event: %@ (%@)",playerName,mediaName);
	[measurement.Media open:mediaName length:duration playerName:playerName cuePoints:cuePoints playerID:playerId];
	openSent = YES;
	closeSent = NO;
}

-(void)setMediaName:(id)args
{
	ENSURE_SINGLE_ARG(args,NSString);
	RELEASE_TO_NIL(mediaName);
	mediaName = [args retain];
	NSLog(@"[DEBUG] media name set to: %@", mediaName);
}

-(void)_initWithProperties:(NSDictionary*)properties
{
	// we need to wait and make sure the player is initialized
	ENSURE_UI_THREAD_1_ARG(properties)   
							   
	[super _initWithProperties:properties];
	
	TiOmnitureSession *session = (TiOmnitureSession*)[properties objectForKey:@"session"];
	TiMediaVideoPlayerProxy *player = [properties objectForKey:@"player"];
	MPMoviePlayerController *controller = [player player];
	duration = controller.duration;

	[[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(handleDuration:) name:MPMovieDurationAvailableNotification object:controller];
	[[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(handlePlayerStateChanged:) name:MPMoviePlayerPlaybackStateDidChangeNotification object:controller];
	[[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(handlePlayerFinished:) name:MPMoviePlayerPlaybackDidFinishNotification object:controller];
	
	measurement = [[session session] retain];
	
	if (duration > 0)
	{
		[self sendOpenEvent];
	}
}

#pragma mark Delegate
-(void)timerTick:(id)sender
{
	offset+=1;
}

-(void)handleDuration:(NSNotification*)note
{
	duration = [(MPMoviePlayerController*)[note object] duration];
	NSLog(@"[DEBUG] player duration determined: %f",duration);
	[self sendOpenEvent];
}

-(void)handlePlayerFinished:(NSNotification*)note
{
	offset = 0;
	[self sendCloseEvent];
}

-(void)handlePlayerStateChanged:(NSNotification*)note
{
	MPMoviePlayerController *controller = (MPMoviePlayerController*)[note object];
	MPMoviePlaybackState state = controller.playbackState;
	
	NSLog(@"[DEBUG] player state changed = %@, state = %d",note,state);

	switch (state) 
	{
		case MPMoviePlaybackStatePlaying:
		{
			// we have to perform our own timer during play to track offset since it seems
			// we can't currently get that from the media player
			// TODO: probably just use a time offset instead of needing a timer -JGH
			timer = [[NSTimer scheduledTimerWithTimeInterval:1 target:self selector:@selector(timerTick:) userInfo:nil repeats:YES] retain];
			if (openSent==NO)
			{
				duration = [controller duration];
				[self sendOpenEvent];
			}
			[measurement.Media play:mediaName offset:offset];
			break;
		}
		case MPMoviePlaybackStatePaused:
		case MPMoviePlaybackStateStopped:
		{
			[timer invalidate];
			RELEASE_TO_NIL(timer);
			[measurement.Media stop:mediaName offset:offset];
			
			if (state == MPMoviePlaybackStateStopped)
			{
				[self sendCloseEvent];
			}
			break;
		}
		default:
			break;
	}

}

	
@end
