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
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.swt.graphics.Color;

public class ConnectionLabel extends FeatureLabel {

	private static final Color COLOR_DEFAULT = new Color(null, 195, 195, 195);

	private static final Border BORDER = new CompoundBorder(new LineBorder(COLOR_DEFAULT), new MarginBorder(0, 1, 1, 1));

	{
		init();
	}

	public ConnectionLabel() {
	}

	protected void init() {
		//	setLabelAlignment(SOUTH);
		setBorder(BORDER);
	}

	protected void paintBorder(Graphics g) {
		if (getFlag(FLAG_SELECTED))
			return;
		super.paintBorder(g);
	}

	protected void paintFigure(Graphics g) {
		if (!getFlag(FLAG_SELECTED))
			g.fillRectangle(getBounds());
		super.paintFigure(g);
	}

}