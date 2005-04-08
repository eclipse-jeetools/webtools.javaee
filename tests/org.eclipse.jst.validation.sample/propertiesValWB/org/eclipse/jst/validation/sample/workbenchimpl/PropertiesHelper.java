package org.eclipse.jst.validation.sample.workbenchimpl;
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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.validation.sample.PModelEnum;
import org.eclipse.jst.validation.sample.parser.APropertyFile;
import org.eclipse.jst.validation.sample.parser.PropertyLine;
import org.eclipse.wst.validation.internal.operations.AWorkbenchHelper;
import org.eclipse.wst.validation.internal.operations.WorkbenchFileDelta;
import org.eclipse.wst.validation.internal.provisional.core.IFileDelta;

/**
 * This class implements the WebSphere Studio IValidationContext for the 
 * Properties Validator.
 */
public class PropertiesHelper extends AWorkbenchHelper {
	public PropertiesHelper() {
		super();
		 
		registerModel(PModelEnum.LINEINPUTREADER, "loadLineNumberReader", new Class[]{java.lang.String.class}); //$NON-NLS-1$
		registerModel(PModelEnum.RELEASE_LINEINPUTREADER, "releaseLineNumberReader", new Class[]{java.io.LineNumberReader.class}); //$NON-NLS-1$
		registerModel(PModelEnum.ALL_PROPERTIES_FILES, "loadAllPropertiesFiles"); //$NON-NLS-1$
		registerModel(PModelEnum.MSGLOGGER, "getMsgLogger"); //$NON-NLS-1$
		registerModel(PModelEnum.FILTER, "filter", new Class[]{IFileDelta[].class}); //$NON-NLS-1$
	}

	/**
	 * @see com.ibm.etools.validate.IWorkbenchHelper#getTargetObjectName(Object)
	 */
	public String getTargetObjectName(Object object) {
		return null;
	}
	
	/**
	 * @see com.ibm.etools.validate.IWorkbenchHelper#getResource(Object)
	 */
	public IResource getResource(Object object) {
		IFile file = null;
		if(object instanceof PropertyLine) {
			PropertyLine line = (PropertyLine)object;
			APropertyFile pFile = line.getFile();
			String fileName = pFile.getQualifiedFileName(); // a ValidationPropertyFile returns the file name as its qualified name
			file = getFile(fileName);
		}
		else if(object instanceof APropertyFile) {
			APropertyFile pFile = (APropertyFile)object;
			String fileName = pFile.getQualifiedFileName(); // a ValidationPropertyFile returns the file name as its qualified name
			file = getFile(fileName);
		}
		
		if((file == null) || (!file.exists())) {
			return super.getResource(object);
		}
		return file;
	}
	
	/**
	 * When a full validation is performed, this method returns an
	 * IFileDelta[] containing one IFileDelta for each .properties 
	 * file in the IProject.
	 */
	public IFileDelta[] loadAllPropertiesFiles() {
		IProject project = getProject();
		
		final Set files = new HashSet();
		IResourceVisitor visitor = new IResourceVisitor() {
			public boolean visit(IResource res) throws CoreException {
				if(!res.isAccessible()) {
					return false; // if the resource isn't accessible then neither are its children
				}
				
				if(isSrcFile(res)) { //$NON-NLS-1$
					WorkbenchFileDelta newFileDelta = new WorkbenchFileDelta(res.getFullPath().toString(), IFileDelta.CHANGED, res);
					files.add(newFileDelta);
				}

				return true; // visit the resource's children as well
			}
		};

		try {
			project.accept(visitor, IResource.DEPTH_INFINITE, true); // true means include phantom resources
		}
		catch(CoreException e) {
			Logger logger = getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(e);
			}
		}
		
		IFileDelta[] result = new IFileDelta[files.size()];
		files.toArray(result);
		return result;
	}
	
	/**
	 * Return the IFile handle for the file identified by fileName.
	 * null will be returned if the file does not exist.
	 */
	protected static IFile getFile(String fileName) {
		IFile file = (IFile)ResourcesPlugin.getWorkspace().getRoot().findMember(fileName);
		return file;
	}
	
	/**
	 * Given the file name of an existing .properties file, return
	 * the LineNumberReader that will read the file.
	 */
	public LineNumberReader loadLineNumberReader(String fileName) {
		try {
			IFile file = getFile(fileName);
			InputStream in = file.getContents();
			InputStreamReader reader = new InputStreamReader(in);
	
			// Because we want to read in a line at a time from the file, convert the InputStreamReader to a LineReader
			LineNumberReader lineInput = new LineNumberReader(reader);
			return lineInput;
		}
		catch(CoreException exc) {
			Logger logger = getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(exc);
			}
			return null;
		}
	}
	
	/**
	 * This method doesn't load anything; it releases the resources allocated
	 * by the loadLineNumberReader method. Its result will never be used, so always
	 * return null.
	 */
	public LineNumberReader releaseLineNumberReader(LineNumberReader reader) {
		if(reader == null) {
			return null;
		}
		
		try {
			reader.close();
		}
		catch (IOException exc) {
			Logger logger = getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(exc);
			}
		}

		return null;
	}
	
	/**
	 * The MsgLogger from this method can be used to log exceptions.
	 * This method will never return null.
	 */
	public Logger getMsgLogger() {
		return PropertiesValidatorPlugin.getPlugin().getMsgLogger();
	}
	
	// package visibility for performance reasons (synthetic accessor method)
	boolean isSrcFile(IResource res) {
		// Want to filter out the copies in the "bin" directory.
		if(res == null) {
			return false;
		}
		
		if(!(res instanceof IFile)) {
			return false;
		}
		
		if(!res.getFileExtension().equals("properties")) { //$NON-NLS-1$
			return false; 		
		}
		
		if(res.isDerived()) {
			// Created by eclipse
			return false;
		}
		
		return true;
	}
	
	public IFileDelta[] filter(IFileDelta[] changedFiles) {
		IFileDelta[] temp = new IFileDelta[changedFiles.length];
		int count = 0;
		for(int i=0; i<changedFiles.length; i++) {
			IFileDelta fd = changedFiles[i];
			IResource resource = ((WorkbenchFileDelta)fd).getResource();
			if(isSrcFile(resource)) {
				temp[count++] = fd;
			}
		}
		
		if(count == changedFiles.length) {
			return changedFiles;
		}
		
		IFileDelta[] result = new IFileDelta[count];
		System.arraycopy(temp, 0, result, 0, count);
		return result;
	}

}
