/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.internal.emitter;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;


/**
 * @author naci
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public abstract class DefaultEjbIntrospector implements IEjbIntrospector {
	
	
	
	public abstract EnterpriseBean getEnterpriseBean();

	protected boolean isEntityBean(ICompilationUnit unit) {
	
		try {
			IType type = unit.findPrimaryType();
			String[] inter = type.getSuperInterfaceNames();
			for (int i = 0; i < inter.length; i++) {
				if (inter[i].endsWith("EntityBean")) {
					return true;
				}
			}
		} catch (JavaModelException e) {
		}
		return false;
	}

	protected boolean isMDBBean(ICompilationUnit unit) {
	
		try {
			IType type = unit.findPrimaryType();
			String[] inter = type.getSuperInterfaceNames();
			for (int i = 0; i < inter.length; i++) {
				if (inter[i].endsWith("MessageDrivenBean")) {
					return true;
				}
			}
		} catch (JavaModelException e) {
		}
		return false;
	}

	/**
	 * @return
	 */
	protected boolean isSessionBean(ICompilationUnit unit) {
	
		try {
			IType type = unit.findPrimaryType();
			String[] inter = type.getSuperInterfaceNames();
			for (int i = 0; i < inter.length; i++) {
				if (inter[i].endsWith("SessionBean")) {
					return true;
				}
			}
		} catch (JavaModelException e) {
		}
		return false;
	}
	

}
