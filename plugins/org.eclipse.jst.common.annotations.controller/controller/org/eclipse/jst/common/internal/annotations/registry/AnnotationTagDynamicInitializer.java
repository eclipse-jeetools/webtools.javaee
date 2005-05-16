/* ***************************************************************************************************
 * Licensed Materials - Property of IBM
 *
 * 5724-I66
 * 
 * © Copyright IBM Corporation 2004. All Rights Reserved.
 * 
 * Note to U.S. Government Users Restricted Rights:  Use, duplication or disclosure restricted by GSA 
 * ADP  Schedule Contract with IBM Corp.
 *  
 *****************************************************************************************************/
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