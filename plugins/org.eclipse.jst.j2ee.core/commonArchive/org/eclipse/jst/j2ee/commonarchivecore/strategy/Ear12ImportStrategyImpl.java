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



import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.commonarchivecore.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveTypeDiscriminator;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveTypeDiscriminatorImpl;


/**
 * Concrete implementer that knows how to import meta-data for a 1.2 Application Client
 */
public class Ear12ImportStrategyImpl extends XmlBasedImportStrategyImpl {

	public static class Discriminator extends ArchiveTypeDiscriminatorImpl {
		public Archive createConvertedArchive() {
			return getArchiveFactory().createEARFile();
		}

		public boolean canImport(Archive anArchive) {
			return anArchive.containsFile(ArchiveConstants.APPLICATION_DD_URI);
		}

		/**
		 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator
		 */
		public org.eclipse.jst.j2ee.commonarchivecore.strategy.ImportStrategy createImportStrategy(Archive old, Archive newArchive) {
			return new Ear12ImportStrategyImpl();
		}

		public String getUnableToOpenMessage() {
			return getXmlDDMessage(CommonArchiveResourceHandler.getString("EAR_File"), ArchiveConstants.APPLICATION_DD_URI); //$NON-NLS-1$ = "EAR File"
		}
	}

	protected static Discriminator discriminator;

	/**
	 * Ear12ImportStrategy constructor comment.
	 */
	public Ear12ImportStrategyImpl() {
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
		}
		return discriminator;
	}

	public EARFile getEARFile() {
		return (EARFile) getArchive();
	}

	/**
	 * @see com.ibm.etools.archive.ImportStrategy
	 */
	public void importMetaData() throws Exception {
		loadDeploymentDescriptor();
	}

	public void loadDeploymentDescriptor() throws Exception {
		Application appl = null;

		appl = (Application) primLoadDeploymentDescriptor();

		getEARFile().setDeploymentDescriptor(appl);
	}
}
