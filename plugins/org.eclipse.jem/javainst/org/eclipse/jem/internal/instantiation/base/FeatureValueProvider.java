/*
 * Created on 20-Jan-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jem.internal.instantiation.base;

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @author JoeWinchester
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface FeatureValueProvider {
	
	public interface Visitor{
		void isSet(EStructuralFeature feature, Object value);
	}	

	void visitSetFeatures(Visitor aVisitor);
}
