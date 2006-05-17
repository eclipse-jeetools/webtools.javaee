/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.adapters;
/*
 *  $RCSfile: BeaninfoModelSynchronizer.java,v $
 *  $Revision: 1.10 $  $Date: 2006/05/17 20:13:00 $ 
 */

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.*;

import org.eclipse.jem.workbench.utility.JavaModelListener;
/**
 * This class listens for changes to the java model and flushs the
 * appropriate class introspection.
 */

public class BeaninfoModelSynchronizer extends JavaModelListener {
	protected BeaninfoAdapterFactory fAdapterFactory;
	protected IJavaProject fProject; // The project this listener is opened on.
	private static final IPath BEANINFOCONFIG_PATH = new Path(BeaninfoNature.P_BEANINFO_SEARCH_PATH);	//$NON-NLS-1$	

	public BeaninfoModelSynchronizer(BeaninfoAdapterFactory aFactory, IJavaProject aProject) {
		super(ElementChangedEvent.POST_CHANGE);
		fAdapterFactory = aFactory;
		fProject = aProject;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.adapters.jdom.JavaModelListener#getJavaProject()
	 */
	protected IJavaProject getJavaProject() {
		return fProject;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.adapters.jdom.JavaModelListener#isAlsoClasspathChange(org.eclipse.core.runtime.IPath)
	 */
	protected boolean isAlsoClasspathChange(IPath path) {
		return path.equals(BEANINFOCONFIG_PATH);
	}
	
	/**
	 * Stop the synchronizer from listening to any more changes.
	 */
	public void stopSynchronizer(boolean clearResults) {
		JavaCore.removeElementChangedListener(this);
		getAdapterFactory().closeAll(clearResults);
	}

	public BeaninfoAdapterFactory getAdapterFactory() {
		return fAdapterFactory;
	}

	protected void processJavaElementChanged(IJavaProject element, IJavaElementDelta delta) {
		if (isInClasspath(element)) {
			if (delta.getKind() == IJavaElementDelta.REMOVED || delta.getKind() == IJavaElementDelta.ADDED) {
				// Don't need to do anything for delete/close/add/open of main project because there is much more that needs to
				// be done by BeaninfoNature on project close/delete, so nature listens for this and does the appropriate cleanup.			
				if (!element.equals(fProject)) {
					// However, all other projects are required projects and if they are deleted/closed/added/opened when need to do
					// a full flush because we don't know any of the state, whether they are still there or not.
					getAdapterFactory().markAllStale();
				}
				return;
			} else if (isClasspathResourceChange(delta)) {
				getAdapterFactory().markAllStale(); // The .classpath file (or .beaninfoconfig) itself in SOME DEPENDENT PROJECT has changed. 
				return;
			}
			processChildren(element, delta);
		}
	}

	/**
	 * Handle the change for a single element, children will be handled separately.
	 * If a working copy, then ignore it because we don't care about changes until
	 * they are committed. Else, if the CU has changed content then mark all of the
	 * types in this CU (such as inner classes) as stale.
	 * If it is not a content change then process the children.
	 */
	protected void processJavaElementChanged(ICompilationUnit element, IJavaElementDelta delta) {
		switch (delta.getKind()) {
			case IJavaElementDelta.CHANGED :
				// A file save had occurred. It doesn't matter if currently working copy or not.
				// It means something has changed to the file on disk, but don't know what.
				if ((delta.getFlags() & IJavaElementDelta.F_PRIMARY_RESOURCE) != 0) {
					getAdapterFactory().markStaleIntrospectionPlusInner(getFullNameFromElement(element), false);	// Flush everything, including inner classes.
				}						
				
				break;
			case IJavaElementDelta.ADDED:
			case IJavaElementDelta.REMOVED:
				// Need to know for add because we optimize the beaninfo such that once found as undefined, it won't
				// introspect again until we mark it stale. So we need to mark it stale to refresh it.
				
				// It doesn't matter if totally removed or just moved somewhere else, we will clear out
				// adapter because there could be a rename which would be a different class.
				// Currently the element is already deleted or added and there is no way to find the types in the unit to flush.
				// So instead we ask factory to flush all it any that start with it plus for inner classes.				
				getAdapterFactory().markStaleIntrospectionPlusInner(getFullNameFromElement(element), true);	// Flush everything, including inner classes.
				break;
		}
	}

	/**
	 * Handle the change for a single element, children will be handled separately.
		 */
	protected void processJavaElementChanged(IClassFile element, IJavaElementDelta delta) {
		if (delta.getKind() == IJavaElementDelta.REMOVED) {
			// It doesn't matter if totally removed or just moved somewhere else, we will clear out and remove the
			// adapter because there could be a rename which would be a different class.
			// Currently the element is already deleted and there is no way to find the types in the unit to remove.
			// So instead we ask factory to remove all it any that start with it plus for inner classes.
			getAdapterFactory().markStaleIntrospectionPlusInner(getFullNameFromElement(element), true);
			return; // Since the classfile was removed we don't need to process the children (actually the children list will be empty
		}
		IJavaElementDelta[] children = delta.getAffectedChildren();
		for (int ii = 0; ii < children.length; ii++) {
			processDelta(children[ii]);
		}
	}

	protected String getFullNameFromElement(IJavaElement element) {
		String name = element.getElementName();		
		if (!(element instanceof ICompilationUnit || element instanceof IClassFile))
			return name;	// Shouldn't be here
		
		// remove extension.
		int periodNdx = name.lastIndexOf('.');
		if (periodNdx == -1)
			return name;	// Shouldn't be here. There should be an extension
					
		String typeName = null;
		String parentName = element.getParent().getElementName();
		if (parentName == null || parentName.length() == 0)
			typeName = name.substring(0, periodNdx); // In default package
		else
			typeName = parentName + "." + name.substring(0, periodNdx); //$NON-NLS-1$
								
		return typeName;
	}

	/**
	 * Handle the change for a single element, children will be handled separately.
	 * If the classpath has changed, mark all as stale because we don't know what
	 * has changed. Things that were in the path may no longer be in the path, or
	 * the order was changed, which could affect the introspection.
	 */
	protected void processJavaElementChanged(IPackageFragmentRoot element, IJavaElementDelta delta) {
		if (isClassPathChange(delta))
			fAdapterFactory.markAllStale();
		else
			super.processJavaElementChanged(element, delta);
	}
	
	protected void processJavaElementChanged(IPackageFragment element, IJavaElementDelta delta) {
		switch (delta.getKind()) {
			case IJavaElementDelta.ADDED:
				break;	// Don't need to do anything on a new package. If this was from a new fragroot, we would recycle already. Otherwise, it will find this package on the first use.
			case IJavaElementDelta.REMOVED:
				if (delta.getAffectedChildren().length == 0)
					fAdapterFactory.markAllStale();
				break;
			default :
				super.processJavaElementChanged(element, delta);
		}
	}
	

	/**
	 * Handle the change for a single element, children will be handled separately.
	 * Something about the type has changed. If it was removed (not a move), then close the
	 * adapter too.
	 */
	protected void processJavaElementChanged(IType element, IJavaElementDelta delta) {
		if (delta.getKind() == IJavaElementDelta.REMOVED) {
			// Close it out. Doesn't matter if moved_to, that would be a rename which requires brand new class.
			// We can't actually get rid of the beaninfo adapter because it may be asked for again
			// just to see if not defined. It may also come back later and we want to know about
			// it to recycle the vm.
			getAdapterFactory().markStaleIntrospection(element.getFullyQualifiedName(), true);	
		} else
			getAdapterFactory().markStaleIntrospection(element.getFullyQualifiedName(), false); // Just mark it stale
		processChildren(element, delta);
	}

	public String toString() {
		return super.toString()+" "+fProject.getElementName(); //$NON-NLS-1$
	}
}
