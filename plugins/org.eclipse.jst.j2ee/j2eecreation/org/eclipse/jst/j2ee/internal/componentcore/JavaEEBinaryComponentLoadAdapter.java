package org.eclipse.jst.j2ee.internal.componentcore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.archive.JavaEEEMFZipFileLoadAdapterImpl;
import org.eclipse.jst.jee.archive.ArchiveOpenFailureException;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;

public class JavaEEBinaryComponentLoadAdapter extends JavaEEEMFZipFileLoadAdapterImpl {

	private java.io.File file = null;
	private IPath archivePath;
	private boolean physicallyOpen = true;
	
	public JavaEEBinaryComponentLoadAdapter(VirtualArchiveComponent archiveComponent) throws ArchiveOpenFailureException {
		super();
		java.io.File diskFile = null;
		diskFile = archiveComponent.getUnderlyingDiskFile();
		if (!diskFile.exists()) {
			IFile wbFile = archiveComponent.getUnderlyingWorkbenchFile();
			diskFile = new File(wbFile.getLocation().toOSString());
		}
		IPath archivePath = new Path(diskFile.getAbsolutePath());
		file = new java.io.File(archivePath.toOSString());
		ZipFile zipFile;
		try {
			zipFile = new ZipFile(file);
		} catch (ZipException e) {
			ArchiveOpenFailureException openFailureException = new ArchiveOpenFailureException(e);
			throw openFailureException;
		} catch (IOException e) {
			ArchiveOpenFailureException openFailureException = new ArchiveOpenFailureException(e);
			throw openFailureException;
		}
		setZipFile(zipFile);
		setArchivePath(archivePath);
	}
	
	private void setArchivePath(IPath archivePath) {
		this.archivePath = archivePath;
	}
	
	public IPath getArchivePath() {
		return archivePath;
	}
	
	public boolean isPhysicallyOpen(){
		return physicallyOpen;
	}
	
	public void physicallyOpen() throws ZipException, IOException{
		if(!isPhysicallyOpen()){
			physicallyOpen = true;
			setZipFile(new ZipFile(file));
			System.err.println("PhysicallyOpen");
			Thread.dumpStack();
		}
	}
	
	public void physicallyClose(){
		if(isPhysicallyOpen()){
			physicallyOpen = false;
			try{
				zipFile.close();
			}
			catch (Throwable t) {
				//Ignore
			}
			System.err.println("PhysicallyClose");
			Thread.dumpStack();
		} 
	}
	
	public java.io.InputStream getInputStream(IArchiveResource aFile) throws IOException, FileNotFoundException {
		final boolean isPhysciallyOpen = isPhysicallyOpen();
		Exception caughtException = null;
		try {
			if (!isPhysciallyOpen) {
				physicallyOpen();
			}
			IPath path = aFile.getPath();
			String uri = path.toString();
			ZipEntry entry = getZipFile().getEntry(uri);
			if (entry == null)
				throw new FileNotFoundException(uri);

			return new java.io.BufferedInputStream(getZipFile().getInputStream(entry)) {
				public void close() throws IOException {
					super.close();
					if (!isPhysciallyOpen ) {
						physicallyClose();
					}
				}
			};
		} catch (FileNotFoundException e) {
			caughtException = e;
			throw e;
		} catch (IllegalStateException zipClosed) {
			caughtException = zipClosed;
			throw new IOException(zipClosed.toString());
		} catch (Exception e) {
			caughtException = e;
			throw new IOException(e.toString());
		} finally {
			if (caughtException != null) {
				if (!isPhysciallyOpen) {
					physicallyClose();
				}
			}
		}
	}

	
	
}
