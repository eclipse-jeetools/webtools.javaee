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
package org.eclipse.jst.j2ee.internal.application.common;


import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandActionDelegate;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * This is used to generate a command that will add a new child of appropriate type to an object.
 * The implementation specifies an owner, a feature, and a helper to which the implementation is
 * delegated,
 */
public class CreateChildCommand extends CommandWrapper implements CommandActionDelegate {

	/**
	 * This is the helper interface to which child creation is delegated.
	 */
	public static interface Helper {
		/**
		 * This implements the default command behavior
		 */
		public Object createChild(Object object);

		/**
		 * This returns the default label
		 */
		public String getCreateChildText(Object object);

		/**
		 * This returns the default help text
		 */
		public String getCreateChildToolTipText(Object object);

		/**
		 * This returns the default image
		 */
		public Object getCreateChildImage(Object object);
	}

	/**
	 * This is the editing doman in which this command operates.
	 */
	protected EditingDomain domain;
	/**
	 * This is the owner to which children will be added.
	 */
	protected EObject owner;
	/**
	 * This is the feature of the owner to which children will be added.
	 */
	protected EReference feature;
	/**
	 * This is the helper object to which child creation is delegated.
	 */
	protected CreateChildCommand.Helper helper;

	/**
	 * This creates an instances that will add something to the owner's feature based on the
	 * delegate helper.
	 */
	public CreateChildCommand(EditingDomain domain, EObject owner, EReference feature, CreateChildCommand.Helper helper) {
		super();

		this.domain = domain;
		this.owner = owner;
		this.feature = feature;
		this.helper = helper;
	}

	/**
	 * This returns a command that creates a new child of some appropriate type.
	 */
	public static Command create(EditingDomain domain, Object object) {
		return domain.createCommand(CreateChildCommand.class, new CommandParameter(object));
	}

	/**
	 * This creates the add command that will implement this action and specifies the collection by
	 * delegating to {@link CreateChildCommand.Helper#getCreateChildImage}.
	 */
	protected Command createCommand() {
		if (feature == null) {
			return AddCommand.create(domain, owner, null, helper.createChild(owner));
		}
		return new AddCommand(domain, owner, feature, helper.createChild(owner));
	}

	/**
	 * This delegates to {@link CreateChildCommand.Helper#getCreateChildToolTipText}.
	 */
	public String getDescription() {
		return helper.getCreateChildToolTipText(owner);
	}

	/**
	 * This delegates to {@link CreateChildCommand.Helper#getCreateChildImage}.
	 */
	public Object getImage() {
		return helper.getCreateChildImage(owner);
	}

	/**
	 * This delegates to {@link CreateChildCommand.Helper#getCreateChildText}.
	 */
	public String getText() {
		return helper.getCreateChildText(owner);
	}

	/**
	 * This delegates to {@link CreateChildCommand.Helper#getCreateChildToolTipText}.
	 */
	public String getToolTipText() {
		return helper.getCreateChildToolTipText(owner);
	}
}