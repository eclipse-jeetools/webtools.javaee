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


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.wst.validation.internal.core.FileDelta;
import org.eclipse.wst.validation.internal.core.IFileDelta;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;

/**
 * This class is the heart of the filesystem validation framework.
 * It &quot;loads&quot; each validator's metadata
 */
public class FilesystemManager {
	private static FilesystemManager _inst = null;
	private Set _loaders = null;
	
	private FilesystemManager() {
		_loaders = new HashSet();
		
		// Please pretend that this metadata, i.e., the file extension, fully-qualified
		// class name of the helper, and fully-qualified class name of the validator, 
		// was loaded generically somehow. In practice, this information could be stored
		// in .xml files, in a configuration file, etc., that the framework would read
		// to find the installed validators.
		try {
			_loaders.add(new FilesystemLoader(new String[]{".properties"}, loadHelper("org.eclipse.jst.validation.sample.filesystemimpl.FilesystemPropertiesHelper"), loadValidator("org.eclipse.jst.validation.sample.PropertiesValidator"))); //$NON-NLS-1$  //$NON-NLS-2$ //$NON-NLS-3$
		}
		catch(IllegalArgumentException exc) {
			exc.printStackTrace();
		}
		catch(ClassNotFoundException exc) {
			exc.printStackTrace();
		}
		catch(IllegalAccessException exc) {
			exc.printStackTrace();
		}
		catch(InstantiationException exc) {
			exc.printStackTrace();
		}
	}
	
	/**
	 * Given a fully-qualified name of a helper, return an instance of it.
	 */
	private static IFilesystemHelper loadHelper(String className) throws IllegalArgumentException, ClassNotFoundException, IllegalAccessException, InstantiationException  {
		return (IFilesystemHelper)loadClass(className);
	}
	
	/**
	 * Given a fully-qualified name of a validator, return an instance of it.
	 */
	private static IValidator loadValidator(String className) throws IllegalArgumentException, ClassNotFoundException, IllegalAccessException, InstantiationException  {
		return (IValidator)loadClass(className);
	}
	
	/**
	 * Return an instance of the class identified by the className parameter,
	 * which contains the fully-qualified class name of the class.
	 */
	private static Object loadClass(String className) throws IllegalArgumentException, ClassNotFoundException, IllegalAccessException, InstantiationException {
		if(className == null) {
			throw new IllegalArgumentException("loadClass argument must not be null"); //$NON-NLS-1$
		}
		
		Class clazz = Class.forName(className);
		return clazz.newInstance();		
	}
	
	/**
	 * Return the FilesystemManager singleton.
	 */
	public static FilesystemManager getManager() {
		if(_inst == null) {
			_inst = new FilesystemManager();
		}
		return _inst;
	}

	/**
	 * Given an array of file names, return an array of IFileDelta
	 * instances to pass to the validator instance.
	 */
	public IFileDelta[] getFileDeltas(String[] fileNames) {
		if((fileNames == null) || (fileNames.length == 0)) {
			return null;
		}
		
		FileDelta[] files = new FileDelta[fileNames.length];
		for(int i=0; i<fileNames.length; i++) {
			files[i] = new FileDelta(fileNames[i], IFileDelta.CHANGED);
		}
		return files;
	}
	
	/**
	 * Given an array of IFileDelta instances, return an array
	 * of the IValidator instances, with their associated IValidationContext instances,
	 * that validate these particular delta instances.
	 */
	public FilesystemLoader[] getLoaders(IFileDelta[] deltas) {
		if(deltas == null) {
			return getAllLoaders();
		}

		Set loaders = new HashSet();		
		for(int i=0; i<deltas.length; i++) {
			FilesystemLoader[] loaderArray = getLoadersFor(deltas[i]);
			if(loaderArray != null) {
				for(int j=0; j<loaderArray.length; j++) {
					loaders.add(loaderArray[j]);
				}
			}
		}
		
		FilesystemLoader[] result = new FilesystemLoader[loaders.size()];
		loaders.toArray(result);
		return result;
	}
	
	/**
	 * Given an IValidator instance, return the IValidationContext instance that the
	 * IValidator uses to load information from the filesystem validation
	 * framework environment.
	 */
	public IFilesystemHelper getHelper(IValidator validator) {
		if(validator == null) {
			return null;
		}
		
		Iterator iterator = _loaders.iterator();
		while(iterator.hasNext()) {
			FilesystemLoader loader = (FilesystemLoader)iterator.next();
			if(validator.equals(loader.getValidator())) {
				return loader.getHelper();
			}
		}
		
		return null;
	}
	
	/**
	 * Given the fully-qualified name of the validator, return the IValidator instance.
	 * null will be returned if the IValidator cannot be found.
	 */
	public IValidator getValidator(String validatorClassName) {
		if(validatorClassName == null) {
			return null;
		}
		
		Iterator iterator = _loaders.iterator();
		while(iterator.hasNext()) {
			FilesystemLoader loader = (FilesystemLoader)iterator.next();
			if(validatorClassName.equals(loader.getValidator().getClass().getName())) {
				return loader.getValidator();
			}
		}
		
		return null;
	}
	
	/**
	 * Return an array of all FilesystemLoader instances. Each loader has
	 * one IValidator and one IValidationContext.
	 */
	public FilesystemLoader[] getAllLoaders() {
		FilesystemLoader[] result = new FilesystemLoader[_loaders.size()];
		_loaders.toArray(result);
		return result;
	}

	/**
	 * Given a file name, return the loader which contains the validator & helper
	 * which can validate that file, if one exists.
	 */
	public FilesystemLoader[] getLoadersFor(IFileDelta delta) {
		Iterator iterator = _loaders.iterator();
		FilesystemLoader[] tempLoaders = new FilesystemLoader[_loaders.size()];
		int count = 0;
		while(iterator.hasNext()) {
			FilesystemLoader loader = (FilesystemLoader)iterator.next();
			String[] fileExtensions = loader.getFileExtensions();
			if(fileExtensions == null) {
				continue;
			}
			
			for(int i=0; i<fileExtensions.length; i++) {
				if(delta.getFileName().endsWith(fileExtensions[i]) && (delta.getDeltaType() == IFileDelta.ADDED || delta.getDeltaType() == IFileDelta.CHANGED || delta.getDeltaType() == IFileDelta.DELETED)) {
					tempLoaders[count++] = loader;
				}
			}
		}
		
		FilesystemLoader[] result = new FilesystemLoader[count];
		System.arraycopy(tempLoaders, 0, result, 0, count);
		return result;
	}

}
