/*******************************************************************************
 * Copyright (c) 2008, 2023 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * SAP AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator.ejb;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.jst.jee.ui.plugin.JEEUIPluginIcons;

public class BeanDecorator extends LabelProvider implements ILightweightLabelDecorator {

	@Override
	public void decorate(Object element, IDecoration decoration) {
		if (element instanceof BeanInterfaceNode){
			BeanInterfaceNode beanInteraceNode = (BeanInterfaceNode) element;
			switch (beanInteraceNode.getKind()) {
			case BeanInterfaceNode.KINDS.BUSSINESS_LOCAL:
				applyDecorator(JEEUIPluginIcons.BUSSINESS_LOCAL, decoration);
				break;
			case BeanInterfaceNode.KINDS.BUSSINESS_REMOTE:
				applyDecorator(JEEUIPluginIcons.BUSSINESS_REMOTE, decoration);
				break;
			case BeanInterfaceNode.KINDS.REMOTE:
				applyDecorator(JEEUIPluginIcons.REMOTE, decoration);
				break;
			case BeanInterfaceNode.KINDS.REMOTE_HOME:
				applyDecorator(JEEUIPluginIcons.REMOTE_HOME, decoration);
				break;
			case BeanInterfaceNode.KINDS.LOCAL:
				applyDecorator(JEEUIPluginIcons.LOCAL, decoration);
				break;
			case BeanInterfaceNode.KINDS.LOCAL_HOME:
				applyDecorator(JEEUIPluginIcons.LOCAL_HOME, decoration);
				break;
			}
		} else if (element instanceof BeanNode){
		  decoration.addOverlay(J2EEUIPlugin.getDefault().getImageDescriptor("ejb_module_ovr")); //$NON-NLS-1$
		}		
	}

	private void applyDecorator(String id, IDecoration decoration) {
		ImageDescriptor imageDescriptor = JEEUIPlugin.getDefault().getImageDescriptor(id);
		decoration.addOverlay(imageDescriptor);
		
	}

}
