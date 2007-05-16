package org.eclipse.jst.javaee.application.internal.util;

import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage;
import org.eclipse.jst.javaee.core.internal.util.JEEXMLHelperImpl;

public class EarXMLHelperImpl extends JEEXMLHelperImpl {

	public EarXMLHelperImpl(XMLResource resource) {
		super(resource);
		// TODO Auto-generated constructor stub
	}

	
	public String getURI(String prefix) {
		if (prefix != null && prefix.equals(""))
			return ApplicationPackage.eNS_URI;
		else
			return super.getURI(prefix);
	}

}
