/*
 * Created on Aug 19, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.util.Util;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class OldJ2EESettingsForMigration {

	protected IFile fSettingsFile;
	protected IProject fProject;
	protected Document fDomDocument;
	protected J2EENature nature = null;
	// Version number may not change with every release,
	// only when changes necessitate a new version number
	public static String CURRENT_VERSION = "600"; //$NON-NLS-1$
	public static String VERSION_V4 = "400"; //$NON-NLS-1$
	public static final String ELEMENT_WORKSPACE_VERSION = "version"; //$NON-NLS-1$

	public static String J2EE_SETTINGS_FILE_NAME = ".j2ee"; //$NON-NLS-1$

	static final String ELEMENT_J2EESETTINGS = "j2eesettings"; //$NON-NLS-1$
	static final String ELEMENT_J2EE_MODULE_VERSION = "moduleversion"; //$NON-NLS-1$

	public OldJ2EESettingsForMigration(IProject project, J2EENature nature) {
		fProject = project;
		this.nature = nature;
		if (getDOMDocument() == null) {
			try {
				createNewDocument();
			} catch (CoreException e) {
				//Ignore
			} catch (IOException e) {
				//Ignore
			}
		}
	}

	public OldJ2EESettingsForMigration(IProject project, J2EENature nature, IFile webSettings) {
		fProject = project;
		this.nature = nature;
	}

	protected void createNewDocument() throws CoreException, IOException {
		StringWriter writer = new StringWriter();
		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); //$NON-NLS-1$
		writer.write("\n"); //$NON-NLS-1$
		writer.write("<j2eesettings version=\""); //$NON-NLS-1$
		writer.write(getCurrentVersion());
		writer.write("\">");//$NON-NLS-1$
		writer.write("\n"); //$NON-NLS-1$
		writer.write("</j2eesettings>"); //$NON-NLS-1$
		writer.write("\n"); //$NON-NLS-1$

		InputStream sourceStream = new ByteArrayInputStream(writer.toString().getBytes("UTF8")); //$NON-NLS-1$
		IFile settingsFile = getSettingsFile();
		if (settingsFile.exists())
			settingsFile.setContents(sourceStream, true, true, null);
		else
			settingsFile.create(sourceStream, true, null);
		read();
	}

	public OldJ2EESettingsForMigration(IProject project) {
		fProject = project;
		if (getDOMDocument() == null) {
			try {
				createNewDocument();
			} catch (CoreException e) {
				//Ignore
			} catch (IOException e) {
				//Ignore
			}
		}
	}

	protected Document getDOMDocument() {
		if (fDomDocument == null) {
			try {
				read();
			} catch (IOException e) {
				//Ignore
			}
		}
		return fDomDocument;
	}

	// Version of getDomDocument for use by import
	protected Document getDOMDocument(IFile webSettings) {
		if (fDomDocument == null) {
			try {
				read(webSettings);
			} catch (IOException e) {
				//Ignore
			}
		}
		return fDomDocument;
	}

	public String getCurrentVersion() {
		// The following change is needed when the websettings file is
		// deleted from a version 4 workspace Checking for webapplication
		// folder - Otherwise, new projects will not work.
		return CURRENT_VERSION;
	}

	protected IFile getSettingsFile() {
		if (fSettingsFile == null) {
			fSettingsFile = fProject.getFile(J2EE_SETTINGS_FILE_NAME);
		}
		return fSettingsFile;
	}

	protected void read() throws IOException {
		// This following was changed for Defect 212723 The Util StringReader
		// was changed to the InputStreamReader MAY
		IFile settingsFile = getSettingsFile();
		InputStream inputStream = null;
		InputStreamReader fileStream = null;
		if (settingsFile.exists()) {
			try {
				ClassLoader prevClassLoader = Thread.currentThread().getContextClassLoader();
				try {
					Thread.currentThread().setContextClassLoader(getClass().getClassLoader());

					// JZ: fix to defect 240171
					inputStream = settingsFile.getContents(true);
					fileStream = new InputStreamReader(inputStream, "utf-8"); //$NON-NLS-1$

					DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					fDomDocument = parser.parse(new InputSource(fileStream));
				} finally {
					Thread.currentThread().setContextClassLoader(prevClassLoader);
				}
			} catch (JavaModelException e) {
				throw new IOException(Util.bind("file.badFormat")); //$NON-NLS-1$	
			} catch (CoreException e) {
				throw new IOException(Util.bind("file.badFormat")); //$NON-NLS-1$			
			} catch (SAXException e) {
				throw new IOException(Util.bind("file.badFormat")); //$NON-NLS-1$
			} catch (ParserConfigurationException e) {
				throw new IOException(Util.bind("file.badFormat")); //$NON-NLS-1$
			} finally {
				if (fileStream != null)
					fileStream.close();
			}
		}
	}


	// Version of read for use by import
	protected void read(IFile settings) throws IOException {
		// This following was changed for Defect 212723 The Util StringReader
		// was changed to the InputStreamReader MAY
		IFile settingsFile = settings;

		InputStream inputStream = null;
		InputStreamReader fileStream = null;
		if (settingsFile != null) {
			try {
				ClassLoader prevClassLoader = Thread.currentThread().getContextClassLoader();
				try {
					Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
					inputStream = settingsFile.getContents();
					fileStream = new InputStreamReader(inputStream, "utf-8"); //$NON-NLS-1$

					DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					fDomDocument = parser.parse(new InputSource(fileStream));
				} finally {
					Thread.currentThread().setContextClassLoader(prevClassLoader);
				}

			} catch (SAXException e) {
				throw new IOException(Util.bind("file.badFormat")); //$NON-NLS-1$
			} catch (ParserConfigurationException e) {
				throw new IOException(Util.bind("file.badFormat")); //$NON-NLS-1$
			} catch (CoreException ce) {
				ce.printStackTrace();
			} finally {
				if (fileStream != null)
					fileStream.close();
			}
		}
	}

	public void write() throws CoreException {
		if (fDomDocument == null)
			return;

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$
			transformer.transform(new DOMSource(fDomDocument.getDocumentElement()), new StreamResult(outStream));
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		InputStream sourceStream = new ByteArrayInputStream(outStream.toByteArray());

		IFile settingsFile = getSettingsFile();
		if (settingsFile.exists())
			settingsFile.setContents(sourceStream, true, true, null);
		else
			settingsFile.create(sourceStream, true, null);
	}

	public String getVersion() {
		Document doc = getDOMDocument();
		if (doc == null)
			return null;

		Element root = doc.getDocumentElement();
		if (root == null)
			return null;
		if (!root.getNodeName().equalsIgnoreCase(getRootNodeName()))
			return null;

		return root.getAttribute(ELEMENT_WORKSPACE_VERSION); //$NON-NLS-1$
	}


	public void setVersion(String version) {
		Document doc = getDOMDocument();
		if (doc == null)
			return;

		Element root = doc.getDocumentElement();
		if (root == null)
			return;

		if (!root.getNodeName().equalsIgnoreCase(getRootNodeName()))
			return;

		root.setAttribute(ELEMENT_WORKSPACE_VERSION, version); //$NON-NLS-1$
	}

	protected void setValue(Element root, String nodeName, String value) {
		Node node = findOrCreateChildNode(root, nodeName);

		NodeList childNodes = node.getChildNodes();

		if (childNodes.getLength() == 0) {
			Text newText = getDOMDocument().createTextNode(value);
			node.appendChild(newText);
			root.appendChild(node);
		} else {
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node curNode = childNodes.item(i);
				if (curNode.getNodeType() == Node.TEXT_NODE)
					curNode.setNodeValue(value);
			}
		}
	}

	protected Node findOrCreateChildNode(Element root, String nodeName) {
		Node node = findChildNode(root, nodeName);
		if (node == null) {
			// If the element does not exist yet, create one.
			node = getDOMDocument().createElement(nodeName);
			root.appendChild(node);
		}
		return node;
	}

	protected Element findChildNode(Element parent, String nodeName) {
		NodeList list = parent.getChildNodes();
		int length = list.getLength();
		for (int i = 0; i < length; ++i) {
			Node curNode = list.item(i);
			if (curNode.getNodeType() == Node.ELEMENT_NODE) {
				Element curElement = (Element) curNode;
				if (curElement.getNodeName().equalsIgnoreCase(nodeName))
					return curElement;
			}
		}
		return null;
	}

	protected Document getOrCreateDocument() {
		Document doc = getDOMDocument();
		if (doc == null) {
			try {
				createNewDocument();
				doc = getDOMDocument();
			} catch (CoreException e) { 
				//Ignore
			} catch (IOException e) {
				//Ignore
			}
		}
		return doc;
	}

	protected Element getRootElement() {
		Document doc = getDOMDocument();
		if (doc == null)
			return null;

		Element root = doc.getDocumentElement();
		if (root == null)
			return null;
		if (!root.getNodeName().equalsIgnoreCase(getRootNodeName()))
			return null;
		return root;
	}

	protected String getValue(String settingName) {
		Element root = getRootElement();
		if (root == null)
			return null;
		return getNodeValue(root, settingName);
	}

	protected String getNodeValue(Element parent, String nodeName) {
		if (parent != null) {
			Element node = findChildNode(parent, nodeName);
			if (node != null)
				return getChildText(node);
		}
		return null;
	}

	protected String getChildText(Element node) {
		NodeList list = node.getChildNodes();
		int length = list.getLength();
		for (int i = 0; i < length; ++i) {
			Node curNode = list.item(i);
			if (curNode.getNodeType() == Node.TEXT_NODE) {
				return curNode.getNodeValue();
			}
		}
		return null;
	}

	protected void setValue(String nodeName, String value) {
		Document doc = getOrCreateDocument();
		setValue(doc.getDocumentElement(), nodeName, value);
	}

	public int getModuleVersion() {
		int version = 0;
		String moduleVer = getValue(ELEMENT_J2EE_MODULE_VERSION);
		if (moduleVer != null)
			version = Integer.valueOf(moduleVer).intValue();
		return version;
	}

	public void setModuleVersion(int moduleVersion) {
		Integer holder = new Integer(moduleVersion);
		setValue(ELEMENT_J2EE_MODULE_VERSION, holder.toString());
	}

	public String getRootNodeName() {
		return ELEMENT_J2EESETTINGS;
	}
}
