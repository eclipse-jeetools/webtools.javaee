/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IBaseBeanInfoConstants.java,v $
 *  $Revision: 1.1 $  $Date: 2005/10/06 15:18:38 $ 
 */
package org.eclipse.jem.beaninfo.vm;

 

/**
 * Constants for the BaseBeanInfo for arguments. This class is common between
 * the IDE and the remote vm so that these constants can be used on both sides.
 * <p>
 * These are constants used in FeatureAttributes as keys. The other special
 * constants that are not keys in FeatureAttributes are left in BaseBeanInfo
 * since they are not needed on both sides.
 * 
 * @since 1.2.0
 */
public interface IBaseBeanInfoConstants {

	/**
	 * Indicator used to describe a factory instantiation pattern.  Not API as will change to become more extensible 
	 * via .override mechanism in future
	 * 
	 * @since 1.1
	 */	
	public static final String FACTORY_CREATION = "FACTORY_CREATION";//$NON-NLS-1$
	
	/**
	 * Category indicator for apply property arguments. Category is a pre-defined attribute name too. That is where the category is stored in a
	 * descriptor.
	 * 
	 * @since 1.1.0
	 */
	public static final String CATEGORY = "category"; //$NON-NLS-1$
	
	/**
	 * Enumeration values indicator for apply property arguments. Enumeration values is a pre-defined attribute name too. That is where the
	 * enumeration values are stored.
	 * 
	 * @since 1.1.0
	 */
	public static final String ENUMERATIONVALUES = "enumerationValues";//$NON-NLS-1$
	
	// The keys for icon file names, NOT THE java.awt.icon key.
	public static final String ICONCOLOR16X16URL = "ICON_COLOR_16x16_URL"; //$NON-NLS-1$	
	public static final String ICONCOLOR32X32URL = "ICON_COLOR_32x32_URL"; //$NON-NLS-1$     	// Not used
	public static final String ICONMONO16X16URL = "ICON_MONO_16x16_URL"; //$NON-NLS-1$        	// Not used
	public static final String ICONMONO32X32URL = "ICON_MONO_32x32_URL"; //$NON-NLS-1$			// Not used


	/**
	 * FeatureAttribute key for explicit property changes. The value is a Boolean. <code>true</code>
	 * indicates that the Customize Bean customizer supplied by the BeanInfo will indicate which
	 * properties it has changed through firing {@link java.beans.PropertyChangeEvent} instead of the 
	 * Visual Editor automatically trying to determine the set of changed properties.
	 * <p>
	 * The default if not set is <code>false</code>.
	 * 
	 * @since 1.1.0.1
	 */
	public static final String EXPLICIT_PROPERTY_CHANGE = "EXPLICIT_PROPERTY_CHANGE"; //$NON-NLS-1$

	/**
	 * Used by Visual Editor as feature attribute key/value to indicate that it must create an implicit setting of a property(s).
	 * For example {@link javax.swing.JFrame#getContentPane()}. There must be a content pane
	 * set in the VE model so that users can drop the components on it. Setting this here
	 * means that the default content pane from the JFrame will show up in the editor to use.
	 * <p>
	 * This should be used with care in that not all properties are required to always show up.
	 * They can be queried when needed.
	 * <p>
	 * The value can be either a {@link String} for one property. Or it can be a {@link String[]} for more
	 * than one property.
	 * 
	 * @since 1.2.0
	 */
	public static final String REQUIRED_IMPLICIT_PROPERTIES = "requiredImplicitProperties";	//$NON-NLS-1$

}
