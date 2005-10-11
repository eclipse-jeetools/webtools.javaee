/*
 * Created on Mar 25, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.servlet.tests.bvt;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.Platform;

/**
 * @author jsholl
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AutomatedBVTEclipse extends AutomatedBVT {
	
	public AutomatedBVTEclipse(){
		super();
		URL url = Platform.getBundle("org.eclipse.jst.servlet.tests").getEntry("/"); //$NON-NLS-1$ //$NON-NLS-2$
        try {
        	AutomatedBVT.baseDirectory = Platform.asLocalURL(url).getFile() + "WARImportTests"+ java.io.File.separatorChar; //$NON-NLS-1$
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
