/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import com.ibm.icu.util.StringTokenizer;
import java.util.Vector;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;


/**
 * This class stores the relationship between two files. In particular, this is an inLink and the
 * files which reference it.
 */
public class RelationData {
	public class Link implements Cloneable {

		private String url;
		private int occurrences = 0;

		public Link(String newUrl, int occ) {
			url = newUrl;
			occurrences = occ;
		}

		/**
		 * Returns an exact copy of this object.
		 * 
		 * @return Created clone object
		 */
		public Object clone() {
			return new Link(url, occurrences);
		}

		/**
		 * Returns the url of the file that references this inLink
		 * 
		 * @return String
		 */
		public String getURL() {
			return url;
		}

		/**
		 * Returns the number of times this file references the inLink
		 * 
		 * @return int
		 */
		public int getOccurrences() {
			return occurrences;
		}

		private int addOccurrence(int add) {
			occurrences = occurrences + add;
			return occurrences;
		}

		public String toString() {
			return url;
		}
	}

	// our relationship table. Key is a string which is the path of the inLink,
	// value is a vector of files that reference the inLink.
	protected Hashtable inLinksTable = null;
	protected Hashtable anchorsTable = null;
	private static final int INIT_HASH_TABLE_SIZE = 100;
	private static final int INIT_ANCHORS_HASH_TABLE_SIZE = 10;
	private boolean fileOk = false;
	private static final String LINK_STATE_FILE_NAME = "link_table_states.txt";//$NON-NLS-1$
	private static final String LINK_SERVERCONTEXTROOT_FILE_NAME = "link_scr_states.txt";//$NON-NLS-1$
	public static final String LINK_MISSING_FILE = "-";//$NON-NLS-1$
	public static final String LINK_INDEX_SEPARATOR = "+";//$NON-NLS-1$
	public static final String ANCHOR_INDEX_SEPARATOR = "#";//$NON-NLS-1$
	public static final String LINK_OCCURENCE_SEPARATOR = "/";//$NON-NLS-1$
	public static final String ANCHOR_SEPARATOR = "#### ANCHORS ####";//$NON-NLS-1$

	/**
	 * RelationData constructor comment.
	 */
	public RelationData() {
		super();
		inLinksTable = new Hashtable(INIT_HASH_TABLE_SIZE);
		anchorsTable = new Hashtable(INIT_ANCHORS_HASH_TABLE_SIZE);
	}

	public Link addInLink(String file_path, String parent_path, int occurence) {
		Vector in_links = (Vector) inLinksTable.get(file_path);
		if (in_links == null) {
			return null;
		}
		for (int i = 0; i < in_links.size(); i++) {
			Link inlnk = (Link) in_links.elementAt(i);
			String url = inlnk.getURL();

			//
			// Increment the occurence and return
			//

			if (url.compareTo(parent_path) == 0) {
				inlnk.addOccurrence(occurence);
				return inlnk;
			}
		}

		//
		// The link does not exist, need to add an entry in the table for it
		//

		Link inlnk = new Link(parent_path, occurence);
		in_links.addElement(inlnk);
		return inlnk;
	}

	public void changeInLink(String filePath, boolean missing) {

		if (missing) {
			Vector inlinks = (Vector) inLinksTable.remove(filePath);
			inLinksTable.put(LINK_MISSING_FILE + filePath, inlinks);
		} else {
			Vector inlinks = (Vector) inLinksTable.remove(LINK_MISSING_FILE + filePath);
			inLinksTable.put(filePath, inlinks);
		}
	}


	/*
	 * returns which projects depend on this file i.e. any projects that are in this file's list of
	 * in_links
	 */
	public Collection getDependentProjects(String file_path) {
		Hashtable outProjects = new Hashtable();
		Vector in_links = (Vector) inLinksTable.get(file_path);

		if (in_links != null) {
			for (int i = 0; i < in_links.size(); i++) {
				Link inlnk = (Link) in_links.elementAt(i);
				String url = inlnk.getURL();
				// should be in the form of: /ProjName/....
				if (url.length() > 0) {
					// key could be the filename or the missing filename ("-" + filename)
					// take this into account and offset the index accordingly
					int index = 0;
					int offset = 1;
					if (url.charAt(0) == '-') {
						index = url.indexOf(IPath.SEPARATOR, 2);
						offset = 2;
					} else {
						index = url.indexOf(IPath.SEPARATOR, 1);
						offset = 1;
					}
					if (index != -1) {
						String projName = url.substring(offset, index);
						outProjects.put(projName, projName);
					}
				}
			}
		}
		return outProjects.values();
	}

