/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.model.internal.validation;

import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;


public abstract interface IFieldType {
	public static final String NO_MESSAGE_PREFIX = ""; // For the two getMessageId_X methods, if the method never requires that particular message, return this message prefix instead //$NON-NLS-1$
	
	public long getId();
	
	public boolean isFieldType(EnterpriseBean bean, JavaClass clazz, Field field);
}
