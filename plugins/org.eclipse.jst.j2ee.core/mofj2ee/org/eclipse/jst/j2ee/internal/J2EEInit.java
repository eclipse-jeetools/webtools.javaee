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
package org.eclipse.jst.j2ee.internal;

import java.lang.reflect.Method;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.impl.ApplicationResourceFactory;
import org.eclipse.jst.j2ee.client.ClientPackage;
import org.eclipse.jst.j2ee.client.impl.ApplicationClientResourceFactory;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.J2EEXMIResourceFactory;
import org.eclipse.jst.j2ee.common.impl.J2EEResourceFactoryRegistry;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.impl.EJBJarResourceFactory;
import org.eclipse.jst.j2ee.ejb.impl.EjbFactoryImpl;
import org.eclipse.jst.j2ee.ejb.util.EJBAttributeMaintenanceFactoryImpl;
import org.eclipse.jst.j2ee.internal.xml.J2EEXmlDtDEntityResolver;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.impl.ConnectorResourceFactory;
import org.eclipse.jst.j2ee.jsp.JspPackage;
import org.eclipse.jst.j2ee.taglib.TaglibPackage;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.impl.WebAppResourceFactory;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapResourceFactory;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;
import org.eclipse.jst.j2ee.webservice.wsclient.impl.WebServicesClientResourceFactory;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddResourceFactory;
import org.eclipse.wst.common.emf.utilities.AdapterFactoryDescriptor;
import org.eclipse.wst.common.emf.utilities.ExtendedEcoreUtil;
import org.eclipse.wst.common.internal.emf.resource.EMF2DOMRenderer;
import org.eclipse.wst.common.internal.emf.resource.EMF2DOMRendererFactory;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;
import org.eclipse.wst.common.internal.emf.utilities.DOMUtilities;
import org.eclipse.wst.common.internal.emf.utilities.EncoderDecoderRegistry;
import org.eclipse.wst.common.internal.emf.utilities.PasswordEncoderDecoder;
import org.eclipse.wst.common.internal.emf.utilities.Revisit;

/**
 * Insert the type's description here.
 * Creation date: (10/3/2000 3:07:37 PM)
 * @author: Administrator
 */
public class J2EEInit {
	
	
	static {
		try { 
			if(Boolean.getBoolean("LOG_XERCES_VERSION")) {				 //$NON-NLS-1$
				Class clz = ClassLoader.getSystemClassLoader().loadClass("org.apache.xerces.impl.Version"); //$NON-NLS-1$
				Method main = clz.getDeclaredMethod("main", new Class[] { String[].class } ); //$NON-NLS-1$
				Object version = clz.newInstance();
				main.invoke(version, new Object[] { new String[] {} }); 
				System.out.println(clz.getResource("Version.class"));  //$NON-NLS-1$
			}
		} catch(Throwable t) {
			System.out.println("Problem while logging version " + t);
			t.printStackTrace();
		} 
	}
	
	protected static boolean initialized = false;
	protected static boolean plugin_initialized = false;
	public static void init() {
		init(true);
	}

	public static void init(boolean shouldPreRegisterPackages) {
		if (!initialized) {
			initialized = true;
			setDefaultEncoderDecoder();
			DOMUtilities.setDefaultEntityResolver(J2EEXmlDtDEntityResolver.INSTANCE);
			org.eclipse.jem.internal.java.init.JavaInit.init(shouldPreRegisterPackages);
			if (shouldPreRegisterPackages)
				preRegisterPackages();
			initResourceFactories();
			EjbFactoryImpl.internalRegisterEJBRelationAdapterFactory(new AdapterFactoryDescriptor() {
				public AdapterFactory createAdapterFactory() {
					return new EJBAttributeMaintenanceFactoryImpl();
				}
			});
			EjbFactoryImpl.internalRegisterRelationshipsAdapterFactory(new AdapterFactoryDescriptor() {
				public AdapterFactory createAdapterFactory() {
					return new EJBAttributeMaintenanceFactoryImpl();
				}
			});
			//TODO: Remove this line after SED Adapter is restored.
			RendererFactory.getDefaultRendererFactory().setValidating(false);

		}
	}

