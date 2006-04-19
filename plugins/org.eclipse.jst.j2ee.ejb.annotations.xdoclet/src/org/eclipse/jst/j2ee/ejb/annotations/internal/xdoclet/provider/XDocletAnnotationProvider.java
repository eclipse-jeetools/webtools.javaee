/*******************************************************************************
 * Copyright (c) 2002, 2003,2004,2005 Eteration Bilisim A.S.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Eteration Bilisim A.S. - initial API and implementation
 *     Naci Dai
 * For more information on eteration, please see
 * <http://www.eteration.com/>.
 ***************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.provider;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IContainerManagedEntityBean;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBean;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IMessageDrivenBean;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.ISessionBean;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.ModelPlugin;
import org.eclipse.jst.j2ee.ejb.annotation.internal.provider.IAnnotationProvider;
import org.eclipse.jst.j2ee.ejb.annotation.internal.provider.IEJBGenerator;
import org.eclipse.jst.j2ee.ejb.annotations.internal.classgen.EjbBuilder;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.EjbEmitter;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.EmitterUtilities;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.EntityEjbEmitter;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.MessageDrivenEjbEmitter;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.SessionEjbEmitter;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.Logger;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletBuildUtility;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletExtensionUtil;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletPreferenceStore;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletRuntime;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.datamodel.FacetInstallDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.FacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class XDocletAnnotationProvider implements IAnnotationProvider, IEJBGenerator {

	private static final String END_XDOCLET_DEFINITION = " * <!-- end-xdoclet-definition -->";

	public boolean isEjbAnnotationProvider() {
		return true;
	}

	public boolean isServletAnnotationProvider() {
		return true;
	}

	public boolean isWebServiceAnnotationProvider() {
		return false;
	}

	public boolean isValid() {

		XDocletPreferenceStore store = XDocletPreferenceStore.forProject(null);
		XDocletRuntime runtime = XDocletExtensionUtil.getRuntime(store
				.getProperty(XDocletPreferenceStore.XDOCLETVERSION));
		runtime.setHome(store.getProperty(XDocletPreferenceStore.XDOCLETHOME));
		return runtime.isValid();
	}

	public String getName() {
		return "XDocletAnnotionProvider"; //$NON-NLS-1$
	}

	public void generateSession(ISessionBean delegate, IProgressMonitor monitor)
			throws CoreException, InterruptedException {

		IDataModel dataModel = delegate.getDataModel();

		String comment = ""; //$NON-NLS-1$
		String stub = ""; //$NON-NLS-1$
		String method = ""; //$NON-NLS-1$

		IConfigurationElement preferredAnnotation = EmitterUtilities
				.findEmitter("XDoclet"); //$NON-NLS-1$

		try {
			EjbEmitter ejbEmitter = new SessionEjbEmitter(preferredAnnotation);
			ejbEmitter.setMonitor(monitor);
			comment = ejbEmitter.emitTypeComment(delegate);
			comment = applyRuntimeAnnotations(delegate, comment);
			stub = ejbEmitter.emitTypeStub(delegate);
			method = ejbEmitter.emitInterfaceMethods(delegate);
			ejbEmitter.deleteProject();
		} catch (CoreException e) {
			throw e;
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR,
					ModelPlugin.PLUGINID, 0, "Session EJB Emitters Failed", e));
		}

		EjbBuilder ejbBuilder = new EjbBuilder();
		ejbBuilder.setConfigurationElement(preferredAnnotation);
		ejbBuilder.setMonitor(monitor);
		ejbBuilder
				.setPackageFragmentRoot((IPackageFragmentRoot) dataModel
						.getProperty(INewJavaClassDataModelProperties.JAVA_PACKAGE_FRAGMENT_ROOT));
		ejbBuilder.setEnterpriseBeanDelegate(delegate);
		ejbBuilder
				.setTypeName(dataModel
						.getStringProperty(INewJavaClassDataModelProperties.CLASS_NAME));
		ejbBuilder
				.setPackageName(dataModel
						.getStringProperty(INewJavaClassDataModelProperties.JAVA_PACKAGE));

		ejbBuilder.setTypeComment(comment);
		ejbBuilder.setTypeStub(stub);
		ejbBuilder.setMethodStub(method);
		ejbBuilder.setFields("");

		ejbBuilder.createType();

		IType bean = ejbBuilder.getCreatedType();
		IResource javaFile = bean.getCorrespondingResource();
		IProject project = (IProject) dataModel
				.getProperty(INewJavaClassDataModelProperties.PROJECT);
		initializeBuilder(monitor, preferredAnnotation, javaFile, project);
		XDocletBuildUtility.runNecessaryBuilders(monitor, project);

	}

	public void generateCMP(IContainerManagedEntityBean delegate,
			IProgressMonitor monitor) throws CoreException,
			InterruptedException {

		IDataModel dataModel = delegate.getDataModel();

		String comment = ""; //$NON-NLS-1$
		String stub = ""; //$NON-NLS-1$
		String method = ""; //$NON-NLS-1$
		String fields = ""; //$NON-NLS-1$

		IConfigurationElement preferredAnnotation = EmitterUtilities
				.findEmitter("XDoclet"); //$NON-NLS-1$

		try {
			EjbEmitter ejbEmitter = new EntityEjbEmitter(preferredAnnotation);
			ejbEmitter.setMonitor(monitor);
			comment = ejbEmitter.emitTypeComment(delegate);
			comment = applyRuntimeAnnotations(delegate, comment);
			stub = ejbEmitter.emitTypeStub(delegate);
			method = ejbEmitter.emitInterfaceMethods(delegate);
			fields = ejbEmitter.emitFields(delegate);
			// ejbEmitter.deleteProject();
		} catch (CoreException e) {
			throw e;
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR,
					ModelPlugin.PLUGINID, 0, "CMP EJB Emitters Failed", e));
		}

		EjbBuilder ejbBuilder = new EjbBuilder();
		ejbBuilder.setConfigurationElement(preferredAnnotation);
		ejbBuilder.setMonitor(monitor);
		ejbBuilder
				.setPackageFragmentRoot((IPackageFragmentRoot) dataModel
						.getProperty(INewJavaClassDataModelProperties.JAVA_PACKAGE_FRAGMENT_ROOT));
		ejbBuilder.setEnterpriseBeanDelegate(delegate);
		ejbBuilder
				.setTypeName(dataModel
						.getStringProperty(INewJavaClassDataModelProperties.CLASS_NAME));
		ejbBuilder
				.setPackageName(dataModel
						.getStringProperty(INewJavaClassDataModelProperties.JAVA_PACKAGE));

		ejbBuilder.setTypeComment(comment);
		ejbBuilder.setTypeStub(stub);
		ejbBuilder.setMethodStub(method);
		ejbBuilder.setFields(fields);

		ejbBuilder.createType();

		IType bean = ejbBuilder.getCreatedType();
		IResource javaFile = bean.getCorrespondingResource();
		IProject project = (IProject) dataModel
				.getProperty(INewJavaClassDataModelProperties.PROJECT);
		initializeBuilder(monitor, preferredAnnotation, javaFile, project);
		XDocletBuildUtility.runNecessaryBuilders(monitor, project);

	}

	private String applyRuntimeAnnotations(
			IEnterpriseBean delegate, String comment) {
		String commentRuntime = XDocletExtensionUtil
				.getRuntimeTypeAnnotations(delegate);
		int i = comment.indexOf(END_XDOCLET_DEFINITION);
		if (commentRuntime == null || i < 0)
			return comment;

		comment = comment.substring(0, i) + commentRuntime
				+ comment.substring(i);
		return comment;
	}

	public void generateMessageDriven(IMessageDrivenBean delegate,
			IProgressMonitor monitor) throws CoreException,
			InterruptedException {

		IDataModel dataModel = delegate.getDataModel();

		String comment = "";
		String stub = "";
		String method = "";
		String fields = "";
		IConfigurationElement emitterConfiguration = EmitterUtilities
				.findEmitter("XDoclet");

		try {
			EjbEmitter ejbEmitter = new MessageDrivenEjbEmitter(
					emitterConfiguration);
			ejbEmitter.setMonitor(monitor);
			fields = ejbEmitter.emitFields(delegate);
			comment = ejbEmitter.emitTypeComment(delegate);
			comment = applyRuntimeAnnotations(delegate, comment);
			stub = ejbEmitter.emitTypeStub(delegate);
			method = ejbEmitter.emitInterfaceMethods(delegate);
			ejbEmitter.deleteProject();
		} catch (CoreException e) {
			throw e;
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR,
					ModelPlugin.PLUGINID, 0,
					"MessageDriven EJB Emitters Failed", e));
		}

		EjbBuilder ejbBuilder = new EjbBuilder();
		ejbBuilder.setConfigurationElement(emitterConfiguration);
		ejbBuilder.setMonitor(monitor);
		ejbBuilder
				.setPackageFragmentRoot((IPackageFragmentRoot) dataModel
						.getProperty(INewJavaClassDataModelProperties.JAVA_PACKAGE_FRAGMENT_ROOT));
		ejbBuilder.setEnterpriseBeanDelegate(delegate);
		ejbBuilder
				.setTypeName(dataModel
						.getStringProperty(INewJavaClassDataModelProperties.CLASS_NAME));
		ejbBuilder
				.setPackageName(dataModel
						.getStringProperty(INewJavaClassDataModelProperties.JAVA_PACKAGE));

		ejbBuilder.setTypeComment(comment);
		ejbBuilder.setTypeStub(stub);
		ejbBuilder.setMethodStub(method);
		ejbBuilder.setFields(fields);

		ejbBuilder.createType();
		IType bean = ejbBuilder.getCreatedType();
		IResource javaFile = bean.getCorrespondingResource();
		IProject project = (IProject) dataModel
				.getProperty(INewJavaClassDataModelProperties.PROJECT);
		initializeBuilder(monitor, emitterConfiguration, javaFile, project);
		XDocletBuildUtility.runNecessaryBuilders(monitor, project);

	}

	protected void initializeBuilder(IProgressMonitor monitor, IConfigurationElement emitterConfiguration, IResource javaFile, IProject project) throws CoreException {
		addXDocletFacet(project, monitor);
		// To add an xdoclet builder we must use a
		// a facet now.
		// EmitterUtilities.addAnnotationBuilderToProject(emitterConfiguration,
		// project);
	}

	private void addXDocletFacet(IProject project, IProgressMonitor monitor) {
		if (J2EEProjectUtilities.isDynamicWebProject(project)) {
			installXDocletFacets(project, monitor, "jst.web.xdoclet");
		} else if (J2EEProjectUtilities.isEJBProject(project)) {
			installXDocletFacets(project, monitor, "jst.ejb.xdoclet");
		}
	}

	private void installXDocletFacets(IProject project, IProgressMonitor monitor, String docletType) {

		try {
			IFacetedProject facetedProject = ProjectFacetsManager.create(project);
			Set facets = facetedProject.getProjectFacets();
			Set fixedFacets = facetedProject.getFixedProjectFacets();
			boolean shouldInstallFacet = true;
			for (Iterator iter = facets.iterator(); iter.hasNext();) {
				IProjectFacetVersion facetVersion = (IProjectFacetVersion) iter.next();
				String facetID = facetVersion.getProjectFacet().getId();
				if (docletType.equals(facetID)) {
					shouldInstallFacet = false;
				}
			}
			if (!shouldInstallFacet)
				return;

			IDataModel dm = DataModelFactory.createDataModel(new FacetInstallDataModelProvider());
			dm.setProperty(IFacetDataModelProperties.FACET_ID, docletType);
			dm.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, project.getName());
			dm.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, "1.2.3"); //$NON-NLS-1$
			IDataModel fdm = DataModelFactory.createDataModel(new FacetProjectCreationDataModelProvider());
			fdm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, project.getName());

			FacetDataModelMap map = (FacetDataModelMap) fdm.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
			map.add(dm);

			fdm.getDefaultOperation().execute(monitor, null);
			facetedProject.setFixedProjectFacets(fixedFacets);
		} catch (Exception e) {
			Logger.logException(e);
		}

	}

}
