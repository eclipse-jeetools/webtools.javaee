package org.eclipse.jst.j2ee.internal.web.operations;
/*
 * Licensed Material - Property of IBM 
 * (C) Copyright IBM Corp. 2002 - All Rights Reserved. 
 * US Government Users Restricted Rights - Use, duplication or disclosure 
 * restricted by GSA ADP Schedule Contract with IBM Corp. 
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.wst.web.internal.operation.ILibModule;
import org.eclipse.wst.web.internal.operation.LibModule;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class OldWebSettingsForMigration extends OldJ2EESettingsForMigration {
	
	static final String ELEMENT_WEBSETTINGS = "websettings"; //$NON-NLS-1$
	static final String ELEMENT_PROJECTTYPE = "project-type"; //$NON-NLS-1$
	static final String ELEMENT_CONTEXTROOT = "context-root"; //$NON-NLS-1$
	static final String ELEMENT_WEBCONTENT = "webcontent"; //$NON-NLS-1$
	static final String ELEMENT_JSPLEVEL = "jsp-level"; //$NON-NLS-1$
	static final String ELEMENT_LIBMODULES = "lib-modules"; //$NON-NLS-1$
	static final String ELEMENT_LIBMODULE = "lib-module"; //$NON-NLS-1$
	static final String ELEMENT_LIBMODULE_JAR = "jar"; //$NON-NLS-1$
	static final String ELEMENT_LIBMODULE_PROJECT = "project"; //$NON-NLS-1$
	static final String ELEMENT_FEATURES = "features"; //$NON-NLS-1$
	static final String ELEMENT_FEATURE = "feature"; //$NON-NLS-1$
	static final String ELEMENT_FEATUREID = "feature-id";	 //$NON-NLS-1$

	static final ILibModule[] EMPTY_LIBMODULES = new ILibModule[0];
	static final String[] EMPTY_FEATURES = new String[0];
	static boolean validWebSettings = true;
		
	public OldWebSettingsForMigration(IProject project) {
		super(project, null);
	}
	
	public OldWebSettingsForMigration(IProject project, IFile webSettings) {
		super(project, null, webSettings);
		if (getDOMDocument(webSettings) == null) {
			validWebSettings = false;
		}
	}

	protected IFile getSettingsFile() {
		if (fSettingsFile == null) {
			fSettingsFile = fProject.getFile(IWebNatureConstants.WEBSETTINGS_MIGRATION_FILE_NAME);
		}
		return fSettingsFile;
	}

	public String getContextRoot() {
		return getValue(ELEMENT_CONTEXTROOT);
	}	
	
	public String getJSPLevel() {
		return getValue(ELEMENT_JSPLEVEL);
	}

	public String getWebContentName() {
		return getValue(ELEMENT_WEBCONTENT);
	}	
	
	public ILibModule[] getLibModules() {
		Element root = getRootElement();
		if (root == null) return EMPTY_LIBMODULES;
		
		Element libModuleNode = findChildNode(root, ELEMENT_LIBMODULES);
		if (libModuleNode == null) return EMPTY_LIBMODULES;		
		
		NodeList children = libModuleNode.getChildNodes();
		ArrayList results = new ArrayList();
		for (int i=0; i<children.getLength(); i++) {
			Node node = children.item(i);
			ILibModule libModule = getLibModule(node);
			if (libModule != null) {
				results.add(libModule);
			}
		}
		
		return (ILibModule[]) results.toArray(new ILibModule[results.size()]);	
	}
	
	protected ILibModule getLibModule(Node node) {
		if (!node.getNodeName().equalsIgnoreCase(ELEMENT_LIBMODULE)) return null;
		String jarName = getNodeValue((Element) node, ELEMENT_LIBMODULE_JAR);
		String projectName = getNodeValue((Element) node, ELEMENT_LIBMODULE_PROJECT);
		
		ILibModule libModule = new LibModule(jarName, projectName);
		return libModule;
	}
	
	public String[] getFeatureIds() {
		Element root = getRootElement();
		if (root == null) return EMPTY_FEATURES;
		
		Element featuresNode = findChildNode(root, ELEMENT_FEATURES);
		if (featuresNode == null) return EMPTY_FEATURES;		
		
		NodeList children = featuresNode.getChildNodes();
		ArrayList results = new ArrayList();
		for (int i=0; i<children.getLength(); i++) {
			Node node = children.item(i);
			String featureId = getFeatureId(node);
			if (featureId != null) {
				results.add(featureId);
			}
		}
		
		return (String[]) results.toArray(new String[results.size()]);	
	}
	
	protected String getFeatureId(Node node) {
		if (!node.getNodeName().equalsIgnoreCase(ELEMENT_FEATURE)) return null;
		String id = getNodeValue((Element) node, ELEMENT_FEATUREID);
		
		return id;
	}	
	
	public String getProjectType() {
		return getValue(ELEMENT_PROJECTTYPE);
	}	
	
	public String getCurrentVersion() {
		// The following change is needed when the websettings file is
		// deleted from a version 4 workspace  Checking for webapplication
		// folder - Otherwise, new projects will not work.
		IContainer webmoduleFolder = fProject.getFolder(IWebNatureConstants.WEB_MODULE_DIRECTORY_V4);
		IFolder webinfFolder = ((IFolder) webmoduleFolder).getFolder(IWebNatureConstants.INFO_DIRECTORY);
		if (webinfFolder.exists()) {
			return VERSION_V4;
		}
		return CURRENT_VERSION;
	}
	
	protected String getValue(String settingName) {		
		Element root = getRootElement();
		if (root == null) return null;		
		return getNodeValue(root, settingName);
	}
	
	protected void createNewDocument() throws CoreException, IOException{
		StringWriter writer = new StringWriter();
		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); //$NON-NLS-1$
		writer.write("\n"); //$NON-NLS-1$
		writer.write("<websettings version=\""); //$NON-NLS-1$
		writer.write(getCurrentVersion());
		writer.write("\">");//$NON-NLS-1$
		writer.write("\n"); //$NON-NLS-1$
		writer.write("</websettings>"); //$NON-NLS-1$
		writer.write("\n"); //$NON-NLS-1$

		InputStream sourceStream = new ByteArrayInputStream(writer.toString().getBytes("UTF8")); //$NON-NLS-1$
		IFile webSettingsFile = getSettingsFile();
		if (webSettingsFile.exists())
			webSettingsFile.setContents(sourceStream, true, true, null);
		else
			webSettingsFile.create(sourceStream, true, null);
		read();
	}
		
	public void setContextRoot(String contextRoot) {
		setValue(ELEMENT_CONTEXTROOT, contextRoot);
	}

	public void setJSPLevel(String jspLevel) {
		setValue(ELEMENT_JSPLEVEL, jspLevel);
	}

	public void setWebContentName(String name) {
    	String defaultName= getWebContentName();
		if (defaultName == null || defaultName.length() == 0 || !name.equals(defaultName))
			setValue(ELEMENT_WEBCONTENT, name);
	}

	public void setProjectType(String projectType) {
		setValue(ELEMENT_PROJECTTYPE, projectType);
	}
	
	public void setLibModules(ILibModule[] libModules) {
		Document doc = getOrCreateDocument();		
		Node libModulesNode = findOrCreateChildNode(doc.getDocumentElement(),ELEMENT_LIBMODULES);
		Node firstChild = null;
		
		// Remove all of the children.
		while ((firstChild = libModulesNode.getFirstChild()) != null)
			libModulesNode.removeChild(firstChild);
			
		// Add new children.
		for (int i = 0; i < libModules.length; i++) {
			ILibModule iLibModule = libModules[i];
			if (iLibModule != null) 
				addLibModule(libModulesNode, iLibModule);
		}
	}
	
	protected void addLibModule(Node libModulesNode, ILibModule libModule) {
		Document doc = getDOMDocument();
		Element libModuleNode = doc.createElement(ELEMENT_LIBMODULE);
		libModulesNode.appendChild(libModuleNode);
		setValue(libModuleNode, ELEMENT_LIBMODULE_JAR, libModule.getJarName());
		setValue(libModuleNode, ELEMENT_LIBMODULE_PROJECT, libModule.getProjectName());
	}

	public void setFeatureIds(String[] featureIds) {
		Document doc = getOrCreateDocument();		
		Node featuresNode = findOrCreateChildNode(doc.getDocumentElement(),ELEMENT_FEATURES);
	
		// Add new children.
		for (int i = 0; i < featureIds.length; i++) {
			String sFeatureId = featureIds[i];
			if (sFeatureId != null) 
				addFeatureId(featuresNode, sFeatureId);
		}
	}

	protected void addFeatureId(Node featuresNode, String featureId) {
		Document doc = getDOMDocument();
		Element featureNode = doc.createElement(ELEMENT_FEATURE);
		featuresNode.appendChild(featureNode);
		setValue(featureNode,ELEMENT_FEATUREID, featureId);
	}

	public boolean isValidWebSettings() {
		return validWebSettings;
	}
	public void removeFeatureId(String removeId) {
		Element root = getRootElement();
		if (root != null) {
			Element featuresNode = findChildNode(root, ELEMENT_FEATURES);
			if (featuresNode != null) {
				NodeList children = featuresNode.getChildNodes();
				ArrayList results = new ArrayList();
				for (int i = 0; i < children.getLength(); i++) {
					Node node = children.item(i);
					String featureId = getFeatureId(node);
					if (featureId != null) {
						// determine if in the list to remove
						if (!(featureId.equals(removeId)))
							results.add(featureId);
					}
				}
				// Remove all of the children.
				Node firstChild = null;
				while ((firstChild = featuresNode.getFirstChild()) != null)
					featuresNode.removeChild(firstChild);
				if (results.size() > 0) {
					String[] updateFeatureIds = (String[]) results.toArray(new String[results.size()]);
					// Add new children.
					for (int i = 0; i < results.size(); i++) {
						String sFeatureId = updateFeatureIds[i];
						if (sFeatureId != null)
							addFeatureId(featuresNode, sFeatureId);
					}
				}
			}
		}
	}
	public String getRootNodeName() {
		return ELEMENT_WEBSETTINGS;
	}
	
	public static String getWebContentDirectory(InputStream inputStream){
		InputStreamReader fileStream = null;
		try {
			fileStream = new InputStreamReader(inputStream, "utf-8"); //$NON-NLS-1$
			DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document fDomDocument = parser.parse(new InputSource(fileStream));
			Element root = fDomDocument.getDocumentElement();
			if(root.getNodeName().equalsIgnoreCase(ELEMENT_WEBSETTINGS)){
				NodeList list = root.getChildNodes();
				for(int i=0, length = list.getLength();i<length; i++){
					Node node = list.item(i);
					if(node.getNodeName().equals(ELEMENT_WEBCONTENT)){
						NodeList childNodes = node.getChildNodes();
						for (int j = 0, childLength = childNodes.getLength(); j < childLength; j++) {
							Node curNode = childNodes.item(j);
							if (curNode.getNodeType() == Node.TEXT_NODE) {
								return curNode.getNodeValue();
							}
						}
						return null;
					}
				}
			}
		} catch (UnsupportedEncodingException e) { 
			//Ignore
		} catch (ParserConfigurationException e) {
			//Ignore
		} catch (FactoryConfigurationError e) {
			//Ignore
		} catch (SAXException e) {
			//Ignore
		} catch (IOException e) {
			//Ignore
		} catch(Exception e){
			//Ignore
		}finally {
			if (fileStream != null)
				try {
					fileStream.close();
				} catch (IOException e1) {
					//Ignore
				}
		} 
		return null;
	}
	
}
