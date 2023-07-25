/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.wizard;


import java.util.ArrayList;
import java.util.List;

/**
 * Insert the type's description here. Creation date: (3/19/2001 5:10:14 PM)
 * 
 * @author: Administrator
 */
public class TableObjects {
	public List tableObjectsList;

	/**
	 * EJBs constructor comment.
	 */
	public TableObjects() {
		super();
		tableObjectsList = new ArrayList();
	}

	public List getTableObjects() {
		return tableObjectsList;
	}

	protected void initList() {
		if (tableObjectsList == null)
			tableObjectsList = new ArrayList();

	}
}