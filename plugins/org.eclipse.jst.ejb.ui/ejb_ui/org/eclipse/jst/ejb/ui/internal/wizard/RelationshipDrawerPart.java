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
 * Created on Apr 26, 2004
 * 
 * @todo To change the template for this generated file go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.draw2d.ButtonModel;
import org.eclipse.draw2d.ChangeEvent;
import org.eclipse.draw2d.ChangeListener;
import org.eclipse.draw2d.FocusEvent;
import org.eclipse.draw2d.FocusListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Toggle;
import org.eclipse.draw2d.ToggleButton;
import org.eclipse.gef.internal.InternalImages;
import org.eclipse.gef.internal.ui.palette.editparts.DrawerEditPart;
import org.eclipse.gef.internal.ui.palette.editparts.RaisedBorder;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;


/**
 * @author blancett
 * 
 * @todo To change the template for this generated type comment go to Window - Preferences - Java -
 *       Code Generation - Code and Comments
 */
public class RelationshipDrawerPart extends DrawerEditPart {
	protected static ToggleListener toggleListener;

	public RelationshipDrawerPart(PaletteDrawer drawer) {
		super(drawer);
		createToggleListner();
	}

	public class ToggleListener implements ChangeListener {
		private boolean releaseFlag = true;

		public ToggleListener() {
			super();
		}

		public boolean internalChange = false;

		public void handleStateChanged(ChangeEvent event) {
			if (event.getPropertyName().equals(ButtonModel.SELECTED_PROPERTY) && event.getSource() != pin && !getAnimationController().isAnimationInProgress()) {
				getAnimationController().animate(RelationshipDrawerPart.this);

			}
			if (event.getPropertyName().equals(ButtonModel.PRESSED_PROPERTY) && event.getSource() == pin && !getAnimationController().isAnimationInProgress()) {
				if (pin != null && releaseFlag == true) {
					toggle.setSelected(!fig.isExpanded());
					releaseFlag = false;
				} else
					releaseFlag = true;
			}
			pin.setSelected(fig.isExpanded());
		}
	}

	private DrawerAnimationController getAnimationController() {
		return (DrawerAnimationController) getViewer().getEditPartRegistry().get(DrawerAnimationController.class);
	}

	protected RelationshipDrawFigure fig;
	protected Toggle toggle;
	protected ToggleButton pin;

	public void createFig() {
		fig = new RelationshipDrawFigure(getViewer().getControl());
	}

	public IFigure createFigure() {
		createFig();
		fig.setExpanded(true);
		fig.setPinned(getDrawer().isInitiallyPinned());
		fig.getCollapseToggle().addChangeListener(new ToggleListener());
		fig.getCollapseToggle().setRequestFocusEnabled(true);
		toggle = fig.toggle;
		pin = fig.pinFigure;
		fig.getCollapseToggle().addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent fe) {
				getViewer().select(RelationshipDrawerPart.this);
			}

			public void focusLost(FocusEvent fe) {
			}
		});
		return fig;
	}

	protected void register() {
		super.register();
		getAnimationController().addDrawer(this);
	}

	protected void unregister() {
		getAnimationController().removeDrawer(this);
		toggleListener = null;
		super.unregister();
	}

	public ToggleButton button;

	protected void refreshVisuals() {
		getDrawerFigure().setToolTip(createToolTip());
		ImageDescriptor img = getPaletteEntry().getSmallIcon();
		if (img == null) {
			img = InternalImages.DESC_FOLDER_OPEN;
		}
		setImageDescriptor(img);
		getDrawerFigure().setTitle(getPaletteEntry().getLabel());
		getDrawerFigure().setLayoutMode(getPreferenceSource().getLayoutSetting());
		//boolean showPin = getPreferenceSource().getAutoCollapseSetting() ==
		// PaletteViewerPreferences.COLLAPSE_AS_NEEDED;
		getDrawerFigure().showPin(true);
		IFigure afig = (IFigure) getDrawerFigure().getChildren().get(0);
		IFigure fig2 = (IFigure) afig.getChildren().get(0);
		button = (ToggleButton) fig2.getChildren().get(0);
		button.setVisible(true);
		button.setBackgroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		RaisedBorder border = new RaisedBorder();
		button.setBorder(border);
		ImageFigure imageFig = (ImageFigure) button.getChildren().get(0);
		imageFig.setImage(J2EEUIPlugin.getDefault().getImage("adown")); //$NON-NLS-1$
		Color background = Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);
		getDrawerFigure().getScrollpane().setBackgroundColor(background);
		getDrawerFigure().getScrollpane().setBackgroundColor(background);
	}

	public ToggleListener createToggleListner() {
		toggleListener = new ToggleListener();
		return toggleListener;
	}

	public static ToggleListener getToggleListener() {
		return toggleListener;
	}

	public RelationshipDrawFigure getFig() {
		return fig;
	}

	public void setFig(RelationshipDrawFigure fig) {
		this.fig = fig;
	}
}