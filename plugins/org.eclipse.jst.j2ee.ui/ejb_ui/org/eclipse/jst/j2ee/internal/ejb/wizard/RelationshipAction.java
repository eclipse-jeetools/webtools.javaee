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
 * Created on May 11, 2004
 * 
 * @todo To change the template for this generated file go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.wizard;

import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.widgets.Event;

/**
 * @author blancett
 * 
 * @todo To change the template for this generated type comment go to Window - Preferences - Java -
 *       Code Generation - Code and Comments
 */
public class RelationshipAction implements IAction {
	public void addPropertyChangeListener(IPropertyChangeListener listener) {
	}

	public int getAccelerator() {
		return 0;
	}

	public String getActionDefinitionId() {
		return null;
	}

	public String getDescription() {
		return null;
	}

	public ImageDescriptor getDisabledImageDescriptor() {
		return null;
	}

	public HelpListener getHelpListener() {
		return null;
	}

	public ImageDescriptor getHoverImageDescriptor() {
		return null;
	}

	public String getId() {
		return "test"; //$NON-NLS-1$
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public IMenuCreator getMenuCreator() {

		return null;
	}

	public int getStyle() {

		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#getText()
	 */
	public String getText() {
		// TODO Auto-generated method stub
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#getToolTipText()
	 */
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#isChecked()
	 */
	public boolean isChecked() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#isEnabled()
	 */
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#removePropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener)
	 */
	public void removePropertyChangeListener(IPropertyChangeListener listener) {
		// TODO Auto-generated method stub
	}

	public void run() {
		MouseEvent mouseEvent = null;
		try {
			mouseEvent = (MouseEvent) MouseEvent.class.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		mouseEvent.x = type;
		mouseListener.mousePressed(mouseEvent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#runWithEvent(org.eclipse.swt.widgets.Event)
	 */
	public void runWithEvent(Event event) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#setActionDefinitionId(java.lang.String)
	 */
	public void setActionDefinitionId(String id) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#setChecked(boolean)
	 */
	public void setChecked(boolean checked) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#setDescription(java.lang.String)
	 */
	public void setDescription(String text) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#setDisabledImageDescriptor(org.eclipse.jface.resource.ImageDescriptor)
	 */
	public void setDisabledImageDescriptor(ImageDescriptor newImage) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {

	}

	public void setHelpListener(HelpListener listener) {

	}

	public void setHoverImageDescriptor(ImageDescriptor newImage) {

	}

	public void setId(String id) {

	}

	public void setImageDescriptor(ImageDescriptor newImage) {
	}

	public void setMenuCreator(IMenuCreator creator) {

	}

	public void setText(String text) {
	}

	public void setToolTipText(String text) {
	}

	public void setAccelerator(int keycode) {

	}

	private String name;
	private int type;
	public MouseListener mouseListener;

	public RelationshipAction(String name, int type, MouseListener mouseListener) {
		super();
		this.mouseListener = mouseListener;
		this.name = name;
		this.type = type;
	}

	/**
	 * @return Returns the type.
	 * @todo Generated comment
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            The type to set.
	 * @todo Generated comment
	 */
	public void setType(int type) {
		this.type = type;
	}
}