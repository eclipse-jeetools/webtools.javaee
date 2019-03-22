/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 27, 2003
 *
 */
package org.eclipse.jst.j2ee.ejb.internal.impl;

import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jst.j2ee.common.internal.impl.XMLResourceImpl;
import org.eclipse.jst.j2ee.core.internal.plugin.J2EECorePlugin;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.internal.model.translator.ejb.EJBJarTranslator;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.wst.common.internal.emf.resource.Renderer;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;

/**
 * @author schacher
 */
public class EJBResourceImpl extends XMLResourceImpl implements EJBResource {

	/**
	 * @param uri
	 * @param aRenderer
	 */
	public EJBResourceImpl(URI uri, Renderer aRenderer) {
		super(uri, aRenderer);
	}

	/**
	 * @param aRenderer
	 */
	public EJBResourceImpl(Renderer aRenderer) {
		super(aRenderer);
	}

	/*
	 * @see EJBResource#isEJB1_1()
	 * @deprecated - use getModuleVersionID() and J2EEVersionConstants
	 */
	public boolean isEJB1_1() {
		return getModuleVersionID() == EJB_1_1_ID;
	}

	/*
	 * @see EJBResource#isEJB2_0()
	 * @deprecated - use getModuleVersionID() and J2EEVersionConstants
	 */
	public boolean isEJB2_0() {
		return getModuleVersionID() == EJB_2_0_ID;
	}



