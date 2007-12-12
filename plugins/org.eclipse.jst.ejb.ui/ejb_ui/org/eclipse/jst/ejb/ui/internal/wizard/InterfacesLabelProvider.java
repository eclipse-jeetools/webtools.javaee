/*******************************************************************************
 * Copyright (c) 2007 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jst.j2ee.ejb.internal.operations.RemoteLocalInterface;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.swt.graphics.Image;

public class InterfacesLabelProvider extends LabelProvider {

	@Override
	public Image getImage(Object element) {
		if (element instanceof RemoteLocalInterface){
			if (((RemoteLocalInterface) element).isLocal()){
				return J2EEUIPlugin.getDefault().getImage("local_interface_overlay_obj"); //$NON-NLS-1$
			}
			else
				return J2EEUIPlugin.getDefault().getImage("remote_interface_overlay_obj"); //$NON-NLS-1$
		}
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		String retText = null;
		if (element instanceof RemoteLocalInterface){
			retText = ((RemoteLocalInterface) element).getInterfaceName();
		}
		return retText;
	}

}
