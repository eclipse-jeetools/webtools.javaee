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
package org.eclipse.jst.j2ee.internal.ejb.workbench.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IRegion;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.internal.adapters.jdom.JDOMSearchHelper;
import org.eclipse.jem.internal.plugin.JavaEMFNature;
import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.Method;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.ejb.MethodTransaction;
import org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBProjectResources;
import org.eclipse.jst.j2ee.model.internal.validation.EJBValidator;
import org.eclipse.jst.j2ee.model.internal.validation.EJBValidatorModelEnum;
import org.eclipse.jst.j2ee.model.internal.validation.IEJBValidatorConstants;
import org.eclipse.jst.j2ee.model.internal.validation.InvalidInputException;
import org.eclipse.jst.j2ee.model.internal.validation.MessageUtility;
import org.eclipse.jst.j2ee.model.internal.validation.ValidationRuleUtility;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;
import org.eclipse.wst.validation.internal.core.IReporter;
import org.eclipse.wst.validation.internal.mof.AWorkbenchMOFHelper;
import org.eclipse.wst.validation.internal.operations.DependencyUtil;
import org.eclipse.wst.validation.internal.operations.WorkbenchReporter;

import com.ibm.wtp.common.logger.LogEntry;
import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * Load the EJB MOF model and return resources for the EJB Validator.
 */
public class EJBHelper extends AWorkbenchMOFHelper {
	protected EJBProjectResources _projectResources = null;
	private IJavaProject[] _dependentJavaProjects = null;
	private IJavaProject[] _requiredJavaProjects = null;
	private IJavaProject _javaProject = null; // this IProject, as an
	// IJavaProject
	private Set _tempSet = null;
	private Map _projectMap = null; // Key is IJavaProject instance, value is
	// either IJavaMOFNature (for non-EJB
	// projects) or an EJBProjectResources. See
	// constructor for more details.
	private static final JavaClass[] EMPTY_ARRAY_JAVACLASS = new JavaClass[0];
	private static LogEntry logEntry;
	private static Logger logger;

	public EJBHelper() {
		super();
		// In the loadChildren method, every time that a JavaClass changes, we
		// need to load the
		// JavaClass which extend/implement it. Because we cannot assume that
		// every JavaClass child
		// is contained in this project, or that every JavaClass child is in an
		// EJB project,
		// maintain a cache of IJavaProject <-> IJavaMOFNature mappings.
		// Whenever we need to load the JavaClass
		// for an IType, if the IType's project is not this project, then
		// return the resource set of
		// the IType's IProject's nature. see the getResourceSet(IType) method.
		//
		// Key = IJavaProject
		// Value = IJavaMOFNature or EJBProjectResources
		// This cache does not contain the current IJavaProject or its nature.
		// Access those through
		// the getProjectResources() method.
		_projectMap = new HashMap();
		registerModel(EJBValidatorModelEnum.EJB, "loadBeans", new Class[]{JavaClass.class}); //$NON-NLS-1$
		registerModel(EJBValidatorModelEnum.CHILDREN, "loadChildren", new Class[]{IReporter.class, Set.class}); //$NON-NLS-1$
		registerModel(EJBValidatorModelEnum.EJB_MODEL, "loadEjbModel"); //$NON-NLS-1$
		registerModel(EJBValidatorModelEnum.EJB_FILE, "loadEjbFile"); //$NON-NLS-1$
		registerModel(EJBValidatorModelEnum.EJB_CLIENTJAR, "loadClientJAR", new Class[]{String.class}); //$NON-NLS-1$
		registerModel(EJBValidatorModelEnum.REMOVE_OLD_MESSAGES, "removeOldMessages", new Class[]{IReporter.class, Map.class}); //$NON-NLS-1$
	}

	/**
	 * @return
	 */
	private static LogEntry getLogEntry() {
		if (logEntry == null) {
			logEntry = new LogEntry(IEJBValidatorConstants.BUNDLE_NAME);
		}
		logEntry.reset(); // reset the values so that we're not logging stale data
		return logEntry;
	}

	/**
	 * @return
	 */
	private static Logger getMsgLogger() {
		if (logger == null)
			logger = EjbPlugin.getPlugin().getMsgLogger();
		return logger;
	}

