/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.ui;
/*
 *  $RCSfile: BuildSearchBasePage.java,v $
 *  $Revision: 1.2 $  $Date: 2004/08/27 15:35:42 $ 
 */
import java.util.List;

public abstract class BuildSearchBasePage {
	
	public abstract List getSelection();
	public abstract void setSelection(List selection);
	
}
