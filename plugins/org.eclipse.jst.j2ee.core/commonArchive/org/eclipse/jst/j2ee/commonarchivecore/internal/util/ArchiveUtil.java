/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.util;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.URIMappingRegistryImpl;
import org.eclipse.jem.java.JavaURL;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.EmptyResourceException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveURIConverterImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.internal.xml.GeneralXmlDocumentReader;
import org.eclipse.jst.j2ee.internal.xml.XmlDocumentReader;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;


/**
 * This is a utility class to hold helper methods common to multiple classes in the archive support
 * packages
 */
public class ArchiveUtil {
	public static final String DOT_CLASS = ".class"; //$NON-NLS-1$

	public static final String DOT_JAVA = ".java"; //$NON-NLS-1$

	public static final String DOT_SQLJ = ".sqlj"; //$NON-NLS-1$
	
	public static final String DOT_JSP = ".jsp"; //$NON-NLS-1$

	protected static boolean isRuntime = false;

	/**
	 * Flag to indicate whether the temp directory should be used for extracting nested jars for
	 * read; defaults to true
	 */
	protected static boolean shouldUseTempDirectoryForRead = true;

	/**
	 * Settable name for the temp directory; must resolve to a valid file on the file system; if not
	 * specified, defaults to the system temp directory
	 */
	protected static String tempDirectoryName;

	protected static java.io.File tempDirectory;

	public static String classNameToJavaUri(String className) {
		return className.replace('.', '/').concat(DOT_JAVA);
	}

	public static String classNameToUri(String className) {
		return className.replace('.', '/').concat(DOT_CLASS);
	}

	/**
	 * For a given uri of a .class file, derive the uri of the .java file; takes into consideration
	 * inner classes by splitting on the first occurrence of '$'
	 * 
	 * @return String a uri or null if the input is null or doesn't end with ".class"
	 */
	public static String classUriToJavaUri(String classUri) {
		if (classUri == null || !classUri.endsWith(DOT_CLASS))
			return null;

		String truncated = truncateIgnoreCase(classUri, DOT_CLASS);
		StringTokenizer tok = new StringTokenizer(truncated, "$"); //$NON-NLS-1$
		return tok.nextToken().concat(DOT_JAVA);
	}

	/**
	 * Concatenates the two strings with a separator, if necessary
	 */
	public static String concatUri(String directoryname, String filename, char separator) {
		String uri = directoryname;
		if (!directoryname.endsWith(separator + "") && !filename.startsWith(separator + "")) //$NON-NLS-2$//$NON-NLS-1$
			uri = uri + separator;
		return uri + filename;
	}

