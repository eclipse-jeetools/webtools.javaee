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
package org.eclipse.jst.j2ee.internal.ejb.codegen;



import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaCompilationUnitGenerator;


/**
 * Insert the type's description here. Creation date: (10/5/00 2:10:53 PM)
 * 
 * @author: Steve Wasleski
 */
public abstract class EnterpriseBeanBeanClassCU extends JavaCompilationUnitGenerator {
	private String fName = null;
	private String fPackageName = null;

	/**
	 * CMPEntityRemoteInterfaceCU constructor comment.
	 */
	public EnterpriseBeanBeanClassCU() {
		super();
	}

	/**
	 * getName method comment.
	 */
	protected String getName() {
		if (fName == null)
			fName = Signature.getSimpleName(((EnterpriseBean) getSourceElement()).getEjbClassName());
		return fName;
	}

	/**
	 * getPackageName method comment.
	 */
	protected String getPackageName() {
		if (fPackageName == null)
			fPackageName = Signature.getQualifier(((EnterpriseBean) getSourceElement()).getEjbClassName());
		return fPackageName;
	}

	/**
	 * Returns the logical name for the type generator.
	 */
	protected abstract String getTypeGeneratorName();

	/**
	 * Adds creation of the type generator.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		IBaseGenerator typeGen = getGenerator(getTypeGeneratorName());
		typeGen.initialize(mofObject);
	}
}