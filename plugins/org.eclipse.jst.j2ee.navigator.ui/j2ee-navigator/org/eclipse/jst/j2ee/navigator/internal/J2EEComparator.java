/*
 * Created on Jan 22, 2005
 * 
 * TODO To change the template for this generated file go to Window - Preferences - Java - Code
 * Style - Code Templates
 */
package org.eclipse.jst.j2ee.navigator.internal;

import java.util.Comparator;

import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.provider.J2EEJavaClassProviderHelper;


public class J2EEComparator implements Comparator {

	private J2EEViewerSorter j2eeViewSorter = new J2EEViewerSorter();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		J2EEJavaClassProviderHelper providerHelperLeftArg = null;
		J2EEJavaClassProviderHelper providerHelperRightArg = null;

		if (o1 instanceof J2EEJavaClassProviderHelper)
			providerHelperLeftArg = (J2EEJavaClassProviderHelper) o1;
		if (o2 instanceof J2EEJavaClassProviderHelper)
			providerHelperRightArg = (J2EEJavaClassProviderHelper) o2;


		/* if both arguments are J2EEJavaClassProviderHelpers */
		if (providerHelperLeftArg != null && providerHelperRightArg != null)
			return providerHelperLeftArg.getText().compareTo(providerHelperRightArg.getText());
		/* if exactly one of the arguments are J2EEJavaClassProviderHelpers */
		else if (providerHelperLeftArg != null && o2 instanceof EnterpriseBean)
			return 1;
		else if (providerHelperRightArg != null && o1 instanceof EnterpriseBean)
			return -1;
		return j2eeViewSorter.compare(null, o1, o2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return obj instanceof J2EEComparator;
	}

}