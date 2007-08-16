/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.provider;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;


/**
 * @author Sachin Patel
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class GroupedEntityItemProvider extends GroupedEJBItemProvider {

	public static final String ENTITY = J2EEUIMessages.getResourceString("Entity_UI_"); //$NON-NLS-1$

	public GroupedEntityItemProvider(AdapterFactory adapterFactory, String text, Object image, Object parent, Collection children) {
		super(adapterFactory, text, image, parent, children);
	}

	public String getText(Object object) {
		return ENTITY;
	}
}