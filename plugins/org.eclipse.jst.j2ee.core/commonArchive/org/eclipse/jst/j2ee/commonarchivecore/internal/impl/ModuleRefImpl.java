/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.impl;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveFactoryRegistry;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveRuntimeException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveWrappedException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.NoModuleFileException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ResourceLoadException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategy;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;


public class ModuleRefImpl extends EObjectImpl implements ModuleRef, EObject {
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected ModuleFile moduleFile = null;
	/**
	 * The cached value of the '{@link #getModule() <em>Module</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getModule()
	 * @generated
	 * @ordered
	 */
	protected Module module = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected ModuleRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CommonarchivePackage.eINSTANCE.getModuleRef();
	}

	protected CommonarchiveFactory getCommonArchiveFactory() {
		return CommonArchiveFactoryRegistry.INSTANCE.getCommonArchiveFactory();
	}

	/**
	 * The default is to do nothing; subclasses should override if necessary
	 */
	protected void buildComponentList(EList list) {
		//Default
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public ModuleFile getModuleFileGen() {
		if (moduleFile != null && moduleFile.eIsProxy()) {
			ModuleFile oldModuleFile = moduleFile;
			moduleFile = (ModuleFile) EcoreUtil.resolve(moduleFile, this);
			if (moduleFile != oldModuleFile) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonarchivePackage.MODULE_REF__MODULE_FILE, oldModuleFile, moduleFile));
			}
		}
		return moduleFile;
	}

	public ModuleFile getModuleFile() {
		if (moduleFile == null)
			initModuleFileFromEAR();
		return moduleFile;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ModuleFile basicGetModuleFile() {
		return moduleFile;
	}

	protected void initModuleFileFromEAR() {
		checkEARFile();
		ModuleFile aFile = null;
		try {
			aFile = (ModuleFile) getEarFile().getFile(module.getUri());
		} catch (FileNotFoundException ex) {
			//Ignore
		} catch (ClassCastException ex) {
			//Ignore
		}
		checkType(module, aFile);
		setModuleFile(aFile);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setModuleFile(ModuleFile newModuleFile) {
		ModuleFile oldModuleFile = moduleFile;
		moduleFile = newModuleFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonarchivePackage.MODULE_REF__MODULE_FILE, oldModuleFile, moduleFile));
	}

	public boolean usesAltDD() {
		if (module == null)
			return false;
		return !ArchiveUtil.isNullOrEmpty(module.getAltDD());
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.MODULE_REF__MODULE_FILE :
				return moduleFile != null;
			case CommonarchivePackage.MODULE_REF__EAR_FILE :
				return getEarFile() != null;
			case CommonarchivePackage.MODULE_REF__MODULE :
				return module != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.MODULE_REF__MODULE_FILE :
				setModuleFile((ModuleFile) newValue);
				return;
			case CommonarchivePackage.MODULE_REF__EAR_FILE :
				setEarFile((EARFile) newValue);
				return;
			case CommonarchivePackage.MODULE_REF__MODULE :
				setModule((Module) newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.MODULE_REF__MODULE_FILE :
				setModuleFile((ModuleFile) null);
				return;
			case CommonarchivePackage.MODULE_REF__EAR_FILE :
				setEarFile((EARFile) null);
				return;
			case CommonarchivePackage.MODULE_REF__MODULE :
				setModule((Module) null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	public EObject getAltDeploymentDescriptor() throws ArchiveWrappedException {
		if (getEarFile() == null)
			return null;
		try {
			return getEarFile().getAltDeploymentDescriptor(getModule());
		} catch (Exception ex) {
			throw new ArchiveWrappedException(ex);
		}
	}

	/*
	 * @see ModuleRef#getDeploymentDescriptor()
	 */
	public EObject getDeploymentDescriptor() throws ArchiveWrappedException {
		if (getEarFile() == null)
			return getStandardDeploymentDescriptor();
		try {
			return getEarFile().getDeploymentDescriptor(getModule());
		} catch (Exception ex) {
			throw new ArchiveWrappedException(ex);
		}
	}

	/*
	 * @see ModuleRef#getEARFile()
	 */
	public EObject getStandardDeploymentDescriptor() {
		if (getModuleFile() == null)
			return null;
		return getModuleFile().getStandardDeploymentDescriptor();
	}


	/*
	 * @see ModuleRef#setURI(String)
	 */
	public void setURI(String uri) {
		if (getModuleFile() != null)
			getModuleFile().setURI(uri);

		if (getModule() != null)
			getModule().setUri(uri);
	}

	/*
	 * @see ModuleRef#isClient()
	 */
	public boolean isClient() {
		return false;
	}

	/*
	 * @see ModuleRef#isConnector()
	 */
	public boolean isConnector() {
		return false;
	}

	/*
	 * @see ModuleRef#isEJB()
	 */
	public boolean isEJB() {
		return false;
	}

	/*
	 * @see ModuleRef#isWeb()
	 */
	public boolean isWeb() {
		return false;
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	public EARFile getEarFile() {
		if (eContainerFeatureID != CommonarchivePackage.MODULE_REF__EAR_FILE)
			return null;
		return (EARFile) eContainer;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setEarFile(EARFile newEarFile) {
		if (newEarFile != eContainer || (eContainerFeatureID != CommonarchivePackage.MODULE_REF__EAR_FILE && newEarFile != null)) {
			if (EcoreUtil.isAncestor(this, newEarFile))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newEarFile != null)
				msgs = ((InternalEObject) newEarFile).eInverseAdd(this, CommonarchivePackage.EAR_FILE__MODULE_REFS, EARFile.class, msgs);
			msgs = eBasicSetContainer((InternalEObject) newEarFile, CommonarchivePackage.MODULE_REF__EAR_FILE, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonarchivePackage.MODULE_REF__EAR_FILE, newEarFile, newEarFile));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Module getModule() {
		if (module != null && module.eIsProxy()) {
			Module oldModule = module;
			module = (Module) EcoreUtil.resolve(module, this);
			if (module != oldModule) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonarchivePackage.MODULE_REF__MODULE, oldModule, module));
			}
		}
		return module;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Module basicGetModule() {
		return module;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setModule(Module newModule) {
		Module oldModule = module;
		module = newModule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonarchivePackage.MODULE_REF__MODULE, oldModule, module));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonarchivePackage.MODULE_REF__EAR_FILE :
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, CommonarchivePackage.MODULE_REF__EAR_FILE, msgs);
				default :
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonarchivePackage.MODULE_REF__EAR_FILE :
					return eBasicSetContainer(null, CommonarchivePackage.MODULE_REF__EAR_FILE, msgs);
				default :
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case CommonarchivePackage.MODULE_REF__EAR_FILE :
					return eContainer.eInverseRemove(this, CommonarchivePackage.EAR_FILE__MODULE_REFS, EARFile.class, msgs);
				default :
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonarchivePackage.MODULE_REF__MODULE_FILE :
				if (resolve)
					return getModuleFile();
				return basicGetModuleFile();
			case CommonarchivePackage.MODULE_REF__EAR_FILE :
				return getEarFile();
			case CommonarchivePackage.MODULE_REF__MODULE :
				if (resolve)
					return getModule();
				return basicGetModule();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * Return an input stream from the "resources path" of either the ModuleFile, if the module does
	 * not use alt-dd, or from the altRoot of the module, found in the resources path of the EAR
	 * 
	 * @see LoadStrategy#getResourceInputStream(String)
	 */
	public InputStream getResourceInputStream(String uri) throws IOException {
		if (usesAltDD())
			return getAltResourceInputStream(uri);
		return getLocalResourceInputStream(uri);
	}

	public InputStream getLocalResourceInputStream(String uri) throws IOException {
		checkModuleFile();
		return moduleFile.getResourceInputStream(uri);
	}

	public InputStream getAltResourceInputStream(String uri) throws IOException {
		checkEARFile();
		return getEarFile().getResourceInputStream(uri);
	}

	/*
	 * @see ModuleRef#getUri()
	 */
	public String getUri() {
		if (module != null)
			return module.getUri();
		if (moduleFile != null)
			return moduleFile.getURI();

		return null;
	}

	/**
	 * @see ModuleRef#getAltMofResource(String)
	 */
	public Resource getAltMofResource(String uri) throws FileNotFoundException, ResourceLoadException {
		checkEARFile();
		return getEarFile().getMofResource(uri);
	}

	/**
	 * @see ModuleRef#getLocalMofResource(String)
	 */
	public Resource getLocalMofResource(String uri) throws FileNotFoundException, ResourceLoadException {
		checkModuleFile();
		return moduleFile.getMofResource(uri);
	}

	/**
	 * @see ModuleRef#getMofResource(String)
	 */
	public Resource getMofResource(String uri) throws FileNotFoundException, ResourceLoadException {
		if (usesAltDD())
			return getAltMofResource(uri);
		return getLocalMofResource(uri);
	}

	/**
	 * @see ModuleRef#makeAltMofResource(String)
	 */
	public Resource makeAltMofResource(String uri) throws DuplicateObjectException {
		checkEARFile();
		return getEarFile().makeMofResource(uri);
	}

	protected void checkEARFile() {
		if (getEarFile() == null)
			throw new ArchiveRuntimeException(CommonArchiveResourceHandler.getString("Module_not_in_EAR", new Object[]{getUri()})); //$NON-NLS-1$
	}

	/**
	 * @see ModuleRef#makeLocalMofResource(String)
	 */
	public Resource makeLocalMofResource(String uri) throws DuplicateObjectException {
		checkModuleFile();
		return moduleFile.makeMofResource(uri);

	}

	protected void checkModuleFile() {
		getModuleFile();
		if (moduleFile == null)
			throw new NoModuleFileException(CommonArchiveResourceHandler.getString("Module_file_does_not_exist_2", new Object[]{getUri()})); //$NON-NLS-1$
	}

	protected void checkType(Module moduleDescriptor, File aFile) {
		if (aFile == null) {
			String message = CommonArchiveResourceHandler.getString("A_file_does_not_exist_for_module", new Object[]{moduleDescriptor.getUri()}); //$NON-NLS-1$
			throw new NoModuleFileException(message);
		}
		//Added for defect 113929
		boolean isValid = true;
		String type = CommonArchiveResourceHandler.getString("Module_file"); //$NON-NLS-1$
		if (moduleDescriptor.isWebModule() && !aFile.isWARFile()) {
			isValid = false;
			type = CommonArchiveResourceHandler.getString("A_WAR_file"); //$NON-NLS-1$
		} else if (moduleDescriptor.isEjbModule() && !aFile.isEJBJarFile()) {
			isValid = false;
			type = CommonArchiveResourceHandler.getString("An_EJB_JAR_file"); //$NON-NLS-1$
		} else if (moduleDescriptor.isJavaModule() && !aFile.isApplicationClientFile()) {
			isValid = false;
			type = CommonArchiveResourceHandler.getString("An_Application_Client_JAR_file"); //$NON-NLS-1$
		} else if (moduleDescriptor.isConnectorModule() && !aFile.isRARFile()) {
			isValid = false;
			type = CommonArchiveResourceHandler.getString("A_RAR_file"); //$NON-NLS-1$
		} else if (!aFile.isModuleFile())
			isValid = false;

		if (isValid)
			return;

		String earUri = ""; //$NON-NLS-1$
		if (getEarFile() != null)
			earUri = getEarFile().getURI();
		String message = CommonArchiveResourceHandler.getString("File_not_correct_type", new Object[]{aFile.getURI(), earUri, type}); //$NON-NLS-1$
		throw new ArchiveRuntimeException(message);
	}


	/**
	 * @see ModuleRef#makeMofResource(String)
	 */
	public Resource makeMofResource(String uri) throws DuplicateObjectException {
		if (usesAltDD())
			return makeAltMofResource(uri);
		return makeLocalMofResource(uri);
	}

}
