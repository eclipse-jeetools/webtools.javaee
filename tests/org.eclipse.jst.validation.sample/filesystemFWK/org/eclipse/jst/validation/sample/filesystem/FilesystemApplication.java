package org.eclipse.jst.validation.sample.filesystem;
/*
 * Licensed Material - Property of IBM 
 * (C) Copyright IBM Corp. 2002, 2003 - All Rights Reserved. 
 * US Government Users Restricted Rights - Use, duplication or disclosure 
 * restricted by GSA ADP Schedule Contract with IBM Corp. 
 *
 * DISCLAIMER OF WARRANTIES.
 * The following [enclosed] code is sample code created by IBM
 * Corporation. This sample code is not part of any standard or IBM
 * product and is provided to you solely for the purpose of assisting
 * you in the development of your applications.  The code is provided
 * "AS IS". IBM MAKES NO WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE, REGARDING THE FUNCTION OR PERFORMANCE OF
 * THIS CODE.  THIS CODE MAY CONTAIN ERRORS.  IBM shall not be liable
 * for any damages arising out of your use of the sample code, even
 * if it has been advised of the possibility of such damages.
 * 
 */


import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclispe.wst.validation.internal.core.IFileDelta;
import org.eclispe.wst.validation.internal.core.Message;
import org.eclispe.wst.validation.internal.core.ValidationException;
import org.eclispe.wst.validation.internal.core.ValidatorLauncher;

/**
 * This class is the representation of a file system based application
 * which needs to use the common validation APIs.
 */
public class FilesystemApplication {
	/**
	 * The argv[] parameter needs to be a fully-qualified list of file names 
	 * of files that should be validated. 
	 */
	public static void main(String argv[]) {
		IFileDelta[] files = FilesystemManager.getManager().getFileDeltas(argv);
		boolean fullBuild = ((files == null) || (files.length == 0));

		IReporter reporter = null;
		if (fullBuild) {
			reporter = new FullReporter();
		}
		else {
			reporter = new IncrementalReporter();
		}

		FilesystemLoader loaders[] = FilesystemManager.getManager().getLoaders(files);
		for (int i=0; i<loaders.length; i++) {
			FilesystemLoader loader = loaders[i];
			try {
				ValidatorLauncher.getLauncher().start(loader.getHelper(), loader.getValidator(), reporter);
				if (!fullBuild) {
					((IncrementalReporter)reporter).report();
				}
			}
			catch (ValidationException exc) {
				Message message = new Message("filesystem", IMessage.HIGH_SEVERITY, "VFFS0000", new String[]{loader.getValidator().getClass().getName()}); //$NON-NLS-1$  //$NON-NLS-2$
				reporter.displaySubtask(loader.getValidator(), message);
				
				if(exc.getAssociatedMessage() != null) {
					System.out.println(exc.getAssociatedMessage());
				}
				
				exc.printStackTrace();
				if(exc.getAssociatedException() != null) {
					exc.getAssociatedException().printStackTrace();
				}
				// continue with the next validation
			}
			catch (Exception exc) {
				Message message = new Message("filesystem", IMessage.HIGH_SEVERITY, "VFFS0000", new String[]{loader.getValidator().getClass().getName()}); //$NON-NLS-1$ //$NON-NLS-2$
				reporter.displaySubtask(loader.getValidator(), message);
				// continue with the next validation
				exc.printStackTrace();
			}
		}
	}
}
