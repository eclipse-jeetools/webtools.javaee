package org.eclipse.jst.j2ee.internal.ejb.codegen.helpers;

/*
 * Licensed Material - Property of IBM 
 * (C) Copyright IBM Corp. 2002 - All Rights Reserved. 
 * US Government Users Restricted Rights - Use, duplication or disclosure 
 * restricted by GSA ADP Schedule Contract with IBM Corp. 
 */

/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.internal.java.adapters.IJavaClassAdaptor;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.internal.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.java.codegen.IJavaMergeStrategy;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * Insert the type's description here. Creation date: (9/1/2000 1:35:28 PM)
 * 
 * @author: Administrator
 */
public class EnterpriseBeanHelper extends org.eclipse.jst.j2ee.internal.java.codegen.JavaTopLevelGenerationHelper implements IEJBCodegenHelper {
	private EObject fMetaObject;
	private EObject oldMetaObject;
	private EnterpriseBean oldSupertype;
	private EJBClassReferenceHelper fBeanHelper;
	private EJBClassReferenceHelper fRemoteHelper;
	private EJBClassReferenceHelper fHomeHelper;
	protected EJBClassReferenceHelper fConcreteBeanHelper;
	private EJBClassReferenceHelper fLocalHomeHelper;
	private EJBClassReferenceHelper fLocalHelper;
	private EJBClassReferenceHelper fServiceEndpointHelper;
	private boolean isCreate;
	private List extendedHelpers;
	protected IEJBCodegenHandler codegenHandler;
	private IJavaProject ejbClientProject;
	private boolean isEjbClientProjectSet;

	/**
	 * EnterpriseJavaBeanCodegenHelper constructor comment.
	 */
	public EnterpriseBeanHelper(EObject aMetaObject) {
		setMetaObject(aMetaObject);
	}

	/**
	 * append method comment.
	 */
	public void append(EJBGenerationHelper aHelper) {
		if (aHelper != null) {
			if (aHelper.isClassReferenceHelper()) {
				aHelper.setParent(this);
				appendClassReference((EJBClassReferenceHelper) aHelper);
			} else {
				appendExtendedHelper(aHelper);
			}
		}
	}

	/**
	 * appendClassReference method comment.
	 */
	protected void appendClassReference(EJBClassReferenceHelper helper) {
		if (helper.isBeanHelper())
			setBeanHelper(helper);
		else if (helper.isConcreteBeanHelper())
			setConcreteBeanHelper(helper);
		else if (helper.isHomeHelper())
			setHomeHelper(helper);
		else if (helper.isRemoteHelper())
			setRemoteHelper(helper);
		else if (helper.isLocalHomeHelper())
			setLocalHomeHelper(helper);
		else if (helper.isLocalHelper())
			setLocalHelper(helper);
		else if (helper.isServiceEndpointHelper())
			setServiceEndpointHelper(helper);
	}

	/*
	 * Note: the add returns true if you can add a key, but you may need to gray some of the fields
	 * in a CMP like dialog
	 *  
	 */
	public static boolean canAddKeyToClass(EnterpriseBean cmpBean) {

		if (cmpBean == null || !(cmpBean instanceof ContainerManagedEntity))
			return false;

		return canCreateARelationship(cmpBean) && canModifySource(((ContainerManagedEntity) cmpBean).getPrimaryKey());

	}

	/*
	 * Note: the add returns true if you can add a key, but you may need to gray some of the fields
	 * in a CMP like dialog
	 *  
	 */
	public static boolean canAddPersistantField(EnterpriseBean cmpBean) {
		if (cmpBean == null || !(cmpBean instanceof ContainerManagedEntity))
			return false;

		return canModifySource(cmpBean.getEjbClass());
	}

	/**
	 * Return true if the bean and the key JavaClass references can be modified.
	 */
	public static boolean canChangeInheritance(EnterpriseBean anEJB) {
		if (anEJB == null || !anEJB.isEntity())
			return false;
		return canModifySource(anEJB.getEjbClass()) && canModifySource(((Entity) anEJB).getRemoteInterface());
	}

