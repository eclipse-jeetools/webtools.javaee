/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

public class DropShadowBorder extends AbstractBorder {

	public static final Image BR, BL, TR, R, B;

	private static int SHADOW_WIDTH;
	private static int SHADOW_HEIGHT;
	private static int SHADOW_LEFT;
	private static int SHADOW_TOP;
	private static Insets INSETS;

	private static Dimension PREF_SIZE = new Dimension(SHADOW_WIDTH + SHADOW_LEFT + 3, SHADOW_HEIGHT + SHADOW_TOP + 3);

	static {
		BR = (Image) AlphaImageHelper.createAlphaImage(DropShadowBorder.class, "icons/shadowBR.bmp");//$NON-NLS-1$
		TR = (Image) AlphaImageHelper.createAlphaImage(DropShadowBorder.class, "icons/shadowTR.bmp");//$NON-NLS-1$
		BL = (Image) AlphaImageHelper.createAlphaImage(DropShadowBorder.class, "icons/shadowBL.bmp");//$NON-NLS-1$
		R = (Image) AlphaImageHelper.createAlphaImage(DropShadowBorder.class, "icons/shadowR.bmp");//$NON-NLS-1$
		B = (Image) AlphaImageHelper.createAlphaImage(DropShadowBorder.class, "icons/shadowB.bmp");//$NON-NLS-1$
	}

	public DropShadowBorder(Color color) {

		SHADOW_WIDTH = BR.getBounds().width;
		SHADOW_HEIGHT = BR.getBounds().height;
		SHADOW_LEFT = BL.getBounds().width;
		SHADOW_TOP = TR.getBounds().height;
		INSETS = new Insets(1, 1, SHADOW_HEIGHT + 1, SHADOW_WIDTH + 1);
	}

	public DropShadowBorder() {
		this(ColorConstants.black);
	}

	public Insets getInsets(IFigure figure) {
		return INSETS;
	}

	public Dimension getPreferredSize(IFigure figure) {
		return PREF_SIZE;
	}

	public boolean isOpaque() {
		return false;
	}

	public void paint(IFigure figure, Graphics g, Insets i) {
		int width = SHADOW_WIDTH;
		int height = SHADOW_HEIGHT;

		Rectangle r = getPaintRectangle(figure, i);
		r.resize(-(width + 1), -(height + 1));
		g.drawRectangle(r);
		r.resize(1, 1);
		g.drawImage(BR, r.right(), r.bottom());
		g.drawImage(TR, r.right(), r.y);
		g.drawImage(BL, r.x, r.bottom());

		int stretch = r.width - SHADOW_LEFT;
		if (stretch > 0)
			g.drawImage(B, 0, 0, 7, height, r.x + SHADOW_LEFT, r.bottom(), stretch, height);

		stretch = r.height - SHADOW_TOP;
		if (stretch > 0)
			g.drawImage(R, 0, 0, width, 5, r.right(), r.y + SHADOW_TOP, width, stretch);
	}

}