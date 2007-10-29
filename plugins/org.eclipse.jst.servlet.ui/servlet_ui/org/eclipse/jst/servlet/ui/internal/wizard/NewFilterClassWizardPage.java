package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.war.ui.util.WebFiltersGroupItemProvider;
import org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.wizard.NewJavaClassWizardPage;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class NewFilterClassWizardPage extends NewJavaClassWizardPage {
    private Button existingButton;
    private Label existingClassLabel;
    private Text existingClassText;
    private Button existingClassButton;
    private final static String[] FILTEREXTENSIONS = { "java" }; //$NON-NLS-1$
    
	public NewFilterClassWizardPage(IDataModel model, String pageName, String pageDesc, String pageTitle, String moduleType) {
		super(model, pageName, pageDesc, pageTitle, moduleType);
		//setFirstTimeToPage(false);
	}
	
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = super.createTopLevelComposite(parent);
		addSeperator(composite,3);
		createUseExistingGroup(composite);
//		createAnnotationsGroup(composite);
		return composite;
	}

	protected IProject getExtendedSelectedProject(Object selection) {
		if (selection instanceof WebFiltersGroupItemProvider) {
			WebApp webApp = (WebApp)((WebFiltersGroupItemProvider)selection).getParent();
			return ProjectUtilities.getProject(webApp);
		}
//		else if (selection instanceof CompressedJavaProject) {
//			return ((CompressedJavaProject)selection).getProject().getProject();
//		}
		return super.getExtendedSelectedProject(selection);
	}
	
	private void createUseExistingGroup(Composite composite) {
        existingButton = new Button(composite, SWT.CHECK);
        existingButton.setText(IWebWizardConstants.USE_EXISTING_FILTER_CLASS);
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        data.horizontalSpan = 3;
        existingButton.setLayoutData(data);
        synchHelper.synchCheckbox(existingButton, INewFilterClassDataModelProperties.USE_EXISTING_CLASS, null);
        existingButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                handleExistingButtonSelected();
            }
        });
        
        existingClassLabel = new Label(composite, SWT.LEFT);
        existingClassLabel.setText(IWebWizardConstants.CLASS_NAME_LABEL);
        existingClassLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
        existingClassLabel.setEnabled(false);

        existingClassText = new Text(composite, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
        existingClassText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        existingClassText.setEnabled(false);
        synchHelper.synchText(existingClassText, INewJavaClassDataModelProperties.CLASS_NAME, null);

        existingClassButton = new Button(composite, SWT.PUSH);
        existingClassButton.setText(IWebWizardConstants.BROWSE_BUTTON_LABEL);
        existingClassButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
        existingClassButton.setEnabled(false);
        existingClassButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                handleClassButtonSelected();
            }
        });
    }
	
	private void handleClassButtonSelected() {
        getControl().setCursor(new Cursor(getShell().getDisplay(), SWT.CURSOR_WAIT));
        IProject project = (IProject) model.getProperty(INewJavaClassDataModelProperties.PROJECT);
        IVirtualComponent component = ComponentCore.createComponent(project);
        MultiSelectFilteredFilterFileSelectionDialog ms = new MultiSelectFilteredFilterFileSelectionDialog(
                getShell(),
                IWebWizardConstants.NEW_FILTER_WIZARD_WINDOW_TITLE,
                IWebWizardConstants.CHOOSE_FILTER_CLASS, 
                FILTEREXTENSIONS, 
                false, 
                project);
        IContainer root = component.getRootFolder().getUnderlyingFolder();
        ms.setInput(root);
        ms.open();
        if (ms.getReturnCode() == Window.OK) {
            String qualifiedClassName = ""; //$NON-NLS-1$
            IType type = (IType) ms.getFirstResult();
            if (type != null) {
                qualifiedClassName = type.getFullyQualifiedName();
            }
            existingClassText.setText(qualifiedClassName);
        }
        getControl().setCursor(null);
    }
	
	private void handleExistingButtonSelected() {
        boolean enable = existingButton.getSelection();
        if (!enable) {
            existingClassText.setText(""); //$NON-NLS-1$
        }
        existingClassLabel.setEnabled(enable);
        existingClassButton.setEnabled(enable);
        packageText.setEnabled(!enable);
        packageButton.setEnabled(!enable);
        packageLabel.setEnabled(!enable);
        classText.setEnabled(!enable);
        classText.setText(""); //$NON-NLS-1$
        classLabel.setEnabled(!enable);
        superText.setEnabled(!enable);
        superButton.setEnabled(!enable);
        superLabel.setEnabled(!enable);
    }
	
//	private boolean isWebDocletProject() {
//		return false;
//	}
}
