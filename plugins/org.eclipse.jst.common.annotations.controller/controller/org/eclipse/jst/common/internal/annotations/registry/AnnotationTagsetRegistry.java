/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Apr 7, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.common.internal.annotations.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jem.util.RegistryReader;

/**
 * @author mdelder
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AnnotationTagsetRegistry extends RegistryReader {

	public static final AnnotationTagsetRegistry INSTANCE = new AnnotationTagsetRegistry();

	private Map index;

	protected AnnotationTagsetRegistry() {
		super("org.eclipse.wst.common.internal.annotations.controller", TagsetDescriptor.TAGSET); //$NON-NLS-1$
		readRegistry();
	}

	private List descriptors;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.RegistryReader#readElement(org.eclipse.core.runtime.IConfigurationElement)
	 */
	public boolean readElement(IConfigurationElement element) {
		if (TagsetDescriptor.TAGSET.equals(element.getName())) {
			getDescriptors().add(new TagsetDescriptor(element));
			return true;
		}
		return false;
	}

	public TagsetDescriptor getDescriptor(String name) {
		if (name != null && name.length() > 0) {

			/* Index descriptors to avoid unnecessary searching */
			TagsetDescriptor descriptor = (TagsetDescriptor) getIndex().get(name);
			if (descriptor != null)
				return descriptor;

			for (Iterator itr = AnnotationTagsetRegistry.INSTANCE.getDescriptors().iterator(); itr.hasNext();) {
				descriptor = (TagsetDescriptor) itr.next();
				if (name.equals(descriptor.getName())) {
					getIndex().put(descriptor.getName(), descriptor);
					return descriptor;

				}
			}
		}
		return null;
	}

	/**
	 * @return Returns the descriptors.
	 */
	protected List getDescriptors() {
		if (descriptors == null)
			descriptors = new ArrayList();
		return descriptors;
	}

	/**
	 * @return Returns the index.
	 */
	protected Map getIndex() {
		if (index == null)
			index = new HashMap();
		return index;
	}

	/**
	 * @param descriptor
	 */
	public void registerTagset(TagsetDescriptor descriptor) {
		if (descriptor != null && getDescriptor(descriptor.getName()) == null)
			getDescriptors().add(descriptor);
	}
}