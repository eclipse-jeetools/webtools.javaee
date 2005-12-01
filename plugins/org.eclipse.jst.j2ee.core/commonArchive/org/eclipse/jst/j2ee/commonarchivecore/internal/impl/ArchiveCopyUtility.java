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
package org.eclipse.jst.j2ee.commonarchivecore.internal.impl;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.common.internal.util.Defaultable;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveRuntimeException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifestImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
import org.eclipse.wst.common.internal.emf.utilities.CopyGroup;
import org.eclipse.wst.common.internal.emf.utilities.EtoolsCopySession;
import org.eclipse.wst.common.internal.emf.utilities.EtoolsCopyUtility;



/**
 * Insert the type's description here. Creation date: (12/18/00 6:26:48 PM)
 * 
 * @author: Administrator
 */
public class ArchiveCopyUtility extends EtoolsCopyUtility {
	/**
	 * ModuleFileCopyUtiltity constructor comment.
	 */
	public ArchiveCopyUtility() {
		super();
		setCopyAdapters(true);
	}

	/*
	 * End of code pulled from EtoolsCopyUtility to over-ride the primCopy method.
	 */

	protected void addDeferredSingleReferenceCopy(EReference reference, EObject aValue, String idSuffix, EObject aCopyContainer) {
		if (((Defaultable) aValue).isDefault())
			return;
		super.addDeferredSingleReferenceCopy(reference, aValue, idSuffix, aCopyContainer);
	}

