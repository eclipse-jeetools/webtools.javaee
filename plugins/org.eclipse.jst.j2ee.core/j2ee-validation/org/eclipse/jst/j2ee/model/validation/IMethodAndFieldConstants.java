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
package org.eclipse.jst.j2ee.model.validation;

public interface IMethodAndFieldConstants {
	public static final long NO_METHODS = 0x0;
	public static final long EXCLUDED_METHOD = ValidationRuleUtility.method_and_field_counter; // 0x1 (1)
	public static final long EXCLUDED_FIELD = (ValidationRuleUtility.method_and_field_counter <<= 1); // 0x2 (2)
	public static final long OTHER_FIELD = (ValidationRuleUtility.method_and_field_counter <<= 1); // 0x4 (4)
	
	public static final long ACCESSOR = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x8 (8)
	public static final long BUSINESS = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x10 (16)

	public static final long CLINIT = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x20 (32)
	public static final long CONSTRUCTOR = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x40 (64)
	public static final long CONSTRUCTOR_NOPARM =  CONSTRUCTOR | (ValidationRuleUtility.method_and_field_counter <<= 1); // 0x40|0x80 (192)
	public static final long CREATE = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x100 (256)
	public static final long CREATE_NOPARM = CREATE | (ValidationRuleUtility.method_and_field_counter <<= 1);  //0x100|0x200 (768)
	
	public static final long EJBACTIVATE_NOPARM = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x400 (1024)
	public static final long EJBCREATE = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x800 (2048)
	public static final long EJBCREATE_NOPARM = EJBCREATE | (ValidationRuleUtility.method_and_field_counter <<= 1);//0x800|0x1000 (6144)
	public static final long EJBFIND = (ValidationRuleUtility.method_and_field_counter <<= 1);  //0x2000 (8192)
	public static final long EJBFINDBYPRIMARYKEY = EJBFIND | (ValidationRuleUtility.method_and_field_counter <<= 1); //0x2000|0x4000 (24576)
	public static final long EJBHOME = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x8000 (32768)
	public static final long EJBLOAD_NOPARM = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x10000 (65536)
	public static final long EJBPASSIVATE_NOPARM = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x20000 (131,072)
	public static final long EJBPOSTCREATE = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x40000 (262,144)
	public static final long EJBREMOVE_NOPARM = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x80000 (524,288)
	public static final long EJBSELECT = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x100000 (1,048,576)
	public static final long EJBSTORE_NOPARM = (ValidationRuleUtility.method_and_field_counter <<= 1);//0x200000 (2,097,152)
	public static final long EQUALS = (ValidationRuleUtility.method_and_field_counter <<= 1);  //0x400000 (4,194,304)
	
	public static final long FIND = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x800000 (8,388,608)
	public static final long FINDBYPRIMARYKEY = FIND | (ValidationRuleUtility.method_and_field_counter <<= 1); //0x1000000 (25,165,824)
	public static final long FINALIZE_NOPARM = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x2000000 (33,554,432)
	
	public static final long HASHCODE_NOPARM = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x4000000 (67,108,864)
	public static final long HELPER = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x8000000 (134,217,728)
	public static final long HOME = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x10000000 (268,435,456)
	
	public static final long ONMESSAGE = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x20000000 (536,870,912)
	
	public static final long SERIALVERSIONUID = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x40000000 (1,073,741,824)
	public static final long SETSESSIONCONTEXT = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x80000000 (2,147,483,648)
	public static final long SETENTITYCONTEXT = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x100000000 (4,294,967,296)
	
	public static final long UNSETENTITYCONTEXT_NOPARM = (ValidationRuleUtility.method_and_field_counter <<= 1); //0x200000000 (8,589,934,592)
	

	// prefixes of some method names
	/*package*/ static final String PREFIX_C = "c"; //$NON-NLS-1$
	/*package*/ static final String PREFIX_EJB = "ejb"; //$NON-NLS-1$
	/*package*/ static final String PREFIX_EJBC = "ejbC"; //$NON-NLS-1$
	/*package*/ static final String PREFIX_EJBF = "ejbF"; //$NON-NLS-1$
	/*package*/ static final String PREFIX_EJBFIND = "ejbFind"; //$NON-NLS-1$
	/*package*/ static final String PREFIX_EJBHOME = "ejbHome"; //$NON-NLS-1$
	/*package*/ static final String PREFIX_EJBSELECT = "ejbSelect"; //$NON-NLS-1$
	/*package*/ static final String PREFIX_F = "f"; //$NON-NLS-1$
	/*package*/ static final String PREFIX_FIND = "find"; //$NON-NLS-1$
	/*package*/ static final String PREFIX_GET = "get"; //$NON-NLS-1$
	/*package*/ static final String PREFIX_SELECT = "select"; //$NON-NLS-1$
	/*package*/ static final String PREFIX_SET = "set"; //$NON-NLS-1$

	// method names	
	/*package*/ static final String METHODNAME_CREATE = "create"; //$NON-NLS-1$
	/*package*/ static final String METHODNAME_EJBACTIVATE = "ejbActivate"; //$NON-NLS-1$
	/*package*/ static final String METHODNAME_EJBCREATE = "ejbCreate"; //$NON-NLS-1$
	/*package*/ static final String METHODNAME_EJBFINDBYPRIMARYKEY = "ejbFindByPrimaryKey"; //$NON-NLS-1$
	/*package*/ static final String METHODNAME_EJBLOAD = "ejbLoad"; //$NON-NLS-1$
	/*package*/ static final String METHODNAME_EJBPASSIVATE = "ejbPassivate"; //$NON-NLS-1$
	/*package*/ static final String METHODNAME_EJBPOSTCREATE = "ejbPostCreate"; //$NON-NLS-1$
	/*package*/ static final String METHODNAME_EJBREMOVE = "ejbRemove"; //$NON-NLS-1$
	/*package*/ static final String METHODNAME_EJBSTORE = "ejbStore"; //$NON-NLS-1$
	/*package*/ static final String METHODNAME_EQUALS = "equals"; //$NON-NLS-1$
	/*package*/ static final String METHODNAME_FINALIZE = "finalize"; //$NON-NLS-1$
	/*package*/ static final String METHODNAME_FINDBYPRIMARYKEY = "findByPrimaryKey"; //$NON-NLS-1$
	/*package*/ static final String METHODNAME_HASHCODE = "hashCode"; //$NON-NLS-1$
	/*package*/ static final String METHODNAME_ONMESSAGE = "onMessage"; //$NON-NLS-1$
	/*package*/ static final String METHODNAME_REMOVE = "remove"; //$NON-NLS-1$
	/*package*/ static final String METHODNAME_SETSESSIONCONTEXT = "setSessionContext"; //$NON-NLS-1$
	/*package*/ static final String METHODNAME_SETENTITYCONTEXT = "setEntityContext"; //$NON-NLS-1$
	/*package*/ static final String METHODNAME_UNSETENTITYCONTEXT = "unsetEntityContext"; //$NON-NLS-1$
	
	// field names
	/*package*/ static final String FIELDNAME_SERIALVERSIONUID = "serialVersionUID"; //$NON-NLS-1$
	
	// For substitution into strings
	/*package*/ static final String METHODSIGNATURE_EQUALS = "equals(java.lang.Object)"; //$NON-NLS-1$
	/*package*/ static final String METHODSIGNATURE_HASHCODE = "hashCode()"; //$NON-NLS-1$
}
