/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile$
 *  $Revision$  $Date$ 
 */
package org.eclipse.jem.internal.proxy.common;

import java.util.LinkedList;

/**
 * A generic event queue. It runs on its own thread and will execute any queued up runnables.
 * <p>
 * When a runnable is added to the queue, the thread will be notified. If the thread is free it will
 * run the runnable. If it is not free, the runnable will be added to the queue. The thread will process
 * all intervening runnables and then execute the runnable in sequence with all of the rest.
 * <p>
 * This is used when you can't use the system widget (e.g. AWT event queue or SWT asyncExec) or when the
 * runnable must be executed on a non-ui thread.
 * <p>
 * <b>Note:</b> It is required that {@link #close()} be called so that resources are cleared up.
 * 
 * @since 1.1.0.1
 */
public class GenericEventQueue {
	
	private LinkedList queue = new LinkedList();

	private class EventThread extends Thread {
		private boolean run = true;
		
		public EventThread(String title) {
			super(title);
		}
		
		public void close() {
			synchronized (queue) {
				run = false;
				queue.clear();
				queue.notifyAll();
			}
		}
		
		public void postEvent(Runnable run) {
			if (!this.isAlive()) {
				throw new IllegalStateException("Event queue thread is not executing.");
			}
			synchronized (queue) {
				if (run != null)
					queue.addLast(run);
				queue.notifyAll();
			}
		}
		
		public void run() {
			while (true) {
				Runnable runnable;
				synchronized (queue) {
					while (run && queue.isEmpty()) {
						try {
							queue.wait();
						} catch (InterruptedException e) {
						}
					}
					if (!run)
						return;	// Done.
					else {
						runnable = (Runnable) queue.removeFirst();
					}
				}

				try {
					runnable.run();
				} catch (RuntimeException e) {
					e.printStackTrace();
				} finally {
					runnable = null;	// Set this so that we don't hold onto resources in the runnable while waiting for next quy.
				}
			}
		}
	}
	
	private EventThread eventThread;
	
	/**
	 * Construct with a title. (This title will be the title on the Thread for the queue).
	 * The thread will have the same priority as the current thread. 
	 * @param queueTitle
	 * 
	 * @since 1.1.0.1
	 */
	public GenericEventQueue(String queueTitle) {
		this(queueTitle, Thread.currentThread().getPriority());
	}
	
	/**
	 * Construct with a title. (This title will be the title on the Thread for the queue).
	 * And a priority for the event queue's processing thread.
	 * @param queueTitle
	 * @param priority priority to use for event queue's thread.
	 * 
	 * @since 1.1.0.1
	 */
	public GenericEventQueue(String queueTitle, int priority) {
		eventThread = new EventThread(queueTitle);
		eventThread.setPriority(priority);
		synchronized(queue) {
			eventThread.start();
			try {
				queue.wait(100);	// Wait for thread to start.
			} catch (InterruptedException e) {
			}	
		}
	}
	
	/**
	 * Close the event queue. This will clear out any pending events. They will not
	 * be executed. 
	 * 
	 * 
	 * @since 1.1.0.1
	 */
	public void close() {
		eventThread.close();
	}
	
	/**
	 * Post the event.
	 * @param runnable runnable to execute, or <code>null</code> to kick-start the thread if for some reason it may be waiting and it shouldn't be.
	 * 
	 * @throws IllegalStateException thrown if the event queue thread has already been closed, or ended for some severe error.
	 * @since 1.1.0.1
	 */
	public void postEvent(Runnable runnable) {
		eventThread.postEvent(runnable);
	}
	
}
