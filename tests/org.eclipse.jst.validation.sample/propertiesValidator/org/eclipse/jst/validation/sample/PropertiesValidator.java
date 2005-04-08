package org.eclipse.jst.validation.sample;
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

import java.io.LineNumberReader;
import java.util.logging.Level;

import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.validation.sample.parser.IValidationConstants;
import org.eclipse.wst.validation.internal.provisional.core.IFileDelta;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidationContext;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;
import org.eclipse.wst.validation.internal.provisional.core.ValidationException;
import org.eclispe.wst.validation.internal.core.Message;

/**
 * This class checks that the .properties files has no syntax or
 * or problems. This validator can run in any validation framework
 * implementation.
 */
public class PropertiesValidator implements IValidator {

	/*
	 * @see IValidator#cleanup(IReporter)
	 */
	public void cleanup(IReporter reporter) {
		// This validator doesn't cache anything so it doesn't need to clean anything up.
	}

	/*
	 * @see IValidator#validate(IValidationContext, IReporter, IFileDelta[])
	 */
	public void validate(IValidationContext helper, IReporter reporter, IFileDelta[] changedFiles) throws ValidationException {
		if((changedFiles == null) || (changedFiles.length == 0)) {
			changedFiles = (IFileDelta[])helper.loadModel(PModelEnum.ALL_PROPERTIES_FILES);
		}
		else {
			// Filter out the files that are in the "bin" directory.
			changedFiles = (IFileDelta[])helper.loadModel(PModelEnum.FILTER, new Object[]{changedFiles});
		}
		if(changedFiles == null) {
			// Problem loading the files. 
			Logger logger = (Logger)helper.loadModel(PModelEnum.MSGLOGGER);
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE,"changedFiles == null; terminating validation"); //$NON-NLS-1$
				return;
			}
		}
		
		for (int i = 0; i < changedFiles.length; i++) {
			// Load the reader for the file
			LineNumberReader reader = null;
			try {
				reader = (LineNumberReader)helper.loadModel(PModelEnum.LINEINPUTREADER, new Object[]{changedFiles[i].getFileName()});
				if(reader == null) {
					// Either: 
					// 1. The file doesn't exist or
					// 2. The file isn't a .properties file or
					// 3. The file can't be read
					IMessage message = new Message(IValidationConstants.BUNDLENAME, IMessage.NORMAL_SEVERITY, IValidationConstants.ABCD0090, new String[]{changedFiles[i].getFileName()});
					reporter.addMessage(this, message);
					continue;
				}
	
				// If we can get a reader then we can get a PropertyFile
				ValidatorPropertyFile propFile = new ValidatorPropertyFile(reader, changedFiles[i].getFileName(), reporter, this);
				propFile.printSyntaxWarnings();
				propFile.printDuplicateMessageId();
				propFile.printDuplicateMessagePrefix();
			}
			finally {
				// Don't catch Throwable, MessageLimitException, OperationCanceledException, or ValidationException.
				// Let them travel up to the framework and the framework will log or cleanup as appropriate.
				if(reader != null) {
					// Release the reader for the file.
					// Because different validation frameworks may allocate LineNumberReaders
					// differently, don't close the reader in the validator. Instead, pass the
					// reader back to the helper, and regardless of whether the reader should 
					// be closed or reused, the helper knows the right thing to do for its
					// framework environment.
					helper.loadModel(PModelEnum.RELEASE_LINEINPUTREADER, new Object[]{reader});
				}
			}
		}
	}
	
}
