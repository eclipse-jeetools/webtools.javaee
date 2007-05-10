/**
 * <copyright>
 * </copyright>
 *
 * $Id: ApplicationclientResourceImpl.java,v 1.3 2007/05/10 22:14:48 jsholl Exp $
 */
package org.eclipse.jst.javaee.applicationclient.internal.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.jst.javaee.applicationclient.ApplicationClient;
import org.eclipse.jst.javaee.applicationclient.ApplicationClientDeploymentDescriptor;
import org.eclipse.jst.javaee.core.JEEXMLLoadImpl;
import org.eclipse.wst.common.internal.emf.resource.IRootObjectResource;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.applicationclient.internal.util.ApplicationclientResourceFactoryImpl
 * @generated
 */
public class ApplicationclientResourceImpl extends XMLResourceImpl implements IRootObjectResource{
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public ApplicationclientResourceImpl(URI uri) {
		super(uri);
	}
	
	protected XMLLoad createXMLLoad() {
		 return new JEEXMLLoadImpl(createXMLHelper());
	}

	
	protected XMLHelper createXMLHelper() {
		
		return new AppClientXMLHelperImpl(this);
	}
	
	/**
	 * Return the first element in the EList.
	 */
	public EObject getRootObject() {
		if (contents == null || contents.isEmpty())
			return null;
		return (EObject) getContents().get(0);
	}
	/**
	 * Return the ear
	 */
	public ApplicationClient getApplicationClient() {
		if (getRootObject() != null)
			return ((ApplicationClientDeploymentDescriptor)getRootObject()).getApplicationClient();
		return null;
		
	}

} //ApplicationclientResourceImpl
