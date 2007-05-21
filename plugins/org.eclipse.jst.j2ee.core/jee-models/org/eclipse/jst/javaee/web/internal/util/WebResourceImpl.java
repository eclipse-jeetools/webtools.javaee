/**
 * <copyright>
 * </copyright>
 *
 * $Id: WebResourceImpl.java,v 1.2 2007/05/21 17:38:00 jsholl Exp $
 */
package org.eclipse.jst.javaee.web.internal.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.jst.javaee.core.JEEXMLLoadImpl;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor;
import org.eclipse.wst.common.internal.emf.resource.IRootObjectResource;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.web.internal.util.WebResourceFactoryImpl
 * @generated
 */
public class WebResourceImpl extends XMLResourceImpl implements IRootObjectResource{
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public WebResourceImpl(URI uri) {
		super(uri);
	}

	protected XMLLoad createXMLLoad() {
		 return new JEEXMLLoadImpl(createXMLHelper());
	}

	protected XMLHelper createXMLHelper() {
		
		return new WebXMLHelperImpl(this);
	}
	/**
	 * Return the first element in the EList.
	 */
	public EObject getRootObject() {
		if (contents == null || contents.isEmpty())
			return null;
		Object root = getContents().get(0);
		if(root == null){
			return null;
		}
		return (EObject)((WebAppDeploymentDescriptor)root).getWebApp();
	}
	/**
	 * Return the war
	 */
	public WebApp getWebApp() {
		return (WebApp)getRootObject();
	}

} //WebResourceImpl