	/**
	 * When the validation is complete, this method will be called so that the IWorkbenchHelper can
	 * clean up any resources it allocated during the validation.
	 * 
	 * If the cleanup is a long-running operation, subtask messages should be sent to the IReporter.
	 */
	public void cleanup(WorkbenchReporter reporter) {
		if (_projectResources != null) {
			_projectResources.cleanup();
			_projectResources = null;
		}
		if (_projectMap != null) {
			Iterator iterator = _projectMap.values().iterator();
			while (iterator.hasNext()) {
				Object value = iterator.next();
				if (value instanceof EJBProjectResources) {
					((EJBProjectResources) value).cleanup();
				}
			}
			_projectMap.clear();
		}
		_dependentJavaProjects = null;
		_javaProject = null;
	}

	/**
	 * Given an IMessage's target object, return a string which identifies the object, so that the
	 * user can locate it. This method is called only if getLineNo() doesn't return a line number.
	 */
	public String getDescription(Object object) {
		if (object == null) {
			return ""; //$NON-NLS-1$
		}
		if (object instanceof JavaClass) {
			return ResourceHandler.getExternalizedMessage(EJBHelperConstants.LOC_CLASS, new String[]{((JavaClass) object).getQualifiedName()});
		} else if (object instanceof Method) {
			Method method = (Method) object;
			JavaClass clazz = method.getContainingJavaClass();
			return ResourceHandler.getExternalizedMessage(EJBHelperConstants.LOC_METHOD, new String[]{method.getMethodElementSignature(), clazz.getQualifiedName()});
		} else if (object instanceof Field) {
			Field field = (Field) object;
			JavaClass clazz = field.getContainingJavaClass();
			return ResourceHandler.getExternalizedMessage(EJBHelperConstants.LOC_FIELD, new String[]{field.getName(), clazz.getQualifiedName()});
		} else if (object instanceof EnterpriseBean) {
			return ResourceHandler.getExternalizedMessage(EJBHelperConstants.LOC_BEAN, new String[]{((EnterpriseBean) object).getName()});
		} else if (object instanceof EJBJar) {
			return ((EJBJar) object).getDisplayName();
		} else if (object instanceof EJBJarFile) {
			return ((EJBJarFile) object).getName();
		} else if (object instanceof EARFile) {
			return ((EARFile) object).getName();
		} else if (object instanceof CommonRelationshipRole) {
			// EJB 1.1 relationship role
			CommonRelationshipRole role = (CommonRelationshipRole) object;
			EnterpriseBean bean = role.getSourceEntity();
			return ResourceHandler.getExternalizedMessage(EJBHelperConstants.LOC_ROLE, new String[]{role.getName(), ((bean == null) ? "?" : bean.getName())}); //$NON-NLS-1$
		} else if (object instanceof EJBRelation) {
			// EJB 2.0 <ejb-relation>
			EJBRelation relation = (EJBRelation) object;
			return ResourceHandler.getExternalizedMessage(EJBHelperConstants.LOC_EJBRELATION, new String[]{relation.getName()});
		} else if (object instanceof EJBRelationshipRole) {
			// EJB 2.0 <ejb-relationship-role>
			EJBRelationshipRole role = (EJBRelationshipRole) object;
			EJBRelation relation = role.getRelationship();
			return ResourceHandler.getExternalizedMessage(EJBHelperConstants.LOC_EJBRELATIONSHIPROLE, new String[]{role.getName(), ((relation == null) ? "?" : relation.getName())}); //$NON-NLS-1$
		} else if (object instanceof MethodElement) {
			MethodElement element = (MethodElement) object;
			EnterpriseBean bean = element.getEnterpriseBean();
			return ResourceHandler.getExternalizedMessage(EJBHelperConstants.LOC_METHODELEMENT, new String[]{element.getName(), ((bean == null) ? "?" : bean.getName())}); //$NON-NLS-1$
		} else if (object instanceof MethodPermission) {
			MethodPermission element = (MethodPermission) object;
			String description = (element.eIsSet(EjbPackage.eINSTANCE.getMethodPermission_Description())) ? element.getDescription() : ((XMIResource) element.eResource()).getID(element);
			return ResourceHandler.getExternalizedMessage(EJBHelperConstants.LOC_METHODPERMISSION, new String[]{description});
		} else if (object instanceof MethodTransaction) {
			MethodTransaction element = (MethodTransaction) object;
			String description = (element.eIsSet(EjbPackage.eINSTANCE.getMethodTransaction_Description())) ? element.getDescription() : ((XMIResource) element.eResource()).getID(element);
			return ResourceHandler.getExternalizedMessage(EJBHelperConstants.LOC_METHODTRANSACTION, new String[]{description});
		} else if (object instanceof SecurityRoleRef) {
			SecurityRoleRef ref = (SecurityRoleRef) object;
			return ResourceHandler.getExternalizedMessage(EJBHelperConstants.LOC_ROLEREF, new String[]{ref.getName()});
		}
		return super.getDescription(object);
	}

