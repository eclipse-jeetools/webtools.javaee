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
 * This interface holds the message constants that are specific to the
 * EJB 2.0 specification (e.g. MDB-only messages).
 */
public interface IMessagePrefixEjb20Constants extends IEJBValidatorMessageConstants {
	/*package*/ static final String SPEC = ".ejb20"; // EJB specification being validated //$NON-NLS-1$
	/*package*/ static final String ON_BASE_SPEC = ON_BASE + SPEC; 
	/*package*/ static final String ON_THIS_SPEC = ON_THIS + SPEC;
	/*package*/ static final String CHKJ = null; // Placeholder for a message id when the id hasn't been created in the .properties file
	
	/*package*/ static final String CHKJ2018 = "CHKJ2018"; // This dependent value class must be serializable. //$NON-NLS-1$

	/*package*/ static final String CHKJ2043 = "CHKJ2043"; // This dependent value class must be public. //$NON-NLS-1$
	/*package*/ static final String CHKJ2044 = "CHKJ2044"; // This dependent value class must not be abstract. //$NON-NLS-1$

	/*package*/ static final String CHKJ2050_acc = "CHKJ2050.acc"; // An accessor method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_constr = "CHKJ2050.constr"; // A public default constructor must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_create = "CHKJ2050.create"; // A create method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_ejbActivate = "CHKJ2050.ejbActivate"; // The ejbActivate method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_ejbCreate = "CHKJ2050.ejbCreate"; // An ejbCreate method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_ejbFind = "CHKJ2050.ejbFind"; // An ejbFind method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_ejbFindByPrimaryKey = "CHKJ2050.ejbFindByPrimaryKey"; // An ejbFindByPrimaryKey method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_ejbHome = "CHKJ2050.ejbHome"; // An ejbHome method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_ejbLoad = "CHKJ2050.ejbLoad"; // An ejbLoad method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_ejbPassivate = "CHKJ2050.ejbPassivate"; // An ejbPassivate method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_ejbPostCreate = "CHKJ2050.ejbPostCreate"; // An ejbPostCreate method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_ejbRemove = "CHKJ2050.ejbRemove"; // An ejbRemove method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_ejbSelect = "CHKJ2050.ejbSelect"; // An ejbSelect method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_ejbStore = "CHKJ2050.ejbStore"; // An ejbStore method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_equals = "CHKJ2050.equals"; // An equals method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_find = "CHKJ2050.find"; // An find method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_findByPrimaryKey = "CHKJ2050.findByPrimaryKey"; // An findByPrimaryKey method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_hashCode = "CHKJ2050.hashCode"; // An hashCode method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_home = "CHKJ2050.home"; // An home method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_onMessage = "CHKJ2050.onMessage"; // An onMessage method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_setEntityContext = "CHKJ2050.setEntityContext"; // An setEntityContext method must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2050_unsetEntityContext = "CHKJ2050.unsetEntityContext"; // An unsetEntityContext method must exist. //$NON-NLS-1$

	/*package*/ static final String CHKJ2404 = "CHKJ2404"; // Application exception X must extend java.lang.Exception. //$NON-NLS-1$

	/*package*/ static final String CHKJ2416 = "CHKJ2416"; // Application exception X must not extend java.lang.RuntimeException. //$NON-NLS-1$
	/*package*/ static final String CHKJ2421 = "CHKJ2421"; // ejbCreate must be public. //$NON-NLS-1$
	/*package*/ static final String CHKJ2422 = "CHKJ2422"; // ejbCreate must not be final. //$NON-NLS-1$
	/*package*/ static final String CHKJ2423 = "CHKJ2423"; // ejbCreate must not be static. //$NON-NLS-1$
	/*package*/ static final String CHKJ2424 = "CHKJ2424"; // ejbCreate must return void. //$NON-NLS-1$
	/*package*/ static final String CHKJ2426 = "CHKJ2426"; // ejbPostCreate must be public. //$NON-NLS-1$
	/*package*/ static final String CHKJ2427 = "CHKJ2427"; // ejbPostCreate must not be final. //$NON-NLS-1$
	/*package*/ static final String CHKJ2428 = "CHKJ2428"; // ejbPostCreate must not be static. //$NON-NLS-1$
	
	/*package*/ static final String CHKJ2430 = "CHKJ2430"; // X must exist for this ejbHome method to be called. //$NON-NLS-1$
	/*package*/ static final String CHKJ2431 = "CHKJ2431"; // ejbHome must be public. //$NON-NLS-1$
	/*package*/ static final String CHKJ2439 = "CHKJ2439"; // ejbHome must not be static. //$NON-NLS-1$
	
