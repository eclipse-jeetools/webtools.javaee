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
package org.eclipse.jst.j2ee.internal.web.providers;


import java.util.Collection;
import java.util.Vector;

import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.jst.j2ee.internal.web.operations.WebEditModel;
import org.eclipse.jst.j2ee.webapplication.WebApp;


/**
 * Insert the type's description here. Creation date: (6/18/2001 5:53:02 PM)
 * 
 * @author: Administrator
 */
public class ItemHolder extends ItemProvider {
	protected WebApp webApp;
	protected WebEditModel editModel;

	public ItemHolder() {
		super();
	}

	/**
	 * Jarholder constructor comment.
	 */
	public ItemHolder(WebApp webApp) {
		super();
		setWebApp(webApp);
	}

	public ItemHolder(WebEditModel anEditModel) {
		super();
		setEditModel(anEditModel);
	}

	public Collection getChildren(Object element) {
		WebApp testElement;
		Vector v = new Vector();
		if (element instanceof ItemHolder) {
			testElement = ((ItemHolder) element).getWebApp();
			v.add(testElement);
		}
		return v;
	}

	/**
	 * Insert the method's description here. Creation date: (6/18/2001 6:00:43 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.ejb.EJBJar
	 */
	public WebApp getWebApp() {
		return webApp;
	}

	/**
	 * Insert the method's description here. Creation date: (6/18/2001 6:00:43 PM)
	 * 
	 * @param newJar
	 *            org.eclipse.jst.j2ee.ejb.EJBJar
	 */
	public void setWebApp(WebApp aWebApp) {
		webApp = aWebApp;
	}

	/**
	 * Gets the editModel.
	 * 
	 * @return Returns a EJBEditModel
	 */
	public WebEditModel getEditModel() {
		return editModel;
	}

	/**
	 * Sets the editModel.
	 * 
	 * @param editModel
	 *            The editModel to set
	 */
	public void setEditModel(WebEditModel editModel) {
		this.editModel = editModel;
	}
}