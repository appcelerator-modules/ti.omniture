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
	NSLog(@"[DEBUG] sending media close event: %@",playerName);
	[measurement.Media close:playerName];
	[measurement.Media track:playerName];
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
	RELEASE_TO_NIL(playerName);
	[super _destroy];
}

-(void)sendOpenEvent
{
	ENSURE_UI_THREAD_0_ARGS
	
	if (openSent) return;
	
	NSString *name = [self valueForUndefinedKey:@"name"];
	NSString *cuePoints = [self valueForUndefinedKey:@"cuePoints"];
	NSString *playerId = [self valueForUndefinedKey:@"playerID"];
	if (cuePoints!=nil)
	{
		[measurement.Media setTrackAtCuePoints:YES];
	}
	[measurement.Media open:name length:duration playerName:playerName cuePoints:cuePoints playerID:playerId];
	openSent = YES;
	NSLog(@"[DEBUG] sending media open event: %@ (%@)",playerName,measurement.Media);
}

-(void)setPlayerName:(id)args
{
	ENSURE_SINGLE_ARG(args,NSString);
	NSLog(@"[DEBUG] set player name called: %@",args);
	RELEASE_TO_NIL(playerName);
	playerName = [args retain];
}

-(void)_initWithProperties:(NSDictionary*)properties
{
	[super _initWithProperties:properties];
	
	NSLog(@"initWithProperties");

	// we need to wait and make sure the player is initialized
	ENSURE_UI_THREAD_0_ARGS
	
	TiOmnitureSession *session = (TiOmnitureSession*)[self valueForUndefinedKey:@"session"];
	TiMediaVideoPlayerProxy *player = [self valueForUndefinedKey:@"player"];
	MPMoviePlayerController *controller = [player player];
	if (controller == nil)
		NSLog(@"Controller is nil");
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
			[measurement.Media play:playerName offset:offset];
			break;
		}
		case MPMoviePlaybackStatePaused:
		case MPMoviePlaybackStateStopped:
		{
			[timer invalidate];
			RELEASE_TO_NIL(timer);
			[measurement.Media stop:playerName offset:offset];
			
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
