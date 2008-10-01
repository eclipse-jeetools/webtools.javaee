package org.eclipse.jst.jee.model.tests;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IElementChangedListener;

public class JdtChangeListenerWithSemaphore implements IElementChangedListener {
	private final Semaphore s;
	private final int expectedEvents;
	private final List<ElementChangedEvent> receivedEvents;

	public JdtChangeListenerWithSemaphore(int expectedEvents) throws InterruptedException {
		this.expectedEvents = expectedEvents;
		this.s = new Semaphore(1);
		this.s.acquire();
		this.receivedEvents = Collections.synchronizedList(new LinkedList<ElementChangedEvent>());
	}

	public boolean waitForEvents() throws InterruptedException {
		return s.tryAcquire(5, TimeUnit.SECONDS);
	}

	public synchronized int getEvents() {
		return receivedEvents.size();
	}

	public void elementChanged(ElementChangedEvent event) {
		receivedEvents.add(event);
		if (receivedEvents.size() > expectedEvents)
			throw new IllegalStateException("The expected events were already reached");
		if (expectedEvents == receivedEvents.size())
			s.release();
	}
}