	public IResource getResource(Object object) {
		IResource result = super.getResource(object);
		if ((result != null) && (result.exists())) {
			return result;
		}
		if (object == null) {
			return null;
		}
		if (object instanceof Method) {
			return getFile(((Method) object).getContainingJavaClass());
		} else if (object instanceof Field) {
			return getFile(((Field) object).getContainingJavaClass());
		} else if (object instanceof JavaClass) {
			return getFile((JavaClass) object);
		} else if (object instanceof EObject) {
			return WorkbenchResourceHelper.getFile(((EObject) object).eResource());
		}
		return null;
	}

	/**
	 * When an IValidator associates a target object with an IMessage, the WorkbenchReporter
	 * eventually resolves that target object with an IResource. Sometimes more than one target
	 * object resolves to the same IResource (usually the IProject, which is the default IResource
	 * when an IFile cannot be found). This method is called, by the WorkbenchReporter, so that the
	 * WorkbenchReporter can distinguish between the IMessages which are on the same IResource, but
	 * refer to different target objects. This is needed for the removeAllMessages(IValidator,
	 * Object) method, so that when one target object removes all of its messages, that it doesn't
	 * remove another target object's messages.
	 * 
	 * This method may return null only if object is null. Otherwise, an id which can uniquely
	 * identify a particular object must be returned. The id needs to be unique only within one
	 * particular IValidator.
	 */
	public String getTargetObjectName(Object obj) {
		if (obj == null) {
			return null;
		} else if (obj instanceof Method) {
			return ((Method) obj).getJavaClass().getQualifiedName();
		} else if (obj instanceof Field) {
			return ((Field) obj).getJavaClass().getQualifiedName();
		} else if (obj instanceof JavaClass) {
			return ((JavaClass) obj).getQualifiedName();
		} else if (obj instanceof EnterpriseBean) {
			return "META-INF/ejb-jar.xml"; // Should resolve to ejb-jar.xml. //$NON-NLS-1$
		} else if (obj instanceof EJBJar) {
			return "META-INF/ejb-jar.xml"; // Should resolve to ejb-jar.xml. //$NON-NLS-1$
		} else if (obj instanceof ModuleFile) {
			return "META-INF/ejb-jar.xml"; // Should resolve to ejb-jar.xml. //$NON-NLS-1$
		} else {
			return ""; //$NON-NLS-1$
		}
	}

	public Object loadModel(String symbolicName, Object[] parms) {
		if (isRegistered(symbolicName)) {
			// one of the constants listed in the constructor
			return super.loadModel(symbolicName, parms);
		}
		// else, it's a URI as calculated by getPortableName(IResource)
		Object[] result = new Object[2];
		JavaClass clazz = load(symbolicName); // symbolic name is really the
		// name of a java class
		//D247561 if(clazz == null) {
		//			return null;
		//		}
		// Fix this problem in a later release.
		result[0] = clazz;
		result[1] = loadBeans((JavaClass) result[0]);
		return result;
	}

	public JavaClass load(String uri) {
		if (uri == null) {
			return null;
		}
		IFile file = getProjectResources().getEJBNature().getFile(uri);
		if ((file == null) || (!file.exists())) {
			return null;
		}
		return JemProjectUtilities.getJavaClass(file);
	}

