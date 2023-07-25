/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.archive;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * { @link ArchiveOptions} is a means for passing data pertaining to opening and
 * saving {@link IArchive}s. This class is simply a wrapper around {@link Map}
 * and a few well defined OptionKeys. Clients are welcome to define and set/get
 * any options deemed necessary.
 * 
 * This class is not intended to be subclassed by clients.
 */
public class ArchiveOptions {

	// TODO add debug tracing support

	/**
	 * An OptionKey used for specifying an {@link IArchiveLoadAdapter}.
	 */
	public static final String LOAD_ADAPTER = "LOAD_ADAPTER"; //$NON-NLS-1$

	/**
	 * An OptionKey used for specifying an {@link IArchiveSaveAdapter}.
	 */
	public static final String SAVE_ADAPTER = "SAVE_ADAPTER"; //$NON-NLS-1$
	
	/**
	 * An OptionKey used for specifying the path for the Archive.
	 */
	public static final String ARCHIVE_PATH = "ARCHIVE_PATH"; //$NON-NLS-1$
	
	/**
	 * An OptionKey used to specify the parent Archive used when opening nested Archives.
	 */
	public static final String PARENT_ARCHIVE = "PARENT_ARCHIVE"; //$NON-NLS-1$
	
	private Map optionsMap = new HashMap();

	public ArchiveOptions() {
	}

	public boolean hasOption(Object optionKey) {
		return optionsMap.containsKey(optionKey);
	}

	public Object getOption(Object optionKey) {
		return optionsMap.get(optionKey);
	}

	@SuppressWarnings("unchecked")
	public void setOption(Object optionKey, Object optionValue) {
		optionsMap.put(optionKey, optionValue);
	}
	
	public void removeOption(Object optionKey){
		optionsMap.remove(optionKey);
	}
	
	public Set keySet(){
		return optionsMap.keySet();
	}

}
