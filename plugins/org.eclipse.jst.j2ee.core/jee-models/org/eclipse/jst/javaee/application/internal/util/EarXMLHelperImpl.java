package org.eclipse.jst.javaee.application.internal.util;

import org.eclipse.emf.ecore.EPackage;
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
	
	protected String getQName(EPackage ePackage, String name, boolean mustHavePrefix)
	  {
	   String nsPrefix = getPrefix(ePackage, mustHavePrefix);
	// Not using EE5 package namespace - default jee5 namespace is used
	   if (nsPrefix.equals(ApplicationPackage.eNS_PREFIX)) 
	    {
	    	return name;
	    }
	    else return super.getQName(ePackage, name, mustHavePrefix);
	  }

}
