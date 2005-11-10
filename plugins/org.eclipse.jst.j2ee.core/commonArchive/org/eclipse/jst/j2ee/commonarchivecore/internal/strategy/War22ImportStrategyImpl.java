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
package org.eclipse.jst.j2ee.commonarchivecore.internal.strategy;



import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminatorImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.webapplication.WebApp;


/**
 * Concrete implementer that knows how to import meta-data for a 2.2 Web app
 */
public class War22ImportStrategyImpl extends XmlBasedImportStrategyImpl {

	public static class Discriminator extends ArchiveTypeDiscriminatorImpl {
		public boolean canImport(Archive anArchive) {
			return anArchive.containsFile(J2EEConstants.WEBAPP_DD_URI);
		}

		/**
		 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator
		 */
		public org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ImportStrategy createImportStrategy(Archive old, Archive newArchive) {
			return new War22ImportStrategyImpl();
		}

		public String getUnableToOpenMessage() {
			return getXmlDDMessage(CommonArchiveResourceHandler.WAR_File, J2EEConstants.WEBAPP_DD_URI); //$NON-NLS-1$ = "WAR File"
		}

		public Archive createConvertedArchive() {
			return getArchiveFactory().createWARFile();
		}
	}

	protected static Discriminator discriminator;

	/**
	 * War22ImportStrategy constructor comment.
	 */
	public War22ImportStrategyImpl() {
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
		}
		return discriminator;
	}

	public WARFile getWARFile() {
		return (WARFile) getArchive();
	}

	/**
	 * @see com.ibm.etools.archive.ImportStrategy
	 */
	public void importMetaData() throws Exception {
		loadDeploymentDescriptor();
	}

	public void loadDeploymentDescriptor() throws Exception {

		WebApp webapp = null;
		webapp = (WebApp) primLoadDeploymentDescriptor();

		getWARFile().setDeploymentDescriptor(webapp);
	}
}
