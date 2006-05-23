/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: DebugModeHelper.java,v $
 *  $Revision: 1.11 $  $Date: 2006/05/23 15:43:03 $ 
 */
package org.eclipse.jem.internal.proxy.remote;


import java.lang.reflect.*;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import org.eclipse.jem.internal.proxy.core.ProxyPlugin;
/**
 * This is a helper for debug mode. It allows access to
 * the workbench, if available, or AWT, if available, for
 * the debug prompts. It uses reflection so that nothing is
 * forced to be loaded or pre-reqd. That way it can run headless.
 */

class DebugModeHelper {
	boolean awt = false;
	boolean console = false;
	boolean setup = false;
	java.io.InputStreamReader inReader = null;
	
	// Workbench reflections
	Object display = null;	
	Constructor cMB = null;
	Field fPrimaryModel = null;
	Method fasync = null;
	Method fsync = null;	
	Method fSetText = null;
	Method fSetMessage = null;
	Method fOpen = null;
	Method fGetWorkbench = null;
	Class cSWT = null;
		
	
	// AWT Reflections
	Class cMessageDialog = null;
	Method fDoit = null;
	
	protected void setupType() {
		if (setup)
			return;
			
		setup = true;
		
		// See if use system console instead. If false or not set, then try to query usage.	 		
	 	console = "true".equalsIgnoreCase(Platform.getDebugOption(ProxyPlugin.getPlugin().getBundle().getSymbolicName()+ProxyRemoteUtil.IO_CONSOLE)); //$NON-NLS-1$
	 	if (console)
	 		return;
		
		try {		 		
		 	// See if PlatformUI plugin available.
		 	Bundle uiBundle = Platform.getBundle("org.eclipse.ui"); //$NON-NLS-1$
		 	if (uiBundle == null) {
		 		setupAWT();	// UI not available, try through AWT.
		 		return;
		 	}
	
	 		// Setup Eclipse
		 	Class cPlatformUI = uiBundle.loadClass("org.eclipse.ui.PlatformUI"); //$NON-NLS-1$
		 	Method isWBRunning = cPlatformUI.getMethod("isWorkbenchRunning", null); //$NON-NLS-1$
		 	if (!((Boolean) isWBRunning.invoke(null, null)).booleanValue()) {
		 		setupAWT();	// UI not available, try through AWT.
		 		return;
		 	}
		 	
		 	fGetWorkbench = cPlatformUI.getMethod("getWorkbench", null); //$NON-NLS-1$
		 	Object w = fGetWorkbench.invoke(null, null);

			if (w != null) {
	 			Class cDisplay = uiBundle.loadClass("org.eclipse.swt.widgets.Display"); //$NON-NLS-1$
	 			Method fGetCurrent = cDisplay.getMethod("getCurrent", null); //$NON-NLS-1$
	 			Method fGetDefault = cDisplay.getMethod("getDefault", null);	 			 //$NON-NLS-1$
	 			fasync = cDisplay.getMethod("asyncExec", new Class[] {Runnable.class}); //$NON-NLS-1$
	 			fsync = cDisplay.getMethod("syncExec", new Class[] {Runnable.class}); //$NON-NLS-1$
	 			
	 			// Get the display
	 			display = fGetCurrent.invoke(null, null);
	 			if (display == null)
	 				display = fGetDefault.invoke(null, null);
			}
			
			if (display != null) { 	
	 			Class cShell = uiBundle.loadClass("org.eclipse.swt.widgets.Shell"); //$NON-NLS-1$
	 			Class cMessageBox = uiBundle.loadClass("org.eclipse.swt.widgets.MessageBox"); //$NON-NLS-1$
	 			cMB = cMessageBox.getConstructor(new Class[] {cShell, Integer.TYPE});

				fSetText = cMessageBox.getMethod("setText", new Class[] {String.class}); //$NON-NLS-1$
				fSetMessage = cMessageBox.getMethod("setMessage", new Class[] {String.class});				 //$NON-NLS-1$
				fOpen = cMessageBox.getMethod("open", null);		 		 //$NON-NLS-1$				
		 			
	 			cSWT = uiBundle.loadClass("org.eclipse.swt.SWT"); //$NON-NLS-1$
	 			fPrimaryModel = cSWT.getField("PRIMARY_MODAL"); //$NON-NLS-1$
		 	} else {
		 		setupAWT();	// UI not available, try through AWT.
		 	}
		} catch (NoSuchMethodException e) {
		} catch (ClassNotFoundException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchFieldException e) {
		}
		
		return;		 			 		
		 			
	}	

