/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.taglib;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.internal.web.operations.ProjectSupportResourceHandler;
import org.eclipse.jst.j2ee.internal.web.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.web.taglib.ITaglibInfo;
import org.eclipse.wst.common.modulecore.ModuleCore;


public class TaglibInfo implements ITaglibInfo, Cloneable {
	private IPath location;
	private String uri;
	private IPath tldLocation;
	private String prefix;
	private IProject project;

	private boolean isInJar = false;
	private boolean isWebXMLEntry = false;
	private boolean isURIFromTLD = false;
	private boolean isLibModule = false;
	private boolean isValid = true;

	private boolean isServerContribution = false;
	private boolean isDirectoryEntry = false;

	public TaglibInfo(IProject project, String uri, IPath tldLocation) {
		this.project = project;
		this.uri = uri;
		this.location = tldLocation;
		this.tldLocation = tldLocation;
		this.isInJar = false;
	}

	public TaglibInfo(IProject project, String uri, IPath jarLocation, IPath tldLocation) {
		this.project = project;
		this.uri = uri;
		this.location = jarLocation;
		this.isInJar = true;
		this.tldLocation = tldLocation;
	}


	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
			System.err.println(ProjectSupportResourceHandler.getString("Cannot_clone_TaglibInfo_1_EXC_")); //$NON-NLS-1$
		}
		return o;
	}

	/*
	 * @see ITaglibInfo#getLocation()
	 */
	public IPath getLocation() {
		return this.location;
	}

	/*
	 * @see ITaglibInfo#getURI()
	 */
	public String getURI() {
		return this.uri;
	}

	/*
	 * @see ITaglibInfo#isInJar()
	 */
	public boolean isInJar() {
		return this.isInJar;
	}

	/*
	 * @see ITaglibInfo#getTLDLocation()
	 */
	public IPath getTLDLocation() {
		return this.tldLocation;
	}


	/**
	 * Sets the location of the TLD file.
	 * 
	 * @param tldLocation
	 *            The tldLocation to set
	 */
	public void setTLDLocation(IPath tldLocation) {
		this.tldLocation = tldLocation;
	}

	/*
	 * @see Object#toString()
	 */
	public String toString() {
		if (this.isInJar)
			return "TaglibInfo(JAR" + (isWebXMLEntry() ? ",WEB.XML: " : ": ") + getURI() + ", " + this.location + ", " + this.tldLocation + ", " + isURIFromTLD() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$

		return "TaglibInfo(TLD" + (isWebXMLEntry() ? ",WEB.XML: " : ": ") + getURI() + ", " + this.location + ", " + isURIFromTLD() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	}

	/*
	 * @see ITaglibInfo#isWebXMLEntry()
	 */
	public boolean isWebXMLEntry() {
		return this.isWebXMLEntry;
	}

	/*
	 * @see Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (!(object instanceof ITaglibInfo))
			return false;
		ITaglibInfo taglibInfo = (ITaglibInfo) object;
		return this.getLocation().equals(taglibInfo.getLocation()) && this.getTLDLocation().equals(taglibInfo.getTLDLocation()) && this.getURI().equals(taglibInfo.getURI()) && this.isWebXMLEntry() == taglibInfo.isWebXMLEntry();
	}

	/**
	 * Sets the isWebXMLEntry.
	 * 
	 * @param isWebXMLEntry
	 *            The isWebXMLEntry to set
	 */
	public void setIsWebXMLEntry(boolean isWebXMLEntry) {
		this.isWebXMLEntry = isWebXMLEntry;
	}

	/*
	 * @see ITaglibInfo#setIsValid()
	 */
	public void setIsValid(boolean validity) {
		this.isValid = validity;
	}

	/*
	 * @see ITaglibInfo#getPrefix()
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/*
	 * @see ITaglibInfo#getSourceProject()
	 */
	public IProject getSourceProject() {
		return this.project;
	}

	/*
	 * @see ITaglibInfo#setPrefix(String)
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setPrefix(TLDDigester digester) {
		if (digester != null) {
			String aprefix = digester.getShortName();
			if (aprefix != null)
				setPrefix(aprefix);
		}
	}



	/*
	 * @see ITaglibInfo#getTLDStream()
	 */
	public InputStream getTLDStream() throws ZipException, IOException, CoreException {
		File javaIOFile = null;
		IFile file = null;
		boolean bIsInJar = false;
		if (isLibModule()) {
			file = (IFile) this.project.findMember(getTLDLocation());
			javaIOFile = file.getLocation().toFile();
		} else if (isServerContribution()) {
			javaIOFile = getLocation().toFile();
			bIsInJar = isInJar();
		} else {
			file = (IFile) this.project.findMember(getLocation());
			if (file != null) {
				javaIOFile = file.getLocation().toFile();
			}

			// for webxml entries try relative to web.xml
			if (javaIOFile == null || !javaIOFile.exists()) {
				if (isWebXMLEntry()) {
					IPath taglibPath = getLocation();
					WebArtifactEdit webEdit = null;
					if (!taglibPath.isAbsolute()) {
						try {
							webEdit = (WebArtifactEdit) ModuleCore.getFirstArtifactEditForRead(project);
							IContainer webInfContainer = webEdit.getWebInfFolder();
							IResource resolvedResource = webInfContainer.findMember(taglibPath);
							if (resolvedResource instanceof IFile) {
								file = (IFile) resolvedResource;
								javaIOFile = file.getLocation().toFile();
							}
						} finally {
							if (webEdit !=null) 
								webEdit.dispose();
						}
					}
				}
			}
			bIsInJar = isInJar();
		}

		if (bIsInJar) {
			if (javaIOFile != null) {
				ZipFile zFile = new ZipFile(javaIOFile);
				try {
					ZipEntry entry = zFile.getEntry(getTLDLocation().toString());
					if (entry != null) {
						InputStream zipInputStream = zFile.getInputStream(entry);
						InputStream retStream = bufferInputStream(zipInputStream);
						zipInputStream.close();
						return retStream;
					}
				} finally {
					if (zFile != null)
						zFile.close();
				}
			}
		} else {
			if (file != null)
				return file.getContents();
			// there is no server contributed tld, only jars
			//			else if(javaIOFile != null)
			//				return new FileInputStream(javaIOFile);
		}
		return null;
	}

	protected InputStream bufferInputStream(InputStream is) {
		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			if (is != null) {
				int available = is.available();
				int totalRead = 0;
				byte[] read = new byte[available];
				while (totalRead < available) {
					int numRead = is.read(read, 0, available);
					output.write(read, 0, numRead);
					totalRead += numRead;
				}
			}
			output.close();
			return new ByteArrayInputStream(output.toByteArray());
		} catch (IOException e) {
			//Do nothing
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					//Do nothing
				}
			}
		}
		return null;
	}

	public String getTLDString() {
		InputStream is = null;
		try {
			is = getTLDStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			if (is != null) {
				int available = is.available();
				byte[] read = new byte[available];
				int numRead = is.read(read);
				output.write(read, 0, numRead);
			}
			output.close();
			return output.toString();
		} catch (ZipException e) {
			//Do nothing
		} catch (IOException e) {
			//Do nothing
		} catch (CoreException e) {
			//Do nothing
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					//Do nothing
				}
			}
		}
		return ProjectSupportResourceHandler.getString("Could_not_read_TLD_15"); //$NON-NLS-1$
	}

	/**
	 * @see ITaglibInfo#isURIFromTLD()
	 */
	public boolean isURIFromTLD() {
		return this.isURIFromTLD;
	}

	/**
	 * Sets the isURIFromTLD.
	 * 
	 * @param isURIFromTLD
	 *            The isURIFromTLD to set
	 */
	public void setIsURIFromTLD(boolean isURIFromTLD) {
		this.isURIFromTLD = isURIFromTLD;
	}

	/**
	 * @see ITaglibInfo#isLibModule()
	 */
	public boolean isLibModule() {
		return this.isLibModule;
	}

	/**
	 * Sets the isLibModule. flag
	 * 
	 * @param isLibModule
	 *            The isLibModule to set
	 */
	public void setIsLibModule(boolean isLibModule) {
		this.isLibModule = isLibModule;
	}

	/**
	 * @see ITaglibInfo#isValid()
	 */
	public boolean isValid() {
		return this.isValid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.taglib.ITaglibInfo#isServerContribution()
	 */
	public boolean isServerContribution() {
		return this.isServerContribution;
	}

	public void setServerContribution(boolean isServerContribution) {
		this.isServerContribution = isServerContribution;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.taglib.ITaglibInfo#isDirectoryEntry()
	 */
	public boolean isDirectoryEntry() {
		return this.isDirectoryEntry;
	}

	/**
	 * @param isDirectoryEntry
	 *            The isDirectoryEntry to set.
	 */
	public void setDirectoryEntry(boolean isDirectoryEntry) {
		this.isDirectoryEntry = isDirectoryEntry;
	}
}