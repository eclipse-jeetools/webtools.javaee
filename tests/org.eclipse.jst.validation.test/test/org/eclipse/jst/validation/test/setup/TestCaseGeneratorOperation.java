package org.eclipse.jst.validation.test.setup;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.jst.validation.test.internal.registry.MessageUtility;
import org.eclipse.wst.validation.core.IMessage;
import org.eclipse.wst.validation.internal.TaskListUtility;
import org.eclipse.wst.validation.internal.ValidatorMetaData;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * Generate an FVT test case, for a selected IProject or IProjects, to
 * be pasted into plugin.xml.
 */
public final class TestCaseGeneratorOperation implements IWorkspaceRunnable {
	private final static String _TESTCASE_TEMPLATE_ = "\t\t\t<test \n\t\t\t\tproject=\"{0}\"\n\t\t\t\tinput=\"\"\n\t\t\t\tvalidator=\"{1}\">\n{2}\t\t\t</test>\n"; //$NON-NLS-1$
	private final static String _TESTCASE_MESSAGE_TEMPLATE_ = "\t\t\t\t<message prefix=\"{0}\" resource=\"{1}\" location=\"{2}\" text=\"{3}\"/>\n"; //$NON-NLS-1$
	private Map _vmdBuffer = null; // map where the ValidatorMetaData is the key and the value is a StringBuffer
	private IProject[] _projects = null;
	private IBuffer _buffer = null;
	
	public TestCaseGeneratorOperation(IProject[] projects, IBuffer buffer) {
		setProjects(projects);
		setBuffer(buffer);
		_vmdBuffer = new HashMap();
	}
	
	/**
	 * Get the buffer where the test case generated output will be sent.
	 */
	public IBuffer getBuffer() {
		return _buffer;
	}
	
	public void setBuffer(IBuffer b) {
		_buffer = b;
	}
	
	public IProject[] getProjects() {
		return _projects;
	}
	
	public void setProjects(IProject[] projects) {
		_projects = projects;
	}

	/**
	 * @see org.eclipse.core.resources.IWorkspaceRunnable#run(IProgressMonitor)
	 */
	public void run(IProgressMonitor arg0) throws CoreException {
		try {
			generate(getProjects());
		}
		catch(Throwable exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
			getBuffer().write("A Throwable was caught; could not generate the test case. Check the LoggingUtil.log file for details."); //$NON-NLS-1$
		}
		finally {
			_vmdBuffer.clear();
		}
	}
	
	protected void generate(IProject[] projects) {
		// First, load all of the validation markers from the selected projects, 
		// and group them by the validator.
		for(int i=0; i<projects.length; i++) {
			IProject project = projects[i];
			IMarker[] markers = TaskListUtility.getValidationTasks(project, IMessage.ALL_MESSAGES);
			if((markers == null) || (markers.length == 0)){
				getBuffer().write("Cannot generate a test case for project " + project.getName() + " until \"Run Validation\" has been run. Enable only the validator whose test case is to be generated, right-click, and \"Run Validation\". Once the validation messages have been reported, a test case can be generated from those messages."); //$NON-NLS-1$ //$NON-NLS-2$
				continue;
			}
			
			// A test case = one validator on one project => what markers
			List markerList = new ArrayList();
			for(int j=0; j<markers.length; j++) {
				markerList.add(markers[j]);
			}
			
			// Sort the markers by validator, project, prefix, location & resource
			Collections.sort(markerList, MessageUtility.getMessageComparator(getBuffer(), false));

			Iterator iterator = markerList.iterator();
			while(iterator.hasNext()) {
				IMarker marker = (IMarker)iterator.next();			
				ValidatorMetaData vmd = MessageUtility.getValidator(marker);
				if(vmd != null) {
					StringBuffer messageBuffer = getBuffer(vmd);
					String messageId = MessageUtility.getMessagePrefix(marker);
					String resource = MessageUtility.getResource(marker);
					Integer lineNumber = MessageUtility.getLineNumber(marker);
					String location = (lineNumber == null) ? (location = MessageUtility.getLocation(marker)) : (location = lineNumber.toString());
					String text = MessageUtility.getMessage(marker); // Emit the text of the message so that, if the message prefixes are changed in the future, it's easy to see that the same test is run with a different prefix, and that the test didn't really fail.
					text = text.replace('<', '['); // remove the XML reserved characters.
					text = text.replace('>', ']');
					text = text.replace('"', '\'');
					messageBuffer.append(MessageFormat.format(_TESTCASE_MESSAGE_TEMPLATE_, new String[]{messageId, resource, location, text}));
				}
			}
			
			iterator = _vmdBuffer.keySet().iterator(); // iterate over the vmds
			while(iterator.hasNext()) {
				ValidatorMetaData vmd = (ValidatorMetaData)iterator.next();
				StringBuffer buffer = (StringBuffer)_vmdBuffer.get(vmd);
				String testcaseTemplate = MessageFormat.format(_TESTCASE_TEMPLATE_, new String[]{project.getName(), vmd.getValidatorUniqueName(), buffer.toString()});
				
				getBuffer().write(testcaseTemplate);
				
				buffer.delete(0, buffer.length()); // clear the buffer for the next project
			}
		}
	}
	
	private StringBuffer getBuffer(ValidatorMetaData vmd) {
		StringBuffer buffer = (StringBuffer)_vmdBuffer.get(vmd);
		if(buffer == null) {
			buffer = new StringBuffer();
			_vmdBuffer.put(vmd, buffer);
		}
		return buffer;
	}

}
