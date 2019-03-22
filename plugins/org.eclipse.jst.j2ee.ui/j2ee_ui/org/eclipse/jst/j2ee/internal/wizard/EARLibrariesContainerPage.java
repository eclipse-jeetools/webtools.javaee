/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPage;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPageExtension;
import org.eclipse.jdt.ui.wizards.NewElementWizardPage;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathContainer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class EARLibrariesContainerPage extends NewElementWizardPage implements IClasspathContainerPage, IClasspathContainerPageExtension {

	protected IClasspathEntry entry = null;

	public EARLibrariesContainerPage() {
		super("EARLibrariesContainerPage"); //$NON-NLS-1$
		setTitle(EARLibrariesMessages.EARLibrariesContainerPage_0);
		setDescription(EARLibrariesMessages.EARLibrariesContainerPage_1);
	}

	public boolean finish() {
		return true;
	}

	public IClasspathEntry getSelection() {
		return JavaCore.newContainerEntry(J2EEComponentClasspathContainer.CONTAINER_PATH);
	}

	public void setSelection(IClasspathEntry containerEntry) {
	}

	public void createControl(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		final Text text = new Text(composite, SWT.MULTI | SWT.READ_ONLY | SWT.WRAP);
		text.setText(getDescription());
		text.setLayoutData(new GridData(GridData.FILL_BOTH));
		setControl(composite);
	}

	public void initialize(IJavaProject project, IClasspathEntry[] currentEntries) {
	}

	
	public static class EARLibrariesMessages extends NLS {
		private static final String BUNDLE_NAME = "org.eclipse.jst.j2ee.internal.wizard.earlibraries"; //$NON-NLS-1$

		public static String EARLibrariesContainerPage_0;

		public static String EARLibrariesContainerPage_1;
		static {
			// initialize resource bundle
			NLS.initializeMessages(BUNDLE_NAME, EARLibrariesMessages.class);
		}

		private EARLibrariesMessages() {
		}
	}
	
}
