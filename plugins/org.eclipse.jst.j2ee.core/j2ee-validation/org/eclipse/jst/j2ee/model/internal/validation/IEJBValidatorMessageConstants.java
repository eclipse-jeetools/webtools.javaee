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
 * This interface holds the message constants that are common across
 * specifications. (e.g. internal error messages, checks that are the same.)
 */
public interface IEJBValidatorMessageConstants {
	// Constants used in message ids
	static final String ON_BASE = ".i"; // String to be registered against a base type, not the JavaClass being validated. (Inherited method.) //$NON-NLS-1$
	static final String ON_THIS = ".m"; // String to be registered against the JavaClass being validated. (Method impl on JavaClass itself.) //$NON-NLS-1$

	// Internal errors and such
	static final String CHKJ2041 = "CHKJ2041"; // unnamed package (Java Language Specification) //$NON-NLS-1$
	static final String CHKJ2433 = "CHKJ2433"; // cannot reflect //$NON-NLS-1$
	static final String CHKJ2456 = "CHKJ2456"; // Should throw X or there may be compile errors in the deploy code. //$NON-NLS-1$
	static final String CHKJ2852 = "CHKJ2852"; // Throwable caught while validating ejb-jar.xml //$NON-NLS-1$
	static final String CHKJ2900 = "CHKJ2900"; // Internal error while running EJB validator //$NON-NLS-1$
	static final String CHKJ2905 = "CHKJ2905"; // EJB Validator could not run because ejb-jar.xml could not be loaded. //$NON-NLS-1$
	static final String CHKJ2907 = "CHKJ2907"; // Type X, or one of its supertypes, cannot be reflected. Check the classpath. //$NON-NLS-1$

	// Specification rules
	static final String CHKJ2003E = "CHKJ2003E"; // implement javax.ejb.SessionSynchronization (can be error or info depending on context) //$NON-NLS-1$
	static final String CHKJ2003I = "CHKJ2003I"; // implement javax.ejb.SessionSynchronization (can be error or info depending on context) //$NON-NLS-1$

	static final String CHKJ2014 = "CHKJ2014"; // class must be, or must not be, abstract //$NON-NLS-1$
	static final String CHKJ2015 = "CHKJ2015"; // class must be, or must not be, final //$NON-NLS-1$
	static final String CHKJ2017 = "CHKJ2017"; // class/interface must extend/implement X //$NON-NLS-1$
	static final String CHKJ2019 = "CHKJ2019"; // key class must be serializable/a legal value type in RMI-IIOP //$NON-NLS-1$

	static final String CHKJ2022 = "CHKJ2022"; // class must be public //$NON-NLS-1$
	static final String CHKJ2023 = "CHKJ2023"; // a matching method (for this component method) must exist on the bean class //$NON-NLS-1$

	static final String CHKJ2040 = "CHKJ2040"; // "this" must not be passed as a method argument or result //$NON-NLS-1$

	static final String CHKJ2103 = "CHKJ2103"; // In component inheritance, the bean class must inherit X. //$NON-NLS-1$
	static final String CHKJ2104 = "CHKJ2104"; // In component inheritance, the home class must not inherit X. //$NON-NLS-1$
	static final String CHKJ2105 = "CHKJ2105"; // In component inheritance, the component interface must extend X. //$NON-NLS-1$
	static final String CHKJ2106 = "CHKJ2106"; // In component inheritance, the bean must use X as its primary key. //$NON-NLS-1$

	static final String CHKJ2205 = "CHKJ2205"; // primary key field X must be public //$NON-NLS-1$
	static final String CHKJ2206 = "CHKJ2206"; // primary key field X must be a <cmp-field> (and in EJB 1.1, also a field of the bean class) //$NON-NLS-1$

	static final String CHKJ2402 = "CHKJ2402"; // The create method must return the remote interface type //$NON-NLS-1$
	static final String CHKJ2403 = "CHKJ2403"; // The finder method must return X or a collection thereof. //$NON-NLS-1$
	static final String CHKJ2407 = "CHKJ2407"; // The ejbFind method must return X or a collection thereof. //$NON-NLS-1$

	static final String CHKJ2411 = "CHKJ2411"; // The name of the method must not start with "ejb". //$NON-NLS-1$

	static final String CHKJ2801 = "CHKJ2801"; // missing <ejb-name> //$NON-NLS-1$
	static final String CHKJ2802_NAMED = "CHKJ2802.named";// <ejb-class> cannot be reflected //$NON-NLS-1$
	static final String CHKJ2802_UNNAMED = "CHKJ2802.unnamed";// unknown <ejb-class> //$NON-NLS-1$
	static final String CHKJ2803_NAMED = "CHKJ2803.named";// <home> cannot be reflected //$NON-NLS-1$
	static final String CHKJ2803_UNNAMED = "CHKJ2803.unnamed";// unknown <home> //$NON-NLS-1$
	static final String CHKJ2804_NAMED = "CHKJ2804.named";// <remote> cannot be reflected //$NON-NLS-1$
	static final String CHKJ2804_UNNAMED = "CHKJ2804.unnamed";// unknown <remote> //$NON-NLS-1$
	static final String CHKJ2806 = "CHKJ2806";// <reentrant> must be either "True" or "False" //$NON-NLS-1$
	static final String CHKJ2807 = "CHKJ2807"; // <session-type> must be either "Stateful" or "Stateless" //$NON-NLS-1$
	static final String CHKJ2808 = "CHKJ2808"; // <transaction-type> must be either "Bean" or "Container" //$NON-NLS-1$
	static final String CHKJ2809 = "CHKJ2809"; // <persistence-type> must be either "Bean" or "Container" //$NON-NLS-1$

