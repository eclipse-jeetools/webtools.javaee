package org.eclipse.jst.j2ee.internal.common;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.internal.archive.operations.ApplicationClientProjectLoadStrategyImpl;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleWorkbenchURIConverterImpl;

public class J2EEArtifactEditUtilities {

	
	/**
	 * @return the EARFile representation of the IVirtualComponent
	 * @throws OpenFailureException
	 */
	
	public EARFile asEARFile() throws OpenFailureException {
		
		/*IVirtualComponent comp = ComponentCore.createComponent(getComponentHandle().getProject(), getComponentHandle().getName());;
		if (comp == null)
			return null;
		ArchiveOptions options = new ArchiveOptions();
		options.setIsReadOnly(true);
		LoadStrategy loader = new EARProjectLoadStrategyImpl(comp);
		options.setLoadStrategy(loader);
		loader.setResourceSet(getArtifactEditModel().getResourceSet());
		return CommonarchiveFactory.eINSTANCE.openEARFile(options, comp.getName());*/
		
		return null;
		
	}
	
	public static ApplicationClientFile asApplicationClientFile(boolean shouldExportSource) throws OpenFailureException {
		
		/*IProject proj = getProject();
		if (proj == null)
			return null;

		if (isBinaryProject()) {
			String location = ((J2EEModuleWorkbenchURIConverterImpl) getJ2EEWorkbenchURIConverter()).getInputJARLocation().toOSString();
			return getCommonArchiveFactory().openApplicationClientFile(location);
		}
		ApplicationClientProjectLoadStrategyImpl loader = new ApplicationClientProjectLoadStrategyImpl(proj);
		loader.setExportSource(shouldExportSource);
		loader.setResourceSet(this.getResourceSet());
		return getCommonArchiveFactory().openApplicationClientFile(loader, proj.getName());*/
		return null;
	}
	

}