	/*
	 * returns which cross-project files depend on this file i.e. any projects that are in this
	 * file's list of in_links
	 */
	public Collection getDependentProjectFiles(String projName) {
		Hashtable outProjects = new Hashtable();

		for (Enumeration aenum = inLinksTable.keys(); aenum.hasMoreElements();) {
			String path = (String) aenum.nextElement();
			// should be in the form of: /ProjName/....
			if (path.length() > 0) {
				// if path is a missing filename ("-" + filename) then it means that it had
				// to come from this project (that is, the external nonexistent file could not have
				// linked to this project). So, we will not add this path. Also, since it is marked
				// broken, it probably won't exist to do anything with it anyway.
				if (path.charAt(0) != '-') {
					int index = path.indexOf(IPath.SEPARATOR, 1);
					if (index != -1) {
						String currentProjName = path.substring(1, index);
						if (!currentProjName.equals(projName)) {
							outProjects.put(path, path);
						}
					}
				}
			}
		}
		return outProjects.values();
	}



	public void createInLinkEntry(String file_path, String parent_path, int occurence) {

		Vector in_links = new Vector();
		Link inlnk = new Link(parent_path, occurence);
		in_links.addElement(inlnk);
		inLinksTable.put(file_path, in_links);
	}



	public void dump() {
		if (false) {
			if (inLinksTable != null) {
				for (Enumeration aenum = inLinksTable.keys(); aenum.hasMoreElements();) {
					StringBuffer St = new StringBuffer();
					String key = (String) aenum.nextElement();
					St.append("\nKey=" + key + "\n");//$NON-NLS-1$//$NON-NLS-2$
					Vector in_links = (Vector) inLinksTable.get(key);
					int nb_of_links = in_links.size();

					// Replace the string by index
					// Add a '+'separator only for more than one links to save space
					//


					for (int i = 0; i < nb_of_links; i++) {
						Link lnk = (Link) in_links.elementAt(i);
						St.append("\tValue=" + lnk.getURL() + "[" + lnk.getOccurrences() + "]");//$NON-NLS-3$//$NON-NLS-2$//$NON-NLS-1$
					}
					org.eclipse.jem.util.logger.proxy.Logger.getLogger().logInfo(St.toString());
				}
			}
		}
	}

	public void dump(boolean dump) {
		if (dump) {
			if (inLinksTable != null) {
				for (Enumeration aenum = inLinksTable.keys(); aenum.hasMoreElements();) {
					StringBuffer St = new StringBuffer();
					String key = (String) aenum.nextElement();
					St.append("\nKey=" + key + "\n");//$NON-NLS-1$//$NON-NLS-2$
					Vector in_links = (Vector) inLinksTable.get(key);
					int nb_of_links = in_links.size();

					// Replace the string by index
					// Add a '+'separator only for more than one links to save space
					//


					for (int i = 0; i < nb_of_links; i++) {
						Link lnk = (Link) in_links.elementAt(i);
						St.append("\tValue=" + lnk.getURL() + "[" + lnk.getOccurrences() + "]");//$NON-NLS-3$//$NON-NLS-2$//$NON-NLS-1$
					}
					org.eclipse.jem.util.logger.proxy.Logger.getLogger().logError(St.toString());
				}
			}
		}
	}

	/**
	 * used for LinksBuilder debugging
	 */
	public void dump2() {
		if (inLinksTable != null) {
			for (Enumeration aenum = inLinksTable.keys(); aenum.hasMoreElements();) {
				StringBuffer St = new StringBuffer();
				String key = (String) aenum.nextElement();

				Vector in_links = (Vector) inLinksTable.get(key);
				int nb_of_links = in_links.size();
				St.append("\nKey=" + key + " -- num:" + nb_of_links + "\n");//$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
				// Replace the string by index
				// Add a '+'separator only for more than one links to save space
				//


				for (int i = 0; i < nb_of_links; i++) {
					Link lnk = (Link) in_links.elementAt(i);
					St.append("\tValue=" + lnk.getURL() + "[" + lnk.getOccurrences() + "]");//$NON-NLS-3$//$NON-NLS-2$//$NON-NLS-1$
				}
				System.out.println(St.toString());
			}
		}
		System.out.println("************************************************\n\n\n\n************************************\n"); //$NON-NLS-1$
	}


