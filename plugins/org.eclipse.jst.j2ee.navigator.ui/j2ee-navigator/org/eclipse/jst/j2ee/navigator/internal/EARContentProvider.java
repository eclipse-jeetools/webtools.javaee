/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.navigator.internal;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.common.CommonPackage;

/**
 * This class handles the EAR navigator content to the project explorer
 */
public class EARContentProvider extends J2EEContentProvider {
	
	/**
	 * Default constructor
	 */
	public EARContentProvider() {
		super();
	}
	
	/**
	 * Constructor
	 * @param aViewerId
	 */
	public EARContentProvider(String aViewerId) {
		super(aViewerId);
	}
	
	/**
	 * This method will filter out any non Application DD objects so as not to duplicate meta nodes in
	 * the binary modules case.
	 */
	@Override
	public Object[] getChildren(Object aParentElement) {
		if (aParentElement instanceof EObject) {
			EObject eObject = (EObject)aParentElement;
			String emfPackage = eObject.eClass().getEPackage().getNsURI();
			if (!emfPackage.equals(ApplicationPackage.eNS_URI)&&
					!emfPackage.equals(CommonPackage.eNS_URI))
				return new Object[]{};
		}
		return super.getChildren(aParentElement);
	}
}
