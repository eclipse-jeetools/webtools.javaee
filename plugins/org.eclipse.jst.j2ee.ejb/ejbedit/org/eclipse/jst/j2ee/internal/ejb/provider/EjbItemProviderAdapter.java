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
package org.eclipse.jst.j2ee.internal.ejb.provider;



import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EObjectItemProvider;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;


/**
 * This extended item provider supports the following commands:
 * {@link org.eclipse.jst.j2ee.ejb.command.CreateChildCommand}. The commands are implemented uniformly on
 * all our item adapters using this common base class.
 */
public class EjbItemProviderAdapter extends EObjectItemProvider {
	protected EjbItemProviderAdapter(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This implements the default behavior for
	 * {@link org.eclipse.jst.j2ee.ejb.command.CreateChildCommand}.
	 */
	public Object createChild(Object object) {
		return null;
	}

	/**
	 * This returns the default image for {@link org.eclipse.jst.j2ee.ejb.command.CreateChildCommand}.
	 */
	public Object getCreateChildImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("CreateChild");//$NON-NLS-1$
	}

	/**
	 * This returns the default label for {@link org.eclipse.jst.j2ee.ejb.command.CreateChildCommand}.
	 */
	public String getCreateChildText(Object object) {
		return EJBProviderLibrariesResourceHandler.getString("Create_Child_UI_"); //$NON-NLS-1$ = "Create Child"
	}

	/**
	 * This returns the default help text for {@link org.eclipse.jst.j2ee.ejb.command.CreateChildCommand}.
	 */
	public String getCreateChildToolTipText(Object object) {
		EObject refObject = (EObject) object;
		return EJBProviderLibrariesResourceHandler.getString("Create_a_new_child_for_the_UI_") + refObject.eClass().getName() + ".";//$NON-NLS-2$ //$NON-NLS-1$ = "Create a new child for the selected "
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.IDisposable#dispose()
	 */
	public void dispose() {
		super.dispose();
		target = null;
	}

}