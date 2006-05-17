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
 * Created on Aug 22, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.common.internal.annotations.registry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;

/**
 * All of the information in a single tagSpec tag, including the enclosing handler.
 */
public class TagSpec {

	/**
	 * Handle to the descriptor of the plugin that declared the completion information for this tag,
	 * if any.
	 */
	Bundle bundle;

	protected ResourceBundle resourceBundle;

	private boolean attemptedToFindResourceBundle = false;

	private AttributeValuesHelper validValuesHelper;
	private TagsetDescriptor tagsetDescriptor;

	/**
	 * Name of the tag.
	 */
	private String tagName;

	/**
	 * Scope of the tag: METHOD | FIELD | TYPE
	 */
	private int scope;

	/**
	 * Multiplicity of the tag: ONE | MANY
	 */
	private int multiplicity;

	/**
	 * Attributes that can be set for this tag. (Instances of TagAttribSpec)
	 */
	private List attributes = new ArrayList();

	private String helpKey;

	/**
	 * Text type for use with localized text container.
	 */
	public static final int HELP_TEXT = 0;

	public static final int METHOD = 0;

	public static final int TYPE = 1;

	public static final int FIELD = 2;

	public interface Multiplicity {
		public static final int ONE = 1;

		public static final int MANY = 2;
	}

	public TagSpec(String aName, int aScope, int aMultiplicity) {
		tagName = aName;
		scope = aScope;
		multiplicity = aMultiplicity;
	}

	public static int scopeFromString(String s) throws CoreException {
		if (s != null) {
			if (s.equalsIgnoreCase("type")) { //$NON-NLS-1$
				return TagSpec.TYPE;
			} else if (s.equalsIgnoreCase("field")) { //$NON-NLS-1$
				return TagSpec.FIELD;
			} else if (s.equalsIgnoreCase("method")) { //$NON-NLS-1$
				return TagSpec.METHOD;
			} else {
				// Should be impossible unless the annotation-taghandler.exsd or
				// calling code changes.
				//TODO: Logging
				throw new CoreException(new Status(IStatus.ERROR, "org.eclipse.wst.common.internal.annotations.controller", IStatus.OK, AnnotationsControllerResources.TagSpec_3 + s, null)); //$NON-NLS-1$
			}
		}
		throw new CoreException(new Status(IStatus.ERROR, "org.eclipse.wst.common.internal.annotations.controller", IStatus.OK, AnnotationsControllerResources.TagSpec_4, null)); //$NON-NLS-1$

	}

	public static int multiplicityFromString(String s) throws CoreException {
		if (s != null) {
			if (s.equalsIgnoreCase("1")) { //$NON-NLS-1$
				return TagSpec.Multiplicity.ONE;
			} else if (s.equalsIgnoreCase("*")) { //$NON-NLS-1$
				return TagSpec.Multiplicity.MANY;
			}
			throw new CoreException(new Status(IStatus.ERROR, "org.eclipse.wst.common.internal.annotations.controller", IStatus.OK, AnnotationsControllerResources.TagSpec_4, null)); //$NON-NLS-1$
		}
		//Return default
		return TagSpec.Multiplicity.ONE;
	}

	/**
	 * @return Scope for tag, METHOD | TYPE | FIELD
	 */
	public int getScope() {
		return scope;
	}

	/**
	 * @return multiplicity for tag, ONE | MANY
	 */
	public int getMultiplicity() {
		return multiplicity;
	}

	/**
	 * @return Name of the tag
	 */
	public String getTagName() {
		return tagName;
	}

	public TagsetDescriptor getTagSetDescriptor() {
		if (tagsetDescriptor == null) {
			String tagSetName = getTagName();
			int index = tagSetName.lastIndexOf('.');
			if (index == -1)
				tagSetName = ""; //$NON-NLS-1$
			else
				tagSetName = tagSetName.substring(index + 1);
			tagsetDescriptor = AnnotationTagsetRegistry.INSTANCE.getDescriptor(tagSetName);
		}
		return tagsetDescriptor;
	}

