/***********************************************************************
 * Copyright (c) 2008, 2019 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.internal.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.core.IType;
import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class Result {

	private Collection<IType> dependedTypes;

	private Collection<JavaEEObject> modelObjects;

	private JavaEEObject mainObject;

	public Result() {
		dependedTypes = new HashSet<IType>();
		// most of the time there will be only one object in the model objects
		// list. Exceptions are made for results that will contain security
		// roles for example. This is way here the modelObjects collection is
		// ArrayList with size 1
		modelObjects = new ArrayList<JavaEEObject>(1);
	}

	public JavaEEObject getMainObject() {
		return mainObject;
	}

	public void setMainObject(JavaEEObject mainObject) {
		this.mainObject = mainObject;
		modelObjects.add(mainObject);
	}

	public Collection<JavaEEObject> getAdditional() {
		return modelObjects;
	}

	public Collection<IType> getDependedTypes() {
		return dependedTypes;
	}
	
	public boolean isEmpty() {
		return mainObject == null && getAdditional().isEmpty();
	}
}
