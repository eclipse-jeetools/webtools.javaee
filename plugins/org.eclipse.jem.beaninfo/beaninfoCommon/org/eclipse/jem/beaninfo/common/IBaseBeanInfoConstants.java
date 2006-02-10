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
 *  $Revision: 1.4 $  $Date: 2006/02/10 21:53:46 $ 
 */
package org.eclipse.jem.beaninfo.common;

 

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
	 * Indicator used to describe a factory instantiation pattern. 
	 * <p>
	 * This will be on the attributes of the BeanDescriptor for the factory class. It will be complete, in that if this
	 * factory is inherited from another factory, it must copy in the superclass's factory attribute. They won't be
	 * automatically merged.
	 * <p>
	 * The format is an Object[][]. The first dimension at index zero is for toolkit wide information and then indexes one and beyond are one for each creation method name. The second dimension is for one entry
	 * of classwide data and the rest are the data for 
	 * each creation method.
	 * <p>
	 * The first entry at Object[0] will be <code>{initString, isShared, onFreeform}</code> where:
	 * <dl>
	 * <dt>initString
	 * <dd>The init string that should be used to create an instance of the toolkit if it needs one, or <code>null</code> if it doesn't
	 * need one (i.e. all static) or if the default constructor should be used.
	 * This is used when a component is dropped from the palette that is for a toolkit component.
	 * <dt>isShared
	 * <dd><code>false</code> if each call to the creation method should have a new instance of the toolkit. This means that the toolkit manages only
	 * one instance. This is more like a controller than a toolkit in this case. <code>true</code> if it should
	 * try to reuse the toolkit of the parent if it has one,  or any other found toolkit of the same type, (i.e. a subtype will be acceptable).  
	 * This is used when a component is dropped from the palette that is for a toolkit component.
	 * <dt>onFreeform
	 * <dd><code>true</code> if the toolkit is created that it should appear on the freeform surface to be selectable. This would be of use
	 * if the toolkit had properties that need to be set. If the toolkit had no properties then it doesn't need to be selectable and should
	 * not be on the freeform. Use <code>false</code> in that case.
	 * </dl> 
	 * <p>
	 * The second and other entries of the array are of the format:
	 * <code>{methodName, returnedClassname, isStatic, Object[], ...}</code> where:
	 * <dl>
	 * <dt>methodName
	 * <dd>The name of the creation method this entry is for (String).
	 * <dt>returnedClassname
	 * <dd>The name of the class that is created and returned from the method (String).
	 * <dt>isStatic
	 * <dd><code>Boolean.TRUE</code> if the method is a static method, <code>Boolean.FALSE</code> if not. 
	 * This is used when a component is dropped from the palette that is for a toolkit component.
	 * <dt>Object[]
	 * <dd>Zero or more arrays. The array contains the name of the properties for each method signature that each
	 * respective argument is for, or <code>null</code> if that arg is not a property. There can be more than one array if there
	 * is more than one factory method of the same name, and returns the same type, but what is different is only the number of arguments.
	 * If there is a
	 * factory method that has no properties as arguments or has no arguments, don't include an array for it. For example if there was only one factory method and it had no
	 * arguments then there would not be any arrays.
	 * Currently cannot handle where more than one method has the same number of arguments but different types for the arguments.
	 * </dl>
	 * <p>
	 * A example is:
	 * <pre><code>
	 * new Object[][] {
	 *   {"new a.b.c.Toolkit(\"ABC\")", Boolean.TRUE, Boolean.FALSE},
	 *   {"createButton", "a.b.c.Button", Boolean.FALSE, new Object[] {"parent", "text"}, new Object[] {"parent"}}
	 * }
	 * </code>
	 * </pre>
	 * <p>
	 * This example says that this class is toolkit (factory). To construct an instead use <code>"new a.b.c.Toolkit(\"ABC\")"</code> and it is shared
	 * with other objects created from this factory instance. Also, the factory method is "createButton", returns an "a.b.c.Button", and it is
	 * not a static call (i.e. use a toolkit instance to create it). It has two forms of factory methods. One is two arguments where the first
	 * arg is the parent property and the second arg is the text property. The other form has only one argument, the parent property.
	 * <p>
	 * The way this is used in a palette entry to drop a new object that a toolkit can create is to have an expression of the form
	 * <code>{toolkit:classname}.factorymethod(args)</code>. So for the above example it would be <code>{toolkit:a.b.c.Toolkit}.createButton(parent)</code>.
	 * The classname <b>must</b> be fully-qualified and if an inner class it must use the "$" instead of "." for the name, i.e. a.b.c.Toolkit.InnerFactory
	 * would be a.b.c.Toolkit$InnerFactory. 
	 * <p>
	 * <b>NOTE:</b> This is an EXPERIMENTAL API and can change in the future until committed.  
	 *   
	 * @since 1.2.0
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