	static final String CHKJ2810_NAMED = "CHKJ2810.named";// <prim-key-class> cannot be reflected //$NON-NLS-1$
	static final String CHKJ2810_UNNAMED = "CHKJ2810.unnamed";// unknown <prim-key-class> //$NON-NLS-1$
	static final String CHKJ2811_NAMED = "CHKJ2811.named";// <field-name> must exist on the bean class //$NON-NLS-1$
	static final String CHKJ2811_UNNAMED = "CHKJ2811.unnamed";// <field-name> must exist //$NON-NLS-1$
	static final String CHKJ2812 = "CHKJ2812"; // Bean class must specify at least one of its fields as a <cmp-field> //$NON-NLS-1$
	static final String CHKJ2814 = "CHKJ2814"; // <method>'s <ejb-name> should specify a known enterprise bean //$NON-NLS-1$

	static final String CHKJ2820 = "CHKJ2820"; // <security-role-ref> must be unique. //$NON-NLS-1$
	static final String CHKJ2822 = "CHKJ2822"; // <security-role-ref> must define a <role-name> //$NON-NLS-1$
	static final String CHKJ2823 = "CHKJ2823"; // <security-role-ref> should define a <role-link> //$NON-NLS-1$
	static final String CHKJ2824 = "CHKJ2824"; // <security-role-ref> must identify a known <security-role> //$NON-NLS-1$
	static final String CHKJ2825 = "CHKJ2825"; // <security-role> should define the <role-name> element. //$NON-NLS-1$
	static final String CHKJ2826 = "CHKJ2826"; // <security-role> name should be unique. //$NON-NLS-1$
	static final String CHKJ2827 = "CHKJ2827"; // Cannot use a <role-link> without <security-role> elements. //$NON-NLS-1$
	static final String CHKJ2828 = "CHKJ2828"; // Cannot use a <primkey-field> element without a primitive primary key. //$NON-NLS-1$
	static final String CHKJ2829 = "CHKJ2829"; // Every field in the primary key must exist on the bean class. //$NON-NLS-1$

	static final String CHKJ2830 = "CHKJ2830"; // The type of the field cannot be reflected. //$NON-NLS-1$
	static final String CHKJ2831 = "CHKJ2831"; // X must be declared as a <cmp-field> element. //$NON-NLS-1$
	static final String CHKJ2832 = "CHKJ2832"; // <ejb-ref>'s home cannot be found in the EJB module. //$NON-NLS-1$
	static final String CHKJ2833 = "CHKJ2833"; // <ejb-ref>'s remote cannot be found in the EJB module. //$NON-NLS-1$
	static final String CHKJ2834 = "CHKJ2834"; // <ejb-ref>'s bean cannot be found in the EJB module. //$NON-NLS-1$
	static final String CHKJ2835 = "CHKJ2835"; // Bean X is a Y type. Update the <ejb-ref-type> element. //$NON-NLS-1$
	static final String CHKJ2836 = "CHKJ2836"; // Bean X uses home Y. Update the <ejb-ref> element. //$NON-NLS-1$
	static final String CHKJ2837 = "CHKJ2837"; // Bean X uses remote Y. Update the <ejb-ref> element. //$NON-NLS-1$
	static final String CHKJ2838 = "CHKJ2838"; // It is recommended that all references to enterprise beans be organized in the "ejb" subcontext. //$NON-NLS-1$
	static final String CHKJ2839 = "CHKJ2839"; // <env-entry-name> of the <env-entry> element must exist. //$NON-NLS-1$

	static final String CHKJ2840 = "CHKJ2840"; // <env-entry-type> of the <env-entry> element must exist. //$NON-NLS-1$
	static final String CHKJ2841 = "CHKJ2841"; // The X name, in the <env-entry> element, must be unique in the bean. //$NON-NLS-1$
	static final String CHKJ2842 = "CHKJ2842"; // <method-params> is ignored when <method-name> is "*" //$NON-NLS-1$
	static final String CHKJ2843 = "CHKJ2843"; // <method> X must exist on bean Y. //$NON-NLS-1$
	static final String CHKJ2844 = "CHKJ2844"; // At least one <method> should exist in a <method-permission>. //$NON-NLS-1$
	static final String CHKJ2845 = "CHKJ2845"; // At least one <role-name> should exist in a <method-permission>. //$NON-NLS-1$
	static final String CHKJ2846 = "CHKJ2846"; // No methods can be found for this <method-permission> //$NON-NLS-1$
	static final String CHKJ2847 = "CHKJ2847"; // No methods can be found for this <container-transaction> //$NON-NLS-1$
	static final String CHKJ2849 = "CHKJ2849"; // Cannot validate component inheritance of X because Y cannot be reflected. //$NON-NLS-1$
	
	static final String CHKJ2875 = "CHKJ2875"; // EJB Client JAR must exist and be in the same EAR as this EJB JAR. //$NON-NLS-1$

	static final String CHKJ2850 = "CHKJ2850"; // Cannot validate <method> because X, or one of its supertypes, cannot be reflected. //$NON-NLS-1$

	static final String CHKJ2895 = "CHKJ2895"; // duplicate <ejb-name> //$NON-NLS-1$
}
