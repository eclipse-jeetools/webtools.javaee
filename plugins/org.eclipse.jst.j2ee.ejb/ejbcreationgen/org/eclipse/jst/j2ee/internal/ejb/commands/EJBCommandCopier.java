/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.commands;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jst.j2ee.common.impl.J2EEResourceFactoryRegistry;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Relationships;
import org.eclipse.jst.j2ee.ejb.impl.EJBJarResourceFactory;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodegenHandlerExtensionReader;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.IEJBCodegenHandler;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.wst.common.emf.utilities.CopyGroup;
import org.eclipse.wst.common.emf.utilities.EtoolsCopyUtility;


public class EJBCommandCopier {

	protected static final Adapter CODEGEN_COPY_ADAPTER = new AdapterImpl();

	protected EtoolsCopyUtility copyUtility;

	protected ResourceSet copyResourceSet;

	protected EJBEditModel editModel;

	protected EJBJar originalEjbJar;

	public static boolean isCodegenCopy(Notifier anObject) {
		if (anObject != null)
			return anObject.eAdapters().contains(CODEGEN_COPY_ADAPTER);
		return false;
	}

	public static void flagAsCodegenCopy(Notifier anObject) {
		anObject.eAdapters().add(CODEGEN_COPY_ADAPTER);
	}

	/**
	 * Constructor for EJBCommandCopier.
	 */
	public EJBCommandCopier(EJBEditModel anEditModel) {
		super();
		editModel = anEditModel;
		initialize();
	}

	protected void initialize() {
		copyUtility = new EtoolsCopyUtility();
		copyUtility.setPreserveIds(true);
	}

	void reset() {
		initialize();
	}

	/**
	 * Gets the copyResourceSet.
	 * 
	 * @return Returns a ResourceSet
	 */
	protected ResourceSet getCopyResourceSet() {
		if (copyResourceSet == null) {
			copyResourceSet = new ResourceSetImpl();
			copyResourceSet.setResourceFactoryRegistry(createResourceFactoryRegistry());
		}
		return copyResourceSet;
	}

	/**
	 * Method createResourceFactoryRegistry.
	 * 
	 * @return Registry
	 */
	private Registry createResourceFactoryRegistry() {
		J2EEResourceFactoryRegistry reg = new J2EEResourceFactoryRegistry();
		reg.registerLastFileSegment(J2EEConstants.EJBJAR_DD_SHORT_NAME, EJBJarResourceFactory.getRegisteredFactory());
		return reg;
	}

	protected EjbFactory getEjbFactory() {
		return ((EjbPackage) EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI)).getEjbFactory();
	}

	protected EObject getCopy(EObject original) {
		return copyUtility.getCopy(original);
	}

	/**
	 * Create a copy of the EJB and the EJB extension (if it exists).
	 */
	public void copy(EnterpriseBean ejb) {
		if (ejb == null || ejb.getEjbJar() == null)
			return;
		int before, after;
		before = getNumberOfCopied20Relationships();
		doCopy(ejb);
		after = getNumberOfCopied20Relationships();
		if (before != after)
			order20Relationships();
	}

	/**
	 * @return
	 */
	private int getNumberOfCopied20Relationships() {
		if (originalEjbJar != null && (originalEjbJar.getVersionID() >= J2EEVersionConstants.EJB_2_0_ID)) {
			Relationships rel = (Relationships) copyUtility.getCopy(originalEjbJar.getRelationshipList());
			if (rel != null)
				return rel.getEjbRelations().size();
		}
		return 0;
	}

	/**
	 * Create a copy of the EJB and the EJB extension (if it exists).
	 */
	protected void doCopy(EnterpriseBean ejb) {
		if (ejb == null)
			return;
		EJBCommandCopyGroup baseCopyGrp = new EJBCommandCopyGroup(ejb, editModel);
		if (baseCopyGrp.shouldAddCopiedResources())
			baseCopyGrp.setCopyContext(getCopyResourceSet());
		doCopy(baseCopyGrp);
	}

	protected void doCopy(List aListOfEJBs) {
		if (aListOfEJBs == null)
			return;
		EJBCopyGroup baseCopyGrp = new EJBCopyGroup(aListOfEJBs, editModel);
		doCopy(baseCopyGrp);
	}

	protected void doCopy(EJBCopyGroup baseCopyGrp) {
		boolean addCopyResources = baseCopyGrp.shouldAddCopiedResources();
		if (baseCopyGrp != null)
			copyUtility.copy(baseCopyGrp);
		if (addCopyResources) {
			CopyGroup extCopy = createExtensionCopyGroup(baseCopyGrp);
			if (extCopy != null) {
				//dcb - Ensure the copy ResourceSet is set on they
				//ExtensionCopyGroup.
				extCopy.setCopyContext(getCopyResourceSet());
				copyUtility.copy(extCopy);
			}
		}
	}

	/**
	 * This is required for code gen to get the relationship roles in the correct order.
	 */
	private void order20Relationships() {
		if (originalEjbJar != null && (originalEjbJar.getVersionID() >= J2EEVersionConstants.EJB_2_0_ID)) {
			Relationships originalRel, copyRel;
			originalRel = originalEjbJar.getRelationshipList();
			if (originalRel != null) {
				copyRel = (Relationships) copyUtility.getCopy(originalRel);
				List originalRelations, copyRelations;
				copyRelations = copyRel.getEjbRelations();
				if (copyRelations.isEmpty() || copyRelations.size() == 1)
					return; //No
				// ordering
				// needed.
				originalRelations = originalRel.getEjbRelations();
				int origSize = originalRelations.size();
				int copySize = copyRelations.size();
				List ordered = new ArrayList(copySize);
				EJBRelation original, copy;
				for (int i = 0; (i < origSize && ordered.size() != copySize); i++) {
					original = (EJBRelation) originalRelations.get(i);
					copy = (EJBRelation) copyUtility.getCopy(original);
					if (copy != null && copyRelations.contains(copy))
						ordered.add(copy);
				}
				copyRel.eSetDeliver(false);
				try {
					copyRelations.clear();
					copyRelations.addAll(ordered);
				} finally {
					copyRel.eSetDeliver(true);
				}
			}
		}
	}

	public void copy(List aListOfEJBs) {
		int before, after;
		before = getNumberOfCopied20Relationships();
		doCopy(aListOfEJBs);
		after = getNumberOfCopied20Relationships();
		if (before != after)
			order20Relationships();
	}

	/**
	 * Create a copy of the EJB extension (if it exists).
	 */
	protected CopyGroup createExtensionCopyGroup(EJBCopyGroup ejbCopyGrp) {
		IEJBCodegenHandler handler = getCodegenHandler();
		if (handler != null)
			return handler.getEJBExtensionCopyGroup(ejbCopyGrp, editModel);
		return null;
	}

	protected IEJBCodegenHandler getCodegenHandler() {
		if (editModel != null)
			return EJBCodegenHandlerExtensionReader.getInstance().getEJBExtHandler(editModel.getProject());
		return EJBCodegenHandlerExtensionReader.getInstance().getEJBExtHandler(null);
	}

	/**
	 * Gets the copyUtility.
	 * 
	 * @return Returns a EtoolsCopyUtility
	 */
	public EtoolsCopyUtility getCopyUtility() {
		return copyUtility;
	}

	public void dispose() {
		copyUtility = null;
	}

}