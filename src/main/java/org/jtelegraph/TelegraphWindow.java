/*
 *   JTelegraph -- a Java message notification library
 *   Copyright (c) 2012, Paulo Roberto Massa Cereda
 *   All rights reserved.
 *
 *   Redistribution and use in source and binary forms, with or without
 *   modification, are permitted provided that the following conditions
 *   are met:
 *
 *   1. Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 *   2. Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 *   3. Neither the name of the project's author nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 *   THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *   "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *   LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 *   FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 *   COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 *   INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 *   BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *   LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *   CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 *   LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 *   WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *   POSSIBILITY OF SUCH DAMAGE.
 */
// package definition
package org.jtelegraph;

// needed imports
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.MatteBorder;

import net.miginfocom.swing.MigLayout;

import org.pushingpixels.trident.Timeline;

/**
 * Implements the telegraph window.
 * 
 * @author Paulo Roberto Massa Cereda
 * @version 2.0
 * @since 2.0
 */
@SuppressWarnings("serial")
public class TelegraphWindow extends JWindow {

	// the configuration
	private final TelegraphConfig config;
	// the title
	String title;
	// the description
	String description;
	private Timeline timeline;

	public void setTimeline(final Timeline timeline) {
		this.timeline = timeline;
	}

	private boolean discarded = false;

	protected void discard() {
		discarded = true;
	}

	public boolean isDiscarded() {
		return discarded;
	}

	/**
	 * Constructor.
	 * 
	 * @param theTitle
	 *            The telegraph title.
	 * @param theDescription
	 *            The telegraph description.
	 * @param theConfig
	 *            The configuration.
	 */
	public TelegraphWindow(final String theTitle, final String theDescription,
			final TelegraphConfig theConfig) {

		// instantiate superclass
		super();

		// set the attributes
		title = theTitle;
		description = theDescription;
		config = theConfig;

		// create a new border
		getRootPane().setBorder(
				new MatteBorder(config.getBorderThickness(), config
						.getBorderThickness(), config.getBorderThickness(),
						config.getBorderThickness(), config.getBorderColor()));

		// set the layout
		setLayout(new MigLayout());

		// set the background color
		getRootPane().setBackground(config.getBackgroundColor());

		// if there's a background image
		if (config.getBackgroundImage() != null) {

			// create a label with that image
			final JLabel labelBackground = new JLabel(
					config.getBackgroundImage());

			// set the bounds
			labelBackground.setBounds(0, 0, config.getBackgroundImage()
					.getIconWidth(), config.getBackgroundImage()
					.getIconHeight());

			// add it
			getLayeredPane().add(labelBackground,
					new Integer(Integer.MIN_VALUE));
		}

		// create a new panel
		final JPanel contentPanel = new JPanel();
		contentPanel.setOpaque(false);

		// set the new layout
		contentPanel.setLayout(new MigLayout("ins dialog, gapx 15, hidemode 3",
				"15[][grow]15", "15[][grow][]15"));

		// create a new icon
		final JLabel icon = new JLabel(config.getIcon());
		contentPanel.add(icon, "cell 0 0 0 2, align center");

		// create the telegraph title
		final String strTitle = String.format(
				"<html><div style=\"width:%dpx;\">%s</div><html>", 200, title);
		final JLabel lblTitle = new JLabel("<html>" + strTitle + "</html>");

		// if there's no font
		if (config.getTitleFont() == null)
			// set default
			lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 14f));
		else
			// set the one from config
			lblTitle.setFont(config.getTitleFont());

		// set the font color
		lblTitle.setForeground(config.getTitleColor());

		// create the telegraph description
		final String strDescription = String.format(
				"<html><div style=\"width:%dpx;\">%s</div><html>", 200,
				description);
		final JLabel lblDescription = new JLabel(strDescription);

		// if there's font
		if (config.getDescriptionFont() != null)
			// set it
			lblDescription.setFont(config.getDescriptionFont());

		// set the description color
		lblDescription.setForeground(config.getDescriptionColor());

		// add both title and description
		contentPanel.add(lblTitle, "cell 1 0, aligny center");
		contentPanel.add(lblDescription,
				"cell 1 1, aligny center, growy, width 260!");

		// if the button is enabled
		if (config.hasEnableButton()) {

			// create a new button
			final JButton button = new JButton(config.getButtonCaption());

			// if there's an icon
			if (config.getButtonIcon() != null)
				// add it to the button
				button.setIcon(config.getButtonIcon());

			// add listener
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(final ActionEvent e) {

					// disable the button
					button.setEnabled(false);

					if (config.getButtonAction() != null)
						config.getButtonAction().doSomething();

					// play animation

					timeline.play();
					TelegraphWindow.this.discard();
				}
			});

			// add the button to the panel
			contentPanel.add(button, "cell 1 2, align right");
		}

		// set content to the window
		setContentPane(contentPanel);

		// set the windows always on top
		setAlwaysOnTop(true);

		// pack everything
		pack();

		// put the window away
		setBounds(-getWidth(), -getHeight(), getWidth(), getHeight());

		// apply mouselistener from config
		if (config.getGlobalListener() != null)
			addMouseListener(config.getGlobalListener());
	}

	/**
	 * Sets position on screen.
	 * 
	 * @param p
	 *            The new position.
	 */
	public void setPosition(final Point p) {

		// if not visible
		if (!isVisible())
			// show window
			setVisible(true);

		// set new location
		setBounds(p.x, p.y, getWidth(), getHeight());
	}
}