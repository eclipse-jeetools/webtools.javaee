package org.eclipse.jst.validation.test.setup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IPluginRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.validation.sample.parser.PropertyLine;
import org.eclipse.jst.validation.sample.workbenchimpl.PluginPropertyFile;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.jst.validation.test.ValidationTypeEnum;
import org.eclipse.jst.validation.test.internal.registry.MessageUtility;
import org.eclipse.jst.validation.test.internal.registry.ValidatorTestcase;
import org.eclipse.jst.validation.test.internal.util.BVTValidationUtility;
import org.eclipse.wst.validation.internal.ValidatorMetaData;
/**
 * Generate a FVT test case for every EJB Validator validation marker on each
 * selected project.
 */
public final class CheckForUntestedPropertiesOperation implements IWorkspaceRunnable {
	private ValidatorMetaData _vmd = null;
	private IBuffer _buffer = null;
	private String _resourceBundleName = null;
	public CheckForUntestedPropertiesOperation(IBuffer buffer, ValidatorMetaData vmd, String resourceBundleName) {
		setValidatorMetaData(vmd);
		setBuffer(buffer);
		setResourceBundleName(resourceBundleName);
	}
	public IBuffer getBuffer() {
		return _buffer;
	}
	void setBuffer(IBuffer b) {
		_buffer = b;
	}
	public ValidatorMetaData getValidatorMetaData() {
		return _vmd;
	}
	private String getPluginId(ValidatorMetaData vmd) {
		try {
			// TODO Remove this hack once a getter has been added to ValidatorMetaData for its pluginId.
			if (vmd != null) {
				ClassLoader cl = vmd.getValidator().getClass().getClassLoader();
				IPluginRegistry registry = Platform.getPluginRegistry();
				IPluginDescriptor[] descriptors = registry.getPluginDescriptors();
				for (int i = 0; i < descriptors.length; i++) {
					IPluginDescriptor desc = descriptors[i];
					if (desc.getPluginClassLoader().equals(cl)) {
						return desc.getUniqueIdentifier();
					}
				}
			}
		} catch (InstantiationException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if (logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
		}
		return null;
	}
	void setValidatorMetaData(ValidatorMetaData vmd) {
		_vmd = vmd;
	}
	public String getResourceBundleName() {
		return _resourceBundleName;
	}
	void setResourceBundleName(String name) {
		_resourceBundleName = name;
	}
	/**
	 * @see org.eclipse.core.resources.IWorkspaceRunnable#run(IProgressMonitor)
	 */
	public void run(IProgressMonitor monitor) throws CoreException {
		List pLines = null;
		List untestedLines = new ArrayList();
		List testedLines = new ArrayList();
		Comparator comparator = MessageUtility.getMessagePrefixComparator();
		// Assume that the BVT tests were run on the projects before this menu
		// action was clicked.
		// Load and parse the .properties file to know what message ids to look
		// for.
		String propFileName = getResourceBundleName();
		ValidatorMetaData vmd = getValidatorMetaData();
		monitor.subTask("Attempting to load file: " + propFileName); //$NON-NLS-1$
		String pluginId = getPluginId(vmd);
		if (pluginId == null) {
			monitor.subTask("Cannot load plugin id for validator " + vmd.getValidatorDisplayName()); //$NON-NLS-1$
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if (logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, "Cannot load plugin id for validator " + vmd.getValidatorDisplayName()); //$NON-NLS-1$
			}
			return;
		}
		PluginPropertyFile pFile = new PluginPropertyFile(pluginId, propFileName);
		monitor.worked(1);
		monitor.subTask("Parsing."); //$NON-NLS-1$
		pLines = pFile.getPropertyLines();
		Collections.sort(pLines, comparator); // false = no verbose output
		monitor.worked(1);
		monitor.subTask("Loading test messages."); //$NON-NLS-1$
		ValidatorTestcase[] tmds = BVTValidationUtility.getValidatorTests(monitor, vmd);
		for (int k = 0; k < tmds.length; k++) {
			ValidatorTestcase tmd = tmds[k];
			List mssg = tmd.getMessages(ValidationTypeEnum.RUN_VALIDATION);
			testedLines.addAll(mssg);
		}
		Collections.sort(testedLines, comparator);
		monitor.subTask("Comparing."); //$NON-NLS-1$
		Iterator iterator = pLines.iterator();
		while (iterator.hasNext()) {
			if (monitor.isCanceled()) {
				return;
			}
			PropertyLine line = (PropertyLine)iterator.next();
			int index = Collections.binarySearch(testedLines, line, comparator);
			if (index < 0) {
				// not found.
				untestedLines.add(line);
			}
		}
		monitor.worked(1);
		// now see if the list of untested lines has entries.
		Collections.sort(untestedLines, comparator);
		Iterator untestediterator = untestedLines.iterator();
		if (untestediterator.hasNext()) {
			getBuffer().write("List of messages which are not tested (" + untestedLines.size() + " of " + pLines.size() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} else {
			getBuffer().write("All messages are tested."); //$NON-NLS-1$
		}
		while (untestediterator.hasNext()) {
			if (monitor.isCanceled()) {
				return;
			}
			PropertyLine line = (PropertyLine)untestediterator.next();
			getBuffer().write(line.toString());
		}
		untestedLines.clear(); // clear the list for the next project
		testedLines.clear();
		monitor.worked(1);
	}
}
