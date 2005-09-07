/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.common.internal.annotations.registry;

/**
 * This method will be called by the AnnotationTagRegistry
 * when it is time to register the tags for a given
 * TagSet.  An AnnotationTagDynamicInitializer defined
 * using the annotationTagDynamicInitializer.
 * 
 * @see com.ibm.wtp.annotations.registry.AnnotationTagRegistry
 */
public interface AnnotationTagDynamicInitializer {
	void registerTags();
}