/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Apr 27, 2004
 * 
 * @todo To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.jst.ejb.ui.internal.wizard;

import java.util.ArrayList;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;

public class RelationshipPolyLine extends PolylineConnection implements IMenuListener, MouseListener, MouseMotionListener {
	private boolean paintLineBoolean = false;

	private Color blue = Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);

	private Color black = Display.getCurrent().getSystemColor(SWT.COLOR_BLACK);

	public PolylineDecoration getPolyLeftDec() {
		return polyLeftDec;
	}

	public void setPolyLeftDec(PolylineDecoration polyLeftDec) {
		this.polyLeftDec = polyLeftDec;
	}

	public PolylineDecoration getPolyRightDec() {
		return polyRightDec;
	}

	public void setPolyRightDec(PolylineDecoration polyRightDec) {
		this.polyRightDec = polyRightDec;
	}

	private PolylineDecoration polyRightDec;

	private PolylineDecoration polyLeftDec;

	protected Cursor hand = new Cursor(Display.getCurrent(), SWT.CURSOR_HAND);

	protected Cursor arrow = new Cursor(Display.getCurrent(), SWT.CURSOR_ARROW);

	public static final int RIGHT_END = 3;

	public static final int LEFT_END = 4;

	private ArrayList listeners = new ArrayList(3);

	public void addRelationshipMouseListner(MouseListener mouseListener) {

		listeners.add(mouseListener);
	}

	public void mousePressed(MouseEvent me) {
		me.x = getEndInt(me.getLocation());
		notifyRelationshipMouseListeners(me);
	}

	public void mouseReleased(MouseEvent me) {
	}

	public void mouseDoubleClicked(MouseEvent me) {
	}

	public void mouseDragged(MouseEvent me) {
	}

	Point currentPoint;

	public void mouseEntered(MouseEvent me) {
		currentPoint = me.getLocation();
		me.x = getEndInt(me.getLocation());
		notifyRelationshipMouseListeners(me);
		setCursor(hand);
		paintLineBoolean = true;
		repaint();
		if (currentPoint == null)
			return;
		if (getEndInt(currentPoint) == RIGHT_END) {
			getPolyLeftDec().setForegroundColor(blue);
			getPolyRightDec().setForegroundColor(black);
		} else {
			getPolyLeftDec().setForegroundColor(black);
			getPolyRightDec().setForegroundColor(blue);
		}
	}

	public int getEndInt(Point point) {
		int start_x = (getStart().x + getEnd().x) / 2;
		if (point.x < start_x) {
			return RIGHT_END;
		}
		return LEFT_END;
	}

	public void mouseExited(MouseEvent me) {
		setCursor(arrow);
		// paintLineBoolean = false;

		if (currentPoint == null)
			return;
		if (getEndInt(currentPoint) == RIGHT_END) {
			getPolyLeftDec().setForegroundColor(blue);
			getPolyRightDec().setForegroundColor(black);
		} else {
			getPolyLeftDec().setForegroundColor(black);
			getPolyRightDec().setForegroundColor(blue);
		}
		// repaint();
	}

	public void mouseHover(MouseEvent me) {
	}

	public void mouseMoved(MouseEvent me) {
	}

	public RelationshipPolyLine(Control control) {
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
		lineWidth = 2;
		setOutline(true);
		//createContextMenuFor(control);
	}

	protected void createContextMenuFor(Control control) {
		MenuManager contextMenu = new MenuManager("LABEL", "id"); //$NON-NLS-2$//$NON-NLS-1$
		Menu menu = contextMenu.createContextMenu(control);
		contextMenu.setRemoveAllWhenShown(true);
		contextMenu.addMenuListener(this);
		control.setMenu(menu);

	}

	protected void outlineShape(Graphics g) {
		//  g.setBackgroundColor(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
		super.outlineShape(g);
	}

	protected void paintClientArea(Graphics graphics) {
		super.paintClientArea(graphics);
		if (paintLineBoolean) {
			graphics.setForegroundColor(blue);
		}
		/*
		 * if (getPolyRightDec() != null) getPolyRightDec().setForegroundColor(blue); if
		 * (getPolyLeftDec() != null) getPolyLeftDec().setForegroundColor(blue);
		 */
		/*
		 * else { if (getPolyRightDec() != null) { getPolyRightDec().setForegroundColor(black);
		 * getPolyRightDec().repaint(); } if (getPolyLeftDec() != null) {
		 * getPolyLeftDec().setForegroundColor(black); getPolyLeftDec().repaint(); }
		 */
		//graphics.setForegroundColor(black);
		/*  */
		//  }
		//      }
		if (currentPoint == null)
			return;
		if (getEndInt(currentPoint) == RIGHT_END)
			graphics.drawLine(getStart().x, getStart().y, getEnd().x / 2 + 80, getEnd().y);
		else
			graphics.drawLine(getEnd().x / 2 + 80, getStart().y, getEnd().x, getEnd().y);

	}

	protected void notifyRelationshipMouseListeners(MouseEvent me) {
		for (int i = 0; i < listeners.size(); i++) {
			((MouseListener) listeners.get(i)).mousePressed(me);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
	 */
	public void menuAboutToShow(IMenuManager manager) {
		MenuManager contextMenu = new MenuManager(EJBFiguresResourceHandler.getResourceString("RelationshipPolyLine_UI_0"), EJBFiguresResourceHandler.getResourceString("RelationshipPolyLine_UI_1")); //$NON-NLS-1$ //$NON-NLS-2$
		MenuManager contextCmrMenu = new MenuManager(EJBFiguresResourceHandler.getResourceString("RelationshipPolyLine_UI_2"), EJBFiguresResourceHandler.getResourceString("RelationshipPolyLine_UI_3")); //$NON-NLS-1$ //$NON-NLS-2$
		contextMenu.add(new RelationshipAction(EJBFiguresResourceHandler.getResourceString("RelationshipPolyLine_UI_4"), 0, this)); //$NON-NLS-1$
		contextMenu.add(new RelationshipAction(EJBFiguresResourceHandler.getResourceString("RelationshipPolyLine_UI_5"), 0, this)); //$NON-NLS-1$
		contextCmrMenu.add(new RelationshipAction(EJBFiguresResourceHandler.getResourceString("RelationshipPolyLine_UI_6"), 0, this)); //$NON-NLS-1$
		contextCmrMenu.add(new RelationshipAction(EJBFiguresResourceHandler.getResourceString("RelationshipPolyLine_UI_7"), 0, this)); //$NON-NLS-1$

		manager.add(contextMenu);
		manager.add(contextCmrMenu);

	}

}