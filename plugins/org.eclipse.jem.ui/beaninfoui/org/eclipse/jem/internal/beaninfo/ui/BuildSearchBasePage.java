/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.ui;
/*
 *  $RCSfile: BuildSearchBasePage.java,v $
 *  $Revision: 1.3 $  $Date: 2005/02/15 23:02:54 $ 
 */
import java.util.List;

public abstract class BuildSearchBasePage {
	
	public abstract List getSelection();
	public abstract void setSelection(List selection);
	
}
