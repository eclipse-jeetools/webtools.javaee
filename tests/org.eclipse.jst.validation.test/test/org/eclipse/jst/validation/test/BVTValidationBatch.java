package org.eclipse.jst.validation.test;

import java.util.logging.Level;

import org.eclipse.core.boot.IPlatformRunnable;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.validation.test.internal.util.BVTRunner;
import org.eclipse.jst.validation.test.internal.util.BVTValidationUtility;
/**
 */
public class BVTValidationBatch implements IPlatformRunnable {
	private String _dir = null; // The test cases (.ear, .jar) are identified through a relative directory, and this is the parent of that relative directory.
	private boolean _verbose = false;
	static Boolean _passed = null;
	
	boolean isVerbose() {
		return _verbose;
	}
	
	void setVerbose(boolean v) {
		_verbose = v;
	}
	
	String getDir() {
		return _dir;
	}
	
	void setDir(String dir) {
		_dir = dir;
	}
	
	private void parseUserSettings(Object args) {
		if(args == null) {
			// nothing to set
			return;
		}
		
		if(args instanceof String[]) {
			String[] userSettings = (String[])args;
			if(userSettings.length == 0) {
				return;
			}
			
			for(int i=0; i<userSettings.length; i++) {
				if(userSettings[i].equals("-dir")) { //$NON-NLS-1$
					setDir(userSettings[i+1]);
				}
				
				if(userSettings[i].equals("-trace")) { //$NON-NLS-1$
					setVerbose(true);
				}
			}

			// turn on framework validation logging
			if(isVerbose()) {
				BVTValidationPlugin.getPlugin().getMsgLogger().setLevel(Level.FINEST);
			}
		}
	}


	/**
	 * Parse the user's settings, import the test cases, and run the test cases.
	 */
	public Object run(Object args) throws BVTValidationException {
		parseUserSettings(args);

		IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) {		
				try {
					monitor.subTask("Workspace is located at: " + BVTValidationPlugin.getPlugin().getStateLocation().toOSString()); //$NON-NLS-1$
			
					ConsoleBuffer buffer = new ConsoleBuffer();
					BVTRunner.singleton().setupTests(buffer, isVerbose());
					
					int numTests = BVTValidationUtility.numValidatorTests(monitor, ResourcesPlugin.getWorkspace().getRoot().getProjects());
					int numPassed = BVTRunner.singleton().test(buffer, ResourcesPlugin.getWorkspace().getRoot().getProjects());
					_passed = ((numPassed == numTests) ? Boolean.TRUE : Boolean.FALSE);
				}
				catch(BVTValidationException exc) {
					Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
					if(logger.isLoggingLevel(Level.SEVERE)) {
						logger.write(Level.SEVERE, exc.getMessage());
						logger.write(Level.SEVERE, exc);
						if(exc.getTargetException() != null) {
							logger.write(Level.SEVERE, exc.getTargetException());
						}
					}
				}
			}
		};
		
		try {
			ConsoleProgressMonitor monitor = new ConsoleProgressMonitor();
			ResourcesPlugin.getWorkspace().run(runnable, monitor);
		}
		catch(CoreException exc) {
			throw new BVTValidationException(exc);
		}
		return _passed;
	}
	
}
