/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ToolbarLayout;

public class FeatureGroupFigure extends Figure {

	private static final Border BORDER = new MarginBorder(3, 2, 2, 1);
	boolean isHighlighted = false;

	{
		init();
	}

	protected void init() {
		setBorder(BORDER);
		ToolbarLayout tbl = new ToolbarLayout();
		tbl.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);
		tbl.setStretchMinorAxis(false);
		setLayoutManager(tbl);
		setBackgroundColor(ClassFigure.COLOR_BACKGROUND);
		setOpaque(true);
	}

	public void setHighlight(boolean light) {
		isHighlighted = light;
		repaint();
	}

	public void paint(Graphics graphics) {
		super.paint(graphics);
		if (isHighlighted == true)
			graphics.drawRectangle(getLocation().x, getLocation().y, getBounds().width - 1, getBounds().height - 1);
	}

}