	protected void setupAWT() {
	 		
	 	// See if AWT is available.
	 	try {
		 	cMessageDialog = Class.forName("org.eclipse.jem.internal.proxy.remote.MessageDialog"); //$NON-NLS-1$
		 	fDoit = cMessageDialog.getMethod("doit", new Class[] {String.class, String.class, String.class, String.class}); //$NON-NLS-1$
		 	awt = true;
		 	return;
	 	} catch (ClassNotFoundException e) {
	 	} catch (NoSuchMethodException e) {
	 	}
 		console = true;	// No AWT either, i.e. true headless environment
	} 	
	
	public boolean debugMode(final String name) {
	 	boolean debugMode = "true".equalsIgnoreCase(Platform.getDebugOption(ProxyPlugin.getPlugin().getBundle().getSymbolicName()+ProxyRemoteUtil.DEBUG_VM)); //$NON-NLS-1$
	 	if (!debugMode)
	 		return debugMode;
	 		
	 	setupType();

		// See if use system console instead. If false or not set, then try to query usage.	 		
	 	if (console)
	 		return debugModeConsole(name);
	 		
	 	if (awt)
	 		return debugModeAWT(name);
		
		try {		 		
 			final Field fYes = cSWT.getField("YES"); //$NON-NLS-1$
 			Field fNo = cSWT.getField("NO"); //$NON-NLS-1$
 			Field fIcon = cSWT.getField("ICON_QUESTION"); //$NON-NLS-1$
	 			
 			final int style = fYes.getInt(null) | fNo.getInt(null) | fIcon.getInt(null) | fPrimaryModel.getInt(null);	 			
 			final boolean[] db = new boolean[1];

			fsync.invoke(display, new Object[] { new Runnable() {
				/**
				 * @see java.lang.Runnable#run()
				 */
				public void run() {
					try {
						Object shell = getActiveShell();
						Object mb = cMB.newInstance(new Object[] {shell, new Integer(style)});					
						fSetText.invoke(mb, new Object[] {"Debug RemoteVM?"}); //$NON-NLS-1$
						fSetMessage.invoke(mb, new Object[] {"Do you want to start the remote vm ("+name+") in debug mode?"});	// Not NLS'd because this is for internal developers only //$NON-NLS-1$ //$NON-NLS-2$
							
						db[0] = fYes.get(null).equals(fOpen.invoke(mb, null));
					} catch (InstantiationException e) {
					} catch (IllegalAccessException e) {
					} catch (InvocationTargetException e) {
					}
				}
			}});
		 	return db[0];
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchFieldException e) {
		}
		
		return debugModeAWT(name);
 	}
 	

	/*
	 * This must be called from within the display thread.
	 */
	protected Object getActiveShell() {
	 	Object shell = null;		
		try {
			Object w = fGetWorkbench.invoke(null, null);
			Object ww = null;
			if (w != null) {
			 	Class cWorkbench = w.getClass();
			 	Method getWorkbenchWindow = cWorkbench.getMethod("getActiveWorkbenchWindow", null); //$NON-NLS-1$
				ww = getWorkbenchWindow.invoke(w, null);
			}
			if (ww != null) {
				Class cWorkbenchWindow = ww.getClass();
				Method getShell = cWorkbenchWindow.getMethod("getShell", null); //$NON-NLS-1$
				shell = getShell.invoke(ww, null);
			}
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchMethodException e) {
		}
	 	
	 	return shell;
	}	
		 	
