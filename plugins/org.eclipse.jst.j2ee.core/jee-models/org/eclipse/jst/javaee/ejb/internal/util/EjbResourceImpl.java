/**
 * <copyright>
 * </copyright>
 *
 * $Id: EjbResourceImpl.java,v 1.1 2007/05/16 06:42:38 cbridgha Exp $
 */
package org.eclipse.jst.javaee.ejb.internal.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.jst.javaee.core.JEEXMLLoadImpl;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.EJBJarDeploymentDescriptor;
import org.eclipse.wst.common.internal.emf.resource.IRootObjectResource;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.ejb.internal.util.EjbResourceFactoryImpl
 * @generated
 */
public class EjbResourceImpl extends XMLResourceImpl implements IRootObjectResource {
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public EjbResourceImpl(URI uri) {
		super(uri);
	}
	
	protected XMLLoad createXMLLoad() {
		 return new JEEXMLLoadImpl(createXMLHelper());
	}

	
	protected XMLHelper createXMLHelper() {
		
		return new EjbXMLHelperImpl(this);
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
	 * Return the jar
	 */
	public EJBJar getEjbJar() {
		if (getRootObject() != null)
			return ((EJBJarDeploymentDescriptor)getRootObject()).getEjbJar();
		return null;
		
	}

} //EjbResourceImpl
