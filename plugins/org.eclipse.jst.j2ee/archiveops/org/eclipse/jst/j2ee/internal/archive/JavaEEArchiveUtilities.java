package org.eclipse.jst.j2ee.internal.archive;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.jee.archive.ArchiveOpenFailureException;
import org.eclipse.jst.jee.archive.ArchiveOptions;
import org.eclipse.jst.jee.archive.ArchiveSaveFailureException;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveFactory;
import org.eclipse.jst.jee.archive.IArchiveLoadAdapter;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class JavaEEArchiveUtilities implements IArchiveFactory {

	private JavaEEArchiveUtilities() {
	}

	public static JavaEEArchiveUtilities INSTANCE = new JavaEEArchiveUtilities();

	public static final String DOT_JAVA = ".java"; //$NON-NLS-1$

	public static final String DOT_CLASS = ".class"; //$NON-NLS-1$

	public static boolean isJava(IFile iFile) {
		return hasExtension(iFile, DOT_JAVA);
	}

	public static boolean isClass(IFile iFile) {
		return hasExtension(iFile, DOT_CLASS);
	}

	public static boolean hasExtension(IFile iFile, String ext) {
		String name = iFile.getName();
		return hasExtension(name, ext);
	}

	public static boolean hasExtension(String name, String ext) {
		int offset = ext.length();
		return name.regionMatches(true, name.length() - offset, ext, 0, offset);
	}

	public IArchive openArchive(IVirtualComponent virtualComponent) throws ArchiveOpenFailureException {
		IArchiveLoadAdapter archiveLoadAdapter = null;
		if (J2EEProjectUtilities.isEARProject(virtualComponent.getProject())) {
			archiveLoadAdapter = new EARComponentArchiveLoadAdapter(virtualComponent);
		} else if (J2EEProjectUtilities.isEJBComponent(virtualComponent)) {
			archiveLoadAdapter = new EJBComponentArchiveLoadAdapter(virtualComponent);
		} else if (J2EEProjectUtilities.isApplicationClientComponent(virtualComponent)) {
			archiveLoadAdapter = new AppClientComponentArchiveLoadAdapter(virtualComponent);
		} else if (J2EEProjectUtilities.isJCAComponent(virtualComponent)) {
			archiveLoadAdapter = new ConnectorComponentArchiveLoadAdapter(virtualComponent);
		} else if (J2EEProjectUtilities.isDynamicWebComponent(virtualComponent)) {
			archiveLoadAdapter = new WebComponentArchiveLoadAdapter(virtualComponent);
		} else if (J2EEProjectUtilities.isUtilityProject(virtualComponent.getProject())) {
			archiveLoadAdapter = new JavaComponentArchiveLoadAdapter(virtualComponent);
		}
		if (archiveLoadAdapter != null) {
			ArchiveOptions options = new ArchiveOptions();
			options.setOption(ArchiveOptions.LOAD_ADAPTER, archiveLoadAdapter);
			return openArchive(options);
		}
		return null;
	}

	public void closeArchive(IArchive archive) {
		IArchiveFactory.INSTANCE.closeArchive(archive);
	}

	public IArchive openArchive(IPath archivePath) throws ArchiveOpenFailureException {
		return IArchiveFactory.INSTANCE.openArchive(archivePath);
	}

	public IArchive openArchive(ArchiveOptions archiveOptions) throws ArchiveOpenFailureException {
		return IArchiveFactory.INSTANCE.openArchive(archiveOptions);
	}

	public void saveArchive(IArchive archive, IPath outputPath) throws ArchiveSaveFailureException {
		IArchiveFactory.INSTANCE.saveArchive(archive, outputPath);
	}

	public void saveArchive(IArchive archive, ArchiveOptions archiveOptions) throws ArchiveSaveFailureException {
		IArchiveFactory.INSTANCE.saveArchive(archive, archiveOptions);
	}

}
