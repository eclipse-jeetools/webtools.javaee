/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IBeanTypeExpressionProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2005/05/11 19:01:12 $ 
 */
package org.eclipse.jem.internal.proxy.core;
 

/**
 * An internal interface for the registries to create the appropriate beantype expression proxy.
 * Not to be implemented or referenced by customers.
 * @since 1.1.0
 */
public interface IBeanTypeExpressionProxy extends IProxyBeanType {

	/**
	 * Allow the expression to set the type name.
	 * @param typeName
	 * 
	 * @since 1.1.0
	 */
	public void setTypeName(String typeName);
}
