package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.util.Set;

import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.CreationConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.J2EEModuleFacetInstallDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class EjbFacetInstallDataModelProvider
		extends J2EEModuleFacetInstallDataModelProvider 
		implements IEjbFacetInstallDataModelProperties{

	public static final IProjectFacetVersion EJB_11 = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EJB_MODULE).getVersion("1.1"); //$NON-NLS-1$
	public static final IProjectFacetVersion EJB_20 = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EJB_MODULE).getVersion("2.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion EJB_21 = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EJB_MODULE).getVersion("2.1"); //$NON-NLS-1$	
	
	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(CONFIG_FOLDER);
		names.add(CREATE_CLIENT);
		names.add(CLIENT_NAME);
		names.add(CLIENT_SOURCE_FOLDER);
		names.add(CLIENT_URI);
		return names;
	}
	
	public Object getDefaultProperty(String propertyName) {
		if(propertyName.equals(FACET_ID)){
			return J2EEProjectUtilities.EJB;
		}else if (propertyName.equals(CREATE_CLIENT)) {
			return Boolean.TRUE;
		} else if (propertyName.equals(CLIENT_SOURCE_FOLDER)) {
			return CreationConstants.DEFAULT_EJB_SOURCE_FOLDER;
		}else if (propertyName.equals(CONFIG_FOLDER)){
			return CreationConstants.DEFAULT_EJB_SOURCE_FOLDER;
		}else if (propertyName.equals(CLIENT_NAME)){
			String projectName = model.getStringProperty(FACET_PROJECT_NAME);
			return projectName + "Client";
		}else if (propertyName.equals(CLIENT_URI)){
			String projectName = model.getStringProperty(FACET_PROJECT_NAME);
			return projectName + "Client.jar";
		}
		return super.getDefaultProperty(propertyName);
	}

	
	protected int convertFacetVersionToJ2EEVersion(IProjectFacetVersion version) {
		if (EJB_11.equals(version)) {
			return J2EEVersionConstants.J2EE_1_2_ID;
		} else if (EJB_20.equals(version)) {
			return J2EEVersionConstants.J2EE_1_3_ID;
		}
		return J2EEVersionConstants.J2EE_1_4_ID;
	}
		
	public boolean isPropertyEnabled(String propertyName) {
		if ( CLIENT_NAME.equals(propertyName )) {
			return getBooleanProperty(CREATE_CLIENT);
		}else if( CLIENT_URI.equals(propertyName )){
			return getBooleanProperty(CREATE_CLIENT);
		}
		return super.isPropertyEnabled(propertyName);
	}

	
	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean status = super.propertySet(propertyName, propertyValue);
		
		if (propertyName.equals(CREATE_CLIENT)){
	    	model.notifyPropertyChange(CLIENT_NAME, IDataModel.ENABLE_CHG);
	    	model.notifyPropertyChange(CLIENT_URI, IDataModel.ENABLE_CHG);
	    }else if (propertyName.equals(FACET_PROJECT_NAME)){
	    	model.setStringProperty(CLIENT_NAME, (String)propertyValue + "Client");
	    	model.setStringProperty(CLIENT_URI, (String)propertyValue + "Client.jar");
	    }
		return status;
	}	
	
}