	public Vector getInLinks(String filePath) {
		return (Vector) inLinksTable.get(filePath);
	}

	public void getInLinks(String filePath, Vector vectInLinks) {

		if (inLinksTable != null) {
			dump();
			Vector in_links = (Vector) inLinksTable.get(filePath);
			if (in_links != null) {
				for (int i = 0; i < in_links.size(); i++) {
					Link inlnk = (Link) ((Link) in_links.elementAt(i)).clone();
					vectInLinks.addElement(inlnk);
				}
			}
		}
	}

	public Hashtable getAnchorsTable() {
		return anchorsTable;
	}

	/**
	 * Returns the file used to store ServerContextRoot for the project.
	 */
	public static File getPreviousSCRFile(IProject project) {
		return new Path(J2EEPlugin.getDefault().getStateLocation().toString() + File.separator + project.getName() + File.separator + LINK_SERVERCONTEXTROOT_FILE_NAME).toFile();
	}

	/**
	 * Returns the file used to store the state of the link relations for the project.
	 * 
	 * @return java.io.File The state file
	 * @param project
	 *            org.eclipse.core.resources.IProject The project to get the state file for.
	 */
	private File getStateFile(IProject project) {
		return new Path(J2EEPlugin.getDefault().getStateLocation().toString() + File.separator + project.getName() + File.separator + LINK_STATE_FILE_NAME).toFile();
	}

	public boolean hasInLinkEntry(String path) {
		return inLinksTable.containsKey(path);
	}

	/**
	 * Run through all missing-file (-) RD entries to see if any of them closely match the path to
	 * this class. Return all of the matches.
	 */
	public Vector getPossibleMissingEndingMatches(IPath classPath) {
		Vector v = new Vector();

		String match = classPath.lastSegment();
		match = match.substring(0, match.length() - 6); // length minus ".class"

		String classPathStr = classPath.toString();
		String matchingClassPath = classPathStr.toString().substring(0, classPathStr.length() - 6);

		for (Enumeration aenum = inLinksTable.keys(); aenum.hasMoreElements();) {
			String path = (String) aenum.nextElement();
			// check if path ends with the class name that has been added/removed
			if (path.startsWith(LINK_MISSING_FILE) && path.endsWith(match)) {
				// OK: simple match found; now let's match with finer granularity.
				// Take path and put it into a form where we can check if it is a
				// close match to the class that has changed:
				// /Proj/Web Content/com.acme.Foo --> com/acme/Foo
				String linkPath;
				int index = path.lastIndexOf(IPath.SEPARATOR);
				if (index != -1 && index < path.length() - 1) {
					linkPath = path.substring(index + 1);
				} else {
					linkPath = path;
				}
				String closeMatch = linkPath.replace('.', IPath.SEPARATOR);

				if (matchingClassPath.endsWith(closeMatch)) {
					Vector in_links = (Vector) inLinksTable.get(path);
					String nonMissingPath = path.substring(1, path.length());
					changeInLink(nonMissingPath, false);
					if (in_links != null) {
						for (int i = 0; i < in_links.size(); i++) {
							Link inlnk = (Link) in_links.elementAt(i);
							String url = inlnk.getURL();
							v.add(url);
						}
					}
				}
			}
		}
		return v;
	}


	/**
	 * Run through all existing RD entries to see if any of them closely match the path to this
	 * class. Return all of the matches.
	 */
	public Vector getPossibleExistingEndingMatches(IPath classPath) {
		Vector v = new Vector();

		String match = classPath.lastSegment();
		match = match.substring(0, match.length() - 6); // length minus ".class"

		String classPathStr = classPath.toString();
		String matchingClassPath = classPathStr.toString().substring(0, classPathStr.length() - 6);

		for (Enumeration aenum = inLinksTable.keys(); aenum.hasMoreElements();) {
			String path = (String) aenum.nextElement();
			// check if path ends with the class name that has been added/removed
			if (path.endsWith(match) && !path.startsWith(LINK_MISSING_FILE)) {
				// OK: simple match found; now let's match with finer granularity.
				// Take path and put it into a form where we can check if it is a
				// close match to the class that has changed:
				// /Proj/Web Content/com.acme.Foo --> com/acme/Foo
				String linkPath;
				int index = path.lastIndexOf(IPath.SEPARATOR);
				if (index != -1 && index < path.length() - 1) {
					linkPath = path.substring(index + 1);
				} else {
					linkPath = path;
				}
				String closeMatch = linkPath.replace('.', IPath.SEPARATOR);

				if (matchingClassPath.endsWith(closeMatch)) {
					Vector in_links = (Vector) inLinksTable.get(path);
					changeInLink(path, true);
					if (in_links != null) {
						for (int i = 0; i < in_links.size(); i++) {
							Link inlnk = (Link) in_links.elementAt(i);
							String url = inlnk.getURL();
							v.add(url);
						}
					}
				}
			}
		}
		return v;
	}

