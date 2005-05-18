package org.eclipse.jem.internal.beaninfo.core;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class BeaninfoCoreMessages {

	private static final String BUNDLE_NAME = "org.eclipse.jem.internal.beaninfo.core.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private BeaninfoCoreMessages() {
	}

	public static String getString(String key) {
		// TODO Auto-generated method stub
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
