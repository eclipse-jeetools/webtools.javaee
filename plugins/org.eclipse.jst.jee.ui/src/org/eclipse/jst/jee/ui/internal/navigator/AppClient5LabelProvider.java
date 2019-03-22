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

import org.eclipse.jst.j2ee.navigator.internal.J2EELabelProvider;
import org.eclipse.jst.jee.ui.internal.navigator.appclient.GroupAppClientProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Application Clent 5.0 Label provider is Deployment Descriptor label provider, 
 * used for decorating of the descriptor tree in project explorer. 
 * 
 * @author Dimitar Giormov
 */
public class AppClient5LabelProvider extends J2EELabelProvider {
	
	@Override
	public Image getImage(Object element) {
		Image ret = null;
		if (element instanceof GroupAppClientProvider) {
			ret = ((GroupAppClientProvider)element).getImage();
		} 
		return ret;
	}

	@Override
	public String getText(Object element) {
		String ret = null;
		if (element instanceof GroupAppClientProvider) {
			ret =  ((GroupAppClientProvider) element).getText();
		}
		return ret;
	}

}
