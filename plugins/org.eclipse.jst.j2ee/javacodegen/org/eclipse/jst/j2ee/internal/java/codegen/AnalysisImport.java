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



import org.eclipse.jst.j2ee.internal.codegen.GenerationException;

/**
 * Used by {@link JavaCompilationUnitGenerator}to keep tract of imports added or removed during
 * analysis. The real add and removes are done during run after the working copy is obtained.
 */
public class AnalysisImport {
	private boolean fAdd = true;
	private String fImport = null;
	private JavaCompilationUnitGenerator fCUGen = null;

	/**
	 * An AnalysisImport is constructed from three parameters. The first parameter is true if the
	 * import is to be added and false if removed. The second parameter is the import and the third
	 * is the compilation unit generator.
	 */
	public AnalysisImport(boolean add, String importName, JavaCompilationUnitGenerator toCUGen) {
		super();
		fAdd = add;
		fImport = importName;
		fCUGen = toCUGen;
	}

	/**
	 * Called by the generator during run to do the import operation.
	 */
	public void execute() throws GenerationException {
		if (fCUGen.primGetCompilationUnit() == null)
			throw new GenerationException(JavaCodeGenResourceHandler.getString("An_analysis_import_can_onl_EXC_")); //$NON-NLS-1$ = "An analysis import can only be executed during run()."
		if (fAdd)
			fCUGen.addImport(fImport);
		else
			fCUGen.removeImport(fImport);
	}
}