/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.common;

import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResource;


public interface XMLResource extends TranslatorResource, J2EEVersionConstants {
	public static final int APP_CLIENT_TYPE = 1;
	public static final int APPLICATION_TYPE = 2;
	public static final int EJB_TYPE = 3;
	public static final int WEB_APP_TYPE = 4;
	public static final int RAR_TYPE = 5;
	public static final int WEB_SERVICES_CLIENT_TYPE = 6;

	public static final String DELETED_ERROR_MSG = "This resource has been deleted and can no longer be used."; /**
	 * Is this a resource loaded as an alternate deployment descriptor?
	 */
	boolean isAlt();
	public int getJ2EEVersionID();
	public int getModuleVersionID();
	public void setJ2EEVersionID(int id);
	public void setModuleVersionID(int id);
	/**
	 * Used only if this resource is an alt; return the application which declares it
	 */
	Application getApplication();

	void setApplication(Application newApplication);
	/**
	 * @deprecated use {@link #getVersionID()}
	 * @see J2EEVersionConstants
	 */
	boolean isJ2EE1_3();

	/**
	 * Returns the type of this J2EE XMLResource.
	 * You can use this method instead of using instanceof.
	 * @see XMLResource#APP_CLIENT_TYPE
	 * @see XMLResource#APPLICATION_TYPE
	 * @see XMLResource#EJB_TYPE
	 * @see XMLResource#WEB_APP_TYPE
	 * @see XMLResource#RAR_TYPE
	 */
	int getType();
	 /**
	 * Is this a resource loaded as an alternate deployment descriptor?
	 */
	void setIsAlt(boolean isAlternateDD);
	/**
	 * @deprecated use {@link TranslatorResource#setVersionID(int)}, 
	 * {@link TranslatorResource#setDoctypeValues(String, String)}
	 * Sets the system id of the XML document.
	 * @see J2EEVersionConstants
	 */
	void setSystemId(String id);
	/**
	 * @deprecated use {@link TranslatorResource#setVersionID(int)},
	 * {@link TranslatorResource#setDoctypeValues(String, String)}
	 * Sets the public id of the XML document.
	 * @see J2EEVersionConstants
	 */
	void setPublicId(String id);

	//The following methods are to support ReferencedResource without having an actual
	//This may be just a subset of what is available in ReferencedResource
	void saveIfNecessary() throws Exception;
	void releaseFromWrite();
	void releaseFromRead();
	void preDelete();
	boolean isSharedForWrite();
	boolean isShared();
	boolean isReadOnly();
	boolean isNew();
	void accessForWrite();
	void accessForRead(); 

}
