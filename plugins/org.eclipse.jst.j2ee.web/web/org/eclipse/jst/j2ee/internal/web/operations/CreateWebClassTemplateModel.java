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
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateWebClassTemplateModel {
	
	protected IDataModel dataModel;
	
	public CreateWebClassTemplateModel(IDataModel dataModel) {
		this.dataModel = dataModel;
	}
	
	public Collection<String> getImports() {
		Collection<String> collection = new TreeSet<String>();
		
		String className = getClassName();
		String superclassName = getQualifiedSuperclassName();

		if (superclassName != null && superclassName.length() > 0 && 
				!equalSimpleNames(className, superclassName)) {
			collection.add(superclassName);
		}
		
		List interfaces = getQualifiedInterfaces();
		if (interfaces != null) {
			Iterator iterator = interfaces.iterator();
			while (iterator.hasNext()) {
				String iface = (String) iterator.next();
				if (!equalSimpleNames(getClassName(), iface)) {
					collection.add(iface);
				}
			}
		}
		
		return collection;
	}

	public String getClassName() {
		return getProperty(INewJavaClassDataModelProperties.CLASS_NAME).trim();
	}

	public String getJavaPackageName() {
		return getProperty(INewJavaClassDataModelProperties.JAVA_PACKAGE).trim();
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
		return getProperty(INewJavaClassDataModelProperties.SUPERCLASS).trim();
	}
	
	public List getInterfaces() {
		List qualifiedInterfaces = getQualifiedInterfaces();
		List interfaces = new ArrayList(qualifiedInterfaces.size());
		
		Iterator iter = qualifiedInterfaces.iterator();
		while (iter.hasNext()) {
			String qualified = (String) iter.next();
			if (equalSimpleNames(getClassName(), qualified)) {
				interfaces.add(qualified);
			} else {
				interfaces.add(Signature.getSimpleName(qualified));
			}
		}
		
		return interfaces;
	}

	public List getQualifiedInterfaces() {
		return (List) this.dataModel.getProperty(INewJavaClassDataModelProperties.INTERFACES);
	}

	public boolean isPublic() {
		return dataModel.getBooleanProperty(INewJavaClassDataModelProperties.MODIFIER_PUBLIC);
	}

	public boolean isFinal() {
		return dataModel.getBooleanProperty(INewJavaClassDataModelProperties.MODIFIER_FINAL);
	}

	public boolean isAbstract() {
		return dataModel.getBooleanProperty(INewJavaClassDataModelProperties.MODIFIER_ABSTRACT);
	}
	
	public boolean isAnnotated() {
		return dataModel.getBooleanProperty(IAnnotationsDataModel.USE_ANNOTATIONS);
	}

	protected String getProperty(String propertyName) {
		return dataModel.getStringProperty(propertyName);
	}
	
	protected boolean equalSimpleNames(String name1, String name2) {
		String simpleName1 = Signature.getSimpleName(name1);
		String simpleName2 = Signature.getSimpleName(name2);
		return simpleName1.equals(simpleName2);
	}

}
