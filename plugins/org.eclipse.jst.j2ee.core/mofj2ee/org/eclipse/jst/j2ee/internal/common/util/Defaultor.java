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
package org.eclipse.jst.j2ee.internal.common.util;

/**
 * Insert the type's description here.
 * Creation date: (6/15/2001 4:58:15 PM)
 * @author: Administrator
 */
public interface Defaultor {
/**
 * Returns true if this objects create @aDefaultable and it is still
 * currently default and not contained.
 * Creation date: (6/15/2001 4:59:28 PM)
 * @return boolean
 * @param aDefaultable org.eclipse.jst.j2ee.internal.common.util.Defaultable
 */
boolean isDefault(Defaultable aDefaultable);
}


