/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Sep 19, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal;

import java.util.List;

import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;



/**
 * This is a helper used for deriving the JNDI name of enterprise beans,
 * and for inheritance support.  The helper can be plugged in by application
 * server vendors.
 */

public interface EjbModuleExtensionHelper extends J2EEModuleExtensionHelper {
	List getSubtypes(EnterpriseBean anEJB);
	EnterpriseBean getSuperType(EnterpriseBean anEJB);
	List getRoleMethodNamesExtended(ContainerManagedEntity cmp);
	String getEJBInheritanceFileName();
	String getJNDIName(EJBJar jar, EnterpriseBean bean); 
	List getRelationships_cmp11(EJBJar jar);
	List getLocalRelationshipRoles_cmp11(ContainerManagedEntity cmp);
	boolean isEJBInheritanceSupported();
}