	/**
	 * Run through all existing RD entries to see if any of them closely match the path to this
	 * class. Remove the inlinks for all the matches.
	 */
	public void removeInLinksPossibleExistingEndingMatches(IPath classPath) {
		String match = classPath.lastSegment();
		match = match.substring(0, match.length() - 6); // length minus ".class"

		String classPathStr = classPath.toString();
		String matchingClassPath = classPathStr.toString().substring(0, classPathStr.length() - 6);

		for (Enumeration aenum = inLinksTable.keys(); aenum.hasMoreElements();) {
			String path = (String) aenum.nextElement();
			if (path.endsWith(match) && !path.startsWith(LINK_MISSING_FILE)) {
				// OK: simple match found; now let's match with finer granularity.
				// Take path and put it into a form where we can check if it is a
				// close match to the class that has changed:
				// /Proj/Web Content/com.acme.Foo --> com/acme/Foo
				String linkPath;
				int index = path.lastIndexOf(IPath.SEPARATOR);
				if (index != -1 && index < path.length() - 1) {
					linkPath = path.substring(index + 1);
				} else {
					linkPath = path;
				}
				String closeMatch = linkPath.replace('.', IPath.SEPARATOR);

				if (matchingClassPath.endsWith(closeMatch)) {
					removeInLinks(path);
				}
			}
		}
	}

	/**
	 * Initialize links tables
	 */
	public void initialize() {
		inLinksTable = new Hashtable(INIT_HASH_TABLE_SIZE);
		anchorsTable = new Hashtable(INIT_ANCHORS_HASH_TABLE_SIZE);

	}

	/**
	 * Initialize links tables
	 */
	public void initializeAnchors() {
		anchorsTable = new Hashtable(INIT_ANCHORS_HASH_TABLE_SIZE);
	}

	/**
	 * Initialize links tables
	 */
	public void initializeInLinks() {
		inLinksTable = new Hashtable(INIT_HASH_TABLE_SIZE);
	}

	/**
	 * Return true if the file to restore data from existed and was successfully read.
	 * 
	 * @return boolean
	 */
	public boolean isFileOk() {
		return fileOk;
	}

	public boolean isInitialized() {
		return (inLinksTable != null);
	}

	public void newInLinkEntry(String path) {
		if (!inLinksTable.containsKey(path))
			inLinksTable.put(path, new Vector());
	}

	public void removeInLinks(String file_path) {

		//
		// Iterate through the fLinksTable to remove all in-links references
		// for file_path
		//

		for (Enumeration aenum = inLinksTable.keys(); aenum.hasMoreElements();) {
			String key = (String) aenum.nextElement();
			Vector in_links = (Vector) inLinksTable.get(key);

			for (int i = 0; i < in_links.size(); i++) {

				Link inlnk = (Link) in_links.elementAt(i);
				String url = inlnk.getURL();

				if (url.compareTo(file_path) == 0) {
					in_links.removeElementAt(i);
					break;
				}

				//
				// Remove the entry in the fLinksTable if the in-link
				// of the file is not in the workbench and an orphan link
				// otherwise update the in-links fLinksTable
				//
			}

			if (in_links.isEmpty() && key.startsWith(LINK_MISSING_FILE)) {
				inLinksTable.remove(key);
			}
		}
	}

	/**
	 * Iterate through the fLinksTable to return all out-links references for file_path
	 * 
	 * @param file_path
	 * @return
	 */
	public List getOutLinks(String file_path) {
		ArrayList list = new ArrayList();
		for (Enumeration aenum = inLinksTable.keys(); aenum.hasMoreElements();) {
			String key = (String) aenum.nextElement();
			Vector in_links = (Vector) inLinksTable.get(key);

			for (int i = 0; i < in_links.size(); i++) {

				Link inlnk = (Link) in_links.elementAt(i);
				String url = inlnk.getURL();

				if (url.compareTo(file_path) == 0) {
					list.add(key);
					break;
				}
			}
		}

		return list;
	}


