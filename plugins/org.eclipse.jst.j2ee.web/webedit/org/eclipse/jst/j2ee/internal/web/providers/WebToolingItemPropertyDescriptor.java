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
package org.eclipse.jst.j2ee.internal.web.providers;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

/**
 * Insert the type's description here. Creation date: (12/11/2000 5:54:28 PM)
 * 
 * @author: Administrator
 */
public class WebToolingItemPropertyDescriptor extends ItemPropertyDescriptor {

	protected int cellEditorType = -1;
	public final static int SMALL_ICON_EDITOR = 1;
	public final static int LARGE_ICON_EDITOR = 2;
	public final static int MULTILINE_TEXT_EDITOR = 3;
	public final static int AUTH_CONSTRAINT_ROLES_EDITOR = 4;

	/**
	 * This creates an instance where the category and filter flags are empty and the cell editor is
	 * determined for the references.
	 */
	public WebToolingItemPropertyDescriptor(AdapterFactory adapterFactory, String displayName, String description, EReference[] parentReferences) {
		super(adapterFactory, displayName, description, parentReferences);
	}

	/**
	 * This creates an instance where the category is filter flags are empty and the cell editor is
	 * determined from the type of the structural feature>
	 */
	public WebToolingItemPropertyDescriptor(AdapterFactory adapterFactory, String displayName, String description, EStructuralFeature feature) {
		super(adapterFactory, displayName, description, feature);
	}

	/**
	 * This creates an instance where the category is filter flags are empty and the cell editor is
	 * determined from the type of the structural feature>
	 */
	public WebToolingItemPropertyDescriptor(AdapterFactory adapterFactory, String displayName, String description, EStructuralFeature feature, int newCellEditorType) {
		super(adapterFactory, displayName, description, feature);
		cellEditorType = newCellEditorType;
	}

	protected void executeCommand(EditingDomain editingDomain, Command command) {
		//TODO We need to use the CommandStack from the WebEditModel.
		//  if (editingDomain instanceof StructuredTextEditingDomain)
		//	  ((StructuredTextEditingDomain) editingDomain).execute(command);
		//  else
		//	  editingDomain.getCommandStack().execute(command);

	}

	/**
	 * Returns the editor type.
	 */
	public int getCellEditorType() {
		return cellEditorType;
	}

	/**
	 * This does the delegated job of setting the property to the given value. It is implemented in
	 * a generic way using the structural feature.
	 */
	public void setPropertyValue(Object object, Object value) {
		EObject refObject = (EObject) object;
		EditingDomain editingDomain = getEditingDomain(object);

		if (parentReferences != null) {
			EObject refValue = (EObject) value;
			Command removeCommand = null;
			for (int i = 0; i < parentReferences.length; ++i) {
				Object oldValue = refObject.eGet(parentReferences[i]);
				if (oldValue != null) {
					final EReference parentReference = parentReferences[i];
					if (oldValue == value) {
						return;
					}
					// else if (refValue.refIsInstanceOf(parentReference.getEType(), true))
					else if (refValue.eClass() == parentReference.getEType() || refValue.eClass().getEAllSuperTypes().contains(parentReference.getEType())) {
						if (editingDomain == null) {
							refObject.eSet(parentReference, value);
						} else {
							Command setCommand = SetCommand.create(editingDomain, refObject, parentReference, value);
							executeCommand(editingDomain, setCommand);
						}
						return;
					} else {
						if (editingDomain == null) {
							refObject.eSet(parentReference, null);
						} else {
							removeCommand = SetCommand.create(editingDomain, refObject, parentReference, null);
						}
						break;
					}
				}
			}

			for (int i = 0; i < parentReferences.length; ++i) {
				final EReference parentReference = parentReferences[i];
				//if (refValue.refIsInstanceOf(parentReference.getEType(), true))
				if (refValue.eClass() == parentReference.getEType() || refValue.eClass().getEAllSuperTypes().contains(parentReference.getEType())) {
					if (editingDomain == null) {
						refObject.eSet(parentReferences[i], value);
					} else {
						if (removeCommand != null) {
							final CompoundCommand compoundCommand = new CompoundCommand(CompoundCommand.LAST_COMMAND_ALL);
							compoundCommand.append(removeCommand);
							compoundCommand.append(SetCommand.create(editingDomain, refObject, parentReference, value));
							executeCommand(editingDomain, compoundCommand);
						} else {
							executeCommand(editingDomain, SetCommand.create(editingDomain, refObject, parentReference, value));
						}
					}
					break;
				}
			}
		} else {
			if (editingDomain == null) {
				refObject.eSet(feature, value);
			} else {
				executeCommand(editingDomain, SetCommand.create(editingDomain, refObject, feature, value));
			}
		}
	}
}