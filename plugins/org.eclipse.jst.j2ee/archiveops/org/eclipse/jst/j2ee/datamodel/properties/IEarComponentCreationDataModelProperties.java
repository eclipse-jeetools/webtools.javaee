/*******************************************************************************
 * Copyright (c) 2003, 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.datamodel.properties;

/**
 * <p>
 * IEarComponentCreationDataModelProperties provides properties to the
 * EarComponentCreationDataModelProvider as well as all extending interfaces extending
 * IEarComponentCreationDataModelProperties
 * 
 * @see org.eclipse.jst.j2ee.internal.earcreation.EarComponentCreationDataModelProvider
 *      </p>
 *      <p>
 *      This interface is not intended to be implemented by clients.
 *      </p>
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * @see org.eclipse.wst.common.frameworks.datamodel.DataModelFactory
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProperties
 * @see org.eclipse.jst.j2ee.datamodel.properties.IJavaComponentCreationDataModelProperties
 * @see org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties
 * @plannedfor 1.0
 */

public interface IEarComponentCreationDataModelProperties extends IJ2EEComponentCreationDataModelProperties {
	/**
	 * A java.util.List of IProject J2EEModules which are to be added to the EAR.
	 */
	public static final String J2EE_PROJECTS_LIST = "IEarComponentCreationDataModel.J2EE_PROJECTS_LIST"; //$NON-NLS-1$
	public static final String JAVA_PROJECT_LIST = "IEarComponentCreationDataModel.JAVA_PROJECT_LIST"; //$NON-NLS-1$	

}
