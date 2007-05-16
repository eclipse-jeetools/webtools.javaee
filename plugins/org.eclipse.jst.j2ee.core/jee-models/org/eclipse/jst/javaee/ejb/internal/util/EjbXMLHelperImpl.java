package org.eclipse.jst.javaee.ejb.internal.util;

import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jst.javaee.core.internal.util.JEEXMLHelperImpl;
import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

public class EjbXMLHelperImpl extends JEEXMLHelperImpl {

	public EjbXMLHelperImpl(XMLResource resource) {
		super(resource);
		// TODO Auto-generated constructor stub
	}

	
	public String getURI(String prefix) {
		if (prefix != null && prefix.equals(""))
			return EjbPackage.eNS_URI;
		else
			return super.getURI(prefix);
	}


}
