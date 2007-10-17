package org.eclipse.jst.servlet.ui.internal.wizard;

import java.util.ArrayList;
import java.util.List;

public class ServletNamesArrayTableWizardSectionCallback {

	private List<String> servletNames;
	
	
	public ServletNamesArrayTableWizardSectionCallback(List<String> servletsList) {
	    if (servletsList != null) {
	        servletNames = servletsList;
	    } else {
	        servletNames = new ArrayList<String>();
	    }
	}

	/**
     * Trims the text values. 
     */
	public boolean validate(String text) {
	    if (text != null) {
	        String newServlet = text.trim();
	        if (servletNames.contains(newServlet)) return true;
	    }
		return false;
	}
	
	public List getServlets() {
	    return servletNames;
	}
	
	public String[] getServletNames() {
	    String[] ret = new String[servletNames.size()];
	    return servletNames.toArray(ret);
	}

}
