package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.ui.WTPDataModelSynchHelper;

public class FlexibleModuleStandaloneGroup {

	protected WTPOperationDataModel model;
	protected WTPDataModelSynchHelper synchHelper;
	protected Button useFlexibleProject;
	
	/**
	 * Constructor
	 */
	public FlexibleModuleStandaloneGroup(Composite parent, WTPOperationDataModel model) {
		super();
		this.model = model;
		synchHelper = new WTPDataModelSynchHelper(model);
		buildComposites(parent);
	}  /**
     * @param parent
     */
    private void buildComposites(Composite parent) {
		// Add spacer
		Label spacer = new Label(parent, SWT.NONE);
		GridData gd1 = new GridData(GridData.FILL_HORIZONTAL);
		gd1.horizontalSpan = 1;
		spacer.setLayoutData(gd1);
		// Add annotations checkbox and label
		useFlexibleProject = new Button(parent, SWT.CHECK);
		useFlexibleProject.setText(J2EEUIMessages.getResourceString("Flexible_Structure")); //$NON-NLS-1$
		synchHelper.synchCheckbox(useFlexibleProject, J2EEModuleCreationDataModel.IS_FLEXIBLE_PROJECT, null);
		
		GridData gd2 = new GridData(GridData.FILL_HORIZONTAL);
		gd2.horizontalSpan = 2;
		useFlexibleProject.setLayoutData(gd2);
	}
}
