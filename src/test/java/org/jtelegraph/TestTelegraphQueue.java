package org.jtelegraph;

import org.junit.Test;

public class TestTelegraphQueue {

	@Test
	public void testDisplayTelegraph() throws Exception {
		final TelegraphQueue queue = new TelegraphQueue();
		final TelegraphConfig c = new TelegraphConfig();
		c.enableButton(true);
		// c.setStopOnMouseOver(true);
		final Telegraph t = new Telegraph("Test",
				"Hey! Look at my first test!", c);
		queue.add(t);
		queue.join();
	}

}
