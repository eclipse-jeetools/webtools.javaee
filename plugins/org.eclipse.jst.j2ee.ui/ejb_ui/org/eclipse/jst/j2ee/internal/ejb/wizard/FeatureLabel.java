/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.wizard;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;

public class FeatureLabel extends Label {

	protected static final int FLAG_SELECTED = Figure.MAX_FLAG << 1;
	protected static final int FLAG_PRIMARY = Figure.MAX_FLAG << 2;

	public FeatureLabel() {
	}

	protected void paintFigure(Graphics g) {
		if (getFlag(FLAG_SELECTED)) {
			g.setForegroundColor(ColorConstants.white);
			g.setBackgroundColor(ColorConstants.menuBackgroundSelected);
			Rectangle r = getTextBounds();
			Rectangle aBounds = getBounds();
			g.fillRectangle(r.x - 1, aBounds.y, r.width + 1, aBounds.height);
			if (getFlag(FLAG_PRIMARY)) {
				g.setXORMode(true);
				g.drawFocus(r.x - 1, aBounds.y, r.width, aBounds.height - 1);
				g.setXORMode(false);
			}
		}
		super.paintFigure(g);
	}

	public void setSelected(boolean value) {
		setFlag(FLAG_SELECTED, value);
		repaint();
	}

	public void setPrimary(boolean value) {
		setFlag(FLAG_PRIMARY, value);
		repaint();
	}

}