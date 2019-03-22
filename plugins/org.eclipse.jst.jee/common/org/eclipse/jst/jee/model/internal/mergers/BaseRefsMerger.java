/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.internal.mergers;

import java.util.ArrayList;
import java.util.List;

/**
 * Base abstract class for JNDI mergers
 * 
 * @author Dimitar Giormov
 */
public abstract class BaseRefsMerger extends ModelElementsMerger {

	/**
	 * Constructor for the class
	 * 
	 * @param baseList -
	 *            base list of references that have higher priority and are
	 *            result of the merge.
	 * @param toMergeList -
	 *            toMerge list of references
	 */
	public BaseRefsMerger(List baseList, List toMergeList) {
		super(baseList, toMergeList);
	}

	/**
	 * When an entry with the same name is present in both lists, missing valuse
	 * in base are filled by toMerge element.
	 * 
	 * @param baseList -
	 *            base list of references
	 * @param toMergeList -
	 *            toMerge list of references
	 */
	protected abstract void copyMissingPropertesInBase(Object base, Object toMerge);

	/**
	 * @param itListBase
	 * @param itListToMerge
	 * @return
	 */
	protected List mergeIngectionTargets(List itListBase, List itListToMerge) {
		// TODO
		List result = new ArrayList();
		return result;
	}

	/**
	 * @param descrListBase
	 * @param descrListToMerge
	 * @return
	 */
	protected List mergeDescriptions(List descrListBase, List descrListToMerge) {
		List result = new ArrayList();
		return result;
	}

}
