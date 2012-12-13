package org.jtelegraph;

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
		final Telegraph t2 = new Telegraph("Test2",
				"Hey! Look at my second test!", c);
		queue.add(t);
		queue.add(t2);
		queue.join();
	}
}
