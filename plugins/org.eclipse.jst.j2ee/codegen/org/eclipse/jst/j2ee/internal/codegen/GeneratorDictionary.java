/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.codegen;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jst.j2ee.internal.xml.GeneralXmlDocumentReader;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * An instance of this class contains and resolves the mapping of generator logical names to
 * generator instances.
 */
class GeneratorDictionary {
	private Map fDictionary = null;
	private ClassLoader classLoader;
	private static final String PATH_SEPARATOR = "/";//$NON-NLS-1$
	private static final String CODEGEN_DICT_NODE_NAME = "CodegenDictionary";//$NON-NLS-1$
	private static final String INCLUDE_NODE_NAME = "include";//$NON-NLS-1$
	private static final String HREF_NODE_NAME = "href";//$NON-NLS-1$
	private static final String GENERATOR_NODE_NAME = "generator";//$NON-NLS-1$
	private static final String GENERATOR_NAME_ITEM_NAME = "name";//$NON-NLS-1$
	private static final String GENERATOR_CODE_ITEM_NAME = "code";//$NON-NLS-1$
	private static final String TEXT_NODE_NAME = "#text";//$NON-NLS-1$
	private static final String COMMENT_NODE_NAME = "#comment";//$NON-NLS-1$

	/**
	 * The constructor uses the specified class to find the corrent class loader for this generation
	 * run (the plugin class loader for the plugin where the generation is initiated). The
	 * dictionary name is the name of a plugin resource file in the plugin for the obtained loader.
	 * The file must be a xml file that contains logical name to class name mappings of the form:
	 * <code>
	 * <br>&nbsp;&nbsp;&nbsp;&lt;?xml version="1.0"?&gt;
	 * <br>&nbsp;&nbsp;&nbsp;&lt;ejbcreationDictionary&gt; 
	 * <br>&nbsp;&nbsp;&nbsp;&lt;generator name= "SessionGenerator"    code="org.eclipse.jst.j2ee.ejb.codegen.SessionGenerator"/&gt;
	 * <br>&nbsp;&nbsp;&nbsp;&lt;generator name= "EntityGenerator"     code="org.eclipse.jst.j2ee.ejb.codegen.EntityGenerator"/&gt;
	 * <br>&nbsp;&nbsp;&nbsp;&lt;generator name= "CMPEntityGenerator"  code="org.eclipse.jst.j2ee.ejb.codegen.CMPEntityGenerator"/&gt;
	 * <br>&nbsp;&nbsp;&nbsp;&lt;/ejbcreationDictionary&gt;
	 * </code>
	 */

	public GeneratorDictionary(String dictionaryName, ClassLoader loader) throws java.io.FileNotFoundException {
		super();
		fDictionary = new HashMap();
		this.classLoader = loader;
		parseFile(dictionaryName);
	}

	/**
	 * Returns the an instance of the generator class referred to by the logical generator name.
	 * 
	 * @return Generator
	 */
	public Generator get(String logicalName) throws GeneratorNotFoundException {
		Generator result = null;
		try {
			// Get the class or class name
			Class genClass = ((GeneratorDictionaryEntry) fDictionary.get(logicalName)).getGenClass(classLoader);

			// Create a new instance to return
			result = (Generator) genClass.newInstance();
		} catch (Exception x) {
			throw new GeneratorNotFoundException(logicalName);
		}
		return result;
	}


	private void parseFile(String filename) throws FileNotFoundException {

		InputStream is = classLoader.getResourceAsStream(filename);
		if (is == null)
			is = classLoader.getResourceAsStream(PATH_SEPARATOR + filename);
		if (is == null)
			is = new FileInputStream(filename);
		try {
			GeneralXmlDocumentReader reader = new GeneralXmlDocumentReader();
			reader.setInputSource(new org.xml.sax.InputSource(is));
			Element root = reader.getDocument().getDocumentElement();

			// get elements under the document root (ie import and class)
			NodeList elements = root.getChildNodes();

			for (int i = 0; i < elements.getLength(); i++) {
				Node node = elements.item(i);
				if (node.getNodeName().equalsIgnoreCase(CODEGEN_DICT_NODE_NAME)) {
					processDictionary(node.getChildNodes());
				} else if (node.getNodeName().equalsIgnoreCase(INCLUDE_NODE_NAME)) {
					NamedNodeMap map = node.getAttributes();
					Node filenode = map.getNamedItem(HREF_NODE_NAME);
					String nestedFile = filenode.getNodeValue();
					parseFile(nestedFile);
				} else if (node.getNodeName().equalsIgnoreCase(GENERATOR_NODE_NAME)) {
					NamedNodeMap map = node.getAttributes();
					String generatorName = map.getNamedItem(GENERATOR_NAME_ITEM_NAME).getNodeValue();
					String generatorCode = map.getNamedItem(GENERATOR_CODE_ITEM_NAME).getNodeValue();
					fDictionary.put(generatorName, new GeneratorDictionaryEntry(generatorCode));
				} else if (node.getNodeName().equals(TEXT_NODE_NAME))
					; // do nothing
				else if (node.getNodeName().equals(COMMENT_NODE_NAME))
					; // do nothing
				else
					; // do nothing
				//				System.out.println("Ignoring unrecognized element: "+node.getNodeName());
			}
		} catch (Exception x) {
			x.printStackTrace();
			throw new FileNotFoundException(filename);
		}
	}

	private void processDictionary(NodeList elements) {
	}
}