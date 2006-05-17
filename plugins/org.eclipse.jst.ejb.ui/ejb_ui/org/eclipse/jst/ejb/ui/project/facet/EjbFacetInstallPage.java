package org.eclipse.jst.ejb.ui.project.facet;

import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.IEjbFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleFacetInstallPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;



public class EjbFacetInstallPage extends J2EEModuleFacetInstallPage 
	implements IEjbFacetInstallDataModelProperties{

    private static final String MODULE_NAME_UI = J2EEUIMessages.getResourceString(J2EEUIMessages.NAME_LABEL);
    
	private Text configFolder;
	private Label configFolderLabel;
	protected Button addClient;	
    protected Text clientNameText = null;  
	private Label clientJarURILabel;
	private Text clientJarURI;
    
	public EjbFacetInstallPage() {
		super("ejb.facet.install.page"); //$NON-NLS-1$
		setTitle(EJBUIMessages.pageTitle);
		setDescription(EJBUIMessages.pageDescription);
	}
	
	protected String[] getValidationPropertyNames() {
		return new String[]{EAR_PROJECT_NAME, CREATE_CLIENT, CLIENT_NAME, CLIENT_SOURCE_FOLDER, CLIENT_URI};
	}

		
	protected Composite createTopLevelComposite(Composite parent) {
		setInfopopID(IJ2EEUIContextIds.NEW_EJB_WIZARD_P3);
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		//setupEarControl(composite);
		
		this.configFolderLabel = new Label(composite, SWT.NONE);
		this.configFolderLabel.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.SOURCEFOLDER));
		this.configFolderLabel.setLayoutData(gdhfill());
		
		this.configFolder = new Text(composite, SWT.BORDER);
		this.configFolder.setLayoutData(gdhfill());
		this.configFolder.setData("label", this.configFolderLabel); //$NON-NLS-1$
		synchHelper.synchText(configFolder, CONFIG_FOLDER, null);
		
		createEJBClientGroup( composite );
		createProjectNameGroup( composite );
		createClientJarURISection( composite );
		return composite;
	}

	private void createEJBClientGroup(Composite parent) {
		// Create Add Client checkbox
		new Label(parent, SWT.NONE);
		addClient = new Button(parent, SWT.CHECK);
		addClient.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.CREATE_EJB_CLIENT_JAR));
		synchHelper.synchCheckbox(addClient, CREATE_CLIENT, null);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		addClient.setLayoutData(gd);
		synchHelper.synchCheckbox(addClient, CREATE_CLIENT, null);
	}
	
    private void createProjectNameGroup(Composite parent) {
        // set up project name label
        Label projectNameLabel = new Label(parent, SWT.NONE);
        projectNameLabel.setText(MODULE_NAME_UI);
        GridData data = new GridData();
        projectNameLabel.setLayoutData(data);
        // set up project name entry field
        clientNameText = new Text(parent, SWT.BORDER);
        data = new GridData(GridData.FILL_HORIZONTAL);
        //data.widthHint = SIZING_TEXT_FIELD_WIDTH;
        clientNameText.setLayoutData(data);
        new Label(parent, SWT.NONE); // pad
        synchHelper.synchText(clientNameText, CLIENT_NAME, new Control[]{projectNameLabel});
        clientNameText.setFocus();
    }
    
    private void createClientJarURISection(Composite parent) {
        // set up project name label
    	clientJarURILabel = new Label(parent, SWT.NONE);
    	clientJarURILabel.setText(EJBUIMessages.Client_JAR_URI + " "); //$NON-NLS-1$
        GridData data = new GridData();
        clientJarURILabel.setLayoutData(data);

        clientJarURI = new Text(parent, SWT.BORDER);
        data = new GridData(GridData.FILL_HORIZONTAL);
        //data.widthHint = SIZING_TEXT_FIELD_WIDTH;
        clientJarURI.setLayoutData(data);
        new Label(parent, SWT.NONE); // pad
		synchHelper.synchText(clientJarURI, CLIENT_URI, new Control[]{clientJarURILabel});
    }    
}
