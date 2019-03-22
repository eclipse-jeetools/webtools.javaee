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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;

/**
 * Abstract group Deployment Descriptor node that has a number of sub nodes 
 * (like Entity Beans, Session Beans, Message Driven Beans).
 * 
 * 
 * @author Dimitar Giormov
 *
 */
public abstract class AbstractGroupProvider {

	protected List children = new ArrayList<Object>();
	protected JavaEEObject javaee;
	protected  String text = ""; //$NON-NLS-1$
	protected boolean isValid = true;

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public AbstractGroupProvider(JavaEEObject javaee) {
		this.javaee = javaee;
		if (javaee != null){
			text = NLS.bind(Messages.DEPLOYMENT_DESCRIPTOR, javaee.toString());	
		}
		
	}

	public abstract List getChildren();

	public abstract Image getImage();

	public String getText() {
		return text ;
	}

	public boolean hasChildren() {
		List children2 = getChildren();
		return children2 != null && !children2.isEmpty();
	}

	public JavaEEObject getJavaEEObject() {
		return javaee;
	}

	public void reinit(JavaEEObject modelObject) {
		this.javaee = modelObject;
	}

	public boolean isValid(){
		return isValid;
	}

}
