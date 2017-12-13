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
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.eclipse.jst.j2ee.model.IModelProviderEvent;
import org.eclipse.jst.j2ee.model.IModelProviderListener;

/**
 * @author  Kiril Mitov k.mitov@sap.com
 *
 */
public class SynchronousModelChangedListener implements IModelProviderListener {

	private final Semaphore s;
	private final int expectedEvents;
	private final Collection<IModelProviderEvent> receivedEvents;

	public SynchronousModelChangedListener(int expectedEvents) throws InterruptedException {
		this.expectedEvents = expectedEvents;
		this.s = new Semaphore(1);
		this.s.acquire();
		this.receivedEvents = Collections.synchronizedList(new LinkedList<IModelProviderEvent>());
	}

	public boolean waitForEvents() throws InterruptedException {
		return s.tryAcquire(5, TimeUnit.SECONDS);
	}

	public Collection<IModelProviderEvent> getReceivedEvents() {
		return receivedEvents;
	}

	public synchronized void modelsChanged(IModelProviderEvent event) {
		try {
			receivedEvents.add(event);
			if (receivedEvents.size() > expectedEvents)
				throw new IllegalStateException("The expected events were already reached <" + dumpEvent(event) + ">");
		} finally {
			if (expectedEvents == receivedEvents.size())
				s.release();
		}
	}

	private String dumpEvent(IModelProviderEvent event) {
		StringBuilder builder = new StringBuilder();
		builder.append("Event for project <" + event.getProject() + "> with changed resources: \r\n");
		for (Object resource : event.getChangedResources()) {
			builder.append(resource + "\r\n");
		}
		return builder.toString();
	}
}
