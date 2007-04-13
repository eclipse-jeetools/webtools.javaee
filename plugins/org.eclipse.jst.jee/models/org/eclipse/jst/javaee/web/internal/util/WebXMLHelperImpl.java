package org.eclipse.jst.javaee.web.internal.util;

import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl;
import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

public class WebXMLHelperImpl extends XMLHelperImpl {

	public WebXMLHelperImpl(XMLResource resource) {
		super(resource);
		// TODO Auto-generated constructor stub
	}

	public String getURI(String prefix) {
		if (prefix != null && prefix.equals(""))
			return WebPackage.eNS_URI;
		else
			return super.getURI(prefix);
	}


}
