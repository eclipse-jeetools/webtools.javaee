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
 * Created on Aug 11, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.war.ui.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jst.j2ee.common.Listener;
import org.eclipse.jst.j2ee.internal.provider.J2EEItemProvider;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebApp;

/**
 * @author jlanuti
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public abstract class WebGroupItemProvider extends J2EEItemProvider {

	class WebGroupComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			if (o1 instanceof Servlet) {
				Servlet s1 = (Servlet) o1;
				Servlet s2 = (Servlet) o2;
				return s1.getServletName().compareTo(s2.getServletName());
			} else if (o1 instanceof Filter) {
				Filter f1 = (Filter) o1;
				Filter f2 = (Filter) o2;
				return f1.getName().compareTo(f2.getName());
			} else if (o1 instanceof Listener) {
				Listener l1 = (Listener) o1;
				Listener l2 = (Listener) o2;
				return l1.getListenerClassName().compareTo(l2.getListenerClassName());
			} else
				return -1;
		}
	}

	protected WebApp webApp = null;

	public WebGroupItemProvider(AdapterFactory adapterFactory, WebApp webApp) {
		super(adapterFactory);
		this.webApp = webApp;
	}

	/**
	 * @return Returns the webApp.
	 */
	public WebApp getWebApp() {
		return webApp;
	}

	/**
	 * @param webApp
	 *            The webApp to set.
	 */
	public void setWebApp(WebApp webApp) {
		this.webApp = webApp;
	}

	public Collection getSortedChildren(List localChildren) {
		Collections.sort(localChildren, new WebGroupComparator());
		return localChildren;
	}
}