	/**
	 * return whether other resources link to this file or not
	 */
	public boolean referencedByOtherLinks(String file_path) {

		//
		// Iterate through the fLinksTable to remove all in-links references
		// for file_path
		//
		boolean referenced = false;
		for (Enumeration aenum = inLinksTable.keys(); !referenced && aenum.hasMoreElements();) {
			String key = (String) aenum.nextElement();
			Vector in_links = (Vector) inLinksTable.get(key);

			for (int i = 0; i < in_links.size(); i++) {
				Link inlnk = (Link) in_links.elementAt(i);
				String url = inlnk.getURL();

				if (url.compareTo(file_path) == 0) {
					referenced = true;
					break;
				}
			}
		}
		return referenced;
	}


	/*
	 * performs as removeInLinks( String ) ... But for performance reasons, this also additionally
	 * returns a list of all the projects that this file is dependent upon. This is useful to get
	 * this information, and then go to all of those projects' relation data and tell them to
	 * removeInLinks for this file.
	 */
	public Collection removeInLinksForProjects(String file_path) {

		//
		// Iterate through the fLinksTable to remove all in-links references
		// for file_path
		//
		Hashtable projects = new Hashtable();
		for (Enumeration aenum = inLinksTable.keys(); aenum.hasMoreElements();) {
			String key = (String) aenum.nextElement();
			Vector in_links = (Vector) inLinksTable.get(key);

			for (int i = 0; i < in_links.size(); i++) {

				Link inlnk = (Link) in_links.elementAt(i);
				String url = inlnk.getURL();

				if (url.compareTo(file_path) == 0) {
					// should be in the form of: /ProjName/....
					if (key.length() > 0) {
						// key could be the filename or the missing filename ("-" + filename)
						// take this into account and offset the index accordingly
						int index = 0;
						int offset = 1;
						if (key.charAt(0) == '-') {
							index = key.indexOf(IPath.SEPARATOR, 2);
							offset = 2;
						} else {
							index = key.indexOf(IPath.SEPARATOR, 1);
							offset = 1;
						}
						if (index != -1) {
							String projName = key.substring(offset, index);
							projects.put(projName, projName);
						}
					}
					in_links.removeElementAt(i);
					break;
				}

				//
				// Remove the entry in the fLinksTable if the in-link
				// of the file is not in the workbench and an orphan link
				// otherwise update the in-links fLinksTable
				//
			}

			if (in_links.isEmpty() && key.startsWith(LINK_MISSING_FILE))
				inLinksTable.remove(key);
		}

		return projects.values();
	}



	/**
	 * performs as removeInLinks( String ) ... But for performance reasons, it additionally returns
	 * which projects depend on this file. i.e. any projects that are in this file's list of
	 * in_links
	 */
	public Collection removeInLinksAndGetDependentProjects(String file_path) {
		//
		// Iterate through the fLinksTable to remove all in-links references
		// for file_path
		//
		Hashtable projects = new Hashtable();
		for (Enumeration aenum = inLinksTable.keys(); aenum.hasMoreElements();) {
			String key = (String) aenum.nextElement();
			Vector in_links = (Vector) inLinksTable.get(key);

			if (key.equals(RelationData.LINK_MISSING_FILE + file_path)) {
				int index = -1;
				for (int i = 0; i < in_links.size(); i++) {
					Link inlnk = (Link) in_links.elementAt(i);
					String url = inlnk.getURL();

					if (url.compareTo(file_path) == 0) {
						index = i;
					}
					if (url.length() > 1) {
						int projindex = url.indexOf(IPath.SEPARATOR, 1);
						if (projindex != -1) {
							String projName = url.substring(1, projindex);
							projects.put(projName, projName);
						}
					}
				}
				if (index != -1) {
					in_links.removeElementAt(index);
				}

			} else {
				for (int i = 0; i < in_links.size(); i++) {

					Link inlnk = (Link) in_links.elementAt(i);
					String url = inlnk.getURL();

					if (url.compareTo(file_path) == 0) {

						in_links.removeElementAt(i);
						break;
					}

					//
					// Remove the entry in the fLinksTable if the in-link
					// of the file is not in the workbench and an orphan link
					// otherwise update the in-links fLinksTable
					//
				}
			}

			if (in_links.isEmpty() && key.startsWith(LINK_MISSING_FILE)) {
				inLinksTable.remove(key);
			}
		}
		return projects.values();
	}


