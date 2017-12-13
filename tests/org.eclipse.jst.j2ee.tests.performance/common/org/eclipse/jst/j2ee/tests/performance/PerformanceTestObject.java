/*
 * Created on Nov 26, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.tests.performance;

/**
 * @author Admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PerformanceTestObject {
	int tagging;
	String shortName;
	String name;
	/**
	 * 
	 */
	public PerformanceTestObject(String name,int tagging, String shortname) {
		super();
		this.tagging = tagging;
		this.name = name;
		this.shortName = shortname;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return Returns the shortName.
	 */
	public String getShortName() {
		return shortName;
	}
	/**
	 * @return Returns the tagging.
	 */
	public int getTagging() {
		return tagging;
	}
}
