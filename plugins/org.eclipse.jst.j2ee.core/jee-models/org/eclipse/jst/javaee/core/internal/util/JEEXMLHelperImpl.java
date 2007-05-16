package org.eclipse.jst.javaee.core.internal.util;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl;
import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

public class JEEXMLHelperImpl extends XMLHelperImpl {

	public JEEXMLHelperImpl(XMLResource resource) {
		super(resource);
	}



	public EStructuralFeature getFeature(EClass class1, String namespaceURI,
			String name, boolean isElement) {
		// If not found in current namespace package, go to common package
		EStructuralFeature aFeature =  super.getFeature(class1, namespaceURI, name, isElement);
		if (aFeature == null)
			aFeature =  super.getFeature(class1, JavaeePackage.eNS_URI, name, isElement);
	return aFeature;
	}

}
