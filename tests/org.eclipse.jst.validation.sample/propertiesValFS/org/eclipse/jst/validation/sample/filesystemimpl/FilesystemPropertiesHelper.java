package org.eclipse.jst.validation.sample.filesystemimpl;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.jst.validation.sample.PModelEnum;
import org.eclipse.jst.validation.sample.filesystem.IFilesystemHelper;
import org.eclipse.jst.validation.sample.parser.APropertyFile;
import org.eclipse.jst.validation.sample.parser.PropertyLine;
import org.eclipse.wst.validation.internal.core.FileDelta;
import org.eclipse.wst.validation.internal.core.IFileDelta;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * This class is the IValidationContext implementation, used by the PropertiesValidator,
 * when the validator is run in the filesystem validation framework.
 */
public class FilesystemPropertiesHelper implements IFilesystemHelper {
	public FilesystemPropertiesHelper() {
		super();
	}

	/**
	 * @see com.ibm.etools.validation.core.IValidationContext#loadModel(String)
	 */
	public Object loadModel(String symbolicName) {
		return loadModel(symbolicName, null);
	}

	/**
	 * @see com.ibm.etools.validation.core.IValidationContext#loadModel(String, Object[])
	 */
	public Object loadModel(String symbolicName, Object[] parm) {
		if ((symbolicName == null) || (symbolicName.equals(""))) { //$NON-NLS-1$
			return null;
		}

		if (symbolicName.equals(PModelEnum.LINEINPUTREADER) && (parm != null) && (parm.length == 1) && (parm[0] instanceof String)) {
			return loadLineNumberReader((String) parm[0]);
		}
		else if (symbolicName.equals(PModelEnum.RELEASE_LINEINPUTREADER) && (parm != null) && (parm.length == 1) && (parm[0] instanceof LineNumberReader)) {
			return releaseLineNumberReader((LineNumberReader) parm[0]);
		}
		else if (symbolicName.equals(PModelEnum.ALL_PROPERTIES_FILES)) {
			return loadAllPropertiesFiles();
		}
		else if(symbolicName.equals(PModelEnum.FILTER)) {
			// Because this environment does not copy resources into a "bin" directory,
			// the input is already filtered.
			return parm;
		}
		else {
			return null;
		}
	}

	/**
	 * Given the file name of an existing .properties file, return
	 * the LineNumberReader that will read the file.
	 */
	protected Object loadLineNumberReader(String fileName) {
		File propertyFile = new File(fileName);
		if (!propertyFile.exists() || !propertyFile.isFile()) {
			return null;
		}

		FileReader input = null;
		try {
			input = new FileReader(propertyFile);
		}
		catch (FileNotFoundException e) {
			return null;
		}

		// Because we want to read in a line at a time from the file, convert the FileReader to a LineReader
		LineNumberReader lineInput = new LineNumberReader(input);
		return lineInput;
	}

	/**
	 * This method doesn't load anything; it releases the resources allocated
	 * by the loadLineNumberReader method. Its result will never be used, so always
	 * return null.
	 */
	protected Object releaseLineNumberReader(LineNumberReader reader) {
		if (reader == null) {
			return null;
		}

		try {
			reader.close();
		}
		catch (IOException e) {
		}

		return null;
	}

	/**
	 * When the validator is told to perform a full validation, this
	 * method returns an IFileDelta[], with one IFileDelta for every
	 * .properties file in the current directory and its subdirectories.
	 */
	protected Object loadAllPropertiesFiles() {
		String pwd = System.getProperty("user.dir"); //$NON-NLS-1$
		File pwdDir = new File(pwd);
		if (!pwdDir.exists()) {
			return null;
		}

		if (!pwdDir.isDirectory()) {
			return null;
		}

		Set tempSet = new HashSet();
		traverseDirectories(pwdDir, tempSet);
		
		IFileDelta[] result = new IFileDelta[tempSet.size()];
		Iterator iterator = tempSet.iterator();
		int count = 0;
		while(iterator.hasNext()) {
			File file = (File)iterator.next();
			result[count++] = new FileDelta(file.getAbsolutePath(), IFileDelta.CHANGED);
		}
		tempSet.clear();
		tempSet = null;
		iterator = null;
		return result;
	}

	/**
	 * Traverse the directory, looking for .properties files, and if a
	 * .properties file is found then add it to the Set.
	 */
	protected void traverseDirectories(File directoryToSearch, Set result) {
		if (directoryToSearch == null) {
			return;
		}

		if (directoryToSearch.isDirectory()) {
			// Traverse into this directory's children.
			String[] children = directoryToSearch.list();
			for (int i = 0; i < children.length; i++) {
				File child = new File(directoryToSearch, children[i]);
				traverseDirectories(child, result);
			}
		}
		else if(directoryToSearch.isFile()) {
			String fileName = directoryToSearch.getName();
			if((fileName != null) && fileName.endsWith("properties")) { //$NON-NLS-1$
				result.add(directoryToSearch);
			}
		}
	}
	
	/**
	 * Given an IMessage, return the file name of the .properties file that
	 * the message was reported against.
	 */
	public String getFileName(IMessage message) {
		Object object = message.getTargetObject();
		if(object instanceof PropertyLine) {
			PropertyLine line = (PropertyLine)object;
			APropertyFile pFile = line.getFile();
			return pFile.getQualifiedFileName(); // a ValidationPropertyFile returns the file name as its qualified name
		}
		else if(object instanceof APropertyFile) {
			APropertyFile pFile = (APropertyFile)object;
			return pFile.getQualifiedFileName(); // a ValidationPropertyFile returns the file name as its qualified name
		}
		return "";	//$NON-NLS-1$
	}

	public String[] getURIs() {
		// TODO Auto-generated method stub
		return null;
	}
}
