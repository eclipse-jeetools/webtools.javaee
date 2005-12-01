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
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminatorImpl;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.internal.J2EEConstants;


/**
 * Concrete implementer that knows how to import meta-data for a 1.1 Ejb Jar
 */
public class EjbJar11ImportStrategyImpl extends XmlBasedImportStrategyImpl {

	public static class Discriminator extends ArchiveTypeDiscriminatorImpl {
		public boolean canImport(Archive anArchive) {
			return anArchive.containsFile(J2EEConstants.EJBJAR_DD_URI);
		}

		/**
		 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator
		 */
		public org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ImportStrategy createImportStrategy(Archive old, Archive newArchive) {
			return new EjbJar11ImportStrategyImpl();
		}

		public String getUnableToOpenMessage() {
			return getXmlDDMessage(CommonArchiveResourceHandler.EJB_Jar_File, J2EEConstants.EJBJAR_DD_URI); //$NON-NLS-1$ = "EJB Jar File"
		}

		public Archive createConvertedArchive() {
			return getArchiveFactory().createEJBJarFile();
		}
	}

	protected static Discriminator discriminator;

	/**
	 * EjbJar11ImportStrategy constructor comment.
	 */
	public EjbJar11ImportStrategyImpl() {
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

	public EJBJarFile getEJBJarFile() {
		return (EJBJarFile) getArchive();
	}

	/**
	 * @see com.ibm.etools.archive.ImportStrategy
	 */
	public void importMetaData() throws Exception {
		loadDeploymentDescriptor();
	}

	public void loadDeploymentDescriptor() throws Exception {
		EJBJar ejbJar = null;

		ejbJar = (EJBJar) primLoadDeploymentDescriptor();
		getEJBJarFile().setDeploymentDescriptor(ejbJar);
	}
}
