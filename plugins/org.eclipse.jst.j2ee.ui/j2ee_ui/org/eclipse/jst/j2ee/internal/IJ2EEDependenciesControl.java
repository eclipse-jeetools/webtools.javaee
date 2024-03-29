/*******************************************************************************
 * Copyright (c) 2005, 2019 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * rfrost@bea.com - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal;

import org.eclipse.swt.widgets.Composite;

/**
 * Interface for classes that implement a portion the "J2EE Module Dependencies"
 * property page logic.
 * @deprecated wtp 3.2
 */
public interface IJ2EEDependenciesControl extends ICommonManifestUIConstants {
	
	/**
	 * Creates the Composite associated with this control.
	 * @param parent Parent Composite.
	 * @return Composite for the control.
	 */
	Composite createContents(Composite parent);
	
	/**
	 * Called when the property page's <code>performOk()</code> method is called.
	 * @return
	 */
	boolean performOk();
	
	/**
	 * Called when the property page's <code>performDefaults()</code> method is called.
	 * @return
	 */
	void performDefaults();
	
	/**
	 * Called when the property page's <code>performCancel()</code> method is called.
	 * @return
	 */
	boolean performCancel();
	
	/**
	 * Called when the property page's <code>setVisible()</code> method is called.
	 * @return
	 */
	void setVisible(boolean visible);
	
	/**
	 * Called when the property page's <code>dispose()</code> method is called.
	 * @return
	 */
	void dispose();
}
