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

import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;




/**
 * Defines a group of related compilation units to be generated. For example, the units generated
 * when an Enterprise Java Bean is created. This generator is usually the top level generator.
 * 
 * <p>
 * Subclasses will typically override these methods:
 * <ul>
 * <li>initialize - create and initialize child generators
 * </ul>
 * <p>
 * Subclasses will override these methods regularly:
 * <ul>
 * <li>terminate - to null object references and release resources
 * </ul>
 */
public abstract class JavaCompilationUnitGroupGenerator extends JavaElementGenerator {
	/**
	 * JavaCompilationUnitGroupGenerator default constructor.
	 */
	public JavaCompilationUnitGroupGenerator() {
		super();
	}

	/**
	 * The compilation unit group is always prepared.
	 * 
	 * @see JavaElementGenerator
	 */
	public boolean isPrepared() throws org.eclipse.jst.j2ee.internal.codegen.GenerationException {
		return true;
	}

	/**
	 * The compilation unit group is always prepared.
	 * 
	 * @see JavaElementGenerator
	 */
	public void prepare() throws org.eclipse.jst.j2ee.internal.codegen.GenerationException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IBaseGenerator#run()
	 */
	public final void run() throws GenerationException {
		IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				try {
					doRun();
				} catch (GenerationException e) {
				}
			}
		};
		try {
			JavaCore.run(runnable, new NullProgressMonitor());
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//	doRun();
	}

	/**
	 * Subclasses should override this method and not run.
	 */
	protected void doRun() throws GenerationException {
		super.run();
	}

}