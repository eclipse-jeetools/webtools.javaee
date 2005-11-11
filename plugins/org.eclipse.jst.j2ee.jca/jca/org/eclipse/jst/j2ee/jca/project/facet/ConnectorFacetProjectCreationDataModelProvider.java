package org.eclipse.jst.j2ee.jca.project.facet;

import org.eclipse.jst.common.project.facet.IJavaFacetInstallDataModelProperties;
import org.eclipse.jst.common.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.FacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class ConnectorFacetProjectCreationDataModelProvider extends FacetProjectCreationDataModelProvider {

	public ConnectorFacetProjectCreationDataModelProvider() {
		super();
	}
	
	public void init() {
		super.init();
		FacetDataModelMap map = (FacetDataModelMap) getProperty(FACET_DM_MAP);
		IDataModel javaFacet = DataModelFactory.createDataModel(new JavaFacetInstallDataModelProvider());
		map.add(javaFacet);
		IDataModel jcaFacet = DataModelFactory.createDataModel(new ConnectorFacetInstallDataModelProvider());
		map.add(jcaFacet);
		javaFacet.setProperty(IJavaFacetInstallDataModelProperties.SOURCE_FOLDER_NAME,jcaFacet.getStringProperty(IConnectorFacetInstallDataModelProperties.CONFIG_FOLDER));
	}

}
