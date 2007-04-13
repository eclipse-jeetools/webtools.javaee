package org.eclipse.jst.javaee.core;

import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.impl.XMLLoadImpl;
import org.xml.sax.helpers.DefaultHandler;

public class JEEXMLLoadImpl extends XMLLoadImpl {

	protected DefaultHandler makeDefaultHandler() {
		
		 return new JEESAXXMLHandler(resource, helper, options);
	}

	public JEEXMLLoadImpl(XMLHelper helper) {
		super(helper);
	}

}
