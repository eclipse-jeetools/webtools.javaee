/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.commands;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jem.java.JavaClass;

public class RemoveClassReferenceCommand extends DeleteClassReferenceCommand {
	/**
	 * Constructor for RemoveClassReferenceCommand.
	 * 
	 * @param parent
	 * @param aJavaClass
	 * @param aType
	 * @param shouldGenJava
	 * @param shouldGenMetadata
	 */
	public RemoveClassReferenceCommand(IRootCommand parent, JavaClass aJavaClass, EStructuralFeature aType) {
		//Do not gen Java
		super(parent, aJavaClass, aType, false, true);
	}

}