/******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.j2ee.ui.project.facet;

import org.eclipse.jst.j2ee.internal.earcreation.IEarFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.wizard.DataModelFacetInstallPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class EarFacetInstallPage extends DataModelFacetInstallPage implements IEarFacetInstallDataModelProperties {
	private Label contentDirLabel;
	private Text contentDir;

	public EarFacetInstallPage() {
		super("ear.facet.install.page"); //$NON-NLS-1$

		setTitle(Resources.pageTitle);
		setDescription(Resources.pageDescription);
	}

	protected String[] getValidationPropertyNames() {
		return new String[]{CONTENT_DIR, J2EE_PROJECTS_LIST};
	}

	protected Composite createTopLevelComposite(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		this.contentDirLabel = new Label(composite, SWT.NONE);
		this.contentDirLabel.setText(Resources.contentDirLabel);
		this.contentDirLabel.setLayoutData(gdhfill());

		this.contentDir = new Text(composite, SWT.BORDER);
		this.contentDir.setLayoutData(gdhfill());
		synchHelper.synchText(contentDir, CONTENT_DIR, null);

		return composite;
	}

	private static final class Resources

	extends NLS

	{
		public static String pageTitle;
		public static String pageDescription;
		public static String contentDirLabel;
		public static String contentDirLabelInvalid;

		static {
			initializeMessages(EarFacetInstallPage.class.getName(), Resources.class);
		}
	}

}
