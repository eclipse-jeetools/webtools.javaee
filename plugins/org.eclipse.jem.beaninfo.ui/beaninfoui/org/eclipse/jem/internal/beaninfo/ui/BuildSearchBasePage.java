package org.eclipse.jem.internal.beaninfo.ui;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: BuildSearchBasePage.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:20:50 $ 
 */
import java.util.List;

public abstract class BuildSearchBasePage {
	
	public abstract List getSelection();
	public abstract void setSelection(List selection);
	
}
