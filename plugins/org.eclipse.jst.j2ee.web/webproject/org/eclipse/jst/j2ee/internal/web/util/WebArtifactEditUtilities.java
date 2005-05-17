package org.eclipse.jst.j2ee.internal.web.util;

import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class WebArtifactEditUtilities {

	public static WARFile asWARFile(IVirtualComponent virtualComponent, boolean shouldExportSource) throws OpenFailureException {
		/*if (getWebNatureType() == IWebNatureConstants.J2EE_WEB_PROJECT) {
			IProject proj = getProject();
			if (proj == null)
				return null;
			LoadStrategy loader = new WTProjectLoadStrategyImpl(proj);
			loader.setResourceSet(getResourceSet());
			return getCommonArchiveFactory().openWARFile(loader, proj.getName());
		}*/
		return null;

	}

}
