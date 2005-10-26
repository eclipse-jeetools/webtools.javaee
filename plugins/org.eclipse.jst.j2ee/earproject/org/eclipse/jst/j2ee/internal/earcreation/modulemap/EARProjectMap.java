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
package org.eclipse.jst.j2ee.internal.earcreation.modulemap;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;


/**
 * @lastgen interface EARProjectMap extends EObject {}
 * @deprecated
 * Use
 * <p>
 * 		Need to use the ModuleCore and WorkbenchComponent Api to get to the referenced modules
 * as with the Flexible project changes a .modulemaps file will not exist in an EAR module and
 * all the info that was captured in .modulemaps file will is now captured in the .component file
 */
public interface EARProjectMap extends EObject {
	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The list of Mappings references
	 */
	EList getMappings();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The list of UtilityJARMappings references
	 */
	EList getUtilityJARMappings();

} //EARProjectMap
