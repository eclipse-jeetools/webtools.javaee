/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.commands;


/**
 * Insert the type's description here. Creation date: (9/5/2000 10:47:00 PM)
 * 
 * @author: Administrator
 */
public interface IPersistentAttributeCommand extends IPersistentFeatureCommand {
	int getArrayDimensions();

	java.lang.String getInitializer();

	java.lang.String getTypeName();

	boolean isArray();

	boolean isRemote();

	void setArrayDimensions(int newArrayDimensions);

	void setGenerateAccessors(boolean newGenerateAccessors);

	void setInitializer(java.lang.String newInitializer);

	void setIsReadOnly(boolean newIsReadOnly);

	void setRemote(boolean newRemote);

	void setLocal(boolean newLocal);

	void setTypeName(java.lang.String newTypeName);
}