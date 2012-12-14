package org.jtelegraph.audio;

import org.jtelegraph.Telegraph;
import org.pushingpixels.trident.Timeline;
import org.pushingpixels.trident.Timeline.TimelineState;
import org.pushingpixels.trident.callback.TimelineCallback;

/**
 * This simple callback allows to play a sound notification before calling
 * another timeline
 * 
 * @author Antoine Neveux
 * @version 2.1
 * @since 2.1
 * 
 */
public class AudioCallback implements TimelineCallback {

	// the achievement
	private final Telegraph telegraph;

	// the next timeline
	private final Timeline nextTimeline;

	/**
	 * Constructor.
	 * 
	 * @param telegraph
	 *            The telegraph.
	 * @param nextTimeline
	 *            the timeline to call after playing the sound
	 */
	public AudioCallback(final Telegraph telegraph, final Timeline nextTimeline) {
		// set it
		this.telegraph = telegraph;
		this.nextTimeline = nextTimeline;
	}

	@Override
	public void onTimelineStateChanged(final TimelineState oldState,
			final TimelineState newState, final float durationFraction,
			final float timelinePosition) {
		// if the current timeline is done
		if (newState == Timeline.TimelineState.DONE)
			// play next
			nextTimeline.play();
	}

	@Override
	public void onTimelinePulse(final float durationFraction,
			final float timelinePosition) {
		if (telegraph.getConfig().isAudioEnabled()) {
			final TelegraphSound sound = new TelegraphSound(telegraph
					.getConfig().getAudioInputStream());
			sound.start();
		}
	}

}
