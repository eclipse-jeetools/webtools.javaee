/*******************************************************************************
 * Copyright (c) 2007, 2023 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.ejb.internal.operations.BusinessInterface;

public class BusinessInterfaceContentProvider implements IStructuredContentProvider {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	@Override
	public void dispose() {

	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof BusinessInterface) {
			return new BusinessInterface[] {(BusinessInterface) inputElement};
		} else if (inputElement instanceof ArrayList) {
			return ((List) inputElement).toArray();
		}
		return new Object[0];
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}
