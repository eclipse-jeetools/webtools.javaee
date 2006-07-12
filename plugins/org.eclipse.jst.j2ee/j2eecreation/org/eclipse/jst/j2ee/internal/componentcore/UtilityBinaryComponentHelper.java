package org.eclipse.jst.j2ee.internal.componentcore;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminator;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class UtilityBinaryComponentHelper extends EnterpriseBinaryComponentHelper {

	public UtilityBinaryComponentHelper(IVirtualComponent component) {
		super(component);
	}
	
	protected Archive getUniqueArchive() {
		String archiveURI = getArchiveURI();
		try {
			return openArchive(archiveURI);
		} catch (OpenFailureException e) {
		}
		return null;
	}
	
	protected ArchiveTypeDiscriminator getDiscriminator() {
		return null;
	}

	protected Archive openArchive(String archiveURI) throws OpenFailureException {
		return CommonarchiveFactory.eINSTANCE.openArchive(getArchiveOptions(), archiveURI);
	}

	public EObject getPrimaryRootObject() {
		return null;
	}

}
