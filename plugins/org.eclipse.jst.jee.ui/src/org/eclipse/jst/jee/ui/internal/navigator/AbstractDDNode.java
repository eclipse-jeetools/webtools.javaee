/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator;

import java.util.List;

import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.swt.graphics.Image;

/**
 * Abstract Deployment Descriptor Node is an abstract wrapper class for the nodes in the Deployment
 * Descriptor it can contain information for the label provider some description for tool tip, an image
 * and the object that it is wrapping.
 * 
 * @author Dimitar Giormov
 */
public abstract class AbstractDDNode {

	protected Object adapterNode;
	protected Image image;
	protected String text;
	protected String description;


	/**
	 * Constructor for the class
	 * 
	 * @param adapterNode - the wrapped object 
	 */
	public AbstractDDNode(Object adapterNode){
		this.adapterNode = adapterNode;
		image = null;
		text = null;
		description = null;
	}

	public abstract List getChildren();

	public abstract boolean hasChildren();

	public void decorate(IDecoration decoration) {
		// do nothing, subclasses should override
	}

	public Image getImage() {
		return image; 
	}

	public String getText() {
		return text;
	}

	public String getDescription() {
		return description;
	}

	public Object getAdapterNode() {
		return adapterNode;
	}

	public void reinit(JavaEEObject modelObject) {
		adapterNode = modelObject;
	}		

}
