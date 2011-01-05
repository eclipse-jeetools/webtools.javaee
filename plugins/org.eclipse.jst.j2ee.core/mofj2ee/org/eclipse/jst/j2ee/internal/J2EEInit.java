/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
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

import org.eclipse.core.runtime.jobs.ILock;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.internal.impl.ApplicationPackageImpl;
import org.eclipse.jst.j2ee.application.internal.impl.ApplicationResourceFactory;
import org.eclipse.jst.j2ee.client.ClientFactory;
import org.eclipse.jst.j2ee.client.ClientPackage;
import org.eclipse.jst.j2ee.client.internal.impl.ApplicationClientResourceFactory;
import org.eclipse.jst.j2ee.client.internal.impl.ClientPackageImpl;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.internal.impl.EJBJarResourceFactory;
import org.eclipse.jst.j2ee.ejb.internal.impl.EjbFactoryImpl;
import org.eclipse.jst.j2ee.ejb.internal.impl.EjbPackageImpl;
import org.eclipse.jst.j2ee.ejb.internal.util.EJBAttributeMaintenanceFactoryImpl;
import org.eclipse.jst.j2ee.internal.xml.J2EEXmlDtDEntityResolver;
import org.eclipse.jst.j2ee.jca.JcaFactory;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.internal.impl.ConnectorResourceFactory;
import org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl;
import org.eclipse.jst.j2ee.jsp.JspFactory;
import org.eclipse.jst.j2ee.jsp.JspPackage;
import org.eclipse.jst.j2ee.jsp.internal.impl.JspPackageImpl;
import org.eclipse.jst.j2ee.taglib.internal.TaglibFactory;
import org.eclipse.jst.j2ee.taglib.internal.TaglibPackage;
import org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.internal.impl.WebAppResourceFactory;
import org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl;
import org.eclipse.jst.j2ee.webservice.internal.wsdd.WsddResourceFactory;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapFactory;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapResourceFactory;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientFactory;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;
import org.eclipse.jst.j2ee.webservice.wsclient.internal.impl.WebServicesClientResourceFactory;
//import org.eclipse.jst.j2ee.webservice.wsclient.internal.impl.Webservice_clientPackageImpl;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage;
import org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.WscommonPackageImpl;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddFactory;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;
import org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.WsddPackageImpl;
import org.eclipse.wst.common.internal.emf.utilities.AdapterFactoryDescriptor;
import org.eclipse.wst.common.internal.emf.utilities.DOMUtilities;
import org.eclipse.wst.common.internal.emf.utilities.EncoderDecoderRegistry;
import org.eclipse.wst.common.internal.emf.utilities.ExtendedEcoreUtil;
import org.eclipse.wst.common.internal.emf.utilities.PasswordEncoderDecoder;
import org.eclipse.wst.common.internal.emf.utilities.Revisit;
import org.eclipse.jst.j2ee.core.internal.plugin.J2EECorePlugin;
import org.eclipse.jst.javaee.applicationclient.ApplicationclientFactory;
import org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationclientPackageImpl;
import org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.core.internal.impl.JavaeePackageImpl;
import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;
import org.eclipse.jst.javaee.web.WebFactory;
import org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl;
import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;
import org.eclipse.jst.javaee.webapp.internal.impl.WebappPackageImpl;
import org.eclipse.jst.javaee.webapp.internal.metadata.WebappPackage;
import org.eclipse.jst.javaee.webfragment.internal.impl.WebfragmentPackageImpl;
import org.eclipse.jst.javaee.webfragment.internal.metadata.WebfragmentPackage;

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
			J2EECorePlugin.logError(t);
		} 
	}
	
	protected static boolean initialized = false;
	protected static boolean plugin_initialized = false;
	private static boolean emfModelsInitialized = false;
	public static void init() {
		init(true);
	}

	public static void init(boolean shouldPreRegisterPackages) {
		if (!initialized) {
			initialized = true;
			setDefaultEncoderDecoder();
			DOMUtilities.setDefaultEntityResolver(J2EEXmlDtDEntityResolver.INSTANCE);
			org.eclipse.jem.internal.java.init.JavaInit.init(shouldPreRegisterPackages);
			if (shouldPreRegisterPackages){
				preRegisterPackages();
				preregisterJavaEEPackages();
			}
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
			public EFactory getEFactory() {
				return CommonFactory.eINSTANCE;
			}
		});
		//application
		ExtendedEcoreUtil.preRegisterPackage("application.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return ApplicationPackage.eINSTANCE;
			}
			public EFactory getEFactory() {
				return ApplicationFactory.eINSTANCE;
			}
		});
		//client
		ExtendedEcoreUtil.preRegisterPackage("client.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return ClientPackage.eINSTANCE;
			}
			public EFactory getEFactory() {
				return ClientFactory.eINSTANCE;
			}
		});
		//webapplication
		ExtendedEcoreUtil.preRegisterPackage("webapplication.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return WebapplicationPackage.eINSTANCE;
			}
			public EFactory getEFactory() {
				return WebapplicationFactory.eINSTANCE;
			}
		});
		//ejb
		ExtendedEcoreUtil.preRegisterPackage("ejb.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return EjbPackage.eINSTANCE;
			}
			public EFactory getEFactory() {
				return EjbFactory.eINSTANCE;
			}
		});
		//jca
		ExtendedEcoreUtil.preRegisterPackage("jca.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return JcaPackage.eINSTANCE;
			}
			public EFactory getEFactory() {
				return JcaFactory.eINSTANCE;
			}
		});
		//webservicesclient
		ExtendedEcoreUtil.preRegisterPackage("webservice_client.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return Webservice_clientPackage.eINSTANCE;
			}
			public EFactory getEFactory() {
				return Webservice_clientFactory.eINSTANCE;
			}
		});
		//webservicescommon
		ExtendedEcoreUtil.preRegisterPackage("wscommon.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return WscommonPackage.eINSTANCE;
			}
			public EFactory getEFactory() {
				return WscommonFactory.eINSTANCE;
			}
		});
		//webservicesdd
		ExtendedEcoreUtil.preRegisterPackage("wsdd.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return WsddPackage.eINSTANCE;
			}
			public EFactory getEFactory() {
				return WsddFactory.eINSTANCE;
			}
		});
		//jaxrpcmap
		ExtendedEcoreUtil.preRegisterPackage("jaxrpcmap.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return JaxrpcmapPackage.eINSTANCE;
			}
			public EFactory getEFactory() {
				return JaxrpcmapFactory.eINSTANCE;
			}
		});		
