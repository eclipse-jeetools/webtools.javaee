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
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;

public class ClassFigure extends Figure implements HandleBounds {

	protected static final Color COLOR_BACKGROUND = new Color(null, 235, 255, 220);
	public static final Font FONT_TITLE = new Font(null, new FontData("Helvetica", 7, SWT.BOLD));//$NON-NLS-1$

	private Label title;
	private Figure contentPane;

	public ClassFigure() {
		init();
	}

	public IFigure getContentPane() {
		return contentPane;
	}

	public Rectangle getHandleBounds() {
		Rectangle r = Rectangle.SINGLETON;
		r.setBounds(getBounds());
		r.crop(getInsets());
		r.expand(1, 1);
		return r;
	}

	private Label getLabel() {
		return title;
	}

	protected void init() {
		setBorder(new DropShadowBorder());
		setOpaque(true);
		setBackgroundColor(COLOR_BACKGROUND);
		setLayoutManager(new ToolbarLayout());

		title = new Label();
		title.setBorder(new TitleBorder());
		title.setFont(FONT_TITLE);

		contentPane = new Figure();
		contentPane.setBackgroundColor(ColorConstants.black);
		contentPane.setOpaque(true);
		ToolbarLayout tb = new ToolbarLayout();
		tb.setSpacing(1);
		contentPane.setLayoutManager(tb);

		ScrollPane scrollpane = new ScrollPane();
		scrollpane.setContents(contentPane);
		scrollpane.getViewport().setContentsTracksWidth(true);
		scrollpane.setHorizontalScrollBarVisibility(ScrollPane.NEVER);
		//scrollpane.setVerticalScrollBar(new PaletteScrollBar());
		scrollpane.setVerticalScrollBarVisibility(ScrollPane.NEVER);

		add(title);
		add(scrollpane);
	}

	protected void paintFigure(Graphics g) {
		Rectangle r = Rectangle.SINGLETON;
		r.setBounds(getBounds());
		r.crop(super.getInsets());
		g.fillRectangle(r);
	}

	public void setIcon(Image image) {
		getLabel().setIcon(image);
	}

	public void setTitle(String text) {
		getLabel().setText(text);
	}

	static class TitleBorder extends AbstractBorder {

		private static final Insets insets = new Insets(1, 2, 3, 2);

		public Insets getInsets(IFigure figure) {
			return insets;
		}

		public boolean isOpaque() {
			return true;
		}

		public void paint(IFigure f, Graphics g, Insets i) {
			Rectangle r = super.getPaintRectangle(f, i);
			g.drawLine(r.x, r.bottom() - 1, r.right(), r.bottom() - 1);
		}
	}

}