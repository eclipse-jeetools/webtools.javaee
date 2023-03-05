/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core.internal.util;

import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.jst.javaee.application.IApplicationResource;
import org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage;
import org.eclipse.jst.javaee.applicationclient.ApplicationClient;
import org.eclipse.jst.javaee.applicationclient.IApplicationClientResource;
import org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage;
import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.IEJBResource;
import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;
import org.eclipse.jst.javaee.jca.Connector;
import org.eclipse.jst.javaee.jca.IConnectorResource;
import org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage;
import org.eclipse.jst.javaee.web.IWebResource;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WebAppVersionType;
import org.eclipse.jst.javaee.web.WebFragment;
import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;
import org.eclipse.jst.javaee.webfragment.internal.util.WebfragmentResourceImpl;

public class JavaEEExtendedMetaData extends BasicExtendedMetaData {

	protected XMLResource resource = null;
	protected String uri = null;
	protected String javaeeUri = null;
	private volatile boolean isInited = false;

	public JavaEEExtendedMetaData() {
		super();
	}

	public JavaEEExtendedMetaData(XMLResource resource) {
		super();
		this.resource = resource;
	}

	// this is the one that should be used
	public JavaEEExtendedMetaData(Registry registry) {
		super(registry);
	}

	public JavaEEExtendedMetaData(String annotationURI, Registry registry) {
		super(annotationURI, registry);
	}

	public JavaEEExtendedMetaData(String annotationURI, Registry registry,
			Map<EModelElement, EAnnotation> annotationMap) {
		super(annotationURI, registry, annotationMap);
	}

	protected synchronized void init()
	{
		if (!isInited)
		{
			isInited = true;
			if (resource != null)
			{
				registry = resource.getResourceSet().getPackageRegistry();
				initURIs();
				// release the resource to make sure there is no memory leak
				// resource = null;
			}
		}
	}

