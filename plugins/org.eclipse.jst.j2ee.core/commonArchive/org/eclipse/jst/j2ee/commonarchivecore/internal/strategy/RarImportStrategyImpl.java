/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.strategy;



import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.RARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminatorImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.xml.J2EEXmlDtDEntityResolver;
import org.eclipse.jst.j2ee.jca.Connector;


/**
 * Concrete implementer that knows how to import meta-data for a J2C Resource Adapter
 */
public class RarImportStrategyImpl extends XmlBasedImportStrategyImpl {

	public static class Discriminator extends ArchiveTypeDiscriminatorImpl {
		public boolean canImport(Archive anArchive) {
			return anArchive.containsFile(J2EEConstants.RAR_DD_URI);
		}

		/**
		 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator
		 */
		public org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ImportStrategy createImportStrategy(Archive old, Archive newArchive) {
			return new RarImportStrategyImpl();
		}

		public String getUnableToOpenMessage() {
			return getXmlDDMessage(CommonArchiveResourceHandler.RAR_File, J2EEConstants.RAR_DD_URI); //$NON-NLS-1$ = "RAR File"
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
	public org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ImportStrategy createImportStrategy(Archive old, Archive newArchive) {
		return getDiscriminator().createImportStrategy(old, newArchive);
	}

	public static ArchiveTypeDiscriminator getDiscriminator() {
		if (discriminator == null) {
			discriminator = new Discriminator();
			// Connectors use their own special entity resolver for now...
			J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.CONNECTOR_SYSTEMID_1_0, "connector_1_0.dtd"); //$NON-NLS-1$
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
