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


/**
 * This interface contains the symbolic names of the models which the
 * EJBValidator needs, and the IHelper that is used in the EJBValidator's
 * validate method must support these symbolic names.
 */
public interface EJBValidatorModelEnum {
	   public final static String EJB_MODEL = "EJB_MODEL"; // return an EJBJar object //$NON-NLS-1$
	   public final static String EJB_FILE = "EJB_FILE"; // return a ModuleFile object (either EJBJarFile or EARFile) //$NON-NLS-1$
	   public final static String EJB = "EJB"; // Given a JavaClass instance, return a java.util.List of EnterpriseBean instances that use that JavaClass as part of the bean. //$NON-NLS-1$
	   public final static String CHILDREN = "children"; // Given a JavaClass instance, return a JavaClass[] of the JavaClasses that inherit or extend the instance. //$NON-NLS-1$
	   public final static String EJB_BINDING = "EJB_BINDING"; // return the EJBJarBinding for the EJBJar //$NON-NLS-1$
	   public final static String EJB_CLIENTJAR = "EJB_CLIENTJAR"; // If the EJB uses a client JAR, given the name of the JAR, this method returns a Boolean.TRUE if the client JAR can be found and loaded successfully. If it can't be found and loaded, return Boolean.FALSE. //$NON-NLS-1$
	   public final static String REMOVE_OLD_MESSAGES = "REMOVE_OLD_MESSAGES"; // Remove all Messages registered against the given Objects in the array. //$NON-NLS-1$
}

