/*******************************************************************************
 * Copyright (c) 2007 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author yavor.vasilev.boyadzhiev@sap.com
 */
public class CreateEnterpriseBeanTemplateModel implements INewJavaClassDataModelProperties {
	
	protected IDataModel dataModel;
	
	public CreateEnterpriseBeanTemplateModel(IDataModel dataModel) {
		this.dataModel = dataModel;
	}
	
	public Collection<String> getImports() {
		Collection<String> collection = new TreeSet<String>();
		
		String className = getClassName();
		String superclassName = getQualifiedSuperclassName();

		if (superclassName != null && superclassName.length() > 0 &&
				!equalSimpleNames(className, superclassName) && 
				!isImportInJavaLang(superclassName))
			collection.add(superclassName);
		
		List<String> interfaces = getQualifiedInterfaces();
		if (interfaces != null) {
			for (String iface : interfaces) {
				if (!equalSimpleNames(getClassName(), iface) && 
						!isImportInJavaLang(iface)) 
					collection.add(iface);
			}
		}
		
		return collection;
	}

	public String getClassName() {
		return getProperty(CLASS_NAME).trim();
	}

	public String getJavaPackageName() {
		return getProperty(JAVA_PACKAGE).trim();
	}

	public String getQualifiedJavaClassName() {
		return getJavaPackageName() + "." + getClassName(); //$NON-NLS-1$
	}

	public String getSuperclassName() {
		String qualified = getQualifiedSuperclassName();
		if (equalSimpleNames(getClassName(), qualified)) {
			return qualified;
		} else {
			return Signature.getSimpleName(qualified);
		}
	}
	
	public String getQualifiedSuperclassName() {
		return getProperty(SUPERCLASS).trim();
	}
	
	public List<String> getInterfaces() {
		List<String> qualifiedInterfaces = getQualifiedInterfaces();
		List<String> interfaces = new ArrayList<String>(qualifiedInterfaces.size());
		
		for (String qualified : qualifiedInterfaces) {
			if (equalSimpleNames(getClassName(), qualified)) {
				interfaces.add(qualified);
			} else {
				interfaces.add(Signature.getSimpleName(qualified));
			}
		}
		
		return interfaces;
	}

	public List<String> getQualifiedInterfaces() {
		List<String> interfaces = (List<String>) dataModel.getProperty(INTERFACES);
		if (interfaces == null) {
			return new ArrayList<String>();
		} else
			return interfaces;
	}
	
	public boolean isPublic() {
		return dataModel.getBooleanProperty(MODIFIER_PUBLIC);
	}

	public boolean isFinal() {
		return dataModel.getBooleanProperty(MODIFIER_FINAL);
	}

	public boolean isAbstract() {
		return dataModel.getBooleanProperty(MODIFIER_ABSTRACT);
	}
	
	protected String getProperty(String propertyName) {
		return dataModel.getStringProperty(propertyName);
	}

	protected boolean equalSimpleNames(String name1, String name2) {
		String simpleName1 = Signature.getSimpleName(name1);
		String simpleName2 = Signature.getSimpleName(name2);
		return simpleName1.equals(simpleName2);
	}
	
    private boolean isImportInJavaLang(String arg) {
    	return arg.startsWith("java.lang."); 
    }
}
