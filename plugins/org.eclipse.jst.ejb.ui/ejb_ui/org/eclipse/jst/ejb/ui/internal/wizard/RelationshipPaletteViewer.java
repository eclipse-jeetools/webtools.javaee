/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Control;

public class RelationshipPaletteViewer extends PaletteViewer {
	RelationshipGEFWidgetFactory factory;

	public RelationshipPaletteViewer() {
		super();
		factory = new RelationshipGEFWidgetFactory();
		setEditPartFactory(factory);

	}

	public void setControl(Control control) {
		super.setControl(control);
	}

	public void reveal(EditPart part) {
		super.reveal(part);
		addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				Object obj = ((IStructuredSelection) event.getSelection()).getFirstElement();
				if (obj instanceof RelationshipDrawerPart)
					return;
			}
		});
	}

}