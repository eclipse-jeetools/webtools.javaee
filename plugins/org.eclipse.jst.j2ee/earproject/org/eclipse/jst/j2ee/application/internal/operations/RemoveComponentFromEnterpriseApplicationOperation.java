/*******************************************************************************
 * Copyright (c) 2005, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.model.IEARModelProvider;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.jst.javaee.application.Module;
import org.eclipse.jst.javaee.application.internal.impl.ModuleImpl;
import org.eclipse.jst.jee.application.ICommonApplication;
import org.eclipse.jst.jee.application.ICommonModule;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.operation.RemoveReferenceComponentOperation;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class RemoveComponentFromEnterpriseApplicationOperation extends RemoveReferenceComponentOperation {


	public RemoveComponentFromEnterpriseApplicationOperation(IDataModel model) {
		super(model);
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		try {
			J2EEComponentClasspathUpdater.getInstance().pauseUpdates();
			updateEARDD(monitor);
			super.execute(monitor, info);
			return OK_STATUS;
		} finally {
			J2EEComponentClasspathUpdater.getInstance().resumeUpdates();
		}
	}

	protected void updateEARDD(final IProgressMonitor monitor) {
		final IVirtualComponent comp = (IVirtualComponent) model.getProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT);
		if (!comp.getProject().isAccessible())
			return;
		J2EEComponentClasspathUpdater.getInstance().queueUpdateEAR(comp.getProject());
		final IEARModelProvider earModel = (IEARModelProvider)ModelProviderManager.getModelProvider(comp.getProject());
		final IVirtualComponent ear = (IVirtualComponent) this.model.getProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT);
		StructureEdit se = null;
		try {
			se = StructureEdit.getStructureEditForWrite(comp.getProject());
			WorkbenchComponent earwc = se.getComponent();
			List list = (List) model.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					final IVirtualComponent wc = (IVirtualComponent) list.get(i);
					StructureEdit compse = null;
					try {
						compse = StructureEdit.getStructureEditForWrite(wc.getProject());
						if(compse != null) {
							final ReferencedComponent ref = AddComponentToEnterpriseApplicationOp.findReferencedComponent(earwc, wc, se, compse);
							
							// Determine if the EAR project has deployment descriptor, if so, then force save.
							IPath saveFlag = null;
							IVirtualFolder rootFolder = ear.getRootFolder();
							IPath path = new Path(J2EEConstants.APPLICATION_DD_URI);
							IVirtualFile vFile = rootFolder.getFile(path);
							if(vFile.exists())
								saveFlag = IModelProvider.FORCESAVE;
							
							earModel.modify(new Runnable() {
								public void run() {
									ICommonApplication application = (ICommonApplication)earModel.getModelObject();
									if (application == null)
										return;
									IVirtualComponent moduleComponent = wc.getComponent();
									if(!moduleComponent.isBinary()){
										J2EEComponentClasspathUpdater.getInstance().queueUpdateModule(moduleComponent.getProject());
									}
									
									Object module = null;
									String moduleURI = null;
									if(ref != null) {
										module = getModule(earModel, ref);
										if(module != null){										
											if(module instanceof org.eclipse.jst.j2ee.application.internal.impl.ModuleImpl) {
												moduleURI = ((org.eclipse.jst.j2ee.application.internal.impl.ModuleImpl)module).getUri();
											} else if (module instanceof ModuleImpl) {
												moduleURI = ((ModuleImpl)module).getUri();
											}
											
											if(moduleURI != null) {
												if(!removeModule(application, module)){
													module = null;
													moduleURI = null;
												}
											}
										}
									}
									
									if(module == null) {
										if(ref != null && ref.getArchiveName() != null)
											module = getModuleFromURI(earModel, ref.getArchiveName());
										else {
											String uri = getModuleURI(earModel, wc);
											module = getModuleFromURI(earModel, uri);
										}
										if(module != null) {
											if(module instanceof Module)
												moduleURI = ((Module)module).getUri();
											else if(module instanceof org.eclipse.jst.j2ee.application.internal.impl.ModuleImpl)
												moduleURI = ((org.eclipse.jst.j2ee.application.internal.impl.ModuleImpl)module).getUri();
											removeModule(application, module);
										}
									}
									
									if(moduleURI != null){
										IVirtualFile vFile = comp.getRootFolder().getFile(moduleURI);
										IFile iFile = vFile.getUnderlyingFile();
										if(iFile.exists()){
											try {
												iFile.delete(true, monitor);
											} catch (CoreException e) {
												J2EEPlugin.logError(e);
											}
										}
									}	
								}
							}, saveFlag);
						} else {
							earModel.modify(new Runnable() {
								public void run() {
									ICommonApplication application = (ICommonApplication)earModel.getModelObject();
									if (application == null)
										return;
									IVirtualComponent moduleComponent = wc.getComponent();
									if(!moduleComponent.isBinary()){
										J2EEComponentClasspathUpdater.getInstance().queueUpdateModule(moduleComponent.getProject());
									}

									String moduleURI = getModuleURI(earModel, wc);
									Object module = getModuleFromURI(earModel, moduleURI);
									if(module != null) {
										if(module instanceof Module)
											moduleURI = ((Module)module).getUri();
										else if(module instanceof org.eclipse.jst.j2ee.application.internal.impl.ModuleImpl)
											moduleURI = ((org.eclipse.jst.j2ee.application.internal.impl.ModuleImpl)module).getUri();
										removeModule(application, module);
									}
									
									if(moduleURI != null){
										IVirtualFile vFile = comp.getRootFolder().getFile(moduleURI);
										IFile iFile = vFile.getUnderlyingFile();
										if(iFile.exists()){
											try {
												iFile.delete(true, monitor);
											} catch (CoreException e) {
												J2EEPlugin.logError(e);
											}
										}
									}
								}
							}, null);
						}
					} finally {
						if (compse != null) {
							compse.saveIfNecessary(monitor);
							compse.dispose();
						}
					}
				}
			}
		} catch (Exception e) {
			J2EEPlugin.logError(e);
		} finally {
			if (se != null) {
				se.saveIfNecessary(monitor);
				se.dispose();
			}
		}
	}
	
	protected Object getModule(final IEARModelProvider earModel, final ReferencedComponent refComp) {
		Object app = earModel.getModelObject();
		Object moduleObj = refComp.getDependentObject();
		if(moduleObj != null){
			String id = null;
			if(moduleObj instanceof org.eclipse.jst.j2ee.application.internal.impl.ModuleImpl) {
				id = ((org.eclipse.jst.j2ee.application.internal.impl.ModuleImpl)moduleObj).getId();
			} else if (moduleObj instanceof ModuleImpl) {
				id = ((Module)moduleObj).getId();
			}
			if(id == null || id.length() == 0) {
				return null;
			}
		}
		if(app instanceof Application){
			List<Module> modules = ((Application)app).getModules();
			if(moduleObj != null){
				for (Module curModule : modules ){
					if(curModule != null && curModule.getUri() != null && curModule.getId() != null) {
						if(moduleObj instanceof ModuleImpl && curModule.getId().equals(((Module)moduleObj).getId())
								|| moduleObj instanceof org.eclipse.jst.j2ee.application.internal.impl.ModuleImpl && curModule.getId().equals(((org.eclipse.jst.j2ee.application.internal.impl.ModuleImpl)moduleObj).getId())){
							return curModule;
						}
					}
				}
			}
		}
		else if (app instanceof org.eclipse.jst.j2ee.application.Application){
			List<org.eclipse.jst.j2ee.application.internal.impl.ModuleImpl> modules = ((org.eclipse.jst.j2ee.application.Application)app).getModules();
			if(moduleObj != null){
				if(moduleObj instanceof org.eclipse.jst.j2ee.application.internal.impl.ModuleImpl){
					for (org.eclipse.jst.j2ee.application.internal.impl.ModuleImpl curModule : modules ){
						if(curModule != null && curModule.getUri() != null && curModule.getId() != null) {
							if(moduleObj instanceof ModuleImpl && curModule.getId().equals(((Module)moduleObj).getId())
									|| moduleObj instanceof org.eclipse.jst.j2ee.application.internal.impl.ModuleImpl && curModule.getId().equals(((org.eclipse.jst.j2ee.application.internal.impl.ModuleImpl)moduleObj).getId())){
								return curModule;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	protected Object getModuleFromURI(final IEARModelProvider earModel, String uri) {
		Object app = earModel.getModelObject();
		if(uri == null)
			return null;
		String archiveName = (new Path(uri)).lastSegment();
		if(app instanceof Application){
			List<Module> modules = ((Application)app).getModules();
			for (Module curModule : modules ){
				if(curModule != null && curModule.getUri() != null) {
					if(new Path(curModule.getUri()).lastSegment().equals(archiveName)) {
						return curModule;
					}
				}
			}
		}
		else if (app instanceof org.eclipse.jst.j2ee.application.Application){
			List<org.eclipse.jst.j2ee.application.internal.impl.ModuleImpl> modules = ((org.eclipse.jst.j2ee.application.Application)app).getModules();
			for (org.eclipse.jst.j2ee.application.internal.impl.ModuleImpl curModule : modules ){
				if(curModule != null && curModule.getUri() != null) {
					if(new Path(curModule.getUri()).lastSegment().equals(archiveName)) {
						return curModule;
					}
				}
			}
		}
		return null;
	}
					
	protected String getModuleURI(final IEARModelProvider earModule, final IVirtualComponent targetComponent) {
		return earModule.getModuleURI(targetComponent);
	}

	protected void removeModule(ICommonApplication application, String moduleURI) {
		ICommonModule module = application.getFirstEARModule(moduleURI);
		application.getEARModules().remove(module);
	}
	
	protected boolean removeModule(ICommonApplication application, Object module) {
		return application.getEARModules().remove(module);
	}
}
