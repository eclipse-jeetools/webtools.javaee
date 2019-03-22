/*******************************************************************************
 * Copyright (c) 2007 BEA Systems, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * BEA Systems, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.classpathdep;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;

/**
 * Reads classpath dependency related extension point data.
 */
public class ClasspathDependencyExtensions {
	
	private static final String CLASSPATH_DEP_EXTENSION_ID = "classpathdep"; //$NON-NLS-1$
	private static final String FILTERED_CLASSPATH_CONTAINER = "filteredClasspathContainer"; //$NON-NLS-1$
	private static final String CLASSPATH_CONTAINER_ID = "containerID"; //$NON-NLS-1$
	private static ClasspathDependencyExtensions _instance = null;
	
	public static ClasspathDependencyExtensions get() {
		if (_instance == null) {
			_instance = new ClasspathDependencyExtensions();
		}
		return _instance;
	}
	
	private final List filteredClasspathContainerIDs;
	
	private ClasspathDependencyExtensions() {
		filteredClasspathContainerIDs = new ArrayList();
		readExtensions();
	}

	/**
	 * Returns the Set of classpath container IDs that should
	 * be filtered from the container that can potentially be marked
	 * as dependencies. This set of defined using the extension point
	 * org.eclipse.jst.j2ee.FilteredClasspathContainersExtension.
	 * @return Set of classpath container IDs.
	 */
	public List getFilteredClasspathContainerIDs() {
		return filteredClasspathContainerIDs;
	}
	
	private void readExtensions() {
		final IExtensionRegistry registry = Platform.getExtensionRegistry();
        final IExtensionPoint point = registry.getExtensionPoint(J2EEPlugin.PLUGIN_ID, 
            		CLASSPATH_DEP_EXTENSION_ID);
        
        if(point == null) {
            throw new RuntimeException("Extension point not found!"); //$NON-NLS-1$
        }
        
        final IExtension[] extensions = point.getExtensions();
        for (int i = 0; i < extensions.length; i++) {
        	final IConfigurationElement[] elements = extensions[i].getConfigurationElements();
        	for (int j = 0; j < elements.length; j++) {
        		if (elements[j].getName().equals(FILTERED_CLASSPATH_CONTAINER)) {
        			String cpContainerID = elements[j].getAttribute(CLASSPATH_CONTAINER_ID); 
                    if (cpContainerID != null) {
                    	filteredClasspathContainerIDs.add(cpContainerID);
                    }
        		}
        	}
        }
	}
}
