/*******************************************************************************
 * Copyright (c) 2007, 2019 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * SAP AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.war.ui.util;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.internal.web.providers.WebAppEditResourceHandler;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WelcomeFileList;


/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebWelcomeFileGroupItemProvider extends WebGroupItemProvider {

	/**
	 * @param adapterFactory
	 */
	public WebWelcomeFileGroupItemProvider(AdapterFactory adapterFactory, WeakReference weakWebApp) {
		super(adapterFactory, weakWebApp);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ITreeItemContentProvider#getChildren(java.lang.Object)
	 */
	@Override
	public Collection getChildren(Object object) {
		List result = new ArrayList();
		if (weakWebApp!=null) {
			Object webApp = weakWebApp.get();
			if (null != webApp) {
			    WelcomeFileList fileList = ((WebApp)webApp).getFileList();
			    if (fileList != null) {
			        result.addAll(fileList.getFile());
			    }
			}
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ItemProvider#getImage()
	 */
	@Override
	public Object getImage(Object object) {
		return WebPlugin.getDefault().getImage("welcome_list"); //$NON-NLS-1$
	}
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ItemProvider#getText()
	 */
	@Override
	public String getText(Object object) {
		return WebAppEditResourceHandler.getString("Welcome_Pages_1"); //$NON-NLS-1$ 
	}
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ITreeItemContentProvider#hasChildren(java.lang.Object)
	 */
	@Override
	public boolean hasChildren(Object object) {
		return !getChildren(object).isEmpty();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ITreeItemContentProvider#getParent(java.lang.Object)
	 */
	@Override
	public Object getParent(Object object) {
		return weakWebApp.get();
	}
}
