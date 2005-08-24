/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.java.adapters;
/*
 *  $RCSfile: JavaXMIFactoryImpl.java,v $
 *  $Revision: 1.7 $  $Date: 2005/08/24 20:20:25 $ 
 */
import java.io.IOException;
import java.util.*;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jem.java.JavaPackage;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jem.java.impl.JavaFactoryImpl;

/**
 * Factory to create the Resource for the Java protocol.
 * 
 * It allows extensions to the reflection key to be registered with it.
 * 
 * Creation date: (10/4/2000 8:22:23 AM)
 * @author: Administrator
 */
public class JavaXMIFactoryImpl extends XMIResourceFactoryImpl {
	private static final String SCHEMA_SEPERATOR = ":/"; //$NON-NLS-1$
	public static final String SCHEME = "java"; //$NON-NLS-1$

	protected List extensions = new ArrayList();

	public static final JavaXMIFactoryImpl INSTANCE = new JavaXMIFactoryImpl();

	/**
	 * JavaXMIFactoryImpl constructor comment.
	 */
	public JavaXMIFactoryImpl() {
		super();
	}

	/**
	 * Register an extension for java reflection key processing.
	 */
	public void registerReflectionKeyExtension(IJavaReflectionKeyExtension extension) {
		extensions.add(extension);
	}

	/**
	 * Deregister an extension for java reflection key processing.
	 */
	public void deregisterReflectionKeyExtension(IJavaReflectionKeyExtension extension) {
		extensions.remove(extension);
	}

	protected void createJavaPackage(String packageName, String xmiuri, Resource resource) {
		// changed to allow a zero-length package name 
		// for the special case of "java:/#int", etc.
		JavaRefFactory jfac = JavaRefFactory.eINSTANCE;
		JavaPackage pack = jfac.createJavaPackage();
		// Need to create and add special JavaFactory for instantiation to work.
		JavaFactoryImpl fact = new JavaFactoryImpl();
		pack.setEFactoryInstance(fact);
		if (packageName.length() > 0) {
			// Standard Java package
			pack.setNsPrefix(packageName);
			pack.setName(packageName);
		} else {
			// Primitive package
			pack.setNsPrefix(JavaPackage.PRIMITIVE_PACKAGE_NAME);
			pack.setName(JavaPackage.PRIMITIVE_PACKAGE_NAME);
		}

		pack.setNsURI(xmiuri);
		resource.getContents().add(pack);
		((XMIResource) pack.eResource()).setID(pack, JavaPackage.PACKAGE_ID);
	}
	public static class JavaXMIResource extends XMIResourceImpl {
		protected JavaReflectionKey reflectionKey;

		public JavaXMIResource(URI uri) {
			super(uri);
		}

		public void setReflectionKey(JavaReflectionKey key) {
			reflectionKey = key;
		}

		public void load(Map options) throws IOException {
			try {
				super.load(options);
			} catch (Exception exception) {
			}
		}

		public EObject getEObject(String uriFragment) {
			EObject result = super.getEObject(uriFragment);
			if (result == null && reflectionKey != null) {
				result = (EObject) reflectionKey.get(uriFragment);
			}
			return result;
		}
		
		/**
		 * Used by JavaReflectionKey to look directly into ID table to bypass an infinite loop. It will 
		 * call here because it may not of first been found, but then added, so now is found.
		 * 
		 * @param uriFragment
		 * @return
		 * 
		 * @since 1.1.0
		 */
		EObject primGetEObject(String uriFragment) {
			return super.getEObject(uriFragment);
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl#useIDAttributes()
		 */
		protected boolean useIDAttributes() {
			return false;
		}

	}

	public Resource createResource(URI uri) {
		JavaXMIResource resource = new JavaXMIResource(uri);
		resource.setReflectionKey(new JavaReflectionKey(extensions, resource));

		String uriString = uri.toString();
		createJavaPackage(stripScheme(uriString), uriString, resource);

		return resource;
	}

	public static void register() {
		Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap().put(SCHEME, INSTANCE);
	}

	/**
	 * Strip the trailing identifier, if any, from the uri.  An identifier
	 * begins with # or |.
	 */
	private String stripScheme(String uri) {
		int index = uri.indexOf(SCHEMA_SEPERATOR);
		if (index != -1)
			return uri.substring(index + SCHEMA_SEPERATOR.length(), uri.length());
		else
			return uri;
	}

}