//		jsp
		ExtendedEcoreUtil.preRegisterPackage("jsp.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return JspPackage.eINSTANCE;
			}
			public EFactory getEFactory() {
				return JspFactory.eINSTANCE;
			}
		});
		//taglib
		ExtendedEcoreUtil.preRegisterPackage("taglib.xmi", new EPackage.Descriptor() { //$NON-NLS-1$
			public EPackage getEPackage() {
				return TaglibPackage.eINSTANCE;
			}
			public EFactory getEFactory() {
				return TaglibFactory.eINSTANCE;
			}
		});	
		
	}
	private static void preregisterJavaEEPackages() {
		ExtendedEcoreUtil.preRegisterPackage(JavaeePackage.eNS_URI, new EPackage.Descriptor() {
			public EPackage getEPackage() {
				return JavaeePackage.eINSTANCE;
			}

			public EFactory getEFactory() {
				return JavaeeFactory.eINSTANCE;
			}
		});
		ExtendedEcoreUtil.preRegisterPackage(EjbPackage.eNS_URI, new EPackage.Descriptor() {
			public EPackage getEPackage() {
				return EjbPackage.eINSTANCE;
			}

			public EFactory getEFactory() {
				return EjbFactory.eINSTANCE;
			}
		});
		ExtendedEcoreUtil.preRegisterPackage(WebPackage.eNS_URI, new EPackage.Descriptor() {
			public EPackage getEPackage() {
				return WebPackage.eINSTANCE;
			}

			public EFactory getEFactory() {
				return WebFactory.eINSTANCE;
			}
		});
		ExtendedEcoreUtil.preRegisterPackage(ApplicationPackage.eNS_URI, new EPackage.Descriptor() {
			public EPackage getEPackage() {
				return ApplicationPackage.eINSTANCE;
			}

			public EFactory getEFactory() {
				return ApplicationFactory.eINSTANCE;
			}
		});
		ExtendedEcoreUtil.preRegisterPackage(ApplicationclientPackage.eNS_URI, new EPackage.Descriptor() {
			public EPackage getEPackage() {
				return ApplicationclientPackage.eINSTANCE;
			}

			public EFactory getEFactory() {
				return ApplicationclientFactory.eINSTANCE;
			}
		});

	}

	public static void initResourceFactories() {
		//Make protected	
		Revisit.revisit();

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

	public static void initEMFModels()
	{
		if (!emfModelsInitialized)
		{
			emfModelsInitialized = true;
			// put the following initialization on its own thread.
			Thread j2eeEmfInitThread = new Thread(new Runnable(){
				public void run()
				{
					// Obtain or create and register interdependencies
					// Create package meta-data objects
					// Initialize created meta-data
//					Webservice_clientPackageImpl theWebservice_clientPackage = (Webservice_clientPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI) instanceof Webservice_clientPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI) : Webservice_clientPackage.eINSTANCE);
//					theWebservice_clientPackage.createPackageContents();
//					theWebservice_clientPackage.initializePackageContents();
					
					CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) instanceof CommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) : CommonPackage.eINSTANCE);
					theCommonPackage.createPackageContents();
					theCommonPackage.initializePackageContents();
					
					JcaPackageImpl theJcaPackage = (JcaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI) instanceof JcaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI) : JcaPackage.eINSTANCE);
					theJcaPackage.createPackageContents();
					theJcaPackage.initializePackageContents();
					
					JspPackageImpl theJspPackage = (JspPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI) instanceof JspPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI) : JspPackage.eINSTANCE);
					theJspPackage.createPackageContents();
					theJspPackage.initializePackageContents();
					
					ClientPackageImpl theClientPackage = (ClientPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI) instanceof ClientPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI) : ClientPackage.eINSTANCE);
					theClientPackage.createPackageContents();
					theClientPackage.initializePackageContents();
					
					ApplicationPackageImpl theApplicationPackage = (ApplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) instanceof ApplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) : ApplicationPackage.eINSTANCE);
					theApplicationPackage.createPackageContents();
					theApplicationPackage.initializePackageContents();
					
					EjbPackageImpl theEjbPackage = (EjbPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI) instanceof EjbPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI) : EjbPackage.eINSTANCE);
					theEjbPackage.createPackageContents();
					theEjbPackage.initializePackageContents();
					
					WscommonPackageImpl theWscommonPackage = (WscommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WscommonPackage.eNS_URI) instanceof WscommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WscommonPackage.eNS_URI) : WscommonPackage.eINSTANCE);
					theWscommonPackage.createPackageContents();
					theWscommonPackage.initializePackageContents();
					
					WsddPackageImpl theWsddPackage = (WsddPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WsddPackage.eNS_URI) instanceof WsddPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WsddPackage.eNS_URI) : WsddPackage.eINSTANCE);
					theWsddPackage.createPackageContents();
					theWsddPackage.initializePackageContents();
					
					WebapplicationPackageImpl theWebapplicationPackage = (WebapplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI) instanceof WebapplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI) : WebapplicationPackage.eINSTANCE);
					theWebapplicationPackage.createPackageContents();
					theWebapplicationPackage.initializePackageContents();

					TaglibPackageImpl theTaglibPackage = (TaglibPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TaglibPackage.eNS_URI) instanceof TaglibPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TaglibPackage.eNS_URI) : TaglibPackage.eINSTANCE);
					theTaglibPackage.createPackageContents();
					theTaglibPackage.initializePackageContents();
				}
			});
			j2eeEmfInitThread.start();

			
			// put the following initialization on its own thread.
			Thread javaeeEmfInitThread = new Thread(new Runnable(){
				public void run()
				{
					// Obtain or create and register interdependencies
					// Create package meta-data objects
					// Initialize created meta-data
					
					JavaeePackageImpl theJavaeePackage = (JavaeePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JavaeePackage.eNS_URI) instanceof JavaeePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(JavaeePackage.eNS_URI) : JavaeePackage.eINSTANCE);
					theJavaeePackage.createPackageContents();
					theJavaeePackage.initializePackageContents();
					
					org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl theJcaPackage = (org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage.eNS_URI) instanceof org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage.eNS_URI) : org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage.eINSTANCE);
					theJcaPackage.createPackageContents();
					theJcaPackage.initializePackageContents();
					
					org.eclipse.jst.javaee.jsp.internal.impl.JspPackageImpl theJspPackage = (org.eclipse.jst.javaee.jsp.internal.impl.JspPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage.eNS_URI) instanceof org.eclipse.jst.javaee.jsp.internal.impl.JspPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage.eNS_URI) : org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage.eINSTANCE);
					theJspPackage.createPackageContents();
					theJspPackage.initializePackageContents();
					
					ApplicationclientPackageImpl theApplicationclientPackage = (ApplicationclientPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ApplicationclientPackage.eNS_URI) instanceof ApplicationclientPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ApplicationclientPackage.eNS_URI) : ApplicationclientPackage.eINSTANCE);
					theApplicationclientPackage.createPackageContents();
					theApplicationclientPackage.initializePackageContents();
					
					org.eclipse.jst.javaee.application.internal.impl.ApplicationPackageImpl theApplicationPackage = (org.eclipse.jst.javaee.application.internal.impl.ApplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage.eNS_URI) instanceof org.eclipse.jst.javaee.application.internal.impl.ApplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage.eNS_URI) : org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage.eINSTANCE);
					theApplicationPackage.createPackageContents();
					theApplicationPackage.initializePackageContents();
					
					org.eclipse.jst.javaee.ejb.internal.impl.EjbPackageImpl theEjbPackage = (org.eclipse.jst.javaee.ejb.internal.impl.EjbPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage.eNS_URI) instanceof org.eclipse.jst.javaee.ejb.internal.impl.EjbPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage.eNS_URI) : org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage.eINSTANCE);
					theEjbPackage.createPackageContents();
					theEjbPackage.initializePackageContents();

					WebPackageImpl theWebPackage = (WebPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WebPackage.eNS_URI) instanceof WebPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WebPackage.eNS_URI) : WebPackage.eINSTANCE);
					theWebPackage.createPackageContents();
					theWebPackage.initializePackageContents();
					
					WebappPackageImpl theWebappPackage = (WebappPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WebappPackage.eNS_URI) instanceof WebappPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WebappPackage.eNS_URI) : WebappPackage.eINSTANCE);
					theWebappPackage.createPackageContents();
					theWebappPackage.initializePackageContents();
					
					WebfragmentPackageImpl theWebfragmentPackage = (WebfragmentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WebfragmentPackage.eNS_URI) instanceof WebfragmentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WebfragmentPackage.eNS_URI) : WebfragmentPackage.eINSTANCE);
					theWebfragmentPackage.createPackageContents();
					theWebfragmentPackage.initializePackageContents();
				}
			});
			javaeeEmfInitThread.start();
		}
		
	}

	private static ILock lock;
	private static final long delay = 30;

    private static ILock getLock() {
        if (lock == null)
            lock = Job.getJobManager().newLock();
        return lock;
    }

    public static void releaseInitializePackageContentsLock() {
        getLock().release();
    }
    public static boolean aquireInitializePackageContentsLock() throws InterruptedException{
    	return getLock().acquire(delay);
    }
}
