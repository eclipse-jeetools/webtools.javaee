package org.eclipse.jst.common.internal.annotations.core;

import org.eclipse.emf.ecore.EObject;

/**
 * This interface is used by clients to allow their own annotation provider to detail information
 * on whether or not a given EObject is annotated for one of their tag sets.
 * 
 * @author jlanuti
 */
public interface IAnnotationsProvider {
	
	/**
	 * Return whether or not the passed EObject is annotated by your annotation provider
	 * 
	 * @param eObject
	 * @return boolean value of isAnnotated
	 */
	public boolean isAnnotated(EObject eObject);
}
