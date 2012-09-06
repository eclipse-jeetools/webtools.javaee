/*******************************************************************************
 * Copyright (c) 2001, 2010 IBM Corporation, Red Hat, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.internal.modulecore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jem.util.emf.workbench.WorkbenchByteArrayOutputStream;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;

import com.ibm.icu.util.StringTokenizer;

public class ManifestUtilities {
	public static final String MANIFEST_HEADER = "Manifest-Version: 1.0\r\nClass-Path: \r\n\r\n"; //$NON-NLS-1$
	public static void createManifestFile(IFile file) throws CoreException, IOException {
		try {
			WorkbenchByteArrayOutputStream out = new WorkbenchByteArrayOutputStream(file);
			out.write(MANIFEST_HEADER.getBytes());
			out.close();
		} catch (IOException ioe) {
			throw ioe;
		}
	}
	
	public static String[] getTokens(String aString) {
		return getTokens(aString, null);
	}

	public static String[] getTokens(String aString, String delimiter) {
		StringTokenizer tok = (delimiter == null) ? new StringTokenizer(aString) : new StringTokenizer(aString, delimiter);
		int size = tok.countTokens();
		String[] tokens = new String[size];
		for (int i = 0; i < size && tok.hasMoreTokens(); i++) {
			tokens[i] = tok.nextToken();
		}
		return tokens;
	}

	/**
	 * getValueIgnoreKeyCase method comment.
	 */
	public static java.lang.String getValueIgnoreKeyCase(java.lang.String key, java.util.jar.Attributes attr) {
		Iterator keysAndValues = attr.entrySet().iterator();
		while (keysAndValues.hasNext()) {
			Map.Entry entry = (Map.Entry) keysAndValues.next();
			String entryKey = entry.getKey().toString();
			if (entryKey.equalsIgnoreCase(key))
				return entry.getValue() == null ? null : entry.getValue().toString();
		}
		return null;
	}

	public static ArchiveManifest getManifest(IVirtualComponent component, IPath manifestPath) {
		if( !component.isBinary() )
			return getNonBinaryComponentManifest(component, manifestPath);
		return getBinaryComponentManifest(component, manifestPath);
	}
	
	public static ArchiveManifest getBinaryComponentManifest(IVirtualComponent component, IPath manifestPath) {
		java.io.File file = (File)component.getAdapter(File.class);
		if( file != null && file.exists()) {
			ArchiveManifest manifest = readBinaryManifest(file, manifestPath);
			return manifest;
		}
		return null;
	}

	public static ArchiveManifest getManifest(IFile f) {
		File f2 = f.getLocation().toFile();
		return getManifest(f2);
	}
	
	public static ArchiveManifest getManifest(File f) {
		if( f != null && f.exists()) {
				InputStream in;
				try {
					in = new FileInputStream(f);
					ArchiveManifest manifest = new ArchiveManifestImpl(in);
					return manifest;
				} catch (FileNotFoundException e) {
				} catch (IOException e) {
				}
		}
		return null;
	}
	
	public static void writeManifest(IFile aFile, ArchiveManifest manifest) throws java.io.IOException {
		OutputStream out = new WorkbenchByteArrayOutputStream(aFile);
		manifest.writeSplittingClasspath(out);
		out.close();
	}

	
	public static ArchiveManifest getNonBinaryComponentManifest(IVirtualComponent component, IPath manifestPath) {
		try {
			if(!component.isBinary()){
				IVirtualFile vManifest = component.getRootFolder().getFile(manifestPath);
				if (vManifest.exists()) {
					IFile manifestFile = vManifest.getUnderlyingFile();
					InputStream in = null;
					try {
						in = manifestFile.getContents();
						ArchiveManifest manifest = new ArchiveManifestImpl(in);
						return manifest;
					} finally {
						if (in != null) {
							in.close();
							in = null;
						}
					}
				}
			} 
		} catch( IOException ioe ) {
		} catch(CoreException ce) {
		}
		return null;
	}
	
	
	public static String[] getManifestClasspath(IVirtualComponent component, IPath manifestPath)  {
		ArchiveManifest mf = getManifest(component, manifestPath);
		if( mf != null ) 
			return mf.getClassPathTokenized();
		return new String[]{};
	}
	
	public static ArchiveManifest readBinaryManifest(File file, IPath manifestPath) {
		ArchiveManifest manifest = null;
		ZipFile zipFile = null;
		if( file != null ) {
			try {
				zipFile = ManifestUtilities.newZipFile(file);
				ZipEntry entry = zipFile.getEntry(manifestPath.toString());
				if( entry != null ) {
					InputStream entryStream = getInputstreamForZipEntry(zipFile, manifestPath.toString());
					manifest = new ArchiveManifestImpl(entryStream);
					zipFile.close();
				}
			} catch( IOException ioe) {
				if( zipFile != null ) {
					try {
						zipFile.close();
					} catch( IOException ioe2) {}
				}
			}
		}
		return manifest;
	}
	
	public static InputStream getInputstreamForZipEntry(ZipFile zipFile, String uri) throws IOException {
		try {
			ZipEntry entry = zipFile.getEntry(uri);
			if (entry == null) {
				// this is a hack, but zip files are sensitive to the difference
				// between '/' and '\\'
				// so the hack is to try all combinations to see if any exist
				char[] chars = uri.toCharArray();
				int[] slashIndices = new int[chars.length];
				int slashCount = 0;
				for (int i = 0; i < uri.length(); i++) {
					if (chars[i] == '/' || chars[i] == '\\') {
						slashIndices[slashCount] = i;
						slashCount++;
					}
				}
				int slashPow = (int) Math.pow(2, slashCount);
				boolean foundIt = false;
				for (int i = 0; i < slashPow && !foundIt; i++) {
					for (int j = 0; j < slashCount; j++) {
						if ((i >> j & 1) == 1) {
							chars[slashIndices[j]] = '/';
						} else {
							chars[slashIndices[j]] = '\\';
						}
					}
					entry = zipFile.getEntry(new String(chars));
					if (entry != null) {
						foundIt = true;
					}
				}
				if (entry == null) {
					Exception del = new FileNotFoundException(uri);
					throw new IOException(del.toString());
				}
			}
			return new java.io.BufferedInputStream(zipFile.getInputStream(entry));
		} catch (IllegalStateException zipClosed) {
			throw new IOException(zipClosed.toString());
		}
	}
	
	public static String[] getNonBinaryComponentManifestClasspath(IVirtualComponent component, IPath manifestPath)
			throws IOException, CoreException {
		String[] manifestClasspath = null;
		if(!component.isBinary()){
			IVirtualFile vManifest = component.getRootFolder().getFile(manifestPath);
			if (vManifest.exists()) {
				IFile manifestFile = vManifest.getUnderlyingFile();
				InputStream in = null;
				try {
					in = manifestFile.getContents();
					ArchiveManifest manifest = new ArchiveManifestImpl(in);
					manifestClasspath = manifest.getClassPathTokenized();
				} finally {
					if (in != null) {
						in.close();
						in = null;
					}
				}
			}
		} 
		return manifestClasspath;
	}

	public static ZipFile newZipFile(String fileName)throws ZipException, IOException {
		return ManifestUtilities.newZipFile(new File(fileName), ZipFile.OPEN_READ);
	}
	public static ZipFile newZipFile(File aFile)throws ZipException, IOException {
		return ManifestUtilities.newZipFile(aFile, ZipFile.OPEN_READ);
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
		return new ZipFile(aFile, mode){
			Collection <InputStream> openStreams = null;
			
			@Override
			public InputStream getInputStream(ZipEntry entry) throws IOException {
				InputStream in = super.getInputStream(entry);
				if(in != null){
					if(openStreams == null){
						openStreams = new ArrayList<InputStream>();
					}
					openStreams.add(in);
				}
				return in;
			}
			
			@Override
			public void close() throws IOException {
				closeOpenStreams();
				super.close();
			}

			private void closeOpenStreams() {
				if(openStreams != null){
					for (Iterator iterator = openStreams.iterator(); iterator.hasNext();) {
						InputStream in = (InputStream) iterator.next();
						try {
							in.close();
						} catch (IOException e) {
							org.eclipse.jem.util.logger.proxy.Logger.getLogger().logWarning(e);
						}
						iterator.remove();
					}
				}
			}
		};
	}
	
	public static ArchiveManifest readManifest(IFile aFile) {
		InputStream in = null;
		try {
			if (aFile == null || !aFile.exists())
				return null;
			in = aFile.getContents();
			return new ArchiveManifestImpl(in);
		} catch (Exception ex) {
			// TODO J2EEPlugin.logError(ex);
			return null;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException weTried) {
				}
			}
		}
	}

	private static ConcurrentHashMap<String, String> manifestClasspaths = new ConcurrentHashMap<String, String>();

	/**
	 * Generates new MANIFEST.MF with a dynamically updated classpath that is written to the specified
	 * output stream.
	 * @param manifestFile The current MANIFEST.MF file.
	 * @param dynamicURIs Is List of URIs to dynamically add to the manifest classpath.
	 * @param outputStream Stream to which the modified entry should be written.
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void updateManifestClasspath(final IFile manifestFile, final List dynamicURIs, final OutputStream outputStream) throws IOException, FileNotFoundException {
		updateManifestClasspathImpl(manifestFile, dynamicURIs, null, outputStream);
    }
	
	/**
	 * Generates new MANIFEST.MF with a dynamically updated classpath that is written to the specified
	 * output stream.
	 * @param manifestFile The current MANIFEST.MF file.
	 * @param dynamicURIs Is List of URIs to dynamically add to the manifest classpath.
	 * @param outputFile File to which the modified entry should be written.
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void updateManifestClasspath(final IFile manifestFile, final List dynamicURIs, final File outputFile) throws IOException, FileNotFoundException {
		updateManifestClasspathImpl(manifestFile, dynamicURIs, outputFile, null);
    }

	/**
	 * Generates new MANIFEST.MF with a dynamically updated classpath that is written to the specified
	 * file or output stream, with the stream taking precedence.
	 * @param manifestFile The current MANIFEST.MF file.
	 * @param dynamicURIs Is List of URIs to dynamically add to the manifest classpath.
	 * @param outputFile File to which the modified entry should be written.
	 * @param OutputStream stream Stream to which the modified entry should be written. If not null,
	 * the stream will be written and the outputFile ignored.
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static void updateManifestClasspathImpl(final IFile manifestFile, final List dynamicURIs, final File outputFile, final OutputStream stream) throws IOException, FileNotFoundException {
		
		OutputStream outputStream = stream; 
        try {
        	InputStream in = null;
        	ArchiveManifest manifest = null;
        	try {
        		in = manifestFile.getContents();
        		manifest = new ArchiveManifestImpl(in);
        	} catch (CoreException ce) {
        		throw new IOException(ce.getLocalizedMessage());
        	} finally {
        		if (in != null) {
        			try {
        				in.close();
        				in = null;
        			} catch (IOException e) {
						org.eclipse.jem.util.logger.proxy.Logger.getLogger().logWarning(e);
        			}
        		}
        	}
        	final String[] manifestClasspath = manifest.getClassPathTokenized();
        	final List updatedCP = new ArrayList();
        	for (int i = 0; i < manifestClasspath.length; i++) {
        		updatedCP.add(manifestClasspath[i]);
        	}
        	// update manifest classpath to include dynamic entries
        	for (int j = 0; j < dynamicURIs.size(); j++) {
        		final String containerURI = (String) dynamicURIs.get(j);
        		// need to check existing entries to ensure it doesn't are exist on the classpath
        		boolean exists = false;
        		for (int i = 0; i < manifestClasspath.length; i++) {
        			if (manifestClasspath[i].equals(containerURI)) {
        				exists = true;
        				break;
        			}
        		}
        		if (!exists) {
        			updatedCP.add(containerURI);
        		}
        	}
        	final StringBuffer cpBuffer = new StringBuffer();
        	boolean first = true;
        	for (int j = 0; j < updatedCP.size(); j++) {
        		if (!first) {
        			cpBuffer.append(" "); //$NON-NLS-1$
        		} else {
        			first = false;
        		}
        		cpBuffer.append((String) updatedCP.get(j));
        	}
        	String cp = cpBuffer.toString();
        	// If we have an output stream, always write to the stream 
        	if (outputStream != null) {
            	manifest.setClassPath(cp);
            	manifest.write(outputStream);
            	outputStream.flush();
        	}
        	// Else, without an output stream, conditionally update the specified file
        	else {
        		// To avoid making the internally generated manifest file appear to projects
        		// as a perpetually modified resource, check if the file needs to change.
        		// This checking helps with the usefulness of "delta" deployment with
        		// utility projects.
            	String manifestPath = manifestFile.getFullPath().toString();
            	String priorClasspath = manifestClasspaths.get(manifestPath);
            	if (priorClasspath == null || !priorClasspath.equals(cp) || !outputFile.exists()) {
                	manifestClasspaths.put(manifestPath, cp);
                	manifest.setClassPath(cp);
                	outputStream = new FileOutputStream(outputFile);
                	manifest.write(outputStream);
                	outputStream.flush();
            	}
        	}
        } finally {
        	if (outputStream != null) {
        		outputStream.close();
        	}
        }
    }

	
}
