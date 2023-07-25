/*******************************************************************************
 * Copyright (c) 2006, 2023 IBM Corporation and others.
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
package org.eclipse.jst.common.internal.annotations.core;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.ICompilationUnit;

/**
 * This interface is used by clients to allow their own annotation provider to detail information
 * on whether or not a given EObject is annotated for one of their tag sets.
 * 
 * @author jlanuti
 * @planned for 2.0
 */
public interface IAnnotationsProvider {
	
	/**
	 * Return whether or not the passed EObject is annotated by your annotation provider
	 * 
	 * @planned for 2.0
	 * @param eObject
	 * @return boolean value of isAnnotated
	 */
	public boolean isAnnotated(EObject eObject);

	/**
	 * The passed eObject may have been annoted.  If true, it is desired to
	 * determine which {@link ICompilationUnit} has the tag or tags that created
	 * this object.
	 * 
	 * @planned for 2.0
	 * @param eObject - An {@link EObject} that may be annotated.
	 * @return the {@link ICompilationUnit} that has the tag that created this eObject.
	 * 
	 * @see #getPrimaryTagset(EObject)
	 */
	public ICompilationUnit getPrimaryAnnotatedCompilationUnit(EObject eObject);

	/**
	 * It is desired to obtain the tagset name which is used to create the passed
	 * eObject.
	 * 
	 * @planned for 2.0
	 * @param eObject An {@link EObject} which may be annotated.
	 * @return The name of the tagset which creates the passed eObject.
	 * 
	 * @see #getPrimaryAnnotatedCompilationUnit(EObject)
	 */
	public String getPrimaryTagset(EObject eObject);
}
