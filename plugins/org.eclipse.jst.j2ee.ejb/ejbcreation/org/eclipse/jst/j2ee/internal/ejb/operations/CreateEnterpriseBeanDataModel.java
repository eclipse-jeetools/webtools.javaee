/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jan 18, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.operations;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.internal.adapters.jdom.JDOMSearchHelper;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.J2EEModuleExtensionHelper;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.application.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodegenHandlerExtensionReader;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.IEJBCodegenHandler;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public abstract class CreateEnterpriseBeanDataModel extends EditModelOperationDataModel implements IAnnotationsDataModel {

	private EJBNatureRuntime ejbNature;

	private EJBJar ejbJar;

	private IJavaProject javaProject;

	private boolean ignorePropertyNotifications = false;

	private Map typeCache;

	private IEJBCodegenHandler codegenHandler = null;

	/**
	 * Set to allow changes to the selected project. (Optional - Default is true.)
	 * 
	 * @link Boolean
	 */
	public static final String ALLOW_PROJECT_CHANGE = "CreateEnterpriseBeanDataModel.allowProjectChange"; //$NON-NLS-1$

	/**
	 * Set to inform the UI not to diplay the basic properties. (Optional - Default is true.)
	 * 
	 * @link Boolean
	 */
	public static final String UI_SHOW_BASIC_PROPS = "CreateEnterpriseBeanDataModel.uiShowBasicProps"; //$NON-NLS-1$

	/**
	 * The name of the bean. (Required)
	 * 
	 * @link String
	 */
	public static final String BEAN_NAME = "CreateEnterpriseBeanDataModel.beanName"; //$NON-NLS-1$

	/**
	 * The source folder name that the bean classes will be generated into. This includes the
	 * interface classes unless an EJB client project is specified. (Optional - Default to
	 * "ejbModule" unless the SOURCE_FOLDER is set.)
	 * 
	 * @link String
	 */
	public static final String SOURCE_FOLDER_NAME = "CreateEnterpriseBeanDataModel.sourceFolderName"; //$NON-NLS-1$

	/**
	 * The source folder that the bean classes will be generated into. This includes the interface
	 * classes unless an EJB client project is specified. (Optional - Defaults to the IFolder for
	 * the SOURCE_FOLDER_NAME.)
	 * 
	 * @link IFolder
	 */
	public static final String SOURCE_FOLDER = "CreateEnterpriseBeanDataModel.sourceFolder"; //$NON-NLS-1$

	/**
	 * The name of the package that the computed qualified names of the generated classes will use.
	 * (Required)
	 * 
	 * @link String
	 */
	public static final String DEFAULT_PACKAGE_NAME = "CreateEnterpriseBeanDataModel.defaultPackageName"; //$NON-NLS-1$

	/**
	 * The qualified name of the bean implementation class. (Optional - The value will be defaulted
	 * using the following pattern [DEFAULT_PACKAGE_NAME].[BEAN_NAME]Bean.)
	 * 
	 * @link String
	 */
	public static final String BEAN_CLASS_NAME = "CreateEnterpriseBeanDataModel.beanClassName"; //$NON-NLS-1$

	/**
	 * The qualified name of the Java class that the bean class should extend. (Optional)
	 * 
	 * @link String
	 */
	public static final String BEAN_CLASS_SUPERCLASS = "CreateEnterpriseBeanDataModel.beanClassSuperclass"; //$NON-NLS-1$

	/**
	 * An EJB within the deployment descriptor that the new EJB should inherit from. This is an
	 * extension to the specification and will only work for vendors that support EJB inheritance.
	 * (Optional)
	 * 
	 * @link EnterpriseBean
	 * 
	 * @see EJBCodegenHandler extension point in com.ibm.wtp.ejb.
	 * @see J2EEModuleExtensionHelper extension point in org.eclipse.jst.j2ee.core.
	 */
	public static final String BEAN_SUPEREJB = "CreateEnterpriseBeanDataModel.beanSuperEJB"; //$NON-NLS-1$

	/**
	 * A name of another EJB within the deployment descriptor that the new EJB should inherit from.
	 * This is an extension to the specification and will only work for vendors that support EJB
	 * inheritance. (Optional)
	 * 
	 * @link String
	 * 
	 * @see EJBCodegenHandler extension point in com.ibm.wtp.ejb.
	 * @see J2EEModuleExtensionHelper extension point in org.eclipse.jst.j2ee.core.
	 */
	public static final String BEAN_SUPEREJB_NAME = "CreateEnterpriseBeanDataModel.beanSuperEJBName"; //$NON-NLS-1$

	/**
	 * This is the IOperationHandler that would be used by an operation that uses this data model.
	 * (Optional)
	 * 
	 * @link org.eclipse.wst.common.framework.operation.IOperationHandler
	 */
	public static final String OPERATION_HANDLER = "CreateEnterpriseBeanDataModel.operationHandler"; //$NON-NLS-1$

	/**
	 * EJB type flag for Session beans. (This is not a property name.)
	 */
	public static final int EJB_TYPE_SESSION = 0;

	/**
	 * EJB type flag for Message Driven beans. (This is not a property name.)
	 */
	public static final int EJB_TYPE_MDB = 1;

	/**
	 * EJB type flag for Bean-Managed entity beans. (This is not a property name.)
	 */
	public static final int EJB_TYPE_BMP = 2;

	/**
	 * EJB type flag for Container-Managed entity beans. (This is not a property name.)
	 */
	public static final int EJB_TYPE_CMP = 3;

	/**
	 * The next three fields are used to determine what type is held by the type property names.
	 * 
	 * @see CreateEnterpriseBeanDataModel#getTypeFlag(String)
	 */
	public static final int TYPE_NEW = 1;

	public static final int TYPE_SOURCE = 2;

	public static final int TYPE_BINARY = 3;

	private String[] validFolderNames;



	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#init()
	 */
	protected void init() {
		super.init();
		setProperty(EDIT_MODEL_ID, IEJBNatureConstants.EDIT_MODEL_ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(BEAN_NAME);
		addValidBaseProperty(BEAN_CLASS_NAME);
		addValidBaseProperty(DEFAULT_PACKAGE_NAME);
		addValidBaseProperty(SOURCE_FOLDER_NAME);
		addValidBaseProperty(SOURCE_FOLDER);
		addValidBaseProperty(BEAN_CLASS_SUPERCLASS);
		addValidBaseProperty(OPERATION_HANDLER);
		addValidBaseProperty(ALLOW_PROJECT_CHANGE);
		addValidBaseProperty(UI_SHOW_BASIC_PROPS);
		addValidBaseProperty(BEAN_SUPEREJB);
		addValidBaseProperty(BEAN_SUPEREJB_NAME);
		addValidBaseProperty(USE_ANNOTATIONS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doGetValidPropertyValues(java.lang.String)
	 */
	protected Object[] doGetValidPropertyValues(String propertyName) {
		if (propertyName.equals(PROJECT_NAME)) {
			return getValidProjectNames();
		}
		if (propertyName.equals(SOURCE_FOLDER))
			return getValidSourceFolders();
		if (propertyName.equals(SOURCE_FOLDER_NAME))
			return getValidSourceFolderNames();
		if (propertyName.equals(BEAN_SUPEREJB_NAME))
			return getValidSuperEJBNames();
		return super.doGetValidPropertyValues(propertyName);
	}

	/**
	 * @return
	 */
	private Object[] getValidSuperEJBNames() {
		if (getCodegenHandler() == null)
			return null;
		EJBJar jar = getEJBJar();
		if (jar == null)
			return null;
		List beans = null;
		switch (getEJBType()) {
			case EJB_TYPE_SESSION :
				beans = jar.getSessionBeans();
				break;
			case EJB_TYPE_MDB :
				beans = jar.getMessageDrivenBeans();
				break;
			case EJB_TYPE_BMP :
				beans = jar.getBeanManagedBeans();
				break;
			case EJB_TYPE_CMP :
				beans = getEntities();
				break;
		}
		if (beans != null) {
			return processAndFilterSuperEJBNames(beans);
		}
		return null;
	}

	/**
	 * @param beans
	 * @return
	 */
	protected Object[] processAndFilterSuperEJBNames(List beans) {
		String[] names = new String[beans.size() + 1];
		names[0] = ""; //$NON-NLS-1$
		EnterpriseBean ejb;
		for (int i = 0; i < beans.size(); i++) {
			ejb = (EnterpriseBean) beans.get(i);
			names[i + 1] = ejb.getName();
		}
		return names;
	}

	/**
	 * @return
	 */
	private List getEntities() {
		List ejbs = getEJBJar().getEnterpriseBeans();
		if (ejbs.isEmpty())
			return ejbs;
		List entities = new ArrayList();
		EnterpriseBean ejb;
		for (int i = 0; i < ejbs.size(); i++) {
			ejb = (EnterpriseBean) ejbs.get(i);
			if (ejb.isEntity())
				entities.add(ejb);
		}
		return entities;
	}

	/**
	 * @return
	 */
	private String[] getValidProjectNames() {
		IProject[] projects = ProjectUtilities.getAllProjects();
		if (projects.length == 0)
			return new String[0];
		List names = new ArrayList();
		int version = getMinimumSupportedProjectVersion();
		IProject proj;
		for (int i = 0; i < projects.length; i++) {
			proj = projects[i];
			try {
				if (proj.hasNature(IEJBNatureConstants.NATURE_ID)) {
					if (version >= J2EEVersionConstants.EJB_1_1_ID)
						names.add(proj.getName());
					else {
						EJBNatureRuntime nature = EJBNatureRuntime.getRuntime(proj);
						if (nature != null && nature.getModuleVersion() >= version)
							names.add(proj.getName());
					}
				}
			} catch (CoreException e) {
			}
		}
		String[] validProjectNames = new String[names.size()];
		names.toArray(validProjectNames);

		if (!isSet(PROJECT_NAME) && validProjectNames.length > 0)
			setProperty(PROJECT_NAME, validProjectNames[0]);

		return validProjectNames;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#getDefaultProperty(java.lang.String)
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(SOURCE_FOLDER_NAME))
			return getDefaultSourceFolderName();
		if (propertyName.equals(SOURCE_FOLDER))
			return getDefaultSourceFolder();
		if (propertyName.equals(BEAN_CLASS_NAME))
			return getDefaultBeanClassName();
		if (propertyName.equals(DEFAULT_PACKAGE_NAME))
			return getDefaultDefaultPackageName();
		if (propertyName.equals(BEAN_CLASS_SUPERCLASS))
			return getDefaultBeanClassSuperclass();
		if (propertyName.equals(ALLOW_PROJECT_CHANGE))
			return Boolean.TRUE;
		if (propertyName.equals(UI_SHOW_BASIC_PROPS))
			return Boolean.TRUE;
		if (propertyName.equals(PROJECT_NAME))
			return getDefaultProjectName();
		if (propertyName.equals(BEAN_SUPEREJB))
			return getDefaultSuperEJB();
		if (propertyName.equals(BEAN_SUPEREJB_NAME))
			return getDefaultSuperEJBName();
		if (propertyName.equals(USE_ANNOTATIONS))
			return Boolean.FALSE;
		return super.getDefaultProperty(propertyName);
	}

	/**
	 * @return
	 */
	private Object getDefaultBeanClassSuperclass() {
		EnterpriseBean superEJB = (EnterpriseBean) getProperty(BEAN_SUPEREJB);
		if (superEJB != null) {
			JavaClass ejbClass = superEJB.getEjbClass();
			if (ejbClass != null)
				return ejbClass.getQualifiedName();
		}
		return null;
	}

	/**
	 * @return
	 */
	private EnterpriseBean getDefaultSuperEJB() {
		if (isSet(BEAN_SUPEREJB_NAME)) {
			String name = (String) getProperty(BEAN_SUPEREJB_NAME);
			return getEJBJar().getEnterpriseBeanNamed(name);
		}
		return null;
	}

	/**
	 * @return
	 */
	private String getDefaultSuperEJBName() {
		if (isSet(BEAN_SUPEREJB)) {
			EnterpriseBean ejb = (EnterpriseBean) getProperty(BEAN_SUPEREJB);
			if (ejb != null)
				return ejb.getName();
		}
		return null;
	}

	/**
	 * @return
	 */
	private Object getDefaultProjectName() {
		String[] validProjectNames = getValidProjectNames();
		if (validProjectNames != null && validProjectNames.length > 0)
			return validProjectNames[0];
		return null;
	}

	/**
	 * @return
	 */
	protected String getDefaultDefaultPackageName() {
		IFolder source = (IFolder) getProperty(SOURCE_FOLDER);
		if (source != null && source.exists()) {
			IJavaElement element = JavaCore.create(source);
			if (element.getElementType() == IJavaElement.PACKAGE_FRAGMENT_ROOT) {
				IPackageFragmentRoot root = (IPackageFragmentRoot) element;
				try {
					IJavaElement[] children = root.getChildren();
					IJavaElement child;
					for (int i = children.length - 1; i != -1; i--) {
						child = children[i];
						if (child.getElementType() == IJavaElement.PACKAGE_FRAGMENT) {
							if (!((IPackageFragment) child).isDefaultPackage())
								return child.getElementName();
						}
					}
				} catch (JavaModelException e) {
				}
			}
		}
		return "ejbs"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		/* reset the codegen handler if the target project changes */
		if (PROJECT_NAME.equals(propertyName))
			codegenHandler = null;

		boolean notify = super.doSetProperty(propertyName, propertyValue);
		if (!ignorePropertyNotifications) {
			if (propertyName.equals(BEAN_NAME) || propertyName.equals(DEFAULT_PACKAGE_NAME))
				beanNameChanged();
			else if (propertyName.equals(PROJECT_NAME)) {
				projectNamedChanged();
			} else if (propertyName.equals(SOURCE_FOLDER)) {
				if (isSet(SOURCE_FOLDER_NAME)) {
					ignorePropertyNotifications = true;
					try {
						setProperty(SOURCE_FOLDER_NAME, null);
					} finally {
						ignorePropertyNotifications = false;
					}
				} else
					notifyDefaultChange(SOURCE_FOLDER_NAME);
			} else if (propertyName.equals(SOURCE_FOLDER_NAME)) {
				if (isSet(SOURCE_FOLDER)) {
					ignorePropertyNotifications = true;
					try {
						setProperty(SOURCE_FOLDER, null);
					} finally {
						ignorePropertyNotifications = false;
					}
				} else
					notifyDefaultChange(SOURCE_FOLDER);
			} else if (propertyName.equals(BEAN_SUPEREJB))
				superEJBChanged((EnterpriseBean) propertyValue);
			else if (propertyName.equals(BEAN_SUPEREJB_NAME)) {
				if (propertyValue == null || ((String) propertyValue).length() == 0)
					setProperty(BEAN_SUPEREJB_NAME, ""); //$NON-NLS-1$
				superEJBNameChanged();
			} else if (propertyName.equals(USE_ANNOTATIONS)) {
				if (((Boolean) propertyValue).booleanValue()) {
					if (isSet(BEAN_SUPEREJB_NAME)) {
						setProperty(BEAN_SUPEREJB, null);
						setProperty(BEAN_SUPEREJB_NAME, null);
					} else {
						notifyEnablementChange(BEAN_SUPEREJB);
						notifyEnablementChange(BEAN_SUPEREJB_NAME);
					}
				} else {
					notifyEnablementChange(BEAN_SUPEREJB);
					notifyEnablementChange(BEAN_SUPEREJB_NAME);
				}
			}
		}
		return notify;
	}

	/**
	 *  
	 */
	protected void superEJBNameChanged() {
		String superEJBName = getStringProperty(BEAN_SUPEREJB_NAME);
		EnterpriseBean superEJB = null;
		if (superEJBName != null && !superEJBName.equals("")) //$NON-NLS-1$
			superEJB = getEJBJar().getEnterpriseBeanNamed(superEJBName);
		setProperty(BEAN_SUPEREJB, superEJB);
		notifyDefaultChange(BEAN_CLASS_SUPERCLASS);

	}

	/**
	 * @param bean
	 */
	private void superEJBChanged(EnterpriseBean bean) {
		//setProperty(BEAN_SUPEREJB_NAME, null);
		if (isSet(BEAN_CLASS_SUPERCLASS))
			setProperty(BEAN_CLASS_SUPERCLASS, null);
		else
			notifyDefaultChange(BEAN_CLASS_SUPERCLASS);
	}

	/**
	 *  
	 */
	protected void beanNameChanged() {
		notifyDefaultChange(BEAN_CLASS_NAME);
	}

	protected void projectNamedChanged() {
		ejbNature = null;
		ejbJar = null;
		javaProject = null;
		IProject proj = getTargetProject();
		if (isSet(SOURCE_FOLDER)) {
			IFolder folder = (IFolder) getProperty(SOURCE_FOLDER);
			if (folder != null && folder.getProject() != proj) {
				notifyValidValuesChange(SOURCE_FOLDER_NAME);
				setProperty(SOURCE_FOLDER, null);
			}
		} else if (isSet(SOURCE_FOLDER_NAME)) {
			String folderName = getStringProperty(SOURCE_FOLDER_NAME);
			if (validFolderNames != null) {
				boolean found = false;
				for (int i = 0; i < validFolderNames.length; i++) {
					if (folderName.equals(validFolderNames[i])) {
						found = true;
						break;
					}
				}
				notifyValidValuesChange(SOURCE_FOLDER_NAME);
				if (found)
					setProperty(SOURCE_FOLDER_NAME, null);
			}
		} else
			notifyValidValuesChange(SOURCE_FOLDER_NAME);
		notifyDefaultChange(DEFAULT_PACKAGE_NAME);
	}

	private IFolder getDefaultSourceFolder() {
		IProject proj = getTargetProject();
		if (proj == null)
			return null;
		if (isSet(SOURCE_FOLDER_NAME)) {
			String folderName = (String) getProperty(SOURCE_FOLDER_NAME);
			return proj.getFolder(folderName);
		}
		return (IFolder) ProjectUtilities.getSourceFolderOrFirst(proj, "ejbModule"); //$NON-NLS-1$
	}

	private IFolder[] getValidSourceFolders() {
		IProject proj = getTargetProject();
		if (proj == null)
			return new IFolder[0];
		List containers = ProjectUtilities.getSourceContainers(proj);
		IFolder[] validSourceFolders = new IFolder[containers.size()];
		containers.toArray(validSourceFolders);
		return validSourceFolders;
	}

	private String[] getValidSourceFolderNames() {
		IFolder[] folders = getValidSourceFolders();
		validFolderNames = new String[folders.length];
		for (int i = 0; i < folders.length; i++) {
			validFolderNames[i] = folders[i].getName();
		}
		return validFolderNames;
	}

	/**
	 * @return
	 */
	private String getDefaultSourceFolderName() {
		IFolder folder = (IFolder) getProperty(SOURCE_FOLDER);
		if (folder != null)
			return folder.getName();
		return "ejbModule"; //$NON-NLS-1$
	}

	/**
	 * @return
	 */
	private Object getDefaultBeanClassName() {
		return getDefaultClassName("Bean"); //$NON-NLS-1$
	}

	protected String getDefaultClassName(String suffix) {
		String beanName = (String) getProperty(BEAN_NAME);
		if (beanName != null) {
			StringBuffer buf = new StringBuffer();
			String defaultPackage = (String) getProperty(DEFAULT_PACKAGE_NAME);
			if (defaultPackage != null) {
				buf.append(defaultPackage);
				buf.append('.');
			}
			buf.append(beanName);
			if (suffix != null)
				buf.append(suffix);
			return buf.toString();
		}
		return null;
	}

	protected EJBNatureRuntime getEJBNature() {
		if (ejbNature == null)
			ejbNature = EJBNatureRuntime.getRuntime(getTargetProject());
		return ejbNature;
	}

	protected EJBJar getEJBJar() {
		if (ejbJar == null) {
			EJBNatureRuntime nature = getEJBNature();
			if (nature != null)
				ejbJar = nature.getEJBJar();
		}
		return ejbJar;
	}

	protected IJavaProject getJavaProject() {
		if (javaProject == null)
			javaProject = JavaCore.create(getTargetProject());
		return javaProject;
	}

	public boolean isVersion2xOrGreater() {
		EJBNatureRuntime nature = getEJBNature();
		if (nature != null)
			return nature.getModuleVersion() >= J2EEVersionConstants.EJB_2_0_ID;
		return true;
	}

	public boolean isVersion20() {
		EJBNatureRuntime nature = getEJBNature();
		if (nature != null)
			return nature.getModuleVersion() == J2EEVersionConstants.EJB_2_0_ID;
		return true;
	}

	public boolean isVersion21OrGreater() {
		EJBNatureRuntime nature = getEJBNature();
		if (nature != null)
			return nature.getModuleVersion() >= J2EEVersionConstants.EJB_2_1_ID;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(BEAN_NAME))
			return validateBeanName();
		if (propertyName.equals(BEAN_CLASS_NAME))
			return validateBeanClassName();
		if (propertyName.equals(SOURCE_FOLDER_NAME) || propertyName.equals(SOURCE_FOLDER))
			return validateSourceFolderName();
		if (propertyName.equals(DEFAULT_PACKAGE_NAME))
			return validateDefaultPackageName();
		if (propertyName.equals(PROJECT_NAME))
			return validateProject();
		if (propertyName.equals(BEAN_CLASS_SUPERCLASS))
			return validateBeanSuperclass();
		return super.doValidateProperty(propertyName);
	}

	/**
	 * @return
	 */
	private IStatus validateProject() {
		IProject project = getTargetProject();
		EJBNatureRuntime nature = EJBNatureRuntime.getRuntime(project);
		if (nature == null)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Select_valid_EJB_project"), //$NON-NLS-1$
						null);
		if (nature.isBinaryProject()) {
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("CANT_BE_BINARY_PROJECT_UI_"), null); //$NON-NLS-1$
		}
		IJavaProject javaProj = JavaCore.create(project);
		try { // check if jds rt.jar or JRE Lib is in the class path
			IClasspathEntry[] classpath = javaProj.getRawClasspath();
			for (int i = 0; i < classpath.length; i++) {
				if (classpath[i].getEntryKind() == IClasspathEntry.CPE_CONTAINER || classpath[i].getPath().lastSegment().equalsIgnoreCase("rt.jar")) //$NON-NLS-1$
					return OK_STATUS;
			}
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("The_project_does_not_conta_UI_"), //$NON-NLS-1$
						null); //$NON-NLS-1$ = "The project does not contain the class path for JDK (rt.jar)"
		} catch (JavaModelException e) {
			Logger.getLogger(EjbPlugin.PLUGIN_ID).logError(e.getMessage());
		}
		return OK_STATUS;
	}

	/**
	 * @return
	 */
	private IStatus validateDefaultPackageName() {
		if (!isSet(DEFAULT_PACKAGE_NAME))
			return OK_STATUS;
		String packName = (String) getProperty(DEFAULT_PACKAGE_NAME);
		if (packName == null || packName.length() == 0)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Default_pack_not_be_null_UI_"), null); //$NON-NLS-1$
		return JavaConventions.validatePackageName(packName);
	}

	/**
	 * @return
	 */
	private IStatus validateSourceFolderName() {
		String rootName = (String) getProperty(SOURCE_FOLDER_NAME);
		if (rootName == null || rootName.length() < 1)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Select_a_source_folder._UI_"), //$NON-NLS-1$
						null); //$NON-NLS-1$ = "Select a source folder."
		IFolder rootFolder = (IFolder) getProperty(SOURCE_FOLDER);
		if (rootFolder != null && !rootFolder.getParent().equals(getTargetProject()))
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Package_root_must_be_direc_UI_"), null); //$NON-NLS-1$
		try {
			IJavaProject javaProj = getJavaProject();
			if (javaProj != null) {
				IPackageFragmentRoot root = javaProj.getPackageFragmentRoot(rootName);
				if (root != null && root.exists()) {
					IClasspathEntry entry = root.getRawClasspathEntry();
					if (entry != null && entry.getEntryKind() != IClasspathEntry.CPE_SOURCE)
						return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("The_selected_source_folder_UI_"), null); //$NON-NLS-1$ = "The selected source folder is not defined as a classpath source folder."
				}
			}
		} catch (JavaModelException e) {
		}
		return OK_STATUS;
	}

	/**
	 * @return
	 */
	private IStatus validateBeanClassName() {
		String beanClassName = (String) getProperty(BEAN_CLASS_NAME);
		if (beanClassName == null || beanClassName.length() < 1)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Message_Enter_bean_class_name_UI_"), null); //$NON-NLS-1$
		// verify if the package is not a default package
		String pkg = getPackageName(beanClassName);
		if (pkg == null || pkg.trim().length() == 0)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Bean_Class_Cannot_Be_In_UI_"), //$NON-NLS-1$
						null); //$NON-NLS-1$
		// verify if it is a valid Java package path
		IStatus javaStatus = validateJavaTypeName(beanClassName, "Bean_class_UI_"); //$NON-NLS-1$
		if (!javaStatus.isOK())
			return javaStatus;
		return validateBeanType(beanClassName);
	}

	/**
	 * @return
	 */
	private IStatus validateBeanType(String beanClassName) {
		IType type = findType(beanClassName);
		if (type != null) {
			if (!implementsInterface(type, getBeanClassEJBInterfaceName())) {
				String msg_pattern = EJBCreationResourceHandler.getString("Class_implements"); //$NON-NLS-1$
				return EjbPlugin.createErrorStatus(MessageFormat.format(msg_pattern, new String[]{beanClassName, getBeanClassEJBInterfaceName()}), null);
			}
		}
		return OK_STATUS;
	}

	private IStatus validateBeanSuperclass() {
		String className = (String) getProperty(BEAN_CLASS_SUPERCLASS);
		if (className == null || className.length() < 1)
			return OK_STATUS;
		IStatus javaStatus = validateJavaTypeName(className, "Bean_superclass_UI_"); //$NON-NLS-1$
		if (!javaStatus.isOK())
			return javaStatus;
		IType type = findType(className);
		if (type == null)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("BEAN_SUPERCLASS_NOT_EXIST"), //$NON-NLS-1$
						null);
		/*
		 * if (!implementsInterface(type, getBeanClassEJBInterfaceName())) { String msg_pattern =
		 * EJBCreationResourceHandler.getString("Class_implements"); //$NON-NLS-1$ return
		 * EjbPlugin.createErrorStatus(MessageFormat.format(msg_pattern, new String[] { className,
		 * getBeanClassEJBInterfaceName()}), null); }
		 */
		return OK_STATUS;
	}

	/**
	 * Return the qualified name of the EJB interface that the bean class must implement.
	 * 
	 * @return
	 */
	protected abstract String getBeanClassEJBInterfaceName();

	protected boolean implementsInterface(IType type, String interfaceName) {
		if (type == null)
			return false;
		String[] typeImplements = null;
		try {
			typeImplements = type.getSuperInterfaceNames();
			if (typeImplements != null && typeImplements.length > 0) {
				for (int i = 0; i < typeImplements.length; i++) {
					typeImplements[i] = JDOMSearchHelper.resolveSimpleTypeName(type, typeImplements[i]);
					if (typeImplements[i].equals(interfaceName))
						return true;
				}
			}
		} catch (JavaModelException e) {
		}
		IType sc = null;
		//iterate over implements
		boolean implementTypeImplements = false;
		for (int j = 0; j < typeImplements.length; j++) {
			sc = findType(typeImplements[j]);
			if (sc != null) {
				implementTypeImplements = implementsInterface(sc, interfaceName);
				if (implementTypeImplements)
					return true;
			}
		}
		//Need to check super,
		String superClazz = null;
		try {
			superClazz = type.getSuperclassName();
		} catch (JavaModelException e) {
		}
		if (superClazz != null && superClazz.length() > 0) {
			try {
				superClazz = JDOMSearchHelper.resolveSimpleTypeName(type, superClazz);
			} catch (JavaModelException e) {
			}
			sc = findType(superClazz);
			if (sc != null)
				return implementsInterface(sc, interfaceName);
		}
		return false;
	}

	/**
	 * @param beanClassName
	 * @return
	 */
	protected String getPackageName(String qualifiedName) {
		if (qualifiedName == null || qualifiedName.length() == 0)
			return null;
		int dotIndex = qualifiedName.lastIndexOf('.');
		if (dotIndex > 0)
			return qualifiedName.substring(dotIndex);
		return null;
	}

	private static final byte[] CHARS = new byte[1 << 16];
	public static final int MASK_NAME = 0x08;

	public static final int MASK_NAME_START = 0x04;

	/**
	 * @deprecated
	 * @param c
	 * @return
	 */
	public static boolean isNameStart(int c) {
		return c < 0x10000 && (CHARS[c] & MASK_NAME_START) != 0;
	}

	/**
	 * @deprecated
	 * @param c
	 * @return
	 */
	public static boolean isName(int c) {
		return c < 0x10000 && (CHARS[c] & MASK_NAME) != 0;
	}

	public static boolean isValidNmtoken(String nmtoken) {
		if (nmtoken.length() == 0)
			return false;
		for (int i = 0; i < nmtoken.length(); i++) {
			char ch = nmtoken.charAt(i);
			if (!isName(ch)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return
	 */
	private IStatus validateBeanName() {
		String beanName = getStringProperty(BEAN_NAME);
		if (beanName.length() < 1)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Enter_bean_name_UI_"), null); //$NON-NLS-1$
		// verify if it is a valid CDATA

		//TODO figure out why this doesn't work.
		//if (!isValidNmtoken(beanName))
		//        return EjbPlugin.createErrorStatus(EJBCreationResourceHandler
		//                .getString("Message_invalid_bean_name_UI_"), null); //$NON-NLS-1$
		// check if the bean name already used
		EJBJar jar = getEJBJar();
		if (jar != null && jar.getEnterpriseBeanNamed(beanName) != null)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Bean_name_is_already_used_UI_"), null); //$NON-NLS-1$
		//validate bean name for a valid java type name
		return validateJavaTypeName(beanName, "Bean_name_UI_"); //$NON-NLS-1$
	}

	protected IStatus validateJavaTypeName(String javaName, String messagePrefixKey) {
		IStatus javaStatus = JavaConventions.validateJavaTypeName(javaName);
		if (javaStatus.getSeverity() == IStatus.ERROR)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString(messagePrefixKey) + " " //$NON-NLS-1$
						+ javaStatus.getMessage(), null);
		else if (javaStatus.getSeverity() == IStatus.WARNING)
			return EjbPlugin.createStatus(IStatus.WARNING, -1, EJBCreationResourceHandler.getString(messagePrefixKey) + " " + javaStatus.getMessage(), null); //$NON-NLS-1$
		return OK_STATUS;
	}

	protected IType findType(String qualifiedName) {
		if (qualifiedName != null && qualifiedName.length() > 0) {
			if (typeCache != null) {
				Object cache = typeCache.get(qualifiedName);
				if (cache != null)
					return cache instanceof IType ? (IType) cache : null;
			} else {
				typeCache = new HashMap();
			}
			IType type = JDOMSearchHelper.findType(qualifiedName, false, getJavaProject(), null);
			if (type == null)
				typeCache.put(qualifiedName, qualifiedName);
			else
				typeCache.put(qualifiedName, type);
			return type;
		}
		return null;
	}

	public boolean typeExists(String propertyName) {
		switch (getTypeFlag(propertyName)) {
			case TYPE_BINARY :
			case TYPE_SOURCE :
				return true;
			default :
				return false;
		}
	}

	/**
	 * Used this method to determine the IType returned by the <code>propertyName</code>.
	 * 
	 * @param propertyName
	 * @return int
	 * 
	 * @see CreateEnterpriseBeanDataModel#TYPE_NEW
	 * @see CreateEnterpriseBeanDataModel#TYPE_BINARY
	 * @see CreateEnterpriseBeanDataModel#TYPE_SOURCE
	 */
	public int getTypeFlag(String propertyName) {
		String typeName = (String) getProperty(propertyName);
		if (typeName != null) {
			IType type = findType(typeName);
			if (type != null)
				return type.isBinary() ? TYPE_BINARY : TYPE_SOURCE; //Binary
			// or
			// Source
			// type.
			return TYPE_NEW; //New type.
		}
		return -1; //The property is not set or the name is invalid.
	}

	/**
	 * Return the type of EJB that this model represents. Sublcasses should override to return the
	 * correct type integer.
	 * 
	 * @see CreateEnterpriseBeanDataModel#EJB_TYPE_SESSION
	 * @see CreateEnterpriseBeanDataModel#EJB_TYPE_MDB
	 * @see CreateEnterpriseBeanDataModel#EJB_TYPE_BMP
	 * @see CreateEnterpriseBeanDataModel#EJB_TYPE_CMP
	 * @return int
	 */
	public abstract int getEJBType();

	public int getMinimumSupportedProjectVersion() {
		return J2EEVersionConstants.EJB_1_1_ID;
	}

	protected String[] getStringValues(List values) {
		String[] names = new String[values.size()];
		for (int i = 0; i < values.size(); i++)
			names[i] = values.get(i).toString();
		return names;
	}

	public String getInterfaceType(String propertyName) {
		if (propertyName.equals(BEAN_CLASS_NAME))
			return getBeanClassEJBInterfaceName();
		return ""; //$NON-NLS-1$
	}

	/**
	 * Return the EnterpriseBean that this model represents if it exists.
	 * 
	 * @return
	 */
	public EnterpriseBean getEJB() {
		EJBJar jar = getEJBJar();
		if (jar != null) {
			String name = (String) getProperty(BEAN_NAME);
			if (name != null)
				return jar.getEnterpriseBeanNamed(name);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#basicIsEnabled(java.lang.String)
	 */
	protected Boolean basicIsEnabled(String propertyName) {
		if (propertyName.equals(BEAN_CLASS_SUPERCLASS))
			return isBeanClassSuperclassEnabled();
		else if (propertyName.equals(BEAN_SUPEREJB))
			return getBoolean(!getBooleanProperty(USE_ANNOTATIONS));
		else if (propertyName.equals(BEAN_SUPEREJB_NAME))
			return getBoolean(!getBooleanProperty(USE_ANNOTATIONS));
		if (USE_ANNOTATIONS.equals(propertyName)) {
			if (getEJBNature() != null && getEJBNature().getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3) {
				return Boolean.FALSE;
			}
			return Boolean.TRUE;
		}
		return super.basicIsEnabled(propertyName);
	}

	/**
	 * @return
	 */
	private Boolean isBeanClassSuperclassEnabled() {
		return getBoolean(getProperty(BEAN_SUPEREJB) == null);
	}

	private IEJBCodegenHandler getCodegenHandler() {
		if (codegenHandler == null)
			codegenHandler = EJBCodegenHandlerExtensionReader.getInstance().getEJBExtHandler(getTargetProject());
		return codegenHandler;
	}

}