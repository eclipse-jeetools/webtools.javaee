package org.eclipse.jst.servlet.ui.internal.wizard;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEEditorUtility;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.operations.NewFilterClassDataModelProvider;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.servlet.ui.IWebUIContextIds;
import org.eclipse.jst.servlet.ui.internal.plugin.ServletUIPlugin;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

/**
 * New servlet filter wizard
 */
public class AddFilterWizard extends NewWebWizard {

	private static final String PAGE_ONE = "pageOne"; //$NON-NLS-1$
    private static final String PAGE_TWO = "pageTwo"; //$NON-NLS-1$
    
	public AddFilterWizard(IDataModel model) {
		super(model);
        setWindowTitle(IWebWizardConstants.ADD_FILTER_WIZARD_WINDOW_TITLE);
        setDefaultPageImageDescriptor(getFilterWizBan());
	}
	
    public AddFilterWizard() {
        this(null);
    }
    
    /* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void doAddPages() {
		NewFilterClassWizardPage page1 = new NewFilterClassWizardPage(
		        getDataModel(),
		        PAGE_ONE,
		        IWebWizardConstants.NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC,
				IWebWizardConstants.ADD_FILTER_WIZARD_PAGE_TITLE, 
				J2EEProjectUtilities.DYNAMIC_WEB);
//		page1.setInfopopID(IWebUIContextIds.WEBEDITOR_FILTER_PAGE_ADD_FILTER_WIZARD_1);
		addPage(page1);

		AddFilterWizardPage page2 = new AddFilterWizardPage(getDataModel(), PAGE_TWO);
//		page2.setInfopopID(IWebUIContextIds.WEBEDITOR_FILTER_PAGE_ADD_FILTER_WIZARD_2);
		addPage(page2);
	}
	
	/* (non-Javadoc)
     * @see org.eclipse.jem.util.ui.wizard.WTPWizard#runForked()
     */
    protected boolean runForked() {
        return false;
    }
	
    public boolean canFinish() {
        return getDataModel().isValid();
    }
    
	@Override
	protected IDataModelProvider getDefaultProvider() {
		return new NewFilterClassDataModelProvider();
	}

//	protected IStructuredSelection getCurrentSelection() {
//		IWorkbenchWindow window = J2EEUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow();
//		if (window != null) {
//			ISelection selection = window.getSelectionService().getSelection();
//			if (selection instanceof IStructuredSelection) {
//				return (IStructuredSelection) selection;
//			}
//		}
//		return null;
//	}

    @Override
    protected void postPerformFinish() throws InvocationTargetException {
      //open new filter class in java editor
        WebArtifactEdit artifactEdit = null;
        try {
            String className = getDataModel().getStringProperty(INewJavaClassDataModelProperties.QUALIFIED_CLASS_NAME);
            IProject p = (IProject) getDataModel().getProperty(INewJavaClassDataModelProperties.PROJECT);
            // filter class
            IJavaProject javaProject = J2EEEditorUtility.getJavaProject(p);
            IFile file = (IFile) javaProject.findType(className).getResource();
            openEditor(file);
        } catch (Exception cantOpen) {
            ServletUIPlugin.log(cantOpen);
        } finally {
            if (artifactEdit!=null)
                artifactEdit.dispose();
        }
    }
	
    private void openEditor(final IFile file) {
        if (file != null) {
            getShell().getDisplay().asyncExec(new Runnable() {
                public void run() {
                    try {
                        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                        IDE.openEditor(page, file, true);
                    }
                    catch (PartInitException e) {
                        ServletUIPlugin.log(e);
                    }
                }
            });
        }
    }
    
    private ImageDescriptor getFilterWizBan() {
		URL url = (URL) J2EEPlugin.getDefault().getImage("newfilter_wiz"); //$NON-NLS-1$
		return ImageDescriptor.createFromURL(url);
	}
}
