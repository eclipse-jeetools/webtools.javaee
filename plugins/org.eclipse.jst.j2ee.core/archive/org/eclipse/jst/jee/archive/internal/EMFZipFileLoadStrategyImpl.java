package org.eclipse.jst.jee.archive.internal;

import java.util.zip.ZipFile;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.jee.archive.ArchiveModelLoadException;
import org.eclipse.jst.jee.archive.IArchive;



public class EMFZipFileLoadStrategyImpl extends ZipFileLoadStrategyImpl {

	private EMFLoadStrategyHelper emfHelper = new EMFLoadStrategyHelper();

	public EMFZipFileLoadStrategyImpl() {
		super();
	}

	public EMFZipFileLoadStrategyImpl(ZipFile zipFile) {
		super(zipFile);
	}

	public void setArchive(IArchive archive) {
		super.setArchive(archive);
		emfHelper.setArchive(archive);
	}

	public boolean containsModelObject(IPath modelObjectPath) {
		return emfHelper.containsModelObject(modelObjectPath);
	}

	public Object getModelObject(IPath modelObjectPath) throws ArchiveModelLoadException {
		return emfHelper.getModelObject(modelObjectPath);
	}
}
