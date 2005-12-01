/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jun 11, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.war.ui.util;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.web.providers.WebAppEditResourceHandler;
import org.eclipse.jst.j2ee.webapplication.WebApp;


/**
 * @author jialin
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class WebListenerGroupItemProvider extends WebGroupItemProvider {

	/**
	 * @param adapterFactory
	 */
	public WebListenerGroupItemProvider(AdapterFactory adapterFactory, WeakReference weakWebApp) {
		super(adapterFactory, weakWebApp);
	}
	
	/**
	 * This returns Filter.gif.
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getDefault().getImage("listener"); //$NON-NLS-1$
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ITreeItemContentProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
	    List result = new ArrayList();
	    Object webApp = weakWebApp.get();
	    if(webApp != null){
	    	result.addAll(((WebApp)webApp).getListeners());
	    }
	    return getSortedChildren(result);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ITreeItemContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		return weakWebApp.get();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.IItemLabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return WebAppEditResourceHandler.getString("LISTENER"); //$NON-NLS-1$
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ITreeItemContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object object) {
		return !getChildren(object).isEmpty();
	}
}
