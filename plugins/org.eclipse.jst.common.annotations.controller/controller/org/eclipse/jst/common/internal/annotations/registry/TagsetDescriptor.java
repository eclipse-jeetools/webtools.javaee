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
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;


/**
 * @author mdelder
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class TagsetDescriptor {

	public static final String TAGSET = "AnnotationTagSet"; //$NON-NLS-1$
	public static final String ATT_NAME = "name"; //$NON-NLS-1$
	public static final String ATT_DISPLAY_NAME = "displayName"; //$NON-NLS-1$
	public static final String ATT_DESCRIPTION = "description"; //$NON-NLS-1$
	public static final String ATT_PARENT_TAGSET = "parentTagset"; //$NON-NLS-1$
	public static final String ATT_VALID_VALUES_HELPER = "validValuesHelper"; //$NON-NLS-1$

	private final IConfigurationElement element;

	protected String name;
	protected String displayName;
	protected String description;
	protected String parentTagset;
	protected AttributeValuesHelper validValuesHelper;

	protected TagsetDescriptor() {
		element = null;
	}

	public TagsetDescriptor(IConfigurationElement element) {
		this.element = element;
		init();
	}

	/**
	 *  
	 */
	private void init() {
		this.name = this.element.getAttribute(ATT_NAME);
		this.displayName = this.element.getAttribute(ATT_DISPLAY_NAME);
		this.description = this.element.getAttribute(ATT_DESCRIPTION);
		this.parentTagset = this.element.getAttribute(ATT_PARENT_TAGSET);
		// set the valid values helper if there is one
		try {
			String validValuesHelperName = this.element.getAttribute(ATT_VALID_VALUES_HELPER);
			if (validValuesHelperName != null) {
				Class loaded = Class.forName(validValuesHelperName);
				this.validValuesHelper = (AttributeValuesHelper) loaded.newInstance();
			}
		} catch (Exception e) {
			// Do nothing
		}
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return Returns the displayName.
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @return Returns the element.
	 */
	public IConfigurationElement getElement() {
		return element;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Returns the parentTagset.
	 */
	public String getParentTagset() {
		return parentTagset;
	}

	public TagsetDescriptor[] getDirectDependents() {

		if (getName() == null || getName().equals("")) //$NON-NLS-1$
			return new TagsetDescriptor[0];

		List dependents = new ArrayList();

		TagsetDescriptor descriptor = null;
		for (Iterator itr = AnnotationTagsetRegistry.INSTANCE.getDescriptors().iterator(); itr.hasNext();) {
			descriptor = (TagsetDescriptor) itr.next();
			if (getName().equals(descriptor.getParentTagset()))
				dependents.add(descriptor);
		}

		TagsetDescriptor[] descriptors = new TagsetDescriptor[dependents.size()];
		dependents.toArray(descriptors);
		return descriptors;
	}

	/**
	 * @return Returns the validValuesHelper.
	 */
	public AttributeValuesHelper getValidValuesHelper() {
		return validValuesHelper;
	}

	/**
	 * @param validValuesHelper
	 *            The validValuesHelper to set.
	 */
	public void setValidValuesHelper(AttributeValuesHelper validValuesHelper) {
		this.validValuesHelper = validValuesHelper;
	}
}