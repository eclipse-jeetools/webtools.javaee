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

public interface ITypeConstants {
	public static long NO_SUPERTYPE = 0x0;
	
	public static long LOCAL_COMPONENT_INTERFACE = ClassUtility.supertype_counter; // 0x1 (1)
	public static long REMOTE_COMPONENT_INTERFACE = (ClassUtility.supertype_counter <<= 1); // 0x2 (2)
	
	public static long JAVAX_EJB_ENTITYBEAN = (ClassUtility.supertype_counter <<= 1); // 0x4 (4)
	public static long JAVAX_EJB_EJBLOCALHOME = (ClassUtility.supertype_counter <<= 1); //0x8 (8)
	public static long JAVAX_EJB_EJBLOCALOBJECT = (ClassUtility.supertype_counter <<= 1);//0x10 (16)
	public static long JAVAX_EJB_EJBHOME = (ClassUtility.supertype_counter <<= 1); //0x20 (32)
	public static long JAVAX_EJB_EJBOBJECT = (ClassUtility.supertype_counter <<= 1); //0x40 (64)
	
	public static long JAVA_LANG_OBJECT = (ClassUtility.supertype_counter <<= 1);//0x80 (128)
	
	public static long JAVAX_EJB_MESSAGEDRIVENBEAN = (ClassUtility.supertype_counter <<= 1); //0x100 (256)
	public static long JAVAX_JMS_MESSAGELISTENER = (ClassUtility.supertype_counter <<= 1);  //0x200 (512)
	
	public static long JAVAX_EJB_SESSIONBEAN = (ClassUtility.supertype_counter <<= 1); //0x400 (1024)
	public static long JAVAX_EJB_SESSIONSYNCHRONIZATION = (ClassUtility.supertype_counter <<= 1); // 0x800 (2048)
	
	// primitive types
	/*package*/ static final String VOID = "void"; //$NON-NLS-1$
	
	// class and interface types
	/*package*/ static final String CLASSNAME_JAVA_IO_IOEXCEPTION = "java.io.IOException"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVA_IO_SERIALIZABLE = "java.io.Serializable"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVA_LANG_OBJECT = "java.lang.Object"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVA_LANG_EXCEPTION = "java.lang.Exception"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVA_LANG_RUNTIMEEXCEPTION = "java.lang.RuntimeException"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVA_RMI_REMOTE = "java.rmi.Remote"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVA_RMI_REMOTEEXCEPTION = "java.rmi.RemoteException"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVA_UTIL_COLLECTION = "java.util.Collection"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVA_UTIL_ENUMERATION = "java.util.Enumeration"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVA_UTIL_SET = "java.util.Set"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVAX_EJB_CREATEEXCEPTION = "javax.ejb.CreateException"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVAX_EJB_ENTITYBEAN = "javax.ejb.EntityBean"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVAX_EJB_EJBEXCEPTION = "javax.ejb.EJBException"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVAX_EJB_EJBHOME = "javax.ejb.EJBHome"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVAX_EJB_EJBLOCALHOME = "javax.ejb.EJBLocalHome"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVAX_EJB_EJBOBJECT = "javax.ejb.EJBObject"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVAX_EJB_EJBLOCALOBJECT = "javax.ejb.EJBLocalObject"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVAX_EJB_ENTITYCONTEXT = "javax.ejb.EntityContext"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVAX_EJB_FINDEREXCEPTION = "javax.ejb.FinderException"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVAX_EJB_MESSAGEDRIVENBEAN = "javax.ejb.MessageDrivenBean"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVAX_EJB_OBJECTNOTFOUNDEXCEPTION = "javax.ejb.ObjectNotFoundException"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVAX_EJB_SESSIONBEAN = "javax.ejb.SessionBean"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVAX_EJB_SESSIONCONTEXT = "javax.ejb.SessionContext"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVAX_EJB_SESSIONSYNCHRONIZATION = "javax.ejb.SessionSynchronization"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVAX_JMS_MESSAGE = "javax.jms.Message"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVAX_JMS_MESSAGELISTENER = "javax.jms.MessageListener"; //$NON-NLS-1$
	/*package*/ static final String CLASSNAME_JAVAX_TRANSACTION_USERTRANSACTION = "javax.transaction.UserTransaction"; //$NON-NLS-1$
}
