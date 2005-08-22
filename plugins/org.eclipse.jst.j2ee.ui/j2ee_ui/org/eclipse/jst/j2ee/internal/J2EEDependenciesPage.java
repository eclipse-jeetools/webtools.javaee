package org.eclipse.jst.j2ee.internal;

import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;

public class J2EEDependenciesPage extends PropertyPage {
	
	public String DESCRIPTION = J2EEUIMessages.getResourceString("DESCRIPTION"); //$NON-NLS-1$

	public J2EEDependenciesPage() {
		super();
	}

	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginWidth = 0;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        createLabelsComposite(composite);
		return composite;
	
	}

	private void createLabelsComposite(Composite parent) {
		Composite labelsGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		labelsGroup.setLayout(layout);
		labelsGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label label = new Label(labelsGroup, SWT.NONE);
		label.setText(ManifestUIResourceHandler.getString("EAR_Modules")); //$NON-NLS-1$
		fillDescription(labelsGroup, ManifestUIResourceHandler.getString("EAR_Modules_Desc")); //$NON-NLS-1$
		
		label = new Label(labelsGroup, SWT.NONE);
		label.setText(ManifestUIResourceHandler.getString("J2EE_Modules")); //$NON-NLS-1$
		fillDescription(labelsGroup, ManifestUIResourceHandler.getString("J2EE_Modules_Desc")); //$NON-NLS-1$
		
		label = new Label(labelsGroup, SWT.NONE);
		label.setText(ManifestUIResourceHandler.getString("Web_Libraries")); //$NON-NLS-1$
		fillDescription(labelsGroup, ManifestUIResourceHandler.getString("Web_Libraries_Desc")); //$NON-NLS-1$
	}
	
	private void fillDescription(Composite c, String s) {
		GridData data = new GridData();
		data.horizontalSpan = 2;
		data.horizontalIndent = 15;
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		data.horizontalSpan = 2;
		data.horizontalIndent = 15;
		data.widthHint = 250;
		data.heightHint = 70;
		Text text = new Text(c, SWT.V_SCROLL | SWT.BORDER | SWT.MULTI | SWT.WRAP);
		text.setLayoutData(data);
		text.setTextLimit(80);
		text.setSize(300, 120);
		text.setEditable(false);
		text.setText(s);
	}

}