	/**
	 * Return a List of the EnterpriseBeans which use this JavaClass as a remote, home, bean class
	 * or key. This method takes an Object[] parameter so that it can be called by the
	 * AWorkbenchHelper's load mechanism. It is expected that this parameter has one entry, and that
	 * entry is an instance of a JavaClass.
	 */
	public List loadBeans(JavaClass clazz) {
		if (clazz == null) {
			return Collections.EMPTY_LIST;
		}
		// First check this project for beans
		Set tempSet = getTempSet();
		addBeans(clazz, getProjectResources(), tempSet);
		// And then check all projects which depend on this one
		for (int i = 0; i < _dependentJavaProjects.length; i++) {
			Object value = _projectMap.get(_dependentJavaProjects[i]);
			if (value instanceof EJBProjectResources) {
				addBeans(clazz, (EJBProjectResources) value, tempSet);
			}
		}
		List list = new ArrayList();
		list.addAll(tempSet);
		return list;
	}

	/**
	 * Given a list of all of the java types, return a list of all JavaClass types that extend or
	 * implement these types.
	 */
	public JavaClass[] loadChildren(IReporter reporter, Set classes) {
		long start = System.currentTimeMillis(), end = 0;
		JavaClass[] children = EMPTY_ARRAY_JAVACLASS;
		int executionMap = 0;
		Set tempSet = getTempSet();
		Logger aLogger = getMsgLogger();
		try {
			if ((classes == null) || (classes.size() == 0)) {
				executionMap |= 0x00000001;
				return children;
			}
			IProgressMonitor monitor = ((WorkbenchReporter) reporter).getProgressMonitor();
			if (classes.size() == 1) {
				JavaClass clazz = (JavaClass) classes.toArray()[0];
				IType type = getType(clazz);
				if (type != null) {
					ITypeHierarchy hierarchy = type.newTypeHierarchy(monitor);
					IType[] subtypes = hierarchy.getAllSubtypes(type);
					if ((subtypes != null) && (subtypes.length > 0)) {
						for (int j = 0; j < subtypes.length; j++) {
							tempSet.add(subtypes[j]);
						}
					}
				}
			} else {
				// Create a region so that only the top (root) types are used
				// as input to the newTypeHierarchy method. The
				// newTypeHierarchyMethod
				// is a heavy method, so the less it's called, the better.
				IRegion region = JavaCore.newRegion();
				Iterator iterator = classes.iterator();
				while (iterator.hasNext()) {
					if (reporter.isCancelled()) {
						executionMap |= 0x00000002;
						return children;
					}
					JavaClass clazz = (JavaClass) iterator.next();
					IType type = getType(clazz);
					if (type == null) {
						if (aLogger.isLoggingLevel(Level.FINER)) {
							LogEntry entry = getLogEntry();
							entry.setSourceID("EJBHelper::loadChildren(JavaClass[])::region"); //$NON-NLS-1$
							entry.setText((clazz != null) ? clazz.getJavaName() : "null JavaClass"); //$NON-NLS-1$
							aLogger.write(Level.FINER, entry);
						}
					} else {
						region.add(type);
					}
				}
				// Now that we know what the top types are, calculate the
				// hierarchies
				// for all of these types in the workspace.
				IJavaElement[] rootTypes = region.getElements();
				ITypeHierarchy[] rootHierarchies = new ITypeHierarchy[rootTypes.length];
				for (int i = 0; i < rootTypes.length; i++) {
					if (reporter.isCancelled()) {
						executionMap |= 0x00000004;
						return children;
					}
					IType type = (IType) rootTypes[i];
					long hStart = System.currentTimeMillis();
					ITypeHierarchy hierarchy = type.newTypeHierarchy(monitor);
					long hEnd = System.currentTimeMillis();
					if (aLogger.isLoggingLevel(Level.FINER)) {
						LogEntry entry = getLogEntry();
						entry.setSourceID("EJBHelper::loadChildren(JavaClass[])::newTypeHierarchy"); //$NON-NLS-1$
						entry.setElapsedTime(hEnd - hStart);
						entry.setText(type.getElementName());
						aLogger.write(Level.FINER, entry);
					}
					rootHierarchies[i] = hierarchy;
				}
				// For each of the changed types, find its subtypes. Add each
				// subtype to a set so that there are no duplicates.
				iterator = classes.iterator();
				while (iterator.hasNext()) {
					if (reporter.isCancelled()) {
						return children;
					}
					JavaClass clazz = (JavaClass) iterator.next();
					IType type = getType(clazz);
					if (type == null) {
						continue;
					}
					for (int i = 0; i < rootHierarchies.length; i++) {
						ITypeHierarchy hierarchy = rootHierarchies[i];
						if (hierarchy.contains(type)) {
							IType[] subtypes = hierarchy.getAllSubtypes(type);
							if ((subtypes == null) || (subtypes.length == 0)) {
								continue;
							}
							for (int j = 0; j < subtypes.length; j++) {
								tempSet.add(subtypes[j]);
							}
							break; // don't need to check the rest of the
							// hierarchies, because each hierarchy is
							// distinct
						}
					}
				}
			}
			int count = 0;
			JavaClass[] tempChildren = new JavaClass[tempSet.size()];
			Iterator iterator = tempSet.iterator();
			while (iterator.hasNext()) {
				IType type = (IType) iterator.next();
				if (type == null) {
					if (aLogger.isLoggingLevel(Level.FINER)) {
						LogEntry entry = getLogEntry();
						entry.setSourceID("EJBHelper::loadChildren(JavaClass[])"); //$NON-NLS-1$
						entry.setText("null type used in project " + getProject().getName()); //$NON-NLS-1$
						aLogger.write(Level.FINER, entry);
					}
					continue;
				}
				JavaHelpers child = getJavaClass(type);
				if ((child != null) && (child.getWrapper() != null)) {
					tempChildren[count++] = child.getWrapper();
				} else {
					if (aLogger.isLoggingLevel(Level.FINER)) {
						LogEntry entry = getLogEntry();
						entry.setSourceID("EJBHelper::loadChildren(JavaClass[])"); //$NON-NLS-1$
						entry.setText(type.getElementName() + " was found in project " + type.getJavaProject().getProject().getName() + ", but either the JavaHelpers is null or its wrapper is null."); //$NON-NLS-1$ //$NON-NLS-2$
						aLogger.write(Level.FINER, entry);
					}
				}
			}
			if (count != tempChildren.length) {
				executionMap |= 0x00000008;
				children = new JavaClass[count];
				System.arraycopy(tempChildren, 0, children, 0, count);
				tempChildren = null;
			} else {
				executionMap |= 0x00000010;
				children = tempChildren;
			}
		} catch (JavaModelException exc) {
			executionMap |= 0x00000020;
			aLogger = getMsgLogger();
			if (aLogger.isLoggingLevel(Level.SEVERE)) {
				LogEntry entry = getLogEntry();
				entry.setSourceID("EJBHelper::loadChildren(JavaClass[])"); //$NON-NLS-1$
				entry.setTargetException(exc);
				aLogger.write(Level.SEVERE, entry);
			}
		} catch (Throwable exc) {
			executionMap |= 0x00000040;
			aLogger = getMsgLogger();
			if (aLogger.isLoggingLevel(Level.SEVERE)) {
				LogEntry entry = getLogEntry();
				entry.setSourceID("EJBHelper::loadChildren(JavaClass[])"); //$NON-NLS-1$
				entry.setTargetException(exc);
				aLogger.write(Level.SEVERE, entry);
			}
		} finally {
			getTempSet().clear();
			end = System.currentTimeMillis();
			aLogger = getMsgLogger();
			if (aLogger.isLoggingLevel(Level.FINER)) {
				StringBuffer buffer = new StringBuffer("Children found in project "); //$NON-NLS-1$
				buffer.append(getProject().getName());
				buffer.append(": IType["); //$NON-NLS-1$
				buffer.append(children.length);
				buffer.append("] = {"); //$NON-NLS-1$
				for (int i = 0; i < children.length; i++) {
					JavaClass clazz = children[i];
					buffer.append(clazz.getJavaName());
					buffer.append(", "); //$NON-NLS-1$
				}
				buffer.append("}"); //$NON-NLS-1$
				LogEntry entry = getLogEntry();
				entry.setSourceID("EJBHelper::loadChildren(JavaClass[])"); //$NON-NLS-1$
				entry.setText(buffer.toString());
				entry.setElapsedTime(end - start);
				entry.setExecutionMap(executionMap);
				aLogger.write(Level.FINER, entry);
			}
		}
		return children;
	}