	/** 
	 * If the currently defaulted encoder is the initial pass thru encoder,
	 * then register a Password encoder for security; otherwise if a more sophisticated
	 * encoder is already registered, then do nothing.
	 */
	private static void setDefaultEncoderDecoder() {
		EncoderDecoderRegistry reg = EncoderDecoderRegistry.getDefaultRegistry();
		if (reg.getDefaultEncoderDecoder() == EncoderDecoderRegistry.INITIAL_DEFAULT_ENCODER) {
			reg.setDefaultEncoderDecoder(new PasswordEncoderDecoder());
		}
	}

	private static void preRegisterPackages() {
		//common
		ExtendedEcoreUtil.preRegisterPackage("common.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return CommonPackage.eINSTANCE;
			}
		});
		//application
		ExtendedEcoreUtil.preRegisterPackage("application.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return ApplicationPackage.eINSTANCE;
			}
		});
		//client
		ExtendedEcoreUtil.preRegisterPackage("client.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return ClientPackage.eINSTANCE;
			}
		});
		//webapplication
		ExtendedEcoreUtil.preRegisterPackage("webapplication.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return WebapplicationPackage.eINSTANCE;
			}
		});
		//ejb
		ExtendedEcoreUtil.preRegisterPackage("ejb.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return EjbPackage.eINSTANCE;
			}
		});
		//jca
		ExtendedEcoreUtil.preRegisterPackage("jca.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return JcaPackage.eINSTANCE;
			}
		});
		//webservicesclient
		ExtendedEcoreUtil.preRegisterPackage("webservice_client.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return Webservice_clientPackage.eINSTANCE;
			}
		});
		//webservicescommon
		ExtendedEcoreUtil.preRegisterPackage("wscommon.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return WscommonPackage.eINSTANCE;
			}
		});
		//webservicesdd
		ExtendedEcoreUtil.preRegisterPackage("wsdd.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return WsddPackage.eINSTANCE;
			}
		});
		//jaxrpcmap
		ExtendedEcoreUtil.preRegisterPackage("jaxrpcmap.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return JaxrpcmapPackage.eINSTANCE;
			}
		});		
//		jsp
		ExtendedEcoreUtil.preRegisterPackage("jsp.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return JspPackage.eINSTANCE;
			}
		});
		//taglib
		ExtendedEcoreUtil.preRegisterPackage("taglib.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return TaglibPackage.eINSTANCE;
			}
		});	
		
	}

	public static void initResourceFactories() {
		//Make protected	
		Revisit.revisit();

		// Only register the default factories if another set has not
		// already been registered.

		Resource.Factory j2ee = J2EEResourceFactoryRegistry.INSTANCE.getFactory(J2EEConstants.EJBJAR_DD_URI_OBJ);
		Resource.Factory defaultFact = J2EEResourceFactoryRegistry.INSTANCE.getFactory(URI.createURI(Resource.Factory.Registry.DEFAULT_EXTENSION));
		if (j2ee == defaultFact) {
			EJBJarResourceFactory.register();
			WebAppResourceFactory.register();
			ApplicationClientResourceFactory.register();
			ApplicationResourceFactory.register();
			ConnectorResourceFactory.register();
			WebServicesClientResourceFactory.register();
			WsddResourceFactory.register();
			//register() is not called on the JaxrpcmapResourceFactory because
			//the jaxprc-mapping descriptor does not have a standard short name.
			//The short names have to be registered once they are known.
			J2EEXMIResourceFactory.register();
		}
		EJBJarResourceFactory.registerDtds();
		WebAppResourceFactory.registerDtds();
		ApplicationClientResourceFactory.registerDtds();
		ApplicationResourceFactory.registerDtds();
		ConnectorResourceFactory.registerDtds();
		WebServicesClientResourceFactory.registerDtds();
		WsddResourceFactory.registerDtds();
		JaxrpcmapResourceFactory.registerDtds();
	}
	public static void setPluginInit(boolean bPluginInit) {
		// Here's where the configuration file would be read.
		plugin_initialized = bPluginInit;
	}
}
