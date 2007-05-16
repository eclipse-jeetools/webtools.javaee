package org.eclipse.jst.javaee.applicationclient.internal.util;

import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage;
import org.eclipse.jst.javaee.core.internal.util.JEEXMLHelperImpl;

public class AppClientXMLHelperImpl extends JEEXMLHelperImpl {

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
