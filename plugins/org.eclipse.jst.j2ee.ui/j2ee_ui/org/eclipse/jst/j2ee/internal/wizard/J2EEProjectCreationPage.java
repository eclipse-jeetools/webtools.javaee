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
/*
 * Created on Nov 7, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEArtifactCreationDataModelOld;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.help.WorkbenchHelp;
import org.eclipse.wst.common.frameworks.internal.operations.WTPPropertyDescriptor;
import org.eclipse.wst.common.frameworks.internal.ui.WTPWizardPage;
import org.eclipse.wst.server.ui.ServerUIUtil;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class J2EEProjectCreationPage extends WTPWizardPage {

	private static final boolean isWindows = SWT.getPlatform().toLowerCase().startsWith("win"); //$NON-NLS-1$

	protected NewProjectGroup projectNameGroup;
	protected Composite advancedComposite;
	protected Button advancedButton;
	protected boolean showAdvanced = false;
	protected AdvancedSizeController advancedController;
	protected boolean advancedControlsBuilt = false;

	/*
	 * @author DABERG
	 * 
	 * This type is responsible for setting the Shell size based on the showAdvanced flag. It will
	 * track the original size of the Shell even if the user resizes it. One problem that we may
	 * face is that the Shell size could change by the framework prior to the Shell being made
	 * visible but the page will already get an enter call. This means that we will need to set the
	 * Shell size based on the showAdvanced flag when the Shell resize event is called and the Shell
	 * is visible.
	 */
	private class AdvancedSizeController implements ControlListener {
		private int advancedHeight = -1;
		private Point originalSize;
		private boolean ignoreShellResize = false;

		private AdvancedSizeController(Shell aShell) {
			originalSize = aShell.getSize();
			aShell.addControlListener(this);
		}

		public void controlMoved(ControlEvent e) {
			//do nothing
		}

		public void controlResized(ControlEvent e) {
			if (!ignoreShellResize) {
				Control control = (Control) e.getSource();
				if (control.isVisible()) {
					originalSize = control.getSize();
					if (advancedHeight == -1)
						setShellSizeForAdvanced();
				}
			}
		}

		protected void resetOriginalShellSize() {
			setShellSize(originalSize.x, originalSize.y);
		}

		private void setShellSize(int x, int y) {
			ignoreShellResize = true;
			try {
				getShell().setSize(x, y);
			} finally {
				ignoreShellResize = false;
			}
		}

		protected void setShellSizeForAdvanced() {
			int height = calculateAdvancedShellHeight();
			if (height != -1)
				setShellSize(getShell().getSize().x, height);
		}

		private int calculateAdvancedShellHeight() {
			Point advancedCompSize = advancedComposite.getSize();
			if (advancedCompSize.x == 0)
				return -1;
			int height = computeAdvancedHeight();
			if (!showAdvanced && height != -1)
				height = height - advancedComposite.getSize().y;
			return height;
		}

		/*
		 * Compute the height with the advanced section showing. @return
		 */
		private int computeAdvancedHeight() {
			if (advancedHeight == -1) {
				Point controlSize = getControl().getSize();
				if (controlSize.x != 0) {
					int minHeight = originalSize.y - controlSize.y;
					Point pageSize = getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT);
					advancedHeight = pageSize.y + minHeight;
				}
			}
			return advancedHeight;
		}
	}

	public J2EEProjectCreationPage(J2EEArtifactCreationDataModelOld dataModel, String pageName) {
		super(dataModel, pageName);
	}

	protected J2EEArtifactCreationDataModelOld getJ2EEProjectCreationDataModel() {
		return (J2EEArtifactCreationDataModelOld) model;
	}

	protected void createProjectNameGroup(Composite parent) {
		projectNameGroup = new NewProjectGroup(parent, SWT.NULL, getJ2EEProjectCreationDataModel().getProjectDataModel());
	}

	protected Composite createTopLevelComposite(Composite parent) {
		Composite top = new Composite(parent, SWT.NONE);
		WorkbenchHelp.setHelp(top, getInfopopID());
		top.setLayout(new GridLayout());
		top.setData(new GridData(GridData.FILL_BOTH));
		Composite composite = new Composite(top, SWT.NONE);
		GridLayout layout = new GridLayout(3, false);
		composite.setLayout(layout);
		createProjectNameGroup(composite);
		Composite detail = new Composite(top, SWT.NONE);
		detail.setLayout(new GridLayout());
		detail.setData(new GridData(GridData.FILL_BOTH));
		createAdvancedComposite(detail);
		return top;
	}

	/**
	 * @param parent
	 */
	protected Composite createAdvancedComposite(Composite parent) {
		advancedControlsBuilt = true;
		advancedButton = new Button(parent, SWT.TOGGLE);
		setAdvancedLabelText();
		final Cursor hand = new Cursor(advancedButton.getDisplay(), SWT.CURSOR_HAND);
		advancedButton.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				hand.dispose();
			}
		});
		advancedComposite = new Composite(parent, SWT.NONE);
		//toggleAdvanced(false);
		GridLayout layout = new GridLayout(3, false);
		GridData data = new GridData();
		advancedComposite.setData(data);
		advancedComposite.setLayout(layout);
		advancedButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				toggleAdvanced(true);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				//do nothing
			}
		});
		advancedButton.addListener(SWT.MouseHover, new Listener() {
			public void handleEvent(Event event) {
				if (event.type == SWT.MouseHover)
					advancedButton.setCursor(hand);
			}
		});
		addToAdvancedComposite(advancedComposite);
		return advancedComposite;
	}

	/**
	 * @param advancedLabel
	 */
	private void setAdvancedLabelText() {
		if (advancedControlsBuilt) {
			if (showAdvanced)
				advancedButton.setText(J2EEUIMessages.getResourceString("J2EEProjectCreationPage_UI_0")); //$NON-NLS-1$
			else
				advancedButton.setText(J2EEUIMessages.getResourceString("J2EEProjectCreationPage_UI_1")); //$NON-NLS-1$
		}
	}

	/**
	 * @param advancedLabel
	 */
	protected void toggleAdvanced(boolean setSize) {
		if (advancedControlsBuilt) {
			showAdvanced = !showAdvanced;
			advancedComposite.setVisible(showAdvanced);
			setAdvancedLabelText();
			if (setSize && isWindows)
				advancedController.setShellSizeForAdvanced();
		}
	}

	protected void addToAdvancedComposite(Composite parent) {
		createVersionComposite(parent);
	}

	/**
	 * @param parent
	 */
	protected abstract void createVersionComposite(Composite parent);

	protected void createVersionComposite(Composite parent, String labelText, String versionProp) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(labelText);
		Combo versionCombo = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 305;
		versionCombo.setLayoutData(gridData);
		Control[] deps = new Control[]{label};
		synchHelper.synchCombo(versionCombo, versionProp, deps);
		new Label(parent, SWT.NONE); //pad
	}

	public static boolean launchNewRuntimeWizard(Shell shell, ServerTargetDataModel model) {
		WTPPropertyDescriptor[] preAdditionDescriptors = model.getValidPropertyDescriptors(ServerTargetDataModel.RUNTIME_TARGET_ID);
		boolean isOK = ServerUIUtil.showNewRuntimeWizard(shell, model.computeTypeId(), model.computeVersionId());
		if (isOK && model != null) {
			model.notifyValidValuesChange(ServerTargetDataModel.RUNTIME_TARGET_ID);
			WTPPropertyDescriptor[] postAdditionDescriptors = model.getValidPropertyDescriptors(ServerTargetDataModel.RUNTIME_TARGET_ID);
			Object[] preAddition = new Object[preAdditionDescriptors.length];
			for (int i = 0; i < preAddition.length; i++) {
				preAddition[i] = preAdditionDescriptors[i].getPropertyValue();
			}
			Object[] postAddition = new Object[postAdditionDescriptors.length];
			for (int i = 0; i < postAddition.length; i++) {
				postAddition[i] = postAdditionDescriptors[i].getPropertyValue();
			}
			Object newAddition = ProjectUtilities.getNewObject(preAddition, postAddition);
			if (newAddition != null)
				model.setProperty(ServerTargetDataModel.RUNTIME_TARGET_ID, newAddition);
		}
		return isOK;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#dispose()
	 */
	public void dispose() {
		super.dispose();
		projectNameGroup.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#enter()
	 */
	protected void enter() {
		if (advancedControlsBuilt) {
			if (isFirstTimeToPage)
				initializeAdvancedController();
			if (isWindows) {
				advancedController.setShellSizeForAdvanced();
			}
		}
		super.enter();
	}

	private void initializeAdvancedController() {
		advancedController = new AdvancedSizeController(getShell());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#exit()
	 */
	protected void exit() {
		if (advancedControlsBuilt && isWindows) {
			advancedController.resetOriginalShellSize();
		}
		super.exit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#storeDefaultSettings()
	 */
	public void storeDefaultSettings() {
		super.storeDefaultSettings();
		if (advancedControlsBuilt) {
			IDialogSettings settings = getDialogSettings();
			if (settings != null)
				settings.put(getShowAdvancedKey(), showAdvanced);
		}
	}

	protected String getShowAdvancedKey() {
		return getClass().getName() + "_SHOW_ADVANCED"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#restoreDefaultSettings()
	 */
	protected void restoreDefaultSettings() {
		super.restoreDefaultSettings();
		if (advancedControlsBuilt) {
			boolean hasServerTarget = model.getProperty(J2EEArtifactCreationDataModelOld.SERVER_TARGET_ID) != null;
			//Need to show advanced if there are no server targets.
			if (!hasServerTarget)
				showAdvanced = false;
			else {
				IDialogSettings settings = getDialogSettings();
				if (settings != null)
					showAdvanced = !settings.getBoolean(getShowAdvancedKey());
			}
			advancedButton.setSelection(!showAdvanced); //set opposite b/c toggleAdvanced(boolean)
			// will flip it
			toggleAdvanced(false);
		}
	}
}