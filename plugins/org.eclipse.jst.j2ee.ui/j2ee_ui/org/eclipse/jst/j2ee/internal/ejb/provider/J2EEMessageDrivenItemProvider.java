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


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jst.j2ee.ejb.MessageDriven;


/**
 * @author jsholl
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class J2EEMessageDrivenItemProvider extends MessageDrivenItemProvider {

	public J2EEMessageDrivenItemProvider(org.eclipse.emf.common.notify.AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public Collection getChildren(Object object) {
		List result = new ArrayList();
		result.addAll(super.getChildrenSuper(object));
		J2EEJavaClassProviderHelper.addChildren((MessageDriven) object, result);
		return result;
	}
}