	/**
	 * remove all references to a specific inLink. When removeOrphan is true the inLink entry is
	 * removed as well, when there are no more refereces to it
	 */
	public void removeInLinks(String inLinkEntry, String inLinkReference, boolean removeOrphan) {

		String key = inLinkEntry;
		Vector in_links = (Vector) inLinksTable.get(key);

		for (int i = 0; i < in_links.size(); i++) {

			Link inlnk = (Link) in_links.elementAt(i);
			String url = inlnk.getURL();

			if (url.compareTo(inLinkReference) == 0) {
				in_links.removeElementAt(i);
				break;
			}
		}

		// If there are no more references to the inLinkEntry
		// and the caller wants this orphan deleted then
		// remove the key from the table
		if (in_links.isEmpty() && removeOrphan)
			inLinksTable.remove(key);

	}

	/**
	 * Restores this builder's saved state and returns whether it was successful in doing so.
	 */
	public boolean restore(IProject project) {
		if (project != null) {
			// get the state
			File stateFile = getStateFile(project);
			if (stateFile.exists()) {
				inLinksTable = new Hashtable(INIT_HASH_TABLE_SIZE);

				// read inLinksTable from stateFile
				Vector keys = new Vector();
				Vector indices = new Vector();
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader(stateFile));

					String line = null;
					//				while( ((line = reader.readLine()) != null) && !line.startsWith(
					// ANCHOR_SEPARATOR ) )
					while ((line = reader.readLine()) != null) {
						String buffer = line;


						// to take into account the possibility of links spanning multiple lines,
						// keep reading until we find a line that starts with an idicator
						// that the link is finished (a line beginning w/ LINK_INDEX_SEPARATOR)
						line = reader.readLine();
						while (line != null && !line.startsWith(LINK_INDEX_SEPARATOR)) {
							buffer = buffer + line;
							line = reader.readLine();
						}
						// add link string (/MyProj/file.html)
						if (buffer != null) {
							keys.addElement(buffer);
						}
						// add references line (+2/3...)
						if (line != null) {
							indices.addElement(line);
						} else {
							System.out.println(ProjectSupportResourceHandler.Syntax_Error_in_the_links_UI_); //$NON-NLS-1$ = "Syntax Error in the links state file"
							return false;
						}
					}

					// Use this to load anchor information
					//				// Now let's add all of the extra anchor information
					//				if ( line != null && line.startsWith( ANCHOR_SEPARATOR ) ) {
					//					while( (line = reader.readLine()) != null )
					//					{
					//						String buffer = line;
					//	
					//						// to take into account the possibility of links spanning multiple lines,
					//						// keep reading until we find a line that starts with an idicator
					//						// that the link is finished (a line beginning w/ LINK_INDEX_SEPARATOR)
					//						line = reader.readLine();
					//						while ( line != null && !line.startsWith( ANCHOR_INDEX_SEPARATOR ) ) {
					//							buffer = buffer + line;
					//							line = reader.readLine();
					//						}
					//
					//						// add file and all of its anchors
					//						if ( buffer != null && line != null)
					//						{
					//							anchorsTable.put( buffer, line );
					//						}
					//						else
					//						{
					//							System.out.println(ResourceHandler.getString("Syntax_Error_in_the_links_UI_"));
					// //$NON-NLS-1$ = "Syntax Error in the links state file"
					//							return false;
					//						}
					//					}
					//		
					//				}
				} catch (FileNotFoundException fe) {
					org.eclipse.jem.util.logger.proxy.Logger.getLogger().logError(fe);
				} catch (IOException ie) {
					org.eclipse.jem.util.logger.proxy.Logger.getLogger().logError(ie);
				} finally {
					if (reader != null) {
						try {
							reader.close();
						} catch (IOException ie2) {
							org.eclipse.jem.util.logger.proxy.Logger.getLogger().logError(ie2);
						}
					}
				}


				//
				// Populate the hash inLinksTable with keys (file paths) and values (in links files
				// paths)
				//
				fileOk = true;
				for (int i = 0; i < keys.size(); i++) {
					try {
						String key = (String) keys.elementAt(i);
						String compacted_indices = (String) indices.elementAt(i);

						//
						// Parse the in-links indexes
						//

						int size = keys.size();
						Vector in_links = new Vector();
						if (compacted_indices != LINK_INDEX_SEPARATOR) {

							// Extract indexes and occurences

							StringTokenizer st_idx_occ = new StringTokenizer(new String(compacted_indices), LINK_INDEX_SEPARATOR);
							while (st_idx_occ.hasMoreTokens()) {
								String idx_occ = new String(st_idx_occ.nextToken());
								int pos_occ = idx_occ.indexOf(LINK_OCCURENCE_SEPARATOR);
								int index;
								int occurence = 1;
								if (pos_occ == -1) {
									index = Integer.valueOf(idx_occ).intValue();
								} else {
									index = Integer.valueOf(idx_occ.substring(0, pos_occ)).intValue();
									occurence = Integer.valueOf(idx_occ.substring(pos_occ + 1)).intValue();
								}
								if (index >= 0 && index < size) {
									Link inlnk = new Link((String) keys.elementAt(index), occurence);
									in_links.addElement(inlnk);
								}
							}
						}
						inLinksTable.put(key, in_links);
					} catch (Exception e) {
						fileOk = false;
					}
				}

				return fileOk;
			}
			return false;

		}

		return false;
	}

	/**
	 * Saves this builder's state to disk so that it can be restarted in the same state later.
	 */
	public void save(IProject project) {

		File stateFile = getStateFile(project);
		if (inLinksTable == null) {
			stateFile.delete();
		} else {

			// Prepare the index vector

			Vector index_vector = new Vector(inLinksTable.size());
			for (Enumeration aenum = inLinksTable.keys(); aenum.hasMoreElements();) {
				index_vector.addElement(aenum.nextElement());
			}

			// write inLinksTable to stateFile

			try {
				File parentFolder = stateFile.getParentFile();
				if (!parentFolder.exists()) {
					parentFolder.mkdirs();
				}
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(stateFile)));
				for (Enumeration aenum = inLinksTable.keys(); aenum.hasMoreElements();) {
					String key = (String) aenum.nextElement();
					out.print(key);
					out.println();
					out.print(LINK_INDEX_SEPARATOR);
					Vector in_links = (Vector) inLinksTable.get(key);
					int nb_of_links = in_links.size();

					// Replace the string by index
					// Add a '+'separator only for more than one links to save space
					// Add '/<occurence>' only if occurence > 1 to save space
					//

					Link lnk = null;
					int nb_lnks = 0;
					if (nb_of_links > 0) {
						lnk = (Link) in_links.elementAt(0);
						out.print(index_vector.indexOf(lnk.getURL()));
						nb_lnks = lnk.getOccurrences();
						if (nb_lnks > 1) {
							out.print(LINK_OCCURENCE_SEPARATOR);
							out.print(nb_lnks);
						}
						for (int i = 1; i < nb_of_links; i++) {
							out.print(LINK_INDEX_SEPARATOR);
							lnk = (Link) in_links.elementAt(i);
							out.print(index_vector.indexOf(lnk.getURL()));
							nb_lnks = lnk.getOccurrences();
							if (nb_lnks > 1) {
								out.print(LINK_OCCURENCE_SEPARATOR);
								out.print(nb_lnks);
							}
						}
					}
					out.println();
				}
				/*
				 * use this to save out anchor information //
				 * ############################################ if ( !anchorsTable.isEmpty() ) {
				 * out.println( ANCHOR_SEPARATOR ); } for (Enumeration enum = anchorsTable.keys();
				 * enum.hasMoreElements();) { String key = (String) enum.nextElement();
				 * out.print(key); out.println(); //out.print(ANCHOR_INDEX_SEPARATOR); out.println(
				 * anchorsTable.get(key) ); }
				 */

				out.flush();
				out.close();
			} catch (IOException e) {
				org.eclipse.jem.util.logger.proxy.Logger.getLogger().logError(ProjectSupportResourceHandler.Error_while_saving_links_s_EXC_); //$NON-NLS-1$ = "Error while saving links state file"
			}
		}
	}

	/**
	 * Insert the method's description here. Creation date: (3/21/2001 1:45:58 PM)
	 * 
	 * @param newFileOk
	 *            boolean
	 */
	void setFileOk(boolean newFileOk) {
		fileOk = newFileOk;
	}
}