	private void initURIs() {
		String version = null;
		if (uri == null)
		{
			if (resource instanceof IApplicationResource)
			{
				Application a = ((IApplicationResource)resource).getApplication();
				if (a != null)
				{
					version = a.getVersion();
					if(J2EEVersionConstants.VERSION_5_TEXT.equals(version)) {
						uri = J2EEConstants.APPLICATION_SCHEMA_LOC_5;
						javaeeUri = J2EEConstants.JAVAEE_NS_URL;
					}
					else if(J2EEVersionConstants.VERSION_6_TEXT.equals(version)) {
						uri = J2EEConstants.APPLICATION_SCHEMA_LOC_6;
						javaeeUri = J2EEConstants.JAVAEE_NS_URL;
					}
					else if(J2EEVersionConstants.VERSION_7_TEXT.equals(version)) {
						uri = J2EEConstants.APPLICATION_SCHEMA_LOC_7;
						javaeeUri = J2EEConstants.JAVAEE7_NS_URL;
					}
					else if(J2EEVersionConstants.VERSION_10_TEXT.equals(version)) {
                        uri = J2EEConstants.APPLICATION_SCHEMA_LOC_10;
                        javaeeUri = J2EEConstants.JAKARTAEE10_NS_URL;
                    }
					else {
						uri = J2EEConstants.APPLICATION_SCHEMA_LOC_8;
						javaeeUri = J2EEConstants.JAVAEE7_NS_URL;
					}
					// release the resource to make sure there is no memory leak
					resource = null;
				}
			}
			if (resource instanceof IApplicationClientResource)
			{
				ApplicationClient ac = ((IApplicationClientResource)resource).getApplicationClient();
				if (ac != null)
				{
					version = ac.getVersion();
					if(J2EEVersionConstants.VERSION_5_TEXT.equals(version)) {
						uri = J2EEConstants.APP_CLIENT_SCHEMA_LOC_5;
						javaeeUri = J2EEConstants.JAVAEE_NS_URL;
					}
					else if(J2EEVersionConstants.VERSION_6_TEXT.equals(version)) {
						uri = J2EEConstants.APP_CLIENT_SCHEMA_LOC_6;
						javaeeUri = J2EEConstants.JAVAEE_NS_URL;
					}
					else if (J2EEVersionConstants.VERSION_7_TEXT.equals(version)) {
						uri = J2EEConstants.APP_CLIENT_SCHEMA_LOC_7;
						javaeeUri = J2EEConstants.JAVAEE7_NS_URL;
					}
					else if (J2EEVersionConstants.VERSION_10_TEXT.equals(version)) {
                        uri = J2EEConstants.APP_CLIENT_SCHEMA_LOC_10;
                        javaeeUri = J2EEConstants.JAKARTAEE10_NS_URL;
                    }
					else {
						uri = J2EEConstants.APP_CLIENT_SCHEMA_LOC_8;
						javaeeUri = J2EEConstants.JAVAEE7_NS_URL;
					}
					// release the resource to make sure there is no memory leak
					resource = null;
				}
			}
			if (resource instanceof IEJBResource)
			{
				EJBJar ejb = ((IEJBResource)resource).getEjbJar();
				if (ejb != null)
				{
					version = ejb.getVersion();
					if(J2EEVersionConstants.VERSION_3_0_TEXT.equals(version)) {
						uri = J2EEConstants.EJB_JAR_SCHEMA_LOC_3_0;
						javaeeUri = J2EEConstants.JAVAEE_NS_URL;
					}
					else if(J2EEVersionConstants.VERSION_3_1_TEXT.equals(version)) {
						uri = J2EEConstants.EJB_JAR_SCHEMA_LOC_3_1;
						javaeeUri = J2EEConstants.JAVAEE_NS_URL;
					}
					else if(J2EEVersionConstants.VERSION_4_0_TEXT.equals(version)) {
                        uri = J2EEConstants.EJB_JAR_SCHEMA_LOC_4_0;
                        javaeeUri = J2EEConstants.JAKARTAEE10_NS_URL;
                    }
					else {
						uri = J2EEConstants.EJB_JAR_SCHEMA_LOC_3_2;
						javaeeUri = J2EEConstants.JAVAEE7_NS_URL;
					}
					// release the resource to make sure there is no memory leak
					resource = null;
				}
			}
			if (resource instanceof IConnectorResource)
			{
				Connector c = ((IConnectorResource)resource).getConnector();
				if (c != null)
				{
					version = c.getVersion();
					if(J2EEVersionConstants.VERSION_1_6_TEXT.equals(version)) {
						uri = J2EEConstants.CONNECTOR_SCHEMA_LOC_1_6;
						javaeeUri = J2EEConstants.JAVAEE_NS_URL;
					}
					else if(J2EEVersionConstants.VERSION_2_1_TEXT.equals(version)) {
                        uri = J2EEConstants.CONNECTOR_SCHEMA_LOC_2_1;
                        javaeeUri = J2EEConstants.JAKARTAEE10_NS_URL;
                    }
					else
					{
						uri = J2EEConstants.CONNECTOR_SCHEMA_LOC_1_7;
						javaeeUri = J2EEConstants.JAVAEE7_NS_URL;
					}
					// release the resource to make sure there is no memory leak
					resource = null;
				}
			}
			if (resource instanceof IWebResource)
			{
				WebApp wa = ((IWebResource)resource).getWebApp();
				if (wa != null)
				{
					WebAppVersionType wavt = wa.getVersion();
					version = wavt.getLiteral();
					if(J2EEVersionConstants.VERSION_2_5_TEXT.equals(version)) {
						uri = J2EEConstants.WEB_APP_SCHEMA_LOC_2_5;
						javaeeUri = J2EEConstants.JAVAEE_NS_URL;
					}
					else if(J2EEVersionConstants.VERSION_3_0_TEXT.equals(version)) {
						uri = J2EEConstants.WEB_APP_SCHEMA_LOC_3_0;
						javaeeUri = J2EEConstants.JAVAEE_NS_URL;
					}
					else if(J2EEVersionConstants.VERSION_3_1_TEXT.equals(version)) {
						uri = J2EEConstants.WEB_APP_SCHEMA_LOC_3_1;
						javaeeUri = J2EEConstants.JAVAEE7_NS_URL;
					}
					else if(J2EEVersionConstants.VERSION_5_0_TEXT.equals(version)) {
                        uri = J2EEConstants.WEB_APP_SCHEMA_LOC_5_0;
                        javaeeUri = J2EEConstants.JAKARTAEE9_NS_URL;
                    }
					else if(J2EEVersionConstants.VERSION_6_0_TEXT.equals(version)) {
                        uri = J2EEConstants.WEB_APP_SCHEMA_LOC_6_0;
                        javaeeUri = J2EEConstants.JAKARTAEE10_NS_URL;
                    }
					else {
						uri = J2EEConstants.WEB_APP_SCHEMA_LOC_4_0;
						javaeeUri = J2EEConstants.JAVAEE7_NS_URL;
					}
					// release the resource to make sure there is no memory leak
					resource = null;
				}
			}
			if (resource instanceof WebfragmentResourceImpl)
			{
				WebFragment wf = ((WebfragmentResourceImpl)resource).getWebFragment();
				if (wf != null)
				{
					WebAppVersionType wavt = wf.getVersion();
					version = wavt.getLiteral();
					if(J2EEVersionConstants.VERSION_3_0_TEXT.equals(version)) {
						uri = J2EEConstants.WEBFRAGMENT_SCHEMA_LOC_3_0;
						javaeeUri = J2EEConstants.JAVAEE_NS_URL;
					}
					else if(J2EEVersionConstants.VERSION_3_1_TEXT.equals(version)) {
						uri = J2EEConstants.WEBFRAGMENT_SCHEMA_LOC_3_1;
						javaeeUri = J2EEConstants.JAVAEE7_NS_URL;
					}
					else if(J2EEVersionConstants.VERSION_5_0_TEXT.equals(version)) {
                        uri = J2EEConstants.WEBFRAGMENT_SCHEMA_LOC_5_0;
                        javaeeUri = J2EEConstants.JAKARTAEE9_NS_URL;
                    }
					else if(J2EEVersionConstants.VERSION_6_0_TEXT.equals(version)) {
                        uri = J2EEConstants.WEBFRAGMENT_SCHEMA_LOC_6_0;
                        javaeeUri = J2EEConstants.JAKARTAEE10_NS_URL;
                    }
					else
					{
						uri = J2EEConstants.WEBFRAGMENT_SCHEMA_LOC_4_0;
						javaeeUri = J2EEConstants.JAVAEE7_NS_URL;
					}
					// release the resource to make sure there is no memory leak
					resource = null;
				}
			}
		}
	}
	
	@Override
	public String getNamespace(EPackage ePackage)
	{
		// need to handle the reverse mapping from the various packages to the proper URI for each version
		String retVal = null;
		initURIs();
		if (ePackage instanceof ApplicationPackage || ePackage instanceof ApplicationclientPackage ||ePackage instanceof EjbPackage ||ePackage instanceof JcaPackage || ePackage instanceof WebPackage)
		{
			// bug 409872 - we need to return the Java EE namespace, not the xsd URI
//			retVal = uri;
			retVal = javaeeUri;
		}
		else if (ePackage instanceof JavaeePackage)
		{
			retVal = javaeeUri;
		}
		else
		{
			retVal = super.getNamespace(ePackage);
		}
		return retVal;
	}

	@Override
	public EPackage getPackage(String namespace)
	{
		init();
		return super.getPackage(namespace);
	}

	@Override
	public void putPackage(String namespace, EPackage ePackage)
	{
		init();
		super.putPackage(namespace, ePackage);
	}
}
