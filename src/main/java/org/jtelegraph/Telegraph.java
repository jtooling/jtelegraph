/**
 * JTelegraph -- a Java message notification library
 * Copyright (c) 2012, Paulo Roberto Massa Cereda
 * All rights reserved.
 *
 * Redistribution and  use in source  and binary forms, with  or without
 * modification, are  permitted provided  that the  following conditions
 * are met:
 *
 * 1. Redistributions  of source  code must  retain the  above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form  must reproduce the above copyright
 * notice, this list  of conditions and the following  disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * 3. Neither  the name  of the  project's author nor  the names  of its
 * contributors may be used to  endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS  PROVIDED BY THE COPYRIGHT  HOLDERS AND CONTRIBUTORS
 * "AS IS"  AND ANY  EXPRESS OR IMPLIED  WARRANTIES, INCLUDING,  BUT NOT
 * LIMITED  TO, THE  IMPLIED WARRANTIES  OF MERCHANTABILITY  AND FITNESS
 * FOR  A PARTICULAR  PURPOSE  ARE  DISCLAIMED. IN  NO  EVENT SHALL  THE
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE  LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY,  OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT  NOT LIMITED  TO, PROCUREMENT  OF SUBSTITUTE  GOODS OR  SERVICES;
 * LOSS  OF USE,  DATA, OR  PROFITS; OR  BUSINESS INTERRUPTION)  HOWEVER
 * CAUSED AND  ON ANY THEORY  OF LIABILITY, WHETHER IN  CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY  OUT  OF  THE USE  OF  THIS  SOFTWARE,  EVEN  IF ADVISED  OF  THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * Telegraph.java: This class packs the window and the animation configuration.
 * Please dispose the object if you don't add it to a queue.
 */

// package definition
package org.jtelegraph;

// needed imports
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.jtelegraph.audio.AudioCallback;
import org.pushingpixels.trident.Timeline;

/**
 * Packs the window and the animation configuration. Please dispose the object
 * if you don't add it to a queue.
 * 
 * @author Paulo Roberto Massa Cereda
 * @version 2.0
 * @since 2.0
 */
public class Telegraph {

	// the message title
	private final String title;

	// the message description
	private final String description;

	// the configuration
	private final TelegraphConfig config;

	// the actual window
	private TelegraphWindow window;

	// the timelines
	private Timeline timelineIntro;
	private Timeline timelineStay;
	private Timeline timelineAway;

	/**
	 * Constructor.
	 * 
	 * @param title
	 *            The telegraph title.
	 * @param description
	 *            The telegraph description.
	 */
	public Telegraph(final String title, final String description) {

		// set both title and description
		this.title = title;
		this.description = description;

		// create a default configuration
		config = new TelegraphConfig();

		// configure it
		configure();
	}

	/**
	 * Constructor with configuration options.
	 * 
	 * @param title
	 *            The telegraph title.
	 * @param description
	 *            The telegraph description.
	 * @param config
	 *            The configuration.
	 */
	public Telegraph(final String title, final String description,
			final TelegraphConfig config) {

		// set everything
		this.title = title;
		this.description = description;
		this.config = config;

		// and configure it
		configure();
	}

	public TelegraphConfig getConfig() {
		return config;
	}

	/**
	 * Configures the telegraph.
	 */
	private void configure() {

		// create a new telegraph window
		window = new TelegraphWindow(title, description, config);

		// set the window height and width
		config.setWindowHeight(window.getHeight());
		config.setWindowWidth(window.getWidth());

		// create the three timelines
		timelineIntro = new Timeline(window);
		timelineStay = new Timeline(window);
		timelineAway = new Timeline(window);

		// configure the intro animation, when the window enters
		timelineIntro.addPropertyToInterpolate("position",
				config.getInitialCoordinates(), config.getFinalCoordinates());

		// add the callback to the main timeline
		timelineIntro.addCallback(new AudioCallback(this, timelineStay));

		// if the window doesn't have a button
		if (!config.hasEnableButton()) {

			// if there's a stop on mouse over
			if (config.hasToStopOnMouseOver())
				// add a new listener
				window.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(final MouseEvent e) {
					}

					@Override
					public void mousePressed(final MouseEvent e) {
					}

					@Override
					public void mouseReleased(final MouseEvent e) {
					}

					@Override
					public void mouseEntered(final MouseEvent e) {

						// if the window is in position
						if (timelineIntro.isDone() && !timelineStay.isDone())
							// suspend animation
							timelineStay.suspend();
					}

					@Override
					public void mouseExited(final MouseEvent e) {

						// window is still in position
						if (timelineIntro.isDone() && !timelineStay.isDone())
							// resume animation
							timelineStay.resume();
					}
				});

			// configure the time the window should wait in the screen
			timelineStay.setDuration(config.getDuration());
			timelineStay.addCallback(new SimpleCallback(timelineAway));

		}

		// add duration
		timelineIntro.setDuration(config.getInDuration());

		// configure the end animation, when the window goes away
		timelineAway.addPropertyToInterpolate("position",
				config.getFinalCoordinates(), config.getInitialCoordinates());
		timelineAway.setDuration(config.getOutDuration());
		timelineAway.addCallback(new EndCallback(window));

		// set the last timeline
		window.setTimeline(timelineAway);

	}

	/**
	 * Plays the animation.
	 */
	protected void show() {
		timelineIntro.play();
	}

	/**
	 * Checks if the animation is still running.
	 * 
	 * @return A boolean which determines if the animation is still running.
	 */
	protected boolean isRunning() {
		if (window != null && window.isDiscarded())
			return false;
		// if every timeline is done
		if (timelineIntro.isDone() && timelineStay.isDone()
				&& timelineAway.isDone())
			// nothing is running, return false
			return false;

		// something is still running, return true
		return true;
	}

	/**
	 * Disposes the telegraph window. There's no need of calling this method,
	 * unless you don't add the telegraph to the queue.
	 */
	public void dispose() {

		// if there's still an object reference
		if (window != null)
			// dispose it
			window.dispose();
	}
}
