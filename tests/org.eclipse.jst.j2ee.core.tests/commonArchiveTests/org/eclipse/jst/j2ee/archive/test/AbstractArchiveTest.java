package org.eclipse.jst.j2ee.archive.test;

 /*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
/**
 * Insert the type's description here.
 * Creation date: (02/07/01 1:48:18 PM)
 * @author: Administrator
 */
public class AbstractArchiveTest extends junit.framework.TestCase {
	private final static String copyright = "(c) Copyright IBM Corporation 2001.";//$NON-NLS-1$
/**
 * AbstractArchiveTest constructor comment.
 * @param name java.lang.String
 */
public AbstractArchiveTest(String name) {
	super(name);
}
public Set getAllUnresolvedProxies(EObject root) {
	Set visited = new HashSet();
	Set proxies = new HashSet();
	getAllUnresolvedProxies(root, proxies, visited, root.eResource());
	return proxies;
}
public void getAllUnresolvedProxies(EObject object, Set proxies, Set visitedObjects, Resource originalResource) {

	if (visitedObjects.contains(object))
		return;
	else
		visitedObjects.add(object);

	if (object.eResource() != originalResource) {
		if (((org.eclipse.emf.ecore.InternalEObject )object).eIsProxy()) {
			proxies.add(object);
		}
		return;
	}

	List refs = object.eClass().getEAllReferences();
	if (refs != null) {
		Iterator ir = refs.iterator();
		while (ir.hasNext()) {
			EReference r = (EReference) ir.next();
			Object value = object.eGet(r);
			if (value == null)
				continue;

			if (r.isMany()) {
				Collection c = (Collection) value;
				Iterator ir2 = c.iterator();
				while (ir2.hasNext()) {
					EObject refObj = (EObject) ir2.next();
					getAllUnresolvedProxies(refObj, proxies, visitedObjects, originalResource);
				}
			} else
				getAllUnresolvedProxies((EObject) value, proxies, visitedObjects, originalResource);
		}
	}
}
public static CommonarchiveFactory getArchiveFactory() {
	return CommonarchivePackage.eINSTANCE.getCommonarchiveFactory();
}
public void verifyProxies(EObject root) {

	Set proxies = getAllUnresolvedProxies(root);
	assertTrue("Some proxies could not be resolved", proxies.isEmpty());
}
}