	/**
	 * Return true if the bean and the key JavaClass references can be modified.
	 */
	public static boolean canCreateARelationship(EnterpriseBean anEJB) {
		if (anEJB == null || !anEJB.isContainerManagedEntity())
			return false;
		if (canModifySource(anEJB.getEjbClass())) {
			if (anEJB.getVersionID() <= J2EEVersionConstants.EJB_1_1_ID)
				return canModifySource(anEJB.getRemoteInterface()) && canModifySource(anEJB.getHomeInterface());
			return canModifySource(anEJB.getLocalInterface()) && canModifySource(anEJB.getLocalHomeInterface());
		}
		return false;
	}

	/**
	 * Return true if the bean and the key JavaClass references can be modified.
	 */
	public static boolean canModifyKey(EnterpriseBean anEJB) {
		if (anEJB == null || !anEJB.isEntity())
			return false;
		boolean canModify = canModifySource(anEJB.getEjbClass());
		if (canModify)
			canModify = canModifySource(((Entity) anEJB).getPrimaryKey());
		return canModify;
	}

	/**
	 * Return true if each of its JavaClass references can be modified.
	 */
	public static boolean canModifySource(EnterpriseBean anEJB) {
		if (anEJB == null)
			return false;
		boolean canModify = canModifySource(anEJB.getEjbClass());
		if (anEJB.hasRemoteClient()) {
			if (canModify)
				canModify = canModifySource(anEJB.getHomeInterface());
			if (canModify)
				canModify = canModifySource(anEJB.getRemoteInterface());
		}
		if (anEJB.hasLocalClient()) {
			if (canModify)
				canModify = canModifySource(anEJB.getLocalHomeInterface());
			if (canModify)
				canModify = canModifySource(anEJB.getLocalInterface());
		}
		return canModify;
	}

	/**
	 * Return true if the source type for
	 * 
	 * @aJavaClass is not binary.
	 */
	public static boolean canModifySource(JavaClass aJavaClass) {
		IJavaClassAdaptor adaptor = getJavaClassAdaptor(aJavaClass);
		if (adaptor == null)
			return false;
		return !adaptor.isSourceTypeFromBinary();
	}

	public static boolean canRemovePersistantField(EnterpriseBean bean, boolean isKey) {

		if (bean == null || !bean.isContainerManagedEntity())
			return false;

		ContainerManagedEntity cmpBean = (ContainerManagedEntity) bean;
		return primCanRemovePersistentField(cmpBean, isKey, cmpBean.getRemoteInterface(), cmpBean.getHomeInterface()) || primCanRemovePersistentField(cmpBean, isKey, cmpBean.getLocalInterface(), cmpBean.getLocalHomeInterface());
	}

	private static boolean primCanRemovePersistentField(ContainerManagedEntity cmpBean, boolean isKey, JavaClass clientInt, JavaClass homeInt) {
		return canModifySource(clientInt) && (!isKey || (isKey && (cmpBean.getPrimKeyField() != null || canModifySource(cmpBean.getPrimaryKey()) && canModifySource(homeInt))));
	}

