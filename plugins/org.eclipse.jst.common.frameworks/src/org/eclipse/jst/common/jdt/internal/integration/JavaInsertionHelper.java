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
package org.eclipse.jst.common.jdt.internal.integration;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.jdom.DOMFactory;
import org.eclipse.wst.common.frameworks.internal.operations.IHeadlessRunnableWithProgress;


/**
 * @author DABERG
 * 
 * This class is used by the Java snippet support to capture the insertionString that is to be
 * inserted at the users selection point. It also provides the ability to define additional fields
 * and methods to support the insertionString.
 */
public class JavaInsertionHelper {
	protected DOMFactory domFactory = new DOMFactory();
	protected List fields;
	protected List methods;
	protected List imports;
	protected String insertionString;
	protected List extendedOperations;

	/**
	 *  
	 */
	public JavaInsertionHelper() {
		super();
	}

	/**
	 * @return
	 */
	public List getFields() {
		return fields;
	}

	/**
	 * @return
	 */
	public String getInsertionString() {
		return insertionString;
	}

	/**
	 * @return
	 */
	public List getMethods() {
		return methods;
	}

	/**
	 * This is required to be set by the client. This is the String that will be inserted at the
	 * users selection point.
	 * 
	 * @param string
	 */
	public void setInsertionString(String string) {
		insertionString = string;
	}

	/**
	 * This is a utility method that will parse the methodString and create a IDOMMethod. The
	 * DOMFactory will be used to create the method. This new method will be added to the list of
	 * methods.
	 * 
	 * @param methodString
	 * @see DOMFactory#createMethod(java.lang.String)
	 * @link org.eclipse.jdt.core.jdom.IDOMMethod
	 */
	public void addMethodFromSourceString(String methodString) {
		if (methodString != null && methodString.length() > 0) {
			if (methods == null)
				methods = new ArrayList();
			methods.add(domFactory.createMethod(methodString));
		}
	}

	/**
	 * This is a utility method that will parse the fieldString and create a IDOMField. The
	 * DOMFactory will be used to create the field. This new field will be added to the list of
	 * fields.
	 * 
	 * @param fieldString
	 * @see DOMFactory#createField(java.lang.String)
	 * @link org.eclipse.jdt.core.jdom.IDOMField
	 */
	public void addFieldFromSourceString(String fieldString) {
		if (fieldString != null && fieldString.length() > 0) {
			if (fields == null)
				fields = new ArrayList();
			fields.add(domFactory.createField(fieldString));
		}
	}

	/**
	 * Add an import that is either the qualified name of a type or a package name with .* at the
	 * end.
	 * 
	 * @param importString
	 */
	public void addImport(String importString) {
		if (importString != null && importString.length() > 0) {
			if (imports == null)
				imports = new ArrayList();
			imports.add(importString);
		}
	}

	/**
	 * Return true if the insertionString is set and not a zero length.
	 * 
	 * @return
	 */
	public boolean canInsertText() {
		return insertionString != null && insertionString.length() > 0;
	}

	/**
	 * @return
	 */
	public boolean hasFields() {
		return fields != null && !fields.isEmpty();
	}

	/**
	 * @return
	 */
	public boolean hasMethods() {
		return methods != null && !methods.isEmpty();
	}

	public boolean hasImports() {
		return imports != null && !imports.isEmpty();
	}

	/**
	 * @return Returns the imports.
	 */
	public List getImportStatements() {
		return imports;
	}

	/**
	 * @return Returns the extendedOperations.
	 */
	public List getExtendedOperations() {
		return extendedOperations;
	}

	/**
	 * This method allows you to add additional operations which will be performed after this
	 * JavaInsertionHelper is processed by the JavaInsertionOperation.
	 * 
	 * @param operation
	 * @link JavaInsertionOperation
	 */
	public void addExtendedOperation(IHeadlessRunnableWithProgress operation) {
		if (operation != null) {
			if (extendedOperations == null)
				extendedOperations = new ArrayList();
			extendedOperations.add(operation);
		}
	}
}