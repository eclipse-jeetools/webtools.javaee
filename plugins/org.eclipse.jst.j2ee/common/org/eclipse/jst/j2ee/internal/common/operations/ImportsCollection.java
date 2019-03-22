/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.common.operations;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import org.eclipse.jdt.core.Signature;

public class ImportsCollection implements Collection<String> {
	
	private Collection<String> collection;
	private CreateJavaEEArtifactTemplateModel model;
	
	public ImportsCollection(CreateJavaEEArtifactTemplateModel model) {
		collection = new TreeSet<String>();
		this.model = model;
	}

	@Override
	public boolean add(String o) {
		if (!isImportInJavaLang(o) && !isImportInSamePackage(o))
			return collection.add(o);

		return false;
	}

	@Override
	public boolean addAll(Collection<? extends String> c) {
		boolean result = false;
		
		for (String o : c)
			result = result | this.add(o);
		
		return result;
	}

	@Override
	public void clear() {
		collection.clear();
	}

	@Override
	public boolean contains(Object o) {
		return collection.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return collection.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return collection.isEmpty();
	}

	@Override
	public Iterator<String> iterator() {
		return collection.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return collection.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return collection.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return collection.retainAll(c);
	}

	@Override
	public int size() {
		return collection.size();
	}

	@Override
	public Object[] toArray() {
		return collection.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return collection.toArray(a);
	}
	
    private static boolean isImportInJavaLang(String quilifiedName) {
    	String qualifier = Signature.getQualifier(quilifiedName);
    	return qualifier.equals("java.lang");  //$NON-NLS-1$
    }
    
    private boolean isImportInSamePackage(String quilifiedName) {
    	String qualifier = Signature.getQualifier(quilifiedName);
    	return qualifier.equals(model.getJavaPackageName());
    }
    
}
