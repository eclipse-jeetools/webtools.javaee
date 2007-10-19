package org.eclipse.jst.j2ee.web.validation;

/**
 * Utility method for URL patterns.
 * 
 * <p>
 * Could be used by components dealing with URL patterns like: Servlet and
 * Filter wizards, web.xml validators, etc.
 * </p>
 * 
 * @author kraev
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
		if (urlPattern.startsWith("/")) {
			if (urlPattern.indexOf("*.") == -1) {
				return true;
			} else {
				return false;
			}
		}

		// Extension Mappings must not contain '/' characters
		if (urlPattern.startsWith("*.")) {
			if (urlPattern.indexOf('/') == -1) {
				return true;
			} else {
				return false;
			}
		}

		// The URL Pattern is neither a Path Mapping, nor Extension Mapping
		// Therefore, it is invalid
		return false;
	}

}