	/*package*/ static final String CHKJ2441 = "CHKJ2441"; // business method must be public. //$NON-NLS-1$
	/*package*/ static final String CHKJ2442 = "CHKJ2442"; // business method must not be final. //$NON-NLS-1$
	/*package*/ static final String CHKJ2443 = "CHKJ2443"; // business method must not be static. //$NON-NLS-1$
	/*package*/ static final String CHKJ2445 = "CHKJ2445"; // onMessage must be public. //$NON-NLS-1$
	/*package*/ static final String CHKJ2446 = "CHKJ2446"; // onMessage must not be final. //$NON-NLS-1$
	/*package*/ static final String CHKJ2447 = "CHKJ2447"; // onMessage must not be static. //$NON-NLS-1$
	/*package*/ static final String CHKJ2449 = "CHKJ2449"; // accessor method must be public. //$NON-NLS-1$
	
	/*package*/ static final String CHKJ2450 = "CHKJ2450"; // accessor method must be abstract //$NON-NLS-1$
	/*package*/ static final String CHKJ2452 = "CHKJ2452"; // A transient field should not be the {0} type. //$NON-NLS-1$
	/*package*/ static final String CHKJ2453 = "CHKJ2453"; // Transient fields are discouraged. //$NON-NLS-1$
	/*package*/ static final String CHKJ2454 = "CHKJ2454"; // CMR and CMP fields must not be defined on the bean class. //$NON-NLS-1$
	/*package*/ static final String CHKJ2457 = "CHKJ2457"; // ejbFind must be public. //$NON-NLS-1$
	/*package*/ static final String CHKJ2458 = "CHKJ2458"; // ejbFind must not be final. //$NON-NLS-1$
	/*package*/ static final String CHKJ2459 = "CHKJ2459"; // ejbFind must not be static. //$NON-NLS-1$
	
	/*package*/ static final String CHKJ2461 = "CHKJ2461"; // This interface must follow RMI-IIOP rules for interfaces. //$NON-NLS-1$
	/*package*/ static final String CHKJ2462 = "CHKJ2462"; // The method name must not start with "create". //$NON-NLS-1$
	/*package*/ static final String CHKJ2463 = "CHKJ2463"; // The method name must not start with "find". //$NON-NLS-1$
	/*package*/ static final String CHKJ2464 = "CHKJ2464"; // The method name must not start with "remove" //$NON-NLS-1$
	/*package*/ static final String CHKJ2465 = "CHKJ2465"; // home method X must throw Y for Z. //$NON-NLS-1$
	/*package*/ static final String CHKJ2466 = "CHKJ2466"; // remote home method X must not expose the Y type. //$NON-NLS-1$
	/*package*/ static final String CHKJ2467 = "CHKJ2467"; // This method must throw CreateException. //$NON-NLS-1$
	/*package*/ static final String CHKJ2468 = "CHKJ2468"; // This interface must follow RMI-IIOP rules for remote interfaces. //$NON-NLS-1$
	/*package*/ static final String CHKJ2469 = "CHKJ2469"; // This method (EJBObject or EJBLocalObject) is provided by the container. //$NON-NLS-1$
	
	/*package*/ static final String CHKJ2470 = "CHKJ2470"; // This component matching method must return X. //$NON-NLS-1$
	/*package*/ static final String CHKJ2471 = "CHKJ2471"; // This component matching method must throw X. //$NON-NLS-1$
	/*package*/ static final String CHKJ2472 = "CHKJ2472"; // This method must not pass X as an argument or result type. //$NON-NLS-1$
	/*package*/ static final String CHKJ2473 = "CHKJ2473"; // This (CMR) method must not be exposed. //$NON-NLS-1$
	/*package*/ static final String CHKJ2474 = "CHKJ2474"; // This method must not expose X. //$NON-NLS-1$
	/*package*/ static final String CHKJ2475 = "CHKJ2475"; // findByPrimaryKey's argument must be the primary key. //$NON-NLS-1$
	/*package*/ static final String CHKJ2476 = "CHKJ2476"; // This method must be the create/ejbCreate method. //$NON-NLS-1$
	/*package*/ static final String CHKJ2477 = "CHKJ2477"; // This method must throw FinderException. //$NON-NLS-1$
	/*package*/ static final String CHKJ2478 = "CHKJ2478"; // This method must not throw ObjectNotFoundException. //$NON-NLS-1$
	/*package*/ static final String CHKJ2479 = "CHKJ2479"; // findByPrimaryKey must return X. //$NON-NLS-1$
	