	/**
	 * For EJB creation generation we default to an instance of JavaTagIncrementalMergeStrategy.
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.internal.java.codegen.IJavaMergeStrategy
	 */
	public IJavaMergeStrategy createMergeStrategy() {
		return new org.eclipse.jst.j2ee.internal.java.codegen.JavaIncrementalMergeStrategy();
	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2000 12:15:27 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.ejb.codegen.helpers.EJBClassReferenceHelper
	 */
	public EJBClassReferenceHelper getBeanHelper() {
		return fBeanHelper;
	}

	protected IEJBCodegenHandler getCodegenHandler() {
		if (codegenHandler == null) {
			IProject project = ProjectUtilities.getProject(getMetaObject());
			codegenHandler = EJBCodegenHandlerExtensionReader.getInstance().getEJBExtHandler(project);
		}
		return codegenHandler;
	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2001 9:24:02 AM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.ejb.codegen.helpers.EJBClassReferenceHelper
	 */
	public EJBClassReferenceHelper getConcreteBeanHelper() {
		return fConcreteBeanHelper;
	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2000 11:39:19 AM)
	 * 
	 * @return EnterpriseBean
	 */
	public EnterpriseBean getEjb() {
		return (EnterpriseBean) getMetaObject();
	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2000 12:15:27 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.ejb.codegen.helpers.EJBClassReferenceHelper
	 */
	public EJBClassReferenceHelper getHomeHelper() {
		return fHomeHelper;
	}

	protected static IJavaClassAdaptor getJavaClassAdaptor(JavaClass aJavaClass) {
		if (aJavaClass == null)
			return null;
		return (IJavaClassAdaptor) EcoreUtil.getRegisteredAdapter(aJavaClass, org.eclipse.jem.internal.java.adapters.ReadAdaptor.TYPE_KEY);
	}

	/**
	 * Insert the method's description here. Creation date: (9/11/2000 1:21:03 PM)
	 * 
	 * @return org.eclipse.emf.ecore.EObject
	 */
	public EObject getMetaObject() {
		return fMetaObject;
	}

	/**
	 * Insert the method's description here. Creation date: (12/22/2000 11:11:37 AM)
	 * 
	 * @return org.eclipse.emf.ecore.EObject
	 */
	public org.eclipse.emf.ecore.EObject getOldMetaObject() {
		return oldMetaObject;
	}

	/**
	 * Insert the method's description here. Creation date: (7/12/2001 12:56:19 PM)
	 * 
	 * @return EnterpriseBean
	 */
	public EnterpriseBean getOldSupertype() {
		return oldSupertype;
	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2000 12:15:27 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.ejb.codegen.helpers.EJBClassReferenceHelper
	 */
	public EJBClassReferenceHelper getRemoteHelper() {
		return fRemoteHelper;
	}

	public EnterpriseBean getSupertype() {
		return getSupertype(getEjb());
	}

	public static EnterpriseBean getSupertype(EnterpriseBean anEJB) {
		if (anEJB == null)
			return null;
		EjbModuleExtensionHelper helper = getEjbModuleExtension();
		if (helper != null)
			return helper.getSuperType(anEJB);
		return null;

	}

	protected static EjbModuleExtensionHelper getEjbModuleExtension() {
		return IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(null);
	}

	public boolean isBecomingRootEJB() {
		IEJBCodegenHandler handler = getCodegenHandler();
		if (handler != null)
			return handler.isBecomingRootEJB(getEjb(), getExtendedHelpers(IEJBCodegenHandler.GENRALIZATION_HELPER_KEY));
		return true;
	}

	public boolean isChangingInheritance() {
		IEJBCodegenHandler handler = getCodegenHandler();
		if (handler != null)
			return codegenHandler.isChangingInheritance(getEjb(), getExtendedHelpers(IEJBCodegenHandler.GENRALIZATION_HELPER_KEY));
		return false;

	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2000 12:15:27 PM)
	 * 
	 * @param newBeanHelper
	 *            org.eclipse.jst.j2ee.internal.internal.ejb.codegen.helpers.EJBClassReferenceHelper
	 */
	public void setBeanHelper(EJBClassReferenceHelper newBeanHelper) {
		fBeanHelper = newBeanHelper;
	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2001 9:24:02 AM)
	 * 
	 * @param newConcreteBeanHelper
	 *            org.eclipse.jst.j2ee.internal.internal.ejb.codegen.helpers.EJBClassReferenceHelper
	 */
	public void setConcreteBeanHelper(EJBClassReferenceHelper newConcreteBeanHelper) {
		fConcreteBeanHelper = newConcreteBeanHelper;
	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2000 12:15:27 PM)
	 * 
	 * @param newHomeHelper
	 *            org.eclipse.jst.j2ee.internal.internal.ejb.codegen.helpers.EJBClassReferenceHelper
	 */
	public void setHomeHelper(EJBClassReferenceHelper newHomeHelper) {
		fHomeHelper = newHomeHelper;
	}

	/**
	 * Insert the method's description here. Creation date: (9/11/2000 1:21:03 PM)
	 * 
	 * @param newMetaObject
	 *            org.eclipse.emf.ecore.EObject
	 */
	public void setMetaObject(EObject newMetaObject) {
		fMetaObject = newMetaObject;
	}

	/**
	 * Insert the method's description here. Creation date: (12/22/2000 11:11:37 AM)
	 * 
	 * @param newOldMetaObject
	 *            org.eclipse.emf.ecore.EObject
	 */
	public void setOldMetaObject(org.eclipse.emf.ecore.EObject newOldMetaObject) {
		oldMetaObject = newOldMetaObject;
	}

	/**
	 * Insert the method's description here. Creation date: (7/12/2001 12:56:19 PM)
	 * 
	 * @param newOldSupertype
	 *            EnterpriseBean
	 */
	public void setOldSupertype(EnterpriseBean newOldSupertype) {
		oldSupertype = newOldSupertype;
	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2000 12:15:27 PM)
	 * 
	 * @param newRemoteHelper
	 *            org.eclipse.jst.j2ee.internal.internal.ejb.codegen.helpers.EJBClassReferenceHelper
	 */
	public void setRemoteHelper(EJBClassReferenceHelper newRemoteHelper) {
		fRemoteHelper = newRemoteHelper;
	}

	/**
	 * Gets the localHomeHelper.
	 * 
	 * @return Returns a EJBClassReferenceHelper
	 */
	public EJBClassReferenceHelper getLocalHomeHelper() {
		return fLocalHomeHelper;
	}

	/**
	 * Sets the localHomeHelper.
	 * 
	 * @param localHomeHelper
	 *            The localHomeHelper to set
	 */
	public void setLocalHomeHelper(EJBClassReferenceHelper localHomeHelper) {
		fLocalHomeHelper = localHomeHelper;
	}

	/**
	 * Gets the localHelper.
	 * 
	 * @return Returns a EJBClassReferenceHelper
	 */
	public EJBClassReferenceHelper getLocalHelper() {
		return fLocalHelper;
	}

	/**
	 * Sets the localHelper.
	 * 
	 * @param localHelper
	 *            The localHelper to set
	 */
	public void setLocalHelper(EJBClassReferenceHelper localHelper) {
		fLocalHelper = localHelper;
	}

	/**
	 * Gets the localHelper.
	 * 
	 * @return Returns a EJBClassReferenceHelper
	 */
	public EJBClassReferenceHelper getServiceEndpointHelper() {
		return fServiceEndpointHelper;
	}

	/**
	 * Sets the localHelper.
	 * 
	 * @param localHelper
	 *            The localHelper to set
	 */
	public void setServiceEndpointHelper(EJBClassReferenceHelper serviceEndpointHelper) {
		fServiceEndpointHelper = serviceEndpointHelper;
	}

	/**
	 * Returns the isCreate.
	 * 
	 * @return boolean
	 */
	public boolean isCreate() {
		return isCreate;
	}

	/**
	 * Sets the isCreate.
	 * 
	 * @param isCreate
	 *            The isCreate to set
	 */
	public void setIsCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}

	/**
	 * Return true if the EJB is not null and it is not contained by the EJBJar. This is necessary
	 * to tell if the EJB was removed but the generated classes are not to be deleted.
	 */
	public boolean isEJBRemoved() {
		EnterpriseBean ejb = getEjb();
		return ejb != null && ejb.getEjbJar() == null;
	}

	public void appendExtendedHelper(EJBGenerationHelper helper) {
		if (helper != null) {
			if (extendedHelpers == null)
				extendedHelpers = new ArrayList();
			extendedHelpers.add(helper);
			helper.setParent(this);
		}
	}

	public List getExtendedHelpers(Object type) {
		List result = null;
		if (extendedHelpers != null && !extendedHelpers.isEmpty()) {
			EJBGenerationHelper helper;
			for (int i = 0; i < extendedHelpers.size(); i++) {
				helper = (EJBGenerationHelper) extendedHelpers.get(i);
				if (helper.isHelperForType(type)) {
					if (result == null)
						result = new ArrayList(extendedHelpers.size());
					result.add(helper);
				}
			}
		}
		if (result != null)
			return result;
		return Collections.EMPTY_LIST;
	}

	/**
	 * Return the IJavaProject for the EJB client project if one exists.
	 */
	public IJavaProject getEJBClientJavaProject() throws GenerationException {
		if (!isEjbClientProjectSet) {
			isEjbClientProjectSet = true;
			IJavaProject javaProject = super.getJavaProject();
			EJBNatureRuntime runtime = EJBNatureRuntime.getRuntime(javaProject.getProject());
			if (runtime != null) {
				IProject proj = runtime.getDefinedEJBClientJARProject();
				if (proj != null && proj.isAccessible())
					ejbClientProject = JavaCore.create(proj);
			}
		}
		return ejbClientProject;
	}
}