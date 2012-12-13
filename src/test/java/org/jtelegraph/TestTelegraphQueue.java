package org.jtelegraph;

import org.junit.Test;

public class TestTelegraphQueue {

	@Test
	public void testDisplayTelegraph() throws Exception {
		final TelegraphQueue queue = new TelegraphQueue();
		final Telegraph t = new Telegraph("Test", "Hey! Look at my first test!");
		queue.add(t);
		Thread.sleep(5000);
	}

}
