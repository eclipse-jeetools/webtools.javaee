package org.eclipse.jst.j2ee.internal.common;

import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategy;
import org.eclipse.jst.j2ee.internal.archive.operations.AppClientComponentLoadStrategyImpl;
import org.eclipse.jst.j2ee.internal.archive.operations.EARComponentLoadStrategyImpl;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class J2EEArtifactEditUtilities {

	/**
	 * @return the EARFile representation of the IVirtualComponent
	 * @throws OpenFailureException
	 */
	public static EARFile asEARFile(IVirtualComponent virtualComponent) throws OpenFailureException {
		ArchiveOptions options = new ArchiveOptions();
		options.setIsReadOnly(true);
		LoadStrategy loader = new EARComponentLoadStrategyImpl(virtualComponent);
		options.setLoadStrategy(loader);
		return CommonarchiveFactory.eINSTANCE.openEARFile(options, virtualComponent.getName());
	}

	public static ApplicationClientFile asApplicationClientFile(IVirtualComponent virtualComponent, boolean shouldExportSource) throws OpenFailureException {
		/*
		 * if (isBinaryProject()) { String location = ((J2EEModuleWorkbenchURIConverterImpl)
		 * getJ2EEWorkbenchURIConverter()).getInputJARLocation().toOSString(); return
		 * getCommonArchiveFactory().openApplicationClientFile(location); }
		 */
		AppClientComponentLoadStrategyImpl loader = new AppClientComponentLoadStrategyImpl(virtualComponent);
		loader.setExportSource(shouldExportSource);
		return CommonarchiveFactory.eINSTANCE.openApplicationClientFile(loader, virtualComponent.getName());
	}

}
