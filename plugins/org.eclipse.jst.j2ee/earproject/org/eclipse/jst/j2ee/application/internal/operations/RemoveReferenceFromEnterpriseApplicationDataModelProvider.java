/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.wst.common.componentcore.datamodel.properties.IAddReferenceDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.RemoveReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class RemoveReferenceFromEnterpriseApplicationDataModelProvider extends RemoveReferenceComponentsDataModelProvider implements IAddComponentToEnterpriseApplicationDataModelProperties {

	public RemoveReferenceFromEnterpriseApplicationDataModelProvider() {
		super();
	}

	@Override
	public IDataModelOperation getDefaultOperation() {
		return new RemoveReferenceFromEnterpriseApplicationOperation(model);
	}
	
	@Override
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(IAddReferenceDataModelProperties.SOURCE_COMPONENT);
		propertyNames.add(IAddReferenceDataModelProperties.TARGET_REFERENCE_LIST);
		return propertyNames;
	}

	public class RemoveReferenceFromEnterpriseApplicationOperation extends RemoveComponentFromEnterpriseApplicationOperation {

		public RemoveReferenceFromEnterpriseApplicationOperation(IDataModel model) {
			super(model);
			setMissingProperties();
		}
		
		protected void setMissingProperties() {
			List<IVirtualReference> refs = (List<IVirtualReference>)model.getProperty(IAddReferenceDataModelProperties.TARGET_REFERENCE_LIST);
			List<IVirtualComponent> compList = new ArrayList<IVirtualComponent>();
			Map<IVirtualComponent, String> uriMap = new HashMap<IVirtualComponent, String>();
			IVirtualReference currentRef;
			for( int i = 0; i < refs.size(); i++ ) {
				currentRef = refs.get(i);
				compList.add(currentRef.getReferencedComponent());
				uriMap.put(currentRef.getReferencedComponent(), currentRef.getArchiveName());
				//  This might not be the best line, but since the parent class only has room 
				// for one deploy path but multiple URI's this is a deficiency in the parent.
				// A fix would be to clone AddComponentToEnterpriseApplicationOp 
				// and make it extend AddReferencesOp
		        model.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH, currentRef.getRuntimePath().toString());
			}
			model.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, compList);
			model.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, model.getProperty(IAddReferenceDataModelProperties.SOURCE_COMPONENT));
		}
	}
}
