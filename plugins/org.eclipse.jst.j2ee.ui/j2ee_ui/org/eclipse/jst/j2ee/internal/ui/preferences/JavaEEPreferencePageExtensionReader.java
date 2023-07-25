/*******************************************************************************
 * Copyright (c) 2009, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.ui.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jem.util.RegistryReader;



public class JavaEEPreferencePageExtensionReader extends RegistryReader {

	private static JavaEEPreferencePageExtensionReader instance = null;
	private List<JavaEEPreferencePageExtension> pageExtenders = null;
	
	public JavaEEPreferencePageExtensionReader(){
		super("org.eclipse.jst.j2ee.ui", "JavaEEPreferencePageExtender"); //$NON-NLS-1$ //$NON-NLS-2$ 
	}
	

	public static JavaEEPreferencePageExtensionReader getInstance() {
		if (instance == null) {
			instance = new JavaEEPreferencePageExtensionReader();
			instance.readRegistry();
		}
		return instance;
	}
	
	@Override
	public boolean readElement(IConfigurationElement element) {
		if (JavaEEPreferencePageExtension.JAVAEE_PAGE_EXTENSION.equals(element.getName())) {
			addExtension(element);
			return true;
		}
		return false;
	}
	
	protected void addExtension(IConfigurationElement newExtension) {
		getJavaEEPageExtenders().add(new JavaEEPreferencePageExtension(newExtension));
	}
	
	public List<JavaEEPreferencePageExtension> getJavaEEPageExtenders() {
		if (pageExtenders == null)
			pageExtenders = new ArrayList();
		return pageExtenders;
	}	

}
