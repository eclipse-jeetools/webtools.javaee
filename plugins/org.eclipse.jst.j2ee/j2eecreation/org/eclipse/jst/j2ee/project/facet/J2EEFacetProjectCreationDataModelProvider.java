package org.eclipse.jst.j2ee.project.facet;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.wst.common.componentcore.datamodel.FacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModelProviderNew;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class J2EEFacetProjectCreationDataModelProvider extends FacetProjectCreationDataModelProvider implements IJ2EEFacetProjectCreationDataModelProperties {

	public J2EEFacetProjectCreationDataModelProvider() {
		super();
	}

	public void init() {
		super.init();
	}

	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(EAR_PROJECT_NAME);
		names.add(ADD_TO_EAR);
		names.add(MODULE_URI);
		return names;
	}

	public boolean propertySet(String propertyName, Object propertyValue) {
		if (EAR_PROJECT_NAME.equals(propertyName) || ADD_TO_EAR.equals(propertyName)) {
			if (getBooleanProperty(ADD_TO_EAR)) {
				IStatus status = validateEAR(model.getStringProperty(EAR_PROJECT_NAME));
				if (status.isOK()) {
					IProject earProject = ProjectUtilities.getProject(getStringProperty(EAR_PROJECT_NAME));
					if (earProject != null) {
						IFacetedProject facetdEarProject;
						try {
							facetdEarProject = ProjectFacetsManager.create(earProject);
							if (facetdEarProject != null) {
								setProperty(FACET_RUNTIME, facetdEarProject.getRuntime());
							}
						} catch (CoreException e) {
							e.printStackTrace();
						}
					}
				}
			}
			model.notifyPropertyChange(FACET_RUNTIME, IDataModel.ENABLE_CHG);
		}
		return super.propertySet(propertyName, propertyValue);
	}

	public boolean isPropertyEnabled(String propertyName) {
		if (FACET_RUNTIME.equals(propertyName)) {
			if (getBooleanProperty(ADD_TO_EAR)) {
				IStatus status = validateEAR(model.getStringProperty(EAR_PROJECT_NAME));
				if (status.isOK()) {
					IProject earProject = ProjectUtilities.getProject(getStringProperty(EAR_PROJECT_NAME));
					if (earProject != null) {
						IFacetedProject facetdEarProject;
						try {
							facetdEarProject = ProjectFacetsManager.create(earProject);
							if (facetdEarProject != null) {
								return false;
							}
						} catch (CoreException e) {
							e.printStackTrace();
						}
					}
				}
			}
			return true;
		}
		return super.isPropertyEnabled(propertyName);
	}

	public IStatus validate(String propertyName) {
		if (ADD_TO_EAR.equals(propertyName) || EAR_PROJECT_NAME.equals(propertyName)) {
			if (model.getBooleanProperty(ADD_TO_EAR)) {
				IStatus status = validateEAR(model.getStringProperty(EAR_PROJECT_NAME));
				if (!status.isOK())
					return status;
			} else
				return OK_STATUS;

		}
		return super.validate(propertyName);
	}

	protected IStatus validateEAR(String earName) {
		if (earName.indexOf("#") != -1 || earName.indexOf("/") != -1) { //$NON-NLS-1$ //$NON-NLS-2$
			String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.ERR_INVALID_CHARS);
			return WTPCommonPlugin.createErrorStatus(errorMessage);
		} else if (earName == null || earName.equals("")) { //$NON-NLS-1$
			String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.ERR_EMPTY_MODULE_NAME);
			return WTPCommonPlugin.createErrorStatus(errorMessage);
		}
		return (ProjectCreationDataModelProviderNew.validateProjectName(earName));
	}

}
