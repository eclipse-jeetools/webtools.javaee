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
package org.eclipse.jst.j2ee.internal.provider;



import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModulemapFactory;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModulemapPackage;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;



/**
 * This extended item provider supports the following commands:
 * <ul>
 * <li>{@link CreateChildCommand}
 * </ul>
 * <p>
 * The commands are implemented uniformly on all our item adapters using this common base class.
 */
public class ModulemapItemProviderAdapter extends ItemProviderAdapter {
	/**
	 * This is the package for the modulemap model.
	 */
	protected static final ModulemapPackage modulemapPackage = (ModulemapPackage) EPackage.Registry.INSTANCE.getEPackage(ModulemapPackage.eNS_URI);
	/**
	 * This is the factory for the modulemap model.
	 */
	protected static final ModulemapFactory modulemapFactory = modulemapPackage.getModulemapFactory();

	/**
	 * This creates an instance from an adapter factory and a domain notifier.
	 */
	protected ModulemapItemProviderAdapter(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This creates the supported commands.
	 */
	public Command createCommand(Object object, EditingDomain editingDomain, Class commandClass, CommandParameter commandParameter) {
		return super.createCommand(object, editingDomain, commandClass, commandParameter);
	}

	/**
	 * This is a convenience method for creating <code>CommandParameter</code> s for a given
	 * parent feature and child type (MOF meta-object).
	 */
	protected CommandParameter createChildParameter(EReference feature, EObject childType) {
		return new CommandParameter(null, feature, modulemapFactory.create(childType.eClass()));
	}

	/**
	 * This returns the default owner object for {@link CreateChildCommand}.
	 */
	public Object getCreateChildOwner(Object parent) {
		return parent;
	}

	/**
	 * This returns the default result collection for {@link CreateChildCommand}.
	 */
	public Collection getCreateChildResult(Object child) {
		Collection result = new ArrayList(1);
		result.add(child);
		return result;
	}

	/**
	 * This returns the default label for {@link CreateChildCommand}.
	 */
	public String getCreateChildText(Object parent, Object feature, Object child) {
		return J2EEPlugin.getPlugin().getString("_UI_CreateChild_text"); //$NON-NLS-1$
	}

	/**
	 * This returns the default description for {@link CreateChildCommand}.
	 */
	public String getCreateChildDescription(Object parent, Object feature, Object child) {
		return J2EEPlugin.getPlugin().getString("_UI_CreateChild_description"); //$NON-NLS-1$
	}

	/**
	 * This returns the default help text for {@link CreateChildCommand}.
	 */
	public String getCreateChildToolTipText(Object parent, Object feature, Object child) {
		return J2EEPlugin.getPlugin().getString("_UI_CreateChild_tooltip"); //$NON-NLS-1$
	}

	/**
	 * This returns the default image for {@link CreateChildCommand}.
	 */
	public Object getCreateChildImage(Object parent, Object feature, Object child) {
		Object image = null;

		if (parent instanceof EObject && child instanceof EObject) {
			String name = ((EObject) parent).eClass().getName() + "Create" + ((EObject) child).eClass().getName(); //$NON-NLS-1$
			image = J2EEPlugin.getPlugin().getImage(name);
		}
		return image;
	}

	/**
	 * This looks up the name of the type of the specified object.
	 */
	protected String getTypeText(Object object) {
		String typeKey = object instanceof EObject ? ((EObject) object).eClass().getName() : "Unknown"; //$NON-NLS-1$
		return J2EEPlugin.getPlugin().getString("_UI_" + typeKey + "_type"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This looks up the name of the specified feature.
	 */
	protected String getFeatureText(Object feature) {
		String featureKey = feature instanceof EReference ? ((EReference) feature).getName() : "Unknown"; //$NON-NLS-1$
		return J2EEPlugin.getPlugin().getString("_UI_" + featureKey + "_feature"); //$NON-NLS-1$ //$NON-NLS-2$
	}
}