	/**
	 * Sets the scope of this tag.
	 * 
	 * @param i
	 *            METHOD | TYPE | FIELD
	 */
	public void setScope(int i) {
		scope = i;
	}

	/**
	 * Sets the multiplicity of this tag.
	 * 
	 * @param i
	 *            ONE | MANY
	 */
	public void setMultiplicity(int i) {
		multiplicity = i;
	}

	/**
	 * Sets the name of the tag
	 * 
	 * @param string
	 *            Name for the tag.
	 */
	public void setTagName(String string) {
		tagName = string;
	}

	/**
	 * 
	 * @return List of attributes for this tag.
	 */
	public List getAttributes() {
		return attributes;
	}

	/**
	 * Adds an attribute to the list of attributes for this tag.
	 * 
	 * @param att
	 *            A TagAttribSpec
	 */
	public void addAttribute(TagAttribSpec att) {
		if (att == null)
			return;
		attributes.add(att);
		att.setTagSpec(this);
	}

	/**
	 * Sets the list of attributes for this tag.
	 * 
	 * @param lst
	 *            An array list of TagAttribSpecs
	 */
	public void setAttributes(List lst) {
		if (lst == null)
			attributes.clear();
		else {
			attributes = lst;
			for (int i = 0; i < lst.size(); i++) {
				TagAttribSpec tas = (TagAttribSpec) lst.get(i);
				tas.setTagSpec(this);
			}
		}
	}

	/**
	 * Looks for attribute named <code>attName</code>. Case insensitive.
	 * 
	 * @param attName
	 *            Name to look for
	 * @return A TagAttribSpec, or null if none was found.
	 */
	public TagAttribSpec attributeNamed(String attName) {
		Iterator i = getAttributes().iterator();

		while (i.hasNext()) {
			TagAttribSpec tas = (TagAttribSpec) i.next();

			if (attName.equalsIgnoreCase(tas.getAttribName())) {
				return tas;
			}
		}
		return null;

	}

	/**
	 * @return Returns the declaringPlugin.
	 */
	public Bundle getBundle() {
		return bundle;
	}

	/**
	 * @param declaringPlugin
	 *            The declaringPlugin to set.
	 */
	protected void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * @return Returns the resourceBundle.
	 */
	public ResourceBundle getResourceBundle() {
		if (resourceBundle == null && !attemptedToFindResourceBundle) {
			attemptedToFindResourceBundle = true;
			Bundle aBundle = getBundle();
			if (aBundle != null)
				resourceBundle = Platform.getResourceBundle(bundle);
		}
		return resourceBundle;
	}

	/**
	 * @param resourceBundle
	 *            The resourceBundle to set.
	 */
	public void setResourceBundle(ResourceBundle resourceBundle) {
		attemptedToFindResourceBundle = false;
		this.resourceBundle = resourceBundle;
	}

	/**
	 * @return Returns the validValuesHelper.
	 */
	public AttributeValuesHelper getValidValuesHelper() {
		if (validValuesHelper == null && getTagSetDescriptor() != null)
			setValidValuesHelper(getTagSetDescriptor().getValidValuesHelper());
		return validValuesHelper;
	}

	/**
	 * @param validValuesHelper
	 *            The validValuesHelper to set.
	 */
	public void setValidValuesHelper(AttributeValuesHelper validValuesHelper) {
		this.validValuesHelper = validValuesHelper;
	}

	public String getHelpKey() {
		if (helpKey == null)
			helpKey = computeHelpKey();
		return helpKey;
	}

	/**
	 * @return
	 */
	protected String computeHelpKey() {
		return "tagh." + getTagName(); //$NON-NLS-1$
	}

	public void setHelpKey(String helpKey) {
		this.helpKey = helpKey;
	}

	public String lookupTagHelp() throws MissingResourceException {
		ResourceBundle b = getResourceBundle();
		if (b == null)
			return null;
		String key = getHelpKey();
		String val = b.getString(getHelpKey());
		if (val == key)
			val = null;
		return val;
	}
}