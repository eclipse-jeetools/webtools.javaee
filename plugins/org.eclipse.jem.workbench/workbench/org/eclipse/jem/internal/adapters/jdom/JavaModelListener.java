package org.eclipse.jem.internal.adapters.jdom;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaModelListener.java,v $
 *  $Revision: 1.2 $  $Date: 2004/01/13 16:17:42 $ 
 */

import org.eclipse.jdt.core.*;

/**
 * Insert the type's description here.
 * Creation date: (10/31/2000 1:13:12 PM)
 * @author: Administrator
 */
public class JavaModelListener implements IElementChangedListener {
	
/**
 * JavaModelListener constructor comment.
 */
public JavaModelListener() {
	super();
	JavaCore.addElementChangedListener(this, ElementChangedEvent.POST_CHANGE | ElementChangedEvent.POST_RECONCILE);
}
/**
 * One or more attributes of one or more elements maintained by
 * the Java model have changed. The specific details of the change
 * are described by the given <code>ElementChangedEvent</code>.
 *
 * @see ElementChangedEvent
 */
public void elementChanged(ElementChangedEvent event) {
	processDelta((IJavaElementDelta) event.getSource());
}
/**
 * Generically dispatch the children of the delta.
 *
 */
protected void processChildren(IJavaElement element, IJavaElementDelta delta) {
	IJavaElementDelta[] children = delta.getAffectedChildren();
	for (int i = 0; i < children.length; i++) {
		processDelta(children[i]);
	}
}
/**
 * Source context has been changed.
 * Creation date: (8/17/2001 3:58:31 PM)
 * @param param org.eclipse.jdt.core.IJavaElementDelta
 */
protected void processContentChanged(IJavaElementDelta delta) {
	  // override to implement specific behavior
}
/**
 * Dispatch the detailed handling of an element changed event.
 *
 * @see ElementChangedEvent
 */
public void processDelta(IJavaElementDelta delta) {
	IJavaElement element = delta.getElement();
	
	switch (element.getElementType()) {
		case IJavaElement.JAVA_MODEL :
			processJavaElementChanged((IJavaModel) element, delta);
			break;
		case IJavaElement.JAVA_PROJECT :
			processJavaElementChanged((IJavaProject) element, delta);
			break;
		case IJavaElement.PACKAGE_FRAGMENT_ROOT :
			processJavaElementChanged((IPackageFragmentRoot) element, delta);
			break;
		case IJavaElement.PACKAGE_FRAGMENT :
			processJavaElementChanged((IPackageFragment) element, delta);
			break;
		case IJavaElement.COMPILATION_UNIT :
			processJavaElementChanged((ICompilationUnit) element, delta);
			processContentChanged(delta) ;   
			break;
		case IJavaElement.CLASS_FILE :
			processJavaElementChanged((IClassFile) element, delta);
			break;
		case IJavaElement.TYPE :
			processJavaElementChanged((IType) element, delta);
			break;
		// Note: if we are to update the Method/Field adapters, we should process the
		//       IJavaElement.METHOD and IJavaElement.FIELD 
	}	
}
/**
 * Handle the change for a single element, children will be handled separately.
 *
 */
protected void processJavaElementChanged(IClassFile element, IJavaElementDelta delta) {
	// override to implement specific behavior
}
/**
 * Handle the change for a single element, children will be handled separately.
 *
 */
protected void processJavaElementChanged(ICompilationUnit element, IJavaElementDelta delta) {
	processChildren(element, delta);
}
/**
 * Handle the change for a single element, children will be handled separately.
 *
 */
protected void processJavaElementChanged(IJavaModel element, IJavaElementDelta delta) {
	processChildren(element, delta);
}
/**
 * Handle the change for a single element, children will be handled separately.
 *
 */
protected void processJavaElementChanged(IJavaProject element, IJavaElementDelta delta) {
	processChildren(element, delta);
}
/**
 * Handle the change for a single element, children will be handled separately.
 *
 */
protected void processJavaElementChanged(IPackageFragment element, IJavaElementDelta delta) {
	processChildren(element, delta);
}
/**
 * Handle the change for a single element, children will be handled separately.
 *
 */
protected void processJavaElementChanged(IPackageFragmentRoot element, IJavaElementDelta delta) {
	processChildren(element, delta);
}
/**
 * Handle the change for a single element, children will be handled separately.
 *
 */
protected void processJavaElementChanged(IType element, IJavaElementDelta delta) {
	// override to implement specific behavior
}
}
