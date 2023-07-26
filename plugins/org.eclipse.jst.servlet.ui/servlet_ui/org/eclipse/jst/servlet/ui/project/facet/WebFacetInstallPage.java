/******************************************************************************
 * Copyright (c) 2005, 2019 BEA Systems, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 *    David Schneider, david.schneider@unisys.com - [142500] WTP properties pages fonts don't follow Eclipse preferences
 ******************************************************************************/

package org.eclipse.jst.servlet.ui.project.facet;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleFacetInstallPage;
import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
import org.eclipse.jst.servlet.ui.IWebUIContextIds;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */
public class WebFacetInstallPage extends J2EEModuleFacetInstallPage implements IWebFacetInstallDataModelProperties {
	private Label contextRootLabel;
	private Text contextRoot;
	private Label contentDirLabel;
	private Text contentDir;

	public WebFacetInstallPage() {
		super("web.facet.install.page"); //$NON-NLS-1$
		setTitle(Resources.pageTitle);
		setDescription(Resources.pageDescription);
	}

	@Override
	protected Composite createTopLevelComposite(final Composite parent) {
		setInfopopID(IWebUIContextIds.NEW_DYNAMIC_WEB_PROJECT_PAGE3);
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		this.contextRootLabel = new Label(composite, SWT.NONE);
		this.contextRootLabel.setText(Resources.contextRootLabel);
		this.contextRootLabel.setLayoutData(new GridData());

		this.contextRoot = new Text(composite, SWT.BORDER);
		this.contextRoot.setLayoutData(gdhfill());
		this.contextRoot.setData("label", this.contextRootLabel); //$NON-NLS-1$
		synchHelper.synchText(contextRoot, CONTEXT_ROOT, new Control[]{contextRootLabel});

		this.contentDirLabel = new Label(composite, SWT.NONE);
		this.contentDirLabel.setText(Resources.contentDirLabel);
		this.contentDirLabel.setLayoutData(new GridData());

		this.contentDir = new Text(composite, SWT.BORDER);
		this.contentDir.setLayoutData(gdhfill());
		this.contentDir.setData("label", this.contentDirLabel); //$NON-NLS-1$
		synchHelper.synchText(contentDir, CONFIG_FOLDER, null);
		
		new Label(composite, SWT.NONE); // pad
		
		createGenerateDescriptorControl(composite, J2EEConstants.WEBAPP_DD_SHORT_NAME);
		registerFacetVersionChangeListener();
		
		Dialog.applyDialogFont(parent);
        
		return composite;
	}
	
	@Override
	protected void handleFacetVersionChangedEvent()
	{
	    String fv = model.getStringProperty(FACET_VERSION_STR);
	    boolean isWeb25OrGreater = J2EEVersionUtil.convertVersionStringToInt(fv) >= J2EEVersionConstants.WEB_2_5_ID;
	    this.addDD.setVisible(isWeb25OrGreater);
	}
	
	@Override
	protected String[] getValidationPropertyNames() {
		return new String[]{EAR_PROJECT_NAME, CONTEXT_ROOT, CONFIG_FOLDER, SOURCE_FOLDER};
	}

	private static final class Resources extends NLS {
		public static String pageTitle;
		public static String pageDescription;
		public static String contextRootLabel;
		public static String contentDirLabel;
		
		static {
			initializeMessages(WebFacetInstallPage.class.getName(), Resources.class);
		}
	}

	@Override
	protected boolean showValidationErrorsOnEnter() {
		return true;
	}
}