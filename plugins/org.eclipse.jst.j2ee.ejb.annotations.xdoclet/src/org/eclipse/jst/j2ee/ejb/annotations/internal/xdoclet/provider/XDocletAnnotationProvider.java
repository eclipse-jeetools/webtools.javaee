/*******************************************************************************
 * Copyright (c) 2002, 2003,2004,2005 Eteration Bilisim A.S.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Eteration Bilisim A.S. - initial API and implementation
 *     Naci Dai
 * For more information on eteration, please see
 * <http://www.eteration.com/>.
 ***************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.provider;

import org.eclipse.jst.j2ee.ejb.annotation.internal.provider.IAnnotationProvider;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletPreferenceStore;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletRuntime;


public class XDocletAnnotationProvider implements IAnnotationProvider {

	public boolean isEjbAnnotationProvider() {
		return true;
	}

	public boolean isServletAnnotationProvider() {
		return true;
	}

	public boolean isWebServiceAnnotationProvider() {
		return false;
	}

	public boolean isValid() {
		XDocletRuntime runtime = new XDocletRuntime();
		runtime.setHome(XDocletPreferenceStore.getProperty(XDocletPreferenceStore.XDOCLETHOME));
		runtime.setVersion(XDocletPreferenceStore.getProperty(XDocletPreferenceStore.XDOCLETVERSION));
		return runtime.isValid(XDocletPreferenceStore.getProperty(XDocletPreferenceStore.XDOCLETVERSION));
	}

	public String getName() {
		return "XDocletAnnotionProvider";
	}

}
