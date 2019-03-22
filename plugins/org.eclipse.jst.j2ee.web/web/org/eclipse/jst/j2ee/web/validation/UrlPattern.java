/*******************************************************************************
 * Copyright (c) 2007, 2009 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * SAP AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.web.validation;

/**
 * Utility method for URL patterns.
 * 
 * <p>
 * Could be used by components dealing with URL patterns like: Servlet and
 * Filter wizards, web.xml validators, etc.
 * </p>
 * 
 * @author Kaloyan Raev
 */
public class UrlPattern {

	/**
	 * Validates an URL pattern.
	 * 
	 * @param urlPattern
	 *            the string representation of the URL pattern to validate
	 * 
	 * @return <code>true</code> if the given pattern is a valid one,
	 *         <code>false</code> - otherwise.
	 */
	public static boolean isValid(String urlPattern) {
		// URL Pattern must not be empty string
		if (urlPattern.length() == 0)
			return false;

		// URL Pattern must not contain Carriage Return characters
		if (urlPattern.indexOf('\r') != -1)
			return false;

		// URL Pattern must not contain New Line characters
		if (urlPattern.indexOf('\n') != -1)
			return false;

		// Path Mappings must not contain "*." character sequences
		if (urlPattern.startsWith("/")) { //$NON-NLS-1$
			if (urlPattern.indexOf("*.") == -1) { //$NON-NLS-1$
				return true;
			}
			return false;
		}

		// Extension Mappings must not contain '/' characters
		if (urlPattern.startsWith("*.")) { //$NON-NLS-1$
			if (urlPattern.indexOf('/') == -1) {
				return true;
			}
			return false;
		}

		// The URL Pattern is neither a Path Mapping, nor Extension Mapping
		// Therefore, it is invalid
		return false;
	}

}
