package org.eclipse.jst.validation.test.internal.registry;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IPluginRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.wst.validation.internal.ValidatorMetaData;

import com.ibm.wtp.common.logger.proxy.Logger;

public class TestcaseMetaData {
	private String _pluginId = null; // The plugin id that has contributed this test case.
	private ValidatorMetaData _vmd = null;
	private MessageMetaData[] _messages = null;
	private String _project = null; // the name of the project that this test case tests
	private String[] _resourceNames = null; // the resources listed in the MessageMetaData of this test case.
	private String _inputFileName = null;
	private String _name = null; // the name of the test case
	
	public TestcaseMetaData(String pluginName, String project, ValidatorMetaData vmd, String inputFileName) {
		_pluginId = pluginName;
		_project = project;
		_vmd = vmd;
		_inputFileName = inputFileName;
	}
	
	public String getDeclaringPluginId() {
		return _pluginId;
	}
	
	public String getProject() {
		return _project;
	}
	
	public IProject findProject() {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(_project);
	}
	
	public String getInputFileName() {
		return _inputFileName;
	}
	
	public int getNumMessages() {
		return getMessages().size();
	}
	
	// The messages need to be stored in a list instead of an array because
	// the list is sorted before searching & displaying.
	public List getMessages() {
		List copy = new ArrayList();
		for(int i=0; i<_messages.length; i++) {
			copy.add(_messages[i]);
		}
		
		return copy;
	}
	
	// Return the resources in this project for which there is a message in this test case.
	// No resource instance will be in the resource more than once.
	public IResource[] getResources(IProject project) {
		Set temp = new HashSet(); // use a set in case there is more than one message registered against a resource (don't want duplicates in the list).
		String[] resourceNames = getResourceNames();
		for(int i=0; i<resourceNames.length; i++) {
			String resourceName = resourceNames[i];
			IResource resource = project.findMember(resourceName);
			if(resource != null) {
				// resource exists
				temp.add(resource);
			}
		}
		
		IResource[] result = new IResource[temp.size()];
		temp.toArray(result);
		return result;
	}
	
	public String[] getResourceNames() {
		if(_resourceNames == null) {
			_resourceNames = new String[_messages.length];
			for(int i=0; i<_messages.length; i++) {
				_resourceNames[i] = _messages[i].getResource();
			}
		}
		return _resourceNames;
	}
	
	/**
	 * When an empty TMD is used to test an operation, and the operation needs the IResource[]
	 * affected by the TMD to know whether or not the operation passes (i.e., one of the ValidatorSubsetOperation
	 * constructors takes an IResource[], and the IResource[] must not be empty or null), then
	 * this method is used to set the "resources" affected by the test case.
	 */
	public void setResourceNames(String[] resourceNames) {
		_resourceNames = resourceNames;
	}
	
	public void setMessages(MessageMetaData[] messages) {
		// If messages are null, that means that the test case expects no validation errors.
		_messages = ((messages == null) ? new MessageMetaData[0] : messages);
	}
	
	public ValidatorMetaData getValidatorMetaData() {
		return _vmd;
	}
	
	public String getValidatorClass() {
		return getValidatorMetaData().getValidatorUniqueName();
	}

	public String getName() {
		if(_name == null) {
			_name = _vmd.getValidatorDisplayName() + "::" + getProject(); //$NON-NLS-1$
		}
		return _name;
	}	
	
	public String getInputDir() {
		// If the directory where the testcase input isn't specified, 
		// assume that the input is in a subdirectory, named "testInput",
		// of the testcase's plugin.
		IPluginRegistry registry = Platform.getPluginRegistry();
		IPluginDescriptor descriptor = registry.getPluginDescriptor(getDeclaringPluginId());
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
				if(logger.getLevel() == Level.SEVERE) {
					logger.write(Level.SEVERE, exc);
				}
			}
		}
		
		// Should never get here, but if we do, assume that the input 
		// directory is the current directory.
		return System.getProperty("user.dir"); //$NON-NLS-1$
	}
}

