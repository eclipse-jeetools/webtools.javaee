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
 * Created on Apr 8, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jem.java.JavaURL;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleImportDataModel;


/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class EJBUnknownPrimKeyHelper {

	/**
	 * @param project
	 */
	public EJBUnknownPrimKeyHelper(EJBModuleImportDataModel ejbDataModel) {
		dm = ejbDataModel;
	}

	public EJBModuleImportDataModel dm;

	public void updateGeneratedKeyField(ContainerManagedEntity entity) {
		if (entity != null) {
			Iterator attrs = entity.getPersistentAttributes().iterator();
			CMPAttribute attr = null;
			String keyFieldName = ContainerManagedEntity.WAS_GENERATED_STRING_FIELD;
			while (attrs.hasNext()) {
				CMPAttribute next = (CMPAttribute) attrs.next();
				if (next.getName().equals(keyFieldName)) {
					attr = next;
					break;
				}
			}
			if (attr == null)
				attr = entity.addPersistentAttributeName(keyFieldName);
			attr.setEType(createJavaClassRef(ContainerManagedEntity.JAVA_LANG_STRING));
			entity.getKeyAttributes().add(attr);
		}
	}

	/**
	 * @param ejbJarFile
	 */
	public void setUpUnknowPrimaryKey() {
		EJBEditModel editModel = null;
		EJBNatureRuntime nature = EJBNatureRuntime.getRuntime(dm.getProject());
		if (nature == null)
			return;
		editModel = nature.getEJBEditModelForWrite(this);
		try {
			EJBJar jar = editModel.getEJBJar();
			if (jar != null) {
				List cmps = jar.getContainerManagedBeans();
				if (!cmps.isEmpty()) {
					for (int i = 0; i < cmps.size(); i++) {
						ContainerManagedEntity entity = (ContainerManagedEntity) cmps.get(i);
						if (entity.getVersionID() >= J2EEVersionConstants.EJB_2_0_ID) {
							JavaClass keyClass = entity.getPrimaryKey();
							if (keyClass == null || keyClass.eIsProxy())
								continue;
							String keyClassName = keyClass.getQualifiedName();
							if (ContainerManagedEntity.UNKNOWN_PRIMARY_KEY.equals(keyClassName)) {
								entity.setPrimaryKeyName(ContainerManagedEntity.WAS_GENERATED_STRING_KEY);
								updateGeneratedKeyField(entity);
							} else if (ContainerManagedEntity.WAS_GENERATED_STRING_KEY.equals(keyClassName))
								updateGeneratedKeyField(entity);
						}
					}
				}
				editModel.saveIfNecessary(this);
			}
		} finally {
			if (editModel != null)
				editModel.releaseAccess(this);
		}
	}

	/**
	 * createJavaClassRef() - create a proxy to a Java class
	 */
	private JavaClass createJavaClassRef(String targetName) {
		JavaClass ref = ((JavaRefPackage) EPackage.Registry.INSTANCE.getEPackage(JavaRefPackage.eNS_URI)).getJavaRefFactory().createJavaClass();
		JavaURL javaurl = new JavaURL(targetName);
		((InternalEObject) ref).eSetProxyURI(URI.createURI(javaurl.getFullString()));
		return ref;
	}
}