package org.eclipse.jst.javaee.applicationclient.internal.util;

import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl;
import org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage;

public class AppClientXMLHelperImpl extends XMLHelperImpl {

	public AppClientXMLHelperImpl(XMLResource resource) {
		super(resource);
		// TODO Auto-generated constructor stub
	}

	
	public String getURI(String prefix) {
		if (prefix != null && prefix.equals(""))
			return ApplicationclientPackage.eNS_URI;
		else
			return super.getURI(prefix);
	}

}
