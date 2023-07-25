/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Milen Manov, milen.manov@sap.com - bugs 248623
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.graphics.Image;

public class J2EEComponentLabelProvider extends LabelProvider {

	ITableLabelProvider tableLableProvider;
	
	private J2EEComponentLabelProvider instance;
	
	public J2EEComponentLabelProvider(ITableLabelProvider tableLableProvider){
		this.tableLableProvider = tableLableProvider;
		instance = this;
		tableLableProvider.addListener(new ILabelProviderListener(){
			@Override
			public void labelProviderChanged(LabelProviderChangedEvent event) {
				LabelProviderChangedEvent newEvent = new LabelProviderChangedEvent(instance);
				fireLabelProviderChanged(newEvent);
			}
		});
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {
		return tableLableProvider.getColumnImage(element, 1);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		return tableLableProvider.getColumnText(element, 1);
//		if(element instanceof IVirtualComponent){
//			IVirtualComponent comp = (IVirtualComponent)element;
//			return comp.getProject().getName();
//		}
//		
//		if (element instanceof IProject) {
//			IProject handle = (IProject)element;
//			return handle.getName();
//		}
//		return null;
	}

}
