/*
 * Created on Jan 5, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers;

import java.io.FileInputStream;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

import junit.framework.Assert;

import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author mdelder
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 */
public abstract class ModuleExportDataModelVerifier extends JEEExportDataModelVerifier {
	@Override
	public void verify(IDataModel model) throws Exception {
		super.verify(model);
	
		if(checkManifest()){
			/**
			 * The manifest must be the first file in the archive.
			 */
			String archivePath = model.getStringProperty(IJ2EEComponentExportDataModelProperties.ARCHIVE_DESTINATION);
			
			FileInputStream fIn = null;
			Manifest mf = null;
			try {
				fIn = new FileInputStream(archivePath);
				JarInputStream jarIn = new JarInputStream(fIn);
				mf = jarIn.getManifest();
				
			} finally{
				if(fIn != null){
					fIn.close();
				}
			}
			if(null == mf){
				Assert.assertNotNull(mf);
			}
		}
	}

	protected boolean checkManifest() {
		return true;
	}
}
