/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.helpers;



import java.util.HashMap;
import java.util.Map;

import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategy;


/**
 * Insert the type's description here. Creation date: (05/02/01 2:58:48 PM)
 * 
 * @author: Administrator
 */
public class ArchiveOptions implements Cloneable {

	public static final int SAX = 1;
	public static final int DOM = 2;
	public static final int DEFAULT = 3;

	private int rendererType = DEFAULT;

	/**
	 * Load strategy for opening/reading the archive; optional - if null a load strategy will be
	 * created when the archive is opened
	 */
	private LoadStrategy loadStrategy;
	/**
	 * Indicator for whether auto java reflection should be turned on in the archive - defaults to
	 * true
	 */
	private boolean useJavaReflection = true;
	/**
	 * Indicator for whether the archive will be modified after opening or if it is just being
	 * opened, read, saved to a new location; this is an optimization to avoid temp file creation
	 * and saving of individual files within a nested archive if the nested archive will not change -
	 * defaults to false
	 */
	private boolean isReadOnly;

	/**
	 * Indicator for whether a libary nested in another archive (eg, utility JARs in an EAR) will be
	 * saved out as an ordinary file or as a nested Archive. When loaded into memory these files are
	 * treated as Archives, to support dynamic class loading for Java reflection of classes in
	 * EJBJarFiles and dependent JARs. An archive opened for edit, either by adding or removing
	 * files or changning the Manifest or a deployment descriptor, will be saved as a new file one
	 * file at a time. The default for this flag is true; it only applies to library Archives, not
	 * instances of ModuleFile. If you wish to edit a nested library, set this flag to false
	 */
	private boolean saveLibrariesAsFiles = true;


	private boolean saveOnlyDirtyMofResources = true;

	/**
	 * By default, nested archives are treated as primitive archives and are not piped through the
	 * discriminator tree on the archive factory; module files in an EAR are opened using the
	 * specific open methods on the factory; change this flag if you'd like nested archives to be
	 * discriminated
	 */
	private boolean discriminateNestedArchives = false;

	private Map readOnlyFlags;

	/**
	 * ArchiveOptions constructor comment.
	 */
	public ArchiveOptions() {
		super();
	}

	/**
	 * Make a copy of the reciever, setting the loadStrategy to null
	 */
	public Object clone() {
		return cloneWith(null);
	}

	/**
	 * Make a copy of the reciever, setting the loadStrategy to null
	 */
	public ArchiveOptions cloneWith(LoadStrategy aLoadStrategy) {
		try {
			ArchiveOptions result = (ArchiveOptions) super.clone();
			result.setLoadStrategy(aLoadStrategy);
			result.readOnlyFlags = null;
			return result;
		} catch (CloneNotSupportedException ignore) {
			return null;
		}
	}

	public ArchiveOptions cloneWith(LoadStrategy aLoadStrategy, String uri) {
		ArchiveOptions result = cloneWith(aLoadStrategy);
		result.setIsReadOnly(isReadOnly(uri));
		return result;
	}

	public org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategy getLoadStrategy() {
		return loadStrategy;
	}

	public boolean isReadOnly() {
		return isReadOnly;
	}

	/**
	 * Insert the method's description here. Creation date: (7/18/2001 2:42:11 PM)
	 * 
	 * @return boolean
	 */
	public boolean saveOnlyDirtyMofResources() {
		return saveOnlyDirtyMofResources;
	}

	public void setIsReadOnly(boolean newIsReadOnly) {
		isReadOnly = newIsReadOnly;
	}

	public void setIsReadOnly(boolean readOnly, String uri) {
		if (readOnlyFlags == null)
			readOnlyFlags = new HashMap();
		readOnlyFlags.put(uri, new Boolean(readOnly));
	}

	public boolean isReadOnly(String uri) {
		if (readOnlyFlags != null) {
			Boolean bool = (Boolean) readOnlyFlags.get(uri);
			if (bool != null)
				return bool.booleanValue();
		}
		return isReadOnly;
	}

	public void setLoadStrategy(org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategy newLoadStrategy) {
		loadStrategy = newLoadStrategy;
		loadStrategy.setReadOnly(isReadOnly());
		loadStrategy.setRendererType(getRendererType());
	}

	/**
	 * Insert the method's description here. Creation date: (7/18/2001 2:42:11 PM)
	 * 
	 * @param newSaveOnlyDirtyMofResources
	 *            boolean
	 */
	public void setSaveOnlyDirtyMofResources(boolean newSaveOnlyDirtyMofResources) {
		saveOnlyDirtyMofResources = newSaveOnlyDirtyMofResources;
	}

	public void setUseJavaReflection(boolean newUseJavaReflection) {
		useJavaReflection = newUseJavaReflection;
	}

	public boolean useJavaReflection() {
		return useJavaReflection;
	}

	/**
	 * Gets the saveLibrariesAsFiles.
	 * 
	 * @return Returns a boolean
	 */
	public boolean isSaveLibrariesAsFiles() {
		return saveLibrariesAsFiles;
	}

	/**
	 * Sets the saveLibrariesAsFiles.
	 * 
	 * @param saveLibrariesAsFiles
	 *            The saveLibrariesAsFiles to set
	 */
	public void setSaveLibrariesAsFiles(boolean saveLibrariesAsFiles) {
		this.saveLibrariesAsFiles = saveLibrariesAsFiles;
	}


	/**
	 * Gets the discriminateNestedArchives.
	 * 
	 * @return Returns a boolean
	 */
	public boolean shouldDiscriminateNestedArchives() {
		return discriminateNestedArchives;
	}

	/**
	 * Sets the discriminateNestedArchives.
	 * 
	 * @param discriminateNestedArchives
	 *            The discriminateNestedArchives to set
	 */
	public void setDiscriminateNestedArchives(boolean discriminateNestedArchives) {
		this.discriminateNestedArchives = discriminateNestedArchives;
	}

	/**
	 * @return Returns the rendererType.
	 */
	public int getRendererType() {
		return rendererType;
	}

	/**
	 * The rendererType allows a user to override the renderer used by Common Archive for special
	 * cases in the UI where you really want to use a non-synchronizing renderer.
	 * 
	 * @param rendererType
	 *            The rendererType to set.
	 */
	public void setRendererType(int rendererType) {
		this.rendererType = rendererType;
	}
}
