package org.eclipse.jst.javaee.web;

import org.eclipse.emf.ecore.EObject;

public interface IWebResource {

	/**
	 * Return the first element in the EList.
	 */
	public abstract EObject getRootObject();

	/**
	 * Return the war
	 */
	public abstract WebApp getWebApp();


}