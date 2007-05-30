package org.eclipse.jst.common.frameworks;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.wst.project.facet.IProductConstants;
import org.eclipse.wst.project.facet.ProductManager;



public class CommonFrameworksPreferenceInitializer extends
		AbstractPreferenceInitializer {

	public CommonFrameworksPreferenceInitializer() {
		// TODO Auto-generated constructor stub
	}

	public void initializeDefaultPreferences() {
		IEclipsePreferences node = new DefaultScope().getNode(CommonFrameworksPlugin.getDefault().getBundle().getSymbolicName());
		
		// migration preferences
		node.put(CommonFrameworksPlugin.DEFAULT_SOURCE_FOLDER, ProductManager.getProperty(IProductConstants.DEFAULT_SOURCE_FOLDER));
		node.put(CommonFrameworksPlugin.OUTPUT_FOLDER, ProductManager.getProperty(IProductConstants.OUTPUT_FOLDER));
	}

}
