/*******************************************************************************
 * Copyright (c) 2008, 2019 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Stefan Dimov, stefan.dimov@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckable;
import org.eclipse.jst.j2ee.internal.ui.DoubleCheckboxTableItem;

public class SecondCheckBoxStateChangedEvent extends CheckStateChangedEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4497186607413856367L;
	private DoubleCheckboxTableItem item = null;
	
	public SecondCheckBoxStateChangedEvent(ICheckable source, 
											Object element, 
											boolean state) {
		super(source, element, state);
	}	
	
	public void setTableItem(DoubleCheckboxTableItem itm) { 
		item = itm;
	}
	
	public DoubleCheckboxTableItem getTableItem() {
		return item;
	}
}
