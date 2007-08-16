/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jan 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

/**
 * @author jialin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IClasspathTableOwner {
	Button primCreatePushButton(String label, Composite buttonColumn);
	Button primCreateRadioButton(String label, Composite parent);
	CheckboxTableViewer createAvailableJARsViewer(Composite parent);
	Composite createButtonColumnComposite(Composite parent);
	//Button createHideEJBClientJARsButton(Composite parent); This method is never used hence commeting it out - vkb
	Group createGroup(Composite parent);
}
