/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb;

import java.util.List;





/**
 * Insert the type's description here.
 * Creation date: (11/6/2000 11:40:16 AM)
 * @author: Administrator
 * @since 1.0 */
public interface IRoleShapeStrategy {
	String ATTRIBUTE_NAME_JOINER = "_"; //$NON-NLS-1$
	String ROLE_GET_PREFIX = "get"; //$NON-NLS-1$
	String ROLE_SET_PREFIX = "set"; //$NON-NLS-1$
boolean isFlat();
boolean isRound();
void reconcileAttributes(List roleAttributes) ;
boolean usesAttributeNamed(String attributeName) ;
}


