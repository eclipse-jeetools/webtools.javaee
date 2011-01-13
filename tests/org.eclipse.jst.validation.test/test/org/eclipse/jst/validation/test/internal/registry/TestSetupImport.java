package org.eclipse.jst.validation.test.internal.registry;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.jst.validation.test.setup.IImportOperation;
import org.eclipse.wst.validation.internal.plugin.ValidationPlugin;

/**
 * This class holds the metadata needed to import an .ear or
 * a .jar file to set up a test case in batch mode.
 */
public class TestSetupImport {
	private String _fileName = null;
	private IImportOperation _importOperation = null;

	public TestSetupImport(IImportOperation op, String fileName) {
		_importOperation = op;
		_fileName = fileName;
	}

	public File getInputFile(String dir) {
		File file = new File(dir, getFileName());
		if(!file.exists()) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, "File " + file.getAbsolutePath() + " must exist and have read access."); //$NON-NLS-1$ //$NON-NLS-2$
			}
			return null;
		}
		
		return file;
	}
	
	public String getFileName() {
		return _fileName;
	}
	
	public IImportOperation getOperation() {
		return _importOperation;
	}
	
	/**
	 * Return true if the file was found and imported successfully.
	 */
	public boolean importFile(IProgressMonitor monitor, String dir) {
		//int executionMap = 0x0;
		boolean imported = true;
		try {
			File file = getInputFile(dir);
			//executionMap |= 0x1;
			try {
				IImportOperation op = getOperation();
				//executionMap |= 0x2;
				imported = op.run(monitor, file);
				//executionMap |= 0x4;
			}
			catch (InterruptedException exc) {
				//executionMap |= 0x8;
				imported = false;
				ValidationPlugin.getPlugin().handleException(exc);
			}
			catch (InvocationTargetException exc) {
				//executionMap |= 0x10;
				imported = false;
				ValidationPlugin.getPlugin().handleException(exc);
				ValidationPlugin.getPlugin().handleException(exc.getTargetException());
			}
			catch (Throwable exc) {
				//executionMap |= 0x20;
				imported = false;
				ValidationPlugin.getPlugin().handleException(exc);
			}
		}
		finally {
			if(!imported) {
				ValidationPlugin.getPlugin().logMessage(IStatus.ERROR, 
						"TestSetup for " + dir + " was unsuccessful."); //$NON-NLS-1$ //$NON-NLS-2$
			}
			
		}
		return imported;
	}
}
