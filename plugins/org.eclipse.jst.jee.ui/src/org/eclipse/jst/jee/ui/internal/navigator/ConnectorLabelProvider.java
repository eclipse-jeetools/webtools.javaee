/*******************************************************************************
 * Copyright (c) 2010, 2023 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator;

import org.eclipse.jst.j2ee.navigator.internal.J2EELabelProvider;
import org.eclipse.jst.javaee.jca.ConfigProperty;
import org.eclipse.jst.javaee.jca.ConnectionDefinition;
import org.eclipse.jst.javaee.jca.RequiredConfigProperty;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.jst.jee.ui.plugin.JEEUIPluginIcons;
import org.eclipse.swt.graphics.Image;

public class ConnectorLabelProvider extends J2EELabelProvider {

	private Image CONFIG_PROP_IMAGE;
	private Image LICENSE_IMAGE;

	@Override
	public String getText(Object element) {
		if (AbstractGroupProvider.class.isInstance(element)){
			return ((AbstractGroupProvider) element).getText();
		} else if (element instanceof org.eclipse.jst.javaee.jca.License){
			return Messages.ConnectorLabelProvider_LICENSE_ARTIFACT;
		}  else if (element instanceof ConfigProperty){
			return Messages.ConnectorLabelProvider_CONFIG_PROPERTY + (((ConfigProperty)element).getConfigPropertyName() != null ? ((ConfigProperty)element).getConfigPropertyName() : ""); //$NON-NLS-1$
		}  else if (element instanceof RequiredConfigProperty){
			return Messages.ConnectorLabelProvider_RequiredConfigProperty + (((RequiredConfigProperty)element).getConfigPropertyName() != null ? ((RequiredConfigProperty)element).getConfigPropertyName() : ""); //$NON-NLS-1$
		}  else if (element instanceof ConnectionDefinition){
			return Messages.ConnectorLabelProvider_ConnectionDefinition + (((ConnectionDefinition)element).getManagedconnectionfactoryClass() != null ?((ConnectionDefinition)element).getManagedconnectionfactoryClass() : ""); //$NON-NLS-1$
		}
		
		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		if (AbstractGroupProvider.class.isInstance(element)){
			return ((AbstractGroupProvider) element).getImage();
		}  else if (element instanceof ConfigProperty){
			if (CONFIG_PROP_IMAGE == null) {
				CONFIG_PROP_IMAGE = JEEUIPlugin.getDefault().getImage(JEEUIPluginIcons.IMG_CONFIG_PROP);
			}
			return CONFIG_PROP_IMAGE;		
		} else if (element instanceof org.eclipse.jst.javaee.jca.License){
			if (LICENSE_IMAGE == null) {
				LICENSE_IMAGE = JEEUIPlugin.getDefault().getImage(JEEUIPluginIcons.IMG_LICENSE);
			}
			return LICENSE_IMAGE;		
		}
		return super.getImage(element);
	}

	
	
}
