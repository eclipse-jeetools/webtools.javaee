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
package org.eclipse.jst.j2ee.model.internal.validation;



/**
 * This interface holds the message constants that are specific
 * to the EJB 1.1 specification.
 */
public interface IMessagePrefixEjb11Constants extends IEJBValidatorMessageConstants {
	/*package*/ static final String SPEC = ".ejb11"; // EJB specification being validated //$NON-NLS-1$
	
	
	/*package*/ static final String CHKJ2001 = "CHKJ2001"; // The key class must implement the X method.  //$NON-NLS-1$
	/*package*/ static final String CHKJ2002 = "CHKJ2002"; // This class should implement a matching ejbPostCreate method for this method. //$NON-NLS-1$
	/*package*/ static final String CHKJ2004 = "CHKJ2004"; // The bean class should not implement the ejbFind method. //$NON-NLS-1$
	/*package*/ static final String CHKJ2005 = "CHKJ2005"; // The method must be either a create method or a find method. //$NON-NLS-1$
	/*package*/ static final String CHKJ2006 = "CHKJ2006"; // The bean class must have a public constructor that takes no arguments. //$NON-NLS-1$
	/*package*/ static final String CHKJ2007 = "CHKJ2007"; // The bean class should not declare the finalize() method. //$NON-NLS-1$
	/*package*/ static final String CHKJ2008 = "CHKJ2008"; // The bean class must contain at least one ejbCreate method. //$NON-NLS-1$
	/*package*/ static final String CHKJ2009 = "CHKJ2009"; // The bean class must contain at least one ejbFindByPrimaryKey method. //$NON-NLS-1$
	/*package*/ static final String CHKJ2010 = "CHKJ2010"; // The home interface must contain at least one create method. //$NON-NLS-1$
	/*package*/ static final String CHKJ2011 = "CHKJ2011"; // The home interface must contain at least one findByPrimaryKey method. //$NON-NLS-1$
	/*package*/ static final String CHKJ2012 = "CHKJ2012"; // This type must be an interface. //$NON-NLS-1$
	/*package*/ static final String CHKJ2013 = "CHKJ2013"; // The bean class must implement X. //$NON-NLS-1$
	/*package*/ static final String CHKJ2020 = "CHKJ2020"; // The key class must be public. //$NON-NLS-1$
	/*package*/ static final String CHKJ2021 = "CHKJ2021"; // The key class must define a public default constructor. //$NON-NLS-1$
	/*package*/ static final String CHKJ2024 = "CHKJ2024"; // X must not exist. Only create() may exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2025 = "CHKJ2025"; // X must not exist. Only ejbCreate() may exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2026 = "CHKJ2026"; // The bean class must implement a matching ejbCreate method for this method. //$NON-NLS-1$
	/*package*/ static final String CHKJ2028 = "CHKJ2028"; // This method will not be called unless a matching ejbCreate method is defined. //$NON-NLS-1$
	/*package*/ static final String CHKJ2029 = "CHKJ2029"; // This method will not be called unless a matching create method is defined on the home. //$NON-NLS-1$
	/*package*/ static final String CHKJ2030 = "CHKJ2030"; // This method must have a matching ejbFind method in the bean class. //$NON-NLS-1$
	/*package*/ static final String CHKJ2032 = "CHKJ2032"; // Bean class X must declare at least one container managed field. //$NON-NLS-1$
	/*package*/ static final String CHKJ2033 = "CHKJ2033"; // The setEntityContext method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2034 = "CHKJ2034"; // The unsetEntityContext method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2035 = "CHKJ2035"; // The ejbActivate method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2036 = "CHKJ2036"; // The ejbPassivate method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2037 = "CHKJ2037"; // The ejbRemove method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2038 = "CHKJ2038"; // The ejbLoad method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2039 = "CHKJ2039"; // The ejbStore method must exist. //$NON-NLS-1$

	/*package*/ static final String CHKJ2100 = "CHKJ2100"; // Both ends of the role must use the same number of attributes. //$NON-NLS-1$
	/*package*/ static final String CHKJ2101 = "CHKJ2101"; // Both ends of the role must use the same type. //$NON-NLS-1$
	/*package*/ static final String CHKJ2102 = "CHKJ2102"; // Either a finder descriptor, or a matching custom finder method, must be defined. //$NON-NLS-1$

