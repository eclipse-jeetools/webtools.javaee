/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on May 25, 2004
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Toggle;
import org.eclipse.draw2d.ToggleButton;
import org.eclipse.gef.internal.ui.palette.editparts.DrawerFigure;
import org.eclipse.swt.widgets.Control;

/**
 * @author blancett
 * 
 * @todo To change the template for this generated type comment go to Window - Preferences - Java -
 *       Code Generation - Code and Comments
 */
public class RelationshipDrawFigure extends DrawerFigure {
	private boolean showPin = true;


	public RelationshipDrawFigure(Control control) {
		super(control);
	}

	public boolean isPinShowing() {
		return showPin;
	}

	public Toggle toggle = null;
	public ToggleButton pinFigure = null;
	protected boolean addListener = true;

	protected void handleExpandStateChanged() {
		super.handleExpandStateChanged();
		toggle = (Toggle) getChildren().get(0);
		IFigure fig2 = (IFigure) toggle.getChildren().get(0);
		pinFigure = (ToggleButton) fig2.getChildren().get(0);
		pinFigure.setVisible(true);
		if (addListener == true) {
			pinFigure.addChangeListener(RelationshipDrawerPart.getToggleListener());
			addListener = false;
		}

	}



	public boolean isShowPin() {
		return showPin;
	}

	public void setShowPin(boolean showPin) {
		this.showPin = showPin;
	}
}