	/**
	 * Load the EJB MOF model.
	 */
	public EObject loadEjbFile() {
		return _projectResources.getEJBFile();
	}

	/**
	 * Load the EJB MOF model.
	 */
	public EObject loadEjbModel() {
		return _projectResources.getEJBJar();
	}

	/**
	 * Return a Boolean.TRUE if at least one of the EARs that contain this EJB module also contains
	 * the named client JAR, and the IProject that is the EJB client is accessible. If the project
	 * cannot be found, or is not accessible, return Boolean.FALSE. A null from this method means
	 * that the helper does not support this symbolic model.
	 */
	public Boolean loadClientJAR(String clientJARName) {
		EJBProjectResources res = getProjectResources();
		EJBNatureRuntime nature = res.getEJBNature();
		if (nature != null) {
			IProject project = nature.getDefinedEJBClientJARProject();
			if (project == null) {
				EARNatureRuntime[] earNatures = nature.getReferencingEARProjects();
				for (int i = 0; i < earNatures.length; i++) {
					EARNatureRuntime earNature = earNatures[i];
					IFile file = earNature.getFile(clientJARName);
					if (file == null)
						return Boolean.FALSE;
				}
			} else if (!project.isAccessible())
				return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * This method can be overriden by AWorkbenchHelpers, if they wish to perform some
	 * initialization once the IProject is set. Default is to do nothing.
	 * 
	 * For example, if this IWorkbenchHelper delegates to another IWorkbenchHelper, then that
	 * IWorkbenchHelper's setProject() method should be called here.
	 */
	public void initialize() {
		super.initialize();
		// Always new up the project resources, because the project's model
		// needs to be reloaded every time the project is reset. (i.e., when
		// setProject(IProject) is called, this helper is working on a new
		// project, and its underlying model may have changed since the last
		// time this helper saw it.
		//
		// Since the setProject can be called multiple times by the
		// framework, always make sure that the currently loaded
		// projectResources is cleaned up before it's removed.
		// That is, if _projectResources is not null, then this
		// helper has been initialized with an IProject before. The
		// initialization occurs every time this IHelper is retrieved
		// from the ValidationRegistryReader.
		//
		// After this IHelper is retrieved, validation may or may not be
		// performed with it. For example, if the properties page is brought
		// up by the user, this helper will be retrieved in order to enable,
		// or disable, the EJB Validator. If validation has not run with this
		// helper, _projectResources may be initialized with the wrong
		// IProject.
		// If _projectResources has not been used for validation, it will have
		// loaded nothing, and the cost of cleaning is minimal. If validation
		// was run with this project, then _projectResources will be null,
		// because cleanup(WorkbenchReporter) is called after validation
		// completes.
		if (_projectResources != null) {
			cleanup(null);
		}
		_projectResources = new EJBProjectResources(getProject());
		// Now build the cache of IJavaProjects which depend on this IProject.
		_javaProject = JavaCore.create(getProject());
		_dependentJavaProjects = DependencyUtil.getDependentJavaProjects(_javaProject);
	}

	protected EJBProjectResources getProjectResources() {
		return _projectResources;
	}

	// Can't assume that the JavaClass is in this project, so load its IType,
	// and retrieve the IJavaProject
	// from the IType, and then look in that projct.
	protected IFile getFile(JavaClass clazz) {
		// Not in the current project, so check the IType's project
		IType type = getType(clazz);
		if (type == null) {
			return null;
		}
		IResource resource = type.getResource();
		if (resource != null && resource.getType() == IResource.FILE && resource.isAccessible())
			return (IFile) resource;
		return null;
	}

	protected JavaHelpers getJavaClass(IType type) {
		try {
			ResourceSet resourceSet = getResourceSet();
			if (resourceSet == null) {
				return null;
			}
			return ValidationRuleUtility.getType(type.getFullyQualifiedName(), resourceSet);
		} catch (InvalidInputException e) {
			// this exception has already been logged by ValidationRuleUtility
			return null;
		}
	}

	private IType getType(JavaClass clazz) {
		if (clazz == null) {
			return null;
		}
		IProject project = ProjectUtilities.getProject(clazz);
		IJavaProject javaProj = ProjectUtilities.getJavaProject(project);
		return JDOMSearchHelper.findType(clazz.getJavaPackage().getName(), clazz.getName(), javaProj);
	}

	private void addBeans(JavaClass clazz, EJBProjectResources res, Set tempSet) {
		EJBJar ejbJar = res.getEJBJar();
		if (ejbJar == null) {
			return;
		}
		tempSet.addAll(ejbJar.getEnterpriseBeansWithReference(clazz));
	}

	// Can't assume that the IType is in the same project as the parent class
	// (loadChildren).
	// So keep a cache of referenced IProjects. If a project is in this
	// project's
	// classpath,
	// then it is likely that more than one type is used from the other
	// project, so
	// it's worth
	// the overhead to keep a cache.
	protected ResourceSet getResourceSet() {
		JavaEMFNature nature = JavaEMFNature.getRuntime(getProject());
		if (nature == null) {
			return null;
		}
		return nature.getResourceSet();
	}

	// To reduce object creation, create this hash set once. It's only used in
	// the
	// getSubTypes and loadBeans methods,
	// so this technique is safe.
	private Set getTempSet() {
		if (_tempSet == null) {
			_tempSet = new HashSet();
		} else {
			_tempSet.clear();
		}
		return _tempSet;
	}

	/**
	 * @see org.eclipse.wst.validation.internal.operations.IWorkbenchHelper#getPortableName(IResource)
	 */
	public String getPortableName(IResource resource) {
		// Return the URI of the object.
		if (!(resource instanceof IFile)) {
			return super.getPortableName(resource);
		}
		IFile file = (IFile) resource;
		if ((resource.getFileExtension() != null) && (resource.getFileExtension().equals("java"))) { //$NON-NLS-1$
			JavaClass clazz = JemProjectUtilities.getJavaClass(file);
			if (clazz == null) {
				return super.getPortableName(resource);
			}
			return ArchiveUtil.classNameToJavaUri(clazz.getQualifiedName());
		} else if ((resource.getFileExtension() != null) && (resource.getFileExtension().equals("class"))) { //$NON-NLS-1$
			JavaClass clazz = JemProjectUtilities.getJavaClass(file);
			if (clazz == null) {
				return super.getPortableName(resource);
			}
			return ArchiveUtil.classNameToUri(clazz.getQualifiedName());
		} else if (resource.getName().equals("ejb-jar.xml")) //$NON-NLS-1$
			return ArchiveConstants.EJBJAR_DD_URI;
		return null;
	}

	public void removeOldMessages(IReporter reporter, Map targets) {
		if (targets == null) {
			// Full validation. Remove messages from all prerequisite (e.g.
			// EJB
			// client JAR) projects.
			if (_requiredJavaProjects != null) {
				for (int i = 0; i < _requiredJavaProjects.length; i++) {
					IJavaProject jp = _requiredJavaProjects[i];
					WorkbenchReporter.removeAllMessages(jp.getProject(), EJBValidator.getValidator());
				}
			}
		} else if (targets.size() > 0) {
			Iterator iterator = targets.keySet().iterator();
			Set targetObjects = new HashSet();
			while (iterator.hasNext()) {
				Set ruleTargetObjects = (Set) targets.get(iterator.next());
				targetObjects.addAll(ruleTargetObjects);
			}
			iterator = targetObjects.iterator();
			while (iterator.hasNext()) {
				EJBValidator.TargetObject to = (EJBValidator.TargetObject) iterator.next();
				Object targetParent = to.getTargetParent();
				Object target = to.getTarget();
				String groupName = MessageUtility.getGroupName(targetParent);
				reporter.removeMessageSubset(EJBValidator.getValidator(), target, groupName);
			}
		}
		// else there's nothing to remove, so just return without doing
		// anything
	}
}