/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Apr 26, 2004
 * 
 * @todo To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.internal.ui.palette.editparts.SliderPaletteEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.PaletteViewer;

public class RelationshipSliderPaletteEditPart extends SliderPaletteEditPart {
	private DrawerAnimationController controller;

	public RelationshipSliderPaletteEditPart(PaletteRoot paletteRoot) {
		super(paletteRoot);
	}

	public IFigure createFigure() {
		Figure aFigure = new Figure() {
			public void paint(Graphics graphics) {
				Rectangle r = getBounds();
				graphics.drawLine(r.x, r.y + 30, r.x + 20, r.y + 30);
				super.paint(graphics);
			}
		};
		aFigure.setOpaque(true);
		return aFigure;
	}

	protected void registerVisuals() {
		super.registerVisuals();
		controller = new DrawerAnimationController(((PaletteViewer) getViewer()).getPaletteViewerPreferences());
		getViewer().getEditPartRegistry().put(DrawerAnimationController.class, controller);
		ToolbarLayout layout = new PaletteToolbarLayout(controller);
		getFigure().setLayoutManager(layout);
	}

}