	/*package*/ static final String CHKJ2480 = "CHKJ2480"; // CMP/CMR field name must begin with a lowercase letter. //$NON-NLS-1$
	/*package*/ static final String CHKJ2481 = "CHKJ2481"; // field X must be a <cmp-field> //$NON-NLS-1$
	/*package*/ static final String CHKJ2482 = "CHKJ2482"; // field X must be a <cmr-field> //$NON-NLS-1$
	/*package*/ static final String CHKJ2483 = "CHKJ2483"; // CMP/CMR field should not be exposed //$NON-NLS-1$
	/*package*/ static final String CHKJ2484 = "CHKJ2484"; // CMP/CMR field must be either a primitive or a serializable type. //$NON-NLS-1$
	/*package*/ static final String CHKJ2485 = "CHKJ2485"; // ejbSelect method must be abstract. //$NON-NLS-1$
	/*package*/ static final String CHKJ2486 = "CHKJ2486"; // ejbSelect must return X or a collection thereof. //$NON-NLS-1$
	/*package*/ static final String CHKJ2487 = "CHKJ2487"; // ejbSelect must be public. //$NON-NLS-1$
	/*package*/ static final String CHKJ2488 = "CHKJ2488"; // ejbSelect must throw FinderException //$NON-NLS-1$
	/*package*/ static final String CHKJ2489 = "CHKJ2489"; // Field must be the primary key type. //$NON-NLS-1$
	
	/*package*/ static final String CHKJ2490 = "CHKJ2490"; // This method must not be exposed. //$NON-NLS-1$
	/*package*/ static final String CHKJ2492 = "CHKJ2492"; // ejbRemove must be public. //$NON-NLS-1$
	/*package*/ static final String CHKJ2493 = "CHKJ2493"; // ejbRemove must not be final. //$NON-NLS-1$
	/*package*/ static final String CHKJ2494 = "CHKJ2494"; // ejbRemove must not be static. //$NON-NLS-1$
	/*package*/ static final String CHKJ2495 = "CHKJ2495"; // This find method must be associated with a query element in ejb-jar.xml. //$NON-NLS-1$
	/*package*/ static final String CHKJ2496 = "CHKJ2496"; // This ejbSelect method must be associated with a query element in ejb-jar.xml. //$NON-NLS-1$
	/*package*/ static final String CHKJ2497 = "CHKJ2497"; // ejbCreate must throw CreateException. //$NON-NLS-1$

	/*package*/ static final String CHKJ2500_ejbCreate = "CHKJ2500.ejbCreate"; // X must be serializable at runtime. //$NON-NLS-1$
	/*package*/ static final String CHKJ2500_ejbHome = "CHKJ2500.ejbHome"; // X must be serializable at runtime. //$NON-NLS-1$
	/*package*/ static final String CHKJ2500_ejbFind = "CHKJ2500.ejbFind"; // X must be serializable at runtime. //$NON-NLS-1$
	/*package*/ static final String CHKJ2500_business = "CHKJ2500.bus"; // X must be serializable at runtime. //$NON-NLS-1$
	/*package*/ static final String CHKJ2500_home = "CHKJ2500.home"; // X must be serializable at runtime. //$NON-NLS-1$
	/*package*/ static final String CHKJ2500_create = "CHKJ2500.create"; // X must be serializable at runtime. //$NON-NLS-1$
	/*package*/ static final String CHKJ2500_find = "CHKJ2500.find"; // X must be serializable at runtime. //$NON-NLS-1$

	/*package*/ static final String CHKJ2501_ejbCreate = "CHKJ2501.ejbCreate"; // ejbCreate must not throw application exceptions. //$NON-NLS-1$
	/*package*/ static final String CHKJ2501_ejbRemove = "CHKJ2501.ejbRemove"; // ejbRemove must not throw application exceptions. //$NON-NLS-1$
	/*package*/ static final String CHKJ2501_onMessage = "CHKJ2501.onMessage"; // ejbRemove must not throw application exceptions. //$NON-NLS-1$

	/*package*/ static final String CHKJ2502_ejbFind = "CHKJ2502.ejbFind"; // ejbFind must not exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2502_ejbSelect = "CHKJ2502.ejbSelect"; // ejbSelect must not exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2502_finalize = "CHKJ2502.finalize"; // finalize must not exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2502 = "CHKJ2502"; // This method must not exist.  //$NON-NLS-1$

