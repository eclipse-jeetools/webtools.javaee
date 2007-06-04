package org.eclipse.jst.javaee.applicationclient.internal.util;

import org.eclipse.emf.ecore.EPackage;
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
	protected String getQName(EPackage ePackage, String name, boolean mustHavePrefix)
	  {
	   String nsPrefix = getPrefix(ePackage, mustHavePrefix);
	   // Not using EE5 package namespace - default jee5 namespace is used
	   if (nsPrefix.equals(ApplicationclientPackage.eNS_PREFIX)) 
	    {
	    	return name;
	    }
	    else return super.getQName(ePackage, name, mustHavePrefix);
	  }

}
