package org.eclipse.jst.javaee.applicationclient;

import org.eclipse.emf.ecore.EObject;

public interface IApplicationClientResource {

	/**
	 * Return the first element in the EList.
	 */
	public abstract EObject getRootObject();

	/**
	 * Return the ear
	 */
	public abstract ApplicationClient getApplicationClient();

}