	/**
	 * Copy all the data from the input stream to the output stream up until the first end of file
	 * character, and close the two streams
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

	public static File createTempDirectory(String baseName, File parentDirectory) throws IOException {

		File tempFile = createTempFile(baseName, parentDirectory);
		tempFile.delete();
		tempFile.mkdir();
		return tempFile;
	}

	public static File createTempFile(String baseName) throws IOException {
		return createTempFile(baseName, getTempDirectory());
	}

	public static File createTempFile(String baseName, File directory) throws IOException {
		String fileName = getFileNameTail(baseName);
		if (fileName.length() < 3) {
			fileName = "WSTMP" + fileName; //$NON-NLS-1$
		}
		File tempFile = File.createTempFile(fileName, null, directory);
		return tempFile;
	}

	/**
	 * deletes a file from the file system; for directories, recurse the subdirectories and delete
	 * them as well
	 * 
	 * @return true if successful; false if any file or sub file could not be deleted
	 */
	public static boolean delete(File aFile) {
		if (aFile.isDirectory()) {
			File[] files = aFile.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (!delete(files[i]))
					return false;
			}
		}
		return aFile.delete();
	}

	/**
	 * returns a list of all files, recursive, that can't be written
	 */
	public static List getWriteProtectedFiles(File aFile, List aList) {
		if (aList == null)
			aList = new ArrayList();
		if (aFile.exists() && !aFile.canWrite())
			aList.add(aFile);
		if (aFile.isDirectory()) {
			File[] files = aFile.listFiles();
			for (int i = 0; i < files.length; i++) {
				getWriteProtectedFiles(files[i], aList);
			}
		}
		return aList;
	}

	/**
	 * Leverage the java.io.File apis to resolve things like "./xxx" and "../xxx" into uris of
	 * entries in the ear file
	 * 
	 * @param classpathEntry -
	 *            a classpath entry from the manifest of
	 * @anArchive
	 * @param anArchive -
	 *            the archive to which the dependent jar is relative
	 * 
	 * @return a cananonicalized relative uri of an entry in an ear file representing the dependent
	 *         jar
	 */
	public static String deriveEARRelativeURI(String classpathEntry, Archive anArchive) {
		return deriveEARRelativeURI(classpathEntry, anArchive.getURI());
	}

	/**
	 * Leverage the java.io.File apis to resolve things like "./xxx" and "../xxx" into uris of
	 * entries in the ear file
	 * 
	 * @param classpathEntry -
	 *            a classpath entry from the manifest of an archive
	 * @param String -
	 *            the uri of the archive to which the dependent jar is relative
	 * 
	 * @return a cananonicalized relative uri of an entry in an ear file representing the dependent
	 *         jar
	 */
	public static String deriveEARRelativeURI(String classpathEntry, String archiveUri) {
		try {
			String parent = getFileNameParent(archiveUri);
			if (parent == null || parent.equals("")) //$NON-NLS-1$
				parent = "."; //$NON-NLS-1$
			String workingDir = new java.io.File(".").getCanonicalPath(); //$NON-NLS-1$

			String resolvedPath = new java.io.File(parent, classpathEntry).getCanonicalPath();
			if (!resolvedPath.startsWith(workingDir))
				return null;
			if (resolvedPath.equals(workingDir))
				return null;
			int start = workingDir.endsWith(java.io.File.separator) ? workingDir.length() : workingDir.length() + 1;
			return resolvedPath.substring(start, resolvedPath.length()).replace(java.io.File.separatorChar, '/');
		} catch (java.io.IOException ex) {
			//getCanonicalPath could throw this
			return null;
		}
	}

	/**
	 * Return "" if there is no extension
	 */
	public static String getFileNameExtension(String uri) {

		if (uri != null) {
			int index = uri.lastIndexOf("."); //$NON-NLS-1$
			if (index != -1)
				return uri.substring(index + 1);
		}
		return ""; //$NON-NLS-1$
	}

	/**
	 * Returns the directory from the uri, or the truncation of the segment after the last
	 * occurrence of a separator
	 */
	public static String getFileNameParent(String uri) {
		if (uri==null)
			return ""; //$NON-NLS-1$
		String tempURI = uri.replace('\\', '/');
		while (tempURI.endsWith("/")) //$NON-NLS-1$
			tempURI = tempURI.substring(0, tempURI.length() - 1);
		int lastIndex = tempURI.lastIndexOf('/');
		if (lastIndex == -1)
			return ""; //$NON-NLS-1$
		else if (lastIndex == 0)
			return "/"; //$NON-NLS-1$
		return uri.substring(0, lastIndex);
	}

	/**
	 * Returns the filename from the uri, or the segment after the last occurrence of a separator
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

	/**
	 * For the given object in the deployment descriptor, return the owning module file; for
	 * example, retrieve the EJBJarFile that owns the EJBJar. This works even for alts
	 */
	public static ModuleFile getModuleFile(EObject ddObject) {
		Resource res = ddObject.eResource();
		if (res == null)
			return null;

		Archive archive = getArchive(res);
		if (archive == null || !archive.isModuleFile())
			return null;

		if (!(res instanceof XMLResource))
			return null;

		XMLResource xmlRes = (XMLResource) res;
		if (xmlRes.isAlt())
			return getModuleFileUsingAltDD((EARFile) archive, res);

		return (ModuleFile) archive;
	}

	protected static ModuleFile getModuleFileUsingAltDD(EARFile ear, Resource res) {
		if (res == null)
			return null;

		String uri = res.getURI().toString();
		Module m = ear.getDeploymentDescriptor().getModuleHavingAltDD(uri);
		if (m == null)
			return null;
		ModuleRef ref = ear.getModuleRef(m);
		if (ref == null)
			return null;

		return ref.getModuleFile();
	}

	public static String getModuleFileTypeName(int moduleType) {
		switch (moduleType) {
			case XMLResource.APPLICATION_TYPE :
				return CommonArchiveResourceHandler.A_Application_file; //$NON-NLS-1$
			case XMLResource.APP_CLIENT_TYPE :
				return CommonArchiveResourceHandler.An_Application_Client_JAR_file; //$NON-NLS-1$
			case XMLResource.EJB_TYPE :
				return CommonArchiveResourceHandler.An_EJB_JAR_file; //$NON-NLS-1$
			case XMLResource.WEB_APP_TYPE :
				return CommonArchiveResourceHandler.A_WAR_file; //$NON-NLS-1$
			case XMLResource.RAR_TYPE :
				return CommonArchiveResourceHandler.A_RAR_file; //$NON-NLS-1$
		}
		return null;
	}

	/**
	 * For the given resource, return the owning module file; for example, retrieve the EJBJarFile
	 * that owns the EJBJar. In the case of alts, this returns the EARFile and not the module file.
	 * This method is internal; clients should use {@link #getModuleFile(EObject)}
	 */
	public static Archive getArchive(Resource res) {
		if (res == null)
			return null;

		ResourceSet set = res.getResourceSet();
		if (set == null)
			return null;

		URIConverter conv = set.getURIConverter();
		if (conv == null || !(conv instanceof ArchiveURIConverterImpl))
			return null;

		return ((ArchiveURIConverterImpl) conv).getArchive();
	}

	/**
	 * Concatenates the two strings and ensures the correct separator is used in the path
	 */
	public static String getOSUri(String uri) {
		char sep = File.separatorChar;
		if (sep != '/')
			return uri.replace('/', sep);
		return uri.replace('\\', sep);
	}

	/**
	 * Concatenates the two strings and ensures the correct separator is used in the path
	 */
	public static String getOSUri(String directoryname, String filename) {
		String osDirName = getOSUri(directoryname);
		String osFileName = getOSUri(filename);
		return concatUri(osDirName, osFileName, File.separatorChar);
	}

	/**
	 * Returns the first element in the extent of the resource
	 * 
	 * @throws EmptyResourceException -
	 *             thrown if the extent is empty
	 */
	public static EObject getRoot(Resource aResource) throws EmptyResourceException {
		EList extent = aResource.getContents();
		if (extent.size() < 1) {
			throw new EmptyResourceException(aResource.getURI().toString());
		}
		return (EObject) extent.get(0);
	}

	public static java.io.File getTempDirectory() {
		return tempDirectory;
	}

	/**
	 * Insert the method's description here. Creation date: (12/19/00 1:16:48 PM)
	 * 
	 * @return java.lang.String
	 */
	public static java.lang.String getTempDirectoryName() {
		return tempDirectoryName;
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

	public static void inform(String message) {
		org.eclipse.jem.util.logger.proxy.Logger.getLogger().logInfo(message);
	}

	/**
	 * Read all the data from the input stream up until the first end of file character, add this
	 * data to a byte array, and close the input stream; returns the byte array
	 */
	public static byte[] inputStreamToBytes(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		copy(in, out);
		return out.toByteArray();
	}

	public static boolean isJavaResource(Resource aResource) {
		return JavaURL.isJavaURL(aResource.getURI().toString());
	}

	public static boolean isRegisteredURIMapping(Resource res) {
		return URIMappingRegistryImpl.INSTANCE.getURI(res.getURI()) != res.getURI();
	}

	public static boolean isPlatformMetaResource(Resource res) {
		URI uri = res.getURI();
		return "platform".equals(uri.scheme()) && "meta".equals(uri.segment(0)); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public static boolean isNullOrEmpty(String aString) {
		return aString == null || aString.trim().length() == 0;
	}

	public static boolean isRuntime() {
		return isRuntime;
	}

	/**
	 * Is it within the spec for the JAR or module file to reference the archive via the Manifest
	 * Class-Path?
	 */
	public static boolean isValidDependency(Archive referencedJAR, Archive referencingJAR) {
		//No other modules should reference wars
		if (referencedJAR.isWARFile())
			return false;

		if (referencedJAR == referencingJAR)
			return false;

		//Clients can reference all but the WARs, which we've already covered
		// above; WARs and EJB JARs
		//can reference all but WARs, above, or ApplicationClients
		return referencingJAR.isApplicationClientFile() || !referencedJAR.isApplicationClientFile();
	}

	/**
	 * Loads a properties file with the given uri from the given archive
	 * 
	 * @throws FileNotFoundException
	 *             if the file does not exist in the archive
	 * @throws IOException
	 *             if an IO exception occurs reading the properties
	 */
	public static Properties loadProperties(Archive archive, String uri) throws FileNotFoundException, IOException {
		InputStream in = null;
		try {
			in = archive.getInputStream(uri);
			Properties p = new Properties();
			p.load(in);
			return p;
		} finally {
			if (in != null)
				in.close();
		}
	}

	public static void setIsRuntime(boolean aBoolean) {
		isRuntime = aBoolean;
	}

	/**
	 *  
	 */
	public static void setShouldUseTempDirectoryForRead(boolean value) {
		shouldUseTempDirectoryForRead = value;
	}

	/**
	 * Insert the method's description here. Creation date: (12/19/00 1:26:28 PM)
	 * 
	 * @param newTempDirectory
	 *            java.io.File
	 */
	public static void setTempDirectory(java.io.File newTempDirectory) {
		tempDirectory = newTempDirectory;
		tempDirectoryName = newTempDirectory.getAbsolutePath();
	}

	/**
	 * Sets the temp directory to the specified path; creates it and sets it to be deleted on exit
	 * if it does not exist.
	 * 
	 * @param newTempDirectoryName
	 *            java.lang.String
	 * @throws ArchiveException
	 *             thrown if the directory does not exist and can not be created
	 */
	public static void setTempDirectoryName(java.lang.String newTempDirectoryName) throws ArchiveException {
		java.io.File dir = new java.io.File(newTempDirectoryName);
		if (!dir.exists()) {
			dir.mkdirs();
			if (!dir.exists())
				throw new ArchiveException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.make_temp_dir_EXC_, (new Object[]{newTempDirectoryName}))); //$NON-NLS-1$ = "Unable to make temp directory: "
			dir.deleteOnExit();
		}
		setTempDirectory(dir);
	}

	/**
	 * Insert the method's description here. Creation date: (12/19/00 1:08:20 PM)
	 * 
	 * @return boolean
	 */
	public static boolean shouldUseTempDirectoryForRead() {
		return shouldUseTempDirectoryForRead;
	}

	/**
	 * Convert the list of string elements to an array of file URLs; if an entry is relative, make
	 * it relative to the uri root
	 */
	public static java.net.URL[] toLocalURLs(List entries, String uriRoot) {
		List urls = new ArrayList();
		for (int i = 0; i < entries.size(); i++) {
			String pathEntry = (String) entries.get(i);
			java.io.File aFile = new java.io.File(pathEntry);
			if (!aFile.isAbsolute()) {
				pathEntry = getOSUri(uriRoot, pathEntry);
				aFile = new java.io.File(pathEntry);
			}
			if (!aFile.exists()) {
				//inform("Classpath element " + pathEntry + " does not
				// exist");
				continue;
			}
			if (aFile.isDirectory()) {
				if (!pathEntry.endsWith(java.io.File.separator)) {
					pathEntry = pathEntry + java.io.File.separatorChar;
				}
			} else {
				ZipFile zip = null;
				try {
					zip = new ZipFile(aFile);
				} catch (Exception ex) {
					inform(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.invalid_classpath_WARN_, (new Object[]{pathEntry}))); //$NON-NLS-1$ = "Classpath element "{0}" is not a directory or a Zip file"
					continue;
				} finally {
					if (zip != null)
						try {
							zip.close();
						} catch (IOException ignore) {
							//Ignore
						}
				}
			}
			try {
				aFile = new java.io.File(pathEntry);
				java.net.URL aUrl = aFile.toURL(); //$NON-NLS-1$
				urls.add(aUrl);
			} catch (java.net.MalformedURLException ex) {
				inform(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.invalid_cp_file_WARN_, (new Object[]{pathEntry}))); //$NON-NLS-1$ = "Classpath element "{0}" does not point to a local file"
			}
		}
		java.net.URL[] urlArray = new java.net.URL[urls.size()];
		return (java.net.URL[]) urls.toArray(urlArray);
	}

	/**
	 * Return a substring of the first parameter, starting at the end of the prefix
	 */
	public static String truncateFromFrontIgnoreCase(String aString, String leadingSubString) {
		if (!aString.toLowerCase().startsWith(leadingSubString.toLowerCase()))
			return aString;
		return aString.substring(leadingSubString.length(), aString.length());
	}

	/**
	 * Return a substring of the first parameter, up to the last index of the second
	 */
	public static String truncateIgnoreCase(String aString, String trailingSubString) {
		int index = aString.toLowerCase().lastIndexOf(trailingSubString.toLowerCase());
		if (index != -1)
			return aString.substring(0, index);
		return aString;
	}

	public static String uriToClassName(String uri) {
		return truncateIgnoreCase(uri.replace('/', '.'), DOT_CLASS);
	}

	public static boolean isJ2EE13FastCheck(ModuleFile moduleFile) {
		if (moduleFile == null)
			return false;
		else if (moduleFile.isRARFile())
			return true;
		InputStream in = null;
		String uri = moduleFile.getDeploymentDescriptorUri();
		try {
			in = moduleFile.getInputStream(uri);

			DocumentType docType = XmlDocumentReader.readDocumentType(in, uri);
			if (docType == null)
				return false;
			String[] ids = getJ2EE13PublicAndSystemIdFor(moduleFile);
			if (ids == null)
				return false;
			return ids[0].equals(docType.getPublicId()) && ids[1].equals(docType.getSystemId());
		} catch (IOException ex) {
			return false;
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException ex) {
				//Ignore
			}
		}

	}

	protected static String[] getJ2EE13PublicAndSystemIdFor(ModuleFile moduleFile) {
		if (moduleFile.isEARFile())
			return new String[]{J2EEConstants.APPLICATION_PUBLICID_1_3, J2EEConstants.APPLICATION_SYSTEMID_1_3};
		else if (moduleFile.isEJBJarFile())
			return new String[]{J2EEConstants.EJBJAR_PUBLICID_2_0, J2EEConstants.EJBJAR_SYSTEMID_2_0};
		else if (moduleFile.isApplicationClientFile())
			return new String[]{J2EEConstants.APP_CLIENT_PUBLICID_1_3, J2EEConstants.APP_CLIENT_SYSTEMID_1_3};
		else if (moduleFile.isWARFile())
			return new String[]{J2EEConstants.WEBAPP_PUBLICID_2_3, J2EEConstants.WEBAPP_SYSTEMID_2_3};
		else if (moduleFile.isRARFile())
			return new String[]{J2EEConstants.CONNECTOR_PUBLICID_1_0, J2EEConstants.CONNECTOR_SYSTEMID_1_0};
		else
			return null;
	}

	//TODO doing the parse with SAX would be faster
	public static int getFastSpecVersion(ModuleFile moduleFile) {
		InputStream in = null;
		String uri = moduleFile.getDeploymentDescriptorUri();
		try {
			in = moduleFile.getInputStream(uri);

			DocumentType docType = null;

			InputSource source = new InputSource(in);
			GeneralXmlDocumentReader aReader = new XmlDocumentReader(source);
			aReader.setValidate(false);
			Document aDocument = aReader.parseDocument();
			if (aDocument != null) {
				docType = aDocument.getDoctype();
			}
			int version = -1;
			if (null != docType) {
				String publicID = docType.getPublicId();
				String systemID = docType.getSystemId();
				if (publicID != null && systemID != null) {
					version = lookupVersion(moduleFile, publicID, systemID);
				}
			}
			if (version == -1) {
				String schemaName = null;
				String versionString = null;
				if (aDocument != null) {
					if (null == docType) {
						NamedNodeMap map = aDocument.getDocumentElement().getAttributes();
						if (null != map) {
							Node schemaNode = map.getNamedItem("xsi:schemaLocation"); //$NON-NLS-1$
							if (null != schemaNode) {
								schemaName = schemaNode.getNodeValue();
							}
							Node versionNode = map.getNamedItem("version");//$NON-NLS-1$
							if (null != versionNode) {
								versionString = versionNode.getNodeValue();
							}
						}
					}
				}
				if (null != schemaName) {
					version = lookupVersion(moduleFile, schemaName);
				}
				if (version == -1) {
					version = parseVersionString(moduleFile, versionString);
				}
			}

			return version;
		} catch (IOException ex) {
			return -1;
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException ex) {
				//Ignore
			}
		}
	}

	private static int parseVersionString(ModuleFile moduleFile, String versionAttr) {
		int version = -1;
		if (moduleFile.isEARFile()) {
			if (null == versionAttr) {
				version = J2EEVersionConstants.J2EE_1_4_ID;
			} else if (versionAttr.equals(J2EEVersionConstants.VERSION_1_2_TEXT)) {
				version = J2EEVersionConstants.J2EE_1_2_ID;
			} else if (versionAttr.equals(J2EEVersionConstants.VERSION_1_3_TEXT)) {
				version = J2EEVersionConstants.J2EE_1_3_ID;
			} else {
				version = J2EEVersionConstants.J2EE_1_4_ID;
			}
		} else if (moduleFile.isEJBJarFile()) {
			if (null == versionAttr) {
				version = J2EEVersionConstants.EJB_2_1_ID;
			} else if (versionAttr.equals(J2EEVersionConstants.VERSION_1_1_TEXT)) {
				version = J2EEVersionConstants.EJB_1_1_ID;
			} else if (versionAttr.equals(J2EEVersionConstants.VERSION_2_0_TEXT)) {
				version = J2EEVersionConstants.EJB_2_0_ID;
			} else {
				version = J2EEVersionConstants.EJB_2_1_ID;
			}
		} else if (moduleFile.isApplicationClientFile()) {
			if (null == versionAttr) {
				version = J2EEVersionConstants.J2EE_1_4_ID;
			} else if (versionAttr.equals(J2EEVersionConstants.VERSION_1_2_TEXT)) {
				version = J2EEVersionConstants.J2EE_1_2_ID;
			} else if (versionAttr.equals(J2EEVersionConstants.VERSION_1_3_TEXT)) {
				version = J2EEVersionConstants.J2EE_1_3_ID;
			} else {
				version = J2EEVersionConstants.J2EE_1_4_ID;
			}
		} else if (moduleFile.isWARFile()) {
			if (null == versionAttr) {
				version = J2EEVersionConstants.WEB_2_4_ID;
			} else if (versionAttr.equals(J2EEVersionConstants.VERSION_2_2_TEXT)) {
				version = J2EEVersionConstants.WEB_2_2_ID;
			} else if (versionAttr.equals(J2EEVersionConstants.VERSION_2_3_TEXT)) {
				version = J2EEVersionConstants.WEB_2_3_ID;
			} else {
				version = J2EEVersionConstants.WEB_2_4_ID;
			}
		} else if (moduleFile.isRARFile()) {
			if (null == versionAttr) {
				version = J2EEVersionConstants.JCA_1_5_ID;
			} else if (versionAttr.equals(J2EEVersionConstants.VERSION_1_0_TEXT)) {
				version = J2EEVersionConstants.JCA_1_0_ID;
			} else {
				version = J2EEVersionConstants.JCA_1_5_ID;
			}
		}
		return version;

	}

	private static int lookupVersion(ModuleFile moduleFile, String schemaName) {
		int version = -1;
		if (moduleFile.isEARFile()) {
			if (schemaName.equals(J2EEConstants.APPLICATION_SCHEMA_1_4)) {
				version = J2EEVersionConstants.J2EE_1_4_ID;
			}
		} else if (moduleFile.isEJBJarFile()) {
			if ((schemaName.indexOf(J2EEConstants.EJBJAR_SCHEMA_2_1_NS)) != -1) {
				version = J2EEVersionConstants.EJB_2_1_ID;
			}
		} else if (moduleFile.isApplicationClientFile()) {
			if (schemaName.equals(J2EEConstants.APP_CLIENT_SCHEMA_1_4)) {
				version = J2EEVersionConstants.J2EE_1_4_ID;
			}
		} else if (moduleFile.isWARFile()) {
			if (schemaName.equals(J2EEConstants.WEBAPP_SCHEMA_2_4)) {
				version = J2EEVersionConstants.WEB_2_4_ID;
			}
		} else if (moduleFile.isRARFile()) {
			if (schemaName.equals(J2EEConstants.CONNECTOR_SCHEMA_1_5)) {
				version = J2EEVersionConstants.JCA_1_5_ID;
			}
		}
		return version;
	}

	private static int lookupVersion(ModuleFile moduleFile, String publicID, String systemID) {
		int version = -1;
		if (moduleFile.isEARFile()) {
			if (publicID.equals(J2EEConstants.APPLICATION_PUBLICID_1_3) && systemID.equals(J2EEConstants.APPLICATION_SYSTEMID_1_3)) {
				version = J2EEVersionConstants.J2EE_1_3_ID;
			} else if (publicID.equals(J2EEConstants.APPLICATION_PUBLICID_1_2) && systemID.equals(J2EEConstants.APPLICATION_SYSTEMID_1_2)) {
				version = J2EEVersionConstants.J2EE_1_2_ID;
			} else {
				version = J2EEVersionConstants.J2EE_1_4_ID;
			}
		} else if (moduleFile.isEJBJarFile()) {
			if (publicID.equals(J2EEConstants.EJBJAR_PUBLICID_2_0) && systemID.equals(J2EEConstants.EJBJAR_SYSTEMID_2_0)) {
				version = J2EEVersionConstants.EJB_2_0_ID;
			} else if (publicID.equals(J2EEConstants.EJBJAR_PUBLICID_1_1) && systemID.equals(J2EEConstants.EJBJAR_SYSTEMID_1_1)) {
				version = J2EEVersionConstants.EJB_1_1_ID;
			} else {
				version = J2EEVersionConstants.EJB_2_1_ID;
			}
		} else if (moduleFile.isApplicationClientFile()) {
			if (publicID.equals(J2EEConstants.APP_CLIENT_PUBLICID_1_3) && systemID.equals(J2EEConstants.APP_CLIENT_SYSTEMID_1_3)) {
				version = J2EEVersionConstants.J2EE_1_3_ID;
			} else if (publicID.equals(J2EEConstants.APP_CLIENT_PUBLICID_1_2) && systemID.equals(J2EEConstants.APP_CLIENT_SYSTEMID_1_2)) {
				version = J2EEVersionConstants.J2EE_1_2_ID;
			} else {
				version = J2EEVersionConstants.J2EE_1_4_ID;
			}
		} else if (moduleFile.isWARFile()) {
			if (publicID.equals(J2EEConstants.WEBAPP_PUBLICID_2_3) && systemID.equals(J2EEConstants.WEBAPP_SYSTEMID_2_3)) {
				version = J2EEVersionConstants.WEB_2_3_ID;
			} else if (publicID.equals(J2EEConstants.WEBAPP_PUBLICID_2_2) && systemID.equals(J2EEConstants.WEBAPP_SYSTEMID_2_2)) {
				version = J2EEVersionConstants.WEB_2_2_ID;
			} else {
				version = J2EEVersionConstants.WEB_2_4_ID;
			}
		} else if (moduleFile.isRARFile()) {
			if (publicID.equals(J2EEConstants.CONNECTOR_PUBLICID_1_0) && systemID.equals(J2EEConstants.CONNECTOR_SYSTEMID_1_0)) {
				version = J2EEVersionConstants.JCA_1_0_ID;
			} else {
				version = J2EEVersionConstants.JCA_1_5_ID;
			}
		}
		return version;
	}

}
