package org.jtelegraph;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.jtelegraph.icons.BatchIconProvider;
import org.junit.Test;

public class TestTelegraphQueue {

	@Test
	public void testDisplayTelegraph() throws Exception {
		final TelegraphQueue queue = new TelegraphQueue();
		final TelegraphConfig c = new TelegraphConfig();
		c.enableButton(true);
		c.setIconProvider(BatchIconProvider.ADDRESS_BOOK);
		// c.setStopOnMouseOver(true);
		final Telegraph t = new Telegraph("Test",
				"Hey! Look at my first test!", c);
		t.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(final MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(final MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(final MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(final MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(final MouseEvent e) {
				System.out.println("Hello there !");
			}
		});
		final Telegraph t2 = new Telegraph("Test2",
				"Hey! Look at my second test!", c);
		queue.add(t);
		queue.add(t2);
		queue.join();
	}
}
