/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.internal.xml.CollectingErrorHandler;
import org.eclipse.jst.j2ee.internal.xml.XmlDocumentReader;

import org.xml.sax.InputSource;

/**
 * Insert the type's description here. Creation date: (03/19/01 10:04:08 AM)
 * 
 * @author: Administrator
 */
public class ValidateXmlCommand extends AbstractCommand {
	protected List results;
	protected ModuleFile archive;
	public boolean validateNested = true;

	/**
	 * ValidateXmlCommand constructor comment.
	 */
	protected ValidateXmlCommand() {
		super();
	}

	/**
	 * ValidateXmlCommand constructor comment.
	 * 
	 * @param label
	 *            java.lang.String
	 * @param description
	 *            java.lang.String
	 */
	public ValidateXmlCommand(ModuleFile m) {
		super();
		archive = m;
	}

	/**
	 * ValidateXmlCommand constructor comment.
	 * 
	 * @param label
	 *            java.lang.String
	 */
	protected ValidateXmlCommand(String label) {
		super(label);
	}

	/**
	 * ValidateXmlCommand constructor comment.
	 * 
	 * @param label
	 *            java.lang.String
	 * @param description
	 *            java.lang.String
	 */
	protected ValidateXmlCommand(String label, String description) {
		super(label, description);
	}

	/**
	 * @see Command
	 */
	public void execute() {
		results = new ArrayList();
		validatateXml();
		if (isValidateNested()) {
			List archives = archive.getArchiveFiles();
			for (int i = 0; i < archives.size(); i++) {
				Archive a = (Archive) archives.get(i);
				if (!a.isModuleFile())
					continue;
				ModuleFile m = (ModuleFile) a;
				ValidateXmlCommand cmd = new ValidateXmlCommand(m);
				cmd.execute();
				results.addAll(cmd.getResult());
			}
		}
	}

	/**
	 * @return List of XmlValidationResult; 1 for the archive, and one for each nested module file
	 */
	public Collection getResult() {
		return results;
	}

	/**
	 * Insert the method's description here. Creation date: (10/22/2001 1:06:52 PM)
	 * 
	 * @return boolean
	 */
	public boolean isValidateNested() {
		return validateNested;
	}

	protected boolean prepare() {
		return true;
	}

	/**
	 * @see Command
	 */
	public void redo() {
	}

	/**
	 * Insert the method's description here. Creation date: (10/22/2001 1:06:52 PM)
	 * 
	 * @param newValidateNested
	 *            boolean
	 */
	public void setValidateNested(boolean newValidateNested) {
		validateNested = newValidateNested;
	}

	protected void validatateXml() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		Resource res = archive.getStandardDeploymentDescriptor().eResource();
		XmlValidationResult result = new XmlValidationResult();
		result.setArchive(archive);
		try {
			res.save(bos, new java.util.HashMap());
		} catch (Exception ex) {
			throw new org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveRuntimeException(ex);
		}
		ByteArrayInputStream inStream = new ByteArrayInputStream(bos.toByteArray());
		InputSource source = new InputSource(inStream);
		CollectingErrorHandler handler = new CollectingErrorHandler();
		XmlDocumentReader parseAdapter = new XmlDocumentReader(source, null, handler);

		// the following try/catch clause is added to handle the case
		// when SAX parser throws a fatal exception (type SAXException)
		// for unmatching end tag that results in a RuntimeException to
		// be thrown. Need to catch it so we can get the parser exceptions
		// and display them to the user.
		try {
			parseAdapter.parseDocument();
		} catch (RuntimeException re) {

			if (handler.getCaughtExceptions() != null) {
				result.setArchive(archive);
				result.setCaughtExceptions(handler.getCaughtExceptions());
				results.add(result);
			}

			throw re;
		}

		result.setArchive(archive);
		result.setCaughtExceptions(handler.getCaughtExceptions());
		results.add(result);
	}
}
