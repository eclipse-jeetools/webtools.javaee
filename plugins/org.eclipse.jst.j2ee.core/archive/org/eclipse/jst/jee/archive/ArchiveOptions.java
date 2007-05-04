/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.archive;

import java.util.HashMap;
import java.util.Map;

public class ArchiveOptions {

	// TODO add debug tracing support

	public static final String LOAD_ADAPTER = "LOAD_ADAPTER"; //$NON-NLS-1$
	
	public static final String SAVE_ADAPTER = "SAVE_ADAPTER"; //$NON-NLS-1$

	public static final String ARCHIVE_HANDLER = "ARCHIVE_HANDLER"; //$NON-NLS-1$

	private Map optionsMap = new HashMap();

	public ArchiveOptions() {
	}

	public boolean hasOption(Object optionKey) {
		return optionsMap.containsKey(optionKey);
	}

	public Object getOption(Object optionKey) {
		return optionsMap.get(optionKey);
	}

	public void setOption(Object optionKey, Object optionValue) {
		optionsMap.put(optionKey, optionValue);
	}

}
