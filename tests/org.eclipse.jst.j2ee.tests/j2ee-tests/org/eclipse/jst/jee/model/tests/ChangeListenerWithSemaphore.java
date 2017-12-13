/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.tests;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;

/**
 * This change listener will release a semaphore after the resource changed
 * method is called. For every resourceChanged call one release will be called.
 * 
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public final class ChangeListenerWithSemaphore implements IResourceChangeListener {
	private final Semaphore s;
	private final Collection<IResourceDelta> deltas;
	private final int expectedEvents;
	private final List<IResourceChangeEvent> receivedEvents;

	public ChangeListenerWithSemaphore(int expectedEvents) throws InterruptedException {
		this.expectedEvents = expectedEvents;
		this.s = new Semaphore(1);
		this.s.acquire();
		this.deltas = Collections.synchronizedList(new LinkedList<IResourceDelta>());
		this.receivedEvents = Collections.synchronizedList(new LinkedList<IResourceChangeEvent>());
	}

	public synchronized void resourceChanged(IResourceChangeEvent event) {
		receivedEvents.add(event);
		if (receivedEvents.size() > expectedEvents)
			throw new IllegalStateException("The expected events were already reached");
		try {
			deltas.add(event.getDelta());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (expectedEvents == receivedEvents.size())
				s.release();
		}
	}

	public boolean waitForEvents() throws InterruptedException {
		return s.tryAcquire(5, TimeUnit.SECONDS);
	}

	public synchronized Collection<IResourceDelta> getDeltas() {
		return deltas;
	}

	public synchronized int getEvents() {
		return receivedEvents.size();
	}

	public List<IResourceChangeEvent> getReceivedEvents() {
		return receivedEvents;
	}
}
