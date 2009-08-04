package org.eclipse.jst.j2ee.internal;

/**
 * Used to introduce EMF model concepts on both Java EE model implementations - separated from clean Module interface
 *
 */
public interface ICommonEMFModule {

	/**
	 * Sets the string fragment used to identify this object - must be unique within the document
	 */
	public void setId(String frag);

	/**
	 * Gets the id used to identify this object
	 */
	public String getId();

}