	/* (non-Javadoc)
	 * @see com.ibm.etools.j2eexml.XMLResource#getType()
	 */
	public int getType() {
		return XMLResource.EJB_TYPE;
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.TranslatorResource#getDoctype()
	 */
	public String getDoctype() {
		switch (getJ2EEVersionID()) {
			case (J2EE_1_2_ID) :
			case (J2EE_1_3_ID) :	
				return J2EEConstants.EJBJAR_DOCTYPE;
			default :
				return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.j2eexml.XMLResourceImpl#getJ2EE_1_2_PublicID()
	 */
	@Override
	public String getJ2EE_1_2_PublicID() {
		return J2EEConstants.EJBJAR_PUBLICID_1_1;
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.j2eexml.XMLResourceImpl#getJ2EE_1_2_SystemID()
	 */
	@Override
	public String getJ2EE_1_2_SystemID() {
		return J2EEConstants.EJBJAR_SYSTEMID_1_1;
	}
	
	@Override
	public String getJ2EE_Alt_1_2_SystemID() {
		return J2EEConstants.EJBJAR_ALT_SYSTEMID_1_1;
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.j2eexml.XMLResourceImpl#getJ2EE_1_3_PublicID()
	 */
	@Override
	public String getJ2EE_1_3_PublicID() {
		return J2EEConstants.EJBJAR_PUBLICID_2_0;
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.j2eexml.XMLResourceImpl#getJ2EE_1_3_SystemID()
	 */
	@Override
	public String getJ2EE_1_3_SystemID() {
		return J2EEConstants.EJBJAR_SYSTEMID_2_0;
	}
	
	@Override
	public String getJ2EE_Alt_1_3_SystemID() {
		return J2EEConstants.EJBJAR_ALT_SYSTEMID_2_0;
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.TranslatorResource#getRootTranslator()
	 */
	public Translator getRootTranslator() {
		return EJBJarTranslator.INSTANCE;
	}
	
	/* Return J2EE version based on module version
	 */
	@Override
	public int getJ2EEVersionID() {
		switch (getModuleVersionID()) {
			case J2EEVersionConstants.EJB_1_0_ID :
				return J2EEVersionConstants.J2EE_1_2_ID;
			case J2EEVersionConstants.EJB_1_1_ID :
				return J2EEVersionConstants.J2EE_1_2_ID;
			case J2EEVersionConstants.EJB_2_0_ID :
				return J2EEVersionConstants.J2EE_1_3_ID;
			case J2EEVersionConstants.EJB_2_1_ID :
				return J2EEVersionConstants.J2EE_1_4_ID;
			case J2EEVersionConstants.EJB_3_0_ID :
				return J2EEVersionConstants.JEE_5_0_ID;
			case J2EEVersionConstants.EJB_3_1_ID :
				return J2EEVersionConstants.JEE_6_0_ID;
			case J2EEVersionConstants.EJB_3_2_ID :
				return J2EEVersionConstants.JEE_7_0_ID;
			default :
			return J2EEVersionConstants.J2EE_1_4_ID;
		}
	}
	
	/**
	 * Return the first element in the EList.
	 */
	public EJBJar getEJBJar() {
		return (EJBJar) getRootObject();
	}
	
	public void setBatchMode(boolean isBatch) {
		renderer.setBatchMode(isBatch);

	}
	/* 
	 * This directly sets the module version id
	 */
	@Override
	public void setModuleVersionID(int id) {
		super.setVersionID(id);
		switch (id) {
				case (EJB_3_2_ID) :
					super.setDoctypeValues(null, null);
					primSetVersionID(EJB_3_2_ID);
					break;
				case (EJB_3_1_ID) :
					super.setDoctypeValues(null, null);
					primSetVersionID(EJB_3_1_ID);
					break;
				case (EJB_3_0_ID) :
					super.setDoctypeValues(null, null);
					primSetVersionID(EJB_3_0_ID);
					break;
				case (EJB_2_1_ID) :
					super.setDoctypeValues(null, null);
					primSetVersionID(EJB_2_1_ID);
					break;
				case (EJB_2_0_ID) :
					super.setDoctypeValues(getJ2EE_1_3_PublicID(), getJ2EE_1_3_SystemID());
					break;
				case (EJB_1_1_ID) :
					super.setDoctypeValues(getJ2EE_1_2_PublicID(), getJ2EE_1_2_SystemID());
					break;
				case (EJB_1_0_ID) :
					super.setDoctypeValues(getJ2EE_1_2_PublicID(), getJ2EE_1_2_SystemID());
					
			}
		syncVersionOfRootObject();
	}
	/*
	 * Based on the J2EE version, this will set the module version
	 */
	@Override
	public void setJ2EEVersionID(int id) {
	switch (id) {
		case (JEE_8_0_ID) : 
		case (JEE_7_0_ID) :
					primSetDoctypeValues(null, null);
					primSetVersionID(EJB_3_2_ID);
					break;
		case (JEE_6_0_ID) :
					primSetDoctypeValues(null, null);
					primSetVersionID(EJB_3_1_ID);
					break;
		case (JEE_5_0_ID) :
					primSetDoctypeValues(null, null);
					primSetVersionID(EJB_3_0_ID);
					break;
		case (J2EE_1_4_ID) :
					primSetDoctypeValues(null, null);
					primSetVersionID(EJB_2_1_ID);
					break;
		case (J2EE_1_3_ID) :
					primSetDoctypeValues(getJ2EE_1_3_PublicID(), getJ2EE_1_3_SystemID());
					primSetVersionID(EJB_2_0_ID);
					break;
		case (J2EE_1_2_ID) :
					primSetDoctypeValues(getJ2EE_1_2_PublicID(), getJ2EE_1_2_SystemID());
					primSetVersionID(EJB_1_1_ID);
			}
		syncVersionOfRootObject();
	}
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.TranslatorResourceImpl#getDefaultVersionID()
	 */
	@Override
	protected int getDefaultVersionID() {
		return EJB_2_1_ID;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.common.impl.XMLResourceImpl#syncVersionOfRootObject()
	 */
	@Override
	protected void syncVersionOfRootObject() {
		EJBJar ejbJar = getEJBJar();
		if (ejbJar == null)
			return;
		
		String version = ejbJar.getVersion();
		String newVersion = getModuleVersionString();
		if (!newVersion.equals(version))
			ejbJar.setVersion(newVersion);
	}

	public boolean isBatchMode() {
		return renderer.isBatchMode();
	}
	@Override
	public void setDoctypeValues(String publicId, String systemId) {
		
		int myVersion = J2EE_1_4_ID;
		if (systemId == null) {
			myVersion = primGetVersionID();
			setModuleVersionID(myVersion);
			return;
		}
		super.setDoctypeValues(publicId, systemId);
		
}

private int primGetVersionID() {
	IFile afile = WorkbenchResourceHelper.getFile(this);
	InputStream in = null;
	JavaEEQuickPeek quickPeek = null;
	if (afile != null && afile.exists()) {
		try {
			in = afile.getContents();
			quickPeek = new JavaEEQuickPeek(in);
			return quickPeek.getVersion();
		}
		catch (CoreException e) {
			J2EECorePlugin.logError(e);
		}
	
	}
	return getModuleVersionID();
}


}