	protected boolean debugModeAWT(String name) {
	 		
	 	// See if AWT is available.
	 	try {
		 	Integer r = (Integer) fDoit.invoke(null, new Object[] {"Debug RemoteVM?", "Do you want to start the remote vm ("+name+") in debug mode?", "Yes", "No"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		 	return r.intValue() == 1;	// i.e. button1 or "yes" was hit.
	 	} catch (IllegalAccessException e) {
	 	} catch (InvocationTargetException e) {
	 	}
	 	
 		return false;	// No AWT either, i.e. headless environment, shouldn't of gotton here.
	} 	
	
	protected boolean debugModeConsole(String name) {
	 		
	 	// Debug through the console
 		inReader = new java.io.InputStreamReader(System.in);
 		System.out.println("Do you want to start the remote vm ("+name+") in debug mode? (Enter Y or N):"); //$NON-NLS-1$ //$NON-NLS-2$
 		char r = ' ';
 		try {
 			r = Character.toLowerCase((char) inReader.read());
 			// Need to flush the input reader now.
 			while (inReader.ready())
 				inReader.read();
		 	return r == 'y';	// i.e. "y" was entered.	 			
 		} catch (java.io.IOException e) {
 		}
	 	
 		return false;	// Error reading, so no debugging.
	} 
 	
 	/**
 	 * Return true if continue debugging.
 	 */
	public boolean promptPort(final int dport) {
 		if (awt)
 			return promptPortAWT(dport);
 		else if (console)
 			return promptPortConsole(dport);
 			
 		try {	
			final Field fOK = cSWT.getField("OK"); //$NON-NLS-1$
			Field fCancel = cSWT.getField("CANCEL"); //$NON-NLS-1$
			Field fIcon = cSWT.getField("ICON_WORKING"); //$NON-NLS-1$
	 			
			final int style = fOK.getInt(null) | fCancel.getInt(null) | fIcon.getInt(null) | fPrimaryModel.getInt(null);

			final boolean[] ok = new boolean[1];
			fsync.invoke(display, new Object[] { new Runnable() {	
				/**
				 * @see java.lang.Runnable#run()
				 */
				public void run() {
					try {
						Object shell = getActiveShell();
						Object mb = cMB.newInstance(new Object[] {shell, new Integer(style)});					
						fSetText.invoke(mb, new Object[] {"Connect Debugger to RemoteVM!"}); //$NON-NLS-1$
						fSetMessage.invoke(mb, new Object[] {"You must now attach the debugger to port number "+dport+". Press OK when the debugger is attached. The program will not continue properly without the debugger being attached. Press Cancel if you could not connect the debugger. This will still cause an error, but it will clean up better."});	// Not NLS'd because this is for internal developers only //$NON-NLS-1$ //$NON-NLS-2$
						ok[0] = fOK.get(null).equals(fOpen.invoke(mb, null));
					} catch (InstantiationException e) {
					} catch (IllegalAccessException e) {
					} catch (InvocationTargetException e) {
					}
				}
			}});			
			return ok[0];
 		} catch (NoSuchFieldException e) {
 		} catch (InvocationTargetException e) {
 		} catch (IllegalAccessException e) {
 		}
 		return false;
	}	 	
 	
	protected boolean promptPortAWT(int dport) {
		try {
		 	Integer r = (Integer) fDoit.invoke(null, new Object[] {"Connect Debugger to RemoteVM!", "You must now attach the debugger to port number "+dport+". Press OK when the debugger is attached. The program will not continue properly without the debugger being attached. Press Cancel if you could not connect the debugger. This will still cause an error, but it will clean up better.", "OK", "Cancel"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		 	return r.intValue() == 1;	// i.e. button1 or "OK" was hit.
		} catch (InvocationTargetException e) {
		} catch (IllegalAccessException e) {
		}
 		
		return false;
	}
		 	
 	protected boolean promptPortConsole(int dport) {
		System.out.println("You must now attach the debugger to port number "+dport+". Enter 'y' when the debugger is attached. The program will not continue properly without the debugger being attached. Press 'n' if you could not connect the debugger. This will still cause an error, but it will clean up better. Enter 'y' or 'n':"); //$NON-NLS-1$ //$NON-NLS-2$
		char r = ' ';
		try {
			r = Character.toLowerCase((char) inReader.read());
 			while (inReader.ready())
 				inReader.read();
			return r == 'y';	// i.e. "y" was entered, go on
		} catch (java.io.IOException e) {
		}
 		
		return false;
	}
	
	/**
	 * Display an error msg. There will only be an OK button.
	 * This will always display, debug mode won't be looked at.
	 * This is for errors. This will be displayed async, so this
	 * may return before the msg has been displayed and answered,
	 * but since the only response is OK, it doesn't really matter.
	 */
	public void displayErrorMessage(final String title, final String msg) {
		setupType();
		if (console)
			displayErrorMessageConsole(title, msg);
		else if (awt)
			displayErrorMessageAWT(title, msg);
		else {
			try { // This needs to be done in display thread.
				Field fOK = cSWT.getField("OK"); //$NON-NLS-1$
				Field fIcon = cSWT.getField("ICON_ERROR"); //$NON-NLS-1$

				final int style = fOK.getInt(null) | fIcon.getInt(null) | fPrimaryModel.getInt(null);
				fasync.invoke(display, new Object[] { new Runnable() {
						/**
						 * @see java.lang.Runnable#run()
						 */
						public void run() { try { Object shell = getActiveShell();
								Object mb = cMB.newInstance(new Object[] { shell, new Integer(style)});

								fSetText.invoke(mb, new Object[] { title });
								fSetMessage.invoke(mb, new Object[] { msg });
								fOpen.invoke(mb, null);
							} catch (InstantiationException e) {
							} catch (IllegalAccessException e) {
							} catch (InvocationTargetException e) {
							}
						}
					}
				});
			} catch (NoSuchFieldException e) {
			} catch (InvocationTargetException e) {
			} catch (IllegalAccessException e) {
			}
		}
	}	
	
 	protected void displayErrorMessageConsole(String title, String msg) {
		System.out.println("Error: " + title); //$NON-NLS-1$
		System.out.println("       " + msg); //$NON-NLS-1$
	}
	
	protected void displayErrorMessageAWT(String title, String msg) {
		try {
		 	fDoit.invoke(null, new Object[] {title, msg, ProxyRemoteMessages.OK_7, null}); 
		} catch (InvocationTargetException e) {
		} catch (IllegalAccessException e) {
		}
	}		
}
