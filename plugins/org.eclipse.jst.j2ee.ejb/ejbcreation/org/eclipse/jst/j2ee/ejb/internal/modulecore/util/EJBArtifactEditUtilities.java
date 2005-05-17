package org.eclipse.jst.j2ee.ejb.internal.modulecore.util;

import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;

public class EJBArtifactEditUtilities {

	public static EJBJarFile asEJBJarFile(boolean shouldExportSource) throws OpenFailureException {

		/*IProject proj = getProject();
		if (proj == null)
			return null;

		if (isBinaryProject()) {
			String location = ((J2EEModuleWorkbenchURIConverterImpl) getJ2EEWorkbenchURIConverter()).getInputJARLocation().toOSString();
			ArchiveOptions options = new ArchiveOptions();
			options.setIsReadOnly(true);
			return getCommonArchiveFactory().openEJB11JarFile(options, location);
		}
		EJBProjectLoadStrategyImpl loader = new EJBProjectLoadStrategyImpl(proj);
		loader.setExportSource(shouldExportSource);
		loader.setResourceSet(this.getResourceSet());
		return getCommonArchiveFactory().openEJB11JarFile(loader, proj.getName());*/
		return null;
	}

}
