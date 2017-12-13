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

import org.eclipse.wst.validation.internal.provisional.core.IValidator;

/**
 * This class associates a Validator with a Helper and the file extensions that
 * the validator runs on.
 */
public class FilesystemLoader {
	private IFilesystemHelper _helper = null;
	private IValidator _validator = null;
	private String[] _fileExtensions = null;
	
	public FilesystemLoader(String[] fileExtensions, IFilesystemHelper helper, IValidator validator) {
		super();

		_fileExtensions = fileExtensions;
		_helper = helper;
		_validator = validator;
	}
	
	/**
	 * Return the helper that the validator needs to access information 
	 * in the filesystem validation framework.
	 */
	public IFilesystemHelper getHelper() {
		return _helper;
	}

	/**
	 * Return the validator that checks the rules.
	 */
	public IValidator getValidator() {
		return _validator;
	}
	
	/**
	 * Return a list of file name extensions that this validator validates.
	 */
	public String[] getFileExtensions() {
		return _fileExtensions;
	}
}
