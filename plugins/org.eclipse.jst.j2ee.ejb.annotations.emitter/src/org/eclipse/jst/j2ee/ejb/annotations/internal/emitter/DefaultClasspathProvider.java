/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/


package org.eclipse.jst.j2ee.ejb.annotations.internal.emitter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author naci
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DefaultClasspathProvider implements IEmitterClasspathProvider {
	
	protected ArrayList list = null;
	public DefaultClasspathProvider() {
		super();
		list = new ArrayList();
	}

	
	public List getClasspathItems() {
		list.clear();
		list.add("org.eclipse.emf.ecore");
		list.add("org.eclipse.emf.ecore.xmi");
		list.add("org.eclipse.emf.common");
		list.add("org.eclipse.jst.j2ee.core");	
		list.add("org.eclipse.jst.j2ee.ejb.annotation.model");	
		list.add("org.eclipse.jst.j2ee.ejb.annotations.emitter");	
		return list;
	}
}
