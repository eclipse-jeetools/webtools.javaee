package org.eclipse.jst.validation.test.setup;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * All validation FVT tests must provide an IImportOperation implementation
 * to import the input file.
 */
public interface IImportOperation {
	/**
	 * Runs this operation without forcing a UI dependency. Return true if the file
	 * was found and imported successfully.
	 *
	 * @param monitor the progress monitor to use to display progress and receive
	 *   requests for cancelation
	 * @param inputFile the file which needs to be imported into the workbench
	 * @exception InvocationTargetException if the run method must propagate a checked exception,
	 * 	it should wrap it inside an <code>InvocationTargetException</code>; runtime exceptions are automatically
	 *  wrapped in an <code>InvocationTargetException</code> by the calling context
	 * @exception InterruptedException if the operation detects a request to cancel, 
	 *  using <code>IProgressMonitor.isCanceled()</code>, it should exit by throwing 
	 *  <code>InterruptedException</code>
	 *
	 * @see IRunnableWithProgress
	 */
	public boolean run(IProgressMonitor monitor, File inputFile) throws InvocationTargetException, InterruptedException;
}
