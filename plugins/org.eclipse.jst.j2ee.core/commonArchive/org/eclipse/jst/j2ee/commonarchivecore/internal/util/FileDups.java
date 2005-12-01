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


import java.io.File;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Insert the type's description here. Creation date: (4/11/2001 10:28:44 AM)
 * 
 * @author: Administrator
 */
public class FileDups {
	private static int sFiles = 0;
	private static int sDups = 0;
	private static int sJARs = 0;
	private static String[] excludedEntryNames = new String[]{"com/ibm/ivj/ejb/runtime/_CopyHelper_Stub.class", //$NON-NLS-1$
				"org/omg/stub/javax/ejb/_HomeHandle_Stub.class", //$NON-NLS-1$
				"org/omg/stub/javax/ejb/_Handle_Stub.class", //$NON-NLS-1$
				"org/omg/stub/javax/ejb/_EJBObject_Stub.class", //$NON-NLS-1$
				"org/omg/stub/javax/ejb/_EJBHome_Stub.class", //$NON-NLS-1$
				"org/omg/stub/java/lang/_Cloneable_Stub.class", //$NON-NLS-1$
				"com/ibm/websphere/csi/_TransactionalObject_Stub.class", //$NON-NLS-1$
				"com/ibm/websphere/csi/_CSIServant_Stub.class", //$NON-NLS-1$
				"com/ibm/ejs/container/_EJSWrapper_Tie.class", //$NON-NLS-1$
				"com/ibm/ejs/container/_EJSWrapper_Stub.class" //$NON-NLS-1$
	};
	private static HashSet excluded = new HashSet(Arrays.asList(excludedEntryNames));

	private static void addEntry(String entry, Map map, String jarName) {
		sFiles++;

		Object current = map.get(entry);
		if (current == null) {
			// This is the first time the entry is found
			map.put(entry, jarName);
		} else if (current instanceof String) {
			// There is one other entry
			map.remove(entry);

			String other = (String) current;
			LinkedList list = new LinkedList();
			list.addFirst(other);
			list.addFirst(jarName);
			map.put(entry, list);

			sDups++;
		} else {
			// There are more than one other instances
			LinkedList list = (LinkedList) current;
			list.addFirst(jarName);

			sDups++;
		}
	}

	/**
	 * Starts the application.
	 * 
	 * @param args
	 *            an array of command-line arguments
	 */
	public static void main(java.lang.String[] args) {
		if (args.length != 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("FileDups utility\r\n\r\n"); //$NON-NLS-1$
			sb.append("Synopsis:\r\n"); //$NON-NLS-1$
			sb.append("Analyzes JAR and ZIP files in a directory for duplicate entries with the same name.  This excludes all entries in the \"META-INF\" directory of the archive.  This does not check timestamps or size.\r\n\r\n"); //$NON-NLS-1$
			sb.append("Usage: FileDups dirName\r\n"); //$NON-NLS-1$
			System.out.println(sb.toString());
			return;
		}

		TreeMap map = new TreeMap();

		try {
			for (int i = 0; i < args.length; i++) {
				File root = new File(args[i]);
				processDirectory(root, map);
			}

			TreeMap outputMap = new TreeMap();

			Set set = map.entrySet();
			Iterator it = set.iterator();
			while (it.hasNext()) {
				Map.Entry me = (Map.Entry) it.next();
				Object o = me.getValue();
				if (o instanceof LinkedList) {
					StringBuffer jars = new StringBuffer();

					LinkedList list = (LinkedList) o;
					Iterator entryIter = list.iterator();
					while (entryIter.hasNext()) {
						jars.append(entryIter.next());
						jars.append("\r\n"); //$NON-NLS-1$
					}

					LinkedList outputList = (LinkedList) outputMap.get(jars.toString());
					if (outputList == null) {
						outputList = new LinkedList();
						outputMap.put(jars.toString(), outputList);
					}
					outputList.addFirst(me.getKey());
				}
			}

			Set outSet = outputMap.entrySet();
			it = outSet.iterator();
			while (it.hasNext()) {
				Map.Entry me = (Map.Entry) it.next();
				String jars = (String) me.getKey();
				StringTokenizer tokens = new StringTokenizer(jars, "\r\n"); //$NON-NLS-1$

				while (tokens.hasMoreTokens()) {
					System.out.println(tokens.nextToken());
				}

				LinkedList list = (LinkedList) me.getValue();
				Iterator entryIter = list.iterator();
				while (entryIter.hasNext()) {
					System.out.println("    " + (String) entryIter.next()); //$NON-NLS-1$
				}
			}

			System.out.println("\r\nSearched " + sJARs + " jars, found " + sDups + " dups in " + sFiles + " files."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		} catch (Exception x) {
			x.printStackTrace();
		}
	}

	private static void processDirectory(File root, Map map) throws java.io.IOException {
		File[] files = root.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isDirectory())
				processDirectory(f, map);
			else {
				String name = f.getName().toLowerCase();
				if (name.endsWith(".zip") || name.endsWith(".jar")) //$NON-NLS-1$ //$NON-NLS-2$
				{
					processFile(f, map);
				}
			}
		}
	}

	private static void processFile(File file, Map map) throws java.io.IOException {
		HashSet filesInFile = new HashSet();

		sJARs++;
		ZipFile zip = null;
		try {
			zip = new ZipFile(file);
			Enumeration eNum = zip.entries();
			while (eNum.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) eNum.nextElement();
				String name = entry.getName();
				if (!name.startsWith("META-INF") && !excluded.contains(name)) //$NON-NLS-1$
					filesInFile.add(name);
			}
		} catch (Exception x) {
			System.out.println("*Error searching in " + file.getAbsolutePath()); //$NON-NLS-1$
		} finally {
			try {
				if (zip != null)
					zip.close();
			} catch (java.io.IOException ignored) {
				//Ignore
			}
		}

		// Now add the packages to the map
		String filename = file.getPath();

		Iterator it = filesInFile.iterator();
		while (it.hasNext()) {
			String entryName = (String) it.next();
			addEntry(entryName, map, filename);
		}
	}
}
