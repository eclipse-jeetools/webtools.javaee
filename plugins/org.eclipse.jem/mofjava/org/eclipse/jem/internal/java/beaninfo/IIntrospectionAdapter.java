/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.java.beaninfo;
/*
 *  $RCSfile: IIntrospectionAdapter.java,v $
 *  $Revision: 1.4 $  $Date: 2005/02/04 23:11:33 $ 
 */
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;


/**
 * The interface for the Beaninfo adapter. The actual implementation
 * is in a separate project so that beaninfo code will not be loaded
 * unless it needs to be.
 */

public interface IIntrospectionAdapter extends org.eclipse.emf.common.notify.Adapter {
	public static final Class ADAPTER_KEY = IIntrospectionAdapter.class;
	public void introspectIfNecessary();
	public boolean isStale();
	public EList getEStructuralFeatures();
	public EList getEOperations();
	public BasicEList getEAllOperations();
	public EList getEvents();
	public EList getAllEvents();
	public EList getAllProperties();	
}


