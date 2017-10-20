/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.archive.IArchiveExportParticipant;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.ArchiveExportParticipantsExtensionPoint;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;
import org.eclipse.wst.common.project.facet.core.runtime.RuntimeManager;

public abstract class J2EEArtifactExportDataModelProvider extends AbstractDataModelProvider implements IJ2EEComponentExportDataModelProperties {

    private static final class ArchiveExportParticipantData implements IArchiveExportParticipantData
    {
        private String id = null;
        private IArchiveExportParticipant extension = null;
        private IDataModel datamodel = null;

        @Override
		public String getId()
        {
            return this.id;
        }
        
        @Override
		public IArchiveExportParticipant getParticipant()
        {
            return this.extension;
        }
        
        @Override
		public IDataModel getDataModel()
        {
            return this.datamodel;
        }
    }

    public HashMap componentMap;

	public J2EEArtifactExportDataModelProvider() {
		super();
	}

	@Override
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(PROJECT_NAME);
		propertyNames.add(ARCHIVE_DESTINATION);
		propertyNames.add(EXPORT_SOURCE_FILES);
		propertyNames.add(OVERWRITE_EXISTING);
		propertyNames.add(RUN_BUILD);
		propertyNames.add(COMPONENT);
		propertyNames.add(OPTIMIZE_FOR_SPECIFIC_RUNTIME);
		propertyNames.add(RUNTIME);
		propertyNames.add(RUNTIME_SPECIFIC_PARTICIPANTS);
		return propertyNames;
	}

	protected abstract String getProjectType();

	protected abstract String getWrongComponentTypeString(String projectName);

	protected abstract String getModuleExtension();

	@Override
	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(ARCHIVE_DESTINATION)) {
			return ""; //$NON-NLS-1$
		} else if (propertyName.equals(EXPORT_SOURCE_FILES)) {
			return Boolean.FALSE;
		} else if (propertyName.equals(OVERWRITE_EXISTING)) {
			return Boolean.FALSE;
		} else if (propertyName.equals(RUN_BUILD)) {
			return Boolean.TRUE;
		} else if (propertyName.equals(RUNTIME_SPECIFIC_PARTICIPANTS)) {
		    return new ArrayList<IArchiveExportParticipantData>();
		} else if(propertyName.equals(OPTIMIZE_FOR_SPECIFIC_RUNTIME)){
			return Boolean.FALSE;
		}
		return super.getDefaultProperty(propertyName);
	}
	
	@Override
	public boolean isPropertyEnabled( final String propertyName )
	{
	    if( propertyName.equals( OPTIMIZE_FOR_SPECIFIC_RUNTIME ) )
	    {
	        return getDataModel().getValidPropertyDescriptors( RUNTIME ).length > 0;
	    }
	    else if( propertyName.equals( RUNTIME ) )
	    {
	        return getBooleanProperty(OPTIMIZE_FOR_SPECIFIC_RUNTIME); 
	    }
	    
	    return true;
	}
	
	@Override
	public boolean propertySet(String propertyName, Object propertyValue) 
	{
		boolean set = super.propertySet(propertyName, propertyValue);
		final IDataModel dm = getDataModel();
		
		if (propertyName.equals(PROJECT_NAME)) {
			if (getComponentMap().isEmpty())
				intializeComponentMap();
			IVirtualComponent component = (IVirtualComponent) getComponentMap().get(propertyValue);
			if (null != component && component.getName().equals(propertyValue)) {
				setProperty(COMPONENT, component);
			} else {
				setProperty(COMPONENT, null);
			}

            dm.notifyPropertyChange( RUNTIME, IDataModel.VALID_VALUES_CHG );
            
            IFacetedProject fproj = null;
            
            if( component != null )
            {
                try
                {
                    fproj = ProjectFacetsManager.create( component.getProject() );
                }
                catch( CoreException e )
                {
                    J2EEPlugin.logError( -1, e.getMessage(), e );
                }
            }
            
            boolean optimize = false;
            IRuntime runtime = null;
            
            if( fproj !=  null )
            {
                runtime = fproj.getPrimaryRuntime();
                
                if( runtime != null )
                {
                    optimize = true;
                }
                else
                {
                    final DataModelPropertyDescriptor[] validValues 
                        = dm.getValidPropertyDescriptors( RUNTIME );
                    
                    if( validValues.length > 0 )
                    {
                        runtime = (IRuntime) validValues[ 0 ].getPropertyValue();
                    }
                }
            }

            setProperty( OPTIMIZE_FOR_SPECIFIC_RUNTIME, optimize);
            setProperty( RUNTIME, runtime );
		}
		else if( propertyName.equals( OPTIMIZE_FOR_SPECIFIC_RUNTIME ) )
		{
            dm.notifyPropertyChange( RUNTIME, IDataModel.ENABLE_CHG );
		}
		else if( propertyName.equals( RUNTIME ) )
		{
            final List<IArchiveExportParticipantData> currentExtDataList
                = (List<IArchiveExportParticipantData>) getProperty( RUNTIME_SPECIFIC_PARTICIPANTS );
            
            if( currentExtDataList != null )
            {
                for( IArchiveExportParticipantData extData : currentExtDataList )
                {
                    dm.removeNestedModel( extData.getId() );
                }
            }
            
            final List<IArchiveExportParticipantData> extensions = new ArrayList<IArchiveExportParticipantData>();
		    
		    if( propertyValue != null )
		    {
		        final IProject project = getProject();
		        
		        if( project != null)
		        {
		            final IRuntime runtime = (IRuntime) propertyValue;
		            
		            for( ArchiveExportParticipantsExtensionPoint.ParticipantInfo partInfo 
		                 : ArchiveExportParticipantsExtensionPoint.getExtensions( project, runtime ) )
		            {
                        ArchiveExportParticipantData partData = new ArchiveExportParticipantData();
                        partData.id = partInfo.getId();
                        partData.extension = partInfo.loadParticipant();
		                
		                if( partData.extension != null )
		                {
		                    try
		                    {
		                        partData.datamodel = partData.extension.createDataModel( dm );
		                        dm.addNestedModel( partData.id, partData.datamodel );
		                    }
		                    catch( Exception e )
		                    {
		                        J2EEPlugin.logError( -1, e.getMessage(), e );
		                        partData = null;
		                    }
		                }
		                else
		                {
		                    partData = null;
		                }
		                
		                if( partData != null )
		                {
		                    extensions.add( partData );
		                }
		            }
		        }
		    }
		    
		    setProperty( RUNTIME_SPECIFIC_PARTICIPANTS, Collections.unmodifiableList( extensions ) );
		}
		
		return set;
	}

	public HashMap getComponentMap() {
		if (componentMap == null)
			componentMap = new HashMap();
		return componentMap;
	}

	public void intializeComponentMap() {
		IVirtualComponent[] comps = ComponentUtilities.getAllWorkbenchComponents();
		for (int i = 0; i < comps.length; i++) {
			getComponentMap().put(comps[i].getName(), comps[i]);
		}
	}
	
	@Override
	public DataModelPropertyDescriptor getPropertyDescriptor(String propertyName){
		if( propertyName.equals( RUNTIME ) )
		{
			IRuntime runtime = (IRuntime) getProperty(RUNTIME);
			if(runtime != null)
				return new DataModelPropertyDescriptor(runtime, runtime.getLocalizedName());
		}
		return super.getPropertyDescriptor(propertyName);
	}
	
	/**
	 * Populate the resource name combo with projects that are not encrypted.
	 */
	@Override
	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		// TODO: populate valid components
		if (propertyName.equals(PROJECT_NAME)) {
			List componentNames = new ArrayList();
			IVirtualComponent[] wbComps = ComponentUtilities.getAllWorkbenchComponents();

			List relevantComponents = new ArrayList();
			for (int i = 0; i < wbComps.length; i++) {
				if (J2EEProjectUtilities.getJ2EEProjectType(wbComps[i].getProject()).equals(getProjectType())) {
					relevantComponents.add(wbComps[i]);
					getComponentMap().put(wbComps[i].getName(), wbComps[i]);
				}
			}

			if (relevantComponents.size() == 0)
				return null;

			for (int j = 0; j < relevantComponents.size(); j++) {
				componentNames.add(((IVirtualComponent) relevantComponents.get(j)).getName());
			}
			String[] names = (String[]) componentNames.toArray(new String[componentNames.size()]);

			return DataModelPropertyDescriptor.createDescriptors(names);
		}
		else if( propertyName.equals( RUNTIME ) )
		{
            final List<IRuntime> runtimes = new ArrayList<IRuntime>();
		    final IVirtualComponent component = (IVirtualComponent) getProperty( COMPONENT );
		    
	        if( component != null )
            {
                try
                {
                    final IFacetedProject fproj = ProjectFacetsManager.create( component.getProject() );
                    
                    for( IRuntime runtime : RuntimeManager.getRuntimes() )
                    {
                        if( fproj.isTargetable( runtime ) )
                        {
                            runtimes.add( runtime );
                        }
                    }
                    
                    DataModelPropertyDescriptor[] descriptors = new DataModelPropertyDescriptor[runtimes.size()];
        			Iterator iterator = runtimes.iterator();
        			for (int i = 0; i < descriptors.length; i++) {
        				IRuntime runtime = (IRuntime) iterator.next();
        				descriptors[i] = new DataModelPropertyDescriptor(runtime, runtime.getLocalizedName());
        			}
        			if(descriptors.length > 2){
        				Arrays.sort(descriptors, 0, descriptors.length, new Comparator() {
        					@Override
							public int compare(Object arg0, Object arg1) {
        						DataModelPropertyDescriptor d1 = (DataModelPropertyDescriptor)arg0;
        						DataModelPropertyDescriptor d2 = (DataModelPropertyDescriptor)arg1;
        						return d1.getPropertyDescription().compareTo(d2.getPropertyDescription());
        					}
        				});
        			}
        			return descriptors;
                }
                catch( CoreException e )
                {
                    J2EEPlugin.logError( -1, e.getMessage(), e );
                }
            }
	        
	        return DataModelPropertyDescriptor.createDescriptors(null);    
		}
		
		return super.getValidPropertyDescriptors(propertyName);
		// (ProjectUtilities.getProjectNamesWithoutForwardSlash((String[])
		// projectsWithNature.toArray(new String[projectsWithNature.size()])));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	@Override
	public IStatus validate(String propertyName) {
		if (PROJECT_NAME.equals(propertyName)) {
			String projectName = (String) model.getProperty(PROJECT_NAME);
			if (projectName == null || projectName.equals("")) //$NON-NLS-1$
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.MODULE_EXISTS_ERROR));
			IVirtualComponent component = (IVirtualComponent) componentMap.get(projectName);
			if (component == null) {
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.MODULE_EXISTS_ERROR));
			}
			if (!J2EEProjectUtilities.getJ2EEProjectType(component.getProject()).equals(getProjectType())) {
				return WTPCommonPlugin.createErrorStatus(getWrongComponentTypeString(projectName));
			}
		}
		if (ARCHIVE_DESTINATION.equals(propertyName)) {
			String archiveLocation = (String) model.getProperty(ARCHIVE_DESTINATION);
			if (!model.isPropertySet(ARCHIVE_DESTINATION) || archiveLocation.equals("")) { //$NON-NLS-1$
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_INVALID)); //);
			} else if (model.isPropertySet(ARCHIVE_DESTINATION) && !validateModuleType(archiveLocation)) {
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_ARCHIVE_SHOULD_END_WITH, new Object[]{getModuleExtension()})); //);
			} else if (model.isPropertySet(ARCHIVE_DESTINATION)) {
				IStatus tempStatus = validateLocation(archiveLocation);
				if (tempStatus != OK_STATUS)
					return tempStatus;
			}
		}
		if (ARCHIVE_DESTINATION.equals(propertyName) || OVERWRITE_EXISTING.equals(propertyName)) {
			String location = (String) getProperty(ARCHIVE_DESTINATION);
			if (checkForExistingFileResource(location)) {
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.RESOURCE_EXISTS_ERROR, new Object[]{location}));
			}
		}
		return OK_STATUS;
	}

	private IStatus validateLocation(String archiveLocation) {
		IPath path = null;
		try {
			path = new Path(archiveLocation);
		} catch (IllegalArgumentException ex) {
			return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_INVALID));
		}
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IStatus status = workspace.validateName(path.lastSegment(), IResource.FILE);
		if (!status.isOK()) {
			return status;
		}
		String device = path.getDevice();
		if (device == null)
			return OK_STATUS;
		if (device.length() == 1 && device.charAt(0) == IPath.DEVICE_SEPARATOR)
			return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_INVALID));

		if (!path.toFile().canWrite()) {
			if (path.toFile().exists()) {
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.IS_READ_ONLY));
			}
			boolean OK = false;
			path = path.removeLastSegments(1);
			for (int i = 1; !OK && i < 20 && path.segmentCount() > 0; i++) {
				if (path.toFile().exists()) {
					OK = true;
				}
				status = workspace.validateName(path.lastSegment(), IResource.FOLDER);
				if (!status.isOK()) {
					return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_INVALID));
				}
				path = path.removeLastSegments(1);
			}
		}

		return OK_STATUS;
	}

	private boolean checkForExistingFileResource(String fileName) {
		if (!model.getBooleanProperty(OVERWRITE_EXISTING)) {
			java.io.File externalFile = new java.io.File(fileName);
			if (externalFile.exists())
				return true;
		}
		return false;
	}

	private boolean validateModuleType(String archive) {
		if ((archive.length() < 4) || (!(archive.substring(archive.length() - 4, archive.length()).equalsIgnoreCase(getModuleExtension())))) {
			return false;
		}
		return true;
	}
	
	private IProject getProject()
	{
	    final IVirtualComponent component = (IVirtualComponent) getProperty( COMPONENT );
	    
	    if( component != null )
	    {
	        return component.getProject();
	    }
	    return null;
	}

}