	/*package*/ static final String CHKJ2200 = "CHKJ2200"; // The static field must be final. //$NON-NLS-1$
	/*package*/ static final String CHKJ2201 = "CHKJ2201"; // The field must not be transient. //$NON-NLS-1$
	/*package*/ static final String CHKJ2202 = "CHKJ2202"; // The field type is invalid for a <cmp-field>. //$NON-NLS-1$
	/*package*/ static final String CHKJ2203 = "CHKJ2203"; // The field must be public. //$NON-NLS-1$
	/*package*/ static final String CHKJ2207 = "CHKJ2207"; // Field X on bean Y must be the same type as the <primkey-field>. //$NON-NLS-1$

	/*package*/ static final String CHKJ2400_bus = "CHKJ2400.bus"; // Deprecated use of RemoteException. //$NON-NLS-1$
	/*package*/ static final String CHKJ2400_ejbCreate = "CHKJ2400.ejbCreate"; // Deprecated use of RemoteException. //$NON-NLS-1$
	/*package*/ static final String CHKJ2400_ejbFind = "CHKJ2400.ejbFind"; // Deprecated use of RemoteException. //$NON-NLS-1$
	/*package*/ static final String CHKJ2400_ejbPostCreate = "CHKJ2400.ejbPostCreate"; // Deprecated use of RemoteException. //$NON-NLS-1$
	/*package*/ static final String CHKJ2401 = "CHKJ2401"; // This method must return void. //$NON-NLS-1$
	/*package*/ static final String CHKJ2405 = "CHKJ2405"; // findByPrimaryKey must have the <prim-key-class> as its argument. //$NON-NLS-1$
	/*package*/ static final String CHKJ2406 = "CHKJ2406"; // This method should return the primary key type. //$NON-NLS-1$
	/*package*/ static final String CHKJ2408_bus = "CHKJ2408.bus"; // This method must be public. //$NON-NLS-1$
	/*package*/ static final String CHKJ2408_ejbCreate = "CHKJ2408.ejbCreate"; // This method must be public. //$NON-NLS-1$
	/*package*/ static final String CHKJ2408_ejbFind = "CHKJ2408.ejbFind"; // This method must be public. //$NON-NLS-1$
	/*package*/ static final String CHKJ2408_ejbPostCreate = "CHKJ2408.ejbPostCreate"; // This method must be public. //$NON-NLS-1$
	/*package*/ static final String CHKJ2409_bus = "CHKJ2409.bus"; // This method must not be final. //$NON-NLS-1$
	/*package*/ static final String CHKJ2409_ejbCreate = "CHKJ2409.ejbCreate"; //This method must not be final. //$NON-NLS-1$
	/*package*/ static final String CHKJ2409_ejbFind = "CHKJ2409.ejbFind"; // This method must not be final. //$NON-NLS-1$
	/*package*/ static final String CHKJ2409_ejbPostCreate = "CHKJ2409.ejbPostCreate"; // This method must not be final. //$NON-NLS-1$
	/*package*/ static final String CHKJ2410_bus = "CHKJ2410.bus"; // This method must not be static. //$NON-NLS-1$
	/*package*/ static final String CHKJ2410_ejbCreate = "CHKJ2410.ejbCreate"; // This method must not be static. //$NON-NLS-1$
	/*package*/ static final String CHKJ2410_ejbFind = "CHKJ2410.ejbFind"; // This method must not be static. //$NON-NLS-1$
	/*package*/ static final String CHKJ2410_ejbPostCreate = "CHKJ2410.ejbPostCreate"; // This method must not be static. //$NON-NLS-1$
	/*package*/ static final String CHKJ2412 = "CHKJ2412"; // The return type of this method must be serializable at runtime. //$NON-NLS-1$
	/*package*/ static final String CHKJ2413 = "CHKJ2413"; // The argument must be serializable at runtime. //$NON-NLS-1$
	/*package*/ static final String CHKJ2414 = "CHKJ2414"; // This method must throw exception X. //$NON-NLS-1$
	/*package*/ static final String CHKJ2415 = "CHKJ2415"; // The return type must match the return type of the matching method. //$NON-NLS-1$
	/*package*/ static final String CHKJ2418 = "CHKJ2418"; // This method must return void. //$NON-NLS-1$
	/*package*/ static final String CHKJ2419 = "CHKJ2419"; // This method must be a create method. //$NON-NLS-1$
	/*package*/ static final String CHKJ2420 = "CHKJ2420"; // X must be thrown by the corresponding method on the home interface. //$NON-NLS-1$
	/*package*/ static final String CHKJ2432 = "CHKJ2432"; // X must be thrown by the corresponding method on the remote interface. //$NON-NLS-1$

	/*package*/ static final String CHKJ2908 = "CHKJ2908"; // An enterprise bean cannot be reflected. Run the XML validator. //$NON-NLS-1$
}
