/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.common;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;

public final class J2EEPreferencePropertyTester extends PropertyTester

{
	/**
	 * Test to see if the @param value preference is set to true in @link J2EEPreferences 
	 */
	@Override
	public boolean test( final Object receiver, 
						final String property, 
						final Object[] args, 
						final Object value )
	{
		return Boolean.parseBoolean(J2EEPlugin.getDefault().getJ2EEPreferences().getString(value.toString()));
	}

}
