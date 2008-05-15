/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers;

import java.io.File;

import junit.framework.Assert;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.AssertWarn;
import org.eclipse.wst.common.tests.DataModelVerifier;

/**
 * @author Administrator
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public abstract class JEEExportDataModelVerifier extends DataModelVerifier {
	protected IDataModel model = null;
	
	@Override
	public void verify(IDataModel model) throws Exception {
		super.verify(model);
		
		this.model = model;
		
		this.verifyArchiveExists();
		this.verifyArchiveTypeAndVersion();
	}
	
	protected abstract int getExportType();
	
	private void verifyArchiveExists() {
		String archivePath = model.getStringProperty(IJ2EEComponentExportDataModelProperties.ARCHIVE_DESTINATION);
		File archive = new File(archivePath);
		Assert.assertTrue("The exported archive must exist.", archive.exists());
	}
	
	public static boolean isEE5WithoutDD(IProject project) throws Exception{
		String sProjVersion = J2EEProjectUtilities.getJ2EEProjectVersion(project);
		int iProjVersion = J2EEVersionUtil.convertVersionStringToInt(sProjVersion);
		IArchive projectArchive = null;
		try {
			projectArchive = JavaEEArchiveUtilities.INSTANCE.openArchive(ComponentCore.createComponent(project));
			JavaEEQuickPeek archiveQuickPeek = JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(projectArchive);
			if(archiveQuickPeek.getJavaEEVersion() == J2EEVersionConstants.JEE_5_0_ID){
				boolean hasDD = false;
				switch(archiveQuickPeek.getType()){
				case J2EEVersionConstants.APPLICATION_CLIENT_TYPE:
					hasDD = projectArchive.containsArchiveResource(new Path(J2EEConstants.APP_CLIENT_DD_URI));
					break;
				case J2EEVersionConstants.APPLICATION_TYPE:
					hasDD = projectArchive.containsArchiveResource(new Path(J2EEConstants.APPLICATION_DD_URI));
					break;
				case J2EEVersionConstants.EJB_TYPE:
					hasDD = projectArchive.containsArchiveResource(new Path(J2EEConstants.EJBJAR_DD_URI));
					break;
				case J2EEVersionConstants.WEB_TYPE:
					hasDD = projectArchive.containsArchiveResource(new Path(J2EEConstants.WEBAPP_DD_URI));
					break;
				}
				if(!hasDD){
					System.err.println("TODO -- NO DD import support needs to be implemented.");
					System.err.println("     -- see https://bugs.eclipse.org/bugs/show_bug.cgi?id=194679");
					return true;
				}
			}
		} finally {
			if (null != projectArchive) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(projectArchive);
			}
		}
		return false;
	}
	
	private void verifyArchiveTypeAndVersion() throws Exception {
		String projName = model.getStringProperty(IJ2EEComponentExportDataModelProperties.PROJECT_NAME);
		IProject exportedProj = ProjectUtilities.getProject(projName);
		
		IArchive projectArchive = null;
		try {
			projectArchive = JavaEEArchiveUtilities.INSTANCE.openArchive(ComponentCore.createComponent(exportedProj));
			JavaEEQuickPeek archiveQuickPeek = JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(projectArchive);
			int type = archiveQuickPeek.getType();
			AssertWarn.warnEquals("Archive type did not match exported type", getExportType(), type);
		} finally {
			if (null != projectArchive) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(projectArchive);
			}
		}
		
		String archivePath = model.getStringProperty(IJ2EEComponentExportDataModelProperties.ARCHIVE_DESTINATION);
		IArchive zipArchive = null;
		
		try {
			zipArchive = JavaEEArchiveUtilities.INSTANCE.openArchive(new Path(archivePath));
			
			JavaEEQuickPeek archiveQuickPeek = JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(zipArchive);
			int type = archiveQuickPeek.getType();
			AssertWarn.warnEquals("Archive type did not match exported type", getExportType(), type);
			int iVersionConstant = archiveQuickPeek.getVersion();
			String sProjVersion = J2EEProjectUtilities.getJ2EEDDProjectVersion(exportedProj);
			int iProjVersion = J2EEVersionUtil.convertVersionStringToInt(sProjVersion);
			AssertWarn.warnEquals("Archive version did not match exported project version", iProjVersion, iVersionConstant);
		} finally {
			if (null != zipArchive) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(zipArchive);
			}
		}
	}
}
