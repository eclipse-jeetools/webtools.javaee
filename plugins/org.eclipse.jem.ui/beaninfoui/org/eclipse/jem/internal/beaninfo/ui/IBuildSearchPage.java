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
 *  $RCSfile: IBuildSearchPage.java,v $
 *  $Revision: 1.1 $  $Date: 2005/10/03 23:06:42 $ 
 */
package org.eclipse.jem.internal.beaninfo.ui;

import java.util.List;

public interface IBuildSearchPage {

	public List getSelection();
	public void setSelection(List selection);
	public void setBeaninfoEnabled(boolean enable);
}
