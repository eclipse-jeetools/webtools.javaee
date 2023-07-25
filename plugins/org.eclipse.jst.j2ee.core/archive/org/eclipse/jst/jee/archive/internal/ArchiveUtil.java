/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.archive.internal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.eclipse.jst.common.internal.modulecore.util.ManifestUtilities;
import org.eclipse.jst.jee.archive.ArchiveSaveFailureException;


public class ArchiveUtil {
	
	protected static String tempDirectoryName;

	protected static java.io.File tempDirectory;

	/**
	 * Returns the filename from the uri, or the segment after the last
	 * occurrence of a separator
	 */
	public static String getFileNameTail(String uri) {
		String tempURI = uri.replace('\\', '/');
		while (tempURI.endsWith("/")) //$NON-NLS-1$
			tempURI = tempURI.substring(0, tempURI.length() - 1);
		int lastIndex = tempURI.lastIndexOf('/');
		if (lastIndex == -1)
			return uri;
		return uri.substring(lastIndex + 1, tempURI.length());
	}

	public static java.io.File getTempDirectory() {
		return tempDirectory;
	}

	public static java.io.File createTempFile(String baseName) throws IOException {
		return createTempFile(baseName, getTempDirectory());
	}

	public static java.io.File createTempFile(String baseName, java.io.File directory) throws IOException {
		String fileName = getFileNameTail(baseName);
		if (fileName.length() < 3) {
			fileName = "WSTMP" + fileName; //$NON-NLS-1$
		}
		java.io.File tempFile = java.io.File.createTempFile(fileName, null, directory);
		DeleteOnExitUtility.markForDeletion(tempFile);
		return tempFile;
	}

	/**
	 * returns a list of all files, recursive, that can't be written
	 */
	public static List getWriteProtectedFiles(java.io.File aFile, List aList) {
		if (aList == null)
			aList = new ArrayList();
		if (aFile.exists() && !aFile.canWrite())
			aList.add(aFile);
		if (aFile.isDirectory()) {
			java.io.File[] files = aFile.listFiles();
			for (int i = 0; i < files.length; i++) {
				getWriteProtectedFiles(files[i], aList);
			}
		}
		return aList;
	}

	public static void checkWriteable(java.io.File aFile) throws ArchiveSaveFailureException {
		List locked = ArchiveUtil.getWriteProtectedFiles(aFile, null);
		if (locked.isEmpty())
			return;

		StringBuffer msg = new StringBuffer();
		msg.append("Cannot write to file: "); //$NON-NLS-1$
		msg.append(aFile.getAbsolutePath());
		msg.append('\n');
		msg.append("One or more files is write protected or locked:"); //$NON-NLS-1$
		msg.append('\n');
		for (int i = 0; i < locked.size(); i++) {
			java.io.File lockedFile = (java.io.File) locked.get(i);
			msg.append(lockedFile.getAbsolutePath());
			msg.append('\n');
		}
		throw new ArchiveSaveFailureException(msg.toString());
	}

	/**
	 * deletes a file from the file system; for directories, recurse the
	 * subdirectories and delete them as well
	 * 
	 * @return true if successful; false if any file or sub file could not be
	 *         deleted
	 */
	public static boolean delete(java.io.File aFile) {
		if (aFile == null)
			return true;
		if (aFile.isDirectory()) {
			java.io.File[] files = aFile.listFiles();
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					if (!delete(files[i]))
						return false;
				}
			}
		}
		return aFile.delete();
	}

	/**
	 * If we can rename it then we can delete it
	 */
	public static boolean isRenameable(java.io.File orig) {
		java.io.File origCopy1 = null;
		java.io.File origCopy2 = null;
		try {
			origCopy1 = orig.getCanonicalFile();
			origCopy2 = orig.getCanonicalFile();
		} catch (java.io.IOException ex) {
			return false;
		}
		String name = null;
		String baseName = "save.tmp"; //$NON-NLS-1$
		try {
			if (orig.getParent() != null)
				baseName = new java.io.File(orig.getParent(), baseName).getCanonicalPath();
		} catch (java.io.IOException ex) {
			return false;
		}

		java.io.File temp = null;
		int index = 0;
		do {
			name = baseName + index;
			temp = new java.io.File(name);
			index++;
		} while (temp.exists());
		return origCopy1.renameTo(temp) && temp.renameTo(origCopy2);
	}

	public static void cleanupAfterTempSave(String aUri, java.io.File original, java.io.File destinationFile) throws ArchiveSaveFailureException {

		checkWriteable(original);
		boolean deleteWorked = false;
		if (original.isDirectory() && !isRenameable(original)) {
			// TODO throw new
			// SaveFailureException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.unable_replace_EXC_,
			// (new Object[]{original.getAbsolutePath()}))); // = "Unable to
			// replace original archive "
		}

		for (int i = 0; i < 10; i++) {
			if (ArchiveUtil.delete(original)) {
				deleteWorked = true;
				break;
			}
			try {
				// TODO Major hack here; the problem is that a previous call
				// to close the source file may not yet have
				// been reflected in the os/vm; therefore a subsequent call
				// to delete fails. To get around this,
				// wait for a bit and retry; if it continues to fail, then
				// time out and throw an exception
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// Ignore
			}
		}
		if (deleteWorked) {
			for (int i = 0; i < 10; i++) {
				if (destinationFile.renameTo(original)){
					DeleteOnExitUtility.fileHasBeenDeleted(destinationFile);
					return;
				}
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// Ignore
				}
			}
		}
		// TODO throw new
		// SaveFailureException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.unable_replace_EXC_,
		// (new Object[]{original.getAbsolutePath()}))); // = "Unable to replace
		// original archive "
	}

	/**
	 * Copy all the data from the input stream to the output stream up until the
	 * first end of file character, and close the two streams
	 */
	public static void copy(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		try {
			int n = in.read(buffer);
			while (n > 0) {
				out.write(buffer, 0, n);
				n = in.read(buffer);
			}
		} finally {
			if (!(in instanceof ZipInputStream))
				in.close();
			if (!(out instanceof ZipOutputStream))
				out.close();
		}
	}
	
	public static void warn(Throwable e){
		org.eclipse.jem.util.logger.proxy.Logger.getLogger().logWarning(e);
	}
	
	public static void warn(String message) {
		org.eclipse.jem.util.logger.proxy.Logger.getLogger().logWarning(message);
	}
	
	public static ZipFile newZipFile(String fileName)throws ZipException, IOException {
		return ArchiveUtil.newZipFile(new File(fileName), ZipFile.OPEN_READ);
	}
	public static ZipFile newZipFile(File aFile)throws ZipException, IOException {
		return ArchiveUtil.newZipFile(aFile, ZipFile.OPEN_READ);
	}
	
	/**
	 * Utility to create ZipFiles which avoid memory leaks 
	 * because closing them fails to close open inputstreams.
	 * There is a SUN bug open for this: http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6735255 
     * but it looks like the "fix" will be to change the Javadoc :-(   
	 * @param aFile mode
	 * @return
	 * @throws ZipException
	 * @throws IOException
	 */
	public static ZipFile newZipFile(File aFile, int mode) throws ZipException, IOException {
		return ManifestUtilities.newZipFile(aFile, mode);
	}
}