	/*package*/ static final String CHKJ2503_bus = "CHKJ2503.bus"; // The business method must throw/not throw RemoteException. //$NON-NLS-1$
	/*package*/ static final String CHKJ2503_create = "CHKJ2503.create"; // This method must throw/not throw RemoteException. //$NON-NLS-1$
	/*package*/ static final String CHKJ2503_ejbCreate = "CHKJ2503.ejbCreate"; // This method must throw/not throw RemoteException. //$NON-NLS-1$
	/*package*/ static final String CHKJ2503_ejbFind = "CHKJ2503.ejbFind"; // This method must throw/not throw RemoteException. //$NON-NLS-1$
	/*package*/ static final String CHKJ2503_ejbHome = "CHKJ2503.ejbHome"; // This method must throw/not throw RemoteException. //$NON-NLS-1$
	/*package*/ static final String CHKJ2503_ejbPostCreate = "CHKJ2503.ejbPostCreate"; // This method must throw/not throw RemoteException. //$NON-NLS-1$
	/*package*/ static final String CHKJ2503_find = "CHKJ2503.find"; // This method must throw/not throw RemoteException. //$NON-NLS-1$
	/*package*/ static final String CHKJ2503_home = "CHKJ2503.home"; // This method must throw/not throw RemoteException. //$NON-NLS-1$
	/*package*/ static final String CHKJ2503_onMessage = "CHKJ2503.onMessage"; // This method must throw/not throw RemoteException. //$NON-NLS-1$

	/*package*/ static final String CHKJ2504_business = "CHKJ2504.business"; // The matching method must exist on the bean class. //$NON-NLS-1$
	/*package*/ static final String CHKJ2504_create = "CHKJ2504.create"; // The matching method must exist on the bean class. //$NON-NLS-1$
	/*package*/ static final String CHKJ2504_find = "CHKJ2504.find"; // The matching method must exist on the bean class. //$NON-NLS-1$
	/*package*/ static final String CHKJ2504_home = "CHKJ2504.home"; // The matching method must exist on the bean class. //$NON-NLS-1$

	/*package*/ static final String CHKJ2505_ejbPostCreate = "CHKJ2505.ejbPostCreate"; // This method must return void. //$NON-NLS-1$
	/*package*/ static final String CHKJ2505_onMessage = "CHKJ2505.onMessage"; // This method must return void. //$NON-NLS-1$
	/*package*/ static final String CHKJ2505_ejbRemove = "CHKJ2505.ejbRemove"; // This method must return void. //$NON-NLS-1$

	/*package*/ static final String CHKJ2800_NAMED = "CHKJ2800.named"; // <local> cannot be reflected. //$NON-NLS-1$
	/*package*/ static final String CHKJ2800_UNNAMED = "CHKJ2800.unnamed"; // <local> must exist. //$NON-NLS-1$
	/*package*/ static final String CHKJ2805_NAMED = "CHKJ2805.named"; // <local-home> cannot be reflected //$NON-NLS-1$
	/*package*/ static final String CHKJ2805_UNNAMED = "CHKJ2805.unnamed"; // <local-home> must exist. //$NON-NLS-1$
	
	/*package*/ static final String CHKJ2813 = "CHKJ2813"; // <ejb-name> X must be a valid Java identifier. //$NON-NLS-1$
	/*package*/ static final String CHKJ2816 = "CHKJ2816"; // <ejb-relation-name> must be unique in ejb-jar.xml. //$NON-NLS-1$
	/*package*/ static final String CHKJ2817 = "CHKJ2817"; // <ejb-relationship-role-name> must be unique within an <ejb-relation> //$NON-NLS-1$
	/*package*/ static final String CHKJ2818 = "CHKJ2818"; // The value of the <multiplicity> element must be either "One" or "Many" //$NON-NLS-1$
	/*package*/ static final String CHKJ2819 = "CHKJ2819"; // <abstract-schema-name> {0} must be a valid Java identifier //$NON-NLS-1$
	
	/*package*/ static final String CHKJ2821 = "CHKJ2821"; // <relationship-role-source> {0} must exist in the module //$NON-NLS-1$

	/*package*/ static final String CHKJ2848 = "CHKJ2848"; // <cascade-delete> must be used with a <multiplicity> of "One" //$NON-NLS-1$
	
