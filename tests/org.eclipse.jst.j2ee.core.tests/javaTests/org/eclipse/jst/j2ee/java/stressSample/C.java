package org.eclipse.jst.j2ee.java.stressSample;

/**
 * Test class C - mostly harmless.  Some inner classes.
 */
public class C {
	public class MyRunnable implements Runnable {
		public void run() {
			return;
		}
	}
	public void makeRunnable() {
		Runnable r = new Runnable() {
			public void run() {
				return;
			}
		};
		r.run();
	}
}
