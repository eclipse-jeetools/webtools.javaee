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
package org.eclipse.jst.j2ee.internal.jca.providers;



import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.jst.j2ee.jca.JcaFactory;
import org.eclipse.jst.j2ee.jca.JcaPackage;


/**
 * This extended item provider supports the following commands:
 * <ul>
 * <li>{@link CreateChildCommand}
 * </ul>
 * <p>
 * The commands are implemented uniformly on all our item adapters using this common base class.
 */
public class JcaItemProviderAdapter extends ItemProviderAdapter {

	/**
	 * This is the package for the jca model.
	 */
	protected static final JcaPackage jcaPackage = (JcaPackage) EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI);

	/**
	 * This is the factory for the jca model.
	 */
	protected static final JcaFactory jcaFactory = jcaPackage.getJcaFactory();

	/**
	 * This creates an instance from an adapter factory and a domain notifier.
	 */
	protected JcaItemProviderAdapter(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}// JcaItemProviderAdapter

	/**
	 * This creates the supported commands.
	 * 
	 * @param Object
	 *            object - The adapted class.
	 * @param EditingDomain -
	 *            The current editing domain.
	 * @param commandClass -
	 *            The current command class.
	 * @param commandParameter -
	 *            The current commandParameter.
	 * @return Command
	 */
	public Command createCommand(Object object, EditingDomain editingDomain, Class commandClass, CommandParameter commandParameter) {
		return super.createCommand(object, editingDomain, commandClass, commandParameter);
	}// createCommand

}// JcaItemProviderAdapter
