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
package org.eclipse.jst.j2ee.commonarchivecore.strategy;



import org.eclipse.jst.j2ee.commonarchivecore.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.RARFile;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveTypeDiscriminator;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveTypeDiscriminatorImpl;
import org.eclipse.jst.j2ee.internal.xml.J2EEXmlDtDEntityResolver;
import org.eclipse.jst.j2ee.jca.Connector;


/**
 * Concrete implementer that knows how to import meta-data for a J2C Resource Adapter
 */
public class RarImportStrategyImpl extends XmlBasedImportStrategyImpl {

	public static class Discriminator extends ArchiveTypeDiscriminatorImpl {
		public boolean canImport(Archive anArchive) {
			return anArchive.containsFile(ArchiveConstants.RAR_DD_URI);
		}

		/**
		 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator
		 */
		public org.eclipse.jst.j2ee.commonarchivecore.strategy.ImportStrategy createImportStrategy(Archive old, Archive newArchive) {
			return new RarImportStrategyImpl();
		}

		public String getUnableToOpenMessage() {
			return getXmlDDMessage(CommonArchiveResourceHandler.getString("RAR_File"), ArchiveConstants.RAR_DD_URI); //$NON-NLS-1$ = "RAR File"
		}

		public Archive createConvertedArchive() {
			return getArchiveFactory().createRARFile();
		}
	}

	protected static Discriminator discriminator;

	/**
	 * RarImportStrategy constructor comment.
	 */
	public RarImportStrategyImpl() {
		super();
	}

	/**
	 * @see com.ibm.etools.archive.ImportStrategy
	 */
	public org.eclipse.jst.j2ee.commonarchivecore.strategy.ImportStrategy createImportStrategy(Archive old, Archive newArchive) {
		return getDiscriminator().createImportStrategy(old, newArchive);
	}

	public static ArchiveTypeDiscriminator getDiscriminator() {
		if (discriminator == null) {
			discriminator = new Discriminator();
			// Connectors use their own special entity resolver for now...
			J2EEXmlDtDEntityResolver.registerDtD(ArchiveConstants.CONNECTOR_SYSTEMID_1_0, "connector_1_0.dtd"); //$NON-NLS-1$
		}
		return discriminator;
	}

	public RARFile getRARFile() {
		return (RARFile) getArchive();
	}

	/**
	 * @see com.ibm.etools.archive.ImportStrategy
	 */
	public void importMetaData() throws Exception {
		loadDeploymentDescriptor();
	}

	public void loadDeploymentDescriptor() throws Exception {
		Connector connector = null;

		connector = (Connector) primLoadDeploymentDescriptor();
		getRARFile().setDeploymentDescriptor(connector);
	}
}
