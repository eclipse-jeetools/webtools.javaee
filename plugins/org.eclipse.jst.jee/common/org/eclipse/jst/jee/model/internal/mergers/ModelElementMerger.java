/***********************************************************************
 * Copyright (c) 2008, 2023 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.internal.mergers;

import java.util.List;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * Model element merger is bease class for Java EE mergers. 
 * It contains the base and toMerge elements.
 * 
 * Base element is the main element that values will be taken with highest priority.
 * toMerge element will give all of his extra values to base.
 * 
 * @author Dimitar Giormov
 */
public abstract class ModelElementMerger implements IMerger {
	
	public static int ADD = 0x1;
	
	public static int CHANGE = 0x2;
	
	public static int REMOVE = 0x4;
	
	public static int COPY = 0x8;

	JavaEEObject base;
	JavaEEObject toMerger;

	private int kind;

	
	/**
	 * Constructor of the class.
	 * 
	 * @param _base element is the main element that values will be taken with highest priority.
	 * @param _toMerge element will give all of his extra values to base.
	 * @param _kind
	 */
	public ModelElementMerger(JavaEEObject _base, JavaEEObject _toMerge, int _kind) {
		base = _base;
		toMerger = _toMerge;
		kind = _kind;
	}

	/**
	 * Returns the base element, which is also the merged result.
	 * 
	 * @return base element.
	 */
	protected JavaEEObject getBase() {
		return base;
	}

	/**
	 * 
	 * @return toMerge element, which will be merged in base.
	 */
	protected JavaEEObject getToMerge() {
		return toMerger;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.javaee.ejb.model.mergers.Merger#process()
	 */
	@Override
	public abstract List process() throws ModelException;
	
	public int getKind() {
		return kind;
	}
	
}
