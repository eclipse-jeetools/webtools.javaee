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
package org.eclipse.jst.j2ee.internal.ejb.commands;



import java.text.MessageFormat;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodeGenResourceHandler;


/**
 * Insert the type's description here. Creation date: (8/22/2000 3:54:13 PM)
 * 
 * @author: Administrator
 */
public abstract class EJBClassReferenceCommand extends EJBDependentCommand implements IEJBClassReferenceCommand {
	private static final String CLASS_SET_FAILED_STRING = EJBCodeGenResourceHandler.getString("IWAJ0129E_The_Java_type_na_ERROR_"); //$NON-NLS-1$ = "IWAJ0129E The Java type named {0} could not be set for the {1} because its EJB is null."
	private String packageName;

	/**
	 * EJBClassReferenceCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.ICommand
	 */
	public EJBClassReferenceCommand(IRootCommand parent, JavaClass aJavaClass) {
		this(parent, aJavaClass, true);
	}

	/**
	 * EJBClassReferenceCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.ICommand
	 */
	public EJBClassReferenceCommand(IRootCommand parent, JavaClass aJavaClass, boolean shouldGenJava) {
		this(parent, aJavaClass, shouldGenJava, true);
	}

	/**
	 * EJBClassReferenceCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.ICommand
	 */
	public EJBClassReferenceCommand(IRootCommand parent, JavaClass aJavaClass, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, shouldGenJava, shouldGenMetadata);
		setJavaClass(aJavaClass);
		if (aJavaClass != null && aJavaClass.getJavaPackage() != null)
			setPackageName(aJavaClass.getJavaPackage().getName());
	}

	/**
	 * EJBClassReferenceCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.ICommand
	 */
	public EJBClassReferenceCommand(IRootCommand parent, String aJavaClassName, String aPackageName) {
		this(parent, aJavaClassName, aPackageName, true);
	}

	/**
	 * EJBClassReferenceCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.ICommand
	 */
	public EJBClassReferenceCommand(IRootCommand parent, String aJavaClassName, String aPackageName, boolean shouldGenJava) {
		this(parent, aJavaClassName, aPackageName, shouldGenJava, true);
	}

	/**
	 * EJBClassReferenceCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.internal.internal.commands.ICommand
	 */
	public EJBClassReferenceCommand(IRootCommand parent, String aJavaClassName, String aPackageName, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, shouldGenJava, shouldGenMetadata);
		setName(aJavaClassName);
		setPackageName(aPackageName);
	}

	/**
	 * Adds a package import.
	 */
	public void addPackageImportName(String packageImportName) {
		getClassReferenceHelper().addPackageImportName(packageImportName);
	}

	/**
	 * Adds a super interface.
	 */
	public void addSuperInterfaceName(String superInterfaceName) {
		getClassReferenceHelper().addSuperInterfaceName(superInterfaceName);
	}

	/**
	 * Adds a type import.
	 */
	public void addTypeImportName(String typeImportName) {
		getClassReferenceHelper().addTypeImportName(typeImportName);
	}

	protected void failedSettingClass() {
		String errorString = MessageFormat.format(CLASS_SET_FAILED_STRING, new String[]{getQualifiedName(), getClass().getName()});
		EJBCommandErrorHandler.handleError(errorString);
	}

	private EJBClassReferenceHelper getClassReferenceHelper() {
		return (EJBClassReferenceHelper) getHelper();
	}

	/**
	 * Insert the method's description here. Creation date: (8/22/2000 4:09:08 PM)
	 * 
	 * @return org.eclipse.jem.internal.java.JavaClass
	 */
	public JavaClass getJavaClass() {
		return (JavaClass) getSourceMetaType();
	}

	public JavaRefFactory getJavaFactory() {
		return ((JavaRefPackage) EPackage.Registry.INSTANCE.getEPackage(JavaRefPackage.eNS_URI)).getJavaRefFactory();
	}

	/**
	 * Insert the method's description here. Creation date: (9/4/2000 7:26:21 PM)
	 * 
	 * @return java.lang.String
	 */
	public String getName() {
		if (super.getName() == null && getJavaClass() != null)
			return getJavaClass().getName();
		return super.getName();
	}

	/**
	 * Insert the method's description here. Creation date: (9/4/2000 10:33:08 PM)
	 * 
	 * @return org.eclipse.jem.internal.java.JavaClass
	 */
	public JavaClass getOriginalJavaClass() {
		return getMetadataHistory() == null ? null : (JavaClass) getMetadataHistory().getOldMetadata();
	}

	/**
	 * Insert the method's description here. Creation date: (9/4/2000 7:45:18 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPackageName() {
		return packageName;
	}

	/**
	 * Insert the method's description here. Creation date: (9/4/2000 7:26:21 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getQualifiedName() {
		if (getName() == null && getJavaClass() != null)
			return getJavaClass().getQualifiedName();
		if (getPackageName() == null)
			return getName();
		return getPackageName() + "." + getName();//$NON-NLS-1$
	}

	/**
	 * Insert the method's description here. Creation date: (8/22/2000 8:56:00 AM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.ejb.Document
	 */
	public Resource getResource() {
		return (getParent() != null) ? ((EnterpriseBeanCommand) getParent()).getResource() : null;
	}

	protected JavaClass reflectJavaClass() {
		if (getName() == null) {
			String pattern = EJBCodeGenResourceHandler.getString("IWAJ0128E_Cannot_reflect_J_ERROR_"); //$NON-NLS-1$ = "IWAJ0128E Cannot reflect JavaClass because its name is not set within the command: {0}."
			EJBCommandErrorHandler.handleError(MessageFormat.format(pattern, new String[]{getClass().getName()}));
		} else
			return (JavaClass) JavaRefFactory.eINSTANCE.reflectType(getQualifiedName(), getContext());
		return null;
	}

	/**
	 * Insert the method's description here. Creation date: (8/22/2000 4:09:08 PM)
	 * 
	 * @param newJavaClass
	 *            org.eclipse.jem.internal.java.JavaClass
	 */
	public void setJavaClass(JavaClass newJavaClass) {
		setSourceMetaType(newJavaClass);
	}

	/**
	 * Insert the method's description here. Creation date: (9/4/2000 7:45:18 PM)
	 * 
	 * @param newPackageName
	 *            java.lang.String
	 */
	public void setPackageName(java.lang.String newPackageName) {
		packageName = newPackageName;
	}

	/**
	 * Insert the method's description here. Creation date: (10/5/00 6:06:08 PM)
	 * 
	 * @param newSuperclassName
	 *            java.lang.String
	 */
	public void setSuperclassName(java.lang.String newSuperclassName) {
		getClassReferenceHelper().setSuperclassName(newSuperclassName);
	}

	/**
	 * Gets the client view method collection.
	 * 
	 * @return List
	 */
	public List getMethodCollection() {
		return this.getClassReferenceHelper().getMethodCollection();
	}// getMethodCollection

	/**
	 * Sets the client view method collection.
	 * 
	 * @param List
	 *            methodCollection - The collection of methods.
	 */
	public void setMethodCollection(List methodCollection) {
		this.getClassReferenceHelper().setMethodCollection(methodCollection);
	}// setMethodCollection
}