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
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.PaletteEditPartFactory;
import org.eclipse.swt.graphics.Color;


public class RelationshipGEFWidgetFactory extends PaletteEditPartFactory {
	protected RelationshipDrawerPart drawer = null;

	protected EditPart createDrawerEditPart(EditPart parentEditPart, Object model) {
		drawer = new RelationshipDrawerPart((PaletteDrawer) model);
		return drawer;
	}

	protected EditPart createMainPaletteEditPart(EditPart parentEditPart, Object model) {
		RelationshipSliderPaletteEditPart spe = new RelationshipSliderPaletteEditPart((PaletteRoot) model);
		Color background = new Color(null, 235, 255, 220);
		spe.getFigure().setBackgroundColor(background);
		return spe;
	}

	public RelationshipDrawerPart getDrawer() {
		return drawer;
	}

}