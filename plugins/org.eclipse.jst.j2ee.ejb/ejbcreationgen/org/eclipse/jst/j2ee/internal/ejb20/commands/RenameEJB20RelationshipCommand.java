/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.commands;


import org.eclipse.jst.j2ee.ejb.EJBRelation;

public class RenameEJB20RelationshipCommand extends EJB20RelationshipCommand {
	String originalName;
	String originalRelationshipDescription;

	/**
	 * Constructor for RenameEJB20RelationshipCommand.
	 */
	public RenameEJB20RelationshipCommand(EJBRelation aRelation, String newName) {
		super(newName);
		initializeFromRelationship(aRelation);
	}

	protected void createCopy() {
	}

	/**
	 * Override to perform the necessary operation to update the Metadata.
	 */
	protected void executeForMetadataGeneration() {
		super.executeForMetadataGeneration();
		getEJBRelation().setName(getName());
		getEJBRelation().setDescription(getRelationshipDescription());

	}

	/**
	 * Insert the method's description here. Creation date: (8/14/2001 9:00:29 PM)
	 * 
	 * @return java.lang.String
	 */
	protected java.lang.String getOriginalName() {
		return originalName;
	}

	protected void initializeFromRelationship(EJBRelation aRelation) {
		if (aRelation != null) {
			setOriginalName(aRelation.getName());
			setOriginalRelationshipDescription(aRelation.getDescription());
			setRelationship(aRelation);
		}
	}

	protected void initializeRelationship() {
	}

	/**
	 * Insert the method's description here. Creation date: (8/14/2001 9:00:29 PM)
	 * 
	 * @param newOriginalName
	 *            java.lang.String
	 */
	protected void setOriginalName(java.lang.String newOriginalName) {
		originalName = newOriginalName;
	}

	/**
	 * Undo the the creation of the relationship.
	 */
	protected void undoMetadataGeneration() {
		if (getEJBRelation() != null) {
			getEJBRelation().setName(getOriginalName());
			getEJBRelation().setDescription(getOriginalRelationshipDescription());
		}
		super.undoMetadataGeneration();
	}

	/**
	 * Returns the originalRelationshipDescription.
	 * 
	 * @return String
	 */
	protected String getOriginalRelationshipDescription() {
		return originalRelationshipDescription;
	}

	/**
	 * Sets the originalRelationshipDescription.
	 * 
	 * @param originalRelationshipDescription
	 *            The originalRelationshipDescription to set
	 */
	protected void setOriginalRelationshipDescription(String originalRelationshipDescription) {
		this.originalRelationshipDescription = originalRelationshipDescription;
	}


}