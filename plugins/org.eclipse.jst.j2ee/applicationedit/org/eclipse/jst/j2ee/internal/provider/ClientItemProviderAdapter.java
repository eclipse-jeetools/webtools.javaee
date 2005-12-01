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
package org.eclipse.jst.j2ee.internal.provider;


import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.jst.j2ee.internal.application.common.CreateChildCommand;
import org.eclipse.jst.j2ee.internal.application.provider.ApplicationProvidersResourceHandler;



/**
 * This extended item provider supports the following commands:
 * {@link org.eclipse.jst.j2ee.internal.internal.client.command.CreateChildCommand}. The commands
 * are implemented uniformly on all our item adapters using this common base class.
 */
public class ClientItemProviderAdapter extends ItemProviderAdapter implements org.eclipse.jst.j2ee.internal.application.common.CreateChildCommand.Helper {
	/**
	 * This creates and instance from an adapter factory and a domain notifier.
	 */
	protected ClientItemProviderAdapter(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This implements the default behavior for
	 * {@link org.eclipse.jst.j2ee.internal.internal.client.command.CreateChildCommand}.
	 */
	public Object createChild(Object object) {
		return null;
	}

	/**
	 * This creates the supported commands.
	 */
	public Command createCommand(Object object, EditingDomain editingDomain, Class commandClass, CommandParameter commandParameter) {
		if (commandClass == CreateChildCommand.class) {
			return new CreateChildCommand(editingDomain, (EObject) object, null, this);
		}
		return super.createCommand(object, editingDomain, commandClass, commandParameter);
	}

	/**
	 * This returns the default image for
	 * {@link org.eclipse.jst.j2ee.internal.internal.client.command.CreateChildCommand}.
	 */
	public Object getCreateChildImage(Object object) {
		return org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.getPlugin().getImage("CreateChild"); //$NON-NLS-1$
	}

	/**
	 * This returns the default label for
	 * {@link org.eclipse.jst.j2ee.internal.internal.client.command.CreateChildCommand}.
	 */
	public String getCreateChildText(Object object) {
		return ApplicationProvidersResourceHandler.getString("Create_Child_UI_"); //$NON-NLS-1$
	}

	/**
	 * This returns the default help text for
	 * {@link org.eclipse.jst.j2ee.internal.internal.client.command.CreateChildCommand}.
	 */
	public String getCreateChildToolTipText(Object object) {
		EObject refObject = (EObject) object;
		return ApplicationProvidersResourceHandler.getString("Create_a_new_child_for_the_selected_UI_") + refObject.eClass().getName() + "."; //$NON-NLS-1$ //$NON-NLS-2$
	}
}
