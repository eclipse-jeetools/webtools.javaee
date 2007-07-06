package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities;
import org.eclipse.jst.jee.archive.ArchiveOpenFailureException;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;

public class ArchiveTestsUtil {

	public static boolean compareFiles(File a, File b) throws IOException {
		InputStream streamA = null;
		InputStream streamB = null;
		try {
			streamA = new FileInputStream(a);
			streamB = new FileInputStream(b);
			return compareStreams(streamA, streamB);
		} finally {
			try {
				if (streamA != null) {
					streamA.close();
				}
			} finally {
				if (streamB != null) {
					streamB.close();
				}
			}
		}
	}

	public static void compareArchives(IPath a, IPath b) throws ArchiveOpenFailureException, IOException {
		IArchive aArchive = null;
		IArchive bArchive = null;
		try {
			aArchive = JavaEEArchiveUtilities.INSTANCE.openArchive(a);
			bArchive = JavaEEArchiveUtilities.INSTANCE.openArchive(b);
			compareArchives(aArchive, bArchive);
		} finally {
			if (null != aArchive) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(aArchive);
			}
			if (null != bArchive) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(bArchive);
			}
		}
	}

	public static final String[] ARCHIVE_EXTENSIONS = new String[] { ".jar", ".war", ".rar" };

	public static void compareArchives(IArchive a, IArchive b) throws ArchiveOpenFailureException, IOException {
		List<IArchiveResource> aResources = a.getArchiveResources();
		List<IArchiveResource> bResources = new ArrayList<IArchiveResource>();
		bResources.addAll(b.getArchiveResources());

		for (IArchiveResource aRes : aResources) {
			if (aRes.getPath().equals(IArchive.EMPTY_MODEL_PATH)) {
				if (b.containsArchiveResource(aRes.getPath())) {
					bResources.remove(b.getArchiveResource(aRes.getPath()));
				}
				continue;
			}
			Assert.assertTrue("IArchive B " + b.getPath() + " is missing " + aRes.getPath(), b.containsArchiveResource(aRes.getPath()));
			IArchiveResource bRes = b.getArchiveResource(aRes.getPath());
			Assert.assertNotNull("IArchive B " + b.getPath() + " is missing " + aRes.getPath(), bRes);
			Assert.assertTrue(bResources.remove(bRes));
			if (aRes.getType() != bRes.getType()) {
				boolean throwError = false;
				// if the resources are loaded by the same adapter type, then
				// the types should be the same
				if (a.getLoadAdapter().getClass() == b.getLoadAdapter().getClass()) {
					throwError = true;
				} // otherwise, it is possible that a generic adapter may not
				// know how a nested archive should be treated
				else if ((aRes.getType() == IArchiveResource.ARCHIVE_TYPE) || bRes.getType() == IArchiveResource.ARCHIVE_TYPE) {
					throwError = false;
				} else {
					throwError = true;
				}
				if (throwError) {
					Assert.assertEquals("IArchiveResource types differ for " + aRes.getPath(), aRes.getType(), bRes.getType());
				}
			}
			InputStream aIn = null;
			InputStream bIn = null;
			try {
				aIn = aRes.getInputStream();
				bIn = bRes.getInputStream();
				boolean isDirectory = aRes.getType() == IArchiveResource.DIRECTORY_TYPE;
				if (isDirectory) {
					Assert.assertNull(aIn);
					Assert.assertNull(bIn);
				} else {
					Assert.assertNotNull("Failed to get IO stream from A for " + aRes.getPath(), aIn);
					Assert.assertNotNull("Failed to get IO stream from B for " + bRes.getPath(), bIn);
					boolean compareArchives = false;
					String pathString = aRes.getPath().toString();
					for (int i = 0; i < ARCHIVE_EXTENSIONS.length && !compareArchives; i++) {
						if (pathString.endsWith(ARCHIVE_EXTENSIONS[i])) {
							compareArchives = true;
						}
					}

					if (compareArchives) {
						compareArchives(a.getNestedArchive(aRes), b.getNestedArchive(bRes));
					} else if (!pathString.endsWith(".class")) {
						Assert.assertTrue("IO Streams are not the same for " + aRes.getPath(), compareStreams(aIn, bIn));
					}
				}
			} finally {
				if (aIn != null) {
					try {
						aIn.close();
					} finally {
						if (bIn != null) {
							bIn.close();
						}
					}
				}
			}
		}

		if (!bResources.isEmpty()) {
			StringBuffer error = new StringBuffer("IArchive B contains");
			for (int i = 0; i < bResources.size(); i++) {
				IArchiveResource bRes = bResources.get(i);
				error.append(" " + bRes.getPath());
			}
			Assert.fail(error.toString());
		}

	}

	public static boolean compareStreams(InputStream a, InputStream b) throws IOException {
		try {
			BufferedInputStream buffA = new BufferedInputStream(a);
			BufferedInputStream buffB = new BufferedInputStream(b);
			int buffSize = 1024;
			byte[] bytesA = new byte[buffSize];
			byte[] bytesB = new byte[buffSize];
			int aRead = -1;
			int bRead = -1;
			while (-1 != (aRead = buffA.read(bytesA))) {
				bRead = buffB.read(bytesB);
				if (aRead != bRead) {
					String aStr = new String(bytesA, 0, aRead);
					String bStr = new String(bytesB, 0, bRead);
					printCompareError(aStr, bStr);
					return false;
				}
				for (int i = 0; i < aRead - 1; i++) {
					if (bytesA[i] != bytesB[i]) {
						String aStr = new String(bytesA, 0, aRead);
						String bStr = new String(bytesB, 0, bRead);
						printCompareError(aStr, bStr);
						return false;
					}
				}
			}

			return true;
		} finally {
			try {
				a.close();
			} finally {
				b.close();
			}
		}
	}

	private static void printCompareError(String aStr, String bStr) {
		aStr = aStr.replaceAll("\n", "<LF>");
		bStr = bStr.replaceAll("\n", "<LF>");
		aStr = aStr.replaceAll("\r", "<CR>");
		bStr = bStr.replaceAll("\r", "<CR>");
		aStr = aStr.replaceAll(" ", "<SP>");
		bStr = bStr.replaceAll(" ", "<SP>");
		System.err.println("A=[" + aStr + "]");
		System.err.println("B=[" + bStr + "]");
	}

}
