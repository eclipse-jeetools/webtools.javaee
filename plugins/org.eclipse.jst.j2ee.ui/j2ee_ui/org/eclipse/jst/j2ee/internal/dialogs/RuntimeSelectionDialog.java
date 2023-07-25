/*******************************************************************************
 * Copyright (c) 2005, 2020 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.jst.j2ee.internal.dialogs;

import java.text.MessageFormat;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.wst.server.core.IRuntime;


/**
 * @author Administrator
 *
 */
public class RuntimeSelectionDialog extends MessageDialog {
	private IProject project = null;
	private String configuredMessage;

	/**
	 * @param parentShell
	 * @param dialogTitle
	 * @param dialogTitleImage
	 * @param dialogMessage
	 * @param dialogImageType
	 * @param dialogButtonLabels
	 * @param defaultIndex
	 */
	public RuntimeSelectionDialog(Shell parentShell, String dialogTitle,
			Image dialogTitleImage, String dialogMessage, int dialogImageType,
			String[] dialogButtonLabels, int defaultIndex, IProject project) {
		super(parentShell, dialogTitle, dialogTitleImage, dialogMessage,
				dialogImageType, dialogButtonLabels, defaultIndex);
		this.project = project;
		this.configuredMessage = MessageFormat.format(J2EEUIMessages.getResourceString("DEPLOY_RUNTIME_CONFIGURED"), new Object []{project.getName()}); //$NON-NLS-1$
	}
	
	@Override
	protected Control createCustomArea(Composite parent) {

		//Composite composite = new Composite(parent, 0);
		createHyperLink(parent);
		return parent;

	}
	
	private void createHyperLink(Composite parent) {
		Hyperlink link = new Hyperlink(parent,SWT.None);
		GridData layout = new GridData(GridData.HORIZONTAL_ALIGN_END);
		layout.horizontalSpan = 2;
		link.setLayoutData(layout);
		link.setUnderlined(true);
		Color color = new Color(parent.getDisplay(),new RGB(0,0,255) );
		link.setForeground(color);
		link.setText(Messages.RuntimeSelectionDialog_Configure_Target_Runtime_);
		link.addHyperlinkListener(new IHyperlinkListener() {
			public static final String DATA_NO_LINK = "PropertyAndPreferencePage.nolink"; //$NON-NLS-1$

			@Override
			public void linkEntered(org.eclipse.ui.forms.events.HyperlinkEvent e) {
			}

			@Override
			public void linkExited(org.eclipse.ui.forms.events.HyperlinkEvent e) {
			}

			@Override
			public void linkActivated(org.eclipse.ui.forms.events.HyperlinkEvent e) {
				String id = getPreferencePageID();
				PreferencesUtil.createPropertyDialogOn(getShell(), project, id, new String[]{id}, DATA_NO_LINK).open(); //
				//(getShell(), id, new String[]{id}, DATA_NO_LINK).open();
				try {
					updateWidgets();
				} catch (Exception ie) {

				}
			}

			private String getPreferencePageID() {
				return "org.eclipse.wst.common.project.facet.ui.internal.RuntimesPropertyPage"; //$NON-NLS-1$
			}
		});
		
	}
	
	private void updateWidgets() {
		if (getTargetRuntime() != null) {
		  messageLabel.setText(configuredMessage);
		  imageLabel.setImage(this.getInfoImage());
		} else {
		  messageLabel.setText(message);
		  imageLabel.setImage(getErrorImage());
		}
				
	}

	private IRuntime getTargetRuntime() {
		try {
			IRuntime runtime = J2EEProjectUtilities.getServerRuntime(project);
			return runtime;
		} catch (CoreException e) {
			J2EEUIPlugin.logError(e);
		}
		return null;
	}

}