	/*package*/ static final String CHKJ2854 = "CHKJ2854"; // <abstract-schema-name> {0} must be unique within the ejb-jar.xml file //$NON-NLS-1$
	/*package*/ static final String CHKJ2855 = "CHKJ2855"; // <cmp-field> {0} must be a Java primitive or a Java serializable type //$NON-NLS-1$
	/*package*/ static final String CHKJ2856 = "CHKJ2856"; // <cmr-field> {0} must be the {1} type or a collection thereof //$NON-NLS-1$
	/*package*/ static final String CHKJ2857 = "CHKJ2857"; // <cmr-field> {0} must be a valid Java identifier and begin with a lowercase letter //$NON-NLS-1$
	/*package*/ static final String CHKJ2858 = "CHKJ2858"; // <abstract-schema-name> must exist //$NON-NLS-1$
	/*package*/ static final String CHKJ2859 = "CHKJ2859"; // Reserved identifier {0} cannot be used as an <ejb-name> //$NON-NLS-1$
	
	/*package*/ static final String CHKJ2860 = "CHKJ2860"; // Reserved identifier {0} cannot be used as an <abstract-schema-name> //$NON-NLS-1$
	/*package*/ static final String CHKJ2866 = "CHKJ2866"; // <cmr-field> {0} must be defined on {1} //$NON-NLS-1$
	/*package*/ static final String CHKJ2867 = "CHKJ2867"; // This bean can have only unidirectional relationships to other beans //$NON-NLS-1$
	/*package*/ static final String CHKJ2868 = "CHKJ2868"; // The value of the <cmr-field-type> element must be either "java.util.Collection" or "java.util.Set" //$NON-NLS-1$
	/*package*/ static final String CHKJ2869 = "CHKJ2869"; // At least one <ejb-relationship-role> must define a <cmr-field> //$NON-NLS-1$

	/*package*/ static final String CHKJ2870 = "CHKJ2870"; // <ejb-name> {0} must exist //$NON-NLS-1$
	/*package*/ static final String CHKJ2871 = "CHKJ2871"; // <ejb-name> {0} must refer to a CMP bean //$NON-NLS-1$
	/*package*/ static final String CHKJ2872 = "CHKJ2872"; // The value of the <cmp-version> element must be either "1.x" or "2.x" //$NON-NLS-1$
	/*package*/ static final String CHKJ2873 = "CHKJ2873"; // Migrate this bean's datasource binding to a CMP Connection Factory binding. //$NON-NLS-1$
	/*package*/ static final String CHKJ2874 = "CHKJ2874"; // Migrate this EJB module's default datasource binding to a default CMP Connection Factory binding. //$NON-NLS-1$
	
	/*package*/ static final String CHKJ2880 = "CHKJ2880"; // This bean must provide a <local-home> //$NON-NLS-1$
	/*package*/ static final String CHKJ2881 = "CHKJ2881"; // This bean must provide a <home> //$NON-NLS-1$
	/*package*/ static final String CHKJ2882 = "CHKJ2882"; // This bean must provide a <local> //$NON-NLS-1$
	/*package*/ static final String CHKJ2883 = "CHKJ2883"; // This bean must provide a <remote> //$NON-NLS-1$
	/*package*/ static final String CHKJ2884 = "CHKJ2884"; // This bean must provide either a <local-home> or a <home> //$NON-NLS-1$
	/*package*/ static final String CHKJ2885 = "CHKJ2885"; // This bean must provide either a <local> or a <remote> //$NON-NLS-1$
	
	/*package*/ static final String CHKJ2886 = "CHKJ2886.ejb21"; // Duplicate activation config: "{0}" exist in message drvien bean: "{1}" //$NON-NLS-1$
	/*package*/ static final String CHKJ2887 = "CHKJ2887.ejb21"; // The activation config with name : "{0}" has invalid value : "1" for message drvien bean: "{2}" //$NON-NLS-1$
	/*package*/ static final String CHKJ2888 = "CHKJ2888.ejb21"; // The activation config: "{0}" can only exist for a destination type: "{1}" //$NON-NLS-1$
	/*package*/ static final String CHKJ2889 = "CHKJ2889.ejb21"; // The message destination is not set for bean: "{0}" //$NON-NLS-1$
	/*package*/ static final String CHKJ2890 = "CHKJ2890.ejb21"; // The message destination type "{0}" and destination type activation config property value "{1}" do not match  for bean: "{2}" //$NON-NLS-1$	
}
