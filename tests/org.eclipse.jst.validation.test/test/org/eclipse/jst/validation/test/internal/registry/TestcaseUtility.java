package org.eclipse.jst.validation.test.internal.registry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IPluginRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.validation.test.BVTValidationPlugin;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * Utility class that contains convenience methods for test cases.
 */
public final class TestcaseUtility {
	/**
	 * Load the project that this test case is supposed to run on.
	 */
	public static IProject findProject(ITestcaseMetaData tmd) {
		if(tmd == null) {
			return null;
		}
		
		return ResourcesPlugin.getWorkspace().getRoot().getProject(tmd.getProjectName());
	}

	/**
	 * Return the directory where the input for this test case can be located.
	 */
	public static String getInputDir(ITestcaseMetaData tmd) {
		// If the directory where the testcase input isn't specified, 
		// assume that the input is in a subdirectory, named "testInput",
		// of the testcase's plugin.
		IPluginRegistry registry = Platform.getPluginRegistry();
		IPluginDescriptor descriptor = registry.getPluginDescriptor(tmd.getDeclaringPluginId());
		if(descriptor != null) {
			// Because Platform.asLocalURL throws an IOException if the URL resolves
			// to a directory, find the plugin.xml file and then strip off the file name 
			// to find the testInput directory.
			try {
				String pluginXmlPath = Platform.asLocalURL(new URL(descriptor.getInstallURL(), "plugin.xml")).getPath(); //$NON-NLS-1$
				File pluginXml = new File(pluginXmlPath);
				if(pluginXml.exists()) {
					File inputDir = new File(pluginXml.getParent(), "testInput"); //$NON-NLS-1$
					if (inputDir.exists() && inputDir.isDirectory()) {
						return inputDir.getPath();
					}
				}
			}
			catch(java.io.IOException exc) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if(logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, exc);
				}
			}
		}
		
		// Should never get here, but if we do, assume that the input 
		// directory is the current directory.
		return System.getProperty("user.dir"); //$NON-NLS-1$
	}

	/**
	 * logFileName must point to a fully-qualified file.
	 */
	public static void flush(String logFileName, String buffer, boolean append) {
		try {
			FileWriter writer = new FileWriter(logFileName, append);
			writer.write(buffer); // Write the entire report to the state log.
			writer.close();
		}
		catch(IOException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
		}
	}
}
