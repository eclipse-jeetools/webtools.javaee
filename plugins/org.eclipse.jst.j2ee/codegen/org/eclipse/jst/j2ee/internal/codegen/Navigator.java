/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.codegen;



import java.util.HashMap;
import java.util.Map;

/**
 * The Navigator class provides a simple cache for values that for efficiency reasons should be
 * cached. Note that typically there is one source navigator {@link ISourceContext#getNavigator()}
 * and one target navigator {@link ITargetContext#getNavigator()}for the generation run. The same
 * source and target navigators are shared by all generators. This implies two things:
 * <ol>
 * <li>If a value is referenced many times by different generators, it may be a candidate to be
 * cached.
 * <li>Care must be taken when calculating keys for the cache to avoid key collisions. Using unique
 * values from the source or target model is a good idea.
 * </ol>
 * <p>
 * Do not cache unless there is good reason. Good reasons may include:
 * <ul>
 * <li>The value is hard to navigate to in the source or target model.
 * <li>The value can only be navigated to or calculated at certain times.
 * <li>The value is expensive to calculate. </eul>
 */
public class Navigator {
	private Map fDictionary = null;

	/**
	 * The Navigator default constructor.
	 */
	public Navigator() {
	}

	/**
	 * Returns the cached value for the given key.
	 * 
	 * @param key
	 *            java.lang.String
	 * @return java.lang.Object
	 */
	public Object getCookie(String key) {
		return getDictionary().get(key);
	}

	/**
	 * Returns a handle to the cache map.
	 * 
	 * @return java.util.Map
	 */
	public Map getDictionary() {
		if (fDictionary == null)
			fDictionary = new HashMap();
		return fDictionary;
	}

	/**
	 * Sets the object into the cache at the specified key.
	 * 
	 * @param key
	 *            java.lang.String
	 * @param mofObject
	 *            java.lang.String
	 */
	public void setCookie(String key, Object mofObject) {
		getDictionary().put(key, mofObject);
	}
}