	public ArchiveManifest copy(ArchiveManifest mf) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			mf.write(out);
			InputStream in = new ByteArrayInputStream(out.toByteArray());
			return new ArchiveManifestImpl(in);
		} catch (IOException iox) {
			//This should almost never happen, unless there is an issure with memory allocation
			throw new ArchiveRuntimeException(CommonArchiveResourceHandler.IOException_occurred_while_EXC_, iox); //$NON-NLS-1$ = "IOException occurred while copying manifest"
		}
	}

	public Archive copy(Archive anArchive) {

		//First create a copy group and copy util; copy the archive and all its resources
		CopyGroup copyGroup = new CopyGroup();
		copyGroup.setPreserveIds(true);
		prepareResourcesForCopy(anArchive, copyGroup);
		copyGroup.add(anArchive);
		this.copy(copyGroup);

		Archive copiedArchive = (Archive) getCopy(anArchive);

		finishCopy(anArchive, copiedArchive, copyGroup);
		return copiedArchive;
	}

	public ModuleFile copy(ModuleFile aModuleFile) {
		/**
		 * Timing issue; if the resources containing the bindings, extensions, and dd have not yet
		 * be loaded, force that before the copy occurs; otherwise, they will get loaded as the
		 * accessors are invoked during copy. Because they will be loaded but not copied into the
		 * new archive's context, then changes will not get saved. By forcing the load before the
		 * copy commences, the loaded resources will also be copied to the new archive
		 */
		aModuleFile.getStandardDeploymentDescriptor();

		/*
		 * RLS-8/12/2002 Hack to fix botp defect "141640-failed to install .war file with
		 * filter-mapping" The problem is that there is a transient field on the filter mapping
		 * class for the servlet name the relationship to the servlet is lazily populated the first
		 * time it is requested. If we don't trip it, then it won't get copied.
		 */

		if (aModuleFile.isWARFile()) {
			List filterMappings = ((WARFile) aModuleFile).getDeploymentDescriptor().getFilterMappings();
			for (int i = 0; i < filterMappings.size(); i++) {
				((FilterMapping) filterMappings.get(i)).getServlet();
			}
		}
		ModuleFile copied = (ModuleFile) copy((Archive) aModuleFile);

		return copied;
	}

	/*
	 * The following methods were pulled out of EtoolsCopyUtility so we can over-ride the
	 * EtoolsCopySession object with our own such that doictypes do get preserved with XML
	 * Resources.
	 */
	public void copy(CopyGroup aGroup) {
		if (aGroup != null) {
			EtoolsCopySession session = new ArchiveCopySessionUtility(this);
			session.setPreserveIds(aGroup.getPreserveIds());
			session.copy(aGroup);
			session.flush();
		}
	}

	public EObject copy(EObject aRefObject, String idSuffix) {
		EtoolsCopySession session = new ArchiveCopySessionUtility(this);
		EObject copied = session.copy(aRefObject, idSuffix);
		session.flush();
		return copied;
	}

	public Resource copy(Resource aResource, String newUri) {
		EtoolsCopySession session = new ArchiveCopySessionUtility(this);
		Resource copied = session.copy(aResource, newUri);
		session.flush();
		return copied;
	}

	protected void copyImportStrategyIfNecessary(ModuleFile aModuleFile, ModuleFile copy) {
		if (!aModuleFile.isDeploymentDescriptorSet())
			copy.setImportStrategy(aModuleFile.getImportStrategy().createImportStrategy(aModuleFile, copy));
	}

	public EObject copyObject(EObject aRefObject, String idSuffix) {
		EtoolsCopySession session = new ArchiveCopySessionUtility(this);
		EObject copied = session.copyObject(aRefObject, idSuffix);
		session.flush();
		return copied;
	}

	public static void createCopy(CopyGroup aGroup) {
		ArchiveCopyUtility utility = new ArchiveCopyUtility();
		utility.copy(aGroup);
	}

	protected void finishCopy(Archive source, Archive copy, CopyGroup group) {

		copy.setLoadStrategy(getCommonarchiveFactory().createEmptyLoadStrategy());
		copy.setExtraClasspath(source.getExtraClasspath());
		copy.setXmlEncoding(source.getXmlEncoding());
		if (source.isManifestSet()) {
			copy.setManifest(copy(source.getManifest()));
		} else {
			try {
				File manifestToCopy = source.getFile(J2EEConstants.MANIFEST_URI);
				if (manifestToCopy != null) {
					File copiedManifest = (File) getCopy(manifestToCopy);
					copiedManifest.setLoadingContainer(manifestToCopy.getLoadingContainer());
					copy.addCopy(copiedManifest);
				}
			} catch (FileNotFoundException e) {
				//Ignore
			} catch (DuplicateObjectException e) {
				//Ignore
			}
		}
		retrieveResourcesFromCopy(copy, group);

		if (source.isModuleFile())
			copyImportStrategyIfNecessary((ModuleFile) source, (ModuleFile) copy);

		List files = source.getFiles();
		for (int i = 0; i < files.size(); i++) {
			File aFile = (File) files.get(i);
			File copiedFile = (File) getCopy(aFile);
			copiedFile.setLoadingContainer(aFile.getLoadingContainer());
			if (aFile.isArchive())
				finishNestedCopy((Archive) aFile, (Archive) copiedFile);
		}
		//Notification was suspended during copy; therefore we need to make sure the files
		//In the archive get indexed
		copy.rebuildFileIndex();
	}

	protected void finishNestedCopy(Archive source, Archive copy) {

		CopyGroup group = new CopyGroup();
		prepareResourcesForCopy(source, group);
		copy(group);
		finishCopy(source, copy, group);
	}

	private CommonarchiveFactory getCommonarchiveFactory() {
		return CommonarchivePackage.eINSTANCE.getCommonarchiveFactory();
	}

	protected void prepareResourcesForCopy(Archive anArchive, CopyGroup copyGroup) {
		Iterator iter = anArchive.getLoadedMofResources().iterator();
		while (iter.hasNext()) {
			Resource resource = (Resource) iter.next();
			if (!ArchiveUtil.isJavaResource(resource))
				copyGroup.add(resource);
		}
	}

	protected void retrieveResourcesFromCopy(Archive copiedArchive, CopyGroup copyGroup) {

		List resources = copyGroup.getCopiedResources();
		for (int i = 0; i < resources.size(); i++) {
			Resource copiedResource = (Resource) resources.get(i);
			copiedArchive.addOrReplaceMofResource(copiedResource);
		}
	}
}
