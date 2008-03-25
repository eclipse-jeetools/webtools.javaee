package org.eclipse.jst.javaee.ejb;

import org.eclipse.emf.ecore.EObject;

public interface IEJBResource {

	/**
	 * Return the first element in the EList.
	 */
	public abstract EObject getRootObject();

	/**
	 * Return the jar
	 */
	public abstract EJBJar getEjbJar();

}