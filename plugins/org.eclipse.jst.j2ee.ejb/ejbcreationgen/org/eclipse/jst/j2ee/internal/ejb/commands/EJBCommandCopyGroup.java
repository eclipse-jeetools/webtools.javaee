/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.commands;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Relationships;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.wst.common.emf.utilities.EtoolsCopyUtility;


public class EJBCommandCopyGroup extends EJBCopyGroup {

	public EJBCommandCopyGroup(EnterpriseBean ejb, EJBEditModel editModel) {
		super(ejb, editModel);
	}

	public void postCopy(EtoolsCopyUtility copyUtil) {
		if (shouldAddCopiedResources() && getCopyContext() != null) {
			EJBJar jar, copyJar;
			jar = ejb.getEjbJar();
			copyJar = (EJBJar) copyUtil.getCopy(jar);
			if (copyJar == null && jar != null) {
				copyJar = createCopy(jar, copyUtil);
				copyUtil.recordCopy(jar, copyJar);
			}
			if (copyJar != null && copiedEJBs != null) {
				copyJar.getEnterpriseBeans().addAll(copiedEJBs);
			}
			if (copyJar != null && jar != null && copiedRelations != null) {
				Relationships rels = getCopyRelationshipList(jar, copyJar, copyUtil);
				rels.getEjbRelations().addAll(copiedRelations);
			}
		}
	}

	private EJBJar createCopy(EJBJar jar, EtoolsCopyUtility copyUtility) {
		EJBJar copyJar = getEjbFactory().createEJBJar();
		copyJar.eSetDeliver(false);
		copyUtility.recordCopy(jar, copyJar);
		Resource jarRes, copyRes;
		jarRes = jar.eResource();
		if (jarRes != null)
			copyRes = createCopyResource(jarRes);
		else
			copyRes = createCopyResource(ArchiveConstants.EJBJAR_DD_URI_OBJ);
		copyRes.getContents().add(copyJar);
		return copyJar;
	}

	protected Resource createCopyResource(URI aUri) {
		return getCopyContext().createResource(aUri);
	}

	protected Resource createCopyResource(Resource resource) {
		Resource copyRes = createCopyResource(resource.getURI());
		if (resource instanceof XMLResource) {
			XMLResource xmlRes, copyXmlRes;
			xmlRes = (XMLResource) resource;
			copyXmlRes = (XMLResource) copyRes;
			copyXmlRes.setDoctypeValues(xmlRes.getPublicId(), xmlRes.getSystemId());
		}
		return copyRes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.commands.EJBCopyGroup#addCopied(org.eclipse.jst.j2ee.ejb.EnterpriseBean)
	 */
	public void addCopied(EnterpriseBean bean) {
		super.addCopied(bean);
		EJBCommandCopier.flagAsCodegenCopy(bean);
	}


	protected Relationships getCopyRelationshipList(EJBJar jar, EJBJar copyJar, EtoolsCopyUtility copyUtility) {
		Relationships relList = null;
		if (copyJar != null) {
			relList = copyJar.getRelationshipList();
			if (relList == null) {
				relList = (Relationships) copyUtility.newInstance(jar.getRelationshipList());
				copyUtility.recordCopy(jar.getRelationshipList(), relList);
				copyJar.setRelationshipList(relList);
			}
		}
		return relList;
	}

	protected EjbFactory getEjbFactory() {
		return ((EjbPackage) EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI)).getEjbFactory();
	}
}