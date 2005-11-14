package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import org.eclipse.jst.common.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.FacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EjbFacetProjectCreationDataModelProvider extends
		FacetProjectCreationDataModelProvider {

	public EjbFacetProjectCreationDataModelProvider() {
		super();
	}

	public void init() {
		super.init();
		FacetDataModelMap map = (FacetDataModelMap) getProperty(FACET_DM_MAP);
		IDataModel javaFacet = DataModelFactory.createDataModel(new JavaFacetInstallDataModelProvider());
		map.add(javaFacet);
		IDataModel webFacet = DataModelFactory.createDataModel(new EjbFacetInstallDataModelProvider());
		map.add(webFacet);
	}
	
}
