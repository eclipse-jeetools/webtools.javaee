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
package org.eclipse.jst.j2ee.internal.java.codegen;



import org.eclipse.jst.j2ee.internal.codegen.DependentGenerator;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenConstants;
import org.eclipse.jst.j2ee.internal.codegen.IGenerationBuffer;


/**
 * A dependent generator used by method generators to format a parameter list. The source object
 * passed to initialize must be an array of {@link JavaParameterDescriptor}objects.
 */
public class JavaParameterListGenerator extends DependentGenerator {
	/**
	 * This run implementation adds to the buffer a string of the form: <code>
	 * <br>&nbsp;&nbsp;&nbsp;"(type name, type name)"
	 * </code>
	 * 
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IDependentGenerator
	 */
	public void run(IGenerationBuffer baseAncestorBuffer) throws GenerationException {
		JavaParameterDescriptor[] parms = (JavaParameterDescriptor[]) getSourceElement();
		baseAncestorBuffer.append(IJavaGenConstants.START_PARMS);
		if (parms != null) {
			for (int i = 0; i < parms.length; i++) {
				baseAncestorBuffer.append(parms[i].toString());
				if (i < (parms.length - 1))
					baseAncestorBuffer.append(IBaseGenConstants.COMMA_SEPARATOR);
			}
		}
		baseAncestorBuffer.append(IJavaGenConstants.END_PARMS);
	}
}