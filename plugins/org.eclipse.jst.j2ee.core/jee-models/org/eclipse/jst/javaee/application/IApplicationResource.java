package org.eclipse.jst.javaee.application;

import org.eclipse.emf.ecore.EObject;

public interface IApplicationResource {

	/**
	 * Return the first element in the EList.
	 */
	public abstract EObject getRootObject();

	/**
	 * Return the ear
	 */
	public abstract Application getApplication();

}