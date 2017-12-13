/*
 * Created on May 1, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.archive.testutilities;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @author administrator
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class EAttributeDescriptor {
	
	EStructuralFeature feature;
	EClass metaClass;
	
	/**
	 * 
	 */
	public EAttributeDescriptor(EStructuralFeature feature, EClass metaClass) {
		this.feature = feature;
		this.metaClass = metaClass;
	}

	/**
	 * @return
	 */
	public EClass getMetaClass() {
		return metaClass;
	}

	/**
	 * @param class1
	 */
	public void setMetaClass(EClass class1) {
		metaClass = class1;
	}
	
	public boolean equals(Object object) {
		EAttributeDescriptor o = (EAttributeDescriptor)object;
		return o.getFeature() == getFeature() && o.getMetaClass() == getMetaClass();
	}

	public int hashCode() {
		return feature.hashCode() ^ metaClass.hashCode();
	}


	/**
	 * @return
	 */
	public EStructuralFeature getFeature() {
		return feature;
	}

	/**
	 * @param attribute
	 */
	public void setFeature(EAttribute attribute) {
		this.feature = attribute;
	}

}
